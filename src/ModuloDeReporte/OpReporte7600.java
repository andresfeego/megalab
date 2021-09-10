package ModuloDeReporte;

import interfaces_Modificadas.ClrCuadroDeTexto;
import interfaces_Modificadas.Colores;
import interfaces_Modificadas.btnRedondo;
import interfaces_Modificadas.clrComboBox;
import interfaces_Modificadas.clrFrameBordes;
import interfaces_Modificadas.clrLabel;
import interfaces_Modificadas.clrPanelBordes;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import metodos.arrastraYsuelta;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.sun.org.apache.xpath.internal.operations.Mod;

import conexion.conexion;
import Interfaces.Principal;
import Interfaces.lbRecepcionXId;
import auxiliares.ItemRecepcion;
import auxiliares.RecepcionCompleta;
import auxiliares.Reporte;
import auxiliares.Usuario;
import auxiliares.itemFactura;

import javax.swing.JSeparator;
import javax.swing.JProgressBar;

public class OpReporte7600 extends JDialog {

	private JPanel contentPane;
	public clrComboBox cbCom;
	public String puertoSeleccinado=Colores.puerto7600;
	public clrLabel lblincorrectos; 
	private Communicator communicator=null;
	private int contador=0;
	private String cadenaDatos="";
	
	private clrLabel lblWBC ;
	private clrLabel lblRBC;
	private clrLabel lblPLT;
	
	private ClrCuadroDeTexto txt1;
	private ClrCuadroDeTexto txt2;
	private ClrCuadroDeTexto txt3;
	private ClrCuadroDeTexto txt4;
	private ClrCuadroDeTexto txt5;
	private ClrCuadroDeTexto txt6;
	private ClrCuadroDeTexto txt7;
	private ClrCuadroDeTexto txt8;
	private ClrCuadroDeTexto txt9;
	private ClrCuadroDeTexto txt10;
	private ClrCuadroDeTexto txt11;
	private ClrCuadroDeTexto txt12;
	private ClrCuadroDeTexto txt13;
	private ClrCuadroDeTexto txt14;
	private btnRedondo btnRedondo_;
	
	private ClrCuadroDeTexto txtCodExamen;
	private ClrCuadroDeTexto txtFecha;
	private ClrCuadroDeTexto txtidPaciente;
	private ClrCuadroDeTexto txtEdadPciente;
	private ClrCuadroDeTexto txtGeneroPaciente;
	private ClrCuadroDeTexto txtNombrePaciente;
	private ClrCuadroDeTexto txtApellidosPaciente;
	private lbRecepcionXId lb1;
	private OpReporte7600 esta;
	private clrPanelBordes panelRecepcion;
	private clrPanelBordes clrPanelBordes__1;
	private RecepcionCompleta RC;
	private itemFactura IFSeleccion;
	
	private StringBuffer resultadoGraWBC;
	private StringBuffer resultadoGraRBC;
	private StringBuffer resultadoGraPLT;
	private boolean conDatos=false;
	private Principal principal;
	private Usuario usuario;
	private JProgressBar progressBar;
	private btnRedondo btnRedondo;
	
	

