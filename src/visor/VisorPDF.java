package visor;

import interfaces_Modificadas.btnRedondo;
import interfaces_Modificadas.clrLabel;
import interfaces_Modificadas.clrPanelBordes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.apache.commons.net.nntp.Threadable;
import org.omg.CORBA.TypeCodePackage.Bounds;

import Interfaces.Principal;
import chrriis.dj.nativeswing.NativeSwing;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;


public class VisorPDF extends SwingWorker<String, Object>  {
	private clrPanelBordes contentPane;
	private clrLabel lblincorrectos;

	private clrLabel lblinicio;
	private JWebBrowser fileBrowser = new JWebBrowser();
	private String path;
	private String titulo;
	private VisorPDF esta;

	

	public VisorPDF(String path, String titulo) {
		VisorPDF.this.path=path;
		VisorPDF.this.titulo=titulo;
		esta=this;
		// Visor.this.setDefaultLookAndFeelDecorated(false);
	
		
		
		

	
			
	

	}



	@Override
	protected String doInBackground() throws Exception {
		Runnable runnable = new Runnable() {
	        public void run() {
	        	final JDialog ventana=new JDialog();
	        	ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	        	ventana.setBounds(0, 0, GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width, GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);
	        	ventana.setUndecorated(true);
	        	contentPane = new clrPanelBordes(true);
	        	contentPane.setLayout(null);
	        	ventana.setContentPane(contentPane);


		lblinicio = new clrLabel(VisorPDF.this.titulo, 2,true);
		lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblinicio.setBounds(10, 11, ventana.getWidth()-10, 28);
		contentPane.add(lblinicio);

	
		
		clrPanelBordes panelVisor = new clrPanelBordes(false);
		panelVisor.setBounds(2, 45, ventana.getWidth()-4, ventana.getHeight()-45);
		panelVisor.setLayout(new BorderLayout());
		
		fileBrowser.setStatusBarVisible(false);
		fileBrowser.setBarsVisible(false);
		panelVisor.add(fileBrowser);
	System.out.println("path>>> "+VisorPDF.this.path);
		fileBrowser.navigate(VisorPDF.this.path);
		
		btnRedondo salir=new btnRedondo("Salir", new Rectangle(ventana.getWidth()-100, 0, 100, 100),2);
		salir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				
				ventana.dispose();	
				
				File archivo=new File(VisorPDF.this.path);
				try {
					archivo.delete();
				} catch (Exception e2) {
					System.out.println("errro"+e2);
				}
			}
		});
		contentPane.add(salir);
	
		contentPane.add(panelVisor);
		ventana.setVisible(true);
	        }
	    };
System.out.println("thread issssss"+SwingUtilities.isEventDispatchThread());
		
	    try {
	    	
			SwingUtilities.invokeAndWait(runnable);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

		return "nada";
	}
}
