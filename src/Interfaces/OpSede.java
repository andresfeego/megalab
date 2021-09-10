package Interfaces;

import interfaces_Modificadas.ClrCuadroDeTexto;
import interfaces_Modificadas.Colores;
import interfaces_Modificadas.btnRedondo;
import interfaces_Modificadas.circuloUsuario;
import interfaces_Modificadas.clrComboBox;
import interfaces_Modificadas.clrLabel;
import interfaces_Modificadas.clrPanelBordes;
import interfaces_Modificadas.clrRadioButton;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
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
import auxiliares.MiLaboratorio;
import auxiliares.Sede;
import auxiliares.Usuario;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;

import com.sun.corba.se.impl.encoding.CodeSetConversion.CTBConverter;
import com.toedter.calendar.JDateChooser;

import conexion.conexion;
import conexion.conexionBusqueda;
import conexion.conexionCombos;

import javax.swing.JPanel;

public class OpSede extends JDialog{
	private clrPanelBordes contentPane;
	private clrLabel lblincorrectos;
	private Principal principal;
	private Usuario usuario;
	private clrLabel lblinicio;
	private circuloUsuario btnRedondo_;
	private OpSede esta;
	
	
	private clrComboBox cbTipoID;
	private ClrCuadroDeTexto txtNombreEstacion;
	private ClrCuadroDeTexto txtNombreSede;
	private btnRedondo btnCancelar;
	private btnRedondo btnAceptar;
	private lbBacteriologoXid lb1;
	private lbBacteriologoXnombre lb2;
	private lbBacteriologoXapellido lb3;
	
	private ButtonGroup BG;
	private clrRadioButton rdbtnIconosBlancos;
	private clrRadioButton rdbtnIconosNegros;
	
	private    btnRedondo btnColorPrincipal;
	private  btnRedondo btnColorSecundario ;
	private  btnRedondo btnColorAlertas;
	
	private InputStream FIS=null;
	private Image logo=null;
	
