package otrosImpresos;

import interfaces_Modificadas.Colores;

import java.awt.PrintJob;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.ColorSupported;
import javax.print.attribute.standard.PrinterName;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import Interfaces.Principal;

public class imprimirPredeterminada {

	public imprimirPredeterminada(String path,String impresora) {
		//Archivo que se desea imprimir
	    FileInputStream inputStream=null;
		try {
			inputStream = new FileInputStream(path);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	 

	 
	    //Nombre de la impresora
	    String printerName = impresora;
	 

	    PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
	    
	    
	    if (defaultPrintService != null) {
          
	    	if (impresora.equals("Lista de impresoras")) {
	    		try {
					printPDF(path, defaultPrintService);
				} catch (IOException e) {
					Principal.getInstancePrincipal().registrarErrorDB("< ERROR AL IMPRIMIR >"+e);
					e.printStackTrace();
				} catch (PrinterException e) {
					Principal.getInstancePrincipal().registrarErrorDB("< ERROR AL IMPRIMIR >"+e);
					e.printStackTrace();
				}
				
			}else{

			    //Busqueda de la impresora por el nombre asignado en attributeSet
			    PrintService[] services = PrintServiceLookup.lookupPrintServices(null,null);
			 for (PrintService printService : services) {
				if(printService.getName().equals(printerName)){
					
					try {
						printPDF(path, printService);
					} catch (IOException e) {
						Principal.getInstancePrincipal().registrarErrorDB("< ERROR AL IMPRIMIR >"+e);
						e.printStackTrace();
					} catch (PrinterException e) {
						Principal.getInstancePrincipal().registrarErrorDB("< ERROR AL IMPRIMIR >"+e);
						e.printStackTrace();
					}
				
				}

			}
			}
	    	
        } else {
            System.err.println("No existen impresoras instaladas");
        }
	    
	    
	    
	    
	    
	    
	    
	   
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    //Envio a la impresora
	   
	 
	    try {
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void printPDF(String fileName, PrintService printer)
	        throws IOException, PrinterException {
	    PrinterJob job = PrinterJob.getPrinterJob();
	    job.setPrintService(printer);
	    PDDocument doc = PDDocument.load(new File(fileName));

	       job.setPageable(new PDFPageable(doc));
	   job.print();
	   doc.close();
	}
	
}
