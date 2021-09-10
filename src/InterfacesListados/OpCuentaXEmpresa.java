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
import otrosImpresos.imprimirReporte;
import Interfaces.Principal;
import Interfaces.lbEmpresaXcod;
import Interfaces.lbEmpresaXnit;
import Interfaces.lbEmpresaXrazon;
import Interfaces.lbExamenXid;
import Interfaces.lbExamenXnombre;
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

public class OpCuentaXEmpresa extends JDialog {

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
	private lbEmpresaXcod lb1;
	private lbEmpresaXnit lb2;
	private lbEmpresaXrazon lb3;
	private JDateChooser fchHasta;
	private JDateChooser fchDesde;
	private ArrayList<itemTarifa> paraEliminar = new ArrayList<itemTarifa>();
	private ArrayList<itemTarifa> paraAgregar = new ArrayList<itemTarifa>();
	private ArrayList<itemTarifa> paraModificar = new ArrayList<itemTarifa>();
	private int[] dias = new int[] { 0, 0, 0, 0, 0, 0, 0 };
	private Paciente paciente;
	private Medico medico;
	private Empresa empresa;
	private Tarifa tarifa;
	private int codSubEmpresa;
	private static OpCuentaXEmpresa esta;
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
	private clrComboBox cbSuGrupo;
	
	private ClrCuadroDeTexto tctCodEmpresa;
	private ClrCuadroDeTexto txtRazonSocial;
	private ClrCuadroDeTexto txtNitEmpresa;
	private clrPanelBordes clrPanelBordes_;
	private clrRadioButton chkDetallar ;
	private ArrayList<DatosAbono> items;
	private int saldoTotal=0;
	private int codSubgrupo=-1;
	
