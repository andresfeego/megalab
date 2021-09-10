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
import auxiliares.Medico;
import auxiliares.Paciente;
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

public class OpMedico extends JDialog{
	private clrPanelBordes contentPane;
	private clrLabel lblincorrectos;
	private Principal principal;
	private Usuario usuario;
	private clrLabel lblinicio;
	private circuloUsuario btnRedondo_;
	private String accion; 
	private OpMedico esta;
	private String idMedico="";
	
	
	private clrComboBox cbTipoID;
	private ClrCuadroDeTexto txtID;
	private ClrCuadroDeTexto txtnombres;
	private ClrCuadroDeTexto txtApellidos;
	private JDateChooser fechaNacimiento;
	private ClrCuadroDeTexto txtEdad;
	private clrComboBox cbGeneros;
	private clrComboBox cbResidencia;
	private ClrCuadroDeTexto txtDireccion;
	private clrComboBox cbCiudades;
	private ClrCuadroDeTexto txtIdMedico;
	private btnRedondo btnCancelar;
	private btnRedondo btnAceptar;
	private ClrCuadroDeTexto txtTelefono1;
	private ClrCuadroDeTexto txtTelefono2;
	private ClrCuadroDeTexto txtEmail1;
	private ClrCuadroDeTexto txtEmail2;
	private lbMedicoXid lb1;
	private lbMedicoXnombre lb2;
	private lbMedicoXApellido lb3;
	private clrLabel lblFechaNacimiento;
	private clrComboBox cbEspecialidad;
	
