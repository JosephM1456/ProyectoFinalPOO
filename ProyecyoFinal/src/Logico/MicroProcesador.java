package Logico;

import java.io.Serializable;

public class MicroProcesador extends Componente implements Serializable {
	private static final long serialVersionUID = 1L;
	private String TipoConex;
	private float VelProcesamientoGHz;
	
	public MicroProcesador(String idComponente, String numeroSerie, float precio, int cantidadDisponible, String marca,
		String modelo, String tipoConex, float velProcesamientoGHz) 
	{
		super(idComponente, numeroSerie, precio, cantidadDisponible, marca, modelo);
		// TODO Auto-generated constructor stub
		this.TipoConex = tipoConex;
		this.VelProcesamientoGHz = velProcesamientoGHz;
	}
	
	public String getTipoConex() {
		return TipoConex;
	}
	public void setTipoConex(String tipoConex) {
		TipoConex = tipoConex;
	}
	public float getVelProcesamientoGHz() {
		return VelProcesamientoGHz;
	}
	public void setVelProcesamientoGHz(float velProcesamientoGHz) {
		VelProcesamientoGHz = velProcesamientoGHz;
	}

	
	
}
