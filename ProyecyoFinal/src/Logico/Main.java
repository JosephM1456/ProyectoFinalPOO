package Logico;

import Visual.Principal;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Empresa.getInstance();
		
		DiscoDuro d1 = new DiscoDuro("C-0", "D10", (float)150.10 , 3, "Nvidia", "Rawr", "SATA-1", (float)1024.0);
		DiscoDuro d2 = new DiscoDuro("C-0", "D10", (float)150.10 , 3, "Nvidia", "Rawr", "IDE", (float)1024.0);
		Empresa.getInstance().insertarComponente(d1);
		Empresa.getInstance().insertarComponente(d2);
		
		Principal ventana = new Principal();
		ventana.setVisible(true);
		
	}

}
