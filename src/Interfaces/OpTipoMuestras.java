package Interfaces;

import interfaces_Modificadas.ClrCuadroDeTexto;
import interfaces_Modificadas.Colores;
import interfaces_Modificadas.btnRedondo;
import interfaces_Modificadas.circuloUsuario;
import interfaces_Modificadas.clrBarraMenu;
import interfaces_Modificadas.clrCheckBox;
import interfaces_Modificadas.clrComboBox;
import interfaces_Modificadas.clrFrameBordes;
import interfaces_Modificadas.clrLabel;
import interfaces_Modificadas.clrPanel;
import interfaces_Modificadas.clrPanelBordes;
import interfaces_Modificadas.clrPassword;
import interfaces_Modificadas.clrScrollBar;
import interfaces_Modificadas.clrToogleBoton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.AbstractListModel;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;

import javax.swing.JLabel;

import java.awt.Component;

import javax.swing.SwingConstants;

import conexion.conexion;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

import sun.awt.RequestFocusController;
import ago.beans.CirclePanel;
import auxiliares.TipoMuestra;
import auxiliares.Usuario;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.Choice;

public class OpTipoMuestras extends JDialog {

	private clrPanelBordes contentPane;
	private ClrCuadroDeTexto txtNombre;
	private clrLabel lblincorrectos;
	private Principal principal;
	private Usuario usuario;
	private clrLabel lblinicio;
	private btnRedondo btnAceptar;
	private btnRedondo btnCancelar;
	private ClrCuadroDeTexto txtSigla;
	private ClrCuadroDeTexto passPrimera;
	private circuloUsuario btnRedondo_;
	private clrLabel clrLabel__1;
	private clrComboBox cbTiempo;
	private CirclePanel circlePanel;
	private clrCheckBox chbActivo;
	private OpTipoMuestras esta;
	private lbTMXSigla lb1;
	private lbTMXNombre lb2;
	private int estado = -1;
	private String siglaAntigua;
	private int idmuestra=-1;
	

