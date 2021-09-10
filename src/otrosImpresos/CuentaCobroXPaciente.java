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
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javafx.scene.layout.Border;

import javax.swing.ImageIcon;

import jbarcodebean.JBarcodeBean;
import metodos.ObtenerFecha;
import metodos.ObtenerFechaColombia;
import net.sourceforge.jbarcodebean.model.Code128;
import visor.VisorPDF;
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
public class CuentaCobroXPaciente {
	private RecepcionCompleta RC;
	private Recepcion recepcion;
	private boolean imprimir;
	private String nombrearchivo;
	private Paciente paciente;
	private boolean detallado;
	private int saldo;
	private Bacteriologo bacteriologo;
	private ArrayList<DatosAbono> items;
	
	private Empresa empresa=conexionBusqueda.getInstance().empresaXid(""+0);
	
	private ArrayList<itemCuenta> itemsCuenta=new ArrayList<itemCuenta>();
	private int contadorSeervicios=0;
	private int contadorGranTotal=0;
	private int contadorGranTotalSaldos=0;
	private int contadorGranTotalAbonos=0;

	
	
	public CuentaCobroXPaciente(Paciente paciente,ArrayList<DatosAbono> items,int saldo,boolean imprimir,boolean detallado,Bacteriologo bacteriologo) {
		this.imprimir=imprimir;
		this.paciente=paciente;
		this.detallado=detallado;
		this.saldo=saldo;
		this.bacteriologo=bacteriologo;
		this.items=items;
		// Creacion del documento con los margenes
		Document document = new Document(Colores.getRectangleItext(Colores.tamHojaListados), 35, 30, 50, 50);
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
				
				
			//preparamos el EncabezadoListados
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
			numeroRecepcion.add(new Phrase("Cuenta de cobro  # "+String.format("%05d", (conexionBusqueda.getInstance().ultimaCuentaCobro()+1)),Colores.fuenteTitulos));
			Image codigo;
			JBarcodeBean barcode=new JBarcodeBean();
			barcode.setCodeType(new Code128());
			barcode.setCode((conexionBusqueda.getInstance().ultimaCuentaCobro()+1)+"");
			barcode.setLabelPosition(0);
			barcode.setCheckDigit(true);
			BufferedImage bufferedImage = barcode.draw(new BufferedImage(100, 20, BufferedImage.TYPE_INT_RGB));
			codigo=Image.getInstance(bufferedImage,null);
			document.add(numeroRecepcion);
				document.add(codigo);

				Paragraph recepcionadopor = new Paragraph();
				recepcionadopor.add(new Phrase("Informe generado el : "+(new ObtenerFechaColombia()).fechaColombia(),Colores.fuentePeque));
				recepcionadopor.add(new Phrase(Chunk.NEWLINE));
				recepcionadopor.add(new Phrase(Chunk.NEWLINE));
				document.add(recepcionadopor);
				
				// datos de la recpcion

				Paragraph cliente = new Paragraph();
				cliente.setAlignment(Element.ALIGN_CENTER);
				cliente.add(new Phrase(paciente.getNombres().toUpperCase()+" "+paciente.getApellidos().toUpperCase(),Colores.fuenteTitulosBold));
				cliente.add(new Phrase(Chunk.NEWLINE));
				cliente.add(new Phrase(paciente.tipoDocString()+" "+paciente.getId(),Colores.fuenteContenidos));
				cliente.add(new Phrase(Chunk.NEWLINE));
				cliente.add(new Phrase("DEDE A: ",Colores.fuenteTitulosBold));
				cliente.add(new Phrase(Chunk.NEWLINE));
				cliente.add(new Phrase(Chunk.NEWLINE));
				MiLaboratorio milab=conexionBusqueda.getInstance().miLab();
				cliente.add(new Phrase(Chunk.NEWLINE));
				cliente.add(new Phrase(milab.getRazonSocial().toUpperCase(),Colores.fuenteTitulosBold));
				cliente.add(new Phrase(Chunk.NEWLINE));
				cliente.add(new Phrase("Nit: "+milab.getNumeroDoc(),Colores.fuenteContenidos));
				cliente.add(new Phrase(Chunk.NEWLINE));
				cliente.add(new Phrase(Chunk.NEWLINE));
				cliente.add(new Phrase("LA SUMA DE : $"+saldo,Colores.fuenteTitulosBold));
				cliente.add(new Phrase(Chunk.NEWLINE));
				cliente.add(new Phrase(Chunk.NEWLINE));

				document.add(cliente);
				
				Paragraph parrafo = new Paragraph();
				parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
				parrafo.add(new Phrase("POR CONCEPTO DE : ",Colores.fuenteTitulosBold));
				SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
				
				parrafo.add(new Phrase(" examenes de laboratorio clinico, servicios prestados en nuestras instalaciónes y facturados en "+items.size()+"  recepciones comprendidas entre las fechas "+formatter.format(items.get(0).getFechaYHora())+" y "+formatter.format(items.get(items.size()-1).getFechaYHora())+".",Colores.fuenteContenidos));
				 
				
			
				
				if (this.detallado) {
					parrafo.add(new Phrase(Chunk.NEWLINE));
					parrafo.add(new Phrase(Chunk.NEWLINE));
				parrafo.add(new Phrase("Las cuales seran relacionadas en el anexo 1 de esta cuenta de cobro.",Colores.fuenteContenidos));
				
				
				}
				document.add(parrafo);
				
				
				if (this.bacteriologo!=null) {
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
				           
					firma.setAbsolutePosition((document.getPageSize().getWidth()/2)-(150/2), 10f);
	      		document.add(firma);
	      		
					    	Paragraph lilneaTexto = new Paragraph();
					    	lilneaTexto.add(new Phrase("__________________________",Colores.fuentePeque));
					    	Paragraph FirmaTexto = new Paragraph();
					    	FirmaTexto.add(new Phrase("                                       Bacteriologo: "+bacteriologo.getNombres()+" "+bacteriologo.getApellidos(),Colores.fuenteContenidos));
					  
				
			}
			
				

				if (this.detallado) {
					document.newPage();
				
							
					Paragraph Anexo = new Paragraph();
					Anexo.setAlignment(Element.ALIGN_JUSTIFIED);
					Anexo.add(new Phrase("ANEXO 1 - Detalles de cuenta de cobro número "+(conexionBusqueda.getInstance().ultimaCuentaCobro()+1)+" para el paciente "+paciente.nombresCompletos(),Colores.fuenteTitulosBold));
					document.add(Anexo);
					
					Paragraph sep1 = new Paragraph();
					sep1.add(new Phrase(Chunk.NEWLINE));
					sep1.add(new Phrase(Chunk.NEWLINE));
					document.add(sep1);
					
					
					
					contadorSeervicios=0;
						contadorGranTotal=0;
						contadorGranTotalSaldos=0;
						contadorGranTotalAbonos=0;
				
							PdfPTable tableDetalles = new PdfPTable(5);
							tableDetalles.setWidthPercentage(100);
							float[] medidaCeldas = {1.2f, 3f, 1.2f,1.3f,1.3f};
							tableDetalles.setWidths(medidaCeldas);
							tableDetalles.setHorizontalAlignment(Element.ALIGN_RIGHT);
					
						
							
						PdfPCell cellTituloDetalles = new PdfPCell(new Phrase("DETALLES",Colores.fuenteContenidosBold));
							cellTituloDetalles.setBorderColor(BaseColor.BLACK);
							cellTituloDetalles.setBackgroundColor(Colores.colorBordesTablas);
							cellTituloDetalles.setColspan(5);
							
							cellTituloDetalles.setHorizontalAlignment(Element.ALIGN_CENTER);
							tableDetalles.addCell(cellTituloDetalles);
							
							PdfPCell cellTituloRec = new PdfPCell(new Phrase("",Colores.fuenteContenidosBold));
							cellTituloRec.setBorderColor(BaseColor.BLACK);
							cellTituloRec.setHorizontalAlignment(Element.ALIGN_CENTER);
							tableDetalles.addCell(cellTituloRec);
							
							PdfPCell cellTituloDescripción = new PdfPCell(new Phrase("Descripción",Colores.fuenteContenidosBold));
							cellTituloDescripción.setBorderColor(BaseColor.BLACK);
							cellTituloDescripción.setHorizontalAlignment(Element.ALIGN_CENTER);
							tableDetalles.addCell(cellTituloDescripción);
							
							PdfPCell cellTituloValor = new PdfPCell(new Phrase("Valor",Colores.fuenteContenidosBold));
							cellTituloValor.setBorderColor(BaseColor.BLACK);
							cellTituloValor.setHorizontalAlignment(Element.ALIGN_CENTER);
							tableDetalles.addCell(cellTituloValor);
							
							PdfPCell cellTituloAbono = new PdfPCell(new Phrase("Abono",Colores.fuenteContenidosBold));
							cellTituloAbono.setBorderColor(BaseColor.BLACK);
							cellTituloAbono.setHorizontalAlignment(Element.ALIGN_CENTER);
							tableDetalles.addCell(cellTituloAbono);
							
							PdfPCell cellTituloSaldo = new PdfPCell(new Phrase("Saldo",Colores.fuenteContenidosBold));
							cellTituloSaldo.setBorderColor(BaseColor.BLACK);
							cellTituloSaldo.setHorizontalAlignment(Element.ALIGN_CENTER);
							tableDetalles.addCell(cellTituloSaldo);
							document.add(tableDetalles);
						
							int contadorFcaturas=0;
							int contadorValor=0;
							int contadorAbono=0;
							int contadorSaldo=0;
						for (int j = 0; j < items.size(); j++) {
							DatosAbono DA=items.get(j);
							RecepcionCompleta RCI=conexionBusqueda.getInstance().RCXid(DA.getCodRecepcion());
							Recepcion recepcion=RCI.getRecepcion();

								
								PdfPTable tableExa = new PdfPTable(5);
								tableExa.setWidthPercentage(100);
								float[] medidaCeldasExa = {1.2f, 3f, 1.2f,1.3f,1.3f};
								tableExa.setWidths(medidaCeldasExa);
								tableExa.setHorizontalAlignment(Element.ALIGN_RIGHT);
								
								contadorFcaturas++;

								PdfPCell cellRecep = new PdfPCell(new Phrase("Recepción :"+RCI.getRecepcion().getIdREcepcion(),Colores.fuenteContenidos));
								cellRecep.setBorder(Rectangle.BOTTOM);
								cellRecep.setBorderColor(BaseColor.BLACK);
								cellRecep.setHorizontalAlignment(Element.ALIGN_LEFT);
								tableExa.addCell(cellRecep);
								
								PdfPCell cellDescripcin = new PdfPCell(new Phrase(RCI.getRecepcion().getPaciente().getNombres()+" "+RCI.getRecepcion().getPaciente().getApellidos(),Colores.fuenteContenidos));
								cellDescripcin.setBorder(Rectangle.BOTTOM);
								cellDescripcin.setBorderColor(BaseColor.BLACK);
								cellDescripcin.setHorizontalAlignment(Element.ALIGN_LEFT);
								tableExa.addCell(cellDescripcin);
								
								PdfPCell cellCedula = new PdfPCell(new Phrase("Doc.: "+RCI.getRecepcion().getPaciente().getId(),Colores.fuenteContenidos));
								cellCedula.setBorderColor(BaseColor.BLACK);
								cellCedula.setBorder(Rectangle.BOTTOM);
								cellCedula.setHorizontalAlignment(Element.ALIGN_LEFT);
								tableExa.addCell(cellCedula);
								
								PdfPCell cellFecha = new PdfPCell(new Phrase(RCI.getRecepcion().getFechaRecepcion().toString(),Colores.fuenteContenidos));
								cellFecha.setBorder(Rectangle.BOTTOM);
								cellFecha.setBorderColor(BaseColor.BLACK);
								cellFecha.setHorizontalAlignment(Element.ALIGN_LEFT);
								tableExa.addCell(cellFecha);
								
								PdfPCell cellOrden = new PdfPCell(new Phrase("Orden: "+RCI.getRecepcion().getNumeroOrdenString()+"Auto: "+RCI.getRecepcion().getAutorizacionString(),Colores.fuenteContenidos));
								cellOrden.setBorder(Rectangle.BOTTOM);
								cellOrden.setBorderColor(BaseColor.BLACK);
								cellOrden.setHorizontalAlignment(Element.ALIGN_LEFT);
								tableExa.addCell(cellOrden);
								
								ArrayList<itemFactura> itemsF=RCI.getItemsFactura();
								
								for (int k = 0; k < itemsF.size(); k++) {
									itemFactura IF=itemsF.get(k);
									
									contadorSeervicios++;
									contadorGranTotal=contadorGranTotal+RCI.getFactura().getTotalConD();
									contadorGranTotalSaldos=contadorGranTotalSaldos+RCI.getFactura().getSaldo();
									contadorGranTotalAbonos=contadorGranTotalAbonos+RCI.getFactura().getAbono();
									
									Examen examen = conexionBusqueda.getInstance().examenXcodigo(IF.getCodExamen());
									PdfPCell cellNada = new PdfPCell(new Phrase("",Colores.fuenteContenidos));
									cellNada.setBorderColor(Colores.colorBordesTablas);
									cellNada.setBorder(Rectangle.BOTTOM);
									cellNada.setHorizontalAlignment(Element.ALIGN_LEFT);
									tableExa.addCell(cellNada);
									
									PdfPCell cellExamen = new PdfPCell(new Phrase(IF.getCodExamen()+" • "+examen.getNombre(),Colores.fuenteContenidos));
									cellExamen.setBorderColor(Colores.colorBordesTablas);
									cellExamen.setHorizontalAlignment(Element.ALIGN_LEFT);
									cellExamen.setBorder(Rectangle.BOTTOM);
									tableExa.addCell(cellExamen);
									
									////////////////////////////////////////////////////
									itemsCuenta.add(new itemCuenta(-1, -1,DA.getCodRecepcion(),recepcion.getPaciente().getId(), recepcion.getFechaRecepcion(),IF.getValor(),examen.getCups()));
		//////////////////////////////////////////////////////////////////////
									
									
									PdfPCell cellValor = new PdfPCell(new Phrase(IF.getValor()+"",Colores.fuenteContenidos));
									cellValor.setBorderColor(Colores.colorBordesTablas);
									cellValor.setBorder(Rectangle.BOTTOM);
									cellValor.setHorizontalAlignment(Element.ALIGN_RIGHT);
									tableExa.addCell(cellValor);
									
									PdfPCell cellAbono= new PdfPCell(new Phrase("",Colores.fuenteContenidos));
									cellAbono.setBorderColor(Colores.colorBordesTablas);
									cellAbono.setBorder(Rectangle.BOTTOM);
									cellAbono.setHorizontalAlignment(Element.ALIGN_LEFT);
									tableExa.addCell(cellAbono);
									
									PdfPCell cellSlado = new PdfPCell(new Phrase("",Colores.fuenteContenidos));
									cellSlado.setBorderColor(Colores.colorBordesTablas);
									cellSlado.setBorder(Rectangle.BOTTOM);
									cellSlado.setHorizontalAlignment(Element.ALIGN_LEFT);
									tableExa.addCell(cellSlado);
									
									
								}
								
								
								PdfPCell cellBlanco = new PdfPCell(new Phrase("",Colores.fuenteContenidosBold));
								cellBlanco.setBorderColor(BaseColor.BLACK);
								cellBlanco.setBorder(Rectangle.BOTTOM);
								cellBlanco.setHorizontalAlignment(Element.ALIGN_CENTER);
								tableExa.addCell(cellBlanco);
								
								PdfPCell cellNExamenes = new PdfPCell(new Phrase("Total  en "+itemsF.size()+" examenes menos descuentos",Colores.fuenteContenidosBold));
								cellNExamenes.setBorderColor(BaseColor.BLACK);
								cellNExamenes.setBorder(Rectangle.BOTTOM);
								cellNExamenes.setHorizontalAlignment(Element.ALIGN_RIGHT);
								tableExa.addCell(cellNExamenes);
								
								contadorValor=contadorValor+RCI.getFactura().getTotalConD();
								contadorAbono=contadorAbono+RCI.getFactura().getAbono();
								contadorSaldo=contadorSaldo+RCI.getFactura().getSaldo();
								
								PdfPCell cellValor = new PdfPCell(new Phrase("$ "+RCI.getFactura().getTotalConD(),Colores.fuenteContenidosBold));
								cellValor.setBorderColor(BaseColor.BLACK);
								cellValor.setBorder(Rectangle.BOTTOM);
								cellValor.setHorizontalAlignment(Element.ALIGN_RIGHT);
								tableExa.addCell(cellValor);
								
								PdfPCell cellAbono = new PdfPCell(new Phrase("Abono: $ "+RCI.getFactura().getAbono(),Colores.fuenteContenidosBold));
								cellAbono.setBorderColor(BaseColor.BLACK);
								cellAbono.setBorder(Rectangle.BOTTOM);
								cellAbono.setHorizontalAlignment(Element.ALIGN_RIGHT);
								tableExa.addCell(cellAbono);
								
								PdfPCell cellSaldo = new PdfPCell(new Phrase("Saldo: $ "+RCI.getFactura().getSaldo(),Colores.fuenteContenidosBold));
								cellSaldo.setBorderColor(BaseColor.BLACK);
								cellSaldo.setBorder(Rectangle.BOTTOM);
								cellSaldo.setHorizontalAlignment(Element.ALIGN_RIGHT);
								tableExa.addCell(cellSaldo);
								
									
								document.add(tableExa);
						
								Paragraph sep3 = new Paragraph();
								sep3.add(new Phrase(Chunk.NEWLINE));
								document.add(sep3);
										
							
						
							
						}
						PdfPTable tableDetalles2 = new PdfPTable(5);
						tableDetalles2.setWidthPercentage(100);
						float[] medidaCeldas2 = {1.2f, 3f, 1.2f,1.3f,1.3f};
						tableDetalles2.setWidths(medidaCeldas2);
						tableDetalles2.setHorizontalAlignment(Element.ALIGN_RIGHT);
						document.add(tableDetalles2);
						
						
						PdfPCell cellTitTotalGrupo = new PdfPCell(new Phrase("TOTALES",Colores.fuenteContenidosBold));
						cellTitTotalGrupo.setBorderColor(BaseColor.BLACK);
						cellTitTotalGrupo.setColspan(5);
						cellTitTotalGrupo.setHorizontalAlignment(Element.ALIGN_CENTER);
						tableDetalles2.addCell(cellTitTotalGrupo);
						
						PdfPCell cellBlankTotal = new PdfPCell(new Phrase("",Colores.fuenteContenidosBold));
						cellBlankTotal.setBorderColor(BaseColor.BLACK);
						cellBlankTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
						tableDetalles2.addCell(cellBlankTotal);
						
						PdfPCell cellNExamenes = new PdfPCell(new Phrase("Total  en "+contadorFcaturas+" facturas menos descuentos",Colores.fuenteContenidosBold));
						cellNExamenes.setBorderColor(BaseColor.BLACK);
						cellNExamenes.setHorizontalAlignment(Element.ALIGN_RIGHT);
						tableDetalles2.addCell(cellNExamenes);
						
						PdfPCell cellValor = new PdfPCell(new Phrase("$ "+contadorValor,Colores.fuenteContenidosBold));
						cellValor.setBorderColor(BaseColor.BLACK);
						cellValor.setHorizontalAlignment(Element.ALIGN_RIGHT);
						tableDetalles2.addCell(cellValor);
						
						PdfPCell cellAbono = new PdfPCell(new Phrase("Abono: $ "+contadorAbono,Colores.fuenteContenidosBold));
						cellAbono.setBorderColor(BaseColor.BLACK);
						cellAbono.setHorizontalAlignment(Element.ALIGN_RIGHT);
						tableDetalles2.addCell(cellAbono);
						
						PdfPCell cellSaldo = new PdfPCell(new Phrase("Saldo: $ "+contadorSaldo,Colores.fuenteContenidosBold));
						cellSaldo.setBorderColor(BaseColor.BLACK);
						cellSaldo.setHorizontalAlignment(Element.ALIGN_RIGHT);
						tableDetalles2.addCell(cellSaldo);
						
						
						document.add(tableDetalles2);
						Paragraph sep = new Paragraph();
						sep.add(new Phrase(Chunk.NEWLINE));
						sep.add(new Phrase(Chunk.NEWLINE));
						document.add(sep);
				
						PdfPTable tableDetalles3 = new PdfPTable(5);
						tableDetalles3.setWidthPercentage(100);
						float[] medidaCeldas3 = {1.2f, 3f, 1.2f,1.3f,1.3f};
						tableDetalles3.setWidths(medidaCeldas3);
						tableDetalles3.setHorizontalAlignment(Element.ALIGN_RIGHT);
						document.add(tableDetalles3);
						
						
						PdfPCell cellTitGranTotal = new PdfPCell(new Phrase("TOTALES GENERALES PARA ESTA CUENTA DE CORBO",Colores.fuenteContenidosBold));
						cellTitGranTotal.setBorderColor(BaseColor.BLACK);
						cellTitGranTotal.setBackgroundColor(Colores.colorBordesTablas);
						cellTitGranTotal.setColspan(5);
						cellTitGranTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
						tableDetalles3.addCell(cellTitGranTotal);
						
						PdfPCell cellBlankgTotal = new PdfPCell(new Phrase("",Colores.fuenteContenidosBold));
						cellBlankgTotal.setBorderColor(BaseColor.BLACK);
						cellBlankgTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
						tableDetalles3.addCell(cellBlankgTotal);
						
						PdfPCell cellNGTServicios = new PdfPCell(new Phrase("Total  en "+contadorSeervicios+" servicios menos descuentos",Colores.fuenteContenidosBold));
						cellNGTServicios.setBorderColor(BaseColor.BLACK);
						cellNGTServicios.setHorizontalAlignment(Element.ALIGN_RIGHT);
						tableDetalles3.addCell(cellNGTServicios);
						
						PdfPCell cellGValor = new PdfPCell(new Phrase("$ "+contadorGranTotal,Colores.fuenteContenidosBold));
						cellGValor.setBorderColor(BaseColor.BLACK);
						cellGValor.setHorizontalAlignment(Element.ALIGN_RIGHT);
						tableDetalles3.addCell(cellGValor);
						
						PdfPCell cellGAbono = new PdfPCell(new Phrase("Abono: $ "+contadorGranTotalAbonos,Colores.fuenteContenidosBold));
						cellGAbono.setBorderColor(BaseColor.BLACK);
						cellGAbono.setHorizontalAlignment(Element.ALIGN_RIGHT);
						tableDetalles3.addCell(cellGAbono);
						
						PdfPCell cellGSaldo = new PdfPCell(new Phrase("Saldo: $ "+contadorGranTotalSaldos,Colores.fuenteContenidosBold));
						cellGSaldo.setBorderColor(BaseColor.BLACK);
						cellGSaldo.setHorizontalAlignment(Element.ALIGN_RIGHT);
						tableDetalles3.addCell(cellGSaldo);
						
						
						document.add(tableDetalles3);
						Paragraph sepG = new Paragraph();
						sepG.add(new Phrase(Chunk.NEWLINE));
						sepG.add(new Phrase(Chunk.NEWLINE));
						document.add(sepG);
				}
				
				   
				   
			// Cerrar el documento
			document.close();
			FileInputStream pdf=	new FileInputStream("C:/6342522/temp/"+nombrearchivo+".pdf");
			cuentaCobro cuenta=new cuentaCobro(-1, (new ObtenerFechaColombia()).fechaColombia(), items.get(items.size()-1).getFechaYHora(), items.get(0).getFechaYHora(), empresa.getCodEps(), empresa.getIdEmpresa()+"", empresa.getRazonSocial(), -1, "0", contadorGranTotalAbonos+"", 0, contadorGranTotalSaldos, contadorSeervicios, itemsCuenta, pdf);
			
			if (conexion.getInstance().nuevoCuenta(cuenta)) {
				// Abrir el archivo
						
						if (imprimir) {
							new imprimirPredeterminada("C:/6342522/temp/"+nombrearchivo+".pdf",Colores.impresoraListados);
						    Principal.getInstancePrincipal().registrarAccion("Impresión de reporte de recepción");

						} else {
							String  path = "C:/6342522/temp/"+nombrearchivo+".pdf";
							NativeInterface.open();
							
							(new VisorPDF(path, "Cuenta de cobro  # "+String.format("%05d", (conexionBusqueda.getInstance().ultimaCuentaCobro()+1)))).execute();
						    Principal.getInstancePrincipal().registrarAccion("Creación de reporte de recepción PDF");

						}
					}else{
						Principal.getInstancePrincipal().registrarErrorDB("< ADVERTENCIA >Error al guardar esta cuenta de cobro  en la base de datos");
					}
						
			
		} catch (Exception ex) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR  >Error al generar el archivo correspondiente a esta cuenta de cobro"+ex);

			}



	

	}
	

	
}
	