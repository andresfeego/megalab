package Interfaces;

import interfaces_Modificadas.ClrCuadroDeTexto;
import interfaces_Modificadas.ClrJdialog;
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
import java.util.StringTokenizer;

import javax.swing.JDialog;
import javax.swing.SwingConstants;

import ago.beans.CirclePanel;
import auxiliares.Bacteriologo;
import auxiliares.Empresa;
import auxiliares.Medico;
import auxiliares.Paciente;
import auxiliares.Usuario;
import conexion.conexion;
import conexion.conexionCombos;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import datechooser.beans.DateChooserCombo;

import com.sun.org.apache.bcel.internal.generic.LLOAD;
import com.sun.org.apache.xml.internal.serializer.utils.StringToIntTable;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;

public class OpEmpresa extends ClrJdialog{
	private clrPanelBordes contentPane;
	private clrLabel lblincorrectos;
	private Principal principal;
	private Usuario usuario;
	private clrLabel lblinicio;
	private circuloUsuario btnRedondo_;
	private String accion; 
	private OpEmpresa esta;
	private ClrCuadroDeTexto txtNit;
	private ClrCuadroDeTexto txtDireccion;
	private ClrCuadroDeTexto txtDependenciaCobro;
	private clrComboBox cbTarifas;
	private clrComboBox cbCiudades;
	private ClrCuadroDeTexto txtRazonSocial;
	private btnRedondo btnCancelar;
	private btnRedondo btnAceptar;
	private ClrCuadroDeTexto txtTelefono1;
	private ClrCuadroDeTexto txtTelefono2;
	private ClrCuadroDeTexto txtEmail1;
	private ClrCuadroDeTexto txtEmail2;
	private lbEmpresaXnit lb1;
	private lbEmpresaXrazon lb2;
	private clrComboBox cbTipoUsuario;
	private ClrCuadroDeTexto descuento;
	private clrtextpane txtAdicional;
	private clrtextpane txtRequisitos;
	private ClrCuadroDeTexto clrCodigoEps;
	private int idEmpresa=-1;
	private String antiguoNit;
	
