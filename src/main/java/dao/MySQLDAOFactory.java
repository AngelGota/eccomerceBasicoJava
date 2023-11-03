package dao;

import interfaces.CabeceraVentasDAO;
import interfaces.DetalleVentasDAO;
import interfaces.EstadoDAO;
import interfaces.ProductoDAO;
import interfaces.TipoDAO;
import interfaces.UsuarioDAO;
import mantenimientos.MySQLCabeceraVentasDAO;
import mantenimientos.MySQLDetalleVentasDAO;
import mantenimientos.MySQLEstadoDAO;
import mantenimientos.MySQLProductoDAO;
import mantenimientos.MySQLTipoDAO;
import mantenimientos.MySQLUsuarioDAO;

public class MySQLDAOFactory extends DAOFactory {

	@Override
	public CabeceraVentasDAO getCabeceraVentasDAO() {
		
		return new MySQLCabeceraVentasDAO();
	}

	@Override
	public DetalleVentasDAO getDetalleVentasDAO() {

		return new MySQLDetalleVentasDAO();
	}

	@Override
	public EstadoDAO getEstadoDAO() {

		return new MySQLEstadoDAO();
	}

	@Override
	public ProductoDAO getProductoDAO() {

		return new MySQLProductoDAO();
	}

	@Override
	public TipoDAO getTipoDAO() {

		return new MySQLTipoDAO();
	}

	@Override
	public UsuarioDAO getUsuarioDAO() {

		return new MySQLUsuarioDAO();
	}

}
