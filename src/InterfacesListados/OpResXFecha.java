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
import java.util.Calendar;
import java.util.GregorianCalendar;

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

import com.toedter.calendar.JDateChooser;

import otrosImpresos.imprimirReporte;
import Interfaces.Principal;
import Interfaces.lbExamenXid;
import Interfaces.lbExamenXnombre;
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

import java.awt.Color;

public class OpResXFecha extends JDialog {

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
	private lbExamenXid lb1;
	private lbExamenXnombre lb2;
	private JDateChooser fchDesde;
	private JDateChooser fchHasta;
	private ArrayList<itemTarifa> paraEliminar = new ArrayList<itemTarifa>();
	private ArrayList<itemTarifa> paraAgregar = new ArrayList<itemTarifa>();
	private ArrayList<itemTarifa> paraModificar = new ArrayList<itemTarifa>();
	private int[] dias = new int[] { 0, 0, 0, 0, 0, 0, 0 };
	private Paciente paciente;
	private Medico medico;
	private Empresa empresa;
	private Tarifa tarifa;
	private int codSubEmpresa;
	private static OpResXFecha esta;
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
	private Calendar hoy=new GregorianCalendar();
	private Examen examen;
	private RecepcionCompleta URC;

	private String[] resultadosURC;
	private ArrayList<Reporte> paraGuardar=new ArrayList<Reporte>();
private 	int idExamenReporte;
	
	private RecepcionCompleta recepcionSeleccionada;

	private boolean porReportar = false;
	private clrLabel lblUltimoReporte;
	private clrComboBox cbBacteriologo;
	private ClrCuadroDeTexto txtClaveFirma;

