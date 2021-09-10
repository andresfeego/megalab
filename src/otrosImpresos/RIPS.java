package otrosImpresos;

import interfaces_Modificadas.Colores;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javafx.scene.layout.Border;

import javax.swing.ImageIcon;

import jbarcodebean.JBarcodeBean;
import metodos.ObtenerFecha;
import metodos.ObtenerFechaColombia;
import net.sourceforge.jbarcodebean.model.Code128;
import visor.VisorPDF;
import visor.VisorPlanos;
import Interfaces.Principal;
import auxiliares.Bacteriologo;
import auxiliares.DatosAbono;
import auxiliares.Empresa;
import auxiliares.Examen;
import auxiliares.GruposEmpresas;
import auxiliares.MiLaboratorio;
import auxiliares.Paciente;
import auxiliares.Recepcion;
import auxiliares.RecepcionCompleta;
import auxiliares.cuentaCobro;
import auxiliares.itemCuenta;
import auxiliares.itemFactura;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.ZapfDingbatsList;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import conexion.conexion;
import conexion.conexionBusqueda;

/**
 * 
 * @author http://datojava.blogspot.com/
 *
 */
public class RIPS {
	private String nombrearchivo;
	private ArrayList<itemCuenta> itemsCC;
	private cuentaCobro CC;
	private MiLaboratorio miLab;
	private Empresa empresa;
	

	
	
