package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import beans.CabeceraBoletaDTO;
import beans.DetalleBoletaDTO;
import beans.CarritoDTO;
import beans.ProductoDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import interfaces.CabeceraVentasDAO;
import dao.DAOFactory;
import interfaces.ProductoDAO;

/**
 * Servlet implementation class BuyServlet
 */
@WebServlet(name = "control de compras", urlPatterns = { "/compra" })
public class CompraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Obtenemos la fabrica DAO
	DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
	ProductoDAO dao = fabrica.getProductoDAO();
	List<ProductoDTO> productos = new ArrayList<ProductoDTO>();
	List<CarritoDTO> listaCarrito = new ArrayList<CarritoDTO>();
	int item = 0;
	double totalPagar = 0.0;
	int cantidad = 1;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CompraServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opcion = request.getParameter("opcion");
		System.out.println(" opcion -->" + opcion);

		switch (opcion) {
		case "addCar":
			agregarCarrito(request, response);
			break;
		case "carrito":
			carrito(request, response);
			break;

		case "eliCar":
			eliminar(request, response);
			break;

		case "actCar":
			actualizarCarro(request, response);
			break;

		case "generarCompra":
			generarCompra(request, response);
			break;
			
		case "his":
			historial(request, response);
			break;

		default:
			throw new IllegalArgumentException("Unexpected value: " + opcion);
		}
	}

	private void generarCompra(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));

			// cargar datos a la cabecera
			CabeceraBoletaDTO cabDTO = new CabeceraBoletaDTO();
			cabDTO.setIdUsuario(idUsuario);
			cabDTO.setTotal(totalPagar);

			// Cargar datos al detalle
			List<DetalleBoletaDTO> listaDetalle = new ArrayList<DetalleBoletaDTO>();

			for (CarritoDTO carritoDTO : listaCarrito) {

				DetalleBoletaDTO detDTO = new DetalleBoletaDTO();
				detDTO.setCantidad(carritoDTO.getCantidad());
				detDTO.setIdProducto(carritoDTO.getIdProducto());
				detDTO.setPrecio(carritoDTO.getPrecioCompra());

				listaDetalle.add(detDTO);
			}

			DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			CabeceraVentasDAO dao = fabrica.getCabeceraVentasDAO();

			dao.registrar(cabDTO, listaDetalle);

			// Inicializacion de varibles luego de comprar para limpiarlas

			listaCarrito = new ArrayList<CarritoDTO>();
			item = 0;
			totalPagar = 0.0;
			cantidad = 1;

			request.getRequestDispatcher("compraConfirmada.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error al generar la compra");
		}
	}

	private void actualizarCarro(HttpServletRequest request, HttpServletResponse response) {

		// Entradas
		int codigo = Integer.parseInt(request.getParameter("cod"));
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));

		for (int i = 0; i < listaCarrito.size(); i++) {

			if (listaCarrito.get(i).getIdProducto() == codigo) {
				listaCarrito.get(i).setCantidad(cantidad);
				double st = listaCarrito.get(i).getPrecioCompra() * cantidad;
				listaCarrito.get(i).setSubTotal(st);
			}
		}
	}

	private void carrito(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		totalPagar = 0.0;
		// Salidas
		request.setAttribute("listaCarrito", listaCarrito);

		for (int i = 0; i < listaCarrito.size(); i++) {
			totalPagar = totalPagar + listaCarrito.get(i).getSubTotal();
		}
		request.setAttribute("totalPagar", totalPagar);
		request.getRequestDispatcher("carrito.jsp").forward(request, response);

	}

	private void agregarCarrito(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Entradas
		int codigo = Integer.parseInt(request.getParameter("cod"));

		ProductoDTO producto = dao.buscar(codigo);

		int pos = 0;
		cantidad = 1;

		if (listaCarrito.size() > 0) {

			for (int i = 0; i < listaCarrito.size(); i++) {
				if (codigo == listaCarrito.get(i).getIdProducto()) {
					pos = i;
				}
			}
			if (codigo == listaCarrito.get(pos).getIdProducto()) {
				cantidad = listaCarrito.get(pos).getCantidad() + cantidad;
				double subtotal = listaCarrito.get(pos).getPrecioCompra() * cantidad;
				listaCarrito.get(pos).setCantidad(cantidad);
				listaCarrito.get(pos).setSubTotal(subtotal);

			} else {
				item = item + 1;
				CarritoDTO car = new CarritoDTO();
				car.setItem(item);
				car.setIdProducto(producto.getIdProducto());
				car.setDescripcion(producto.getNomProducto());
				car.setPrecioCompra(producto.getPrecio());
				car.setCantidad(cantidad);
				car.setSubTotal(cantidad * producto.getPrecio());
				listaCarrito.add(car);
			}

		} else {

			item = item + 1;
			CarritoDTO car = new CarritoDTO();
			car.setItem(item);
			car.setIdProducto(producto.getIdProducto());
			car.setDescripcion(producto.getNomProducto());
			car.setPrecioCompra(producto.getPrecio());
			car.setCantidad(cantidad);
			car.setSubTotal(cantidad * producto.getPrecio());
			listaCarrito.add(car);
		}
		// Salidas
		ArrayList<ProductoDTO> lista = dao.listado();
		request.setAttribute("lstProductos", lista);

		// Salidas
		request.setAttribute("contador", listaCarrito.size());
		request.getRequestDispatcher("producto?menu=Catalogo&accion=Listar").forward(request, response);

	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// variables
		String mensaje = "";
		String url;

		// Entradas
		int codigo = Integer.parseInt(request.getParameter("cod"));
		for (int i = 0; i < listaCarrito.size(); i++) {

			if (listaCarrito.get(i).getIdProducto() == codigo) {
				listaCarrito.remove(i);
			}
		}

		carrito(request, response);

	}

	private void historial(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("cod"));

		DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		CabeceraVentasDAO dao = fabrica.getCabeceraVentasDAO();

		ArrayList<CabeceraBoletaDTO> lista = dao.listadoPorID(id);

		request.setAttribute("lstHistory", lista);
		request.getRequestDispatcher("userHistory.jsp").forward(request, response);
	}

}
