package interfaces_Modificadas;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;

public class clrLabel extends JLabel{
	private String Label;
	private clrLabel este;

	public clrLabel(String label,int tam) {
		super();
		this.Label=label;
		this.setText(label);
		if (tam==1) {
			this.setFont(Colores.fuenteNormal);

		} 
		
		if(tam==2) {
			this.setFont(Colores.fuenteMediana);

		}
	}
	
	public clrLabel(String label,int tam,boolean titulo) {
		super();
		this.Label=label;
		this.este=this;
		this.setForeground(Colores.clrTextoPrincipal);
		this.setText(label);
		if (tam==1) {
			this.setFont(Colores.fuenteNormal);

		} 
		
		if(tam==2) {
			this.setFont(Colores.fuenteMediana);

		}
	}
	
	
	public void reiniciar() {
	
		this.setText(Label);
		this.setForeground(Color.black);

	}

	public void soloNumeros(){

		este.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});

	
	}

}
