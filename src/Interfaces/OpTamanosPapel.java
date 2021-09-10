package Interfaces;

import interfaces_Modificadas.ClrCuadroDeTexto;
import interfaces_Modificadas.Colores;
import interfaces_Modificadas.btnRedondo;
import interfaces_Modificadas.circuloUsuario;
import interfaces_Modificadas.clrComboBox;
import interfaces_Modificadas.clrLabel;
import interfaces_Modificadas.clrPanelBordes;
import interfaces_Modificadas.clrPestanas;
import interfaces_Modificadas.clrScrollBar;
import interfaces_Modificadas.clrtextpane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
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
import auxiliares.encaFirma;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;

import com.itextpdf.text.pdf.codec.Base64.OutputStream;
import com.toedter.calendar.JDateChooser;

import conexion.conexion;
import conexion.conexionBusqueda;
import conexion.conexionCombos;

import javax.swing.JTabbedPane;

import interfaces_Modificadas.clrRadioButton;

import javax.swing.JSeparator;

public class OpTamanosPapel extends JDialog{
	private clrPanelBordes contentPane;
	private clrLabel lblincorrectos;
	private Principal principal;
	private Usuario usuario;
	private clrLabel lblinicio;
	private circuloUsuario btnRedondo_;
	private OpTamanosPapel esta;
		private btnRedondo btnCancelar;
	private btnRedondo btnAceptar;
	private TitledBorder dragBorder;
	
	private clrComboBox cbPapelListados;
	private clrPanelBordes panelFirmaListados ;
	private JLabel lblEspacioListados ;
	private ClrCuadroDeTexto txtEspacioListados;
	private 	JLabel lblImagenFirmaListados;
	private clrRadioButton chkImagenListados;
	private clrComboBox cbImpresoraListados;
	private  clrtextpane txtPieListados ;
	
	private clrComboBox cbPapelFacturas;
	private ClrCuadroDeTexto txtEspacioFacturas;
	private 	JLabel lblImagenFirmaFacturas;
	private clrPanelBordes panelFirmaFacturas;
	private JLabel lblEspacioFacturas ;
	private clrRadioButton chkImagenFacturas;
	private clrComboBox cbImpresoraFacturas;
	private  clrtextpane txtPieFacturas ;
	
	private clrComboBox cbPapelRecepciones;
	private ClrCuadroDeTexto txtEspacioRecepciones;
	private 	JLabel lblImagenFirmaRecepciones;
	private clrPanelBordes panelFirmaRecepciones;
	private JLabel lblEspacioRecepciones ;
	private clrRadioButton chkImagenRecepciones;
	private clrComboBox cbImpresoraRecepciones;
	private  clrtextpane txtPieRecepciones ;
	
	private clrComboBox cbPapelCotizaciones;
	private ClrCuadroDeTexto txtEspacioCotizaciones;
	private 	JLabel lblImagenFirmaCotizaciones;
	private clrPanelBordes panelFirmaCotizaciones;
	private JLabel lblEspacioCotizaciones ;
	private clrRadioButton chkImagenCotizaciones;
	private clrComboBox cbImpresoraCotizaciones;
	private  clrtextpane txtPieCotizaciones ;

	private clrComboBox cbPapelNotas;
	private ClrCuadroDeTexto txtEspacioNotas;
	private 	JLabel lblImagenFirmaNotas;
	private clrPanelBordes panelFirmaNotas;
	private JLabel lblEspacioNotas ;
	private clrRadioButton chkImagenNotas;
	private clrComboBox cbImpresoraNotas;
	private  clrtextpane txtPieNotas ;
	
	private clrComboBox cbImpresoraStickers;
	private ClrCuadroDeTexto txtMarSuperior;
	private JLabel lblEspacioStickers;

	
	private ClrCuadroDeTexto txtFirma1Web;
	private 	JLabel lblImagenFirmaWeb;
	private clrPanelBordes panelFirmaWeb;
	private 	JLabel lblImagenEncaWeb;
	private clrPanelBordes panelEncaWeb;
	private clrRadioButton chkImpReportados;
	
	
	private  clrPanelBordes panelLis;
	private clrPanelBordes panelFac;
	private clrPanelBordes panelRec;
	private clrPanelBordes panelCot;
	private  clrPanelBordes panelNot;
	private  clrPanelBordes panelSti;
	private  clrPanelBordes panelWeb;
	
	private InputStream encaIS=null;
	private InputStream firmaIS=null;
	private Image logoListados=null;
	private Image logoFacturas=null;
	private Image logoRecepciones=null;
	private Image logoCotizaciones=null;
	private Image logoNotas=null;
	private Image logoStickers=null;
	private Image encabezadoWeb=null;
	private Image firmaWeb=null;
	private clrPestanas panelPestanas;
	
	private ClrCuadroDeTexto txtFirma2Web ;
	   private clrRadioButton chkImpTodo;
	private clrRadioButton chkImpNoRep;
	
	private  ClrCuadroDeTexto txtHeightSticker;
	private ClrCuadroDeTexto txtWidthSticker;
	private ClrCuadroDeTexto txtHeightPapel;
	private ClrCuadroDeTexto txtMarIzquierda;
	private ClrCuadroDeTexto txtWidthPapel;
	private clrRadioButton clrRadioButton;
	private clrRadioButton clrRadioButton_1;
	private JSeparator separator;
	
	
	public OpTamanosPapel(Principal principal, Usuario usuario) {
		super(principal,true);
		this.principal=principal;
		this.usuario=usuario;
		this.esta=this;

		//this.setDefaultLookAndFeelDecorated(false);


		setBounds(100, 100, 908, 672);
		this.setUndecorated(true);
		this.setLocationRelativeTo(getParent()); 
		contentPane = new clrPanelBordes(true);
		contentPane.setLayout(null);
		setContentPane(contentPane);


		
		


		
		
		

		lblinicio = new clrLabel("Opciones de impresi\u00F3n",2,true);
		lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblinicio.setBounds(10, 11, 888, 28);
		contentPane.add(lblinicio);
				
		lblincorrectos = new clrLabel("", 1);
		lblincorrectos.setForeground(Colores.clrAlertaCamarada);
		lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
		lblincorrectos.setAlignmentX(0.5f);
		lblincorrectos.setBounds(20, 50, 878, 28);
		contentPane.add(lblincorrectos);
		
		btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50),2);
		btnCancelar.setBounds(738, 611, 129, 50);
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		contentPane.add(btnCancelar);
		
		btnAceptar = new btnRedondo("Guardar", new Rectangle(48, 172,121,50),12);
		btnAceptar.setSelected(true);
		btnAceptar.setBounds(590, 611,121,50);
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			guardar();
				
			}
		});
		contentPane.add(btnAceptar);
		
		
		 dragBorder = new javax.swing.border.TitledBorder( new MatteBorder(2, 2, 2, 2, Colores.clrSecundario),"Suelta la imagen", TitledBorder.LEFT, TitledBorder.TOP, null,Colores.clrPrincipal);
		   
		   panelPestanas = new clrPestanas(JTabbedPane.TOP);
		   panelPestanas.setBounds(10, 89, 888, 511);
		   contentPane.add(panelPestanas);
			
		    panelLis = new clrPanelBordes(false);
			panelLis.setLayout(null);
		   panelPestanas.addTab("Listados y reportes", null, panelLis, null);
		   
		   panelFac = new clrPanelBordes(false);
		   panelFac.setLayout(null);
		   panelPestanas.addTab("Facturas", null, panelFac, null);
		   
		    panelRec = new clrPanelBordes(false);
		   panelRec.setLayout(null);
		   panelPestanas.addTab("Recepciones", null, panelRec, null);
		   
		   panelCot = new clrPanelBordes(false);
		   panelCot.setLayout(null);
		   panelPestanas.addTab("Cotizaciones", null, panelCot, null);
		   
		  panelNot = new clrPanelBordes(false);
		   panelNot.setLayout(null);
		   panelPestanas.addTab("Notas credito y debito", null, panelNot, null);
		   
		   panelSti = new clrPanelBordes(false);
		   panelSti.setLayout(null);
		   panelPestanas.addTab("Stickers", null, panelSti, null);
		   
		   panelWeb = new clrPanelBordes(false);
		   panelWeb.setLayout(null);
		   panelPestanas.addTab("Reportes web", null, panelWeb, null);
		   
	  
		   
		   
		   construirListados();
		   construirFacturas();
		   construirRecepciones();
		   construirCotizaciones();
		   construirNotas();
		   construirStickers();
		   construirReporteWeb();
		
		SwingUtilities.invokeLater( new Runnable() 
        { 
        public void run() 
            { 
            btnAceptar.requestFocusInWindow(); 
        } 
    }); 
		
		this.setVisible(true);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
		
	}
	
	
	

	
	
	
	
	
	
	


