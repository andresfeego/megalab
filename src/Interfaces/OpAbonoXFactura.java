package Interfaces;

import interfaces_Modificadas.ClrCuadroDeTexto;
import interfaces_Modificadas.Colores;
import interfaces_Modificadas.MiTableModelNoEditable;
import interfaces_Modificadas.btnRedondo;
import interfaces_Modificadas.circuloUsuario;
import interfaces_Modificadas.clrCheckBox;
import interfaces_Modificadas.clrComboBox;
import interfaces_Modificadas.clrLabel;
import interfaces_Modificadas.clrPanelBordes;
import interfaces_Modificadas.clrRenderTablaAbonosXPaciente;
import interfaces_Modificadas.clrRenderTablaItemRecepcion;
import interfaces_Modificadas.clrScrollBar;
import interfaces_Modificadas.clrSeparador;
import interfaces_Modificadas.clrtextpane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import javax.naming.LimitExceededException;
import javax.swing.JDialog;
import javax.swing.SwingConstants;

import ago.beans.CirclePanel;
import auxiliares.AbonoXEmpresa;
import auxiliares.AbonoXFactura;
import auxiliares.AbonoXPaciente;
import auxiliares.Bacteriologo;
import auxiliares.DatosAbono;
import auxiliares.Empresa;
import auxiliares.Factura;
import auxiliares.GruposEmpresas;
import auxiliares.ItemRecepcion;
import auxiliares.Medico;
import auxiliares.OTD;
import auxiliares.Paciente;
import auxiliares.PerfilExamenes;
import auxiliares.Recepcion;
import auxiliares.RecepcionCompleta;
import auxiliares.Secciones;
import auxiliares.Tarifa;
import auxiliares.TipoDato;
import auxiliares.Usuario;
import auxiliares.Examen;
import auxiliares.itemFactura;
import auxiliares.itemProtocolo;
import auxiliares.protocolo;
import conexion.conexion;
import conexion.conexionBusqueda;
import conexion.conexionCombos;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import datechooser.beans.DateChooserCombo;

import com.sun.org.apache.bcel.internal.generic.LLOAD;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.TabExpander;
import javax.swing.text.TabableView;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSeparator;

import java.awt.Font;

public class OpAbonoXFactura extends JDialog {
	private clrPanelBordes contentPane;
	private clrLabel lblincorrectos;
	private Principal principal;
	private Usuario usuario;
	private clrLabel lblinicio;
	private circuloUsuario btnRedondo_;
	private OpAbonoXFactura esta;
	private int idtipo;
	private int ordenauxi = 1;
	private int saldo=0;
	private DatosAbono DA;
	private Factura facturaSeleccion;
	private ArrayList<DatosAbono>DatosAbono;

	private String codigoAntiguo;
	private int idprotocolo;
	private btnRedondo btnCancelar;
	private btnRedondo btnGuardarYSalir;
	private lbfacturaXID lb1;
	private lbEmpresaXnit lb2;
	private lbEmpresaXrazon lb3;
	private JScrollPane scrollPane;
	private JTable table;
	private MiTableModelNoEditable TM;
	private ArrayList<itemProtocolo> paraEliminar = new ArrayList<itemProtocolo>();
	private ArrayList<itemProtocolo> paraAgregar = new ArrayList<itemProtocolo>();
	private ArrayList<itemProtocolo> paraModificar = new ArrayList<itemProtocolo>();
	private clrPanelBordes panelPaciente;
	private ClrCuadroDeTexto txtEmpresa;
	private ClrCuadroDeTexto txtIdFactura;
	private ClrCuadroDeTexto txtAbono;
	private ClrCuadroDeTexto txtSaldo;
	private ClrCuadroDeTexto txtNumeroTarjeta;
	private clrComboBox cbFormaPago;
	private clrtextpane txtObs;

	private int valorInicial = 0;
	private int porcientoUrgencias = 0;
	private int porcientoFestivos = 0;
	private int porcientoEspecial = 0;


	private int porFacturar = 0;
	private boolean perfilPorAgregar = false;

	private boolean okUrgencias = false;
	private boolean okFestivos = false;
	private boolean okEspecial = false;

	private Examen auxiexamen = null;

	private clrLabel clrLabel;
	private ClrCuadroDeTexto txtPaciente;
	private clrLabel lblSaldo;

