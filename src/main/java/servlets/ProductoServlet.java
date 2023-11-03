package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import beans.ProductoDTO;

import java.io.IOException;
import java.util.ArrayList;

import dao.DAOFactory;

/**
 * Servlet implementation class ClienteCatalogoServlet
 */
@WebServlet(description = "Control de catalogo productos", urlPatterns = { "/producto" })
public class ProductoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String menu = request.getParameter("menu");
		String accion = request.getParameter("accion");
		if (menu.equals("Inicio")) {
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
		if (menu.equals("Catalogo")) {
			switch (accion) {
			case "Listar":
				DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
				ArrayList<ProductoDTO> lstProductos = fabrica.getProductoDAO().listado();
				request.setAttribute("lstProductos", lstProductos);
				request.getRequestDispatcher("catalogo.jsp").forward(request, response);
				break;

			}

		}
	}

}