public Image resize(String imgPath) {
	 ImageIcon MyImage = new ImageIcon(imgPath);
     Image img = MyImage.getImage();

    int w = img.getWidth(null);
    int h = img.getHeight(null);
    int  newW=1200;
    int newH=((1200*h)/w);
    BufferedImage imagenRedimensionada = new BufferedImage(newW,newH , BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = imagenRedimensionada.createGraphics();
    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
    g.dispose();
    Image imgNew=  imagenRedimensionada;
    
       
  
    return imgNew;
}

public Image resizeEnca(String imgPath) {
	 ImageIcon MyImage = new ImageIcon(imgPath);
    Image img = MyImage.getImage();

   int w = img.getWidth(null);
   int h = img.getHeight(null);
   int  newW=612;
   int newH=((612*h)/w);
   BufferedImage imagenRedimensionada = new BufferedImage(newW,newH , BufferedImage.TYPE_INT_ARGB);
   Graphics2D g = imagenRedimensionada.createGraphics();
   g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
   g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
   g.dispose();
   Image imgNew=  imagenRedimensionada;
   
      
   try { 
   	ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(imagenRedimensionada,"png", os); 
		encaIS = new ByteArrayInputStream(os.toByteArray());
	} catch (IOException e) {
		
		e.printStackTrace();
	} 

   return imgNew;
}

public Image resizeFirma(String imgPath) {
	 ImageIcon MyImage = new ImageIcon(imgPath);
   Image img = MyImage.getImage();

  int w = img.getWidth(null);
  int h = img.getHeight(null);
  int  newW=300;
  int newH=((300*h)/w);
  BufferedImage imagenRedimensionada = new BufferedImage(newW,newH , BufferedImage.TYPE_INT_ARGB);
  Graphics2D g = imagenRedimensionada.createGraphics();
  g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
  g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
  g.dispose();
  Image imgNew=  imagenRedimensionada;
  
     
  try { 
  	ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(imagenRedimensionada,"png", os); 
		firmaIS = new ByteArrayInputStream(os.toByteArray());
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

public void construirListados(){
	   cbPapelListados = new clrComboBox(conexionCombos.getInstance().listaTamPapel(),1);
	   cbPapelListados.setBounds(10, 316, 186, 20);
	   panelLis.add(cbPapelListados);
	   
	   ImageIcon imagenListados = new ImageIcon(OpTamanosPapel.class.getResource("/Recursos/encabezado.png"));
	    

	    
	    txtEspacioListados = new ClrCuadroDeTexto(4,true,"Espacio para este encabezado en mil\u00EDmetros",1);
	    txtEspacioListados.setBounds(269, 130, 339, 25);
	    txtEspacioListados.setVisible(false);
	    panelLis.add(txtEspacioListados);
	   
	    lblEspacioListados = new JLabel("");
	   lblEspacioListados.setBounds(10, 78, 865, 138);
	   panelLis.add(lblEspacioListados);
	   lblEspacioListados.setVisible(false);
	   lblEspacioListados.setIcon(imagenListados);
	   lblEspacioListados.setBackground(Color.RED);
	   
	   ButtonGroup BG=new ButtonGroup();
	   
	    chkImagenListados = new clrRadioButton("Imagen de encabezado");
	   chkImagenListados.setBounds(10, 17, 216, 23);
	   chkImagenListados.setSelected(true);
	   chkImagenListados.addItemListener(new ItemListener() {
		
		@Override
		public void itemStateChanged(ItemEvent arg0) {
			if (chkImagenListados.isSelected()) {
			panelFirmaListados.setVisible(true);
			txtEspacioListados.setVisible(false);
			lblEspacioListados.setVisible(false);
			}else{
				panelFirmaListados.setVisible(false);
			txtEspacioListados.setVisible(true);
			lblEspacioListados.setVisible(true);
				
			}
			
		}
	});
	   panelLis.add(chkImagenListados);
	   
	   clrRadioButton chkEspacioListados = new clrRadioButton("Espacio para encabezado preimpreso");
	   chkEspacioListados.setBounds(243, 17, 309, 23);
	   panelLis.add(chkEspacioListados);
	   
	   BG.add(chkEspacioListados);
	   BG.add(chkImagenListados);
	   
	    panelFirmaListados = new clrPanelBordes(false);
	   panelFirmaListados.setBounds(10, 47, 863, 258);
	   panelLis.add(panelFirmaListados);
	   panelFirmaListados.setLayout(null);
	   panelFirmaListados.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) Colores.clrPrincipal), "Arrastra aqu\u00ED el encabezado", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
	   
	   
	   lblImagenFirmaListados = new JLabel("");
	   lblImagenFirmaListados.setBounds(10, 23, 843, 224);
	   panelFirmaListados.add(lblImagenFirmaListados);
	   new arrastraYsuelta( System.out, panelFirmaListados,dragBorder, new arrastraYsuelta.Listener()
        {   public void filesDropped( java.io.File[] files )
            {  
                  try
                    {
                	logoListados = resize(files[0].getCanonicalPath());
                	lblImagenFirmaListados.setIcon(new ImageIcon(resize(files[0].getCanonicalPath(),lblImagenFirmaListados.getWidth())));
                	System.out.println("path path : >>>>>>>>>>>>>>>>>>>>>"+ files[0].getCanonicalPath() + "\n" );
                    }   // end try
                    catch( java.io.IOException e ) {
                    	lblincorrectos.setText("Error en la imagen seleccionada esta debe estar en formato png con fondo transparente");
                    	
                    }
                  // end for: through each dropped file
            }   // end filesDropped
        });
	   
	   cbImpresoraListados = new clrComboBox(conexionCombos.getInstance().listaImpresoras(),1);
	   cbImpresoraListados.setSelectedIndex(1);
	   cbImpresoraListados.setBounds(206, 316, 309, 20);
	   panelLis.add(cbImpresoraListados);
	   
	   txtPieListados = new clrtextpane(500, false, "Nota para la parte final del documento", 0);
	   txtPieListados.setBounds(12, 347, 863, 122);
	   panelLis.add(txtPieListados);
	   
	   	//  llenando listados
	   
	   if (Colores.impimirEncabezadoListados&&Colores.enabezadoListados!=null) {
		chkImagenListados.setSelected(true);
    	lblImagenFirmaListados.setIcon(new ImageIcon(resize(Colores.enabezadoListados,lblImagenFirmaListados.getWidth())));

		
	}else{
		chkEspacioListados.setSelected(true);

	}
			txtEspacioListados.setText(Math.round(Colores.espacioEncabezadosListados*0.353)+"");

	   cbPapelListados.setSelectedItem(Colores.tamHojaListados);
	   cbImpresoraListados.setSelectedItem(Colores.impresoraListados);
	   if (!Colores.notaListados.equals("")) {
		   txtPieListados.setText(Colores.notaListados);
	}
	   
	   
	
}

public void construirFacturas(){
	   cbPapelFacturas = new clrComboBox(conexionCombos.getInstance().listaTamPapel(),1);
	   cbPapelFacturas.setBounds(10, 316, 186, 20);
	   panelFac.add(cbPapelFacturas);
	   
	   ImageIcon imagenFacturas = new ImageIcon(OpTamanosPapel.class.getResource("/Recursos/encabezado.png"));
	    

	    
	    txtEspacioFacturas = new ClrCuadroDeTexto(4,true,"Espacio para este encabezado en mil\u00EDmetros",1);
	    txtEspacioFacturas.setBounds(269, 130, 339, 25);
	    txtEspacioFacturas.setVisible(false);
	    panelFac.add(txtEspacioFacturas);
	   
	    lblEspacioFacturas = new JLabel("");
	   lblEspacioFacturas.setBounds(10, 78, 865, 138);
	   panelFac.add(lblEspacioFacturas);
	   lblEspacioFacturas.setVisible(false);
	   lblEspacioFacturas.setIcon(imagenFacturas);
	   lblEspacioFacturas.setBackground(Color.RED);
	   
	   ButtonGroup BG=new ButtonGroup();
	   
	    chkImagenFacturas = new clrRadioButton("Imagen de encabezado");
	   chkImagenFacturas.setBounds(10, 17, 216, 23);
	   chkImagenFacturas.setSelected(true);
	   chkImagenFacturas.addItemListener(new ItemListener() {
		
		@Override
		public void itemStateChanged(ItemEvent arg0) {
			if (chkImagenFacturas.isSelected()) {
			panelFirmaFacturas.setVisible(true);
			txtEspacioFacturas.setVisible(false);
			lblEspacioFacturas.setVisible(false);
			}else{
				panelFirmaFacturas.setVisible(false);
			txtEspacioFacturas.setVisible(true);
			lblEspacioFacturas.setVisible(true);
				
			}
			
		}
	});
	   panelFac.add(chkImagenFacturas);
	   
	   clrRadioButton chkEspacioFacturas = new clrRadioButton("Espacio para encabezado preimpreso");
	   chkEspacioFacturas.setBounds(243, 17, 309, 23);
	   panelFac.add(chkEspacioFacturas);
	   
	   BG.add(chkEspacioFacturas);
	   BG.add(chkImagenFacturas);
	   
	    panelFirmaFacturas = new clrPanelBordes(false);
	   panelFirmaFacturas.setBounds(10, 47, 863, 258);
	   panelFac.add(panelFirmaFacturas);
	   panelFirmaFacturas.setLayout(null);
	   panelFirmaFacturas.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) Colores.clrPrincipal), "Arrastra aqu\u00ED el encabezado", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
	   
	   
	   lblImagenFirmaFacturas = new JLabel("");
	   lblImagenFirmaFacturas.setBounds(10, 23, 843, 224);
	   panelFirmaFacturas.add(lblImagenFirmaFacturas);
	   new arrastraYsuelta( System.out, panelFirmaFacturas,dragBorder, new arrastraYsuelta.Listener()
     {   public void filesDropped( java.io.File[] files )
         {  
               try
                 {
             	logoFacturas = resize(files[0].getCanonicalPath());
             	lblImagenFirmaFacturas.setIcon(new ImageIcon(resize(files[0].getCanonicalPath(),lblImagenFirmaFacturas.getWidth())));
             	System.out.println("path path : >>>>>>>>>>>>>>>>>>>>>"+ files[0].getCanonicalPath() + "\n" );
                 }   // end try
                 catch( java.io.IOException e ) {
                 	lblincorrectos.setText("Error en la imagen seleccionada esta debe estar en formato png con fondo transparente");
                 	
                 }
               // end for: through each dropped file
         }   // end filesDropped
     });
	   
	   cbImpresoraFacturas = new clrComboBox(conexionCombos.getInstance().listaImpresoras(),1);
	   cbImpresoraFacturas.setSelectedIndex(1);
	   cbImpresoraFacturas.setBounds(206, 316, 309, 20);
	   panelFac.add(cbImpresoraFacturas);
	   
	   txtPieFacturas = new clrtextpane(500, false, "Nota para la parte final del documento", 0);
	   txtPieFacturas .setBounds(12, 347, 863, 122);
	   panelFac.add(txtPieFacturas );

 	//  llenando Facturas
	   
	   if (Colores.impimirEncabezadoFacturas&&Colores.enabezadoFacturas!=null) {
		chkImagenFacturas.setSelected(true);
    	lblImagenFirmaFacturas.setIcon(new ImageIcon(resize(Colores.enabezadoFacturas,lblImagenFirmaFacturas.getWidth())));
		
	}else{
		chkEspacioFacturas.setSelected(true);

	}
			txtEspacioFacturas.setText(Math.round(Colores.espacioEncabezadosFacturas*0.353)+"");

	   cbPapelFacturas.setSelectedItem(Colores.tamHojaFacturas);
	   cbImpresoraFacturas.setSelectedItem(Colores.impresoraFacturas);
	   if (!Colores.notaFacturas.equals("")) {
		   txtPieFacturas.setText(Colores.notaFacturas);
	}
	   
	
}

