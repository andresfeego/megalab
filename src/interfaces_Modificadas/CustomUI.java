package interfaces_Modificadas;

 import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
 import java.awt.Graphics;
import java.awt.Graphics2D;
 import java.awt.Rectangle;
import java.awt.RenderingHints;

 import javax.swing.BorderFactory;
 import javax.swing.DefaultListCellRenderer;
 import javax.swing.ImageIcon;
 import javax.swing.JButton;
 import javax.swing.JComponent;
 import javax.swing.JList;
 import javax.swing.ListCellRenderer;
 import javax.swing.plaf.ComboBoxUI;
 import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
 /**
  * @web http://www.jc-mouse.net/
  * @author Mouse
  */
 public class CustomUI extends BasicComboBoxUI{
     
     private ImageIcon espacio =  new ImageIcon(getClass().getResource("/Recursos/espacio.png"));
    private Rectangle bouns;
     
     public static ComboBoxUI createUI(JComponent c) {
    	 
         return new CustomUI();
     }
 
    @Override 
     protected JButton createArrowButton() {        
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