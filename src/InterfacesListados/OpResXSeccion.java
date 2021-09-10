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

public class OpResXSeccion extends JDialog {

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
	private JDateChooser fchElDia;
	private  clrComboBox cbSecciones;
	private ArrayList<itemTarifa> paraEliminar = new ArrayList<itemTarifa>();
	private ArrayList<itemTarifa> paraAgregar = new ArrayList<itemTarifa>();
	private ArrayList<itemTarifa> paraModificar = new ArrayList<itemTarifa>();
	private int[] dias = new int[] { 0, 0, 0, 0, 0, 0, 0 };
	private clrPanelBordes panelPaciente;
	private Paciente paciente;
	private Medico medico;
	private Empresa empresa;
	private Tarifa tarifa;
	private int codSubEmpresa;
	private static OpResXSeccion esta;
	private clrPanelBordes panelUR;
	private clrPanelBordes panelR1;
	private clrPanelBordes panelR2;
	private clrPanelBordes panelR3;
	private clrPanelBordes panelR4;
	private clrRadioButton chkPorSeccion;
	private clrRadioButton chkPorExamen;
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
	private ClrCuadroDeTexto txtCodigo;
	private ClrCuadroDeTexto txtNombreExamen;
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

	public OpResXSeccion(Principal principal, Usuario usuario) {
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

		lblinicio = new clrLabel("Resultados por secci\u00F3n o examen", 2, true);
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
		panelPaciente.setBounds(10, 166, 377, 96);
		contentPane.add(panelPaciente);
		
		ButtonGroup BG2=new ButtonGroup();
		
		chkPorSeccion = new clrRadioButton("Por secci\u00F3n");
		chkPorSeccion.setBounds(6, 22, 118, 23);
		chkPorSeccion.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if (chkPorSeccion.isSelected()) {
					cbSecciones.setVisible(true);
					txtCodigo.setVisible(false);
					txtNombreExamen.setVisible(false);
				} else {
					cbSecciones.setVisible(false);
					txtCodigo.setVisible(true);
					txtNombreExamen.setVisible(true);
				}
				
			}
		});
		panelPaciente.add(chkPorSeccion);
		
		 chkPorExamen = new clrRadioButton("Por examen");
		chkPorExamen.setBounds(126, 22, 118, 23);
		panelPaciente.add(chkPorExamen);
		
		
		BG2.add(chkPorExamen);
		BG2.add(chkPorSeccion);
		
		
		cbSecciones = new clrComboBox(conexionCombos.getInstance().listaSecciones(), 0);
		cbSecciones.setFocusCycleRoot(false);
		cbSecciones.setVisible(false);
		cbSecciones.setBounds(16, 52, 331, 25);
		panelPaciente.add(cbSecciones);
		
		txtCodigo= new ClrCuadroDeTexto(11, false, "C\u00F3digo",true);
		txtCodigo.setFocusCycleRoot(false);
		txtCodigo.setBounds(252, 21, 95, 25);
		txtCodigo.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				lb1.setVisible(false);
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				if (lb1==null) {
					lb1=new lbExamenXid(txtCodigo, esta, panelPaciente, esta.principal);
				}
				
			}
		});
		panelPaciente.add(txtCodigo);
		
		txtNombreExamen = new ClrCuadroDeTexto(13, false, "Nombre examen",true);
		txtNombreExamen.setFocusCycleRoot(false);
		txtNombreExamen.setBounds(16, 52, 331, 25);
		txtNombreExamen.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				lb2.setVisible(false);
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				if (lb2==null) {
					lb2=new lbExamenXnombre(txtNombreExamen, esta, panelPaciente, esta.principal);
				}
				
			}
		});
		panelPaciente.add(txtNombreExamen);

		panelRecepcion = new clrPanelBordes(false);
		panelRecepcion.setLayout(null);
		panelRecepcion.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Rango de fechas", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
		panelRecepcion.setBounds(10, 89, 377, 72);
		contentPane.add(panelRecepcion);

		fchElDia = new JDateChooser();
		fchElDia.setBounds(10, 36, 171, 25);
		fchElDia.setCalendar(hoy);
		panelRecepcion.add(fchElDia);
		fchElDia.setBorder(javax.swing.BorderFactory.createMatteBorder( 1, 1, 1, 1, Colores.clrTextoInactivo));
		
		clrLabel clrLabel_ = new clrLabel("Fecha de recpci\u00F3n", 1);
		clrLabel_.setHorizontalAlignment(SwingConstants.LEFT);
		clrLabel_.setForeground(Colores.clrTextoInactivo);
		clrLabel_.setBounds(10, 22, 136, 14);
		panelRecepcion.add(clrLabel_);
		
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
				String[] seccion=cbSecciones.getSelectedItem().toString().split(" • ");
				ArrayList<RecepcionCompleta> items=new ArrayList<RecepcionCompleta>();
				
			if (chkPorSeccion.isSelected()) {
				if (fchElDia.getDate()!=null) {
					items=conexionBusqueda.getInstance().arrayExamenesXSeccion( fchElDia.getDate(), seccion[0]);
				}else{
					lblincorrectos.setText("Falta una fecha de recepción");
				}
				
			} else {
				
				if (examen!=null) {

					if (fchElDia.getDate()!=null) {
						items=conexionBusqueda.getInstance().arrayExamenesXExamen( fchElDia.getDate(), examen.getSigla());
					}else{
						lblincorrectos.setText("Falta una fecha de recepción");
					}
					
				
				}else{
					lblincorrectos.setText("No has escogido ningún examen");
					
				}
				
			}
				
				
				if (chkImprimirDirecto.isSelected()) {
					ventanaPregunta VP1=new ventanaPregunta(OpResXSeccion.this.principal, "Estas a punto de imprimir "+items.size()+" reportes de resultados, deseas continuar", "Continuar", "Cancelar");
					if (VP1.escuchar()) {

						if (cbBacteriologo.getSelectedIndex()!=0) {
							if (txtClaveFirma.getText().equals(txtClaveFirma.getLabel())) {
							lblincorrectos.setText("Falta clave para la firma del documento");	
							} else {

								String[] codbac=cbBacteriologo.getSelectedItem().toString().split(" • ");
								bacteriologo=conexionBusqueda.getInstance().bacteriologoXid(codbac[0]);
								if (bacteriologo.getClaveFirma()==Integer.parseInt(txtClaveFirma.getText())) {
									if (items.size()>0) {
										for (int i = 0; i < items.size(); i++) {
											RecepcionCompleta RC=items.get(i);
											new imprimirReporte(RC, chkImprimirDirecto.isSelected(), queImprime,bacteriologo);
											
										}
										dispose();
									}else{
										lblincorrectos.setText("Sin examenes para impresion con estas caracteristicas");
									}
								}else{
									ventanaPregunta VP=new ventanaPregunta(OpResXSeccion.this.principal, "Clave de firma incorrecta, desea imprimir sin firma?", "Continuar", "Cancelar");
									if (VP.escuchar()) {
										if (items.size()>0) {
											for (int i = 0; i < items.size(); i++) {
												RecepcionCompleta RC=items.get(i);
												new imprimirReporte(RC, chkImprimirDirecto.isSelected(), queImprime,bacteriologo);
												
											}
											dispose();
										}else{
											lblincorrectos.setText("Sin examenes para impresion con estas caracteristicas");
										}
									} 
									
								}
							

							}
							
						} else{
							if (items.size()>0) {
								for (int i = 0; i < items.size(); i++) {
									RecepcionCompleta RC=items.get(i);
									new imprimirReporte(RC, chkImprimirDirecto.isSelected(), queImprime,bacteriologo);
									
								}
								dispose();
							}else{
								lblincorrectos.setText("Sin examenes para impresion con estas caracteristicas");
							}
							
							
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
								if (items.size()>0) {
									for (int i = 0; i < items.size(); i++) {
										RecepcionCompleta RC=items.get(i);
										new imprimirReporte(RC, chkImprimirDirecto.isSelected(), queImprime,bacteriologo);
										
									}
									dispose();
								}else{
									lblincorrectos.setText("Sin examenes para impresion con estas caracteristicas");
								}
							}else{
								ventanaPregunta VP=new ventanaPregunta(OpResXSeccion.this.principal, "Clave de firma incorrecta, desea imprimir sin firma?", "Continuar", "Cancelar");
								if (VP.escuchar()) {
									if (items.size()>0) {
										for (int i = 0; i < items.size(); i++) {
											RecepcionCompleta RC=items.get(i);
											new imprimirReporte(RC, chkImprimirDirecto.isSelected(), queImprime,bacteriologo);
											
										}
										dispose();
									}else{
										lblincorrectos.setText("Sin examenes para impresion con estas caracteristicas");
									}
								} 
								
							}
						

						}
						
					} else{
						if (items.size()>0) {
							for (int i = 0; i < items.size(); i++) {
								RecepcionCompleta RC=items.get(i);
								new imprimirReporte(RC, chkImprimirDirecto.isSelected(), queImprime,bacteriologo);
								
							}
							dispose();
						}else{
							lblincorrectos.setText("Sin examenes para impresion con estas caracteristicas");
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



	public void llenarExamen(Examen examen){
		this.examen=examen;
		txtCodigo.setText(examen.getSigla());
		txtCodigo.setForeground(Color.black);
		
		txtNombreExamen.setText(examen.getNombre());
		txtNombreExamen.setForeground(Color.BLACK);
		
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
