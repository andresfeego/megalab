package Interfaces;

import interfaces_Modificadas.ClrCuadroDeTexto;
import interfaces_Modificadas.Colores;
import interfaces_Modificadas.EntregasTableCellEditor;
import interfaces_Modificadas.MiCheckBoxRender;
import interfaces_Modificadas.MiTableModelEntregas;
import interfaces_Modificadas.MiTableModelNoEditable;
import interfaces_Modificadas.MiTableModelReportes;
import interfaces_Modificadas.ReportesTableCellEditor;
import interfaces_Modificadas.btnRedondo;
import interfaces_Modificadas.circuloUsuario;
import interfaces_Modificadas.clrCheckBox;
import interfaces_Modificadas.clrComboBox;
import interfaces_Modificadas.clrLabel;
import interfaces_Modificadas.clrPanelBordes;
import interfaces_Modificadas.clrRadioButton;
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
import auxiliares.Nota;
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

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
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
import javax.swing.table.TableCellEditor;
import javax.swing.text.TabExpander;
import javax.swing.text.TabableView;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSeparator;

import java.awt.Font;

public class OpRegistroEntrega extends JDialog {
	private clrPanelBordes contentPane;
	private clrLabel lblincorrectos;
	private Principal principal;
	private Usuario usuario;
	private clrLabel lblinicio;
	private circuloUsuario btnRedondo_;
	private OpRegistroEntrega esta;
	private int idtipo;
	private int ordenauxi = 1;
	private int saldo=0;
	private RecepcionCompleta recepcionSeleccion;
	private Paciente paciente;
	private RecepcionCompleta RCS;
	private int valorTotal;
	private ArrayList<DatosAbono>DatosAbono;

	private btnRedondo btnCancelar;
	private btnRedondo btnGuardarYSalir;
	private lbRecepcionXId lb1;
	private JScrollPane scrollPane;
	private JTable table;
	private MiTableModelEntregas TM;
	private ArrayList<itemProtocolo> paraEliminar = new ArrayList<itemProtocolo>();
	private ArrayList<itemProtocolo> paraAgregar = new ArrayList<itemProtocolo>();
	private ArrayList<itemProtocolo> paraModificar = new ArrayList<itemProtocolo>();
	private clrPanelBordes panelPaciente;
	private ClrCuadroDeTexto txtEmpresa;
	private ClrCuadroDeTexto txtIdRecepcion;
	private ClrCuadroDeTexto txtSeEntregaA;
	private clrtextpane txtObs;
	private clrLabel lblTotal ;
	private ButtonGroup BG;
	private clrRadioButton rdbtnEntregaPaciente;
	private clrRadioButton rdbtnEntregaOtro;

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

	private clrLabel lblSeEntrega;
	private ClrCuadroDeTexto txtPaciente;
	private String labelTipo="débito";
	private int tipoNota=0;

