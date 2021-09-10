package Interfaces;

import interfaces_Modificadas.ClrCuadroDeTexto;
import interfaces_Modificadas.Colores;
import interfaces_Modificadas.btnRedondo;
import interfaces_Modificadas.circuloUsuario;
import interfaces_Modificadas.clrComboBox;
import interfaces_Modificadas.clrLabel;
import interfaces_Modificadas.clrPanelBordes;
import interfaces_Modificadas.clrScrollBar;
import interfaces_Modificadas.clrtextpane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import otrosImpresos.DJiTextEjemplo;
import visor.VisorPDF;
import visor.Visor2;
import metodos.arrastraYsuelta;
import auxiliares.Bacteriologo;
import auxiliares.Usuario;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;

import com.toedter.calendar.JDateChooser;

import conexion.conexion;
import conexion.conexionCombos;

public class OpBacteriologo extends JDialog{
	private clrPanelBordes contentPane;
	private clrLabel lblincorrectos;
	private Principal principal;
	private Usuario usuario;
	private clrLabel lblinicio;
	private circuloUsuario btnRedondo_;
	private String accion; 
	private OpBacteriologo esta;
	
	
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
	private ClrCuadroDeTexto txtRegistro;
	private btnRedondo btnCancelar;
	private btnRedondo btnAceptar;
	private ClrCuadroDeTexto txtTelefono1;
	private ClrCuadroDeTexto txtTelefono2;
	private ClrCuadroDeTexto txtEmail1;
	private ClrCuadroDeTexto txtEmail2;
	private lbBacteriologoXid lb1;
	private lbBacteriologoXnombre lb2;
	private lbBacteriologoXapellido lb3;
	private clrLabel lblFechaNacimiento;
	private ClrCuadroDeTexto txtTitulo;
	private clrtextpane txtOtrosEstudios;
	private clrtextpane txtInfAdicional;
	private ClrCuadroDeTexto txtClaveFirma;
	private 	JLabel lblImagenFirma;
	private String idBacteriologo="";
	
	
	private InputStream FIS=null;
	private Image firma=null;
	