	public OpAbonoXFactura(Principal principal, Usuario usuario,Factura factura) {
		super(principal, true);
		this.principal = principal;
		this.usuario = usuario;
		this.facturaSeleccion=factura;
		this.esta = this;
		
		

		// this.setDefaultLookAndFeelDecorated(false);

		setBounds(100, 100, 666, 444);
		this.setUndecorated(true);
		this.setLocationRelativeTo(getParent());
		contentPane = new clrPanelBordes(true);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		lblinicio = new clrLabel("Abonos a factura", 2, true);
		lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblinicio.setBounds(10, 11, 645, 28);
		contentPane.add(lblinicio);

		lblincorrectos = new clrLabel("", 1);
		lblincorrectos.setForeground(Colores.clrAlertaCamarada);   
		lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
		lblincorrectos.setAlignmentX(0.5f);
		lblincorrectos.setBounds(10, 50, 645, 28);
		contentPane.add(lblincorrectos);

		btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50), 2);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(519, 369, 136, 50);
		contentPane.add(btnCancelar);

		btnGuardarYSalir = new btnRedondo("Abonar  y salir", new Rectangle(48, 172,121,50), 1);
		btnGuardarYSalir.setSelected(true);
		btnGuardarYSalir.setBounds(352, 369, 157, 50);
		btnGuardarYSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Abonar();
				
			}
		});
		
		contentPane.add(btnGuardarYSalir);

		TM = new MiTableModelNoEditable(new Object[][] {}, new String[] { "# Factura", "# Recepción", "Total factura", "Abono", "Saldo", "Fecha y hora de facturación"});
		scrollPane = new clrScrollBar();
		scrollPane.setBounds(10, 58, 625, 75);

		table = new JTable();
		
		table.setModel(TM);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.setDefaultRenderer(Object.class, new clrRenderTablaAbonosXPaciente());
		scrollPane.setViewportView(table);
		JTableHeader TH = table.getTableHeader();
		TH.setBackground(Colores.clrSecundario);
		table.setTableHeader(TH);

		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(170);

		panelPaciente = new clrPanelBordes(false);
		panelPaciente.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Informaci\u00F3n de paciente y saldo", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
		panelPaciente.setBounds(10, 77, 645, 140);
		panelPaciente.setLayout(null);
		contentPane.add(panelPaciente);
		panelPaciente.add(scrollPane);

		txtEmpresa = new ClrCuadroDeTexto(100,false,"Empresa");
		txtEmpresa.setFocusCycleRoot(false);
		txtEmpresa.setBounds(473, 22, 162, 25);
		txtEmpresa.setEnabled(false);
		panelPaciente.add(txtEmpresa);

		txtIdFactura = new ClrCuadroDeTexto(11,true,"N\u00FAmero de factura", 1);
		txtIdFactura.setFocusCycleRoot(false);
		txtIdFactura.setBounds(10, 22, 162, 25);
		txtIdFactura.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				lb1.setVisible(false);
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (lb1==null) {
					lb1=new lbfacturaXID(txtIdFactura, esta, panelPaciente, esta.principal);
				}
				
			}
		});
		panelPaciente.add(txtIdFactura);
		
		txtPaciente = new ClrCuadroDeTexto(255,false,"Paciente");
		txtPaciente.setHorizontalAlignment(SwingConstants.LEFT);
		txtPaciente.setFocusCycleRoot(false);
		txtPaciente.setBounds(178, 22, 285, 25);
		txtPaciente.setEnabled(false);
		panelPaciente.add(txtPaciente);

		clrPanelBordes clrPanelBordes_ = new clrPanelBordes(false);
		clrPanelBordes_.setBounds(9, 262, 645, 96);
		contentPane.add(clrPanelBordes_);
		clrPanelBordes_.setLayout(null);
		clrPanelBordes_.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) Colores.clrPrincipal), "Información de pago", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));

		txtAbono = new ClrCuadroDeTexto(15,true,"Abono");
		txtAbono.setFocusCycleRoot(false);
		txtAbono.setBounds(486, 16, 149, 25);
		txtAbono.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(Integer.parseInt(txtAbono.getText())>saldo){
					txtAbono.borrar();
					lblincorrectos.setText("No puedes abonar una cantidad mayor al saldo total actual");
				}else{
					lblincorrectos.setText("");
					calcularSaldo();
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				
			}
		});
		clrPanelBordes_.add(txtAbono);

		txtSaldo = new ClrCuadroDeTexto(15,true,"Saldo");
		txtSaldo.setFocusCycleRoot(false);
		txtSaldo.setEnabled(false);
		txtSaldo.setBounds(486, 49, 149, 25);
		clrPanelBordes_.add(txtSaldo);

		txtNumeroTarjeta = new ClrCuadroDeTexto(13,true,"Numero de tarjeta");
		txtNumeroTarjeta.setFocusCycleRoot(false);
		txtNumeroTarjeta.setBounds(10, 47, 201, 25);
		txtNumeroTarjeta.setVisible(false);
		clrPanelBordes_.add(txtNumeroTarjeta);

		cbFormaPago = new clrComboBox(conexionCombos.getInstance().listaFormaPago(),1);
		cbFormaPago.setFocusCycleRoot(false);
		cbFormaPago.setBounds(10, 14, 201, 25);
		cbFormaPago.setSelectedIndex(1);
		cbFormaPago.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(cbFormaPago.getSelectedIndex()==3){
					txtNumeroTarjeta.setVisible(true);
				}else{
					txtNumeroTarjeta.setVisible(false);
				}
				
			}
		});
		clrPanelBordes_.add(cbFormaPago);

		clrLabel = new clrLabel("Abono $", 1);
		clrLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		clrLabel.setForeground(Colores.clrPrincipal);
		clrLabel.setAlignmentX(0.5f);
		clrLabel.setBounds(409, 14, 67, 28);
		clrPanelBordes_.add(clrLabel);

		clrLabel clrLabel__2 = new clrLabel("Saldo $", 1);
		clrLabel__2.setHorizontalAlignment(SwingConstants.RIGHT);
		clrLabel__2.setForeground(Colores.clrPrincipal);
		clrLabel__2.setAlignmentX(0.5f);
		clrLabel__2.setBounds(409, 49, 67, 28);
		clrPanelBordes_.add(clrLabel__2);
		
		clrScrollBar clrScrollBar_ = new clrScrollBar();
		clrScrollBar_.setBounds(221, 15, 190, 63);
		clrPanelBordes_.add(clrScrollBar_);
		
		txtObs = new clrtextpane(200,false,"Observaciones",0);
		clrScrollBar_.setViewportView(txtObs);
		
		lblSaldo = new clrLabel("Saldo $", 1);
		lblSaldo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSaldo.setForeground(Colores.clrPrincipal);
		lblSaldo.setAlignmentX(0.5f);
		lblSaldo.setBounds(449, 223, 206, 28);
		contentPane.add(lblSaldo);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				btnGuardarYSalir.requestFocusInWindow();	
				if (facturaSeleccion!=null) {
			llenarFactura(facturaSeleccion);
		}
			}
		});

		this.setVisible(true);
	
	}

	
	
	public void llenarFactura(Factura factura) {
		facturaSeleccion=factura;
		DA=conexionBusqueda.getInstance().facturasDabonoXfactura(factura.getIdFactura()+"");
	
		txtEmpresa.setText(DA.getRzonSocial());
		txtEmpresa.setForeground(Color.BLACK);
		
		txtIdFactura.setText(""+factura.getIdFactura());
		txtIdFactura.setForeground(Color.black);
		
		txtPaciente.setText(DA.getIdPaciente()+" • "+DA.getNombresYApellidos());
		txtPaciente.setForeground(Color.black);
		
		
	
		
		llenarTabla(DA);
	}
	
	public void llenarTabla(DatosAbono datos){
		while(TM.getRowCount()>0){
			TM.removeRow(0);
		}
		
		String[] mat=new String[7];
	
	
		
			mat[0]=datos.getIdFactura()+"";
			mat[1]=datos.getCodRecepcion()+"";
			mat[2]=datos.getTotal()+"";
			mat[3]=datos.getAbono()+"";
			mat[4]=datos.getSaldo()+"";
			mat[5]=datos.getFechaYHora()+"";
			
			lblSaldo.setText("Saldo $ "+ datos.getSaldo());
			TM.addRow(mat);
					
	saldo=datos.getSaldo();
		
	}

	public void Abonar(){
		if (txtAbono.getText().equals(txtAbono.getLabel())||cbFormaPago.getSelectedIndex()==0||TM.getRowCount()<0) {
			if(txtAbono.getText().equals(txtAbono.getLabel())){lblincorrectos.setText("No vas a abonar nada a esta cuenta");};
			if(cbFormaPago.getSelectedIndex()==0){lblincorrectos.setText("No has seleccionado una forma de pago");};
			if(TM.getRowCount()<0){lblincorrectos.setText("No hay cuenta a la cual abonar");};
		} else {
			lblincorrectos.setText("");
			String auxiObs="";if(!txtObs.getText().equals(txtObs.getLabel())){auxiObs=txtObs.getText();};
			int auxiNumeroTarjeta=0;if(txtNumeroTarjeta.isVisible()&&!txtNumeroTarjeta.getText().equals(txtNumeroTarjeta.getLabel())){auxiNumeroTarjeta=Integer.parseInt(txtNumeroTarjeta.getText());};
			
			AbonoXFactura abono=new AbonoXFactura(facturaSeleccion.getIdFactura(), cbFormaPago.getSelectedIndex(), Integer.parseInt(txtAbono.getText()),auxiObs,DA,auxiNumeroTarjeta);
			
			if (conexion.getInstance().nuevoAbonoXFactura(esta, facturaSeleccion, abono)) {
				esta.principal.registrarAccion("Abono a factura #  ' "+facturaSeleccion.getIdFactura());
				dispose();
			}else{
				lblincorrectos.setText("Error  en la conexion con la base de datos");
			}
		}
	}
	
public void calcularSaldo(){
	
	txtSaldo.setText(""+(saldo-Integer.parseInt(txtAbono.getText())));		
			
}

public void reportarError(String error){
	lblincorrectos.setText(error);
}
}
