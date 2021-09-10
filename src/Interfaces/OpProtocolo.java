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
import interfaces_Modificadas.clrRenderTablaExamenesXReportar;
import interfaces_Modificadas.clrRenderTablaProtocolos;
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

import javax.swing.JDialog;
import javax.swing.SwingConstants;

import ago.beans.CirclePanel;
import auxiliares.Bacteriologo;
import auxiliares.GruposEmpresas;
import auxiliares.Medico;
import auxiliares.OTD;
import auxiliares.Paciente;
import auxiliares.Secciones;
import auxiliares.TipoDato;
import auxiliares.Usuario;
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
import javax.swing.JSeparator;

public class OpProtocolo extends JDialog {
	private clrPanelBordes contentPane;
	private clrLabel lblincorrectos;
	private Principal principal;
	private Usuario usuario;
	private String accion;
	private clrLabel lblinicio;
	private circuloUsuario btnRedondo_;
	private OpProtocolo esta;
	private int ordenauxi = 1;

	private String codigoAntiguo;
	private int idprotocolo=-1;
	private ClrCuadroDeTexto txtCodigo;
	private btnRedondo btnEliminar;
	private btnRedondo btnAgregar;
	private btnRedondo btnCancelar;
	private btnRedondo btnGuardarYSalir;
	private lbProtocoloXcod lb1;
	private lbProtocoloXnombre lb2;
	private JScrollPane scrollPane;
	private JTable table;
	private MiTableModelNoEditable TM;
	private ArrayList<itemProtocolo> paraEliminar = new ArrayList<itemProtocolo>();
	private ArrayList<itemProtocolo> paraAgregar = new ArrayList<itemProtocolo>();
	private ArrayList<itemProtocolo> paraModificar = new ArrayList<itemProtocolo>();
	private JPanel panel;

	private ClrCuadroDeTexto txtNombreProtocolo;
	private clrComboBox cbSeccion;
	private clrComboBox cbPlanillas;
	private ClrCuadroDeTexto txtOrden;
	private ClrCuadroDeTexto txtNombreItem;
	private clrComboBox cbTipoDato;
	private ClrCuadroDeTexto txtUnidad;
	private ClrCuadroDeTexto txtAbreviatura;
	private clrComboBox cbValorNormal;
	private ClrCuadroDeTexto txtDesde;
	private ClrCuadroDeTexto txtHasta;
	private clrComboBox cbGeneros;
	private btnRedondo btnAgregarTitulo;
	private ClrCuadroDeTexto txtTitulo;
	private clrSeparador separator;
	private clrLabel lblIncorrectosItem;
	private clrSeparador separator_1;
	private btnRedondo btnAgregarSeparador;
	private ClrCuadroDeTexto txtordenItem;
	private btnRedondo btnInterpreta;
	private clrPanelBordes contentPane1;
	private JDialog interpreta;
	private clrtextpane txtinterpreta;
	private btnRedondo btnGuardarInterpreta;
	

	public OpProtocolo(Principal principal, Usuario usuario, String accion) {
		super(principal, true);
		this.principal = principal;
		this.usuario = usuario;
		this.esta = this;
		this.accion = accion;

		// this.setDefaultLookAndFeelDecorated(false);

		setBounds(100, 100, 1097, 703);
		this.setUndecorated(true);
		this.setLocationRelativeTo(getParent());
		contentPane = new clrPanelBordes(true);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		/////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////
			
		if (accion.equals("Agregar")) {

			lblinicio = new clrLabel("Agregar protocolo de resultados", 2,true);
			lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
			lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblinicio.setBounds(10, 11, 1077, 28);
			contentPane.add(lblinicio);

			lblincorrectos = new clrLabel("", 1);
			lblincorrectos.setForeground(Colores.clrAlertaCamarada);
			lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
			lblincorrectos.setAlignmentX(0.5f);
			lblincorrectos.setBounds(10, 38, 1077, 28);
			contentPane.add(lblincorrectos);



			txtCodigo = new ClrCuadroDeTexto(6,false,"Código",1);
			txtCodigo.setFocusCycleRoot(false);
			txtCodigo.setBounds(44, 77, 114, 20);
			contentPane.add(txtCodigo);

			btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50),2);
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnCancelar.setBounds(544, 613, 136, 50);
			contentPane.add(btnCancelar);

