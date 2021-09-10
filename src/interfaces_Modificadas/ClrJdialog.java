package interfaces_Modificadas;

import java.awt.BasicStroke;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;

public class ClrJdialog extends JDialog {

	public ClrJdialog() {
		super();
	}

	@Override
	public void paint(Graphics g) {
			super.paint(g); 
			
	
	}


	public ClrJdialog(Frame owner, boolean modal) {
		super(owner, modal);
	}

	
	
	
	
	
	
	
	
	
}
