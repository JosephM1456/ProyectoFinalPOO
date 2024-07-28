package Visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Logico.Cliente;
import Logico.Empleado;
import Logico.Visitante;

import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PanelCliente extends JPanel {
	private Cliente cliente;
	private JLabel lbl;
	private JTextArea txt;
	private GridBagLayout lt;
	private GridBagConstraints constr, constr2;
	private Dimension size = new Dimension(30, 60);

	public PanelCliente(Cliente cliente) {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		// TODO Auto-generated constructor stub
		this.lt = new GridBagLayout();
		this.constr = new GridBagConstraints();
		this.constr2 = new GridBagConstraints();
		this.lt.columnWidths = new int[] {121, 0};
		this.lt.rowHeights = new int[] {110, 60, 0};
		this.lt.columnWeights = new double[] {0.0};
		this.lt.rowWeights = new double[] {0.0, 0.0, Double.MIN_VALUE};
		this.constr.gridheight = 1;
		this.constr.gridwidth = 1;
		this.constr.gridx = 0;
		this.constr.gridy = 0;
		this.constr.insets = new Insets(1, 10, 10, 10);
		this.constr.anchor = GridBagConstraints.NORTH;
		this.setPreferredSize(new Dimension(150, 210));
		//this.setMinimumSize(new Dimension(150, 274));
		//this.setMaximumSize(new Dimension(150, 280));
		this.setLayout(lt);
		this.cliente = cliente;
		

		if(cliente instanceof Empleado)
		{
			this.lbl = new JLabel(new ImageIcon(new ImageIcon(PanelCliente.class.getResource("/img/empleado.png")).getImage().getScaledInstance(110, 100, 0)));
		}
		else if(cliente instanceof Visitante)
		{
			this.lbl = new JLabel(new ImageIcon(new ImageIcon(PanelCliente.class.getResource("/img/user.png")).getImage().getScaledInstance(110, 100, 0)));
		}
		
		
		this.txt = new JTextArea();
		txt.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.txt.setEditable(false);
		this.txt.setText("Nombre: "+cliente.getNombre()+"\n"+"Teléfono: "+cliente.getTelefono()+"\n"+"Cédula: "+cliente.getCedula());
		
		
		this.add(lbl, constr);
		this.constr2 = (GridBagConstraints) constr.clone();
		this.constr2.gridy = 1;
		this.constr2.fill = GridBagConstraints.BOTH;
		this.constr.insets = new Insets(0, 10, 10, 10);
		this.add(txt, constr2);
		
		
		
	}

    public static void main(String[] args) {
    	Visitante v1 = new Visitante("C-1", "aa", "809-948-4848", "1234567", "Armando Guerra", 3);
    	Empleado v2 = new Empleado("1", "2", "809-242-9889", "1234567", "Pedrito Mendoza", (float)123.4, 2, 3);
    	PanelCliente example = new PanelCliente(v2);
        JFrame ventana = new JFrame();
        // Adding components dynamically
    	
        ventana.getContentPane().add(example);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(400, 400);
        ventana.setVisible(true);
        
    }

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public JLabel getLbl() {
		return lbl;
	}

	public void setLbl(JLabel lbl) {
		this.lbl = lbl;
	}

	public JTextArea getTxt() {
		return txt;
	}

	public void setTxt(JTextArea txt) {
		this.txt = txt;
	}
	
}
