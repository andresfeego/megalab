package otrosImpresos;

import interfaces_Modificadas.Colores;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.ImageIcon;

import jbarcodebean.JBarcodeBean;
import visor.VisorPDF;
import auxiliares.Recepcion;
import auxiliares.RecepcionCompleta;
import chrriis.dj.nativeswing.NativeSwing;
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
import com.itextpdf.text.ZapfDingbatsList;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import conexion.conexion;
import conexion.conexionBusqueda;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.sourceforge.jbarcodebean.BarcodeException;
import net.sourceforge.jbarcodebean.model.Code128;
import net.sourceforge.jbarcodebean.model.Code39;
import net.sourceforge.jbarcodebean.model.Code93;
import net.sourceforge.jbarcodebean.model.Interleaved25;
/**
 * 
 * @author http://datojava.blogspot.com/
 *
 */
public class DJiTextEjemplo {
	public static void main(String[] args) throws BarcodeException {
		NativeInterface.open();
		// Creacion del documento con los margenes
		Document document = new Document(PageSize.LETTER, 35, 30, 50, 50);
		try {
			
			File directorio=new File("C:/6342522/temp/");
			if (!directorio.exists()) {
				directorio.mkdirs();
			} 
			// El archivo pdf que vamos a generar
			FileOutputStream fileOutputStream = new FileOutputStream("C:/6342522/temp/001.pdf");
			
			// Obtener la instancia del PdfWriter
			PdfWriter.getInstance(document, fileOutputStream);

			// Abrir el documento
			document.open();
				// Crear las fuentes para el contenido y los titulos
			
			Font fontTitulos = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL,BaseColor.BLACK);
			Font fontContenido = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL,BaseColor.BLACK);
			
			//preparamos el encabezado
			Image image = null;

			if (Colores.impimirEncabezadoListados) {
				image = Image.getInstance("fotoDJ.png");
			image.scaleAbsolute(80f, 60f);
			}else{
				   BufferedImage imageBuff = new BufferedImage(100, Colores.espacioEncabezadosListados, BufferedImage.TYPE_INT_RGB);
			        Graphics2D g = (Graphics2D)imageBuff.getGraphics();
			        g.setColor(Color.WHITE);
			        g.fillRect(0, 0,100, Colores.espacioEncabezadosListados);
			        ImageIcon IA=new ImageIcon(imageBuff);
			        image= Image.getInstance(IA.getImage(),null);
			}		
			PdfPTable table = new PdfPTable(1);
			PdfPCell cell = new PdfPCell(image);
			cell.setColspan(5);
			cell.setBorderColor(BaseColor.WHITE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);
			document.add(table);
		
			
			Paragraph paragraph = new Paragraph();
			paragraph.add(new Phrase(Chunk.NEWLINE));
			paragraph.add(new Phrase(Chunk.NEWLINE));
			
	
			// Datos de recepcion
	
		// Cargar codigo de barras
			Image codigo;
			JBarcodeBean barcode=new JBarcodeBean();
			barcode.setCodeType(new Code128());
			barcode.setCode("1354645");
			barcode.setLabelPosition(0);
			barcode.setCheckDigit(true);
			BufferedImage bufferedImage = barcode.draw(new BufferedImage(150, 10, BufferedImage.TYPE_INT_RGB));
			codigo=Image.getInstance(bufferedImage,null);
				document.add(codigo);
			
				paragraph.add(new Phrase(Chunk.NEWLINE));

			

				

			// Creacion del parrafo


			// Agregar un titulo con su respectiva fuente
			paragraph.add(new Phrase("Características:", fontTitulos));

			// Agregar saltos de linea
			paragraph.add(new Phrase(Chunk.NEWLINE));
			paragraph.add(new Phrase(Chunk.NEWLINE));