	public OpReporte7600(Principal principal,Usuario usuario) {
		super(principal, true);
		this.principal=principal;
		this.usuario=usuario;
		this.esta=this;
				
		
		setBounds(100, 100, 729, 883);
		this.setUndecorated(true);
		this.setLocationRelativeTo(getParent());
		contentPane = new clrPanelBordes(true);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
		
		communicator = new Communicator(this);

		
		clrLabel clrLabel_ = new clrLabel("Resultados por recepci\u00F3n", 2, true);
		clrLabel_.setHorizontalAlignment(SwingConstants.CENTER);
		clrLabel_.setAlignmentX(0.5f);
		clrLabel_.setBounds(10, 11, 703, 28);
		contentPane.add(clrLabel_);
		
		lblincorrectos = new clrLabel("", 1);
		lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
		lblincorrectos.setForeground(new Color(56, 195, 140));
		lblincorrectos.setAlignmentX(0.5f);
		lblincorrectos.setBounds(10, 50, 703, 28);
		contentPane.add(lblincorrectos);
		
		clrPanelBordes clrPanelBordes_ = new clrPanelBordes(false);
		clrPanelBordes_.setLayout(null);
		clrPanelBordes_.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Información de paciente", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
		clrPanelBordes_.setBounds(10, 159, 377, 103);
		contentPane.add(clrPanelBordes_);
		
		txtidPaciente = new ClrCuadroDeTexto(15, true, "Identificaci\u00F3n");
		txtidPaciente.setHorizontalAlignment(SwingConstants.LEFT);
		txtidPaciente.setFocusCycleRoot(false);
		txtidPaciente.setEnabled(false);
		txtidPaciente.setBounds(10, 28, 136, 25);
		clrPanelBordes_.add(txtidPaciente);
		
		txtNombrePaciente= new ClrCuadroDeTexto(100, false, "Nombres");
		txtNombrePaciente.setFocusCycleRoot(false);
		txtNombrePaciente.setEnabled(false);
		txtNombrePaciente.setBounds(10, 64, 168, 25);
		clrPanelBordes_.add(txtNombrePaciente);
		
		txtApellidosPaciente= new ClrCuadroDeTexto(100, false, "Apellidos");
		txtApellidosPaciente.setFocusCycleRoot(false);
		txtApellidosPaciente.setEnabled(false);
		txtApellidosPaciente.setBounds(188, 64, 179, 25);
		clrPanelBordes_.add(txtApellidosPaciente);
		
		txtEdadPciente= new ClrCuadroDeTexto(100, false, "Edad");
		txtEdadPciente.setHorizontalAlignment(SwingConstants.LEFT);
		txtEdadPciente.setFocusCycleRoot(false);
		txtEdadPciente.setEnabled(false);
		txtEdadPciente.setBounds(156, 28, 116, 25);
		clrPanelBordes_.add(txtEdadPciente);
		
		txtGeneroPaciente= new ClrCuadroDeTexto(100, false, "Genero");
		txtGeneroPaciente.setHorizontalAlignment(SwingConstants.LEFT);
		txtGeneroPaciente.setFocusCycleRoot(false);
		txtGeneroPaciente.setEnabled(false);
		txtGeneroPaciente.setBounds(282, 28, 78, 25);
		clrPanelBordes_.add(txtGeneroPaciente);
		
		 panelRecepcion = new clrPanelBordes(false);
		panelRecepcion.setLayout(null);
		panelRecepcion.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Informaci\u00F3n de recepci\u00F3n", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
		panelRecepcion.setBounds(10, 89, 377, 59);
		contentPane.add(panelRecepcion);
		
		txtCodExamen= new ClrCuadroDeTexto(100, false, "# Recepcion", true);
		txtCodExamen.setFocusCycleRoot(false);
		txtCodExamen.setBounds(10, 21, 169, 25);
		txtCodExamen.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				if (lb1==null) {
					JFrame FR=new JFrame();
					FR.setVisible(false);
					lb1=new lbRecepcionXId(txtCodExamen, esta, panelRecepcion,OpReporte7600.this.principal);
				}
				
			}
		});
		panelRecepcion.add(txtCodExamen);
		
		 txtFecha = new ClrCuadroDeTexto(100, false, "Fecha Recepci\u00F3n");
		 txtFecha.setHorizontalAlignment(SwingConstants.LEFT);
		 txtFecha.setFocusCycleRoot(false);
		 txtFecha.setBounds(189, 21, 178, 25);
		 panelRecepcion.add(txtFecha);
		
		txt1 = new ClrCuadroDeTexto(100, false, "Eritrocitos");
		txt1.setFocusCycleRoot(false);
		txt1.setEnabled(false);
		txt1.setBounds(453, 90, 251, 25);
		contentPane.add(txt1);
		
		clrLabel clrLabel__2 = new clrLabel("RBC", 1);
		clrLabel__2.setHorizontalAlignment(SwingConstants.RIGHT);
		clrLabel__2.setForeground(new Color(0, 0, 0, 130));
		clrLabel__2.setBounds(390, 95, 60, 14);
		contentPane.add(clrLabel__2);
		
		clrLabel clrLabel__3 = new clrLabel("HGB", 1);
		clrLabel__3.setHorizontalAlignment(SwingConstants.RIGHT);
		clrLabel__3.setForeground(new Color(0, 0, 0, 130));
		clrLabel__3.setBounds(390, 130, 60, 14);
		contentPane.add(clrLabel__3);
		
		 txt2 = new ClrCuadroDeTexto(100, false, "Hemoglobina");
		txt2.setFocusCycleRoot(false);
		txt2.setEnabled(false);
		txt2.setBounds(453, 125, 251, 25);
		contentPane.add(txt2);
		
		clrLabel clrLabel__4 = new clrLabel("HCT", 1);
		clrLabel__4.setHorizontalAlignment(SwingConstants.RIGHT);
		clrLabel__4.setForeground(new Color(0, 0, 0, 130));
		clrLabel__4.setBounds(390, 164, 60, 14);
		contentPane.add(clrLabel__4);
		
		 txt3 = new ClrCuadroDeTexto(100, false, "Hematocrito");
		txt3.setFocusCycleRoot(false);
		txt3.setEnabled(false);
		txt3.setBounds(453, 160, 251, 25);
		contentPane.add(txt3);
		
		clrLabel clrLabel__5 = new clrLabel("MCV", 1);
		clrLabel__5.setHorizontalAlignment(SwingConstants.RIGHT);
		clrLabel__5.setForeground(new Color(0, 0, 0, 130));
		clrLabel__5.setBounds(390, 200, 60, 14);
		contentPane.add(clrLabel__5);
		
		 txt4 = new ClrCuadroDeTexto(100, false, "Volumen corpuscular medio");
		txt4.setHorizontalAlignment(SwingConstants.LEFT);
		txt4.setFocusCycleRoot(false);
		txt4.setEnabled(false);
		txt4.setBounds(453, 195, 251, 25);
		contentPane.add(txt4);
		
		clrLabel clrLabel__6 = new clrLabel("MCH", 1);
		clrLabel__6.setHorizontalAlignment(SwingConstants.RIGHT);
		clrLabel__6.setForeground(new Color(0, 0, 0, 130));
		clrLabel__6.setBounds(390, 236, 60, 14);
		contentPane.add(clrLabel__6);
		
		 txt5 = new ClrCuadroDeTexto(100, false, "Hemoglobina Corpuscular");
		txt5.setFocusCycleRoot(false);
		txt5.setEnabled(false);
		txt5.setBounds(453, 230, 251, 25);
		contentPane.add(txt5);
		
		clrLabel clrLabel__7 = new clrLabel("RDW-CV", 1);
		clrLabel__7.setHorizontalAlignment(SwingConstants.RIGHT);
		clrLabel__7.setForeground(new Color(0, 0, 0, 130));
		clrLabel__7.setBounds(390, 272, 60, 14);
		contentPane.add(clrLabel__7);
		
		 txt6 = new ClrCuadroDeTexto(100, false, "Indice de distribuci\u00F3n eritrocitaria");
		txt6.setFocusCycleRoot(false);
		txt6.setEnabled(false);
		txt6.setBounds(453, 265, 251, 25);
		contentPane.add(txt6);
		
		clrLabel clrLabel__8 = new clrLabel("WBC", 1);
		clrLabel__8.setHorizontalAlignment(SwingConstants.RIGHT);
		clrLabel__8.setForeground(new Color(0, 0, 0, 130));
		clrLabel__8.setBounds(390, 308, 60, 14);
		contentPane.add(clrLabel__8);
		
		 txt7 = new ClrCuadroDeTexto(100, false, "Recuento de leucocitos");
		txt7.setFocusCycleRoot(false);
		txt7.setEnabled(false);
		txt7.setBounds(453, 300, 251, 25);
		contentPane.add(txt7);
		
		clrLabel clrLabel__9 = new clrLabel("GRA%", 1);
		clrLabel__9.setHorizontalAlignment(SwingConstants.RIGHT);
		clrLabel__9.setForeground(new Color(0, 0, 0, 130));
		clrLabel__9.setBounds(390, 340, 60, 14);
		contentPane.add(clrLabel__9);
		
		 txt8 = new ClrCuadroDeTexto(100, false, "P.N. Neutrofilos");
		txt8.setFocusCycleRoot(false);
		txt8.setEnabled(false);
		txt8.setBounds(453, 335, 251, 25);
		contentPane.add(txt8);
		
		clrLabel clrLabel__10 = new clrLabel("MID%", 1);
		clrLabel__10.setHorizontalAlignment(SwingConstants.RIGHT);
		clrLabel__10.setForeground(new Color(0, 0, 0, 130));
		clrLabel__10.setBounds(390, 375, 60, 14);
		contentPane.add(clrLabel__10);
		
		 txt9 = new ClrCuadroDeTexto(100, false, "P.N. Eosinofilos");
		txt9.setFocusCycleRoot(false);
		txt9.setEnabled(false);
		txt9.setBounds(453, 370, 251, 25);
		contentPane.add(txt9);
		
		clrLabel clrLabel__11 = new clrLabel("LYM%", 1);
		clrLabel__11.setHorizontalAlignment(SwingConstants.RIGHT);
		clrLabel__11.setForeground(new Color(0, 0, 0, 130));
		clrLabel__11.setBounds(390, 410, 60, 14);
		contentPane.add(clrLabel__11);
		
		 txt10 = new ClrCuadroDeTexto(100, false, "Linfocitos");
		txt10.setFocusCycleRoot(false);
		txt10.setEnabled(false);
		txt10.setBounds(453, 405, 251, 25);
		contentPane.add(txt10);
		
		clrLabel clrLabel__12 = new clrLabel("PLT", 1);
		clrLabel__12.setHorizontalAlignment(SwingConstants.RIGHT);
		clrLabel__12.setForeground(new Color(0, 0, 0, 130));
		clrLabel__12.setBounds(390, 445, 60, 14);
		contentPane.add(clrLabel__12);
		
		 txt11 = new ClrCuadroDeTexto(100, false, "Recuento de plaquetas");
		txt11.setFocusCycleRoot(false);
		txt11.setEnabled(false);
		txt11.setBounds(453, 440, 251, 25);
		contentPane.add(txt11);
		
		clrLabel clrLabel__13 = new clrLabel("MPV", 1);
		clrLabel__13.setHorizontalAlignment(SwingConstants.RIGHT);
		clrLabel__13.setForeground(new Color(0, 0, 0, 130));
		clrLabel__13.setBounds(390, 480, 60, 14);
		contentPane.add(clrLabel__13);
		
		 txt12 = new ClrCuadroDeTexto(100, false, "Volumen plaquetario medio");
		txt12.setFocusCycleRoot(false);
		txt12.setEnabled(false);
		txt12.setBounds(453, 475, 251, 25);
		contentPane.add(txt12);
		
		clrLabel clrLabel__14 = new clrLabel("PCT", 1);
		clrLabel__14.setHorizontalAlignment(SwingConstants.RIGHT);
		clrLabel__14.setForeground(new Color(0, 0, 0, 130));
		clrLabel__14.setBounds(390, 515, 60, 14);
		contentPane.add(clrLabel__14);
		
		 txt13 = new ClrCuadroDeTexto(100, false, "Plaquetocrito");
		txt13.setFocusCycleRoot(false);
		txt13.setEnabled(false);
		txt13.setBounds(453, 510, 251, 25);
		contentPane.add(txt13);
		
		clrLabel clrLabel__15 = new clrLabel("PDW", 1);
		clrLabel__15.setHorizontalAlignment(SwingConstants.RIGHT);
		clrLabel__15.setForeground(new Color(0, 0, 0, 130));
		clrLabel__15.setBounds(390, 550, 60, 14);
		contentPane.add(clrLabel__15);
		
		 txt14 = new ClrCuadroDeTexto(100, false, "Volumen ancho de distribuci\u00F3n");
		txt14.setFocusCycleRoot(false);
		txt14.setEnabled(false);
		txt14.setBounds(453, 545, 251, 25);
		contentPane.add(txt14);
		
		btnRedondo btnSalir = new btnRedondo("Salir", new Rectangle(237, 172, 121, 50), 9);
		btnSalir.setBounds(376, 816, 114, 50);
		btnSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (communicator.getConnected()) {
					communicator.disconnect();	
				}
				dispose();
			}
		});
		contentPane.add(btnSalir);
		
		btnRedondo btnReportar = new btnRedondo("Reportar", new Rectangle(237, 172, 121, 50), 12);
		btnReportar.setBounds(217, 816, 114, 50);
		btnReportar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				reportar();
				
			}
		});
		contentPane.add(btnReportar);
		
		lblWBC = new clrLabel("", 1);
		lblWBC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWBC.setForeground(new Color(0, 0, 0, 130));
		lblWBC.setBounds(10, 273, 320, 170);
		contentPane.add(lblWBC);
		
		lblRBC = new clrLabel("", 1);
		lblRBC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRBC.setForeground(new Color(0, 0, 0, 130));
		lblRBC.setBounds(10, 454, 320, 170);
		contentPane.add(lblRBC);
		
		 lblPLT = new clrLabel("", 1);
		lblPLT.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPLT.setForeground(new Color(0, 0, 0, 130));
		lblPLT.setBounds(10, 635, 320, 170);
		contentPane.add(lblPLT);
		
		progressBar = new JProgressBar(0,870);
		progressBar.setValue(0);
		progressBar.setBorder(null);
		progressBar.setStringPainted(false);
		progressBar.setBounds(3, 44, this.getWidth()-6, 8);
		contentPane.add(progressBar);
		
		clrPanelBordes__1 = new clrPanelBordes(false);
		clrPanelBordes__1.setLayout(null);
		clrPanelBordes__1.setBorder(null);
		clrPanelBordes__1.setBounds(-322, this.getHeight()-53, 377, 52);
		clrPanelBordes__1.setBackground(Colores.clrPrincipal);
		contentPane.add(clrPanelBordes__1);
		
		btnRedondo_ = new btnRedondo("", new Rectangle(237, 172, 121, 50), 17);
		btnRedondo_.setBounds(327, 0, 50, 50);
		btnRedondo_.setVisible(true);
		btnRedondo_.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Colores.slowMotion(-200,  OpReporte7600.this.getHeight()-53, clrPanelBordes__1,contentPane,150);
				btnRedondo_.setVisible(false);
				btnRedondo.setVisible(true);
				
					
			
			}
		});
		clrPanelBordes__1.add(btnRedondo_);
		
		cbCom = new clrComboBox(new String[] {}, 0);
		cbCom.setBounds(225, 11, 92, 25);
		clrPanelBordes__1.add(cbCom);
		cbCom.setFocusCycleRoot(false);
		
		btnRedondo = new btnRedondo("", new Rectangle(237, 172, 121, 50), 1);
		btnRedondo.setBounds(327, 0, 50, 50);
		btnRedondo.setVisible(false);
	btnRedondo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Colores.slowMotion(-322,  OpReporte7600.this.getHeight()-53, clrPanelBordes__1,contentPane,150);
				btnRedondo_.setVisible(true);
				btnRedondo.setVisible(false);
				
					
			
			}
		});
		clrPanelBordes__1.add(btnRedondo);
		cbCom.removeAllItems();
		cbCom.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				Colores.guardarPuerto(cbCom.getSelectedItem().toString());
				puertoSeleccinado=Colores.puerto7600;
				

				if (communicator.getConnected()) {
					communicator.disconnect();	
				}
				
				
				 communicator.connect();
			        if (communicator.getConnected() == true)
			        {
			            if (communicator.initIOStream() == true)
			            {
			                communicator.initListener();
			            }
			        }
			}
		});
		
		 communicator.connect();
	        if (communicator.getConnected() == true)
	        {
	            if (communicator.initIOStream() == true)
	            {
	                communicator.initListener();
	            }
	        }
			communicator.searchForPorts();

	        this.setVisible(true);
	}

