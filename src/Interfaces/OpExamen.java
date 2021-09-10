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
import interfaces_Modificadas.opExamenesTableCellEditor;

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
import auxiliares.Tarifa;
import auxiliares.TipoDato;
import auxiliares.TipoMuestra;
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
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.text.TabExpander;
import javax.swing.text.TabableView;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

public class OpExamen extends JDialog {
	private clrPanelBordes contentPane;
	private clrLabel lblincorrectos;
	private Principal principal;
	private Usuario usuario;
	private String accion;
	private clrLabel lblinicio;
	private circuloUsuario btnRedondo_;
	private OpExamen esta;
	private int idExamen=-1;
	private int idDias;
	private String codigoAntiguo;

	private protocolo elPprotocolo=null;
	private ClrCuadroDeTexto txtCodigoProtocolo;
	private btnRedondo btnCancelar;
	private btnRedondo btnGuardarYSalir;
	private lbProtocoloXcod lb1;
	private lbProtocoloXnombre lb2;
	private lbExamenXid lb3;
	private lbExamenXnombre lb4;
	private lbExamenXcups lb5;
	private JScrollPane scrollPane;
	private JTable table;
	private MiTableModelTarifas TM;
	private ArrayList<itemTarifa> paraEliminar = new ArrayList<itemTarifa>();
	private ArrayList<itemTarifa> paraAgregar = new ArrayList<itemTarifa>();
	private ArrayList<itemTarifa> paraModificar = new ArrayList<itemTarifa>();
	private int[]dias=new int[]{0,0,0,0,0,0,0};
	private JPanel panel;

	private ClrCuadroDeTexto txtNombreProtocolo;
	private ClrCuadroDeTexto txtNombreExamen;
	private clrComboBox cbTipodeMuestra;
	private ClrCuadroDeTexto txtDuracion;
	private ClrCuadroDeTexto txtCUPS;
	private btnRedondo btnNuevoProtocolo;
	private clrLabel lblIncorrectosItem;
	private btnRedondo btnrecalcular;

	private clrPanelBordes panelProtocolo;
	private ClrCuadroDeTexto txtCodigoExamen;
	private ClrCuadroDeTexto txtNivel;
	private ClrCuadroDeTexto txtCodigoBayer;
	private clrCheckBox chckbxLunes;
	private clrCheckBox chckbxMartes;
	private clrCheckBox chckbxMiercoles;
	private clrCheckBox chckbxJueves;
	private clrCheckBox chckbxViernes;
	private clrCheckBox chckbxSabado;
	private clrCheckBox chckbxDomingo;
	private ButtonGroup BG;
	private clrRadioButton rdbtnSi;
	private clrRadioButton rdbtnNo;
	private ClrCuadroDeTexto txtStickers;
	private btnRedondo btnLimpiarProtocolo ;
	private clrLabel lblSeProcesaLos;
	private clrLabel lblSeRemite;
	private clrtextpane txtAuxiPagina;

