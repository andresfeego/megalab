package interfaces_Modificadas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

import com.itextpdf.text.log.SysoCounter;

import Interfaces.Principal;

public class clrBarraMenu extends JMenuBar {
	private  int pX,pY;
	private clrBotonCuadrado btnCerrar;
	private clrBotonCuadrado btnMaximizar;
	private clrBotonCuadrado btnRestaurar;
	private clrBotonCuadrado btnMInimizar;
	 private clrBotonCuadrado btnMover;
	
	public clrBarraMenu() {
		super();
		this.setBackground(Colores.clrPrincipal);
	
	}
	

	@Override
	protected void paintComponent(Graphics g) {
	
		super.paintComponent(g);
		
		 	if(btnCerrar!=null){this.remove(btnCerrar);};
		if(btnMaximizar!=null){this.remove(btnMaximizar);};
		if(btnMover!=null){this.remove(btnMover);};
		if(btnMInimizar!=null){this.remove(btnMInimizar);};
		if(btnRestaurar!=null){this.remove(btnRestaurar);};
		this.
	       
	        btnCerrar=new clrBotonCuadrado(20,1);
	        btnCerrar.setBounds(this.getWidth()-this.getHeight(),1, this.getHeight(),this.getHeight()-4);
	        btnCerrar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Principal.getInstancePrincipal().dispose();
				}
			});
	        this.add(btnCerrar);
	    
	        
	      if (Principal.getInstancePrincipal().getWidth()<1300) {
	    	  
	    	  btnMaximizar=new clrBotonCuadrado(20,4);
	    	  btnMaximizar.setBounds(this.getWidth()-(this.getHeight()*2),1, this.getHeight(),this.getHeight()-4);
	    	  btnMaximizar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
							Principal.getInstancePrincipal().setBounds(0, 0,GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width,GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);
							if (Principal.getInstancePrincipal().getUsuario()==null) {
								Principal.getInstancePrincipal().crearMenu(null);	
							}else{
								Principal.getInstancePrincipal().crearMenu(Principal.getInstancePrincipal().getUsuario());
							}
					}
				});
		        this.add(btnMaximizar);
		} else {
			 
		   btnRestaurar=new clrBotonCuadrado(20,2);
	        btnRestaurar.setBounds(this.getWidth()-(this.getHeight()*2),1, this.getHeight(),this.getHeight()-4);
	        btnRestaurar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Principal.getInstancePrincipal().setBounds(0,0, 1200, 500);
					if (Principal.getInstancePrincipal().getUsuario()==null) {
						Principal.getInstancePrincipal().crearMenu(null);	
					}else{
						Principal.getInstancePrincipal().crearMenu(Principal.getInstancePrincipal().getUsuario());
					}
				}
			});
	        this.add(btnRestaurar);
		}
	       
	      btnMInimizar=new clrBotonCuadrado(20,3);
	        btnMInimizar.setBounds(this.getWidth()-(this.getHeight()*3),1, this.getHeight(),this.getHeight()-4);
	        btnMInimizar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Principal.getInstancePrincipal().setState(JFrame.ICONIFIED);
				}
			});
	        this.add(btnMInimizar);
	        System.out.println("agreo boton de minimizar ");
	        btnMover=new clrBotonCuadrado(20,5);
	        btnMover.setBounds(this.getWidth()-(this.getHeight()*4),1, this.getHeight(),this.getHeight()-4);
	        btnMover.addMouseListener(new MouseAdapter() {
				
			
				public void mousePressed(MouseEvent e) {
					 Principal.getInstancePrincipal().setBounds(100, 10, 1200, 600);
					clrBarraMenu.this.repaint();
	               
	                pX=e.getX();
	                pY=e.getY();
	               
				}
				
				
				@Override
				public void mouseReleased(MouseEvent e) {
					if (Principal.getInstancePrincipal().getUsuario()==null) {
						Principal.getInstancePrincipal().crearMenu(null);	
					}else{
						Principal.getInstancePrincipal().crearMenu(Principal.getInstancePrincipal().getUsuario());
					}
					super.mouseReleased(e);
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
	        	   Principal.getInstancePrincipal().setLocation( Principal.getInstancePrincipal().getLocation().x+me.getX()-pX, Principal.getInstancePrincipal().getLocation().y+me.getY()-pY);
	            }
	        	
			});
	        this.add(btnMover);
	        
	        
	        
	}



	
	
	
	

}
