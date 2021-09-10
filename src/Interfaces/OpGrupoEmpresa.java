package Interfaces;

import interfaces_Modificadas.ClrCuadroDeTexto;
import interfaces_Modificadas.Colores;
import interfaces_Modificadas.btnRedondo;
import interfaces_Modificadas.circuloUsuario;
import interfaces_Modificadas.clrCheckBox;
import interfaces_Modificadas.clrComboBox;
import interfaces_Modificadas.clrLabel;
import interfaces_Modificadas.clrPanelBordes;
import interfaces_Modificadas.clrScrollBar;
import interfaces_Modificadas.clrtextpane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.SwingConstants;

import ago.beans.CirclePanel;
import auxiliares.Bacteriologo;
import auxiliares.GruposEmpresas;
import auxiliares.Medico;
import auxiliares.Paciente;
import auxiliares.Usuario;
import conexion.conexion;
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
import javax.swing.SwingUtilities;

public class OpGrupoEmpresa extends JDialog{
	private clrPanelBordes contentPane;
	private clrLabel lblincorrectos;
	private Principal principal;
	private Usuario usuario;
	private clrLabel lblinicio;
	private circuloUsuario btnRedondo_;
	private String accion; 
	private OpGrupoEmpresa esta;
	private int idgrupo=-1;
	private String nombreAntiguo;
	
	private clrComboBox cbCodEmpresa;
	private ClrCuadroDeTexto txtNombreGrupo;
	private clrComboBox cbCodTarifa;
	private btnRedondo btnCancelar;
	private btnRedondo btnAceptar;
	private lbGrupoEXnombre lb1;
	
