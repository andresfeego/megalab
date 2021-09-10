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
import auxiliares.Usuario;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.Choice;

public class OpUsuario extends JDialog {

	private clrPanelBordes contentPane;
	private ClrCuadroDeTexto txtNombres;
	private clrLabel lblincorrectos;
	private Principal principal;
	private Usuario usuario;
	private clrLabel lblinicio;
	private btnRedondo btnAceptar;
	private btnRedondo btnCancelar;
	private ClrCuadroDeTexto txtusuario;
	private ClrCuadroDeTexto passPrimera;
	private circuloUsuario btnRedondo_;
	private clrLabel clrLabel__1;
	private clrComboBox cbTiempo;
	private CirclePanel circlePanel;
	private clrCheckBox chbActivo;
	private OpUsuario ou;
	private lbUsuarioXusuario lb1;
	private lbUsuarioXnombre lb;
	private int estado=-1;
	

	/**
	 * Create the frame.
	 */
	public OpUsuario(Principal pri ,Usuario usuario,String Accion) {
		super(pri,true);
		System.out.println();
		this.usuario=usuario;
		ou=this;
		this.principal=pri;
		setRootPaneCheckingEnabled(false);
		
		 
		if (Accion.endsWith("Agregar")) {
			construirAgregar();
		} else {
			
			if (Accion.equals("Modificar")) {

				construirModificar();
			
			} else {

					setBounds(100, 100, 389, 271);
					this.setUndecorated(true);
					this.setLocationRelativeTo(getParent()); 
					contentPane = new clrPanelBordes(true);
					contentPane.setLayout(null);
					lblinicio = new clrLabel("Activar / Desactivar Usuario",2,true);
					lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
					lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
					lblinicio.setBounds(10, 11, 369, 28);
					
					contentPane.add(lblinicio);
					
					
					 txtusuario = new ClrCuadroDeTexto(50,false,"Usuario",true);
					 txtusuario.setBounds(41, 117, 306, 28);
					 contentPane.add(txtusuario);
					 txtusuario.addFocusListener(new FocusListener() {
							@Override
							public void focusLost(FocusEvent e) {
								lb1.setVisible(false);
							}
							
							@Override
							public void focusGained(FocusEvent e) {
								if (lb1==null) {
									lb1=new lbUsuarioXusuario(txtusuario, ou,principal);
								}else {
									lb1.setVisible(true);
								}

							}
						});
					 				
					 
					 
					 btnAceptar = new btnRedondo("",new Rectangle(48, 172,121,50),7);
					 btnAceptar.addKeyListener(new KeyAdapter() {

						@Override
						public void keyReleased(KeyEvent e) {
							if (e.getKeyCode()==KeyEvent.VK_ENTER) {

								if (estado==-1) {
									lblincorrectos.setText("No se ha escogido ningun usuario");
								} else {
									if(conexion.acceso(ou.getUsuario().getUsuario())!=null){
									if (estado==1) {
										if (conexion.getInstance().activaciones(ou.usuario, "Activar")) {
											principal.registrarAccion("Activacion de usuario: "+ou.getUsuario().getUsuario());
											dispose();
										}else{
											lblincorrectos.setText("Error  en la conexion con la base de datos");
										}
									} else {
										if (conexion.getInstance().activaciones(ou.usuario, "Desactivar")) {
											principal.registrarAccion("Desactivacion de usuario: "+ou.getUsuario().getUsuario());
											dispose();
										}
										else{
											lblincorrectos.setText("Error  en la conexion con la base de datos");
										}
									}

								}}
							
							} 
							
			if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
				dispose();
			} 
						}
					
					});
					 btnAceptar.addMouseListener(new MouseListener() {
						
						@Override
						public void mouseReleased(MouseEvent e) {
							if (estado==-1) {
								lblincorrectos.setText("No se ha escogido ningun usuario");
							} else {
								if (estado==1) {
									if (conexion.getInstance().activaciones(ou.usuario, "Activar")) {
										principal.registrarAccion("Activacion de usuario: "+ou.getUsuario().getUsuario());
										dispose();
									}else{
										lblincorrectos.setText("Error  en la conexion con la base de datos");
									}
								} else {
									if (conexion.getInstance().activaciones(ou.usuario, "Desactivar")) {
										principal.registrarAccion("Desactivacion de usuario: "+ou.getUsuario().getUsuario());
										dispose();
									}else{
										lblincorrectos.setText("Error  en la conexion con la base de datos");
									}
								}

							}
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
					 btnAceptar.setBounds(41, 178, 135, 50);
					 contentPane.add(btnAceptar);
					
					
					
					btnCancelar = new btnRedondo("Cancelar",new Rectangle(237, 172,121,50),2);
					btnCancelar.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							if(lb!=null)lb.setVisible(false);
							if(lb1!=null)lb1.setVisible(false);
							
							dispose();
						}
					});
					btnCancelar.setBounds(218, 178, 129, 50);
					contentPane.add(btnCancelar);
					setContentPane(contentPane);
					
					getRootPane().setDefaultButton(btnAceptar);
					
					lblincorrectos = new clrLabel("", 1);
					lblincorrectos.setForeground(Colores.clrAlertaCamarada);
					lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
					lblincorrectos.setAlignmentX(0.5f);
					lblincorrectos.setBounds(10, 39, 358, 28);
					contentPane.add(lblincorrectos);
					
				
					
					txtNombres = new ClrCuadroDeTexto(75,false,"Nombre completo",true);
					txtNombres.setBounds(41, 78, 306, 28);
					txtNombres.addFocusListener(new FocusListener() {
						@Override
						public void focusLost(FocusEvent e) {
							lb.setVisible(false);
						}
						
						@Override
						public void focusGained(FocusEvent e) {
							
							if (lb==null) {
							lb=new lbUsuarioXnombre(txtNombres, ou,principal);
							}else {
								lb.setVisible(true);
							}

						}
					});
					
					contentPane.add(txtNombres);
					
					
					
					btnAceptar.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							if (txtusuario.getText().equals("")||txtNombres.getText().equals("")||passPrimera.getText().equals("")) {
								lblincorrectos.setText("Faltan datos por llenar");
							}else {
								
								if (crearUsuario()) {
									principal.registrarAccion("Creacion de usuario: "+txtusuario.getText());
									dispose();
								}else{
									lblincorrectos.setText("Error  en la conexion con la base de datos");
								}
							}
						}

						
					});
					
					SwingUtilities.invokeLater( new Runnable() 
			        { 
			        public void run() 
			            { 
			            btnAceptar.requestFocusInWindow(); 
			        } 
			    }); 
						
					this.setVisible(true);
					
			}
		}
		
	}
	
	public void construirAgregar(){

		
		setRootPaneCheckingEnabled(false);
		
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 389, 590);
		/*  javax.swing.plaf.InternalFrameUI ifu= this.getUI();
		  ((javax.swing.plaf.basic.BasicInternalFrameUI)ifu).setNorthPane(null);  */
		  
		  //___________________________________________________________________________________________
		  
		 this.setUndecorated(true);
		 this.setLocationRelativeTo(getParent()); 
		 
		contentPane = new clrPanelBordes(true);
		//setContentPane(contentPane);
		contentPane.setLayout(null);
		lblinicio = new clrLabel("Agregar Usuario",2,true);
		lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblinicio.setBounds(10, 11, 369, 28);
		
		contentPane.add(lblinicio);
		
		
		btnAceptar = new btnRedondo("Agregar",new Rectangle(48, 172,121,50),1);
		
		btnAceptar.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					if (txtusuario.getText().equals("")||txtNombres.getText().equals("")||passPrimera.getText().equals("")) {
						lblincorrectos.setText("Faltan datos por llenar");
					}else {
						
						if (crearUsuario()) {
							principal.registrarAccion("Creacion de usuario: "+txtusuario.getText());
							dispose();
						}else{
							lblincorrectos.setText("Error  en la conexion con la base de datos");
						}
					}
				} 
				
