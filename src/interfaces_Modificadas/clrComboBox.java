package interfaces_Modificadas;

import interfaces_Modificadas.clrScrollBar.MyScrollbarUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicScrollBarUI;


public class clrComboBox extends JComboBox{
	private clrComboBox esta=this;
	private static int obligatorio=0;
		
	public clrComboBox(String[] items,int obligatorio) {
		llenar(items);
		escucha();
		this.obligatorio=obligatorio;
		this.setFont(Colores.fuenteNormal);
		this.setForeground(Colores.clrTextoInactivo);
		this.setBackground(Colores.clrFondo);
		
		if (items.length>0) {
			this.setToolTipText(items[0]);

		} else {
			this.setToolTipText("Sin datos");

		}
		
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

	public clrComboBox(int obligatorio) {
		super();
		llenar(new String[]{});
		escucha();
		this.setFont(Colores.fuenteNormal);
		this.setBackground(Colores.clrFondo);
		this.setForeground(Colores.clrTextoInactivo);
		UIManager.put("RadioButton.Background", new ColorUIResource(Color.magenta));
		JTextField txt=(JTextField) getEditor().getEditorComponent();
		setUI(CustomUI.createUI(this));
	}
	
	
	
	

	
	

	public void llenar(String [] lista) {
		
		this.removeAllItems();
		
		if (lista==null || lista.length==0) {
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
	
	 public static class CustomUI extends BasicComboBoxUI{
	     
	     private ImageIcon espacio =  new ImageIcon(getClass().getResource("/Recursos/espacio.png"));
	    private Rectangle bouns;
	     
	     public static ComboBoxUI createUI(JComponent c) {
	    	 
	         return new CustomUI();
	     }
	 
	    @Override 
	     protected JButton createArrowButton() {        
	      if (obligatorio!=0) {
	    	   BasicArrowButton basicArrowButton = new BasicArrowButton(BasicArrowButton.SOUTH,//Direccion de la flecha
		                 Color.WHITE, //Color de fondo
		                Colores.clrAlertaCamarada,//sombra
		                 Colores.clrAlertaCamarada,//darkShadow
		                 Color.WHITE //highlight
		                 );       
	    	   
	    	   //se quita el efecto 3d del boton, sombra y darkShadow no se aplican 
	         basicArrowButton.setBorder(BorderFactory.createLineBorder(Colores.clrAlertaCamarada,2));
	         basicArrowButton.setContentAreaFilled(false);        
	         return basicArrowButton;
		} else {
			   BasicArrowButton basicArrowButton = new BasicArrowButton(BasicArrowButton.SOUTH,//Direccion de la flecha
		                 Color.WHITE, //Color de fondo
		                Colores.clrPrincipal,//sombra
		                 Colores.clrPrincipal,//darkShadow
		                 Color.WHITE //highlight
		                 );         
			   
			   //se quita el efecto 3d del boton, sombra y darkShadow no se aplican 
		         basicArrowButton.setBorder(BorderFactory.createLineBorder(Colores.clrPrincipal,2));
		         basicArrowButton.setContentAreaFilled(false);        
		         return basicArrowButton;
		}
	       //FALTA
	     }        
	 
	     //Se puede usar un JButton para usar un icono personalizado en lugar del arrow
	     /* 
	45  @Override 
	46  protected JButton createArrowButton() { 
	47  JButton button = new JButton(); 
	48  //se quita el efecto 3d del boton, sombra y darkShadow no se aplican 
	49  button.setText("");
	50  button.setBorder(BorderFactory.createLineBorder(red,2));
	51  button.setContentAreaFilled(false);
	52  button.setIcon( new ImageIcon(getClass().getResource("/org/bolivia/res/estrella.png")) );
	53  return button;
	54  } 
	55  */
	    
	    
	     
	     @Override
	     public void paintCurrentValueBackground(Graphics g,
	                                Rectangle bounds,
	                                boolean hasFocus)
	     {
	         g.setColor( Colores.clrSecundario );       
	         Graphics2D g2 = (Graphics2D)g.create();
	         g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	         g2.setStroke(new BasicStroke(2f));
	         g2.setColor(Colores.clrTextoInactivo);
	         
	         g2.drawRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, 0, 0);
	         this.bouns=bounds;
	     }
	       
	    //Pinta los items
	     @Override
	     protected ListCellRenderer createRenderer()
	     {
	    	 
	         return new DefaultListCellRenderer() {      
	             
	         @Override
	         public Component getListCellRendererComponent(JList list,Object value,int index,
	           boolean isSelected,boolean cellHasFocus) {
	       
	         super.getListCellRendererComponent(list,value,index,isSelected,cellHasFocus);
	         list.setSelectionBackground(Colores.clrTextoInactivo);
	                if (isSelected)
	         {
	             setBackground( Colores.clrSecundario );
	             setForeground(Color.WHITE);
	         }
	         else
	         {
	             setBackground( Color.WHITE );            
	             setForeground( new Color(70,70,70));
	         }
	         if (index!=-1) {          
	           setIcon( espacio );          
	         }
	         return this;
	       }
	     };
	     }
	 }
}
