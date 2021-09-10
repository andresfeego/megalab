package otrosImpresos;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.Phaser;

import javax.swing.ImageIcon;

import interfaces_Modificadas.Colores;
import auxiliares.Bacteriologo;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.TabStop.Alignment;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class FormatoDocFirmado extends PdfPageEventHelper{    
    private Image imagen;
    private Image firma;
    private Bacteriologo bact;
    private PdfTemplate total;
	Font fuentePequeña = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL,BaseColor.BLACK);

    /**
     * Constructor de la clase, inicializa la imagen que se utilizara en el membrete
     */
    public FormatoDocFirmado(Bacteriologo bact){
    	this.bact=bact;
        try
        {
        	   java.awt.Image Logo=Colores.LogoMEGALABMem;
            AlphaComposite ac = java.awt.AlphaComposite.getInstance(AlphaComposite.SRC,0.7f);

        	   BufferedImage imageBuff = new BufferedImage(Logo.getWidth(null), Logo.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		        Graphics2D g = (Graphics2D)imageBuff.getGraphics();
		        g.setComposite(ac);
		        g.drawImage(Logo, 0, 0, Logo.getWidth(null), Logo.getHeight(null),null);
		        ImageIcon IA=new ImageIcon(imageBuff);
		           imagen = Image.getInstance(IA.getImage(),null);
		           float alto=(70*Logo.getHeight(null))/Logo.getWidth(null);
		           imagen.scaleAbsolute(70, alto);
		           
		   
		     
            
        }catch(Exception r)
        {
            System.err.println("Error al leer la imagen");
        }    
    }
    
    /**
     * Manejador del evento onEndPage, usado para generar el encabezado
     */
    public void onEndPage(PdfWriter writer, Document document) {

        try{
        	imagen.setAbsolutePosition(document.getPageSize().getWidth()-105f, 30f);
        	Image totalimagen=Image.getInstance(total);
        	totalimagen.setAbsolutePosition(0,0);

        document.add(totalimagen);
            document.add(imagen);
      
        	Paragraph numeracion = new Paragraph();
        	numeracion.add(new Phrase("Pagina "+writer.getPageNumber(),fuentePequeña));
			
        	ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_LEFT, numeracion, 30, 30, 0);
        
        }catch(Exception doc)
         {
             doc.printStackTrace();
         }         
     }
    
    
    
    @Override
	public void onOpenDocument(PdfWriter writer, Document document) {
	total=writer.getDirectContent().createTemplate(document.getPageSize().getWidth(),document.getPageSize().getHeight());
		super.onOpenDocument(writer, document);
	}

	@Override
	public void onCloseDocument(PdfWriter writer, Document document) {


        try{
        
      
        	Paragraph numeracionde = new Paragraph();
        	numeracionde.add(new Phrase(" de "+writer.getPageNumber(),fuentePequeña));
			
        	ColumnText.showTextAligned(total,Element.ALIGN_LEFT, numeracionde, 54, 30, 0);
        	
        

        	
          	
		
         }catch(Exception doc)
         {
             doc.printStackTrace();
         }         
     
		
		super.onCloseDocument(writer, document);
	}
    
}