	public OpBacteriologo(Principal principal, Usuario usuario, String Accion) {
		super(principal,true);
		this.principal=principal;
		this.usuario=usuario;
		this.accion=Accion;
		this.esta=this;

		//this.setDefaultLookAndFeelDecorated(false);


		setBounds(100, 100, 650, 687);
		this.setUndecorated(true);
		this.setLocationRelativeTo(getParent()); 
		contentPane = new clrPanelBordes(true);
		contentPane.setLayout(null);
		setContentPane(contentPane);


		
		


		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		if(accion.equals("Agregar")){
			lblinicio = new clrLabel("Ingresar Bacteriólogo",2,true);
			lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
			lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblinicio.setBounds(10, 11, 630, 28);
			contentPane.add(lblinicio);
					
			lblincorrectos = new clrLabel("", 1);
			lblincorrectos.setForeground(Colores.clrAlertaCamarada);
			lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
			lblincorrectos.setAlignmentX(0.5f);
			lblincorrectos.setBounds(20, 50, 606, 28);
			contentPane.add(lblincorrectos);
			
		
			
			txtID = new ClrCuadroDeTexto(15,true,"Documento de identificación",1);
			txtID.setBounds(21, 110, 308, 20);
			
			contentPane.add(txtID);
			
			cbTipoID = new clrComboBox(conexionCombos.getInstance().listaTipoId(),1);
			cbTipoID.setBounds(20, 79, 309, 20);
			contentPane.add(cbTipoID);
			
			txtnombres = new ClrCuadroDeTexto(100,false,"Nombres",1);
			txtnombres.setColumns(10);
			txtnombres.setBounds(21, 141, 308, 20);
			contentPane.add(txtnombres);
			
			txtApellidos = new ClrCuadroDeTexto(100,false,"Apellidos",1);
			txtApellidos.setColumns(10);
			txtApellidos.setBounds(21, 172, 308, 20);
			contentPane.add(txtApellidos);
			
			fechaNacimiento = new JDateChooser();
			fechaNacimiento.setBorder(javax.swing.BorderFactory.createMatteBorder( 1, 1, 1, 1, Colores.clrPrincipal));
			fechaNacimiento.setBounds(21, 228, 308, 20);
			contentPane.add(fechaNacimiento);
			
			 txtEdad = new ClrCuadroDeTexto(500,false,"Edad",1);
			txtEdad.setColumns(10);
			txtEdad.setBounds(21, 259, 149, 20);
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
			cbGeneros.setBounds(180, 259, 149, 20);
			contentPane.add(cbGeneros);
			
			txtDireccion = new ClrCuadroDeTexto(100,false,"Direccion");
			txtDireccion.setColumns(10);
			txtDireccion.setBounds(19, 353, 308, 20);
			contentPane.add(txtDireccion);
			
			cbCiudades = new clrComboBox(conexionCombos.getInstance().listaCiudades(),1);
			cbCiudades.setBounds(21, 321, 308, 20);
			contentPane.add(cbCiudades);
			
			txtRegistro = new ClrCuadroDeTexto(11,false,"Registro");
			txtRegistro.setBounds(19, 508, 308, 20);
			
			contentPane.add(txtRegistro);
			
			lblFechaNacimiento = new clrLabel("Fecha de nacimiento", 1);
			lblFechaNacimiento.setHorizontalAlignment(SwingConstants.LEFT);
			lblFechaNacimiento.setForeground(Colores.clrSecundario);
			lblFechaNacimiento.setAlignmentX(0.5f);
			lblFechaNacimiento.setBounds(21, 203, 308, 20);
			contentPane.add(lblFechaNacimiento);
			
			btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50),2);
			btnCancelar.setBounds(328, 587, 129, 50);
			btnCancelar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					
				}
			});
			contentPane.add(btnCancelar);
			
			btnAceptar = new btnRedondo("Ingresar", new Rectangle(48, 172,121,50),1);
			btnAceptar.setSelected(true);
			btnAceptar.setBounds(180, 587,121,50);
			btnAceptar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
				ingresarBacteriologo();
					
				}
			});
			contentPane.add(btnAceptar);
			
			txtTelefono1 = new ClrCuadroDeTexto(12,true,"Telefono de contacto 1");
			txtTelefono1.setBounds(20, 384, 308, 20);
			contentPane.add(txtTelefono1);
	
			
			txtTelefono2 = new ClrCuadroDeTexto(12,true,"Telefono de contacto 2");
			txtTelefono2.setBounds(20, 415, 308, 20);
	
		
			contentPane.add(txtTelefono2);
			
			txtTelefono2.setColumns(10);
			
			txtEmail1 = new ClrCuadroDeTexto(100,false,"E-mail para envio de notificaciones");
			txtEmail1.setBounds(20, 446, 308, 20);
			contentPane.add(txtEmail1);
			txtEmail1.setColumns(10);
			
			txtEmail2 = new ClrCuadroDeTexto(100,false,"E-mail");
			txtEmail2.setBounds(19, 477, 308, 20);
			contentPane.add(txtEmail2);
			txtEmail2.setColumns(10);
			
			txtTitulo = new ClrCuadroDeTexto(100,false,"Titulo");
			txtTitulo.setColumns(10);
			txtTitulo.setBounds(19, 539, 308, 20);
			contentPane.add(txtTitulo);
			
			clrScrollBar scrollPane = new clrScrollBar();
			scrollPane.setBounds(339, 78, 301, 115);
			contentPane.add(scrollPane);
			
			
			cbResidencia = new clrComboBox(conexionCombos.getInstance().listaZonaResidencial(),1);
			cbResidencia.setBounds(21, 290, 308, 20);
			contentPane.add(cbResidencia);
			
			txtOtrosEstudios = new clrtextpane(500,false,"Otros estudios",0);
			scrollPane.setViewportView(txtOtrosEstudios);
			
			clrScrollBar clrScrollBar_ = new clrScrollBar();
			clrScrollBar_.setBounds(339, 200, 301, 115);
			contentPane.add(clrScrollBar_);
			
			txtInfAdicional = new clrtextpane(500,false,"Informacion adicional",0);
			clrScrollBar_.setViewportView(txtInfAdicional);
			
			clrPanelBordes panelFirma = new clrPanelBordes(false);
			panelFirma.setLayout(null);
			panelFirma.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Arrastra aqu\u00ED la imagen para la firma (325x325)", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
			panelFirma.setBounds(337, 321, 303, 243);
			contentPane.add(panelFirma);
			
			
			lblImagenFirma = new JLabel("");
			lblImagenFirma.setBounds(10, 42, 283, 138);
			panelFirma.add(lblImagenFirma);
			
			
			TitledBorder dragBorder = new javax.swing.border.TitledBorder( new MatteBorder(2, 2, 2, 2, Colores.clrSecundario),"Suelta la imagen", TitledBorder.LEFT, TitledBorder.TOP, null,Colores.clrPrincipal);
			   new arrastraYsuelta( System.out, panelFirma,dragBorder, new arrastraYsuelta.Listener()
		        {   public void filesDropped( java.io.File[] files )
		            {  
		                  try
		                    {
		                	firma = resize(files[0].getCanonicalPath(),lblImagenFirma.getWidth(),lblImagenFirma.getHeight());
		                	lblImagenFirma.setIcon(new ImageIcon(firma));
		                	System.out.println("path path : >>>>>>>>>>>>>>>>>>>>>"+ files[0].getCanonicalPath() + "\n" );
		                    }   // end try
		                    catch( java.io.IOException e ) {}
		                  // end for: through each dropped file
		            }   // end filesDropped
		        }); // end FileDrop.Listener
			
			txtClaveFirma = new ClrCuadroDeTexto(10,true,"Clave para firmar",1);
			txtClaveFirma.setColumns(10);
			txtClaveFirma.setBounds(10, 207, 283, 25);
			panelFirma.add(txtClaveFirma);
			
			
			
			
			
			SwingUtilities.invokeLater( new Runnable() 
	        { 
	        public void run() 
	            { 
	            btnAceptar.requestFocusInWindow(); 
	        } 
	    }); 
			
			this.setVisible(true);}else{
			if (accion.equals("Modificar")) {
				
				lblinicio = new clrLabel("Modificar Bacteriólogo",2,true);
				lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
				lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
				lblinicio.setBounds(10, 11, 630, 28);
				contentPane.add(lblinicio);
						
				lblincorrectos = new clrLabel("", 1);
				lblincorrectos.setForeground(Colores.clrAlertaCamarada);
				lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
				lblincorrectos.setAlignmentX(0.5f);
				lblincorrectos.setBounds(10, 39, 319, 28);
				contentPane.add(lblincorrectos);
				
			
				
				txtID = new ClrCuadroDeTexto(15,true,"Documento de identificacion",true,1);
				txtID.setBounds(21, 110, 308, 20);
		
				
				txtID.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						
						lb1.setVisible(false);
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						
						if (lb1==null) {
							lb1=new lbBacteriologoXid(txtID, esta, esta.principal);
						}
					}
				});
				contentPane.add(txtID);
				
				cbTipoID = new clrComboBox(conexionCombos.getInstance().listaTipoId(),1);
				cbTipoID.setBounds(20, 79, 309, 20);
				contentPane.add(cbTipoID);
				
				txtnombres = new ClrCuadroDeTexto(100,false,"Nombres",true,1);
				txtnombres.setColumns(10);
				txtnombres.setBounds(21, 141, 308, 20);
				
				txtnombres.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						
						lb2.setVisible(false);
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						
						if (lb2==null) {
							lb2= new lbBacteriologoXnombre(txtnombres, esta, esta.principal);
						}
						
					}
				});
				contentPane.add(txtnombres);
				
				txtApellidos = new ClrCuadroDeTexto(100,false,"Apellidos",true,1);
				txtApellidos.setColumns(10);
				txtApellidos.setBounds(21, 172, 308, 20);
				txtApellidos.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						
						lb3.setVisible(false);
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						
						if (lb3==null) {
							lb3= new lbBacteriologoXapellido(txtApellidos, esta, esta.principal);
						}
					}
				});
				contentPane.add(txtApellidos);
				
				fechaNacimiento = new JDateChooser();
				fechaNacimiento.setBorder(javax.swing.BorderFactory.createMatteBorder( 1, 1, 1, 1, Colores.clrTextoInactivo));
				fechaNacimiento.setBounds(21, 228, 308, 20);
				contentPane.add(fechaNacimiento);
				
				 txtEdad = new ClrCuadroDeTexto(500,false,"Edad",1);
				txtEdad.setColumns(10);
				txtEdad.setBounds(21, 259, 149, 20);
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
				cbGeneros.setBounds(180, 259, 149, 20);
				contentPane.add(cbGeneros);
				
				txtDireccion = new ClrCuadroDeTexto(100,false,"Direccion");
				txtDireccion.setColumns(10);
				txtDireccion.setBounds(19, 353, 308, 20);
				contentPane.add(txtDireccion);
				
				cbCiudades = new clrComboBox(conexionCombos.getInstance().listaCiudades(),1);
				cbCiudades.setBounds(21, 321, 308, 20);
				contentPane.add(cbCiudades);
				
				txtRegistro = new ClrCuadroDeTexto(11,false,"Registro");
				txtRegistro.setBounds(19, 508, 308, 20);
			
				contentPane.add(txtRegistro);
				
				lblFechaNacimiento = new clrLabel("Fecha de nacimiento", 1);
				lblFechaNacimiento.setHorizontalAlignment(SwingConstants.LEFT);
				lblFechaNacimiento.setForeground(Colores.clrSecundario);
				lblFechaNacimiento.setAlignmentX(0.5f);
				lblFechaNacimiento.setBounds(21, 203, 308, 20);
				contentPane.add(lblFechaNacimiento);
				
				btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50),2);
				btnCancelar.setBounds(328, 587, 129, 50);
				btnCancelar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						
					}
				});
				contentPane.add(btnCancelar);
				
				btnAceptar = new btnRedondo("Modificar", new Rectangle(48, 172,121,50),3);
				btnAceptar.setSelected(true);
				btnAceptar.setBounds(180, 587,121,50);
				btnAceptar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
					modificarBacteriologo();
						
					}
				});
				contentPane.add(btnAceptar);
				
				txtTelefono1 = new ClrCuadroDeTexto(12,true,"Telefono de contacto 1");
				txtTelefono1.setBounds(20, 384, 308, 20);
	
				contentPane.add(txtTelefono1);
		
	
				
				txtTelefono2 = new ClrCuadroDeTexto(12,true,"Telefono de contacto 2");
				txtTelefono2.setBounds(20, 415, 308, 20);
		
				contentPane.add(txtTelefono2);
				
				txtTelefono2.setColumns(10);
				
				txtEmail1 = new ClrCuadroDeTexto(100,false,"E-mail para envio de notificaciones");
				txtEmail1.setBounds(20, 446, 308, 20);
				contentPane.add(txtEmail1);
				txtEmail1.setColumns(10);
				
				txtEmail2 = new ClrCuadroDeTexto(100,false,"E-mail");
				txtEmail2.setBounds(19, 477, 308, 20);
				contentPane.add(txtEmail2);
				txtEmail2.setColumns(10);
				
				txtTitulo = new ClrCuadroDeTexto(100,false,"Titulo");
				txtTitulo.setColumns(10);
				txtTitulo.setBounds(19, 539, 308, 20);
				contentPane.add(txtTitulo);
				
				clrScrollBar scrollPane = new clrScrollBar();
				scrollPane.setBounds(339, 78, 301, 115);
				contentPane.add(scrollPane);
				
				
				cbResidencia = new clrComboBox(conexionCombos.getInstance().listaZonaResidencial(),1);
				cbResidencia.setBounds(21, 290, 308, 20);
				contentPane.add(cbResidencia);
				
				txtOtrosEstudios = new clrtextpane(500,false,"Otros estudios",0);
				scrollPane.setViewportView(txtOtrosEstudios);
				
				clrScrollBar clrScrollBar_ = new clrScrollBar();
				clrScrollBar_.setBounds(339, 200, 301, 115);
				contentPane.add(clrScrollBar_);
				
				txtInfAdicional = new clrtextpane(500,false,"Informacion adicional",0);
				clrScrollBar_.setViewportView(txtInfAdicional);
				
				clrPanelBordes panelFirma = new clrPanelBordes(false);
				panelFirma.setLayout(null);
				panelFirma.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Arrastra aqu\u00ED la imagen para la firma (325x325)", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
				panelFirma.setBounds(337, 321, 303, 243);
				contentPane.add(panelFirma);
				
				
				lblImagenFirma = new JLabel("");
				lblImagenFirma.setBounds(10, 42, 283, 138);
				panelFirma.add(lblImagenFirma);
				
				
				TitledBorder dragBorder = new javax.swing.border.TitledBorder( new MatteBorder(2, 2, 2, 2, Colores.clrSecundario),"Suelta la imagen", TitledBorder.LEFT, TitledBorder.TOP, null,Colores.clrPrincipal);
				   new arrastraYsuelta( System.out, panelFirma,dragBorder, new arrastraYsuelta.Listener()
			        {   public void filesDropped( java.io.File[] files )
			            {  
			                  try
			                    {
				                	firma = resize(files[0].getCanonicalPath(),lblImagenFirma.getWidth(),lblImagenFirma.getHeight());
				                	lblImagenFirma.setIcon(new ImageIcon(firma));
				                	System.out.println("path path : >>>>>>>>>>>>>>>>>>>>>"+ files[0].getCanonicalPath() + "\n" );
				                    }   // end try
			                    catch( java.io.IOException e ) {}
			                  // end for: through each dropped file
			            }   // end filesDropped
			        }); // end FileDrop.Listener
				
				txtClaveFirma = new ClrCuadroDeTexto(10,false,"Clave para firmar",1);
				txtClaveFirma.setColumns(10);
				txtClaveFirma.setBounds(10, 207, 283, 25);
				panelFirma.add(txtClaveFirma);
				
				SwingUtilities.invokeLater( new Runnable() 
		        { 
		        public void run() 
		            { 
		            btnAceptar.requestFocusInWindow(); 
		        } 
		    }); 
				
				this.setVisible(true);
				
				
				
			} else {
				
				lblinicio = new clrLabel("Eliminar Bacteriólogo",2,true);
				lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
				lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
				lblinicio.setBounds(10, 11, 630, 28);
				contentPane.add(lblinicio);
						
				lblincorrectos = new clrLabel("", 1);
				lblincorrectos.setForeground(Colores.clrAlertaCamarada);
				lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
				lblincorrectos.setAlignmentX(0.5f);
				lblincorrectos.setBounds(10, 39, 319, 28);
				contentPane.add(lblincorrectos);
				
		
				
				txtID = new ClrCuadroDeTexto(100,false,"Documento de identificacion",true);
				txtID.setBounds(21, 110, 308, 20);
			
				txtID.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						
						lb1.setVisible(false);
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						
						if (lb1==null) {
							lb1=new lbBacteriologoXid(txtID, esta, esta.principal);
						}
					}
				});
				contentPane.add(txtID);
				
				cbTipoID = new clrComboBox(conexionCombos.getInstance().listaTipoId(),1);
				cbTipoID.setBounds(20, 79, 309, 20);
				contentPane.add(cbTipoID);
				
				txtnombres = new ClrCuadroDeTexto(100,false,"Nombres",true);
				txtnombres.setColumns(10);
				txtnombres.setBounds(21, 141, 308, 20);
				txtnombres.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						
						lb2.setVisible(false);
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						
						if (lb2==null) {
							lb2= new lbBacteriologoXnombre(txtnombres, esta, esta.principal);
						}
						
					}
				});
				contentPane.add(txtnombres);
				
				txtApellidos = new ClrCuadroDeTexto(100,false,"Apellidos",true);
				txtApellidos.setColumns(10);
				txtApellidos.setBounds(21, 172, 308, 20);
				txtApellidos.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						
						lb3.setVisible(false);
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						
						if (lb3==null) {
							lb3= new lbBacteriologoXapellido(txtApellidos, esta, esta.principal);
						}
					}
				});
				contentPane.add(txtApellidos);
				
				fechaNacimiento = new JDateChooser();
				fechaNacimiento.setBorder(javax.swing.BorderFactory.createMatteBorder( 1, 1, 1, 1, Colores.clrTextoInactivo));
				fechaNacimiento.setBounds(21, 228, 308, 20);
				contentPane.add(fechaNacimiento);
				
				 txtEdad = new ClrCuadroDeTexto(500,false,"Edad");
				txtEdad.setColumns(10);
				txtEdad.setBounds(21, 259, 149, 20);
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
				cbGeneros.setBounds(180, 259, 149, 20);
				contentPane.add(cbGeneros);
				
				txtDireccion = new ClrCuadroDeTexto(100,false,"Direccion");
				txtDireccion.setColumns(10);
				txtDireccion.setBounds(19, 353, 308, 20);
				contentPane.add(txtDireccion);
				
				cbCiudades = new clrComboBox(conexionCombos.getInstance().listaCiudades(),1);
				cbCiudades.setBounds(21, 321, 308, 20);
				contentPane.add(cbCiudades);
				
				txtRegistro = new ClrCuadroDeTexto(11,false,"Registro");
				txtRegistro.setColumns(10);
				txtRegistro.setBounds(19, 508, 308, 20);
			
				contentPane.add(txtRegistro);
				
				lblFechaNacimiento = new clrLabel("Fecha de nacimiento", 1);
				lblFechaNacimiento.setHorizontalAlignment(SwingConstants.LEFT);
				lblFechaNacimiento.setForeground(Colores.clrSecundario);
				lblFechaNacimiento.setAlignmentX(0.5f);
				lblFechaNacimiento.setBounds(21, 203, 308, 20);
				contentPane.add(lblFechaNacimiento);
				
				btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50),2);
				btnCancelar.setBounds(328, 587, 129, 50);
				btnCancelar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						
					}
				});
				contentPane.add(btnCancelar);
				
				btnAceptar = new btnRedondo("Eliminar", new Rectangle(48, 172,121,50),5);
				btnAceptar.setSelected(true);
				btnAceptar.setBounds(180, 587,121,50);
				btnAceptar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						eliminarBacteriologo();
						
					}
				});
				contentPane.add(btnAceptar);
				
				txtTelefono1 = new ClrCuadroDeTexto(12,true,"Telefono de contacto 1");
				txtTelefono1.setBounds(20, 384, 308, 20);
				contentPane.add(txtTelefono1);
			
				txtTelefono1.setColumns(10);
				
				txtTelefono2 = new ClrCuadroDeTexto(12,true,"Telefono de contacto 2");
				txtTelefono2.setBounds(20, 415, 308, 20);
			
				contentPane.add(txtTelefono2);
				
				txtTelefono2.setColumns(10);
				
				txtEmail1 = new ClrCuadroDeTexto(100,false,"E-mail para envio de notificaciones");
				txtEmail1.setBounds(20, 446, 308, 20);
				contentPane.add(txtEmail1);
				txtEmail1.setColumns(10);
				
				txtEmail2 = new ClrCuadroDeTexto(100,false,"E-mail");
				txtEmail2.setBounds(19, 477, 308, 20);
				contentPane.add(txtEmail2);
				txtEmail2.setColumns(10);
				
				txtTitulo = new ClrCuadroDeTexto(100,false,"Titulo");
				txtTitulo.setColumns(10);
				txtTitulo.setBounds(19, 539, 308, 20);
				contentPane.add(txtTitulo);
				
				clrScrollBar scrollPane = new clrScrollBar();
				scrollPane.setBounds(339, 78, 301, 115);
				contentPane.add(scrollPane);
				
				
				cbResidencia = new clrComboBox(conexionCombos.getInstance().listaZonaResidencial(),1);
				cbResidencia.setBounds(21, 290, 308, 20);
				contentPane.add(cbResidencia);
				
				txtOtrosEstudios = new clrtextpane(500,false,"Otros estudios",0);
				scrollPane.setViewportView(txtOtrosEstudios);
				
				clrScrollBar clrScrollBar_ = new clrScrollBar();
				clrScrollBar_.setBounds(339, 200, 301, 115);
				contentPane.add(clrScrollBar_);
				
				txtInfAdicional = new clrtextpane(500,false,"Información adicional",0);
				clrScrollBar_.setViewportView(txtInfAdicional);
				
				clrPanelBordes panelFirma = new clrPanelBordes(false);
				panelFirma.setLayout(null);
				panelFirma.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Arrastra aqu\u00ED la imagen para la firma (325x325)", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
				panelFirma.setBounds(337, 321, 303, 243);
				contentPane.add(panelFirma);
				
				
				lblImagenFirma = new JLabel("");
				lblImagenFirma.setBounds(10, 42, 283, 138);
				panelFirma.add(lblImagenFirma);
				
				
				TitledBorder dragBorder = new javax.swing.border.TitledBorder( new MatteBorder(2, 2, 2, 2, Colores.clrSecundario),"Suelta la imagen", TitledBorder.LEFT, TitledBorder.TOP, null,Colores.clrPrincipal);
				   new arrastraYsuelta( System.out, panelFirma,dragBorder, new arrastraYsuelta.Listener()
			        {   public void filesDropped( java.io.File[] files )
			            {  
			                  try
			                    {
				                	firma = resize(files[0].getCanonicalPath(),lblImagenFirma.getWidth(),lblImagenFirma.getHeight());
				                	lblImagenFirma.setIcon(new ImageIcon(firma));
				                	System.out.println("path path : >>>>>>>>>>>>>>>>>>>>>"+ files[0].getCanonicalPath() + "\n" );
				                    }   // end try
			                    catch( java.io.IOException e ) {}
			                  // end for: through each dropped file
			            }   // end filesDropped
			        }); // end FileDrop.Listener
				
				txtClaveFirma = new ClrCuadroDeTexto(10,true,"Clave para firmar");
				txtClaveFirma.setColumns(10);
				txtClaveFirma.setBounds(10, 207, 283, 25);
				panelFirma.add(txtClaveFirma);
				
		
				
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
	
	public void  ingresarBacteriologo(){
		
		
		
		if (cbTipoID.getSelectedIndex()==0||txtID.getText().equals("")||txtID.getText().equals(txtID.getLabel())||txtnombres.getText().equals("")||txtnombres.getText().equals("Nombres")||txtApellidos.getText().equals("")||txtApellidos.getText().equals("Apellidos")
				||fechaNacimiento.getDate()==null||cbResidencia.getSelectedIndex()==0||cbGeneros.getSelectedIndex()==0||cbCiudades.getSelectedIndex()==0||txtEdad.getText().equals("error - fecha exedida")||txtEdad.getText().equals("Sin fecha de nacimiento")||txtEdad.getText().equals("")||FIS==null||txtClaveFirma.getText().equals(txtClaveFirma.getLabel())) {
			
			lblincorrectos.setText("Faltan campos por llenar");
		} else {
			lblincorrectos.setText("");

			String Direccauxi="";
			String Email1auxi="";
			String Email2auxi="";
			String Tel1auxi="";
			String Tel2auxi="";
			String registroauxi="";
			String tituloauxi="";
			String estudiosauxi="";
			String inf_adicionalauxi="";
			if(!txtDireccion.getText().equals("Direccion"))Direccauxi=txtDireccion.getText();
			if(!txtEmail1.getText().equals("E-mail para envio de notificaciones"))Email1auxi=txtEmail1.getText();
			if(!txtEmail2.getText().equals("E-mail"))Email2auxi=txtEmail2.getText();
			if(!txtTelefono1.getText().equals("Telefono de contacto 1"))Tel1auxi=txtTelefono1.getText();
			if(!txtTelefono2.getText().equals("Telefono de contacto 2"))Tel2auxi=txtTelefono2.getText();
			if(!txtRegistro.getText().equals("Registro"))registroauxi=txtRegistro.getText();
			if(!txtTitulo.getText().equals("Titulo"))tituloauxi=txtTitulo.getText();
			if(!txtOtrosEstudios.getText().equals("Otros estudios"))estudiosauxi=txtOtrosEstudios.getText();
			if(!txtInfAdicional.getText().equals("Informacion adicional"))inf_adicionalauxi=txtInfAdicional.getText();
			
			
			Bacteriologo bacteriologo=new Bacteriologo(txtID.getText(), cbTipoID.getSelectedIndex(), txtnombres.getText(), txtApellidos.getText(), fechaNacimiento.getDate(), calculaFecha(fechaNacimiento.getDate()), cbGeneros.getSelectedIndex()-1, cbResidencia.getSelectedIndex(), cbCiudades.getSelectedItem().toString(), Direccauxi, Email1auxi, Email2auxi, Tel1auxi, Tel2auxi, registroauxi, tituloauxi, estudiosauxi, inf_adicionalauxi,firma,FIS,Integer.parseInt(txtClaveFirma.getText()));
		
			
			conexion cone=conexion.getInstance();
			if (cone.nuevoBacteriologo(esta, bacteriologo)) {
				esta.principal.registrarAccion("Creacion de bacteriólogo '"+txtnombres.getText()+" "+txtApellidos.getText()+"'");
				dispose();
			}else{
				lblincorrectos.setText("Error  en la conexion con la base de datos");
			}
	
				}
		}
	
	
