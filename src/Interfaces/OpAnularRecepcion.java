package Interfaces;

import interfaces_Modificadas.ClrCuadroDeTexto;
import interfaces_Modificadas.Colores;
import interfaces_Modificadas.MiTableModelTarifas;
import interfaces_Modificadas.StringDigitalCellEditorr;
import interfaces_Modificadas.btnRedondo;
import interfaces_Modificadas.circuloUsuario;
import interfaces_Modificadas.clrCheckBox;
import interfaces_Modificadas.clrComboBox;
import interfaces_Modificadas.clrLabel;
import interfaces_Modificadas.clrPanelBordes;
import interfaces_Modificadas.clrRadioButton;
import interfaces_Modificadas.clrScrollBar;
import interfaces_Modificadas.clrSeparador;
import interfaces_Modificadas.clrtextpane;
import interfaces_Modificadas.miRenderTabla;

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
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import javax.swing.JDialog;
import javax.swing.SwingConstants;

import ago.beans.CirclePanel;
import auxiliares.Bacteriologo;
import auxiliares.Empresa;
import auxiliares.GruposEmpresas;
import auxiliares.Medico;
import auxiliares.OTD;
import auxiliares.Paciente;
import auxiliares.Recepcion;
import auxiliares.RecepcionCompleta;
import auxiliares.Secciones;
import auxiliares.Tarifa;
import auxiliares.TipoDato;
import auxiliares.Usuario;
import auxiliares.Examen;
import auxiliares.itemProtocolo;
import auxiliares.itemTarifa;
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
import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.Document;
import javax.swing.text.TabExpander;
import javax.swing.text.TabableView;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

public class OpAnularRecepcion extends JDialog {
	private clrPanelBordes contentPane;
	private clrLabel lblincorrectos;
	private Principal principal;
	private Usuario usuario;
	private String accion;
	private clrLabel lblinicio;
	private circuloUsuario btnRedondo_;

	private int idDias;
	private String codigoAntiguo;

	private protocolo elPprotocolo = null;
	private btnRedondo btnCancelar;
	private btnRedondo btnAnular;
	private lbRecepcionXId lb1;
	private lbMedicoXid lb7;
	private lbMedicoXnombre lb8;
	private lbMedicoXApellido lb9;
	private MiTableModelTarifas TM;
	private ArrayList<itemTarifa> paraEliminar = new ArrayList<itemTarifa>();
	private ArrayList<itemTarifa> paraAgregar = new ArrayList<itemTarifa>();
	private ArrayList<itemTarifa> paraModificar = new ArrayList<itemTarifa>();
	private int[] dias = new int[] { 0, 0, 0, 0, 0, 0, 0 };
	private clrPanelBordes panelEmpresa;
	private JDateChooser fchMuestra;
	private JDateChooser fchOrden;
	private ClrCuadroDeTexto txtNitEmpresa;
	private ClrCuadroDeTexto txtRazonSocialEmpresa;
	private clrComboBox cbSubGrupoEmpresa;
	private clrLabel lblTarifa;
	private ClrCuadroDeTexto txtAutorizacion;
	private ClrCuadroDeTexto txtNumeroOrden;
	private clrtextpane txtRequisitos;
	private clrLabel lblFechaOrden;
	private clrComboBox cbPlanesPYP;
	private clrComboBox cbSalas;
	private clrComboBox cbTipoUsuario;
	private clrComboBox cbAmbito;
	private clrComboBox cbFinalidad;
	private ClrCuadroDeTexto txtIDMedico;
	private ClrCuadroDeTexto txtNombreMedico;
	private ClrCuadroDeTexto txtNumeroMuestra;
	private ClrCuadroDeTexto txtNumeroCama;
	private clrLabel lblFechaToma;
	private clrLabel lblFechaRecepcion;
	private ClrCuadroDeTexto txtApellidoMedico;
	private clrPanelBordes panelPaciente;
	private ClrCuadroDeTexto txtIDPaciente;
	private ClrCuadroDeTexto txtNombrePaciente;
	private ClrCuadroDeTexto txtApellidoPaciente;
	private ClrCuadroDeTexto txtEdadPaciente;
	private ClrCuadroDeTexto txtTelPaciente;
	private ClrCuadroDeTexto txtFechaRecepcion;
	private clrPanelBordes panelRecepcion;
	private clrPanelBordes panelBuscarRecepcion;
	private ClrCuadroDeTexto txtCodEmpresa;
	private ClrCuadroDeTexto txtNumRecepcion;
	private Calendar hoy = new GregorianCalendar();
	private clrtextpane txtObservaciones;