public void leer(String datos){
	conDatos=false;
	lblincorrectos.setText("Recibiendo datos...");
	if (datos.equals(",")) {
		contador++;
	}
	progressBar.setValue(contador);
	System.out.println("Contador "+contador);
	cadenaDatos=cadenaDatos+datos;
	if (contador==870) {	
		progressBar.setValue(progressBar.getMinimum());

		llenar();
		communicator.disconnect();
	
	}
}

public void llenar(){
	communicator.connect();
	lblincorrectos.setText("Datos recibidos de forma correcta");

	String[] cadena=cadenaDatos.split(",");
	contador=0;
	txt1.setText(cadena[38]+" "+cadena[39]);
	txt2.setText(cadena[41]+" "+cadena[42]);
	txt3.setText(cadena[59]+" "+cadena[60]);
	txt4.setText(cadena[50]+" "+cadena[51]);
	txt5.setText(cadena[47]+" "+cadena[48]);
	txt6.setText(cadena[53]+" "+cadena[54]);
	txt7.setText(cadena[17]+" "+cadena[18]);
	txt8.setText(cadena[35]+" "+cadena[36]);
	txt9.setText(cadena[32]+" "+cadena[33]);
	txt10.setText(cadena[29]+" "+cadena[30]);
	txt11.setText(cadena[62]+" "+cadena[63]);
	txt12.setText(cadena[65]+" "+cadena[66]);
	txt13.setText(cadena[71]+" "+cadena[72]);
	txt14.setText(cadena[68]+" "+cadena[69]);
	
	ImageIcon II1=new ImageIcon(creaImagenWBC());
	ImageIcon II2=new ImageIcon(creaImagenRBC());
ImageIcon II3=new ImageIcon(creaImagenPLT());
	
	lblWBC.setIcon(II1);
	lblRBC.setIcon(II2);
	lblPLT.setIcon(II3);
}