	public OpEmpresa(Principal principal, Usuario usuario, String Accion) {
		super(principal,true);
		this.principal=principal;
		this.usuario=usuario;
		this.accion=Accion;
		this.esta=this;

		//this.setDefaultLookAndFeelDecorated(false);


		setBounds(100, 100, 616, 573);
		this.setUndecorated(true);
		this.setLocationRelativeTo(getParent()); 
		contentPane = new clrPanelBordes(true);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		



			
		
		
		
		
	
		
		
		
		
	

		
		
		
		if(accion.equals("Agregar")){

			lblinicio = new clrLabel("Ingresar Empresa",2,true);
			lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
			lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblinicio.setBounds(10, 11, 596, 28);
			contentPane.add(lblinicio);
					
			lblincorrectos = new clrLabel("", 1);
			lblincorrectos.setForeground(Colores.clrAlertaCamarada);
			lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
			lblincorrectos.setAlignmentX(0.5f);
			lblincorrectos.setBounds(10, 38, 596, 28);
			contentPane.add(lblincorrectos);
			
			
			
			txtNit = new ClrCuadroDeTexto(13,false,"Nit",1);
			txtNit.setBounds(23, 77, 308, 20);
			txtNit.setFocusCycleRoot(false);
			contentPane.add(txtNit);
			
			txtDireccion = new ClrCuadroDeTexto(100,false,"Dirección");
			txtDireccion.setColumns(10);
			txtDireccion.setBounds(23, 139, 308, 20);
			contentPane.add(txtDireccion);
			
			txtDependenciaCobro = new ClrCuadroDeTexto(50,false,"Dependencia de cobro");
			txtDependenciaCobro.setColumns(10);
			txtDependenciaCobro.setBounds(23, 201, 308, 20);
			contentPane.add(txtDependenciaCobro);
			
			cbCiudades = new clrComboBox(conexionCombos.getInstance().listaCiudades(),1);
			cbCiudades.setBounds(23, 170, 308, 20);
			contentPane.add(cbCiudades);
			
			txtRazonSocial = new ClrCuadroDeTexto(100,false,"Razon Social",1);
			txtRazonSocial.setColumns(10);
			txtRazonSocial.setBounds(23, 108, 308, 20);
			contentPane.add(txtRazonSocial);
			
			btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50),2);
			btnCancelar.setBounds(312, 472, 136, 50);
			btnCancelar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					
				}
			});
			contentPane.add(btnCancelar);
			
			btnAceptar = new btnRedondo("Ingresar", new Rectangle(48, 172,121,50),1);
			btnAceptar.setSelected(true);
			btnAceptar.setBounds(164, 472, 138, 50);
			btnAceptar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
				ingresarEmpresa();
					
				}
			});
			contentPane.add(btnAceptar);
			
			txtTelefono1 = new ClrCuadroDeTexto(12,true,"Telefono de contacto 1");
			txtTelefono1.setBounds(23, 320, 308, 20);
			contentPane.add(txtTelefono1);
		
			
			txtTelefono2 = new ClrCuadroDeTexto(12,true,"Telefono de contacto 2");
			txtTelefono2.setBounds(23, 351, 308, 20);
		
			contentPane.add(txtTelefono2);
			
			
			txtEmail1 = new ClrCuadroDeTexto(100,false,"E-mail para envio de notificaciones");
			txtEmail1.setBounds(23, 382, 308, 20);
			contentPane.add(txtEmail1);
			txtEmail1.setColumns(10);
			
			txtEmail2 = new ClrCuadroDeTexto(100,false,"E-mail");
			txtEmail2.setBounds(23, 413, 308, 20);
			contentPane.add(txtEmail2);
			txtEmail2.setColumns(10);
			
			cbTipoUsuario = new clrComboBox(conexionCombos.getInstance().listaTipoUsuario(),1);
			cbTipoUsuario.setBounds(23, 289, 308, 20);
			contentPane.add(cbTipoUsuario);
			
			
			cbTarifas = new clrComboBox(conexionCombos.getInstance().listaTarifas(),1);
			cbTarifas.setBounds(23, 232, 308, 20);
			contentPane.add(cbTarifas);
			
			descuento = new ClrCuadroDeTexto(2,true,"Descuento (%)");
			descuento.setColumns(10);
			descuento.setBounds(23, 258, 144, 20);
			contentPane.add(descuento);
			
			clrCodigoEps = new ClrCuadroDeTexto(6,false,"Código EPS");
			clrCodigoEps.setBounds(187, 258, 144, 20);
			contentPane.add(clrCodigoEps);
			
			clrScrollBar scrollPane = new clrScrollBar();
			scrollPane.setBounds(361, 78, 245, 155);
			contentPane.add(scrollPane);
			
			txtAdicional = new clrtextpane(255,false,"Información adicional",0);
			scrollPane.setViewportView(txtAdicional);
			
			clrScrollBar scrollPane2 = new clrScrollBar();
			scrollPane2.setBounds(361, 255, 245, 155);
			contentPane.add(scrollPane2);
				
			txtRequisitos = new clrtextpane(255,false,"Requisitos para recepción",0);
			scrollPane2.setViewportView(txtRequisitos);
			
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

				lblinicio = new clrLabel("Modificar Empresa",2,true);
				lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
				lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
				lblinicio.setBounds(10, 11, 596, 28);
				contentPane.add(lblinicio);
						
				lblincorrectos = new clrLabel("", 1);
				lblincorrectos.setForeground(Colores.clrAlertaCamarada);
				lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
				lblincorrectos.setAlignmentX(0.5f);
				lblincorrectos.setBounds(10, 38, 596, 28);
				contentPane.add(lblincorrectos);
				
			
				
				txtNit = new ClrCuadroDeTexto(13,false,"Nit",true,1);
				txtNit.setBounds(23, 77, 308, 20);
				txtNit.setFocusCycleRoot(false);
				txtNit.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						
						lb1.setVisible(false);
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						
						if (lb1==null) {
							lb1= new lbEmpresaXnit(txtNit, esta, esta.principal);
						}
					}
				});
				contentPane.add(txtNit);
				
				txtDireccion = new ClrCuadroDeTexto(100,false,"Direcci\u00F3n");
				txtDireccion.setColumns(10);
				txtDireccion.setBounds(23, 139, 308, 20);
				contentPane.add(txtDireccion);
				
				txtDependenciaCobro = new ClrCuadroDeTexto(50,false,"Dependencia de cobro");
				txtDependenciaCobro.setColumns(10);
				txtDependenciaCobro.setBounds(23, 201, 308, 20);
				contentPane.add(txtDependenciaCobro);
				
				cbCiudades = new clrComboBox(conexionCombos.getInstance().listaCiudades(),1);
				cbCiudades.setBounds(23, 170, 308, 20);
				contentPane.add(cbCiudades);
				
				txtRazonSocial = new ClrCuadroDeTexto(100,false,"Razon Social",true,1);
				txtRazonSocial.setColumns(10);
				txtRazonSocial.setBounds(23, 108, 308, 20);
				txtRazonSocial.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						
						lb2.setVisible(false);
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						
						if (lb2==null) {
							lb2= new lbEmpresaXrazon(txtRazonSocial, esta, esta.principal);
						}
					}
				});
				contentPane.add(txtRazonSocial);
				
				btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50),2);
				btnCancelar.setBounds(312, 472, 136, 50);
				btnCancelar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						
					}
				});
				contentPane.add(btnCancelar);
				
				btnAceptar = new btnRedondo("Modificar", new Rectangle(48, 172,121,50),3);
				btnAceptar.setSelected(true);
				btnAceptar.setBounds(164, 472, 138, 50);
				btnAceptar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						modificarEmpresa();
						
					}
				});
				contentPane.add(btnAceptar);
				
				txtTelefono1 = new ClrCuadroDeTexto(12,true,"Telefono de contacto 1");
				txtTelefono1.setBounds(23, 320, 308, 20);
				contentPane.add(txtTelefono1);
				
				
				txtTelefono2 = new ClrCuadroDeTexto(12,true,"Telefono de contacto 2");
				txtTelefono2.setBounds(23, 351, 308, 20);
				
				contentPane.add(txtTelefono2);
				
				
				txtEmail1 = new ClrCuadroDeTexto(100,false,"E-mail para envio de notificaciones");
				txtEmail1.setBounds(23, 382, 308, 20);
				contentPane.add(txtEmail1);
				txtEmail1.setColumns(10);
				
				txtEmail2 = new ClrCuadroDeTexto(100,false,"E-mail");
				txtEmail2.setBounds(23, 413, 308, 20);
				contentPane.add(txtEmail2);
				txtEmail2.setColumns(10);
				
				cbTipoUsuario = new clrComboBox(conexionCombos.getInstance().listaTipoUsuario(),1);
				cbTipoUsuario.setBounds(23, 289, 308, 20);
				contentPane.add(cbTipoUsuario);
				
				
				cbTarifas = new clrComboBox(conexionCombos.getInstance().listaTarifas(),1);
				cbTarifas.setBounds(23, 232, 308, 20);
				contentPane.add(cbTarifas);
				
				descuento = new ClrCuadroDeTexto(2,true,"Descuento (%)");
				descuento.setColumns(10);
				descuento.setBounds(23, 258, 144, 20);
				contentPane.add(descuento);
				
				clrCodigoEps = new ClrCuadroDeTexto(6,false,"Código EPS");
				clrCodigoEps.setBounds(187, 258, 144, 20);
				contentPane.add(clrCodigoEps);
				
				clrScrollBar scrollPane = new clrScrollBar();
				scrollPane.setBounds(361, 78, 245, 155);
				contentPane.add(scrollPane);
				
				txtAdicional = new clrtextpane(255,false,"Información adicional",0);
				scrollPane.setViewportView(txtAdicional);
				
				clrScrollBar scrollPane2 = new clrScrollBar();
				scrollPane2.setBounds(361, 255, 245, 155);
				contentPane.add(scrollPane2);
					
				txtRequisitos = new clrtextpane(255,false,"Requisitos para recepción",0);
				scrollPane2.setViewportView(txtRequisitos);
				
				SwingUtilities.invokeLater( new Runnable() 
		        { 
		        public void run() 
		            { 
		            btnCancelar.requestFocusInWindow(); 
		        } 
		    }); 
				
				
				this.setVisible(true);
			
				
			} else {


				lblinicio = new clrLabel("Eliminar Empresa",2,true);
				lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
				lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
				lblinicio.setBounds(10, 11, 596, 28);
				contentPane.add(lblinicio);
						
				lblincorrectos = new clrLabel("", 1);
				lblincorrectos.setForeground(Colores.clrAlertaCamarada);
				lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
				lblincorrectos.setAlignmentX(0.5f);
				lblincorrectos.setBounds(10, 38, 596, 28);
				contentPane.add(lblincorrectos);
				
			
				
				txtNit = new ClrCuadroDeTexto(13,false,"Nit",true);
				txtNit.setBounds(23, 77, 308, 20);
				txtNit.setFocusCycleRoot(false);
				txtNit.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						
						lb1.setVisible(false);
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						
						if (lb1==null) {
							lb1= new lbEmpresaXnit(txtNit, esta, esta.principal);
						}
					}
				});
				contentPane.add(txtNit);
				
				txtDireccion = new ClrCuadroDeTexto(100,false,"Direcci\u00F3n");
				txtDireccion.setColumns(10);
				txtDireccion.setBounds(23, 139, 308, 20);
				contentPane.add(txtDireccion);
				
				txtDependenciaCobro = new ClrCuadroDeTexto(50,false,"Dependencia de cobro");
				txtDependenciaCobro.setColumns(10);
				txtDependenciaCobro.setBounds(23, 201, 308, 20);
				contentPane.add(txtDependenciaCobro);
				
				cbCiudades = new clrComboBox(conexionCombos.getInstance().listaCiudades(),1);
				cbCiudades.setBounds(23, 170, 308, 20);
				contentPane.add(cbCiudades);
				
				txtRazonSocial = new ClrCuadroDeTexto(100,false,"Razon Social",true);
				txtRazonSocial.setColumns(10);
				txtRazonSocial.setBounds(23, 108, 308, 20);
				txtRazonSocial.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						
						lb2.setVisible(false);
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						
						if (lb2==null) {
							lb2= new lbEmpresaXrazon(txtRazonSocial, esta, esta.principal);
						}
					}
				});
				contentPane.add(txtRazonSocial);
				
				btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50),2);
				btnCancelar.setBounds(312, 472, 136, 50);
				btnCancelar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						
					}
				});
				contentPane.add(btnCancelar);
				
				btnAceptar = new btnRedondo("Eliminar", new Rectangle(48, 172,121,50),5);
				btnAceptar.setSelected(true);
				btnAceptar.setBounds(164, 472, 138, 50);
				btnAceptar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
					eliminarEmpresa();
						
					}
				});
				
				
				contentPane.add(btnAceptar);
				
				txtTelefono1 = new ClrCuadroDeTexto(12,true,"Telefono de contacto 1");
				txtTelefono1.setBounds(23, 320, 308, 20);
				contentPane.add(txtTelefono1);
		
				
				txtTelefono2 = new ClrCuadroDeTexto(12,true,"Telefono de contacto 2");
				txtTelefono2.setBounds(23, 351, 308, 20);
			contentPane.add(txtTelefono2);
				
				
				
				txtEmail1 = new ClrCuadroDeTexto(100,false,"E-mail para envio de notificaciones");
				txtEmail1.setBounds(23, 382, 308, 20);
				contentPane.add(txtEmail1);
				txtEmail1.setColumns(10);
				
				txtEmail2 = new ClrCuadroDeTexto(100,false,"E-mail");
				txtEmail2.setBounds(23, 413, 308, 20);
				contentPane.add(txtEmail2);
				txtEmail2.setColumns(10);
				
				cbTipoUsuario = new clrComboBox(conexionCombos.getInstance().listaTipoUsuario(),1);
				cbTipoUsuario.setBounds(23, 289, 308, 20);
				contentPane.add(cbTipoUsuario);
				
				
				cbTarifas = new clrComboBox(conexionCombos.getInstance().listaTarifas(),1);
				cbTarifas.setBounds(23, 232, 308, 20);
				contentPane.add(cbTarifas);
				
				descuento = new ClrCuadroDeTexto(2,true,"Descuento (%)");
				descuento.setColumns(10);
				descuento.setBounds(23, 258, 144, 20);
				contentPane.add(descuento);
				
				clrCodigoEps = new ClrCuadroDeTexto(6,false,"Código EPS");
				clrCodigoEps.setColumns(10);
				clrCodigoEps.setBounds(187, 258, 144, 20);
				contentPane.add(clrCodigoEps);
				
				clrScrollBar scrollPane = new clrScrollBar();
				scrollPane.setBounds(361, 78, 245, 155);
				contentPane.add(scrollPane);
				
				txtAdicional = new clrtextpane(255,false,"Información adicional",0);
				scrollPane.setViewportView(txtAdicional);
				
				clrScrollBar scrollPane2 = new clrScrollBar();
				scrollPane2.setBounds(361, 255, 245, 155);
				contentPane.add(scrollPane2);
					
				txtRequisitos = new clrtextpane(255,false,"Requisitos para recepción",0);
				scrollPane2.setViewportView(txtRequisitos);
				
				
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
	
	public void  ingresarEmpresa(){
		
		
		
		if (txtNit.getText().equals(txtNit.getLabel())||txtRazonSocial.getText().equals(txtRazonSocial.getLabel())||txtDireccion.getText().equals(txtDireccion.getLabel())||cbCiudades.getSelectedIndex()==0||cbTipoUsuario.getSelectedIndex()==0||cbTarifas.getSelectedIndex()==0) {
			
			lblincorrectos.setText("Faltan campos por llenar");
		} else {
			
			lblincorrectos.setText("");

			String DependenciasAuxi="";
			String adicionalauxi="";
			String requisitoAuxi="";
			String epsauxi="";
			String Email1auxi="";
			String Email2auxi="";
			String Tel1auxi="";
			String Tel2auxi="";
			int descuentuAuxi=0;
			if(!txtDependenciaCobro.getText().equals("Dependencia de cobro"))DependenciasAuxi=txtDependenciaCobro.getText();
			if(!txtAdicional.getText().equals("Información adicional"))adicionalauxi=txtAdicional.getText();
			if(!txtRequisitos.getText().equals("Requisitos para recepción"))requisitoAuxi=txtRequisitos.getText();
			if(!clrCodigoEps.getText().equals("Código EPS"))epsauxi=clrCodigoEps.getText();
			if(!txtEmail1.getText().equals("E-mail para envio de notificaciones"))Email1auxi=txtEmail1.getText();
			if(!txtEmail2.getText().equals("E-mail"))Email2auxi=txtEmail2.getText();
			if(!txtTelefono1.getText().equals("Telefono de contacto 1"))Tel1auxi=txtTelefono1.getText();
			if(!txtTelefono2.getText().equals("Telefono de contacto 2"))Tel2auxi=txtTelefono2.getText();
			if(!descuento.getText().equals("Descuento (%)"))descuentuAuxi=Integer.parseInt(descuento.getText());
			
			Empresa empresa=new Empresa(txtNit.getText(), txtRazonSocial.getText(), txtDireccion.getText(), cbCiudades.getSelectedItem().toString(), DependenciasAuxi,cbTarifas.getSelectedItem().toString(), descuentuAuxi, epsauxi, cbTipoUsuario.getSelectedIndex(), adicionalauxi, requisitoAuxi,1,Email1auxi , Email2auxi, Tel1auxi, Tel2auxi);
			
			conexion cone=conexion.getInstance();
			
			if (cone.nuevaEmpresa(esta, empresa)) {
				esta.principal.registrarAccion("Creación de Empresa '"+txtRazonSocial.getText()+"'");
				dispose();
			}else{
				lblincorrectos.setText("Error  en la conexion con la base de datos");
			}
			
	
				}
		
	}
	
	
