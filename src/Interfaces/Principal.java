package Interfaces;

import interfaces_Modificadas.ClrCuadroDeTexto;
import interfaces_Modificadas.Colores;
import interfaces_Modificadas.btnRedondo;
import interfaces_Modificadas.circuloConexion;
import interfaces_Modificadas.circuloUsuario;
import interfaces_Modificadas.clrBarraMenu;
import interfaces_Modificadas.clrDeskPanel;
import interfaces_Modificadas.clrFrameBordes;
import interfaces_Modificadas.clrMenu;
import interfaces_Modificadas.clrMenu2;
import interfaces_Modificadas.clrMenuItem;
import interfaces_Modificadas.clrMenuItemIndicativo;
import interfaces_Modificadas.clrPanel;
import interfaces_Modificadas.clrSeparador;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.Box;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;

import InterfacesListados.OpCuentaXEmpresa;
import InterfacesListados.OpCuentaXPaciente;
import InterfacesListados.OpResXFecha;
import InterfacesListados.OpResXRecepcion;
import InterfacesListados.OpResXSeccion;
import InterfacesListados.OpRipsXCuentaCobro;
import ModuloDeReporte.OpReporte7600;
import ago.beans.CirclePanel;
import auxiliares.Usuario;

import javax.swing.JLabel;

import sun.applet.Main;
import visor.VisorPDF;
import metodos.CerrarSesionInactivo;
import metodos.ObtenerFecha;
import chrriis.dj.nativeswing.NativeSwing;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import conexion.conexion;
import conexion.conexionBusqueda;

import javax.swing.SwingConstants;

import otrosImpresos.imprimirRecepcion;

public class Principal extends clrFrameBordes {

	private clrDeskPanel contentPane;
	private clrBarraMenu menuBar;
	private Usuario usuario;
	private JLabel lblInfoAcciones;
	private JLabel lblInfoErrores;
	private static Principal principal;
	private circuloUsuario cirUsuario;
	private circuloConexion cirConexion;
	private Image logoMegaLab = null;
	private ImageIcon icon;
	private boolean reportandoErrores=false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
			
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final Principal frame = new Principal();
					frame.setVisible(true);
			
					
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		  