	public OpMedico(Principal principal, Usuario usuario, String Accion) {
		super(principal,true);
		this.principal=principal;
		this.usuario=usuario;
		this.accion=Accion;
		this.esta=this;

		//this.setDefaultLookAndFeelDecorated(false);


		setBounds(100, 100, 404, 687);
		this.setUndecorated(true);
		this.setLocationRelativeTo(getParent()); 
		contentPane = new clrPanelBordes(true);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		


		
			
		
		
		
		
	
		
		
		
		
	

		
		
		
		if(accion.equals("Agregar")){
			lblinicio = new clrLabel("Ingresar Médico",2,true);
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
			
		
			
			txtID = new ClrCuadroDeTexto(15,true,"Documento de identificacion",1);
			txtID.setBounds(46, 108, 308, 20);
			txtID.setFocusCycleRoot(false);
		
			contentPane.add(txtID);
			
			cbTipoID = new clrComboBox(conexionCombos.getInstance().listaTipoId(),1);
			cbTipoID.setBounds(45, 77, 309, 20);
			contentPane.add(cbTipoID);
			
			txtnombres = new ClrCuadroDeTexto(100,false,"Nombres",1);
			txtnombres.setColumns(10);
			txtnombres.setBounds(45, 170, 308, 20);
			contentPane.add(txtnombres);
			
			txtApellidos = new ClrCuadroDeTexto(100,false,"Apellidos",1);
			txtApellidos.setColumns(10);
			txtApellidos.setBounds(45, 201, 308, 20);
			contentPane.add(txtApellidos);
			
			fechaNacimiento = new JDateChooser();
			fechaNacimiento.setBorder(javax.swing.BorderFactory.createMatteBorder( 1, 1, 1, 1, Colores.clrTextoInactivo));
			fechaNacimiento.setBounds(45, 257, 308, 20);
			contentPane.add(fechaNacimiento);
			
			 txtEdad = new ClrCuadroDeTexto(500,false,"Edad",1);
			txtEdad.setColumns(10);
			txtEdad.setBounds(45, 288, 149, 20);
			txtEdad.setEditable(false);
			txtEdad.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					
					
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					txtEdad.setText(calculaFecha(fechaNacimiento.getDate()));
					
				}
			});
			contentPane.add(txtEdad);
			
			cbGeneros = new clrComboBox(conexionCombos.getInstance().listaGeneros(),1);
			cbGeneros.setBounds(204, 288, 149, 20);
			contentPane.add(cbGeneros);
			
			txtDireccion = new ClrCuadroDeTexto(100,false,"Direccion");
			txtDireccion.setColumns(10);
			txtDireccion.setBounds(43, 382, 308, 20);
			contentPane.add(txtDireccion);
			
			cbCiudades = new clrComboBox(conexionCombos.getInstance().listaCiudades(),1);
			cbCiudades.setBounds(45, 350, 308, 20);
			contentPane.add(cbCiudades);
			
			txtIdMedico = new ClrCuadroDeTexto(11,false,"ID Medico");
			txtIdMedico.setColumns(10);
			txtIdMedico.setBounds(46, 139, 308, 20);
			contentPane.add(txtIdMedico);
			
			lblFechaNacimiento = new clrLabel("Fecha de nacimiento", 1);
			lblFechaNacimiento.setHorizontalAlignment(SwingConstants.LEFT);
			lblFechaNacimiento.setForeground(Colores.clrSecundario);
			lblFechaNacimiento.setAlignmentX(0.5f);
			lblFechaNacimiento.setBounds(45, 232, 308, 20);
			contentPane.add(lblFechaNacimiento);
			
			btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50),2);
			btnCancelar.setBounds(210, 587, 136, 50);
			btnCancelar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					
				}
			});
			contentPane.add(btnCancelar);
			
			btnAceptar = new btnRedondo("Ingresar", new Rectangle(48, 172,121,50),1);
			btnAceptar.setSelected(true);
			btnAceptar.setBounds(62, 587, 138, 50);
			btnAceptar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
				ingresarMedico();
					
				}
			});
			contentPane.add(btnAceptar);
			
			txtTelefono1 = new ClrCuadroDeTexto(12,true,"Telefono de contacto 1");
			txtTelefono1.setBounds(44, 413, 308, 20);
			contentPane.add(txtTelefono1);
			
			
			txtTelefono2 = new ClrCuadroDeTexto(12,true,"Telefono de contacto 2");
			txtTelefono2.setBounds(44, 444, 308, 20);
			contentPane.add(txtTelefono2);
			
			
			txtEmail1 = new ClrCuadroDeTexto(100,false,"E-mail para envio de notificaciones");
			txtEmail1.setBounds(44, 475, 308, 20);
			contentPane.add(txtEmail1);
			txtEmail1.setColumns(10);
			
			txtEmail2 = new ClrCuadroDeTexto(100,false,"E-mail");
			txtEmail2.setBounds(43, 506, 308, 20);
			contentPane.add(txtEmail2);
			txtEmail2.setColumns(10);
			
			cbEspecialidad = new clrComboBox(conexionCombos.getInstance().listaEspecialidades(),1);
			cbEspecialidad.setBounds(45, 537, 308, 20);
			contentPane.add(cbEspecialidad);
			
			
			cbResidencia = new clrComboBox(conexionCombos.getInstance().listaZonaResidencial(),1);
			cbResidencia.setBounds(45, 319, 308, 20);
			contentPane.add(cbResidencia);
			
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
				
				lblinicio = new clrLabel("Modificar Médico",2,true);
				lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
				lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
				lblinicio.setBounds(10, 11, 384, 28);
				contentPane.add(lblinicio);
						
				lblincorrectos = new clrLabel("", 1);
				lblincorrectos.setForeground(Colores.clrAlertaCamarada);
				lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
				lblincorrectos.setAlignmentX(0.5f);
				lblincorrectos.setBounds(10, 39, 384, 28);
				contentPane.add(lblincorrectos);
				
			
				
				txtID = new ClrCuadroDeTexto(15,true,"Documento de identificacion",1);
				txtID.setBounds(46, 108, 308, 20);
			
				
				
				contentPane.add(txtID);
				
				cbTipoID = new clrComboBox(conexionCombos.getInstance().listaTipoId(),1);
				cbTipoID.setBounds(45, 77, 309, 20);
				contentPane.add(cbTipoID);
				
				txtnombres = new ClrCuadroDeTexto(100,false,"Nombres",true,1);
				txtnombres.setColumns(10);
				txtnombres.setBounds(45, 170, 308, 20);
				txtnombres.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						
						lb2.setVisible(false);
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						
						if (lb2==null) {
							lb2=new lbMedicoXnombre(txtnombres, esta, esta.principal);
						}
					}
				});
				contentPane.add(txtnombres);
				
				txtApellidos = new ClrCuadroDeTexto(100,false,"Apellidos",true,1);
				txtApellidos.setColumns(10);
				txtApellidos.setBounds(45, 201, 308, 20);
				txtApellidos.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						
						lb3.setVisible(false);
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						
						if (lb3==null) {
							lb3=new lbMedicoXApellido(txtApellidos, esta, esta.principal);
						}
					}
				});
				contentPane.add(txtApellidos);
				
				fechaNacimiento = new JDateChooser();
				fechaNacimiento.setBorder(javax.swing.BorderFactory.createMatteBorder( 1, 1, 1, 1, Colores.clrTextoInactivo));
				fechaNacimiento.setBounds(45, 257, 308, 20);
				contentPane.add(fechaNacimiento);
				
				 txtEdad = new ClrCuadroDeTexto(500,false,"Edad",true);
				txtEdad.setColumns(10);
				txtEdad.setBounds(45, 288, 149, 20);
				txtEdad.setEditable(false);
				txtEdad.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						txtEdad.setText(calculaFecha(fechaNacimiento.getDate()));
						
					}
				});
				contentPane.add(txtEdad);
				
				cbGeneros = new clrComboBox(conexionCombos.getInstance().listaGeneros(),1);
				cbGeneros.setBounds(204, 288, 149, 20);
				contentPane.add(cbGeneros);
				
				txtDireccion = new ClrCuadroDeTexto(100,false,"Direccion");
				txtDireccion.setColumns(10);
				txtDireccion.setBounds(43, 382, 308, 20);
				contentPane.add(txtDireccion);
				
				cbCiudades = new clrComboBox(conexionCombos.getInstance().listaCiudades(),1);
				cbCiudades.setBounds(45, 350, 308, 20);
				contentPane.add(cbCiudades);
				
				txtIdMedico = new ClrCuadroDeTexto(11,false,"ID Medico",true);
				txtIdMedico.setColumns(10);
				txtIdMedico.setBounds(46, 139, 308, 20);
				txtIdMedico.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						
						lb1.setVisible(false);
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						
						if (lb1==null) {
							lb1=new lbMedicoXid(txtIdMedico, esta, esta.principal);
						}
					}
				});
				contentPane.add(txtIdMedico);
				
				lblFechaNacimiento = new clrLabel("Fecha de nacimiento", 1);
				lblFechaNacimiento.setHorizontalAlignment(SwingConstants.LEFT);
				lblFechaNacimiento.setForeground(Colores.clrSecundario);
				lblFechaNacimiento.setAlignmentX(0.5f);
				lblFechaNacimiento.setBounds(45, 232, 308, 20);
				contentPane.add(lblFechaNacimiento);
				
				btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50),2);
				btnCancelar.setBounds(210, 587, 136, 50);
				btnCancelar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						
					}
				});
				contentPane.add(btnCancelar);
				
				btnAceptar = new btnRedondo("Modificar", new Rectangle(48, 172, 121, 50),3);
				btnAceptar.setSelected(true);
				btnAceptar.setBounds(62, 587, 138, 50);
				btnAceptar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
					modificarMedico();
						
					}
				});
				contentPane.add(btnAceptar);
				
				txtTelefono1 = new ClrCuadroDeTexto(12,true,"Telefono de contacto 1");
				txtTelefono1.setBounds(44, 413, 308, 20);
				contentPane.add(txtTelefono1);
			
				
				txtTelefono2 = new ClrCuadroDeTexto(12,true,"Telefono de contacto 2");
				txtTelefono2.setBounds(44, 444, 308, 20);
				contentPane.add(txtTelefono2);
				
				
				txtEmail1 = new ClrCuadroDeTexto(100,false,"E-mail para envio de notificaciones");
				txtEmail1.setBounds(44, 475, 308, 20);
				contentPane.add(txtEmail1);
				txtEmail1.setColumns(10);
				
				txtEmail2 = new ClrCuadroDeTexto(100,false,"E-mail");
				txtEmail2.setBounds(43, 506, 308, 20);
				contentPane.add(txtEmail2);
				txtEmail2.setColumns(10);
				
				cbEspecialidad = new clrComboBox(conexionCombos.getInstance().listaEspecialidades(),1);
				cbEspecialidad.setBounds(45, 537, 308, 20);
				contentPane.add(cbEspecialidad);
				
				cbResidencia = new clrComboBox(conexionCombos.getInstance().listaZonaResidencial(),1);
				cbResidencia.setBounds(45, 319, 308, 20);
				contentPane.add(cbResidencia);
				
				SwingUtilities.invokeLater( new Runnable() 
		        { 
		        public void run() 
		            { 
		            btnAceptar.requestFocusInWindow(); 
		        } 
		    }); 
				
				this.setVisible(true);
				
				
			} else {
				
				lblinicio = new clrLabel("Eliminar Médico",2,true);
				lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
				lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
				lblinicio.setBounds(10, 11, 384, 28);
				contentPane.add(lblinicio);
						
				lblincorrectos = new clrLabel("", 1);
				lblincorrectos.setForeground(Colores.clrAlertaCamarada);
				lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
				lblincorrectos.setAlignmentX(0.5f);
				lblincorrectos.setBounds(10, 39, 384, 28);
				contentPane.add(lblincorrectos);
				
			
				txtID = new ClrCuadroDeTexto(15,true,"Documento de identificacion");
				txtID.setBounds(46, 108, 308, 20);
				
				
				contentPane.add(txtID);
				
				cbTipoID = new clrComboBox(conexionCombos.getInstance().listaTipoId(),1);
				cbTipoID.setBounds(45, 77, 309, 20);
				contentPane.add(cbTipoID);
				
				txtnombres = new ClrCuadroDeTexto(100,false,"Nombres",true);
				txtnombres.setColumns(10);
				txtnombres.setBounds(45, 170, 308, 20);
				txtnombres.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						
						lb2.setVisible(false);
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						
						if (lb2==null) {
							lb2=new lbMedicoXnombre(txtnombres, esta, esta.principal);
						}
					}
				});
				contentPane.add(txtnombres);
				
				txtApellidos = new ClrCuadroDeTexto(100,false,"Apellidos",true);
				txtApellidos.setColumns(10);
				txtApellidos.setBounds(45, 201, 308, 20);
				txtApellidos.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						
						lb3.setVisible(false);
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						
						if (lb3==null) {
							lb3=new lbMedicoXApellido(txtApellidos, esta, esta.principal);
						}
					}
				});
				contentPane.add(txtApellidos);
				
				fechaNacimiento = new JDateChooser();
				fechaNacimiento.setBorder(javax.swing.BorderFactory.createMatteBorder( 1, 1, 1, 1, Colores.clrTextoInactivo));
				fechaNacimiento.setBounds(45, 257, 308, 20);
				contentPane.add(fechaNacimiento);
				
				 txtEdad = new ClrCuadroDeTexto(500,false,"Edad",true);
				txtEdad.setColumns(10);
				txtEdad.setBounds(45, 288, 149, 20);
				txtEdad.setEditable(false);
				txtEdad.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						txtEdad.setText(calculaFecha(fechaNacimiento.getDate()));
						
					}
				});
				contentPane.add(txtEdad);
				
				cbGeneros = new clrComboBox(conexionCombos.getInstance().listaGeneros(),1);
				cbGeneros.setBounds(204, 288, 149, 20);
				contentPane.add(cbGeneros);
				
				txtDireccion = new ClrCuadroDeTexto(100,false,"Direccion");
				txtDireccion.setColumns(10);
				txtDireccion.setBounds(43, 382, 308, 20);
				contentPane.add(txtDireccion);
				
				cbCiudades = new clrComboBox(conexionCombos.getInstance().listaCiudades(),1);
				cbCiudades.setBounds(45, 350, 308, 20);
				contentPane.add(cbCiudades);
				
				txtIdMedico = new ClrCuadroDeTexto(11,false,"ID Medico",true);
				txtIdMedico.setColumns(10);
				txtIdMedico.setBounds(46, 139, 308, 20);
				txtIdMedico.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						
						lb1.setVisible(false);
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						
						if (lb1==null) {
							lb1=new lbMedicoXid(txtIdMedico, esta, esta.principal);
						}
					}
				});
				contentPane.add(txtIdMedico);
				
				lblFechaNacimiento = new clrLabel("Fecha de nacimiento", 1);
				lblFechaNacimiento.setHorizontalAlignment(SwingConstants.LEFT);
				lblFechaNacimiento.setForeground(Colores.clrSecundario);
				lblFechaNacimiento.setAlignmentX(0.5f);
				lblFechaNacimiento.setBounds(45, 232, 308, 20);
				contentPane.add(lblFechaNacimiento);
				
				btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50),2);
				btnCancelar.setBounds(210, 587, 136, 50);
				btnCancelar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						
					}
				});
				contentPane.add(btnCancelar);
				
				btnAceptar = new btnRedondo("Eliminar", new Rectangle(48, 172,121,50),5);
				btnAceptar.setSelected(true);
				btnAceptar.setBounds(62, 587, 138, 50);
				btnAceptar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
					eliminarMedico();
						
					}
				});
				contentPane.add(btnAceptar);
				
				txtTelefono1 = new ClrCuadroDeTexto(12,true,"Telefono de contacto 1");
				txtTelefono1.setBounds(44, 413, 308, 20);
				contentPane.add(txtTelefono1);
			
				
				txtTelefono2 = new ClrCuadroDeTexto(100,true,"Telefono de contacto 2");
				txtTelefono2.setBounds(44, 444, 308, 20);
				
				contentPane.add(txtTelefono2);
				
				
				txtEmail1 = new ClrCuadroDeTexto(100,false,"E-mail para envio de notificaciones");
				txtEmail1.setBounds(44, 475, 308, 20);
				contentPane.add(txtEmail1);
				txtEmail1.setColumns(10);
				
				txtEmail2 = new ClrCuadroDeTexto(100,false,"E-mail");
				txtEmail2.setBounds(43, 506, 308, 20);
				contentPane.add(txtEmail2);
				txtEmail2.setColumns(10);
				
				cbEspecialidad = new clrComboBox(conexionCombos.getInstance().listaEspecialidades(),1);
				cbEspecialidad.setBounds(45, 537, 308, 20);
				contentPane.add(cbEspecialidad);
				
				cbResidencia = new clrComboBox(conexionCombos.getInstance().listaZonaResidencial(),1);
				cbResidencia.setBounds(45, 319, 308, 20);
				contentPane.add(cbResidencia);
				
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
	
	public void  ingresarMedico(){
		
		
		
		if (cbTipoID.getSelectedIndex()==0||txtID.getText().equals("")||txtID.getText().equals("Documento de identificacion")||txtnombres.getText().equals("")||txtnombres.getText().equals("Nombres")||txtApellidos.getText().equals("")||txtApellidos.getText().equals("Apellidos")
				||fechaNacimiento.getDate()==null||cbResidencia.getSelectedIndex()==0||cbGeneros.getSelectedIndex()==0||cbCiudades.getSelectedIndex()==0||cbEspecialidad.getSelectedIndex()==0||txtEdad.equals("error - fecha exedida")||txtEdad.getText().equals("Sin fecha de nacimiento")||txtEdad.getText().equals("")) {
			
			lblincorrectos.setText("Faltan campos por llenar");
		} else {
			lblincorrectos.setText("");

			String Direccauxi="";
			String Email1auxi="";
			String Email2auxi="";
			String Tel1auxi="";
			String Tel2auxi="";
			String idmedicoauxi="";
			if(!txtDireccion.getText().equals("Direccion"))Direccauxi=txtDireccion.getText();
			if(!txtEmail1.getText().equals("E-mail para envio de notificaciones"))Email1auxi=txtEmail1.getText();
			if(!txtEmail2.getText().equals("E-mail"))Email2auxi=txtEmail2.getText();
			if(!txtTelefono1.getText().equals("Telefono de contacto 1"))Tel1auxi=txtTelefono1.getText();
			if(!txtTelefono2.getText().equals("Telefono de contacto 2"))Tel2auxi=txtTelefono2.getText();
			if(!txtIdMedico.getText().equals("Registro"))idmedicoauxi=txtIdMedico.getText();
			
			Medico medico=new Medico(txtID.getText(), cbTipoID.getSelectedIndex(), txtnombres.getText(), txtApellidos.getText(), fechaNacimiento.getDate(), calculaFecha(fechaNacimiento.getDate()), cbGeneros.getSelectedIndex()-1, cbResidencia.getSelectedIndex(), cbCiudades.getSelectedItem().toString(), Direccauxi, Email1auxi, Email2auxi, Tel1auxi, Tel2auxi, idmedicoauxi, cbEspecialidad.getSelectedIndex(), 1);
			
			conexion cone=conexion.getInstance();
			
			if (cone.nuevoMedico(esta, medico)) {
				esta.principal.registrarAccion("Creacion de médico '"+txtnombres.getText()+" "+txtApellidos.getText()+"'");
				dispose();
			}else{
				lblincorrectos.setText("Error  en la conexion con la base de datos");
			}
			
		
				}
		
	
		
	}
	
	