public void modificarEmpresa(){
		
		
		
	if (txtNit.getText().equals(txtNit.getLabel())||txtRazonSocial.getText().equals(txtRazonSocial.getLabel())||txtDireccion.getText().equals(txtDireccion.getLabel())||cbCiudades.getSelectedIndex()==0||cbTipoUsuario.getSelectedIndex()==0||cbTarifas.getSelectedIndex()==0) {
		
		lblincorrectos.setText("Faltan campos por llenar");
	} else {
		
		lblincorrectos.setText("");

		String DependenciasAuxi="";
		String adicionalauxi="";
		String requisitoAuxi="";
		String epsauxi="";
		String Email1auxi="";
		String Email2auxi="";
		String Tel1auxi="";
		String Tel2auxi="";
		int descuentuAuxi=0;
		if(!txtDependenciaCobro.getText().equals("Dependencia de cobro"))DependenciasAuxi=txtDependenciaCobro.getText();
		if(!txtAdicional.getText().equals("Información adicional"))adicionalauxi=txtAdicional.getText();
		if(!txtRequisitos.getText().equals("Requisitos para recepción"))requisitoAuxi=txtRequisitos.getText();
		if(!clrCodigoEps.getText().equals("Código EPS"))epsauxi=clrCodigoEps.getText();
		if(!txtEmail1.getText().equals("E-mail para envio de notificaciones"))Email1auxi=txtEmail1.getText();
		if(!txtEmail2.getText().equals("E-mail"))Email2auxi=txtEmail2.getText();
		if(!txtTelefono1.getText().equals("Telefono de contacto 1"))Tel1auxi=txtTelefono1.getText();
		if(!txtTelefono2.getText().equals("Telefono de contacto 2"))Tel2auxi=txtTelefono2.getText();
		if(!descuento.getText().equals("Descuento (%)"))descuentuAuxi=Integer.parseInt(descuento.getText());
		String[] ST=cbTipoUsuario.getSelectedItem().toString().split(" • ");
		
		
		Empresa empresa=new Empresa(txtNit.getText(), txtRazonSocial.getText(), txtDireccion.getText(), cbCiudades.getSelectedItem().toString(), DependenciasAuxi,cbTarifas.getSelectedItem().toString(), descuentuAuxi, epsauxi, ST[1], adicionalauxi, requisitoAuxi,1,Email1auxi , Email2auxi, Tel1auxi, Tel2auxi);
			empresa.setIdEmpresa(idEmpresa);
			conexion cone=conexion.getInstance();
			
			if (cone.editarEmpresa(empresa,antiguoNit)) {
				esta.principal.registrarAccion("Modificación de Empresa '"+txtRazonSocial.getText()+"'");
				dispose();
			}else{
				lblincorrectos.setText("Error  en la conexion con la base de datos");
			}
			
	
				}
		
		
	}