	public OpGrupoEmpresa(Principal principal, Usuario usuario, String Accion) {
		super(principal,true);
		this.principal=principal;
		this.usuario=usuario;
		this.accion=Accion;
		this.esta=this;




		setBounds(100, 100, 404, 274);
		this.setUndecorated(true);
		this.setLocationRelativeTo(getParent()); 
		contentPane = new clrPanelBordes(true);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		


		
			

		
		
	
		
		
		
		
	

		
		
		
		if(accion.equals("Agregar")){

			lblinicio = new clrLabel("Ingresar Sub Grupo de empresa",2,true);
			lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
			lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblinicio.setBounds(10, 11, 384, 28);
			contentPane.add(lblinicio);
					
			lblincorrectos = new clrLabel("", 1);
			lblincorrectos.setForeground(Colores.clrAlertaCamarada);
			lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
			lblincorrectos.setAlignmentX(0.5f);
			lblincorrectos.setBounds(10, 38, 384, 28);
			contentPane.add(lblincorrectos);
			
			
			
			txtNombreGrupo = new ClrCuadroDeTexto(100,false,"Nombre del grupo",1);
			txtNombreGrupo.setBounds(46, 108, 308, 20);
			txtNombreGrupo.setFocusCycleRoot(false);
			contentPane.add(txtNombreGrupo);
			
			cbCodEmpresa = new clrComboBox(conexionCombos.getInstance().listaEmpresas(),1);
			cbCodEmpresa.setBounds(45, 77, 309, 20);
			contentPane.add(cbCodEmpresa);
			
			btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50),2);
			btnCancelar.setBounds(204, 170, 136, 50);
			btnCancelar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					
				}
			});
			contentPane.add(btnCancelar);
			
			btnAceptar = new btnRedondo("Ingresar", new Rectangle(48, 172,121,50),1);
			btnAceptar.setSelected(true);
			btnAceptar.setBounds(56, 170, 138, 50);
			btnAceptar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					ingresarGrupoEmpresa();
					
				}
			});
			contentPane.add(btnAceptar);
			
			
			cbCodTarifa = new clrComboBox(conexionCombos.getInstance().listaTarifas(),1);
			cbCodTarifa.setBounds(46, 139, 308, 20);
			contentPane.add(cbCodTarifa);
			
			SwingUtilities.invokeLater( new Runnable() 
	        { 
	        public void run() 
	            { 
	            btnAceptar.requestFocusInWindow(); 
	        } 
	    }); 
			
			this.setVisible(true);
		
			
		}else{
			if (accion.equals("Modificar")) {

				lblinicio = new clrLabel("Modificar Sub Grupo de empresa",2,true);
				lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
				lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
				lblinicio.setBounds(10, 11, 384, 28);
				contentPane.add(lblinicio);
						
				lblincorrectos = new clrLabel("", 1);
				lblincorrectos.setForeground(Colores.clrAlertaCamarada);
				lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
				lblincorrectos.setAlignmentX(0.5f);
				lblincorrectos.setBounds(10, 38, 384, 28);
				contentPane.add(lblincorrectos);
				
	
				
				txtNombreGrupo = new ClrCuadroDeTexto(100,false,"Nombre del grupo",true,1);
				txtNombreGrupo.setBounds(46, 108, 308, 20);
				txtNombreGrupo.setFocusCycleRoot(false);
				txtNombreGrupo.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent arg0) {
						
						lb1.setVisible(false);
					}
					
					@Override
					public void focusGained(FocusEvent arg0) {
						
						if (lb1==null) {
							lb1= new lbGrupoEXnombre(txtNombreGrupo, esta, esta.principal);
						}
					}
				});
				contentPane.add(txtNombreGrupo);
				
				cbCodEmpresa = new clrComboBox(conexionCombos.getInstance().listaEmpresas(),1);
				cbCodEmpresa.setBounds(45, 77, 309, 20);
				contentPane.add(cbCodEmpresa);
				
				btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50),2);
				btnCancelar.setBounds(204, 170, 136, 50);
				btnCancelar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						
					}
				});
				contentPane.add(btnCancelar);
				
				btnAceptar = new btnRedondo("Modificar", new Rectangle(48, 172,121,50),3);
				btnAceptar.setSelected(true);
				btnAceptar.setBounds(56, 170, 138, 50);
				btnAceptar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
					modificarGrupoEmpresa();
						
					}
				});
				contentPane.add(btnAceptar);
				
				
				cbCodTarifa = new clrComboBox(conexionCombos.getInstance().listaTarifas(),1);
				cbCodTarifa.setBounds(46, 139, 308, 20);
				contentPane.add(cbCodTarifa);
				
				SwingUtilities.invokeLater( new Runnable() 
		        { 
		        public void run() 
		            { 
		            btnAceptar.requestFocusInWindow(); 
		        } 
		    }); 
				
				this.setVisible(true);
			
					
			} else {

				lblinicio = new clrLabel("Eliminar Sub Grupo de empresa",2,true);
				lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
				lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
				lblinicio.setBounds(10, 11, 384, 28);
				contentPane.add(lblinicio);
						
				lblincorrectos = new clrLabel("", 1);
				lblincorrectos.setForeground(Colores.clrAlertaCamarada);
				lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
				lblincorrectos.setAlignmentX(0.5f);
				lblincorrectos.setBounds(10, 38, 384, 28);
				contentPane.add(lblincorrectos);
				
		
				
				txtNombreGrupo = new ClrCuadroDeTexto(100,false,"Nombre del grupo",true);
				txtNombreGrupo.setBounds(46, 108, 308, 20);
				txtNombreGrupo.setFocusCycleRoot(false);
				txtNombreGrupo.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent arg0) {
						
						lb1.setVisible(false);
					}
					
					@Override
					public void focusGained(FocusEvent arg0) {
						
						if (lb1==null) {
							lb1= new lbGrupoEXnombre(txtNombreGrupo, esta, esta.principal);
						}
					}
				});
				contentPane.add(txtNombreGrupo);
				
				cbCodEmpresa = new clrComboBox(conexionCombos.getInstance().listaEmpresas(),1);
				cbCodEmpresa.setBounds(45, 77, 309, 20);
				contentPane.add(cbCodEmpresa);
				
				btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50),2);
				btnCancelar.setBounds(204, 170, 136, 50);
				btnCancelar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						
					}
				});
				contentPane.add(btnCancelar);
				
				btnAceptar = new btnRedondo("Eliminar", new Rectangle(48, 172,121,50),5);
				btnAceptar.setSelected(true);
				btnAceptar.setBounds(56, 170, 138, 50);
				btnAceptar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
					eliminarGrupoEmpresa();
						
					}
				});
				contentPane.add(btnAceptar);
				
				
				cbCodTarifa = new clrComboBox(conexionCombos.getInstance().listaTarifas(),1);
				cbCodTarifa.setBounds(46, 139, 308, 20);
				contentPane.add(cbCodTarifa);
				
				SwingUtilities.invokeLater( new Runnable() 
		        { 
		        public void run() 
		            { 
		            btnAceptar.requestFocusInWindow(); 
		        } 
		    }); 
				
				this.setVisible(true);
			
				
			}
		}
		
	}
	
	public void ingresarGrupoEmpresa(){
		
		
		
		if (cbCodEmpresa.getSelectedIndex()==0||cbCodTarifa.getSelectedIndex()==0||txtNombreGrupo.getText().equals("Nombre del grupo")) {
			
			lblincorrectos.setText("Faltan campos por llenar");
		} else {
			
			lblincorrectos.setText("");

			GruposEmpresas grupoEmpresa=new GruposEmpresas(0, txtNombreGrupo.getText(), cbCodEmpresa.getSelectedItem().toString(), cbCodTarifa.getSelectedItem().toString());
			
			conexion cone=conexion.getInstance();
			
			if (cone.nuevoGrupoEmpresa(esta, grupoEmpresa)) {
				esta.principal.registrarAccion("Creacion de sub grupo de empresa '"+txtNombreGrupo.getText()+"'");
				dispose();
			}else{
				lblincorrectos.setText("Error  en la conexion con la base de datos");
			}
			
	
				}
		
	}
	
	