	/**
	 * Create the frame.
	 */
	public OpTipoMuestras(Principal pri, Usuario usuario, String Accion) {
		super(pri, true);
		System.out.println();
		this.usuario = usuario;
		esta = this;
		this.principal = pri;
		setRootPaneCheckingEnabled(false);
	setBounds(100, 100, 389, 271);
			this.setUndecorated(true);
			this.setLocationRelativeTo(getParent());

		
		
		
		
		
		
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		

		if (Accion.endsWith("Agregar")) {
		
			contentPane = new clrPanelBordes(true);
			contentPane.setLayout(null);
			lblinicio = new clrLabel("Agregar tipo de muestra", 2, true);
			lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
			lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblinicio.setBounds(10, 11, 369, 28);

			contentPane.add(lblinicio);

			txtSigla = new ClrCuadroDeTexto(5,false,"Sigla",1);
			txtSigla.setBounds(41, 117, 306, 28);
			contentPane.add(txtSigla);
		

			btnAceptar = new btnRedondo("Agregar", new Rectangle(48, 172,121,50),1);
			btnAceptar.addKeyListener(new KeyAdapter() {

				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						agregarTipoMuestra();
						}

					if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						dispose();
					}
				}

			});
			btnAceptar.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					agregarTipoMuestra();
					}

				@Override
				public void mousePressed(MouseEvent e) {
					

				}

				@Override
				public void mouseExited(MouseEvent e) {
					

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					

				}
			});
			btnAceptar.setSelected(true);
			btnAceptar.setBounds(67, 180, 113, 50);
			contentPane.add(btnAceptar);

			btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50), 2);
			btnCancelar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					if (lb2 != null)
						lb2.setVisible(false);
					if (lb1 != null)
						lb1.setVisible(false);

					dispose();
				}
			});
			btnCancelar.setBounds(190, 180, 129, 50);
			contentPane.add(btnCancelar);
			setContentPane(contentPane);

			getRootPane().setDefaultButton(btnAceptar);

			lblincorrectos = new clrLabel("", 1);
			lblincorrectos.setForeground(Colores.clrAlertaCamarada);
			lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
			lblincorrectos.setAlignmentX(0.5f);
			lblincorrectos.setBounds(10, 39, 358, 28);
			contentPane.add(lblincorrectos);

		

			txtNombre = new ClrCuadroDeTexto(100,false,"Nombre tipo de muestra",1);
			txtNombre.setBounds(41, 78, 306, 28);
			

			contentPane.add(txtNombre);

			btnAceptar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					agregarTipoMuestra();
				}
				

			});

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					btnAceptar.requestFocusInWindow();
				}
			});
			this.setVisible(true);
		} else {

			if (Accion.equals("Modificar")) {
				contentPane = new clrPanelBordes(true);
				contentPane.setLayout(null);
				lblinicio = new clrLabel("Modificar tipo de muestra", 2, true);
				lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
				lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
				lblinicio.setBounds(10, 11, 369, 28);

				contentPane.add(lblinicio);

				txtSigla = new ClrCuadroDeTexto(5,false,"Sigla",true,1);
				txtSigla.setBounds(41, 117, 306, 28);
				txtSigla.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						lb1.setVisible(false);
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						if (lb1==null) {
							lb1 = new lbTMXSigla(txtSigla, esta, esta.principal);
						}
						
					}
				});
				contentPane.add(txtSigla);
			

				btnAceptar = new btnRedondo("Modificar", new Rectangle(48, 172,121,50),3);
				btnAceptar.addKeyListener(new KeyAdapter() {

					@Override
					public void keyReleased(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							modificarTipoMuestra();
							}

						if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
							dispose();
						}
					}

				});
				btnAceptar.addMouseListener(new MouseListener() {

					@Override
					public void mouseReleased(MouseEvent e) {
						modificarTipoMuestra();
						}

					@Override
					public void mousePressed(MouseEvent e) {
						

					}

					@Override
					public void mouseExited(MouseEvent e) {
						

					}

					@Override
					public void mouseEntered(MouseEvent e) {
						

					}

					@Override
					public void mouseClicked(MouseEvent e) {
						

					}
				});
				btnAceptar.setSelected(true);
				btnAceptar.setBounds(67, 180, 113, 50);
				contentPane.add(btnAceptar);

				btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50), 2);
				btnCancelar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if (lb1 != null)
							lb1.setVisible(false);
						if (lb2 != null)
							lb2.setVisible(false);

						dispose();
					}
				});
				btnCancelar.setBounds(190, 180, 129, 50);
				contentPane.add(btnCancelar);
				setContentPane(contentPane);

				getRootPane().setDefaultButton(btnAceptar);

				lblincorrectos = new clrLabel("", 1);
				lblincorrectos.setForeground(Colores.clrAlertaCamarada);
				lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
				lblincorrectos.setAlignmentX(0.5f);
				lblincorrectos.setBounds(10, 39, 358, 28);
				contentPane.add(lblincorrectos);

		

				txtNombre = new ClrCuadroDeTexto(100,false,"Nombre tipo de muestra",true,1);
				txtNombre.setBounds(41, 78, 306, 28);
				txtNombre.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent arg0) {
						lb2.setVisible(false);
						
					}
					
					@Override
					public void focusGained(FocusEvent arg0) {
						if (lb2==null) {
							lb2 = new lbTMXNombre(txtNombre, esta, esta.principal);
						}
						
					}
				});
				

				contentPane.add(txtNombre);

				btnAceptar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						modificarTipoMuestra();
					}
					

				});

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						btnAceptar.requestFocusInWindow();
					}
				});
				this.setVisible(true);
			
				
				
				} else {
					

					contentPane = new clrPanelBordes(true);
					contentPane.setLayout(null);
					lblinicio = new clrLabel("Eliminar tipo de muestra", 2, true);
					lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
					lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
					lblinicio.setBounds(10, 11, 369, 28);

					contentPane.add(lblinicio);

					txtSigla = new ClrCuadroDeTexto(5,false,"Sigla",true);
					txtSigla.setBounds(41, 117, 306, 28);
					txtSigla.addFocusListener(new FocusListener() {
						
						@Override
						public void focusLost(FocusEvent e) {
							lb1.setVisible(false);
							
						}
						
						@Override
						public void focusGained(FocusEvent e) {
							if (lb1==null) {
								lb1 = new lbTMXSigla(txtSigla, esta, esta.principal);
							}
							
						}
					});
					contentPane.add(txtSigla);
				

					btnAceptar = new btnRedondo("Eliminar", new Rectangle(48, 172,121,50),5);
					btnAceptar.addKeyListener(new KeyAdapter() {

						@Override
						public void keyReleased(KeyEvent e) {
							if (e.getKeyCode() == KeyEvent.VK_ENTER) {
								eliminarTipoMuestra();
								}

							if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
								dispose();
							}
						}

					});
					btnAceptar.addMouseListener(new MouseListener() {

						@Override
						public void mouseReleased(MouseEvent e) {
							eliminarTipoMuestra();
							}

						@Override
						public void mousePressed(MouseEvent e) {
							

						}

						@Override
						public void mouseExited(MouseEvent e) {
							

						}

						@Override
						public void mouseEntered(MouseEvent e) {
							

						}

						@Override
						public void mouseClicked(MouseEvent e) {
							

						}
					});
					btnAceptar.setSelected(true);
					btnAceptar.setBounds(67, 180, 113, 50);
					contentPane.add(btnAceptar);

					btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50), 2);
					btnCancelar.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							if (lb1 != null)
								lb1.setVisible(false);
							if (lb2 != null)
								lb2.setVisible(false);

							dispose();
						}
					});
					btnCancelar.setBounds(190, 180, 129, 50);
					contentPane.add(btnCancelar);
					setContentPane(contentPane);

					getRootPane().setDefaultButton(btnAceptar);

					lblincorrectos = new clrLabel("", 1);
					lblincorrectos.setForeground(Colores.clrAlertaCamarada);
					lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
					lblincorrectos.setAlignmentX(0.5f);
					lblincorrectos.setBounds(10, 39, 358, 28);
					contentPane.add(lblincorrectos);

		

					txtNombre = new ClrCuadroDeTexto(100,false,"Nombre tipo de muestra",true);
					txtNombre.setBounds(41, 78, 306, 28);
					txtNombre.addFocusListener(new FocusListener() {
						
						@Override
						public void focusLost(FocusEvent arg0) {
							lb2.setVisible(false);
							
						}
						
						@Override
						public void focusGained(FocusEvent arg0) {
							if (lb2==null) {
								lb2 = new lbTMXNombre(txtNombre, esta, esta.principal);
							}
							
						}
					});
					

					contentPane.add(txtNombre);

					btnAceptar.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							eliminarTipoMuestra();
						}
						

					});

					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							btnAceptar.requestFocusInWindow();
						}
					});
					this.setVisible(true);
				
					
					
					


			}
		}

	}



	



	public void llenar(TipoMuestra muestra) {
		siglaAntigua=muestra.getSigla();
		idmuestra=muestra.getIdTipoMuestra();
		
		txtNombre.setText(muestra.getNombre());
		txtNombre.setForeground(Color.BLACK);
		
		txtSigla.setText(muestra.getSigla());
		txtSigla.setForeground(Color.BLACK);
		
		
	}

	public void  agregarTipoMuestra(){
		if (txtSigla.getText().equals("") || txtNombre.getText().equals("") ||txtSigla.getText().equals("Sigla")||txtNombre.getText().equals("Nombre tipo de muestra")) {
			lblincorrectos.setText("Faltan datos por llenar");
			
		} else {

			lblincorrectos.setText("");

		TipoMuestra TM= new TipoMuestra(-1, txtNombre.getText(), txtSigla.getText());
		
		if (conexion.getInstance().nuevoTipoMuestra(esta,TM)) {
			principal.registrarAccion("Creación de tipo de muestra: " + txtSigla.getText());
			dispose();
		}else{
			lblincorrectos.setText("Error  en la conexion con la base de datos");
		}
		}
	}
	
	public void modificarTipoMuestra(){
		if (txtSigla.getText().equals("") || txtNombre.getText().equals("") ||txtSigla.getText().equals("Sigla")||txtNombre.getText().equals("Nombre tipo de muestra")) {
			lblincorrectos.setText("Faltan datos por llenar");
			
		} else {

			lblincorrectos.setText("");

		TipoMuestra TM= new TipoMuestra(idmuestra, txtNombre.getText(), txtSigla.getText());
		
		if (conexion.getInstance().editarTipoMuestra(esta,TM,siglaAntigua)) {
			principal.registrarAccion("Modificación de  tipo de muestra: " + txtSigla.getText());
			dispose();
		}else{
			lblincorrectos.setText("Error  en la conexion con la base de datos");
		}
		}
	}
	
	
	public void eliminarTipoMuestra(){
		if (idmuestra==-1) {
			lblincorrectos.setText("No has escogido ningún tipo de muestra");
			
		} else {

			lblincorrectos.setText("");

		
		if (conexion.getInstance().eliminarTipoMuestra(esta,idmuestra)) {
			principal.registrarAccion("Eliminación de tipo de muestra: " + siglaAntigua);
			dispose();
		}else{
			lblincorrectos.setText("Error  en la conexion con la base de datos");
		}
		}
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}





	public void reportarError(String error){
		lblincorrectos.setText(error);
	}



	
	
	
	/*
	 * public static void main(String[] args) { OpUsuario op=new OpUsuario(null,
	 * new Usuario(1, "nn", "nn", "nn", new Color(125,134,21), 1800000, 1, 1,
	 * "111111111111111"), "Modificar"); op.setVisible(true); }
	 */

}