public void construirRecepciones(){
	   cbPapelRecepciones = new clrComboBox(conexionCombos.getInstance().listaTamPapel(),1);
	   cbPapelRecepciones.setBounds(10, 316, 186, 20);
	   panelRec.add(cbPapelRecepciones);
	   
	   ImageIcon imagenRecepciones = new ImageIcon(OpTamanosPapel.class.getResource("/Recursos/encabezado.png"));
	    

	    
	    txtEspacioRecepciones = new ClrCuadroDeTexto(4,true,"Espacio para este encabezado en mil\u00EDmetros",1);
	    txtEspacioRecepciones.setBounds(269, 130, 339, 25);
	    txtEspacioRecepciones.setVisible(false);
	    panelRec.add(txtEspacioRecepciones);
	   
	    lblEspacioRecepciones = new JLabel("");
	   lblEspacioRecepciones.setBounds(10, 78, 865, 138);
	   panelRec.add(lblEspacioRecepciones);
	   lblEspacioRecepciones.setVisible(false);
	   lblEspacioRecepciones.setIcon(imagenRecepciones);
	   lblEspacioRecepciones.setBackground(Color.RED);
	   
	   ButtonGroup BG=new ButtonGroup();
	   
	    chkImagenRecepciones = new clrRadioButton("Imagen de encabezado");
	   chkImagenRecepciones.setBounds(10, 17, 216, 23);
	   chkImagenRecepciones.setSelected(true);
	   chkImagenRecepciones.addItemListener(new ItemListener() {
		
		@Override
		public void itemStateChanged(ItemEvent arg0) {
			if (chkImagenRecepciones.isSelected()) {
			panelFirmaRecepciones.setVisible(true);
			txtEspacioRecepciones.setVisible(false);
			lblEspacioRecepciones.setVisible(false);
			}else{
				panelFirmaRecepciones.setVisible(false);
			txtEspacioRecepciones.setVisible(true);
			lblEspacioRecepciones.setVisible(true);
				
			}
			
		}
	});
	   panelRec.add(chkImagenRecepciones);
	   
	   clrRadioButton chkEspacioRecepciones = new clrRadioButton("Espacio para encabezado preimpreso");
	   chkEspacioRecepciones.setBounds(243, 17, 291, 23);
	   panelRec.add(chkEspacioRecepciones);
	   
	   BG.add(chkEspacioRecepciones);
	   BG.add(chkImagenRecepciones);
	   
	    panelFirmaRecepciones = new clrPanelBordes(false);
	   panelFirmaRecepciones.setBounds(10, 47, 863, 258);
	   panelRec.add(panelFirmaRecepciones);
	   panelFirmaRecepciones.setLayout(null);
	   panelFirmaRecepciones.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) Colores.clrPrincipal), "Arrastra aqu\u00ED el encabezado", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
	   
	   
	   lblImagenFirmaRecepciones = new JLabel("");
	   lblImagenFirmaRecepciones.setBounds(10, 23, 843, 224);
	   panelFirmaRecepciones.add(lblImagenFirmaRecepciones);
	   new arrastraYsuelta( System.out, panelFirmaRecepciones,dragBorder, new arrastraYsuelta.Listener()
  {   public void filesDropped( java.io.File[] files )
      {  
            try
              {
          	logoRecepciones = resize(files[0].getCanonicalPath());
          	lblImagenFirmaRecepciones.setIcon(new ImageIcon(resize(files[0].getCanonicalPath(),lblImagenFirmaRecepciones.getWidth())));
          	System.out.println("path path : >>>>>>>>>>>>>>>>>>>>>"+ files[0].getCanonicalPath() + "\n" );
              }   // end try
              catch( java.io.IOException e ) {
              	lblincorrectos.setText("Error en la imagen seleccionada esta debe estar en formato png con fondo transparente");
              	
              }
            // end for: through each dropped file
      }   // end filesDropped
  });
	   
	   cbImpresoraRecepciones = new clrComboBox(conexionCombos.getInstance().listaImpresoras(),1);
	   cbImpresoraRecepciones.setSelectedIndex(1);
	   cbImpresoraRecepciones.setBounds(206, 316, 309, 20);
	   panelRec.add(cbImpresoraRecepciones);
	   
	   txtPieRecepciones = new clrtextpane(500, false, "Nota para la parte final del documento", 0);
	   txtPieRecepciones.setBounds(12, 347, 863, 122);
	   panelRec.add(txtPieRecepciones);

 	//  llenando Recepciones
	   
	   if (Colores.impimirEncabezadoRecepciones&&Colores.enabezadoRecepciones!=null) {
		chkImagenRecepciones.setSelected(true);
    	lblImagenFirmaRecepciones.setIcon(new ImageIcon(resize(Colores.enabezadoRecepciones,lblImagenFirmaRecepciones.getWidth())));
		
	}else{
		chkEspacioRecepciones.setSelected(true);

	}
			txtEspacioRecepciones.setText(Math.round(Colores.espacioEncabezadosRecepciones*0.353)+"");

	   cbPapelRecepciones.setSelectedItem(Colores.tamHojaRecepciones);
	   cbImpresoraRecepciones.setSelectedItem(Colores.impresoraRecepciones);
	   if (!Colores.notaRecepciones.equals("")) {
		   txtPieRecepciones.setText(Colores.notaRecepciones);
	}
	   
	
}

