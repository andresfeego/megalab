package otrosImpresos;

import interfaces_Modificadas.Colores;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.layout.Border;

import javax.swing.ImageIcon;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import jbarcodebean.JBarcodeBean;
import metodos.ObtenerFecha;
import metodos.ObtenerFechaColombia;
import net.sourceforge.jbarcodebean.model.Code128;
import visor.VisorPDF;
import Interfaces.Principal;
import auxiliares.Bacteriologo;
import auxiliares.Examen;
import auxiliares.ItemRecepcion;
import auxiliares.Paciente;
import auxiliares.Recepcion;
import auxiliares.RecepcionCompleta;
import auxiliares.itemFactura;
import auxiliares.protocolo;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
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
import com.itextpdf.text.pdf.ColumnText;
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
public class imprimirReporte {
	private RecepcionCompleta RC;
	private Recepcion recepcion;
	private String queImprime;
	private Bacteriologo bacteriologo;
	private Document document;
	private boolean imprimir;
	private PdfWriter writer;
	private String nombrearchivo;
	private StringBuffer resultadoGraWBC;
	private StringBuffer resultadoGraRBC;
	private StringBuffer resultadoGraPLT;
	
	
	public imprimirReporte(RecepcionCompleta RC,boolean imprimir,String queImprime,Bacteriologo bacteriologo) {
		this.RC=RC;
		this.recepcion=RC.getRecepcion();
		this.imprimir=imprimir;
		this.queImprime=queImprime;
		this.bacteriologo=bacteriologo;
		// Creacion del documento con los margenes
		try {

			document = new Document(Colores.getRectangleItext(Colores.tamHojaListados), 35, 30, 50, 50);
			
				
				File directorio=new File("C:/6342522/temp/");
				if (!directorio.exists()) {
					directorio.mkdirs();
				} 
				nombrearchivo="MEGALABtemp"+Colores.getCadenaAlfanumAleatoria(7);
				FileOutputStream fileOutputStream = new FileOutputStream("C:/6342522/temp/"+nombrearchivo+".pdf");
				writer=PdfWriter.getInstance(document, fileOutputStream);
				        writer.setPageEvent(new FormatoDocumento());
		

			document.open();
				
			// Crear las fuentes y colores para el contenido y los titulos
			
		
			
			//preparamos el encabezado
			Image image = null;

			if (Colores.impimirEncabezadoListados) {
				image = Image.getInstance(Colores.enabezadoListados,null);
				image.scaleAbsolute(document.getPageSize().getWidth()-65,(((document.getPageSize().getWidth()-65)*image.getHeight())/image.getWidth()));
			}else{
				   BufferedImage imageBuff = new BufferedImage(100, Colores.espacioEncabezadosListados, BufferedImage.TYPE_INT_ARGB);
			        Graphics2D g = (Graphics2D)imageBuff.getGraphics();
			        g.setColor(Colores.clrTransparente);
			        g.fillRect(0, 0,100, Colores.espacioEncabezadosListados);
			        ImageIcon IA=new ImageIcon(imageBuff);
			        image= Image.getInstance(IA.getImage(),null);
			        
			}		
			document.add(image);
			
		// Cargar codigo de barras
			Paragraph numeroRecepcion = new Paragraph();
			numeroRecepcion.add(new Phrase("Fecha recepción "+recepcion.getFechaRecepcion(),Colores.fuenteContenidos));
			numeroRecepcion.add(new Phrase(Chunk.NEWLINE));
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
				recepcionadopor.add(new Phrase("Recepcionado por : "+recepcion.getRecepcionadoPor()+" || Informe generado el: "+(new ObtenerFechaColombia()).fechaColombia(),Colores.fuentePeque));
				recepcionadopor.add(new Phrase(Chunk.NEWLINE));
				recepcionadopor.add(new Phrase(Chunk.NEWLINE));
				document.add(recepcionadopor);
				
				// datos de la recpcion


				PdfPTable table = new PdfPTable(2);
				table.setWidthPercentage(49);
				table.setHorizontalAlignment(Element.ALIGN_LEFT);
				
				PdfPCell cell = new PdfPCell(new Phrase("Datos de paciente",Colores.fuenteContenidosBold));
				cell.setColspan(2);
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
				
				PdfPCell cellMedico = new PdfPCell(new Phrase("Medico: "+recepcion.getCodMedicoString(),Colores.fuenteContenidos));
				cellMedico.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cellMedico);
				
				PdfPCell cellEmpresa = new PdfPCell(new Phrase("Empresa: "+recepcion.getEmpresa().getRazonSocial(),Colores.fuenteContenidos));
				cellEmpresa.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cellEmpresa);
				
			
				