		construirVentana();
		this.addWindowStateListener(new WindowStateListener() {

			@Override
			public void windowStateChanged(WindowEvent e) {
				crearMenu(usuario);
			System.out.println("state change ventana ");
			}
		});
		

	}

	public void iniciarSesion() {
		Iniciar_Sesion is = new Iniciar_Sesion(this);
		is.setVisible(true);
		
			
		
		}

	public void crearMenu(Usuario usuar) {
	

		this.usuario = usuar;
		if (usuario == null) {

			menuBar.removeAll();
			contentPane.removeAll();
			clrMenu clrMenu_ = new clrMenu("Inicio");
			menuBar.add(clrMenu_);

			clrSeparador clrSeparador__9 = new clrSeparador();
			clrMenu_.add(clrSeparador__9);

			clrMenuItemIndicativo clrMenuItemIndicativo__4 = new clrMenuItemIndicativo("Usuario",1);
			clrMenu_.add(clrMenuItemIndicativo__4);

			clrSeparador clrSeparador__10 = new clrSeparador();
			clrMenu_.add(clrSeparador__10);

			clrMenuItem clrMenuItem__10 = new clrMenuItem("Iniciar Sesion");
			clrMenu_.add(clrMenuItem__10);
			clrMenuItem__10.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					iniciarSesion();

				}
			});

			clrMenuItem clrMenuItem__13 = new clrMenuItem("Salir");
			clrMenuItem__13.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					registrarAccion("Salida de sistema");
					File directorio=new File("C:/6342522/temp/");
					if (directorio.exists()) {
						directorio.delete();
					} 
					System.exit(DISPOSE_ON_CLOSE);
				}
			});
			clrMenu_.add(clrMenuItem__13);

			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(null);
			setContentPane(contentPane);
			contentPane.updateUI();
			menuBar.updateUI();

			cirConexion = new circuloConexion(new Rectangle(0, 0, 0, 0));
			cirConexion.setBounds(25, this.getHeight() - 80, 250, 50);
			contentPane.add(cirConexion);
			
			String ruta="/Recursos/DB.png";
			icon=new ImageIcon(Principal.class.getResource(ruta));
			
			lblInfoErrores = new JLabel("");
			
			lblInfoErrores.setIcon(icon);
			lblInfoErrores.setBounds(cirConexion.getX()-(icon.getIconWidth()/4), cirConexion.getY() +50, this.getWidth(), icon.getIconHeight());
			lblInfoErrores.setForeground(Colores.clrTextoInactivo);
			contentPane.add(lblInfoErrores);

		} else {
			contentPane.removeAll();

			cirUsuario = new circuloUsuario(usuario, new Rectangle(0, 0, 0, 0));
			cirUsuario.setBounds(10, this.getHeight() - 110, 500, 50);
			contentPane.add(cirUsuario);

			// / aqui boyotn

			lblInfoAcciones = new JLabel("");
			lblInfoAcciones.setBounds(cirUsuario.getX() + 50, cirUsuario.getY() + 30, this.getWidth(), 16);
			lblInfoAcciones.setForeground(Colores.clrTextoInactivo);
			contentPane.add(lblInfoAcciones);
			
			String ruta="/Recursos/DB.png";
			icon=new ImageIcon(Principal.class.getResource(ruta));
			
			lblInfoErrores = new JLabel("");
			
			lblInfoErrores.setIcon(icon);
			lblInfoErrores.setBounds(cirConexion.getX()-(icon.getIconWidth()/4), lblInfoAcciones.getY() +50, this.getWidth(), icon.getIconHeight());
			lblInfoErrores.setForeground(Colores.clrTextoInactivo);
			contentPane.add(lblInfoErrores);
			
			this.registrarAccion("Se ha iniciado sesion");

			

			cirConexion = new circuloConexion(new Rectangle(0, 0, 0, 0));
			cirConexion.setBounds(25, this.getHeight() - 135, 250, 50);
			contentPane.add(cirConexion);

			menuBar.removeAll();
			String acceso = usuario.getAcceso();
			int menu = 0;
			clrMenu mnAdmin = null;
			clrMenu mnInfo = null;
			clrMenu mnContab = null;
			clrMenu mnCustom = null;
			do {
				if (acceso.charAt(menu) == '1') {
					switch (menu) {
					case 0:
						clrMenu clrMenu_ = new clrMenu("Inicio");
						menuBar.add(clrMenu_);

						clrSeparador clrSeparador__9 = new clrSeparador();
						clrMenu_.add(clrSeparador__9);

						clrMenuItemIndicativo clrMenuItemIndicativo__4 = new clrMenuItemIndicativo("Usuario",1);
						clrMenu_.add(clrMenuItemIndicativo__4);

						clrSeparador clrSeparador__10 = new clrSeparador();
						clrMenu_.add(clrSeparador__10);

						clrMenuItem clrMenuItem__10 = new clrMenuItem("Opciones de usuario");
						clrMenu_.add(clrMenuItem__10);

						clrMenuItem__10.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								new OpUsuario(principal, usuario, "Modificar");
								
							}
						});

						clrMenuItem clrMenuItem__11 = new clrMenuItem("Cambiar contrase\u00F1a");
						clrMenu_.add(clrMenuItem__11);

						clrMenuItem__11.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								cambiarpass();

							}
						});

						clrMenuItem clrMenuItem__12 = new clrMenuItem("Cerrar sesion");
						clrMenu_.add(clrMenuItem__12);

						clrMenuItem__12.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {

								cerrarSesion();
							}
						});

						clrSeparador clrSeparador__17 = new clrSeparador();
						clrMenu_.add(clrSeparador__17);

						clrMenuItem clrMenuItem__13 = new clrMenuItem("Salir");
						clrMenuItem__13.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseReleased(MouseEvent e) {
								registrarAccion("Salida de sistema");
								File directorio=new File("C:/6342522/temp/");
								if (directorio.exists()) {
									directorio.delete();
								} 
								System.exit(DISPOSE_ON_CLOSE);
							}
						});
						clrMenu_.add(clrMenuItem__13);

						clrSeparador clrSeparador__18 = new clrSeparador();
						clrMenu_.add(clrSeparador__18);
						menu++;

						break;

					case 1:
						Component horizontalStrut = Box.createHorizontalStrut(15);
						menuBar.add(horizontalStrut);

						clrMenu clrMenu__1 = new clrMenu("Laboratorio");
						menuBar.add(clrMenu__1);

						clrSeparador clrSeparador__13 = new clrSeparador();
						clrMenu__1.add(clrSeparador__13);

						clrMenuItemIndicativo clrMenuItemIndicativo__6 = new clrMenuItemIndicativo("Laboratorio",2);
						clrMenu__1.add(clrMenuItemIndicativo__6);

						clrSeparador clrSeparador__14 = new clrSeparador();
						clrMenu__1.add(clrSeparador__14);

						clrMenuItem clrMenuItem_ = new clrMenuItem("Nueva recepción ");
						clrMenu__1.add(clrMenuItem_);
						clrMenuItem_.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								new OpRecepcion(principal, usuario);

							}
						});

						clrMenuItem clrMenuItem__23 = new clrMenuItem("Reportar resultados");
						clrMenuItem__23.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpReportarResultados(principal, usuario);								
							}
						});
						clrMenu__1.add(clrMenuItem__23);
						
						
						clrMenuItem clrREportarHema = new clrMenuItem("Reportar resultados desde RAYTO RT-7600");
						clrREportarHema.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpReporte7600(principal, usuario);								
							}
						});
						clrMenu__1.add(clrREportarHema);

						clrMenuItem clrMenuItem__24 = new clrMenuItem("Registrar entrega de resultados");
						clrMenu__1.add(clrMenuItem__24);
						clrMenuItem__24.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent arg0) {
							new OpRegistroEntrega(principal, usuario);
								
							}
						});

						clrMenuItem clrMenuItem__2 = new clrMenuItem("Cotización");
						clrMenu__1.add(clrMenuItem__2);
						clrMenuItem__2.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpItemCotizacion(principal, usuario);
								
							}
						});
						
						menu++;

						break;

					case 2:
						if (mnAdmin == null) {
							Component horizontalStrut_1 = Box.createHorizontalStrut(15);
							menuBar.add(horizontalStrut_1);

							mnAdmin = new clrMenu("Administrar");
							menuBar.add(mnAdmin);
						}

						clrSeparador clrSeparador__u = new clrSeparador();
						mnAdmin.add(clrSeparador__u);

						clrMenuItemIndicativo mntmUsuarios = new clrMenuItemIndicativo("Usuarios",1);
						mnAdmin.add(mntmUsuarios);

						clrSeparador separatoru2 = new clrSeparador();
						mnAdmin.add(separatoru2);

						clrMenu2 mntmUsuariosSistema = new clrMenu2("Usuarios del sistema");
						mnAdmin.add(mntmUsuariosSistema);

						clrMenuItem clrMenuItem__15 = new clrMenuItem("Agregar");
						mntmUsuariosSistema.add(clrMenuItem__15);

						clrMenuItem__15.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								new OpUsuario(principal, null, "Agregar");
							}
						});

						if (usuario.getAdmin()==1) {
							clrMenuItem controlAcceso = new clrMenuItem("Control de acceso");
							mntmUsuariosSistema.add(controlAcceso);

							controlAcceso.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									new OpControlAcceso(principal,usuario);
								}
							});
						}
						
						clrMenuItem clrMenuItem__102 = new clrMenuItem("Activar/Desactivar");
						mntmUsuariosSistema.add(clrMenuItem__102);

						clrMenuItem__102.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								new OpUsuario(principal, usuario, "Desactivar");

							}
						});
						
						
						clrMenuItem clrMenuItem__202 = new clrMenuItem("Restaurar contraseña de usuario Web");
						mntmUsuariosSistema.add(clrMenuItem__202);

						clrMenuItem__202.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								new OpUsuarioWEB(principal, usuario);

							}
						});
						
						
						menu++;

						break;
					case 3:
						if (mnAdmin == null) {
							Component horizontalStrut_1 = Box.createHorizontalStrut(15);
							menuBar.add(horizontalStrut_1);

							mnAdmin = new clrMenu("Administrar");
							menuBar.add(mnAdmin);
						}
						clrSeparador clrSeparador__2 = new clrSeparador();
						mnAdmin.add(clrSeparador__2);

						clrMenuItemIndicativo mntmPersonal = new clrMenuItemIndicativo("Personal",3);
						mnAdmin.add(mntmPersonal);

						clrSeparador separator = new clrSeparador();
						mnAdmin.add(separator);

						clrMenu2 mntmPacientes = new clrMenu2("Pacientes");
						mnAdmin.add(mntmPacientes);

						clrMenuItem clrMenuItem__103 = new clrMenuItem("Agregar");
						mntmPacientes.add(clrMenuItem__103);
						clrMenuItem__103.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								new OpPaciente(principal, usuario, "Agregar");
							}
						});

						clrMenuItem clrMenuItem__104 = new clrMenuItem("Modificar");
						clrMenuItem__104.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								new OpPaciente(principal, usuario, "Modificar");

							}
						});
						mntmPacientes.add(clrMenuItem__104);

						clrMenuItem clrMenuItem__105 = new clrMenuItem("Eliminar");
						clrMenuItem__105.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								new OpPaciente(principal, usuario, "Eliminar");

							}
						});
						mntmPacientes.add(clrMenuItem__105);

						clrMenu2 mntmMedicos = new clrMenu2("Médicos");
						mnAdmin.add(mntmMedicos);

						clrMenuItem clrMenuItem__106 = new clrMenuItem("Agregar");
						mntmMedicos.add(clrMenuItem__106);
						clrMenuItem__106.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								
								new OpMedico(principal, usuario, "Agregar");
							}
						});

						clrMenuItem clrMenuItem__107 = new clrMenuItem("Modificar");
						mntmMedicos.add(clrMenuItem__107);
						clrMenuItem__107.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								
								new OpMedico(principal, usuario, "Modificar");
							}
						});

						clrMenuItem clrMenuItem__108 = new clrMenuItem("Eliminar");
						mntmMedicos.add(clrMenuItem__108);
						clrMenuItem__108.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								
								new OpMedico(principal, usuario, "Eliminar");

							}
						});

						clrMenu2 mntmBacteriologos = new clrMenu2("Bacteri\u00F3logos");
						mnAdmin.add(mntmBacteriologos);

						clrMenuItem clrMenuItem__109 = new clrMenuItem("Agregar");
						mntmBacteriologos.add(clrMenuItem__109);
						clrMenuItem__109.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								new OpBacteriologo(principal, usuario, "Agregar");
							}
						});

						clrMenuItem clrMenuItem__110 = new clrMenuItem("Modificar");
						mntmBacteriologos.add(clrMenuItem__110);
						clrMenuItem__110.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								
								new OpBacteriologo(principal, usuario, "Modificar");
							}
						});

						clrMenuItem clrMenuItem__111 = new clrMenuItem("Eliminar");
						mntmBacteriologos.add(clrMenuItem__111);
						clrMenuItem__111.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								
								new OpBacteriologo(principal, usuario, "Eliminar");
							}
						});

						menu++;

						break;
					case 4:
						if (mnAdmin == null) {
							Component horizontalStrut_1 = Box.createHorizontalStrut(15);
							menuBar.add(horizontalStrut_1);

							mnAdmin = new clrMenu("Administrar");
							menuBar.add(mnAdmin);
						}

						clrSeparador clrSeparador__1 = new clrSeparador();
						mnAdmin.add(clrSeparador__1);

						clrMenuItemIndicativo clrMenuItemIndicativo_ = new clrMenuItemIndicativo("Empresas",4);
						mnAdmin.add(clrMenuItemIndicativo_);

						clrSeparador clrSeparador_ = new clrSeparador();
						mnAdmin.add(clrSeparador_);

						clrMenu2 menuItem_1 = new clrMenu2("Empresas");
						mnAdmin.add(menuItem_1);

						clrMenuItem clrMenuItem__112 = new clrMenuItem("Agregar");
						menuItem_1.add(clrMenuItem__112);
						clrMenuItem__112.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								
								new OpEmpresa(principal, usuario, "Agregar");
							}
						});

						clrMenuItem clrMenuItem__113 = new clrMenuItem("Modificar");
						menuItem_1.add(clrMenuItem__113);
						clrMenuItem__113.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								
								new OpEmpresa(principal, usuario, "Modificar");

							}
						});

						clrMenuItem clrMenuItem__114 = new clrMenuItem("Eliminar");
						menuItem_1.add(clrMenuItem__114);
						clrMenuItem__114.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								
								new OpEmpresa(principal, usuario, "Eliminar");

							}
						});

						clrMenu2 menuItem_2 = new clrMenu2("Sub-grupos por empresa");
						mnAdmin.add(menuItem_2);

						clrMenuItem clrMenuItem__115 = new clrMenuItem("Agregar");
						menuItem_2.add(clrMenuItem__115);
						clrMenuItem__115.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								
								new OpGrupoEmpresa(principal, usuario, "Agregar");
							}
						});

						clrMenuItem clrMenuItem__116 = new clrMenuItem("Modificar");
						menuItem_2.add(clrMenuItem__116);
						clrMenuItem__116.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								
								new OpGrupoEmpresa(principal, usuario, "Modificar");

							}
						});

						clrMenuItem clrMenuItem__117 = new clrMenuItem("Eliminar");
						menuItem_2.add(clrMenuItem__117);
						clrMenuItem__117.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								
								new OpGrupoEmpresa(principal, usuario, "Eliminar");

							}
						});

						menu++;

						break;
					case 5:
						if (mnAdmin == null) {
							Component horizontalStrut_1 = Box.createHorizontalStrut(15);
							menuBar.add(horizontalStrut_1);

							mnAdmin = new clrMenu("Administrar");
							menuBar.add(mnAdmin);
						}
						clrSeparador clrSeparador__11 = new clrSeparador();
						mnAdmin.add(clrSeparador__11);

						clrMenuItemIndicativo clrMenuItemIndicativo__5 = new clrMenuItemIndicativo("Procedimientos",5);
						mnAdmin.add(clrMenuItemIndicativo__5);

						clrSeparador clrSeparador__12 = new clrSeparador();
						mnAdmin.add(clrSeparador__12);

						clrMenu2 clrMenuItem__14 = new clrMenu2("Protocolo de resultados");
						mnAdmin.add(clrMenuItem__14);

						clrMenuItem clrMenuItem__118 = new clrMenuItem("Agregar");
						clrMenuItem__14.add(clrMenuItem__118);
						clrMenuItem__118.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								
								new OpProtocolo(principal, usuario, "Agregar");
							}
						});

						clrMenuItem clrMenuItem__119 = new clrMenuItem("Modificar");
						clrMenuItem__14.add(clrMenuItem__119);
						clrMenuItem__119.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								
								new OpProtocolo(principal, usuario, "Modificar");
							}
						});

						clrMenuItem clrMenuItem__120 = new clrMenuItem("Eliminar");
						clrMenuItem__14.add(clrMenuItem__120);
						clrMenuItem__120.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								
								new OpProtocolo(principal, usuario, "Eliminar");
							}
						});

						clrMenu2 clrMenuItem__17 = new clrMenu2("Tipos de datos");
						mnAdmin.add(clrMenuItem__17);

						clrMenuItem clrMenuItem__121 = new clrMenuItem("Agregar");
						clrMenuItem__17.add(clrMenuItem__121);
						clrMenuItem__121.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								
								new OpTipoDato(principal, usuario, "Agregar");
							}
						});

						clrMenuItem clrMenuItem__122 = new clrMenuItem("Modificar");
						clrMenuItem__17.add(clrMenuItem__122);
						clrMenuItem__122.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								
								new OpTipoDato(principal, usuario, "Modificar");
							}
						});

						clrMenuItem clrMenuItem__123 = new clrMenuItem("Eliminar");
						clrMenuItem__17.add(clrMenuItem__123);
						clrMenuItem__123.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								
								new OpTipoDato(principal, usuario, "Eliminar");
							}
						});

						clrMenuItem clrMenuItem__21 = new clrMenuItem("Opciones de tipos de datos");
						mnAdmin.add(clrMenuItem__21);
						clrMenuItem__21.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								
								new OpOPTD(principal, usuario);
							}
						});

						clrMenu2 clrMenuItem__20 = new clrMenu2("Tipos de muestras");
						mnAdmin.add(clrMenuItem__20);

						clrMenuItem clrMenuItem__127 = new clrMenuItem("Agregar");
						clrMenuItem__20.add(clrMenuItem__127);
						clrMenuItem__127.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpTipoMuestras(principal, usuario, "Agregar");
								
							}
						});

						clrMenuItem clrMenuItem__128 = new clrMenuItem("Modificar");
						clrMenuItem__20.add(clrMenuItem__128);
						clrMenuItem__128.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpTipoMuestras(principal, usuario, "Modificar");
								
							}
						});

						clrMenuItem clrMenuItem__129 = new clrMenuItem("Eliminar");
						clrMenuItem__20.add(clrMenuItem__129);
						clrMenuItem__129.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpTipoMuestras(principal, usuario, "Eliminar");
								
							}
						});

						clrMenu2 clrMenuItem__19 = new clrMenu2("Perfiles de exámenes");
						mnAdmin.add(clrMenuItem__19);

						clrMenuItem clrMenuItem__130 = new clrMenuItem("Agregar");
						clrMenuItem__19.add(clrMenuItem__130);
						clrMenuItem__130.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpPerfiles(principal, usuario, "Agregar");
								
							}
						});

						clrMenuItem clrMenuItem__131 = new clrMenuItem("Modificar");
						clrMenuItem__19.add(clrMenuItem__131);
						clrMenuItem__131.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpPerfiles(principal, usuario, "Modificar");
								
							}
						});

				

						clrMenu2 clrMenuItem__18 = new clrMenu2("Tarifas");
						mnAdmin.add(clrMenuItem__18);

						clrMenuItem clrMenuItem__133 = new clrMenuItem("Agregar");
						clrMenuItem__18.add(clrMenuItem__133);
						clrMenuItem__133.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpTarifas(principal, usuario, "Agregar");
								
							}
						});

						clrMenuItem clrMenuItem__134 = new clrMenuItem("Modificar");
						clrMenuItem__18.add(clrMenuItem__134);
						clrMenuItem__134.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpTarifas(principal, usuario, "Modificar");
								
							}
						});

						clrMenuItem clrMenuItem__135 = new clrMenuItem("Eliminar");
						clrMenuItem__18.add(clrMenuItem__135);
						clrMenuItem__135.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpTarifas(principal, usuario, "Eliminar");
								
							}
						});

						clrMenu2 clrMenuItem__16 = new clrMenu2("CUPS y ex\u00E1menes");
						mnAdmin.add(clrMenuItem__16);

						clrMenuItem clrMenuItem__136 = new clrMenuItem("Agregar");
						clrMenuItem__16.add(clrMenuItem__136);
						clrMenuItem__136.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								new OpExamen(principal, usuario, "Agregar");
							}
						});

						clrMenuItem clrMenuItem__137 = new clrMenuItem("Modificar");
						clrMenuItem__16.add(clrMenuItem__137);
						clrMenuItem__137.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								new OpExamen(principal, usuario, "Modificar");
							}
						});

						clrMenuItem clrMenuItem__138 = new clrMenuItem("Eliminar");
						clrMenuItem__16.add(clrMenuItem__138);
						clrMenuItem__138.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								new OpExamen(principal, usuario, "Eliminar");
							}
						});
						menu++;

						break;
					case 6:
						if (mnAdmin == null) {
							Component horizontalStrut_1 = Box.createHorizontalStrut(15);
							menuBar.add(horizontalStrut_1);

							mnAdmin = new clrMenu("Administrar");
							menuBar.add(mnAdmin);
						}
						clrSeparador clrSeparador__3 = new clrSeparador();
						mnAdmin.add(clrSeparador__3);

						clrMenuItemIndicativo clrMenuItemIndicativo__1 = new clrMenuItemIndicativo("Laboratorios",6);
						mnAdmin.add(clrMenuItemIndicativo__1);

						clrSeparador clrSeparador__4 = new clrSeparador();
						mnAdmin.add(clrSeparador__4);

						clrMenu2 clrMenuItem__1 = new clrMenu2("Laboratorios");
						mnAdmin.add(clrMenuItem__1);

						clrMenuItem clrMenuItem__139 = new clrMenuItem("Agregar");
						clrMenuItem__1.add(clrMenuItem__139);
						clrMenuItem__139.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpLaboratorios(principal, usuario, "Agregar");
								
							}
						});

						clrMenuItem clrMenuItem__140 = new clrMenuItem("Modificar");
						clrMenuItem__1.add(clrMenuItem__140);
						clrMenuItem__140.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpLaboratorios(principal, usuario, "Modificar");
								
							}
						});

						clrMenuItem clrMenuItem__141 = new clrMenuItem("Eliminar");
						clrMenuItem__1.add(clrMenuItem__141);
						clrMenuItem__141.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpLaboratorios(principal, usuario, "Eliminar");
								
							}
						});
						menu++;

						break;
					case 7:
						if (mnAdmin == null) {
							Component horizontalStrut_1 = Box.createHorizontalStrut(15);
							menuBar.add(horizontalStrut_1);

							mnAdmin = new clrMenu("Administrar");
							menuBar.add(mnAdmin);
						}

						clrSeparador clrSeparador__8 = new clrSeparador();
						mnAdmin.add(clrSeparador__8);

						clrMenuItemIndicativo clrMenuItemIndicativo__3 = new clrMenuItemIndicativo("Geogr\u00E1ficos",7);
						mnAdmin.add(clrMenuItemIndicativo__3);

						clrSeparador clrSeparador__7 = new clrSeparador();
						mnAdmin.add(clrSeparador__7);

						clrMenu2 clrMenuItem__8 = new clrMenu2("Ciudades");
						mnAdmin.add(clrMenuItem__8);

						clrMenuItem clrMenuItem__142 = new clrMenuItem("Agregar");
						clrMenuItem__8.add(clrMenuItem__142);
						clrMenuItem__142.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpCiudades(principal, usuario, "Agregar");
								
							}
						});

						clrMenuItem clrMenuItem__143 = new clrMenuItem("Modificar");
						clrMenuItem__8.add(clrMenuItem__143);
						clrMenuItem__143.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpCiudades(principal, usuario, "Modificar");
								
							}
						});

						clrMenuItem clrMenuItem__144 = new clrMenuItem("Eliminar");
						clrMenuItem__8.add(clrMenuItem__144);
						clrMenuItem__144.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpCiudades(principal, usuario, "Eliminar");
								
							}
						});

					/*	clrMenu2 clrMenuItem__9 = new clrMenu2("Departamentos");
						mnAdmin.add(clrMenuItem__9);

						clrMenuItem clrMenuItem__145 = new clrMenuItem("Agregar");
						clrMenuItem__9.add(clrMenuItem__145);

						clrMenuItem clrMenuItem__146 = new clrMenuItem("Modificar");
						clrMenuItem__9.add(clrMenuItem__146);

						clrMenuItem clrMenuItem__147 = new clrMenuItem("Eliminar");
						clrMenuItem__9.add(clrMenuItem__147);*/
						menu++;

						break;
					case 8:
						if (mnAdmin == null) {
							Component horizontalStrut_1 = Box.createHorizontalStrut(15);
							menuBar.add(horizontalStrut_1);

							mnAdmin = new clrMenu("Administrar");
							menuBar.add(mnAdmin);
						}

						clrSeparador clrSeparador__5 = new clrSeparador();
						mnAdmin.add(clrSeparador__5);

						clrMenuItemIndicativo clrMenuItemIndicativo__2 = new clrMenuItemIndicativo("Otros",8);
						mnAdmin.add(clrMenuItemIndicativo__2);

						clrSeparador clrSeparador__6 = new clrSeparador();
						mnAdmin.add(clrSeparador__6);

						clrMenu2 clrMenuItem__6 = new clrMenu2("Salas");
						mnAdmin.add(clrMenuItem__6);

						clrMenuItem clrMenuItem__148 = new clrMenuItem("Agregar");
						clrMenuItem__6.add(clrMenuItem__148);
						clrMenuItem__148.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpSalas(principal, usuario, "Agregar");
								
							}
						});

						clrMenuItem clrMenuItem__149 = new clrMenuItem("Modificar");
						clrMenuItem__6.add(clrMenuItem__149);
						clrMenuItem__149.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpSalas(principal, usuario, "Modificar");
								
							}
						});

						clrMenuItem clrMenuItem__150 = new clrMenuItem("Eliminar");
						clrMenuItem__6.add(clrMenuItem__150);
						clrMenuItem__150.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpSalas(principal, usuario, "Eliminar");
								
							}
						});

						clrMenu2 clrMenuItem__3 = new clrMenu2("Secciones");
						mnAdmin.add(clrMenuItem__3);

						clrMenuItem clrMenuItem__151 = new clrMenuItem("Agregar");
						clrMenuItem__3.add(clrMenuItem__151);
						clrMenuItem__151.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								
								new OpSecciones(principal, usuario, "Agregar");
							}
						});

						clrMenuItem clrMenuItem__152 = new clrMenuItem("Modificar");
						clrMenuItem__3.add(clrMenuItem__152);
						clrMenuItem__152.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								
								new OpSecciones(principal, usuario, "Modificar");
							}
						});

						clrMenuItem clrMenuItem__153 = new clrMenuItem("Eliminar");
						clrMenuItem__3.add(clrMenuItem__153);
						clrMenuItem__153.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								
								new OpSecciones(principal, usuario, "Eliminar");
							}
						});

						clrMenu2 clrMenuItem__7 = new clrMenu2("Parentesco");
						mnAdmin.add(clrMenuItem__7);

						clrMenuItem clrMenuItem__154 = new clrMenuItem("Agregar");
						clrMenuItem__7.add(clrMenuItem__154);
						clrMenuItem__154.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpParentesco(principal, usuario, "Agregar");
								
							}
						});

						clrMenuItem clrMenuItem__155 = new clrMenuItem("Modificar");
						clrMenuItem__7.add(clrMenuItem__155);
						clrMenuItem__155.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpParentesco(principal, usuario, "Modificar");
								
							}
						});

						clrMenuItem clrMenuItem__156 = new clrMenuItem("Eliminar");
						clrMenuItem__7.add(clrMenuItem__156);
						clrMenuItem__156.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpParentesco(principal, usuario, "Eliminar");
								
							}
						});

						clrMenu2 clrMenuItem__4 = new clrMenu2("Planes P y P");
						mnAdmin.add(clrMenuItem__4);

						clrMenuItem clrMenuItem__157 = new clrMenuItem("Agregar");
						clrMenuItem__4.add(clrMenuItem__157);
						clrMenuItem__157.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpPyP(principal, usuario, "Agregar");
								
							}
						});

						clrMenuItem clrMenuItem__158 = new clrMenuItem("Modificar");
						clrMenuItem__4.add(clrMenuItem__158);
						clrMenuItem__158.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpPyP(principal, usuario, "Modificar");
								
							}
						});

						clrMenuItem clrMenuItem__159 = new clrMenuItem("Eliminar");
						clrMenuItem__4.add(clrMenuItem__159);
						clrMenuItem__159.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpPyP(principal, usuario, "Eliminar");
								
							}
						});

						clrMenu2 clrMenuItem__5 = new clrMenu2("Especialidades");
						mnAdmin.add(clrMenuItem__5);
						

						clrMenuItem clrMenuItem__160 = new clrMenuItem("Agregar");
						clrMenuItem__5.add(clrMenuItem__160);
						clrMenuItem__160.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpEspecialidades(principal, usuario, "Agregar");
								
							}
						});

						clrMenuItem clrMenuItem__161 = new clrMenuItem("Modificar");
						clrMenuItem__5.add(clrMenuItem__161);
						clrMenuItem__161.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpEspecialidades(principal, usuario, "Modificar");
								
							}
						});

						clrMenuItem clrMenuItem__162 = new clrMenuItem("Eliminar");
						clrMenuItem__5.add(clrMenuItem__162);
						clrMenuItem__162.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpEspecialidades(principal, usuario, "Eliminar");
								
							}
						});
						
						
						menu++;

						break;
					case 9:
						if (mnInfo == null) {
							Component horizontalStrut_2 = Box.createHorizontalStrut(15);
							menuBar.add(horizontalStrut_2);

							mnInfo = new clrMenu("Informes y listados");
							menuBar.add(mnInfo);
						}
						clrSeparador clrSeparador__15 = new clrSeparador();
						mnInfo.add(clrSeparador__15);

						clrMenuItemIndicativo clrMenuItemIndicativo__7 = new clrMenuItemIndicativo("Informes",9);
						mnInfo.add(clrMenuItemIndicativo__7);

						clrSeparador clrSeparador__19 = new clrSeparador();
						mnInfo.add(clrSeparador__19);

						clrMenu2 clrMenuItem__25 = new clrMenu2("Resultados");
						mnInfo.add(clrMenuItem__25);

						clrMenuItem clrMenuItem__32 = new clrMenuItem("Por recepcion");
						clrMenuItem__25.add(clrMenuItem__32);
						clrMenuItem__32.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpResXRecepcion(principal, usuario);
								
							}
						});

						clrMenuItem clrMenuItem__55 = new clrMenuItem("Por seccion o examen");
						clrMenuItem__25.add(clrMenuItem__55);
						clrMenuItem__55.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent arg0) {
								new OpResXSeccion(principal, usuario);								
							}
						});

						clrMenuItem clrMenuItem__54 = new clrMenuItem("Por fecha");
						clrMenuItem__25.add(clrMenuItem__54);
						clrMenuItem__54.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent arg0) {
								new OpResXFecha(principal, usuario);
								
							}
						});
							
						
						clrMenuItem clrMenuItem__53 = new clrMenuItem("Por paciente");
						clrMenuItem__25.add(clrMenuItem__53);

						clrSeparador clrSeparador__44 = new clrSeparador();
						clrMenuItem__25.add(clrSeparador__44);

						clrMenuItem clrMenuItem__51 = new clrMenuItem("Reportes de entrega");
						clrMenuItem__25.add(clrMenuItem__51);

						clrMenu2 clrMenuItem__26 = new clrMenu2("Duplicados");
						mnInfo.add(clrMenuItem__26);

						clrMenuItem clrMenuItem__56 = new clrMenuItem("Recibo");
						clrMenuItem__26.add(clrMenuItem__56);

						clrMenuItem clrMenuItem__62 = new clrMenuItem("Recepcion");
						clrMenuItem__26.add(clrMenuItem__62);

						clrMenuItem clrMenuItem__63 = new clrMenuItem("Sticker de recepcion");
						clrMenuItem__26.add(clrMenuItem__63);

						clrSeparador clrSeparador__41 = new clrSeparador();
						clrMenuItem__26.add(clrSeparador__41);

						clrMenuItem clrMenuItem__61 = new clrMenuItem("Remision");
						clrMenuItem__26.add(clrMenuItem__61);

						clrMenuItem clrMenuItem__60 = new clrMenuItem("Sticker de remision");
						clrMenuItem__26.add(clrMenuItem__60);

						clrSeparador clrSeparador__42 = new clrSeparador();
						clrMenuItem__26.add(clrSeparador__42);

						clrMenuItem clrMenuItem__59 = new clrMenuItem("Factura");
						clrMenuItem__26.add(clrMenuItem__59);

						clrMenuItem clrMenuItem__101 = new clrMenuItem("Cotizacion");
						clrMenuItem__26.add(clrMenuItem__101);

						clrSeparador clrSeparador__43 = new clrSeparador();
						clrMenuItem__26.add(clrSeparador__43);

						clrMenuItem clrMenuItem__58 = new clrMenuItem("Nota credito");
						clrMenuItem__26.add(clrMenuItem__58);

						clrMenuItem clrMenuItem__57 = new clrMenuItem("Nota debito");
						clrMenuItem__26.add(clrMenuItem__57);

						clrMenu2 clrMenuItem__27 = new clrMenu2("Estad\u00EDsticos");
						mnInfo.add(clrMenuItem__27);

						clrMenuItem clrMenuItem__65 = new clrMenuItem("Examenes por empresa");
						clrMenuItem__27.add(clrMenuItem__65);

						clrMenuItem clrMenuItem__71 = new clrMenuItem("Recepciones por empresa");
						clrMenuItem__27.add(clrMenuItem__71);

						clrMenuItem clrMenuItem__70 = new clrMenuItem("Examenes por medico");
						clrMenuItem__27.add(clrMenuItem__70);

						clrMenuItem clrMenuItem__69 = new clrMenuItem("Examenes por paciente");
						clrMenuItem__27.add(clrMenuItem__69);

						clrMenuItem clrMenuItem__68 = new clrMenuItem("Examenes por seccion");
						clrMenuItem__27.add(clrMenuItem__68);

						clrMenuItem clrMenuItem__67 = new clrMenuItem("Tipos de recepcion por empresa");
						clrMenuItem__27.add(clrMenuItem__67);

						clrSeparador clrSeparador__40 = new clrSeparador();
						clrMenuItem__27.add(clrSeparador__40);

						clrMenuItem clrMenuItem__66 = new clrMenuItem("Acumulados de estudios");
						clrMenuItem__27.add(clrMenuItem__66);

						clrMenuItem clrMenuItem__64 = new clrMenuItem("Cantidad de examenes por empresa");
						clrMenuItem__27.add(clrMenuItem__64);

						clrMenuItem clrMenuItem__100 = new clrMenuItem("Registros de movimientos en sistema");
						clrMenuItem__27.add(clrMenuItem__100);

						clrMenu2 clrMenuItem__28 = new clrMenu2("Otros informes");
						mnInfo.add(clrMenuItem__28);

						clrMenuItem clrMenuItem__73 = new clrMenuItem("Resultados impresos");
						clrMenuItem__28.add(clrMenuItem__73);

						clrMenuItem clrMenuItem__74 = new clrMenuItem("Resultados pendientes");
						clrMenuItem__28.add(clrMenuItem__74);

						clrMenuItem clrMenuItem__75 = new clrMenuItem("Remisiones");
						clrMenuItem__28.add(clrMenuItem__75);

						clrMenuItem clrMenuItem__76 = new clrMenuItem("Recepcion por pacientes");
						clrMenuItem__28.add(clrMenuItem__76);

						clrMenuItem clrMenuItem__78 = new clrMenuItem("Detallado de recepciones");
						clrMenuItem__28.add(clrMenuItem__78);

						clrMenuItem clrMenuItem__79 = new clrMenuItem("Auditoria de recepciones");
						clrMenuItem__28.add(clrMenuItem__79);

						clrMenuItem clrMenuItem__80 = new clrMenuItem("Facturas anuladas");
						clrMenuItem__28.add(clrMenuItem__80);

						clrMenuItem clrMenuItem__81 = new clrMenuItem("Recepciones anuladas");
						clrMenuItem__28.add(clrMenuItem__81);

						clrMenuItem clrMenuItem__82 = new clrMenuItem("Precios por tarifas");
						clrMenuItem__28.add(clrMenuItem__82);

						clrMenuItem clrMenuItem__83 = new clrMenuItem("Examenes con todas las tarifas");
						clrMenuItem__28.add(clrMenuItem__83);

						clrSeparador clrSeparador__39 = new clrSeparador();
						clrMenuItem__28.add(clrSeparador__39);

						clrMenuItem clrMenuItem__84 = new clrMenuItem("Codigos Bayerlab");
						clrMenuItem__28.add(clrMenuItem__84);

						clrMenuItem clrMenuItem__85 = new clrMenuItem("Examenes con protocolos");
						clrMenuItem__28.add(clrMenuItem__85);

						clrMenuItem clrMenuItem__86 = new clrMenuItem("Protocolos con items");
						clrMenuItem__28.add(clrMenuItem__86);

						clrMenuItem clrMenuItem__72 = new clrMenuItem("Medicos");
						clrMenuItem__28.add(clrMenuItem__72);
						menu++;

						break;
					case 10:

						if (mnInfo == null) {
							Component horizontalStrut_2 = Box.createHorizontalStrut(15);
							menuBar.add(horizontalStrut_2);

							mnInfo = new clrMenu("Informes y listados");
							menuBar.add(mnInfo);
						}
						clrSeparador clrSeparador__21 = new clrSeparador();
						mnInfo.add(clrSeparador__21);

						clrMenuItemIndicativo clrMenuItemIndicativo__8 = new clrMenuItemIndicativo("Listados",10);
						mnInfo.add(clrMenuItemIndicativo__8);

						clrSeparador clrSeparador__22 = new clrSeparador();
						mnInfo.add(clrSeparador__22);

						clrMenu2 clrMenuItem__29 = new clrMenu2("Por empresa");
						mnInfo.add(clrMenuItem__29);

						clrMenuItem clrMenuItem__91 = new clrMenuItem("Procedimientos por fecha");
						clrMenuItem__29.add(clrMenuItem__91);

						clrMenuItem clrMenuItem__92 = new clrMenuItem("Atencion por empresa");
						clrMenuItem__29.add(clrMenuItem__92);

						clrMenuItem clrMenuItem__93 = new clrMenuItem("Recepcion por examen");
						clrMenuItem__29.add(clrMenuItem__93);

						clrMenuItem clrMenuItem__94 = new clrMenuItem("Estado de cuenta");
						clrMenuItem__29.add(clrMenuItem__94);

						clrMenuItem clrMenuItem__95 = new clrMenuItem("Cuentas por fecha");
						clrMenuItem__29.add(clrMenuItem__95);

						clrMenuItem clrMenuItem__96 = new clrMenuItem("Facturas por Fecha");
						clrMenuItem__29.add(clrMenuItem__96);

						clrSeparador clrSeparador__46 = new clrSeparador();
						clrMenuItem__29.add(clrSeparador__46);

						clrMenuItem clrMenuItem__97 = new clrMenuItem("Tarifas por empresa");
						clrMenuItem__29.add(clrMenuItem__97);

						clrMenuItem clrMenuItem__98 = new clrMenuItem("Listados de empresas");
						clrMenuItem__29.add(clrMenuItem__98);

						clrMenuItem clrMenuItem__99 = new clrMenuItem("Pacientes por empresa");
						clrMenuItem__29.add(clrMenuItem__99);

						clrMenuItem clrMenuItem__31 = new clrMenuItem("Vencimientos de cartera");
						mnInfo.add(clrMenuItem__31);

						clrMenuItem clrMenuItem__33 = new clrMenuItem("Listado de actividades");
						mnInfo.add(clrMenuItem__33);

						clrMenuItem clrMenuItem__30 = new clrMenuItem("Res\u00FAmenes diarios");
						mnInfo.add(clrMenuItem__30);
						menu++;

						break;
					case 11:

						if (mnInfo == null) {
							Component horizontalStrut_2 = Box.createHorizontalStrut(15);
							menuBar.add(horizontalStrut_2);

							mnInfo = new clrMenu("Informes y listados");
							menuBar.add(mnInfo);
						}
						clrSeparador clrSeparador__23 = new clrSeparador();
						mnInfo.add(clrSeparador__23);

						clrMenuItemIndicativo clrMenuItemIndicativo__9 = new clrMenuItemIndicativo("Otros",8);
						mnInfo.add(clrMenuItemIndicativo__9);

						clrSeparador clrSeparador__49 = new clrSeparador();
						mnInfo.add(clrSeparador__49);

						clrMenu2 mnMenuenMenu = new clrMenu2("Plantillas de trabajo");
						mnInfo.add(mnMenuenMenu);

						clrMenuItem clrMenuItem__87 = new clrMenuItem("Estilo rejilla");
						mnMenuenMenu.add(clrMenuItem__87);

						clrMenuItem clrMenuItem__88 = new clrMenuItem("Rejilla por item");
						mnMenuenMenu.add(clrMenuItem__88);

						clrMenuItem clrMenuItem__77 = new clrMenuItem("Plantilla de trabajo estandar");
						mnMenuenMenu.add(clrMenuItem__77);

						clrSeparador clrSeparador__45 = new clrSeparador();
						mnMenuenMenu.add(clrSeparador__45);

						clrMenuItem clrMenuItem__89 = new clrMenuItem("Por paciente");
						mnMenuenMenu.add(clrMenuItem__89);

						clrMenuItem clrMenuItem__90 = new clrMenuItem("Abreviada");
						mnMenuenMenu.add(clrMenuItem__90);
						menu++;

						break;
					case 12:
						if (mnContab == null) {
							Component horizontalStrut_3 = Box.createHorizontalStrut(15);
							menuBar.add(horizontalStrut_3);

							mnContab = new clrMenu("Contabilidad");
							menuBar.add(mnContab);
						}
						clrSeparador clrSeparador__16 = new clrSeparador();
						mnContab.add(clrSeparador__16);

						clrMenuItemIndicativo clrMenuItemIndicativo__10 = new clrMenuItemIndicativo("Cartera",11);
						mnContab.add(clrMenuItemIndicativo__10);

						clrSeparador clrSeparador__20 = new clrSeparador();
						mnContab.add(clrSeparador__20);

						clrMenuItem clrMenuItem__35 = new clrMenuItem("Abonos a paciente");
						mnContab.add(clrMenuItem__35);
						clrMenuItem__35.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpAbonoXpaciente(principal, usuario);
								
							}
						});

						clrMenuItem clrMenuItem__36 = new clrMenuItem("Abonos a empresas");
						mnContab.add(clrMenuItem__36);
						clrMenuItem__36.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpAbonoXEmpresa(principal, usuario);
								
							}
						});
						
						clrMenuItem clrMenuItem163=new clrMenuItem("Abonos por factura");
						mnContab.add(clrMenuItem163);
						clrMenuItem163.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpAbonoXFactura(principal, usuario, null);
								
							}
						});
						

						clrMenuItem clrMenuItem__37 = new clrMenuItem("Nota crédito");
						mnContab.add(clrMenuItem__37);
						clrMenuItem__37.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpNotasCD(principal, usuario,1);
								
							}
						});

						clrMenuItem clrMenuItem__38 = new clrMenuItem("Nota debito");
						mnContab.add(clrMenuItem__38);
						clrMenuItem__38.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
							new OpNotasCD(principal, usuario, 2);
								
							}
						});
						
						menu++;

						break;
					case 13:
						if (mnContab == null) {
							Component horizontalStrut_3 = Box.createHorizontalStrut(15);
							menuBar.add(horizontalStrut_3);

							mnContab = new clrMenu("Contabilidad");
							menuBar.add(mnContab);
						}
						clrSeparador clrSeparador__25 = new clrSeparador();
						mnContab.add(clrSeparador__25);

						clrMenuItemIndicativo clrMenuItemIndicativo__11 = new clrMenuItemIndicativo("Facturaci\u00F3n",12);
						mnContab.add(clrMenuItemIndicativo__11);

						clrSeparador clrSeparador__26 = new clrSeparador();
						mnContab.add(clrSeparador__26);

						clrMenuItem clrMenuItem__39 = new clrMenuItem("Cuenta de cobro por empresa");
						mnContab.add(clrMenuItem__39);
						clrMenuItem__39.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
							new OpCuentaXEmpresa(principal, usuario);
								
							}
						});

						clrMenuItem clrMenuItem__40 = new clrMenuItem("Cuenta de cobro por paciente");
						mnContab.add(clrMenuItem__40);
						clrMenuItem__40.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpCuentaXPaciente(principal, usuario);
								
							}
						});

						clrMenuItem clrMenuItem__41 = new clrMenuItem("Imprimir factura");
						mnContab.add(clrMenuItem__41);

						clrMenuItem clrMenuItem__42 = new clrMenuItem("RIPS");
						mnContab.add(clrMenuItem__42);
						clrMenuItem__42.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpRipsXCuentaCobro(principal, usuario);
								
							}
						});
						
						
						
						menu++;

						break;
					case 14:
						if (mnCustom == null) {
							Component horizontalStrut_4 = Box.createHorizontalStrut(15);
							menuBar.add(horizontalStrut_4);

							mnCustom = new clrMenu("Personalizar");
							menuBar.add(mnCustom);
						}
						clrSeparador clrSeparador__27 = new clrSeparador();
						mnCustom.add(clrSeparador__27);

						clrMenuItemIndicativo clrMenuItemIndicativo__12 = new clrMenuItemIndicativo("Impresiones",13);
						mnCustom.add(clrMenuItemIndicativo__12);

						clrSeparador clrSeparador__28 = new clrSeparador();
						mnCustom.add(clrSeparador__28);

						clrMenuItem clrMenuItem__43 = new clrMenuItem("Opciones de impresión");
						mnCustom.add(clrMenuItem__43);
						clrMenuItem__43.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
							new OpTamanosPapel(principal, usuario);
								
							}
						});

					
						menu++;

						break;
					case 15:

						if (mnCustom == null) {
							Component horizontalStrut_4 = Box.createHorizontalStrut(15);
							menuBar.add(horizontalStrut_4);

							mnCustom = new clrMenu("Personalizar");
							menuBar.add(mnCustom);
						}

						clrSeparador clrSeparador__29 = new clrSeparador();
						mnCustom.add(clrSeparador__29);

						clrMenuItemIndicativo clrMenuItemIndicativo__13 = new clrMenuItemIndicativo("Cambios",14);
						mnCustom.add(clrMenuItemIndicativo__13);

						clrSeparador clrSeparador__30 = new clrSeparador();
						mnCustom.add(clrSeparador__30);

						clrMenu2 clrMenuItem__47 = new clrMenu2("Cambiar datos");
						mnCustom.add(clrMenuItem__47);
						
						clrMenuItem clrMenuItemCamREc = new clrMenuItem("Cambiar datos de recepción");
						clrMenuItem__47.add(clrMenuItemCamREc);
						clrMenuItemCamREc.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpCambiosRecepcion(principal, usuario);
								
							}
						});

						clrMenu2 clrMenuItem__48 = new clrMenu2("Anulaciones");
						mnCustom.add(clrMenuItem__48);
						
						clrMenuItem clrMenuItemAnuREc = new clrMenuItem("Anular recepción");
						clrMenuItem__48.add(clrMenuItemAnuREc);
						clrMenuItemAnuREc.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent arg0) {
								new OpAnularRecepcion(principal, usuario);
								
							}
						});
						
						menu++;

						break;
					case 16:
						if (mnCustom == null) {
							Component horizontalStrut_4 = Box.createHorizontalStrut(15);
							menuBar.add(horizontalStrut_4);

							mnCustom = new clrMenu("Personalizar");
							menuBar.add(mnCustom);
						}
						clrSeparador clrSeparador__31 = new clrSeparador();
						mnCustom.add(clrSeparador__31);

						clrMenuItemIndicativo clrMenuItemIndicativo__14 = new clrMenuItemIndicativo("Laboratorio",15);
						mnCustom.add(clrMenuItemIndicativo__14);

						clrSeparador clrSeparador__32 = new clrSeparador();
						mnCustom.add(clrSeparador__32);

						clrMenuItem clrMenuItem__49 = new clrMenuItem("Datos de laboratorio");
						mnCustom.add(clrMenuItem__49);
						clrMenuItem__49.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpMiLaboratorio(principal, getUsuario());
								
							}
						});
						
						clrMenuItem datosSede = new clrMenuItem("Datos de sede");
						mnCustom.add(datosSede);
						datosSede.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new OpSede(principal, getUsuario());
								
								
							}
						});
						menu++;

						break;
					case 17:
						Component horizontalStrut_5 = Box.createHorizontalStrut(15);
						menuBar.add(horizontalStrut_5);

						clrMenu clrMenu__5 = new clrMenu("Ayuda");
						menuBar.add(clrMenu__5);

						clrSeparador clrSeparador__33 = new clrSeparador();
						clrMenu__5.add(clrSeparador__33);

						clrMenuItemIndicativo clrMenuItemIndicativo__15 = new clrMenuItemIndicativo("Acerca de este software",16);
						clrMenu__5.add(clrMenuItemIndicativo__15);

						clrSeparador clrSeparador__34 = new clrSeparador();
						clrMenu__5.add(clrSeparador__34);

						clrMenuItem clrMenuItem__45 = new clrMenuItem("Manual de operación");
						clrMenu__5.add(clrMenuItem__45);

						clrMenuItem clrMenuItem__46 = new clrMenuItem("Manual de conexión");
						clrMenu__5.add(clrMenuItem__46);

						clrSeparador clrSeparador__35 = new clrSeparador();
						clrMenu__5.add(clrSeparador__35);

						clrMenuItemIndicativo clrMenuItemIndicativo__16 = new clrMenuItemIndicativo("Acerca de nuestra empresa      ",17);
						clrMenu__5.add(clrMenuItemIndicativo__16);

						clrSeparador clrSeparador__36 = new clrSeparador();
						clrMenu__5.add(clrSeparador__36);

						clrMenuItem clrMenuItem__50 = new clrMenuItem("Feego System ");
						clrMenu__5.add(clrMenuItem__50);

						clrSeparador clrSeparador__37 = new clrSeparador();
						clrMenu__5.add(clrSeparador__37);

						clrMenuItemIndicativo clrMenuItemIndicativo__17 = new clrMenuItemIndicativo("Acerca de licenciamiento",18);
						clrMenu__5.add(clrMenuItemIndicativo__17);

						clrSeparador clrSeparador__38 = new clrSeparador();
						clrMenu__5.add(clrSeparador__38);

						clrMenuItem clrMenuItem__52 = new clrMenuItem("Licencias");
						clrMenu__5.add(clrMenuItem__52);
						setJMenuBar(menuBar);
						contentPane.updateUI();
						menuBar.updateUI();
						menu++;
						// cerrarSesionTiempo(usuario.getTiempoI());
						break;

					default:
						menu++;
						break;
					}
				} else {
					menu++;
				}

			} while (menu != 18);
		}

	}

	public void construirVentana() {

		contentPane = new clrDeskPanel();
		this.principal = this;

		cirConexion = new circuloConexion(new Rectangle(0, 0, 0, 0));
		cirConexion.setBounds(25, this.getHeight() - 80, 250, 50);
		contentPane.add(cirConexion);
		
		String ruta="/Recursos/DB.png";
		icon=new ImageIcon(Principal.class.getResource(ruta));
		
		lblInfoErrores = new JLabel("");
		
		lblInfoErrores.setIcon(icon);
		lblInfoErrores.setBounds(cirConexion.getX()-(icon.getIconWidth()/4), cirConexion.getY() +50, this.getWidth(), icon.getIconHeight());
		lblInfoErrores.setForeground(Colores.clrTextoInactivo);
		contentPane.add(lblInfoErrores);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(200, 200,1910, 200);
		menuBar = new clrBarraMenu(); 
		setJMenuBar(menuBar);

		clrMenu clrMenu_ = new clrMenu("Usuario");
		menuBar.add(clrMenu_);

		clrSeparador clrSeparador__9 = new clrSeparador();
		clrMenu_.add(clrSeparador__9);

		clrMenuItemIndicativo clrMenuItemIndicativo__4 = new clrMenuItemIndicativo("Usuario",1);
		clrMenu_.add(clrMenuItemIndicativo__4);

		clrSeparador clrSeparador__10 = new clrSeparador();
		clrMenu_.add(clrSeparador__10);

		clrMenuItem clrMenuItem__10 = new clrMenuItem("Iniciar Sesion");
		clrMenu_.add(clrMenuItem__10);

		clrMenuItem__10.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				iniciarSesion();
			}
		});

		clrMenuItem clrMenuItem__13 = new clrMenuItem("Salir");
		clrMenuItem__13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {

				registrarAccion("Salida de sistema");
				File directorio=new File("C:/6342522/temp/");
				if (directorio.exists()) {
					directorio.delete();
				} 
				System.exit(DISPOSE_ON_CLOSE);
			}
		});
		clrMenu_.add(clrMenuItem__13);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		this.setVisible(true);
		iniciarSesion();

	}

	// creacion de menu cuando un usuario es tipo 0 osea tipo usuario

	public Usuario getUsuario() {
		return usuario;
	}

	public void actualizarBtnLogin() {

		cirUsuario.repaint();
		contentPane.updateUI();

	}

	public void cerrarSesion() {
		registrarAccion("Cierre de sesion");
		contentPane.removeAll();
		crearMenu(null);
	}

	public void cerrarSesionTiempo(int tiempoMil) {
		registrarAccion("Cierre de sesion");
	
		}

	public void cambiarpass() {
		cambiarPass cb = new cambiarPass(this, usuario);
		cb.setVisible(true);
	}

	public void registrarAccion(String Accion) {
		ObtenerFecha of = new ObtenerFecha();
		Date fecha = of.getNTPDate();
		String s = "";
		if (fecha != null) {
			DateFormat df = DateFormat.getInstance();
			s = df.format(fecha);
		} else {
			DateFormat df = DateFormat.getInstance();
			s = df.format(new Date()) + "(local)";

		}
		String respuesta = conexion.getInstance().registrarAccion(usuario, Accion, Colores.sede);

		if (respuesta.equals("true") && usuario != null) {
			lblInfoAcciones.setText("Fecha:" + s + " || Usuario:" + usuario.getUsuario() + " || Accion:" + Accion + " || Sede: "+Colores.sede);
		} else {
			if (usuario != null) {
				lblInfoAcciones.setText(respuesta);
				
			}
		}

	}
	
	
	public void registrarErrorDB(String Error) {
		System.err.println(Error);
		if (!reportandoErrores) {
			reportandoErrores=true;
			int delay=300;
			final int paso=delay/30;
					lblInfoErrores.setText(Error);
					System.err.println(Error);
		        
					 Thread reporte = new Thread(new Runnable() {
						    @Override
						    public void run() {
						         try{

						            for (int z=0; z<30; z++){
						            	lblInfoErrores.setLocation(lblInfoErrores.getX(),( lblInfoErrores.getY()-1));
						            		contentPane.updateUI();
						            Thread.sleep(paso);  
						            } 
						            
						            Thread.sleep(15000);  

						            
						            for (int z=0; z<30; z++){
						            	lblInfoErrores.setLocation(lblInfoErrores.getX(),( lblInfoErrores.getY()+1));
						            	
						            Thread.sleep(paso);  
						            } 
						        }catch(Exception ae){

						    }
						         reportandoErrores=false;
						    }
						});
						reporte.start();

			
		}
		
	}

	public static Principal getInstancePrincipal() {
		return principal;
	}

}