public BufferedImage creaImagenWBC()
{try {



	resultadoGraWBC=new StringBuffer();
    XYSeries series = new XYSeries("");
	String[] cadena=cadenaDatos.split(",");
int indiceCadena=80;
    for (int i = 0; i <251 ; i++) {
    	series.add(Double.parseDouble(""+i), Double.parseDouble(cadena[indiceCadena]));
    	resultadoGraWBC.append(cadena[indiceCadena]+",");
    	indiceCadena++;
	}
    
    XYDataset juegoDatos= new XYSeriesCollection(series);
    
    JFreeChart chart = ChartFactory.createXYLineChart        ("WBC","fL","V",juegoDatos,PlotOrientation.VERTICAL, false,false,false);
   XYPlot plot= chart.getXYPlot();
   plot.getRenderer().setSeriesPaint(0, Colores.clrAlertaCamarada);
   BufferedImage image = chart.createBufferedImage(320,170);
    
    return image;



} catch (Exception e) {
	System.err.println(e);
	return null;
}}

public BufferedImage creaImagenRBC()
{


	  resultadoGraRBC=new StringBuffer();
    XYSeries series = new XYSeries("");
	String[] cadena=cadenaDatos.split(",");
int indiceCadena=345;
    for (int i = 0; i <249 ; i++) {
    	series.add(Double.parseDouble(""+i), Double.parseDouble(cadena[indiceCadena]));
    	resultadoGraRBC.append(cadena[indiceCadena]+",");
    	indiceCadena++;
	}
    
    XYDataset juegoDatos= new XYSeriesCollection(series);
    
    JFreeChart chart = ChartFactory.createXYLineChart        ("RBC",    "fL","V",juegoDatos,PlotOrientation.VERTICAL,    false,    false,    false);
    XYPlot plot= chart.getXYPlot();
    plot.getRenderer().setSeriesPaint(0, Colores.clrAlertaCamarada);
    BufferedImage image = chart.createBufferedImage(320,170);
    conDatos=true;
    return image;


}

