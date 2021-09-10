package interfaces_Modificadas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.UIManager;


public class clrCheckBox extends JCheckBox {

	public clrCheckBox(String label) {
		super();
		this.setText(label);
		this.setBackground(Colores.clrFondo);
		this.setFont(Colores.fuenteNormal);
		clrCheckBox.this.setForeground(Colores.clrTextoInactivo);
		this.setBorderPainted(false);
		ImageIcon imagen=new ImageIcon(btnRedondo.class.getResource("/Recursos/chkiconA.png"));
    	ImageIcon imagen2=new ImageIcon(btnRedondo.class.getResource("/Recursos/chkiconS.png"));
    	ImageIcon imagen3=new ImageIcon(btnRedondo.class.getResource("/Recursos/chkiconD.png"));
    	ImageIcon imagen4=new ImageIcon(btnRedondo.class.getResource("/Recursos/chkiconDS.png"));
    	setIcon(imagen);
    	setSelectedIcon(imagen2);
    	setDisabledIcon(imagen3);
    	setDisabledSelectedIcon(imagen4);
    	
    	this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (clrCheckBox.this.isSelected()) {
					clrCheckBox.this.setForeground(Colores.clrPrincipal);
				}else{
					clrCheckBox.this.setForeground(Colores.clrTextoInactivo);
				}
			}
		});
		UIManager.put("CheckBox.border", Colores.clrPrincipal);

	}

	public void reiniciar(){
		this.setSelected(false);
		this.setForeground(Colores.clrTextoInactivo);
	}
	
	
}
