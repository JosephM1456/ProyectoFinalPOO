package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLayeredPane;

import Logico.Componente;
import Logico.Empresa;
import Logico.DiscoDuro;
import Logico.MicroProcesador;
import Logico.RAM;
import Logico.TarjetaMadre;

import java.util.ArrayList;

public class RegComponente extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField idTextField;
    private JTextField numSerieTextField;
    private JTextField precioTextField;
    private JTextField cantDispTextField;
    private JTextField marcaTextField;
    private JTextField modeloTextField;
    private JTextField tipoConexTextField;
    private JTextField capAlmTextField;
    private JTextField velProcTextField;
    private JTextField cantGBTextField;
    private JTextField tipoMemTextField;
    private JTextField conectorTextField;
    private JTextField tipoRamTextField;
    private JTextField tipoConexTextField2;

    private JPanel panelDiscoDuro;
    private JPanel panelMicroProcesador;
    private JPanel panelRAM;
    private JPanel panelTarjetaMadre;

    private JRadioButton btnDiscoDuro;
    private JRadioButton btnMicroProcesador;
    private JRadioButton btnRAM;
    private JRadioButton btnTarjetaMadre;

    public static void main(String[] args) {
		Empresa.getInstance();
		
//		DiscoDuro d1 = new DiscoDuro("C-0", "D10", (float)150.10 , 3, "Nvidia", "Rawr", "SATA-1", (float)1024.0);
//		DiscoDuro d2 = new DiscoDuro("C-0", "D10", (float)150.10 , 3, "Nvidia", "Hola", "IDE", (float)1024.0);
//		DiscoDuro d3 = new DiscoDuro("C-0", "D10", (float)150.10 , 3, "Nvidia", "Hol", "IDE", (float)1024.0);
//		DiscoDuro d4 = new DiscoDuro("C-0", "D10", (float)150.10 , 3, "Nvidia", "Ho", "IDE", (float)1024.0);
//		DiscoDuro d5 = new DiscoDuro("C-0", "D10", (float)150.10 , 3, "Nvidia", "Huh?", "IDE", (float)1024.0);
//		DiscoDuro d6 = new DiscoDuro("C-0", "D10", (float)150.10 , 3, "Nvidia", "mambo", "IDE", (float)1024.0);
//		DiscoDuro d7 = new DiscoDuro("C-0", "D10", (float)150.10 , 3, "Vota #1 L24", "Lui Abinad", "PRM", (float)1024.0);
//		
//		Empresa.getInstance().insertarComponente(d1);
//		Empresa.getInstance().insertarComponente(d2);
//		Empresa.getInstance().insertarComponente(d3);
//		Empresa.getInstance().insertarComponente(d4);
//		Empresa.getInstance().insertarComponente(d5);
//		Empresa.getInstance().insertarComponente(d6);
//		Empresa.getInstance().insertarComponente(d7);
//		
//		Principal ventana = new Principal();
//		ventana.setVisible(true);
    	
    	try {
            RegComponente dialog = new RegComponente(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RegComponente(Componente aux) {
        setBounds(100, 100, 721, 522);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(null);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(54, 20, 80, 25);
        contentPanel.add(lblId);

        idTextField = new JTextField();
        idTextField.setEditable(false);
        idTextField.setBounds(144, 20, 200, 25);
        contentPanel.add(idTextField);
        idTextField.setColumns(10);
        if (aux != null) {
            idTextField.setText(aux.getIdComponente());
        } else {
            idTextField.setText("C-" + Empresa.getIdComponente());
        }

        JLabel lblNumSerie = new JLabel("Num Serie:");
        lblNumSerie.setBounds(54, 56, 80, 25);
        contentPanel.add(lblNumSerie);

        numSerieTextField = new JTextField();
        numSerieTextField.setBounds(144, 56, 200, 25);
        contentPanel.add(numSerieTextField);
        numSerieTextField.setColumns(10);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(54, 92, 80, 25);
        contentPanel.add(lblPrecio);

        precioTextField = new JTextField();
        precioTextField.setBounds(144, 92, 200, 25);
        contentPanel.add(precioTextField);
        precioTextField.setColumns(10);

        JLabel lblCantDisp = new JLabel("Cant. Disp:");
        lblCantDisp.setBounds(54, 140, 80, 25);
        contentPanel.add(lblCantDisp);

        cantDispTextField = new JTextField();
        cantDispTextField.setBounds(144, 140, 200, 25);
        contentPanel.add(cantDispTextField);
        cantDispTextField.setColumns(10);

        JLabel lblMarca = new JLabel("Marca:");
        lblMarca.setBounds(54, 176, 80, 25);
        contentPanel.add(lblMarca);

        marcaTextField = new JTextField();
        marcaTextField.setBounds(144, 180, 200, 25);
        contentPanel.add(marcaTextField);
        marcaTextField.setColumns(10);

        JLabel lblModelo = new JLabel("Modelo:");
        lblModelo.setBounds(54, 212, 80, 25);
        contentPanel.add(lblModelo);

        modeloTextField = new JTextField();
        modeloTextField.setBounds(144, 216, 200, 25);
        contentPanel.add(modeloTextField);
        modeloTextField.setColumns(10);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(354, 11, 351, 150);
        contentPanel.add(layeredPane);

        JLabel lblTipoComp = new JLabel("Tipo de Componentes");
        lblTipoComp.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTipoComp.setBounds(88, 11, 200, 25);
        layeredPane.add(lblTipoComp, new Integer(1));

        ButtonGroup group = new ButtonGroup();

        btnDiscoDuro = new JRadioButton("Disco Duro");
        btnDiscoDuro.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnDiscoDuro.setBounds(59, 56, 100, 25);
        btnDiscoDuro.setSelected(true);
        btnDiscoDuro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                panelDiscoDuro.setVisible(true);
                panelMicroProcesador.setVisible(false);
                panelRAM.setVisible(false);
                panelTarjetaMadre.setVisible(false);
            }
        });
        group.add(btnDiscoDuro);
        layeredPane.add(btnDiscoDuro, new Integer(1));

        btnMicroProcesador = new JRadioButton("MicroProcesador");
        btnMicroProcesador.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnMicroProcesador.setBounds(58, 84, 130, 25);
        btnMicroProcesador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                panelDiscoDuro.setVisible(false);
                panelMicroProcesador.setVisible(true);
                panelRAM.setVisible(false);
                panelTarjetaMadre.setVisible(false);
            }
        });
        group.add(btnMicroProcesador);
        layeredPane.add(btnMicroProcesador, new Integer(1));

        btnRAM = new JRadioButton("RAM");
        btnRAM.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnRAM.setBounds(200, 56, 80, 25);
        btnRAM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelDiscoDuro.setVisible(false);
                panelMicroProcesador.setVisible(false);
                panelRAM.setVisible(true);
                panelTarjetaMadre.setVisible(false);
            }
        });
        group.add(btnRAM);
        layeredPane.add(btnRAM, new Integer(1));

        btnTarjetaMadre = new JRadioButton("Tarjeta Madre");
        btnTarjetaMadre.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnTarjetaMadre.setBounds(190, 84, 120, 25);
        btnTarjetaMadre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelDiscoDuro.setVisible(false);
                panelMicroProcesador.setVisible(false);
                panelRAM.setVisible(false);
                panelTarjetaMadre.setVisible(true);
            }
        });
        group.add(btnTarjetaMadre);
        layeredPane.add(btnTarjetaMadre, new Integer(1));

        panelDiscoDuro = new JPanel();
        panelDiscoDuro.setBounds(15, 320, 400, 80);
        contentPanel.add(panelDiscoDuro);
        panelDiscoDuro.setLayout(null);

        JLabel lblTipoConexDD = new JLabel("Tipo Conex:");
        lblTipoConexDD.setBounds(10, 10, 80, 25);
        panelDiscoDuro.add(lblTipoConexDD);

        tipoConexTextField = new JTextField();
        tipoConexTextField.setBounds(100, 10, 200, 25);
        panelDiscoDuro.add(tipoConexTextField);
        tipoConexTextField.setColumns(10);

        JLabel lblCapAlmDD = new JLabel("Cap. Almacenamiento:");
        lblCapAlmDD.setBounds(10, 45, 150, 25);
        panelDiscoDuro.add(lblCapAlmDD);

        capAlmTextField = new JTextField();
        capAlmTextField.setBounds(160, 45, 140, 25);
        panelDiscoDuro.add(capAlmTextField);
        capAlmTextField.setColumns(10);

        panelMicroProcesador = new JPanel();
        panelMicroProcesador.setBounds(15, 320, 400, 80);
        contentPanel.add(panelMicroProcesador);
        panelMicroProcesador.setLayout(null);

        JLabel lblTipoConexMP = new JLabel("Tipo Conex:");
        lblTipoConexMP.setBounds(10, 10, 80, 25);
        panelMicroProcesador.add(lblTipoConexMP);

        tipoConexTextField2 = new JTextField();
        tipoConexTextField2.setBounds(100, 10, 200, 25);
        panelMicroProcesador.add(tipoConexTextField2);
        tipoConexTextField2.setColumns(10);

        JLabel lblVelProcMP = new JLabel("Vel. Procesamiento:");
        lblVelProcMP.setBounds(10, 45, 150, 25);
        panelMicroProcesador.add(lblVelProcMP);

        velProcTextField = new JTextField();
        velProcTextField.setBounds(160, 45, 140, 25);
        panelMicroProcesador.add(velProcTextField);
        velProcTextField.setColumns(10);

        panelRAM = new JPanel();
        panelRAM.setBounds(15, 320, 400, 80);
        contentPanel.add(panelRAM);
        panelRAM.setLayout(null);

        JLabel lblCantGB = new JLabel("Cant. GB:");
        lblCantGB.setBounds(10, 10, 80, 25);
        panelRAM.add(lblCantGB);

        cantGBTextField = new JTextField();
        cantGBTextField.setBounds(100, 10, 200, 25);
        panelRAM.add(cantGBTextField);
        cantGBTextField.setColumns(10);

        JLabel lblTipoMem = new JLabel("Tipo Memoria:");
        lblTipoMem.setBounds(10, 45, 100, 25);
        panelRAM.add(lblTipoMem);

        tipoMemTextField = new JTextField();
        tipoMemTextField.setBounds(110, 45, 190, 25);
        panelRAM.add(tipoMemTextField);
        tipoMemTextField.setColumns(10);

        panelTarjetaMadre = new JPanel();
        panelTarjetaMadre.setBounds(15, 320, 400, 80);
        contentPanel.add(panelTarjetaMadre);
        panelTarjetaMadre.setLayout(null);

        JLabel lblConector = new JLabel("Conector:");
        lblConector.setBounds(10, 10, 80, 25);
        panelTarjetaMadre.add(lblConector);

        conectorTextField = new JTextField();
        conectorTextField.setBounds(100, 10, 200, 25);
        panelTarjetaMadre.add(conectorTextField);
        conectorTextField.setColumns(10);

        JLabel lblTipoRam = new JLabel("Tipo RAM:");
        lblTipoRam.setBounds(10, 45, 80, 25);
        panelTarjetaMadre.add(lblTipoRam);

        tipoRamTextField = new JTextField();
        tipoRamTextField.setBounds(100, 45, 200, 25);
        panelTarjetaMadre.add(tipoRamTextField);
        tipoRamTextField.setColumns(10);

        panelMicroProcesador.setVisible(false);
        panelRAM.setVisible(false);
        panelTarjetaMadre.setVisible(false);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (numSerieTextField.getText().isEmpty() || precioTextField.getText().isEmpty() || cantDispTextField.getText().isEmpty()
                        || marcaTextField.getText().isEmpty() || modeloTextField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor complete todos los campos.");
                } else {
                    try {
                        String id = idTextField.getText();
                        String numSerie = numSerieTextField.getText();
                        float precio = Float.parseFloat(precioTextField.getText());
                        int cantDisp = Integer.parseInt(cantDispTextField.getText());
                        String marca = marcaTextField.getText();
                        String modelo = modeloTextField.getText();

                        if (btnDiscoDuro.isSelected()) {
                            String tipoConex = tipoConexTextField.getText();
                            float capAlm = Float.parseFloat(capAlmTextField.getText());
                            DiscoDuro discoDuro = new DiscoDuro(id, numSerie, precio, cantDisp, marca, modelo, tipoConex, capAlm);
                            Empresa.getInstance().insertarComponente(discoDuro);
                        } else if (btnMicroProcesador.isSelected()) {
                            String tipoConex = tipoConexTextField.getText();
                            float velProc = Float.parseFloat(velProcTextField.getText());
                            MicroProcesador microProcesador = new MicroProcesador(id, numSerie, precio, cantDisp, marca, modelo, tipoConex, velProc);
                            Empresa.getInstance().insertarComponente(microProcesador);
                        } else if (btnRAM.isSelected()) {
                            float cantGB = Float.parseFloat(cantGBTextField.getText());
                            String tipoMem = tipoMemTextField.getText();
                            RAM ram = new RAM(id, numSerie, precio, cantDisp, marca, modelo, cantGB, tipoMem);
                            Empresa.getInstance().insertarComponente(ram);
                        } else if (btnTarjetaMadre.isSelected()) {
                            String conector = conectorTextField.getText();
                            String tipoRam = tipoRamTextField.getText();
                            ArrayList<String> conexiones = new ArrayList<>(); 
                            TarjetaMadre tarjetaMadre = new TarjetaMadre(id, numSerie, precio, cantDisp, marca, modelo, conector, tipoRam, conexiones);
                            Empresa.getInstance().insertarComponente(tarjetaMadre);
                        }

                        limpiarCampos();
                        JOptionPane.showMessageDialog(null, "Componente registrado con éxito.");
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Por favor ingrese valores numéricos válidos en los campos de precio, cantidad, capacidad de almacenamiento, velocidad de procesamiento y cantidad de GB.");
                    }
                }
            }
        });
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		dispose();
        	}
        });
        buttonPane.add(btnCancelar);
    }

    private void limpiarCampos() {
    	idTextField.setText("C-" + Empresa.getIdComponente());
        numSerieTextField.setText("");
        precioTextField.setText("");
        cantDispTextField.setText("");
        marcaTextField.setText("");
        modeloTextField.setText("");
        tipoConexTextField.setText("");
        capAlmTextField.setText("");
        velProcTextField.setText("");
        cantGBTextField.setText("");
        tipoMemTextField.setText("");
        conectorTextField.setText("");
        tipoRamTextField.setText("");
        tipoConexTextField2.setText("");
    }
}