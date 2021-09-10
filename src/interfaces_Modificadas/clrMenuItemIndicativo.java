package interfaces_Modificadas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;


public class clrMenuItemIndicativo extends JMenuItem{
	private String rutaIcono;
    private Image imgr;
    private int xIcono=-1;
    private int yIcono=-1;
    
    
	public clrMenuItemIndicativo(String label,int tipo) {
		super();

		this.setText(label);
		this.setBackground(Colores.clrFondo);
		this.setForeground(Colores.clrSecundario);
		this.setFont(Colores.fuenteNormal);
		rutaIcono = "/Recursos/cancelarN.png";
		
		switch (tipo) {
		case 1:rutaIcono = "/Recursos/usuarioN.png";
			break;
			
		case 2:rutaIcono = "/Recursos/laboratorioN.png";
		break;
		
		case 3:rutaIcono = "/Recursos/personasN.png";
		break;
		
		case 4:rutaIcono = "/Recursos/empresaN.png";
		break;
		
		case 5:rutaIcono = "/Recursos/prosedimientosN.png";
		break;
		
		case 6:rutaIcono = "/Recursos/laboratoriosN.png";
		break;
		
		case 7:rutaIcono = "/Recursos/geograficaN.png";
		break;
		
		case 8:rutaIcono = "/Recursos/agregarN.png";
		break;
		
		case 9:rutaIcono = "/Recursos/informesN.png";
		break;
		
		case 10:rutaIcono = "/Recursos/listadosN.png";
		break;
		
		case 11:rutaIcono = "/Recursos/carteraN.png";
		break;
		
		case 12:rutaIcono = "/Recursos/facturacionN.png";
		break;
		
		case 13:rutaIcono = "/Recursos/impresionN.png";
		break;
		
		case 14:rutaIcono = "/Recursos/actualizarN.png";
		break;
		
		case 15:rutaIcono = "/Recursos/laboratoriosN.png";
		break;
		
		case 16:rutaIcono = "/Recursos/megalabN.png";
		break;
		
		case 17:rutaIcono = "/Recursos/feegoN.png";
		break;
		
		case 18:rutaIcono = "/Recursos/licenciaN.png";
		break;
		

		default:rutaIcono = "/Recursos/nada.png";
			break;
		}

		
		
		  ImageIcon imagen=new ImageIcon(clrBotonCuadrado.class.getResource(rutaIcono));
			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(50/2, -1, java.awt.Image.SCALE_SMOOTH));
			this.imgr= iconoEscala.getImage();
		 
	}

	
	public clrMenuItemIndicativo(String label,String m) {
		super();

		this.setText(label);
		this.setBackground(Colores.clrFondo);
		this.setForeground(Colores.clrSecundario);
		this.setFont(Colores.fuenteNormal);
		
		 
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		
		 super.paintComponent(g);
		
	    Graphics2D gi = (Graphics2D)g.create();
        gi.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        xIcono=this.getWidth()-25;
        yIcono=0;
   
       gi.drawImage(imgr, xIcono, yIcono,25,25, null);
      
        gi.dispose();   
       
        
	}


	
	

	
	
}
