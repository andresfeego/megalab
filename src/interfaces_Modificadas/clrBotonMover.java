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
import java.awt.Polygon;
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

import org.omg.CORBA.Bounds;

import com.mysql.jdbc.EscapeTokenizer;



public class clrBotonMover extends JButton implements MouseListener{

 private Color colorPulsado=new Color(0,0,0);
  
   
    private String label;
    private int anchoString;
    private int  width;
    private int  heigth;
    private String  rutaIcono;
    private Image imgr;
    private int tipo=0;
    private int escala=Colores.escala; 

    private int xIcono=-1;
    private int yIcono=-1;
    private final Border ColorBorder = javax.swing.BorderFactory.createLineBorder(Colores.clrPrincipal);

    
    public clrBotonMover(String label,int width,int heigth,String rutaIcono){
    	
    	super();
    	this.width=width;
    	this.heigth=heigth;
    	this.rutaIcono=rutaIcono;
    	this.label=label;
    	Font font = Colores.fuenteNormal ;
    	  BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    	  FontMetrics fm = img.getGraphics().getFontMetrics(font);
    	   this.anchoString= fm.stringWidth(label);
    	
		
    	//this.setIcon(iconoEscala);
    	this.setHorizontalAlignment(SwingConstants.LEFT);
    	
    	this.setBounds( 0, 0, anchoString+width,heigth);
     this.setFont(Colores.fuenteBtn);
     this.setPreferredSize(new Dimension(anchoString+width,heigth));
        this.setCursor( new Cursor(Cursor.HAND_CURSOR));
        setBackground(Colores.clrPrincipal);
  this.setContentAreaFilled(false);
  this.setBorderPainted(false);
  this.repaint();
  this.addMouseListener(this);
  
  ImageIcon imagen=new ImageIcon(clrBotonMover.class.getResource(rutaIcono));
	ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(width/3, -1, java.awt.Image.SCALE_DEFAULT));
	this.imgr= iconoEscala.getImage();
 
   
     
    }
    
    public clrBotonMover(int width){
    	
    	super();
    	this.width=width;
    	this.heigth=width;
    	this.rutaIcono=rutaIcono;
    	
		
    	//this.setIcon(iconoEscala);
    	this.setHorizontalAlignment(SwingConstants.LEFT);
    	
    	this.setBounds( 0, 0, anchoString+width,heigth);
     this.setFont(Colores.fuenteBtn);
     this.setPreferredSize(new Dimension(anchoString+width,heigth));
        this.setCursor( new Cursor(Cursor.HAND_CURSOR));
        setBackground(Colores.clrPrincipal);
  this.setContentAreaFilled(false);
  this.setBorderPainted(false);
  this.repaint();
  this.addMouseListener(this);

		  if (Colores.clrTextoPrincipal==Color.WHITE) {
				rutaIcono = "/Recursos/mover.png";

		} else {
			rutaIcono = "/Recursos/moverN.png";
		}

  
	  
	


	  

  ImageIcon imagen=new ImageIcon(clrBotonMover.class.getResource(rutaIcono));
	ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(width/1, -1, java.awt.Image.SCALE_DEFAULT));
	this.imgr= iconoEscala.getImage();
 
   
     
    }

 
    

    

    @Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		

    	
    	Graphics2D g3 = (Graphics2D)g.create();
        g3.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g3.setStroke(new BasicStroke(7f));
        
        g3.setColor(Color.WHITE);
        int[] x=new int[3];
        int[] y=new int[3];
        int n;
        x[0]=0; x[1]=this.getWidth(); x[2]=this.getWidth();
        y[0]=0; y[1]=0; y[2]=this.getWidth();
        n = 3;

        g3.fillPolygon(x, y, n);        
        
        g3.setColor(Colores.clrSecundario);
        g3.fillPolygon(x, y, n);        
        
      
        Graphics2D gi = (Graphics2D)g.create();
        gi.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        xIcono=(this.getWidth()-2)- (imgr.getWidth(null));
        yIcono=2;
   
       gi.drawImage(imgr, xIcono, yIcono, imgr.getWidth(null), imgr.getHeight(null), null);
      
        gi.dispose();   

	}

	@Override
    protected void paintBorder(Graphics g) {
		
	}

 public Color getColorPulsado() {
  return colorPulsado;
 }

 public void setColorPulsado(Color colorPulsado) {
  this.colorPulsado = colorPulsado;
 }

 @Override
 public void mouseClicked(MouseEvent arg0) {
  
  
 }

 @Override
 public void mouseEntered(MouseEvent arg0) {
  
  
 }

 @Override
 public void mouseExited(MouseEvent arg0) {
  
  
 }

 @Override
 public void mousePressed(MouseEvent arg0) {
  colorPulsado=new Color(245,130,7);;
  repaint();
 }

 @Override
 public void mouseReleased(MouseEvent arg0) {
  colorPulsado=new Color(235,166,38);
  repaint();
 }

}