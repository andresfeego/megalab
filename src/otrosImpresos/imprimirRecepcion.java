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
import auxiliares.Examen;
import auxiliares.Paciente;
import auxiliares.Recepcion;
import auxiliares.RecepcionCompleta;
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
public class imprimirRecepcion {
	private RecepcionCompleta RC;
	private Recepcion recepcion;
	private boolean imprimir;
	private String nombrearchivo;
	public imprimirRecepcion(RecepcionCompleta RC,boolean imprimir) {
		this.RC=RC;
		this.recepcion=RC.getRecepcion();
		this.imprimir=imprimir;
		// Creacion del documento con los margenes

		Document document = new Document(Colores.getRectangleItext(Colores.tamHojaRecepciones), 35, 30, 50, 50);
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

			if (Colores.impimirEncabezadoRecepciones) {
				image = Image.getInstance(Colores.enabezadoRecepciones,null);
				image.scaleAbsolute(document.getPageSize().getWidth()-65,(((document.getPageSize().getWidth()-65)*image.getHeight())/image.getWidth()));
			}else{
				   BufferedImage imageBuff = new BufferedImage(100, Colores.espacioEncabezadosRecepciones, BufferedImage.TYPE_INT_ARGB);
			        Graphics2D g = (Graphics2D)imageBuff.getGraphics();
			        g.setColor(Colores.clrTransparente);
			        g.fillRect(0, 0,100, Colores.espacioEncabezadosRecepciones);
			        ImageIcon IA=new ImageIcon(imageBuff);
			        image= Image.getInstance(IA.getImage(),null);
			        
			}		
			document.add(image);
			
		// Cargar codigo de barras
			Paragraph numeroRecepcion = new Paragraph();
			numeroRecepcion.add(new Phrase("Recepción # "+String.format("%05d", recepcion.getIdREcepcion()),Colores.fuenteContenidos));
			Image codigo;
			JBarcodeBean barcode=new JBarcodeBean();
			barcode.setCodeType(new Code128());
			barcode.setCode(recepcion.getIdREcepcion()+"");
			barcode.setLabelPosition(0);
			barcode.setCheckDigit(true);
			BufferedImage bufferedImage = barcode.draw(new BufferedImage(100, 20, BufferedImage.TYPE_INT_RGB));
			codigo=Image.getInstance(bufferedImage,null);
			document.add(numeroRecepcion);
				document.add(codigo);

				Paragraph recepcionadopor = new Paragraph();
				recepcionadopor.add(new Phrase("Recepcionado por : "+recepcion.getRecepcionadoPor()+" || Informe generado el : "+(new ObtenerFechaColombia()).fechaColombia(),Colores.fuentePeque));
				recepcionadopor.add(new Phrase(Chunk.NEWLINE));
				recepcionadopor.add(new Phrase(Chunk.NEWLINE));
				document.add(recepcionadopor);
				
				// datos de la recpcion


				PdfPTable table = new PdfPTable(1);
				table.setWidthPercentage(49);
				table.setHorizontalAlignment(Element.ALIGN_LEFT);
				
				PdfPCell cell = new PdfPCell(new Phrase("Datos de paciente",Colores.fuenteContenidosBold));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
		
				
				Paciente paciente=recepcion.getPaciente();
				
					PdfPCell cellID = new PdfPCell(new Phrase("ID: "+paciente.getId(),Colores.fuenteContenidos));
				cellID.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cellID);
				
