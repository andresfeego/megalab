package otrosImpresos;

import interfaces_Modificadas.Colores;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javafx.scene.layout.Border;

import javax.swing.ImageIcon;

import jbarcodebean.JBarcodeBean;
import metodos.ObtenerFecha;
import metodos.ObtenerFechaColombia;
import net.sourceforge.jbarcodebean.model.Code128;
import visor.VisorPDF;
import Interfaces.Principal;
import auxiliares.Cotizacion;
import auxiliares.Examen;
import auxiliares.Paciente;
import auxiliares.Recepcion;
import auxiliares.RecepcionCompleta;
import auxiliares.itemCotizacion;
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

import conexion.conexionBusqueda;

/**
 * 
 * @author http://datojava.blogspot.com/
 *
 */
public class ImprimirCotizacion {
	private Cotizacion coti;
	private int numeroCoti;
	private boolean imprimir;
	private String nombrearchivo;
	
	public ImprimirCotizacion(Cotizacion coti,boolean imprimir) {
		this.coti=coti;
		this.imprimir=imprimir;
		this.numeroCoti=coti.getIdCotizacion();
		// Creacion del documento con los margenes
		Document document = new Document(Colores.getRectangleItext(Colores.tamHojaCotizaciones), 35, 30, 50, 50);
		try {
			
			File directorio=new File("C:/6342522/temp/");
			if (!directorio.exists()) {
				directorio.mkdirs();
			} 
			
			nombrearchivo="MEGALABtemp"+Colores.getCadenaAlfanumAleatoria(7);
			
			FileOutputStream fileOutputStream = new FileOutputStream("C:/6342522/temp/"+nombrearchivo+".pdf");
			PdfWriter writer=PdfWriter.getInstance(document, fileOutputStream);
	        writer.setPageEvent(new FormatoDocumento());

			document.open();
				
				
			//preparamos el encabezado
			Image image = null;

			if (Colores.impimirEncabezadoCotizaciones) {
				image = Image.getInstance(Colores.enabezadoCotizaciones,null);
				image.scaleAbsolute(document.getPageSize().getWidth()-65,(((document.getPageSize().getWidth()-65)*image.getHeight())/image.getWidth()));
			}else{
				   BufferedImage imageBuff = new BufferedImage(100, Colores.espacioEncabezadosCotizaciones, BufferedImage.TYPE_INT_ARGB);
			        Graphics2D g = (Graphics2D)imageBuff.getGraphics();
			        g.setColor(Colores.clrTransparente);
			        g.fillRect(0, 0,100, Colores.espacioEncabezadosCotizaciones);
			        ImageIcon IA=new ImageIcon(imageBuff);
			        image= Image.getInstance(IA.getImage(),null);
			        
			}		
			document.add(image);
			
		// Cargar codigo de barras
			Paragraph numeroRecepcion = new Paragraph();
			numeroRecepcion.add(new Phrase("Cotización # "+String.format("%05d", numeroCoti),Colores.fuenteContenidos));
			Image codigo;
			JBarcodeBean barcode=new JBarcodeBean();
			barcode.setCodeType(new Code128());
			barcode.setCode(numeroCoti+"");
			barcode.setLabelPosition(0);
			barcode.setCheckDigit(true);
			BufferedImage bufferedImage = barcode.draw(new BufferedImage(100, 20, BufferedImage.TYPE_INT_RGB));
			codigo=Image.getInstance(bufferedImage,null);
			document.add(numeroRecepcion);
				document.add(codigo);

		
				// datos de la recpcion


				PdfPTable table = new PdfPTable(2);
				table.setWidthPercentage(49);
				table.setHorizontalAlignment(Element.ALIGN_LEFT);
				
				PdfPCell cell = new PdfPCell(new Phrase("Datos de la cotización",Colores.fuenteContenidosBold));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorderColor(BaseColor.BLACK);
				cell.setColspan(2);
				table.addCell(cell);
		
				
		
				PdfPCell cellNombre = new PdfPCell(new Phrase("Cotizado a: "+coti.getCotizadoA(),Colores.fuenteContenidos));
				cellNombre.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cellNombre);
				
				PdfPCell cellDireccioin = new PdfPCell(new Phrase("Dirección: "+coti.getDireccion(),Colores.fuenteContenidos));
				cellDireccioin.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cellDireccioin);
				