public void  modificarBacteriologo(){
		
		
		
		if (cbTipoID.getSelectedIndex()==0||txtID.equals("")||txtID.equals("Documento de identificacion")||txtnombres.equals("")||txtnombres.equals("Nombres")||txtApellidos.equals("")||txtApellidos.equals("Apellidos")
				||fechaNacimiento.getDate()==null||cbResidencia.getSelectedIndex()==0||cbGeneros.getSelectedIndex()==0||cbCiudades.getSelectedIndex()==0||txtEdad.equals("error - fecha exedida")||txtEdad.equals("Sin fecha de nacimiento")||txtEdad.equals("")) {
			
			lblincorrectos.setText("Faltan campos por llenar");
		} else {
			lblincorrectos.setText("");

			String Direccauxi="";
			String Email1auxi="";
			String Email2auxi="";
			String Tel1auxi="";
			String Tel2auxi="";
			String registroauxi="";
			String tituloauxi="";
			String estudiosauxi="";
			String inf_adicionalauxi="";
			if(!txtDireccion.getText().equals("Direccion"))Direccauxi=txtDireccion.getText();
			if(!txtEmail1.getText().equals("E-mail para envio de notificaciones"))Email1auxi=txtEmail1.getText();
			if(!txtEmail2.getText().equals("E-mail"))Email2auxi=txtEmail2.getText();
			if(!txtTelefono1.getText().equals("Telefono de contacto 1"))Tel1auxi=txtTelefono1.getText();
			if(!txtTelefono2.getText().equals("Telefono de contacto 2"))Tel2auxi=txtTelefono2.getText();
			if(!txtRegistro.getText().equals("Registro"))registroauxi=txtRegistro.getText();
			if(!txtTitulo.getText().equals("Titulo"))tituloauxi=txtTitulo.getText();
			if(!txtOtrosEstudios.getText().equals("Otros estudios"))estudiosauxi=txtOtrosEstudios.getText();
			if(!txtInfAdicional.getText().equals("Informacion adicional"))inf_adicionalauxi=txtInfAdicional.getText();
			
			Bacteriologo bacteriologo=new Bacteriologo(txtID.getText(), cbTipoID.getSelectedIndex(), txtnombres.getText(), txtApellidos.getText(), fechaNacimiento.getDate(), calculaFecha(fechaNacimiento.getDate()), cbGeneros.getSelectedIndex()-1, cbResidencia.getSelectedIndex(), cbCiudades.getSelectedItem().toString(), Direccauxi, Email1auxi, Email2auxi, Tel1auxi, Tel2auxi, registroauxi, tituloauxi, estudiosauxi, inf_adicionalauxi,firma,FIS,Integer.parseInt(txtClaveFirma.getText()));
			
			conexion cone=conexion.getInstance();
			if (cone.editarBacteriologo(bacteriologo)) {
				esta.principal.registrarAccion("Bacteriólogo modificado :'"+txtnombres.getText()+" "+txtApellidos.getText()+"'");
				dispose();
			}else{
				lblincorrectos.setText("Error  en la conexion con la base de datos");
			}
			
		
				}
	}