				PdfPTable tableCompletaR = new PdfPTable(1);
				tableCompletaR.setWidthPercentage(100);
				tableCompletaR.setHorizontalAlignment(Element.ALIGN_RIGHT);
				tableCompletaR.getDefaultCell().setBorderColor(Colores.colorBordesTablas);
				tableCompletaR.addCell(table);
				
				document.add(tableCompletaR);
				
				Paragraph separador1 = new Paragraph();
				separador1.add(new Phrase(Chunk.NEWLINE));
				document.add(separador1);
				
				PdfPTable tableDetalles = new PdfPTable(3);
				tableDetalles.setWidthPercentage(100);
				tableDetalles.getDefaultCell().setBorderColor(BaseColor.BLACK);
				tableDetalles.setHorizontalAlignment(Element.ALIGN_RIGHT);
				
				PdfPCell cellTituloDetalles = new PdfPCell(new Phrase("Detalles para esta recepción",Colores.fuenteContenidosBold));
				cellTituloDetalles.setBorderColor(Colores.colorBordesTablas);
				cellTituloDetalles.setColspan(3);
				cellTituloDetalles.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableDetalles.addCell(cellTituloDetalles);
				
				PdfPCell cellTituloItem = new PdfPCell(new Phrase("Item",Colores.fuenteContenidosBold));
				cellTituloItem.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableDetalles.addCell(cellTituloItem);
				
				PdfPCell cellTituloValor= new PdfPCell(new Phrase("Valor",Colores.fuenteContenidosBold));
				cellTituloValor.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableDetalles.addCell(cellTituloValor);
				
				PdfPCell cellTituloReferencia = new PdfPCell(new Phrase("Valor de referencia o normal",Colores.fuenteContenidosBold));
				cellTituloReferencia.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableDetalles.addCell(cellTituloReferencia);
				document.add(tableDetalles);
				
				Paragraph separador2 = new Paragraph();
				separador2.add(new Phrase(Chunk.NEWLINE));
				document.add(separador2);
				
		System.out.println("Que imprime"+queImprime);
				
				if (queImprime.equals("0")) {
					agregarTodosExa();
				} else {
					if (queImprime.equals("1")) {
						agregarExaNORep();
					} else {
						if (queImprime.equals("2")) {
							agregarExaRep();
						} else {
								agregarExaXCod();
						}
					}
				}
				
				
				
		   if (bacteriologo!=null) {
			     java.awt.Image Firma=bacteriologo.getFirma();

	        	   BufferedImage imageBuffFirma = new BufferedImage(Firma.getWidth(null), Firma.getHeight(null)+50, BufferedImage.TYPE_INT_ARGB);
			        Graphics2D gf = (Graphics2D)imageBuffFirma.getGraphics();
					gf.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			        gf.drawImage(Firma, 0, 0, Firma.getWidth(null), Firma.getHeight(null),null);
			        gf.setStroke(new BasicStroke(1f));
			        gf.setColor(new Color(10,10,10));
			        gf.drawLine(0,Firma.getHeight(null)-20, Firma.getWidth(null),Firma.getHeight(null)-20);
			        gf.drawString("Bacteriologo: "+bacteriologo.getNombres()+" "+bacteriologo.getApellidos(), 0, Firma.getHeight(null));
			        ImageIcon IAf=new ImageIcon(imageBuffFirma);
			       Image firma = Image.getInstance(IAf.getImage(),null);
			           
			           float altof=(150*Firma.getHeight(null))/Firma.getWidth(null);
			           firma.scaleAbsolute(150, altof);
			           
				firma.setAbsolutePosition((document.getPageSize().getWidth()/2)-(150/2), 80f);
      		document.add(firma);
      		
				    	Paragraph lilneaTexto = new Paragraph();
				    	lilneaTexto.add(new Phrase("__________________________",Colores.fuentePeque));
				    	Paragraph FirmaTexto = new Paragraph();
				    	FirmaTexto.add(new Phrase("                                       Bacteriologo: "+bacteriologo.getNombres()+" "+bacteriologo.getApellidos(),Colores.fuenteContenidos));
				  
			
		}
		

			// Cerrar el documento
			document.close();

			// Abrir el archivo
			
