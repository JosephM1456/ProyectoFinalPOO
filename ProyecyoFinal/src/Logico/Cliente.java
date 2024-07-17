package Logico;

public abstract class Cliente {
	protected String IdCliente;
	protected String Direccion;
	protected String Telefono;
	protected String Cedula;
	
	public String getIdCliente() {
		return IdCliente;
	}
	public void setIdCliente(String idCliente) {
		IdCliente = idCliente;
	}
	public String getDireccion() {
		return Direccion;
	}
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	public String getTelefono() {
		return Telefono;
	}
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	public String getCedula() {
		return Cedula;
	}
	public void setCedula(String cedula) {
		Cedula = cedula;
	}
	public Cliente(String idCliente, String direccion, String telefono, String cedula) {
		super();
		IdCliente = idCliente;
		Direccion = direccion;
		Telefono = telefono;
		Cedula = cedula;
	}
}