public BufferedImage creaImagenPLT()
{


	  resultadoGraPLT=new StringBuffer();
    XYSeries series = new XYSeries("");
	String[] cadena=cadenaDatos.split(",");
int indiceCadena=608;
    for (int i = 0; i <126 ; i++) {
    	series.add(Double.parseDouble(""+i), Double.parseDouble(cadena[indiceCadena]));
    	resultadoGraPLT.append(cadena[indiceCadena]+",");
    	indiceCadena++;
	}
    
    XYDataset juegoDatos= new XYSeriesCollection(series);
    
    JFreeChart chart = ChartFactory.createXYLineChart        ("PLT","fL","V",juegoDatos,PlotOrientation.VERTICAL,    false, false,true);
    XYPlot plot= chart.getXYPlot();
    plot.getRenderer().setSeriesPaint(0, Colores.clrAlertaCamarada);
    BufferedImage image = chart.createBufferedImage(320,170);
    
    return image;


}


public void llenarRC(RecepcionCompleta RC){
	txtCodExamen.setText(RC.getRecepcion().getIdREcepcion()+"");
	txtFecha.setText(RC.getRecepcion().getFechaRecepcion().toString());
	txtidPaciente.setText(RC.getRecepcion().getPaciente().getId());
	txtEdadPciente.setText(RC.getRecepcion().getPaciente().calcularEdad());
	txtGeneroPaciente.setText(RC.getRecepcion().getPaciente().getGeneroString());
	txtNombrePaciente.setText(RC.getRecepcion().getPaciente().getNombres());
	txtApellidosPaciente.setText(RC.getRecepcion().getPaciente().getApellidos());
	ArrayList<itemFactura> itemsF = RC.getItemsFactura();
	for (int i = 0; i < itemsF.size(); i++) {
		itemFactura IF=itemsF.get(i);
		int cont=0;
		if (IF.getCodExamen().equals("HEMGIV")) {
			cont++;
			IFSeleccion=IF;
			this.RC=RC;
			lblincorrectos.setText("Examen listo para reporte de resultados");
			break;
		}
		
		if (cont==0) {
			this.RC=null;
			lblincorrectos.setText("La recepción no contiene el examen Hemograma Tipo IV - HEMGIV");
		}
		
	}

}

