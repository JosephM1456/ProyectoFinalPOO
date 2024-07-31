package Visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;

import java.awt.GridLayout;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;

import Logico.Componente;
import Logico.DiscoDuro;
import Logico.Empresa;
import Logico.Factura;
import Logico.MicroProcesador;
import Logico.RAM;
import Logico.TarjetaMadre;

import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ScrollPaneConstants;

public class ListComponentes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtBuscar;
	private JPanel panelSeleccion;
	private JPanel panelTodos;
	private JButton btnTodos;
	private JPanel panelBotones;
	private JButton btnTarjetaMadre;
	private JButton btnMicroChip;
	private JButton btnRAM;
	private JButton btnDiscoDuro;
	private JMenuBar menuBar;
	private JButton btnBuscar;
	private JMenu menuFiltro;
	private JRadioButtonMenuItem rdBtnID;
	private JRadioButtonMenuItem rdBtnNombre;
	
	private ButtonGroup btnGrupo;
	private JButton btnRetroceder;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JPanel panelLista;
	private JScrollPane scrollPane;
	
	private ArrayList<Componente> listaComp = Empresa.getInstance().getLosComponentes();
	private String idSelect;
	private String tipoSelect;
	private Component componentSelect = null;
	private JPanel panelNoEncontrado;
	private int defaultWidth = 760;
	private int defaultHeight = 510;
	private int cantComponentes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		try {
			ListComponentes dialog = new ListComponentes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListComponentes() {
		setTitle("Listado de Componentes");
		setBounds(100, 100, 869, 697);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			panelNoEncontrado = new JPanel();
			panelNoEncontrado.setVisible(false);
			panelNoEncontrado.setBounds(37, 56, 732, 229);
			contentPanel.add(panelNoEncontrado);
			panelNoEncontrado.setLayout(null);
			
			JLabel lblNoEncontrado = new JLabel("Componente no encontrado");
			lblNoEncontrado.setBounds(224, 100, 284, 28);
			lblNoEncontrado.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 21));
			panelNoEncontrado.add(lblNoEncontrado);
		}
		{
			panelSeleccion = new JPanel();
			panelSeleccion.setBounds(15, 16, 806, 539);
			contentPanel.add(panelSeleccion);
			panelSeleccion.setLayout(null);
			{
				panelTodos = new JPanel();
				panelTodos.setBounds(0, 0, 806, 90);
				panelSeleccion.add(panelTodos);
				panelTodos.setLayout(new BorderLayout(0, 0));
				{
					btnTodos = new JButton("Todos los componentes");
					btnTodos.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							mostrarLista();
							tipoSelect = new String("");
							loadComp(tipoSelect);
						}
					});
					panelTodos.add(btnTodos, BorderLayout.CENTER);
				}
			}
			{
				panelBotones = new JPanel();
				panelBotones.setBounds(0, 90, 806, 449);
				panelSeleccion.add(panelBotones);
				panelBotones.setLayout(new GridLayout(2, 0, 0, 0));
				{
					btnTarjetaMadre = new JButton("Tarjeta Madre");
					btnTarjetaMadre.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							mostrarLista();
							tipoSelect = new String(btnTarjetaMadre.getText());
							loadComp(tipoSelect);
						}
					});
					panelBotones.add(btnTarjetaMadre);
				}
				{
					btnMicroChip = new JButton("Micro Procesadores");
					btnMicroChip.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							mostrarLista();
							tipoSelect = new String(btnMicroChip.getText());
							loadComp(tipoSelect);
						}
					});
					panelBotones.add(btnMicroChip);
				}
				{
					btnRAM = new JButton("RAM");
					btnRAM.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							mostrarLista();
							tipoSelect = new String(btnRAM.getText());
							loadComp(tipoSelect);
						}
					});
					panelBotones.add(btnRAM);
				}
				{
					btnDiscoDuro = new JButton("Discos Duros");
					btnDiscoDuro.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							mostrarLista();
							loadComp("Discos Duros");
						}
					});
					panelBotones.add(btnDiscoDuro);
				}
			}
		}
		{
			
			scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(15, 16, 806, 539);
			scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Listado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(scrollPane);
			panelLista = new JPanel();
			panelLista.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(componentSelect != null)
					{
						((JComponent) componentSelect).setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));;
					}
					
					componentSelect = panelLista.getComponentAt(panelLista.getMousePosition()); 
					if(componentSelect != null)
					{
						if(componentSelect instanceof PanelComponente)
						{
							idSelect = new String(((PanelComponente)componentSelect).getComp().getIdComponente());
							//System.out.println(((PanelComponente)componentSelect).getComp().getIdComponente()+"\t"+((PanelComponente)componentSelect).getComp().getModelo()+"\t"+((PanelComponente)componentSelect).getComp().getPrecio());
							btnEliminar.setEnabled(true);
							btnActualizar.setEnabled(true);
							((JComponent) componentSelect).setBorder(new LineBorder(new Color(155, 155, 200), 2));
						}
					}
				}
			});
			scrollPane.setViewportView(panelLista);
			FlowLayout flowLayout = (FlowLayout) panelLista.getLayout();
			flowLayout.setAlignment(FlowLayout.LEADING);
			panelLista.setPreferredSize(new Dimension(defaultWidth, defaultHeight));
			panelLista.setVisible(false);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRetroceder = new JButton("Atr\u00E1s");
				btnRetroceder.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mostrarSeleccion();
					}
				});
				btnRetroceder.setVisible(false);
				btnRetroceder.setActionCommand("OK");
				{
					btnEliminar = new JButton("Eliminar");
					btnEliminar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Componente compSelect = Empresa.getInstance().buscarCompoById(idSelect);
							boolean valido = true;
							for(Factura ind : Empresa.getInstance().getLasFacturas())
							{
								if(ind.getLosComponentes().contains(compSelect))
								{
									valido = false;
								}
							}
							if(valido)
							{
								int result;
								result = JOptionPane.showConfirmDialog(scrollPane, "Está seguro que desea eliminar este componente?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
								if(result == 0)
								{
									cantComponentes -= 1;
									Empresa.getInstance().deleteComponente(idSelect);
									listaComp.remove(compSelect);
									panelLista.remove(componentSelect);
									panelLista.revalidate();
									panelLista.repaint();
									JOptionPane.showMessageDialog(scrollPane, "Operación exitosa", "Componente Eliminado", JOptionPane.INFORMATION_MESSAGE);
								}	
							} else JOptionPane.showMessageDialog(scrollPane, "Este componente ya no puede ser eliminado", "Error", JOptionPane.INFORMATION_MESSAGE);
						
							if(cantComponentes % 5 == 0)
							{
								loadComp(tipoSelect);
							}
						}
					});
					btnEliminar.setEnabled(false);
					btnEliminar.setVisible(false);
					{
						btnActualizar = new JButton("Actualizar");
						btnActualizar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								Componente comp = Empresa.getInstance().buscarCompoById(idSelect);
								RegComponente ventana = new RegComponente(comp);
								ventana.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
								ventana.setModal(true);
								ventana.setVisible(true);
								ventana.addWindowListener(new WindowAdapter() {
									
									public void windowClosed(WindowEvent arg0)
									{
										//System.out.println("windowClosed event triggered");
										//panelLista.removeAll();
										//loadComp(tipoSelect);
										if(componentSelect instanceof PanelComponente)
										{
											((PanelComponente)componentSelect).getTxt().setText("Marca: "+comp.getMarca()+"\nModelo: "+comp.getModelo()+"\nPrecio: "+comp.getPrecio() );
											((PanelComponente)componentSelect).getTxt().revalidate();
											((PanelComponente)componentSelect).getTxt().repaint();
										}
										panelLista.revalidate();
										panelLista.repaint();
										ventana.dispose();
									}
								});								
								

							}
						});
						btnActualizar.setEnabled(false);
						btnActualizar.setVisible(false);
						buttonPane.add(btnActualizar);
					}
					buttonPane.add(btnEliminar);
				}
				buttonPane.add(btnRetroceder);
				getRootPane().setDefaultButton(btnRetroceder);
			}
			{
				JButton btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(rdBtnNombre.isSelected() && ( (arg0.getKeyCode() <= KeyEvent.VK_Z && arg0.getKeyCode() >= KeyEvent.VK_0) || (arg0.getKeyCode() == KeyEvent.VK_BACK_SPACE)) )
				{
					String input = null;
					if(arg0.getKeyCode() <= KeyEvent.VK_Z && arg0.getKeyCode() >= KeyEvent.VK_0)
					{
						Character ch = new Character(arg0.getKeyChar());
						input = new String(txtBuscar.getText().concat(ch.toString()));
					}
					else if(arg0.getKeyCode() == KeyEvent.VK_BACK_SPACE && txtBuscar.getText().length() > 1)
					{
						input = new String(txtBuscar.getText().substring(0, txtBuscar.getText().length()-1));
					}
					else input = new String("");
					
					//System.out.println(input);

					if(rdBtnNombre.isSelected() && !input.equalsIgnoreCase(""))
					{
						ArrayList<Componente> lista = new ArrayList<Componente>();
						if(buscarCompByNombre(lista, input) == 1)
						{
							scrollPane.getVerticalScrollBar().setValue(0);
							panelLista.removeAll();
							panelLista.setPreferredSize(new Dimension(defaultWidth, defaultHeight));
							mostrarLista();
							int ind = 0;
							while(ind < 10)
							{
								if(ind == lista.size())
								{
									break;
								}
								PanelComponente pan = new PanelComponente(lista.get(ind));
								panelLista.add(pan);
								ind++;
							}
							panelLista.revalidate();
							panelLista.repaint();
						}
						else ComponenteNoEncontrado();
						
					}
					else ComponenteNoEncontrado();
					
				}

			}
		});
		txtBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Componente componente = null;
				if(rdBtnID.isSelected())
				{
					componente = Empresa.getInstance().buscarCompoById(txtBuscar.getText());
					if(componente != null)
					{
						panelLista.removeAll();
						panelLista.revalidate();
						panelLista.repaint();
						PanelComponente pan = new PanelComponente(componente);
						panelLista.add(pan);
					}
					else
					{
						JOptionPane.showMessageDialog(panelLista, "Componente no encontrado", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
					}
				}
				else if(rdBtnNombre.isSelected() && !txtBuscar.getText().equalsIgnoreCase(""))
				{
					ArrayList<Componente> lista = new ArrayList<Componente>();
					String input = new String(txtBuscar.getText());
					if(buscarCompByNombre(lista, input) == 1)
					{
						scrollPane.getVerticalScrollBar().setValue(0);
						panelLista.removeAll();
						panelLista.setPreferredSize(new Dimension(defaultWidth, defaultHeight));
						mostrarLista();
						int ind = 0;
						while(ind < 10)
						{
							if(ind == lista.size())
							{
								break;
							}
							PanelComponente pan = new PanelComponente(lista.get(ind));
							panelLista.add(pan);								
							ind++;
						}
						panelLista.revalidate();
						panelLista.repaint();
					}
					else ComponenteNoEncontrado();
					
				}
			}
		});
		txtBuscar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtBuscar.setText("");
				txtBuscar.setForeground(getForeground());
			}
		});


		menuBar.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Componente componente = null;
				if(rdBtnID.isSelected())
				{
					componente = Empresa.getInstance().buscarCompoById(txtBuscar.getText());
					if(componente != null)
					{
						panelLista.removeAll();
						panelLista.revalidate();
						panelLista.repaint();
						PanelComponente pan = new PanelComponente(componente);
						panelLista.add(pan);
					}
					else
					{
						JOptionPane.showMessageDialog(panelLista, "Componente no encontrado", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
					}
				}
				else if(rdBtnNombre.isSelected() && !txtBuscar.getText().equalsIgnoreCase(""))
				{
					ArrayList<Componente> lista = new ArrayList<Componente>();
					String input = new String(txtBuscar.getText());
					if(buscarCompByNombre(lista, input) == 1)
					{
						scrollPane.getVerticalScrollBar().setValue(0);
						panelLista.removeAll();
						panelLista.setPreferredSize(new Dimension(defaultWidth, defaultHeight));
						mostrarLista();
						int ind = 0;
						while(ind < 10)
						{
							if(ind == lista.size())
							{
								break;
							}
							PanelComponente pan = new PanelComponente(lista.get(ind));
							panelLista.add(pan);								
							ind++;
						}
						panelLista.revalidate();
						panelLista.repaint();
					}
					else ComponenteNoEncontrado();
					
				} else ComponenteNoEncontrado();
			}
		});
		menuBar.add(btnBuscar);
		{
			menuFiltro = new JMenu("Buscar por: ID");
			menuBar.add(menuFiltro);
			{
				rdBtnID = new JRadioButtonMenuItem("ID");
				rdBtnID.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						menuFiltro.setText("Buscar por: ID");
					}
				});
				rdBtnID.setSelected(true);
				menuFiltro.add(rdBtnID);
			}
			{
				rdBtnNombre = new JRadioButtonMenuItem("Nombre");
				rdBtnNombre.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						menuFiltro.setText("Buscar por: Nombre");
					}
				});
				menuFiltro.add(rdBtnNombre);
			}
		}
		
		btnGrupo = new ButtonGroup();
		btnGrupo.add(rdBtnID);
		btnGrupo.add(rdBtnNombre);
		
	}
	
	public void loadComp(String tipo)
	{
		scrollPane.getVerticalScrollBar().setValue(0);
		panelLista.removeAll();
		panelLista.setPreferredSize(new Dimension(defaultWidth, defaultHeight));
		panelLista.revalidate();
		panelLista.repaint();
		cantComponentes = 0;
		int aumento = 0;
		for(Componente ind : listaComp)
		{

			
			switch(tipo)
			{
				case "Tarjeta Madre":
					if(ind instanceof TarjetaMadre)
					{
						PanelComponente pan = new PanelComponente(ind);
						panelLista.add(pan);
						cantComponentes++;
					}
					break;
				
				case "Micro Procesadores":
					if(ind instanceof MicroProcesador)
					{
						PanelComponente pan = new PanelComponente(ind);
						panelLista.add(pan);
						cantComponentes++;
					}
					break;
				case "RAM":
					if(ind instanceof RAM)
					{
						PanelComponente pan = new PanelComponente(ind);
						panelLista.add(pan);
						cantComponentes++;
					}
					break;
				case "Discos Duros":
					if(ind instanceof DiscoDuro)
					{
						PanelComponente pan = new PanelComponente(ind);
						panelLista.add(pan);
						cantComponentes++;
					}
					break;
				default:
					PanelComponente pan = new PanelComponente(ind);
					panelLista.add(pan);
					cantComponentes++;
					break;
			}
			if((cantComponentes-1) % 5 == 0 && cantComponentes > 10)
			{
				aumento += 100;
			}

		}
		panelLista.setPreferredSize(new Dimension(defaultWidth, defaultHeight+aumento));
		panelLista.revalidate();
		panelLista.repaint();
		
		if(cantComponentes == 0)
			ComponenteNoEncontrado();
	}
	
	public int buscarCompByNombre(ArrayList<Componente> listaTemp, String input)
	{
		ArrayList<Integer> listaAcierto = new ArrayList<Integer>();
		for(int i = 0; i < 10;  i++) listaAcierto.add(0);
		
		for(int ind1 = 0; ind1 < listaComp.size(); ind1++)
		{
			int result = comparacionString(listaComp.get(ind1).getModelo(), input);
			int ind2 = 0;
			while(ind2 < 10)
			{
				if(result > listaAcierto.get(ind2))
				{
					listaTemp.add(ind2, listaComp.get(ind1));
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
	
	public void mostrarLista()
	{
		panelSeleccion.setVisible(false);
		panelLista.setVisible(true);
		scrollPane.setVisible(true);
		btnRetroceder.setVisible(true);
		btnEliminar.setVisible(true);
		btnActualizar.setVisible(true);
		panelNoEncontrado.setVisible(false);
	}
	
	public void mostrarSeleccion()
	{
		panelLista.removeAll();
		panelLista.setVisible(false);
		panelSeleccion.setVisible(true);
		btnEliminar.setVisible(false);
		btnEliminar.setEnabled(false);
		btnActualizar.setVisible(false);
		btnActualizar.setEnabled(false);
		btnRetroceder.setVisible(false);
		panelNoEncontrado.setVisible(false);
	}
	
	public void ComponenteNoEncontrado()
	{
		panelSeleccion.setVisible(false);
		scrollPane.setVisible(true);
		panelLista.setVisible(false);
//		panelLista.removeAll();
//		
//		panelLista.revalidate();
//		panelLista.repaint();
		panelNoEncontrado.setVisible(true);
		panelNoEncontrado.revalidate();
		panelNoEncontrado.repaint();
		btnRetroceder.setVisible(true);

		
	}
}