public void  eliminarBacteriologo(){
	
	
	
	if (idBacteriologo.equals("")) {
		
		lblincorrectos.setText("no has escogio ningún bacteriologo");
	} else {
		lblincorrectos.setText("");
		conexion cone=conexion.getInstance();
		if (cone.eliminarBacteriologo(idBacteriologo)) {
			esta.principal.registrarAccion("Eliminación de bacteriólogo : '"+txtnombres.getText()+" "+txtApellidos.getText()+"'");
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

	
	public static void FNumKeyTyped(JTextField txt,KeyEvent evt,int pValor)	{
	if (txt.getText().length()>=pValor)
	{
	evt.consume();
	}
	}
	
	public void llenar(Bacteriologo bac){
		idBacteriologo=bac.getId();
		
		firma=bac.getFirma();
		lblImagenFirma.setIcon(new ImageIcon(firma));
		
		txtID.setText(bac.getId()+"");
		txtID.setForeground(Color.black);
		txtID.setEditable(false);

		cbTipoID.setSelectedIndex(bac.getTipoId());
		txtnombres.setText(bac.getNombres());
		txtnombres.setForeground(Color.black);
		txtApellidos.setText(bac.getApellidos());
		txtApellidos.setForeground(Color.black);
		fechaNacimiento.setDate(bac.getFechaNacimiento());
		txtEdad.setText(calculaFecha(fechaNacimiento.getDate()));
		txtEdad.setForeground(Color.black);
		cbGeneros.setSelectedIndex(bac.getGenero()+1);
		cbResidencia.setSelectedIndex(bac.getZonaResidencial());
		cbCiudades.setSelectedItem(bac.getCiudad());
		
		if(!bac.getRegistro().equals("")){
			txtRegistro.setText(bac.getRegistro()+"");
			txtRegistro.setForeground(Color.black);

		}else{
			txtRegistro.reiniciar();
		}

		
		if(!bac.getTitulo().equals("")){
			txtTitulo.setText(bac.getTitulo()+"");
			txtTitulo.setForeground(Color.black);

		}else{
			txtTitulo.reiniciar();
		}

		
		if(!bac.getOtrosEstudios().equals("")){
			txtOtrosEstudios.setText(bac.getOtrosEstudios()+"");
			txtOtrosEstudios.setForeground(Color.black);

		}else{
			txtOtrosEstudios.reiniciar();
		}

		
		if(!bac.getInf_adicional().equals("")){
			txtInfAdicional.setText(bac.getInf_adicional()+"");
			txtInfAdicional.setForeground(Color.black);

		}else{
			txtInfAdicional.reiniciar();
		}

		
		if(!bac.getDireccion().equals("")){
			txtDireccion.setText(bac.getDireccion());
			txtDireccion.setForeground(Color.black);

			}else{
				txtDireccion.reiniciar();
			}
	
		if(!bac.getEmail1().equals("")){
			txtEmail1.setText(bac.getEmail1());
			txtEmail1.setForeground(Color.black);
			}else{
				txtEmail1.reiniciar();
			}
	
		if(!bac.getEmail2().equals("")){
			txtEmail2.setText(bac.getEmail2());
			txtEmail2.setForeground(Color.black);

		}else{
			txtEmail2.reiniciar();
		}

		if(!bac.getTelefono1().equals("")){
			txtTelefono1.setText(bac.getTelefono1()+"");
			txtTelefono1.setForeground(Color.black);

		}else{
			txtTelefono1.reiniciar();
		}

		if(!bac.getTelefono2().equals("")){
			txtTelefono2.setText(bac.getTelefono2()+"");
			txtTelefono2.setForeground(Color.black);

		}else{
			txtTelefono2.reiniciar();
		}

		
		if(bac.getClaveFirma()!=-1){
			txtClaveFirma.setText(""+bac.getClaveFirma());
			txtClaveFirma.setForeground(Color.black);

		}else{
			txtClaveFirma.reiniciar();
		}

		
	}
	
	
	


public Image resize(String imgPath, int newW, int newH) {
	 ImageIcon MyImage = new ImageIcon(imgPath);
     Image img = MyImage.getImage();

    int w = img.getWidth(null);
    int h = img.getHeight(null);
    BufferedImage imagenRedimensionada = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = imagenRedimensionada.createGraphics();
    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
    g.dispose();
    Image imgNew=  imagenRedimensionada;
    
       
    try { 
    	ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(imagenRedimensionada,"png", os); 
		FIS = new ByteArrayInputStream(os.toByteArray());
	} catch (IOException e) {
		
		e.printStackTrace();
	} 

    return imgNew;
}


public void reportarError(String error){
	lblincorrectos.setText(error);
}
}