	public RIPS(cuentaCobro cuenta,String numeroContrato) {
		this.CC=cuenta;
		miLab=conexionBusqueda.getInstance().miLab();
		empresa=conexionBusqueda.getInstance().empresaXid(CC.getIdEmpresa());
		itemsCC=CC.getItems();
		
    	String nombreArchivoCT="CT"+String.format("%06d", CC.getIdCuenta());
    	String nombreArchivoAP="AP"+String.format("%06d", CC.getIdCuenta());
    	String nombreArchivoAD="AD"+String.format("%06d", CC.getIdCuenta());
    	String nombreArchivoUS="US"+String.format("%06d", CC.getIdCuenta());
    	String nombreArchivoAF="AF"+String.format("%06d", CC.getIdCuenta());
    	
    	 int contAP=0;
    	 int contAD=0;
    	 int contUS=0;
    	 int contAF=0;
    	
		
		try {
			
			File directorio=new File("C:/6342522/temp/");
			if (!directorio.exists()) {
				directorio.mkdirs();
			} 
			
			
			
 SimpleDateFormat SDT=new SimpleDateFormat("dd/MM/yyyy");
 FileWriter fichero = null;
 PrintWriter pw = null;
	        try
	        {

	            fichero = new FileWriter("C:/6342522/temp/"+nombreArchivoAF+".txt");
	            pw = new PrintWriter(fichero);

	           
	            contAF++;
	            
	                pw.println(miLab.getCodigoIPS()+","+miLab.getRazonSocial()+","+miLab.getTipoDocString()+","+miLab.getNumeroDoc()+","+String.format("%05d", CC.getIdCuenta())+","+SDT.format(CC.getFechaCuenta())+","+SDT.format(CC.getInicioRango())+","+SDT.format(CC.getFinRango())+","+empresa.getCodEps()+","+empresa.getRazonSocial()+","+numeroContrato+","+","+","+CC.getCopago()+","+","+"0"+","+CC.getNetoPago());

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	           try {
	           if (null != fichero)
	              fichero.close();
	           } catch (Exception e2) {
	              e2.printStackTrace();
	           }
	        }
	        
	      
	        
	        
	        
	        FileWriter ficheroAP = null;
	        PrintWriter pwAP = null;
	        try
	        {

	            ficheroAP = new FileWriter("C:/6342522/temp/"+nombreArchivoAP+".txt");
	            pwAP = new PrintWriter(ficheroAP);

	           
	            for (int i = 0; i<itemsCC.size() ; i++) {
					itemCuenta IC=itemsCC.get(i);
					Paciente paciente=conexionBusqueda.getInstance().pacienteXid(IC.getIdPaciente());
					RecepcionCompleta RC= conexionBusqueda.getInstance().RCXid(IC.getNumFacturaRecepcion());
					String auxiAmbito="";if(RC.getRecepcion().getCodAmbito()!=-1){auxiAmbito=RC.getRecepcion().getCodAmbito()+"";};
					String auxiAutoriza="";if(!RC.getRecepcion().getAutorizacion().equals("-1")){auxiAutoriza=RC.getRecepcion().getAutorizacion();};
					String auxiFinali="";if(RC.getRecepcion().getCodFinalidad()!=-1){auxiFinali=RC.getRecepcion().getCodFinalidad()+"";};
	                pwAP.println(String.format("%05d", CC.getIdCuenta())+","+miLab.getCodigoIPS()+","+paciente.tipoDocString()+","+paciente.getId()+","+SDT.format(IC.getFechaRecepcion())+","+auxiAutoriza+","+IC.getCups()+","+auxiAmbito+","+auxiFinali+","+","+","+","+","+","+IC.getValorProcedimiento());
					contAP++;
				}
	            

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	           try {
	           if (null != ficheroAP)
	              ficheroAP.close();
	           } catch (Exception e2) {
	              e2.printStackTrace();
	           }
	        }
	        
	        
	        FileWriter ficheroAD = null;
	        PrintWriter pwAD = null;
	        try
	        {

	            ficheroAD = new FileWriter("C:/6342522/temp/"+nombreArchivoAD+".txt");
	            pwAD = new PrintWriter(ficheroAD);

	           
	            
	            contAD++;
	                pwAD.println(String.format("%05d", CC.getIdCuenta())+","+miLab.getCodigoIPS()+","+"02"+","+contAP+","+"0"+","+CC.getNetoPago());

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	           try {
	           if (null != ficheroAD)
	              ficheroAD.close();
	           } catch (Exception e2) {
	              e2.printStackTrace();
	           }
	        }
	        
	        FileWriter ficheroUS = null;
	        PrintWriter pwUS = null;
	        try
	        {

	            ficheroUS = new FileWriter("C:/6342522/temp/"+nombreArchivoUS+".txt");
	            pwUS = new PrintWriter(ficheroUS);

	           ArrayList<String> listaDocs=new ArrayList<String>();
	           listaDocs.add(itemsCC.get(0).getIdPaciente());
	           itemCuenta IC0=itemsCC.get(0);
				Paciente paciente0=conexionBusqueda.getInstance().pacienteXid(IC0.getIdPaciente());
				RecepcionCompleta RC0= conexionBusqueda.getInstance().RCXid(IC0.getNumFacturaRecepcion());
				   pwUS.println(paciente0.tipoDocString()+","+paciente0.getId()+","+empresa.getCodEps()+","+RC0.getRecepcion().getTipoUsuario()+","+paciente0.primerApellido()+","+paciente0.segundoApellido()+","+paciente0.PrimerNombre()+","+paciente0.SegundoNombre()+","+paciente0.edadNum()+","+paciente0.edadTipo()+","+paciente0.getGeneroSigla()+","+paciente0.codCiudad().substring(0, 2)+","+paciente0.codCiudad().substring(2, 5)+","+paciente0.getResidenciaSigla());
               contUS++;
	            for (int i = 1; i<itemsCC.size() ; i++) {
					itemCuenta IC=itemsCC.get(i);
					Paciente paciente=conexionBusqueda.getInstance().pacienteXid(IC.getIdPaciente());
					RecepcionCompleta RC= conexionBusqueda.getInstance().RCXid(IC.getNumFacturaRecepcion());
					int cont =0;
					for (int j = 0; j < listaDocs.size(); j++) {
						System.err.println(listaDocs.get(j)+"fffffff"+paciente.getId());
						if (listaDocs.get(j).equals(paciente.getId())) {
							cont++;
						}
					}
					if (cont==0) {
						listaDocs.add(paciente.getId());
						   pwUS.println(paciente.tipoDocString()+","+paciente.getId()+","+empresa.getCodEps()+","+RC.getRecepcion().getTipoUsuario()+","+paciente.primerApellido()+","+paciente.segundoApellido()+","+paciente.PrimerNombre()+","+paciente.SegundoNombre()+","+paciente.edadNum()+","+paciente.edadTipo()+","+paciente.getGeneroSigla()+","+paciente.codCiudad().substring(0, 2)+","+paciente.codCiudad().substring(2,5)+","+paciente.getResidenciaSigla());
			                contUS++;
					}
			
				}
	            

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	           try {
	           if (null != ficheroUS)
	              ficheroUS.close();
	           } catch (Exception e2) {
	              e2.printStackTrace();
	           }
	        }
	        
	        
	        
	         FileWriter ficheroCT = null;
		        PrintWriter pwCT = null;
		        try
		        {

		            ficheroCT = new FileWriter("C:/6342522/temp/"+nombreArchivoCT+".txt");
		            pwCT = new PrintWriter(ficheroCT);

		           
		            
		  
	                pwCT.println(miLab.getCodigoIPS()+","+SDT.format(CC.getFechaCuenta())+","+nombreArchivoAD+","+contAD);
	                pwCT.println(miLab.getCodigoIPS()+","+SDT.format(CC.getFechaCuenta())+","+nombreArchivoAF+","+contAF);
	                pwCT.println(miLab.getCodigoIPS()+","+SDT.format(CC.getFechaCuenta())+","+nombreArchivoUS+","+contUS);
	                pwCT.println(miLab.getCodigoIPS()+","+SDT.format(CC.getFechaCuenta())+","+nombreArchivoAP+","+contAP);

		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		           try {
		           if (null != ficheroCT)
		              ficheroCT.close();
		           } catch (Exception e2) {
		              e2.printStackTrace();
		           }
		        }
		        
			
			
		
	// Abrir el archivo
			
				String  path ="C:/6342522/temp/"+nombreArchivoAF+".txt";
				new VisorPlanos(path);
				
				String  path1 ="C:/6342522/temp/"+nombreArchivoAP+".txt";
				new VisorPlanos(path1);
				
				String  path2 ="C:/6342522/temp/"+nombreArchivoAD+".txt";
				new VisorPlanos(path2);
				
				String  path3 ="C:/6342522/temp/"+nombreArchivoUS+".txt";
				new VisorPlanos(path3);
				
				String  path4 ="C:/6342522/temp/"+nombreArchivoCT+".txt";
				new VisorPlanos(path4);
	
		
			
		} catch (Exception ex) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR  >Error al generar el archivo correspondiente a esta cuenta de cobro");

		}



	

	}
	

	
}
	