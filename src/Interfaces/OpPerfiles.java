package Interfaces;

import interfaces_Modificadas.ClrCuadroDeTexto;
import interfaces_Modificadas.Colores;
import interfaces_Modificadas.MiTableModelNoEditable;
import interfaces_Modificadas.btnRedondo;
import interfaces_Modificadas.circuloUsuario;
import interfaces_Modificadas.clrComboBox;
import interfaces_Modificadas.clrLabel;
import interfaces_Modificadas.clrPanelBordes;
import interfaces_Modificadas.clrScrollBar;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import auxiliares.Examen;
import auxiliares.OTD;
import auxiliares.PerfilExamenes;
import auxiliares.TipoDato;
import auxiliares.Usuario;
import conexion.conexion;
import conexion.conexionBusqueda;
import conexion.conexionCombos;

import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;

public class OpPerfiles extends JDialog {
	private clrPanelBordes contentPane;
	private clrLabel lblincorrectos;
	private Principal principal;
	private Usuario usuario;
	private clrLabel lblinicio;
	private circuloUsuario btnRedondo_;
	private OpPerfiles esta;
	private int idtipo;
	private String nombreAntiguo;
	private ClrCuadroDeTexto txtNombreExamen;
	private btnRedondo btnEliminar;
	private btnRedondo btnAgregar;
	private btnRedondo btnCancelar;
	private btnRedondo btnGuardarYSalir;
	private lbExamenXid lb1;
	private lbExamenXnombre lb2;
	private lbPerfilesXcodigo lb3;
	private lbPerfilesXnombre lb4;
	private JScrollPane scrollPane;
	private JTable table;
	private MiTableModelNoEditable TM;
	private Examen examen;
	private ArrayList<Examen> paraEliminar = new ArrayList<Examen>();
	private ArrayList<Examen> paraAgregar = new ArrayList<Examen>();
	private String accion;
	private ClrCuadroDeTexto txtNombrePerfil;
	private ClrCuadroDeTexto txtCodPerfil;

	private ClrCuadroDeTexto txtCodExamen;
	private clrPanelBordes panelExamenes;
	
	private PerfilExamenes perfil;

	public OpPerfiles(Principal principal, Usuario usuario, String accion) {
		super(principal, true);
		this.principal = principal;
		this.usuario = usuario;
		this.esta = this;
		this.accion = accion;
		// this.setDefaultLookAndFeelDecorated(false);

		setBounds(100, 100, 709, 430);
		this.setUndecorated(true);
		this.setLocationRelativeTo(getParent());
		contentPane = new clrPanelBordes(true);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		if (this.accion.equals("Agregar")) {
			
			lblinicio = new clrLabel("Agregar perfil", 2, true);
			lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
			lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblinicio.setBounds(10, 11, 689, 28);
			contentPane.add(lblinicio);

			lblincorrectos = new clrLabel("", 1);
			lblincorrectos.setForeground(Colores.clrAlertaCamarada);
			lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
			lblincorrectos.setAlignmentX(0.5f);
			lblincorrectos.setBounds(10, 38, 689, 28);
			contentPane.add(lblincorrectos);

	

			btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50), 2);
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnCancelar.setBounds(362, 330, 136, 50);
			contentPane.add(btnCancelar);

			btnGuardarYSalir = new btnRedondo("Guardar y salir", new Rectangle(48, 172,121,50), 12);
			btnGuardarYSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					agregarPerfil();
				}
			});
			btnGuardarYSalir.setSelected(true);
			btnGuardarYSalir.setBounds(173, 330, 169, 50);
			contentPane.add(btnGuardarYSalir);

			scrollPane = new clrScrollBar();
			scrollPane.setBounds(347, 72, 340, 247);
			contentPane.add(scrollPane);

			TM = new MiTableModelNoEditable(new Object[][] {}, new String[] { "Código examen", "Nombre examen" });
			table = new JTable();
			table.setModel(TM);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
