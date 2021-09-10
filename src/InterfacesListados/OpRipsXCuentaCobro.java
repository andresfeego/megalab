package InterfacesListados;

import interfaces_Modificadas.ClrCuadroDeTexto;
import interfaces_Modificadas.Colores;
import interfaces_Modificadas.MiTableModelNoEditable;
import interfaces_Modificadas.MiTableModelReportes;
import interfaces_Modificadas.ReportesTableCellEditor;
import interfaces_Modificadas.btnRedondo;
import interfaces_Modificadas.circuloUsuario;
import interfaces_Modificadas.clrComboBox;
import interfaces_Modificadas.clrLabel;
import interfaces_Modificadas.clrPanelBordes;
import interfaces_Modificadas.clrPestanas;
import interfaces_Modificadas.clrRadioButton;
import interfaces_Modificadas.clrRenderTablaExamenesXReportar;
import interfaces_Modificadas.clrRenderTablaNoReportar;
import interfaces_Modificadas.clrRenderTablaReportar;
import interfaces_Modificadas.clrSeparador;

import java.awt.Component;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellEditor;

import com.sun.glass.ui.Pixels.Format;

import otrosImpresos.RIPS;
import otrosImpresos.imprimirReporte;
import Interfaces.Principal;
import Interfaces.lbCuentaXId;
import Interfaces.lbRecepcionXId;
import Interfaces.ventanaPregunta;
import auxiliares.Bacteriologo;
import auxiliares.Empresa;
import auxiliares.Examen;
import auxiliares.ItemRecepcion;
import auxiliares.Medico;
import auxiliares.Paciente;
import auxiliares.Recepcion;
import auxiliares.RecepcionCompleta;
import auxiliares.Reporte;
import auxiliares.Tarifa;
import auxiliares.Usuario;
import auxiliares.cuentaCobro;
import auxiliares.itemFactura;
import auxiliares.itemTarifa;
import auxiliares.protocolo;
import conexion.conexion;
import conexion.conexionBusqueda;
import conexion.conexionCombos;

public class OpRipsXCuentaCobro extends JDialog {

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
	private lbCuentaXId lb1;

	
	private ArrayList<itemTarifa> paraEliminar = new ArrayList<itemTarifa>();
	private ArrayList<itemTarifa> paraAgregar = new ArrayList<itemTarifa>();
	private ArrayList<itemTarifa> paraModificar = new ArrayList<itemTarifa>();
	private int[] dias = new int[] { 0, 0, 0, 0, 0, 0, 0 };
	private ClrCuadroDeTexto fchRecepcion;
	private clrPanelBordes panelPaciente;
	private ClrCuadroDeTexto txtIdEmpresa;
	private ClrCuadroDeTexto txtDesde;
	private ClrCuadroDeTexto txtHasta;
	private ClrCuadroDeTexto txtNit;
	private ClrCuadroDeTexto txtIdCuenta;
	private clrComboBox cbExamenesImp ;
	private Paciente paciente;
	private Medico medico;
	private Empresa empresa;
	private Tarifa tarifa;
	private int codSubEmpresa;
	private static OpRipsXCuentaCobro esta;
	private clrPanelBordes panelUR;
	private clrPanelBordes panelR1;
	private clrPanelBordes panelR2;
	private clrPanelBordes panelR3;
	private clrPanelBordes panelR4;
	
	private clrPanelBordes panelRecepcion;
	private JScrollPane scrollPaneUR;
	private JScrollPane scrollPaneR1;
	private JScrollPane scrollPaneR2;
	private JScrollPane scrollPaneR3;
	private JScrollPane scrollPaneR4;
	private cuentaCobro CC;

	private RecepcionCompleta URC;

	private String[] resultadosURC;
	private ArrayList<Reporte> paraGuardar=new ArrayList<Reporte>();
private 	int idExamenReporte;
	
	private RecepcionCompleta recepcionSeleccionada;