	public OpRegistroEntrega(Principal principal, Usuario usuario) {
		super(principal, true);
		this.principal = principal;
		this.usuario = usuario;
		this.esta = this;
		

		// this.setDefaultLookAndFeelDecorated(false);

		setBounds(100, 100, 666, 480);
		this.setUndecorated(true);
		this.setLocationRelativeTo(getParent());
		contentPane = new clrPanelBordes(true);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		lblinicio = new clrLabel("Reporte de entrega de resultados", 2, true);
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
		btnCancelar.setBounds(519, 423, 136, 50);
		contentPane.add(btnCancelar);

		btnGuardarYSalir = new btnRedondo("Reportar entrega", new Rectangle(48, 172,121,50), 1);
		btnGuardarYSalir.setSelected(true);
		btnGuardarYSalir.setBounds(331, 423, 178, 50);
		btnGuardarYSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				TableCellEditor TCE=table.getCellEditor();
			if (table.isEditing()) {
				TCE.stopCellEditing();
			}
				
			
				reportarEntrega();
				
			}
		});
		
		contentPane.add(btnGuardarYSalir);

		TM = new MiTableModelEntregas(new Object[][] {}, new String[] {  "Cod. Examen", "Nom. Examen", "R", "I", "E", "Entregar"});
		scrollPane = new clrScrollBar();
		scrollPane.setBounds(10, 58, 625, 148);

		table = new JTable();
		table.setModel(TM);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultRenderer(Object.class, new clrRenderTablaAbonosXPaciente());
		scrollPane.setViewportView(table);
		JTableHeader TH = table.getTableHeader();
		TH.setBackground(Colores.clrSecundario);
		table.setTableHeader(TH);
		table.getColumnModel().getColumn(5).setCellEditor(new EntregasTableCellEditor());
		table.getColumnModel().getColumn(5).setCellRenderer(new MiCheckBoxRender(""));
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(350);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setPreferredWidth(20);
		table.getColumnModel().getColumn(4).setPreferredWidth(20);
		table.getColumnModel().getColumn(5).setPreferredWidth(80);
		

		panelPaciente = new clrPanelBordes(false);
		panelPaciente.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Informaci\u00F3n de factura", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
		panelPaciente.setBounds(10, 77, 645, 217);
		panelPaciente.setLayout(null);
		contentPane.add(panelPaciente);
		panelPaciente.add(scrollPane);

		txtEmpresa = new ClrCuadroDeTexto(100,false,"Empresa");
		txtEmpresa.setFocusCycleRoot(false);
		txtEmpresa.setBounds(473, 22, 162, 25);
		txtEmpresa.setEnabled(false);
		panelPaciente.add(txtEmpresa);

		txtIdRecepcion = new ClrCuadroDeTexto(11,true,"N\u00FAmero de recepci\u00F3n", true,1);
		txtIdRecepcion.setFocusCycleRoot(false);
		txtIdRecepcion.setBounds(10, 22, 173, 25);
		txtIdRecepcion.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				lb1.setVisible(false);
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (lb1==null) {
					lb1 = new lbRecepcionXId(txtIdRecepcion, esta, panelPaciente, esta.principal);
				}
				
			}
		});
		panelPaciente.add(txtIdRecepcion);
		
		txtPaciente = new ClrCuadroDeTexto(100,false,"Paciente");
		txtPaciente.setHorizontalAlignment(SwingConstants.LEFT);
		txtPaciente.setFocusCycleRoot(false);
		txtPaciente.setBounds(193, 22, 270, 25);
		txtPaciente.setEnabled(false);
		panelPaciente.add(txtPaciente);

		clrPanelBordes clrPanelBordes_ = new clrPanelBordes(false);
		clrPanelBordes_.setBounds(10, 316, 645, 91);
		contentPane.add(clrPanelBordes_);
		clrPanelBordes_.setLayout(null);
		clrPanelBordes_.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Informaci\u00F3n de la entrega", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));

		BG = new ButtonGroup();
	
		rdbtnEntregaPaciente = new clrRadioButton("Se entrega al paciente");
		rdbtnEntregaPaciente.setSelected(true);
		rdbtnEntregaPaciente.setBounds(233, 17, 187, 23);
		rdbtnEntregaPaciente.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if (rdbtnEntregaPaciente.isSelected()&&paciente!=null) {
					txtSeEntregaA.setText(paciente.getNombres()+" "+paciente.getApellidos());
					txtSeEntregaA.setForeground(Color.BLACK);

				}else{
					txtSeEntregaA.reiniciar();
				}
				
			}
		});
		clrPanelBordes_.add(rdbtnEntregaPaciente);
		BG.add(rdbtnEntregaPaciente);

		rdbtnEntregaOtro = new clrRadioButton("Se entrega a otra persona");
		rdbtnEntregaOtro.setBounds(422, 17, 213, 23);
		clrPanelBordes_.add(rdbtnEntregaOtro);
		BG.add(rdbtnEntregaOtro);
		
		txtSeEntregaA = new ClrCuadroDeTexto(100,false,"Nombres y apellidos",1);
		txtSeEntregaA.setFocusCycleRoot(false);
		txtSeEntregaA.setBounds(353, 49, 282, 25);
		

		clrPanelBordes_.add(txtSeEntregaA);

	
		lblSeEntrega = new clrLabel("Se entrega a ", 1);
		lblSeEntrega.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSeEntrega.setForeground(Colores.clrPrincipal);
		lblSeEntrega.setAlignmentX(0.5f);
		lblSeEntrega.setBounds(233, 47, 110, 28);
		clrPanelBordes_.add(lblSeEntrega);
		
		clrScrollBar clrScrollBar_ = new clrScrollBar();
		clrScrollBar_.setBounds(10, 17, 213, 64);
		clrPanelBordes_.add(clrScrollBar_);
		
		txtObs = new clrtextpane(250,false,"Observaciones",0);
		clrScrollBar_.setViewportView(txtObs);
		
		lblTotal = new clrLabel("R = Reportado || I = Impreso || E = Entregado", 1);
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setForeground(Colores.clrPrincipal);
		lblTotal.setFont(Colores.fuentePequena);
		lblTotal.setAlignmentX(0.5f);
		lblTotal.setBounds(352, 291, 303, 28);
		contentPane.add(lblTotal);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				btnGuardarYSalir.requestFocusInWindow();
			}
		});

		this.setVisible(true);
		

	}

	
	
	public void llenarURC(RecepcionCompleta RC) {
		recepcionSeleccion=RC;
		RCS=RC;
		Recepcion recepcion=RC.getRecepcion();
		paciente=RC.getRecepcion().getPaciente();
		
		txtEmpresa.setText(recepcion.getEmpresa().getRazonSocial());
		txtEmpresa.setForeground(Color.BLACK);
		
		txtIdRecepcion.setText(""+recepcion.getIdREcepcion());
		txtIdRecepcion.setForeground(Color.black);
		
		txtPaciente.setText(paciente.getNombres()+" "+paciente.getApellidos());
		txtPaciente.setForeground(Color.black);
		
		if (rdbtnEntregaPaciente.isSelected()) {
			txtSeEntregaA.setText(paciente.getNombres()+" "+paciente.getApellidos());
			txtSeEntregaA.setForeground(Color.BLACK);

		}
		
		llenarTabla(RC.getItemsFactura());
	}
	
	public void llenarTabla(ArrayList<itemFactura> datos){
		while(TM.getRowCount()>0){
			TM.removeRow(0);
		}
		
		Object[] mat=new Object[6];
	
	for (int i = 0; i < datos.size(); i++) {
		
	itemFactura IF=datos.get(i);
		Examen EX=conexionBusqueda.getInstance().examenXcodigo(IF.getCodExamen());
		
		String auxireportado = "no";
		if (IF.getReportado() == 1) {
			auxireportado = "si";
		}
		
		String auxiImpreso = "no";
		if (IF.getImpreso() == 1) {
			auxiImpreso = "si";
		}
		
		String auxiEntregado = "no";
		if (IF.getEntregado() == 1) {
			auxiEntregado = "si";
		}
		
		
			mat[0]=IF.getCodExamen()+"";
			mat[1]=EX.getNombre()+"";
			mat[2]=auxireportado;
			mat[3]=auxiImpreso;
			mat[4]=auxiEntregado;
			mat[5]=false;
			
			
			TM.addRow(mat);
					
	}
		
	}

	public void reportarEntrega(){
		if (paciente==null||txtSeEntregaA.getText().equals(txtSeEntregaA.getLabel())) {
			if(txtSeEntregaA.getText().equals(txtSeEntregaA.getLabel())){lblincorrectos.setText("No has elegido un receptor para esta entrega");};

			if(paciente==null){lblincorrectos.setText("Nohas elegido una recepción");};
			
		} else {
			
			ArrayList<itemFactura> items=RCS.getItemsFactura();
			
			ArrayList<itemFactura> nuevosItems=new ArrayList<itemFactura>();
			for (int i = 0; i < items.size(); i++) {
				itemFactura IF=items.get(i);
				if ((Boolean) table.getValueAt(i, 5)) {
					IF.setEntregado(1);
					IF.setEntregadoA(txtSeEntregaA.getText());
					nuevosItems.add(IF);
				}
				
				
			}
			
			if (nuevosItems.size()==0) {
				lblincorrectos.setText("No vas a entregar ningun examen, debes hacer check en la columna entregar");
			} else {
				
				lblincorrectos.setText("");
				
				
				String auxiObs="";if(!txtObs.getText().equals(txtObs.getLabel())){auxiObs=txtObs.getText();};
				
				if (conexion.getInstance().reportarEntrega(esta,RCS, auxiObs,nuevosItems)) {
					esta.principal.registrarAccion("Reporte de entrega de resultados para recepción #"+RCS.getRecepcion().getIdREcepcion());
					dispose();
				}else{
					lblincorrectos.setText("Error  en la conexion con la base de datos");
				}
			}
		}
	}
	public void reportarError(String error){
		lblincorrectos.setText(error);
	}

}
