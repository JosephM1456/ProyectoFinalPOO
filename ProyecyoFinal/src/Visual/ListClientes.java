package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import Logico.Cliente;
import Logico.Componente;
import Logico.Empleado;
import Logico.Empresa;
import Logico.Visitante;

import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ListClientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtBuscar;
	private JPanel panelLista;
	private JScrollPane scrollPane;
	private JMenuBar menuBar;
	private JButton btnBuscar;
	private JMenu menuBuscar;
	private JRadioButtonMenuItem rdBtnNombre;
	private JRadioButtonMenuItem rdBtnId;
	
	private ArrayList<Cliente> listaCliente = Empresa.getInstance().getLosClientes();
	private Integer cantCliente = new Integer(0);
	private int defaultWidth = 760;
	private int defaultHeight = 510;
	private Component clienteSelect = null;
	private String idSelect = null;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JPanel panelNoEncontrado;
	private JLabel lblClienteNoEncontrado;
	private JButton btnRetroceder;
	
	private ButtonGroup grupoBtn;
	private ButtonGroup grupoBtn2;
	private JRadioButtonMenuItem rdBtnNinguno;
	private JRadioButtonMenuItem rdBtnVisitante;
	private JRadioButtonMenuItem rdBtnEmpleado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListClientes dialog = new ListClientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListClientes() {
		setTitle("Listado de Clientes");
		setBounds(100, 100, 869, 697);
		getContentPane().setLayout(new BorderLayout(0, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		panelNoEncontrado = new JPanel();
		panelNoEncontrado.setVisible(false);
		panelNoEncontrado.setBounds(57, 56, 732, 229);
		contentPanel.add(panelNoEncontrado);
		panelNoEncontrado.setLayout(null);
		
		lblClienteNoEncontrado = new JLabel("Cliente no encontrado");
		lblClienteNoEncontrado.setHorizontalAlignment(SwingConstants.CENTER);
		lblClienteNoEncontrado.setBounds(224, 100, 284, 28);
		lblClienteNoEncontrado.setFont(new Font("SansSerif", Font.BOLD, 21));
		panelNoEncontrado.add(lblClienteNoEncontrado);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Listado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(26, 16, 806, 539);
		contentPanel.add(scrollPane);
		
		panelLista = new JPanel();
		panelLista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(clienteSelect != null)
				{
					((JComponent)clienteSelect).setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				}
				
				clienteSelect = panelLista.getComponentAt(panelLista.getMousePosition());
				if(clienteSelect instanceof PanelCliente)
				{
					idSelect = ((PanelCliente)clienteSelect).getCliente().getIdCliente();
					btnActualizar.setEnabled(true);
					btnEliminar.setEnabled(true);
					((JComponent) clienteSelect).setBorder(new LineBorder(new Color(0, 255, 0), 2));
				}
				
			}
		});
		FlowLayout flowLayout = (FlowLayout) panelLista.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		scrollPane.setViewportView(panelLista);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			btnActualizar = new JButton("Actualizar");
			btnActualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Cliente cliente = Empresa.getInstance().buscarClienteById(idSelect);
					if(cliente != null)
					{
						RegCliente ventana = new RegCliente(cliente);
						ventana.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
						ventana.setModal(true);
						ventana.setVisible(true);
						ventana.addWindowListener(new WindowAdapter() {
							
							public void windowClosed(WindowEvent arg0)
							{
								if(clienteSelect instanceof PanelCliente)
								{
									((PanelCliente)clienteSelect).getTxt().setText("Nombre: "+cliente.getNombre()+"\n"+"Teléfono: "+cliente.getTelefono()+"\n"+"Cédula: "+cliente.getCedula());
									((PanelCliente)clienteSelect).getTxt().revalidate();
									((PanelCliente)clienteSelect).getTxt().repaint();
								}
								panelLista.revalidate();
								panelLista.repaint();
								ventana.dispose();
							}
							
						});
					}
					
				}
			});
			btnActualizar.setEnabled(false);
			buttonPane.add(btnActualizar);
			
			btnEliminar = new JButton("Eliminar");
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Cliente cliente = Empresa.getInstance().buscarClienteById(idSelect);
					boolean hacer = true;
					int ind = 0;
					int result;
					while(ind < Empresa.getInstance().getLasFacturas().size())
					{
						if(Empresa.getInstance().getLasFacturas().get(ind).getCliente().getIdCliente().equals(cliente.getIdCliente()))
						{
							hacer = false;
							break;
						}
						ind++;
					}
					
					if(hacer)
					{
						result = JOptionPane.showConfirmDialog(null, "Está seguro que desea eliminar este cliente?", "Confimación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if(result == 0)
						{
							Empresa.getInstance().getLosClientes().remove(cliente);
							panelLista.remove(clienteSelect);
							panelLista.revalidate();
							panelLista.repaint();
							clienteSelect = null;
							btnActualizar.setEnabled(false);
							btnEliminar.setEnabled(false);
						}
					}
					else JOptionPane.showMessageDialog(null, "Este cliente ya no puede ser eliminado", null, JOptionPane.ERROR_MESSAGE);
					
				}
			});
			btnEliminar.setEnabled(false);
			buttonPane.add(btnEliminar);
			
			btnRetroceder = new JButton("Atr\u00E1s");
			btnRetroceder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					panelNoEncontrado.setVisible(false);
					panelLista.setVisible(true);
					loadClientes();
				}
			});
			btnRetroceder.setEnabled(false);
			buttonPane.add(btnRetroceder);
			
			JButton btnSalir = new JButton("Salir");
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			buttonPane.add(btnSalir);
		}
		{
			menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			{
				txtBuscar = new JTextField();
				txtBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(rdBtnId.isSelected())
						{
							panelNoEncontrado.setVisible(false);
							panelLista.setVisible(true);
							Cliente cliente = Empresa.getInstance().buscarClienteById(txtBuscar.getText());
							if(cliente != null)
							{
								panelLista.removeAll();
								for(Cliente ind : listaCliente)
								{
									if(ind.getIdCliente().equals(cliente.getIdCliente()))
									{
										PanelCliente pan = new PanelCliente(cliente);
										panelLista.add(pan);
									}
								}
								panelLista.revalidate();
								panelLista.repaint();
							}
							else clienteNoEncontrado();
						}
						else if(rdBtnNombre.isSelected())
						{
							ArrayList<Cliente> lista = new ArrayList<Cliente>();
							String input = new String(txtBuscar.getText());
							if(buscarClienteByNombre(lista, input) == 1)
							{
								panelNoEncontrado.setVisible(false);
								panelLista.setVisible(true);
								scrollPane.getVerticalScrollBar().setValue(0);
								panelLista.removeAll();
								panelLista.setPreferredSize(new Dimension(defaultWidth, defaultHeight));
								
								int ind = 0;
								while(ind < 10)
								{
									if(ind == lista.size())
									{
										break;
									}
									PanelCliente pan = new PanelCliente(lista.get(ind));
									panelLista.add(pan);
									ind++;
								}
								panelLista.revalidate();
								panelLista.repaint();
							}
							else clienteNoEncontrado();
							
						}
						else clienteNoEncontrado();
					}
				});
				txtBuscar.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						
						//System.out.println(txtBuscar.getText());
						
						if(rdBtnNombre.isSelected() && ( (e.getKeyCode() <= KeyEvent.VK_Z && e.getKeyCode() >= KeyEvent.VK_0) || (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)) )
						{
							String input = null;
							if(e.getKeyCode() <= KeyEvent.VK_Z && e.getKeyCode() >= KeyEvent.VK_0)
							{
								Character ch = new Character(e.getKeyChar());
								input = new String(txtBuscar.getText().concat(ch.toString()));
							}
							else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE && txtBuscar.getText().length() > 1)
							{
								input = new String(txtBuscar.getText().substring(0, txtBuscar.getText().length()-1));
							}
							else input = new String("");
							
							System.out.println(input);

							if(rdBtnNombre.isSelected() && !input.equalsIgnoreCase(""))
							{
								ArrayList<Cliente> lista = new ArrayList<Cliente>();
								if(buscarClienteByNombre(lista, input) == 1)
								{
									panelNoEncontrado.setVisible(false);
									panelLista.setVisible(true);
									scrollPane.getVerticalScrollBar().setValue(0);
									panelLista.removeAll();
									panelLista.setPreferredSize(new Dimension(defaultWidth, defaultHeight));
									panelLista.revalidate();
									panelLista.repaint();
									int ind = 0;
									while(ind < 10)
									{
										if(ind == lista.size())
										{
											break;
										}
										PanelCliente pan = new PanelCliente(lista.get(ind));
										panelLista.add(pan);
										ind++;
									}
									panelLista.revalidate();
									panelLista.repaint();
								}
								else clienteNoEncontrado();
								
							}
							else clienteNoEncontrado();
							
						}
					}
				});
				menuBar.add(txtBuscar);
				txtBuscar.setColumns(10);
			}
			{
				btnBuscar = new JButton("Buscar");
				btnBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(rdBtnId.isSelected())
						{
							panelNoEncontrado.setVisible(false);
							panelLista.setVisible(true);
							Cliente cliente = Empresa.getInstance().buscarClienteById(txtBuscar.getText());
							if(cliente != null)
							{
								panelLista.removeAll();
								for(Cliente ind : listaCliente)
								{
									if(ind.getIdCliente().equals(cliente.getIdCliente()))
									{
										PanelCliente pan = new PanelCliente(cliente);
										panelLista.add(pan);
									}
								}
								panelLista.revalidate();
								panelLista.repaint();
							}
							else clienteNoEncontrado();
						}
						else if(rdBtnNombre.isSelected())
						{
							ArrayList<Cliente> lista = new ArrayList<Cliente>();
							String input = new String(txtBuscar.getText());
							if(buscarClienteByNombre(lista, input) == 1)
							{
								panelNoEncontrado.setVisible(false);
								panelLista.setVisible(true);
								scrollPane.getVerticalScrollBar().setValue(0);
								panelLista.removeAll();
								panelLista.setPreferredSize(new Dimension(defaultWidth, defaultHeight));
								
								int ind = 0;
								while(ind < 10)
								{
									if(ind == lista.size())
									{
										break;
									}
									PanelCliente pan = new PanelCliente(lista.get(ind));
									panelLista.add(pan);
									ind++;
								}
								panelLista.revalidate();
								panelLista.repaint();
							}
							else clienteNoEncontrado();
							
						}
						else clienteNoEncontrado();
						
					}
				});
				menuBar.add(btnBuscar);
			}
			
			JMenu menuFiltrar = new JMenu("Filtrar");
			menuFiltrar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			menuBar.add(menuFiltrar);
			
			rdBtnNinguno = new JRadioButtonMenuItem("Ninguno");
			rdBtnNinguno.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					loadClientes();
				}
			});
			menuFiltrar.add(rdBtnNinguno);
			
			rdBtnVisitante = new JRadioButtonMenuItem("Visitante");
			rdBtnVisitante.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					loadClientes();
				}
			});
			menuFiltrar.add(rdBtnVisitante);
			
			rdBtnEmpleado = new JRadioButtonMenuItem("Empleado");
			rdBtnEmpleado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					loadClientes();
				}
			});
			menuFiltrar.add(rdBtnEmpleado);
			{
				menuBuscar = new JMenu("Buscar por: Nombre");
				menuBuscar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
				menuBar.add(menuBuscar);
				{
					rdBtnNombre = new JRadioButtonMenuItem("Nombre");
					rdBtnNombre.setSelected(true);
					rdBtnNombre.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							menuBuscar.setText("Buscar por: Nombre");
						}
					});
					menuBuscar.add(rdBtnNombre);
				}
				{
					rdBtnId = new JRadioButtonMenuItem("ID");
					rdBtnId.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							menuBuscar.setText("Buscar por: ID");
						}
					});
					menuBuscar.add(rdBtnId);
				}
			}
		}
		
		grupoBtn = new ButtonGroup();
		grupoBtn.add(rdBtnId);
		grupoBtn.add(rdBtnNombre);
		
		grupoBtn2 = new ButtonGroup();
		grupoBtn2.add(rdBtnNinguno);
		grupoBtn2.add(rdBtnVisitante);
		grupoBtn2.add(rdBtnEmpleado);
		
		loadClientes();
	}
	
	
	private void loadClientes()
	{
		panelLista.removeAll();
		panelLista.setPreferredSize(new Dimension(defaultWidth, defaultHeight));
		panelLista.revalidate();
		panelLista.repaint();
		
		int aumento = 0;
		cantCliente = 0;
		for(Cliente ind : listaCliente)
		{
			if(rdBtnVisitante.isSelected())
			{
				if(ind instanceof Visitante)
				{
					PanelCliente pan = new PanelCliente(ind);
					panelLista.add(pan);
					cantCliente++;
					
					if( ((cantCliente-1) % 5 == 0) && cantCliente > 10)
					{
						aumento += 100;
					}
				}
			}
			else if(rdBtnEmpleado.isSelected())
			{
				if(ind instanceof Empleado)
				{
					PanelCliente pan = new PanelCliente(ind);
					panelLista.add(pan);
					cantCliente++;
					
					if( ((cantCliente-1) % 5 == 0) && cantCliente > 10)
					{
						aumento += 100;
					}
				}
			}
			else
			{
				PanelCliente pan = new PanelCliente(ind);
				panelLista.add(pan);
				cantCliente++;
				
				if( ((cantCliente-1) % 5 == 0) && cantCliente > 10)
				{
					aumento += 100;
				}				
			}

		}
		panelLista.setPreferredSize(new Dimension(panelLista.getWidth(), panelLista.getHeight()+aumento));
		panelLista.revalidate();
		panelLista.repaint();
		
	}
	
	private void clienteNoEncontrado()
	{
		panelLista.setVisible(false);
		panelNoEncontrado.setVisible(true);
		btnActualizar.setEnabled(false);
		btnEliminar.setEnabled(false);
		btnRetroceder.setEnabled(true);
	}
	
	public int buscarClienteByNombre(ArrayList<Cliente> listaTemp, String input)
	{
		ArrayList<Integer> listaAcierto = new ArrayList<Integer>();
		for(int i = 0; i < 10;  i++) listaAcierto.add(0);
		
		for(int ind1 = 0; ind1 < listaCliente.size(); ind1++)
		{
			int result = comparacionString(listaCliente.get(ind1).getNombre(), input);
			int ind2 = 0;
			while(ind2 < 10)
			{
				if(result > listaAcierto.get(ind2))
				{
					listaTemp.add(ind2, listaCliente.get(ind1));
					break;
				}
				else if(result == 0)
				{
					break;
				}
				ind2++;
			}
		}
		
		if(listaTemp.isEmpty())
		{
			return 0;
		}
		else return 1;
	}
	
	public int comparacionString(String str1, String str2)
	{
		
		if(str1.length() < str2.length())
		{
			return 0;
		}
		
		for(int ind = str1.length(); ind > 0; ind--)
		{
			if(str1.substring(0, ind).toLowerCase().contains(str2.toLowerCase()))
			{
				return ind;
			}
		}
		return 0;
	}
}
