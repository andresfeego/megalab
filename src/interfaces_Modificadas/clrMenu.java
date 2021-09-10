package interfaces_Modificadas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;

public class clrMenu extends JMenu {

//este es el menu principal - la barra principal de menu 
	
	public clrMenu(String label) {
		super();
		this.setText(label);
		this.setFont(Colores.fuenteNormal);
		this.setForeground(Colores.clrTextoPrincipal);
		this.repaint();
	}

	
	
	
	

}