public void  modificarMedico(){
		
		
		
		if (cbTipoID.getSelectedIndex()==0||txtID.getText().equals("")||txtID.getText().equals("Documento de identificacion")||txtnombres.getText().equals("")||txtnombres.getText().equals("Nombres")||txtApellidos.getText().equals("")||txtApellidos.getText().equals("Apellidos")
				||fechaNacimiento.getDate()==null||cbResidencia.getSelectedIndex()==0||cbGeneros.getSelectedIndex()==0||cbCiudades.getSelectedIndex()==0||cbEspecialidad.getSelectedIndex()==0||txtEdad.equals("error - fecha exedida")||txtEdad.getText().equals("Sin fecha de nacimiento")||txtEdad.getText().equals("")) {
			
			lblincorrectos.setText("Faltan campos por llenar");
		} else {
			lblincorrectos.setText("");

			String Direccauxi="";
			String Email1auxi="";
			String Email2auxi="";
			String Tel1auxi="";
			String Tel2auxi="";
			String idmedicoauxi="";
			if(!txtDireccion.getText().equals("Direccion"))Direccauxi=txtDireccion.getText();
			if(!txtEmail1.getText().equals("E-mail para envio de notificaciones"))Email1auxi=txtEmail1.getText();
			if(!txtEmail2.getText().equals("E-mail"))Email2auxi=txtEmail2.getText();
			if(!txtTelefono1.getText().equals("Telefono de contacto 1"))Tel1auxi=txtTelefono1.getText();
			if(!txtTelefono2.getText().equals("Telefono de contacto 2"))Tel2auxi=txtTelefono2.getText();
			if(!txtIdMedico.getText().equals(txtIdMedico.getLabel()))idmedicoauxi=txtIdMedico.getText();
			
			Medico medico=new Medico(txtID.getText(), cbTipoID.getSelectedIndex(), txtnombres.getText(), txtApellidos.getText(), fechaNacimiento.getDate(), calculaFecha(fechaNacimiento.getDate()), cbGeneros.getSelectedIndex()-1, cbResidencia.getSelectedIndex(), cbCiudades.getSelectedItem().toString(), Direccauxi, Email1auxi, Email2auxi, Tel1auxi, Tel2auxi, idmedicoauxi, cbEspecialidad.getSelectedIndex(), 0);
			
			conexion cone=conexion.getInstance();
			
			if (cone.editarMedico(medico)) {
				esta.principal.registrarAccion("Modificación de médico '"+txtnombres.getText()+" "+txtApellidos.getText()+"'");
				dispose();
			}else{
				lblincorrectos.setText("Error  en la conexion con la base de datos");
			}
			
		
				}
		
		
	}

