package interfaces;

import java.util.ArrayList;

import beans.UsuarioDTO;

public interface UsuarioDAO {
	// Metodos para el acceso
	public int registrar(UsuarioDTO u); 
	public UsuarioDTO validar(String usuario, String clave);
	// Metodos para crud
	public int actualizar(UsuarioDTO u);
	public int eliminar(int codigo);
	public UsuarioDTO buscar(int codigo);
	public ArrayList<UsuarioDTO> listado();
}
