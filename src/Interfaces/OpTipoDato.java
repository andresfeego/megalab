package Interfaces;

import interfaces_Modificadas.ClrCuadroDeTexto;
import interfaces_Modificadas.Colores;
import interfaces_Modificadas.btnRedondo;
import interfaces_Modificadas.circuloUsuario;
import interfaces_Modificadas.clrCheckBox;
import interfaces_Modificadas.clrComboBox;
import interfaces_Modificadas.clrLabel;
import interfaces_Modificadas.clrPanelBordes;
import interfaces_Modificadas.clrScrollBar;
import interfaces_Modificadas.clrtextpane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.SwingConstants;

import ago.beans.CirclePanel;
import auxiliares.Bacteriologo;
import auxiliares.GruposEmpresas;
import auxiliares.Medico;
import auxiliares.Paciente;
import auxiliares.TipoDato;
import auxiliares.Usuario;
import conexion.conexion;
import conexion.conexionCombos;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import datechooser.beans.DateChooserCombo;

import com.sun.org.apache.bcel.internal.generic.LLOAD;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

public class OpTipoDato extends JDialog{
	private clrPanelBordes contentPane;
	private clrLabel lblincorrectos;
	private Principal principal;
	private Usuario usuario;
	private clrLabel lblinicio;
	private circuloUsuario btnRedondo_;
	private String accion; 
	private OpTipoDato esta;
	private int idtipo;
	private String nombreAntiguo;
	private ClrCuadroDeTexto txtNombreTD;
	private ClrCuadroDeTexto clrSiglaTD;
	private btnRedondo btnCancelar;
	private btnRedondo btnAceptar;
	private lbTDXnombre lb1;
	private lbTDXSigla lb2;
	private TipoDato tDatoActual; 
	
