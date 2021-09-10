package interfaces_Modificadas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import org.omg.CORBA.Bounds;

import com.mysql.jdbc.EscapeTokenizer;
import com.sun.org.apache.bcel.internal.generic.NEW;



public class btnRedondo extends JButton {

 private Color colorPulsado=new Color(0,0,0);
 private int ancho; 
    private int alto;
    private String label;
    private int anchoString;
    private Rectangle rec;
    private Image imgr;
    private int tipo=0;
    private int escala=Colores.escala; 
    private Color principal=Colores.clrPrincipal;
    private Color secundario=Colores.clrSecundario;

    private int xIcono=-1;
    private int yIcono=-1;
    private final Border ColorBorder = javax.swing.BorderFactory.createLineBorder(Colores.clrPrincipal);

    
  /*  public btnRedondo(String label,Rectangle bounds){
    	
    	super();
    	this.rec=bounds;
    	this.setBounds(bounds);
    	this.label=label;
    	this.alto=50;
    	this.ancho=50;
    	Font font = Colores.fuenteNormal ;
    	  BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    	  FontMetrics fm = img.getGraphics().getFontMetrics(font);
    	   this.anchoString= fm.stringWidth(label);
    	
		
    	//this.setIcon(iconoEscala);
    	this.setHorizontalAlignment(SwingConstants.LEFT);
    	
    	this.setBounds( 0, 0, anchoString+ancho, alto );
     this.setFont(Colores.fuenteBtn);
     this.setPreferredSize(new Dimension(anchoString+ancho,alto));
        this.setCursor( new Cursor(Cursor.HAND_CURSOR));
        setBackground(Colores.clrBordes);
  this.setContentAreaFilled(false);
  this.setBorderPainted(false);
  this.repaint();
  this.addMouseListener(this);
  
  ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource("/Recursos/nada.png"));
	ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/3, -1, java.awt.Image.SCALE_DEFAULT));
	this.imgr= iconoEscala.getImage();
  
  //·······················espacio para sobreescribir tabbedpane······································································
	  
	
	UIManager.put("TabbedPane.background", Colores.clrFondo);
	UIManager.put("TabbedPane.foreground", Color.BLACK);
	UIManager.put("TabbedPane.selectedForeground", Colores.clrTitulo);
	UIManager.put("TabbedPane.selected", Colores.clrBordesClaro);
UIManager.put("TabbedPane.contentAreaColor", Color.white);

	UIManager.put("Button.background", Color.WHITE);//color de los botones por defecto para jbutton y para fechas jdatechooser
	UIManager.put("Button.foreground", Color.black);//color de los botones por defecto para jbutton y para fechas jdatechooser
	UIManager.put("ScrollBar.background", Color.white);
	UIManager.put("ToggleButton.background", Color.white);
	UIManager.put("ToggleButton.select", Colores.clrBordes);
	UIManager.put("ToggleButton.border", ColorBorder);
	UIManager.put("ToggleButton.font", Colores.fuenteBtn);
	UIManager.put("Spinner.border", ColorBorder);
	UIManager.put("Spinner.arrowButtonBorder", ColorBorder);
	UIManager.put("ComboBox.background", Color.WHITE);
	UIManager.put("ScrollBar.track", Color.WHITE);
	
 //·····························································································
   
     
    }

 */
    
    
    public btnRedondo(String label,Rectangle bounds,int tipo){
    	
    	super();
    	this.rec=bounds;
    	this.setBounds(bounds);
    	this.label=label;
    	this.alto=50;
    	this.tipo=tipo;
    	this.ancho=50;
    	Font font = Colores.fuenteNormal ;
    	  BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    	  FontMetrics fm = img.getGraphics().getFontMetrics(font);
    	   this.anchoString= fm.stringWidth(label);
    	   if (tipo==0) {

    		   String ruta="/Recursos/nadaN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/nada.png";
			}   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
    			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
    			this.imgr= iconoEscala.getImage();
		}
    	   
