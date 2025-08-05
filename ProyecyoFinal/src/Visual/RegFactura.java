package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Logico.Cliente;
import Logico.Componente;
import Logico.ConexionBD;
import Logico.Empleado;
import Logico.Empresa;
import Logico.Factura;
import Logico.Visitante;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegFactura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JButton btnBuscar1;
	private JTextField txtDireccion;
	private JTextField txtNombre;
	private JTextField txtIdProducto;
	private JTextField txtPrecioTotal;
	private JTextField txtDescuento;
	private JTable table;
	private DefaultTableModel modelo;
	private DefaultTableModel modelo1;
	private Object dispRow[];
	private Object cartRow[];
	private JButton btnBuscarProd;
	private JButton btnAñadir;
	private JButton btnEliminar;
	private JButton btnFacturar;
	private JButton btnCancelar;
	private ArrayList<Componente> componentesCarrito = new ArrayList<>();
	private JTable table_1;
	private JTextField txtTelefono;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegFactura dialog = new RegFactura();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegFactura() {
		setTitle("Registro de Facturas");
		setBounds(100, 100, 1238, 700);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblID = new JLabel("C\u00E9dula:");
			lblID.setBounds(15, 16, 69, 20);
			panel.add(lblID);
			
			txtCedula = new JTextField();
			txtCedula.setBounds(91, 13, 177, 26);
			panel.add(txtCedula);
			txtCedula.setColumns(10);
			
			btnBuscar1 = new JButton("Buscar");
			btnBuscar1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String cedula = txtCedula.getText();
			        if (cedula.isEmpty()) {
			            JOptionPane.showMessageDialog(null, "Por favor, ingrese una cédula.", "Error", JOptionPane.ERROR_MESSAGE);
			            return;
			        }
			        
			        Cliente cliente = buscarCliente(cedula);
			        if (cliente == null) {
			            JOptionPane.showMessageDialog(null, "No se encontró ningún cliente con la cédula: " + cedula, "Error", JOptionPane.ERROR_MESSAGE);
			            limpiarCamposCliente();
			            habilitarCamposCliente(true);
			        } else {
			            mostrarDatosCliente(cliente);
			            habilitarCamposCliente(false);
			        }
				}
			});
			btnBuscar1.setBounds(283, 12, 115, 29);
			panel.add(btnBuscar1);
			
			JLabel lblDireccion = new JLabel("Direccion:");
			lblDireccion.setBounds(413, 16, 91, 20);
			panel.add(lblDireccion);
			
			txtDireccion = new JTextField();
			txtDireccion.setEnabled(false);
			txtDireccion.setBounds(503, 13, 285, 25);
			panel.add(txtDireccion);
			txtDireccion.setColumns(10);
			
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(15, 58, 69, 20);
			panel.add(lblNombre);
			
			txtNombre = new JTextField();
			txtNombre.setEnabled(false);
			txtNombre.setBounds(101, 55, 285, 26);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "Productos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(159, 112, 406, 408);
			panel.add(panel_1);
			panel_1.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panel_1.add(scrollPane, BorderLayout.CENTER);
			
			modelo = new DefaultTableModel();
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					btnAñadir.setEnabled(true);
					btnEliminar.setEnabled(false);
				}
			});
			String[] headers = {"Código","Modelo","Marca","Num de Serie", "Precio"};
			modelo.setColumnIdentifiers(headers);
			table.setModel(modelo);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(table);
			
			btnBuscarProd = new JButton("Buscar");
			btnBuscarProd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String id = txtIdProducto.getText();
					cargarComponenteTabla(id);
				}
			});
			btnBuscarProd.setBounds(29, 257, 115, 29);
			panel.add(btnBuscarProd);
			
			btnEliminar = new JButton("Eliminar");
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int selectedRow = table_1.getSelectedRow();
			        if (selectedRow >= 0) {
			            String idComponente = (String) modelo1.getValueAt(selectedRow, 0);
			            Componente eliminar = null;
			            for (Componente componente : componentesCarrito) {
			                if (componente.getIdComponente().equals(idComponente)) {
			                	eliminar = componente;
			                }
			            }
			            if (eliminar != null) {
			                componentesCarrito.remove(eliminar);
			                carritoUpload();
			                btnEliminar.setEnabled(false);
			            }
			        }
				}
			});
			btnEliminar.setEnabled(false);
			btnEliminar.setBounds(574, 257, 115, 29);
			panel.add(btnEliminar);
			
			btnAñadir = new JButton("A\u00F1adir");
			btnAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int selectedRow = table.getSelectedRow();
			        if (selectedRow >= 0) {
			            String idComponente = (String) modelo.getValueAt(selectedRow, 0);
			            Componente componente = Empresa.getInstance().buscarCompoById(idComponente);
			            if (componente != null) {
			                componentesCarrito.add(componente);
			                carritoUpload();
			            }
			        }
				}
			});
			btnAñadir.setEnabled(false);
			btnAñadir.setBounds(574, 209, 115, 29);
			panel.add(btnAñadir);
			
			txtIdProducto = new JTextField();
			txtIdProducto.setText("C-");
			txtIdProducto.setBounds(25, 225, 125, 26);
			panel.add(txtIdProducto);
			txtIdProducto.setColumns(10);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(null, "Carrito", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_2.setBounds(704, 112, 458, 383);
			panel.add(panel_2);
			panel_2.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane_1 = new JScrollPane();
			panel_2.add(scrollPane_1, BorderLayout.CENTER);
			
			modelo1 = new DefaultTableModel();
			table_1 = new JTable();
			table_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					btnAñadir.setEnabled(false);
					btnEliminar.setEnabled(true);
				}
			});
			String[] headers1 = {"Código","Modelo","Marca","Num de Serie", "Precio"};
			modelo1.setColumnIdentifiers(headers1);
			table_1.setModel(modelo1);
			table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane_1.setViewportView(table_1);
			
			
			JLabel lblPrecioTotal = new JLabel("Precio Total:");
			lblPrecioTotal.setBounds(936, 512, 91, 20);
			panel.add(lblPrecioTotal);
			
			JLabel lblDescuento = new JLabel("Descuento:");
			lblDescuento.setBounds(936, 556, 79, 20);
			panel.add(lblDescuento);
			
			txtPrecioTotal = new JTextField();
			txtPrecioTotal.setText("0.0$");
			txtPrecioTotal.setEnabled(false);
			txtPrecioTotal.setBounds(1042, 512, 120, 26);
			panel.add(txtPrecioTotal);
			txtPrecioTotal.setColumns(10);
			
			txtDescuento = new JTextField();
			txtDescuento.setText("0.0$");
			txtDescuento.setEnabled(false);
			txtDescuento.setBounds(1042, 553, 122, 26);
			panel.add(txtDescuento);
			txtDescuento.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("Codigo Producto:");
			lblNewLabel.setBounds(24, 189, 129, 20);
			panel.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Telefono:");
			lblNewLabel_1.setBounds(803, 16, 69, 20);
			panel.add(lblNewLabel_1);
			
			txtTelefono = new JTextField();
			txtTelefono.setEnabled(false);
			txtTelefono.setBounds(887, 13, 146, 26);
			panel.add(txtTelefono);
			txtTelefono.setColumns(10);
			
			JButton btnLimpiar = new JButton("Limpiar");
			btnLimpiar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clean();
				}
			});
			btnLimpiar.setBounds(413, 54, 124, 29);
			panel.add(btnLimpiar);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnFacturar = new JButton("Facturar");
				btnFacturar.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        String idFactura = "F-" + (Empresa.getInstance().countIdFactura());
				        float costoTotal = 0;
				        Cliente cliente = cargarCliente(txtCedula.getText());
				     
				        if (cliente != null) {
				            if(calcularDescuento(cliente) == 0) {
				                costoTotal = Float.parseFloat(txtPrecioTotal.getText().replace("$", ""));
				            } else {
				                costoTotal = calcularDescuento(cliente);
				            }
				            
				            // CREAR LA FACTURA CON EL CONSTRUCTOR BÁSICO
				            Factura factura = new Factura(idFactura, cliente);
				            
				            // AGREGAR COMPONENTES UNO POR UNO (esto calcula el precio sin descuento)
				            for (Componente componente : componentesCarrito) {
				                factura.agregarComponente(componente);
				            }
				            
				            // DESPUÉS DE AGREGAR TODOS LOS COMPONENTES, ESTABLECER EL COSTO FINAL CON DESCUENTO
				            factura.setCostoTotal(costoTotal);
				            
				            // Agregar a la memoria
				            Empresa.getInstance().getLasFacturas().add(factura);
				            
				            // Insertar en la base de datos
				            insertarFacturaEnBD(factura);
				            
				            JOptionPane.showMessageDialog(null, "Factura generada con éxito!", "Información", JOptionPane.INFORMATION_MESSAGE);
				            clean();
				        } else {
				            JOptionPane.showMessageDialog(null, "No se pudo generar la factura. Verifique los datos del cliente.", "Error", JOptionPane.ERROR_MESSAGE);
				        }
				    }
				});
				btnFacturar.setEnabled(false);
				btnFacturar.setActionCommand("OK");
				buttonPane.add(btnFacturar);
				getRootPane().setDefaultButton(btnFacturar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		loadComponentes();
	}
	public void loadComponentes() {
        ArrayList<Componente> aux = Empresa.getInstance().getLosComponentes();
        modelo.setRowCount(0);
        dispRow = new Object[table.getColumnCount()];
        for (Componente componente : aux) {
        	dispRow[0] = componente.getIdComponente();
        	dispRow[1] = componente.getModelo();
        	dispRow[2] = componente.getMarca();
        	dispRow[3] = componente.getNumeroSerie();
        	dispRow[4] = componente.getPrecio();
            modelo.addRow(dispRow);
        }
    }
	public void carritoUpload() {
		float precioTotal = 0;
	    modelo1.setRowCount(0);
	    cartRow = new Object[table_1.getColumnCount()];
	    
	    for (Componente componente : componentesCarrito) {
	        cartRow[0] = componente.getIdComponente();
	        cartRow[1] = componente.getModelo();
	        cartRow[2] = componente.getMarca();
	        cartRow[3] = componente.getNumeroSerie();
	        cartRow[4] = componente.getPrecio();
	        modelo1.addRow(cartRow);
	        precioTotal += componente.getPrecio();
	    }
	    
	    txtPrecioTotal.setText(String.format("%.2f$", precioTotal));
	    Cliente clienteActual = buscarCliente(txtCedula.getText());
	    if (clienteActual != null) {
	        float descuento = calcularDescuento(clienteActual);
	        txtDescuento.setText(String.format("%.2f$", descuento));
	    } else {
	        txtDescuento.setText("0.00$");
	    }

	    if(precioTotal != 0.0) {
	        btnFacturar.setEnabled(true);
	    } else {
	        btnFacturar.setEnabled(false);
	    }
	}
	private Cliente cargarCliente(String cedula) {
	    ArrayList<Cliente> aux = Empresa.getInstance().getLosClientes();
	    for (Cliente cliente : aux) {
	        if (cliente.getCedula().equals(cedula)) {
	        	mostrarDatosCliente(cliente);
	            return cliente;
	        }
	    }
	    txtNombre.setEnabled(true);
	    txtDireccion.setEnabled(true);
	    txtTelefono.setEnabled(true);
	    return registrarNuevoCliente();
	}
	private Cliente registrarNuevoCliente() {
	    String cedula = txtCedula.getText();
	    String nombre = txtNombre.getText();
	    String direccion = txtDireccion.getText();
	    String telefono = txtTelefono.getText();
	    int cantcompras = 1;
	    
	    if (nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos del cliente.", "Error", JOptionPane.ERROR_MESSAGE);
	        return null;
	    }
	    
	    Cliente nuevoCliente = new Visitante("CL-" + (Empresa.getIdCliente() + 1), direccion, telefono, cedula, nombre, cantcompras);
	    Empresa.getInstance().insertarCliente(nuevoCliente);
	    JOptionPane.showMessageDialog(this, "Nuevo cliente registrado con éxito.", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
	    return nuevoCliente;
	}
	private void cargarComponenteTabla(String id) {
		ArrayList<Componente> aux = Empresa.getInstance().getLosComponentes();
		modelo.setRowCount(0); 
        boolean encontrado = false;
        for (Componente componente : aux) {
            if (componente.getIdComponente().equals(id)) {
            	dispRow = new Object[table.getColumnCount()];
            	dispRow[0] = componente.getIdComponente();
            	dispRow[1] = componente.getModelo();
            	dispRow[2] = componente.getMarca();
            	dispRow[3] = componente.getNumeroSerie();
            	dispRow[4] = componente.getPrecio();
            	modelo.addRow(dispRow);
                encontrado = true;
            }
        }
        if(!encontrado) {
        	JOptionPane.showMessageDialog(this, "No se encontró ningún componente con el ID: " + id, "Componente no encontrado", JOptionPane.INFORMATION_MESSAGE);
        	loadComponentes();
        }
	}
	private Cliente buscarCliente(String cedula) {
	    ArrayList<Cliente> aux = Empresa.getInstance().getLosClientes();
	    for (Cliente cliente : aux) {
	        if (cliente.getCedula().equals(cedula)) {
	            return cliente;
	        }
	    }
	    return null;
	}

	private void limpiarCamposCliente() {
	    txtNombre.setText("");
	    txtDireccion.setText("");
	    txtTelefono.setText("");
	}

	private void habilitarCamposCliente(boolean habilitar) {
	    txtNombre.setEnabled(habilitar);
	    txtDireccion.setEnabled(habilitar);
	    txtTelefono.setEnabled(habilitar);
	}

	private void mostrarDatosCliente(Cliente cliente) {
	    txtNombre.setText(cliente.getNombre());
	    txtDireccion.setText(cliente.getDireccion());
	    txtTelefono.setText(cliente.getTelefono());
	}
	private float calcularPrecioTotal() {
        float total = 0;
        for (Componente componente : componentesCarrito) {
            total += componente.getPrecio();
        }
        return total;
    }
	private float calcularDescuento(Cliente cliente) {
	    float descuentoPorAnios = 0;
	    float descuentoPorMarca = 0;
	    float precioTotal = calcularPrecioTotal();

	    if (cliente instanceof Empleado) {
	        Empleado empleado = (Empleado) cliente;
	        if (empleado.getCantAniosTrabajando() == 1) {
	            descuentoPorAnios = (float)(precioTotal - precioTotal * 0.05);
	        } else if (empleado.getCantAniosTrabajando() == 2) {
	            descuentoPorAnios = (float)(precioTotal - precioTotal * 0.10);
	        } else if (empleado.getCantAniosTrabajando() >= 3) {
	            descuentoPorAnios = (float)(precioTotal - precioTotal * 0.15);
	        }
	    }
	    for (int i = 0; i < componentesCarrito.size(); i++) {
	        int cont = 1;
	        for (int j = i + 1; j < componentesCarrito.size(); j++) {
	            if (componentesCarrito.get(i).getMarca().equals(componentesCarrito.get(j).getMarca())) {
	                cont++;
	            }
	        }
	        if (cliente instanceof Empleado && cont >= 2) {
	            descuentoPorMarca = (float)(precioTotal * 0.05);
	        }else if (cliente instanceof Visitante && cont >= 2) {
	        	descuentoPorMarca = (float)(precioTotal - precioTotal * 0.05);
	        }
	    }
	    if(descuentoPorAnios == 0) {
	    	return descuentoPorMarca;
	    }
	    else {
	    	return descuentoPorAnios - descuentoPorMarca;
	    }
	}
	private void clean() {
	    txtCedula.setText("");
	    txtNombre.setText("");
	    txtDireccion.setText("");
	    txtTelefono.setText("");
	    txtIdProducto.setText("C-");
	    txtPrecioTotal.setText("0.0$");
	    txtDescuento.setText("0.0$");
	    componentesCarrito.clear();
	    modelo1.setRowCount(0);
	    btnFacturar.setEnabled(false);
	}
	
	private void insertarFacturaEnBD(Factura factura) {
	    String user = "sa";
	    String password = "sa123";
	    String connectionUrl = "jdbc:sqlserver://localhost\\MSSQLSERVER01;" +
	            "databaseName=TiendaComponentes;" +
	            "user=" + user + ";" +
	            "password=" + password + ";" +
	            "encrypt=false;" +
	            "trustServerCertificate=true;";
	    
	    try (Connection connection = DriverManager.getConnection(connectionUrl)) {
	        ConexionBD conexionBD = new ConexionBD();
	        conexionBD.insertarFactura(connection, factura);
	        System.out.println("Factura insertada correctamente en la base de datos.");
	    } catch (SQLException ex) {
	        System.out.println("Error al insertar factura en la base de datos: " + ex.getMessage());
	        JOptionPane.showMessageDialog(null, "Error al guardar en la base de datos: " + ex.getMessage(), 
	                                    "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
	    }
	}
}
