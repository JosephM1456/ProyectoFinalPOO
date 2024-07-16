package Visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Logico.Componente;
import Logico.DiscoDuro;

public class PanelComponente extends JPanel {
	private Componente comp;
	private JButton btn;
	private JLabel lbl;
	private JFormattedTextField txt;
	private GridBagLayout lt;
	private GridBagConstraints constr;
	private Dimension size = new Dimension(30, 60);

	public PanelComponente(Componente comp) {
		// TODO Auto-generated constructor stub
		this.lt = new GridBagLayout();
		this.constr = new GridBagConstraints();
		this.lt.columnWidths = new int[] {121, 0};
		this.lt.rowHeights = new int[] {125, 108, 29, 0};
		this.lt.columnWeights = new double[] {0.0};
		this.lt.rowWeights = new double[] {0.0, 0.0, 0.0, Double.MIN_VALUE};
		this.constr.gridheight = 1;
		this.constr.gridwidth = 1;
		this.constr.gridx = 0;
		this.constr.gridy = 0;
		this.constr.insets = new Insets(1, 10, 0, 10);
		this.constr.anchor = GridBagConstraints.CENTER;
		this.setPreferredSize(new Dimension(150, 275));
		this.setMinimumSize(new Dimension(150, 275));
		this.setMaximumSize(new Dimension(150, 275));
		this.setLayout(lt);
		this.comp = comp;
		
		this.btn = new JButton("Comprar");
		Dimension btnSize = new Dimension(112, 29);
		this.btn.setPreferredSize(btnSize);
		this.lbl = new JLabel( new ImageIcon(new ImageIcon(PanelComponente.class.getResource("/img/discoduro.png")).getImage().getScaledInstance(110, 100, 0) ));
		this.txt = new JFormattedTextField();
		//this.txt.setEditable(false);
		
		
		this.txt = new JFormattedTextField();
		this.txt.setPreferredSize(size);
		this.txt.setColumns(10);
		this.txt.setText("Marca: "+comp.getMarca()+"\nModelo: "+comp.getModelo()+"\nPrecio: "+comp.getPrecio() );
		
		this.add(lbl, constr);
		this.constr.gridy = 1;
		//constr.weighty = 1.0;
		//this.constr.anchor = GridBagConstraints.CENTER;
		this.constr.insets = new Insets(40, 10, 10, 10);
		this.add(txt, constr);
		constr.weighty = 0.0;
		this.constr.gridy = 2;
		constr.ipady = 0;
		this.constr.anchor = GridBagConstraints.NORTH;
		this.constr.insets = new Insets(1, 10, 1, 10);
		this.add(btn, constr);
		
		
		
	}

    public static void main(String[] args) {
    	DiscoDuro d1 = new DiscoDuro("C-0", "D10", (float)150.10 , 3, "Nvidia", "Rawr", "SATA-1", (float)1024.0);
    	PanelComponente example = new PanelComponente(d1);
        JFrame ventana = new JFrame();
        // Adding components dynamically
    	
        ventana.add(example);
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

	public JButton getBtn() {
		return btn;
	}

	public void setBtn(JButton btn) {
		this.btn = btn;
	}

	public JLabel getLbl() {
		return lbl;
	}

	public void setLbl(JLabel lbl) {
		this.lbl = lbl;
	}

	public JFormattedTextField getTxt() {
		return txt;
	}

	public void setTxt(JFormattedTextField txt) {
		this.txt = txt;
	}
    
    
}
