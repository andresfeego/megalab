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

public class OpTarifas extends JDialog {

	private clrPanelBordes contentPane;
	private ClrCuadroDeTexto txtNombre;
	private clrLabel lblincorrectos;
	private Principal principal;
	private Usuario usuario;
	private clrLabel lblinicio;
	private btnRedondo btnAceptar;
	private btnRedondo btnCancelar;
	private ClrCuadroDeTexto passPrimera;
	private circuloUsuario btnRedondo_;
	private clrLabel clrLabel__1;
	private clrComboBox cbTiempo;
	private CirclePanel circlePanel;
	private clrCheckBox chbActivo;
	private OpTarifas esta;
	private lbTarifaXnombre lb2;
	private int estado = -1;
	private String nombreAntigua;
	private int idTarif=-1;
	private String porciAntigua;
	private ClrCuadroDeTexto txtRecalculo;
	

	

	/**
	 * Create the frame.
	 */
	public OpTarifas(Principal pri, Usuario usuario, String Accion) {
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
			lblinicio = new clrLabel("Agregar tarifa", 2, true);
			lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
			lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblinicio.setBounds(10, 11, 369, 28);

			contentPane.add(lblinicio);
		

			btnAceptar = new btnRedondo("Agregar", new Rectangle(48, 172,121,50),1);
			btnAceptar.addKeyListener(new KeyAdapter() {

				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						agregarTarifa();
						}

					if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						dispose();
					}
				}

			});
			
			btnAceptar.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					agregarTarifa();
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

		

			txtNombre = new ClrCuadroDeTexto(100,false,"Nombre",1);
			txtNombre.setBounds(38, 94, 306, 25);
			

			contentPane.add(txtNombre);
			
			txtRecalculo = new ClrCuadroDeTexto(3,true,"% de recalculo sobre la tarifa principal",1);
			txtRecalculo.setBounds(38, 131, 306, 28);
			contentPane.add(txtRecalculo);

		
		

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
				lblinicio = new clrLabel("Modificar tarifa", 2, true);
				lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
				lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
				lblinicio.setBounds(10, 11, 369, 28);

				contentPane.add(lblinicio);
			

				btnAceptar = new btnRedondo("Modificiar", new Rectangle(48, 172,121,50),3);
				btnAceptar.addKeyListener(new KeyAdapter() {

					@Override
					public void keyReleased(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							modificarTarifa();
							}

						if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
							dispose();
						}
					}

				});
				
				btnAceptar.addMouseListener(new MouseListener() {

					@Override
					public void mouseReleased(MouseEvent e) {
						modificarTarifa();

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

			

				txtNombre = new ClrCuadroDeTexto(100,false,"Nombre",true,1);
				txtNombre.setBounds(38, 94, 306, 28);
				txtNombre.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						lb2.setVisible(false);
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						if (lb2==null) {
							lb2=new lbTarifaXnombre(txtNombre, esta, esta.principal);
						}
						
					}
				});

				contentPane.add(txtNombre);
				
				txtRecalculo = new ClrCuadroDeTexto(3,true,"% de recalculo sobre la tarifa principal",1);
				txtRecalculo.setBounds(38, 131, 306, 28);
			
				contentPane.add(txtRecalculo);

			
			

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						btnAceptar.requestFocusInWindow();
					}
				});
				this.setVisible(true);
			
				
				
				
			} else {
				

				contentPane = new clrPanelBordes(true);
				contentPane.setLayout(null);
				lblinicio = new clrLabel("Eliminar tarifa", 2, true);
				lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
				lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
				lblinicio.setBounds(10, 11, 369, 28);

				contentPane.add(lblinicio);
			

				btnAceptar = new btnRedondo("Eliminar", new Rectangle(48, 172,121,50),5);
				btnAceptar.addKeyListener(new KeyAdapter() {

					@Override
					public void keyReleased(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							eliminarTarifa();
							}

						if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
							dispose();
						}
					}

				});
				
				btnAceptar.addMouseListener(new MouseListener() {

					@Override
					public void mouseReleased(MouseEvent e) {
						eliminarTarifa();

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

			

				txtNombre = new ClrCuadroDeTexto(100,false,"Nombre",true);
				txtNombre.setBounds(38, 94, 306, 28);
				txtNombre.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						lb2.setVisible(false);
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						if (lb2==null) {
							lb2=new lbTarifaXnombre(txtNombre, esta, esta.principal);
						}
						
					}
				});

				contentPane.add(txtNombre);
				
				txtRecalculo = new ClrCuadroDeTexto(3,true,"% de recalculo sobre la tarifa principal");
				txtRecalculo.setBounds(38, 131, 306, 25);
			
				contentPane.add(txtRecalculo);

			
			

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						btnAceptar.requestFocusInWindow();
					}
				});
				this.setVisible(true);
			
				
				
				
			}
		}

	}



	



	public void llenar(int  idTarifa,String nombre,String porci) {
		nombreAntigua=nombre;
		idTarif=idTarifa;
		porciAntigua=porci;
		
		txtNombre.setText(nombre);
		txtNombre.setForeground(Color.BLACK);
		
		txtRecalculo.setText(porci);
		txtRecalculo.setForeground(Color.black);
	
		
	}

	public void  agregarTarifa(){
		if (txtNombre.getText().equals("")||txtNombre.getText().equals(txtNombre.getLabel())||txtRecalculo.getText().equals(txtRecalculo.getLabel())) {
			if(txtNombre.getText().equals(txtNombre.getLabel())){lblincorrectos.setText("No has introducido un nombre para la tarifa");};
			if(txtRecalculo.getText().equals(txtRecalculo.getLabel())){lblincorrectos.setText("No has introducido % de recalculo  para esta tarifa");};
			
			
		} else {

			lblincorrectos.setText("");

				
		if (conexion.getInstance().nuevoTarifa(esta, txtRecalculo.getText(),txtNombre.getText())) {
			principal.registrarAccion("Creaci?n de tarifa: " + txtNombre.getText());
			dispose();
		}else{
			lblincorrectos.setText("Error  en la conexion con la base de datos");
		}
		}
	}
	
	public void modificarTarifa(){
		if (txtNombre.getText().equals(nombreAntigua)&&txtRecalculo.getText().equals(porciAntigua)) {
			lblincorrectos.setText("No has cambiado nada");
			
		} else {
			if (idTarif==-1) {
				lblincorrectos.setText("No has seleccionado ninguna tarifa");
			}else{
				lblincorrectos.setText("");

				
				if (conexion.getInstance().editarTarifa(esta, idTarif, txtNombre.getText(),txtRecalculo.getText(), nombreAntigua, porciAntigua)) {
					principal.registrarAccion("Modificaci?n de  tarifa: " + txtNombre.getText());
					dispose();
				}else{
					lblincorrectos.setText("Error  en la conexion con la base de datos");
				}
			}
		
		}
	}
	
	
	public void eliminarTarifa(){
		if (idTarif==-1) {
			lblincorrectos.setText("No has escogido ninguna tarifa");
			
		} else {

			lblincorrectos.setText("");

		
		if (conexion.getInstance().eliminarTarifa(esta,idTarif)) {
			principal.registrarAccion("Eliminaci?n de tarifa: " + nombreAntigua);
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

}