	private boolean porReportar = false;
	private clrLabel lblUltimoReporte;
	private ClrCuadroDeTexto txtNumSer;
	private ClrCuadroDeTexto txtTotalPago;
	private ClrCuadroDeTexto txtRazonSocial;
	private ClrCuadroDeTexto txtNumContrato;

	public OpRipsXCuentaCobro(Principal principal, Usuario usuario) {
		super(principal, true);
		this.esta = this;
		this.principal = principal;
		this.usuario = usuario;
		this.esta = this;
		this.accion = accion;

		// this.setDefaultLookAndFeelDecorated(false);

		setBounds(100, 100, 401, 461);
		this.setUndecorated(true);
		this.setLocationRelativeTo(getParent());
		contentPane = new clrPanelBordes(true);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		lblinicio = new clrLabel("Archivos planos RIPS", 2, true);
		lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblinicio.setBounds(10, 11, 377, 28);
		contentPane.add(lblinicio);

		lblincorrectos = new clrLabel("", 1);
		lblincorrectos.setForeground(Colores.clrAlertaCamarada);
		lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
		lblincorrectos.setAlignmentX(0.5f);
		lblincorrectos.setBounds(10, 50, 377, 28);
		contentPane.add(lblincorrectos);


		panelPaciente = new clrPanelBordes(false);
		panelPaciente.setLayout(null);
		panelPaciente.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Información de la cuenta", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
		panelPaciente.setBounds(10, 159, 377, 178);
		contentPane.add(panelPaciente);

		txtIdEmpresa = new ClrCuadroDeTexto(15,true,"Id");
		txtIdEmpresa.setHorizontalAlignment(SwingConstants.LEFT);
		txtIdEmpresa.setFocusCycleRoot(false);
		txtIdEmpresa.setEnabled(false);
		txtIdEmpresa.setBounds(10, 28, 168, 25);

		panelPaciente.add(txtIdEmpresa);

		txtDesde = new ClrCuadroDeTexto(100,false,"Desde");
		txtDesde.setFocusCycleRoot(false);
		txtDesde.setEnabled(false);
		txtDesde.setBounds(10, 95, 168, 25);

		panelPaciente.add(txtDesde);

		txtHasta = new ClrCuadroDeTexto(100,false,"Hasta");
		txtHasta.setFocusCycleRoot(false);
		txtHasta.setEnabled(false);
		txtHasta.setBounds(188, 95, 179, 25);

		panelPaciente.add(txtHasta);

		txtNit = new ClrCuadroDeTexto(100,false,"Nit");
		txtNit.setHorizontalAlignment(SwingConstants.LEFT);
		txtNit.setFocusCycleRoot(false);
		txtNit.setEnabled(false);
		txtNit.setBounds(188, 28, 179, 25);
		panelPaciente.add(txtNit);
		
		txtNumSer = new ClrCuadroDeTexto(100, false, "N\u00FAmero de servicios");
		txtNumSer.setFocusCycleRoot(false);
		txtNumSer.setEnabled(false);
		txtNumSer.setBounds(10, 126, 168, 25);
		panelPaciente.add(txtNumSer);
		
		txtTotalPago = new ClrCuadroDeTexto(100, false, "Total a pagar");
		txtTotalPago.setFocusCycleRoot(false);
		txtTotalPago.setEnabled(false);
		txtTotalPago.setBounds(188, 126, 179, 25);
		panelPaciente.add(txtTotalPago);
		
		txtRazonSocial = new ClrCuadroDeTexto(100, false, "Raz\u00F3n Social");
		txtRazonSocial.setHorizontalAlignment(SwingConstants.LEFT);
		txtRazonSocial.setFocusCycleRoot(false);
		txtRazonSocial.setEnabled(false);
		txtRazonSocial.setBounds(10, 59, 357, 25);
		panelPaciente.add(txtRazonSocial);

		panelRecepcion = new clrPanelBordes(false);
		panelRecepcion.setLayout(null);
		panelRecepcion.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Informaci\u00F3n de recepci\u00F3n", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
		panelRecepcion.setBounds(10, 89, 377, 59);
		contentPane.add(panelRecepcion);

		txtIdCuenta = new ClrCuadroDeTexto(100,false,"# Cuenta de cobro", true);
		txtIdCuenta.setBounds(10, 21, 169, 25);
		txtIdCuenta.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				lb1.setVisible(false);

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (lb1 == null) {
					lb1 = new lbCuentaXId(txtIdCuenta, esta, panelRecepcion, esta.principal);
				}

			}
		});
		panelRecepcion.add(txtIdCuenta);
		txtIdCuenta.setFocusCycleRoot(false);

		fchRecepcion = new ClrCuadroDeTexto(500,false,"Fecha de emisi\u00F3n");
		fchRecepcion.setBounds(189, 21, 178, 25);
		panelRecepcion.add(fchRecepcion);
		fchRecepcion.setEnabled(false);
		fchRecepcion.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, Colores.clrTextoInactivo));
		
		ButtonGroup BG=new ButtonGroup();
		
		lblUltimoReporte = new clrLabel("", 1);
		lblUltimoReporte.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUltimoReporte.setForeground(Colores.clrAlertaCamarada);
		lblUltimoReporte.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblUltimoReporte.setAlignmentX(0.5f);
		lblUltimoReporte.setBounds(789, 60, 266, 28);
		contentPane.add(lblUltimoReporte);
		
				btnSalir = new btnRedondo("Salir", new Rectangle(237, 172,121,50), 9);
				btnSalir.setBounds(211, 400, 114, 50);
				contentPane.add(btnSalir);
				
						btnRedondo btnGenerar = new btnRedondo("Generar", new Rectangle(237, 172,121,50), 16);
						btnGenerar.setBounds(57, 400, 144, 50);
						contentPane.add(btnGenerar);
						
						txtNumContrato = new ClrCuadroDeTexto(15, false, "N\u00FAmero de contrato");
						txtNumContrato.setFocusCycleRoot(false);
						txtNumContrato.setEnabled(false);
						txtNumContrato.setBounds(10, 343, 240, 25);
						contentPane.add(txtNumContrato);
						btnGenerar.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								generar();
							}
						});
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});


	

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
			}
		});

		this.setVisible(true);

	}

	public  void  llenarCuenta(cuentaCobro CCC) {
		this.CC=CCC;
		txtIdCuenta.setText(""+CCC.getIdCuenta());
SimpleDateFormat formatter;
		
		formatter = new SimpleDateFormat("dd-MM-yyyy");
		String fechaF = formatter.format(CCC.getFechaCuenta());
		fchRecepcion.setText(fechaF);
		Empresa empresa=conexionBusqueda.getInstance().empresaXid(CCC.getIdEmpresa()+"");
		txtIdEmpresa.setText(empresa.getIdEmpresa()+"");
		txtNit.setText(empresa.getDocEmpresa());
		txtRazonSocial.setText(empresa.getRazonSocial());
		txtDesde.setText(formatter.format(CCC.getInicioRango()));
		txtHasta.setText(formatter.format(CCC.getFinRango()));
		txtNumSer.setText(CCC.getCantServicios()+"");
		txtTotalPago.setText(CCC.getNetoPago()+"");
		
		
		
		
	}

	
	
	public void generar() {
	if (CC==null) {
		lblincorrectos.setText("No has escogido ninguna cuenta");
	} else {
		if (txtNumContrato.getText().equals(txtNumContrato.getLabel())) {
			String numContrato="";
			new RIPS(CC,numContrato);

		} else {
			new RIPS(CC,txtNumContrato.getText());

		}
	}
	}
	
	public void reportarError(String error){
		lblincorrectos.setText(error);
	}
}
