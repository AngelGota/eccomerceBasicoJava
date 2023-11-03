package beans;

import java.util.Date;

public class CabeceraBoletaDTO {
	private int idDetalle;
	private int idUsuario;
	private Date fregistro;
	private double total;
	
	public int getIdDetalle() {
		return idDetalle;
	}
	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Date getFregistro() {
		return fregistro;
	}
	public void setFregistro(Date fregistro) {
		this.fregistro = fregistro;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
}
