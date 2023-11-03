package interfaces;

import java.util.ArrayList;

import beans.ProductoDTO;

public interface ProductoDAO {
	public int registrar(ProductoDTO p);
	public int actualizar(ProductoDTO p);
	public int eliminar(int codigo);
	
	public ArrayList<ProductoDTO> listado();
	public ProductoDTO buscar(int codigo);
}
