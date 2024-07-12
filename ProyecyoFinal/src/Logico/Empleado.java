package Logico;

public class Empleado extends Cliente {
	float SueldoTotal;
	int CantAniosTrabajando;
	int Descuento;
	public float getSueldoTotal() {
		return SueldoTotal;
	}
	public void setSueldoTotal(float sueldoTotal) {
		SueldoTotal = sueldoTotal;
	}
	public int getCantAniosTrabajando() {
		return CantAniosTrabajando;
	}
	public void setCantAniosTrabajando(int cantAniosTrabajando) {
		CantAniosTrabajando = cantAniosTrabajando;
	}
	public int getDescuento() {
		return Descuento;
	}
	public void setDescuento(int descuento) {
		Descuento = descuento;
	}
	public Empleado(float sueldoTotal, int cantAniosTrabajando, int descuento) {
		super();
		SueldoTotal = sueldoTotal;
		CantAniosTrabajando = cantAniosTrabajando;
		Descuento = descuento;
	}
	
}
