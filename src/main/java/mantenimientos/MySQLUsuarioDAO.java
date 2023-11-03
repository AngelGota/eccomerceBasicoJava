package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.UsuarioDTO;
import interfaces.UsuarioDAO;
import utils.MySQLConexion;

public class MySQLUsuarioDAO implements UsuarioDAO {

	@Override
	public int registrar(UsuarioDTO u) {
		int rs=0;
		Connection con= null;
		PreparedStatement pst=null;
		try {
			con=MySQLConexion.getConexion();
			String sql=" insert into tb_usuario values (null,?,?,?,?,?,?,default) ";
			pst=con.prepareStatement(sql);
			pst.setString(1, u.getCorreo());
			pst.setString(2, u.getNombre());
			pst.setString(3, u.getApellido());
			pst.setString(4, u.getContrasena());
			pst.setInt(5, u.getTelefono());
			pst.setString(6, u.getDireccion());
			rs=pst.executeUpdate();	
			
		} catch (Exception e) {
		  System.out.println("Error en el mantenimiento registro usuario - "+e.getMessage());
		}finally{
			try {
				if(pst!=null)pst.close();
				if(con!=null)con.close();
			} catch (SQLException e2) {
				System.out.println("Error al cerrar el mantenimiento registro usuario - "+e2.getMessage());
			}	
		}
		
		return rs;
	}

	@Override
	public UsuarioDTO validar(String correo, String clave) {
		UsuarioDTO u = null;
		Connection con = null;
		PreparedStatement pst= null;
		ResultSet rs= null;
		
		try {
			con= MySQLConexion.getConexion();
			String sql =" call usp_validaAcceso(?,?) ";
			pst=con.prepareStatement(sql);
			
			 pst.setString(1, correo);
			 pst.setString(2, clave);
			 rs= pst.executeQuery();
			 while(rs.next()) {
				 u = new UsuarioDTO();
				 u.setIdUsuario( rs.getInt(1) );
				 u.setCorreo(rs.getString(2));
				 u.setNombre( rs.getString(3));
				 u.setApellido(rs.getString(4));
				 u.setContrasena(rs.getString(5));
				 u.setTelefono(rs.getInt(6));
				 u.setDireccion(rs.getString(7));
				 u.setIdTipo(rs.getInt(8));
			 }
			
		} catch (Exception e) {
			System.out.println("Error en el mantenimiento validar usuario - "+e.getMessage());
		}finally {
			try {
				if (pst!=null) pst.close();
				if (rs!=null)  rs.close();
				if (con!=null) con.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar el mantenimiento validar usuario - "+e2.getMessage());
			}
			
		}

		return u;
	}

	@Override
	public int actualizar(UsuarioDTO u) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "update tb_usuario set  correo=?, nombre=?, apellido=?, contrasena=?, telefono=?, direccion=?, idTipo=? where idUsuario= ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, u.getCorreo());
			pst.setString(2, u.getNombre());
			pst.setString(3, u.getApellido());
			pst.setString(4, u.getContrasena());
			pst.setInt(5, u.getTelefono());
			pst.setString(6, u.getDireccion());
			pst.setInt(7, u.getIdTipo());
			pst.setInt(8, u.getIdUsuario());
			rs = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en el mantenimiento actualizar usuario - " + e.getMessage());
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				System.out.println("Error al cerrar el mantenimiento actualizar usuario - " + e2.getMessage());
			}
		}
		return rs;
	}

	@Override
	public int eliminar(int codigo) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "delete from tb_usuario where idUsuario=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, codigo);
			rs = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en el mantenimiento eliminar usuario - " + e.getMessage());
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				System.out.println("Error al cerrar el mantenimiento eliminar usuario - " + e2.getMessage());
			}
		}
		return rs;
	}

	@Override
	public UsuarioDTO buscar(int codigo) {
		UsuarioDTO u = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from tb_usuario where idUsuario=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, codigo);
			rs = pst.executeQuery();
			while (rs.next()) {
				u = new UsuarioDTO();
				u.setIdUsuario(rs.getInt(1));
				u.setCorreo(rs.getString(2));
				u.setNombre(rs.getString(3));
				u.setApellido(rs.getString(4));
				u.setContrasena(rs.getString(5));
				u.setTelefono(rs.getInt(6));
				u.setDireccion(rs.getString(7));
				u.setIdTipo(rs.getInt(8));
			}
		} catch (Exception e) {
			System.out.println("Error en en el mantenimiento buscar usuario - " + e.getMessage());
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				System.out.println("Error al cerrar mantenimiento buscar usuario - " + e2.getMessage());
			}
		}
		return u;
	}

	@Override
	public ArrayList<UsuarioDTO> listado() {
		ArrayList<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			con = MySQLConexion.getConexion();
			String sql = "SELECT u.idUsuario, u.correo, u.nombre, u.apellido, u.contrasena, u.telefono, u.direccion, t.tipo AS Tipo FROM tb_usuario AS u JOIN tb_tipo AS t ON u.idTipo = t.idTipo";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				UsuarioDTO u = new UsuarioDTO();
				u.setIdUsuario(rs.getInt(1));
				u.setCorreo(rs.getString(2));
				u.setNombre(rs.getString(3));
				u.setApellido(rs.getString(4));
				u.setContrasena(rs.getString(5));
				u.setTelefono(rs.getInt(6));
				u.setDireccion(rs.getString(7));
				u.setIdTipo(rs.getInt(8));
				lista.add(u);
			}

		} catch (Exception e) {
			System.out.println("Error en mantenimiento listado usuario - " + e.getMessage());
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				System.out.println("Error al cerrar el mantenimiento listado usuario - " + e2.getMessage());
			}
		}
		return lista;
	}

}
