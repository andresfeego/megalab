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

import otrosImpresos.imprimirReporte;
import Interfaces.Principal;
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
import auxiliares.itemFactura;
import auxiliares.itemTarifa;
import auxiliares.protocolo;
import conexion.conexion;
import conexion.conexionBusqueda;
import conexion.conexionCombos;

public class OpResXRecepcion extends JDialog {

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
	private ClrCuadroDeTexto txtIdRecepcion;
	private ClrCuadroDeTexto txtGeneroPaciente;
	private clrComboBox cbExamenesImp ;
	private Paciente paciente;
	private Medico medico;
	private Empresa empresa;
	private Tarifa tarifa;
	private int codSubEmpresa;
	private static OpResXRecepcion esta;
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
	private clrRadioButton chkImprimirRep;
	private clrRadioButton chkImprimirTodo;
	private clrRadioButton chkImprimirNoRep;
	private clrRadioButton chkImprimirDirecto ;
	private clrRadioButton chkVistaPrevia;

	private RecepcionCompleta URC;

	private String[] resultadosURC;
	private ArrayList<Reporte> paraGuardar=new ArrayList<Reporte>();
private 	int idExamenReporte;
	
	private RecepcionCompleta recepcionSeleccionada;

	private boolean porReportar = false;
	private clrLabel lblUltimoReporte;
	private clrComboBox cbBacteriologo;
	private ClrCuadroDeTexto txtClaveFirma;

	public OpResXRecepcion(Principal principal, Usuario usuario) {
		super(principal, true);
		this.esta = this;
		this.principal = principal;
		this.usuario = usuario;
		this.esta = this;
		this.accion = accion;

		// this.setDefaultLookAndFeelDecorated(false);

		setBounds(100, 100, 401, 567);
		this.setUndecorated(true);
		this.setLocationRelativeTo(getParent());
		contentPane = new clrPanelBordes(true);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		lblinicio = new clrLabel("Resultados por recepci\u00F3n", 2, true);
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

		clrPanelBordes clrPanelBordes_ = new clrPanelBordes(false);
		clrPanelBordes_.setLayout(null);
		clrPanelBordes_.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Información de paciente", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
		clrPanelBordes_.setBounds(10, 273, 377, 284);
		contentPane.add(clrPanelBordes_);

		chkImprimirRep= new clrRadioButton("Imprimir reportados");
		chkImprimirRep.setBounds(10, 47, 179, 23);
		chkImprimirRep.setSelected(true);
		clrPanelBordes_.add(chkImprimirRep);

		chkImprimirTodo = new clrRadioButton("Imprimir todo");
		chkImprimirTodo.setBounds(210, 21, 133, 23);
		clrPanelBordes_.add(chkImprimirTodo);

		chkImprimirNoRep = new clrRadioButton("Imprimir no reportados");
		chkImprimirNoRep.setBounds(10, 21, 198, 23);
		clrPanelBordes_.add(chkImprimirNoRep);

		ButtonGroup BG1=new ButtonGroup();
		BG1.add(chkImprimirTodo);
		BG1.add(chkImprimirNoRep);
		BG1.add(chkImprimirRep);
		 cbExamenesImp = new clrComboBox(new String[] {},0);
		cbExamenesImp.setFocusCycleRoot(false);
		cbExamenesImp.setBounds(10, 77, 198, 25);
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
		separator.setBounds(10, 113, 357, 2);
		clrPanelBordes_.add(separator);
		
		ButtonGroup BG=new ButtonGroup();

		chkImprimirDirecto = new clrRadioButton("Imprimir directamente en impresora");
		chkImprimirDirecto.setBounds(10, 122, 289, 23);
		BG.add(chkImprimirDirecto);
		clrPanelBordes_.add(chkImprimirDirecto);

		chkVistaPrevia = new clrRadioButton("Vista previa");
		chkVistaPrevia.setBounds(10, 148, 120, 23);
		chkVistaPrevia.setSelected(true);
		BG.add(chkVistaPrevia);
		clrPanelBordes_.add(chkVistaPrevia);

		btnRedondo btnImprimir = new btnRedondo("Imprimir", new Rectangle(237, 172,121,50), 15);
		btnImprimir.setBounds(30, 224, 144, 50);
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
								ventanaPregunta VP=new ventanaPregunta(OpResXRecepcion.this.principal, "Clave de firma incorrecta, desea imprimir sin firma?", "Continuar", "Cancelar");
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
		cbBacteriologo.setBounds(10, 188, 198, 25);
		clrPanelBordes_.add(cbBacteriologo);
		
		txtClaveFirma = new ClrCuadroDeTexto(100, true, "Clave firma");
		txtClaveFirma.setHorizontalAlignment(SwingConstants.LEFT);
		txtClaveFirma.setFocusCycleRoot(false);
		txtClaveFirma.setBounds(218, 188, 149, 25);
		clrPanelBordes_.add(txtClaveFirma);
		
		clrSeparador clrSeparador_ = new clrSeparador();
		clrSeparador_.setBounds(10, 178, 357, 12);
		clrPanelBordes_.add(clrSeparador_);
		
				btnSalir = new btnRedondo("Salir", new Rectangle(237, 172,121,50), 9);
				btnSalir.setBounds(184, 224, 114, 50);
				clrPanelBordes_.add(btnSalir);
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
		
		lblUltimoReporte = new clrLabel("", 1);
		lblUltimoReporte.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUltimoReporte.setForeground(Colores.clrAlertaCamarada);
		lblUltimoReporte.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblUltimoReporte.setAlignmentX(0.5f);
		lblUltimoReporte.setBounds(789, 60, 266, 28);
		contentPane.add(lblUltimoReporte);


	

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
			}
		});

		this.setVisible(true);

	}

	public  boolean  llenarURC(RecepcionCompleta RC) {
		try {
			URC=RC;
		
			cbExamenesImp.removeAllItems();
			cbExamenesImp.addItem("Imprimir solo este");

			for (int i = 0; i < RC.getItemsFactura().size(); i++) {
				itemFactura IF=RC.getItemsFactura().get(i);
				cbExamenesImp.addItem(IF.getCodExamen());
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

		
			return true;
		} catch (Exception e) {
			return false;
		}
		
		
	}

	
	
	public boolean reportar(int iditemfacturaa) {
		if (true) {
			return true;
		} else {
			return false;

		}
	}
	public void reportarError(String error){
		lblincorrectos.setText(error);
	}
}
