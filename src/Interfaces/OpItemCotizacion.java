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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import javax.naming.LimitExceededException;
import javax.swing.JDialog;
import javax.swing.SwingConstants;

import ago.beans.CirclePanel;
import auxiliares.Bacteriologo;
import auxiliares.Cotizacion;
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
import auxiliares.itemCotizacion;
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

import otrosImpresos.ImprimirCotizacion;

import java.awt.Font;

public class OpItemCotizacion extends JDialog {
	private clrPanelBordes contentPane;
	private clrLabel lblincorrectos;
	private Principal principal;
	private Usuario usuario;
	private clrLabel lblinicio;
	private circuloUsuario btnRedondo_;
	private OpItemCotizacion esta;
	private int idtipo;
	private int ordenauxi = 1;

	private String codigoAntiguo;
	private int idprotocolo;
	private btnRedondo btnCancelar;
	private btnRedondo btnGuardarYSalir;
	private lbExamenXid lb1;
	private lbExamenXnombre lb2;
	private lbPerfilesXcodigo lb3;
	private lbPerfilesXnombre lb4;
	private JScrollPane scrollPane;
	private JTable table;
	private MiTableModelNoEditable TM;
	private ArrayList<itemProtocolo> paraEliminar = new ArrayList<itemProtocolo>();
	private ArrayList<itemProtocolo> paraAgregar = new ArrayList<itemProtocolo>();
	private ArrayList<itemProtocolo> paraModificar = new ArrayList<itemProtocolo>();
	private clrPanelBordes panelExamenes;
	private ClrCuadroDeTexto txtNombreExamen;
	private ClrCuadroDeTexto txtCodExamen;
	private ClrCuadroDeTexto txtValor;
	private ClrCuadroDeTexto txtDuracion;
	private ClrCuadroDeTexto txtTotalRecargos;
	private ClrCuadroDeTexto txtTotalExamen;
	private btnRedondo btnAgregarExamen;
	private btnRedondo btnEliminarExamen;
	private clrLabel lblPorcientoUrgencias;
	private clrLabel lblPorcientoFestivos;
	private clrLabel lblPorcientoEspecial;
	private clrCheckBox chkUrgencias;
	private clrCheckBox chkFestivos;
	private clrCheckBox chkEspecial;
	private ClrCuadroDeTexto txtNombrePerfil;
	private ClrCuadroDeTexto txtCodPerfil;
	private clrPanelBordes panelPerfiles;
	private ClrCuadroDeTexto txtTotalDescuentos;
	private btnRedondo btnAgregarPerfil;
	private ClrCuadroDeTexto txtTotalExamenes;
	private ClrCuadroDeTexto txtDescuentoPesos;
	private ClrCuadroDeTexto txtDescuentoPorciento;
	private clrCheckBox chkImprimirDirecto;
	private ClrCuadroDeTexto txtTotalFactura;
	private clrtextpane txtObs;
	private clrComboBox cbTarifas;
	private clrLabel lblTarifa;
	private ClrCuadroDeTexto txtCotizadoA ;
	private ClrCuadroDeTexto txtDireccion ;
	private ClrCuadroDeTexto txtTelefono ; 

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
	private Tarifa tarifaActual;

	private btnRedondo btnElimiinarPerfil;
	private clrLabel clrLabel_2;
	private clrLabel clrLabel;
	private clrLabel clrLabel_1;
	private clrPanelBordes clrPanelBordes;
	private clrLabel clrLabel_5;