scrollPane.setViewportView(table);

			table.getColumnModel().getColumn(0).setPreferredWidth(150);
			table.getColumnModel().getColumn(1).setPreferredWidth(400);

			panelExamenes = new clrPanelBordes(false);
			panelExamenes.setLayout(null);
			panelExamenes.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) Colores.clrPrincipal), "Agregar perfiles", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
			panelExamenes.setBounds(10, 108, 332, 146);
			contentPane.add(panelExamenes);

			txtCodExamen = new ClrCuadroDeTexto(6,false,"C\u00F3digo de examen",1);
			txtCodExamen.setBounds(10, 21, 277, 25);
			txtCodExamen.addFocusListener(new FocusListener() {

				@Override
				public void focusLost(FocusEvent arg0) {
					lb1.setVisible(false);
				}

				@Override
				public void focusGained(FocusEvent arg0) {
					if (lb1==null) {
						lb1=new lbExamenXid(txtCodExamen, esta,panelExamenes, esta.principal);
					}
				}
			});
			panelExamenes.add(txtCodExamen);
			txtCodExamen.setFocusCycleRoot(false);

			txtNombreExamen = new ClrCuadroDeTexto(100,false,"Nombre del examen",1);
			txtNombreExamen.setBounds(10, 52, 277, 25);
			txtNombreExamen.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					lb2.setVisible(false);
					
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					if (lb2==null) {
						lb2=new lbExamenXnombre(txtNombreExamen, esta, panelExamenes, esta.principal);
					}
					
				}
			});
			panelExamenes.add(txtNombreExamen);
			txtNombreExamen.setFocusCycleRoot(false);

			btnEliminar = new btnRedondo("Eliminar", new Rectangle(237, 172,121,50), 5);
			btnEliminar.setBounds(168, 88, 136, 50);
			panelExamenes.add(btnEliminar);

			btnAgregar = new btnRedondo("Agregar", new Rectangle(48, 172,121,50), 4);
			btnAgregar.setBounds(20, 88, 138, 50);
			panelExamenes.add(btnAgregar);
			btnAgregar.setSelected(true);

			txtNombrePerfil = new ClrCuadroDeTexto(100,false,"Nombre del perfil",1);
			txtNombrePerfil.setFocusCycleRoot(false);
			txtNombrePerfil.setBounds(10, 72, 332, 25);
			contentPane.add(txtNombrePerfil);
			btnAgregar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					agregarAtabla();
				}
			});
			btnEliminar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					quitarDtabla();

				}
			});

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					btnGuardarYSalir.requestFocusInWindow();
				}
			});

			this.setVisible(true);
			
			
			} else {
			if (this.accion.equals("Modificar")) {
				


				lblinicio = new clrLabel("Modificar perfil", 2, true);
				lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
				lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
				lblinicio.setBounds(10, 11, 689, 28);
				contentPane.add(lblinicio);

				lblincorrectos = new clrLabel("", 1);
				lblincorrectos.setForeground(Colores.clrAlertaCamarada);
				lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
				lblincorrectos.setAlignmentX(0.5f);
				lblincorrectos.setBounds(10, 38, 689, 28);
				contentPane.add(lblincorrectos);

			

				btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50), 2);
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnCancelar.setBounds(362, 330, 136, 50);
				contentPane.add(btnCancelar);

				btnGuardarYSalir = new btnRedondo("Guardar y salir", new Rectangle(48, 172,121,50), 12);
				btnGuardarYSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						modificarPerfil();
					}
				});
				btnGuardarYSalir.setSelected(true);
				btnGuardarYSalir.setBounds(173, 330, 169, 50);
				contentPane.add(btnGuardarYSalir);

				scrollPane = new clrScrollBar();
				scrollPane.setBounds(347, 72, 340, 247);
				contentPane.add(scrollPane);

				TM = new MiTableModelNoEditable(new Object[][] {}, new String[] { "Código examen", "Nombre examen" });
				table = new JTable();
				table.setModel(TM);
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
scrollPane.setViewportView(table);

				table.getColumnModel().getColumn(0).setPreferredWidth(150);
				table.getColumnModel().getColumn(1).setPreferredWidth(400);

				panelExamenes = new clrPanelBordes(false);
				panelExamenes.setLayout(null);
				panelExamenes.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) Colores.clrPrincipal), "Agregar perfiles", TitledBorder.LEFT, TitledBorder.TOP, null, Colores.clrPrincipal));
				panelExamenes.setBounds(10, 160, 332, 146);
				contentPane.add(panelExamenes);

				txtCodExamen = new ClrCuadroDeTexto(6,false,"C\u00F3digo de examen", true);
				txtCodExamen.setBounds(10, 21, 312, 25);
				txtCodExamen.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						lb1.setVisible(false);
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						if (lb1==null) {
							lb1=new lbExamenXid(txtCodExamen, esta, panelExamenes, esta.principal);
						}
						
					}
				});
				panelExamenes.add(txtCodExamen);
				txtCodExamen.setFocusCycleRoot(false);

				txtNombreExamen = new ClrCuadroDeTexto(100,false,"Nombre del examen", true);
				txtNombreExamen.setBounds(10, 52, 312, 25);
				txtNombreExamen.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						lb2.setVisible(false);
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						if (lb2==null) {
							lb2=new lbExamenXnombre(txtNombreExamen, esta, panelExamenes, esta.principal);
						}
						
					}
				});
				panelExamenes.add(txtNombreExamen);
				txtNombreExamen.setFocusCycleRoot(false);

				btnEliminar = new btnRedondo("Eliminar", new Rectangle(237, 172,121,50), 5);
				btnEliminar.setBounds(168, 88, 136, 50);
				panelExamenes.add(btnEliminar);

				btnAgregar = new btnRedondo("Agregar", new Rectangle(48, 172,121,50), 4);
				btnAgregar.setBounds(20, 88, 138, 50);
				panelExamenes.add(btnAgregar);
				btnAgregar.setSelected(true);

				txtCodPerfil = new ClrCuadroDeTexto(11,true,"C\u00F3digo de perfil",true);
				txtCodPerfil.setFocusCycleRoot(false);
				txtCodPerfil.setBounds(20, 93, 317, 25);
				txtCodPerfil.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						lb3.setVisible(false);
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {

						if (lb3==null) {
							lb3=new lbPerfilesXcodigo(txtCodPerfil, esta, esta.principal);
						}
						
					}
				});
				contentPane.add(txtCodPerfil);

				 txtNombrePerfil = new ClrCuadroDeTexto(100,false,"Nombre del perfil",true,1);
				txtNombrePerfil.setFocusCycleRoot(false);
				txtNombrePerfil.setBounds(20, 124, 317, 25);
				txtNombrePerfil.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						lb4.setVisible(false);
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						if (lb4==null) {
							lb4=new lbPerfilesXnombre(txtCodPerfil, esta, esta.principal);
						}
						
					}
				});
				contentPane.add(txtNombrePerfil);
				btnAgregar.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						
						agregarAtabla();
					}
				});
				btnEliminar.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						quitarDtabla();

					}
				});

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						btnGuardarYSalir.requestFocusInWindow();
					}
				});

				this.setVisible(true);

			
				
				
				
			} else {

			}

		}

		
		
		
		

		
		
		
		
		
		

	}

	
	
	
	
	
	
	
	
	
	
	
	
	public void agregarAtabla() {
		if (examen == null) {
			lblincorrectos.setText("No examen para agregar");

		} else {
			Object[] auxi = { examen.getSigla(), examen.getNombre() };
			TM.addRow(auxi);

			paraAgregar.add(examen);
			examen = null;
			txtCodExamen.reiniciar();
			txtNombreExamen.reiniciar();
		}
	}

	public void quitarDtabla() {
		if (table.getSelectedRow() == -1) {
			lblincorrectos.setText("No has seleccionado ninguna de la tabla de examenes");
		} else {
			if (accion.equals("Agregar")) {
				paraAgregar.remove(table.getSelectedRow());

			}else{
				
				ArrayList<Examen> examenes=perfil.getExamenesDelPerfil();
				
				for (int i = 0; i < examenes.size(); i++) {
					Examen exa=examenes.get(i);
					if (table.getValueAt(table.getSelectedRow(), 0).equals(exa.getSigla())) {
						paraEliminar.add(exa);
					}else{
						for (int j = 0; j < paraAgregar.size(); j++) {
							Examen exaA=paraAgregar.get(j);
							if (exa.getSigla().equals(exaA.getSigla())) {
								paraAgregar.remove(j);
								j=paraAgregar.size()+1;
							}
						}
					}
				}
				Examen examenAuxi = conexionBusqueda.examenXcodigo("" + table.getValueAt(table.getSelectedRow(), 0));
			paraEliminar.add(examenAuxi);
				TM.removeRow(table.getSelectedRow());

			}
			

		}
	}

	public void llenarExamen(Examen examen) {
		this.examen = examen;
		txtCodExamen.setText(examen.getSigla());
		txtCodExamen.setForeground(Color.black);

		txtNombreExamen.setText(examen.getNombre());
		txtNombreExamen.setForeground(Color.black);
	}

	
	public void llenarPerfil(PerfilExamenes perfil){
		this.perfil=perfil;
		txtCodPerfil.setText(""+this.perfil.getIdPerfil());
		txtCodPerfil.setForeground(Color.BLACK);
		
		txtNombrePerfil.setText(perfil.getNombrePerfil());
		txtNombrePerfil.setForeground(Color.black);
		
		llenarTabla(perfil.getExamenesDelPerfil());
	}
	
	
	public void llenarTabla(ArrayList<Examen> examenesDelPerfil) {
		paraAgregar=new ArrayList<Examen>();
		paraEliminar=new ArrayList<Examen>();
		
		String[] auxiExamen=new String[2];
		TM = new MiTableModelNoEditable(new Object[][] {}, new String[] { "Código examen", "Nombre examen" });		
		table.setModel(TM);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(400);
		
		
		for (int i = 0; i < examenesDelPerfil.size(); i++) {
			Examen examen=examenesDelPerfil.get(i);
			
			auxiExamen[0]=examen.getSigla();
			auxiExamen[1]=examen.getNombre();
			
			TM.addRow(auxiExamen);
			
		}
		
	}

	public void agregarPerfil() {
		if (txtNombrePerfil.getText().equals(txtNombrePerfil.getLabel()) || paraAgregar.size() < 1) {
			if (txtNombrePerfil.getText().equals(txtNombrePerfil.getLabel())) {
				lblincorrectos.setText("No has introducido nombre para el perfil");
			}

			if (paraAgregar.size() < 1) {
				lblincorrectos.setText("No hay examenes para este perfil");
			}

		} else {

			lblincorrectos.setText("");
			PerfilExamenes PE = new PerfilExamenes(-1, txtNombrePerfil.getText(), paraAgregar);
			if (conexion.getInstance().nuevoPerfilExamenes(esta, PE)) {
				esta.principal.registrarAccion("Creación del perfil de examenes: " + txtNombrePerfil.getText());

				dispose();
			}else{
				lblincorrectos.setText("Error  en la conexion con la base de datos");
			}
		}

	}

	public void modificarPerfil() {
		if (txtNombrePerfil.getText().equals(txtNombrePerfil.getLabel())||table.getRowCount()<1) {
			if (txtNombrePerfil.getText().equals(txtNombrePerfil.getLabel())) {
				lblincorrectos.setText("No has introducido nombre para el perfil");
			}
			if (table.getRowCount()<1) {
				lblincorrectos.setText("No tienes ningun exmen pra este perfil");
			}

		} else {

			lblincorrectos.setText("");
			perfil.setNombrePerfil(txtNombrePerfil.getText());
			if (conexion.getInstance().editarPerfil(esta, perfil, paraEliminar, paraAgregar)) {
				esta.principal.registrarAccion("Modificación del perfil de examenes: " + perfil.getIdPerfil());

				dispose();
			}else{
				lblincorrectos.setText("Error  en la conexion con la base de datos");
			}
		}

	}
	public void reportarError(String error){
		lblincorrectos.setText(error);
	}

}
