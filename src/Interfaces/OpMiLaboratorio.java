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
import auxiliares.MiLaboratorio;
import auxiliares.Usuario;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;

import com.toedter.calendar.JDateChooser;

import conexion.conexion;
import conexion.conexionBusqueda;
import conexion.conexionCombos;

public class OpMiLaboratorio extends JDialog{
	private clrPanelBordes contentPane;
	private clrLabel lblincorrectos;
	private Principal principal;
	private Usuario usuario;
	private clrLabel lblinicio;
	private circuloUsuario btnRedondo_;
	private OpMiLaboratorio esta;
	
	
	private clrComboBox cbTipoID;
	private ClrCuadroDeTexto txtnumeroDoc;
	private ClrCuadroDeTexto txtRazonSocial;
	private ClrCuadroDeTexto txtCodIPS;
	private ClrCuadroDeTexto txtDireccion;
	private btnRedondo btnCancelar;
	private btnRedondo btnAceptar;
	private ClrCuadroDeTexto txtTelefono1;
	private ClrCuadroDeTexto txtTelefono2;
	private ClrCuadroDeTexto txtEmail1;
	private ClrCuadroDeTexto txtEmail2;
	private lbBacteriologoXid lb1;
	private lbBacteriologoXnombre lb2;
	private lbBacteriologoXapellido lb3;
	private 	JLabel lblImagenFirma;
	
	
	private InputStream FIS=null;
	private Image logo=null;
	
