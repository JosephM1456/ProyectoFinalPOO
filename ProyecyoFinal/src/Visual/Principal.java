package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import Logico.ArchivoEmpresa;
import Logico.Cliente;
import Logico.Componente;
import Logico.ConexionBD;
import Logico.DiscoDuro;
import Logico.Empresa;
import Logico.Factura;
import Logico.MicroProcesador;
import Logico.RAM;
import Logico.TarjetaMadre;

import java.awt.GridBagConstraints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Principal extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private JPanel panel_1;
	private GridBagConstraints constr = new GridBagConstraints();

	static Socket sfd = null;
	static ObjectInputStream EntradaSocket;
	static ObjectOutputStream SalidaSocket;
	private JPanel panelSaludo;
	private JLabel lblBienvenido;
	private JLabel lblDatos;
	private JPanel panelGrafica1;
	private JPanel panelGrafica2;



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
				regcomponente.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e)
					{
						lblDatos.setText("Se tiene registrado "+Empresa.getInstance().getLosClientes().size()+" Cliente(s), "+Empresa.getInstance().getLosComponentes().size()
								+"Componente(s) y "+Empresa.getInstance().getLasFacturas().size()+" Facturas");
						lblDatos.revalidate();
						lblDatos.repaint();
					}
				});
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
				ventana.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e)
					{
						lblDatos.setText("Se tiene registrado "+Empresa.getInstance().getLosClientes().size()+" Cliente(s), "+Empresa.getInstance().getLosComponentes().size()
								+"Componente(s) y "+Empresa.getInstance().getLasFacturas().size()+" Facturas");
						lblDatos.revalidate();
						lblDatos.repaint();
					}
				});
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
				regfactura.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e)
					{
						lblDatos.setText("Se tiene registrado "+Empresa.getInstance().getLosClientes().size()+" Cliente(s), "+Empresa.getInstance().getLosComponentes().size()
								+"Componente(s) y "+Empresa.getInstance().getLasFacturas().size()+" Facturas");
						lblDatos.revalidate();
						lblDatos.repaint();
					}
				});
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
				ListFactura listfactura = new ListFactura();
				listfactura.setModal(true);
				listfactura.setVisible(true);
			}
		});
		menuPanel.add(btnListadoFacturas);

		JButton btnCliente = new JButton("Clientes");
		btnCliente.setIcon(new ImageIcon(Principal.class.getResource("/img/admin_user_man_22187.png")));
		btnCliente.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnCliente.getMinimumSize().height));
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegCliente regcliente = new RegCliente(null);
				regcliente.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e)
					{
						lblDatos.setText("Se tiene registrado "+Empresa.getInstance().getLosClientes().size()+" Cliente(s), "+Empresa.getInstance().getLosComponentes().size()
								+"Componente(s) y "+Empresa.getInstance().getLasFacturas().size()+" Facturas");
						lblDatos.revalidate();
						lblDatos.repaint();
					}
				});
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
				ventana.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e)
					{
						lblDatos.setText("Se tiene registrado "+Empresa.getInstance().getLosClientes().size()+" Cliente(s), "+Empresa.getInstance().getLosComponentes().size()
								+"Componente(s) y "+Empresa.getInstance().getLasFacturas().size()+" Facturas");
						lblDatos.revalidate();
						lblDatos.repaint();
					}
				});
				ventana.setModal(true);
				ventana.setVisible(true);
			}
		});
		menuPanel.add(btnListadoCliente);

		JButton btnRespaldo = new JButton("Respaldo");
		btnRespaldo.setIcon(new ImageIcon(Principal.class.getResource("/img/backup.png")));

		btnRespaldo.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnRespaldo.getMinimumSize().height));
		btnRespaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Socket sfd = new Socket("localhost", 7000);
					ObjectOutputStream SalidaSocket = new ObjectOutputStream(sfd.getOutputStream());

					Empresa empresa = Empresa.getInstance();
					SalidaSocket.writeObject(empresa);
					SalidaSocket.flush();

					JOptionPane.showMessageDialog(null, "Respaldo enviado con éxito.");

					SalidaSocket.close();
					sfd.close();
				} catch (UnknownHostException uhe) {
					JOptionPane.showMessageDialog(null, "No se puede acceder al servidor: " + uhe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (IOException ioe) {
					JOptionPane.showMessageDialog(null, "Error de comunicación: " + ioe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		menuPanel.add(btnRespaldo);

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

		panelSaludo = new JPanel();
		panelSaludo.setBounds(177, 39, 945, 162);
		panel_1.add(panelSaludo);
		panelSaludo.setLayout(null);

		lblBienvenido = new JLabel("Bienvenido al sistema SysCompMaster");
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenido.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		lblBienvenido.setBounds(283, 16, 378, 54);
		panelSaludo.add(lblBienvenido);

		lblDatos = new JLabel("New label");
		lblDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatos.setBounds(204, 102, 537, 20);
		panelSaludo.add(lblDatos);

		//cargarEmpresa();
		cargarEmpresaDesdeBD();
		//guardarFacturasEnBD();
		//guardarClientesEnBD();
		//guardarComponentesEnBD();
		lblDatos.setText("Se tiene registrado "+Empresa.getInstance().getLosClientes().size()+" Cliente(s), "+Empresa.getInstance().getLosComponentes().size()
				+" Componente(s) y "+Empresa.getInstance().getLasFacturas().size()+" Facturas");

		JLabel lblEstadistica = new JLabel("Estad\u00EDsticas:");
		lblEstadistica.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		lblEstadistica.setBounds(25, 309, 91, 20);
		panel_1.add(lblEstadistica);

		panelGrafica1 = new JPanel();
		panelGrafica1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 255, 0)));
		panelGrafica1.setBounds(25, 345, 529, 378);
		panel_1.add(panelGrafica1);

		panelGrafica2 = new JPanel();
		panelGrafica2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 255, 0)));
		panelGrafica2.setBounds(627, 345, 529, 378);
		panel_1.add(panelGrafica2);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				guardarEmpresa();
			}
		});
		generarGrafica();
		generarGraficaModa();
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

	private void generarGrafica()
	{
		ArrayList<Componente>listaComp = Empresa.getInstance().getLosComponentes();
		Double cantDiscoDuro = 0.0;
		Double cantMicroChip = 0.0;
		Double cantRam = 0.0;
		Double cantTarjetaMadre = 0.0;

		for(Componente ind : listaComp)
		{
			if(ind instanceof DiscoDuro)
			{
				cantDiscoDuro++;
			}
			else if(ind instanceof MicroProcesador)
			{
				cantMicroChip++;
			}
			else if(ind instanceof RAM)
			{
				cantRam++;
			}
			else if(ind instanceof TarjetaMadre)
			{
				cantTarjetaMadre++;
			}
		}

		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("Disco Duro", ((cantDiscoDuro/listaComp.size())*100) );
		data.setValue("Micro Procesador", (cantMicroChip/listaComp.size())*100 );
		data.setValue("RAM", (cantRam/listaComp.size())*100 );
		data.setValue("Tarjeta Madre", (cantTarjetaMadre/listaComp.size())*100 );

		JFreeChart chart = ChartFactory.createPieChart("Proporciones del listado de Componentes"
				, data, true, true, false);

		ChartPanel panelGrafica = new ChartPanel(chart);
		panelGrafica.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panelGrafica.setPreferredSize(new Dimension(panelGrafica1.getWidth()-30, panelGrafica1.getHeight()-20));
		panelGrafica.setBackground(getBackground());
		panelGrafica.setForeground(getForeground());
		//panelGrafica.setFont(titleLabel.getFont());
		panelGrafica1.add(panelGrafica);


	}

	private void generarGraficaModa()
	{
		ArrayList<Componente>listaComp = new ArrayList<Componente>();
		ArrayList<Double>listaCompCant = new ArrayList<Double>();
		ArrayList<Factura>listafacturas = Empresa.getInstance().getLasFacturas();
		Double restoVentas = new Double(0.0);

		for(int i = 0; i < Empresa.getInstance().getLosComponentes().size(); i++)
		{
			listaCompCant.add(0.0);
			listaComp.add(Empresa.getInstance().getLosComponentes().get(i));
		}

		System.out.println(listaComp.size());

		for(Factura indFact : listafacturas)
		{
			for(Componente ind : indFact.getLosComponentes())
			{
				int index = listaComp.indexOf(ind);
				listaCompCant.set(index, listaCompCant.get(index)+1);
			}
		}


		quickSort(listaCompCant, listaComp, 0, listaComp.size()-1);


		if(listaComp.size() > 4)
		{
			for(int ind = 0; ind < listaComp.size()-5; ind++)
			{
				restoVentas += listaCompCant.get(ind);
			}
		}

		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue(listaComp.get(listaComp.size()-1).getModelo(), (listaCompCant.get(0)/listaComp.size())*100);
		data.setValue(listaComp.get(listaComp.size()-2).getModelo(), (listaCompCant.get(1)/listaComp.size())*100);
		data.setValue(listaComp.get(listaComp.size()-3).getModelo(), (listaCompCant.get(2)/listaComp.size())*100);
		data.setValue(listaComp.get(listaComp.size()-4).getModelo(), (listaCompCant.get(3)/listaComp.size())*100);
		data.setValue("Otros componentes", (restoVentas/listaComp.size())*100);

		JFreeChart chart = ChartFactory.createPieChart("Componentes mas vendidos"
				, data, true, true, false);

		ChartPanel panelGrafica = new ChartPanel(chart);
		panelGrafica.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panelGrafica.setPreferredSize(new Dimension(panelGrafica1.getWidth()-30, panelGrafica1.getHeight()-20));
		panelGrafica.setBackground(getBackground());
		panelGrafica.setForeground(getForeground());
		//panelGrafica.setFont(titleLabel.getFont());
		panelGrafica2.add(panelGrafica);

	}

	public void quickSort(ArrayList<Double> arr, ArrayList<Componente>lista, int begin, int end) {
		if (begin < end) {
			int partitionIndex = partition(arr, lista, begin, end);

			quickSort(arr, lista, begin, partitionIndex-1);
			quickSort(arr, lista, partitionIndex+1, end);
		}
	}

	private int partition(ArrayList<Double> arr, ArrayList<Componente>lista, int begin, int end) {
		double pivot = arr.get(end);
		int i = (begin-1);

		for (int j = begin; j < end; j++) {
			if (arr.get(j) <= pivot) {
				i++;

				Componente swapComp = lista.get(i);
				lista.set(i, lista.get(j));
				lista.set(j, swapComp);

				double swapTemp = arr.get(i);
				arr.set(i, arr.get(j));
				arr.set(j, swapTemp);
			}
		}

		Componente swapComp = lista.get(i+1);
		lista.set(i+1, lista.get(end));
		lista.set(end, swapComp);

		double swapTemp = arr.get(i+1);
		arr.set(i+1, arr.get(end));
		arr.set(end, swapTemp);

		return i+1;
	}

	//    private void guardarClientesEnBD() {
	//        String user = "sa";
	//        String password = "sa123";
	//        String connectionUrl = "jdbc:sqlserver://localhost\\MSSQLSERVER01;" +
	//                "databaseName=TiendaComponentes;" +
	//                "user=" + user + ";" +
	//                "password=" + password + ";" +
	//                "encrypt=false;" +
	//                "trustServerCertificate=true;";
	//        
	//        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
	//            ConexionBD conexionBD = new ConexionBD();
	//            conexionBD.guardarClientesEnBD(connection);
	//            
	//            System.out.println("Clientes del archivo guardados en BD exitosamente.");
	//            
	//        } catch (SQLException e) {
	//            System.out.println("Error al guardar clientes en BD: " + e.getMessage());
	//            e.printStackTrace();
	//        }
	//    }

	//    private void guardarComponentesEnBD() {
	//        String user = "sa";
	//        String password = "sa123";
	//        String connectionUrl = "jdbc:sqlserver://localhost\\MSSQLSERVER01;" +
	//                "databaseName=TiendaComponentes;" +
	//                "user=" + user + ";" +
	//                "password=" + password + ";" +
	//                "encrypt=false;" +
	//                "trustServerCertificate=true;";
	//        
	//        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
	//            ConexionBD conexionBD = new ConexionBD();
	//            conexionBD.guardarComponentesEnBD(connection);
	//            
	//            System.out.println("Componentes del archivo guardados en BD exitosamente.");
	//            
	//        } catch (SQLException e) {
	//            System.out.println("Error al guardar componentes en BD: " + e.getMessage());
	//            e.printStackTrace();
	//        }
	//    }

	private void guardarFacturasEnBD() {
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
	        conexionBD.guardarFacturasEnBD(connection);
	        
	        System.out.println("Facturas del archivo guardadas en BD exitosamente.");
	        
	    } catch (SQLException e) {
	        System.out.println("Error al guardar facturas en BD: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

	private void cargarEmpresaDesdeBD() {
	    String connectionUrl = "jdbc:sqlserver://localhost\\MSSQLSERVER01;" +
	            "databaseName=TiendaComponentes;" +
	            "user=sa;password=sa123;" +
	            "encrypt=false;trustServerCertificate=true;";
	    
	    try (Connection connection = DriverManager.getConnection(connectionUrl)) {
	        ConexionBD conexionBD = new ConexionBD();
	        
	        System.out.println("=== Iniciando carga desde BD ===");
	        
	        // IMPORTANTE: Limpiar datos existentes antes de cargar
	        Empresa.getInstance().getLosClientes().clear();
	        Empresa.getInstance().getLosComponentes().clear();
	        Empresa.getInstance().getLasFacturas().clear();
	        
	        // Cargar datos desde BD
	        ArrayList<Cliente> clientes = conexionBD.cargarClientesDesdeBD(connection);
	        ArrayList<Componente> componentes = conexionBD.cargarComponentesDesdeBD(connection);
	        
	        // Agregar clientes y componentes a la empresa
	        System.out.println("Agregando " + clientes.size() + " clientes...");
	        for(Cliente c : clientes) {
	            Empresa.getInstance().insertarCliente(c);
	        }
	        
	        System.out.println("Agregando " + componentes.size() + " componentes...");
	        for(Componente comp : componentes) {
	            Empresa.getInstance().insertarComponente(comp);
	        }
	        
	        // Cargar facturas (necesitan clientes y componentes ya cargados)
	        System.out.println("Cargando facturas...");
	        ArrayList<Factura> facturas = conexionBD.cargarFacturasDesdeBD(connection);
	        
	        System.out.println("Agregando " + facturas.size() + " facturas...");
	        for(Factura f : facturas) {
	            Empresa.getInstance().insertarFactura(f);
	        }
	        
	        System.out.println("=== Carga completada desde BD ===");
	        System.out.println("Datos finales en Empresa:");
	        System.out.println("  - Clientes: " + Empresa.getInstance().getLosClientes().size());
	        System.out.println("  - Componentes: " + Empresa.getInstance().getLosComponentes().size());
	        System.out.println("  - Facturas: " + Empresa.getInstance().getLasFacturas().size());
	        
	        // Verificar integridad de los datos
	        verificarIntegridadDatos();
	                          
	    } catch (SQLException ex) {
	        System.out.println("Error al cargar desde BD: " + ex.getMessage());
	        ex.printStackTrace();
	        
	        // En caso de error, intentar cargar desde archivo como respaldo
	        System.out.println("Intentando cargar desde archivo como respaldo...");
	        try {
	            ArchivoEmpresa.getInstance().cargarEmpresa("empresa.txt");
	            System.out.println("Datos cargados desde archivo de respaldo exitosamente.");
	        } catch (IOException | ClassNotFoundException ex2) {
	            JOptionPane.showMessageDialog(null, 
	                "Error crítico: No se pudieron cargar datos ni desde BD ni desde archivo.", 
	                "Error Fatal", 
	                JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}
	
	private void verificarIntegridadDatos() {
	    System.out.println("=== Verificando integridad de datos ===");
	    
	    // Verificar facturas
	    for (Factura factura : Empresa.getInstance().getLasFacturas()) {
	        // Verificar que el cliente existe
	        boolean clienteExiste = false;
	        for (Cliente cliente : Empresa.getInstance().getLosClientes()) {
	            if (cliente.getIdCliente().equals(factura.getCliente().getIdCliente())) {
	                clienteExiste = true;
	                break;
	            }
	        }
	        
	        if (!clienteExiste) {
	            System.out.println("ERROR: Factura " + factura.getIdFactura() + 
	                             " referencia cliente inexistente: " + factura.getCliente().getIdCliente());
	        }
	        
	        // Verificar componentes de la factura
	        for (Componente comp : factura.getLosComponentes()) {
	            boolean componenteExiste = false;
	            for (Componente compEmpresa : Empresa.getInstance().getLosComponentes()) {
	                if (compEmpresa.getIdComponente().equals(comp.getIdComponente())) {
	                    componenteExiste = true;
	                    break;
	                }
	            }
	            
	            if (!componenteExiste) {
	                System.out.println("ERROR: Factura " + factura.getIdFactura() + 
	                                 " referencia componente inexistente: " + comp.getIdComponente());
	            }
	        }
	    }
	    
	    System.out.println("=== Verificación completada ===");
	}
}
