package Interfaces;

import interfaces_Modificadas.ClrCuadroDeTexto;
import interfaces_Modificadas.Colores;
import interfaces_Modificadas.btnRedondo;
import interfaces_Modificadas.clrBarraMenu;
import interfaces_Modificadas.clrFrameBordes;
import interfaces_Modificadas.clrLabel;
import interfaces_Modificadas.clrPanel;
import interfaces_Modificadas.clrPanelBordes;
import interfaces_Modificadas.clrPassword;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;

import java.awt.Component;

import javax.swing.SwingConstants;

import otrosImpresos.CuentaCobroXEmpresa;
import otrosImpresos.ImprimirCotizacion;
import otrosImpresos.imprimirRecepcion;
import otrosImpresos.imprimirReporte;
import visor.VisorPDF;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import auxiliares.Usuario;
import conexion.conexion;
import conexion.conexionBusqueda;
import conexion.conexionComprobar;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Iniciar_Sesion extends JDialog {

	private clrPanelBordes contentPane;
	private ClrCuadroDeTexto txtUsuario;
	private clrPassword passContrase;
	public clrLabel lblincorrectos;
	public btnRedondo btnAceptar;
	private Principal principal;
	private Iniciar_Sesion esta;
	/**
	 * Create the frame.
	 */
	public Iniciar_Sesion(Principal pri ) {
		
		super(pri,true);
		this.principal=pri;
		this.esta=this;
		
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 378, 250);
		/*  javax.swing.plaf.InternalFrameUI ifu= this.getUI();
		  ((javax.swing.plaf.basic.BasicInternalFrameUI)ifu).setNorthPane(null);  */
		  
		  //___________________________________________________________________________________________
		  
		 this.setUndecorated(true);
		 this.setLocationRelativeTo(getParent()); 
		 
		contentPane = new clrPanelBordes(true);
		//setContentPane(contentPane);
		contentPane.setLayout(null);
		clrLabel lblinicio = new clrLabel("Inicio de sesion",2,true);
		lblinicio.setFocusCycleRoot(true);
		lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblinicio.setBounds(10, 11, 358, 28);
		
		contentPane.add(lblinicio);
		
		
		btnAceptar = new btnRedondo("Aceptar",new Rectangle(48, 172,121,50),1);
		btnAceptar.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {

					
					if (txtUsuario.getText().equals("Usuario")) {
						lblincorrectos.setText("No has ingresado el usuario");
					} else {
						if (passContrase.getText().equals("123456789")) {
							lblincorrectos.setText("No has ingresado la contraseña");

						} else {
							iniciar(txtUsuario.getText(),passContrase.getText());

						}
						

					}
				
				} 
				
if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
	principal.crearMenu(null);
	conexion.getInstance().closeConnection();
	dispose();
} 
			}
		
		});
		
		
		btnAceptar.setSelected(true);
		btnAceptar.setBounds(56, 172,121,50);
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
				if (txtUsuario.getText().equals("Usuario")) {
					lblincorrectos.setText("No has ingresado el usuario");
				} else {
					if (passContrase.getText().equals("123456789")) {
						lblincorrectos.setText("No has ingresado la contraseña");

					} else {
						iniciar(txtUsuario.getText(),passContrase.getText());

					}
					

				}
			}

			
		});
		contentPane.add(btnAceptar);
		
		txtUsuario = new ClrCuadroDeTexto(50,false,"Usuario");
		txtUsuario.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {

					
					if (txtUsuario.getText().equals("Usuario")) {
						lblincorrectos.setText("No has ingresado el usuario");
					} else {
						if (passContrase.getText().equals("123456789")) {
							lblincorrectos.setText("No has ingresado la contraseña");

						} else {
							iniciar(txtUsuario.getText(),passContrase.getText());

						}
						

					}
				
				} 
				if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
					principal.crearMenu(null);
					conexion.getInstance().closeConnection();
					dispose();
				} 
			}
		
		});
		txtUsuario.transferFocus();
		txtUsuario.setBounds(48, 76, 274, 28);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		passContrase = new clrPassword(50);
		passContrase.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {

					
					if (txtUsuario.getText().equals("Usuario")) {
						lblincorrectos.setText("No has ingresado el usuario");
					} else {
						if (passContrase.getText().equals("123456789")) {
							lblincorrectos.setText("No has ingresado la contraseña");

						} else {
							iniciar(txtUsuario.getText(),passContrase.getText());

						}
						

					}
				
				} 
				if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
					conexion.getInstance().closeConnection();
					principal.crearMenu(null);
					dispose();
					
				} 
			}
		
		});
		passContrase.setFocusCycleRoot(true);
		passContrase.setBounds(48, 115, 274, 28);
		contentPane.add(passContrase);
		
		
		
		btnRedondo btnCancelar = new btnRedondo("Cancelar",new Rectangle(237, 172,121,50),2);
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				conexion.getInstance().closeConnection();
				//principal.crearMenu(null);
				//new CuentaCobroXEmpresa(conexionBusqueda.getInstance().empresaXid(0+""),0, conexionBusqueda.getInstance().facturasDabonoXEmpresa(0+""), 10000, false, true,conexionBusqueda.getInstance().bacteriologoXid(544+""));
				dispose();
		
			}
		});
		btnCancelar.setBounds(192, 172, 129, 50);
		contentPane.add(btnCancelar);
		setContentPane(contentPane);
		
		
		lblincorrectos = new clrLabel("", 1);
		lblincorrectos.setForeground(Colores.clrAlertaCamarada);
		lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
		lblincorrectos.setAlignmentX(0.5f);
		lblincorrectos.setBounds(10, 39, 358, 28);
		contentPane.add(lblincorrectos);
		
		
		
		SwingUtilities.invokeLater( new Runnable() 
        { 
        public void run() 
            { 
            btnAceptar.requestFocusInWindow(); 
        } 
    }); 
		
		
		
	
	}
	
 public void iniciar(String usu,String pass) {
		conexion.getInstance().closeConnection();
		conexion cn= conexion.getInstance();
		if (conexionComprobar.comprobarConexion()) {
					
			Usuario usua=null;
			usua=cn.acceso(usu, pass);
			if (usua!=null) {
				if (!conexion.getInstance().tieneAcceso(usu)) {
					lblincorrectos.setText("El usuario no tiene acceso al sistema");

				} else {
	principal.crearMenu(usua);
				dispose();
				}
			
			} else {
				lblincorrectos.setText("Datos incorrectos");
	
			}
		}
		else{
			lblincorrectos.setText("No es posible conectar con la base de datos");

		}
	}

public Iniciar_Sesion getEsta() {
	return esta;
}

public void setEsta(Iniciar_Sesion esta) {
	this.esta = esta;
}
 

	
	
}
