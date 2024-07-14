package Logico;

public abstract class Componente {

	protected String IdComponente;
	protected String NumeroSerie;
	protected float Precio;
	protected int CantidadDisponible;
	protected String Marca;
	protected String Modelo;
	
	
	public Componente(String idComponente, String numeroSerie, float precio, int cantidadDisponible, String marca,
			String modelo) {
		super();
		IdComponente = idComponente;
		NumeroSerie = numeroSerie;
		Precio = precio;
		CantidadDisponible = cantidadDisponible;
		Marca = marca;
		Modelo = modelo;
	}
	public String getIdComponente() {
		return IdComponente;
	}
	public void setIdComponente(String idComponente) {
		IdComponente = idComponente;
	}
	public String getNumeroSerie() {
		return NumeroSerie;
	}
	public void setNumeroSerie(String numeroSerie) {
		NumeroSerie = numeroSerie;
	}
	public float getPrecio() {
		return Precio;
	}
	public void setPrecio(float precio) {
		Precio = precio;
	}
	public int getCantidadDisponible() {
		return CantidadDisponible;
	}
	public void setCantidadDisponible(int cantidadDisponible) {
		CantidadDisponible = cantidadDisponible;
	}
	public String getMarca() {
		return Marca;
	}
	public void setMarca(String marca) {
		Marca = marca;
	}
	public String getModelo() {
		return Modelo;
	}
	public void setModelo(String modelo) {
		Modelo = modelo;
	}
}