public void modificarGrupoEmpresa(){
		
		
		
		if (cbCodEmpresa.getSelectedIndex()==0||cbCodTarifa.getSelectedIndex()==0||txtNombreGrupo.getText().equals("Nombre del grupo")) {
			
			lblincorrectos.setText("Faltan campos por llenar");
		} else {
			lblincorrectos.setText("");

			GruposEmpresas grupoEmpresa=new GruposEmpresas(idgrupo, txtNombreGrupo.getText(), cbCodEmpresa.getSelectedItem().toString(), cbCodTarifa.getSelectedItem().toString());

			conexion cone=conexion.getInstance();
			
			if (cone.editarGrupoEmpresa(esta,grupoEmpresa,nombreAntiguo)) {
				esta.principal.registrarAccion("Modificación de sub grupo de empresa '"+txtNombreGrupo.getText()+"'");
				dispose();
			}else{
				lblincorrectos.setText("Error  en la conexion con la base de datos");
			}
			

				}
		
		
	}

public void eliminarGrupoEmpresa(){
	
	
	
	if (idgrupo==-1) {
		
		lblincorrectos.setText("No has escogido ningún sub grupo de empresas aun");
	} else {			
		
		
		conexion cone=conexion.getInstance();
		
		if (cone.eliminarGrupoEmpresa(idgrupo)) {
			esta.principal.registrarAccion("Eliminación de sub grupo de empresa '"+txtNombreGrupo.getText()+"'");
			dispose();
		}else{
			lblincorrectos.setText("Error  en la conexion con la base de datos");
		}
		
		
			}
	
	
}


	
	
	public String calculaFecha(Date FechaNac){
		Calendar fechaActual = Calendar.getInstance();
        Calendar FechaNacimi = Calendar.getInstance();


		 if(FechaNac!=null){
		        FechaNacimi.setTime(FechaNac);

			 if(fechaActual.getTimeInMillis()<FechaNacimi.getTimeInMillis()){
				 return "error - fecha exedida";
			 }else{
	        //Se restan la fecha actual y la fecha de nacimiento
	        int aos = fechaActual.get(Calendar.YEAR)- FechaNacimi.get(Calendar.YEAR);
	        int mes =fechaActual.get(Calendar.MONTH)- FechaNacimi.get(Calendar.MONTH);
	        int dia = fechaActual.get(Calendar.DATE)- FechaNacimi.get(Calendar.DATE);
	        //Se ajusta el año dependiendo el mes y el día
	        if(mes<0 || (mes==0 && dia<0)){
	            aos--;
	        }
	        
	        if (dia<0||mes==0&&dia<0) {
				mes--;
			}
	        
	        if (aos>=5) {
	        	return aos+" Años";
			} else {
				if (aos<5&&aos>=1) {

					String meses="";
					if (mes<0)meses= 12+(mes)+"- Meses";
					if (mes>0)meses= (mes)+"- Meses";
					
					return aos+" Años "+meses;
				} else {
					if (aos<1&&mes!=0) {

						String meses="";
						if (mes<0)meses= 12+(mes)+" Meses";
						if (mes>0)meses= (mes)+" Meses";
							
							return meses;
					} else {
						return fechaActual.get(Calendar.DAY_OF_YEAR)-FechaNacimi.get(Calendar.DAY_OF_YEAR)+" Dias de nacido";
					}
				
			}
		}
	        
	        
	        
	      
			 }
		 }
	        else{
			 return "Sin fecha de nacimiento";
			 
		 }
	}

	
	
	public void llenar(GruposEmpresas grupoEmpresa){
		idgrupo=grupoEmpresa.getIdGrupo();
		txtNombreGrupo.setText(grupoEmpresa.getNombreGrupo());
		cbCodEmpresa.setSelectedItem(grupoEmpresa.getEmpresa());
		cbCodTarifa.setSelectedItem(grupoEmpresa.getTarifa());
		
		nombreAntiguo=grupoEmpresa.getNombreGrupo();
		
	}
	
	public void reportarError(String error){
		lblincorrectos.setText(error);
	}
}
