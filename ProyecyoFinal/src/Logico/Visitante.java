package Logico;

import java.io.Serializable;

public class Visitante extends Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	int CantCompras;

	public int getCantCompras() {
		return CantCompras;
	}

	public void setCantCompras(int cantCompras) {
		CantCompras = cantCompras;
	}

	public Visitante(String idCliente, String direccion, String telefono, String cedula, String nombre, int cantCompras) {
		super(idCliente, direccion, telefono, cedula, nombre);
		CantCompras = cantCompras;
	}

	
}