				PdfPCell cellTelefono = new PdfPCell(new Phrase("Telefono: "+coti.getTelefono(),Colores.fuenteContenidos));
				cellTelefono.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cellTelefono);
				
				PdfPCell cellFechaCot = new PdfPCell(new Phrase("Cotizado el: "+coti.getFecha(),Colores.fuenteContenidos));
				cellFechaCot.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cellFechaCot);
				
				PdfPCell cellTarifa = new PdfPCell(new Phrase("Bajo la tarifa: "+(conexionBusqueda.getInstance().TarifaXid(coti.getCodTarifa()+"")).getDescripcion(),Colores.fuenteContenidos));
				cellTarifa.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cellTarifa);
				
				PdfPCell cellDescuento = new PdfPCell(new Phrase("Descuento: "+coti.getDescuento()+" %",Colores.fuenteContenidos));
				cellDescuento.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cellDescuento);
				
				PdfPCell cellObs = new PdfPCell(new Phrase("Observaciones: "+coti.getObs(),Colores.fuenteContenidos));
				cellObs.setHorizontalAlignment(Element.ALIGN_LEFT);
				cellObs.setColspan(2);
				table.addCell(cellObs);
				
							
				PdfPTable tableCompletaR = new PdfPTable(1);
				tableCompletaR.setWidthPercentage(100);
				tableCompletaR.setHorizontalAlignment(Element.ALIGN_RIGHT);
				tableCompletaR.getDefaultCell().setBorderColor(Colores.colorBordesTablas);
				tableCompletaR.addCell(table);
				
				document.add(tableCompletaR);
				
				Paragraph separador1 = new Paragraph();
				separador1.add(new Phrase(Chunk.NEWLINE));
				document.add(separador1);
				
				PdfPTable tableDetalles = new PdfPTable(4);
				tableDetalles.setWidthPercentage(100);
				float[] medidaCeldas = {1.2f, 2.7f, 1.5f,1f};
				tableDetalles.setWidths(medidaCeldas);
				tableDetalles.getDefaultCell().setBorderColor(Colores.colorBordesTablas);
				tableDetalles.setHorizontalAlignment(Element.ALIGN_RIGHT);
				
				PdfPCell cellTituloDetalles = new PdfPCell(new Phrase("Detalles para esta cotización",Colores.fuenteContenidosBold));
				cellTituloDetalles.setBorderColor(BaseColor.BLACK);
				cellTituloDetalles.setColspan(4);
				cellTituloDetalles.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableDetalles.addCell(cellTituloDetalles);
				
				PdfPCell cellTitulocodExamenT = new PdfPCell(new Phrase("Código de  examen",Colores.fuenteContenidosBold));
				cellTitulocodExamenT.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableDetalles.addCell(cellTitulocodExamenT);
				
				PdfPCell cellTituloNomExamenT = new PdfPCell(new Phrase("Nombre de  examen",Colores.fuenteContenidosBold));
				cellTituloNomExamenT.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableDetalles.addCell(cellTituloNomExamenT);
				
				PdfPCell cellTituloRecargos = new PdfPCell(new Phrase("Recargos",Colores.fuenteContenidosBold));
				cellTituloRecargos.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableDetalles.addCell(cellTituloRecargos);
				
				PdfPCell cellTituloValExamenT = new PdfPCell(new Phrase("Valor de  examen",Colores.fuenteContenidosBold));
				cellTituloValExamenT.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableDetalles.addCell(cellTituloValExamenT);
				
				ArrayList<itemCotizacion> ItemsC=coti.getItems();
				int cant=0;
				for (int i = 0; i < ItemsC.size(); i++) {
					itemCotizacion IC=ItemsC.get(i);
					Examen exa=conexionBusqueda.getInstance().examenXid(IC.getCodExamen());
					cant++;
					
					PdfPCell cellTitulocodExamen = new PdfPCell(new Phrase(exa.getCups()+"",Colores.fuenteContenidos));
					cellTitulocodExamen.setHorizontalAlignment(Element.ALIGN_LEFT);
					cellTitulocodExamen.setBorderColor(Colores.colorBordesTablas);
					tableDetalles.addCell(cellTitulocodExamen);
					
					PdfPCell cellTituloNomExamen = new PdfPCell(new Phrase(exa.getNombre(),Colores.fuenteContenidos));
					cellTituloNomExamen.setHorizontalAlignment(Element.ALIGN_LEFT);
					cellTituloNomExamen.setBorderColor(Colores.colorBordesTablas);
					tableDetalles.addCell(cellTituloNomExamen);
					
					String auxiRU="No";
					if (IC.getRU()==1) {auxiRU="Si";}
					
					String auxiRF="No";
					if (IC.getRF()==1) {auxiRF="Si";}
					
					String auxiRE="No";
					if (IC.getRE()==1) {auxiRE="Si";}
					
					PdfPCell cellTitulorecargos = new PdfPCell(new Phrase("Urg.: "+auxiRU+"|| Fes.: "+auxiRF+"|| Esp.l: "+auxiRE,Colores.fuenteContenidos));
					cellTitulorecargos.setHorizontalAlignment(Element.ALIGN_LEFT);
					cellTitulorecargos.setBorderColor(Colores.colorBordesTablas);
					tableDetalles.addCell(cellTitulorecargos);
					
					PdfPCell cellTituloValExamen = new PdfPCell(new Phrase("$ "+IC.getValor(),Colores.fuenteContenidos));
					cellTituloValExamen.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cellTituloValExamen.setBorderColor(Colores.colorBordesTablas);
					tableDetalles.addCell(cellTituloValExamen);
				}
				
				PdfPCell cellTitulocodExamen = new PdfPCell(new Phrase("",Colores.fuenteContenidosBold));
				cellTitulocodExamen.setHorizontalAlignment(Element.ALIGN_LEFT);
				tableDetalles.addCell(cellTitulocodExamen);
				
				PdfPCell cellTitulocodExamen2 = new PdfPCell(new Phrase("",Colores.fuenteContenidosBold));
				cellTitulocodExamen2.setHorizontalAlignment(Element.ALIGN_LEFT);
				tableDetalles.addCell(cellTitulocodExamen2);
				
				PdfPCell cellTituloNomExamen = new PdfPCell(new Phrase("TOTAL =",Colores.fuenteContenidosBold));
				cellTituloNomExamen.setHorizontalAlignment(Element.ALIGN_RIGHT);
				tableDetalles.addCell(cellTituloNomExamen);
				
				PdfPCell cellTituloValExamen = new PdfPCell(new Phrase("$ "+coti.getTotalConD()+"",Colores.fuenteContenidos));
				cellTituloValExamen.setHorizontalAlignment(Element.ALIGN_RIGHT);
				tableDetalles.addCell(cellTituloValExamen);
			
				document.add(tableDetalles);
				
				PdfPTable tableTotales = new PdfPTable(4);
				tableTotales.setWidthPercentage(70);
				tableTotales.getDefaultCell().setBorderColor(Colores.colorBordesTablas);
				tableTotales.setHorizontalAlignment(Element.ALIGN_RIGHT);
				
				PdfPCell celltotales1 = new PdfPCell(new Phrase("Cant. Examenes: "+cant,Colores.fuenteContenidos));
				celltotales1.setBorderColor(Colores.colorBordesTablas);
				celltotales1.setHorizontalAlignment(Element.ALIGN_LEFT);
				tableTotales.addCell(celltotales1);
				
				PdfPCell celltotales2 = new PdfPCell(new Phrase("Descuento % : "+coti.getDescuento(),Colores.fuenteContenidos));
				celltotales2.setHorizontalAlignment(Element.ALIGN_LEFT);
				tableTotales.addCell(celltotales2);
				
				PdfPCell celltotales3 = new PdfPCell(new Phrase("",Colores.fuenteContenidos));
				celltotales3.setHorizontalAlignment(Element.ALIGN_LEFT);
				tableTotales.addCell(celltotales3);
				
				PdfPCell celltotales4 = new PdfPCell(new Phrase("",Colores.fuenteContenidos));
				celltotales4.setHorizontalAlignment(Element.ALIGN_LEFT);
				tableTotales.addCell(celltotales4);
				
				document.add(tableTotales);
			
		

			// Cerrar el documento
			document.close();

			// Abrir el archivo
			
			if (imprimir) {
				new imprimirPredeterminada("C:/6342522/temp/"+nombrearchivo+".pdf",Colores.impresoraCotizaciones);
			    Principal.getInstancePrincipal().registrarAccion("Impresión de cotizacíon");

			} else {
				String  path = "C:/6342522/temp/"+nombrearchivo+".pdf";
				NativeInterface.open();
				(new VisorPDF(path, "Cotización # "+String.format("%05d", numeroCoti))).execute();
			    Principal.getInstancePrincipal().registrarAccion("Creación de cotizacíon PDF");

			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}



	

	}
	
}
	