public void  eliminarEmpresa(){
	
	
	
	if (idEmpresa==-1) {
		
		lblincorrectos.setText("No has escogido una empresa aun");
	} else {
		
		lblincorrectos.setText("");
		
		conexion cone=conexion.getInstance();
		
		if (cone.eliminarEmpresa(idEmpresa)) {
			esta.principal.registrarAccion("Eliminación de Empresa '"+txtRazonSocial.getText()+"'");
			dispose();
		}else{
			lblincorrectos.setText("Error  en la conexion con la base de datos");
		}
		
	
			}
}


	
	
	public String calculaFecha(Date FechaNac){
		Calendar fechaActual = Calendar.getInstance();
        Calendar FechaNacimi = Calendar.getInstance();


		 if(FechaNac!=null){
		        FechaNacimi.setTime(FechaNac);

			 if(fechaActual.getTimeInMillis()<FechaNacimi.getTimeInMillis()){
				 return "error - fecha exedida";
			 }else{
	        //Se restan la fecha actual y la fecha de nacimiento
	        int aos = fechaActual.get(Calendar.YEAR)- FechaNacimi.get(Calendar.YEAR);
	        int mes =fechaActual.get(Calendar.MONTH)- FechaNacimi.get(Calendar.MONTH);
	        int dia = fechaActual.get(Calendar.DATE)- FechaNacimi.get(Calendar.DATE);
	        //Se ajusta el año dependiendo el mes y el día
	        if(mes<0 || (mes==0 && dia<0)){
	            aos--;
	        }
	        
	        if (dia<0||mes==0&&dia<0) {
				mes--;
			}
	        
	        if (aos>=5) {
	        	return aos+" Años";
			} else {
				if (aos<5&&aos>=1) {

					String meses="";
					if (mes<0)meses= 12+(mes)+"- Meses";
					if (mes>0)meses= (mes)+"- Meses";
					
					return aos+" Años "+meses;
				} else {
					if (aos<1&&mes!=0) {

						String meses="";
						if (mes<0)meses= 12+(mes)+" Meses";
						if (mes>0)meses= (mes)+" Meses";
							
							return meses;
					} else {
						return fechaActual.get(Calendar.DAY_OF_YEAR)-FechaNacimi.get(Calendar.DAY_OF_YEAR)+" Dias de nacido";
					}
				
			}
		}
	        
	        
	        
	      
			 }
		 }
	        else{
			 return "Sin fecha de nacimiento";
			 
		 }
	}

	
	
	public void llenar(Empresa empresa){
		antiguoNit=empresa.getDocEmpresa();
		idEmpresa=empresa.getIdEmpresa();
		
		
		
		txtNit.setText(empresa.getDocEmpresa()+"");
		txtNit.setForeground(Color.black);
		
		txtRazonSocial.setText(empresa.getRazonSocial()+"");
		txtRazonSocial.setForeground(Color.black);
		
		
		
		txtDireccion.setText(empresa.getDireccion()+"");
		txtDireccion.setForeground(Color.black);
		
		cbCiudades.setSelectedItem(empresa.getCiudad());	
		
		cbTipoUsuario.setSelectedIndex(empresa.getTipoUsuario());
		
		cbTarifas.setSelectedItem(empresa.getTarifa());
		
					
				
		if(!empresa.getDependenciaCobro().equals("")){
			txtDependenciaCobro.setText(empresa.getDependenciaCobro());
			txtDependenciaCobro.setForeground(Color.black);

			}else{
				txtDependenciaCobro.reiniciar();
			}
		
		if(empresa.getDescuento()!=0){
			descuento.setText(empresa.getDescuento()+"");
			descuento.setForeground(Color.black);
			}else{
				descuento.reiniciar();
			}
	
		
		if(!empresa.getCodEps().equals("")){
			clrCodigoEps.setText(empresa.getCodEps());
			clrCodigoEps.setForeground(Color.black);

		}else{
			clrCodigoEps.reiniciar();
		}

		
		if(!empresa.getAdicional().equals("")){
			txtAdicional.setText(empresa.getAdicional()+"");
			txtAdicional.setForeground(Color.black);

		}else{
			txtAdicional.reiniciar();
		}

		
		if(!empresa.getRequisitosRecepcion().equals("")){
			txtRequisitos.setText(empresa.getRequisitosRecepcion()+"");
			txtRequisitos.setForeground(Color.black);

		}else{
			txtRequisitos.reiniciar();
		}

		
		if(!empresa.getEmail1().equals("")){
			txtEmail1.setText(empresa.getEmail1()+"");
			txtEmail1.setForeground(Color.black);

		}else{
			txtEmail1.reiniciar();
		}

		
		if(!empresa.getEmail2().equals("")){
			txtEmail2.setText(empresa.getEmail2()+"");
			txtEmail2.setForeground(Color.black);

		}else{
			txtEmail2.reiniciar();
		}

		
		if(!empresa.getTelefono1().equals("")){
			txtTelefono1.setText(empresa.getTelefono1()+"");
			txtTelefono1.setForeground(Color.black);

		}else{
			txtTelefono1.reiniciar();
		}

		
		if(!empresa.getTelefono2().equals("")){
			txtTelefono2.setText(empresa.getTelefono2()+"");
			txtTelefono2.setForeground(Color.black);

		}else{
			txtTelefono2.reiniciar();
		}

		//////////////////////////////////////////////////////////////
		
		
		
		
		
	}
	
	public void reportarError(String error){
		lblincorrectos.setText(error);
	}
}
