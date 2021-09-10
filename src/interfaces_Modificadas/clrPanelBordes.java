package interfaces_Modificadas;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.io.ObjectInputStream.GetField;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import Interfaces.Principal;


public class clrPanelBordes extends JPanel {
	private boolean titulo;
	private  int pX,pY;
	
	
	    public clrPanelBordes(boolean titulo) {
	    	
	        super();
	        this.titulo=titulo;
	        setBackground(Colores.clrFondo);
	        setLayout(new FlowLayout());
	        setBorder(javax.swing.BorderFactory.createMatteBorder( 3, 3, 3, 3, Color.gray));
	        //this.getRootPane().setBorder(ColorBorder);
	    }
	    
	    
		@Override
		public void paintAll(Graphics g) {
			
			  
			super.paintAll(g);
		}
		@Override
		protected void paintBorder(Graphics g) {
			
			if (titulo) {
				Graphics2D g3 = (Graphics2D)g.create();
		        g3.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		       g3.setColor(Colores.clrPrincipal);
		       g3.fillRect(this.getX(), this.getY(), this.getWidth(), 45); 
		      
		        g3.dispose();   
		        
		        clrBotonMover btnMover=new clrBotonMover(20);
				btnMover.setBounds(this.getWidth()-46, 3, 43, 43);
				btnMover.addMouseListener(new MouseAdapter() {
					
					
					public void mousePressed(MouseEvent e) {
						 	               
		                pX=e.getX();
		                pY=e.getY();
		               
					}
				});
				
				  btnMover.addMouseMotionListener(new MouseAdapter() {
			        	
			        	public void mouseDragged(MouseEvent me)
			            {
			                // Set the location
			                // get the current location x-co-ordinate and then get
			                // the current drag x co-ordinate, add them and subtract most recent
			                // mouse pressed x co-ordinate
			                // do same for y co-ordinate
			        		SwingUtilities.getWindowAncestor(clrPanelBordes.this).setLocation(SwingUtilities.getWindowAncestor(clrPanelBordes.this).getLocation().x+me.getX()-pX,SwingUtilities.getWindowAncestor(clrPanelBordes.this).getLocation().y+me.getY()-pY);
			            }
			        	
					});
				this.add(btnMover);
	      
			
			}
			
			super.paintBorder(g);
		}
	    
	    
	    
	   
}
