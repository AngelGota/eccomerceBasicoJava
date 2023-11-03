package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.TipoDTO;
import interfaces.TipoDAO;
import utils.MySQLConexion;

public class MySQLTipoDAO implements TipoDAO {

	@Override
	public ArrayList<TipoDTO> listarTipo() {
		ArrayList<TipoDTO> lista = new ArrayList<TipoDTO>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			
			con = MySQLConexion.getConexion();
			String sql = "select * from tb_tipo";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				TipoDTO c = new TipoDTO();
				c.setIdTipo(rs.getInt(1));
				c.setDescripcion(rs.getString(2));
				lista.add(c);
			}
			
		} catch (Exception e) {
			System.out.println("Error en mantenimiento listadoTipo - " + e.getMessage());
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				System.out.println("Error al cerrar el mantenimiento listadoTipo - " + e2.getMessage());
			}
		}
		return lista;
	}
}
