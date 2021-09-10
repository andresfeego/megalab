package interfaces_Modificadas;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
public class clrPassword extends JPasswordField {
    private final Border ColorBorder = javax.swing.BorderFactory.createLineBorder(Colores.clrTextoInactivo);
    private final String label = "123456789";
    private int limite;
	private clrPassword cdp;

    public clrPassword(int limite) {
        super();
        this.limite=limite;
        this.cdp=this;
        limitar(limite);
        setBorder(ColorBorder);
        setEchoChar('☺');
        setBackground(Colores.clrFondo);
    
        if (getText().equals("")) {
			setForeground(Colores.clrTextoInactivo);
			setText(label);
		}
    
    
    
    this.addFocusListener(new FocusAdapter() {
    	@Override
		public void focusGained(FocusEvent arg0) {
			if (getText().equals(label)) {
				setText("");
				setForeground(Color.BLACK);
			}
			
		}
		@Override
		public void focusLost(FocusEvent arg0) {
			if (getText().equals("")) {
				setForeground(Colores.clrTextoInactivo);
				setText(label);
			}
			

		}
	
	});
   
}
    
    public void limitar(final int limite) {

		cdp.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e)

			{
				if (cdp.getText().length() == limite)

					e.consume();
			}

			public void keyPressed(KeyEvent arg0) {
			}

			public void keyReleased(KeyEvent arg0) {
			}
		});

	}


}