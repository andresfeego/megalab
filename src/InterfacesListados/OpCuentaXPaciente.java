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

import otrosImpresos.CuentaCobroXEmpresa;
import otrosImpresos.CuentaCobroXPaciente;
import otrosImpresos.imprimirReporte;
import Interfaces.Principal;
import Interfaces.lbEmpresaXcod;
import Interfaces.lbEmpresaXnit;
import Interfaces.lbEmpresaXrazon;
import Interfaces.lbExamenXid;
import Interfaces.lbExamenXnombre;
import Interfaces.lbPacienteXapellido;
import Interfaces.lbPacienteXid;
import Interfaces.lbPacienteXnombre;
import Interfaces.lbRecepcionXId;
import Interfaces.ventanaPregunta;
import auxiliares.Bacteriologo;
import auxiliares.DatosAbono;
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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class OpCuentaXPaciente extends JDialog {

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
	private lbPacienteXid lb1;
	private lbPacienteXnombre lb2;
	private lbPacienteXapellido lb3;
	private JDateChooser fchHasta;
	private JDateChooser fchDesde;
	private ArrayList<itemTarifa> paraEliminar = new ArrayList<itemTarifa>();
	private ArrayList<itemTarifa> paraAgregar = new ArrayList<itemTarifa>();
	private ArrayList<itemTarifa> paraModificar = new ArrayList<itemTarifa>();
	private int[] dias = new int[] { 0, 0, 0, 0, 0, 0, 0 };
	private Paciente paciente;
	private Medico medico;
	private Tarifa tarifa;
	private int codSubEmpresa;
	private static OpCuentaXPaciente esta;
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
	private clrRadioButton chkImprimirDirecto ;
	private clrRadioButton chkVistaPrevia;
	private Calendar hoy=new GregorianCalendar();
	private RecepcionCompleta URC;

	private String[] resultadosURC;
	private ArrayList<Reporte> paraGuardar=new ArrayList<Reporte>();
private 	int idExamenReporte;
	
	private RecepcionCompleta recepcionSeleccionada;

	private boolean porReportar = false;
	private clrLabel lblUltimoReporte;
	private clrComboBox cbBacteriologo;
	private ClrCuadroDeTexto txtClaveFirma;
	
	private ClrCuadroDeTexto txtIdPaciente;
	private ClrCuadroDeTexto txtApellidosPaciente;
	private ClrCuadroDeTexto txtNombrePaciente;
	private clrPanelBordes panelPaciente;
	private clrRadioButton chkDetallar ;
	private ArrayList<DatosAbono> items;
	private int saldoTotal=0;
	private clrLabel clrLabel;
	
	public OpCuentaXPaciente(Principal principal, Usuario usuario) {
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

		lblinicio = new clrLabel("Cuenta de cobro por paciente", 2, true);
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
		
		fchHasta = new JDateChooser();
		fchHasta.setBounds(196, 36, 171, 25);
		fchHasta.setCalendar(hoy);
		panelRecepcion.add(fchHasta);
		fchHasta.setBorder(javax.swing.BorderFactory.createMatteBorder( 1, 1, 1, 1, Colores.clrTextoInactivo));
		
		fchHasta.getDateEditor().addPropertyChangeListener(
			    new PropertyChangeListener() {
			        @Override
			        public void propertyChange(PropertyChangeEvent e) {
			          
			        	if (paciente!=null) {
							llenarPaciente(paciente);
						}
			        	
			        }
			    });
		
		fchDesde = new JDateChooser();
		fchDesde.setBounds(10, 36, 171, 25);
		fchDesde.setCalendar(hoy);
		panelRecepcion.add(fchDesde);
		fchDesde.setBorder(javax.swing.BorderFactory.createMatteBorder( 1, 1, 1, 1, Colores.clrTextoInactivo));
		
		fchDesde.getDateEditor().addPropertyChangeListener(
			    new PropertyChangeListener() {
			        @Override
			        public void propertyChange(PropertyChangeEvent e) {
			          
			        	if (paciente!=null) {
							llenarPaciente(paciente);
						}
			        	
			        }
			    });
		
		clrLabel clrLabel__1 = new clrLabel("Hasta el d\u00EDa", 1);
		clrLabel__1.setHorizontalAlignment(SwingConstants.LEFT);
		clrLabel__1.setForeground(new Color(0, 0, 0, 130));
		clrLabel__1.setBounds(196, 22, 122, 14);
		panelRecepcion.add(clrLabel__1);
		
		clrLabel = new clrLabel("Hasta el d\u00EDa", 1);
		clrLabel.setHorizontalAlignment(SwingConstants.LEFT);
		clrLabel.setForeground(new Color(0, 0, 0, 130));
		clrLabel.setBounds(10, 22, 122, 14);
		panelRecepcion.add(clrLabel);
		
		panelPaciente = new clrPanelBordes(false);
		panelPaciente.setLayout(null);
		panelPaciente.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Información de paciente", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
		panelPaciente.setBounds(10, 172, 377, 284);
		contentPane.add(panelPaciente);

		ButtonGroup BG1=new ButtonGroup();
	

		clrSeparador separator = new clrSeparador();
		separator.setBounds(10, 113, 357, 2);
		panelPaciente.add(separator);
		
		ButtonGroup BG=new ButtonGroup();

		chkImprimirDirecto = new clrRadioButton("Imprimir directamente en impresora");
		chkImprimirDirecto.setBounds(10, 122, 289, 23);
		BG.add(chkImprimirDirecto);
		panelPaciente.add(chkImprimirDirecto);

		chkVistaPrevia = new clrRadioButton("Vista previa");
		chkVistaPrevia.setBounds(10, 148, 120, 23);
		chkVistaPrevia.setSelected(true);
		BG.add(chkVistaPrevia);
		panelPaciente.add(chkVistaPrevia);

		btnRedondo btnImprimir = new btnRedondo("Imprimir", new Rectangle(237, 172,121,50), 15);
		btnImprimir.setBounds(30, 224, 144, 50);
		btnImprimir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

			
				Bacteriologo bacteriologo=null;
				
				if (fchHasta.getDate()==null ) {
					lblincorrectos.setText("Falta fecha para la consulta de resultados");
				} else {
				

					
					if (items.size()<0) {
						lblincorrectos.setText("No hay resultados para esta consulta");
					} else {

						
						if (cbBacteriologo.getSelectedIndex()!=0) {
							if (txtClaveFirma.getText().equals(txtClaveFirma.getLabel())) {
							lblincorrectos.setText("Falta clave para la firma del documento");	
							} else {

								String[] codbac=cbBacteriologo.getSelectedItem().toString().split(" • ");
								bacteriologo=conexionBusqueda.getInstance().bacteriologoXid(codbac[0]);
								if (bacteriologo.getClaveFirma()==Integer.parseInt(txtClaveFirma.getText())) {

									new CuentaCobroXPaciente(paciente, items, saldoTotal, chkImprimirDirecto.isSelected(), chkDetallar.isSelected(),bacteriologo);
									dispose();
								
								}else{
									ventanaPregunta VP=new ventanaPregunta(OpCuentaXPaciente.this.principal, "Clave de firma incorrecta, desea imprimir sin firma?", "Continuar", "Cancelar");
									if (VP.escuchar()) {

										new CuentaCobroXPaciente(paciente, items, saldoTotal, chkImprimirDirecto.isSelected(), chkDetallar.isSelected(),bacteriologo);
										dispose();
		
									
									
									} 
									
								}
							

							}
							
						} else{
							new CuentaCobroXPaciente(paciente, items, saldoTotal, chkImprimirDirecto.isSelected(), chkDetallar.isSelected(),bacteriologo);
							dispose();

						
						}
					
					}
					
				}
			
			
			}
		});
		panelPaciente.add(btnImprimir);
		
		cbBacteriologo = new clrComboBox(conexionCombos.getInstance().listaBacteriologos(), 0);
		cbBacteriologo.setFocusCycleRoot(false);
		cbBacteriologo.setBounds(10, 188, 198, 25);
		panelPaciente.add(cbBacteriologo);
		
		txtClaveFirma = new ClrCuadroDeTexto(100, true, "Clave firma");
		txtClaveFirma.setHorizontalAlignment(SwingConstants.LEFT);
		txtClaveFirma.setFocusCycleRoot(false);
		txtClaveFirma.setBounds(218, 188, 149, 25);
		panelPaciente.add(txtClaveFirma);
		
		clrSeparador clrSeparador_ = new clrSeparador();
		clrSeparador_.setBounds(10, 178, 357, 12);
		panelPaciente.add(clrSeparador_);
		
				btnSalir = new btnRedondo("Salir", new Rectangle(237, 172,121,50), 9);
				btnSalir.setBounds(184, 224, 114, 50);
				panelPaciente.add(btnSalir);
				
				txtIdPaciente = new ClrCuadroDeTexto(11, false, "Id Paciente", true);
				txtIdPaciente.setFocusCycleRoot(false);
				txtIdPaciente.setBounds(10, 23, 143, 25);
				txtIdPaciente.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						lb1.setVisible(false);
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						if (lb1==null) {
							lb1= new lbPacienteXid(txtIdPaciente, esta,panelPaciente, esta.principal);
						}
						
					}
				});
				panelPaciente.add(txtIdPaciente);
				
				txtApellidosPaciente = new ClrCuadroDeTexto(13, false, "Apellidos Paciente", true);
				txtApellidosPaciente.setFocusCycleRoot(false);
				txtApellidosPaciente.setBounds(10, 51, 357, 25);
				txtApellidosPaciente.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						lb3.setVisible(false);
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						if (lb3==null) {
							lb3= new lbPacienteXapellido(txtApellidosPaciente, esta,panelPaciente, esta.principal);
						}
						
					}
				});
				panelPaciente.add(txtApellidosPaciente);
				
				 chkDetallar = new clrRadioButton("Detallar por factura");
				chkDetallar.setBounds(10, 83, 289, 23);
				chkDetallar.setVisible(false);
				chkDetallar.setSelected(true);
				panelPaciente.add(chkDetallar);
				
				txtNombrePaciente = new ClrCuadroDeTexto(11, false, "Nombres Paciente", true);
				txtNombrePaciente.setFocusCycleRoot(false);
				txtNombrePaciente.setBounds(163, 23, 204, 25);
				txtNombrePaciente.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						lb2.setVisible(false);
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						if (lb2==null) {
							lb2= new lbPacienteXnombre(txtNombrePaciente, esta,panelPaciente, esta.principal);
						}
						
					}
				});
				panelPaciente.add(txtNombrePaciente);
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



	
	

	
	public void llenarPaciente(Paciente paciente) {
		this.paciente=paciente;
		
		txtIdPaciente.setText(paciente.getId());
		txtNombrePaciente.setText(paciente.getNombres());
		txtApellidosPaciente.setText(paciente.getApellidos());
		
		items=conexionBusqueda.getInstance().facturasDabonoXPaciente(paciente.getId()+"",fchHasta.getDate(),fchDesde.getDate());
		saldoTotal=0;
		int numeroFacturas=items.size();
		for (int i = 0; i < items.size(); i++) {
			DatosAbono DA=items.get(i);
			saldoTotal=saldoTotal+DA.getSaldo();
			
		}
		if (items.size()>0) {
			lblincorrectos.setText("El paciente presenta un saldo de "+ saldoTotal+" en "+numeroFacturas+" facturas");

		}else{
			lblincorrectos.setText("El paciente no presenta saldo a esta fecha ");
		}
		
	}
	public void reportarError(String error){
		lblincorrectos.setText(error);
	}
}
