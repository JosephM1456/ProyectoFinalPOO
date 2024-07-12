package Logico;

public class MicroProcesador extends Componente {
	private String TipoConex;
	private float VelProcesamientoGHz;
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
	public MicroProcesador(String tipoConex, float velProcesamientoGHz) {
		super();
		TipoConex = tipoConex;
		VelProcesamientoGHz = velProcesamientoGHz;
	}
	
}
