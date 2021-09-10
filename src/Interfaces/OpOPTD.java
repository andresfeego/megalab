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

import auxiliares.OTD;
import auxiliares.TipoDato;
import auxiliares.Usuario;
import conexion.conexion;
import conexion.conexionBusqueda;
import conexion.conexionCombos;

public class OpOPTD extends JDialog{
	private clrPanelBordes contentPane;
	private clrLabel lblincorrectos;
	private Principal principal;
	private Usuario usuario;
	private clrLabel lblinicio;
	private circuloUsuario btnRedondo_;
	private OpOPTD esta;
	private int idtipo;
	private String nombreAntiguo;
	private clrComboBox cbTD;
	private ClrCuadroDeTexto txtOpcion;
	private btnRedondo btnEliminar;
	private btnRedondo btnAgregar;
	private btnRedondo btnCancelar;
	private btnRedondo btnGuardarYSalir;
	private lbTDXnombre lb1;
	private lbTDXSigla lb2;
	private JScrollPane scrollPane;
	private JTable table;
	private MiTableModelNoEditable TM;
	private ArrayList<OTD> paraEliminar=new ArrayList<OTD>();
	private ArrayList<OTD> paraAgregar=new ArrayList<OTD>();
	
	
	public OpOPTD(Principal principal, Usuario usuario) {
		super(principal,true);
		this.principal=principal;
		this.usuario=usuario;
		this.esta=this;

		//this.setDefaultLookAndFeelDecorated(false);


		setBounds(100, 100, 709, 430);
		this.setUndecorated(true);
		this.setLocationRelativeTo(getParent()); 
		contentPane = new clrPanelBordes(true);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		


		




		lblinicio = new clrLabel("Opciones de tipos de datos",2,true);
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
		
	
		
		cbTD = new clrComboBox(conexionCombos.getInstance().listaTD(),0);
		cbTD.setBounds(44, 77, 277, 20);
		cbTD.setFocusCycleRoot(false);
		cbTD.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				if (cbTD.getSelectedIndex()!=0) {
					llenarTabla(cbTD.getSelectedItem().toString());
				}
			}
		});
		contentPane.add(cbTD);
		
		btnEliminar = new btnRedondo("Eliminar", new Rectangle(237, 172,121,50),5);
		btnEliminar.setBounds(192, 173, 136, 50);
		btnEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				quitarDtabla();
				
			}
		});
		contentPane.add(btnEliminar);
		
		btnAgregar = new btnRedondo("Agregar", new Rectangle(48, 172,121,50),4);
		btnAgregar.setSelected(true);
		btnAgregar.setBounds(44, 173, 138, 50);
		btnAgregar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				agregarAtabla();
			}
		});
		contentPane.add(btnAgregar);
		
		txtOpcion = new ClrCuadroDeTexto(100,false,"Opción");
		txtOpcion.setFocusCycleRoot(false);
		txtOpcion.setBounds(44, 108, 277, 20);
		contentPane.add(txtOpcion);
		
		btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50),2);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(362, 330, 136, 50);
		contentPane.add(btnCancelar);
		
		btnGuardarYSalir = new btnRedondo("Guardar y salir", new Rectangle(48, 172,121,50),12);
		btnGuardarYSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardarDatos();
			}
		});
		btnGuardarYSalir.setSelected(true);
		btnGuardarYSalir.setBounds(173, 330, 169, 50);
		contentPane.add(btnGuardarYSalir);
		
		scrollPane = new clrScrollBar();
		scrollPane.setBounds(347, 72, 340, 225);
		contentPane.add(scrollPane);
		
		TM = new MiTableModelNoEditable(new Object[][] {},new String[] {"ID","Opción"});
		table = new JTable();
		table.setModel(TM);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
scrollPane.setViewportView(table);
		
		SwingUtilities.invokeLater( new Runnable() 
        { 
        public void run() 
            { 
            btnGuardarYSalir.requestFocusInWindow(); 
        } 
    }); 
		
		this.setVisible(true);
	
		
	
		
			

		
	}
	
	



	public void agregarAtabla(){
		if (txtOpcion.getText().equals("Opción")||cbTD.getSelectedIndex()==0) {
			lblincorrectos.setText("No hay nada para agregar");
			
		} else {
			Object[] auxi={" • ",txtOpcion.getText()};
			TM.addRow(auxi);
			
			String[] ST=cbTD.getSelectedItem().toString().split(" • ");
			TipoDato TDauxi=conexionBusqueda.getInstance().TDXnombre(ST[0]);
			OTD OTDauxi=new OTD(0, txtOpcion.getText(), TDauxi.getIdTD());
			paraAgregar.add(OTDauxi);
			
			txtOpcion.setText("Opción");
			txtOpcion.setForeground(Colores.clrTextoInactivo);
		}
	}
	
	
	public void quitarDtabla(){
		if (table.getSelectedRow()==-1) {
			lblincorrectos.setText("No has seleccionado ninguna opción");
		} else {
			String[] ST=cbTD.getSelectedItem().toString().split(" • ");
			TipoDato TDauxi=conexionBusqueda.getInstance().TDXnombre(ST[0]);
			int idauxi=Integer.parseInt(""+TM.getValueAt(table.getSelectedRow(), 0));
			String opcionAuxi=TM.getValueAt(table.getSelectedRow(), 1)+"";
			OTD OTDauxi=new OTD(idauxi, opcionAuxi, TDauxi.getIdTD());
			paraEliminar.add(OTDauxi);
			TM.removeRow(table.getSelectedRow());


		}
	}
	
	
	public void llenarTabla(String nombreTD){
		String[] ST=nombreTD.split(" • ");
		
		TM = new MiTableModelNoEditable(conexionCombos.getInstance().listaOPTD(ST[0]), new String[] {"ID","Opción"});
		table.setModel(TM);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
	
	
	public void guardarDatos(){
		if (conexion.getInstance().editarOPTD(esta, paraAgregar, paraEliminar)) {
			esta.principal.registrarAccion("Modificación de opciones de tipos de datos");

			dispose();
		}else{
			lblincorrectos.setText("Error  en la conexion con la base de datos");
		}
		
	}
	

	public void reportarError(String error){
		lblincorrectos.setText(error);
	}

}
