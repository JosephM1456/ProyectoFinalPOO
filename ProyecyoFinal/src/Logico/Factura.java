package Logico;

import java.util.ArrayList;

public class Factura {
	private String IdFactura;
	private float CostoTotal;
	private ArrayList<Componente>LosComponentes;
	Cliente cliente;
	public String getIdFactura() {
		return IdFactura;
	}
	public void setIdFactura(String idFactura) {
		IdFactura = idFactura;
	}
	public float getCostoTotal() {
		return CostoTotal;
	}
	public void setCostoTotal(float costoTotal) {
		CostoTotal = costoTotal;
	}
	public ArrayList<Componente> getLosComponentes() {
		return LosComponentes;
	}
	public void setLosComponentes(ArrayList<Componente> losComponentes) {
		LosComponentes = losComponentes;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Factura(String idFactura, float costoTotal, ArrayList<Componente> losComponentes, Cliente cliente) {
		super();
		IdFactura = idFactura;
		CostoTotal = costoTotal;
		LosComponentes = losComponentes;
		this.cliente = cliente;
	}
}
