package Interfaces;

import interfaces_Modificadas.ClrCuadroDeTexto;
import interfaces_Modificadas.Colores;
import interfaces_Modificadas.MiTableModelNoEditable;
import interfaces_Modificadas.MiTableModelReportes;
import interfaces_Modificadas.MiTableModelTarifas;
import interfaces_Modificadas.MiTextfieldEditor;
import interfaces_Modificadas.ReportesTableCellEditor;
import interfaces_Modificadas.StringDigitalCellEditorr;
import interfaces_Modificadas.btnRedondo;
import interfaces_Modificadas.circuloUsuario;
import interfaces_Modificadas.clrCheckBox;
import interfaces_Modificadas.clrComboBox;
import interfaces_Modificadas.clrLabel;
import interfaces_Modificadas.clrPanelBordes;
import interfaces_Modificadas.clrPestanas;
import interfaces_Modificadas.clrRadioButton;
import interfaces_Modificadas.clrRenderTablaExamenesXReportar;
import interfaces_Modificadas.clrRenderTablaItemRecepcion;
import interfaces_Modificadas.clrRenderTablaNoReportar;
import interfaces_Modificadas.clrRenderTablaReportar;
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
import auxiliares.ItemRecepcion;
import auxiliares.Medico;
import auxiliares.OTD;
import auxiliares.Paciente;
import auxiliares.Recepcion;
import auxiliares.RecepcionCompleta;
import auxiliares.Reporte;
import auxiliares.Secciones;
import auxiliares.Tarifa;
import auxiliares.TipoDato;
import auxiliares.Usuario;
import auxiliares.Examen;
import auxiliares.itemFactura;
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
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.text.Document;
import javax.swing.text.TabExpander;
import javax.swing.text.TabableView;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;

import otrosImpresos.imprimirReporte;

import java.awt.Font;

public class OpReportarResultados extends JDialog {

	private clrPanelBordes contentPane;
	private clrLabel lblincorrectos;
	private Principal principal;
	private Usuario usuario;
	private String accion;
	private clrLabel lblinicio;
	private circuloUsuario btnRedondo_;
	private int idExamen;
	private int idDias;
	private String codigoAntiguo;

	private protocolo elPprotocolo = null;
	private btnRedondo btnSalir;
	private lbRecepcionXId lb1;

	private MiTableModelNoEditable TM;
	private MiTableModelNoEditable TM1;
	private MiTableModelReportes TMUR;
	private MiTableModelNoEditable TMR1;
	private MiTableModelNoEditable TMR2;
	private MiTableModelNoEditable TMR3;
	private MiTableModelNoEditable TMR4;
	private ArrayList<itemTarifa> paraEliminar = new ArrayList<itemTarifa>();
	private ArrayList<itemTarifa> paraAgregar = new ArrayList<itemTarifa>();
	private ArrayList<itemTarifa> paraModificar = new ArrayList<itemTarifa>();
	private int[] dias = new int[] { 0, 0, 0, 0, 0, 0, 0 };
	private ClrCuadroDeTexto fchRecepcion;
	private clrPanelBordes panelPaciente;
	private ClrCuadroDeTexto txtIDPaciente;
	private ClrCuadroDeTexto txtNombrePaciente;
	private ClrCuadroDeTexto txtApellidoPaciente;
	private ClrCuadroDeTexto txtEdadPaciente;
	private clrPanelBordes panelExamenes;
	private ClrCuadroDeTexto txtIdRecepcion;
	private ClrCuadroDeTexto txtGeneroPaciente;
	private clrComboBox cbExamenesImp ;
	private Paciente paciente;
	private Medico medico;
	private Empresa empresa;
	private Tarifa tarifa;
	private int codSubEmpresa;
	private static OpReportarResultados esta;
	private clrPanelBordes panelUR;
	private clrPanelBordes panelR1;
	private clrPanelBordes panelR2;
	private clrPanelBordes panelR3;
	private clrPanelBordes panelR4;
	private JTable tableUR;
	private JTable tableR1;
	private JTable tableR2;
	private JTable tableR3;
	private JTable tableR4;
	private JTable tablaExamenes;
	private clrScrollBar scrollPane_1;
	private clrPanelBordes panelRecepcion;
	private clrPestanas panelReportes;
	private clrScrollBar scrollPaneUR;
	private clrScrollBar scrollPaneR1;
	private clrScrollBar scrollPaneR2;
	private clrScrollBar scrollPaneR3;
	private clrScrollBar scrollPaneR4;
	private clrRadioButton chkImprimirRep;
	private clrRadioButton chkImprimirTodo;
	private clrRadioButton chkImprimirNoRep;
	private clrRadioButton chkImprimirDirecto ;
	private clrRadioButton chkVistaPrevia;

	private RecepcionCompleta URC;
	private RecepcionCompleta RC1;
	private RecepcionCompleta RC2;
	private RecepcionCompleta RC3;
	private RecepcionCompleta RC4;
	
	private String[] resultadosURC;
	private ArrayList<Reporte> paraGuardar=new ArrayList<Reporte>();
private 	int idExamenReporte;
	
	private RecepcionCompleta recepcionSeleccionada;
	private btnRedondo btnGuardar;

	private boolean porReportar = false;
	private clrLabel lblUltimoReporte;
	private clrComboBox cbBacteriologo;
	private ClrCuadroDeTexto txtClaveFirma;
	
	private btnRedondo btnInterpreta;
	private JDialog observa;
	private clrPanelBordes contentPane1; 
	private clrtextpane txtobserva;
	private btnRedondo btnGuardarobserva;
	
	
	public OpReportarResultados(Principal principal, Usuario usuario) {
		super(principal, true);
		this.esta = this;
		this.principal = principal;
		this.usuario = usuario;
		this.esta = this;
		this.accion = accion;

		// this.setDefaultLookAndFeelDecorated(false);

		setBounds(100, 100, 1119, 769);
		this.setUndecorated(true);
		this.setLocationRelativeTo(getParent());
		contentPane = new clrPanelBordes(true);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		lblinicio = new clrLabel("Reportar resultados", 2, true);
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

		btnSalir = new btnRedondo("Salir", new Rectangle(237, 172,121,50), 9);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSalir.setBounds(995, 708, 114, 50);
		contentPane.add(btnSalir);

		panelExamenes = new clrPanelBordes(false);
		panelExamenes.setLayout(null);
		panelExamenes.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Informaci\u00F3n examenes para reporte", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
		panelExamenes.setBounds(10, 273, 377, 333);
		contentPane.add(panelExamenes);

		scrollPane_1 = new clrScrollBar();
		scrollPane_1.setBounds(4, 21, 369, 308);
		panelExamenes.add(scrollPane_1);

		TM1 = new MiTableModelNoEditable(new Object[][] {}, new String[] { "Examen", "Protocolo", "Nombre examen", "R", "I" });

		tablaExamenes = new JTable();
		tablaExamenes.setModel(TM1);
		tablaExamenes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tablaExamenes.setDefaultRenderer(Object.class, new clrRenderTablaExamenesXReportar());
		scrollPane_1.setViewportView(tablaExamenes);

		tablaExamenes.getColumnModel().getColumn(0).setPreferredWidth(90);
		tablaExamenes.getColumnModel().getColumn(1).setPreferredWidth(110);
		tablaExamenes.getColumnModel().getColumn(2).setPreferredWidth(400);
		tablaExamenes.getColumnModel().getColumn(3).setPreferredWidth(30);
		tablaExamenes.getColumnModel().getColumn(4).setPreferredWidth(30);

		tablaExamenes.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				

			}

