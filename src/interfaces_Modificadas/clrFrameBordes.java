package interfaces_Modificadas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.ObjectInputStream.GetField;

import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class clrFrameBordes extends JFrame {

	private final Border ColorBorder = javax.swing.BorderFactory.createLineBorder(Colores.clrPrincipal);
	private Dimension tamanoPantalla;
	private JLabel barratitulo;

	public clrFrameBordes() {
		super();
		this.setUndecorated(true);
		getRootPane().setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, Color.gray));
		getRootPane().setBackground(Colores.clrFondo);
		Toolkit tk = Toolkit.getDefaultToolkit();
		tamanoPantalla = tk.getScreenSize();
		setBounds(0, 0, GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width, GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);

		this.getContentPane().setForeground(Color.BLACK);
		this.setFont(new Font("Waukegan LDO Extended", Font.BOLD, 18));
		this.getContentPane().setBackground(Colores.clrFondo);

		Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Recursos/iconMegaLab.png"));
		setIconImage(icon);

		BorderLayout bl = new BorderLayout();

		this.getContentPane().setLayout(bl);

		barratitulo = new JLabel("yo estoy aqui ");
		this.getContentPane().add(barratitulo, bl.NORTH);

		// agregando imagen

		// UIManager.put("Label.background", Color.black);

		// this.getRootPane().setBorder(ColorBorder);
	}

	public void setTitulo(String titulo) {

	}

}