			if (imprimir) {
				new imprimirPredeterminada("C:/6342522/temp/"+nombrearchivo+".pdf",Colores.impresoraListados);
			    Principal.getInstancePrincipal().registrarAccion("Impresión de reporte de resultados");

			} else {
				String  path ="C:/6342522/temp/"+nombrearchivo+".pdf";
				NativeInterface.open();
				(new VisorPDF(path, "Recepcion # "+String.format("%05d", recepcion.getIdREcepcion()))).execute();
			    Principal.getInstancePrincipal().registrarAccion("Creación de reporte de resultados PDF");

			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}



	

	}
	
	
	public void agregarTodosExa(){
		ArrayList<itemFactura> AE=RC.getItemsFactura();

		for (int i = 0; i < AE.size(); i++) {
			itemFactura IF=AE.get(i);
			conexion.getInstance().reportarImpreso(this, IF);

			Examen exa=conexionBusqueda.getInstance().examenXcodigo(IF.getCodExamen());
			float[] medidaCeldas = {2.25f, 1.5f, 1.5f};
			PdfPTable tableExamenes = new PdfPTable(3);
			tableExamenes.setWidthPercentage(100);
			try {
				tableExamenes.setWidths(medidaCeldas);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tableExamenes.setHorizontalAlignment(Element.ALIGN_RIGHT);
			String observa="";
			if (!IF.getObservaciones().equals("")) {
				observa=" * "+IF.getObservaciones();
			}
			PdfPCell cellTitulocodExamenT = new PdfPCell(new Phrase(exa.getCups()+" • "+exa.getNombre()+observa,Colores.fuenteContenidosBold));
			cellTitulocodExamenT.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellTitulocodExamenT.setBorderColor(BaseColor.BLACK);
			cellTitulocodExamenT.setColspan(2);
			tableExamenes.addCell(cellTitulocodExamenT);
			
			PdfPCell cellTituloSeccion = new PdfPCell(new Phrase(conexionBusqueda.getInstance().seccionXid((conexionBusqueda.getInstance().protocoloXid(exa.getCodProtocolo()+"")).getCodSeccion()).getNombre()+"",Colores.fuenteContenidosBold));
			cellTituloSeccion.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellTituloSeccion.setColspan(2);
			cellTituloSeccion.setBorderColor(BaseColor.BLACK);
			tableExamenes.addCell(cellTituloSeccion);

			ArrayList<ItemRecepcion> items=IF.getItemsExamenes();
			
		for (int j = 0; j < items.size(); j++) {
				ItemRecepcion IR=items.get(j);
			if (!IR.getConcepto().equals("Gráfica WBC")&&!IR.getConcepto().equals("Gráfica RBC")&&!IR.getConcepto().equals("Gráfica PLT")) {

				String inter="";
			if (!IR.getInterpretacion().equals("")) {
				inter=" * \n"+ IR.getInterpretacion();
			}
				
				PdfPCell cellTituloConcepto = new PdfPCell(new Phrase(IR.getConcepto()+inter,Colores.fuenteContenidos));
				cellTituloConcepto.setHorizontalAlignment(Element.ALIGN_LEFT);
				cellTituloConcepto.setBorder(Rectangle.BOTTOM);
				cellTituloConcepto.setBorderColor(Colores.colorBordesTablas);
				tableExamenes.addCell(cellTituloConcepto);
				
				
				String auxiResultado = "";
				if (!IR.getValorResultado().equals("-1")) {
					auxiResultado = IR.getValorResultado();
				}
				
				
				if (IR.getTipoDato()==3) {
					PdfPCell cellTituloResultado = new PdfPCell(new Phrase(auxiResultado+" "+ IR.getUnidadMedida(),Colores.fuenteContenidos));
					cellTituloResultado.setHorizontalAlignment(Element.ALIGN_CENTER);
					cellTituloResultado.setBorder(Rectangle.BOTTOM);
					cellTituloResultado.setBorderColor(Colores.colorBordesTablas);
					tableExamenes.addCell(cellTituloResultado);
					
					PdfPCell cellTituloValorRefNorm = new PdfPCell(new Phrase(IR.getDesde()+" a "+IR.getHasta()+"  "+IR.getUnidadMedida(),Colores.fuenteContenidos));
					cellTituloValorRefNorm.setHorizontalAlignment(Element.ALIGN_CENTER);
					cellTituloValorRefNorm.setBorder(Rectangle.BOTTOM);
					cellTituloValorRefNorm.setBorderColor(Colores.colorBordesTablas);
					tableExamenes.addCell(cellTituloValorRefNorm);
				} else {
					if (IR.getTipoDato()==1||IR.getTipoDato()==2 ||IR.getTipoDato()==45) {
						PdfPCell cellTituloResultado = new PdfPCell(new Phrase("",Colores.fuenteContenidos));
						cellTituloResultado.setHorizontalAlignment(Element.ALIGN_CENTER);
						cellTituloResultado.setBorder(Rectangle.BOTTOM);
						cellTituloResultado.setBorderColor(Colores.colorBordesTablas);
						tableExamenes.addCell(cellTituloResultado);
						
						PdfPCell cellTituloValorRefNorm = new PdfPCell(new Phrase("",Colores.fuenteContenidos));
						cellTituloValorRefNorm.setHorizontalAlignment(Element.ALIGN_CENTER);
						cellTituloValorRefNorm.setBorder(Rectangle.BOTTOM);
						cellTituloValorRefNorm.setBorderColor(Colores.colorBordesTablas);
						tableExamenes.addCell(cellTituloValorRefNorm);
					} else {
						PdfPCell cellTituloResultado = new PdfPCell(new Phrase(auxiResultado,Colores.fuenteContenidos));
						cellTituloResultado.setHorizontalAlignment(Element.ALIGN_CENTER);
						cellTituloResultado.setBorder(Rectangle.BOTTOM);
						cellTituloResultado.setBorderColor(Colores.colorBordesTablas);
						tableExamenes.addCell(cellTituloResultado);
						
						PdfPCell cellTituloValorRefNorm = new PdfPCell(new Phrase(IR.getValorNormal(),Colores.fuenteContenidos));
						cellTituloValorRefNorm.setHorizontalAlignment(Element.ALIGN_CENTER);
						cellTituloValorRefNorm.setBorder(Rectangle.BOTTOM);
						cellTituloValorRefNorm.setBorderColor(Colores.colorBordesTablas);
						tableExamenes.addCell(cellTituloValorRefNorm);
					}

				}
				
			
			}else{
				String[] cadena=IR.getValorResultado().split(",");
	
					Image grafica;
					try {
						grafica = Image.getInstance(creaGrafica(cadena,IR.getConcepto()),null);		
						
						float[] medidaCeldas1 = {1.5f, 1.5f, 1.5f};
						tableExamenes.setWidths(medidaCeldas1);
						
						tableExamenes.addCell(grafica);
						
						float[] medidaCeldas2 = {2.25f, 1.5f, 1.5f};
						tableExamenes.setWidths(medidaCeldas2);

					} catch (BadElementException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
			}
		}
		try {
			document.add(tableExamenes);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Paragraph separador3 = new Paragraph();
		separador3.add(new Phrase(Chunk.NEWLINE));
		try {
			document.add(separador3);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		}
	}
	
	public void agregarExaRep(){
		ArrayList<itemFactura> AE=RC.getItemsFactura();

		for (int i = 0; i < AE.size(); i++) {
			itemFactura IF=AE.get(i);
			
			conexion.getInstance().reportarImpreso(this, IF);
			if (IF.getReportado()==1) {
				Examen exa=conexionBusqueda.getInstance().examenXcodigo(IF.getCodExamen());
				float[] medidaCeldas = {2.25f, 1.5f, 1.5f};
				PdfPTable tableExamenes = new PdfPTable(3);
				tableExamenes.setWidthPercentage(100);
				try {
					tableExamenes.setWidths(medidaCeldas);
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tableExamenes.setHorizontalAlignment(Element.ALIGN_RIGHT);
				
				PdfPCell cellTitulocodExamenT = new PdfPCell(new Phrase(exa.getCups()+" • "+exa.getNombre(),Colores.fuenteContenidosBold));
				cellTitulocodExamenT.setHorizontalAlignment(Element.ALIGN_LEFT);
				cellTitulocodExamenT.setBorderColor(BaseColor.BLACK);
				cellTitulocodExamenT.setColspan(2);
				tableExamenes.addCell(cellTitulocodExamenT);
				
				PdfPCell cellTituloSeccion = new PdfPCell(new Phrase(conexionBusqueda.getInstance().seccionXid((conexionBusqueda.getInstance().protocoloXid(exa.getCodProtocolo()+"")).getCodSeccion()).getNombre()+"",Colores.fuenteContenidosBold));
				cellTituloSeccion.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cellTituloSeccion.setColspan(2);
				cellTituloSeccion.setBorderColor(BaseColor.BLACK);
				tableExamenes.addCell(cellTituloSeccion);

				ArrayList<ItemRecepcion> items=IF.getItemsExamenes();
				
			for (int j = 0; j < items.size(); j++) {
				ItemRecepcion IR=items.get(j);
				if (!IR.getConcepto().equals("Gráfica WBC")&&!IR.getConcepto().equals("Gráfica RBC")&&!IR.getConcepto().equals("Gráfica PLT")) {
					
					if (IR.getReportado()==1||IR.getTipoDato()==1||IR.getTipoDato()==2||IR.getTipoDato()==45) {

					
					PdfPCell cellTituloConcepto = new PdfPCell(new Phrase(IR.getConcepto(),Colores.fuenteContenidos));
					cellTituloConcepto.setHorizontalAlignment(Element.ALIGN_LEFT);
					cellTituloConcepto.setBorder(Rectangle.BOTTOM);
					cellTituloConcepto.setBorderColor(Colores.colorBordesTablas);
					tableExamenes.addCell(cellTituloConcepto);
					
					
					String auxiResultado = "";
					if (!IR.getValorResultado().equals("-1")) {
						auxiResultado = IR.getValorResultado();
					}
					
					
					if (IR.getTipoDato()==3) {
						PdfPCell cellTituloResultado = new PdfPCell(new Phrase(auxiResultado+" "+ IR.getUnidadMedida(),Colores.fuenteContenidos));
						cellTituloResultado.setHorizontalAlignment(Element.ALIGN_CENTER);
						cellTituloResultado.setBorder(Rectangle.BOTTOM);
						cellTituloResultado.setBorderColor(Colores.colorBordesTablas);
						tableExamenes.addCell(cellTituloResultado);
						
						PdfPCell cellTituloValorRefNorm = new PdfPCell(new Phrase(IR.getDesde()+" a "+IR.getHasta()+" "+IR.getUnidadMedida(),Colores.fuenteContenidos));
						cellTituloValorRefNorm.setHorizontalAlignment(Element.ALIGN_CENTER);
						cellTituloValorRefNorm.setBorder(Rectangle.BOTTOM);
						cellTituloValorRefNorm.setBorderColor(Colores.colorBordesTablas);
						tableExamenes.addCell(cellTituloValorRefNorm);
					} else {
						if (IR.getTipoDato()==1||IR.getTipoDato()==2 ||IR.getTipoDato()==45) {
							PdfPCell cellTituloResultado = new PdfPCell(new Phrase("",Colores.fuenteContenidos));
							cellTituloResultado.setHorizontalAlignment(Element.ALIGN_CENTER);
							cellTituloResultado.setBorder(Rectangle.BOTTOM);
							cellTituloResultado.setBorderColor(Colores.colorBordesTablas);
							tableExamenes.addCell(cellTituloResultado);
							
							PdfPCell cellTituloValorRefNorm = new PdfPCell(new Phrase("",Colores.fuenteContenidos));
							cellTituloValorRefNorm.setHorizontalAlignment(Element.ALIGN_CENTER);
							cellTituloValorRefNorm.setBorder(Rectangle.BOTTOM);
							cellTituloValorRefNorm.setBorderColor(Colores.colorBordesTablas);
							tableExamenes.addCell(cellTituloValorRefNorm);
						} else {
							PdfPCell cellTituloResultado = new PdfPCell(new Phrase(auxiResultado,Colores.fuenteContenidos));
							cellTituloResultado.setHorizontalAlignment(Element.ALIGN_CENTER);
							cellTituloResultado.setBorder(Rectangle.BOTTOM);
							cellTituloResultado.setBorderColor(Colores.colorBordesTablas);
							tableExamenes.addCell(cellTituloResultado);
							
							PdfPCell cellTituloValorRefNorm = new PdfPCell(new Phrase(IR.getValorNormal(),Colores.fuenteContenidos));
							cellTituloValorRefNorm.setHorizontalAlignment(Element.ALIGN_CENTER);
							cellTituloValorRefNorm.setBorder(Rectangle.BOTTOM);
							cellTituloValorRefNorm.setBorderColor(Colores.colorBordesTablas);
							tableExamenes.addCell(cellTituloValorRefNorm);
						}

					}
					
				
				}
			}else{
				String[] cadena=IR.getValorResultado().split(",");
				
				Image grafica;
				try {
					grafica = Image.getInstance(creaGrafica(cadena,IR.getConcepto()),null);		
					
					float[] medidaCeldas1 = {1.5f, 1.5f, 1.5f};
					tableExamenes.setWidths(medidaCeldas1);
					
					tableExamenes.addCell(grafica);
					
					float[] medidaCeldas2 = {2.25f, 1.5f, 1.5f};
					tableExamenes.setWidths(medidaCeldas2);

				} catch (BadElementException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		}
			}
			try {
				document.add(tableExamenes);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Paragraph separador3 = new Paragraph();
			separador3.add(new Phrase(Chunk.NEWLINE));
			try {
				document.add(separador3);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			}
		
		}
	}
	
	public void agregarExaNORep(){
		ArrayList<itemFactura> AE=RC.getItemsFactura();

		for (int i = 0; i < AE.size(); i++) {
			itemFactura IF=AE.get(i);
			
			conexion.getInstance().reportarImpreso(this, IF);


			Examen exa=conexionBusqueda.getInstance().examenXcodigo(IF.getCodExamen());
			float[] medidaCeldas = {2.25f, 1.5f, 1.5f};
			PdfPTable tableExamenes = new PdfPTable(3);
			tableExamenes.setWidthPercentage(100);
			try {
				tableExamenes.setWidths(medidaCeldas);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tableExamenes.setHorizontalAlignment(Element.ALIGN_RIGHT);
			
			PdfPCell cellTitulocodExamenT = new PdfPCell(new Phrase(exa.getCups()+" • "+exa.getNombre(),Colores.fuenteContenidosBold));
			cellTitulocodExamenT.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellTitulocodExamenT.setBorderColor(BaseColor.BLACK);
			cellTitulocodExamenT.setColspan(2);
			tableExamenes.addCell(cellTitulocodExamenT);
			
			PdfPCell cellTituloSeccion = new PdfPCell(new Phrase(conexionBusqueda.getInstance().seccionXid((conexionBusqueda.getInstance().protocoloXid(exa.getCodProtocolo()+"")).getCodSeccion()).getNombre()+"",Colores.fuenteContenidosBold));
			cellTituloSeccion.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellTituloSeccion.setColspan(2);
			cellTituloSeccion.setBorderColor(BaseColor.BLACK);
			tableExamenes.addCell(cellTituloSeccion);

			ArrayList<ItemRecepcion> items=IF.getItemsExamenes();
			
		for (int j = 0; j < items.size(); j++) {
								ItemRecepcion IR=items.get(j);
								if (!IR.getConcepto().equals("Gráfica WBC")&&!IR.getConcepto().equals("Gráfica RBC")&&!IR.getConcepto().equals("Gráfica PLT")) {
			if (IR.getReportado()==0||IR.getTipoDato()==1||IR.getTipoDato()==2||IR.getTipoDato()==45) {

				
				PdfPCell cellTituloConcepto = new PdfPCell(new Phrase(IR.getConcepto(),Colores.fuenteContenidos));
				cellTituloConcepto.setHorizontalAlignment(Element.ALIGN_LEFT);
				cellTituloConcepto.setBorder(Rectangle.BOTTOM);
				cellTituloConcepto.setBorderColor(Colores.colorBordesTablas);
				tableExamenes.addCell(cellTituloConcepto);
				
				
				String auxiResultado = "";
				if (!IR.getValorResultado().equals("-1")) {
					auxiResultado = IR.getValorResultado();
				}
				
				
				if (IR.getTipoDato()==3) {
					PdfPCell cellTituloResultado = new PdfPCell(new Phrase(auxiResultado+" "+ IR.getUnidadMedida(),Colores.fuenteContenidos));
					cellTituloResultado.setHorizontalAlignment(Element.ALIGN_CENTER);
					cellTituloResultado.setBorder(Rectangle.BOTTOM);
					cellTituloResultado.setBorderColor(Colores.colorBordesTablas);
					tableExamenes.addCell(cellTituloResultado);
					
					PdfPCell cellTituloValorRefNorm = new PdfPCell(new Phrase(IR.getDesde()+" a "+IR.getHasta()+" "+IR.getUnidadMedida(),Colores.fuenteContenidos));
					cellTituloValorRefNorm.setHorizontalAlignment(Element.ALIGN_CENTER);
					cellTituloValorRefNorm.setBorder(Rectangle.BOTTOM);
					cellTituloValorRefNorm.setBorderColor(Colores.colorBordesTablas);
					tableExamenes.addCell(cellTituloValorRefNorm);
				} else {
					if (IR.getTipoDato()==1||IR.getTipoDato()==2 ||IR.getTipoDato()==45) {
						PdfPCell cellTituloResultado = new PdfPCell(new Phrase("",Colores.fuenteContenidos));
						cellTituloResultado.setHorizontalAlignment(Element.ALIGN_CENTER);
						cellTituloResultado.setBorder(Rectangle.BOTTOM);
						cellTituloResultado.setBorderColor(Colores.colorBordesTablas);
						tableExamenes.addCell(cellTituloResultado);
						
						PdfPCell cellTituloValorRefNorm = new PdfPCell(new Phrase("",Colores.fuenteContenidos));
						cellTituloValorRefNorm.setHorizontalAlignment(Element.ALIGN_CENTER);
						cellTituloValorRefNorm.setBorder(Rectangle.BOTTOM);
						cellTituloValorRefNorm.setBorderColor(Colores.colorBordesTablas);
						tableExamenes.addCell(cellTituloValorRefNorm);
					} else {
						PdfPCell cellTituloResultado = new PdfPCell(new Phrase(auxiResultado,Colores.fuenteContenidos));
						cellTituloResultado.setHorizontalAlignment(Element.ALIGN_CENTER);
						cellTituloResultado.setBorder(Rectangle.BOTTOM);
						cellTituloResultado.setBorderColor(Colores.colorBordesTablas);
						tableExamenes.addCell(cellTituloResultado);
						
						PdfPCell cellTituloValorRefNorm = new PdfPCell(new Phrase(IR.getValorNormal(),Colores.fuenteContenidos));
						cellTituloValorRefNorm.setHorizontalAlignment(Element.ALIGN_CENTER);
						cellTituloValorRefNorm.setBorder(Rectangle.BOTTOM);
						cellTituloValorRefNorm.setBorderColor(Colores.colorBordesTablas);
						tableExamenes.addCell(cellTituloValorRefNorm);
					}

				}
				
			
			}else{
				String[] cadena=IR.getValorResultado().split(",");
				
				Image grafica;
				try {
					grafica = Image.getInstance(creaGrafica(cadena,IR.getConcepto()),null);		
					
					float[] medidaCeldas1 = {1.5f, 1.5f, 1.5f};
					tableExamenes.setWidths(medidaCeldas1);
					
					tableExamenes.addCell(grafica);
					
					float[] medidaCeldas2 = {2.25f, 1.5f, 1.5f};
					tableExamenes.setWidths(medidaCeldas2);

				} catch (BadElementException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		}
			}
		}
		try {
			document.add(tableExamenes);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Paragraph separador3 = new Paragraph();
		separador3.add(new Phrase(Chunk.NEWLINE));
		try {
			document.add(separador3);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		
		}
	}
	
	public void agregarExaXCod(){
		ArrayList<itemFactura> AE=RC.getItemsFactura();

		for (int i = 0; i < AE.size(); i++) {
			itemFactura IF=AE.get(i);
			
			conexion.getInstance().reportarImpreso(this, IF);

			if (IF.getCodExamen().equals(queImprime)) {
				Examen exa=conexionBusqueda.getInstance().examenXcodigo(IF.getCodExamen());
				float[] medidaCeldas = {2.25f, 1.5f, 1.5f};
				PdfPTable tableExamenes = new PdfPTable(3);
				tableExamenes.setWidthPercentage(100);
				try {
					tableExamenes.setWidths(medidaCeldas);
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tableExamenes.setHorizontalAlignment(Element.ALIGN_RIGHT);
				
				PdfPCell cellTitulocodExamenT = new PdfPCell(new Phrase(exa.getCups()+" • "+exa.getNombre(),Colores.fuenteContenidosBold));
				cellTitulocodExamenT.setHorizontalAlignment(Element.ALIGN_LEFT);
				cellTitulocodExamenT.setBorderColor(BaseColor.BLACK);
				cellTitulocodExamenT.setColspan(2);
				tableExamenes.addCell(cellTitulocodExamenT);
				
				PdfPCell cellTituloSeccion = new PdfPCell(new Phrase(conexionBusqueda.getInstance().seccionXid((conexionBusqueda.getInstance().protocoloXid(exa.getCodProtocolo()+"")).getCodSeccion()).getNombre()+"",Colores.fuenteContenidosBold));
				cellTituloSeccion.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cellTituloSeccion.setColspan(2);
				cellTituloSeccion.setBorderColor(BaseColor.BLACK);
				tableExamenes.addCell(cellTituloSeccion);

				ArrayList<ItemRecepcion> items=IF.getItemsExamenes();
				
			for (int j = 0; j < items.size(); j++) {
				ItemRecepcion IR=items.get(j);
				if (!IR.getConcepto().equals("Gráfica WBC")&&!IR.getConcepto().equals("Gráfica RBC")&&!IR.getConcepto().equals("Gráfica PLT")) {
				PdfPCell cellTituloConcepto = new PdfPCell(new Phrase(IR.getConcepto(),Colores.fuenteContenidos));
				cellTituloConcepto.setHorizontalAlignment(Element.ALIGN_LEFT);
				cellTituloConcepto.setBorder(Rectangle.BOTTOM);
				cellTituloConcepto.setBorderColor(Colores.colorBordesTablas);
				tableExamenes.addCell(cellTituloConcepto);
				
				
				String auxiResultado = "";
				if (!IR.getValorResultado().equals("-1")) {
					auxiResultado = IR.getValorResultado();
				}
				
				
				if (IR.getTipoDato()==3) {
					PdfPCell cellTituloResultado = new PdfPCell(new Phrase(auxiResultado+" "+ IR.getUnidadMedida(),Colores.fuenteContenidos));
					cellTituloResultado.setHorizontalAlignment(Element.ALIGN_CENTER);
					cellTituloResultado.setBorder(Rectangle.BOTTOM);
					cellTituloResultado.setBorderColor(Colores.colorBordesTablas);
					tableExamenes.addCell(cellTituloResultado);
					
					PdfPCell cellTituloValorRefNorm = new PdfPCell(new Phrase(IR.getDesde()+" a "+IR.getHasta()+" "+IR.getUnidadMedida(),Colores.fuenteContenidos));
					cellTituloValorRefNorm.setHorizontalAlignment(Element.ALIGN_CENTER);
					cellTituloValorRefNorm.setBorder(Rectangle.BOTTOM);
					cellTituloValorRefNorm.setBorderColor(Colores.colorBordesTablas);
					tableExamenes.addCell(cellTituloValorRefNorm);
				} else {
					if (IR.getTipoDato()==1||IR.getTipoDato()==2 ||IR.getTipoDato()==45) {
						PdfPCell cellTituloResultado = new PdfPCell(new Phrase("",Colores.fuenteContenidos));
						cellTituloResultado.setHorizontalAlignment(Element.ALIGN_CENTER);
						cellTituloResultado.setBorder(Rectangle.BOTTOM);
						cellTituloResultado.setBorderColor(Colores.colorBordesTablas);
						tableExamenes.addCell(cellTituloResultado);
						
						PdfPCell cellTituloValorRefNorm = new PdfPCell(new Phrase("",Colores.fuenteContenidos));
						cellTituloValorRefNorm.setHorizontalAlignment(Element.ALIGN_CENTER);
						cellTituloValorRefNorm.setBorder(Rectangle.BOTTOM);
						cellTituloValorRefNorm.setBorderColor(Colores.colorBordesTablas);
						tableExamenes.addCell(cellTituloValorRefNorm);
					} else {
						PdfPCell cellTituloResultado = new PdfPCell(new Phrase(auxiResultado,Colores.fuenteContenidos));
						cellTituloResultado.setHorizontalAlignment(Element.ALIGN_CENTER);
						cellTituloResultado.setBorder(Rectangle.BOTTOM);
						cellTituloResultado.setBorderColor(Colores.colorBordesTablas);
						tableExamenes.addCell(cellTituloResultado);
						
						PdfPCell cellTituloValorRefNorm = new PdfPCell(new Phrase(IR.getValorNormal(),Colores.fuenteContenidos));
						cellTituloValorRefNorm.setHorizontalAlignment(Element.ALIGN_CENTER);
						cellTituloValorRefNorm.setBorder(Rectangle.BOTTOM);
						cellTituloValorRefNorm.setBorderColor(Colores.colorBordesTablas);
						tableExamenes.addCell(cellTituloValorRefNorm);
					}

				}
			}else{
				String[] cadena=IR.getValorResultado().split(",");
				
				Image grafica;
				try {
					grafica = Image.getInstance(creaGrafica(cadena,IR.getConcepto()),null);		
					
					float[] medidaCeldas1 = {1.5f, 1.5f, 1.5f};
					tableExamenes.setWidths(medidaCeldas1);
					
					tableExamenes.addCell(grafica);
					
					float[] medidaCeldas2 = {2.25f, 1.5f, 1.5f};
					tableExamenes.setWidths(medidaCeldas2);

				} catch (BadElementException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		}
			}
			try {
				document.add(tableExamenes);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Paragraph separador3 = new Paragraph();
			separador3.add(new Phrase(Chunk.NEWLINE));
			try {
				document.add(separador3);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			}
		
		}
	}
	
	
	public java.awt.Image creaGrafica(String[] cadena,String label)
	{try {



		resultadoGraWBC=new StringBuffer();
	    XYSeries series = new XYSeries("");
		
	    for (int i = 0; i <cadena.length ; i++) {
	    	series.add(Double.parseDouble(""+i), Double.parseDouble(cadena[i]));
		}
	    
	    XYDataset juegoDatos= new XYSeriesCollection(series);
	    
	    JFreeChart chart = ChartFactory.createXYLineChart        (label,"fL","V",juegoDatos,PlotOrientation.VERTICAL, false,false,false);
	   XYPlot plot= chart.getXYPlot();
	   plot.getRenderer().setSeriesPaint(0, Colores.clrAlertaCamarada);
	   BufferedImage image = chart.createBufferedImage(320,170);
	    
	    return image;



	} catch (Exception e) {
		System.err.println(e);
		return null;
	}}



}
	