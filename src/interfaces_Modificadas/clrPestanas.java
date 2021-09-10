package interfaces_Modificadas;

import java.awt.Color;
import java.util.Enumeration;

import javax.swing.JTabbedPane;
import javax.swing.UIManager;

public class clrPestanas extends JTabbedPane{

	public clrPestanas(int ubicacion) {
		super(ubicacion);
		
		this.setUI(new customTabbedPaneUI());
		this.setFont(Colores.fuentePequena);
		
	
	
	
	
	}
	
	

}
