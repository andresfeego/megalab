package interfaces_Modificadas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;

import metodos.ComprobarConexion;

import org.omg.CORBA.Bounds;

import auxiliares.Usuario;
import conexion.conexion;

public class circuloConexion extends JButton implements MouseListener {

	private int ancho;
	private int alto;
	private String label;
	private int anchoString;
	private Rectangle rec;
	private Color colorBoton = Color.RED;
	private final Border ColorBorder = javax.swing.BorderFactory.createLineBorder(Colores.clrPrincipal);

	public circuloConexion(Rectangle bounds) {

		super();
		this.rec = bounds;
		this.setBounds(bounds);
		this.label = "Comprobando conexión";
		colorBoton=Colores.clrSecundario;
		this.alto = 20;
		this.ancho = 20;

		Font font = Colores.fuenteNormal;
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		FontMetrics fm = img.getGraphics().getFontMetrics(font);
		this.anchoString = fm.stringWidth(label);
		// this.setIcon(iconoEscala);
		this.setHorizontalAlignment(SwingConstants.LEFT);

		this.setBounds(0, 0, anchoString + ancho, alto);
		this.setFont(Colores.fuenteBtn);
		this.setPreferredSize(new Dimension(anchoString + ancho, alto));
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		setBackground(Colores.clrPrincipal);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		setEnabled(false);
		this.repaint();

		ComprobarConexion CC = new ComprobarConexion(this);
		CC.start();

	}

	@Override
	protected void paintBorder(Graphics g) {

		Graphics2D g3 = (Graphics2D) g.create();
		g3.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g3.setPaint(colorBoton);// aqui color para botones
								// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
		g3.fill(new Ellipse2D.Double(2, 2, ancho - 7, alto - 7));
		g3.setColor(Colores.clrFondo);
		g3.drawOval(2, 2, ancho - 8, alto - 8);
		// g3.setColor(Color.green);
		g3.setStroke(new BasicStroke(7f));

		g3.setColor(new Color(255, 255, 255, 50));
		g3.drawOval(2, 2, ancho - 7, alto - 7);

		g3.dispose();

		Graphics2D gi = (Graphics2D) g.create();
		gi.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		gi.dispose();

		Graphics2D g4 = (Graphics2D) g.create();
		g4.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g4.setPaint(Colores.clrTextoInactivo);
		g4.setStroke(new BasicStroke(7f));
		g4.drawString(label, ancho, (alto / 2) + 5);
		g4.dispose();

	}

	public Color getColorBoton() {
		return colorBoton;
	}

	public void setColorBoton(Color colorBoton) {
		this.colorBoton = colorBoton;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void escucharconexion() {

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}