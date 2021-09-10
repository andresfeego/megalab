package interfaces_Modificadas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;

import conexion.conexion;
import conexion.conexionBusqueda;
import conexion.conexionComprobar;
import auxiliares.MiLaboratorio;
import Interfaces.Principal;

public class clrDeskPanel extends JDesktopPane {
	private Image logoLab=null;
	public clrDeskPanel() {
		super();
		
		setBackground(Color.white);
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		 Image logoMegaLab=Colores.LogoMEGALAB;
		  Graphics2D gi = (Graphics2D)g.create();
	        gi.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	        gi.drawImage(logoMegaLab,this.getWidth()-logoMegaLab.getWidth(null)-20,this.getHeight()-logoMegaLab.getHeight(null)-20,logoMegaLab.getWidth(null),logoMegaLab.getHeight(null),null);
	        gi.dispose();
	      


			 logoLab=Colores.logoLab;

    	  Graphics2D gml = (Graphics2D)g.create();
    	  gml.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    	  gml.drawImage(logoLab,(this.getWidth()/2)-(logoLab.getWidth(null)/2),(this.getHeight()/2)-(logoLab.getHeight(null)/2),logoLab.getWidth(null),logoLab.getHeight(null),null);
    	  gml.dispose();
    	  
	
	}

	
	
}
