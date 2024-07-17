package Logico;

public class Visitante extends Cliente {
	int CantCompras;

	public int getCantCompras() {
		return CantCompras;
	}

	public void setCantCompras(int cantCompras) {
		CantCompras = cantCompras;
	}

	public Visitante(String idCliente, String direccion, String telefono, String cedula, int cantCompras) {
		super( idCliente,  direccion, telefono,  cedula);
		CantCompras = cantCompras;
	}
}

