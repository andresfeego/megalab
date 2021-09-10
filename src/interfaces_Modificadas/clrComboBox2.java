package interfaces_Modificadas;

import interfaces_Modificadas.clrScrollBar.MyScrollbarUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;


public class clrComboBox2 extends JComboBox{
	private clrComboBox2 esta=this;
		
	public clrComboBox2(String[] items) {
		llenar(items);
		escucha();
		this.setFont(Colores.fuentePequena);
		this.setForeground(Colores.clrTextoInactivo);
		this.setBackground(Colores.clrFondo);
		UIManager.put("RadioButton.Background", new ColorUIResource(Color.magenta));
		JTextField txt=(JTextField) getEditor().getEditorComponent();
		setUI(CustomUI.createUI(this));
		
		Object obejct = this.getUI().getAccessibleChild(this, 0);
        Component co =  ((Container) obejct).getComponent(0) ;
                if( co instanceof JScrollPane){
                      JScrollPane pane = (JScrollPane) co;        

                      	////   aqui es donde pongo la scrollbar perzonalizada para loa combobox 
                      
                      JScrollBar sb= pane.getVerticalScrollBar();
              		sb.setPreferredSize(new Dimension(15, Integer.MAX_VALUE));
              	    sb.setUI(new MyScrollbarUI());
              	    
              	    
                }
}

	public clrComboBox2() {
		super();
		llenar(new String[]{});
		escucha();
		this.setFont(Colores.fuentePequena);
		this.setBackground(Colores.clrFondo);
		this.setForeground(Colores.clrTextoInactivo);
		UIManager.put("RadioButton.Background", new ColorUIResource(Color.magenta));
		JTextField txt=(JTextField) getEditor().getEditorComponent();
		setUI(CustomUI.createUI(this));
	}
	
	public void llenar(String [] lista) {
		
		this.removeAllItems();
		
		if (lista.length==0) {
			this.addItem("Sin datos");
		}else{
			
			for (int i = 0; i < lista.length; i++) {
			
				this.addItem(lista[i]);
				
			}
		}
	}
	
	private void escucha() {
		
		this.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				if (esta.getSelectedIndex()!=0) {
					esta.setForeground(Color.BLACK);
				} else{
					esta.setForeground(Colores.clrTextoInactivo);
				}
			}
		});
	}
	
	public void reiniciar(){
		this.setSelectedIndex(0);
		this.setForeground(Colores.clrTextoInactivo);
	}
}
