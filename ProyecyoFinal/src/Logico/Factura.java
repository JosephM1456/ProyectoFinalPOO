package Logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;
	private String IdFactura;
	private float CostoTotal;
	private ArrayList<Componente>LosComponentes;
	Cliente cliente;
	public String getIdFactura() {
		return IdFactura;
	}
	public Factura(String idFactura, Cliente cliente) {
		this(idFactura, 0.0f, new ArrayList<Componente>(), cliente);
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
	public void agregarComponente(Componente componente) {
		if (LosComponentes == null) {
			LosComponentes = new ArrayList<Componente>();
		}
		LosComponentes.add(componente);
		// Actualizar el costo total sumando el precio del componente
		CostoTotal += componente.getPrecio();
	}
}
