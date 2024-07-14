package Logico;

import java.util.ArrayList;

public class TarjetaMadre extends Componente {

	private String Conector;
	private String TipoRam;
	private ArrayList<String>Conexiones;

	
	
	public TarjetaMadre(String idComponente, String numeroSerie, float precio, int cantidadDisponible, String marca,
			String modelo, String conector, String tipoRam, ArrayList<String>conexiones) {
		super(idComponente, numeroSerie, precio, cantidadDisponible, marca, modelo);
		// TODO Auto-generated constructor stub
		this.Conector = conector;
		this.TipoRam = tipoRam;
		this.Conexiones = conexiones;
	}
	
	
	public String getConector() {
		return Conector;
	}
	public void setConector(String conector) {
		Conector = conector;
	}
	public String getTipoRam() {
		return TipoRam;
	}
	public void setTipoRam(String tipoRam) {
		TipoRam = tipoRam;
	}
	public ArrayList<String> getConexiones() {
		return Conexiones;
	}
	public void setConexiones(ArrayList<String> conexiones) {
		Conexiones = conexiones;
	}
}