public void construirCotizaciones(){
	   cbPapelCotizaciones = new clrComboBox(conexionCombos.getInstance().listaTamPapel(),1);
	   cbPapelCotizaciones.setBounds(10, 316, 186, 20);
	   panelCot.add(cbPapelCotizaciones);
	   
	   ImageIcon imagenCotizaciones = new ImageIcon(OpTamanosPapel.class.getResource("/Recursos/encabezado.png"));
	    

	    
	    txtEspacioCotizaciones = new ClrCuadroDeTexto(4,true,"Espacio para este encabezado en mil\u00EDmetros",1);
	    txtEspacioCotizaciones.setBounds(269, 130, 339, 25);
	    txtEspacioCotizaciones.setVisible(false);
	    panelCot.add(txtEspacioCotizaciones);
	   
	    lblEspacioCotizaciones = new JLabel("");
	   lblEspacioCotizaciones.setBounds(10, 78, 865, 138);
	   panelCot.add(lblEspacioCotizaciones);
	   lblEspacioCotizaciones.setVisible(false);
	   lblEspacioCotizaciones.setIcon(imagenCotizaciones);
	   lblEspacioCotizaciones.setBackground(Color.RED);
	   
	   ButtonGroup BG=new ButtonGroup();
	   
	    chkImagenCotizaciones = new clrRadioButton("Imagen de encabezado");
	   chkImagenCotizaciones.setBounds(10, 17, 216, 23);
	   chkImagenCotizaciones.setSelected(true);
	   chkImagenCotizaciones.addItemListener(new ItemListener() {
		
		@Override
		public void itemStateChanged(ItemEvent arg0) {
			if (chkImagenCotizaciones.isSelected()) {
			panelFirmaCotizaciones.setVisible(true);
			txtEspacioCotizaciones.setVisible(false);
			lblEspacioCotizaciones.setVisible(false);
			}else{
				panelFirmaCotizaciones.setVisible(false);
			txtEspacioCotizaciones.setVisible(true);
			lblEspacioCotizaciones.setVisible(true);
				
			}
			
		}
	});
	   panelCot.add(chkImagenCotizaciones);
	   
	   clrRadioButton chkEspacioCotizaciones = new clrRadioButton("Espacio para encabezado preimpreso");
	   chkEspacioCotizaciones.setBounds(243, 17, 350, 23);
	   panelCot.add(chkEspacioCotizaciones);
	   
	   BG.add(chkEspacioCotizaciones);
	   BG.add(chkImagenCotizaciones);
	   
	    panelFirmaCotizaciones = new clrPanelBordes(false);
	   panelFirmaCotizaciones.setBounds(10, 47, 863, 258);
	   panelCot.add(panelFirmaCotizaciones);
	   panelFirmaCotizaciones.setLayout(null);
	   panelFirmaCotizaciones.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) Colores.clrPrincipal), "Arrastra aqu\u00ED el encabezado", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
	   
	   
	   lblImagenFirmaCotizaciones = new JLabel("");
	   lblImagenFirmaCotizaciones.setBounds(10, 23, 843, 224);
	   panelFirmaCotizaciones.add(lblImagenFirmaCotizaciones);
	   new arrastraYsuelta( System.out, panelFirmaCotizaciones,dragBorder, new arrastraYsuelta.Listener()
{   public void filesDropped( java.io.File[] files )
   {  
         try
           {
       	logoCotizaciones = resize(files[0].getCanonicalPath());
       	lblImagenFirmaCotizaciones.setIcon(new ImageIcon(resize(files[0].getCanonicalPath(),lblImagenFirmaCotizaciones.getWidth())));
       	System.out.println("path path : >>>>>>>>>>>>>>>>>>>>>"+ files[0].getCanonicalPath() + "\n" );
           }   // end try
           catch( java.io.IOException e ) {
           	lblincorrectos.setText("Error en la imagen seleccionada esta debe estar en formato png con fondo transparente");
           	
           }
         // end for: through each dropped file
   }   // end filesDropped
});
	   
	   cbImpresoraCotizaciones = new clrComboBox(conexionCombos.getInstance().listaImpresoras(),1);
	   cbImpresoraCotizaciones.setSelectedIndex(1);
	   cbImpresoraCotizaciones.setBounds(206, 316, 309, 20);
	   panelCot.add(cbImpresoraCotizaciones);
	   
	   txtPieCotizaciones = new clrtextpane(500, false, "Nota para la parte final del documento", 0);
	   txtPieCotizaciones.setBounds(12, 347, 863, 122);
	   panelCot.add(txtPieCotizaciones);

 	//  llenando Cotizaciones
	   
	   if (Colores.impimirEncabezadoCotizaciones&&Colores.enabezadoCotizaciones!=null) {
		chkImagenCotizaciones.setSelected(true);
    	lblImagenFirmaCotizaciones.setIcon(new ImageIcon(resize(Colores.enabezadoCotizaciones,lblImagenFirmaCotizaciones.getWidth())));
		
	}else{
		chkEspacioCotizaciones.setSelected(true);

	}
			txtEspacioCotizaciones.setText(Math.round(Colores.espacioEncabezadosCotizaciones*0.353)+"");

	   cbPapelCotizaciones.setSelectedItem(Colores.tamHojaCotizaciones);
	   cbImpresoraCotizaciones.setSelectedItem(Colores.impresoraCotizaciones);
	   if (!Colores.notaCotizaciones.equals("")) {
		   txtPieCotizaciones.setText(Colores.notaCotizaciones);
	}
	   
	
}

