package interfaces_Modificadas;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class clrToogleBoton extends JToggleButton{

	
	public clrToogleBoton(String label) {
		super(label);

			this.setBackground(Colores.clrFondo);
			this.setFont(Colores.fuenteNormal);
			
			
		


	}
	
	

}
