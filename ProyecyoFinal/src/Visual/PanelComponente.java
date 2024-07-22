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

import Logico.Componente;
import Logico.DiscoDuro;
import Logico.MicroProcesador;
import Logico.RAM;
import Logico.TarjetaMadre;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PanelComponente extends JPanel {
	private Componente comp;
	private JLabel lbl;
	private JTextArea txt;
	private GridBagLayout lt;
	private GridBagConstraints constr, constr2;
	private Dimension size = new Dimension(30, 60);

	public PanelComponente(Componente comp) {
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
		this.comp = comp;
		
		if(this.comp instanceof DiscoDuro)
		{
			this.lbl = new JLabel( new ImageIcon(new ImageIcon(PanelComponente.class.getResource("/img/discoduro.png")).getImage().getScaledInstance(110, 100, 0) ));
		}
		else if(this.comp instanceof MicroProcesador)
		{
			this.lbl = new JLabel( new ImageIcon(new ImageIcon(PanelComponente.class.getResource("/img/microprocesador.png")).getImage().getScaledInstance(110, 100, 0) ));
		}
		else if(this.comp instanceof RAM)
		{
			this.lbl = new JLabel( new ImageIcon(new ImageIcon(PanelComponente.class.getResource("/img/ram.jpg")).getImage().getScaledInstance(110, 100, 0)));
		}
		else if(this.comp instanceof TarjetaMadre)
		{
			this.lbl = new JLabel(new ImageIcon(new ImageIcon(PanelComponente.class.getResource("/img/tarjetamadre.png")).getImage().getScaledInstance(110, 100, 0)));
		}
		this.txt = new JTextArea();
		txt.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.txt.setEditable(false);
		this.txt.setText("Marca: "+comp.getMarca()+"\nModelo: "+comp.getModelo()+"\nPrecio: "+comp.getPrecio() );
		
		this.add(lbl, constr);
		this.constr2 = (GridBagConstraints) constr.clone();
		this.constr2.gridy = 1;
		this.constr2.fill = GridBagConstraints.BOTH;
		this.constr.insets = new Insets(0, 10, 10, 10);
		this.add(txt, constr2);
		
		
		
	}

    public static void main(String[] args) {
    	DiscoDuro d1 = new DiscoDuro("C-0", "D10", (float)150.10 , 3, "Nvidia", "Rawr", "SATA-1", (float)1024.0);
    	PanelComponente example = new PanelComponente(d1);
        JFrame ventana = new JFrame();
        // Adding components dynamically
    	
        ventana.getContentPane().add(example);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(400, 400);
        ventana.setVisible(true);
        
    }

	public Componente getComp() {
		return comp;
	}

	public void setComp(Componente comp) {
		this.comp = comp;
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
