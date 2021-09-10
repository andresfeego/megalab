package interfaces_Modificadas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
import java.awt.geom.Ellipse2D;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class ClrCuadroDeTexto extends JTextField {
	private final Border ColorBorder = javax.swing.BorderFactory.createLineBorder(Colores.clrTextoInactivo);
	private String label;
	private ClrCuadroDeTexto cdt;
	private boolean tipo = false;
	private int obligatorio = 0;
	private int limite;
	private boolean soloNumeros;

	public ClrCuadroDeTexto(int limite,boolean soloNumeros,final String Label,int obligatorio) {
		super();
		this.label = Label;
		cdt = this;
		this.limite=limite;
		this.soloNumeros=soloNumeros;
		limitar(limite);
		
		this.setToolTipText(label);
		if (soloNumeros) {
			soloNumeros();
		}
		
		
		this.obligatorio=obligatorio;
		setBorder(ColorBorder);
		
		this.setFont(Colores.fuenteNormal);
		setBackground(Colores.clrFondo);
		setText(Label);
		setForeground(Colores.clrTextoInactivo);
		setDisabledTextColor(Colores.clrTextoInactivo);

		this.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (getTextt().equals(label)) {
					setTextt("");
					setForegroundd(Color.BLACK);
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

	}

	public ClrCuadroDeTexto(int limite,boolean soloNumeros,final String Label) {
		super();
		this.label = Label;
		this.obligatorio=obligatorio;
		cdt = this;
		this.limite=limite;
		this.soloNumeros=soloNumeros;
		limitar(limite);
		this.setToolTipText(label);
		if (soloNumeros) {
			soloNumeros();
		}
		
		setBorder(ColorBorder);
		
		this.setFont(Colores.fuenteNormal);
		setBackground(Colores.clrFondo);
		setText(Label);
		setForeground(Colores.clrTextoInactivo);
		setDisabledTextColor(Colores.clrTextoInactivo);

		this.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (getTextt().equals(label)) {
					setTextt("");
					setForegroundd(Color.BLACK);
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

	}
	
	public ClrCuadroDeTexto(int limite,boolean soloNumeros,final String Label, boolean tipo,int obligatorio) {
		super();
		this.label = Label;
		this.obligatorio=obligatorio;
		cdt = this;
		this.limite=limite;
		this.setToolTipText(label);
		this.soloNumeros=soloNumeros;
		limitar(limite);
		if (soloNumeros) {
			soloNumeros();
		}
		
		setBorder(ColorBorder);
		
		this.setFont(Colores.fuenteNormal);
		setBackground(Colores.clrFondo);
		setText(Label);
		setForeground(Colores.clrTextoInactivo);
		setDisabledTextColor(Colores.clrTextoInactivo);
		this.tipo = tipo;

		this.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (getTextt().equals(label)) {
					setTextt("");
					setForegroundd(Color.BLACK);
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

	}
	
	public ClrCuadroDeTexto(int limite,boolean soloNumeros,final String Label, boolean tipo) {
		super();
		this.label = Label;
		this.obligatorio=obligatorio;
		cdt = this;
		this.limite=limite;
		this.soloNumeros=soloNumeros;
		limitar(limite);
		
		this.setToolTipText(label);
		if (soloNumeros) {
			soloNumeros();
		}
		
		setBorder(ColorBorder);
		
		this.setFont(Colores.fuenteNormal);
		setBackground(Colores.clrFondo);
		setText(Label);
		setForeground(Colores.clrTextoInactivo);
		setDisabledTextColor(Colores.clrTextoInactivo);
		this.tipo = tipo;

		this.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (getTextt().equals(label)) {
					setTextt("");
					setForegroundd(Color.BLACK);
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

	}

	@Override
	protected void paintBorder(Graphics g) {

		if (tipo) {

			Graphics2D g3 = (Graphics2D) g.create();
			g3.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			g3.setPaint(Colores.clrSecundario);// aqui color para botones
												// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
			g3.fill(new Ellipse2D.Double(this.getWidth() - 23, 2, 21, 21));
			g3.setColor(Colores.clrFondo);
			g3.drawOval(this.getWidth() - 23, 2, 21, 21);
			String ruta = "/Recursos/buscar.png";
			if (Colores.clrIconosNegros) {
				ruta = "/Recursos/buscarN.png";
			}
			ImageIcon imagen = new ImageIcon(btnRedondo.class.getResource(ruta));
			ImageIcon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(21 / 1, -1, java.awt.Image.SCALE_DEFAULT));
			Image icono = iconoEscala.getImage();

			Graphics2D gi = (Graphics2D) g.create();
			gi.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			gi.drawImage(icono, this.getWidth() - 28 + 25 / 4, 25 / 6, icono.getWidth(null), icono.getHeight(null), null);

			gi.dispose();

			g3.dispose();

		}
		
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
        }
		super.paintBorder(g);
	}

	public String getTextt() {
		return this.getText();
	}

	public void setTextt(String string) {
		this.setText(string);
	}

	public void setForegroundd(Color clrTextoInactivo) {
		this.setForeground(clrTextoInactivo);
	}

	public void soloNumeros() {
		cdt.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});

	}

	public void borrar(){
		this.setText( this.getText().substring(0,this.getText().length()-1));
	}
	
	public void limitar(final int limite) {

		cdt.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e)

			{
				if (cdt.getText().length() == limite){

					e.consume();
				}
				
				char c = e.getKeyChar();
				if (c == '|') {
					getToolkit().beep();
					e.consume();
				}
				
				
			}

			public void keyPressed(KeyEvent arg0) {
			}

			public void keyReleased(KeyEvent arg0) {
			}
		});

	}

	public void reiniciar() {
		this.setText(label);
		this.setForegroundd(Colores.clrTextoInactivo);
	}

	@Override
	public void setBounds(int x, int y, int width, int height) {
		

		super.setBounds(x, y, width, 25);
		this.repaint();
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	

}