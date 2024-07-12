package Logico;

public class RAM extends Componente {
	private float CantGB;
	private String TipoMemoria;
	public float getCantGB() {
		return CantGB;
	}
	public void setCantGB(float cantGB) {
		CantGB = cantGB;
	}
	public String getTipoMemoria() {
		return TipoMemoria;
	}
	public void setTipoMemoria(String tipoMemoria) {
		TipoMemoria = tipoMemoria;
	}
	public RAM(float cantGB, String tipoMemoria) {
		super();
		CantGB = cantGB;
		TipoMemoria = tipoMemoria;
	}
	
}
