package interfaces_Modificadas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.ObjectInputStream.GetField;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class clrFrameBordesTitulo extends JFrame {
	
	  private final Border ColorBorder = javax.swing.BorderFactory.createLineBorder(Colores.clrPrincipal);
	    public clrFrameBordesTitulo() {
	        super();
	        getRootPane().setBorder(javax.swing.BorderFactory.createMatteBorder( 3, 3, 3, 3, Color.gray));
	        
	      //  Toolkit tk = Toolkit.getDefaultToolkit();
			//tamanoPantalla= tk.getScreenSize();
			
			
			this.getContentPane().setForeground(Color.BLACK);
			this.setFont(new Font("Waukegan LDO Extended", Font.BOLD, 18));
			this.getContentPane().setBackground(Colores.clrFondo);
			this.setUndecorated(true);
			this.getContentPane().setLayout(null);
			this.getContentPane().setLayout(null);
			// agregando imagen 
			
			//UIManager.put("Label.background", Color.black);
			
	        //this.getRootPane().setBorder(ColorBorder);
	    }

}