	public OpItemCotizacion(Principal principal, Usuario usuario) {
		super(principal, true);
		this.principal = principal;
		this.usuario = usuario;
		this.esta = this;
	
		tarifaActual=conexionBusqueda.getInstance().TarifaXid("0");
		// this.setDefaultLookAndFeelDecorated(false);

		setBounds(100, 100, 666, 843);
		this.setUndecorated(true);
		this.setLocationRelativeTo(getParent());
		contentPane = new clrPanelBordes(true);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		lblinicio = new clrLabel("Nueva cotizaci\u00F3n", 2, true);
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
		btnCancelar.setBounds(519, 784, 136, 50);
		contentPane.add(btnCancelar);

		btnGuardarYSalir = new btnRedondo("Cotizar  y salir", new Rectangle(48, 172,121,50), 1);
		btnGuardarYSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			guargarRecepcion();
			}
		});
		btnGuardarYSalir.setSelected(true);
		btnGuardarYSalir.setBounds(330, 784, 169, 50);
		contentPane.add(btnGuardarYSalir);

		TM = new MiTableModelNoEditable(new Object[][] {}, new String[] { "Código", "Nombre de examen", "Días", "Rem", "CUPS", "Valor $", "RU", "RF", "RE", "F" });
		scrollPane = new clrScrollBar();
		scrollPane.setBounds(10, 201, 625, 217);

		table = new JTable();
		table.setModel(TM);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.setDefaultRenderer(Object.class, new clrRenderTablaItemRecepcion());
		scrollPane.setViewportView(table);
		JTableHeader TH = table.getTableHeader();
		TH.setBackground(Colores.clrSecundario);
		table.setTableHeader(TH);

		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(400);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(90);
		table.getColumnModel().getColumn(6).setPreferredWidth(30);
		table.getColumnModel().getColumn(7).setPreferredWidth(30);
		table.getColumnModel().getColumn(8).setPreferredWidth(30);
		table.getColumnModel().getColumn(9).setPreferredWidth(30);
		
		clrPanelBordes = new clrPanelBordes(false);
		clrPanelBordes.setLayout(null);
		clrPanelBordes.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) Colores.clrPrincipal), "Información de pago", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
		clrPanelBordes.setBounds(10, 84, 645, 95);
		contentPane.add(clrPanelBordes);
		
		clrLabel_5 = new clrLabel("Se totalizara con la tarifa :", 1);
		clrLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		clrLabel_5.setForeground(Colores.clrTextoInactivo);
		clrLabel_5.setAlignmentX(0.5f);
		clrLabel_5.setBounds(221, 15, 186, 28);
		clrPanelBordes.add(clrLabel_5);
		
		cbTarifas = new clrComboBox(conexionCombos.getInstance().listaTarifas(),1);
		cbTarifas.setSelectedIndex(1);
		cbTarifas.setFocusCycleRoot(false);
		cbTarifas.setBounds(10, 17, 201, 25);
		cbTarifas.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				tarifaActual=conexionBusqueda.getInstance().TarifaXnombre(cbTarifas.getSelectedItem()+"");
				lblTarifa.setText(tarifaActual.getDescripcion());
			}
		});
		clrPanelBordes.add(cbTarifas);
		
		lblTarifa = new clrLabel(tarifaActual.getDescripcion(), 1);
		lblTarifa.setHorizontalAlignment(SwingConstants.LEFT);
		lblTarifa.setForeground(Colores.clrPrincipal);
		lblTarifa.setAlignmentX(0.5f);
		lblTarifa.setBounds(417, 15, 218, 28);
		clrPanelBordes.add(lblTarifa);
		
		txtCotizadoA = new ClrCuadroDeTexto(100,false,"Cotizado a",1);
		txtCotizadoA.setFocusCycleRoot(false);
		txtCotizadoA.setBounds(10, 53, 225, 25);
		clrPanelBordes.add(txtCotizadoA);
		
		txtDireccion = new ClrCuadroDeTexto(100,false,"Direcci\u00F3n");
		txtDireccion.setFocusCycleRoot(false);
		txtDireccion.setBounds(245, 54, 225, 25);
		clrPanelBordes.add(txtDireccion);
		
		txtTelefono = new ClrCuadroDeTexto(12,true,"Telefono");
		txtTelefono.setFocusCycleRoot(false);
		txtTelefono.setBounds(480, 54, 155, 25);
		clrPanelBordes.add(txtTelefono);

		panelExamenes = new clrPanelBordes(false);
		panelExamenes.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) Colores.clrPrincipal), "Examenes para esta recepción", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
		panelExamenes.setBounds(10, 179, 645, 429);
		panelExamenes.setLayout(null);
		contentPane.add(panelExamenes);
		panelExamenes.add(scrollPane);

		txtNombreExamen = new ClrCuadroDeTexto(100,false,"Nombre de examen", true);
		txtNombreExamen.setFocusCycleRoot(false);
		txtNombreExamen.setBounds(164, 34, 215, 25);
		txtNombreExamen.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				lb2.setVisible(false);

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (lb2 == null) {
					lb2 = new lbExamenXnombre(txtNombreExamen, esta, panelExamenes, esta.principal);
				}

			}
		});
		panelExamenes.add(txtNombreExamen);

		txtCodExamen = new ClrCuadroDeTexto(6,false,"Código", true);
		txtCodExamen.setFocusCycleRoot(false);
		txtCodExamen.setBounds(25, 34, 129, 20);
		txtCodExamen.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				lb1.setVisible(false);

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (lb1 == null) {
					lb1 = new lbExamenXid(txtCodExamen, esta, panelExamenes, esta.principal);
				}

			}
		});
		panelExamenes.add(txtCodExamen);

		txtValor = new ClrCuadroDeTexto(11,true,"Valor");
		txtValor.setFocusCycleRoot(false);
		txtValor.setEnabled(false);
		txtValor.setBounds(488, 34, 129, 20);
		panelExamenes.add(txtValor);

		txtDuracion = new ClrCuadroDeTexto(11,true,"Duración");
		txtDuracion.setFocusCycleRoot(false);
		txtDuracion.setVisible(false);
		txtDuracion.setBounds(389, 34, 89, 25);
		panelExamenes.add(txtDuracion);

		txtTotalExamen = new ClrCuadroDeTexto(50,true,"Total examen");
		txtTotalExamen.setFocusCycleRoot(false);
		txtTotalExamen.setEnabled(false);
		txtTotalExamen.setBounds(488, 104, 129, 20);
		panelExamenes.add(txtTotalExamen);

		btnAgregarExamen = new btnRedondo("", new Rectangle(48, 172,121,50), 4);
		btnAgregarExamen.setSelected(true);
		btnAgregarExamen.setBounds(507, 140, 50, 50);
		btnAgregarExamen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				agregarExamen();

			}
		});
		panelExamenes.add(btnAgregarExamen);

		btnEliminarExamen = new btnRedondo("", new Rectangle(48, 172,121,50), 5);
		btnEliminarExamen.setSelected(true);
		btnEliminarExamen.setBounds(567, 140, 50, 50);
		btnEliminarExamen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				eliminarExamen();

			}

			
		});
		panelExamenes.add(btnEliminarExamen);

		chkUrgencias = new clrCheckBox("Recargo urgencia");
		chkUrgencias.setBounds(25, 70, 158, 23);
		chkUrgencias.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {

				if (chkUrgencias.isSelected()) {
					okUrgencias = true;
					lblPorcientoUrgencias.setForeground(Colores.clrSecundario);
					calcularTotal();
				} else {
					okUrgencias = false;
					lblPorcientoUrgencias.setForeground(Color.black);
					calcularTotal();
				}
			}
		});
		panelExamenes.add(chkUrgencias);

		chkFestivos = new clrCheckBox("Recargo festivos");
		chkFestivos.setBounds(182, 70, 147, 23);
		chkFestivos.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {

				if (chkFestivos.isSelected()) {
					okFestivos = true;
					lblPorcientoFestivos.setForeground(Colores.clrSecundario);
					calcularTotal();
				} else {
					okFestivos = false;
					lblPorcientoFestivos.setForeground(Color.black);
					calcularTotal();
				}
			}
		});
		panelExamenes.add(chkFestivos);

		chkEspecial = new clrCheckBox("Recargo especial");
		chkEspecial.setBounds(339, 70, 147, 23);
		chkEspecial.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {

				if (chkEspecial.isSelected()) {
					okEspecial = true;
					lblPorcientoEspecial.setForeground(Colores.clrSecundario);
					calcularTotal();
				} else {
					okEspecial = false;
					lblPorcientoEspecial.setForeground(Color.black);
					calcularTotal();
				}
			}
		});
		panelExamenes.add(chkEspecial);

		panelPerfiles = new clrPanelBordes(false);
		panelPerfiles.setLayout(null);
		panelPerfiles.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) Colores.clrPrincipal), "Agregar perfiles", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
		panelPerfiles.setBounds(10, 124, 468, 66);
		panelExamenes.add(panelPerfiles);

		txtCodPerfil = new ClrCuadroDeTexto(11,true,"Código perfil", true);
		txtCodPerfil.setFocusCycleRoot(false);
		txtCodPerfil.setBounds(10, 19, 113, 25);
		txtCodPerfil.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				lb3.setVisible(false);

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (lb3 == null) {
					lb3 = new lbPerfilesXcodigo(txtCodPerfil, esta, panelExamenes, panelPerfiles, esta.principal);
				}

			}
		});
		panelPerfiles.add(txtCodPerfil);

		txtNombrePerfil = new ClrCuadroDeTexto(100,false,"Nombre de perfil",true);
		txtNombrePerfil.setBounds(133, 19, 205, 25);
		txtNombrePerfil.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				lb4.setVisible(false);
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (lb4==null) {
					lb4 = new lbPerfilesXnombre(txtNombrePerfil, esta, panelExamenes, panelPerfiles, esta.principal);

				}
				
			}
		});
		panelPerfiles.add(txtNombrePerfil);

		btnAgregarPerfil = new btnRedondo("", new Rectangle(48, 172,121,50), 4);
		btnAgregarPerfil.setSelected(true);
		btnAgregarPerfil.setBounds(348, 11, 50, 50);
		btnAgregarPerfil.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (perfilPorAgregar) {
					agregarPerfil();
				} else {
					lblincorrectos.setText("No hay perfil por agregar");
				}

			}
		});
		panelPerfiles.add(btnAgregarPerfil);

		btnElimiinarPerfil = new btnRedondo("", new Rectangle(48, 172,121,50), 5);
		btnElimiinarPerfil.setSelected(true);
		btnElimiinarPerfil.setBounds(408, 11, 50, 50);
		btnElimiinarPerfil.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (perfilPorAgregar) {
					limpiarPerfil();
				} else {
					lblincorrectos.setText("No hay perfil por Limpiar");
				}

			}
		});
		panelPerfiles.add(btnElimiinarPerfil);

		txtTotalRecargos = new ClrCuadroDeTexto(11,true,"Total recargos");
		txtTotalRecargos.setFocusCycleRoot(false);
		txtTotalRecargos.setEnabled(false);
		txtTotalRecargos.setBounds(488, 70, 129, 25);
		panelExamenes.add(txtTotalRecargos);

		lblPorcientoUrgencias = new clrLabel("", 1);
		lblPorcientoUrgencias.setHorizontalAlignment(SwingConstants.CENTER);
		lblPorcientoUrgencias.setForeground(Colores.clrTextoInactivo);
		lblPorcientoUrgencias.setBounds(35, 99, 119, 14);
		panelExamenes.add(lblPorcientoUrgencias);

		lblPorcientoFestivos = new clrLabel("", 1);
		lblPorcientoFestivos.setHorizontalAlignment(SwingConstants.CENTER);
		lblPorcientoFestivos.setForeground(Colores.clrTextoInactivo);
		lblPorcientoFestivos.setBounds(192, 99, 129, 14);
		panelExamenes.add(lblPorcientoFestivos);

		lblPorcientoEspecial = new clrLabel("", 1);
		lblPorcientoEspecial.setHorizontalAlignment(SwingConstants.CENTER);
		lblPorcientoEspecial.setForeground(Colores.clrTextoInactivo);
		lblPorcientoEspecial.setBounds(349, 99, 119, 14);
		panelExamenes.add(lblPorcientoEspecial);

		clrPanelBordes clrPanelBordes_ = new clrPanelBordes(false);
		clrPanelBordes_.setBounds(10, 637, 645, 136);
		contentPane.add(clrPanelBordes_);
		clrPanelBordes_.setLayout(null);
		clrPanelBordes_.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) Colores.clrPrincipal), "Información de pago", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));

		txtTotalDescuentos = new ClrCuadroDeTexto(15,true,"Total descuentos");
		txtTotalDescuentos.setFocusCycleRoot(false);
		txtTotalDescuentos.setBounds(481, 50, 149, 25);
		txtTotalDescuentos.setEnabled(false);
		clrPanelBordes_.add(txtTotalDescuentos);

		txtTotalExamenes = new ClrCuadroDeTexto(15,true,"Total examenes");
		txtTotalExamenes.setFocusCycleRoot(false);
		txtTotalExamenes.setBounds(481, 14, 149, 25);
		txtTotalExamenes.setEnabled(false);
		clrPanelBordes_.add(txtTotalExamenes);

		txtDescuentoPesos = new ClrCuadroDeTexto(15,true,"Descuento en pesos");
		txtDescuentoPesos.setFocusCycleRoot(false);
		txtDescuentoPesos.setBounds(33, 16, 149, 25);
		txtDescuentoPesos.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				// / aqui vamos manito fata cuadrar bien estas condiciones para
				// calcular descuentos
				int auxi = 0;
				if (!txtDescuentoPesos.getText().equals("") && !txtDescuentoPesos.getText().equals("Descuento en pesos")&&calcularTotalExamenesEntero()>=0) {

					auxi = Integer.parseInt(txtDescuentoPesos.getText());

				}

				
					calcularTotalDescuentosXpesos(auxi);
				

			}

		});
		clrPanelBordes_.add(txtDescuentoPesos);

		txtDescuentoPorciento = new ClrCuadroDeTexto(3,true,"% descuento");
		txtDescuentoPorciento.setFocusCycleRoot(false);
		txtDescuentoPorciento.setBounds(33, 54, 149, 25);
		txtDescuentoPorciento.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				int auxi = 0;
				if (!txtDescuentoPorciento.getText().equals("") && !txtDescuentoPorciento.getText().equals("Porcentaje de descuento")&&calcularTotalExamenesEntero()>=0) {

					auxi = Integer.parseInt(txtDescuentoPorciento.getText());

				}

			
					calcularTotalDescuentosXporciento(auxi);
	

			}

		});
		clrPanelBordes_.add(txtDescuentoPorciento);

		chkImprimirDirecto = new clrCheckBox("Imprimir directamente en impresora");
		chkImprimirDirecto.setBounds(10, 90, 287, 23);
		clrPanelBordes_.add(chkImprimirDirecto);

		txtTotalFactura = new ClrCuadroDeTexto(15,true,"Total factura");
		txtTotalFactura.setFocusCycleRoot(false);
		txtTotalFactura.setEnabled(false);
		txtTotalFactura.setBounds(481, 86, 149, 25);
		clrPanelBordes_.add(txtTotalFactura);

		clrLabel clrLabel__3 = new clrLabel("Valor a cancelar $", 1);
		clrLabel__3.setHorizontalAlignment(SwingConstants.RIGHT);
		clrLabel__3.setForeground(Colores.clrPrincipal);
		clrLabel__3.setAlignmentX(0.5f);
		clrLabel__3.setBounds(322, 86, 149, 28);
		clrPanelBordes_.add(clrLabel__3);

		clrLabel clrLabel__4 = new clrLabel("Descuentos $", 1);
		clrLabel__4.setHorizontalAlignment(SwingConstants.RIGHT);
		clrLabel__4.setForeground(Colores.clrPrincipal);
		clrLabel__4.setAlignmentX(0.5f);
		clrLabel__4.setBounds(322, 47, 149, 28);
		clrPanelBordes_.add(clrLabel__4);

		clrLabel clrLabel__5 = new clrLabel("Total $", 1);
		clrLabel__5.setHorizontalAlignment(SwingConstants.RIGHT);
		clrLabel__5.setForeground(Colores.clrPrincipal);
		clrLabel__5.setAlignmentX(0.5f);
		clrLabel__5.setBounds(322, 14, 149, 28);
		clrPanelBordes_.add(clrLabel__5);
		
		clrScrollBar clrScrollBar_ = new clrScrollBar();
		clrScrollBar_.setBounds(192, 13, 184, 64);
		clrPanelBordes_.add(clrScrollBar_);
		
		txtObs = new clrtextpane(255,false,"Observaciones cotizaci\u00F3n",0);
		clrScrollBar_.setViewportView(txtObs);
		
		clrLabel = new clrLabel("$", 1);
		clrLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		clrLabel.setForeground(Colores.clrPrincipal);
		clrLabel.setAlignmentX(0.5f);
		clrLabel.setBounds(10, 14, 20, 28);
		clrPanelBordes_.add(clrLabel);
		
		clrLabel_1 = new clrLabel("%", 1);
		clrLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		clrLabel_1.setForeground(Colores.clrPrincipal);
		clrLabel_1.setAlignmentX(0.5f);
		clrLabel_1.setBounds(10, 51, 20, 28);
		clrPanelBordes_.add(clrLabel_1);
		
		clrLabel_2 = new clrLabel("F=Facturado / RU=Recargo urgencias / RF=Recargo festivos / RE=Recargo especial ", 1);
		clrLabel_2.setText("Rem=Se remite / F=Facturado / RU=Recargo urgencias / RF=Recargo festivos / RE=Recargo especial ");
		clrLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		clrLabel_2.setFont(Colores.fuentePequena);
		clrLabel_2.setForeground(Colores.clrPrincipal);
		clrLabel_2.setAlignmentX(0.5f);
		clrLabel_2.setBounds(10, 606, 645, 28);
		contentPane.add(clrLabel_2);
		
		clrLabel clrLabel_ = new clrLabel("Cotización # "+String.format("%05d", (conexionBusqueda.getInstance().ultimaCotizacion())+1),1);
		clrLabel_.setHorizontalAlignment(SwingConstants.LEFT);
		clrLabel_.setForeground(Colores.clrTextoPrincipal);
		clrLabel_.setAlignmentX(0.5f);
		clrLabel_.setBounds(10, 11, 149, 28);
		contentPane.add(clrLabel_);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				btnGuardarYSalir.requestFocusInWindow();
			}
		});

		this.setVisible(true);

	}

	public void llenarExamen(Examen examen) {

		txtCodExamen.setText(examen.getSigla());
		txtCodExamen.setForeground(Color.BLACK);

		txtNombreExamen.setText(examen.getNombre());
		txtNombreExamen.setForegroundd(Color.BLACK);
		System.out.println(tarifaActual.getIdTarifa());
		int[] valores = conexionBusqueda.getInstance().busquedaValoresXExamen(examen.getIdExamen(), tarifaActual.getIdTarifa());
		valorInicial = valores[0];
		porcientoUrgencias = valores[1];
		porcientoFestivos = valores[2];
		porcientoEspecial = valores[3];

		lblPorcientoUrgencias.setText(valores[1] + "%");
		lblPorcientoUrgencias.setForeground(Color.black);
		lblPorcientoFestivos.setText(valores[2] + "%");
		lblPorcientoFestivos.setForeground(Color.black);
		lblPorcientoEspecial.setText(valores[3] + "%");
		lblPorcientoEspecial.setForeground(Color.black);
		System.out.println("recargo urgencias " + valores[3]);

		calcularTotal();
		txtValor.setText("" + valores[0]);
		txtTotalExamen.setForeground(Color.BLACK);

		auxiexamen = examen;

	}

	public void llenarPerfilExamen(PerfilExamenes Pexamen) {
		
		System.out.println("si hay por agregar?"+perfilPorAgregar);
		if (perfilPorAgregar) {

			ventanaPregunta pregunta = new ventanaPregunta(OpItemCotizacion.this.principal, "No has agregado los examenes del perfil a esta recepción, deseas agregarlos ahora", "Si", "No");
			if (pregunta.escuchar()) {
				agregarPerfil();
			} else {
				limpiarPerfil();
			}
		}
		
		txtNombrePerfil.setText(Pexamen.getNombrePerfil());
		txtNombrePerfil.setForeground(Color.BLACK);

		txtCodPerfil.setText("" + Pexamen.getIdPerfil());
		txtCodPerfil.setForeground(Color.BLACK);

		ArrayList<Examen> listaExamenes = Pexamen.getExamenesDelPerfil();
		int totalPerfil = 0;
		for (int i = 0; i < listaExamenes.size(); i++) {
			Examen Ex = listaExamenes.get(i);
			int[] valores = conexionBusqueda.getInstance().busquedaValoresXExamen(Ex.getIdExamen(),tarifaActual.getIdTarifa());
			String[] paratabla = new String[10];
			String auxiRemite = "si";
			if (Ex.getSeRemite() == 0) {
				auxiRemite = "no";
			}
			;
			paratabla[0] = Ex.getSigla();
			paratabla[1] = Ex.getNombre();
			paratabla[2] = Ex.getDuracion() + "";
			paratabla[3] = auxiRemite;
			paratabla[4] = Ex.getCups() + "";
			paratabla[5] = valores[0] + "";
			paratabla[6] = "";
			paratabla[7] = "";
			paratabla[8] = "";
			paratabla[9] = "no";

			TM.addRow(paratabla);
			porFacturar++;

			totalPerfil = totalPerfil + valores[0];

		}

		int grantotalTentativo = calcularTotalExamenesEntero();

		String[] paratabla1 = new String[10];
		paratabla1[0] = "";
		paratabla1[1] = "";
		paratabla1[2] = "";
		paratabla1[3] = "";
		paratabla1[4] = "T. perfil $";
		paratabla1[5] = totalPerfil + "";
		paratabla1[6] = "";
		paratabla1[7] = "";
		paratabla1[8] = "";
		paratabla1[9] = "--";

		String[] paratabla2 = new String[10];
		paratabla2[0] = "";
		paratabla2[1] = "";
		paratabla2[2] = "";
		paratabla2[3] = "";
		paratabla2[4] = "T. final $";
		paratabla2[5] = grantotalTentativo + "";
		paratabla2[6] = "";
		paratabla2[7] = "";
		paratabla2[8] = "";
		paratabla2[9] = "--";

		TM.addRow(paratabla1);
		TM.addRow(paratabla2);
		perfilPorAgregar = true;
	}

	public void limpiarItems() {
		txtNombreExamen.setText("Nombre de item");

		txtNombreExamen.setForeground(Colores.clrTextoInactivo);

	}

	public void calcularTotal() {
		int valor = valorInicial;
		int totalRecargos = 0;

		if (okUrgencias) {
			valor = valor + ((valorInicial * porcientoUrgencias) / 100);
			totalRecargos = totalRecargos + ((valorInicial * porcientoUrgencias) / 100);
		}

		if (okFestivos) {
			valor = valor + ((valorInicial * porcientoFestivos) / 100);
			totalRecargos = totalRecargos + ((valorInicial * porcientoFestivos) / 100);

		}

		if (okEspecial) {
			valor = valor + ((valorInicial * porcientoEspecial) / 100);
			totalRecargos = totalRecargos + ((valorInicial * porcientoEspecial) / 100);

		}

		txtTotalExamen.setText("" + valor);
		txtTotalRecargos.setText("" + totalRecargos);

		txtValor.setForeground(Color.BLACK);
		txtTotalRecargos.setForeground(Color.BLACK);

	}

	public int calcularTotalExamenEntero() {
		int valor = valorInicial;
		int totalRecargos = 0;

		if (okUrgencias) {
			valor = valor + ((valorInicial * porcientoUrgencias) / 100);
			totalRecargos = totalRecargos + ((valorInicial * porcientoUrgencias) / 100);
		}

		if (okFestivos) {
			valor = valor + ((valorInicial * porcientoFestivos) / 100);
			totalRecargos = totalRecargos + ((valorInicial * porcientoFestivos) / 100);

		}

		if (okEspecial) {
			valor = valor + ((valorInicial * porcientoEspecial) / 100);
			totalRecargos = totalRecargos + ((valorInicial * porcientoEspecial) / 100);

		}

		return valor;

	}

	public void agregarExamen() {
		if (auxiexamen == null) {
			lblincorrectos.setText("Ningun examen por agregar");
		} else {
			String[] paratabla = new String[10];
			String auxiRemite = "si";
			if (auxiexamen.getSeRemite() == 0) {
				auxiRemite = "no";
			}
			;

			paratabla[0] = auxiexamen.getSigla();
			paratabla[1] = auxiexamen.getNombre();
			paratabla[2] = auxiexamen.getDuracion() + "";
			paratabla[3] = auxiRemite;
			paratabla[4] = auxiexamen.getCups() + "";
			paratabla[5] = calcularTotalExamenEntero() + "";
			
			if (okUrgencias) {
				paratabla[6] = "si";
			} else {
				paratabla[6] = "";
			}
			
			if (okFestivos) {
				paratabla[7] = "si";
			} else {
				paratabla[7] = "";
			}
			
			if (okEspecial) {
				paratabla[8] = "si";
			} else {
				paratabla[8] = "";
			}
			
			paratabla[9] = "si";
			calcularTotalExamenes();
			TM.addRow(paratabla);
	
			limpiarCamposExamen();
		}

		calcularTotalExamenes();
	}

	public void limpiarCamposExamen() {
		txtNombreExamen.reiniciar();
		txtCodExamen.reiniciar();
		txtValor.reiniciar();
		txtTotalRecargos.reiniciar();
		txtTotalExamen.reiniciar();
		lblPorcientoUrgencias.reiniciar();
		lblPorcientoFestivos.reiniciar();
		lblPorcientoEspecial.reiniciar();
		chkUrgencias.reiniciar();
		chkFestivos.reiniciar();
		chkEspecial.reiniciar();
		auxiexamen = null;

		porcientoUrgencias = 0;
		porcientoFestivos = 0;
		porcientoEspecial = 0;
		valorInicial = 0;
		calcularTotal();

	}

	public void limpiarPerfil() {
		System.out.println("por facturar>>>" + porFacturar);
		System.out.println("rowcount>>>" + TM.getRowCount());
		txtCodPerfil.reiniciar();
		txtNombrePerfil.reiniciar();
		TM.removeRow(TM.getRowCount() - 1);
		TM.removeRow(TM.getRowCount() - 1);
		int limite = (TM.getRowCount() - porFacturar + 1);
		for (int i = TM.getRowCount(); i >= limite; i--) {
			TM.removeRow(i - 1);
		}
		porFacturar = 0;
		perfilPorAgregar = false;
	}

	public void agregarPerfil() {
		System.out.println("por facturar>>>" + porFacturar);
		System.out.println("rowcount>>>" + TM.getRowCount());
		txtCodPerfil.reiniciar();
		txtNombrePerfil.reiniciar();

		TM.removeRow(TM.getRowCount() - 1);
		TM.removeRow(TM.getRowCount() - 1);

		int limite = (TM.getRowCount() - porFacturar + 1);
		for (int i = TM.getRowCount(); i >= limite; i--) {
			table.setValueAt("si", i - 1, 9);
		}
		table.updateUI();
		porFacturar = 0;
		perfilPorAgregar = false;
		calcularTotalExamenes();
	}

	public void calcularTotalExamenes() {
		if (perfilPorAgregar) {
			ventanaPregunta pregunta = new ventanaPregunta(OpItemCotizacion.this.principal, "No has agregado los examenes del perfil a esta recepción, deseas agregarlos ahora", "Si", "No");
			if (pregunta.escuchar()) {
				agregarPerfil();
			} else {
				limpiarPerfil();
			}
		}
		int salida = 0;
		for (int i = 0; i < TM.getRowCount(); i++) {
			salida = salida + Integer.parseInt(TM.getValueAt(i, 5).toString());
		}

		System.out.println("" + salida);
		txtTotalExamenes.setText("" + salida);
		calcularTotalFactura();
	}

	public void calcularTotalFactura() {

		int auxi = 0;
		if (!txtDescuentoPorciento.getText().equals("") && !txtDescuentoPorciento.getText().equals(txtDescuentoPorciento.getLabel())) {

			auxi = Integer.parseInt(txtDescuentoPorciento.getText());

		}

		
			calcularTotalDescuentosXporciento(auxi);
	

	}

	public int calcularTotalExamenesEntero() {
		int salida = 0;
		for (int i = 0; i < TM.getRowCount(); i++) {
			salida = salida + Integer.parseInt(TM.getValueAt(i, 5).toString());
		}

		return salida;
	}

	public void calcularTotalDescuentosXpesos(int valor) {
		if (valor>calcularTotalExamenesEntero()) {
			txtDescuentoPesos.borrar();
			lblincorrectos.setText("El descuento excede el valor total de los examenes");
		} else {
			lblincorrectos.setText("");

		
		int porcentaje = (100 * valor) / calcularTotalExamenesEntero();
		txtDescuentoPorciento.setText("" + porcentaje);
		txtTotalDescuentos.setText("" + valor);
		
		int residuo = (calcularTotalExamenesEntero() - valor) %100;
		int div=0;
		if (residuo>=50) {
			div=((calcularTotalExamenesEntero() - valor) / 100)+1;
		}else{
			div=((calcularTotalExamenesEntero() - valor) / 100);

		}
		txtTotalFactura.setText("" + (div) * 100);
		
	}
	}

	public void calcularTotalDescuentosXporciento(int valor) {
		if (valor != 0) {
			if (valor>100) {
				txtDescuentoPorciento.borrar();
				lblincorrectos.setText("El valor del descuento no puede ser mayor al total del valor de los examenes");
			} else {
				lblincorrectos.setText("");
			
			int descuentoPesos = (calcularTotalExamenesEntero() * valor) / 100;
			System.out.println("descuento pesos >>>> " + descuentoPesos);
			txtDescuentoPesos.setText("" + descuentoPesos);
			txtTotalDescuentos.setText("" + descuentoPesos);
			int residuo = (calcularTotalExamenesEntero() - descuentoPesos) %100;
			int div=0;
			if (residuo>=50) {
				div=((calcularTotalExamenesEntero() - descuentoPesos) / 100)+1;
			}else{
				div=((calcularTotalExamenesEntero() - descuentoPesos) / 100);

			}
			txtTotalFactura.setText("" + (div) * 100);
}
		} else {
			txtDescuentoPesos.reiniciar();
			txtTotalDescuentos.reiniciar();
			
			int residuo = (calcularTotalExamenesEntero() - 0) %100;
			int div=0;
			if (residuo>50) {
				div=((calcularTotalExamenesEntero() - 0) / 100)+1;
			}else{
				div=((calcularTotalExamenesEntero() - 0) / 100);

			}
			txtTotalFactura.setText("" + (div) * 100);
			
			txtTotalFactura.setText("" + (((calcularTotalExamenesEntero() - 0)) / 100) * 100);
		}

	}


	public void guargarRecepcion(){
		calcularTotalExamenes();
		if (TM.getRowCount()<1) {
			lblincorrectos.setText("No has agregado examenes para esta cotización");
			
		} else {
			if (txtCotizadoA.getText().equals(txtCotizadoA.getLabel())) {
				lblincorrectos.setText("A quien va dirigida la cotización");
				
			} else {
				
				
				
				lblincorrectos.setText("");

				
				
				int auxiDescuentoPorciento=0;if(!txtDescuentoPorciento.getText().equals("")&&!txtDescuentoPorciento.getText().equals(txtDescuentoPorciento.getLabel())){auxiDescuentoPorciento=Integer.parseInt(txtDescuentoPorciento.getText());}
				String obs="";if (!txtObs.getText().equals(txtObs.getLabel())) {obs=txtObs.getText();}
				
				ArrayList<itemCotizacion> listaItems= new ArrayList<itemCotizacion>();
			
					
					for (int j = 0; j < TM.getRowCount(); j++) {
						int auxiRU=0;if((TM.getValueAt(j, 6)+"").equals("si")){auxiRU=1;};
						int auxiRF=0;if((TM.getValueAt(j,7)+"").equals("si")){auxiRF=1;};
						int auxiRE=0;if((TM.getValueAt(j, 8)+"").equals("si")){auxiRE=1;};
						
						
					itemCotizacion IC=new itemCotizacion(-1, -1, TM.getValueAt(j, 0).toString(), auxiRU, auxiRF, auxiRE, Integer.parseInt(TM.getValueAt(j, 5).toString()), j+1);
					listaItems.add(IC);
					}
					String auxidir="";if(!txtDireccion.getText().equals(txtDireccion.getLabel())){auxidir=txtDireccion.getText();};
					String auxiTel="0";if(!txtTelefono.getText().equals(txtTelefono.getLabel())){auxiTel=txtTelefono.getText();};
					
									Cotizacion coti=new Cotizacion(-1, txtCotizadoA.getText(),auxidir,auxiTel, null, tarifaActual.getIdTarifa(),auxiDescuentoPorciento , Integer.parseInt(txtTotalExamenes.getText()), Integer.parseInt(txtTotalFactura.getText()), listaItems,obs);

				if (conexion.getInstance().nuevoCotizacion(esta, coti)) {
					esta.principal.registrarAccion("Creación de cotización '"+(conexionBusqueda.getInstance().ultimaCotizacion())+"'");
					
					new ImprimirCotizacion(conexionBusqueda.getInstance().CotizacionCompleta((conexionBusqueda.getInstance().ultimaCotizacion())+""), chkImprimirDirecto.isSelected());
					dispose();
				} else{
					lblincorrectos.setText("Error  en la conexion con la base de datos");
				}
				

				
			
			

				}
		}
	}
	
	public void eliminarExamen() {
		if (table.getSelectedColumn()!=-1) {
			TM.removeRow(table.getSelectedRow());
			calcularTotalExamenes();
		}	else {
			lblincorrectos.setText("No has seleccionado ningun examen");
		}
	}
	
	public void reportarError(String error){
		lblincorrectos.setText(error);
	}
}
