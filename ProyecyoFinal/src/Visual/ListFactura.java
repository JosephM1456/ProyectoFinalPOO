package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Logico.Componente;
import Logico.DiscoDuro;
import Logico.Empresa;
import Logico.Factura;
import Logico.MicroProcesador;
import Logico.RAM;
import Logico.TarjetaMadre;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class ListFactura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel modelo;
	private static Object row[];
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListFactura dialog = new ListFactura();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListFactura() {
		setTitle("Lista de Facturas\r\n");
		setBounds(100, 100, 1164, 710);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					modelo = new DefaultTableModel();
					table = new JTable();
					String[] headers = {"Código","Nombre","Componentes","Marcas","Precio Total"};
					modelo.setColumnIdentifiers(headers);
					table.setModel(modelo);
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		loadFacturas();
	}
	private void loadFacturas() {
	    modelo.setRowCount(0);
	    row = new Object[modelo.getColumnCount()];

	    for (Factura factura : Empresa.getInstance().getLasFacturas()) {
	        row[0] = factura.getIdFactura();
	        row[1] = factura.getCliente().getNombre();
	   
	        List<String> tiposComponentes = new ArrayList<>();
	        List<String> marcasComponentes = new ArrayList<>();
	        for (Componente componente : factura.getLosComponentes()) {
	            String tipo = getTipoComponente(componente);
	            tiposComponentes.add(tipo);
	            marcasComponentes.add(componente.getMarca());
	        }
	        
	        row[2] = String.join(", ", tiposComponentes);
	        row[3] = String.join(", ", marcasComponentes);
	        row[4] = String.format("%.2f", factura.getCostoTotal());

	        modelo.addRow(row);
	    }
	}

	private String getTipoComponente(Componente componente) {
	    if (componente instanceof TarjetaMadre) {
	        return "Tarjeta Madre";
	    } else if (componente instanceof RAM) {
	        return "RAM";
	    } else if (componente instanceof DiscoDuro) {
	        return "Disco Duro";
	    } else if (componente instanceof MicroProcesador) {
	        return "Microprocesador";
	    }
	    return null;
	}

}