public void construirNotas(){
	   cbPapelNotas = new clrComboBox(conexionCombos.getInstance().listaTamPapel(),1);
	   cbPapelNotas.setBounds(10, 316, 186, 20);
	   panelNot.add(cbPapelNotas);
	   
	   ImageIcon imagenNotas = new ImageIcon(OpTamanosPapel.class.getResource("/Recursos/encabezado.png"));
	    

	    
	    txtEspacioNotas = new ClrCuadroDeTexto(4,true,"Espacio para este encabezado en mil\u00EDmetros",1);
	    txtEspacioNotas.setBounds(269, 130, 339, 25);
	    txtEspacioNotas.setVisible(false);
	    panelNot.add(txtEspacioNotas);
	   
	    lblEspacioNotas = new JLabel("");
	   lblEspacioNotas.setBounds(10, 78, 865, 138);
	   panelNot.add(lblEspacioNotas);
	   lblEspacioNotas.setVisible(false);
	   lblEspacioNotas.setIcon(imagenNotas);
	   lblEspacioNotas.setBackground(Color.RED);
	   
	   ButtonGroup BG=new ButtonGroup();
	   
	    chkImagenNotas = new clrRadioButton("Imagen de encabezado");
	   chkImagenNotas.setBounds(10, 17, 216, 23);
	   chkImagenNotas.setSelected(true);
	   chkImagenNotas.addItemListener(new ItemListener() {
		
		@Override
		public void itemStateChanged(ItemEvent arg0) {
			if (chkImagenNotas.isSelected()) {
			panelFirmaNotas.setVisible(true);
			txtEspacioNotas.setVisible(false);
			lblEspacioNotas.setVisible(false);
			}else{
				panelFirmaNotas.setVisible(false);
			txtEspacioNotas.setVisible(true);
			lblEspacioNotas.setVisible(true);
				
			}
			
		}
	});
	   panelNot.add(chkImagenNotas);
	   
	   clrRadioButton chkEspacioNotas = new clrRadioButton("Espacio para encabezado preimpreso");
	   chkEspacioNotas.setBounds(243, 17, 309, 23);
	   panelNot.add(chkEspacioNotas);
	   
	   BG.add(chkEspacioNotas);
	   BG.add(chkImagenNotas);
	   
	    panelFirmaNotas = new clrPanelBordes(false);
	   panelFirmaNotas.setBounds(10, 47, 863, 258);
	   panelNot.add(panelFirmaNotas);
	   panelFirmaNotas.setLayout(null);
	   panelFirmaNotas.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) Colores.clrPrincipal), "Arrastra aqu\u00ED el encabezado", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
	   
	   
	   lblImagenFirmaNotas = new JLabel("");
	   lblImagenFirmaNotas.setBounds(10, 23, 843, 224);
	   panelFirmaNotas.add(lblImagenFirmaNotas);
	   new arrastraYsuelta( System.out, panelFirmaNotas,dragBorder, new arrastraYsuelta.Listener()
{   public void filesDropped( java.io.File[] files )
{  
      try 
        {
    	logoNotas = resize(files[0].getCanonicalPath());
    	lblImagenFirmaNotas.setIcon(new ImageIcon(resize(files[0].getCanonicalPath(),lblImagenFirmaNotas.getWidth())));
    	System.out.println("path path : >>>>>>>>>>>>>>>>>>>>>"+ files[0].getCanonicalPath() + "\n" );
        }   // end try
        catch( java.io.IOException e ) {
        	lblincorrectos.setText("Error en la imagen seleccionada esta debe estar en formato png con fondo transparente");
        	
        }
      // end for: through each dropped file
}   // end filesDropped
});
	   
	   cbImpresoraNotas = new clrComboBox(conexionCombos.getInstance().listaImpresoras(),1);
	   cbImpresoraNotas.setSelectedIndex(1);
	   cbImpresoraNotas.setBounds(206, 316, 309, 20);
	   panelNot.add(cbImpresoraNotas);
	   
	   txtPieNotas = new clrtextpane(500, false, "Nota para la parte final del documento", 0);
	   txtPieNotas.setBounds(12, 347, 863, 122);
	   panelNot.add(txtPieNotas);

 	//  llenando Notas
	   
	   if (Colores.impimirEncabezadoNotas&&Colores.enabezadoNotas!=null) {
		chkImagenNotas.setSelected(true);
    	lblImagenFirmaNotas.setIcon(new ImageIcon(resize(Colores.enabezadoNotas,lblImagenFirmaNotas.getWidth())));
		
	}else{
		chkEspacioNotas.setSelected(true);

	}
			txtEspacioNotas.setText(Math.round(Colores.espacioEncabezadosNotas*0.353)+"");

	   cbPapelNotas.setSelectedItem(Colores.tamHojaNotas);
	   cbImpresoraNotas.setSelectedItem(Colores.impresoraNotas);
	   if (!Colores.notaNotas.equals("")) {
		   txtPieNotas.setText(Colores.notaNotas);
	}
	   
	
}

