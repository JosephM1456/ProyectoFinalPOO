package Logico;

import java.io.Serializable;

public class DiscoDuro extends Componente implements Serializable {
	private static final long serialVersionUID = 1L;

	private String TipoConex;
	private float CapAlmacenamiento;
	
	public DiscoDuro(String idComponente, String numeroSerie, float precio, int cantidadDisponible, String marca,
			String modelo, String tipoConex, float capAlmacenamiento) {
		super(idComponente, numeroSerie, precio, cantidadDisponible, marca, modelo);
		// TODO Auto-generated constructor stub
		this.TipoConex = tipoConex;
		this.CapAlmacenamiento = capAlmacenamiento;
	}
	
	public String getTipoConex() {
		return TipoConex;
	}
	public void setTipoConex(String tipoConex) {
		TipoConex = tipoConex;
	}
	public float getCapAlmacenamiento() {
		return CapAlmacenamiento;
	}
	public void setCapAlmacenamiento(float capAlmacenamiento) {
		CapAlmacenamiento = capAlmacenamiento;
	}

}