	private Paciente paciente;
	private Medico medico;
	private Empresa empresa;
	private Tarifa tarifa;
	private int codSubEmpresa;
	private int idRecepcion = -1;
	private OpAnularRecepcion esta;
	private clrLabel lblTarifaActual;

	public OpAnularRecepcion(Principal principal, Usuario usuario) {
		super(principal, true);
		this.principal = principal;
		this.usuario = usuario;
		this.esta = this;
		this.accion = accion;

		// this.setDefaultLookAndFeelDecorated(false);

		setBounds(100, 100, 1056, 655);
		this.setUndecorated(true);
		this.setLocationRelativeTo(getParent());
		contentPane = new clrPanelBordes(true);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		panelBuscarRecepcion = new clrPanelBordes(false);
		panelBuscarRecepcion.setBounds(10, 89, 248, 71);
		contentPane.add(panelBuscarRecepcion);
		panelBuscarRecepcion.setLayout(null);
		panelBuscarRecepcion.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(28, 188, 158)), "Recepci\u00F3n a cambiar", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(28, 188, 158)));

		txtNumRecepcion = new ClrCuadroDeTexto(15, true, "# de recepci\u00F3n", true, 1);
		txtNumRecepcion.setFocusCycleRoot(false);
		txtNumRecepcion.setBounds(10, 28, 226, 25);
		txtNumRecepcion.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				lb1.setVisible(false);

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (lb1 == null) {
					lb1 = new lbRecepcionXId(txtNumRecepcion, esta, panelBuscarRecepcion, esta.principal);
				}

			}
		});
		panelBuscarRecepcion.add(txtNumRecepcion);

		lblinicio = new clrLabel("Cambiar datos a una recepci\u00F3n", 2, true);
		lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblinicio.setBounds(10, 11, 1036, 28);
		contentPane.add(lblinicio);

		lblincorrectos = new clrLabel("", 1);
		lblincorrectos.setForeground(Colores.clrAlertaCamarada);
		lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
		lblincorrectos.setAlignmentX(0.5f);
		lblincorrectos.setBounds(10, 50, 1036, 28);
		contentPane.add(lblincorrectos);

		btnCancelar = new btnRedondo("Salir", new Rectangle(237, 172,121,50), 9);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(541, 592, 136, 50);
		contentPane.add(btnCancelar);

		btnAnular = new btnRedondo("Anular", new Rectangle(48, 172,121,50), 5);
		btnAnular.setSelected(true);
		btnAnular.setBounds(352, 592, 169, 50);
		btnAnular.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cambiarDatos();

			}
		});
		contentPane.add(btnAnular);

		panelEmpresa = new clrPanelBordes(false);
		panelEmpresa.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Informacion de empresa y tarifa", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
		panelEmpresa.setBounds(268, 89, 778, 290);
		panelEmpresa.setLayout(null);
		contentPane.add(panelEmpresa);

		txtNitEmpresa = new ClrCuadroDeTexto(13, false, "NIT");
		txtNitEmpresa.setEnabled(false);
		txtNitEmpresa.setFocusCycleRoot(false);
		txtNitEmpresa.setBounds(115, 26, 168, 25);

		panelEmpresa.add(txtNitEmpresa);

		txtRazonSocialEmpresa = new ClrCuadroDeTexto(100, false, "Razón social");
		txtRazonSocialEmpresa.setEnabled(false);
		txtRazonSocialEmpresa.setFocusCycleRoot(false);
		txtRazonSocialEmpresa.setBounds(293, 26, 226, 25);

		panelEmpresa.add(txtRazonSocialEmpresa);

		cbSubGrupoEmpresa = new clrComboBox(new String[] {}, 0);
		cbSubGrupoEmpresa.setEnabled(false);
		cbSubGrupoEmpresa.setFocusCycleRoot(false);
		cbSubGrupoEmpresa.setBounds(529, 25, 226, 25);

		panelEmpresa.add(cbSubGrupoEmpresa);

		lblTarifa = new clrLabel("Tenga en cuenta que se facturara por tarifa de empresa o subgrupo de empresa.", 1);
		lblTarifa.setHorizontalAlignment(SwingConstants.CENTER);
		lblTarifa.setForeground(Colores.clrTextoInactivo);
		lblTarifa.setBounds(10, 62, 745, 14);
		panelEmpresa.add(lblTarifa);

		txtAutorizacion = new ClrCuadroDeTexto(20, false, "Número de autorización");
		txtAutorizacion.setEnabled(false);
		txtAutorizacion.setFocusCycleRoot(false);
		txtAutorizacion.setBounds(10, 107, 226, 25);
		panelEmpresa.add(txtAutorizacion);

		txtNumeroOrden = new ClrCuadroDeTexto(20, false, "Número de orden");
		txtNumeroOrden.setEnabled(false);
		txtNumeroOrden.setFocusCycleRoot(false);
		txtNumeroOrden.setBounds(10, 143, 226, 25);
		panelEmpresa.add(txtNumeroOrden);

		fchOrden = new JDateChooser();
		fchOrden.setBounds(10, 240, 226, 25);
		fchOrden.setCalendar(hoy);
		panelEmpresa.add(fchOrden);
		fchOrden.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, Colores.clrTextoInactivo));

		txtRequisitos = new clrtextpane(255, false, "Requisitos para recepción", 0);
		txtRequisitos.setBounds(260, 107, 495, 158);
		txtRequisitos.setEnabled(false);
		panelEmpresa.add(txtRequisitos);

		lblFechaOrden = new clrLabel("Fecha de la orden", 1);
		lblFechaOrden.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaOrden.setForeground(Colores.clrTextoInactivo);
		lblFechaOrden.setBounds(11, 218, 226, 14);
		panelEmpresa.add(lblFechaOrden);

		cbPlanesPYP = new clrComboBox(conexionCombos.getInstance().listaPlanesPP(), 0);
		cbPlanesPYP.setEnabled(false);
		cbPlanesPYP.setFocusCycleRoot(false);
		cbPlanesPYP.setBounds(10, 174, 226, 25);
		panelEmpresa.add(cbPlanesPYP);

		txtCodEmpresa = new ClrCuadroDeTexto(11, true, "Código");
		txtCodEmpresa.setEnabled(false);
		txtCodEmpresa.setFocusCycleRoot(false);
		txtCodEmpresa.setBounds(10, 26, 95, 25);

		panelEmpresa.add(txtCodEmpresa);

		lblTarifaActual = new clrLabel("", 1);
		lblTarifaActual.setHorizontalAlignment(SwingConstants.CENTER);
		lblTarifaActual.setForeground(Colores.clrTextoInactivo);
		lblTarifaActual.setBounds(10, 82, 745, 14);
		panelEmpresa.add(lblTarifaActual);

		panelRecepcion = new clrPanelBordes(false);
		panelRecepcion.setLayout(null);
		panelRecepcion.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Informaci\u00F3n adicional para esta recepci\u00F3n", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
		panelRecepcion.setBounds(10, 397, 1036, 184);
		contentPane.add(panelRecepcion);

		cbSalas = new clrComboBox(conexionCombos.getInstance().listaSalas(), 0);
		cbSalas.setEnabled(false);
		cbSalas.setFocusCycleRoot(false);
		cbSalas.setBounds(10, 25, 226, 25);
		panelRecepcion.add(cbSalas);

		cbTipoUsuario = new clrComboBox(conexionCombos.getInstance().listaTipoUsuario(), 0);
		cbTipoUsuario.setEnabled(false);
		cbTipoUsuario.setFocusCycleRoot(false);
		cbTipoUsuario.setBounds(246, 25, 226, 25);
		panelRecepcion.add(cbTipoUsuario);

		cbAmbito = new clrComboBox(conexionCombos.getInstance().listaAmbito(), 0);
		cbAmbito.setEnabled(false);
		cbAmbito.setFocusCycleRoot(false);
		cbAmbito.setBounds(482, 25, 226, 25);
		panelRecepcion.add(cbAmbito);

		cbFinalidad = new clrComboBox(conexionCombos.getInstance().listaFinalidad(), 0);
		cbFinalidad.setEnabled(false);
		cbFinalidad.setFocusCycleRoot(false);
		cbFinalidad.setBounds(718, 25, 226, 25);
		panelRecepcion.add(cbFinalidad);

		txtIDMedico = new ClrCuadroDeTexto(15, false, "Id de médico", true);
		txtIDMedico.setEnabled(false);
		txtIDMedico.setFocusCycleRoot(false);
		txtIDMedico.setBounds(10, 61, 226, 25);
		txtIDMedico.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				lb7.setVisible(false);

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (lb7 == null) {
					lb7 = new lbMedicoXid(txtIDMedico, esta, panelRecepcion, esta.principal);
				}

			}
		});
		panelRecepcion.add(txtIDMedico);

		txtNombreMedico = new ClrCuadroDeTexto(100, false, "Nombres de médico", true);
		txtNombreMedico.setEnabled(false);
		txtNombreMedico.setFocusCycleRoot(false);
		txtNombreMedico.setBounds(246, 61, 226, 25);
		txtNombreMedico.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				lb8.setVisible(false);

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (lb8 == null) {
					lb8 = new lbMedicoXnombre(txtNombreMedico, esta, panelRecepcion, esta.principal);
				}

			}
		});
		panelRecepcion.add(txtNombreMedico);

		txtNumeroMuestra = new ClrCuadroDeTexto(11, true, "Número de muestra");
		txtNumeroMuestra.setEnabled(false);
		txtNumeroMuestra.setFocusCycleRoot(false);
		txtNumeroMuestra.setBounds(482, 133, 226, 25);
		panelRecepcion.add(txtNumeroMuestra);

		txtNumeroCama = new ClrCuadroDeTexto(4, true, "Número de cama");
		txtNumeroCama.setEnabled(false);
		txtNumeroCama.setBounds(482, 97, 226, 25);
		panelRecepcion.add(txtNumeroCama);
		txtNumeroCama.setFocusCycleRoot(false);

		fchMuestra = new JDateChooser();
		fchMuestra.setBounds(246, 97, 226, 25);
		fchMuestra.setCalendar(hoy);
		panelRecepcion.add(fchMuestra);
		fchMuestra.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, Colores.clrTextoInactivo));

		lblFechaToma = new clrLabel("Fecha toma de muestra", 1);
		lblFechaToma.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaToma.setForeground(Colores.clrTextoInactivo);
		lblFechaToma.setBounds(10, 97, 226, 14);
		panelRecepcion.add(lblFechaToma);

		lblFechaRecepcion = new clrLabel("Fecha de recepción", 1);
		lblFechaRecepcion.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaRecepcion.setForeground(Colores.clrTextoInactivo);
		lblFechaRecepcion.setBounds(10, 133, 226, 14);
		panelRecepcion.add(lblFechaRecepcion);

		txtApellidoMedico = new ClrCuadroDeTexto(100, false, "Apellidos de médico", true);
		txtApellidoMedico.setEnabled(false);
		txtApellidoMedico.setFocusCycleRoot(false);
		txtApellidoMedico.setBounds(482, 61, 226, 25);
		txtApellidoMedico.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				lb9.setVisible(false);

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (lb9 == null) {
					lb9 = new lbMedicoXApellido(txtApellidoMedico, esta, panelRecepcion, esta.principal);
				}

			}
		});
		panelRecepcion.add(txtApellidoMedico);

		clrScrollBar clrScrollBar_ = new clrScrollBar();
		clrScrollBar_.setBounds(718, 57, 308, 101);

		panelRecepcion.add(clrScrollBar_);

		txtObservaciones = new clrtextpane(500, false, "Observaciones", 0);
		txtObservaciones.setBounds(718, 61, 295, 97);
		clrScrollBar_.setViewportView(txtObservaciones);

		txtFechaRecepcion = new ClrCuadroDeTexto(500, false, "Fecha de recepci\u00F3n");
		txtFechaRecepcion.setFocusCycleRoot(false);
		txtFechaRecepcion.setEnabled(false);
		txtFechaRecepcion.setBounds(246, 133, 226, 25);
		panelRecepcion.add(txtFechaRecepcion);

		panelPaciente = new clrPanelBordes(false);
		panelPaciente.setLayout(null);
		panelPaciente.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Información de paciente", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
		panelPaciente.setBounds(10, 169, 248, 210);
		contentPane.add(panelPaciente);

		txtIDPaciente = new ClrCuadroDeTexto(15, true, "Documento de identificación");
		txtIDPaciente.setEnabled(false);
		txtIDPaciente.setFocusCycleRoot(false);
		txtIDPaciente.setBounds(10, 28, 226, 25);
		panelPaciente.add(txtIDPaciente);

		txtNombrePaciente = new ClrCuadroDeTexto(100, false, "Nombres");
		txtNombrePaciente.setEnabled(false);
		txtNombrePaciente.setFocusCycleRoot(false);
		txtNombrePaciente.setBounds(10, 64, 226, 25);
		panelPaciente.add(txtNombrePaciente);

		txtApellidoPaciente = new ClrCuadroDeTexto(100, false, "Apellidos");
		txtApellidoPaciente.setEnabled(false);
		txtApellidoPaciente.setFocusCycleRoot(false);
		txtApellidoPaciente.setBounds(10, 100, 226, 25);
		panelPaciente.add(txtApellidoPaciente);

		txtEdadPaciente = new ClrCuadroDeTexto(500, false, "Edad");
		txtEdadPaciente.setFocusCycleRoot(false);
		txtEdadPaciente.setEnabled(false);
		txtEdadPaciente.setBounds(10, 136, 226, 25);
		panelPaciente.add(txtEdadPaciente);

		txtTelPaciente = new ClrCuadroDeTexto(12, true, "Teléfono");
		txtTelPaciente.setFocusCycleRoot(false);
		txtTelPaciente.setEnabled(false);
		txtTelPaciente.setBounds(10, 172, 226, 25);
		panelPaciente.add(txtTelPaciente);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				btnAnular.requestFocusInWindow();
			}
		});

		this.setVisible(true);

	}

	public void llenarPaciente(Paciente paciente) {
		setPaciente(paciente);
		txtEdadPaciente.reiniciar();
		txtTelPaciente.reiniciar();

		System.out.println(this.paciente.toString());
		txtIDPaciente.setText(paciente.getId());
		txtIDPaciente.setForeground(Color.BLACK);
		txtNombrePaciente.setText(paciente.getNombres());
		txtNombrePaciente.setForeground(Color.BLACK);
		txtApellidoPaciente.setText(paciente.getApellidos());
		txtApellidoPaciente.setForeground(Color.BLACK);
		txtEdadPaciente.setText(calculaFecha(paciente.getFechaNacimiento()));
		txtEdadPaciente.setForeground(Color.BLACK);

		txtTelPaciente.setText(paciente.getTelefono1());
		txtTelPaciente.setForeground(Color.BLACK);

	}

	public void llenarEmpresa(Empresa empresa) {
		setEmpresa(empresa);
		ItemListener AL = new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (cbSubGrupoEmpresa.getSelectedIndex() != -1 && cbSubGrupoEmpresa.getSelectedIndex() != 0) {
					String[] ST = cbSubGrupoEmpresa.getSelectedItem().toString().split( " • ");
					tarifa = conexionBusqueda.getInstance().TarifaXnombre(conexionBusqueda.getInstance().grupoEmpresaXID(ST[0]).getTarifa());
					lblTarifaActual.setText("Se facturó con la tarifa: " + tarifa.getDescripcion());
				}

			}
		};
		this.tarifa = conexionBusqueda.getInstance().TarifaXnombre(empresa.getTarifa());
		lblTarifaActual.setText("Se facturará con la tarifa: " + tarifa.getDescripcion());
		txtNitEmpresa.setText(empresa.getDocEmpresa() + "");
		txtNitEmpresa.setForeground(Color.BLACK);
		txtCodEmpresa.setText(empresa.getIdEmpresa() + "");
		txtCodEmpresa.setForeground(Color.BLACK);
		txtRazonSocialEmpresa.setText(empresa.getRazonSocial());
		txtRazonSocialEmpresa.setForeground(Color.BLACK);
		txtRequisitos.setText(empresa.getRequisitosRecepcion());
		txtRequisitos.setForeground(Color.BLACK);
		cbSubGrupoEmpresa.removeItemListener(AL);
		cbSubGrupoEmpresa.llenar(conexionCombos.getInstance().listaSubGrupoEmpresa(empresa.getIdEmpresa()));
		cbSubGrupoEmpresa.addItemListener(AL);
		cbTipoUsuario.setSelectedIndex(empresa.getTipoUsuario());
	}

	public void llenarMedico(Medico medico) {
		setMedico(medico);

		txtNombreMedico.setText(medico.getNombres());
		txtNombreMedico.setForeground(Color.BLACK);
		txtApellidoMedico.setText(medico.getApellidos());
		txtApellidoMedico.setForeground(Color.BLACK);
		txtIDMedico.setText(medico.getIdmedico());
		txtIDMedico.setForeground(Color.BLACK);

	}

	public void llenarRecepcion(RecepcionCompleta RC) {

		Recepcion recepcion = RC.getRecepcion();
		idRecepcion = recepcion.getIdREcepcion();

		llenarPaciente(recepcion.getPaciente());

		llenarEmpresa(recepcion.getEmpresa());
		if (recepcion.getCodSubEmpresa() == -1) {
			cbSubGrupoEmpresa.setSelectedIndex(0);
		} else {
			GruposEmpresas GE = conexionBusqueda.getInstance().grupoEmpresaXID(recepcion.getCodSubEmpresa() + "");
			cbSubGrupoEmpresa.setSelectedItem(GE.getIdGrupo() + " • " + GE.getNombreGrupo());
		}

		if (recepcion.getAutorizacion().equals("-1")) {
			txtAutorizacion.reiniciar();
		} else {
			txtAutorizacion.setText(recepcion.getAutorizacion() + "");
			txtAutorizacion.setForeground(Color.BLACK);
		}

		if (recepcion.getNumeroOrden().equals("-1")) {
			txtNumeroOrden.reiniciar();
		} else {
			txtNumeroOrden.setText(recepcion.getNumeroOrden() + "");
			txtNumeroOrden.setForeground(Color.BLACK);
		}

		if (recepcion.getCodEPyP() == -1) {
			cbPlanesPYP.setSelectedIndex(0);
		} else {
			String[] plan = conexionBusqueda.getInstance().planPYPXid("" + recepcion.getCodEPyP());
			System.out.println(plan[0] + " • " + plan[1]);
			cbPlanesPYP.setSelectedItem(plan[0] + " • " + plan[1]);
		}

		if (recepcion.getCodSala() == -1) {
			cbSalas.setSelectedIndex(0);
		} else {
			String[] sala = conexionBusqueda.getInstance().SalaXid("" + recepcion.getCodSala());
			cbSalas.setSelectedItem(sala[1] + " • " + sala[2]);
		}

		if (recepcion.getCodTipoUsuario() == -1) {
			cbTipoUsuario.setSelectedIndex(0);
		} else {
			cbTipoUsuario.setSelectedIndex(recepcion.getCodTipoUsuario());
		}

		if (recepcion.getCodAmbito() == -1) {
			cbAmbito.setSelectedIndex(0);
		} else {
			cbAmbito.setSelectedIndex(recepcion.getCodAmbito());
		}

		if (recepcion.getCodFinalidad() == -1) {
			cbFinalidad.setSelectedIndex(0);
		} else {
			cbFinalidad.setSelectedIndex(recepcion.getCodFinalidad());
		}

		if (recepcion.getCodMedico().equals("-1")) {
			txtIDMedico.reiniciar();
			txtNombreMedico.reiniciar();
			txtApellidoMedico.reiniciar();
		} else {
			llenarMedico(conexionBusqueda.getInstance().medicoXid(recepcion.getCodMedico()));
		}

		fchOrden.setDate(recepcion.getFechaOrden());
		fchMuestra.setDate(recepcion.getFechaTomaMuestra());
		txtFechaRecepcion.setText(recepcion.getFechaRecepcion().toString());
		txtFechaRecepcion.setForeground(Color.BLACK);

		if (recepcion.getCama() == -1) {
			txtNumeroCama.reiniciar();
		} else {
			txtNumeroCama.setText(recepcion.getCama() + "");
			txtNumeroCama.setForeground(Color.BLACK);
		}

		if (recepcion.getNumeroMuestra() == -1) {
			txtNumeroMuestra.reiniciar();
		} else {
			txtNumeroMuestra.setText(recepcion.getNumeroMuestra() + "");
			txtNumeroMuestra.setForeground(Color.BLACK);
		}

		if (recepcion.getObservaciones().equals("")) {
			txtObservaciones.reiniciar();
		} else {
			txtObservaciones.setText(recepcion.getObservaciones() + "");
			txtObservaciones.setForeground(Color.BLACK);
		}

	}

	public void cambiarDatos() {
		if (idRecepcion == -1) {
			lblincorrectos.setText("No has escogido ninguna recepción");

		} else {

			ventanaPregunta VP = new ventanaPregunta(principal, "Al anular esta recepción también estas anulando la factura asociada a esta, estas seguro que deseas continuar con estas acción", "Si", "No");

			if (VP.escuchar()) {
				if (conexion.getInstance().AnularRecepcion(esta, idRecepcion)) {
					esta.principal.registrarAccion("Anulacion de recepción  #: '" + idRecepcion + "' y su factura equivalente");
					dispose();
				} else {
					lblincorrectos.setText("Error  en la conexion con la base de datos");
				}
			}

		}
	}

	public String calculaFecha(Date FechaNac) {
		Calendar fechaActual = Calendar.getInstance();
		Calendar FechaNacimi = Calendar.getInstance();

		if (FechaNac != null) {
			FechaNacimi.setTime(FechaNac);

			if (fechaActual.getTimeInMillis() < FechaNacimi.getTimeInMillis()) {
				return "error - fecha exedida";
			} else {
				// Se restan la fecha actual y la fecha de nacimiento
				int aos = fechaActual.get(Calendar.YEAR) - FechaNacimi.get(Calendar.YEAR);
				int mes = fechaActual.get(Calendar.MONTH) - FechaNacimi.get(Calendar.MONTH);
				int dia = fechaActual.get(Calendar.DATE) - FechaNacimi.get(Calendar.DATE);
				// Se ajusta el año dependiendo el mes y el día
				if (mes < 0 || (mes == 0 && dia < 0)) {
					aos--;
				}

				if (dia < 0 || mes == 0 && dia < 0) {
					mes--;
				}

				if (aos >= 5) {
					return aos + " Años";
				} else {
					if (aos < 5 && aos >= 1) {

						String meses = "";
						if (mes < 0)
							meses = 12 + (mes) + "- Meses";
						if (mes > 0)
							meses = (mes) + "- Meses";

						return aos + " Años " + meses;
					} else {
						if (aos < 1 && mes != 0) {

							String meses = "";
							if (mes < 0)
								meses = 12 + (mes) + " Meses";
							if (mes > 0)
								meses = (mes) + " Meses";

							return meses;
						} else {
							return fechaActual.get(Calendar.DAY_OF_YEAR) - FechaNacimi.get(Calendar.DAY_OF_YEAR) + " Dias de nacido";
						}

					}
				}

			}
		} else {
			return "Sin fecha de nacimiento";

		}
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public int getCodSubEmpresa() {
		return codSubEmpresa;
	}

	public void setCodSubEmpresa(int codSubEmpresa) {
		this.codSubEmpresa = codSubEmpresa;
	}

	public void reportarError(String error) {
		lblincorrectos.setText(error);
	}
}
