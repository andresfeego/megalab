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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import javax.naming.LimitExceededException;
import javax.swing.JDialog;
import javax.swing.SwingConstants;

import ago.beans.CirclePanel;
import auxiliares.AbonoXPaciente;
import auxiliares.Bacteriologo;
import auxiliares.DatosAbono;
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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class OpAbonoXpaciente extends JDialog {
	private clrPanelBordes contentPane;
	private clrLabel lblincorrectos;
	private Principal principal;
	private Usuario usuario;
	private clrLabel lblinicio;
	private circuloUsuario btnRedondo_;
	private OpAbonoXpaciente esta;
	private int idtipo;
	private int ordenauxi = 1;
	private int saldo=0;
	private Paciente paci;
	private ArrayList<DatosAbono>DatosAbono;

	private String codigoAntiguo;
	private int idprotocolo;
	private btnRedondo btnCancelar;
	private btnRedondo btnGuardarYSalir;
	private lbPacienteXid lb1;
	private lbPacienteXnombre lb2;
	private lbPacienteXapellido lb3;
	private JScrollPane scrollPane;
	private JTable table;
	private MiTableModelNoEditable TM;
	private ArrayList<itemProtocolo> paraEliminar = new ArrayList<itemProtocolo>();
	private ArrayList<itemProtocolo> paraAgregar = new ArrayList<itemProtocolo>();
	private ArrayList<itemProtocolo> paraModificar = new ArrayList<itemProtocolo>();
	private clrPanelBordes panelPaciente;
	private ClrCuadroDeTexto txtNombrePaciente;
	private ClrCuadroDeTexto txtIdPaciente;
	private ClrCuadroDeTexto txtApellidoPaciente;
	private btnRedondo btnAbonarXFactura;
	private ClrCuadroDeTexto txtAbono;
	private ClrCuadroDeTexto txtSaldo;
	private ClrCuadroDeTexto txtNumeroTarjeta;
	private clrComboBox cbFormaPago;
	private clrtextpane txtObs;
	private clrLabel lblSaldo;
	private clrLabel lblNumeroFacturas ;

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
	
	private JDateChooser fchHasta;
	private JDateChooser fchDesde;
	private Calendar hoy=new GregorianCalendar();


	public OpAbonoXpaciente(Principal principal, Usuario usuario) {
		super(principal, true);
		this.principal = principal;
		this.usuario = usuario;
		this.esta = this;
		

		// this.setDefaultLookAndFeelDecorated(false);

		setBounds(100, 100, 666, 637);
		this.setUndecorated(true);
		this.setLocationRelativeTo(getParent());
		contentPane = new clrPanelBordes(true);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		lblinicio = new clrLabel("Abonos por paciente", 2, true);
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
		btnCancelar.setBounds(520, 576, 136, 50);
		contentPane.add(btnCancelar);

		btnGuardarYSalir = new btnRedondo("Abonar  y salir", new Rectangle(48, 172,121,50), 1);
		btnGuardarYSalir.setSelected(true);
		btnGuardarYSalir.setBounds(353, 576, 157, 50);
		btnGuardarYSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Abonar();
			}
		});
		
		contentPane.add(btnGuardarYSalir);

		TM = new MiTableModelNoEditable(new Object[][] {}, new String[] { "# Factura", "# Recepción", "Total factura", "Abono", "Saldo", "Fecha y hora de facturación", "Empresa"});
		scrollPane = new clrScrollBar();
		scrollPane.setBounds(10, 163, 625, 146);

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
		table.getColumnModel().getColumn(6).setPreferredWidth(100);

		panelPaciente = new clrPanelBordes(false);
		panelPaciente.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Informaci\u00F3n de paciente y saldo", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
		panelPaciente.setBounds(10, 77, 645, 381);
		panelPaciente.setLayout(null);
		contentPane.add(panelPaciente);
		panelPaciente.add(scrollPane);

		txtNombrePaciente = new ClrCuadroDeTexto(100,false,"Nombre del paciente", true);
		txtNombrePaciente.setFocusCycleRoot(false);
		txtNombrePaciente.setBounds(230, 22, 201, 25);
		txtNombrePaciente.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				lb2.setVisible(false);
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				if(lb2==null){
					lb2=new lbPacienteXnombre(txtNombrePaciente, esta, panelPaciente, esta.principal);
				}
				
			}
		});
		panelPaciente.add(txtNombrePaciente);

		txtIdPaciente = new ClrCuadroDeTexto(15,true,"Identificaci\u00F3n del paciente", true,1);
		txtIdPaciente.setFocusCycleRoot(false);
		txtIdPaciente.setBounds(10, 22, 210, 25);
		txtIdPaciente.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				lb1.setVisible(false);
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (lb1==null) {
					lb1=new lbPacienteXid(txtIdPaciente, esta, panelPaciente, esta.principal);
				}				
			}
		});
		panelPaciente.add(txtIdPaciente);

		btnAbonarXFactura = new btnRedondo("Abonar por factura", new Rectangle(48, 172,121,50), 4);
		btnAbonarXFactura.setSelected(true);
		btnAbonarXFactura.setBounds(441, 320, 194, 50);
		btnAbonarXFactura.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (TM.getRowCount()>0) {
					lblincorrectos.setText("");
					if (table.getSelectedRow()>=0) {
						Factura fac=conexionBusqueda.getInstance().FacturaXID(""+TM.getValueAt(table.getSelectedRow(), 0));
						dispose();
						new OpAbonoXFactura(esta.principal, esta.usuario, fac);
						
					}else{
						lblincorrectos.setText("No has escogido ninguna factura");
					}
				}else{
					lblincorrectos.setText("No hay facturas por abonar");
				}
				
			}
		});
		panelPaciente.add(btnAbonarXFactura);
		
		txtApellidoPaciente = new ClrCuadroDeTexto(100,false,"Apellidos del paciente", true);
		txtApellidoPaciente.setFocusCycleRoot(false);
		txtApellidoPaciente.setBounds(441, 22, 194, 25);
		txtApellidoPaciente.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				lb3.setVisible(false);				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (lb3==null) {
					lb3=new lbPacienteXapellido(txtApellidoPaciente, esta, panelPaciente, esta.principal);
					
				}
				
			}
		});
		panelPaciente.add(txtApellidoPaciente);
		
		
		fchHasta = new JDateChooser();
		fchHasta.setBounds(199, 79, 171, 25);
		fchHasta.setCalendar(hoy);
		panelPaciente.add(fchHasta);
		fchHasta.setBorder(javax.swing.BorderFactory.createMatteBorder( 1, 1, 1, 1, Colores.clrTextoInactivo));
		
		fchHasta.getDateEditor().addPropertyChangeListener(
			    new PropertyChangeListener() {
			        @Override
			        public void propertyChange(PropertyChangeEvent e) {
			        	
			        	if (paci!=null) {
							llenarPaciente(paci);
						}
			        }
			    });
		
		fchDesde = new JDateChooser();
		fchDesde.setBounds(10, 79, 171, 25);
		fchDesde.setCalendar(hoy);
		panelPaciente.add(fchDesde);
		fchDesde.setBorder(javax.swing.BorderFactory.createMatteBorder( 1, 1, 1, 1, Colores.clrTextoInactivo));
		
		
		fchDesde.getDateEditor().addPropertyChangeListener(
			    new PropertyChangeListener() {
			        @Override
			        public void propertyChange(PropertyChangeEvent e) {
			        	
			        	if (paci!=null) {
			        		llenarPaciente(paci);
						}
			        }
			    });
		
		
		
		clrLabel clrLabel__1 = new clrLabel("Hasta el d\u00EDa", 1);
		clrLabel__1.setHorizontalAlignment(SwingConstants.LEFT);
		clrLabel__1.setForeground(new Color(0, 0, 0, 130));
		clrLabel__1.setBounds(199, 60, 92, 14);
		panelPaciente.add(clrLabel__1);
		
		clrLabel clrLabel_ = new clrLabel("Desde el d\u00EDa", 1);
		clrLabel_.setHorizontalAlignment(SwingConstants.LEFT);
		clrLabel_.setForeground(new Color(0, 0, 0, 130));
		clrLabel_.setBounds(10, 58, 92, 14);
		panelPaciente.add(clrLabel_);
		
		lblNumeroFacturas = new clrLabel("", 1);
		lblNumeroFacturas.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroFacturas.setForeground(Colores.clrPrincipal);
		lblNumeroFacturas.setFont(Colores.fuenteNormal);
		lblNumeroFacturas.setAlignmentX(0.5f);
		lblNumeroFacturas.setBounds(347, 124, 119, 28);
		panelPaciente.add(lblNumeroFacturas);
		
		clrLabel clrLabel__8 = new clrLabel(" en ", 1);
		clrLabel__8.setHorizontalAlignment(SwingConstants.RIGHT);
		clrLabel__8.setForeground(Colores.clrTextoInactivo);
		clrLabel__8.setFont(new Font("Calibri", Font.PLAIN, 13));
		clrLabel__8.setAlignmentX(0.5f);
		clrLabel__8.setBounds(334, 124, 19, 28);
		panelPaciente.add(clrLabel__8);
		
		lblSaldo = new clrLabel("", 1);
		lblSaldo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaldo.setForeground(Colores.clrPrincipal);
		lblSaldo.setFont(new Font("Calibri", Font.PLAIN, 17));
		lblSaldo.setAlignmentX(0.5f);
		lblSaldo.setBounds(234, 124, 103, 28);
		panelPaciente.add(lblSaldo);
		
		clrLabel clrLabel__10 = new clrLabel("El paciente presenta un saldo total de ", 1);
		clrLabel__10.setHorizontalAlignment(SwingConstants.RIGHT);
		clrLabel__10.setForeground(Colores.clrTextoInactivo);
		clrLabel__10.setFont(new Font("Calibri", Font.PLAIN, 13));
		clrLabel__10.setAlignmentX(0.5f);
		clrLabel__10.setBounds(10, 124, 230, 28);
		panelPaciente.add(clrLabel__10);

		clrPanelBordes clrPanelBordes_ = new clrPanelBordes(false);
		clrPanelBordes_.setBounds(10, 469, 645, 96);
		contentPane.add(clrPanelBordes_);
		clrPanelBordes_.setLayout(null);
		clrPanelBordes_.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) Colores.clrPrincipal), "Información de pago", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));

		txtAbono = new ClrCuadroDeTexto(15,true,"Abono",1);
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

		txtNumeroTarjeta = new ClrCuadroDeTexto(13,true,"Numero de tarjeta",1);
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

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				btnGuardarYSalir.requestFocusInWindow();
			}
		});

		this.setVisible(true);

	}

	
	
	public void llenarPaciente(Paciente paciente) {


		if (fchHasta.getDate()==null||fchDesde.getDate()==null) {
			lblincorrectos.setText("Falta una fecha para establecer el rango del abono");
		} else {
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		String fechaFH = formatter.format(fchHasta.getDate());
		String fechaFD = formatter.format(fchDesde.getDate());
		
		SimpleDateFormat formatter1;
		formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaFH1;
		Date fechaFD1;
		try {
			fechaFH1 = formatter.parse(fechaFH);
			fechaFD1 = formatter.parse(fechaFD);

				if (fechaFH1.before(fechaFD1)) {
			lblincorrectos.setText("La fecha final de busqueda es mayor  a la inicial");
		}else{
			
			paci=paciente;
			DatosAbono=conexionBusqueda.getInstance().facturasDabonoXPaciente(paciente.getId(),fchHasta.getDate(),fchDesde.getDate());
			txtNombrePaciente.setText(paciente.getNombres());
			txtNombrePaciente.setForeground(Color.BLACK);
			System.err.println("pasa por aqui ------------------mmmmmmmmmmmmmmmmmmmmmmmm------------------");

			txtIdPaciente.setText(paciente.getId());
			txtIdPaciente.setForeground(Color.black);
			
			txtApellidoPaciente.setText(paciente.getApellidos());
			txtApellidoPaciente.setForeground(Color.black);
			
			llenarTabla(DatosAbono);
			
			
		}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		
	}
		
	
		
		
		
		
		
	
	}
	
	public void llenarTabla(ArrayList<DatosAbono> datos){

		while(TM.getRowCount()>0){
			TM.removeRow(0);
		}
		
		String[] mat=new String[7];
		int saldoTotal=0;
		DatosAbono DA=null;
			for (int i = 0; i < datos.size(); i++) {
			DA=datos.get(i);
			mat[0]=DA.getIdFactura()+"";
			mat[1]=DA.getCodRecepcion()+"";
			mat[2]=DA.getTotal()+"";
			mat[3]=DA.getAbono()+"";
			mat[4]=DA.getSaldo()+"";
			mat[5]=DA.getFechaYHora()+"";
			mat[6]=DA.getRzonSocial();
			
			saldoTotal=saldoTotal+DA.getSaldo();
			
			TM.addRow(mat);
					
		}
		
			saldo=saldoTotal;

			lblSaldo.setText("$ "+saldoTotal);
			lblNumeroFacturas.setText((datos.size())+" facturas ");
				if (datos.size()>0) {
				//	lblFechaFacturas.setText(DA.getFechaYHora()+"");
			}
	
		
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
			AbonoXPaciente abono=new AbonoXPaciente(paci.getId(), cbFormaPago.getSelectedIndex(), Integer.parseInt(txtAbono.getText()),auxiObs,DatosAbono,auxiNumeroTarjeta);
			if (conexion.getInstance().nuevoAbonoXpaciente(esta, abono)) {
				esta.principal.registrarAccion("Abono a facturas de paciente id:' "+paci.getId()+" ' por $ "+txtAbono.getText());
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