	public OpCuentaXEmpresa(Principal principal, Usuario usuario) {
		super(principal, true);
		this.esta = this;
		this.principal = principal;
		this.usuario = usuario;
		this.esta = this;
		this.accion = accion;

		// this.setDefaultLookAndFeelDecorated(false);

		setBounds(100, 100, 401, 504);
		this.setUndecorated(true);
		this.setLocationRelativeTo(getParent());
		contentPane = new clrPanelBordes(true);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		lblinicio = new clrLabel("Cuenta de cobro por empresa", 2, true);
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
		panelRecepcion.setBounds(10, 78, 377, 70);
		contentPane.add(panelRecepcion);
		
		fchHasta = new JDateChooser();
		fchHasta.setBounds(196, 34, 171, 25);
		fchHasta.setCalendar(hoy);
		panelRecepcion.add(fchHasta);
		fchHasta.setBorder(javax.swing.BorderFactory.createMatteBorder( 1, 1, 1, 1, Colores.clrTextoInactivo));
		
		fchHasta.getDateEditor().addPropertyChangeListener(
			    new PropertyChangeListener() {
			        @Override
			        public void propertyChange(PropertyChangeEvent e) {
			          
			        	if (empresa!=null) {
							llenarEmpresa(empresa);
						}
			        	
			        }
			    });
		
		fchDesde = new JDateChooser();
		fchDesde.setBounds(10, 34, 171, 25);
		fchDesde.setCalendar(hoy);
		panelRecepcion.add(fchDesde);
		fchDesde.setBorder(javax.swing.BorderFactory.createMatteBorder( 1, 1, 1, 1, Colores.clrTextoInactivo));
		
		
		fchDesde.getDateEditor().addPropertyChangeListener(
			    new PropertyChangeListener() {
			        @Override
			        public void propertyChange(PropertyChangeEvent e) {
			          
			        	if (empresa!=null) {
							llenarEmpresa(empresa);
						}
			        	
			        }
			    });
		
		
		
		clrLabel clrLabel__1 = new clrLabel("Hasta el d\u00EDa", 1);
		clrLabel__1.setHorizontalAlignment(SwingConstants.LEFT);
		clrLabel__1.setForeground(new Color(0, 0, 0, 130));
		clrLabel__1.setBounds(196, 13, 92, 14);
		panelRecepcion.add(clrLabel__1);
		
		clrLabel clrLabel_ = new clrLabel("Desde el d\u00EDa", 1);
		clrLabel_.setHorizontalAlignment(SwingConstants.LEFT);
		clrLabel_.setForeground(new Color(0, 0, 0, 130));
		clrLabel_.setBounds(10, 13, 92, 14);
		panelRecepcion.add(clrLabel_);
		
		clrPanelBordes_ = new clrPanelBordes(false);
		clrPanelBordes_.setLayout(null);
		clrPanelBordes_.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Información de Empresa", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
		clrPanelBordes_.setBounds(10, 160, 377, 333);
		contentPane.add(clrPanelBordes_);

		ButtonGroup BG1=new ButtonGroup();
	

		clrSeparador separator = new clrSeparador();
		separator.setBounds(10, 161, 357, 2);
		clrPanelBordes_.add(separator);
		
		ButtonGroup BG=new ButtonGroup();

		chkImprimirDirecto = new clrRadioButton("Imprimir directamente en impresora");
		chkImprimirDirecto.setBounds(10, 170, 289, 23);
		BG.add(chkImprimirDirecto);
		clrPanelBordes_.add(chkImprimirDirecto);

		chkVistaPrevia = new clrRadioButton("Vista previa");
		chkVistaPrevia.setBounds(10, 196, 120, 23);
		chkVistaPrevia.setSelected(true);
		BG.add(chkVistaPrevia);
		clrPanelBordes_.add(chkVistaPrevia);

		btnRedondo btnImprimir = new btnRedondo("Imprimir", new Rectangle(237, 172,121,50), 15);
		btnImprimir.setBounds(30, 272, 144, 50);
		btnImprimir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

			
				Bacteriologo bacteriologo=null;
				
				if (fchHasta.getDate()==null ) {
					lblincorrectos.setText("Falta fecha para la consulta de resultados");
				} else {
					
					if (empresa==null) {
						lblincorrectos.setText("No has escogido ninguna empresa");
					} else {

						

						
						if (items.size()<=0) {
							lblincorrectos.setText("No hay resultados para esta consulta");
						} else {

							
							if (cbBacteriologo.getSelectedIndex()!=0) {
								if (txtClaveFirma.getText().equals(txtClaveFirma.getLabel())) {
								lblincorrectos.setText("Falta clave para la firma del documento");	
								} else {

									String[] codbac=cbBacteriologo.getSelectedItem().toString().split(" • ");
									bacteriologo=conexionBusqueda.getInstance().bacteriologoXid(codbac[0]);
									if (bacteriologo.getClaveFirma()==Integer.parseInt(txtClaveFirma.getText())) {
										int subGrupo=-1;
										if (cbSuGrupo.getSelectedIndex()!=0) {
											String[] grupo=cbSuGrupo.getSelectedItem().toString().split(" • ");
											 subGrupo=Integer.parseInt(grupo[0]);
										}
										
										new CuentaCobroXEmpresa(empresa,subGrupo, items, saldoTotal, chkImprimirDirecto.isSelected(), chkDetallar.isSelected(),bacteriologo);
										dispose();
									
									}else{
										ventanaPregunta VP=new ventanaPregunta(OpCuentaXEmpresa.this.principal, "Clave de firma incorrecta, desea imprimir sin firma?", "Continuar", "Cancelar");
										if (VP.escuchar()) {
											int subGrupo=-1;
											if (cbSuGrupo.getSelectedIndex()!=0) {
												String[] grupo=cbSuGrupo.getSelectedItem().toString().split(" • ");
												 subGrupo=Integer.parseInt(grupo[0]);
											}
											
											new CuentaCobroXEmpresa(empresa,subGrupo, items, saldoTotal, chkImprimirDirecto.isSelected(), chkDetallar.isSelected(),bacteriologo);
											dispose();
			
										
										
										} 
										
									}
								

								}
								
							} else{
								int subGrupo=-1;
								if (cbSuGrupo.getSelectedIndex()!=0) {
									String[] grupo=cbSuGrupo.getSelectedItem().toString().split(" • ");
									 subGrupo=Integer.parseInt(grupo[0]);
								}
								
								new CuentaCobroXEmpresa(empresa,subGrupo, items, saldoTotal, chkImprimirDirecto.isSelected(), chkDetallar.isSelected(),bacteriologo);
								dispose();

							
							}
						
						}
						
					
					}
				}
			
			
			}
		});
		clrPanelBordes_.add(btnImprimir);
		
		cbBacteriologo = new clrComboBox(conexionCombos.getInstance().listaBacteriologos(), 0);
		cbBacteriologo.setFocusCycleRoot(false);
		cbBacteriologo.setBounds(10, 236, 198, 25);
		clrPanelBordes_.add(cbBacteriologo);
		
		txtClaveFirma = new ClrCuadroDeTexto(100, true, "Clave firma");
		txtClaveFirma.setHorizontalAlignment(SwingConstants.LEFT);
		txtClaveFirma.setFocusCycleRoot(false);
		txtClaveFirma.setBounds(218, 236, 149, 25);
		clrPanelBordes_.add(txtClaveFirma);
		
		clrSeparador clrSeparador_ = new clrSeparador();
		clrSeparador_.setBounds(10, 226, 357, 12);
		clrPanelBordes_.add(clrSeparador_);
		
				btnSalir = new btnRedondo("Salir", new Rectangle(237, 172,121,50), 9);
				btnSalir.setBounds(184, 272, 114, 50);
				clrPanelBordes_.add(btnSalir);
				
				tctCodEmpresa = new ClrCuadroDeTexto(11, false, "Codigo Empresa", true);
				tctCodEmpresa.setFocusCycleRoot(false);
				tctCodEmpresa.setBounds(10, 23, 143, 25);
				tctCodEmpresa.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						lb1.setVisible(false);
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						if (lb1==null) {
							lb1= new lbEmpresaXcod(tctCodEmpresa, esta,clrPanelBordes_, esta.principal);
						}
						
					}
				});
				clrPanelBordes_.add(tctCodEmpresa);
				
				txtRazonSocial = new ClrCuadroDeTexto(13, false, "Raz\u00F3n social", true);
				txtRazonSocial.setFocusCycleRoot(false);
				txtRazonSocial.setBounds(10, 63, 357, 25);
				txtRazonSocial.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						lb3.setVisible(false);
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						if (lb3==null) {
							lb3= new lbEmpresaXrazon(txtRazonSocial, esta,clrPanelBordes_, esta.principal);
						}
						
					}
				});
				clrPanelBordes_.add(txtRazonSocial);
				
				 chkDetallar = new clrRadioButton("Detallar por factura");
				chkDetallar.setBounds(10, 131, 289, 23);
				chkDetallar.setVisible(false);
				chkDetallar.setSelected(true);
				clrPanelBordes_.add(chkDetallar);
				
				txtNitEmpresa = new ClrCuadroDeTexto(11, false, "Nit de empresa", true);
				txtNitEmpresa.setFocusCycleRoot(false);
				txtNitEmpresa.setBounds(163, 23, 204, 25);
				txtNitEmpresa.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						lb2.setVisible(false);
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						if (lb2==null) {
							lb2= new lbEmpresaXnit(txtNitEmpresa, esta,clrPanelBordes_, esta.principal);
						}
						
					}
				});
				clrPanelBordes_.add(txtNitEmpresa);
				
				cbSuGrupo = new clrComboBox(new String[] {"Sub grupo de empresa"}, 0);
				cbSuGrupo.setFocusCycleRoot(false);
				cbSuGrupo.setBounds(10, 99, 357, 25);
				
				clrPanelBordes_.add(cbSuGrupo);
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



	
	

	
	public void llenarEmpresa(Empresa empresa) {
		this.empresa=empresa;
		cbSuGrupo.llenar(conexionCombos.getInstance().listaSubGrupoEmpresa(empresa.getIdEmpresa()));
		tctCodEmpresa.setText(empresa.getIdEmpresa()+"");
		txtNitEmpresa.setText(empresa.getDocEmpresa());
		txtRazonSocial.setText(empresa.getRazonSocial());
		
		cbSuGrupo.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if (cbSuGrupo.getSelectedIndex()>0) {
					String[] codigo=cbSuGrupo.getSelectedItem().toString().split(" • ");
					codSubgrupo=Integer.parseInt(codigo[0]);
					calcularTota();
				}else{
					codSubgrupo=-1;
					calcularTota();
				}
				
			}
		});
		
		
		items=conexionBusqueda.getInstance().facturasDabonoXEmpresa(empresa.getIdEmpresa()+"",fchHasta.getDate(),fchDesde.getDate());
		saldoTotal=0;
		int numeroFacturas=items.size();
		for (int i = 0; i < items.size(); i++) {
			DatosAbono DA=items.get(i);
			saldoTotal=saldoTotal+DA.getSaldo();
			
		}
		if (items.size()>0) {
			lblincorrectos.setText("La empresa presenta un saldo de "+ saldoTotal+" en "+numeroFacturas+" facturas");

		}else{
			lblincorrectos.setText("La empresa no presenta saldo a esta fecha ");
		}
		
	}
	public void reportarError(String error){
		lblincorrectos.setText(error);
	}
	
	public void calcularTota(){
		if (codSubgrupo!=-1) {

			saldoTotal=0;
			int numeroFacturas=0;
			for (int i = 0; i < items.size(); i++) {
						DatosAbono DA=items.get(i);
						if (conexionBusqueda.getInstance().RCXid(DA.getCodRecepcion()).getRecepcion().getCodSubEmpresa()==codSubgrupo) {
								saldoTotal=saldoTotal+DA.getSaldo();
								numeroFacturas++;
				}
						lblincorrectos.setText("La empresa presenta un saldo de "+ saldoTotal+" en "+numeroFacturas+" facturas");

			}
		
		} else {


			saldoTotal=0;
			int numeroFacturas=0;
			for (int i = 0; i < items.size(); i++) {
						DatosAbono DA=items.get(i);
								saldoTotal=saldoTotal+DA.getSaldo();

						lblincorrectos.setText("La empresa presenta un saldo de "+ saldoTotal+" en "+numeroFacturas+" facturas");

			}
		
		
		}
		
	}
}
