package Logico;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Componente>LosComponentes;
	private ArrayList<Cliente>LosClientes;
	private ArrayList<Factura>LasFacturas;
	public static int IdComponente;
	public static int IdFactura;
	public static int IdCliente;
	private static Empresa miEmpresa = null;
	
	public static Empresa getInstance() {
		if (miEmpresa == null) {
			miEmpresa = new Empresa();
		}
		return miEmpresa;
	}
	private Empresa() {
		super();
		this.LasFacturas = new ArrayList<>();
		this.LosClientes = new ArrayList<>();
		this.LosComponentes = new ArrayList<>();
		
	}
	public ArrayList<Componente> getLosComponentes() {
		return LosComponentes;
	}
	public void setLosComponentes(ArrayList<Componente> losComponentes) {
		LosComponentes = losComponentes;
	}
	public ArrayList<Cliente> getLosClientes() {
		return LosClientes;
	}
	public void setLosClientes(ArrayList<Cliente> losClientes) {
		LosClientes = losClientes;
	}
	public ArrayList<Factura> getLasFacturas() {
		return LasFacturas;
	}
	public void setLasFacturas(ArrayList<Factura> lasFacturas) {
		LasFacturas = lasFacturas;
	}
	public static int getIdComponente() {
		return IdComponente;
	}
	public static void setIdComponente(int idComponente) {
		IdComponente = idComponente;
	}
	public static int getIdFactura() {
		return IdFactura;
	}
	public static void setIdFactura(int idFactura) {
		IdFactura = idFactura;
	}
	public static int getIdCliente() {
		return IdCliente;
	}
	public static void setIdCliente(int idCliente) {
		IdCliente = idCliente;
	}
	
	
	public void insertarComponente(Componente v1)  {
		LosComponentes.add(v1);
		IdComponente++;
	
	}
	public void insertarCliente(Cliente v1) {
		LosClientes.add(v1);
		IdCliente++;
	}
	public void insertarFactura(Factura v1) {
		LasFacturas.add(v1);
		IdFactura++;
	}
	
	public Componente buscarCompoById(String id) {
	    for (Componente buscar : LosComponentes) {
	        if (buscar.getIdComponente().equalsIgnoreCase(id)) {
	            return buscar;
	        }
	    }
	    return null;
	}

	public Cliente buscarClienteById(String id) {
	    for (Cliente buscar : LosClientes) {
	        if (buscar.getIdCliente().equalsIgnoreCase(id)){
	            return buscar;
	        }
	    }
	    return null;
	}
	
	public Cliente buscarClienteByCedula(String cedula) {
		for (Cliente buscar: LosClientes) {
			if(buscar.getCedula().equals(cedula)) {
				return buscar;
			}
		}
		return null;
	}
	
	public Factura buscarFacturaById(String id) {
	    for (Factura buscar : LasFacturas) {
	        if (buscar.getIdFactura().equalsIgnoreCase(id)) {
	            return buscar;
	        }
	    }
	    return null;
	}
	
	public void deleteCliente(String codino) {
		Cliente aux = buscarClienteById(codino);
		if(aux!=null){
			LosClientes.remove(aux);
		}
	}
	public void deleteComponente(String codino) {
		Componente aux = buscarCompoById(codino);
		if(aux!=null){
			LosComponentes.remove(aux);
		}
	}
	public void deleteFactura(String codino) {
		Factura aux = buscarFacturaById(codino);
		if(aux!=null){
			LasFacturas.remove(aux);
		}
	}
	 public void updateCliente(Cliente clienteActualizado) {
	        for (int i = 0; i < LosClientes.size(); i++) {
	            Cliente cliente = LosClientes.get(i);
	            if (cliente.getIdCliente().equals(clienteActualizado.getIdCliente())) {
	                LosClientes.set(i, clienteActualizado);
	                break;
	            }
	        }
	    }
	 
	 public void updateCompo(Componente componenteActualizado) {
	        for (int i = 0; i < LosComponentes.size(); i++) {
	            Componente compo = LosComponentes.get(i);
	            if (compo.getIdComponente().equals(componenteActualizado.getIdComponente())) {
	                LosComponentes.set(i, componenteActualizado);
	                break;
	            }
	        }
	    }
	 public void updateFactura(Factura facturaActualizado) {
	        for (int i = 0; i < LasFacturas.size(); i++) {
	            Factura factura = LasFacturas.get(i);
	            if (factura.getIdFactura().equals(facturaActualizado.getIdFactura())) {
	                LasFacturas.set(i, facturaActualizado);
	                break;
	            }
	        }
	    }
	 
	 public ArrayList<Componente> getComponentesSeleccionados() {
	        ArrayList<Componente> componentesSeleccionados = new ArrayList<>();
	        for (Componente componente : LosComponentes) {
	            if (componente.isSeleccionado()) {
	            	componentesSeleccionados.add(componente);
	            }
	        }
	        return componentesSeleccionados;
	    }
	public static void setInstance(Empresa NewEmpresa) {
		miEmpresa = NewEmpresa;
		
	}
	
	public int countIdComponente() {
	    int countIdMax = 0;
	    for (int i = 0; i < LosComponentes.size(); i++) {
	        String idComponente = LosComponentes.get(i).getIdComponente();
	        try {
	            String numericPart = idComponente.replace("C-", "");
	            int countId = Integer.parseInt(numericPart);
	            if (countIdMax < countId) {
	                countIdMax = countId;
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("ID de componente inválido: " + idComponente);
	        }
	    }
	    return countIdMax + 1;
	}

	public int countIdCliente() {
	    int countIdMax = 0;
	    for (int i = 0; i < LosClientes.size(); i++) {
	        String idCliente = LosClientes.get(i).getIdCliente();
	        try {
	            String numericPart = idCliente.replace("CL-", "");
	            int countId = Integer.parseInt(numericPart);
	            if (countIdMax < countId) {
	                countIdMax = countId;
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("ID de cliente inválido: " + idCliente);
	        }
	    }
	    return countIdMax + 1;
	}
	public int countIdFactura() {
	    int countIdMax = 0;
	    for (int i = 0; i < LasFacturas.size(); i++) {
	        String idFactura = LasFacturas.get(i).getIdFactura();
	        try {
	            String numericPart = idFactura.replace("F-", "");
	            int countId = Integer.parseInt(numericPart);
	            if (countIdMax < countId) {
	                countIdMax = countId;
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("ID de factura inválido: " + idFactura);
	        }
	    }
	    return countIdMax + 1;
	}
	
}
