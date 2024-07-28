package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import Logico.ArchivoEmpresa;
import Logico.Empresa;
import java.awt.GridBagConstraints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;


public class Principal extends JFrame {

    private JPanel contentPane;
    private Dimension dim;
    private JPanel panel_1;
    private GridBagConstraints constr = new GridBagConstraints();



    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
//            UIManager.put("control", new Color(50, 50, 50));
//            UIManager.put("info", new Color(50, 50, 50));
//            UIManager.put("nimbusBase", new Color(0, 0, 0));
//            UIManager.put("nimbusAlertYellow", new Color(255, 223, 0));
//            UIManager.put("nimbusDisabledText", new Color(128, 128, 128));
//            UIManager.put("nimbusFocus", new Color(0, 255, 0));
//            UIManager.put("nimbusGreen", new Color(0, 255, 0));
//            UIManager.put("nimbusInfoBlue", new Color(66, 139, 221));
//            UIManager.put("nimbusLightBackground", new Color(50, 50, 50));
//            UIManager.put("nimbusOrange", new Color(255, 200, 0));
//            UIManager.put("nimbusRed", new Color(169, 46, 34));
//            UIManager.put("nimbusSelectedText", new Color(255, 255, 255));
//            UIManager.put("nimbusSelectionBackground", new Color(0, 255, 0));
//            UIManager.put("text", new Color(255, 255, 255));
        } catch (Exception e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal frame = new Principal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Principal() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/img/black_server_icon-icons.com_76717.png")));
        setTitle("SystemComputerMaster Enterprise");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        dim = getToolkit().getScreenSize();
        setSize(2415, 1427);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        // Custom title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(contentPane.getWidth(), 50));
        contentPane.add(titlePanel, BorderLayout.NORTH);

        JLabel titleLabel = new JLabel("SysCompMaster\u2122");
        titleLabel.setFont(new Font("Segoe UI", Font.ITALIC, 24));
        titlePanel.add(titleLabel);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        contentPane.add(menuPanel, BorderLayout.WEST);

        JButton btnComponentes = new JButton("Componentes");
        btnComponentes.setIcon(new ImageIcon(Principal.class.getResource("/img/motherboard_46935.png")));
        btnComponentes.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnComponentes.getMinimumSize().height));
        btnComponentes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegComponente regcomponente = new RegComponente(null);
                regcomponente.setModal(true);
                regcomponente.setVisible(true);
            }
        });
        menuPanel.add(btnComponentes);

        JButton btnListadoCompo = new JButton("Listado de Componentes");
        btnListadoCompo.setIcon(new ImageIcon(Principal.class.getResource("/img/Folder_icon-icons.com_76516.png")));
        btnListadoCompo.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnListadoCompo.getMinimumSize().height));
        btnListadoCompo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ListComponentes ventana = new ListComponentes();
                ventana.setModal(true);
                ventana.setVisible(true);
            }
        });
        menuPanel.add(btnListadoCompo);

        JButton btnFactura = new JButton("Factura");
        btnFactura.setIcon(new ImageIcon(Principal.class.getResource("/img/credit_card_22167.png")));
        btnFactura.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnFactura.getMinimumSize().height));
        btnFactura.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegFactura regfactura = new RegFactura();
                regfactura.setModal(true);
                regfactura.setVisible(true);
            }
        });
        menuPanel.add(btnFactura);

        JButton btnListadoFacturas = new JButton("Listado de Facturas ");
        btnListadoFacturas.setIcon(new ImageIcon(Principal.class.getResource("/img/invoice_22150.png")));
        btnListadoFacturas.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnListadoFacturas.getMinimumSize().height));
        btnListadoFacturas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        menuPanel.add(btnListadoFacturas);

        JButton btnCliente = new JButton("Clientes");
        btnCliente.setIcon(new ImageIcon(Principal.class.getResource("/img/admin_user_man_22187.png")));
        btnCliente.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnCliente.getMinimumSize().height));
        btnCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegCliente regcliente = new RegCliente(null);
                regcliente.setModal(true);
                regcliente.setVisible(true);

            }
        });
        menuPanel.add(btnCliente);

        JButton btnListadoCliente = new JButton("Listado de Clientes");
        btnListadoCliente.setIcon(new ImageIcon(Principal.class.getResource("/img/addressbook_104329.png")));
        btnListadoCliente.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnListadoCliente.getMinimumSize().height));
        btnListadoCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ListClientes ventana = new ListClientes();
            	ventana.setModal(true);
            	ventana.setVisible(true);
            }
        });
        menuPanel.add(btnListadoCliente);

        constr.gridheight = 100;
        constr.gridwidth = 75;
        constr.fill = GridBagConstraints.NONE;
        constr.ipadx = 10;
        constr.ipady = 5;
        constr.gridy = 0;
        constr.weightx = 1.0;
        constr.weighty = 1.0;

        JPanel panel = new JPanel();
        panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setVgap(10);
        contentPane.add(panel, BorderLayout.SOUTH);
        panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 255, 0)));
        contentPane.add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(null);

        cargarEmpresa();

   
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                guardarEmpresa();
            }
        });
    }

    private void cargarEmpresa() {
        try {
            ArchivoEmpresa.getInstance().cargarEmpresa("empresa.txt");
        } catch (IOException | ClassNotFoundException ex) {
        	JOptionPane.showMessageDialog( null, "Datos de empresa no encontrados, iniciando programa con empresa nueva...");
        }
    }

    private void guardarEmpresa() {
        try {
            ArchivoEmpresa.getInstance().guardarEmpresa(Empresa.getInstance());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar los datos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
