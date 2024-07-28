package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import Logico.Cliente;
import Logico.Visitante;
import Logico.Empleado;
import Logico.Empresa;
import javax.swing.border.TitledBorder;

public class RegCliente extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField idTextField;
    private JTextField direccionTextField;
    private JTextField telefonoTextField;
    private JTextField cedulaTextField;
    private JTextField sueldoTotalTextField;
    private JTextField cantAniosTrabajandoTextField;
    private JTextField descuentoTextField;

    private JPanel panelVisitante;
    private JPanel panelEmpleado;

    private JRadioButton btnVisitante;
    private JRadioButton btnEmpleado;
    private JTextField txtNombre;

    public static void main(String[] args) {
        try {
            RegCliente dialog = new RegCliente(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RegCliente(Cliente aux) {
        setBounds(100, 100, 500, 565);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(null);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(30, 20, 80, 25);
        contentPanel.add(lblId);

        idTextField = new JTextField();
        idTextField.setEditable(false);
        idTextField.setBounds(120, 20, 200, 25);
        contentPanel.add(idTextField);
        idTextField.setColumns(10);
        if (aux != null) {
            idTextField.setText(aux.getIdCliente());
        } else {
            idTextField.setText("CL-" + Empresa.getInstance().countIdCliente());
        }

        JLabel lblDireccion = new JLabel("Direccion:");
        lblDireccion.setBounds(30, 60, 80, 25);
        contentPanel.add(lblDireccion);

        direccionTextField = new JTextField();
        direccionTextField.setBounds(120, 60, 200, 25);
        contentPanel.add(direccionTextField);
        direccionTextField.setColumns(10);

        JLabel lblTelefono = new JLabel("Telefono:");
        lblTelefono.setBounds(30, 100, 80, 25);
        contentPanel.add(lblTelefono);

        telefonoTextField = new JTextField();
        telefonoTextField.setBounds(120, 100, 200, 25);
        contentPanel.add(telefonoTextField);
        telefonoTextField.setColumns(10);

        JLabel lblCedula = new JLabel("Cedula:");
        lblCedula.setBounds(30, 140, 80, 25);
        contentPanel.add(lblCedula);

        cedulaTextField = new JTextField();
        cedulaTextField.setBounds(120, 139, 200, 25);
        contentPanel.add(cedulaTextField);
        cedulaTextField.setColumns(10);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBorder(new TitledBorder(null, "Tipo de Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        layeredPane.setBounds(30, 214, 400, 50);
        contentPanel.add(layeredPane);

        ButtonGroup group = new ButtonGroup();

        btnVisitante = new JRadioButton("Visitante");
        btnVisitante.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnVisitante.setBounds(77, 30, 100, 25);
        btnVisitante.setSelected(true);
        btnVisitante.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                panelVisitante.setVisible(true);
                panelEmpleado.setVisible(false);
            }
        });
        group.add(btnVisitante);
        layeredPane.add(btnVisitante, new Integer(1));

        btnEmpleado = new JRadioButton("Empleado");
        btnEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnEmpleado.setBounds(209, 30, 100, 25);
        btnEmpleado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                panelVisitante.setVisible(false);
                panelEmpleado.setVisible(true);
            }
        });
        group.add(btnEmpleado);
        layeredPane.add(btnEmpleado, new Integer(1));

        panelVisitante = new JPanel();
        panelVisitante.setBounds(30, 270, 400, 160);
        contentPanel.add(panelVisitante);
        panelVisitante.setLayout(null);

        panelEmpleado = new JPanel();
        panelEmpleado.setBounds(30, 270, 400, 160);
        contentPanel.add(panelEmpleado);
        panelEmpleado.setLayout(null);

        JLabel lblSueldoTotal = new JLabel("Sueldo Total:");
        lblSueldoTotal.setBounds(10, 10, 100, 25);
        panelEmpleado.add(lblSueldoTotal);

        sueldoTotalTextField = new JTextField();
        sueldoTotalTextField.setBounds(120, 10, 200, 25);
        panelEmpleado.add(sueldoTotalTextField);
        sueldoTotalTextField.setColumns(10);

        JLabel lblCantAniosTrabajando = new JLabel("Cant. Años Trabajando:");
        lblCantAniosTrabajando.setBounds(10, 40, 150, 25);
        panelEmpleado.add(lblCantAniosTrabajando);

        cantAniosTrabajandoTextField = new JTextField();
        cantAniosTrabajandoTextField.setBounds(170, 40, 150, 25);
        panelEmpleado.add(cantAniosTrabajandoTextField);
        cantAniosTrabajandoTextField.setColumns(10);

        JLabel lblDescuento = new JLabel("Descuento:");
        lblDescuento.setBounds(10, 70, 100, 25);
        panelEmpleado.add(lblDescuento);

        descuentoTextField = new JTextField();
        descuentoTextField.setBounds(120, 70, 200, 25);
        panelEmpleado.add(descuentoTextField);
        descuentoTextField.setColumns(10);
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 173, 80, 25);
        contentPanel.add(lblNombre);
        
        txtNombre = new JTextField();
        txtNombre.setColumns(10);
        txtNombre.setBounds(120, 173, 200, 25);
        contentPanel.add(txtNombre);

        panelEmpleado.setVisible(false);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	if(aux == null)
            	{
	                if (direccionTextField.getText().isEmpty() || telefonoTextField.getText().isEmpty() || cedulaTextField.getText().isEmpty() || txtNombre.getText().isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "Por favor complete todos los campos.");
	                } else {
	                    try {
							if (btnVisitante.isSelected()) {
							    Visitante visitante = new Visitante(idTextField.getText(), direccionTextField.getText(), telefonoTextField.getText(), cedulaTextField.getText(), txtNombre.getText(),0);
							    Empresa.getInstance().insertarCliente(visitante);
							} else if (btnEmpleado.isSelected()) {
							    if (sueldoTotalTextField.getText().isEmpty() || cantAniosTrabajandoTextField.getText().isEmpty() || descuentoTextField.getText().isEmpty()) {
							        JOptionPane.showMessageDialog(null, "Por favor complete todos los campos de empleado.");
							        return;
							    }
							    Empleado empleado = new Empleado(idTextField.getText(), direccionTextField.getText(), telefonoTextField.getText(), cedulaTextField.getText(), txtNombre.getText(), Float.parseFloat(sueldoTotalTextField.getText()), Integer.parseInt(cantAniosTrabajandoTextField.getText()), Integer.parseInt(descuentoTextField.getText()));
							    Empresa.getInstance().insertarCliente(empleado);
							}

						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Por favor llene los campos con los tipos de datos apropiados", "Error de registro", JOptionPane.ERROR_MESSAGE);
						}
	                    limpiarCampos();
	                    JOptionPane.showMessageDialog(null, "Cliente registrado con éxito.");
	                }            		
            	}
            	else if(aux != null)
            	{
	                if (direccionTextField.getText().isEmpty() || telefonoTextField.getText().isEmpty() || cedulaTextField.getText().isEmpty() || txtNombre.getText().isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "Por favor complete todos los campos.");
	                }
	                else
	                {
	                	try
	                	{
	                		aux.setCedula(cedulaTextField.getText());
	                		aux.setTelefono(telefonoTextField.getText());
	                		aux.setNombre(txtNombre.getText());
	                		aux.setDireccion(direccionTextField.getText());
	                		if(btnEmpleado.isSelected())
	                		{
	                			((Empleado)aux).setCantAniosTrabajando(Integer.parseInt(cantAniosTrabajandoTextField.getText()));
	                			((Empleado)aux).setDescuento(Integer.parseInt(descuentoTextField.getText()));
	                			((Empleado)aux).setSueldoTotal(Float.parseFloat(sueldoTotalTextField.getText()));
	                		}
	                		JOptionPane.showMessageDialog(null, "Cliente actualizado con éxito.");
	                		dispose();
	                	}
	                	catch(NumberFormatException e)
	                	{
	                		JOptionPane.showMessageDialog(null, "Por favor llene los campos con los tipos de datos apropiados", "Error de registro", JOptionPane.ERROR_MESSAGE);
	                	}
	                }
            	}
            }
        });
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
        
        if(aux != null)
        {
        	modoActualizar(aux);
        }
    }

    private void limpiarCampos() {
    	idTextField.setText("CL-" + Empresa.getInstance().countIdCliente());
        direccionTextField.setText("");
        txtNombre.setText("");
        telefonoTextField.setText("");
        cedulaTextField.setText("");
        sueldoTotalTextField.setText("");
        cantAniosTrabajandoTextField.setText("");
        descuentoTextField.setText("");
        btnVisitante.setSelected(true);
        panelVisitante.setVisible(true);
        panelEmpleado.setVisible(false);
    }
    
    private void modoActualizar(Cliente cliente)
    {
    	btnEmpleado.setEnabled(false);
    	btnVisitante.setEnabled(false);
    	idTextField.setEnabled(false);
    	idTextField.setText(cliente.getIdCliente());
    	cedulaTextField.setText(cliente.getCedula());
    	direccionTextField.setText(cliente.getDireccion());
    	txtNombre.setText(cliente.getNombre());
    	telefonoTextField.setText(cliente.getTelefono());
    	
    	if(cliente instanceof Empleado)
    	{
    		panelVisitante.setVisible(false);
    		panelEmpleado.setVisible(true);
    		btnEmpleado.setSelected(true);
    		cantAniosTrabajandoTextField.setText( ((Integer)((Empleado)cliente).getCantAniosTrabajando()).toString() );
    		descuentoTextField.setText( ((Integer)( (Empleado)cliente).getDescuento()).toString() );
    		sueldoTotalTextField.setText( ((Float)((Empleado)cliente).getSueldoTotal()).toString() );
    	}
    	else btnVisitante.setSelected(true);
    }
}