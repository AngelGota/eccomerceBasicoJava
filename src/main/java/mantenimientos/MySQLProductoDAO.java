package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.ProductoDTO;
import interfaces.ProductoDAO;
import utils.MySQLConexion;

public class MySQLProductoDAO implements ProductoDAO {

	@Override
	public int registrar(ProductoDTO p) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "insert into tb_producto values (?, ?, ?, ?, ?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, p.getIdProducto());
			pst.setString(2, p.getNomProducto());
			pst.setInt(3, p.getStock());
			pst.setDouble(4, p.getPrecio());
			pst.setInt(5, p.getIdEstado());
			rs = pst.executeUpdate();

		} catch (Exception e) {
			System.out.println("Error en el mantenimiento registrar producto - " + e.getMessage());
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				System.out.println("Error al cerrar el mantenimiento registrar producto - " + e2.getMessage());
			}
		}

		return rs;
	}

	@Override
	public int actualizar(ProductoDTO p) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "update tb_producto set  nomProducto=?, stock=?, precio=?, idEstado=? where idProducto= ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, p.getNomProducto());
			pst.setInt(2, p.getStock());
			pst.setDouble(3, p.getPrecio());
			pst.setInt(4, p.getIdEstado());
			pst.setInt(5, p.getIdProducto());
		} catch (Exception e) {
			System.out.println("Error en el mantenimiento actualizar producto - " + e.getMessage());
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				System.out.println("Error al cerrar el mantenimiento producto - " + e2.getMessage());
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
			String sql = "delete from tb_producto where idProducto =?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, codigo);
			rs = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en el mantenimiento eliminar producto - " + e.getMessage());
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				System.out.println("Error al cerrar el mantenimiento eliminar producto - " + e2.getMessage());
			}
		}
		return rs;
	}

	@Override
	public ArrayList<ProductoDTO> listado() {
		ArrayList<ProductoDTO> lista = new ArrayList<ProductoDTO>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			con = MySQLConexion.getConexion();
			String sql = "SELECT p.idProducto, p.nomProducto, p.stock, p.precio, e.estado AS Estado FROM tb_producto AS p JOIN tb_estado AS e ON p.idEstado = e.idEstado";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				ProductoDTO l = new ProductoDTO();
				l.setIdProducto(rs.getInt(1));
				l.setNomProducto(rs.getString(2));
				l.setStock(rs.getInt(3));
				l.setPrecio(rs.getDouble(4));
				l.setDesEstado(rs.getString(5));
				lista.add(l);
			}

		} catch (Exception e) {
			System.out.println("Error en mantenimiento listado producto - " + e.getMessage());
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				System.out.println("Error al cerrar el mantenimiento listado producto - " + e2.getMessage());
			}
		}
		return lista;
	}

	@Override
	public ProductoDTO buscar(int codigo) {
		ProductoDTO p = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from tb_producto where idProducto=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, codigo);
			rs = pst.executeQuery();
			while (rs.next()) {
				p = new ProductoDTO();
				p.setIdProducto(rs.getInt(1));
				p.setNomProducto(rs.getString(2));
				p.setStock(rs.getInt(3));
				p.setPrecio(rs.getDouble(4));
				p.setIdEstado(rs.getInt(5));
			}
		} catch (Exception e) {
			System.out.println("Error en en el mantenimiento buscar producto - " + e.getMessage());
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				System.out.println("Error al cerrar mantenimiento buscar producto - " + e2.getMessage());
			}
		}
		return p;
	}

}