			// Agregar contenido con su respectiva fuente
			paragraph
					.add(new Phrase(
							"El sensor de la X-E1 presenta el mismo excelente rendimiento que el X-Trans CMOS "
									+ "de 16 megapíxeles del modelo superior de la serie X, la X-Pro1. Gracias la matriz "
									+ "de filtro de color con disposición aleatoria de los píxeles, desarrollada originalmente"
									+ " por Fujifilm, el sensor X-Trans CMOS elimina la necesidad del filtro óptico de paso bajo"
									+ " que se utiliza en los sistemas convencionales para inhibir el muaré a expensas de la"
									+ " resolución. Esta matriz innovadora permite al sensor X-Trans CMOS captar la luz sin filtrar"
									+ " del objetivo y obtener una resolución sin precedentes. La exclusiva disposición aleatoria de"
									+ " la matriz de filtro de color resulta asimismo muy eficaz para mejorar la separación de ruido"
									+ " en la fotografía de alta sensibilidad. Otra ventaja del gran sensor APS-C es la capacidad"
									+ " para crear un hermoso efecto “bokeh”, el estético efecto desenfocado que se crea al disparar"
									+ " con poca profundidad de campo.",
							fontContenido));

			paragraph.add(new Phrase(Chunk.NEWLINE));
			paragraph.add(new Phrase(Chunk.NEWLINE));
			paragraph.add(new Phrase(Chunk.NEWLINE));
			paragraph.add(new Phrase("Otras Caracaterísticas:", fontTitulos));

			// Agregar el parrafo al documento
			document.add(paragraph);

			// La lista final
			List listaFinal = new List(List.UNORDERED);
			ListItem listItem = new ListItem();
			List list = new List();

			// Crear sangria en la lista
			list.setListSymbol(new Chunk("   "));
			ListItem itemNuevo = new ListItem("   ");

			// ZapfDingbatsListm, lista con simbolos
			List listSymbol = new ZapfDingbatsList(51);

			// Agregar contenido a la lista
			listSymbol
					.add(new ListItem(
							"Sensor CMOS X-Trans – Consigue una calidad de imagen superior",
							fontContenido));
			listSymbol
					.add(new ListItem(
							"Visor electrónico OLED de 2,36 pulgadas de alta resolución y luminosidad",
							fontContenido));
			listSymbol.add(new ListItem("Diseño y accesorios", fontContenido));
			listSymbol.add(new ListItem("Rápida respuesta", fontContenido));

			itemNuevo.add(listSymbol);
			list.add(itemNuevo);
			listItem.add(list);

			// Agregar todo a la lista final
			listaFinal.add(listItem);

			// Agregar la lista
			for (int i = 0; i < 100; i++) {
					document.add(listaFinal);
			}
		

			paragraph = new Paragraph();
			paragraph.add(new Phrase(Chunk.NEWLINE));
			paragraph.add(new Phrase(Chunk.NEWLINE));
			document.add(paragraph);
			
			for (int i = 0; i < 10; i++) {
				// Crear tabla nueva con dos posiciones
				table = new PdfPTable(2);
				float[] longitudes = { 5.0f, 5.0f };

				// Establecer posiciones de celdas
				table.setWidths(longitudes);
				cell = new PdfPCell();

				// Cargar imagenes del producto y agregarlas
				image = Image.getInstance("fujifilmex1-2.png");
				image.scaleAbsolute(40f, 20f);
				table.getDefaultCell().setBorderColor(BaseColor.WHITE);
				table.addCell(image);
				if (i==5) {
					image = Image.getInstance("fotoDJ.png");
				image.scaleAbsolute(40f, 20f);
				table.addCell(image);
				}else{
					image = null;
					table.addCell(image);
				}
				

				// Agregar la tabla al documento
				document.add(table);
			}
			

			// Cerrar el documento
			document.close();

			// Abrir el archivo
			String  path = "C:/6342522/temp/001.pdf";
			new VisorPDF(path, "PDF de prueba");
			NativeInterface.runEventPump();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
