package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.CabeceraBoletaDTO;
import beans.DetalleBoletaDTO;
import interfaces.CabeceraVentasDAO;
import utils.MySQLConexion;

public class MySQLCabeceraVentasDAO implements CabeceraVentasDAO {
	
	@Override
	public int registrar(CabeceraBoletaDTO cabeceraBoletaDTO, List<DetalleBoletaDTO> detalleBoletaDTO) {

		int resultado=0;
		Connection con= null;
		ResultSet rs=null;
		PreparedStatement pst1=null;
		PreparedStatement pst2=null;
		PreparedStatement pst3=null;
		int idCabBoleta=0;
		
		try {
			con=MySQLConexion.getConexion();
			con.setAutoCommit(false);     
			

			String sql1=" insert into tb_cab_venta values (null,?,curdate(),?) ";
			pst1= con.prepareStatement(sql1);

			pst1.setInt(1, cabeceraBoletaDTO.getIdUsuario() ); 
			pst1.setDouble(2, cabeceraBoletaDTO.getTotal()); 
			resultado=pst1.executeUpdate();

			String sql2=" select @@identity as idDetalle ";
			rs=pst1.executeQuery(sql2);
			rs.next();
			idCabBoleta=rs.getInt("idDetalle");
			rs.close();
			
			String sql3=" insert into tb_detalle_venta values (null,?,?,?,?) ";
			String sql4=" update tb_producto set stock=stock-? where idProducto=? ";
			
			for (DetalleBoletaDTO detBol : detalleBoletaDTO) {
				
				pst2=con.prepareStatement(sql3);
				pst2.setInt(1, idCabBoleta);
				pst2.setInt(2, detBol.getIdProducto());
				pst2.setInt(3, detBol.getCantidad());
				pst2.setDouble(4, detBol.getPrecio());
				resultado=pst2.executeUpdate();
				
				pst3=con.prepareStatement(sql4);
				pst3.setInt(1, detBol.getCantidad());
				pst3.setInt(2, detBol.getIdProducto());
				resultado=pst3.executeUpdate();
				
			}
			con.commit();
						
		} catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error al registrar la Cabecera de Ventas: " + e.getMessage());
	    } finally {
	        try {
	            if (pst1 != null) pst1.close();
	            if (pst2 != null) pst2.close();
	            if (pst3 != null) pst3.close();
	            if (con != null) con.close();
	            if (rs != null) rs.close();
	        } catch (SQLException e2) {
	            System.out.println("Error al cerrar el mantenimiento la Cabecera de Ventas: " + e2.getMessage());
	        }
	    }

	    return resultado;
	}

	@Override
	public ArrayList<CabeceraBoletaDTO> listado() {
		
		ArrayList<CabeceraBoletaDTO> lista = new ArrayList<CabeceraBoletaDTO>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from tb_cab_venta";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				CabeceraBoletaDTO genSales = new CabeceraBoletaDTO();
				genSales.setIdDetalle(rs.getInt(1));
				genSales.setIdUsuario(rs.getInt(2));
				genSales.setFregistro(rs.getDate(3));
				genSales.setTotal(rs.getDouble(4));
				lista.add(genSales);
			}
			
		} catch (Exception e) {
			System.out.println("Error en el mantenimiento listado GeneralSales - " + e.getMessage());
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				System.out.println("Error al cerrar el mantenimiento listado GeneralSales - " + e2.getMessage());
			}
		}
		
		return lista;
	}
	@Override
	public ArrayList<CabeceraBoletaDTO> listadoPorID(int idUsuario) {
	    ArrayList<CabeceraBoletaDTO> lista = new ArrayList<>();
	    Connection con = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;

	    try {
	        con = MySQLConexion.getConexion();
	        String sql = "SELECT * FROM tb_cab_venta WHERE idUsuario = ?";
	        pst = con.prepareStatement(sql);
	        pst.setInt(1, idUsuario); 
	        rs = pst.executeQuery();

	        while (rs.next()) {
	            CabeceraBoletaDTO cabecera = new CabeceraBoletaDTO();
	            cabecera.setIdDetalle(rs.getInt(1));
	            cabecera.setIdUsuario(rs.getInt(2));
	            cabecera.setFregistro(rs.getDate(3));
	            cabecera.setTotal(rs.getDouble(4));
	            lista.add(cabecera);
	        }
	    } catch (Exception e) {
	        System.out.println("Error en el método listadoPorID - " + e.getMessage());
	    } finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (pst != null) {
	                pst.close();
	            }
	            if (con != null) {
	                con.close();
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al cerrar la conexión - " + e.getMessage());
	        }
	    }

	    return lista;
	}
}
