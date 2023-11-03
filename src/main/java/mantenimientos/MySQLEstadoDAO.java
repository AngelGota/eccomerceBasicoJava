package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.EstadoDTO;
import interfaces.EstadoDAO;
import utils.MySQLConexion;

public class MySQLEstadoDAO implements EstadoDAO {

	@Override
	public ArrayList<EstadoDTO> listarEstado() {
		ArrayList<EstadoDTO> lista = new ArrayList<EstadoDTO>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			
			con = MySQLConexion.getConexion();
			String sql = "select * from tb_estado";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				EstadoDTO c = new EstadoDTO();
				c.setIdEstado(rs.getInt(1));
				c.setDescripcion(rs.getString(2));
				lista.add(c);
			}
			
		} catch (Exception e) {
			System.out.println("Error en mantenimiento listadoEstado - " + e.getMessage());
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				System.out.println("Error al cerrar el mantenimiento listadoEstado - " + e2.getMessage());
			}
		}
		return lista;
	}
}
