package interfaces_Modificadas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import Interfaces.Principal;
import auxiliares.MiLaboratorio;
import auxiliares.Recepcion;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.FontFactory;

import conexion.conexion;
import conexion.conexionBusqueda;
import conexion.conexionComprobar;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.ZapfDingbatsList;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Colores {
	
	
	
	public static Color clrPrincipal=new Color(0,131,203);
	public static Color clrTextoInactivo=new Color(0,0,0,130);
	public static Color clrAlertaCamarada=new Color(0,150,136);
	public static boolean clrIconosNegros=true;
	public  static Color clrTextoPrincipal= Color.white;
	public static Color clrTextoSecundario=Color.black;
	public static String sede="Sin nombre de sede";
	public static String estacion="Sin nombre de estación";
	public static Image LogoMEGALAB=prepararLogoMEGALAB();
	public static Image LogoMEGALABMem=prepararLogoMEGALABMem();
	
	public static String host ="";
	public static String user ="";
	public static String pass ="";
	public static String dtbs ="";
	
	public static String host2 ="";
	public static String user2 ="";
	public static String pass2 ="";
	public static String dtbs2 ="";


	public static boolean bordesTablas=true;
	public static BaseColor colorBordesTablas;

	public static com.itextpdf.text.Font fuenteTitulos = FontFactory.getFont(FontFactory.HELVETICA, 14, com.itextpdf.text.Font.NORMAL,BaseColor.BLACK);
	public static com.itextpdf.text.Font fuenteContenidos = FontFactory.getFont(FontFactory.HELVETICA,9, com.itextpdf.text.Font.NORMAL,BaseColor.BLACK);
	public static com.itextpdf.text.Font fuentePeque = FontFactory.getFont(FontFactory.HELVETICA, 6, com.itextpdf.text.Font.NORMAL,BaseColor.BLACK);
	
	public static com.itextpdf.text.Font fuenteTitulosBold = FontFactory.getFont(FontFactory.HELVETICA, 11, com.itextpdf.text.Font.BOLD,BaseColor.BLACK);
	public static com.itextpdf.text.Font fuenteContenidosBold = FontFactory.getFont(FontFactory.HELVETICA,9, com.itextpdf.text.Font.BOLD,BaseColor.BLACK);
	public static com.itextpdf.text.Font fuentePequeBold = FontFactory.getFont(FontFactory.HELVETICA, 6, com.itextpdf.text.Font.BOLD,BaseColor.BLACK);
	
	public static String impresoraListados;
	public static boolean impimirEncabezadoListados;
	public static int espacioEncabezadosListados;
	public static Image enabezadoListados;
	public static String tamHojaListados;
	public static String tamListados;
	public static String notaListados;
	
	public static String impresoraFacturas;
	public static boolean impimirEncabezadoFacturas;
	public static int espacioEncabezadosFacturas;
	public static Image enabezadoFacturas;
	public static String tamHojaFacturas;
	public static String tamFacturas;
	public static String notaFacturas;
	
	public static String impresoraRecepciones;
	public static boolean impimirEncabezadoRecepciones;
	public static int espacioEncabezadosRecepciones;
	public static Image enabezadoRecepciones;
	public static String tamHojaRecepciones;
	public static String tamRecepciones;
	public static String notaRecepciones;
	
	public static String impresoraCotizaciones;
	public static boolean impimirEncabezadoCotizaciones;
	public static int espacioEncabezadosCotizaciones;
	public static Image enabezadoCotizaciones;
	public static String tamHojaCotizaciones;
	public static String tamCotizaciones;
	public static String notaCotizaciones;
	
	public static String impresoraNotas;
	public static boolean impimirEncabezadoNotas;
	public static int espacioEncabezadosNotas;
	public static Image enabezadoNotas;
	public static String tamHojaNotas;
	public static String tamNotas;
	public static String notaNotas;
	
	public static String impresoraStickers;
	public static String[] medidasStickers;
	public static boolean stickersCups;


	
	
	public static String puerto7600="COM1";
	
	public static Color clrSecundario=calcularColorBordeClaro();
	public static Image  logoLab=prepararLogo();

	public static Color clrFondo=Color.white;
	public static Color clrTransparente=new Color(255,255,255,0);
	public static Color clrFondoInactivo=new Color(0,0,0,30);
	public static Color clrBotones=new Color(clrPrincipal.getRed(),clrPrincipal.getGreen(),clrPrincipal.getBlue());
	public static Font fuenteNormal = new Font("Calibri", Font.PLAIN, 17);
	public static Font fuentePequena = new Font("Calibri", Font.PLAIN, 13);
	public static Font fuentegrande = new Font("Calibri", Font.PLAIN, 52);
	public static Font fuenteBtn = new Font("Calibri", Font.PLAIN, 18);
	public static Font fuenteMediana = new Font("Calibri", Font.PLAIN, 21);
	public static int escala=2;
	
	public static int tiempoBusInactivasNormal=4000;
	public static int tiempoBusInactivasRapido=100;
	
	

	
	
	public static void calcularColorTextoPrincipal(){
		

			int promedio = (clrPrincipal.getRed()+clrPrincipal.getBlue()+clrPrincipal.getGreen())/3;
			
			if (promedio < 127) {
				clrTextoPrincipal= Color.WHITE;
			} else{
				clrTextoPrincipal=Color.BLACK;
			}
			
			
		
	}
	
	public static void calcularColorTextoSecundario(){
	
		
		int promedio = (clrSecundario.getRed()+clrSecundario.getBlue()+clrSecundario.getGreen()+clrSecundario.getAlpha())/4;
		
		if (promedio < 127) {
			clrTextoSecundario= Color.WHITE;
		} else{
			clrTextoSecundario=Color.BLACK;
		}
		
		}
	
public static Color calcularColorClaroPrincipal(){
	
		return new Color(clrPrincipal.getRed(),clrPrincipal.getGreen(),clrPrincipal.getBlue(),130);

		}

public static Color calcularColorClaroSecundario(){
	
	return new Color(clrSecundario.getRed(),clrSecundario.getGreen(),clrSecundario.getBlue(),130);

	}
	
	
	public static Color calcularColorBordeClaro(){
		
		FileInputStream entrada=null;
		Properties propiedades = new Properties();
		
		try {

			entrada = new FileInputStream("C:/6342522/conad.prop");

			propiedades.load(entrada);

		} catch (IOException ex) {
			System.out.println(ex);
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR COLORES> : no es posible leer el archivo de configuración");
		}
		String principal=propiedades.getProperty("principal");
		String alertas=propiedades.getProperty("alertas");
		String secundario=propiedades.getProperty("secundario");
		String iconosNegros=propiedades.getProperty("iconosNegros");
		puerto7600=propiedades.getProperty("puerto7600");
		sede=propiedades.getProperty("sede");
		estacion=propiedades.getProperty("nombre");
		
		StringTokenizer STP=new StringTokenizer(principal, ",");
		clrPrincipal=new Color(Integer.parseInt(STP.nextToken()),Integer.parseInt(STP.nextToken()),Integer.parseInt(STP.nextToken()));

		StringTokenizer STA=new StringTokenizer(alertas, ",");
		clrAlertaCamarada=new Color(Integer.parseInt(STA.nextToken()),Integer.parseInt(STA.nextToken()),Integer.parseInt(STA.nextToken()));

		StringTokenizer STS=new StringTokenizer(secundario, ",");
		clrSecundario=new Color(Integer.parseInt(STS.nextToken()),Integer.parseInt(STS.nextToken()),Integer.parseInt(STS.nextToken()));

		clrIconosNegros=Boolean.parseBoolean(iconosNegros);

		calcularColorTextoPrincipal();
		calcularColorTextoSecundario();
		
		host = propiedades.getProperty("host");
		user = propiedades.getProperty("usuario");
		pass = propiedades.getProperty("pass");
		dtbs = propiedades.getProperty("db");
		
		host2 = propiedades.getProperty("host2");
		user2 = propiedades.getProperty("usuario2");
		pass2 = propiedades.getProperty("pass2");
		dtbs2 = propiedades.getProperty("db2");
		
		
		cargarOpcionesImpresion(propiedades);
		
		
		//falta preparar cordes tablas segun archivo
		
		if (bordesTablas) {
			colorBordesTablas=new BaseColor(0, 0, 0, 30);
		}else{
			colorBordesTablas=BaseColor.WHITE;
		}
		
		
		
		return clrSecundario;
		}

	public static  Image prepararLogo() {
        MiLaboratorio milab=null;

        	   if(conexionComprobar.getInstance1().comprobarConexion()){
        		   System.out.println("pintando logo");
       		        milab=conexionBusqueda.getInstance().miLab();

       		     if (milab.getLogoImagen()!=null) {
       		    	 return  milab.getLogoImagen();
   	        	    	        	  
   			}else{
   				System.out.println("pintando nada");
   				ImageIcon imagenlogo=new ImageIcon(Principal.class.getResource("/Recursos/nada.png"));
   			 return imagenlogo.getImage();
        	 		
   			}
       		     
        	   }else{
      				System.out.println("pintando nada");
       				ImageIcon imagenlogo=new ImageIcon(Principal.class.getResource("/Recursos/nada.png"));
       			 return imagenlogo.getImage();
            	 		
       			}
	
	}

	
	public static Image prepararLogoMEGALAB(){
		ImageIcon II=new ImageIcon(Colores.class.getResource("/Recursos/LogoMEGALAB.png"));
		return II.getImage();
	}
	
	public static Image prepararLogoMEGALABMem(){
		ImageIcon II=new ImageIcon(Colores.class.getResource("/Recursos/LogoMEGALABMem.png"));
		return II.getImage();
	}
	
	public static Color getClrPrincipal() {
		return clrPrincipal;
	}


	public static void setClrPrincipal(Color clrPrincipal) {
		Colores.clrPrincipal = clrPrincipal;
	}


	public static Color getClrSecundario() {
		return clrSecundario;
	}


	public static void setClrSecundario(Color clrSecundario) {
		Colores.clrSecundario = clrSecundario;
	}


	public static Color getClrTextoInactivo() {
		return clrTextoInactivo;
	}


	public static void setClrTextoInactivo(Color clrTextoInactivo) {
		Colores.clrTextoInactivo = clrTextoInactivo;
	}


	public static Color getClrAlertaCamarada() {
		return clrAlertaCamarada;
	}


	public static void setClrAlertaCamarada(Color clrAlertaCamarada) {
		Colores.clrAlertaCamarada = clrAlertaCamarada;
	}


	public static boolean isClrIconosNegros() {
		return clrIconosNegros;
	}


	public static void setClrIconosNegros(boolean clrIconosNegros) {
		Colores.clrIconosNegros = clrIconosNegros;
	}

	
	
	public static String getHost2() {
		return host2;
	}

	public static void setHost2(String host2) {
		Colores.host2 = host2;
	}

	public static String getUser2() {
		return user2;
	}

	public static void setUser2(String user2) {
		Colores.user2 = user2;
	}

	public static String getPass2() {
		return pass2;
	}

	public static void setPass2(String pass2) {
		Colores.pass2 = pass2;
	}

	public static String getDtbs2() {
		return dtbs2;
	}

	public static void setDtbs2(String dtbs2) {
		Colores.dtbs2 = dtbs2;
	}

	public static String getHost() {
		return host;
	}

	public static void setHost(String host) {
		Colores.host = host;
	}

	public static String getUser() {
		return user;
	}

	public static void setUser(String user) {
		Colores.user = user;
	}

	public static String getPass() {
		return pass;
	}

	public static void setPass(String pass) {
		Colores.pass = pass;
	}

	public static String getDtbs() {
		return dtbs;
	}

	public static void setDtbs(String dtbs) {
		Colores.dtbs = dtbs;
	}
	
public static 	String getCadenaAlfanumAleatoria (int longitud){
		String cadenaAleatoria = "";
		long milis = new java.util.GregorianCalendar().getTimeInMillis();
		Random r = new Random(milis);
		int i = 0;
		while ( i < longitud){
		char c = (char)r.nextInt(255);
		if ( (c >= '0' && c <='9') || (c >='A' && c <='Z') ){
		cadenaAleatoria += c;
		i ++;
		}
		}
		return cadenaAleatoria;
		}
	
	public static void slowMotion(int newX,int newY,final JComponent component,final JComponent padre,int duracionMs){
	
		

		int delay=duracionMs;
		
		final int numpasosX=abs(abs(component.getX())-(abs(newX)));
		final int numPasosY=abs(abs(component.getY())-(abs(newY)));
		
		int direccX=0;if(component.getX()>newX){direccX=-1;}else{if(component.getX()<newX){direccX=1;}}
		int direccY=0;if(component.getY()>newY){direccY=-1;}else{if(component.getY()<newY){direccY=1;}}

		final int direX=direccX;
		final int direY=direccY;
		
	     final  int trayMayor= Math.max(numpasosX, numPasosY);
	     int diviDel=trayMayor;
	     if (trayMayor==0) {
			diviDel=delay;
		}
	     final int delayPaso=delay/diviDel;
				 Thread reporte = new Thread(new Runnable() {
					    @Override
					    public void run() {
					         try{

					            for (int z=0; z<trayMayor; z++){
					            	if (z<numPasosY) {
					            		component.setLocation(component.getX(),( component.getY()+direY));
					            		padre.updateUI();
									}
					            	
					            	if (z<numpasosX) {
					            		component.setLocation(component.getX()+direX,( component.getY()));
					            		padre.updateUI();
									}
					            	
					            Thread.sleep(delayPaso);  
					            } 
					    
					        }catch(Exception ae){

					    }
					        
					    }
					});
					reporte.start();

		
	
		
	
	}
	
	public static void guardarPuerto(String puerto){
		FileInputStream entrada=null;
		Properties propiedades = new Properties();
		try {

			entrada = new FileInputStream("C:/6342522/conad.prop");

			propiedades.load(entrada);
propiedades.setProperty("puerto7600", puerto);
		puerto7600=puerto;
	
		
		  FileOutputStream fos=new FileOutputStream("C:/6342522/conad.prop");
			 
          propiedades.store(fos,null);
		} catch (IOException ex) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR COLORES> : no es posible leer el archivo de configuración");
		}
		
		
	}
	
	
	 public static int abs (int numero) {
         return numero > 0 ? numero : -numero;
   }

