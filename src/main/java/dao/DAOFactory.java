package dao;

import interfaces.CabeceraVentasDAO;
import interfaces.DetalleVentasDAO;
import interfaces.EstadoDAO;
import interfaces.ProductoDAO;
import interfaces.TipoDAO;
import interfaces.UsuarioDAO;

public abstract class DAOFactory {
	public static final int MYSQL =1;
	public static final int SQL=2;
	
	public abstract CabeceraVentasDAO getCabeceraVentasDAO();
	public abstract DetalleVentasDAO getDetalleVentasDAO();
	public abstract EstadoDAO getEstadoDAO();
	public abstract ProductoDAO getProductoDAO();
	public abstract TipoDAO getTipoDAO();
	public abstract UsuarioDAO getUsuarioDAO();
	
	public static DAOFactory getDAOFactory(int qBD) {
		switch (qBD) {
		case MYSQL:  
			return new MySQLDAOFactory();
		default:
			return null;
		}
		
	}
	
}