	public OpMiLaboratorio(Principal principal, Usuario usuario) {
		super(principal,true);
		this.principal=principal;
		this.usuario=usuario;
		this.esta=this;

		//this.setDefaultLookAndFeelDecorated(false);


		setBounds(100, 100, 650, 436);
		this.setUndecorated(true);
		this.setLocationRelativeTo(getParent()); 
		contentPane = new clrPanelBordes(true);
		contentPane.setLayout(null);
		setContentPane(contentPane);


		
		


		
		
		

		lblinicio = new clrLabel("Opciones de impresi\u00F3n",2,true);
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
		
	
		
		txtnumeroDoc = new ClrCuadroDeTexto(15,true,"Documento de identificación",1);
		txtnumeroDoc.setBounds(21, 110, 308, 20);
		
		contentPane.add(txtnumeroDoc);
		
		cbTipoID = new clrComboBox(new String[]{"Tipo de identificación","C.C. - Cedula de ciudadanía","C.E. - Cedula de extrangería","NIT - Numero de identificación tributária","PA - Pasaporte"},1);
		cbTipoID.setBounds(20, 79, 309, 20);
		contentPane.add(cbTipoID);
		
		txtRazonSocial = new ClrCuadroDeTexto(250,false,"Razon social",1);
		txtRazonSocial.setColumns(10);
		txtRazonSocial.setBounds(21, 141, 308, 20);
		contentPane.add(txtRazonSocial);
		
		txtCodIPS = new ClrCuadroDeTexto(15,true,"C\u00F3digo IPS",1);
		txtCodIPS.setColumns(10);
		txtCodIPS.setBounds(21, 172, 308, 20);
		contentPane.add(txtCodIPS);
		
		txtDireccion = new ClrCuadroDeTexto(100,false,"Direccion",1);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(20, 208, 308, 20);
		contentPane.add(txtDireccion);
		
		btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50),2);
		btnCancelar.setBounds(331, 368, 129, 50);
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		contentPane.add(btnCancelar);
		
		btnAceptar = new btnRedondo("Ingresar", new Rectangle(48, 172,121,50),1);
		btnAceptar.setSelected(true);
		btnAceptar.setBounds(183, 368,121,50);
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			ingresarDatos();
				
			}
		});
		contentPane.add(btnAceptar);
		
		txtTelefono1 = new ClrCuadroDeTexto(12,true,"Telefono de contacto 1",1);
		txtTelefono1.setBounds(21, 239, 308, 20);
		contentPane.add(txtTelefono1);

		
		txtTelefono2 = new ClrCuadroDeTexto(12,true,"Telefono de contacto 2",1);
		txtTelefono2.setBounds(21, 270, 308, 20);

	
		contentPane.add(txtTelefono2);
		
		txtTelefono2.setColumns(10);
		
		txtEmail1 = new ClrCuadroDeTexto(100,false,"E-mail para envio de notificaciones",1);
		txtEmail1.setBounds(21, 301, 308, 20);
		contentPane.add(txtEmail1);
		txtEmail1.setColumns(10);
		
		txtEmail2 = new ClrCuadroDeTexto(100,false,"E-mail",1);
		txtEmail2.setBounds(20, 332, 308, 20);
		contentPane.add(txtEmail2);
		txtEmail2.setColumns(10);
		
		clrPanelBordes panelFirma = new clrPanelBordes(false);
		panelFirma.setLayout(null);
		panelFirma.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) Colores.clrPrincipal), "Arrastra aqu\u00ED el logo para el laboratorio", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
		panelFirma.setBounds(337, 79, 303, 278);
		contentPane.add(panelFirma);
		
		
		lblImagenFirma = new JLabel("");
		lblImagenFirma.setBounds(10, 23, 283, 244);
		panelFirma.add(lblImagenFirma);
		
		
		TitledBorder dragBorder = new javax.swing.border.TitledBorder( new MatteBorder(2, 2, 2, 2, Colores.clrSecundario),"Suelta la imagen", TitledBorder.LEFT, TitledBorder.TOP, null,Colores.clrPrincipal);
		   new arrastraYsuelta( System.out, panelFirma,dragBorder, new arrastraYsuelta.Listener()
	        {   public void filesDropped( java.io.File[] files )
	            {  
	                  try
	                    {
	                	logo = resize(files[0].getCanonicalPath());
	                	lblImagenFirma.setIcon(new ImageIcon(resize(files[0].getCanonicalPath(),lblImagenFirma.getWidth())));
	                	System.out.println("path path : >>>>>>>>>>>>>>>>>>>>>"+ files[0].getCanonicalPath() + "\n" );
	                    }   // end try
	                    catch( java.io.IOException e ) {
	                    	lblincorrectos.setText("Error en la imagen seleccionada esta debe estar en formato png con fondo transparente");
	                    	
	                    }
	                  // end for: through each dropped file
	            }   // end filesDropped
	        });
		
		
		
		
		
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
		
		
		
		if (txtRazonSocial.getText().equals(txtRazonSocial.getLabel()) || cbTipoID.getSelectedIndex()==0 || txtnumeroDoc.getText().equals(txtnumeroDoc.getLabel()) || txtCodIPS.getText().equals(txtCodIPS.getLabel()) || txtTelefono1.getText().equals(txtTelefono1.getLabel()) || txtTelefono2.getText().equals(txtTelefono2.getLabel()) || txtDireccion.getText().equals(txtDireccion.getLabel()) || txtEmail1.getText().equals(txtEmail1.getLabel()) || txtEmail2.getText().equals(txtEmail2.getLabel()) || FIS==null) {
			
			lblincorrectos.setText("Faltan campos por llenar");
		} else {
			lblincorrectos.setText("");

			MiLaboratorio lab=new MiLaboratorio(txtRazonSocial.getText(), cbTipoID.getSelectedIndex(),txtnumeroDoc.getText(), txtCodIPS.getText(), txtDireccion.getText(),txtTelefono1.getText(), txtTelefono2.getText(), txtEmail1.getText(), txtEmail2.getText(), logo, FIS);
		
			
			
			if (conexion.getInstance().configLaboratorio(esta, lab)){
				esta.principal.registrarAccion("Configuración de datos de laboratorio '"+lab.getRazonSocial()+"'");
				dispose();
			}  else{
				lblincorrectos.setText("Error  en la conexion con la base de datos");
			}
				}
		
		
	
		
	}
	
	

	
	
	
	public void llenar(){
		MiLaboratorio miLab=conexionBusqueda.getInstance().miLab();
		
		if(!miLab.getRazonSocial().equals("")){
			txtRazonSocial.setText(miLab.getRazonSocial());
		txtRazonSocial.setForeground(Color.black);

		}else{
			txtRazonSocial.reiniciar();
		}
		
		
		cbTipoID.setSelectedIndex(miLab.getTipoDoc());
		
			if(!miLab.getNumeroDoc().equals("")){
			txtnumeroDoc.setText(""+miLab.getNumeroDoc());
			txtnumeroDoc.setForeground(Color.black);

		}else{
			txtnumeroDoc.reiniciar();
		}
			
			if(!miLab.getCodigoIPS().equals("")){
				txtCodIPS.setText(""+miLab.getCodigoIPS());
				txtCodIPS.setForeground(Color.black);

			}else{
				txtCodIPS.reiniciar();
			}
		
			if(!miLab.getTelefono1().equals("")){
				txtTelefono1.setText(""+miLab.getTelefono1());
				txtTelefono1.setForeground(Color.black);

			}else{
				txtTelefono1.reiniciar();
			}
			
			if(!miLab.getTelefono2().equals("")){
				txtTelefono2.setText(""+miLab.getTelefono2());
				txtTelefono2.setForeground(Color.black);

			}else{
				txtTelefono2.reiniciar();
			}
			
			
			if(!miLab.getDireccion().equals("")){
				txtDireccion.setText(miLab.getDireccion());
				txtDireccion.setForeground(Color.black);
				

			}else{
				txtDireccion.reiniciar();
			}
			
			
			if(!miLab.getEmail1().equals("")){
				txtEmail1.setText(miLab.getEmail1());
				txtEmail1.setForeground(Color.BLACK);

			}else{
				txtEmail1.reiniciar();
			}
			
			if(!miLab.getEmai2().equals("")){
				txtEmail2.setText(miLab.getEmai2());
				txtEmail2.setForeground(Color.black);

			}else{
				txtEmail2.reiniciar();
			}
		
	

			if (miLab.getLogoImagen()!=null) {
				lblImagenFirma.setIcon(new ImageIcon(resize(miLab.getLogoImagen(), lblImagenFirma.getWidth())));		
			}
		

		
	}
	
	
	


public Image resize(String imgPath) {
	 ImageIcon MyImage = new ImageIcon(imgPath);
     Image img = MyImage.getImage();

    int w = img.getWidth(null);
    int h = img.getHeight(null);
    int  newW=120;
    int newH=((120*h)/w);
    BufferedImage imagenRedimensionada = new BufferedImage(newW,newH , BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = imagenRedimensionada.createGraphics();
    g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
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
