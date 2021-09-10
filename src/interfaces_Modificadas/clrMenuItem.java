package interfaces_Modificadas;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JMenuItem;

public class clrMenuItem extends JMenuItem{

	  private final Insets margen= new Insets(10, 50, 10, 50);

	// estos son los menus normales - los de la mayoria de item ejemplo "iniciar sesion" 
	  
	  
	public clrMenuItem(String label) {
		super();
		
		this.setText(label);
		this.setBackground(Colores.clrFondo);
		this.setFont(Colores.fuenteNormal);
		this.setForeground(Color.BLACK);
	}


	
	
}