	public OpSede(Principal principal, Usuario usuario) {
		super(principal,true);
		this.principal=principal;
		this.usuario=usuario;
		this.esta=this;

		//this.setDefaultLookAndFeelDecorated(false);


		setBounds(100, 100, 349, 476);
		this.setUndecorated(true);
		this.setLocationRelativeTo(getParent()); 
		contentPane = new clrPanelBordes(true);
		contentPane.setLayout(null);
		setContentPane(contentPane);


		
		


		
		
		

		lblinicio = new clrLabel("Datos para esta estaci\u00F3n",2,true);
		lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblinicio.setBounds(10, 11, 250, 28);
		contentPane.add(lblinicio);
				
		lblincorrectos = new clrLabel("", 1);
		lblincorrectos.setForeground(Colores.clrAlertaCamarada);
		lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
		lblincorrectos.setAlignmentX(0.5f);
		lblincorrectos.setBounds(20, 50, 606, 28);
		contentPane.add(lblincorrectos);
		
	
		
		txtNombreEstacion = new ClrCuadroDeTexto(100,false,"Nombre de la estaci\u00F3n",1);
		txtNombreEstacion.setBounds(21, 110, 308, 20);
		
		contentPane.add(txtNombreEstacion);
		
		cbTipoID = new clrComboBox(conexionCombos.getInstance().listaCiudades(),1);
		cbTipoID.setBounds(20, 79, 309, 20);
		contentPane.add(cbTipoID);
		
		txtNombreSede = new ClrCuadroDeTexto(100,false,"Nombre de la sede",1);
		txtNombreSede.setColumns(10);
		txtNombreSede.setBounds(21, 141, 308, 20);
		contentPane.add(txtNombreSede);
		
		btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50),2);
		btnCancelar.setBounds(181, 412, 129, 50);
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		contentPane.add(btnCancelar);
		
		btnAceptar = new btnRedondo("Ingresar", new Rectangle(48, 172,121,50),1);
		btnAceptar.setSelected(true);
		btnAceptar.setBounds(33, 412,121,50);
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			ingresarDatos();
				
			}
		});
		contentPane.add(btnAceptar);
		
		
		TitledBorder dragBorder = new javax.swing.border.TitledBorder( new MatteBorder(2, 2, 2, 2, Colores.clrSecundario),"Suelta la imagen", TitledBorder.LEFT, TitledBorder.TOP, null,Colores.clrPrincipal);
		   
		   btnColorPrincipal = new btnRedondo("Color principal", new Rectangle(48, 172,121,50), 1,true);
		   btnColorPrincipal.setSelected(true);
		   btnColorPrincipal.setBounds(20, 177, 309, 50);
		   btnColorPrincipal.setSecundario(Colores.clrSecundario);
		   btnColorPrincipal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaColores colores=new VentanaColores(OpSede.this.principal, btnColorPrincipal.getSecundario());
				
				btnColorPrincipal.setSecundario(colores.escuchar());
				contentPane.updateUI();
			}
		});
		   contentPane.add(btnColorPrincipal);
		   
		   btnColorSecundario = new btnRedondo("Color secundario", new Rectangle(48, 172,121,50), 1,true);
		   btnColorSecundario.setSelected(true);
		   btnColorSecundario.setBounds(20, 238, 309, 50);
		   btnColorSecundario.setSecundario(Colores.clrSecundario);
		   btnColorSecundario.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaColores colores=new VentanaColores(OpSede.this.principal, btnColorSecundario.getSecundario());
					
					btnColorSecundario.setSecundario(colores.escuchar());
					contentPane.updateUI();
				}
			});
		   contentPane.add(btnColorSecundario);
		   
		   btnColorAlertas = new btnRedondo("Color de alertas", new Rectangle(48, 172,121,50), 1,true);
		   btnColorAlertas.setSelected(true);
		   btnColorAlertas.setBounds(20, 299, 309, 50);
		   btnColorAlertas.setSecundario(Colores.clrAlertaCamarada);
		   btnColorAlertas.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaColores colores=new VentanaColores(OpSede.this.principal, btnColorAlertas.getSecundario());
					
					btnColorAlertas.setSecundario(colores.escuchar());
					contentPane.updateUI();
				}
			});
		   contentPane.add(btnColorAlertas);
		
		   BG = new ButtonGroup();

			rdbtnIconosBlancos = new clrRadioButton("Iconos Blancos");
			rdbtnIconosBlancos.setBounds(20, 372, 142, 23);
			rdbtnIconosBlancos.setSelected(true);
			contentPane.add(rdbtnIconosBlancos);
			BG.add(rdbtnIconosBlancos);

			rdbtnIconosNegros = new clrRadioButton("Iconos negros");
			rdbtnIconosNegros.setBounds(184, 372, 142, 23);
			contentPane.add(rdbtnIconosNegros);
			BG.add(rdbtnIconosNegros);
		
		
		
		SwingUtilities.invokeLater( new Runnable() 
        { 
        public void run() 
            { 
            btnAceptar.requestFocusInWindow(); 
            llenar();
        } 
    }); 
		
		this.setVisible(true);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
		
	}
	
	public void  ingresarDatos(){
		
		if (cbTipoID.getSelectedIndex()==0 || txtNombreEstacion.getText().equals(txtNombreEstacion.getLabel()) || txtNombreSede.getText().equals(txtNombreSede.getLabel())) {
			lblincorrectos.setText("Faltan campos por llenar");
		} else {
			lblincorrectos.setText("");
			String ciudad=cbTipoID.getSelectedItem()+"";
			String nombre=txtNombreEstacion.getText();
			String sede=txtNombreSede.getText();
			
			String principal=btnColorPrincipal.getSecundario().getRed()+","+btnColorPrincipal.getSecundario().getGreen()+","+btnColorPrincipal.getSecundario().getBlue();
			String alertas=btnColorAlertas.getSecundario().getRed()+","+btnColorAlertas.getSecundario().getGreen()+","+btnColorAlertas.getSecundario().getBlue();
			String secundario=btnColorSecundario.getSecundario().getRed()+","+btnColorSecundario.getSecundario().getGreen()+","+btnColorSecundario.getSecundario().getBlue();
			
			String iconosNegros=rdbtnIconosNegros.isSelected()+"";
			
			FileInputStream entrada=null;
			Properties propiedades = new Properties();
			
			try {

				entrada = new FileInputStream("C:/6342522/conad.prop");

				propiedades.load(entrada);

			} catch (IOException ex) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR COLORES> : no es posible leer el archivo de configuración");
			}
			
			propiedades.setProperty("ciudad", ciudad);
			propiedades.setProperty("nombre", nombre);
			propiedades.setProperty("sede", sede);
			
			propiedades.setProperty("principal", principal);
			propiedades.setProperty("alertas", alertas);
			propiedades.setProperty("secundario", secundario);
			propiedades.setProperty("iconosNegros", iconosNegros);
			
		/*	Sede Sede=new Sede(ciudad, nombre, sede, principal, secundario, alertas, iconosNegros);
			
			if (conexion.getInstance().configSede(esta, Sede)) {
				this.principal.registrarAccion("Cambio de configuración en la sede de a base de datos");
			} else {
				this.principal.registrarErrorDB("< ERROR SEDE > Error al registrar datos de sede en la baso de datos");
			}*/
	          
	       try {
			   FileOutputStream fos=new FileOutputStream("C:/6342522/conad.prop");
	 
	                        propiedades.store(fos,null);
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR COLORES> : no es posible escribir  el archivo de configuración");

		}
	                     
	 
	            
			
		}
		
		esta.principal.registrarAccion("Cambio de datos 'sede' en archivo de configuración");
		
		
		dispose();
		Colores.calcularColorBordeClaro();
		
		
		
		principal.construirVentana();
	}
	
	

	
	
	
	public void llenar(){
		FileInputStream entrada=null;
		Properties propiedades = new Properties();
		
		try {

			entrada = new FileInputStream("C:/6342522/conad.prop");

			propiedades.load(entrada);

		} catch (IOException ex) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR COLORES> : no es posible leer el archivo de configuración");
		}
		String ciudad=propiedades.getProperty("ciudad");
		String nombre=propiedades.getProperty("nombre");
		String sede=propiedades.getProperty("sede");		
		
		String principal=propiedades.getProperty("principal");
		String alertas=propiedades.getProperty("alertas");
		String secundario=propiedades.getProperty("secundario");
		String iconosNegros=propiedades.getProperty("iconosNegros");
		
		StringTokenizer STP=new StringTokenizer(principal, ",");
		btnColorPrincipal.setSecundario(new Color(Integer.parseInt(STP.nextToken()),Integer.parseInt(STP.nextToken()),Integer.parseInt(STP.nextToken())));

		StringTokenizer STS=new StringTokenizer(secundario, ",");
		btnColorSecundario.setSecundario(new Color(Integer.parseInt(STS.nextToken()),Integer.parseInt(STS.nextToken()),Integer.parseInt(STS.nextToken())));

		StringTokenizer STA=new StringTokenizer(alertas, ",");
		btnColorAlertas.setSecundario(new Color(Integer.parseInt(STA.nextToken()),Integer.parseInt(STA.nextToken()),Integer.parseInt(STA.nextToken())));

		boolean clrIconosNegros=Boolean.parseBoolean(iconosNegros);
		if (clrIconosNegros) {
			rdbtnIconosNegros.setSelected(true);
		} else {
			rdbtnIconosBlancos.setSelected(true);
		}
		
		cbTipoID.setSelectedItem(ciudad);
		txtNombreEstacion.setText(nombre);
		txtNombreSede.setText(sede);
		
	}
	
	
	


