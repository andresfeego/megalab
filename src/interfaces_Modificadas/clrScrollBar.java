package interfaces_Modificadas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Scrollbar;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.BasicScrollPaneUI;
import javax.swing.plaf.metal.MetalScrollBarUI;

public class clrScrollBar extends JScrollPane{

	public clrScrollBar() {
		super();
		JScrollBar sb= this.getVerticalScrollBar();
		sb.setPreferredSize(new Dimension(15, Integer.MAX_VALUE));
	    sb.setUI(new MyScrollbarUI());
	    
	    this.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
	 
	    
	}
	
	static class MyScrollbarUI extends MetalScrollBarUI  {
	    private Image imageThumb, imageTrack;
	    
	    MyScrollbarUI() {
	        try {
	            imageThumb = ImageIO.read(new File("/Recursos/thums.png"));
	            imageTrack = ImageIO.read(new File("/Recursos/track.png"));
	        } catch (IOException e){}
	    }

	    	@Override
	    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {        
	        g.translate(thumbBounds.x, thumbBounds.y);
	        g.setColor( Colores.clrSecundario );
	        g.drawRect( 0, 0, thumbBounds.width - 2, thumbBounds.height - 1 );
	        g.fillRect( 0, 0, thumbBounds.width - 2, thumbBounds.height - 1 );
	       // AffineTransform transform = AffineTransform.getScaleInstance((double)thumbBounds.width/imageThumb.getWidth(null),(double)thumbBounds.height/imageThumb.getHeight(null));
	        //((Graphics2D)g).drawImage(imageThumb, transform, null);
	        g.translate( thumbBounds.x, thumbBounds.y );
	    }
	    	@Override
	    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {        
	    		g.translate(trackBounds.x, trackBounds.y);
		        g.setColor( Colores.clrPrincipal );
		        g.drawRect( 0, 0, trackBounds.width - 1, trackBounds.height -1 );
		       // AffineTransform transform = AffineTransform.getScaleInstance((double)thumbBounds.width/imageThumb.getWidth(null),(double)thumbBounds.height/imageThumb.getHeight(null));
		        //((Graphics2D)g).drawImage(imageThumb, transform, null);
		        g.translate( -trackBounds.x, -trackBounds.y );
	    }
	    	
	    	protected JButton createDecreaseButton( int orientation ){
	    		JButton but = new JButton(); 
	    		but.setBackground(Colores.clrPrincipal);
	    		but.setVisible(true); 
	    		return but;
	    	}
	    	
	    	protected JButton createIncreaseButton( int orientation ){
	    		JButton but = new JButton(); 
	    		but.setBackground(Colores.clrPrincipal);
	    		but.setVisible(true); 
	    		return but;
	    	}

	}
	

}
