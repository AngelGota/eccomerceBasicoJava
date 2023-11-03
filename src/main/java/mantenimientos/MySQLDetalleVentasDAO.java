package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.DetalleBoletaDTO;
import interfaces.DetalleVentasDAO;
import utils.MySQLConexion;

public class MySQLDetalleVentasDAO implements DetalleVentasDAO {

	@Override
	public ArrayList<DetalleBoletaDTO> listado() {
		
		ArrayList<DetalleBoletaDTO> lista = new ArrayList<DetalleBoletaDTO>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from tb_detalle_venta";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				DetalleBoletaDTO detSales = new DetalleBoletaDTO();
				detSales.setIdDetalle(rs.getInt(1));
				detSales.setIdCabDetalle(rs.getInt(2));
				detSales.setIdProducto(rs.getInt(3));
				detSales.setCantidad(rs.getInt(4));
				detSales.setPrecio(rs.getDouble(5));
				lista.add(detSales);
			}
		} catch (Exception e) {
			System.out.println("Error en el mantenimiento listado DetalleVentas - " + e.getMessage());
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				System.out.println("Error al cerrar el mantenimiento listado DetalleVentas - " + e2.getMessage());
			}
		}
		
		return lista;
	}

}
