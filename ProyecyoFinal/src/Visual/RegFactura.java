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
import Logico.Empresa;
import Logico.Factura;
import Logico.Visitante;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
		setBounds(100, 100, 851, 606);
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
			        
			        Cliente cliente = cargarCliente(cedula);
			        if (cliente == null) {
			            JOptionPane.showMessageDialog(null, "No se encontró ningún cliente con la cédula: " + cedula, "Error", JOptionPane.ERROR_MESSAGE);
			            txtNombre.setEnabled(true);
			            txtDireccion.setEnabled(true);
			            txtTelefono.setEnabled(true);
			            txtNombre.setText("");
			            txtDireccion.setText("");
			            txtTelefono.setText("");
			        } else {
			            txtNombre.setEnabled(false);
			            txtDireccion.setEnabled(false);
			            txtTelefono.setEnabled(false);
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
			txtDireccion.setBounds(519, 16, 285, 25);
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
			panel_1.setBounds(159, 112, 236, 293);
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
			btnEliminar.setBounds(410, 257, 115, 29);
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
			btnAñadir.setBounds(410, 212, 115, 29);
			panel.add(btnAñadir);
			
			txtIdProducto = new JTextField();
			txtIdProducto.setText("C-");
			txtIdProducto.setBounds(25, 225, 125, 26);
			panel.add(txtIdProducto);
			txtIdProducto.setColumns(10);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(null, "Carrito", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_2.setBounds(540, 112, 236, 293);
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
			lblPrecioTotal.setBounds(550, 421, 91, 20);
			panel.add(lblPrecioTotal);
			
			JLabel lblDescuento = new JLabel("Descuento:");
			lblDescuento.setBounds(560, 462, 79, 20);
			panel.add(lblDescuento);
			
			txtPrecioTotal = new JTextField();
			txtPrecioTotal.setText("0.0$");
			txtPrecioTotal.setEnabled(false);
			txtPrecioTotal.setBounds(656, 418, 120, 26);
			panel.add(txtPrecioTotal);
			txtPrecioTotal.setColumns(10);
			
			txtDescuento = new JTextField();
			txtDescuento.setText("0.0$");
			txtDescuento.setEnabled(false);
			txtDescuento.setBounds(654, 459, 122, 26);
			panel.add(txtDescuento);
			txtDescuento.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("Codigo Producto:");
			lblNewLabel.setBounds(24, 189, 129, 20);
			panel.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Telefono:");
			lblNewLabel_1.setBounds(413, 52, 69, 20);
			panel.add(lblNewLabel_1);
			
			txtTelefono = new JTextField();
			txtTelefono.setEnabled(false);
			txtTelefono.setBounds(519, 52, 146, 26);
			panel.add(txtTelefono);
			txtTelefono.setColumns(10);
			
			JButton btnLimpiar = new JButton("Limpiar");
			btnLimpiar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					txtCedula.setText("");
				    txtNombre.setText("");
				    txtDireccion.setText("");
				    txtTelefono.setText("");
				}
			});
			btnLimpiar.setBounds(680, 48, 124, 29);
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
				        float costoTotal = Float.parseFloat(txtPrecioTotal.getText().replace("$", ""));
				        Cliente cliente = cargarCliente(txtCedula.getText());
				     
				        if (cliente != null) {
				            Factura factura = new Factura(idFactura, costoTotal, new ArrayList<>(componentesCarrito), cliente);
				            Empresa.getInstance().getLasFacturas().add(factura);
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
	            txtNombre.setText(cliente.getNombre());
	            txtDireccion.setText(cliente.getDireccion());
	            txtTelefono.setText(cliente.getTelefono());
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
            else if(!encontrado) {
            	JOptionPane.showMessageDialog(this, "No se encontró ningún componente con el ID: " + id, "Componente no encontrado", JOptionPane.INFORMATION_MESSAGE);
            	loadComponentes();
            }
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
}