public void construirStickers(){
	 
	   ImageIcon imagenStickers = new ImageIcon(OpTamanosPapel.class.getResource("/Recursos/sticker.png"));
	   
	   cbImpresoraStickers = new clrComboBox(conexionCombos.getInstance().listaImpresoras(),1);
	   cbImpresoraStickers.setSelectedIndex(1);
	   cbImpresoraStickers.setBounds(21, 449, 309, 20);
	   panelSti.add(cbImpresoraStickers);
	   
	   	
	       txtMarSuperior = new ClrCuadroDeTexto(4,true,"0",1);
	       txtMarSuperior.setBounds(407, 68, 50, 25);
	       panelSti.add(txtMarSuperior);
	        
	        txtHeightSticker = new ClrCuadroDeTexto(4, true, "0", 1);
	        txtHeightSticker.setBounds(537, 192, 50, 25);
	        panelSti.add(txtHeightSticker);
	        
	        txtWidthSticker = new ClrCuadroDeTexto(4, true, "0", 1);
	        txtWidthSticker.setBounds(422, 256, 50, 25);
	        panelSti.add(txtWidthSticker);
	       
	        lblEspacioStickers = new JLabel("");
	        lblEspacioStickers.setBounds(165, 24, 552, 377);
	        panelSti.add(lblEspacioStickers);
	        lblEspacioStickers.setIcon(imagenStickers);
	        lblEspacioStickers.setBackground(Color.RED);
	        
	        txtHeightPapel = new ClrCuadroDeTexto(4, true, "0", 1);
	        txtHeightPapel.setBounds(712, 192, 50, 25);
	        panelSti.add(txtHeightPapel);
	        
	        txtMarIzquierda = new ClrCuadroDeTexto(4, true, "0", 1);
	        txtMarIzquierda.setBounds(105, 177, 50, 25);
	        panelSti.add(txtMarIzquierda);
	        
	        txtWidthPapel = new ClrCuadroDeTexto(4, true, "0", 1);
	        txtWidthPapel.setBounds(407, 382, 50, 25);
	        panelSti.add(txtWidthPapel);
	        
	        clrLabel clrLabel_ = new clrLabel("Indique las medidas del papel en milimetros", 1);
	        clrLabel_.setHorizontalAlignment(SwingConstants.CENTER);
	        clrLabel_.setForeground(new Color(0, 0, 0, 130));
	        clrLabel_.setBounds(50, 418, 745, 14);
	        panelSti.add(clrLabel_);
	        
	        ButtonGroup BGCups=new ButtonGroup();
	        
	     clrRadioButton = new clrRadioButton("Imprimir CUPS");
	        clrRadioButton.setSelected(true);
	        clrRadioButton.setBounds(346, 446, 145, 23);
	        panelSti.add(clrRadioButton);
	        
	        clrRadioButton_1 = new clrRadioButton("Imprimir c\u00F3digos internos");
	        clrRadioButton_1.setBounds(493, 446, 216, 23);
	        panelSti.add(clrRadioButton_1);
	        
	        BGCups.add(clrRadioButton);
	        BGCups.add(clrRadioButton_1);
	        
	        //llenando stickers
	        
	        cbImpresoraStickers.setSelectedItem(Colores.impresoraStickers);
	        txtWidthPapel.setText(Colores.medidasStickers[0]);
	        txtHeightPapel.setText(Colores.medidasStickers[1]);
	        txtWidthSticker.setText(Colores.medidasStickers[2]);
	        txtHeightSticker.setText(Colores.medidasStickers[3]);
	        txtMarSuperior.setText(Colores.medidasStickers[4]);
	        txtMarIzquierda.setText(Colores.medidasStickers[5]);
	        
	        if (Colores.stickersCups) {
		        clrRadioButton.setSelected(true);
		        clrRadioButton_1.setSelected(false);

			}else{
				clrRadioButton.setSelected(false);
				clrRadioButton_1.setSelected(true);
			}
	        
}

