package interfaces;

import java.util.ArrayList;
import java.util.List;

import beans.CabeceraBoletaDTO;
import beans.DetalleBoletaDTO;

public interface CabeceraVentasDAO {
	public ArrayList<CabeceraBoletaDTO> listado();
	public ArrayList<CabeceraBoletaDTO> listadoPorID(int idUsuario);
	int registrar(CabeceraBoletaDTO cabeceraBoletaDTO, List<DetalleBoletaDTO> detalleBoletaDTO);
}