public static Rectangle getRectangleItext(String tam){
	
	
	if (tam.equals("Carta")) {
		return new Rectangle(0, 0, 612, 792, 0);
	}
	else if(tam.equals("Media Carta")){
		return new Rectangle(0, 0, 396, 612, 0);
	}
	
	else if(tam.equals("Oficio")){
		return new Rectangle(0, 0, 612, 1008, 0);
	}
	
	else
		return new Rectangle(0, 0, 612, 792, 0);

	
}

public static void cargarOpcionesImpresion(Properties propiedades){
	try {
	
	impresoraListados= propiedades.getProperty("impresoraListados");
	impimirEncabezadoListados= Boolean.parseBoolean(propiedades.getProperty("imprimirEncListados"));
	espacioEncabezadosListados=Math.round(Float.parseFloat(propiedades.getProperty("espacioListados")));
		String rutaEncListados="";if (!propiedades.getProperty("encListados").equals("")) {rutaEncListados="C:/6342522"+propiedades.getProperty("encListados");enabezadoListados= ImageIO.read(new File(rutaEncListados));};
		enabezadoListados.flush();
		
		tamHojaListados=propiedades.getProperty("tamHojaListados");
		notaListados=propiedades.getProperty("notaListados");
		
		impresoraFacturas= propiedades.getProperty("impresoraFacturas");
		impimirEncabezadoFacturas= Boolean.parseBoolean(propiedades.getProperty("imprimirEncFacturas"));
		espacioEncabezadosFacturas=Math.round(Float.parseFloat(propiedades.getProperty("espacioFacturas")));
		String rutaEncFacturas="";if (!propiedades.getProperty("encFacturas").equals("")) {rutaEncFacturas="C:/6342522"+propiedades.getProperty("encFacturas");enabezadoFacturas=ImageIO.read(new File(rutaEncFacturas));};
			
			tamHojaFacturas=propiedades.getProperty("tamHojaFacturas");
			notaFacturas=propiedades.getProperty("notaFacturas");
			
			
		impresoraRecepciones= propiedades.getProperty("impresoraRecepciones");
		impimirEncabezadoRecepciones= Boolean.parseBoolean(propiedades.getProperty("imprimirEncRecepciones"));
		espacioEncabezadosRecepciones=Math.round(Float.parseFloat(propiedades.getProperty("espacioRecepciones")));
		String rutaEncRecepciones="";
		if (!propiedades.getProperty("encRecepciones").equals("")) {
			rutaEncRecepciones="C:/6342522"+propiedades.getProperty("encRecepciones");
			enabezadoRecepciones=ImageIO.read(new File(rutaEncRecepciones));
			}
			
			tamHojaRecepciones=propiedades.getProperty("tamHojaRecepciones");
			notaRecepciones=propiedades.getProperty("notaRecepciones");
			
			impresoraCotizaciones= propiedades.getProperty("impresoraCotizaciones");
			impimirEncabezadoCotizaciones= Boolean.parseBoolean(propiedades.getProperty("imprimirEncCotizaciones"));
			espacioEncabezadosCotizaciones=Math.round(Float.parseFloat(propiedades.getProperty("espacioCotizaciones")));
			String rutaEncCotizaciones="";if (!propiedades.getProperty("encCotizaciones").equals("")) {rutaEncCotizaciones="C:/6342522"+propiedades.getProperty("encCotizaciones");enabezadoCotizaciones=ImageIO.read(new File(rutaEncCotizaciones));};
				
				tamHojaCotizaciones=propiedades.getProperty("tamHojaCotizaciones");
				notaCotizaciones=propiedades.getProperty("notaCotizaciones");
				
				
				impresoraNotas= propiedades.getProperty("impresoraNotas");
				impimirEncabezadoNotas= Boolean.parseBoolean(propiedades.getProperty("imprimirEncNotas"));
				espacioEncabezadosNotas=Math.round(Float.parseFloat(propiedades.getProperty("espacioNotas")));
				String rutaEncNotas="";if (!propiedades.getProperty("encNotas").equals("")) {rutaEncNotas="C:/6342522"+propiedades.getProperty("encNotas");enabezadoNotas=ImageIO.read(new File(rutaEncNotas));};
					
					tamHojaNotas=propiedades.getProperty("tamHojaNotas");
					notaNotas=propiedades.getProperty("notaNotas");
					
					impresoraStickers=propiedades.getProperty("impresoraStickers");
					System.err.println(propiedades.getProperty("medidaSticker"));
					medidasStickers=propiedades.getProperty("medidaSticker").split(",");
					stickersCups= Boolean.parseBoolean(propiedades.getProperty("stickerCups"));

} catch (Exception e) {
	System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////// /n"+e);
}}

public static Properties getPropiedades(){
	FileInputStream entrada=null;
	Properties propiedades = new Properties();
	
	try {

		entrada = new FileInputStream("C:/6342522/conad.prop");

		propiedades.load(entrada);
		return propiedades;

	} catch (IOException ex) {
		System.out.println(ex);
		Principal.getInstancePrincipal().registrarErrorDB("< ERROR COLORES> : no es posible leer el archivo de configuración");
		return null;
	}
}



}