public Image resize(String imgPath) {
	 ImageIcon MyImage = new ImageIcon(imgPath);
     Image img = MyImage.getImage();

    int w = img.getWidth(null);
    int h = img.getHeight(null);
    int  newW=500;
    int newH=((500*h)/w);
    BufferedImage imagenRedimensionada = new BufferedImage(newW,newH , BufferedImage.TYPE_INT_ARGB);
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


public Image resize(String imgPath, int newW) {
	 ImageIcon MyImage = new ImageIcon(imgPath);
    Image img = MyImage.getImage();

   int w = img.getWidth(null);
   int h = img.getHeight(null);
   int newH=((newW*h)/w);
   BufferedImage imagenRedimensionada = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
   Graphics2D g = imagenRedimensionada.createGraphics();
   g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
   g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
   g.dispose();
   Image imgNew=  imagenRedimensionada;
 

   return imgNew;
}

public Image resize(Image imagen, int newW) {
	
   Image img = imagen;

  int w = img.getWidth(null);
  int h = img.getHeight(null);
  int newH=((newW*h)/w);
  BufferedImage imagenRedimensionada = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
  Graphics2D g = imagenRedimensionada.createGraphics();
  g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
  g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
  g.dispose();
  Image imgNew=  imagenRedimensionada;


  return imgNew;
}


public void reportarError(String error){
	lblincorrectos.setText(error);
}
}
