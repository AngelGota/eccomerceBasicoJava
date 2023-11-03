package beans;

public class DetalleBoletaDTO {
	private int idCabDetalle;
	private int idDetalle;
	private int idProducto;
	private int cantidad;
	private double precio;
		
	public int getIdCabDetalle() {
		return idCabDetalle;
	}
	public void setIdCabDetalle(int idCabDetalle) {
		this.idCabDetalle = idCabDetalle;
	}
	public int getIdDetalle() {
		return idDetalle;
	}
	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
}
