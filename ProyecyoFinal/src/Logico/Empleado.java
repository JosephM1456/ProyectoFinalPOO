package Logico;

import java.io.Serializable;

public class Empleado extends Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
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
	public Empleado(String idCliente, String direccion, String telefono, String cedula, String nombre, float sueldoTotal, int cantAniosTrabajando, int descuento) {
		super(idCliente, direccion, telefono, cedula, nombre);
		SueldoTotal = sueldoTotal;
		CantAniosTrabajando = cantAniosTrabajando;
		Descuento = descuento;
	}
	
	
}

