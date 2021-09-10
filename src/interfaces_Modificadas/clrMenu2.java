package interfaces_Modificadas;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;

public class clrMenu2 extends JMenu {


	// estos son los  menus expandibles 
	
	
	public clrMenu2(String label) {
		super();
		this.setText(label);
		this.setFont(Colores.fuenteNormal);
		this.setForeground(Color.black);
		

		this.repaint();
	}
	
	
	
	
	

}
