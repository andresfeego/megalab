package interfaces_Modificadas;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.Border;
public class clrtextpane extends JTextArea {
    private final Border ColorBorder = javax.swing.BorderFactory.createLineBorder(Colores.clrPrincipal);
    private String label;
    private clrtextpane cdt;
    private int obligatorio=0;
    private int limite;
    private boolean soloNumeros;
    
    public clrtextpane(int limite,boolean soloNumeros,String Label,int obligatorio) {
        super();
        this.label=Label;
        this.obligatorio=obligatorio;
        this.limite=limite;
        this.soloNumeros=soloNumeros;
		this.setToolTipText(label);

        setBorder(ColorBorder);
        cdt=this;
		this.setFont(Colores.fuenteNormal);
        setBackground(Colores.clrFondo);
        setText(Label);
        setForeground(Colores.clrTextoInactivo);
        setLineWrap(true);
        if (soloNumeros) {
			soloNumeros();
		}
        limitar(limite);
        
    

    cdt.addFocusListener(new FocusAdapter() {
    	@Override
		public void focusGained(FocusEvent arg0) {
			if (getTextt().equals(label)) {
				setTextt("");
				setForegroundd(Color.black);
			}
			
		}
		@Override
		public void focusLost(FocusEvent arg0) {
			if (getTextt().equals("")) {
				setForegroundd(Colores.clrTextoInactivo);
				setTextt(label);
			}
			

		}
	
	});
    
    /*Graphics2D g3 = (Graphics2D)g.create();
        g3.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g3.setPaint(new Color(Colores.clrBordes.getRed(), Colores.clrBordes.getGreen(), Colores.clrBordes.getBlue()));
        g3.fill(new Ellipse2D.Double(2, 2, ancho-7, alto-7));
        g3.setColor(new Color(100, 100, 100));
       g3.drawOval(2, 2, ancho-8, alto-8);
       g3.setColor(new Color(235,166,38));
       g3.setStroke(new BasicStroke(7f));
     
       
       g3.setColor(new Color(255, 255, 255, 50));
       g3.drawOval(2, 2, ancho-7, alto-7); 
      
        g3.dispose();   */
    }
    
    
    
    
	@Override
	protected void paintBorder(Graphics g) {
	if (obligatorio!=0) {
			
			Graphics2D g4 = (Graphics2D)g.create();
        g4.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g4.setStroke(new BasicStroke(7f));
        
        g4.setColor(Color.WHITE);
        int[] x=new int[3];
        int[] y=new int[3];
        int n;
        x[0]=this.getWidth()-10; x[1]=this.getWidth(); x[2]=this.getWidth();
        y[0]=0; y[1]=0; y[2]=10;
        n = 3;

        g4.fillPolygon(x, y, n);        
        
        g4.setColor(Colores.clrAlertaCamarada);
        g4.fillPolygon(x, y, n);        
           
        
        g4.dispose();   
        }		super.paintBorder(g);
	}




	public String getTextt() {
		return this.getText();
	}
	public void setTextt(String string) {
		this.setText(string);			
	}
	public void setForegroundd(Color cl) {
		this.setForeground(cl);			
	}
    
	public void soloNumeros(){
		cdt.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
			char c = e.getKeyChar();
			if (!((Character.isDigit(c) ||
			(c == KeyEvent.VK_BACK_SPACE) ||
			(c == KeyEvent.VK_DELETE)))){
			getToolkit().beep();
			e.consume();
			}
			}
			});

	}

	public void limitar(final int limite) {

		cdt.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e)

			{
				if (cdt.getText().length() == limite)

					e.consume();
			}

			public void keyPressed(KeyEvent arg0) {
			}

			public void keyReleased(KeyEvent arg0) {
			}
		});

	}


	public String getLabel() {
		return label;
	}



	public void setLabel(String label) {
		this.label = label;
	}
	
	public void reiniciar(){
		this.setText(label);
		this.setForeground(Colores.clrTextoInactivo);
	}
	
   
}