public void construirReporteWeb(){
	  
	   
	    

	    
	    txtFirma1Web = new ClrCuadroDeTexto(100,false,"Primer dato del firmante",1);
	    txtFirma1Web.setHorizontalAlignment(SwingConstants.CENTER);
	    txtFirma1Web.setBounds(459, 367, 339, 25);
	    panelWeb.add(txtFirma1Web);
	   
	
	   ButtonGroup BG=new ButtonGroup();
	   
	    chkImpReportados = new clrRadioButton("Imprimir reportados");
	   chkImpReportados.setBounds(10, 17, 183, 23);
	   chkImpReportados.setSelected(true);
	
	   panelWeb.add(chkImpReportados);
	   
	    chkImpNoRep = new clrRadioButton("Imprimir no reportados");
	   chkImpNoRep.setBounds(195, 17, 204, 23);
	   panelWeb.add(chkImpNoRep);
	   
	   BG.add(chkImpNoRep);
	   BG.add(chkImpReportados);
	   
	    panelFirmaWeb = new clrPanelBordes(false);
	   panelFirmaWeb.setBounds(10, 307, 354, 162);
	   panelWeb.add(panelFirmaWeb);
	   panelFirmaWeb.setLayout(null);
	   panelFirmaWeb.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) Colores.clrPrincipal), "Arrastra aqu\u00ED la firma", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
	   
	   
	   lblImagenFirmaWeb = new JLabel("");
	   lblImagenFirmaWeb.setBounds(10, 23, 334, 128);
	   panelFirmaWeb.add(lblImagenFirmaWeb);
	   new arrastraYsuelta( System.out, panelFirmaWeb,dragBorder, new arrastraYsuelta.Listener()
{   public void filesDropped( java.io.File[] files )
{  
   try 
     {
 	firmaWeb = resizeFirma(files[0].getCanonicalPath());
 	lblImagenFirmaWeb.setIcon(new ImageIcon(resize(files[0].getCanonicalPath(),lblImagenFirmaWeb.getWidth())));
 	System.out.println("path path : >>>>>>>>>>>>>>>>>>>>>"+ files[0].getCanonicalPath() + "\n" );
     }   // end try
     catch( java.io.IOException e ) {
     	lblincorrectos.setText("Error en la imagen seleccionada esta debe estar en formato png con fondo transparente");
     	
     }
   // end for: through each dropped file
}   // end filesDropped
});
	   
	   
	   
	   panelEncaWeb = new clrPanelBordes(false);
	   panelEncaWeb.setBounds(10, 47, 863, 258);
	   panelWeb.add(panelEncaWeb);
	   panelEncaWeb.setLayout(null);
	   panelEncaWeb.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) Colores.clrPrincipal), "Arrastra aqu\u00ED el encabezado", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
	   
	   
	   
	   lblImagenEncaWeb = new JLabel("");
	   lblImagenEncaWeb.setBounds(10, 23, 843, 224);
	   panelEncaWeb.add(lblImagenEncaWeb);
	   new arrastraYsuelta( System.out, panelEncaWeb,dragBorder, new arrastraYsuelta.Listener()
{   public void filesDropped( java.io.File[] files )
{  
   try 
     {
 	encabezadoWeb = resizeEnca(files[0].getCanonicalPath());
 	lblImagenEncaWeb.setIcon(new ImageIcon(resize(files[0].getCanonicalPath(),lblImagenEncaWeb.getWidth())));
 	System.out.println("path path : >>>>>>>>>>>>>>>>>>>>>"+ files[0].getCanonicalPath() + "\n" );
     }   // end try
     catch( java.io.IOException e ) {
     	lblincorrectos.setText("Error en la imagen seleccionada esta debe estar en formato png con fondo transparente");
     	
     }
   // end for: through each dropped file
}   // end filesDropped
});
	   
	   separator = new JSeparator();
	   separator.setBounds(447, 353, 354, 2);
	   panelWeb.add(separator);
	   
	    chkImpTodo = new clrRadioButton("Imprimir todos");
	   chkImpTodo.setSelected(true);
	   chkImpTodo.setBounds(401, 17, 183, 23);
	   panelWeb.add(chkImpTodo);
	   BG.add(chkImpTodo);
	   
	   txtFirma2Web = new ClrCuadroDeTexto(100, false, "Segundo dato del firmante", 1);
	   txtFirma2Web.setHorizontalAlignment(SwingConstants.CENTER);
	   txtFirma2Web.setBounds(459, 403, 339, 25);
	   panelWeb.add(txtFirma2Web);
	//  llenando Web
	
	   encaFirma enca=conexionBusqueda.getInstance().encaFirma();
	   
	   if (enca.getEncaImg()!=null) {
	    	lblImagenEncaWeb.setIcon(new ImageIcon(resize(enca.getEncaImg(),lblImagenEncaWeb.getWidth())));

	}
	   
	   if (enca.getFirmaImg()!=null) {
		lblImagenFirmaWeb.setIcon(new ImageIcon(resize(enca.getFirmaImg(), lblImagenFirmaWeb.getWidth())));
		if(!enca.getQuienFirma1().equals("")){txtFirma1Web.setText(enca.getQuienFirma1());};
		if(!enca.getQuienFirma2().equals("")){txtFirma2Web.setText(enca.getQuienFirma2());};
	}
	   
	   switch (enca.getQueReporta()) {
	case 0:
		chkImpTodo.setSelected(true);
		break;
		
	case 1:
		chkImpNoRep.setSelected(true);
		break;
		
	case 2:
				chkImpReportados.setSelected(true);
		break;
		
	default:
		break;
	}
	
}