if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
	dispose();
} 
			}
		
		});
		
		
		btnAceptar.setSelected(true);
		btnAceptar.setBounds(43, 490,121,50);
		contentPane.add(btnAceptar);
		
		
		
		btnCancelar = new btnRedondo("Cancelar",new Rectangle(237, 172,121,50),2);
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(220, 490, 129, 50);
		contentPane.add(btnCancelar);
		setContentPane(contentPane);
		
		getRootPane().setDefaultButton(btnAceptar);
		
		lblincorrectos = new clrLabel("", 1);
		lblincorrectos.setForeground(Colores.clrAlertaCamarada);
		lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
		lblincorrectos.setAlignmentX(0.5f);
		lblincorrectos.setBounds(10, 39, 358, 28);
		contentPane.add(lblincorrectos);
		
		 txtusuario = new ClrCuadroDeTexto(50,false,"Usuario");
		txtusuario.setBounds(43, 78, 306, 28);
		contentPane.add(txtusuario);
		
	
		
		passPrimera = new ClrCuadroDeTexto(50,false,"Contrase\u00F1a por primera vez");
		passPrimera.setBounds(43, 117, 306, 28);
		contentPane.add(passPrimera);
		
		txtNombres = new ClrCuadroDeTexto(75,false,"Nombre completo");
		txtNombres.setBounds(43, 156, 306, 28);
		contentPane.add(txtNombres);
		
		cbTiempo = new clrComboBox(new String[] {"1", "2", "5", "10", "15", "20", "25", "30", "45", "60"},0);
		
		cbTiempo.setBounds(278, 195, 71, 28);
		contentPane.add(cbTiempo);
		
		clrLabel__1 = new clrLabel("Tiempo de inactividad (minutos)", 1);
		clrLabel__1.setHorizontalAlignment(SwingConstants.LEFT);
		clrLabel__1.setForeground(Colores.clrSecundario);
		clrLabel__1.setAlignmentX(0.5f);
		clrLabel__1.setBounds(43, 197, 274, 28);
		contentPane.add(clrLabel__1);
		
		chbActivo = new clrCheckBox("Usuario activo");
		chbActivo.setSelected(true);
		chbActivo.setFocusPainted(false);
		chbActivo.setBounds(43, 236, 135, 50);
		contentPane.add(chbActivo);
		
		circlePanel = new CirclePanel();
		circlePanel.setColor(new Color(255, 255, 255));
		circlePanel.setBounds(43, 297, 184, 160);
		circlePanel.setBackground(Color.red);
		circlePanel.repaint();

		contentPane.add(circlePanel);
		
		final circuloUsuario btnRedondo_ = new circuloUsuario(new Usuario(1, "", "", "", Color.WHITE, 1, 1, 1,0, ""), new Rectangle(48, 172,121,50));
		btnRedondo_.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRedondo_.setSelected(true);
		btnRedondo_.setBounds(278, 346, 58, 50);
		contentPane.add(btnRedondo_);
		circlePanel.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				btnRedondo_.getUsuario().setColor(circlePanel.getColor());
				btnRedondo_.repaint();
			}
		});
		
		
		
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (txtusuario.getText().equals("")||txtNombres.getText().equals("")||passPrimera.getText().equals("")) {
					lblincorrectos.setText("Faltan datos por llenar");
				}else {
					
					if (crearUsuario()) {
						principal.registrarAccion("Creacion de usuario: "+txtusuario.getText());
						dispose();
					}else{
						lblincorrectos.setText("Error  en la conexion con la base de datos");
					}
				}
			}

			
		});
		
		SwingUtilities.invokeLater( new Runnable() 
        { 
        public void run() 
            { 
            btnAceptar.requestFocusInWindow(); 
        } 
    }); 
			
		this.setVisible(true);
		
	
	
		
	}
	
	
	public void construirModificar(){
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 389, 500);
	/*  javax.swing.plaf.InternalFrameUI ifu= this.getUI();
	  ((javax.swing.plaf.basic.BasicInternalFrameUI)ifu).setNorthPane(null);  */
	  
	  //___________________________________________________________________________________________
	  
	 this.setUndecorated(true);
	 this.setLocationRelativeTo(getParent()); 

		
		contentPane = new clrPanelBordes(true);
		//setContentPane(contentPane);
		contentPane.setLayout(null);
		lblinicio = new clrLabel("Modificar Usuario",2,true);
		lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblinicio.setBounds(10, 11, 369, 28);
		
		contentPane.add(lblinicio);
		
		
		btnAceptar = new btnRedondo("Modificar",new Rectangle(48, 172,121,50),3);
	
		
		btnAceptar.setSelected(true);
		btnAceptar.setBounds(41, 412, 135, 50);
		contentPane.add(btnAceptar);
		
		
		
		btnCancelar = new btnRedondo("Cancelar",new Rectangle(237, 172,121,50),2);
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(218, 412, 129, 50);
		contentPane.add(btnCancelar);
		setContentPane(contentPane);
		
		getRootPane().setDefaultButton(btnAceptar);
		
		lblincorrectos = new clrLabel("", 1);
		lblincorrectos.setForeground(Colores.clrAlertaCamarada);
		lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
		lblincorrectos.setAlignmentX(0.5f);
		lblincorrectos.setBounds(10, 39, 358, 28);
		contentPane.add(lblincorrectos);
		
	
		
		txtNombres = new ClrCuadroDeTexto(75,false,"Nombre completo");
		txtNombres.setText(this.usuario.getNombre());
		txtNombres.setForeground(Color.BLACK);
		txtNombres.setBounds(41, 78, 306, 28);
		contentPane.add(txtNombres);
		
		cbTiempo = new clrComboBox(new String[] {"1", "2", "5", "10", "15", "20", "25", "30", "45", "60"},0);
		cbTiempo.setSelectedItem((this.usuario.getTiempoI()/60000)+"");
		cbTiempo.setBounds(276, 117, 71, 28);
		contentPane.add(cbTiempo);
		
		clrLabel__1 = new clrLabel("Tiempo de inactividad (minutos)", 1);
		clrLabel__1.setHorizontalAlignment(SwingConstants.LEFT);
		clrLabel__1.setForeground(Colores.clrSecundario);
		clrLabel__1.setAlignmentX(0.5f);
		clrLabel__1.setBounds(41, 119, 274, 28);
		contentPane.add(clrLabel__1);
		
		circlePanel = new CirclePanel();
		circlePanel.setBounds(41, 219, 184, 160);
		circlePanel.setBackground(this.usuario.getColor());
		circlePanel.setColor(this.usuario.getColor());
		circlePanel.repaint();

		contentPane.add(circlePanel);
		
		final circuloUsuario btnRedondo_ = new circuloUsuario(new Usuario(1, "", "", "", Color.WHITE, 1, 1, 1,0, ""), new Rectangle(48, 172,121,50));
		btnRedondo_.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRedondo_.setSelected(true);
		btnRedondo_.setBounds(276, 268, 58, 50);
		btnRedondo_.getUsuario().setColor(circlePanel.getColor());
		contentPane.add(btnRedondo_);
		circlePanel.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				btnRedondo_.getUsuario().setColor(circlePanel.getColor());
				btnRedondo_.repaint();
			}
		});
		
		
		
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (txtNombres.getText().equals("")) {
					lblincorrectos.setText("Faltan datos por llenar");
				}else {
					
				modificarUsuario();
				}
			}

			
		});
		
		SwingUtilities.invokeLater( new Runnable() 
        { 
        public void run() 
            { 
            btnAceptar.requestFocusInWindow(); 
        } 
    }); 
			
		this.setVisible(true);
		
	}
	
	public void listaBusquedas(ClrCuadroDeTexto cuadroTexto){
		int activo=0;
		
		clrScrollBar scrollPane = new clrScrollBar();
		scrollPane.setBounds((int)(cuadroTexto.getBounds().getX()+cuadroTexto.getBounds().getWidth()+20),(int)(cuadroTexto.getBounds().getY()),(int)Math.floor(cuadroTexto.getBounds().getWidth()), 150);
		scrollPane.transferFocus();
		this.contentPane.add(scrollPane);
		this.setBounds((int)this.getBounds().getX(),(int)this.getBounds().getY(),(int)this.getBounds().getWidth()+500,(int)this.getBounds().getHeight());
		
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"item 1", "item 1", "item 1", "item 1", "item 1", "item 1", "item 1", "item 1", "item 1", "item 1", "item 1"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		if(activo==0){
		list.setBounds((int)Math.floor(ou.getBounds().getX()+cuadroTexto.getBounds().getX()),(int)Math.floor(ou.getBounds().getY()+cuadroTexto.getBounds().getHeight()+cuadroTexto.getBounds().getY()),(int)Math.floor(cuadroTexto.getBounds().getWidth()), 150);
		activo=1;
		}
		scrollPane.setViewportView(list);
		list.setFocusTraversalKeysEnabled(false);
		list.setFocusCycleRoot(true);
		
	}
	
	
	
	public void  modificarUsuario(){
		
		conexion cn=conexion.getInstance();
		
		
		try {
			
			if (cn.editarUsuario(usuario, txtNombres.getText(), circlePanel.getColor(),Integer.parseInt(cbTiempo.getSelectedItem().toString()) *60000)) {
				usuario.setNombre(txtNombres.getText());
				usuario.setColor(circlePanel.getColor());
				usuario.setTiempoI(Integer.parseInt(cbTiempo.getSelectedItem().toString()) *60000);
				principal.crearMenu(usuario);
				principal.registrarAccion("Modificacion de usuario: "+txtNombres.getText());
				dispose();
			}else{
				lblincorrectos.setText("Error  en la conexion con la base de datos");
			}
			
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
			

		} catch (SQLException e) {
			
			e.printStackTrace();
		

		}

		
	}
	
	public boolean crearUsuario(){
		
		conexion cn=conexion.getInstance();
		int act=0;
		if(chbActivo.isSelected())
			act=1;
		
		try {
			
			if (cn.nuevoUsuario(this, txtusuario.getText(), passPrimera.getText(), txtNombres.getText(), Integer.parseInt(cbTiempo.getSelectedItem().toString()), 1, act, circlePanel.getColor().getRed(), circlePanel.getColor().getGreen(),  circlePanel.getColor().getBlue(),circlePanel.getColor().getAlpha())) {
							return true;
			}else{
			lblincorrectos.setText("Usuario ya existe");
				return false;

			}

		} catch (NumberFormatException e) {
			
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;

		}
		
	}

	public ClrCuadroDeTexto getTxtNombres() {
		return txtNombres;
	}

	public ClrCuadroDeTexto getTxtusuario() {
		return txtusuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void llenar(Usuario usu) {
		txtNombres.setText(usu.getNombre());
		txtusuario.setText(usu.getUsuario());
		txtNombres.setForeground(Color.black);
		txtusuario.setForeground(Color.black);
		ou.usuario=usu;
		if (usu.getActivo()==0) {
			contentPane.remove(btnAceptar);
			btnAceptar=new btnRedondo("Activar",new Rectangle(48, 172,121,50),10);
			estado=1;
			btnAceptar.setBounds(41, 178, 135, 50);
			btnAceptar.addKeyListener(new KeyAdapter() {

				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode()==KeyEvent.VK_ENTER) {

						if (estado==-1) {
							lblincorrectos.setText("No se ha escogido ningun usuario");
						} else {
							if (estado==1) {
								if (conexion.getInstance().activaciones(ou.usuario, "Activar")) {
									principal.registrarAccion("Activacion de usuario: "+ou.getUsuario().getUsuario());
									dispose();
								}else{
									lblincorrectos.setText("Error  en la conexion con la base de datos");
								}
							} else {
								if (conexion.getInstance().activaciones(ou.usuario, "Desactivar")) {
									principal.registrarAccion("Desactivacion de usuario: "+ou.getUsuario().getUsuario());
									dispose();
								}else{
									lblincorrectos.setText("Error  en la conexion con la base de datos");
								}
							}

						}
					
					} 
					
	if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
		dispose();
	} 
				}
			
			});
			btnAceptar.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					if (estado==-1) {
						lblincorrectos.setText("No se ha escogido ningun usuario");
					} else {
						if (estado==1) {
							if (conexion.getInstance().activaciones(ou.usuario, "Activar")) {
								principal.registrarAccion("Activacion de usuario: "+ou.getUsuario().getUsuario());
								dispose();
							}else{
								lblincorrectos.setText("Error  en la conexion con la base de datos");
							}
						} else {
							if (conexion.getInstance().activaciones(ou.usuario, "Desactivar")) {
								principal.registrarAccion("Desactivacion de usuario: "+ou.getUsuario().getUsuario());
								dispose();
							}else{
								lblincorrectos.setText("Error  en la conexion con la base de datos");
							}
						}

					}
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
			contentPane.add(btnAceptar);
			contentPane.updateUI();
		} 	else {
			contentPane.remove(btnAceptar);
			btnAceptar=new btnRedondo("Desactivar",new Rectangle(48, 172,121,50),11);
			btnAceptar.setBounds(41, 178, 135, 50);
			estado=0;
			btnAceptar.addKeyListener(new KeyAdapter() {

				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode()==KeyEvent.VK_ENTER) {

						if (estado==-1) {
							lblincorrectos.setText("No se ha escogido ningun usuario");
						} else {
							if (estado==1) {
								if (conexion.getInstance().activaciones(ou.usuario, "Activar")) {
									principal.registrarAccion("Activacion de usuario: "+ou.getUsuario().getUsuario());
									dispose();
								}else{
									lblincorrectos.setText("Error  en la conexion con la base de datos");
								}
							} else {
								if (conexion.getInstance().activaciones(ou.usuario, "Desactivar")) {
									principal.registrarAccion("Desactivacion de usuario: "+ou.getUsuario().getUsuario());
									dispose();
								}else{
									lblincorrectos.setText("Error  en la conexion con la base de datos");
								}
							}

						}
					
					} 
					
	if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
		dispose();
	} 
				}
			
			});
			btnAceptar.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					if (estado==-1) {
						lblincorrectos.setText("No se ha escogido ningun usuario");
					} else {
						if (estado==1) {
							if (conexion.getInstance().activaciones(ou.usuario, "Activar")) {
								principal.registrarAccion("Activacion de usuario: "+ou.getUsuario().getUsuario());
								dispose();
							}else{
								lblincorrectos.setText("Error  en la conexion con la base de datos");
							}
						} else {
							if (conexion.getInstance().activaciones(ou.usuario, "Desactivar")) {
								principal.registrarAccion("Desactivacion de usuario: "+ou.getUsuario().getUsuario());
								dispose();
							}else{
								lblincorrectos.setText("Error  en la conexion con la base de datos");
							}
						}

					}
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
			contentPane.add(btnAceptar);
			contentPane.updateUI();
		}
	}
	
	
	/*public static void main(String[] args) {
		OpUsuario op=new OpUsuario(null, new Usuario(1, "nn", "nn", "nn", new Color(125,134,21), 1800000, 1, 1, "111111111111111"), "Modificar");
		op.setVisible(true);
	}*/
	
	public void reportarError(String error){
		lblincorrectos.setText(error);
	}
}
