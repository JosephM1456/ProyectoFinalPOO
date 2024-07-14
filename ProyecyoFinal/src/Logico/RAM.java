package Logico;

public class RAM extends Componente {
	private float CantGB;
	private String TipoMemoria;
	
	
	
	public RAM(String idComponente, String numeroSerie, float precio, int cantidadDisponible, String marca,
			String modelo, float cantGB, String tipoMemoria) {
		super(idComponente, numeroSerie, precio, cantidadDisponible, marca, modelo);
		// TODO Auto-generated constructor stub
		this.CantGB = cantGB;
		this.TipoMemoria = tipoMemoria;
	}
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

	
}