			btnGuardarYSalir = new btnRedondo("Agregar  y salir", new Rectangle(48, 172,121,50),1);
			btnGuardarYSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					insertarProtocolo();
				}
			});
			btnGuardarYSalir.setSelected(true);
			btnGuardarYSalir.setBounds(355, 613, 169, 50);
			contentPane.add(btnGuardarYSalir);

			TM = new MiTableModelNoEditable(new Object[][] {}, new String[] { "Nombre", "Tipo de dato", "Unidad", "Abrev.", "Valor Normal", "Desde", "Hasta", "Genero", "Orden" });
			scrollPane = new clrScrollBar();
			scrollPane.setBounds(322, 24, 734, 374);

			table = new JTable();
			table.setModel(TM);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(table);
		

			table.getColumnModel().getColumn(0).setPreferredWidth(500);
			table.getColumnModel().getColumn(1).setPreferredWidth(110);
			table.getColumnModel().getColumn(2).setPreferredWidth(80);
			table.getColumnModel().getColumn(3).setPreferredWidth(80);
			table.getColumnModel().getColumn(4).setPreferredWidth(120);
			table.getColumnModel().getColumn(5).setPreferredWidth(70);
			table.getColumnModel().getColumn(6).setPreferredWidth(70);
			table.getColumnModel().getColumn(7).setPreferredWidth(70);
			table.getColumnModel().getColumn(8).setPreferredWidth(70);
			table.setDefaultRenderer(Object.class, new clrRenderTablaProtocolos());

			txtNombreProtocolo = new ClrCuadroDeTexto(200,false,"Nombre del protocolo",1);
			txtNombreProtocolo.setFocusCycleRoot(false);
			txtNombreProtocolo.setBounds(168, 77, 273, 20);
			contentPane.add(txtNombreProtocolo);

			cbSeccion = new clrComboBox(conexionCombos.getInstance().listaSecciones(),1);
			cbSeccion.setFocusCycleRoot(false);
			cbSeccion.setBounds(451, 77, 254, 20);
			contentPane.add(cbSeccion);

			cbPlanillas = new clrComboBox(conexionCombos.getInstance().listaPlanillas(),1);
			cbPlanillas.setFocusCycleRoot(false);
			cbPlanillas.setBounds(715, 77, 254, 20);
			contentPane.add(cbPlanillas);

			txtOrden = new ClrCuadroDeTexto(3,true,"Orden");
			txtOrden.setFocusCycleRoot(false);
			txtOrden.setBounds(979, 77, 75, 20);
			contentPane.add(txtOrden);

			panel = new clrPanelBordes(false);
			panel.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) Colores.clrPrincipal), "Items para este protocolo", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
			panel.setBounds(10, 112, 1077, 499);
			panel.setLayout(null);
			contentPane.add(panel);
			panel.add(scrollPane);

			txtNombreItem = new ClrCuadroDeTexto(100,false,"Nombre de item");
			txtNombreItem.setFocusCycleRoot(false);
			txtNombreItem.setBounds(25, 39, 273, 20);
			panel.add(txtNombreItem);

			cbTipoDato = new clrComboBox(conexionCombos.getInstance().listaTD(),0);
			cbTipoDato.setFocusCycleRoot(false);
			cbTipoDato.setBounds(25, 70, 273, 20);
			cbTipoDato.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent arg0) {
					
					validarTipoDato(cbTipoDato.getSelectedItem().toString());
				}
			});
			panel.add(cbTipoDato);

			txtUnidad = new ClrCuadroDeTexto(45,false,"Unidad de medida");
			txtUnidad.setFocusCycleRoot(false);
			txtUnidad.setBounds(25, 101, 129, 20);
			panel.add(txtUnidad);

			txtAbreviatura = new ClrCuadroDeTexto(5,false,"Abreviatura");
			txtAbreviatura.setFocusCycleRoot(false);
			txtAbreviatura.setBounds(169, 101, 129, 20);
			panel.add(txtAbreviatura);

			cbValorNormal = new clrComboBox(0);
			cbValorNormal.setFocusCycleRoot(false);
			cbValorNormal.setVisible(false);
			cbValorNormal.setBounds(25, 163, 273, 20);
			panel.add(cbValorNormal);

			txtDesde = new ClrCuadroDeTexto(11,true,"Desde");
			txtDesde.setFocusCycleRoot(false);
			txtDesde.setVisible(false);
			txtDesde.setBounds(25, 194, 129, 20);
			panel.add(txtDesde);

			txtHasta = new ClrCuadroDeTexto(11,true,"Hasta");
			txtHasta.setFocusCycleRoot(false);
			txtHasta.setVisible(false);
			txtHasta.setBounds(169, 194, 129, 20);
			panel.add(txtHasta);

			cbGeneros = new clrComboBox(conexionCombos.getInstance().listaGeneros2(),0);
			cbGeneros.setFocusCycleRoot(false);
			cbGeneros.setBounds(25, 132, 273, 20);
			panel.add(cbGeneros);

			btnAgregar = new btnRedondo("Agregar", new Rectangle(48, 172,121,50),4);
			btnAgregar.setBounds(772, 409, 138, 50);
			panel.add(btnAgregar);
			btnAgregar.setSelected(true);

			btnEliminar = new btnRedondo("Eliminar", new Rectangle(237, 172,121,50),5);
			btnEliminar.setBounds(920, 409, 136, 50);
			panel.add(btnEliminar);

			lblIncorrectosItem = new clrLabel("", 1);
			lblIncorrectosItem.setHorizontalAlignment(SwingConstants.CENTER);
			lblIncorrectosItem.setForeground(Color.RED);
			lblIncorrectosItem.setAlignmentX(0.5f);
			lblIncorrectosItem.setBounds(10, 454, 1057, 34);
			panel.add(lblIncorrectosItem);

			btnAgregarTitulo = new btnRedondo("Agregar título", new Rectangle(48, 172,121,50),4);
			btnAgregarTitulo.setSelected(true);
			btnAgregarTitulo.setBounds(25, 282, 273, 50);
			btnAgregarTitulo.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					agregarTitulo();
				}
			});
			panel.add(btnAgregarTitulo);

			txtTitulo = new ClrCuadroDeTexto(100,false,"Título");
			txtTitulo.setFocusCycleRoot(false);
			txtTitulo.setBounds(25, 254, 273, 20);
			panel.add(txtTitulo);

			separator = new clrSeparador();
			separator.setBounds(25, 241, 273, 2);
			panel.add(separator);

			separator_1 = new clrSeparador();
			separator_1.setBounds(25, 340, 273, 2);
			panel.add(separator_1);

			btnAgregarSeparador = new btnRedondo("Agregar separador", new Rectangle(48, 172,121,50),4);
			btnAgregarSeparador.setSelected(true);
			btnAgregarSeparador.setBounds(25, 353, 273, 50);
			btnAgregarSeparador.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					agregarSeparador();
				}
			});
			panel.add(btnAgregarSeparador);

			txtordenItem = new ClrCuadroDeTexto(3,true,"Orden");
			txtordenItem.setFocusCycleRoot(false);
			txtordenItem.setBounds(717, 423, 45, 20);
			panel.add(txtordenItem);
			
			btnInterpreta = new btnRedondo("Agregar interpretac\u00F3n", new Rectangle(48, 172, 121, 50), 16);
			btnInterpreta.setSelected(true);
			btnInterpreta.setBounds(332, 409, 219, 50);
			btnInterpreta.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					System.err.println("selecciona "+table.getSelectedRow());
					
					if (table.getSelectedRow()==-1) {
						lblincorrectos.setText("No has seleccionado ningún item para agregar interpretación");
					} else {
						
						interpreta=new JDialog(esta);
						interpreta.setBounds(100, 100, 500,270);
						interpreta.setUndecorated(true);
						interpreta.setLocationRelativeTo(getParent());
						contentPane1 = new clrPanelBordes(true);
						contentPane1.setLayout(null);
						interpreta.setContentPane(contentPane1);
						
						
						clrScrollBar scrollPane1 = new clrScrollBar();
						scrollPane1.setBounds(20, 70, 460, 90);
						contentPane1.add(scrollPane1);
						String interpretacion=paraAgregar.get(table.getSelectedRow()).getInterpreta();
						
						txtinterpreta = new clrtextpane(500,false,"Interpretación",0);
						if (!interpretacion.equals("")) {
							txtinterpreta.setText(paraAgregar.get(table.getSelectedRow()).getInterpreta());
						}
						scrollPane1.setViewportView(txtinterpreta);
						
						btnGuardarInterpreta = new btnRedondo("Guardar  interpretac\u00F3n", new Rectangle(48, 172, 121, 50), 16);
						btnGuardarInterpreta.setSelected(true);
						btnGuardarInterpreta.setBounds(140, 180, 219, 50);
						contentPane1.add(btnGuardarInterpreta);
						btnGuardarInterpreta.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								System.err.println("re antes - "+paraAgregar.get(table.getSelectedRow()).getInterpreta()+"-");
								if (!txtinterpreta.getText().equals(txtinterpreta.getLabel())) {
									itemProtocolo itemPro=paraAgregar.get(table.getSelectedRow());
									itemPro.setInterpreta(txtinterpreta.getText());
									System.err.println("antes"+paraAgregar.get(table.getSelectedRow()).getInterpreta());
									paraAgregar.set(table.getSelectedRow(), itemPro);
									System.err.println("depsues"+paraAgregar.get(table.getSelectedRow()).getInterpreta());
									interpreta.dispose();
								} else {
									itemProtocolo itemPro=paraAgregar.get(table.getSelectedRow());
									itemPro.setInterpreta("");
									dispose();
								}
								
							}
						});
						
						interpreta.setVisible(true);			
						System.err.println("okokokokokoko");
						
					
					}
					
				}
			});
			panel.add(btnInterpreta);
			

			btnEliminar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					quitarDtabla();

				}
			});
			btnAgregar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					agregarAtabla();
				}
			});

			SwingUtilities.invokeLater( new Runnable() 
	        { 
	        public void run() 
	            { 
	            btnGuardarYSalir.requestFocusInWindow(); 
	        } 
	    }); 
			
			this.setVisible(true);

		} else {
			if (accion.equals("Modificar")) {
				lblinicio = new clrLabel("Modificar  protocolo de resultados", 2,true);
				lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
				lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
				lblinicio.setBounds(10, 11, 1077, 28);
				contentPane.add(lblinicio);

				lblincorrectos = new clrLabel("", 1);
				lblincorrectos.setForeground(Colores.clrAlertaCamarada);
				lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
				lblincorrectos.setAlignmentX(0.5f);
				lblincorrectos.setBounds(10, 38, 1077, 28);
				contentPane.add(lblincorrectos);

		
				txtCodigo = new ClrCuadroDeTexto(6,false,"Código",true,1);
				txtCodigo.setFocusCycleRoot(false);
				txtCodigo.setBounds(44, 77, 114, 20);
				txtCodigo.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						
						lb1.setVisible(false);
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						
						if (lb1==null) {
							lb1=new lbProtocoloXcod(txtCodigo, esta, esta.principal);
						}
					}
				});
				contentPane.add(txtCodigo);

				btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50),2);
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnCancelar.setBounds(544, 613, 136, 50);
				contentPane.add(btnCancelar);

				btnGuardarYSalir = new btnRedondo("Modificar  y salir", new Rectangle(48, 172,121,50),3);
				btnGuardarYSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						modificarProtocolo();
					}
				});
				btnGuardarYSalir.setSelected(true);
				btnGuardarYSalir.setBounds(355, 613, 169, 50);
				contentPane.add(btnGuardarYSalir);

				TM = new MiTableModelNoEditable(new Object[][] {}, new String[] { "Nombre", "Tipo de dato", "Unidad", "Abrev.", "Valor Normal", "Desde", "Hasta", "Genero", "Orden" });
				scrollPane = new clrScrollBar();
				scrollPane.setBounds(322, 24, 734, 374);

				table = new JTable();
				table.setModel(TM);
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(table);

				table.getColumnModel().getColumn(0).setPreferredWidth(500);
				table.getColumnModel().getColumn(1).setPreferredWidth(110);
				table.getColumnModel().getColumn(2).setPreferredWidth(80);
				table.getColumnModel().getColumn(3).setPreferredWidth(80);
				table.getColumnModel().getColumn(4).setPreferredWidth(120);
				table.getColumnModel().getColumn(5).setPreferredWidth(70);
				table.getColumnModel().getColumn(6).setPreferredWidth(70);
				table.getColumnModel().getColumn(7).setPreferredWidth(70);
				table.getColumnModel().getColumn(8).setPreferredWidth(70);
				table.setDefaultRenderer(Object.class, new clrRenderTablaProtocolos());

				txtNombreProtocolo = new ClrCuadroDeTexto(200,false,"Nombre del protocolo",true,1);
				txtNombreProtocolo.setFocusCycleRoot(false);
				txtNombreProtocolo.setBounds(168, 77, 273, 20);
				txtNombreProtocolo.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						lb2.setVisible(false);
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						if (lb2==null) {
							lb2=new lbProtocoloXnombre(txtNombreProtocolo, esta, esta.principal);
						}
					}
				});
				contentPane.add(txtNombreProtocolo);

				cbSeccion = new clrComboBox(conexionCombos.getInstance().listaSecciones(),1);
				cbSeccion.setFocusCycleRoot(false);
				cbSeccion.setBounds(451, 77, 254, 20);
				contentPane.add(cbSeccion);

				cbPlanillas = new clrComboBox(conexionCombos.getInstance().listaPlanillas(),1);
				cbPlanillas.setFocusCycleRoot(false);
				cbPlanillas.setBounds(715, 77, 254, 20);
				contentPane.add(cbPlanillas);

				txtOrden = new ClrCuadroDeTexto(3,true,"Orden");
				txtOrden.setFocusCycleRoot(false);
				txtOrden.setBounds(979, 77, 75, 20);
				contentPane.add(txtOrden);
				
				

				panel = new clrPanelBordes(false);
				panel.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) Colores.clrPrincipal), "Items para este protocolo", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
				panel.setBounds(10, 112, 1077, 499);
				panel.setLayout(null);
				contentPane.add(panel);
				panel.add(scrollPane);

				txtNombreItem = new ClrCuadroDeTexto(100,false,"Nombre de item");
				txtNombreItem.setFocusCycleRoot(false);
				txtNombreItem.setBounds(25, 39, 273, 20);
				panel.add(txtNombreItem);

				cbTipoDato = new clrComboBox(conexionCombos.getInstance().listaTD(),0);
				cbTipoDato.setFocusCycleRoot(false);
				cbTipoDato.setBounds(25, 70, 273, 20);
				cbTipoDato.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent arg0) {
						
						validarTipoDato(cbTipoDato.getSelectedItem().toString());
					}
				});
				panel.add(cbTipoDato);

				txtUnidad = new ClrCuadroDeTexto(45,false,"Unidad de medida");
				txtUnidad.setFocusCycleRoot(false);
				txtUnidad.setBounds(25, 101, 129, 20);
				panel.add(txtUnidad);

				txtAbreviatura = new ClrCuadroDeTexto(5,false,"Abreviatura");
				txtAbreviatura.setFocusCycleRoot(false);
				txtAbreviatura.setBounds(169, 101, 129, 20);
				panel.add(txtAbreviatura);

				cbValorNormal = new clrComboBox(0);
				cbValorNormal.setFocusCycleRoot(false);
				cbValorNormal.setVisible(false);
				cbValorNormal.setBounds(25, 163, 273, 20);
				panel.add(cbValorNormal);

				txtDesde = new ClrCuadroDeTexto(11,true,"Desde");
				txtDesde.setFocusCycleRoot(false);
				txtDesde.setVisible(false);
				txtDesde.setBounds(25, 194, 129, 20);
				panel.add(txtDesde);

				txtHasta = new ClrCuadroDeTexto(11,true,"Hasta");
				txtHasta.setFocusCycleRoot(false);
				txtHasta.setVisible(false);
				txtHasta.setBounds(169, 194, 129, 20);
				panel.add(txtHasta);

				cbGeneros = new clrComboBox(conexionCombos.getInstance().listaGeneros2(),0);
				cbGeneros.setFocusCycleRoot(false);
				cbGeneros.setBounds(25, 132, 273, 20);
				panel.add(cbGeneros);

				btnAgregar = new btnRedondo("Agregar", new Rectangle(48, 172,121,50),4);
				btnAgregar.setBounds(772, 409, 138, 50);
				panel.add(btnAgregar);
				btnAgregar.setSelected(true);

				btnEliminar = new btnRedondo("Eliminar", new Rectangle(237, 172,121,50),5);
				btnEliminar.setBounds(920, 409, 136, 50);
				panel.add(btnEliminar);

				lblIncorrectosItem = new clrLabel("", 1);
				lblIncorrectosItem.setHorizontalAlignment(SwingConstants.CENTER);
				lblIncorrectosItem.setForeground(Color.RED);
				lblIncorrectosItem.setAlignmentX(0.5f);
				lblIncorrectosItem.setBounds(10, 454, 1057, 34);
				panel.add(lblIncorrectosItem);

				btnAgregarTitulo = new btnRedondo("Agregar título", new Rectangle(48, 172,121,50),4);
				btnAgregarTitulo.setSelected(true);
				btnAgregarTitulo.setBounds(25, 282, 273, 50);
				btnAgregarTitulo.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						agregarTitulo();
					}
				});
				panel.add(btnAgregarTitulo);

				txtTitulo = new ClrCuadroDeTexto(100,false,"Título");
				txtTitulo.setFocusCycleRoot(false);
				txtTitulo.setBounds(25, 254, 273, 20);
				panel.add(txtTitulo);

				separator = new clrSeparador();
				separator.setBounds(25, 241, 273, 2);
				panel.add(separator);

				separator_1 = new clrSeparador();
				separator_1.setBounds(25, 340, 273, 2);
				panel.add(separator_1);

				btnAgregarSeparador = new btnRedondo("Agregar separador", new Rectangle(48, 172,121,50),4);
				btnAgregarSeparador.setSelected(true);
				btnAgregarSeparador.setBounds(25, 353, 273, 50);
				btnAgregarSeparador.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						
						agregarSeparador();
					}
				});
				panel.add(btnAgregarSeparador);

				txtordenItem = new ClrCuadroDeTexto(3,true,"Orden");
				txtordenItem.setFocusCycleRoot(false);
				txtordenItem.setBounds(717, 423, 45, 20);
				panel.add(txtordenItem);

				
				
				btnInterpreta = new btnRedondo("Agregar interpretac\u00F3n", new Rectangle(48, 172, 121, 50), 16);
				btnInterpreta.setSelected(true);
				btnInterpreta.setBounds(332, 409, 219, 50);
				btnInterpreta.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						System.err.println("selecciona "+table.getSelectedRow());
						
						if (table.getSelectedRow()==-1) {
							lblincorrectos.setText("No has seleccionado ningún item para agregar interpretación");
						} else {
							
							interpreta=new JDialog(esta);
							interpreta.setBounds(100, 100, 500,270);
							interpreta.setUndecorated(true);
							interpreta.setLocationRelativeTo(getParent());
							contentPane1 = new clrPanelBordes(true);
							contentPane1.setLayout(null);
							interpreta.setContentPane(contentPane1);
							
							
							clrScrollBar scrollPane1 = new clrScrollBar();
							scrollPane1.setBounds(20, 70, 460, 90);
							contentPane1.add(scrollPane1);
							String interpretacion=paraAgregar.get(table.getSelectedRow()).getInterpreta();
							
							txtinterpreta = new clrtextpane(500,false,"Interpretación",0);
							if (!interpretacion.equals("")) {
								txtinterpreta.setText(paraAgregar.get(table.getSelectedRow()).getInterpreta());
							}
							scrollPane1.setViewportView(txtinterpreta);
							
							btnGuardarInterpreta = new btnRedondo("Guardar  interpretac\u00F3n", new Rectangle(48, 172, 121, 50), 16);
							btnGuardarInterpreta.setSelected(true);
							btnGuardarInterpreta.setBounds(140, 180, 219, 50);
							contentPane1.add(btnGuardarInterpreta);
							btnGuardarInterpreta.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									System.err.println("re antes - "+paraAgregar.get(table.getSelectedRow()).getInterpreta()+"-");
									if (!txtinterpreta.getText().equals(txtinterpreta.getLabel())) {
										itemProtocolo itemPro=paraAgregar.get(table.getSelectedRow());
										itemPro.setInterpreta(txtinterpreta.getText());
										System.err.println("antes"+paraAgregar.get(table.getSelectedRow()).getInterpreta());
										paraAgregar.set(table.getSelectedRow(), itemPro);
										System.err.println("depsues"+paraAgregar.get(table.getSelectedRow()).getInterpreta());
										interpreta.dispose();
									} else {
										itemProtocolo itemPro=paraAgregar.get(table.getSelectedRow());
										itemPro.setInterpreta("");
										dispose();
									}
									
								}
							});
							
							interpreta.setVisible(true);			
							System.err.println("okokokokokoko");
							
						
						}
						
					}
				});
				panel.add(btnInterpreta);
				
				
				btnEliminar.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						quitarDtabla();

					}
				});
				btnAgregar.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						
						agregarAtabla();
					}
				});

				SwingUtilities.invokeLater( new Runnable() 
		        { 
		        public void run() 
		            { 
		            btnGuardarYSalir.requestFocusInWindow(); 
		        } 
		    }); 
				
				this.setVisible(true);


			} else {
				lblinicio = new clrLabel("Agregar protocolo de resultados", 2,true);
				lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
				lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
				lblinicio.setBounds(10, 11, 1077, 28);
				contentPane.add(lblinicio);

				lblincorrectos = new clrLabel("", 1);
				lblincorrectos.setForeground(Colores.clrAlertaCamarada);
				lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
				lblincorrectos.setAlignmentX(0.5f);
				lblincorrectos.setBounds(10, 38, 1077, 28);
				contentPane.add(lblincorrectos);

			

				txtCodigo = new ClrCuadroDeTexto(6,false,"Código",true,1);
				txtCodigo.setFocusCycleRoot(false);
				txtCodigo.setBounds(44, 77, 114, 20);
				txtCodigo.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						
						lb1.setVisible(false);
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						
						if (lb1==null) {
							lb1=new lbProtocoloXcod(txtCodigo, esta, esta.principal);
						}
					}
				});
				contentPane.add(txtCodigo);

				btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50),2);
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnCancelar.setBounds(544, 613, 136, 50);
				contentPane.add(btnCancelar);

				btnGuardarYSalir = new btnRedondo("Eliminar  y salir", new Rectangle(48, 172,121,50),5);
				btnGuardarYSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						EliminarProtocolo();
					}
				});
				btnGuardarYSalir.setSelected(true);
				btnGuardarYSalir.setBounds(355, 613, 169, 50);
				contentPane.add(btnGuardarYSalir);

				TM = new MiTableModelNoEditable(new Object[][] {}, new String[] { "Nombre", "Tipo de dato", "Unidad", "Abrev.", "Valor Normal", "Desde", "Hasta", "Genero", "Orden" });
				scrollPane = new clrScrollBar();
				scrollPane.setBounds(322, 24, 734, 374);

				table = new JTable();
				table.setModel(TM);
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(table);

				table.getColumnModel().getColumn(0).setPreferredWidth(500);
				table.getColumnModel().getColumn(1).setPreferredWidth(110);
				table.getColumnModel().getColumn(2).setPreferredWidth(80);
				table.getColumnModel().getColumn(3).setPreferredWidth(80);
				table.getColumnModel().getColumn(4).setPreferredWidth(120);
				table.getColumnModel().getColumn(5).setPreferredWidth(70);
				table.getColumnModel().getColumn(6).setPreferredWidth(70);
				table.getColumnModel().getColumn(7).setPreferredWidth(70);
				table.getColumnModel().getColumn(8).setPreferredWidth(70);
				table.setDefaultRenderer(Object.class, new clrRenderTablaProtocolos());

				txtNombreProtocolo = new ClrCuadroDeTexto(200,false,"Nombre del protocolo",true,1);
				txtNombreProtocolo.setFocusCycleRoot(false);
				txtNombreProtocolo.setBounds(168, 77, 273, 20);
				txtNombreProtocolo.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						lb2.setVisible(false);
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						if (lb2==null) {
							lb2=new lbProtocoloXnombre(txtNombreProtocolo, esta, esta.principal);
						}
					}
				});
				contentPane.add(txtNombreProtocolo);

				cbSeccion = new clrComboBox(conexionCombos.getInstance().listaSecciones(),1);
				cbSeccion.setFocusCycleRoot(false);
				cbSeccion.setBounds(451, 77, 254, 20);
				contentPane.add(cbSeccion);

				cbPlanillas = new clrComboBox(conexionCombos.getInstance().listaPlanillas(),1);
				cbPlanillas.setFocusCycleRoot(false);
				cbPlanillas.setBounds(715, 77, 254, 20);
				contentPane.add(cbPlanillas);

				txtOrden = new ClrCuadroDeTexto(3,true,"Orden");
				txtOrden.setFocusCycleRoot(false);
				txtOrden.setBounds(979, 77, 75, 20);
				contentPane.add(txtOrden);

				panel = new clrPanelBordes(false);
				panel.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) Colores.clrPrincipal), "Items para este protocolo", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
				panel.setBounds(10, 112, 1077, 499);
				panel.setLayout(null);
				contentPane.add(panel);
				panel.add(scrollPane);

				txtNombreItem = new ClrCuadroDeTexto(100,false,"Nombre de item");
				txtNombreItem.setFocusCycleRoot(false);
				txtNombreItem.setBounds(25, 39, 273, 20);
				panel.add(txtNombreItem);

				cbTipoDato = new clrComboBox(conexionCombos.getInstance().listaTD(),0);
				cbTipoDato.setFocusCycleRoot(false);
				cbTipoDato.setBounds(25, 70, 273, 20);
				cbTipoDato.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent arg0) {
						
						validarTipoDato(cbTipoDato.getSelectedItem().toString());
					}
				});
				panel.add(cbTipoDato);

				txtUnidad = new ClrCuadroDeTexto(45,false,"Unidad de medida");
				txtUnidad.setFocusCycleRoot(false);
				txtUnidad.setBounds(25, 101, 129, 20);
				panel.add(txtUnidad);

				txtAbreviatura = new ClrCuadroDeTexto(5,false,"Abreviatura");
				txtAbreviatura.setFocusCycleRoot(false);
				txtAbreviatura.setBounds(169, 101, 129, 20);
				panel.add(txtAbreviatura);

				cbValorNormal = new clrComboBox(0);
				cbValorNormal.setFocusCycleRoot(false);
				cbValorNormal.setVisible(false);
				cbValorNormal.setBounds(25, 163, 273, 20);
				panel.add(cbValorNormal);

				txtDesde = new ClrCuadroDeTexto(11,true,"Desde");
				txtDesde.setFocusCycleRoot(false);
				txtDesde.setVisible(false);
				txtDesde.setBounds(25, 194, 129, 20);
				panel.add(txtDesde);

				txtHasta = new ClrCuadroDeTexto(11,true,"Hasta");
				txtHasta.setFocusCycleRoot(false);
				txtHasta.setVisible(false);
				txtHasta.setBounds(169, 194, 129, 20);
				panel.add(txtHasta);

				cbGeneros = new clrComboBox(conexionCombos.getInstance().listaGeneros2(),0);
				cbGeneros.setFocusCycleRoot(false);
				cbGeneros.setBounds(25, 132, 273, 20);
				panel.add(cbGeneros);

				btnAgregar = new btnRedondo("Agregar", new Rectangle(48, 172,121,50),4);
				btnAgregar.setBounds(772, 409, 138, 50);
				panel.add(btnAgregar);
				btnAgregar.setSelected(true);

				btnEliminar = new btnRedondo("Eliminar", new Rectangle(237, 172,121,50),5);
				btnEliminar.setBounds(920, 409, 136, 50);
				panel.add(btnEliminar);

				lblIncorrectosItem = new clrLabel("", 1);
				lblIncorrectosItem.setHorizontalAlignment(SwingConstants.CENTER);
				lblIncorrectosItem.setForeground(Color.RED);
				lblIncorrectosItem.setAlignmentX(0.5f);
				lblIncorrectosItem.setBounds(10, 454, 1057, 34);
				panel.add(lblIncorrectosItem);

				btnAgregarTitulo = new btnRedondo("Agregar título", new Rectangle(48, 172,121,50),4);
				btnAgregarTitulo.setSelected(true);
				btnAgregarTitulo.setBounds(25, 282, 273, 50);
				btnAgregarTitulo.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						agregarTitulo();
					}
				});
				panel.add(btnAgregarTitulo);

				txtTitulo = new ClrCuadroDeTexto(100,false,"Título");
				txtTitulo.setFocusCycleRoot(false);
				txtTitulo.setBounds(25, 254, 273, 20);
				panel.add(txtTitulo);

				separator = new clrSeparador();
				separator.setBounds(25, 241, 273, 2);
				panel.add(separator);

				separator_1 = new clrSeparador();
				separator_1.setBounds(25, 340, 273, 2);
				panel.add(separator_1);

				btnAgregarSeparador = new btnRedondo("Agregar separador", new Rectangle(48, 172,121,50),4);
				btnAgregarSeparador.setSelected(true);
				btnAgregarSeparador.setBounds(25, 353, 273, 50);
				btnAgregarSeparador.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						
						agregarSeparador();
					}
				});
				panel.add(btnAgregarSeparador);

				txtordenItem = new ClrCuadroDeTexto(3,false,"Orden");
				txtordenItem.setFocusCycleRoot(false);
				txtordenItem.setBounds(717, 423, 45, 20);
				panel.add(txtordenItem);
				
			

				btnEliminar.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						quitarDtabla();

					}
				});
				btnAgregar.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						
						agregarAtabla();
					}
				});

				SwingUtilities.invokeLater( new Runnable() 
		        { 
		        public void run() 
		            { 
		            btnGuardarYSalir.requestFocusInWindow(); 
		        } 
		    }); 
				
				this.setVisible(true);

			}
		}

	}

	public void agregarAtabla() {

		if (txtNombreItem.getText().equals("Nombre de item") || cbTipoDato.getSelectedIndex() == 0 || cbGeneros.getSelectedIndex() == 0) {
			lblIncorrectosItem.setText("Faltan campos or llenar");

		} else {
			if (cbTipoDato.getSelectedItem().equals("Numérico • NUM")) {
				if (txtDesde.getText().equals("Desde")) {
					lblIncorrectosItem.setText("Falta valor para desde.");
				} else {
					if (txtHasta.getText().equals("Hasta")) {
						lblIncorrectosItem.setText("Falta valor para hasta.");
					} else {

						String unidadauxi = "";
						String abreauxi = "";
						if (!txtUnidad.getText().equals("Unidad de medida"))
							unidadauxi = txtUnidad.getText();
						if (!txtAbreviatura.getText().equals("Abreviatura"))
							abreauxi = txtAbreviatura.getText();

						String[] ST = cbTipoDato.getSelectedItem().toString().split(" • ");
						String auxiTD1 = ST[0];
						String auxiTD2 = ST[1];

						String[] ST1 =cbGeneros.getSelectedItem().toString().split(" • ");
						String auxiGenero = ST1[1];

						if (txtordenItem.getText().equals("Orden") || Integer.parseInt(txtordenItem.getText()) > table.getRowCount()) {
							Object[] auxi = { txtNombreItem.getText(), auxiTD2, unidadauxi, abreauxi, "", txtDesde.getText(), txtHasta.getText(), auxiGenero, ordenauxi };

							TM.addRow(auxi);

							TipoDato auxitd = conexionBusqueda.getInstance().TDXnombre(auxiTD1);

							itemProtocolo IPauxi = new itemProtocolo(-1, -1, txtNombreItem.getText(), auxitd.getIdTD(), unidadauxi, abreauxi, "-1", Double.parseDouble(txtDesde.getText()),  Double.parseDouble(txtHasta.getText()), cbGeneros.getSelectedIndex(), ordenauxi,"");
							paraAgregar.add(IPauxi);
						} else {
							Object[] auxi = { txtNombreItem.getText(), auxiTD2, unidadauxi, abreauxi, "", txtDesde.getText(), txtHasta.getText(), auxiGenero, txtordenItem.getText() };

							TM.insertRow(Integer.parseInt(txtordenItem.getText()) - 1, auxi);

							TipoDato auxitd = conexionBusqueda.getInstance().TDXnombre(auxiTD1);

							itemProtocolo IPauxi = new itemProtocolo(-1, -1, txtNombreItem.getText(), auxitd.getIdTD(), unidadauxi, abreauxi, "-1",  Double.parseDouble(txtDesde.getText()),  Double.parseDouble(txtHasta.getText()), cbGeneros.getSelectedIndex(), Integer.parseInt(txtordenItem.getText()),"");
							paraAgregar.add(Integer.parseInt(txtordenItem.getText()) - 1, IPauxi);
							recalcularOrden(Integer.parseInt(txtordenItem.getText()), "+");
							recalcularOrdenDeAgregados(Integer.parseInt(txtordenItem.getText()), "+");
						}

						ordenauxi++;
						limpiarItems();

					}
				}
			} else {
				
				if (cbTipoDato.getSelectedItem().equals("Texto ( Descriptivo ) • TEXTO")) {

					String unidadauxi = "";
					String abreauxi = "";
					if (!txtUnidad.getText().equals("Unidad de medida"))
						unidadauxi = txtUnidad.getText();
					if (!txtAbreviatura.getText().equals("Abreviatura"))
						abreauxi = txtAbreviatura.getText();

					String[] ST =cbTipoDato.getSelectedItem().toString().split(" • ");
					String auxiTD1 = ST[0];
					String auxiTD2 = ST[1];

					String[] ST1 = cbGeneros.getSelectedItem().toString().split(" • ");
					String auxiGenero = ST1[1];

					if (txtordenItem.getText().equals("Orden") || Integer.parseInt(txtordenItem.getText()) > table.getRowCount()) {
						Object[] auxi = { txtNombreItem.getText(), auxiTD2, unidadauxi, abreauxi, "", "", "", auxiGenero, ordenauxi };

						TM.addRow(auxi);

						TipoDato auxitd = conexionBusqueda.getInstance().TDXnombre(auxiTD1);

						itemProtocolo IPauxi = new itemProtocolo(-1, -1, txtNombreItem.getText(), auxitd.getIdTD(), unidadauxi, abreauxi, "-1", -1, -1, cbGeneros.getSelectedIndex(), ordenauxi,"");
						paraAgregar.add(IPauxi);
					} else {
						Object[] auxi = { txtNombreItem.getText(), auxiTD2, unidadauxi, abreauxi, "", "", "", auxiGenero, txtordenItem.getText() };

						TM.insertRow(Integer.parseInt(txtordenItem.getText()) - 1, auxi);

						TipoDato auxitd = conexionBusqueda.getInstance().TDXnombre(auxiTD1);

						itemProtocolo IPauxi = new itemProtocolo(-1, -1, txtNombreItem.getText(), auxitd.getIdTD(), unidadauxi, abreauxi, "-1", -1, -1, cbGeneros.getSelectedIndex(), Integer.parseInt(txtordenItem.getText()),"");
						paraAgregar.add(Integer.parseInt(txtordenItem.getText()) - 1, IPauxi);
						recalcularOrden(Integer.parseInt(txtordenItem.getText()), "+");
						recalcularOrdenDeAgregados(Integer.parseInt(txtordenItem.getText()), "+");
					}

					ordenauxi++;
					limpiarItems();

				} else {

					if (cbValorNormal.getSelectedItem().toString().equals("Valor normal")) {
						lblIncorrectosItem.setText("Falta campo valor normal");

					} else {

						String unidadauxi = "";
						String abreauxi = "";

						if (!txtUnidad.getText().equals("Unidad de medida"))
							unidadauxi = txtUnidad.getText();
						if (!txtAbreviatura.getText().equals("Abreviatura"))
							abreauxi = txtAbreviatura.getText();

						String[] ST =cbTipoDato.getSelectedItem().toString().split(" • ");
						String auxiTD1 = ST[0];
						String auxiTD = ST[1];

						String[] ST1 = cbGeneros.getSelectedItem().toString().split(" • ");
						String auxiGenero = ST1[1];

						if (txtordenItem.getText().equals("Orden") || Integer.parseInt(txtordenItem.getText()) > table.getRowCount()) {
							Object[] auxi = { txtNombreItem.getText(), auxiTD, unidadauxi, abreauxi, cbValorNormal.getSelectedItem().toString(), "", "", auxiGenero, ordenauxi };

							TM.addRow(auxi);

							TipoDato auxitd = conexionBusqueda.getInstance().TDXnombre(auxiTD1);

							itemProtocolo IPauxi = new itemProtocolo(-1, -1, txtNombreItem.getText(), auxitd.getIdTD(), unidadauxi, abreauxi, cbValorNormal.getSelectedItem().toString(), -1, -1, cbGeneros.getSelectedIndex(), ordenauxi,"");
							paraAgregar.add(IPauxi);
						} else {
							Object[] auxi = { txtNombreItem.getText(), auxiTD, unidadauxi, abreauxi, cbValorNormal.getSelectedItem().toString(), "", "", auxiGenero, txtordenItem.getText() };

							TM.insertRow(Integer.parseInt(txtordenItem.getText()) - 1, auxi);
							
							cbGeneros.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									if (cbGeneros.getSelectedIndex()==2){cbGeneros.setForeground(Color.GREEN);};
									
								}
							});

							TipoDato auxitd = conexionBusqueda.getInstance().TDXnombre(auxiTD1);

							itemProtocolo IPauxi = new itemProtocolo(-1, -1, txtNombreItem.getText(), auxitd.getIdTD(), unidadauxi, abreauxi, cbValorNormal.getSelectedItem().toString(), -1, -1, cbGeneros.getSelectedIndex(), Integer.parseInt(txtordenItem.getText()),"");
							paraAgregar.add(Integer.parseInt(txtordenItem.getText()) - 1, IPauxi);
							recalcularOrden(Integer.parseInt(txtordenItem.getText()), "+");
							recalcularOrdenDeAgregados(Integer.parseInt(txtordenItem.getText()), "+");
						}

						ordenauxi++;
						limpiarItems();

					}
				
				}
				
			}
		}
	}

	public void quitarDtabla() {
		if (table.getSelectedRow() == -1) {
			lblIncorrectosItem.setText("No has seleccionado ningun item");
		} else {

		

				recalcularOrden(table.getSelectedRow(), " • ");
				itemProtocolo IP=paraAgregar.get(table.getSelectedRow());
				paraAgregar.remove(table.getSelectedRow());
				paraEliminar.add(IP);
				recalcularOrdenDeAgregados(table.getSelectedRow(), " • ");
				TM.removeRow(table.getSelectedRow());
				ordenauxi--;
		

		}
	}

	public void llenar(protocolo Pro) {
		idprotocolo=Pro.getIdProtocolo();
		txtCodigo.setText(Pro.getCodigo());
		txtCodigo.setForeground(Color.BLACK);
		
		if (Pro.getOrden()==-1) {
			txtOrden.setText(txtOrden.getLabel());
			txtOrden.setForeground(Colores.clrTextoInactivo);
		} else {
			txtOrden.setText(Pro.getOrden()+"");
			txtOrden.setForeground(Color.BLACK);
		}

		
		txtNombreProtocolo.setText(Pro.getNombre());
		txtNombreProtocolo.setForeground(Color.BLACK);
		
		Secciones sec=conexionBusqueda.seccionXid(Pro.getCodSeccion());
		cbSeccion.setSelectedItem(sec.getNombre()+" • "+sec.getSigla());
		
		cbPlanillas.setSelectedIndex(Pro.getIdPlanilla());
		
		
		TM = new MiTableModelNoEditable(conexionCombos.getInstance().listaItemsProtocolo(Pro.getIdProtocolo()), new String[] { "Nombre", "Tipo de dato", "Unidad", "Abrev.", "Valor Normal", "Desde", "Hasta", "Genero", "Orden" });
	
		table.setModel(TM);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getColumnModel().getColumn(0).setPreferredWidth(500);
		table.getColumnModel().getColumn(1).setPreferredWidth(110);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(120);
		table.getColumnModel().getColumn(5).setPreferredWidth(70);
		table.getColumnModel().getColumn(6).setPreferredWidth(70);
		table.getColumnModel().getColumn(7).setPreferredWidth(70);
		table.getColumnModel().getColumn(8).setPreferredWidth(70);
		table.setDefaultRenderer(Object.class, new clrRenderTablaProtocolos());
		paraAgregar=conexionCombos.getInstance().arrayItemsProtocolo(Pro.getIdProtocolo());
		ordenauxi=paraAgregar.get(paraAgregar.size()-1).getOrden()+1;
		
		codigoAntiguo=Pro.getCodigo();
		idprotocolo=Pro.getIdProtocolo();
		
		
	}

	public void insertarProtocolo() {
		if (txtCodigo.getText().equals("Código") || txtNombreProtocolo.getText().equals("Nombre del protocolo") || cbSeccion.getSelectedIndex() == 0 || cbPlanillas.getSelectedIndex() == 0) {
			lblIncorrectosItem.setText("Faltan campos por llenar para protocolo");
			if (txtCodigo.getText().equals("Código"))
				txtCodigo.setForeground(Color.red);
			if (txtNombreProtocolo.getText().equals("Nombre del protocolo"))
				txtNombreProtocolo.setForeground(Color.red);
			if (cbSeccion.getSelectedIndex() == 0)
				cbSeccion.setForeground(Color.red);
			if (cbPlanillas.getSelectedIndex() == 0)
				cbPlanillas.setForeground(Color.red);

		} else {
			if (table.getRowCount() == 0) {
				lblIncorrectosItem.setText("No has agregado items para este protocolo");

			} else {
				lblincorrectos.setText("");

				int orden;
				if (txtOrden.getText().equals("Orden")) {
					orden = -1;
				} else {
					orden = Integer.parseInt(txtOrden.getText());
				}
				String[] ST = cbSeccion.getSelectedItem().toString().split(" • ");
				Secciones seccion = conexionBusqueda.getInstance().seccionXnombre((ST[0]));

				protocolo protocolo = new protocolo(0, txtCodigo.getText().toUpperCase(), txtNombreProtocolo.getText(), seccion.getIdSeccion(), cbPlanillas.getSelectedIndex(), orden);

				if (conexion.getInstance().nuevoProtocolo(esta, protocolo, paraAgregar)) {
					esta.principal.registrarAccion("Agregar nuevo protocolo: '" + protocolo.getNombre()+"'");
					limpiarItems();
				}else{
					lblincorrectos.setText("Error  en la conexion con la base de datos");
				}
			}
		}
	}
	
	
	public void modificarProtocolo() {
		if (txtCodigo.getText().equals("Código") || txtNombreProtocolo.getText().equals("Nombre del protocolo") || cbSeccion.getSelectedIndex() == 0 || cbPlanillas.getSelectedIndex() == 0) {
			lblIncorrectosItem.setText("Faltan campos por llenar para protocolo");
			if (txtCodigo.getText().equals("Código"))
				txtCodigo.setForeground(Color.red);
			if (txtNombreProtocolo.getText().equals("Nombre del protocolo"))
				txtNombreProtocolo.setForeground(Color.red);
			if (cbSeccion.getSelectedIndex() == 0)
				cbSeccion.setForeground(Color.red);
			if (cbPlanillas.getSelectedIndex() == 0)
				cbPlanillas.setForeground(Color.red);

		} else {
			if (table.getRowCount() == 0) {
				lblIncorrectosItem.setText("No has agregado items para este protocolo");

			} else {
				lblincorrectos.setText("");

				int orden;
				if (txtOrden.getText().equals("Orden")) {
					orden = -1;
				} else {
					orden = Integer.parseInt(txtOrden.getText());
				}
				String[] ST = cbSeccion.getSelectedItem().toString().split(" • ");
				Secciones seccion = conexionBusqueda.getInstance().seccionXnombre((ST[0]));

				protocolo protocolo = new protocolo(idprotocolo, txtCodigo.getText().toUpperCase(), txtNombreProtocolo.getText(), seccion.getIdSeccion(), cbPlanillas.getSelectedIndex(), orden);

				if (conexion.getInstance().editarProtocolo(esta, protocolo, paraAgregar,paraEliminar,codigoAntiguo)) {
					esta.principal.registrarAccion("Modificación de  protocolo: '" + protocolo.getNombre()+"'");

					dispose();
				}else{
					lblincorrectos.setText("Error  en la conexion con la base de datos");
				}
			}
		}
	}

	public void EliminarProtocolo() {
		if (idprotocolo==-1) {
			lblIncorrectosItem.setText("No has escogido un protocolo aun");
		

		} else {
			lblincorrectos.setText("");

	
			if (conexion.getInstance().eliminarProtocolo(esta, idprotocolo)) {
				esta.principal.registrarAccion("Modificación de  protocolo con id códido: '" + idprotocolo+"'");

				dispose();
			}else{
				lblincorrectos.setText("Error  en la conexion con la base de datos");
			}
		}
	}
	

	public void validarTipoDato(String seleccion) {
		String[] ST =seleccion.split(" • ");
		String auxiseleccion = ST[0];
		if (auxiseleccion.equals("Numérico")) {
			cbValorNormal.setVisible(false);
			txtDesde.setVisible(true);
			txtHasta.setVisible(true);

			contentPane.updateUI();
		} else {
			if (auxiseleccion.equals("Tipo de datos")) {
				txtDesde.setVisible(false);
				txtHasta.setVisible(false);
				cbValorNormal.setVisible(false);
				contentPane.updateUI();
			} else {
				if (auxiseleccion.equals("Texto ( Descriptivo )")) {
					txtDesde.setVisible(false);
					txtHasta.setVisible(false);
					cbValorNormal.setVisible(false);
					contentPane.updateUI();
				} else {
				txtDesde.setVisible(false);
				txtHasta.setVisible(false);
				cbValorNormal.llenar(conexionCombos.getInstance().listaOPTDvector(auxiseleccion));
				cbValorNormal.setVisible(true);
				contentPane.updateUI();
				}
			}

		}
	}

	public void agregarTitulo() {
		if (txtTitulo.getText().equals("Título")) {
			lblIncorrectosItem.setText("Escribe un titulo para anexar a los items del protocolo");
		} else {
			String auuxi = "<html><b>" + txtTitulo.getText() + "</b></html>";

			if (txtordenItem.getText().equals("Orden") || Integer.parseInt(txtordenItem.getText()) > table.getRowCount()) {
				Object[] auxi = { auuxi, "•", "", "", "", "", "", "", ordenauxi };

				itemProtocolo IPauxi = new itemProtocolo(-1, -1, txtTitulo.getText(),2, "", "", "", -1, -1, -1, ordenauxi,"");
				paraAgregar.add(IPauxi);

				TM.addRow(auxi);
			} else {

				Object[] auxi = { auuxi, "•", "", "", "", "", "", "", txtordenItem.getText() };

				itemProtocolo IPauxi = new itemProtocolo(-1, -1, txtTitulo.getText(), 2, "", "", "", -1, -1, -1, Integer.parseInt(txtordenItem.getText()),"");
				paraAgregar.add(Integer.parseInt(txtordenItem.getText()) - 1, IPauxi);

				TM.insertRow(Integer.parseInt(txtordenItem.getText()) - 1, auxi);
				recalcularOrden(Integer.parseInt(txtordenItem.getText()), "+");
				recalcularOrdenDeAgregados(Integer.parseInt(txtordenItem.getText()), "+");
			}

			limpiarItems();
			ordenauxi++;

		}
	}

	public void agregarSeparador() {

		String auuxi = "--------------------------------------------------------------";

		if (txtordenItem.getText().equals("Orden") || Integer.parseInt(txtordenItem.getText()) > table.getRowCount()) {

			Object[] auxi = { auuxi, "-", "", "", "", "", "", "", ordenauxi };

			itemProtocolo IPauxi = new itemProtocolo(-1, -1, auuxi, 45, "", "", "", -1, -1, -1, ordenauxi,"");
			paraAgregar.add(IPauxi);
			TM.addRow(auxi);

		} else {
			Object[] auxi = { auuxi, "-", "", "", "", "", "", "", txtordenItem.getText() };

			itemProtocolo IPauxi = new itemProtocolo(-1, -1, auuxi, 45, "", "", "", -1, -1, -1, Integer.parseInt(txtordenItem.getText()),"");
			paraAgregar.add(Integer.parseInt(txtordenItem.getText()) - 1, IPauxi);
			TM.insertRow(Integer.parseInt(txtordenItem.getText()) - 1, auxi);
			recalcularOrden(Integer.parseInt(txtordenItem.getText()), "+");
			recalcularOrdenDeAgregados(Integer.parseInt(txtordenItem.getText()), "+");

		}
		limpiarItems();

		ordenauxi++;

	}

	public void recalcularOrden(int indice, String accion) {
		if (accion.equals(" • ")) {
			for (int i = indice; i < table.getRowCount(); i++) {
				table.setValueAt((Integer.parseInt(table.getValueAt(i, 8) + "") - 1), i, 8);
			}
		} else {
			for (int i = indice; i < table.getRowCount(); i++) {
				table.setValueAt((Integer.parseInt(table.getValueAt(i, 8) + "") + 1), i, 8);
			}
		}

	}

	public void recalcularOrdenDeAgregados(int indice, String accion) {
		if (accion.equals(" • ")) {
			for (int i = indice; i < paraAgregar.size(); i++) {

				paraAgregar.get(i).setOrden(paraAgregar.get(i).getOrden() - 1);

			}
		} else {
			for (int i = indice; i < paraAgregar.size(); i++) {

				paraAgregar.get(i).setOrden(paraAgregar.get(i).getOrden() + 1);

			}
		}

		System.out.println(paraAgregar.toString());
	}

	public void limpiarItems() {
		txtNombreItem.setText("Nombre de item");
		txtUnidad.setText("Unidad de medida");
		txtAbreviatura.setText("Abreviatura");
		cbValorNormal.setSelectedIndex(0);
		txtDesde.setText("Desde");
		txtHasta.setText("Hasta");
		cbTipoDato.setSelectedIndex(0);
		cbGeneros.setSelectedIndex(0);
		txtTitulo.setText("Título");
		txtordenItem.setText("Orden");
		
		

		txtNombreItem.setForeground(Colores.clrTextoInactivo);
		txtUnidad.setForeground(Colores.clrTextoInactivo);
		txtAbreviatura.setForeground(Colores.clrTextoInactivo);
		cbValorNormal.setForeground(Colores.clrTextoInactivo);
		txtDesde.setForeground(Colores.clrTextoInactivo);
		txtHasta.setForeground(Colores.clrTextoInactivo);
		txtTitulo.setForeground(Colores.clrTextoInactivo);
		txtordenItem.setForeground(Colores.clrTextoInactivo);

	}
	public void reportarError(String error){
		lblincorrectos.setText(error);
	}
}