	public OpTipoDato(Principal principal, Usuario usuario, String Accion) {
		super(principal,true);
		this.principal=principal;
		this.usuario=usuario;
		this.accion=Accion;
		this.esta=this;

		//this.setDefaultLookAndFeelDecorated(false);


		setBounds(100, 100, 404, 274);
		this.setUndecorated(true);
		this.setLocationRelativeTo(getParent()); 
		contentPane = new clrPanelBordes(true);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		


		
	
		
		
		
		
	

		
		
		
		if(accion.equals("Agregar")){



			lblinicio = new clrLabel("Ingresar tipo de datos",2,true);
			lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
			lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblinicio.setBounds(10, 11, 384, 28);
			contentPane.add(lblinicio);
					
			lblincorrectos = new clrLabel("", 1);
			lblincorrectos.setForeground(Colores.clrAlertaCamarada);
			lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
			lblincorrectos.setAlignmentX(0.5f);
			lblincorrectos.setBounds(10, 38, 384, 28);
			contentPane.add(lblincorrectos);
			
		
			
			txtNombreTD = new ClrCuadroDeTexto(100,false,"Nombre",1);
			txtNombreTD.setBounds(44, 77, 308, 20);
			txtNombreTD.setFocusCycleRoot(false);
			contentPane.add(txtNombreTD);
			
			btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50),2);
			btnCancelar.setBounds(204, 170, 136, 50);
			btnCancelar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					
				}
			});
			contentPane.add(btnCancelar);
			
			btnAceptar = new btnRedondo("Ingresar", new Rectangle(48, 172,121,50),1);
			btnAceptar.setSelected(true);
			btnAceptar.setBounds(56, 170, 138, 50);
			btnAceptar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
				ingresarTD();
					
				}
			});
			contentPane.add(btnAceptar);
			
			clrSiglaTD = new ClrCuadroDeTexto(6,false,"Sigla",1);
			clrSiglaTD.setFocusCycleRoot(false);
			clrSiglaTD.setBounds(44, 108, 150, 20);
			contentPane.add(clrSiglaTD);
			
			SwingUtilities.invokeLater( new Runnable() 
	        { 
	        public void run() 
	            { 
	            btnAceptar.requestFocusInWindow(); 
	        } 
	    }); 
			
			this.setVisible(true);
		
			
		
			
				

			
			
		}else{
			if (accion.equals("Modificar")) {



				lblinicio = new clrLabel("Modificar tipo de datos",2,true);
				lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
				lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
				lblinicio.setBounds(10, 11, 384, 28);
				contentPane.add(lblinicio);
						
				lblincorrectos = new clrLabel("", 1);
				lblincorrectos.setForeground(Colores.clrAlertaCamarada);
				lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
				lblincorrectos.setAlignmentX(0.5f);
				lblincorrectos.setBounds(10, 38, 384, 28);
				contentPane.add(lblincorrectos);
				
				
				
				txtNombreTD = new ClrCuadroDeTexto(100,false,"Nombre",true,1);
				txtNombreTD.setBounds(44, 77, 308, 20);
				txtNombreTD.setFocusCycleRoot(false);
				txtNombreTD.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						
						lb1.setVisible(false);
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						
						if (lb1==null) {
							lb1=new lbTDXnombre(txtNombreTD, esta, esta.principal);
						}
					}
				});
				contentPane.add(txtNombreTD);
				
				btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50),2);
				btnCancelar.setBounds(204, 170, 136, 50);
				btnCancelar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						
					}
				});
				contentPane.add(btnCancelar);
				
				btnAceptar = new btnRedondo("Modificar", new Rectangle(48, 172,121,50),3);
				btnAceptar.setSelected(true);
				btnAceptar.setBounds(56, 170, 138, 50);
				btnAceptar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						modificarTD();
						
					}
				});
				contentPane.add(btnAceptar);
				
				clrSiglaTD = new ClrCuadroDeTexto(6,false,"Sigla",true,1);
				clrSiglaTD.setFocusCycleRoot(false);
				clrSiglaTD.setBounds(44, 108, 150, 20);
				clrSiglaTD.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						
						lb2.setVisible(false);
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						
						if (lb2==null) {
							lb2= new lbTDXSigla(clrSiglaTD, esta, esta.principal);
						}
					}
				});
				contentPane.add(clrSiglaTD);
				
				SwingUtilities.invokeLater( new Runnable() 
		        { 
		        public void run() 
		            { 
		            btnAceptar.requestFocusInWindow(); 
		        } 
		    }); 
				
				this.setVisible(true);
			
				
			
				
					

				
				
				
			} else {



				lblinicio = new clrLabel("Eliminar tipo de datos",2,true);
				lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
				lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
				lblinicio.setBounds(10, 11, 384, 28);
				contentPane.add(lblinicio);
						
				lblincorrectos = new clrLabel("", 1);
				lblincorrectos.setForeground(Colores.clrAlertaCamarada);
				lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
				lblincorrectos.setAlignmentX(0.5f);
				lblincorrectos.setBounds(10, 38, 384, 28);
				contentPane.add(lblincorrectos);
				
			
				
				txtNombreTD = new ClrCuadroDeTexto(100,false,"Nombre",true);
				txtNombreTD.setBounds(44, 77, 308, 20);
				txtNombreTD.setFocusCycleRoot(false);
				txtNombreTD.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						
						lb1.setVisible(false);
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						
						if (lb1==null) {
							lb1=new lbTDXnombre(txtNombreTD, esta, esta.principal);
						}
					}
				});
				contentPane.add(txtNombreTD);
				
				btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50),2);
				btnCancelar.setBounds(204, 170, 136, 50);
				btnCancelar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						
					}
				});
				contentPane.add(btnCancelar);
				
				btnAceptar = new btnRedondo("Eliminar", new Rectangle(48, 172,121,50),5);
				btnAceptar.setSelected(true);
				btnAceptar.setBounds(56, 170, 138, 50);
				btnAceptar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
				eliminarTD();
						
					}
				});
				contentPane.add(btnAceptar);
				
				clrSiglaTD = new ClrCuadroDeTexto(6,false,"Sigla",true);
				clrSiglaTD.setFocusCycleRoot(false);
				clrSiglaTD.setBounds(44, 108, 150, 20);
				clrSiglaTD.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						
						lb2.setVisible(false);
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						
						if (lb2==null) {
							lb2= new lbTDXSigla(clrSiglaTD, esta, esta.principal);
						}
					}
				});
				contentPane.add(clrSiglaTD);
				
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
	
	public void  ingresarTD(){
		
		
		
		if (txtNombreTD.getText().equals(txtNombreTD.getLabel())||clrSiglaTD.getText().equals(clrSiglaTD.getLabel())) {
			
			lblincorrectos.setText("Faltan campos por llenar");
		} else {
			
			lblincorrectos.setText("");

			TipoDato TD=new TipoDato(0, txtNombreTD.getText(),clrSiglaTD.getText());
			
			conexion cone=conexion.getInstance();
			
			if (cone.nuevoTD(esta, TD)) {
				esta.principal.registrarAccion("Creacion de tipo de dato '"+txtNombreTD.getText()+"'");
				dispose();
			}else{
				lblincorrectos.setText("Error  en la conexion con la base de datos");
			}
			
		
				}
		
		
	}
	
	public void  modificarTD(){
		
		
		if (txtNombreTD.getText().equals(txtNombreTD.getLabel())||clrSiglaTD.getText().equals(clrSiglaTD.getLabel())) {
			
			lblincorrectos.setText("Faltan campos por llenar");
		} else {
			
			lblincorrectos.setText("");

			TipoDato TD=new TipoDato(idtipo, txtNombreTD.getText(),clrSiglaTD.getText());
			
			conexion cone=conexion.getInstance();
			
			if (cone.editarTD(esta,TD,nombreAntiguo)) {
				esta.principal.registrarAccion("Modifcación de tipo de dato '"+txtNombreTD.getText()+"'");
				dispose();
			}else{
				lblincorrectos.setText("Error  en la conexion con la base de datos");
			}
			
		
				}
		
		
	}

	public void  eliminarTD(){
		
		if (txtNombreTD.getText().equals(txtNombreTD.getLabel())||clrSiglaTD.getText().equals(clrSiglaTD.getLabel())) {
			
			lblincorrectos.setText("Faltan campos por llenar");
		} else {
			
			lblincorrectos.setText("");

			
			conexion cone=conexion.getInstance();
		
			if (cone.nuevoTD(esta, tDatoActual)) {
				esta.principal.registrarAccion("Eliminación de tipo de dato '"+txtNombreTD.getText()+"'");
				dispose();
			}else{
				lblincorrectos.setText("Error  en la conexion con la base de datos");
			}
			
		
				}
		
		
		
	}


	
	
	
	public void llenar(TipoDato TD){
		tDatoActual=TD;
		idtipo=TD.getIdTD();
		nombreAntiguo=TD.getNombreTD();
		
		txtNombreTD.setText(TD.getNombreTD());
		txtNombreTD.setForeground(Color.black);
		
		clrSiglaTD.setText(TD.getSiglaTD());
		clrSiglaTD.setForeground(Color.black);

	}
	
	public void reportarError(String error){
		lblincorrectos.setText(error);
	}
}
