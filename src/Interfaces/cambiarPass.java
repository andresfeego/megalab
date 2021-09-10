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
import interfaces_Modificadas.clrScrollBar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import java.awt.Component;

import javax.swing.SwingConstants;

import auxiliares.Usuario;
import conexion.conexion;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class cambiarPass extends JDialog {

	private clrPanelBordes contentPane;
	private clrPassword passNueva;
	private clrPassword passAntig;
	private clrPassword passNuevaRep;
	private clrLabel lblincorrectos;
	private btnRedondo btnAceptar ;
	private Principal principal;
	private Usuario usuario;

	/**
	 * Create the frame.
	 */
	public cambiarPass(Principal pri ,Usuario usuario) {
		
		super(pri,true);
		this.usuario=usuario;
		this.principal=pri;
		setRootPaneCheckingEnabled(false);
		
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 367, 462);
		/*  javax.swing.plaf.InternalFrameUI ifu= this.getUI();
		  ((javax.swing.plaf.basic.BasicInternalFrameUI)ifu).setNorthPane(null);  */
		  
		  //___________________________________________________________________________________________
		  
		 this.setUndecorated(true);
		 this.setLocationRelativeTo(getParent()); 
		 
		contentPane = new clrPanelBordes(true);
		//setContentPane(contentPane);
		contentPane.setLayout(null);
		clrLabel lblinicio = new clrLabel("Cambiar contraseña",2,true);
		lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblinicio.setBounds(10, 11, 358, 28);
		contentPane.add(lblinicio);
		
		
		
		
		
		
		
		btnAceptar = new btnRedondo("Cambiar",new Rectangle(48, 172,121,50),3);
		btnAceptar.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {

					
					if (passAntig.getText().equals("123456789")||passAntig.getText().equals("")) {
						lblincorrectos.setText("No has ingresado la contraseña antigua");
					} else {
						if (passNueva.getText().equals("123456789")||passNueva.getText().equals("")) {
							lblincorrectos.setText("No has ingresado la contraseña nueva");

						} else {
							if (passNuevaRep.getText().equals("123456789")||passNuevaRep.getText().equals("")) {
								lblincorrectos.setText("Repite la contraseña nueva");

							} else {
								if (passAntig.getText().equals(cambiarPass.this.usuario.getPass())) {
									if (passNueva.getText().equals(passNuevaRep.getText())) {
										//metodo cambiar
											if (conexion.getInstance().cambiarPass(passNueva.getText())) {
												principal.registrarAccion("Se ha cambiado la contraseña para este usuario");
												dispose();
											}
											
									} else {
										lblincorrectos.setText("Las contraseñas nuevas no coinciden");

									}

								} else {
									lblincorrectos.setText("Contraseña antigua incorrecta");

								}

							}
						}
						

					}
				
				} 
				
if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
	dispose();
} 
			}
		
		});
		
		
		btnAceptar.setSelected(true);
		btnAceptar.setBounds(48, 401,121,50);
		contentPane.add(btnAceptar);
		
		passNueva = new clrPassword(50);
		passNueva.setFocusTraversalKeysEnabled(false);
		passNueva.setFocusCycleRoot(true);
		passNueva.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {

					
					if (passAntig.getText().equals("123456789")||passAntig.getText().equals("")) {
						lblincorrectos.setText("No has ingresado la contraseña antigua");
					} else {
						if (passNueva.getText().equals("123456789")||passNueva.getText().equals("")) {
							lblincorrectos.setText("No has ingresado la contraseña nueva");

						} else {
							if (passNuevaRep.getText().equals("123456789")||passNuevaRep.getText().equals("")) {
								lblincorrectos.setText("Repite la contraseña nueva");

							} else {
								if (passAntig.getText().equals(cambiarPass.this.usuario.getPass())) {
									if (passNueva.getText().equals(passNuevaRep.getText())) {
										//metodo cambiar
											if (conexion.getInstance().cambiarPass(passNueva.getText())) {
												principal.registrarAccion("Se ha cambiado la contraseña para este usuario");
												dispose();
											}
											
									} else {
										lblincorrectos.setText("Las contraseñas nuevas no coinciden");

									}

								} else {
									lblincorrectos.setText("Contraseña antigua incorrecta");

								}

							}
						}
						

					}
				
				} 
				if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
					
					dispose();
					
				} 
			}
		
		});
		passNueva.setBounds(48, 225, 274, 28);
		contentPane.add(passNueva);
		
		
		
		btnRedondo btnCancelar = new btnRedondo("Cancelar",new Rectangle(237, 172,121,50),2);
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(184, 401, 129, 50);
		contentPane.add(btnCancelar);
		setContentPane(contentPane);
		
		getRootPane().setDefaultButton(btnAceptar);
		
		lblincorrectos = new clrLabel("", 1);
		lblincorrectos.setForeground(Color.RED);
		lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
		lblincorrectos.setAlignmentX(0.5f);
		lblincorrectos.setBounds(10, 39, 358, 28);
		contentPane.add(lblincorrectos);
		
		clrLabel clrLabel_ = new clrLabel("Contrase\u00F1a antigua", 1);
		clrLabel_.setForeground(Colores.clrPrincipal);
		clrLabel_.setHorizontalAlignment(SwingConstants.LEFT);
		clrLabel_.setAlignmentX(0.5f);
		clrLabel_.setBounds(48, 88, 274, 28);
		contentPane.add(clrLabel_);
		
		  passAntig = new clrPassword(50);
		  passAntig.setFocusTraversalKeysEnabled(false);
		  
		  
		  passAntig.addFocusListener(new FocusAdapter() {
		  	
		  
		  	@Override
		  	public void focusLost(FocusEvent e) {
		  		contentPane.updateUI();
		  	}
		  });
		passAntig.setBounds(48, 127, 274, 28);
		contentPane.add(passAntig);
		
		clrLabel clrLabel__1 = new clrLabel("Contrase\u00F1a nueva", 1);
		clrLabel__1.setHorizontalAlignment(SwingConstants.LEFT);
		clrLabel__1.setForeground(Colores.clrPrincipal);
		clrLabel__1.setAlignmentX(0.5f);
		clrLabel__1.setBounds(48, 186, 274, 28);
		contentPane.add(clrLabel__1);
		
		clrLabel clrLabel__2 = new clrLabel("Repetir contrase\u00F1a nueva", 1);
		clrLabel__2.setHorizontalAlignment(SwingConstants.LEFT);
		clrLabel__2.setForeground(Colores.clrPrincipal);
		clrLabel__2.setAlignmentX(0.5f);
		clrLabel__2.setBounds(48, 264, 274, 28);
		contentPane.add(clrLabel__2);
		
		passNuevaRep = new clrPassword(50);
		passNuevaRep.setBounds(48, 303, 274, 28);
		contentPane.add(passNuevaRep);
		
		
		
		
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {

				
				if (passAntig.getText().equals("123456789")||passAntig.getText().equals("")) {
					lblincorrectos.setText("No has ingresado la contraseña antigua");
				} else {
					if (passNueva.getText().equals("123456789")||passNueva.getText().equals("")) {
						lblincorrectos.setText("No has ingresado la contraseña nueva");

					} else {
						if (passNuevaRep.getText().equals("123456789")||passNuevaRep.getText().equals("")) {
							lblincorrectos.setText("Repite la contraseña nueva");

						} else {
							if (passAntig.getText().equals(cambiarPass.this.usuario.getPass())) {
								if (passNueva.getText().equals(passNuevaRep.getText())) {
									//metodo cambiar
										if (conexion.getInstance().cambiarPass(passNueva.getText())) {
											principal.registrarAccion("Se ha cambiado la contraseña para este usuario");
											dispose();
										}
										
								} else {
									lblincorrectos.setText("Las contraseñas nuevas no coinciden");

								}

							} else {
								lblincorrectos.setText("Contraseña antigua incorrecta");

							}

						}
					}
					

				}
			
			}

			
		});
		
		passNuevaRep.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {

					
					if (passAntig.getText().equals("123456789")||passAntig.getText().equals("")) {
						lblincorrectos.setText("No has ingresado la contraseña antigua");
					} else {
						if (passNueva.getText().equals("123456789")||passNueva.getText().equals("")) {
							lblincorrectos.setText("No has ingresado la contraseña nueva");

						} else {
							if (passNuevaRep.getText().equals("123456789")||passNuevaRep.getText().equals("")) {
								lblincorrectos.setText("Repite la contraseña nueva");

							} else {
								if (passAntig.getText().equals(cambiarPass.this.usuario.getPass())) {
									if (passNueva.getText().equals(passNuevaRep.getText())) {
										//metodo cambiar
											if (conexion.getInstance().cambiarPass(passNueva.getText())) {
												principal.registrarAccion("Se ha cambiado la contraseña para este usuario");
												dispose();
											}
											
									} else {
										lblincorrectos.setText("Las contraseñas nuevas no coinciden");

									}

								} else {
									lblincorrectos.setText("Contraseña antigua incorrecta");

								}

							}
						}
						

					}
				
				} 
				if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
					
					dispose();
					
				} 
			}
		
		});
		
		passAntig.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {

					
					if (passAntig.getText().equals("123456789")||passAntig.getText().equals("")) {
						lblincorrectos.setText("No has ingresado la contraseña antigua");
					} else {
						if (passNueva.getText().equals("123456789")||passNueva.getText().equals("")) {
							lblincorrectos.setText("No has ingresado la contraseña nueva");

						} else {
							if (passNuevaRep.getText().equals("123456789")||passNuevaRep.getText().equals("")) {
								lblincorrectos.setText("Repite la contraseña nueva");

							} else {
								if (passAntig.getText().equals(cambiarPass.this.usuario.getPass())) {
									if (passNueva.getText().equals(passNuevaRep.getText())) {
										//metodo cambiar
											if (conexion.getInstance().cambiarPass(passNueva.getText())) {
												principal.registrarAccion("Se ha cambiado la contraseña para este usuario");
												dispose();
											}
											
									} else {
										lblincorrectos.setText("Las contraseñas nuevas no coinciden");

									}

								} else {
									lblincorrectos.setText("Contraseña antigua incorrecta");

								}

							}
						}
						

					}
				
				} 
				if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
					
					dispose();
					
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
		
	}
	
	
/*	public void crearBusqueda(String Usuario, String palabra){
		
		scrollPane = new clrScrollBar();
		scrollPane.setBounds(48, 155, 274, 150);
		scrollPane.transferFocus();
		contentPane.add(scrollPane);
		
		final JList list = new JList();
		list.transferFocus();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"item 1", "item 1", "item 1", "item 1", "item 1", "item 1", "item 1", "item 1", "item 1", "item 1", "item 1"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				passAntig.setText(list.getSelectedValue().toString());
				
			}

			
		});
		
		
		list.setBounds(0, 0, 274, 150);
		scrollPane.setViewportView(list);
	}*/
	
	
	
	

}
