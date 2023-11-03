package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.SimpleDateFormat;

import beans.UsuarioDTO;
import dao.DAOFactory;
import interfaces.UsuarioDAO;

/**
 * Servlet implementation class AccesoServlet
 */
@WebServlet(name="Access Controller", urlPatterns = {"/acceso"})
public class AccesoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccesoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Get");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String opcion =request.getParameter("opcion");
		System.out.println(" opcion -->" +opcion);
		 
		switch (opcion) {
		case "log":  
					filtroIngresar(request,response); 
					break;
		case "reg":
					registrarNuevo(request, response);
					break;
		case "logout":
					cerrarSesion(request,response);
					break;
	 
		default:
			throw new IllegalArgumentException("Unexpected value: " + opcion);
		}
	}
	
	private void filtroIngresar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		System.out.println("Post");
		//Variables
		String mensaje="", url="";
		
		//Entrada de datos
		String correo = request.getParameter("txtCorreo");
		String clave = request.getParameter("txtClave");

		System.out.println("Correo: "+correo);
		System.out.println("Contrasena: "+clave);
		
		//Proceso
		DAOFactory fabrica=  DAOFactory.getDAOFactory(DAOFactory.MYSQL);	
		UsuarioDTO u = fabrica.getUsuarioDAO().validar(correo, clave);
		
		if (u != null) {
	        url = "home.jsp";
	        
	        HttpSession miSession = request.getSession();
	        System.out.println("ID Session: " + miSession.getId());
	        System.out.println("Fecha: " + new SimpleDateFormat().format(miSession.getCreationTime()));
	        System.out.println("Duración: " + miSession.getMaxInactiveInterval());
	        
	        request.getSession().setAttribute("mensaje", mensaje);
	        request.getSession().setAttribute("datosusu", u);
	        
		} else {
	        url = "accesoIniciarSesion.jsp";
	    }
		
		request.getRequestDispatcher(url).forward(request, response);
		// Usar localstorege
		// Guardar carrito en sesion
	}
	
	private void registrarNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String mensaje = "";
		String url;

		String correo = request.getParameter("txtCorreo");
		String nombre = request.getParameter("txtNombre");
		String apellido = request.getParameter("txtApellido");
		String contrasena = request.getParameter("txtClave");
		String direccion = request.getParameter("txtDireccion");
		int telefono = Integer.parseInt(request.getParameter("txtTelefono"));

		UsuarioDTO u = new UsuarioDTO();
		u.setCorreo(correo);
		u.setNombre(nombre);
		u.setApellido(apellido);
		u.setContrasena(contrasena);
		u.setDireccion(direccion);
		u.setTelefono(telefono);

		System.out.println("usuario: " + correo);
		System.out.println("nombre: " + nombre);
		System.out.println("apellido: " + apellido);
		System.out.println("Contrasena: " + contrasena);
		System.out.println("Direccion: " + direccion);
		System.out.println("Telefono: " + telefono);

		DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		UsuarioDAO dao = fabrica.getUsuarioDAO();

		int ok = dao.registrar(u);

		if (ok == 0) {
			mensaje += "Error al registrar los datos, revise sus datos porfavor:";
			url = "/accesoRegistro.jsp";
		} else {
			mensaje += " <script> alert('" + "Registro del usuario: <strong>" + nombre
					+ "</strong> fue correcto, ahora puede ingresar al sistema." + "') </script>";
			url = "/accesoIniciarSesion.jsp";
		}

		request.setAttribute("mensaje", mensaje);
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Cerrando la sesion: " + request.getSession().getId());
		request.getSession().invalidate();
		response.sendRedirect("accesoIniciarSesion.jsp");
	}

}