public void reportar(){
	if (!conDatos || RC==null) {
		if (!conDatos) {
			lblincorrectos.setText("No hay datos que reportar");
		}
		if (RC==null) {
			lblincorrectos.setText("No has escogido ninguna recepción para reporte");
		}
	}else{
		lblincorrectos.setText("");
		ArrayList<Reporte> itemsAreportar=new ArrayList<Reporte>();
		for (int i = 0; i < IFSeleccion.getItemsExamenes().size(); i++) {
			ItemRecepcion IR=IFSeleccion.getItemsExamenes().get(i);
			if (IR.getConcepto().equals("Eritrocitos")) {
				if (IR.getGenero()==RC.getRecepcion().getPaciente().getGenero()+1) {
					Reporte rep=new Reporte(txt1.getText(), IR.getIdItemRecepcion());
					itemsAreportar.add(rep);
				}
			}
			
	if (IR.getConcepto().equals("Hemoglobina")) {
		if (IR.getGenero()==RC.getRecepcion().getPaciente().getGenero()+1) {
			Reporte rep=new Reporte(txt2.getText(), IR.getIdItemRecepcion());
			itemsAreportar.add(rep);
		}
			}
	
	if (IR.getConcepto().equals("Hematocrito")) {
		if (IR.getGenero()==RC.getRecepcion().getPaciente().getGenero()+1) {
			Reporte rep=new Reporte(txt3.getText(), IR.getIdItemRecepcion());
			itemsAreportar.add(rep);
		}
	}
	
	if (IR.getConcepto().equals("Volumen corpuscularmedio (MVC)")) {
		Reporte rep=new Reporte(txt4.getText(), IR.getIdItemRecepcion());
		itemsAreportar.add(rep);
	}
	
	if (IR.getConcepto().equals("Hemoglobina Corpuscular (MHC)")) {
		Reporte rep=new Reporte(txt5.getText(), IR.getIdItemRecepcion());
		itemsAreportar.add(rep);
	}
	
	if (IR.getConcepto().equals("Indice de distribución eritrocitaria (RDW)")) {
		Reporte rep=new Reporte(txt6.getText(), IR.getIdItemRecepcion());
		itemsAreportar.add(rep);
	}
	
	if (IR.getConcepto().equals("Recuento de Leucocitos")) {
		Reporte rep=new Reporte(txt7.getText(), IR.getIdItemRecepcion());
		itemsAreportar.add(rep);
	}
	if (IR.getConcepto().equals("P.N. Neutrofilos")) {
		Reporte rep=new Reporte(txt8.getText(), IR.getIdItemRecepcion());
		itemsAreportar.add(rep);
	}
	
	if (IR.getConcepto().equals("P.N. Eosinofilos")) {
		Reporte rep=new Reporte(txt9.getText(), IR.getIdItemRecepcion());
		itemsAreportar.add(rep);
	}
	
	if (IR.getConcepto().equals("Linfocitos")) {
		Reporte rep=new Reporte(txt10.getText(), IR.getIdItemRecepcion());
		itemsAreportar.add(rep);
	}
	
	if (IR.getConcepto().equals("Recuento de Plaquetas.")) {
		Reporte rep=new Reporte(txt11.getText(), IR.getIdItemRecepcion());
		itemsAreportar.add(rep);
	}
	
	if (IR.getConcepto().equals("Volumen plaquetario medio (MPV)")) {
		Reporte rep=new Reporte(txt12.getText(), IR.getIdItemRecepcion());
		itemsAreportar.add(rep);
	}
	
	if (IR.getConcepto().equals("Plaquetocrito (PCT)")) {
		Reporte rep=new Reporte(txt13.getText(), IR.getIdItemRecepcion());
		itemsAreportar.add(rep);
	}
	
	if (IR.getConcepto().equals("Volumen ancho de distribución (PDW)")) {
		Reporte rep=new Reporte(txt14.getText(), IR.getIdItemRecepcion());
		itemsAreportar.add(rep);
	}
	System.err.println(resultadoGraWBC.length());System.err.println(resultadoGraRBC.length());System.err.println(resultadoGraPLT.length());
	if (IR.getConcepto().equals("Gráfica WBC")) {
		Reporte rep=new Reporte(resultadoGraWBC.toString(), IR.getIdItemRecepcion());
		itemsAreportar.add(rep);
	}
	
	if (IR.getConcepto().equals("Gráfica RBC")) {
		Reporte rep=new Reporte(resultadoGraRBC.toString(), IR.getIdItemRecepcion());
		itemsAreportar.add(rep);
	}
	
	if (IR.getConcepto().equals("Gráfica PLT")) {
		Reporte rep=new Reporte(resultadoGraPLT.toString(), IR.getIdItemRecepcion());
		itemsAreportar.add(rep);
	}
		}
		
		if (conexion.getInstance().reportar(esta, itemsAreportar, IFSeleccion.getIdItemAI())) {
			principal.registrarAccion("Reporte de resultado desde RAYTO 7600 en recepción # "+RC.getRecepcion().getIdREcepcion());
			communicator.disconnect();
			dispose();
		}else {
			lblincorrectos.setText("Error al reportar resultado");
		}
	}
}


public void reportarError(String error){
	lblincorrectos.setText(error);
}
}
