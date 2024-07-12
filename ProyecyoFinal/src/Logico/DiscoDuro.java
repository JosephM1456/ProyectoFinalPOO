package Logico;

public class DiscoDuro extends Componente {
	private String TipoConex;
	private float CapAlmacenamiento;
	
	
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
	public DiscoDuro(String tipoConex, float capAlmacenamiento) {
		super();
		TipoConex = tipoConex;
		CapAlmacenamiento = capAlmacenamiento;
	}
}
