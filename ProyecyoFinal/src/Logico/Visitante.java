package Logico;

public class Visitante extends Cliente {
	int CantCompras;

	public int getCantCompras() {
		return CantCompras;
	}

	public void setCantCompras(int cantCompras) {
		CantCompras = cantCompras;
	}

	public Visitante(int cantCompras) {
		super();
		CantCompras = cantCompras;
	}
}