			@Override
			public void mousePressed(MouseEvent e) {
				

			}

			@Override
			public void mouseExited(MouseEvent e) {
				

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				

			}

			@Override
			public void mouseClicked(MouseEvent e) {

				if (porReportar) {

					ventanaPregunta pregunta = new ventanaPregunta(OpReportarResultados.this.principal, "Deseas guardar los reportes hechos para este examen", "Si", "No");
					if (pregunta.escuchar()) {
						if (reportar(URC.getItemsFactura().get(idExamenReporte).getIdItemAI())) {
							OpReportarResultados.this.principal.registrarAccion("Reporte o cambio de resultados para recepción # " + URC.getRecepcion().getIdREcepcion());
							porReportar=false;
							paraGuardar=new ArrayList<Reporte>();
							tablaExamenes.setValueAt("si", idExamenReporte, 3);

						}else{
							lblincorrectos.setText("Error  en la conexion con la base de datos");
						}
					}else{
						paraGuardar=new ArrayList<Reporte>();
							porReportar=false;
						}
				}

				TableCellEditor TCE = tableUR.getCellEditor();
				if (tableUR.isEditing()) {
					TCE.stopCellEditing();
				}
				ArrayList<ItemRecepcion> itemsRC = recepcionSeleccionada.getItemsFactura().get(tablaExamenes.getSelectedRow()).getItemsExamenes();
				llenarItems(itemsRC);
				if (RC1 != null) {
					panelReportes.setTitleAt(1, "Recepción: " + RC1.getRecepcion().getIdREcepcion());
				}
				;
				if (RC2 != null) {
					panelReportes.setTitleAt(2, "Recepción: " + RC2.getRecepcion().getIdREcepcion());
				}
				;
				if (RC3 != null) {
					panelReportes.setTitleAt(3, "Recepción: " + RC3.getRecepcion().getIdREcepcion());
				}
				;
				if (RC4 != null) {
					panelReportes.setTitleAt(4, "Recepción: " + RC4.getRecepcion().getIdREcepcion());
				}
				;

			}
		});

		panelPaciente = new clrPanelBordes(false);
		panelPaciente.setLayout(null);
		panelPaciente.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Información de paciente", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
		panelPaciente.setBounds(10, 159, 377, 103);
		contentPane.add(panelPaciente);

		txtIDPaciente = new ClrCuadroDeTexto(15,true,"Identificaci\u00F3n");
		txtIDPaciente.setHorizontalAlignment(SwingConstants.LEFT);
		txtIDPaciente.setFocusCycleRoot(false);
		txtIDPaciente.setEnabled(false);
		txtIDPaciente.setBounds(10, 28, 136, 25);

		panelPaciente.add(txtIDPaciente);

		txtNombrePaciente = new ClrCuadroDeTexto(100,false,"Nombres");
		txtNombrePaciente.setFocusCycleRoot(false);
		txtNombrePaciente.setEnabled(false);
		txtNombrePaciente.setBounds(10, 64, 168, 25);

		panelPaciente.add(txtNombrePaciente);

		txtApellidoPaciente = new ClrCuadroDeTexto(100,false,"Apellidos");
		txtApellidoPaciente.setFocusCycleRoot(false);
		txtApellidoPaciente.setEnabled(false);
		txtApellidoPaciente.setBounds(188, 64, 179, 25);

		panelPaciente.add(txtApellidoPaciente);

		txtEdadPaciente = new ClrCuadroDeTexto(100,false,"Edad");
		txtEdadPaciente.setHorizontalAlignment(SwingConstants.LEFT);
		txtEdadPaciente.setFocusCycleRoot(false);
		txtEdadPaciente.setEnabled(false);
		txtEdadPaciente.setBounds(156, 28, 116, 25);
		panelPaciente.add(txtEdadPaciente);

		txtGeneroPaciente = new ClrCuadroDeTexto(100,false,"Genero");
		txtGeneroPaciente.setHorizontalAlignment(SwingConstants.LEFT);
		txtGeneroPaciente.setFocusCycleRoot(false);
		txtGeneroPaciente.setEnabled(false);
		txtGeneroPaciente.setBounds(282, 28, 78, 25);
		panelPaciente.add(txtGeneroPaciente);

		panelRecepcion = new clrPanelBordes(false);
		panelRecepcion.setLayout(null);
		panelRecepcion.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Informaci\u00F3n de recepci\u00F3n", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
		panelRecepcion.setBounds(10, 89, 377, 59);
		contentPane.add(panelRecepcion);

		txtIdRecepcion = new ClrCuadroDeTexto(100,false,"# Recepcion", true);
		txtIdRecepcion.setBounds(10, 21, 169, 25);
		txtIdRecepcion.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				lb1.setVisible(false);

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (lb1 == null) {
					lb1 = new lbRecepcionXId(txtIdRecepcion, esta, panelRecepcion, esta.principal);
				}

			}
		});
		panelRecepcion.add(txtIdRecepcion);
		txtIdRecepcion.setFocusCycleRoot(false);

		fchRecepcion = new ClrCuadroDeTexto(500,false,"Fecha recepción");
		fchRecepcion.setBounds(189, 21, 178, 25);
		panelRecepcion.add(fchRecepcion);
		fchRecepcion.setEnabled(false);
		fchRecepcion.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, Colores.clrTextoInactivo));

		panelReportes = new clrPestanas(JTabbedPane.TOP);
		panelReportes.setBounds(397, 98, 712, 508);
		panelReportes.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {

				if (porReportar) {

					ventanaPregunta pregunta = new ventanaPregunta(OpReportarResultados.this.principal, "Deseas guardar los reportes hechos para este examen", "Si", "No");
					if (pregunta.escuchar()) {
						if (reportar(URC.getItemsFactura().get(idExamenReporte).getIdItemAI())) {
							OpReportarResultados.this.principal.registrarAccion("Reporte o cambio de resultados para recepción # " + URC.getRecepcion().getIdREcepcion());
							porReportar=false;
							paraGuardar=new ArrayList<Reporte>();
							tablaExamenes.setValueAt("si", idExamenReporte, 3);

						}else{
							lblincorrectos.setText("Error  en la conexion con la base de datos");
						}
					}else{
						paraGuardar=new ArrayList<Reporte>();
							porReportar=false;
						}
				}

				if (RC1 != null) {
					panelReportes.setTitleAt(1, "Recepción: " + RC1.getRecepcion().getIdREcepcion());
				}
				;
				if (RC2 != null) {
					panelReportes.setTitleAt(2, "Recepción: " + RC2.getRecepcion().getIdREcepcion());
				}
				;
				if (RC3 != null) {
					panelReportes.setTitleAt(3, "Recepción: " + RC3.getRecepcion().getIdREcepcion());
				}
				;
				if (RC4 != null) {
					panelReportes.setTitleAt(4, "Recepción: " + RC4.getRecepcion().getIdREcepcion());
				}
				;

				switch (panelReportes.getSelectedIndex()) {
				case 0:
					llenarExamenes(URC);
					limpiarTabla(TMUR);
					recepcionSeleccionada = URC;
					break;

				case 1:
					llenarExamenes(RC1);
					limpiarTabla(TMR1);
					recepcionSeleccionada = RC1;
					break;

				case 2:
					llenarExamenes(RC2);
					limpiarTabla(TMR2);
					recepcionSeleccionada = RC2;
					break;

				case 3:
					llenarExamenes(RC3);
					limpiarTabla(TMR3);
					recepcionSeleccionada = RC3;
					break;

				case 4:
					llenarExamenes(RC4);
					limpiarTabla(TMR4);
					recepcionSeleccionada = RC4;
					break;

				default:
					break;
				}

			}
		});
		contentPane.add(panelReportes);

		clrLabel clrLabel_ = new clrLabel("A=Aviso || Med=Unidad de medida || Abr=Abreviatura unidad || MnR = Minimo de referencia || MxR = Maximo de referencia ", 1);
		clrLabel_.setHorizontalAlignment(SwingConstants.RIGHT);
		clrLabel_.setForeground(Colores.clrPrincipal);
		clrLabel_.setFont(Colores.fuentePequena);
		clrLabel_.setAlignmentX(0.5f);
		clrLabel_.setBounds(407, 600, 702, 28);
		contentPane.add(clrLabel_);

		clrLabel clrLabel__1 = new clrLabel("G=Genero || TD=Tipo de dato || OR=Orden || VN=Valor normal || Ci=Codigo interno", 1);
		clrLabel__1.setHorizontalAlignment(SwingConstants.RIGHT);
		clrLabel__1.setForeground(Colores.clrPrincipal);
		clrLabel__1.setFont(new Font("Calibri", Font.PLAIN, 13));
		clrLabel__1.setAlignmentX(0.5f);
		clrLabel__1.setBounds(417, 621, 692, 28);
		contentPane.add(clrLabel__1);

		clrLabel clrLabel__2 = new clrLabel("R=Reportado || I=Impreso  ", 1);
		clrLabel__2.setHorizontalAlignment(SwingConstants.RIGHT);
		clrLabel__2.setForeground(Colores.clrPrincipal);
		clrLabel__2.setFont(new Font("Calibri", Font.PLAIN, 13));
		clrLabel__2.setAlignmentX(0.5f);
		clrLabel__2.setBounds(227, 600, 160, 28);
		contentPane.add(clrLabel__2);

		clrPanelBordes clrPanelBordes_ = new clrPanelBordes(false);
		clrPanelBordes_.setLayout(null);
		clrPanelBordes_.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Información de paciente", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
		clrPanelBordes_.setBounds(10, 660, 952, 98);
		contentPane.add(clrPanelBordes_);

		btnInterpreta = new btnRedondo("Agregar Observaci\u00F3n", new Rectangle(48, 172, 121, 50), 16);
		btnInterpreta.setSelected(true);
		btnInterpreta.setBounds(10, 609, 219, 50);
		btnInterpreta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				

				
				if (URC==null) {
					lblincorrectos.setText("No has seleccionado ningúna recepción");
				} else {
					if (tablaExamenes.getSelectedRow()<0) {
						lblincorrectos.setText("No has seleccionado ningún examen para agregar una observación");

					} else {

						
						observa=new JDialog(esta);
						observa.setBounds(100, 100, 500,270);
						observa.setUndecorated(true);
						observa.setLocationRelativeTo(getParent());
						contentPane1 = new clrPanelBordes(true);
						contentPane1.setLayout(null);
						observa.setContentPane(contentPane1);
						
						
						clrScrollBar scrollPane1 = new clrScrollBar();
						scrollPane1.setBounds(20, 70, 460, 90);
						contentPane1.add(scrollPane1);
						String observacion=URC.getItemsFactura().get(tablaExamenes.getSelectedRow()).getObservaciones();
						System.err.println(observacion);
						txtobserva = new clrtextpane(500,false,"Observación",0);
						if (!observacion.equals("")) {
							txtobserva.setText(observacion);
						}
						scrollPane1.setViewportView(txtobserva);
						
						btnGuardarobserva = new btnRedondo("Guardar  observac\u00F3n", new Rectangle(48, 172, 121, 50), 16);
						btnGuardarobserva.setSelected(true);
						btnGuardarobserva.setBounds(140, 180, 219, 50);
						contentPane1.add(btnGuardarobserva);
						btnGuardarobserva.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								String observac;
								System.err.println();
								if (!txtobserva.getText().equals(txtobserva.getLabel())) {
									observac=txtobserva.getText();
									if (escribirObserva(URC.getItemsFactura().get(tablaExamenes.getSelectedRow()).getIdItemAI(), observac)) {
										URC.getItemsFactura().get(tablaExamenes.getSelectedRow()).setObservaciones(observac);
										lblincorrectos.setText("Se ha escrito correctamente la observación para este examen");
										observa.dispose();

									} else {
										lblincorrectos.setText("No ha sido posible  escribir correctamente la observación para este examen");
										observa.dispose();
									}
								} else {
									observac="";
									if (escribirObserva(URC.getItemsFactura().get(tablaExamenes.getSelectedRow()).getIdItemAI(), observac)) {
										URC.getItemsFactura().get(tablaExamenes.getSelectedRow()).setObservaciones(observac);
										lblincorrectos.setText("Se ha escrito correctamente la observación para este examen");
										observa.dispose();

									} else {
										lblincorrectos.setText("No ha sido posible  escribir correctamente la observación para este examen");
										observa.dispose();
									}
								}
								
							}
						});
						
						observa.setVisible(true);			
						
					
					
					}
				}
				
			}
		});
		contentPane.add(btnInterpreta);
		
		chkImprimirRep= new clrRadioButton("Imprimir reportados");
		chkImprimirRep.setBounds(10, 22, 179, 23);
		chkImprimirRep.setSelected(true);
		clrPanelBordes_.add(chkImprimirRep);

		chkImprimirTodo = new clrRadioButton("Imprimir todo");
		chkImprimirTodo.setBounds(199, 22, 133, 23);
		clrPanelBordes_.add(chkImprimirTodo);

		chkImprimirNoRep = new clrRadioButton("Imprimir no reportados");
		chkImprimirNoRep.setBounds(342, 22, 198, 23);
		clrPanelBordes_.add(chkImprimirNoRep);

		ButtonGroup BG1=new ButtonGroup();
		BG1.add(chkImprimirTodo);
		BG1.add(chkImprimirNoRep);
		BG1.add(chkImprimirRep);
		 cbExamenesImp = new clrComboBox(new String[] {},0);
		cbExamenesImp.setFocusCycleRoot(false);
		cbExamenesImp.setBounds(550, 20, 226, 25);
		cbExamenesImp.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if (cbExamenesImp.getSelectedIndex()==0) {
					chkImprimirRep.setSelected(true);
				}else{
					chkImprimirNoRep.setSelected(false);
					chkImprimirRep.setSelected(false);
					chkImprimirTodo.setSelected(false);
				}
				
			}
		});
		clrPanelBordes_.add(cbExamenesImp);

		clrSeparador separator = new clrSeparador();
		separator.setBounds(10, 56, 766, 2);
		clrPanelBordes_.add(separator);
		
		ButtonGroup BG=new ButtonGroup();

		chkImprimirDirecto = new clrRadioButton("Imprimir directamente en impresora");
		chkImprimirDirecto.setBounds(10, 63, 289, 23);
		BG.add(chkImprimirDirecto);
		clrPanelBordes_.add(chkImprimirDirecto);

		chkVistaPrevia = new clrRadioButton("Vista previa");
		chkVistaPrevia.setBounds(309, 63, 120, 23);
		chkVistaPrevia.setSelected(true);
		BG.add(chkVistaPrevia);
		clrPanelBordes_.add(chkVistaPrevia);

		btnRedondo btnImprimir = new btnRedondo("Imprimir", new Rectangle(237, 172,121,50), 15);
		btnImprimir.setBounds(798, 11, 144, 50);
		btnImprimir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (URC!=null) {

					String queImprime="0";
					if (chkImprimirNoRep.isSelected()) {
						queImprime="1";
					}else{
						if (chkImprimirRep.isSelected()) {
							queImprime="2";
					
						} else {
							if (cbExamenesImp.getSelectedIndex()!=0) {
								queImprime=cbExamenesImp.getSelectedItem()+"";

							}
						
						}
					}
					Bacteriologo bacteriologo=null;
					if (cbBacteriologo.getSelectedIndex()!=0) {
						if (txtClaveFirma.getText().equals(txtClaveFirma.getLabel())) {
						lblincorrectos.setText("Falta clave para la firma del documento");	
						} else {

							String[] codbac=cbBacteriologo.getSelectedItem().toString().split(" • ");
							bacteriologo=conexionBusqueda.getInstance().bacteriologoXid(codbac[0]);
							if (bacteriologo.getClaveFirma()==Integer.parseInt(txtClaveFirma.getText())) {
									new imprimirReporte(URC, chkImprimirDirecto.isSelected(), queImprime,bacteriologo);
						dispose();
							}else{
								ventanaPregunta VP=new ventanaPregunta(OpReportarResultados.this.principal, "Clave de firma incorrecta, desea imprimir sin firma?", "Continuar", "Cancelar");
								if (VP.escuchar()) {
									new imprimirReporte(URC, chkImprimirDirecto.isSelected(), queImprime,bacteriologo);
									dispose();
								} 
								
							}
						

						}
						
					} else{
						new imprimirReporte(URC, chkImprimirDirecto.isSelected(), queImprime,bacteriologo);
						dispose();
					}
					
				
				
				} else {
					lblincorrectos.setText("No has escogido ninguna recepción");
				}
			}
		});
		clrPanelBordes_.add(btnImprimir);
		
		cbBacteriologo = new clrComboBox(conexionCombos.getInstance().listaBacteriologos(), 0);
		cbBacteriologo.setFocusCycleRoot(false);
		cbBacteriologo.setBounds(435, 61, 226, 25);
		clrPanelBordes_.add(cbBacteriologo);
		
		txtClaveFirma = new ClrCuadroDeTexto(100, true, "Clave firma");
		txtClaveFirma.setHorizontalAlignment(SwingConstants.LEFT);
		txtClaveFirma.setFocusCycleRoot(false);
		txtClaveFirma.setBounds(667, 61, 133, 25);
		clrPanelBordes_.add(txtClaveFirma);

		btnGuardar = new btnRedondo("", new Rectangle(48, 172,121,50), 12);
		btnGuardar.setSelected(true);
		btnGuardar.setBounds(1056, 50, 50, 50);
		btnGuardar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TableCellEditor TCE = tableUR.getCellEditor();
				if (tableUR.isEditing()) {
					TCE.stopCellEditing();

				}

				if (porReportar) {

					if (reportar(URC.getItemsFactura().get(tablaExamenes.getSelectedRow()).getIdItemAI())) {
						OpReportarResultados.this.principal.registrarAccion("Reporte o cambio de resultados para recepción # " + URC.getRecepcion().getIdREcepcion());
						porReportar=false;
						paraGuardar = new ArrayList<Reporte>();
						llenarURC(conexionBusqueda.getInstance().RCXid(URC.getRecepcion().getIdREcepcion()));

					}else{
						lblincorrectos.setText("Error  en la conexion con la base de datos");
					}

				}else{
					lblincorrectos.setText("No hay reportes por guardar");
				}

			}
		});
		contentPane.add(btnGuardar);
		
		lblUltimoReporte = new clrLabel("", 1);
		lblUltimoReporte.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUltimoReporte.setForeground(Colores.clrAlertaCamarada);
		lblUltimoReporte.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblUltimoReporte.setAlignmentX(0.5f);
		lblUltimoReporte.setBounds(789, 60, 266, 28);
		contentPane.add(lblUltimoReporte);

		TMUR = new MiTableModelReportes(new Object[][] {}, new String[] { "Concepto", "Resultado", "A", "Med", "Abr", "MnR", "MxR", "VN", "G", "TD", "Or", "Ci", "R" });

		tableUR = new JTable();
		tableUR.setModel(TMUR);
		tableUR.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tableUR.getColumnModel().getColumn(1).setCellEditor(new ReportesTableCellEditor());
		tableUR.setDefaultRenderer(Object.class, new clrRenderTablaReportar());
		tableUR.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableUR.getSelectedRow();

				if (!tableUR.getValueAt(row, 9).equals("2") && !tableUR.getValueAt(row, 9).equals("1") && !tableUR.getValueAt(row, 9).equals("2")) {
					String idExamen = "" + tablaExamenes.getValueAt(tablaExamenes.getSelectedRow(), 0);

					if (RC1 != null) {
						for (int i = 0; i < RC1.getItemsFactura().size(); i++) {
							itemFactura IF = RC1.getItemsFactura().get(i);

							if (IF.getCodExamen().equals(idExamen + "")) {

							
									ItemRecepcion IR = IF.getItemsExamenes().get(tableUR.getSelectedRow());
										if (IR.getReportado()==0) {
											panelReportes.setTitleAt(1, "Valor no reportado aun");
										} else {
											panelReportes.setTitleAt(1, "Res: " + IR.getValorResultado());

										}
					
						
							}

						}

					}
					
					
					if (RC2 != null) {
						for (int i = 0; i < RC2.getItemsFactura().size(); i++) {
							itemFactura IF = RC2.getItemsFactura().get(i);

							if (IF.getCodExamen().equals(idExamen + "")) {

							
									ItemRecepcion IR = IF.getItemsExamenes().get(tableUR.getSelectedRow());
										if (IR.getReportado()==0) {
											panelReportes.setTitleAt(2, "Valor no reportado aun");
										} else {
											panelReportes.setTitleAt(2, "Res: " + IR.getValorResultado());

										}
					
						
							}

						}

					}
					
					if (RC3 != null) {
						for (int i = 0; i < RC3.getItemsFactura().size(); i++) {
							itemFactura IF = RC3.getItemsFactura().get(i);

							if (IF.getCodExamen().equals(idExamen + "")) {

							
									ItemRecepcion IR = IF.getItemsExamenes().get(tableUR.getSelectedRow());
										if (IR.getReportado()==0) {
											panelReportes.setTitleAt(3, "Valor no reportado aun");
										} else {
											panelReportes.setTitleAt(3, "Res: " + IR.getValorResultado());

										}
					
						
							}

						}

					}
					
					if (RC4 != null) {
						for (int i = 0; i < RC4.getItemsFactura().size(); i++) {
							itemFactura IF = RC4.getItemsFactura().get(i);

							if (IF.getCodExamen().equals(idExamen + "")) {

							
									ItemRecepcion IR = IF.getItemsExamenes().get(tableUR.getSelectedRow());
										if (IR.getReportado()==0) {
											panelReportes.setTitleAt(4, "Valor no reportado aun");
										} else {
											panelReportes.setTitleAt(4, "Res: " + IR.getValorResultado());

										}
					
						
							}

						}

					}
					
					
					
					
				} else {
					if(RC1!=null){panelReportes.setTitleAt(1, "Recepción: " + RC1.getRecepcion().getIdREcepcion());}
					if(RC2!=null){panelReportes.setTitleAt(2, "Recepción: " + RC2.getRecepcion().getIdREcepcion());}
					if(RC3!=null){panelReportes.setTitleAt(3, "Recepción: " + RC3.getRecepcion().getIdREcepcion());}
					if(RC4!=null){panelReportes.setTitleAt(4, "Recepción: " + RC4.getRecepcion().getIdREcepcion());}

				}
			}

		});

		tableUR.getColumnModel().getColumn(1).getCellEditor().addCellEditorListener(new CellEditorListener() {
				
			
			@Override
			public void editingStopped(ChangeEvent e) {
				System.out.println("estaba editnado >> "+tableUR.getSelectedRow());
				
				if (!resultadosURC[tableUR.getSelectedRow()].equals(tableUR.getValueAt(tableUR.getSelectedRow(), 1))) {
					porReportar = true;
					System.out.println("Si cambio >> "+tableUR.getValueAt(tableUR.getSelectedRow(), 1));
					resultadosURC[tableUR.getSelectedRow()]=""+tableUR.getValueAt(tableUR.getSelectedRow(), 1);
					Reporte reporteAuxi=new Reporte(""+tableUR.getValueAt(tableUR.getSelectedRow(), 1), Integer.parseInt(""+tableUR.getValueAt(tableUR.getSelectedRow(), 11)));
					paraGuardar.add(reporteAuxi);
					idExamenReporte=tablaExamenes.getSelectedRow();
				}

			}

			@Override
			public void editingCanceled(ChangeEvent e) {
				System.out.println("cancel cancel  cambio ");

			}
		});

		TMR1 = new MiTableModelNoEditable(new Object[][] {}, new String[] { "Concepto", "Resultado", "A", "Med", "Abr", "MnR", "MxR", "VN", "G", "TD", "Or", "Ci", "R" });

		tableR1 = new JTable();
		tableR1.setDefaultRenderer(Object.class, new clrRenderTablaNoReportar());

		tableR1.setModel(TMR1);
		tableR1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		TMR2 = new MiTableModelNoEditable(new Object[][] {}, new String[] { "Concepto", "Resultado", "A", "Med", "Abr", "MnR", "MxR", "VN", "G", "TD", "Or", "Ci", "R" });

		tableR2 = new JTable();
		tableR2.setDefaultRenderer(Object.class, new clrRenderTablaNoReportar());

		tableR2.setModel(TMR2);
		tableR2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		TMR3 = new MiTableModelNoEditable(new Object[][] {}, new String[] { "Concepto", "Resultado", "A", "Med", "Abr", "MnR", "MxR", "VN", "G", "TD", "Or", "Ci", "R" });

		tableR3 = new JTable();
		tableR3.setDefaultRenderer(Object.class, new clrRenderTablaNoReportar());
		tableR3.setModel(TMR3);
		tableR3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		TMR4 = new MiTableModelNoEditable(new Object[][] {}, new String[] { "Concepto", "Resultado", "A", "Med", "Abr", "MnR", "MxR", "VN", "G", "TD", "Or", "Ci", "R" });

		tableR4 = new JTable();
		tableR4.setDefaultRenderer(Object.class, new clrRenderTablaNoReportar());

		tableR4.setModel(TMR4);
		tableR4.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
			}
		});

		this.setVisible(true);

	}

	public  boolean  llenarURC(RecepcionCompleta RC) {
		try {
			URC=null;
			RC1=null;
			RC2=null;
			RC3=null;
			RC4=null;
			cbExamenesImp.removeAllItems();
			cbExamenesImp.addItem("Imprimir solo este");

			for (int i = 0; i < RC.getItemsFactura().size(); i++) {
				itemFactura IF=RC.getItemsFactura().get(i);
				cbExamenesImp.addItem(IF.getCodExamen());
				System.out.println("Lllenando cb");
			}
			
			Recepcion recepcion = RC.getRecepcion();

			txtIdRecepcion.setText(recepcion.getIdREcepcion() + "");
			fchRecepcion.setText(recepcion.getFechaRecepcion().toString());

			txtIDPaciente.setText(recepcion.getPaciente().getId());
			txtNombrePaciente.setText(recepcion.getPaciente().getNombres());
			txtEdadPaciente.setText(recepcion.getPaciente().calcularEdad());
			String auxigenero = "Masculino";
			if (recepcion.getPaciente().getGenero() == 0) {
				auxigenero = "Femenino";
			}
			;
			txtGeneroPaciente.setText(auxigenero);
			txtApellidoPaciente.setText(recepcion.getPaciente().getApellidos());

			while ( panelReportes.getComponentCount()>0) {
				panelReportes.remove(0);
			}
			agregarPestanas(0, RC);
		

		
			ArrayList<RecepcionCompleta> ultimasRecepciones = conexionBusqueda.getInstance().recepcionesAnterioresA(recepcion.getPaciente(), RC.getRecepcion());
			for (int i = 0; i < ultimasRecepciones.size(); i++) {
				RecepcionCompleta auxiRC = ultimasRecepciones.get(i);
				agregarPestanas(i + 1, auxiRC);
			}
			panelReportes.setSelectedIndex(0);
			return true;
		} catch (Exception e) {
			return false;
		}
		
		
	}

	public void llenarExamenes(final RecepcionCompleta RecepC) {

		if (RecepC == null) {

		} else {

			ArrayList<itemFactura> itemsFactura = RecepC.getItemsFactura();

			TM1 = new MiTableModelNoEditable(new Object[][] {}, new String[] { "Examen", "Protocolo", "Nombre examen", "R", "I" });
			tablaExamenes.setModel(TM1);
			tablaExamenes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			tablaExamenes.getColumnModel().getColumn(0).setPreferredWidth(90);
			tablaExamenes.getColumnModel().getColumn(1).setPreferredWidth(110);
			tablaExamenes.getColumnModel().getColumn(2).setPreferredWidth(400);
			tablaExamenes.getColumnModel().getColumn(3).setPreferredWidth(30);
			tablaExamenes.getColumnModel().getColumn(4).setPreferredWidth(30);

			String[] paratabla = new String[5];
			for (int i = 0; i < itemsFactura.size(); i++) {
				itemFactura IF = itemsFactura.get(i);
				System.out.println("llenando " + IF.getCodExamen());

				paratabla[0] = IF.getCodExamen();
				Examen examen = conexionBusqueda.getInstance().examenXcodigo(IF.getCodExamen());
				paratabla[1] = examen.getSigla() + "";
				paratabla[2] = examen.getNombre();
				String auxiReportado = "si";
				if (IF.getReportado() == 0) {
					auxiReportado = "no";
				}
				;
				String auxiImpreso = "si";
				if (IF.getImpreso() == 0) {
					auxiImpreso = "no";
				}
				;
				paratabla[3] = auxiReportado;
				paratabla[4] = auxiImpreso;

				TM1.addRow(paratabla);

			}
			recepcionSeleccionada = RecepC;

		}

	}

	public void agregarPestanas(int indice, RecepcionCompleta RC) {

		if (indice == 0) {
			URC = RC;
			panelUR = new clrPanelBordes(false);
			panelReportes.addTab("Recepcion: " + RC.getRecepcion().getIdREcepcion(), null, panelUR, null);
			panelUR.setLayout(null);

			scrollPaneUR = new clrScrollBar();
			scrollPaneUR.setBounds(0, 0, 707, 486);
			panelUR.add(scrollPaneUR);

			scrollPaneUR.setViewportView(tableUR);

			tableUR.getColumnModel().getColumn(0).setPreferredWidth(400);
			tableUR.getColumnModel().getColumn(1).setPreferredWidth(250);
			tableUR.getColumnModel().getColumn(2).setPreferredWidth(20);
			tableUR.getColumnModel().getColumn(3).setPreferredWidth(50);
			tableUR.getColumnModel().getColumn(4).setPreferredWidth(80);
			tableUR.getColumnModel().getColumn(5).setPreferredWidth(50);
			tableUR.getColumnModel().getColumn(6).setPreferredWidth(50);
			tableUR.getColumnModel().getColumn(7).setPreferredWidth(150);
			tableUR.getColumnModel().getColumn(8).setPreferredWidth(35);
			tableUR.getColumnModel().getColumn(9).setPreferredWidth(30);
			tableUR.getColumnModel().getColumn(10).setPreferredWidth(30);
			tableUR.getColumnModel().getColumn(11).setPreferredWidth(30);
			tableUR.getColumnModel().getColumn(12).setPreferredWidth(30);
		}

		if (indice == 1) {
			RC1 = RC;
			panelR1 = new clrPanelBordes(false);
			panelReportes.addTab("Recepcion: " + RC.getRecepcion().getIdREcepcion(), null, panelR1, null);
			panelR1.setLayout(null);

			scrollPaneR1 = new clrScrollBar();
			scrollPaneR1.setBounds(0, 0, 707, 486);
			panelR1.add(scrollPaneR1);

			scrollPaneR1.setViewportView(tableR1);

			tableR1.getColumnModel().getColumn(0).setPreferredWidth(400);
			tableR1.getColumnModel().getColumn(1).setPreferredWidth(250);
			tableR1.getColumnModel().getColumn(2).setPreferredWidth(20);
			tableR1.getColumnModel().getColumn(3).setPreferredWidth(50);
			tableR1.getColumnModel().getColumn(4).setPreferredWidth(80);
			tableR1.getColumnModel().getColumn(5).setPreferredWidth(50);
			tableR1.getColumnModel().getColumn(6).setPreferredWidth(50);
			tableR1.getColumnModel().getColumn(7).setPreferredWidth(150);
			tableR1.getColumnModel().getColumn(8).setPreferredWidth(35);
			tableR1.getColumnModel().getColumn(9).setPreferredWidth(30);
			tableR1.getColumnModel().getColumn(10).setPreferredWidth(30);
			tableR1.getColumnModel().getColumn(11).setPreferredWidth(30);
			tableR1.getColumnModel().getColumn(12).setPreferredWidth(30);
		}

		if (indice == 2) {
			RC2 = RC;

			panelR2 = new clrPanelBordes(false);
			panelReportes.addTab("Recepcion: " + RC.getRecepcion().getIdREcepcion(), null, panelR2, null);
			panelR2.setLayout(null);

			scrollPaneR2 = new clrScrollBar();
			scrollPaneR2.setBounds(0, 0, 707, 486);
			panelR2.add(scrollPaneR2);

			scrollPaneR2.setViewportView(tableR2);

			tableR2.getColumnModel().getColumn(0).setPreferredWidth(400);
			tableR2.getColumnModel().getColumn(1).setPreferredWidth(250);
			tableR2.getColumnModel().getColumn(2).setPreferredWidth(20);
			tableR2.getColumnModel().getColumn(3).setPreferredWidth(50);
			tableR2.getColumnModel().getColumn(4).setPreferredWidth(80);
			tableR2.getColumnModel().getColumn(5).setPreferredWidth(50);
			tableR2.getColumnModel().getColumn(6).setPreferredWidth(50);
			tableR2.getColumnModel().getColumn(7).setPreferredWidth(150);
			tableR2.getColumnModel().getColumn(8).setPreferredWidth(35);
			tableR2.getColumnModel().getColumn(9).setPreferredWidth(30);
			tableR2.getColumnModel().getColumn(10).setPreferredWidth(30);
			tableR2.getColumnModel().getColumn(11).setPreferredWidth(30);
			tableR2.getColumnModel().getColumn(12).setPreferredWidth(30);
		}

		if (indice == 3) {
			RC3 = RC;

			panelR3 = new clrPanelBordes(false);
			panelReportes.addTab("Recepcion: " + RC.getRecepcion().getIdREcepcion(), null, panelR3, null);
			panelR3.setLayout(null);

			scrollPaneR3 = new clrScrollBar();
			scrollPaneR3.setBounds(0, 0, 707, 486);
			panelR3.add(scrollPaneR3);

			scrollPaneR3.setViewportView(tableR3);

			tableR3.getColumnModel().getColumn(0).setPreferredWidth(400);
			tableR3.getColumnModel().getColumn(1).setPreferredWidth(250);
			tableR3.getColumnModel().getColumn(2).setPreferredWidth(20);
			tableR3.getColumnModel().getColumn(3).setPreferredWidth(50);
			tableR3.getColumnModel().getColumn(4).setPreferredWidth(80);
			tableR3.getColumnModel().getColumn(5).setPreferredWidth(50);
			tableR3.getColumnModel().getColumn(6).setPreferredWidth(50);
			tableR3.getColumnModel().getColumn(7).setPreferredWidth(150);
			tableR3.getColumnModel().getColumn(8).setPreferredWidth(35);
			tableR3.getColumnModel().getColumn(9).setPreferredWidth(30);
			tableR3.getColumnModel().getColumn(10).setPreferredWidth(30);
			tableR3.getColumnModel().getColumn(11).setPreferredWidth(30);
			tableR3.getColumnModel().getColumn(12).setPreferredWidth(30);
		}

		if (indice == 4) {
			RC4 = RC;

			panelR4 = new clrPanelBordes(false);
			panelReportes.addTab("Recepcion: " + RC.getRecepcion().getIdREcepcion(), null, panelR4, null);
			panelR4.setLayout(null);

			scrollPaneR4 = new clrScrollBar();
			scrollPaneR4.setBounds(0, 0, 707, 486);
			panelR4.add(scrollPaneR4);

			scrollPaneR4.setViewportView(tableR4);

			tableR4.getColumnModel().getColumn(0).setPreferredWidth(400);
			tableR4.getColumnModel().getColumn(1).setPreferredWidth(250);
			tableR4.getColumnModel().getColumn(2).setPreferredWidth(20);
			tableR4.getColumnModel().getColumn(3).setPreferredWidth(50);
			tableR4.getColumnModel().getColumn(4).setPreferredWidth(80);
			tableR4.getColumnModel().getColumn(5).setPreferredWidth(50);
			tableR4.getColumnModel().getColumn(6).setPreferredWidth(50);
			tableR4.getColumnModel().getColumn(7).setPreferredWidth(150);
			tableR4.getColumnModel().getColumn(8).setPreferredWidth(35);
			tableR4.getColumnModel().getColumn(9).setPreferredWidth(30);
			tableR4.getColumnModel().getColumn(10).setPreferredWidth(30);
			tableR4.getColumnModel().getColumn(11).setPreferredWidth(30);
			tableR4.getColumnModel().getColumn(12).setPreferredWidth(30);
		}

	}

	public void mostrarItems(RecepcionCompleta RC) {
		ArrayList<itemFactura> IR = RC.getItemsFactura();
		for (int i = 0; i < IR.size(); i++) {
			System.out.println("ok " + IR.get(i).getCodExamen());
		}
	}

	public void llenarItems(ArrayList<ItemRecepcion> itemsRC) {
		System.err.println("llega a llenar examenes ");
		if (recepcionSeleccionada.getItemsFactura().get(tablaExamenes.getSelectedRow()).getFechaReporte().toString().equals("1970-01-01")) {
			lblUltimoReporte.setText("");
		} else {
			lblUltimoReporte.setText("Reportado el: "+recepcionSeleccionada.getItemsFactura().get(tablaExamenes.getSelectedRow()).getFechaReporte().toString());

		}
		switch (panelReportes.getSelectedIndex()) {
		case 0:
			limpiarTabla(TMUR);
			resultadosURC=new String[itemsRC.size()];
			for (int i = 0; i < itemsRC.size(); i++) {
							
				ItemRecepcion IR = itemsRC.get(i);
				
				
				String auxiResultado = "";
				if (!IR.getValorResultado().equals("-1")) {
					auxiResultado = IR.getValorResultado();
				}
				
				resultadosURC[i]=auxiResultado;

				
				String auxiMnr = "";
				if (IR.getDesde() != -1) {
					auxiMnr = IR.getDesde() + "";
				}
				
				String auxiMxr = "";
				if (IR.getHasta() != -1) {
					auxiMxr = IR.getHasta() + "";
				}
			
				String auxiG = "";
				if (IR.getGenero() != -1) {
					switch (IR.getGenero()) {
					case 1:
						 auxiG = "F";
						break;
						
					case 2:
						 auxiG = "M";
						break;
						
					case 3:
						 auxiG = "A";
						break;

					default:
						break;
					}
				}
				

				String[] datos = new String[13];
				datos[0] = IR.getConcepto();
				datos[1] = auxiResultado;
				datos[2] = "";
				datos[3] = IR.getUnidadMedida();
				datos[4] = IR.getAbreviaturaMedida();
				datos[5] = auxiMnr;
				datos[6] = auxiMxr;
				datos[7] = IR.getValorNormal();
				datos[8] = auxiG;
				datos[9] = IR.getTipoDato() + "";
				datos[10] = IR.getOrden() + "";
				datos[11] = IR.getIdItemRecepcion() + "";
				String auxireportado = "no";
				if (IR.getReportado() == 1) {
					auxireportado = "si";
				}
				
				datos[12] = auxireportado;

				TMUR.addRow(datos);
			}

			break;

		case 1:
			TMR1 = new MiTableModelNoEditable(new Object[][] {}, new String[] { "Concepto", "Resultado", "A", "Med", "Abr", "MnR", "MxR", "VN", "G", "TD", "Or", "Ci", "R" });

			tableR1.setModel(TMR1);
			tableR1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			scrollPaneR1.setViewportView(tableR1);

			tableR1.getColumnModel().getColumn(0).setPreferredWidth(400);
			tableR1.getColumnModel().getColumn(1).setPreferredWidth(250);
			tableR1.getColumnModel().getColumn(2).setPreferredWidth(20);
			tableR1.getColumnModel().getColumn(3).setPreferredWidth(50);
			tableR1.getColumnModel().getColumn(4).setPreferredWidth(80);
			tableR1.getColumnModel().getColumn(5).setPreferredWidth(50);
			tableR1.getColumnModel().getColumn(6).setPreferredWidth(50);
			tableR1.getColumnModel().getColumn(7).setPreferredWidth(150);
			tableR1.getColumnModel().getColumn(8).setPreferredWidth(35);
			tableR1.getColumnModel().getColumn(9).setPreferredWidth(30);
			tableR1.getColumnModel().getColumn(10).setPreferredWidth(30);
			tableR1.getColumnModel().getColumn(11).setPreferredWidth(30);
			tableR1.getColumnModel().getColumn(12).setPreferredWidth(30);

			for (int i = 0; i < itemsRC.size(); i++) {
				ItemRecepcion IR = itemsRC.get(i);
				String auxiResultado = "";
				if (!IR.getValorResultado().equals("-1")) {
					auxiResultado = IR.getValorResultado();
				}
				;
				String auxiMnr = "";
				if (IR.getDesde() != -1) {
					auxiMnr = IR.getDesde() + "";
				}
				;
				String auxiMxr = "";
				if (IR.getHasta() != -1) {
					auxiMxr = IR.getHasta() + "";
				}
				;
				String auxiG = "";
				if (IR.getGenero() != -1) {
					switch (IR.getGenero()) {
					case 1:
						 auxiG = "F";
						break;
						
					case 2:
						 auxiG = "M";
						break;
						
					case 3:
						 auxiG = "A";
						break;

					default:
						break;
					}
				}

				String[] datos = new String[13];
				datos[0] = IR.getConcepto();
				datos[1] = auxiResultado;
				datos[2] = "";
				datos[3] = IR.getUnidadMedida();
				datos[4] = IR.getAbreviaturaMedida();
				datos[5] = auxiMnr;
				datos[6] = auxiMxr;
				datos[7] = IR.getValorNormal();
				datos[8] = auxiG;
				datos[9] = IR.getTipoDato() + "";
				datos[10] = IR.getOrden() + "";
				datos[11] = IR.getIdItemRecepcion() + "";
				String auxireportado = "no";
				if (IR.getReportado() == 1) {
					auxireportado = "si";
				}
				;
				datos[12] = auxireportado;

				TMR1.addRow(datos);
			}
			break;

		case 2:
			TMR2 = new MiTableModelNoEditable(new Object[][] {}, new String[] { "Concepto", "Resultado", "A", "Med", "Abr", "MnR", "MxR", "VN", "G", "TD", "Or", "Ci", "R" });

			tableR2.setModel(TMR2);
			tableR2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			scrollPaneR2.setViewportView(tableR2);

			tableR2.getColumnModel().getColumn(0).setPreferredWidth(400);
			tableR2.getColumnModel().getColumn(1).setPreferredWidth(250);
			tableR2.getColumnModel().getColumn(2).setPreferredWidth(20);
			tableR2.getColumnModel().getColumn(3).setPreferredWidth(50);
			tableR2.getColumnModel().getColumn(4).setPreferredWidth(80);
			tableR2.getColumnModel().getColumn(5).setPreferredWidth(50);
			tableR2.getColumnModel().getColumn(6).setPreferredWidth(50);
			tableR2.getColumnModel().getColumn(7).setPreferredWidth(150);
			tableR2.getColumnModel().getColumn(8).setPreferredWidth(35);
			tableR2.getColumnModel().getColumn(9).setPreferredWidth(30);
			tableR2.getColumnModel().getColumn(10).setPreferredWidth(30);
			tableR2.getColumnModel().getColumn(11).setPreferredWidth(30);
			tableR2.getColumnModel().getColumn(12).setPreferredWidth(30);

			for (int i = 0; i < itemsRC.size(); i++) {
				ItemRecepcion IR = itemsRC.get(i);
				String auxiResultado = "";
				if (!IR.getValorResultado().equals("-1")) {
					auxiResultado = IR.getValorResultado();
				}
				;
				String auxiMnr = "";
				if (IR.getDesde() != -1) {
					auxiMnr = IR.getDesde() + "";
				}
				;
				String auxiMxr = "";
				if (IR.getHasta() != -1) {
					auxiMxr = IR.getHasta() + "";
				}
				;
				String auxiG = "";
				if (IR.getGenero() != -1) {
					switch (IR.getGenero()) {
					case 1:
						 auxiG = "F";
						break;
						
					case 2:
						 auxiG = "M";
						break;
						
					case 3:
						 auxiG = "A";
						break;

					default:
						break;
					}
				}

				String[] datos = new String[13];
				datos[0] = IR.getConcepto();
				datos[1] = auxiResultado;
				datos[2] = "";
				datos[3] = IR.getUnidadMedida();
				datos[4] = IR.getAbreviaturaMedida();
				datos[5] = auxiMnr;
				datos[6] = auxiMxr;
				datos[7] = IR.getValorNormal();
				datos[8] = auxiG;
				datos[9] = IR.getTipoDato() + "";
				datos[10] = IR.getOrden() + "";
				datos[11] = IR.getIdItemRecepcion() + "";
				String auxireportado = "no";
				if (IR.getReportado() == 1) {
					auxireportado = "si";
				}
				;
				datos[12] = auxireportado;

				TMR2.addRow(datos);
			}
			break;

		case 3:
			TMR3 = new MiTableModelNoEditable(new Object[][] {}, new String[] { "Concepto", "Resultado", "A", "Med", "Abr", "MnR", "MxR", "VN", "G", "TD", "Or", "Ci", "R" });

			tableR3.setModel(TMR3);
			tableR3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			scrollPaneR3.setViewportView(tableR3);

			tableR3.getColumnModel().getColumn(0).setPreferredWidth(400);
			tableR3.getColumnModel().getColumn(1).setPreferredWidth(250);
			tableR3.getColumnModel().getColumn(2).setPreferredWidth(20);
			tableR3.getColumnModel().getColumn(3).setPreferredWidth(50);
			tableR3.getColumnModel().getColumn(4).setPreferredWidth(80);
			tableR3.getColumnModel().getColumn(5).setPreferredWidth(50);
			tableR3.getColumnModel().getColumn(6).setPreferredWidth(50);
			tableR3.getColumnModel().getColumn(7).setPreferredWidth(150);
			tableR3.getColumnModel().getColumn(8).setPreferredWidth(35);
			tableR3.getColumnModel().getColumn(9).setPreferredWidth(30);
			tableR3.getColumnModel().getColumn(10).setPreferredWidth(30);
			tableR3.getColumnModel().getColumn(11).setPreferredWidth(30);
			tableR3.getColumnModel().getColumn(12).setPreferredWidth(30);

			for (int i = 0; i < itemsRC.size(); i++) {
				ItemRecepcion IR = itemsRC.get(i);

				String auxiResultado = "";
				if (!IR.getValorResultado().equals("-1")) {
					auxiResultado = IR.getValorResultado();
				}
				;
				String auxiMnr = "";
				if (IR.getDesde() != -1) {
					auxiMnr = IR.getDesde() + "";
				}
				;
				String auxiMxr = "";
				if (IR.getHasta() != -1) {
					auxiMxr = IR.getHasta() + "";
				}
				;
				String auxiG = "";
				if (IR.getGenero() != -1) {
					switch (IR.getGenero()) {
					case 1:
						 auxiG = "F";
						break;
						
					case 2:
						 auxiG = "M";
						break;
						
					case 3:
						 auxiG = "A";
						break;

					default:
						break;
					}
				}

				String[] datos = new String[13];
				datos[0] = IR.getConcepto();
				datos[1] = auxiResultado;
				datos[2] = "";
				datos[3] = IR.getUnidadMedida();
				datos[4] = IR.getAbreviaturaMedida();
				datos[5] = auxiMnr;
				datos[6] = auxiMxr;
				datos[7] = IR.getValorNormal();
				datos[8] = auxiG;
				datos[9] = IR.getTipoDato() + "";
				datos[10] = IR.getOrden() + "";
				datos[11] = IR.getIdItemRecepcion() + "";
				String auxireportado = "no";
				if (IR.getReportado() == 1) {
					auxireportado = "si";
				}
				;
				datos[12] = auxireportado;

				TMR3.addRow(datos);
			}
			break;

		case 4:
			TMR4 = new MiTableModelNoEditable(new Object[][] {}, new String[] { "Concepto", "Resultado", "A", "Med", "Abr", "MnR", "MxR", "VN", "G", "TD", "Or", "Ci", "R" });

			tableR4.setModel(TMR4);		
			tableR4.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			scrollPaneR4.setViewportView(tableR4);

			tableR4.getColumnModel().getColumn(0).setPreferredWidth(400);
			tableR4.getColumnModel().getColumn(1).setPreferredWidth(250);
			tableR4.getColumnModel().getColumn(2).setPreferredWidth(20);
			tableR4.getColumnModel().getColumn(3).setPreferredWidth(50);
			tableR4.getColumnModel().getColumn(4).setPreferredWidth(80);
			tableR4.getColumnModel().getColumn(5).setPreferredWidth(50);
			tableR4.getColumnModel().getColumn(6).setPreferredWidth(50);
			tableR4.getColumnModel().getColumn(7).setPreferredWidth(150);
			tableR4.getColumnModel().getColumn(8).setPreferredWidth(35);
			tableR4.getColumnModel().getColumn(9).setPreferredWidth(30);
			tableR4.getColumnModel().getColumn(10).setPreferredWidth(30);
			tableR4.getColumnModel().getColumn(11).setPreferredWidth(30);
			tableR4.getColumnModel().getColumn(12).setPreferredWidth(30);

			for (int i = 0; i < itemsRC.size(); i++) {
				ItemRecepcion IR = itemsRC.get(i);

				String auxiResultado = "";
				if (!IR.getValorResultado().equals("-1")) {
					auxiResultado = IR.getValorResultado();
				}
				;
				String auxiMnr = "";
				if (IR.getDesde() != -1) {
					auxiMnr = IR.getDesde() + "";
				}
				;
				String auxiMxr = "";
				if (IR.getHasta() != -1) {
					auxiMxr = IR.getHasta() + "";
				}
				;
				
				String auxiG = "";
				if (IR.getGenero() != -1) {
					switch (IR.getGenero()) {
					case 1:
						 auxiG = "F";
						break;
						
					case 2:
						 auxiG = "M";
						break;
						
					case 3:
						 auxiG = "A";
						break;

					default:
						break;
					}
				}

				String[] datos = new String[13];
				datos[0] = IR.getConcepto();
				datos[1] = auxiResultado;
				datos[2] = "";
				datos[3] = IR.getUnidadMedida();
				datos[4] = IR.getAbreviaturaMedida();
				datos[5] = auxiMnr;
				datos[6] = auxiMxr;
				datos[7] = IR.getValorNormal();
				datos[8] = auxiG;
				datos[9] = IR.getTipoDato() + "";
				datos[10] = IR.getOrden() + "";
				datos[11] = IR.getIdItemRecepcion() + "";
				String auxireportado = "no";
				if (IR.getReportado() == 1) {
					auxireportado = "si";
				}
				;
				datos[12] = auxireportado;

				TMR4.addRow(datos);
			}
			break;

		default:
			break;
		}
	}

	public void limpiarTabla(MiTableModelReportes paralimpiar) {
		if (paralimpiar != null) {
			while (paralimpiar.getRowCount() > 0)
				paralimpiar.removeRow(0);

		}
	}

	public void limpiarTabla(MiTableModelNoEditable paralimpiar) {
		if (paralimpiar != null) {
			while (paralimpiar.getRowCount() > 0)
				paralimpiar.removeRow(0);

		}

	}

	public boolean reportar(int iditemfacturaa) {
		if (conexion.getInstance().reportar(esta, paraGuardar, iditemfacturaa)) {
			return true;
		} else {
			return false;

		}
	}
	
	public boolean escribirObserva(int iditemfacturaa,String observa) {
		if (conexion.getInstance().escribirObserva(esta, iditemfacturaa, observa)) {
			return true;
		} else {
			return false;

		}
	}
	public void reportarError(String error){
		lblincorrectos.setText(error);
	}
}