				PdfPCell cellNombre = new PdfPCell(new Phrase("Nombre: "+paciente.getNombres()+" "+paciente.getApellidos(),Colores.fuenteContenidos));
				cellNombre.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cellNombre);
				
				PdfPCell cellEdad = new PdfPCell(new Phrase("Edad: "+paciente.calcularEdad(),Colores.fuenteContenidos));
				cellEdad.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cellEdad);
				
				PdfPCell cellGenero = new PdfPCell(new Phrase("Genero: "+paciente.getGeneroString(),Colores.fuenteContenidos));
				cellGenero.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cellGenero);
				
				PdfPCell cellDireccion = new PdfPCell(new Phrase("Dirección: "+paciente.getDireccion(),Colores.fuenteContenidos));
				cellDireccion.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cellDireccion);
				
				PdfPCell cellTelefono = new PdfPCell(new Phrase("Telefono: "+paciente.getTelefono1(),Colores.fuenteContenidos));
				cellTelefono.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cellTelefono);
				
				
			

				PdfPTable tableR = new PdfPTable(1);
				tableR.setWidthPercentage(49);
				tableR.setHorizontalAlignment(Element.ALIGN_RIGHT);
				
				PdfPCell cell1 = new PdfPCell(new Phrase("Datos de recepción",Colores.fuenteContenidosBold));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableR.addCell(cell1);
		
				
								
					PdfPCell cellIDRecepcion = new PdfPCell(new Phrase("Recepción # : "+String.format("%05d", recepcion.getIdREcepcion()),Colores.fuenteContenidos));
					cellIDRecepcion.setHorizontalAlignment(Element.ALIGN_LEFT);
				tableR.addCell(cellIDRecepcion);
				
				PdfPCell cellFecha = new PdfPCell(new Phrase("Fecha: "+recepcion.getFechaRecepcion(),Colores.fuenteContenidos));
				cellFecha.setHorizontalAlignment(Element.ALIGN_LEFT);
				tableR.addCell(cellFecha);
				
			
				PdfPCell cellMedico = new PdfPCell(new Phrase("Medico: "+recepcion.getCodMedicoString(),Colores.fuenteContenidos));
				cellMedico.setHorizontalAlignment(Element.ALIGN_LEFT);
				tableR.addCell(cellMedico);
				
				PdfPCell cellEmpresa = new PdfPCell(new Phrase("Empresa: "+recepcion.getEmpresa().getRazonSocial(),Colores.fuenteContenidos));
				cellEmpresa.setHorizontalAlignment(Element.ALIGN_LEFT);
				tableR.addCell(cellEmpresa);
				
				PdfPCell cellSede = new PdfPCell(new Phrase("Sede: "+Colores.sede,Colores.fuenteContenidos));
				cellSede.setHorizontalAlignment(Element.ALIGN_LEFT);
				tableR.addCell(cellSede);
				
				PdfPCell cellAutorizacion = new PdfPCell(new Phrase("Autorización #: "+recepcion.getAutorizacionString(),Colores.fuenteContenidos));
				cellAutorizacion.setHorizontalAlignment(Element.ALIGN_LEFT);
				tableR.addCell(cellAutorizacion);
				
				PdfPCell cellOrden = new PdfPCell(new Phrase("Número de orden: "+recepcion.getNumeroOrdenString(),Colores.fuenteContenidos));
				cellOrden.setBorderColor(Colores.colorBordesTablas);
				cellOrden.setHorizontalAlignment(Element.ALIGN_LEFT);
				tableR.addCell(cellOrden);
				
				
				PdfPTable tableCompletaR = new PdfPTable(2);
				tableCompletaR.setWidthPercentage(100);
				tableCompletaR.setHorizontalAlignment(Element.ALIGN_RIGHT);
				tableCompletaR.getDefaultCell().setBorderColor(Colores.colorBordesTablas);
				tableCompletaR.addCell(table);
				tableCompletaR.addCell(tableR);
				
				document.add(tableCompletaR);
				
				Paragraph separador1 = new Paragraph();
				separador1.add(new Phrase(Chunk.NEWLINE));
				document.add(separador1);
				
				PdfPTable tableDetalles = new PdfPTable(3);
				tableDetalles.setWidthPercentage(100);
				tableDetalles.getDefaultCell().setBorderColor(Colores.colorBordesTablas);
				tableDetalles.setHorizontalAlignment(Element.ALIGN_RIGHT);
				
				PdfPCell cellTituloDetalles = new PdfPCell(new Phrase("Detalles para esta recepción",Colores.fuenteContenidosBold));
				cellTituloDetalles.setBorderColor(Colores.colorBordesTablas);
				cellTituloDetalles.setColspan(3);
				cellTituloDetalles.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableDetalles.addCell(cellTituloDetalles);
				
				PdfPCell cellTitulocodExamenT = new PdfPCell(new Phrase("Código de  examen",Colores.fuenteContenidosBold));
				cellTitulocodExamenT.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableDetalles.addCell(cellTitulocodExamenT);
				
				PdfPCell cellTituloNomExamenT = new PdfPCell(new Phrase("Nombre de  examen",Colores.fuenteContenidosBold));
				cellTituloNomExamenT.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableDetalles.addCell(cellTituloNomExamenT);
				
				PdfPCell cellTituloValExamenT = new PdfPCell(new Phrase("Valor de  examen",Colores.fuenteContenidosBold));
				cellTituloValExamenT.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableDetalles.addCell(cellTituloValExamenT);
				
				ArrayList<itemFactura> AE=RC.getItemsFactura();
				int cant=0;
				for (int i = 0; i < AE.size(); i++) {
					itemFactura IF=AE.get(i);
					Examen exa=conexionBusqueda.getInstance().examenXcodigo(IF.getCodExamen());
					cant++;
					
					PdfPCell cellTitulocodExamen = new PdfPCell(new Phrase(exa.getCups()+"",Colores.fuenteContenidos));
					cellTitulocodExamen.setHorizontalAlignment(Element.ALIGN_LEFT);
					tableDetalles.addCell(cellTitulocodExamen);
					
					PdfPCell cellTituloNomExamen = new PdfPCell(new Phrase(exa.getNombre(),Colores.fuenteContenidos));
					cellTituloNomExamen.setHorizontalAlignment(Element.ALIGN_LEFT);
					tableDetalles.addCell(cellTituloNomExamen);
					
					PdfPCell cellTituloValExamen = new PdfPCell(new Phrase("$ "+IF.getValor(),Colores.fuenteContenidos));
					cellTituloValExamen.setHorizontalAlignment(Element.ALIGN_RIGHT);
					tableDetalles.addCell(cellTituloValExamen);
				}
				
				PdfPCell cellTitulocodExamen = new PdfPCell(new Phrase("",Colores.fuenteContenidosBold));
				cellTitulocodExamen.setHorizontalAlignment(Element.ALIGN_LEFT);
				tableDetalles.addCell(cellTitulocodExamen);
				
				PdfPCell cellTituloNomExamen = new PdfPCell(new Phrase("TOTAL=",Colores.fuenteContenidosBold));
				cellTituloNomExamen.setHorizontalAlignment(Element.ALIGN_RIGHT);
				tableDetalles.addCell(cellTituloNomExamen);
				
				PdfPCell cellTituloValExamen = new PdfPCell(new Phrase("$ "+RC.getFactura().getTotalConD()+"",Colores.fuenteContenidos));
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
				
				PdfPCell celltotales2 = new PdfPCell(new Phrase("Descuento % : "+RC.getFactura().getDescuentoPorciento(),Colores.fuenteContenidos));
				celltotales2.setHorizontalAlignment(Element.ALIGN_LEFT);
				tableTotales.addCell(celltotales2);
				
				PdfPCell celltotales3 = new PdfPCell(new Phrase("Abono : "+RC.getFactura().getAbono(),Colores.fuenteContenidos));
				celltotales3.setHorizontalAlignment(Element.ALIGN_LEFT);
				tableTotales.addCell(celltotales3);
				
				PdfPCell celltotales4 = new PdfPCell(new Phrase("Saldo : "+RC.getFactura().getSaldo(),Colores.fuenteContenidos));
				celltotales4.setHorizontalAlignment(Element.ALIGN_LEFT);
				tableTotales.addCell(celltotales4);
				
				document.add(tableTotales);
			
		

			// Cerrar el documento
			document.close();

			// Abrir el archivo
			
			if (imprimir) {
				new imprimirPredeterminada("C:/6342522/temp/"+nombrearchivo+".pdf",Colores.impresoraRecepciones);
			    Principal.getInstancePrincipal().registrarAccion("Impresión de reporte de recepción");

			} else {
				String  path = "C:/6342522/temp/"+nombrearchivo+".pdf";
				NativeInterface.open();
				
				(new VisorPDF(path, "Recepción # "+String.format("%05d", recepcion.getIdREcepcion()))).execute();
			    Principal.getInstancePrincipal().registrarAccion("Creación de reporte de recepción PDF");

			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}



	

	}
	
}
	