	public OpResXFecha(Principal principal, Usuario usuario) {
		super(principal, true);
		this.esta = this;
		this.principal = principal;
		this.usuario = usuario;
		this.esta = this;
		this.accion = accion;

		// this.setDefaultLookAndFeelDecorated(false);

		setBounds(100, 100, 401, 471);
		this.setUndecorated(true);
		this.setLocationRelativeTo(getParent());
		contentPane = new clrPanelBordes(true);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		lblinicio = new clrLabel("Resultados por fecha", 2, true);
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
		
		ButtonGroup BG2=new ButtonGroup();

		panelRecepcion = new clrPanelBordes(false);
		panelRecepcion.setLayout(null);
		panelRecepcion.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Rango de fechas", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
		panelRecepcion.setBounds(10, 89, 377, 72);
		contentPane.add(panelRecepcion);

		fchDesde = new JDateChooser();
		fchDesde.setBounds(10, 36, 171, 25);
		fchDesde.setCalendar(hoy);
		panelRecepcion.add(fchDesde);
		fchDesde.setBorder(javax.swing.BorderFactory.createMatteBorder( 1, 1, 1, 1, Colores.clrTextoInactivo));
		
		fchHasta = new JDateChooser();
		fchHasta.setBounds(196, 36, 171, 25);
		fchHasta.setCalendar(hoy);
		panelRecepcion.add(fchHasta);
		fchHasta.setBorder(javax.swing.BorderFactory.createMatteBorder( 1, 1, 1, 1, Colores.clrTextoInactivo));
		
		clrLabel clrLabel_ = new clrLabel("Desde", 1);
		clrLabel_.setHorizontalAlignment(SwingConstants.LEFT);
		clrLabel_.setForeground(Colores.clrTextoInactivo);
		clrLabel_.setBounds(10, 22, 136, 14);
		panelRecepcion.add(clrLabel_);
		
		clrLabel clrLabel__1 = new clrLabel("Hasta", 1);
		clrLabel__1.setHorizontalAlignment(SwingConstants.LEFT);
		clrLabel__1.setForeground(new Color(0, 0, 0, 130));
		clrLabel__1.setBounds(195, 22, 136, 14);
		panelRecepcion.add(clrLabel__1);
		
		clrPanelBordes clrPanelBordes_ = new clrPanelBordes(false);
		clrPanelBordes_.setLayout(null);
		clrPanelBordes_.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Información de paciente", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
		clrPanelBordes_.setBounds(10, 172, 377, 284);
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

				String queImprime="0";
				if (chkImprimirNoRep.isSelected()) {
					queImprime="1";
				}else{
					if (chkImprimirRep.isSelected()) {
						queImprime="2";
				
					} 
				}
				Bacteriologo bacteriologo=null;
				
				if (fchDesde.getDate()==null || fchHasta.getDate()==null ) {
					lblincorrectos.setText("Faltan fechas para la consulta de resultados");
				} else {
					ArrayList<RecepcionCompleta> items=new ArrayList<RecepcionCompleta>();
					items=conexionBusqueda.getInstance().arrayExamenesXFecha( fchDesde.getDate(), fchHasta.getDate());

					
					if (items.size()<0) {
						lblincorrectos.setText("No hay resultados para esta consulta");
					} else {
						if (chkImprimirDirecto.isSelected()) {
						ventanaPregunta VP3=new ventanaPregunta(OpResXFecha.this.principal, "Estas a punto de imprimir "+items.size()+" reportes de resultados, deseas continuar", "Continuar", "Cancelar");
						
						if (VP3.escuchar()) {


							
							if (cbBacteriologo.getSelectedIndex()!=0) {
								if (txtClaveFirma.getText().equals(txtClaveFirma.getLabel())) {
								lblincorrectos.setText("Falta clave para la firma del documento");	
								} else {

									String[] codbac=cbBacteriologo.getSelectedItem().toString().split(" • ");
									bacteriologo=conexionBusqueda.getInstance().bacteriologoXid(codbac[0]);
									if (bacteriologo.getClaveFirma()==Integer.parseInt(txtClaveFirma.getText())) {

										for (int i = 0; i < items.size(); i++) {
											RecepcionCompleta RC=items.get(i);
											new imprimirReporte(RC, chkImprimirDirecto.isSelected(), queImprime,bacteriologo);
											
										}dispose();
									
									
									}else{
										ventanaPregunta VP=new ventanaPregunta(OpResXFecha.this.principal, "Clave de firma incorrecta, desea imprimir sin firma?", "Continuar", "Cancelar");
										if (VP.escuchar()) {

											for (int i = 0; i < items.size(); i++) {
												RecepcionCompleta RC=items.get(i);
												new imprimirReporte(RC, chkImprimirDirecto.isSelected(), queImprime,bacteriologo);
												
											}dispose();
										
										
										} 
										
									}
								

								}
								
							} else{
								for (int i = 0; i < items.size(); i++) {
									RecepcionCompleta RC=items.get(i);
									System.out.println(RC.getRecepcion().getIdREcepcion());

									new imprimirReporte(RC, chkImprimirDirecto.isSelected(), queImprime,bacteriologo);
									
								}dispose();
							
							}
						
						
						} 
						
					} else {

						
						if (cbBacteriologo.getSelectedIndex()!=0) {
							if (txtClaveFirma.getText().equals(txtClaveFirma.getLabel())) {
							lblincorrectos.setText("Falta clave para la firma del documento");	
							} else {

								String[] codbac=cbBacteriologo.getSelectedItem().toString().split(" • ");
								bacteriologo=conexionBusqueda.getInstance().bacteriologoXid(codbac[0]);
								if (bacteriologo.getClaveFirma()==Integer.parseInt(txtClaveFirma.getText())) {

									for (int i = 0; i < items.size(); i++) {
										RecepcionCompleta RC=items.get(i);
										new imprimirReporte(RC, chkImprimirDirecto.isSelected(), queImprime,bacteriologo);
										
									}dispose();
								
								
								}else{
									ventanaPregunta VP=new ventanaPregunta(OpResXFecha.this.principal, "Clave de firma incorrecta, desea imprimir sin firma?", "Continuar", "Cancelar");
									if (VP.escuchar()) {

										for (int i = 0; i < items.size(); i++) {
											RecepcionCompleta RC=items.get(i);
											new imprimirReporte(RC, chkImprimirDirecto.isSelected(), queImprime,bacteriologo);
											
										}dispose();
									
									
									} 
									
								}
							

							}
							
						} else{
							for (int i = 0; i < items.size(); i++) {
								RecepcionCompleta RC=items.get(i);
								System.out.println(RC.getRecepcion().getIdREcepcion());
								new imprimirReporte(RC, chkImprimirDirecto.isSelected(), queImprime,bacteriologo);
								
							}dispose();
						
						}
					
					}
					}
					
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
