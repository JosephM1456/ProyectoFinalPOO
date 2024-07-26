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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import Logico.ArchivoEmpresa;
import Logico.Componente;
import Logico.Empresa;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Principal extends JFrame {

    private JPanel contentPane;
    private Dimension dim;
    private JPanel panel_1;
    private GridBagConstraints constr = new GridBagConstraints();
    private JPanel compPanel;
    private ArrayList<Componente> listaComp = Empresa.getInstance().getLosComponentes();
    private int rangoSup = 0;
    private int rangoInf = 0;
    private JScrollPane scrollPane;

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            UIManager.put("control", new Color(50, 50, 50));
            UIManager.put("info", new Color(50, 50, 50));
            UIManager.put("nimbusBase", new Color(0, 0, 0));
            UIManager.put("nimbusAlertYellow", new Color(255, 223, 0));
            UIManager.put("nimbusDisabledText", new Color(128, 128, 128));
            UIManager.put("nimbusFocus", new Color(0, 255, 0));
            UIManager.put("nimbusGreen", new Color(0, 255, 0));
            UIManager.put("nimbusInfoBlue", new Color(66, 139, 221));
            UIManager.put("nimbusLightBackground", new Color(50, 50, 50));
            UIManager.put("nimbusOrange", new Color(255, 200, 0));
            UIManager.put("nimbusRed", new Color(169, 46, 34));
            UIManager.put("nimbusSelectedText", new Color(255, 255, 255));
            UIManager.put("nimbusSelectionBackground", new Color(0, 255, 0));
            UIManager.put("text", new Color(255, 255, 255));
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
        setTitle("");
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
        titlePanel.setBackground(Color.BLACK);
        titlePanel.setPreferredSize(new Dimension(contentPane.getWidth(), 50));
        contentPane.add(titlePanel, BorderLayout.NORTH);

        JLabel titleLabel = new JLabel("Nombre de Tienda");
        titleLabel.setForeground(Color.GREEN);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);

        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(Color.DARK_GRAY);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        contentPane.add(menuPanel, BorderLayout.WEST);

        JButton btnComponentes = new JButton("Componentes");
        btnComponentes.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnComponentes.getMinimumSize().height));
        btnComponentes.setForeground(Color.WHITE);
        btnComponentes.setBackground(Color.BLACK);
        btnComponentes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegComponente regcomponente = new RegComponente(null);
                regcomponente.setModal(true);
                regcomponente.setVisible(true);
            }
        });
        menuPanel.add(btnComponentes);

        JButton btnListadoCompo = new JButton("Listado de Componentes");
        btnListadoCompo.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnListadoCompo.getMinimumSize().height));
        btnListadoCompo.setForeground(Color.WHITE);
        btnListadoCompo.setBackground(Color.BLACK);
        btnListadoCompo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ListComponentes ventana = new ListComponentes();
                ventana.setModal(true);
                ventana.setVisible(true);
            }
        });
        menuPanel.add(btnListadoCompo);

        JButton btnFactura = new JButton("Factura");
        btnFactura.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnFactura.getMinimumSize().height));
        btnFactura.setForeground(Color.WHITE);
        btnFactura.setBackground(Color.BLACK);
        btnFactura.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegFactura regfactura = new RegFactura();
                regfactura.setModal(true);
                regfactura.setVisible(true);
            }
        });
        menuPanel.add(btnFactura);

        JButton btnListadoFacturas = new JButton("Listado de Facturas ");
        btnListadoFacturas.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnListadoFacturas.getMinimumSize().height));
        btnListadoFacturas.setForeground(Color.WHITE);
        btnListadoFacturas.setBackground(Color.BLACK);
        btnListadoFacturas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        menuPanel.add(btnListadoFacturas);

        JButton btnCliente = new JButton("Clientes");
        btnCliente.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnCliente.getMinimumSize().height));
        btnCliente.setForeground(Color.WHITE);
        btnCliente.setBackground(Color.BLACK);
        btnCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegCliente regcliente = new RegCliente(null);
                regcliente.setModal(true);
                regcliente.setVisible(true);

            }
        });
        menuPanel.add(btnCliente);

        JButton btnListadoCliente = new JButton("Listado de Clientes");
        btnListadoCliente.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnListadoCliente.getMinimumSize().height));
        btnListadoCliente.setForeground(Color.WHITE);
        btnListadoCliente.setBackground(Color.BLACK);
        btnListadoCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

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

        scrollPane = new JScrollPane();
        scrollPane.setBounds(15, 16, 360, 876);
        panel_1.add(scrollPane);
        scrollPane.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        scrollPane.setBorder(new TitledBorder(null, "Componentes", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GREEN));

        compPanel = new JPanel();
        scrollPane.setViewportView(compPanel);
        compPanel.setAutoscrolls(true);
        FlowLayout fl_compPanel = new FlowLayout(FlowLayout.LEADING, 10, 5);
        fl_compPanel.setAlignOnBaseline(true);
        compPanel.setMinimumSize(new Dimension(300, 100));
        compPanel.setPreferredSize(new Dimension(340, 1000));
        compPanel.setMaximumSize(new Dimension(360, 5000));
        compPanel.setLayout(fl_compPanel);

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
            ex.printStackTrace();
        }
    }

    private void guardarEmpresa() {
        try {
            ArchivoEmpresa.getInstance().guardarEmpresa(Empresa.getInstance());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
