package interfaces_Modificadas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

public class clrRadioButton extends JRadioButton{

	public clrRadioButton(String label) {
		super();
		
		this.setBackground(Colores.clrFondo);
		this.setText(label);
		this.setFont(Colores.fuenteNormal);
		clrRadioButton.this.setForeground(Colores.clrTextoInactivo);
		ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource("/Recursos/radiconA.png"));
    	ImageIcon imagen2=new ImageIcon(btnRedondo.class.getResource("/Recursos/radiconS.png"));
    	ImageIcon imagen3=new ImageIcon(btnRedondo.class.getResource("/Recursos/radiconD.png"));
    	ImageIcon imagen4=new ImageIcon(btnRedondo.class.getResource("/Recursos/radiconDS.png"));
    	setIcon(imagen);
    	setSelectedIcon(imagen2);
    	setDisabledIcon(imagen3);
    	setDisabledSelectedIcon(imagen4);
    	
    	this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (clrRadioButton.this.isSelected()) {
					clrRadioButton.this.setForeground(Colores.clrPrincipal);
				} else {
						clrRadioButton.this.setForeground(Colores.clrTextoInactivo);	
						
				}
				
			}
		});
		
	}

	public void reiniciar(){
		this.setSelected(false);
		this.setForeground(Colores.clrTextoInactivo);
	}
}