public void  eliminarMedico(){
	
	
	
	if (idMedico.equals("")) {
		
		lblincorrectos.setText("No has escogido ningún medico aun");
	} else {
		
		conexion cone=conexion.getInstance();
		
		if (cone.eliminarMedico(idMedico)) {
			esta.principal.registrarAccion("Eliminación de médico '"+txtnombres.getText()+" "+txtApellidos.getText()+"'");
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

	
	
	public void llenar(Medico med){
		idMedico=med.getId();
		txtID.setText(med.getId()+"");
		txtID.setForeground(Color.black);
		txtID.setEditable(false);

		cbTipoID.setSelectedIndex(med.getTipoId());
		txtnombres.setText(med.getNombres());
		txtnombres.setForeground(Color.black);
		txtApellidos.setText(med.getApellidos());
		txtApellidos.setForeground(Color.black);
		fechaNacimiento.setDate(med.getFechaNacimiento());
		txtEdad.setText(calculaFecha(fechaNacimiento.getDate()));
		txtEdad.setForeground(Color.black);
		cbGeneros.setSelectedIndex(med.getGenero()+1);
		cbResidencia.setSelectedIndex(med.getZonaResidencial());
		cbCiudades.setSelectedItem(med.getCiudad());
		cbEspecialidad.setSelectedIndex(med.getEspecialidad());
		
		if(!med.getIdmedico().equals("")){
			txtIdMedico.setText(med.getIdmedico()+"");
			txtIdMedico.setForeground(Color.black);

		}else{
			txtIdMedico.reiniciar();
		}
		
			
				
		if(!med.getDireccion().equals("")){
			txtDireccion.setText(med.getDireccion());
			txtDireccion.setForeground(Color.black);

			}else{
				txtDireccion.reiniciar();
			}
		
		if(!med.getEmail1().equals("")){
			txtEmail1.setText(med.getEmail1());
			txtEmail1.setForeground(Color.black);
			}else{
			txtEmail1.reiniciar();
		}
		
		if(!med.getEmail2().equals("")){
			txtEmail2.setText(med.getEmail2());
			txtEmail2.setForeground(Color.black);

		}else{
			txtEmail2.reiniciar();
		}
		
		
		if(!med.getTelefono1().equals("")){
			txtTelefono1.setText(med.getTelefono1()+"");
			txtTelefono1.setForeground(Color.black);

		}else{
			txtTelefono1.reiniciar();
		}
		
		
		if(!med.getTelefono2().equals("")){
			txtTelefono2.setText(med.getTelefono2()+"");
			txtTelefono2.setForeground(Color.black);

		}else{
			txtTelefono2.reiniciar();
		}
		
		
		
	}
	
	public void reportarError(String error){
		lblincorrectos.setText(error);
	}
}