    	   if (tipo==1) {
    		   String ruta="/Recursos/aceptarN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/aceptar.png";
			}   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
    	   
    	   
    	   if (tipo==2) {
    		   String ruta="/Recursos/cancelarN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/cancelar.png";
			}   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
    			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
    			this.imgr= iconoEscala.getImage();
		}
    	   
    	   if (tipo==3) {
    		   String ruta="/Recursos/actualizarN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/actualizar.png";
			}   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
    	   
    	   if (tipo==4) {
    		   String ruta="/Recursos/agregarN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/agregar.png";
			}   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
    	   
    	   if (tipo==5) {
    		   String ruta="/Recursos/eliminarN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/eliminar.png";
			}   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
    	   
    	   if (tipo==6) {
    		   String ruta="/Recursos/buscarN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/buscar.png";
			}   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
    	   
    	   if (tipo==7) {
    		   String ruta="/Recursos/usuarioN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/usuario.png";
			}   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
    	   
    	   if (tipo==8) {
    		   String ruta="/Recursos/siguienteN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/siguiente.png";
			}   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
    	   
    	   if (tipo==9) {
    		   String ruta="/Recursos/salirN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/salir.png";
			}   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
    	   
    	   if (tipo==10) {
    		   String ruta="/Recursos/activarUsuarioN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/activarUsuario.png";
			}   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
    	   
    	   if (tipo==11) {
    		   String ruta="/Recursos/desactivarUsuarioN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/desactivarUsuario.png";
			}   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
    	
    	   if (tipo==12) {
    		   String ruta="/Recursos/guardarN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/guardar.png";
			}   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
    	   
    	   if (tipo==13) {
    		   String ruta="/Recursos/limpiarN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/limpiar.png";
			}   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
		
    	   if (tipo==14) {
    		   String ruta="/Recursos/recalcularN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/recalcular.png";
			} 
    		   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
    	   
    	   if (tipo==15) {
    		   String ruta="/Recursos/impresionN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/impresion.png";
			} 
    		   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
    	   
    	   if (tipo==16) {
    		   String ruta="/Recursos/listadosN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/listados.png";
			} 
    		   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
    	   
    	   if (tipo==17) {
    		   String ruta="/Recursos/configN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/config.png";
			} 
    		   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
    	   
    	//this.setIcon(iconoEscala);
    	this.setHorizontalAlignment(SwingConstants.LEFT);
    	
    	this.setBounds( 0, 0, anchoString+ancho, alto );
     this.setFont(Colores.fuenteBtn);
     this.setPreferredSize(new Dimension(anchoString+ancho,alto));
        this.setCursor( new Cursor(Cursor.HAND_CURSOR));
        setBackground(Colores.clrPrincipal);
  this.setContentAreaFilled(false);
  this.setBorderPainted(false);
  this.repaint();
  this.addMouseListener(new MouseListener() {

		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			secundario=Colores.clrSecundario;
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			secundario=Colores.clrPrincipal;
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
});
  
  
  
  //·······················espacio para sobreescribir tabbedpane······································································
	
  UIManager.put("TabbedPane.unselectedBackground", Colores.clrFondo);
	UIManager.put("TabbedPane.foreground", Color.black);
	UIManager.put("TabbedPane.selectedForeground", Color.white);
	UIManager.put("TabbedPane.selected", Colores.clrPrincipal);
	UIManager.put("TabbedPane.contentAreaColor", Color.white);

	UIManager.put("Button.background", Color.WHITE);//color de los botones por defecto para jbutton y para fechas jdatechooser
	UIManager.put("Button.foreground", Color.black);//color de los botones por defecto para jbutton y para fechas jdatechooser
	UIManager.put("ScrollBar.background", Color.white);
	UIManager.put("ToggleButton.background", Color.white);
	UIManager.put("ToggleButton.select", Colores.clrPrincipal);
	UIManager.put("ToggleButton.border", ColorBorder);
	UIManager.put("ToggleButton.font", Colores.fuenteBtn);
	UIManager.put("Spinner.border", ColorBorder);
	UIManager.put("Spinner.arrowButtonBorder", ColorBorder);
	UIManager.put("ComboBox.background", Color.WHITE);
	UIManager.put("ScrollBar.track", Color.WHITE);
	UIManager.put("ToolTip.background", Colores.calcularColorClaroSecundario());
	UIManager.put("ToolTip.backgroundInactive", Colores.calcularColorClaroSecundario());
	UIManager.put("ToolTip.foreground", Color.BLACK);
	UIManager.put("ToolTip.foregroundInactive", Color.BLACK);
	UIManager.put("ToolTip.font", Colores.fuentePequena);
	UIManager.put("ToolTip.border", new MatteBorder(1, 1, 1, 1, Colores.clrAlertaCamarada));
	UIManager.put("ProgressBar.background", Color.WHITE);
	UIManager.put("ProgressBar.foreground", Colores.clrSecundario);
	
 //·····························································································
   
     
    }
    
  public btnRedondo(String label,Rectangle bounds,int tipo,boolean noEscuchas){
    	
    	super();
    	this.rec=bounds;
    	this.setBounds(bounds);
    	this.label=label;
    	this.alto=50;
    	this.tipo=tipo;
    	this.ancho=50;
    	Font font = Colores.fuenteNormal ;
    	  BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    	  FontMetrics fm = img.getGraphics().getFontMetrics(font);
    	   this.anchoString= fm.stringWidth(label);
    	   if (tipo==0) {

    		   String ruta="/Recursos/nadaN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/nada.png";
			}   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
    			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
    			this.imgr= iconoEscala.getImage();
		}
    	   
    	   if (tipo==1) {
    		   String ruta="/Recursos/aceptarN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/aceptar.png";
			}   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
    	   
    	   
    	   if (tipo==2) {
    		   String ruta="/Recursos/cancelarN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/cancelar.png";
			}   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
    			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
    			this.imgr= iconoEscala.getImage();
		}
    	   
    	   if (tipo==3) {
    		   String ruta="/Recursos/actualizarN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/actualizar.png";
			}   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
    	   
    	   if (tipo==4) {
    		   String ruta="/Recursos/agregarN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/agregar.png";
			}   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
    	   
    	   if (tipo==5) {
    		   String ruta="/Recursos/eliminarN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/eliminar.png";
			}   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
    	   
    	   if (tipo==6) {
    		   String ruta="/Recursos/buscarN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/buscar.png";
			}   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
    	   
    	   if (tipo==7) {
    		   String ruta="/Recursos/usuarioN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/usuario.png";
			}   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
    	   
    	   if (tipo==8) {
    		   String ruta="/Recursos/siguienteN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/siguiente.png";
			}   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
    	   
    	   if (tipo==9) {
    		   String ruta="/Recursos/salirN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/salir.png";
			}   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
    	   
    	   if (tipo==10) {
    		   String ruta="/Recursos/activarUsuarioN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/activarUsuario.png";
			}   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
    	   
    	   if (tipo==11) {
    		   String ruta="/Recursos/desactivarUsuarioN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/desactivarUsuario.png";
			}   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
    	
    	   if (tipo==12) {
    		   String ruta="/Recursos/guardarN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/guardar.png";
			}   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
    	   
    	   if (tipo==13) {
    		   String ruta="/Recursos/limpiarN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/limpiar.png";
			}   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
		
    	   if (tipo==14) {
    		   String ruta="/Recursos/recalcularN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/recalcular.png";
			} 
    		   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
    	   
    	   if (tipo==15) {
    		   String ruta="/Recursos/impresionN.png";
    		   if (!Colores.clrIconosNegros) {
    			   ruta="/Recursos/impresion.png";
			} 
    		   
    		   ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource(ruta));
   			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(ancho/escala, -1, java.awt.Image.SCALE_SMOOTH));
   			this.imgr= iconoEscala.getImage();
		}
    	   
    	//this.setIcon(iconoEscala);
    	this.setHorizontalAlignment(SwingConstants.LEFT);
    	
    	this.setBounds( 0, 0, anchoString+ancho, alto );
     this.setFont(Colores.fuenteBtn);
     this.setPreferredSize(new Dimension(anchoString+ancho,alto));
        this.setCursor( new Cursor(Cursor.HAND_CURSOR));
        setBackground(Colores.clrPrincipal);
  this.setContentAreaFilled(false);
  this.setBorderPainted(false);
  this.repaint();
  
  
  
  //·······················espacio para sobreescribir tabbedpane······································································
	
  UIManager.put("TabbedPane.unselectedBackground", Colores.clrFondo);
	UIManager.put("TabbedPane.foreground", Color.black);
	UIManager.put("TabbedPane.selectedForeground", Color.white);
	UIManager.put("TabbedPane.selected", Colores.clrPrincipal);
	UIManager.put("TabbedPane.contentAreaColor", Color.white);

	UIManager.put("Button.background", Color.WHITE);//color de los botones por defecto para jbutton y para fechas jdatechooser
	UIManager.put("Button.foreground", Color.black);//color de los botones por defecto para jbutton y para fechas jdatechooser
	UIManager.put("ScrollBar.background", Color.white);
	UIManager.put("ToggleButton.background", Color.white);
	UIManager.put("ToggleButton.select", Colores.clrPrincipal);
	UIManager.put("ToggleButton.border", ColorBorder);
	UIManager.put("ToggleButton.font", Colores.fuenteBtn);
	UIManager.put("Spinner.border", ColorBorder);
	UIManager.put("Spinner.arrowButtonBorder", ColorBorder);
	UIManager.put("ComboBox.background", Color.WHITE);
	UIManager.put("ScrollBar.track", Color.WHITE);
	
 //·····························································································
   
     
    }
    
    


    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(2f));
        g2.setColor(principal);
        g2.drawOval(2, 2, ancho-7 , alto-7);
        g2.dispose();
        
        
        Graphics2D g3 = (Graphics2D)g.create();
        g3.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g3.setPaint(secundario);// aqui color para botones $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
        g3.fill(new Ellipse2D.Double(2, 2, ancho-7, alto-7));
        g3.setColor(Colores.clrFondo);
       g3.drawOval(3, 3, ancho-9, alto-9);
      // g3.setColor(Color.green);
       g3.setStroke(new BasicStroke(7f));
     
       
       g3.setColor(new Color(255, 255, 255, 50));
       g3.drawOval(2, 2, ancho-7, alto-7); 
      
        g3.dispose();   
        
        
        
        Graphics2D gi = (Graphics2D)g.create();
        gi.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (escala==2) {
			xIcono=12;
			yIcono=12;
		}
       gi.drawImage(imgr, xIcono, yIcono, imgr.getWidth(null), imgr.getHeight(null), null);
      
        gi.dispose();   
        
          
        
        
        Graphics2D g4 = (Graphics2D)g.create();
        g4.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g4.setPaint(new Color(0,0, 0));
        g4.setStroke(new BasicStroke(7f));
        g4.drawString(label,ancho,(alto/2)+5);
        g4.dispose();
        
        
       
        
        
    }



 public Color getSecundario() {
		return secundario;
	}




	public void setSecundario(Color secundario) {
		this.secundario = secundario;
		this.repaint();
	}






}