	public OpExamen(Principal principal, Usuario usuario, String accion) {
		super(principal, true);
		this.principal = principal;
		this.usuario = usuario;
		this.esta = this;
		this.accion = accion;

		// this.setDefaultLookAndFeelDecorated(false);

		setBounds(100, 100, 1056, 703);
		this.setUndecorated(true);
		this.setLocationRelativeTo(getParent());
		contentPane = new clrPanelBordes(true);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		

		if (accion.equals("Agregar")) {

			lblinicio = new clrLabel("Agregar Examen", 2,true);
			lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
			lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblinicio.setBounds(10, 11, 1036, 28);
			contentPane.add(lblinicio);

			lblincorrectos = new clrLabel("", 1);
			lblincorrectos.setForeground(Colores.clrAlertaCamarada);
			lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
			lblincorrectos.setAlignmentX(0.5f);
			lblincorrectos.setBounds(10, 38, 1036, 28);
			contentPane.add(lblincorrectos);

			

			btnCancelar = new btnRedondo("Salir", new Rectangle(237, 172,121,50),9);
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnCancelar.setBounds(544, 613, 136, 50);
			contentPane.add(btnCancelar);

			btnGuardarYSalir = new btnRedondo("Agregar", new Rectangle(48, 172,121,50),4);
			btnGuardarYSalir.setSelected(true);
			btnGuardarYSalir.setBounds(355, 613, 169, 50);
			btnGuardarYSalir.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					insertarExamen();
					
				}
			});
			contentPane.add(btnGuardarYSalir);

			TM = new MiTableModelTarifas(conexionCombos.getInstance().listaTarifasVacio(), new String[] { "ID", "Nombre de la tarifa", "Valor", "%  Urgencias", "% Festivos", "% Especial" });
			TM.escuchar();
			scrollPane = new clrScrollBar();
			
			//FALTA LIMITAR A SOLO NUMEROS LA EDICION DE VALORES PARA LA TABLA DE TARIFAS PARA ESTE EXAMEN
			
			scrollPane.setBounds(10, 27, 568, 395);

			table = new JTable();

			scrollPane.setViewportView(table);
			table.setModel(TM);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.getColumnModel().getColumn(2).setCellEditor(new opExamenesTableCellEditor());
			table.setDefaultRenderer(Object.class, new miRenderTabla());

			table.getColumnModel().getColumn(0).setPreferredWidth(7);
			table.getColumnModel().getColumn(1).setPreferredWidth(180);
			table.getColumnModel().getColumn(2).setPreferredWidth(90);
			table.getColumnModel().getColumn(3).setPreferredWidth(60);
			table.getColumnModel().getColumn(4).setPreferredWidth(60);
			table.getColumnModel().getColumn(5).setPreferredWidth(60);
			table.setRowHeight(20);
			table.addMouseListener(new MouseAdapter() {
				
		
				@Override
				public void mouseClicked(MouseEvent e) {
					TableCellEditor TCE = table.getCellEditor();
					if (table.isEditing()) {
						TCE.stopCellEditing();
					}
				}
			});
			panel = new clrPanelBordes(false);
			panel.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Tarifas para este examen", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
			panel.setBounds(458, 80, 588, 510);
			panel.setLayout(null);
			contentPane.add(panel);
			panel.add(scrollPane);

			lblIncorrectosItem = new clrLabel("",1);
			lblIncorrectosItem.setHorizontalAlignment(SwingConstants.CENTER);
			lblIncorrectosItem.setForeground(Color.RED);
			lblIncorrectosItem.setAlignmentX(0.5f);
			lblIncorrectosItem.setBounds(10, 470, 568, 29);
			panel.add(lblIncorrectosItem);

			btnrecalcular = new btnRedondo("Recalcular para las demas tarifas", new Rectangle(237, 172,121,50),14);
			btnrecalcular.setBounds(281, 423, 297, 50);
			btnrecalcular.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					recalcular(conexionCombos.getInstance().arrayTarifas());
				}
			});
			panel.add(btnrecalcular);

			txtNombreExamen = new ClrCuadroDeTexto(100,false,"Nombre del examen",1);
			txtNombreExamen.setBounds(175, 77, 273, 20);
			contentPane.add(txtNombreExamen);
			txtNombreExamen.setFocusCycleRoot(false);

			cbTipodeMuestra = new clrComboBox(conexionCombos.getInstance().listaMuestras(),1);
			cbTipodeMuestra.setBounds(175, 108, 273, 20);
			contentPane.add(cbTipodeMuestra);
			cbTipodeMuestra.setFocusCycleRoot(false);

			txtDuracion = new ClrCuadroDeTexto(11,true,"Duraci\u00F3n en dias",1);
			txtDuracion.setBounds(31, 139, 129, 20);
			contentPane.add(txtDuracion);
			txtDuracion.setFocusCycleRoot(false);

			txtCUPS = new ClrCuadroDeTexto(11,true,"C.U.P.S.",1);
			txtCUPS.setBounds(31, 108, 129, 20);
			contentPane.add(txtCUPS);
			txtCUPS.setFocusCycleRoot(false);

			panelProtocolo = new clrPanelBordes(false);
			panelProtocolo.setLayout(null);
			panelProtocolo.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Protocolo para este examen", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
			panelProtocolo.setBounds(10, 470, 438, 120);
			contentPane.add(panelProtocolo);

			txtCodigoProtocolo = new ClrCuadroDeTexto(6,false,"Código",true,1);
			txtCodigoProtocolo.setBounds(20, 31, 114, 20);
			txtCodigoProtocolo.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					lb1.setVisible(false);
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					if (lb1==null) {
						lb1= new lbProtocoloXcod(txtCodigoProtocolo, esta,panelProtocolo,esta.principal);
					}
					
				}
			});
			panelProtocolo.add(txtCodigoProtocolo);
			txtCodigoProtocolo.setFocusCycleRoot(false);

			txtNombreProtocolo = new ClrCuadroDeTexto(200,false,"Nombre del protocolo",true,1);
			txtNombreProtocolo.setBounds(144, 31, 273, 20);
			panelProtocolo.add(txtNombreProtocolo);
			txtNombreProtocolo.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					lb2.setVisible(false);
					
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					if (lb2==null) {
						lb2 = new lbProtocoloXnombre(txtNombreProtocolo,esta, panelProtocolo, esta.principal);
					}
					
				}
			});
			txtNombreProtocolo.setFocusCycleRoot(false);

			btnNuevoProtocolo = new btnRedondo("Agregar protocolo", new Rectangle(48, 172,121,50),4);
			btnNuevoProtocolo.setBounds(236, 59, 192, 50);
			panelProtocolo.add(btnNuevoProtocolo);
			btnNuevoProtocolo.setSelected(true);
			
			btnLimpiarProtocolo = new btnRedondo("Limpiar selección", new Rectangle(48, 172,121,50),13);
			btnLimpiarProtocolo.setSelected(true);
			btnLimpiarProtocolo.setBounds(47, 60, 183, 50);
			btnLimpiarProtocolo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					limpiarProtocolo();
					
				}
			});
			panelProtocolo.add(btnLimpiarProtocolo);
			btnNuevoProtocolo.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					new OpProtocolo(OpExamen.this.principal, OpExamen.this.usuario, "Agregar");
				}
			});

			txtCodigoExamen = new ClrCuadroDeTexto(6,false,"Código",1);
			txtCodigoExamen.setFocusCycleRoot(false);
			txtCodigoExamen.setBounds(31, 77, 129, 20);
			contentPane.add(txtCodigoExamen);

			txtNivel = new ClrCuadroDeTexto(11,true,"Nivel",1);
			txtNivel.setFocusCycleRoot(false);
			txtNivel.setBounds(357, 139, 91, 20);
			contentPane.add(txtNivel);

			txtCodigoBayer = new ClrCuadroDeTexto(11,false,"Código Bayer");
			txtCodigoBayer.setFocusCycleRoot(false);
			txtCodigoBayer.setBounds(31, 170, 129, 20);
			contentPane.add(txtCodigoBayer);

			lblSeProcesaLos = new clrLabel("Se procesa los d\u00EDas:", 1);
			lblSeProcesaLos.setForeground(Colores.clrTextoInactivo);
			lblSeProcesaLos.setBounds(31, 201, 147, 14);
			contentPane.add(lblSeProcesaLos);

			chckbxLunes = new clrCheckBox("Lunes");
			chckbxLunes.setBounds(31, 222, 73, 23);
			contentPane.add(chckbxLunes);

			chckbxMartes = new clrCheckBox("Martes");
			chckbxMartes.setBounds(31, 256, 83, 23);
			contentPane.add(chckbxMartes);

			chckbxMiercoles = new clrCheckBox("Miercoles");
			chckbxMiercoles.setBounds(31, 290, 97, 23);
			contentPane.add(chckbxMiercoles);

			chckbxJueves = new clrCheckBox("Jueves");
			chckbxJueves.setBounds(31, 321, 97, 23);
			contentPane.add(chckbxJueves);

			chckbxViernes = new clrCheckBox("Viernes");
			chckbxViernes.setBounds(31, 355, 97, 23);
			contentPane.add(chckbxViernes);

			chckbxSabado = new clrCheckBox("Sabados");
			chckbxSabado.setBounds(31, 389, 97, 23);
			contentPane.add(chckbxSabado);

			chckbxDomingo = new clrCheckBox("Domingos");
			chckbxDomingo.setBounds(31, 423, 97, 23);
			contentPane.add(chckbxDomingo);

			lblSeRemite = new clrLabel("Se remite:", 1);
			lblSeRemite.setForeground(Colores.clrTextoInactivo);
			lblSeRemite.setBounds(213, 201, 147, 14);
			contentPane.add(lblSeRemite);

			BG = new ButtonGroup();

			rdbtnSi = new clrRadioButton("Si");
			rdbtnSi.setBounds(213, 222, 46, 23);
			contentPane.add(rdbtnSi);
			BG.add(rdbtnSi);

			rdbtnNo = new clrRadioButton("No");
			rdbtnNo.setBounds(293, 222, 55, 23);
			contentPane.add(rdbtnNo);
			BG.add(rdbtnNo);

			txtStickers = new ClrCuadroDeTexto(1,true,"Stickers a imprimir",1);
			txtStickers.setFocusCycleRoot(false);
			txtStickers.setBounds(175, 139, 172, 20);
			contentPane.add(txtStickers);
			
			txtAuxiPagina = new clrtextpane(500, false, "Requerimientos para toma de muestra", 0);
			txtAuxiPagina.setBounds(175, 277, 273, 182);
			contentPane.add(txtAuxiPagina);
			
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

				lblinicio = new clrLabel("Modificar Examen", 2,true);
				lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
				lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
				lblinicio.setBounds(10, 11, 1036, 28);
				contentPane.add(lblinicio);

				lblincorrectos = new clrLabel("", 1);
				lblincorrectos.setForeground(Colores.clrAlertaCamarada);
				lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
				lblincorrectos.setAlignmentX(0.5f);
				lblincorrectos.setBounds(10, 38, 1036, 28);
				contentPane.add(lblincorrectos);

				

				btnCancelar = new btnRedondo("Salir", new Rectangle(237, 172,121,50),9);
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnCancelar.setBounds(544, 613, 136, 50);
				contentPane.add(btnCancelar);

				btnGuardarYSalir = new btnRedondo("Modificar y salir", new Rectangle(48, 172,121,50),3);
				btnGuardarYSalir.setSelected(true);
				btnGuardarYSalir.setBounds(355, 613, 169, 50);
				btnGuardarYSalir.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						modificarExamen();
						
					}
				});
				contentPane.add(btnGuardarYSalir);

				TM = new MiTableModelTarifas(conexionCombos.getInstance().listaTarifasVacio(), new String[] { "ID", "Nombre de la tarifa", "Valor", "%  Urgencias", "% Festivos", "% Especial" });
				TM.escuchar();
				scrollPane = new clrScrollBar();
				
				
				scrollPane.setBounds(10, 27, 568, 395);

				table = new JTable();
				scrollPane.setViewportView(table);
				table.setModel(TM);
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				table.setDefaultRenderer(Object.class, new miRenderTabla());

				table.getColumnModel().getColumn(0).setPreferredWidth(7);
				table.getColumnModel().getColumn(1).setPreferredWidth(180);
				table.getColumnModel().getColumn(2).setPreferredWidth(90);
				table.getColumnModel().getColumn(3).setPreferredWidth(60);
				table.getColumnModel().getColumn(4).setPreferredWidth(60);
				table.getColumnModel().getColumn(5).setPreferredWidth(60);
				table.setRowHeight(20);

				panel = new clrPanelBordes(false);
				panel.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Tarifas para este examen", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
				panel.setBounds(458, 80, 588, 510);
				panel.setLayout(null);
				contentPane.add(panel);
				panel.add(scrollPane);

				lblIncorrectosItem = new clrLabel("",1);
				lblIncorrectosItem.setHorizontalAlignment(SwingConstants.CENTER);
				lblIncorrectosItem.setForeground(Color.RED);
				lblIncorrectosItem.setAlignmentX(0.5f);
				lblIncorrectosItem.setBounds(10, 470, 568, 29);
				panel.add(lblIncorrectosItem);

				btnrecalcular = new btnRedondo("Recalcular para las demas tarifas", new Rectangle(237, 172,121,50),14);
				btnrecalcular.setBounds(281, 423, 297, 50);
				btnrecalcular.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						recalcular(conexionCombos.getInstance().arrayTarifas());
					}
				});
				panel.add(btnrecalcular);

				txtNombreExamen = new ClrCuadroDeTexto(100,false,"Nombre del examen",true,1);
				txtNombreExamen.setBounds(175, 77, 273, 20);
				contentPane.add(txtNombreExamen);
				txtNombreExamen.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						lb4.setVisible(false);
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						if (lb4==null) {
							lb4=new lbExamenXnombre(txtNombreExamen, esta, esta.principal);
						}
						
					}
				});
				txtNombreExamen.setFocusCycleRoot(false);

				cbTipodeMuestra = new clrComboBox(conexionCombos.getInstance().listaMuestras(),1);
				cbTipodeMuestra.setBounds(175, 108, 273, 20);
				contentPane.add(cbTipodeMuestra);
				cbTipodeMuestra.setFocusCycleRoot(false);

				txtDuracion = new ClrCuadroDeTexto(11,true,"Duraci\u00F3n en dias",1);
				txtDuracion.setBounds(31, 139, 129, 20);
				contentPane.add(txtDuracion);
				txtDuracion.setFocusCycleRoot(false);

				txtCUPS = new ClrCuadroDeTexto(11,true,"C.U.P.S.",true,1);
				txtCUPS.setBounds(31, 108, 129, 20);
				contentPane.add(txtCUPS);
				txtCUPS.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						lb5.setVisible(false);
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						if (lb5==null) {
							lb5=new lbExamenXcups(txtCUPS, esta, esta.principal);
						}
					}
				});
				txtCUPS.setFocusCycleRoot(false);

				panelProtocolo = new clrPanelBordes(false);
				panelProtocolo.setLayout(null);
				panelProtocolo.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Protocolo para este examen", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
				panelProtocolo.setBounds(10, 470, 438, 120);
				contentPane.add(panelProtocolo);

				txtCodigoProtocolo = new ClrCuadroDeTexto(6,false,"Código",true,1);
				txtCodigoProtocolo.setBounds(20, 31, 114, 20);
				txtCodigoProtocolo.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						lb1.setVisible(false);
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						if (lb1==null) {
							lb1= new lbProtocoloXcod(txtCodigoProtocolo, esta,panelProtocolo,esta.principal);
						}
						
					}
				});
				panelProtocolo.add(txtCodigoProtocolo);
				txtCodigoProtocolo.setFocusCycleRoot(false);

				txtNombreProtocolo = new ClrCuadroDeTexto(200,false,"Nombre del protocolo",true,1);
				txtNombreProtocolo.setBounds(144, 31, 273, 20);
				panelProtocolo.add(txtNombreProtocolo);
				txtNombreProtocolo.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						lb2.setVisible(false);
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						if (lb2==null) {
							lb2 = new lbProtocoloXnombre(txtNombreProtocolo,esta, panelProtocolo, esta.principal);
						}
						
					}
				});
				txtNombreProtocolo.setFocusCycleRoot(false);

				btnNuevoProtocolo = new btnRedondo("Agregar protocolo", new Rectangle(48, 172,121,50),4);
				btnNuevoProtocolo.setBounds(236, 59, 192, 50);
				panelProtocolo.add(btnNuevoProtocolo);
				btnNuevoProtocolo.setSelected(true);
				
				btnLimpiarProtocolo = new btnRedondo("Limpiar selección", new Rectangle(48, 172,121,50),13);
				btnLimpiarProtocolo.setSelected(true);
				btnLimpiarProtocolo.setBounds(47, 60, 183, 50);
				btnLimpiarProtocolo.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						limpiarProtocolo();
						
					}
				});
				panelProtocolo.add(btnLimpiarProtocolo);
				btnNuevoProtocolo.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						new OpProtocolo(OpExamen.this.principal, OpExamen.this.usuario, "Agregar");
					}
				});

				txtCodigoExamen = new ClrCuadroDeTexto(6,false,"Código",true,1);
				txtCodigoExamen.setFocusCycleRoot(false);
				txtCodigoExamen.setBounds(31, 77, 129, 20);
				txtCodigoExamen.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						lb3.setVisible(false);
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						if (lb3==null) {
							lb3=new lbExamenXid(txtCodigoExamen, esta, esta.principal);
						}
					}
				});
				contentPane.add(txtCodigoExamen);

				txtNivel = new ClrCuadroDeTexto(11,true,"Nivel",1);
				txtNivel.setFocusCycleRoot(false);
				txtNivel.setBounds(357, 139, 91, 20);
				contentPane.add(txtNivel);

				txtCodigoBayer = new ClrCuadroDeTexto(11,false,"Código Bayer");
				txtCodigoBayer.setFocusCycleRoot(false);
				txtCodigoBayer.setBounds(31, 170, 129, 20);
				contentPane.add(txtCodigoBayer);

				lblSeProcesaLos = new clrLabel("Se procesa los d\u00EDas:", 1);
				lblSeProcesaLos.setForeground(Colores.clrTextoInactivo);
				lblSeProcesaLos.setBounds(31, 201, 147, 14);
				contentPane.add(lblSeProcesaLos);

				chckbxLunes = new clrCheckBox("Lunes");
				chckbxLunes.setBounds(31, 222, 73, 23);
				contentPane.add(chckbxLunes);

				chckbxMartes = new clrCheckBox("Martes");
				chckbxMartes.setBounds(31, 256, 83, 23);
				contentPane.add(chckbxMartes);

				chckbxMiercoles = new clrCheckBox("Miercoles");
				chckbxMiercoles.setBounds(31, 290, 97, 23);
				contentPane.add(chckbxMiercoles);

				chckbxJueves = new clrCheckBox("Jueves");
				chckbxJueves.setBounds(31, 321, 97, 23);
				contentPane.add(chckbxJueves);

				chckbxViernes = new clrCheckBox("Viernes");
				chckbxViernes.setBounds(31, 355, 97, 23);
				contentPane.add(chckbxViernes);

				chckbxSabado = new clrCheckBox("Sabados");
				chckbxSabado.setBounds(31, 389, 97, 23);
				contentPane.add(chckbxSabado);

				chckbxDomingo = new clrCheckBox("Domingos");
				chckbxDomingo.setBounds(31, 423, 97, 23);
				contentPane.add(chckbxDomingo);

				lblSeRemite = new clrLabel("Se remite:", 1);
				lblSeRemite.setForeground(Colores.clrTextoInactivo);
				lblSeRemite.setBounds(213, 201, 147, 14);
				contentPane.add(lblSeRemite);

				BG = new ButtonGroup();

				rdbtnSi = new clrRadioButton("Si");
				rdbtnSi.setBounds(213, 222, 46, 23);
				contentPane.add(rdbtnSi);
				BG.add(rdbtnSi);

				rdbtnNo = new clrRadioButton("No");
				rdbtnNo.setBounds(293, 222, 55, 23);
				contentPane.add(rdbtnNo);
				BG.add(rdbtnNo);

				txtStickers = new ClrCuadroDeTexto(1,true,"Stickers a imprimir",1);
				txtStickers.setFocusCycleRoot(false);
				txtStickers.setBounds(175, 139, 172, 20);
				contentPane.add(txtStickers);
				
				txtAuxiPagina = new clrtextpane(500, false, "Requerimientos para toma de muestra", 0);
				txtAuxiPagina.setBounds(175, 277, 273, 182);
				contentPane.add(txtAuxiPagina);
				
				SwingUtilities.invokeLater( new Runnable() 
		        { 
		        public void run() 
		            { 
		          btnGuardarYSalir.requestFocusInWindow(); 
		        } 
		    }); 
				
				this.setVisible(true);

			} else {
				

				lblinicio = new clrLabel("Eliminar Examen", 2,true);
				lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
				lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
				lblinicio.setBounds(10, 11, 1036, 28);
				contentPane.add(lblinicio);

				lblincorrectos = new clrLabel("", 1);
				lblincorrectos.setForeground(Colores.clrAlertaCamarada);
				lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
				lblincorrectos.setAlignmentX(0.5f);
				lblincorrectos.setBounds(10, 38, 1036, 28);
				contentPane.add(lblincorrectos);

				

				btnCancelar = new btnRedondo("Salir", new Rectangle(237, 172,121,50),9);
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnCancelar.setBounds(544, 613, 136, 50);
				contentPane.add(btnCancelar);

				btnGuardarYSalir = new btnRedondo("Eliminar y salir", new Rectangle(48, 172,121,50),5);
				btnGuardarYSalir.setSelected(true);
				btnGuardarYSalir.setBounds(355, 613, 169, 50);
				btnGuardarYSalir.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						eliminarExamen();
		
						
					}
				});
				contentPane.add(btnGuardarYSalir);

				TM = new MiTableModelTarifas(conexionCombos.getInstance().listaTarifasVacio(), new String[] { "ID", "Nombre de la tarifa", "Valor", "%  Urgencias", "% Festivos", "% Especial" });
				TM.escuchar();
				scrollPane = new clrScrollBar();
				
				
				scrollPane.setBounds(10, 27, 568, 395);

				table = new JTable();
				scrollPane.setViewportView(table);
				table.setModel(TM);
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				table.setDefaultRenderer(Object.class, new miRenderTabla());

				table.getColumnModel().getColumn(0).setPreferredWidth(7);
				table.getColumnModel().getColumn(1).setPreferredWidth(180);
				table.getColumnModel().getColumn(2).setPreferredWidth(90);
				table.getColumnModel().getColumn(3).setPreferredWidth(60);
				table.getColumnModel().getColumn(4).setPreferredWidth(60);
				table.getColumnModel().getColumn(5).setPreferredWidth(60);
				table.setRowHeight(20);

				panel = new clrPanelBordes(false);
				panel.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Tarifas para este examen", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
				panel.setBounds(458, 80, 588, 510);
				panel.setLayout(null);
				contentPane.add(panel);
				panel.add(scrollPane);

				lblIncorrectosItem = new clrLabel("",1);
				lblIncorrectosItem.setHorizontalAlignment(SwingConstants.CENTER);
				lblIncorrectosItem.setForeground(Color.RED);
				lblIncorrectosItem.setAlignmentX(0.5f);
				lblIncorrectosItem.setBounds(10, 470, 568, 29);
				panel.add(lblIncorrectosItem);

				btnrecalcular = new btnRedondo("Recalcular para las demas tarifas", new Rectangle(237, 172,121,50),14);
				btnrecalcular.setBounds(281, 423, 297, 50);
				btnrecalcular.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						recalcular(conexionCombos.getInstance().arrayTarifas());
					}
				});
				panel.add(btnrecalcular);

				txtNombreExamen = new ClrCuadroDeTexto(100,false,"Nombre del examen",true);
				txtNombreExamen.setBounds(175, 77, 273, 20);
				contentPane.add(txtNombreExamen);
				txtNombreExamen.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						lb4.setVisible(false);
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						if (lb4==null) {
							lb4=new lbExamenXnombre(txtNombreExamen, esta, esta.principal);
						}
						
					}
				});
				txtNombreExamen.setFocusCycleRoot(false);

				cbTipodeMuestra = new clrComboBox(conexionCombos.getInstance().listaMuestras(),1);
				cbTipodeMuestra.setBounds(175, 108, 273, 20);
				contentPane.add(cbTipodeMuestra);
				cbTipodeMuestra.setFocusCycleRoot(false);

				txtDuracion = new ClrCuadroDeTexto(11,true,"Duración en dias",true);
				txtDuracion.setBounds(31, 139, 129, 20);
				contentPane.add(txtDuracion);
				txtDuracion.setFocusCycleRoot(false);

				txtCUPS = new ClrCuadroDeTexto(11,true,"C.U.P.S.",true);
				txtCUPS.setBounds(31, 108, 129, 20);
				contentPane.add(txtCUPS);
				txtCUPS.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						lb5.setVisible(false);
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						if (lb5==null) {
							lb5=new lbExamenXcups(txtCUPS, esta, esta.principal);
						}
					}
				});
				txtCUPS.setFocusCycleRoot(false);

				panelProtocolo = new clrPanelBordes(false);
				panelProtocolo.setLayout(null);
				panelProtocolo.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Protocolo para este examen", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
				panelProtocolo.setBounds(10, 470, 438, 120);
				contentPane.add(panelProtocolo);

				txtCodigoProtocolo = new ClrCuadroDeTexto(6,false,"Código",true);
				txtCodigoProtocolo.setBounds(20, 31, 114, 20);
				txtCodigoProtocolo.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						lb1.setVisible(false);
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						if (lb1==null) {
							lb1= new lbProtocoloXcod(txtCodigoProtocolo, esta,panelProtocolo,esta.principal);
						}
						
					}
				});
				panelProtocolo.add(txtCodigoProtocolo);
				txtCodigoProtocolo.setFocusCycleRoot(false);

				txtNombreProtocolo = new ClrCuadroDeTexto(200,false,"Nombre del protocolo",true);
				txtNombreProtocolo.setBounds(144, 31, 273, 20);
				panelProtocolo.add(txtNombreProtocolo);
				txtNombreProtocolo.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						lb2.setVisible(false);
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						if (lb2==null) {
							lb2 = new lbProtocoloXnombre(txtNombreProtocolo,esta, panelProtocolo, esta.principal);
						}
						
					}
				});
				txtNombreProtocolo.setFocusCycleRoot(false);

				btnNuevoProtocolo = new btnRedondo("Agregar protocolo", new Rectangle(48, 172,121,50),4);
				btnNuevoProtocolo.setBounds(236, 59, 192, 50);
				panelProtocolo.add(btnNuevoProtocolo);
				btnNuevoProtocolo.setSelected(true);
				
				btnLimpiarProtocolo = new btnRedondo("Limpiar selección", new Rectangle(48, 172,121,50),13);
				btnLimpiarProtocolo.setSelected(true);
				btnLimpiarProtocolo.setBounds(47, 60, 183, 50);
				btnLimpiarProtocolo.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						limpiarProtocolo();
						
					}
				});
				panelProtocolo.add(btnLimpiarProtocolo);
				btnNuevoProtocolo.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						new OpProtocolo(OpExamen.this.principal, OpExamen.this.usuario, "Agregar");
					}
				});

				txtCodigoExamen = new ClrCuadroDeTexto(6,false,"Código",true);
				txtCodigoExamen.setFocusCycleRoot(false);
				txtCodigoExamen.setBounds(31, 77, 129, 20);
				txtCodigoExamen.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						lb3.setVisible(false);
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						if (lb3==null) {
							lb3=new lbExamenXid(txtCodigoExamen, esta, esta.principal);
						}
					}
				});
				contentPane.add(txtCodigoExamen);

				txtNivel = new ClrCuadroDeTexto(11,true,"Nivel",true);
				txtNivel.setFocusCycleRoot(false);
				txtNivel.setBounds(357, 139, 91, 20);
				contentPane.add(txtNivel);

				txtCodigoBayer = new ClrCuadroDeTexto(11,false,"Código Bayer");
				txtCodigoBayer.setFocusCycleRoot(false);
				txtCodigoBayer.setBounds(31, 170, 129, 20);
				contentPane.add(txtCodigoBayer);

				lblSeProcesaLos = new clrLabel("Se procesa los d\u00EDas:", 1);
				lblSeProcesaLos.setForeground(Colores.clrTextoInactivo);
				lblSeProcesaLos.setBounds(31, 201, 147, 14);
				contentPane.add(lblSeProcesaLos);

				chckbxLunes = new clrCheckBox("Lunes");
				chckbxLunes.setBounds(31, 222, 73, 23);
				contentPane.add(chckbxLunes);

				chckbxMartes = new clrCheckBox("Martes");
				chckbxMartes.setBounds(31, 256, 83, 23);
				contentPane.add(chckbxMartes);

				chckbxMiercoles = new clrCheckBox("Miercoles");
				chckbxMiercoles.setBounds(31, 290, 97, 23);
				contentPane.add(chckbxMiercoles);

				chckbxJueves = new clrCheckBox("Jueves");
				chckbxJueves.setBounds(31, 321, 97, 23);
				contentPane.add(chckbxJueves);

				chckbxViernes = new clrCheckBox("Viernes");
				chckbxViernes.setBounds(31, 355, 97, 23);
				contentPane.add(chckbxViernes);

				chckbxSabado = new clrCheckBox("Sabados");
				chckbxSabado.setBounds(31, 389, 97, 23);
				contentPane.add(chckbxSabado);

				chckbxDomingo = new clrCheckBox("Domingos");
				chckbxDomingo.setBounds(31, 423, 97, 23);
				contentPane.add(chckbxDomingo);

				lblSeRemite = new clrLabel("Se remite:", 1);
				lblSeRemite.setForeground(Colores.clrTextoInactivo);
				lblSeRemite.setBounds(213, 201, 147, 14);
				contentPane.add(lblSeRemite);

				BG = new ButtonGroup();

				rdbtnSi = new clrRadioButton("Si");
				rdbtnSi.setBounds(213, 222, 46, 23);
				contentPane.add(rdbtnSi);
				BG.add(rdbtnSi);

				rdbtnNo = new clrRadioButton("No");
				rdbtnNo.setBounds(293, 222, 55, 23);
				contentPane.add(rdbtnNo);
				BG.add(rdbtnNo);

				txtStickers = new ClrCuadroDeTexto(1,true,"Stickers a imprimir",true);
				txtStickers.setFocusCycleRoot(false);
				txtStickers.setBounds(175, 139, 172, 20);
				contentPane.add(txtStickers);
				
				txtAuxiPagina = new clrtextpane(500, false, "Requerimientos para toma de muestra", 0);
				txtAuxiPagina.setBounds(175, 277, 273, 182);
				contentPane.add(txtAuxiPagina);
				
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

	public void llenar(protocolo Pro) {
		this.elPprotocolo=Pro;
		
		txtCodigoProtocolo.setText(Pro.getCodigo());
		txtCodigoProtocolo.setForeground(Color.BLACK);
		txtCodigoProtocolo.setEditable(false);

		txtNombreProtocolo.setText(Pro.getNombre());
		txtNombreProtocolo.setForeground(Color.BLACK);
		txtNombreProtocolo.setEditable(false);
	}
	
	public void llenar(Examen examen) {
		txtCodigoExamen.setText(examen.getSigla());
		txtCodigoExamen.setForeground(Color.BLACK);
		txtNombreExamen.setText(examen.getNombre());
		txtNombreExamen.setForeground(Color.BLACK);
		txtCUPS.setText(examen.getCups()+"");
		txtCUPS.setForeground(Color.BLACK);
		
		
		TipoMuestra  mues=conexionBusqueda.getInstance().muestraXid(examen.getIdMuestra()+"");
		cbTipodeMuestra.setSelectedItem(mues.getNombre()+" • "+mues.getSigla());
		
		txtDuracion.setText(examen.getDuracion()+"");
		txtDuracion.setForeground(Color.BLACK);
		txtStickers.setText(examen.getStickers()+"");
		txtStickers.setForeground(Color.BLACK);
		txtNivel.setText(examen.getNivel()+"");
		txtNivel.setForeground(Color.BLACK);
		txtCodigoBayer.setText(examen.getCodBayer());
		txtCodigoBayer.setForeground(Color.BLACK);
		
		if (!examen.getAuxiWeb().equals("")) {
			txtAuxiPagina.setText(examen.getAuxiWeb());
			txtAuxiPagina.setForeground(Color.BLACK);
		} else {
			txtAuxiPagina.reiniciar();
		}
	
		
		if (examen.getSeRemite()==0) {
			rdbtnNo.setSelected(true);
			rdbtnNo.setForeground(Colores.clrPrincipal);
		} else {
			rdbtnSi.setSelected(true);
			rdbtnSi.setForeground(Colores.clrPrincipal);
		}
		
		
		
		int[] dias=conexionBusqueda.getInstance().diasXid(examen.getIdDias()+"");
		if(dias[0]==1){chckbxLunes.setSelected(true);chckbxLunes.setForeground(Colores.clrPrincipal);}
		if(dias[1]==1){chckbxMartes.setSelected(true);chckbxMartes.setForeground(Colores.clrPrincipal);}
		if(dias[2]==1){chckbxMiercoles.setSelected(true);chckbxMiercoles.setForeground(Colores.clrPrincipal);}
		if(dias[3]==1){chckbxJueves.setSelected(true);chckbxJueves.setForeground(Colores.clrPrincipal);}
		if(dias[4]==1){chckbxViernes.setSelected(true);chckbxViernes.setForeground(Colores.clrPrincipal);}
		if(dias[5]==1){chckbxSabado.setSelected(true);chckbxSabado.setForeground(Colores.clrPrincipal);}
		if(dias[6]==1){chckbxDomingo.setSelected(true);chckbxDomingo.setForeground(Colores.clrPrincipal);}
		
		llenar(conexionBusqueda.getInstance().protocoloXid(examen.getCodProtocolo()+""));
		llenarValoresTablaItems(examen.getIT());
		codigoAntiguo=examen.getSigla();
		idExamen=examen.getIdExamen();
		idDias=examen.getIdDias();
		paraModificar=examen.getIT();
		System.out.println(examen.getIT().toString());
		
	}
	
	private void llenarValoresTablaItems(ArrayList<itemTarifa> ArrayIT) {
			for (int i = 0; i < table.getRowCount(); i++) {
				for (int j = 0; j < ArrayIT.size(); j++) {
					itemTarifa IT=ArrayIT.get(j);
					if (table.getValueAt(i, 0).equals(IT.getCodTarifa()+"")) {
						String auxiurgen="";
						String auxifes="";
						String auxiesp="";
						
						if (IT.getRecargaUrgencias()!=-1){auxiurgen=IT.getRecargaUrgencias()+"";}
						if (IT.getRecargaFestivos()!=-1){auxifes=IT.getRecargaFestivos()+"";}
						if (IT.getRecargaEspecial()!=-1){auxiesp=IT.getRecargaEspecial()+"";}
						
						table.setValueAt(IT.getValor(), i, 2);
						table.setValueAt(auxiurgen, i, 3);
						table.setValueAt(auxifes, i, 4);
						table.setValueAt(auxiesp, i, 5);
					}
				}
			}
	}

	public void limpiarProtocolo( ) {
		
this.elPprotocolo=null;
		
		txtCodigoProtocolo.setText("Código");
		txtCodigoProtocolo.setForeground(Colores.clrTextoInactivo);
		txtCodigoProtocolo.setEditable(true);

		txtNombreProtocolo.setText("Nombre del protocolo");
		txtNombreProtocolo.setForeground(Colores.clrTextoInactivo);
		txtNombreProtocolo.setEditable(true);
	}
	
	public boolean faltanValores( ) {
			
		for (int i = 0; i < table.getRowCount(); i++) {
			if (table.getValueAt(i,2).equals("")) {
				return true;
			}
		}
		return false;
			}

	public void insertarExamen() {
		if (txtCodigoExamen.getText().equals("Código") || elPprotocolo==null || txtNombreExamen.getText().equals("Nombre del examen") ||txtCUPS.getText().equals("C.U.P.S.")||cbTipodeMuestra.getSelectedIndex()==0||txtDuracion.getText().equals("Duración en días")||txtNivel.getText().equals("Nivel")||txtStickers.getText().equals("Stickers a imprimir")||(rdbtnSi.isSelected()==false&&rdbtnNo.isSelected()==false)||(chckbxLunes.isSelected()==false&&chckbxMartes.isSelected()==false&&chckbxMiercoles.isSelected()==false&&chckbxJueves.isSelected()==false&&chckbxViernes.isSelected()==false&&chckbxSabado.isSelected()==false&&chckbxDomingo.isSelected()==false)) {
			lblincorrectos.setText("Faltan campos por llenar para este examen");
			
			if (txtCodigoExamen.getText().equals("Código")){txtCodigoExamen.setForegroundd(Color.red);}else {txtCodigoExamen.setForegroundd(Color.BLACK);}
			if (elPprotocolo==null){panelProtocolo.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Color.RED), "Protocolo para este examen", TitledBorder.LEFT, TitledBorder.TOP, null, Color.RED));}else {panelProtocolo.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Protocolo para este examen", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));}
			if (txtNombreExamen.getText().equals("Nombre del examen")){txtNombreExamen.setForegroundd(Color.RED);}else {txtNombreExamen.setForegroundd(Color.black);}
			if (txtCUPS.getText().equals("C.U.P.S.")){txtCUPS.setForegroundd(Color.RED);}else {txtCUPS.setForegroundd(Color.black);}
			if (cbTipodeMuestra.getSelectedIndex()==0){cbTipodeMuestra.setForeground(Color.RED);}else {cbTipodeMuestra.setForeground(Color.black);}
			if (txtDuracion.getText().equals("Duración en dias")){txtDuracion.setForegroundd(Color.RED);}else {txtDuracion.setForegroundd(Color.black);}
			if (txtNivel.getText().equals("Nivel")){txtNivel.setForegroundd(Color.RED);}else {txtNivel.setForegroundd(Color.black);}
			if (	txtStickers.getText().equals("Stickers a imprimir")){txtStickers.setForegroundd(Color.RED);}else {txtStickers.setForegroundd(Color.black);}
			if (chckbxLunes.isSelected()==false&&chckbxMartes.isSelected()==false&&chckbxMiercoles.isSelected()==false&&chckbxJueves.isSelected()==false&&chckbxViernes.isSelected()==false&&chckbxSabado.isSelected()==false&&chckbxDomingo.isSelected()==false){lblSeProcesaLos.setForeground(Color.RED);}else {lblSeProcesaLos.setForeground(Color.black);}
			if (rdbtnSi.isSelected()==false&&rdbtnNo.isSelected()==false){lblSeRemite.setForeground(Color.RED);}else {lblSeRemite.setForeground(Color.black);}

			
			
		
				
		}else {
			if( faltanValores()) {
				lblIncorrectosItem.setText("Asigna un valor a cada tarifa manualmente o recalculando según tarifa principal");
				panel.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Color.red), "Tarifas para este examen", TitledBorder.LEFT, TitledBorder.TOP, null, Color.RED));

			} else {
				lblIncorrectosItem.setText("");
				panel.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Tarifas para este examen", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));

		
			lblincorrectos.setText("");
			lblIncorrectosItem.setText("");
			panelProtocolo.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Protocolo para este examen", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
			lblSeProcesaLos.setForeground(Colores.clrTextoInactivo);
			lblSeRemite.setForeground(Colores.clrTextoInactivo);

				String  bayer="-1";
				if (!txtCodigoBayer.getText().equals("Código Bayer")) bayer =txtCodigoBayer.getText().toUpperCase();
				
				int remite=0;
				if (rdbtnSi.isSelected()) {
					remite = 1;
				} 
				String[] ST = cbTipodeMuestra.getSelectedItem().toString().split( " • ");
				
				TipoMuestra muestra = conexionBusqueda.getInstance().muestraXsigla(ST[1]);
				protocolo proto=conexionBusqueda.getInstance().protocoloXnombre(txtNombreProtocolo.getText());
				String auxiWeb="";if(!txtAuxiPagina.getText().equals(txtAuxiPagina.getLabel())){auxiWeb=txtAuxiPagina.getText();};
				Examen examen = new Examen(-1, txtNombreExamen.getText(), Integer.parseInt(txtCUPS.getText()), Integer.parseInt(txtDuracion.getText()), Integer.parseInt(txtNivel.getText()), muestra.getIdTipoMuestra(), -1,Integer.parseInt(txtStickers.getText()),proto.getIdProtocolo() , txtCodigoExamen.getText().toUpperCase(), bayer, remite,auxiWeb);
				
				llenarArrayItems();
				llenarArrayDias();
				
				if (conexion.getInstance().nuevoExamen(esta, examen, paraAgregar,dias)) {
					esta.principal.registrarAccion("Agregar nuevo examen: '" + examen.getNombre() + "'");
					limpiarItems();
				
			}else{
				lblincorrectos.setText("Error  en la conexion con la base de datos");
			}
		}}
	}

	public void modificarExamen() {
		if (txtCodigoExamen.getText().equals("Código") || elPprotocolo==null || txtNombreExamen.getText().equals("Nombre del examen") ||txtCUPS.getText().equals("C.U.P.S.")||cbTipodeMuestra.getSelectedIndex()==0||txtDuracion.getText().equals("Duración en días")||txtNivel.getText().equals("Nivel")||txtStickers.getText().equals("Stickers a imprimir")||(rdbtnSi.isSelected()==false&&rdbtnNo.isSelected()==false)||(chckbxLunes.isSelected()==false&&chckbxMartes.isSelected()==false&&chckbxMiercoles.isSelected()==false&&chckbxJueves.isSelected()==false&&chckbxViernes.isSelected()==false&&chckbxSabado.isSelected()==false&&chckbxDomingo.isSelected()==false)) {
			lblincorrectos.setText("Faltan campos por llenar para este examen");
			
			if (txtCodigoExamen.getText().equals("Código")){txtCodigoExamen.setForegroundd(Color.red);}else {txtCodigoExamen.setForegroundd(Color.BLACK);}
			if (elPprotocolo==null){panelProtocolo.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Color.RED), "Protocolo para este examen", TitledBorder.LEFT, TitledBorder.TOP, null, Color.RED));}else {panelProtocolo.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Protocolo para este examen", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));}
			if (txtNombreExamen.getText().equals("Nombre del examen")){txtNombreExamen.setForegroundd(Color.RED);}else {txtNombreExamen.setForegroundd(Color.black);}
			if (txtCUPS.getText().equals("C.U.P.S.")){txtCUPS.setForegroundd(Color.RED);}else {txtCUPS.setForegroundd(Color.black);}
			if (cbTipodeMuestra.getSelectedIndex()==0){cbTipodeMuestra.setForeground(Color.RED);}else {cbTipodeMuestra.setForeground(Color.black);}
			if (txtDuracion.getText().equals("Duración en dias")){txtDuracion.setForegroundd(Color.RED);}else {txtDuracion.setForegroundd(Color.black);}
			if (txtNivel.getText().equals("Nivel")){txtNivel.setForegroundd(Color.RED);}else {txtNivel.setForegroundd(Color.black);}
			if (	txtStickers.getText().equals("Stickers a imprimir")){txtStickers.setForegroundd(Color.RED);}else {txtStickers.setForegroundd(Color.black);}
			if (chckbxLunes.isSelected()==false&&chckbxMartes.isSelected()==false&&chckbxMiercoles.isSelected()==false&&chckbxJueves.isSelected()==false&&chckbxViernes.isSelected()==false&&chckbxSabado.isSelected()==false&&chckbxDomingo.isSelected()==false){lblSeProcesaLos.setForeground(Color.RED);}else {lblSeProcesaLos.setForeground(Color.black);}
			if (rdbtnSi.isSelected()==false&&rdbtnNo.isSelected()==false){lblSeRemite.setForeground(Color.RED);}else {lblSeRemite.setForeground(Color.black);}

			
			
		
				
		}else {
			if( faltanValores()) {
				lblIncorrectosItem.setText("Asigna un valor a cada tarifa manualmente o recalculando según tarifa principal");
				panel.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Color.red), "Tarifas para este examen", TitledBorder.LEFT, TitledBorder.TOP, null, Color.RED));

			} else {
				lblIncorrectosItem.setText("");
				panel.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Tarifas para este examen", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));

		
			lblincorrectos.setText("");
			lblIncorrectosItem.setText("");
			panelProtocolo.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, Colores.clrPrincipal), "Protocolo para este examen", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
			lblSeProcesaLos.setForeground(Colores.clrTextoInactivo);
			lblSeRemite.setForeground(Colores.clrTextoInactivo);

				String  bayer="-1";
				if (!txtCodigoBayer.getText().equals("Código Bayer")) bayer =txtCodigoBayer.getText().toUpperCase();
				
				int remite=0;
				if (rdbtnSi.isSelected()) {
					remite = 1;
				} 
				String[] ST =cbTipodeMuestra.getSelectedItem().toString().split(" • ");
				
				TipoMuestra  muestra = conexionBusqueda.getInstance().muestraXsigla(ST[1]);
				protocolo proto=conexionBusqueda.getInstance().protocoloXnombre(txtNombreProtocolo.getText());
				String auxiWeb="";if(!txtAuxiPagina.getText().equals(txtAuxiPagina.getLabel())){auxiWeb=txtAuxiPagina.getText();};
				Examen examen = new Examen(idExamen, txtNombreExamen.getText(), Integer.parseInt(txtCUPS.getText()), Integer.parseInt(txtDuracion.getText()), Integer.parseInt(txtNivel.getText()), muestra.getIdTipoMuestra(), idDias,Integer.parseInt(txtStickers.getText()),proto.getIdProtocolo() , txtCodigoExamen.getText().toUpperCase(), bayer, remite,auxiWeb);
				
				llenarArrayItems();
				llenarArrayDias();
				llenarArrayItemsModificar();
				
				if (conexion.getInstance().editarExamen(esta, examen, paraModificar,dias,codigoAntiguo)) {
					esta.principal.registrarAccion("Modificar examen: '" + examen.getNombre() + "'");
					dispose();
				
			}else{
				lblincorrectos.setText("Error  en la conexion con la base de datos");
			}
		}}
	}

	public void eliminarExamen() {
		if (idExamen==-1) {
			lblincorrectos.setText("No has escogido ningún examen aun");
			
	
		
				
		}else {
			lblIncorrectosItem.setText("");
	
			
			if (conexion.getInstance().eliminarExamen(idExamen)) {
				esta.principal.registrarAccion("Eliminación de  examen: '" + txtNombreExamen.getText() + "'");
				dispose();
			
		}else{
			lblincorrectos.setText("Error  en la conexion con la base de datos");
		}
	}
	}
	

	public void llenarArrayItems(){
		for (int i = 0; i < table.getRowCount(); i++) {
			int valor=0;
			int rUreg=0;
			int rFest=0;
			int rEspec=0;
			
			if (!table.getValueAt(i,2).toString().equals("")) valor= Integer.parseInt( table.getValueAt(i,2).toString());
			if (!table.getValueAt(i,3).toString().equals("")) rUreg= Integer.parseInt( table.getValueAt(i,3).toString());
			if (!table.getValueAt(i,4).toString().equals("")) rFest= Integer.parseInt( table.getValueAt(i,4).toString());
			if (!table.getValueAt(i,5).toString().equals("")) rEspec= Integer.parseInt( table.getValueAt(i,5).toString());
			
			itemTarifa IT=new itemTarifa(-1,-1, Integer.parseInt(table.getValueAt(i, 0).toString()),valor,rUreg,rFest,rEspec);
			paraAgregar.add(IT);
		}
	}
	
	public void llenarArrayItemsModificar(){
		for (int i = 0; i < table.getRowCount(); i++) {
			int valor=0;
			int rUreg=0;
			int rFest=0;
			int rEspec=0;
			
			if (!table.getValueAt(i,2).toString().equals("")) valor= Integer.parseInt( table.getValueAt(i,2).toString());
			if (!table.getValueAt(i,3).toString().equals("")) rUreg= Integer.parseInt( table.getValueAt(i,3).toString());
			if (!table.getValueAt(i,4).toString().equals("")) rFest= Integer.parseInt( table.getValueAt(i,4).toString());
			if (!table.getValueAt(i,5).toString().equals("")) rEspec= Integer.parseInt( table.getValueAt(i,5).toString());
			if (table.getValueAt(i, 0).equals(paraModificar.get(i).getCodTarifa()+"")) {
				itemTarifa IT=new itemTarifa(paraModificar.get(i).getIdItemTarifa(),idExamen, Integer.parseInt(table.getValueAt(i, 0).toString()),valor,rUreg,rFest,rEspec);
				paraModificar.set(i, IT);
			}
		
		}
	}
	
	public void llenarArrayDias(){
		
		if (chckbxLunes.isSelected())dias[0]=1;
		if (chckbxMartes.isSelected())dias[1]=1;
		if (chckbxMiercoles.isSelected())dias[2]=1;
		if (chckbxJueves.isSelected())dias[3]=1;
		if (chckbxViernes.isSelected())dias[4]=1;
		if (chckbxSabado.isSelected())dias[5]=1;
		if (chckbxDomingo.isSelected())dias[6]=1;
		
		
		}
	
	public void limpiarItems() {
		
		txtCodigoExamen.reiniciar();
		txtNombreExamen.reiniciar();
		txtCUPS.reiniciar();
		txtDuracion.reiniciar();
		txtStickers.reiniciar();
		txtNivel.reiniciar();
		txtCodigoBayer.reiniciar();
		txtCodigoProtocolo.reiniciar();
		txtNombreProtocolo.reiniciar();
		chckbxLunes.reiniciar();
		chckbxMartes.reiniciar();
		chckbxMiercoles.reiniciar();
		chckbxJueves.reiniciar();
		chckbxViernes.reiniciar();
		chckbxSabado.reiniciar();
		chckbxDomingo.reiniciar();
		rdbtnSi.reiniciar();
		rdbtnNo.reiniciar();
		BG.clearSelection();
		cbTipodeMuestra.reiniciar();
				
		TM = new MiTableModelTarifas(conexionCombos.getInstance().listaTarifasVacio(), new String[] { "ID", "Nombre de la tarifa", "Valor", "%  Urgencias", "% Festivos", "% Especial" });
		table.setModel(TM);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}

	public boolean  recalcular(ArrayList<Tarifa> tarifas){
		
		for (int i = 0; i < table.getRowCount(); i++) {
			for (int j = 0; j < tarifas.size(); j++) {
				Tarifa tarifa=tarifas.get(j);
				if (table.getValueAt(i, 0).toString().equals(tarifa.getIdTarifa()+"")) {
					if (table.getValueAt(0, 2).equals("")) {
						lblIncorrectosItem.setText("Para poder recalcular debes escribir un valor para la tarifa principal.");
						return false;
					} else {
						int recalculo=((((Integer.parseInt(table.getValueAt(0, 2).toString())*tarifa.getRecalculo())/100)+Integer.parseInt(table.getValueAt(0, 2).toString()))/100)*100;
								table.setValueAt(recalculo, i, 2);
					}
				
				} 
				
			}
			
		}
		return true;

	}
	
	public void reportarError(String error){
		lblincorrectos.setText(error);
	}
}