public void guardar(){
	try {

		
		FileInputStream entrada=null;
		Properties propiedades = new Properties();
		
		try {

			entrada = new FileInputStream("C:/6342522/conad.prop");

			propiedades.load(entrada);

		} catch (IOException ex) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR OPCIONES DE IMPRESION> : no es posible leer el archivo de configuración");
		}
		
		if (txtEspacioListados.getText().equals(txtEspacioListados.getLabel())) {
			txtEspacioListados.setText(100+"");
		}
		
		String auxiNotaListados="";if(!txtPieListados.getText().equals(txtPieListados.getLabel())){auxiNotaListados=txtPieListados.getText();};
		propiedades.setProperty("impresoraListados", cbImpresoraListados.getSelectedItem().toString());
		propiedades.setProperty("imprimirEncListados", chkImagenListados.isSelected()+"");
		propiedades.setProperty("espacioListados",( Integer.parseInt(txtEspacioListados.getText())/0.353)+"");
		propiedades.setProperty("tamHojaListados", cbPapelListados.getSelectedItem().toString());
		propiedades.setProperty("notaListados", auxiNotaListados);
		if (chkImagenListados.isSelected()&&logoListados!=null) {
			BufferedImage biListados = new BufferedImage(logoListados.getWidth(null),logoListados.getHeight(null),BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2Listados = biListados.createGraphics();
			g2Listados.drawImage(logoListados, 0, 0, null);
			g2Listados.dispose();
			try {
				Colores.enabezadoListados=null ;
				ImageIO.write(biListados, "png", new File("C:/6342522/encabezadoListados.png"));
				propiedades.setProperty("encListados", "/encabezadoListados.png");

			} catch (IOException e) {
				lblincorrectos.setText("Error al crear imagen para encabezado de listados");
				e.printStackTrace();
			}
		}
		
		
		
		String auxiNotaFacturas="";if(!txtPieFacturas.getText().equals(txtPieFacturas.getLabel())){auxiNotaFacturas=txtPieFacturas.getText();};
		propiedades.setProperty("impresoraFacturas", cbImpresoraFacturas.getSelectedItem().toString());
		propiedades.setProperty("imprimirEncFacturas", chkImagenFacturas.isSelected()+"");
		propiedades.setProperty("espacioFacturas",( Integer.parseInt(txtEspacioFacturas.getText())/0.353)+"");
		propiedades.setProperty("tamHojaFacturas", cbPapelFacturas.getSelectedItem().toString());
		propiedades.setProperty("notaFacturas", auxiNotaFacturas);
		if (chkImagenFacturas.isSelected()&&logoFacturas!=null) {
			BufferedImage biFacturas = new BufferedImage(logoFacturas.getWidth(null),logoFacturas.getHeight(null),BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2Facturas = biFacturas.createGraphics();
			g2Facturas.drawImage(logoFacturas, 0, 0, null);
			g2Facturas.dispose();
			try {
				ImageIO.write(biFacturas, "png", new File("C:/6342522/encabezadoFacturas.png"));
				propiedades.setProperty("encFacturas", "/encabezadoFacturas.png");

			} catch (IOException e) {
				lblincorrectos.setText("Error al crear imagen para encabezado de Facturas");
				e.printStackTrace();
			}
		}
		
		
		String auxiNotaRecepciones="";if(!txtPieRecepciones.getText().equals(txtPieRecepciones.getLabel())){auxiNotaRecepciones=txtPieRecepciones.getText();};
		propiedades.setProperty("impresoraRecepciones", cbImpresoraRecepciones.getSelectedItem().toString());
		propiedades.setProperty("imprimirEncRecepciones", chkImagenRecepciones.isSelected()+"");
		propiedades.setProperty("espacioRecepciones",( Integer.parseInt(txtEspacioRecepciones.getText())/0.353)+"");
		propiedades.setProperty("tamHojaRecepciones", cbPapelRecepciones.getSelectedItem().toString());
		propiedades.setProperty("notaRecepciones", auxiNotaRecepciones);
	if (chkImagenRecepciones.isSelected()&&logoRecepciones!=null) {
		BufferedImage biRecepciones = new BufferedImage(logoRecepciones.getWidth(null),logoRecepciones.getHeight(null),BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2Recepciones = biRecepciones.createGraphics();
		g2Recepciones.drawImage(logoRecepciones, 0, 0, null);
		g2Recepciones.dispose();
		try {
			ImageIO.write(biRecepciones, "png", new File("C:/6342522/encabezadoRecepciones.png"));
			propiedades.setProperty("encRecepciones", "/encabezadoRecepciones.png");

		} catch (IOException e) {
			lblincorrectos.setText("Error al crear imagen para encabezado de Recepciones");
			e.printStackTrace();
		}
	}
		
		
		String auxiNotaCotizaciones="";if(!txtPieCotizaciones.getText().equals(txtPieCotizaciones.getLabel())){auxiNotaCotizaciones=txtPieCotizaciones.getText();};
		propiedades.setProperty("impresoraCotizaciones", cbImpresoraCotizaciones.getSelectedItem().toString());
		propiedades.setProperty("imprimirEncCotizaciones", chkImagenCotizaciones.isSelected()+"");
		propiedades.setProperty("espacioCotizaciones",( Integer.parseInt(txtEspacioCotizaciones.getText())/0.353)+"");
		propiedades.setProperty("tamHojaCotizaciones", cbPapelCotizaciones.getSelectedItem().toString());
		propiedades.setProperty("notaCotizaciones", auxiNotaCotizaciones);
		if (chkImagenCotizaciones.isSelected()&&logoCotizaciones!=null) {
			BufferedImage biCotizaciones = new BufferedImage(logoCotizaciones.getWidth(null),logoCotizaciones.getHeight(null),BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2Cotizaciones = biCotizaciones.createGraphics();
			g2Cotizaciones.drawImage(logoCotizaciones, 0, 0, null);
			g2Cotizaciones.dispose();
			try {
				ImageIO.write(biCotizaciones, "png", new File("C:/6342522/encabezadoCotizaciones.png"));
				propiedades.setProperty("encCotizaciones", "/encabezadoCotizaciones.png");

			} catch (IOException e) {
				lblincorrectos.setText("Error al crear imagen para encabezado de Cotizaciones");
				e.printStackTrace();
			}
			
		}
		
		String auxiNotaNotas="";if(!txtPieNotas.getText().equals(txtPieNotas.getLabel())){auxiNotaNotas=txtPieNotas.getText();};
		propiedades.setProperty("impresoraNotas", cbImpresoraNotas.getSelectedItem().toString());
		propiedades.setProperty("imprimirEncNotas", chkImagenNotas.isSelected()+"");
		propiedades.setProperty("espacioNotas",( Integer.parseInt(txtEspacioNotas.getText())/0.353)+"");
		propiedades.setProperty("tamHojaNotas", cbPapelNotas.getSelectedItem().toString());
		propiedades.setProperty("notaNotas", auxiNotaNotas);
		if (chkImagenNotas.isSelected()&&logoNotas!=null) {
			BufferedImage biNotas = new BufferedImage(logoNotas.getWidth(null),logoNotas.getHeight(null),BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2Notas = biNotas.createGraphics();
			g2Notas.drawImage(logoNotas, 0, 0, null);
			g2Notas.dispose();
			try {
				ImageIO.write(biNotas, "png", new File("C:/6342522/encabezadoNotas.png"));
				propiedades.setProperty("encNotas", "/encabezadoNotas.png");

			} catch (IOException e) {
				lblincorrectos.setText("Error al crear imagen para encabezado de Notas");
				e.printStackTrace();
			}
			
		}
		
		
		propiedades.setProperty("medidaSticker", (txtWidthPapel.getText()+","+txtHeightPapel.getText()+","+txtWidthSticker.getText()+","+txtHeightSticker.getText()+","+txtMarSuperior.getText()+","+txtMarIzquierda.getText()));
		propiedades.setProperty("impresoraStickers", cbImpresoraStickers.getSelectedItem().toString());
		propiedades.setProperty("stickerCups", clrRadioButton.isSelected()+"");
	      
		encaFirma encaFirma=new encaFirma();
		
		if(firmaIS!=null){
			encaFirma.setFirmaImg(firmaWeb);
			encaFirma.setFirmaIS(firmaIS);
		}
		
		if (encaIS!=null) {
			encaFirma.setEncaImg(encabezadoWeb);
			encaFirma.setEncaIS(encaIS);
	
		
		}
			if (txtFirma1Web.getText().equals(txtFirma1Web.getLabel())) {
			encaFirma.setQuienFirma1("");
		}else{
			encaFirma.setQuienFirma1(txtFirma1Web.getText());
		}
		
		if (txtFirma2Web.getText().equals(txtFirma2Web.getLabel())) {
			encaFirma.setQuienFirma2("");
		}else{
			encaFirma.setQuienFirma2(txtFirma2Web.getText());
		}
		int queReporta=0;
		if(chkImpNoRep.isSelected()){queReporta=1;};
		if(chkImpReportados.isSelected()){queReporta=2;};
		
		encaFirma.setQueReporta(queReporta);
		
		
		conexion.getInstance().configEncaWeb(esta, encaFirma);
					
		
		
		
	   try {
		   FileOutputStream fos=new FileOutputStream("C:/6342522/conad.prop");

	                    propiedades.store(fos,null);
	} catch (Exception e) {
		Principal.getInstancePrincipal().registrarErrorDB("< ERROR OPCIONES DE IMPRESIÓN> : no es posible escribir  el archivo de configuración");

	}
		
		
Principal.getInstancePrincipal().registrarAccion("Cambio de opciones de impresión");
dispose();
try {
	Colores.cargarOpcionesImpresion(Colores.getPropiedades());

} catch (Exception e) {
	Principal.getInstancePrincipal().registrarErrorDB("< ERROR OPCIONES DE IMPRESIÓN> : cargar opciones de impresión en la interface");
}


	} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR OPCIONES DE IMPRESÓN > "+e);
	}
	
}
}
