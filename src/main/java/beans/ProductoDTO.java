package beans;

public class ProductoDTO {
	private int idProducto;
    private String nomProducto;
    private int stock;
    private double precio;
	private int idEstado;
	private String desEstado;
	
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public String getNomProducto() {
		return nomProducto;
	}
	public void setNomProducto(String nomProducto) {
		this.nomProducto = nomProducto;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	public String getDesEstado() {
		return desEstado;
	}
	public void setDesEstado(String desEstado) {
		this.desEstado = desEstado;
	}
	
}
