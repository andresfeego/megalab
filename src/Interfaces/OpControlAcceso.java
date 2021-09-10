package Interfaces;

import interfaces_Modificadas.ClrCuadroDeTexto;
import interfaces_Modificadas.Colores;
import interfaces_Modificadas.btnRedondo;
import interfaces_Modificadas.circuloUsuario;
import interfaces_Modificadas.clrBarraMenu;
import interfaces_Modificadas.clrCheckBox;
import interfaces_Modificadas.clrComboBox;
import interfaces_Modificadas.clrFrameBordes;
import interfaces_Modificadas.clrLabel;
import interfaces_Modificadas.clrPanel;
import interfaces_Modificadas.clrPanelBordes;
import interfaces_Modificadas.clrPassword;
import interfaces_Modificadas.clrScrollBar;
import interfaces_Modificadas.clrToogleBoton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.AbstractListModel;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;

import javax.swing.JLabel;

import java.awt.Component;

import javax.swing.SwingConstants;

import conexion.conexion;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

import sun.awt.RequestFocusController;
import ago.beans.CirclePanel;
import auxiliares.Usuario;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.Choice;
import interfaces_Modificadas.clrSeparador;

public class OpControlAcceso extends JDialog {

	private clrPanelBordes contentPane;
	private ClrCuadroDeTexto txtNombres;
	private clrLabel lblincorrectos;
	private Principal principal;
	private Usuario usuario;
	private clrLabel lblinicio;
	private btnRedondo btnAceptar;
	private btnRedondo btnCancelar;
	private ClrCuadroDeTexto txtusuario;
	private ClrCuadroDeTexto passPrimera;
	private circuloUsuario btnRedondo_;
	private clrLabel clrLabel__1;
	private clrComboBox cbTiempo;
	private CirclePanel circlePanel;
	private clrCheckBox chbActivo;
	private OpControlAcceso esta;
	private lbUsuarioXusuario lb1;
	private lbUsuarioXnombre lb;
	private int estado=-1;
	private clrLabel lblAdministracin;
	private 	clrCheckBox chkUsuarios ;
	private clrCheckBox chkPersonas;
	private clrCheckBox chkEmpresa;
	private 	clrCheckBox chkProcedimientos;
	private clrCheckBox chkLaboratorios;
	private clrCheckBox chkGeograico;
	private clrCheckBox chkOtrosAdmin;
	private clrCheckBox chkInformes;
	private clrCheckBox chkOtrosInformes;
	private clrCheckBox chkListados;
	private clrCheckBox chkFactura;
	private clrCheckBox chkCartera;
	private clrCheckBox chkLaboratorio;
	private clrCheckBox chkImpresoras;
	private clrCheckBox chkCambios;
	private Usuario usuarioEdicion;

	/**
	 * Create the frame.
	 */
	public OpControlAcceso(Principal pri ,Usuario usuario) {
		super(pri,true);
		System.out.println();
		this.usuario=usuario;
		esta=this;
		this.principal=pri;
		setRootPaneCheckingEnabled(false);
		


		setBounds(100, 100, 389, 707);
		this.setUndecorated(true);
		this.setLocationRelativeTo(getParent()); 
		contentPane = new clrPanelBordes(true);
		contentPane.setLayout(null);
		lblinicio = new clrLabel("Control de acceso a usuarios",2,true);
		lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblinicio.setBounds(10, 11, 369, 28);
		
		contentPane.add(lblinicio);
		
		
		 txtusuario = new ClrCuadroDeTexto(50,false,"Usuario",true);
		 txtusuario.setBounds(41, 117, 306, 28);
		 contentPane.add(txtusuario);
		 txtusuario.addFocusListener(new FocusListener() {
				@Override
				public void focusLost(FocusEvent e) {
					lb1.setVisible(false);
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					if (lb1==null) {
						lb1=new lbUsuarioXusuario(txtusuario, esta,principal);
					}else {
						lb1.setVisible(true);
					}

				}
			});
		 				
		 
		 
		 btnAceptar = new btnRedondo("Guardar",new Rectangle(48, 172,121,50),1);
		 btnAceptar.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {

					if (estado==-1) {
						lblincorrectos.setText("No se ha escogido ningun usuario");
					} else {
						if(conexion.acceso(esta.getUsuario().getUsuario())!=null){
						if (estado==1) {
							if (conexion.getInstance().activaciones(esta.usuario, "Activar")) {
								principal.registrarAccion("Activacion de usuario: "+esta.getUsuario().getUsuario());
								dispose();
							}else{
								lblincorrectos.setText("Error  en la conexion con la base de datos");
							}
						} else {
							if (conexion.getInstance().activaciones(esta.usuario, "Desactivar")) {
								principal.registrarAccion("Desactivacion de usuario: "+esta.getUsuario().getUsuario());
								dispose();
							}
							else{
								lblincorrectos.setText("Error  en la conexion con la base de datos");
							}
						}

					}}
				
				} 
				
if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
	dispose();
} 
			}
		
		});
		 btnAceptar.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if (estado==-1) {
					lblincorrectos.setText("No se ha escogido ningun usuario");
				} else {
					if (estado==1) {
						if (conexion.getInstance().activaciones(esta.usuario, "Activar")) {
							principal.registrarAccion("Activacion de usuario: "+esta.getUsuario().getUsuario());
							dispose();
						}
					} else {
						if (conexion.getInstance().activaciones(esta.usuario, "Desactivar")) {
							principal.registrarAccion("Desactivacion de usuario: "+esta.getUsuario().getUsuario());
							dispose();
						}else{
							lblincorrectos.setText("Error  en la conexion con la base de datos");
						}
					}

				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}
		});
		 btnAceptar.setSelected(true);
		 btnAceptar.setBounds(41, 639, 135, 50);
		 contentPane.add(btnAceptar);
		
		
		
		btnCancelar = new btnRedondo("Cancelar",new Rectangle(237, 172,121,50),2);
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(lb!=null)lb.setVisible(false);
				if(lb1!=null)lb1.setVisible(false);
				
				dispose();
			}
		});
		btnCancelar.setBounds(218, 639, 129, 50);
		contentPane.add(btnCancelar);
		setContentPane(contentPane);
		
		getRootPane().setDefaultButton(btnAceptar);
		
		lblincorrectos = new clrLabel("", 1);
		lblincorrectos.setForeground(Colores.clrAlertaCamarada);
		lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
		lblincorrectos.setAlignmentX(0.5f);
		lblincorrectos.setBounds(10, 39, 358, 28);
		contentPane.add(lblincorrectos);
		
	
		
		txtNombres = new ClrCuadroDeTexto(75,false,"Nombre completo",true);
		txtNombres.setBounds(41, 78, 306, 28);
		txtNombres.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				lb.setVisible(false);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				
				if (lb==null) {
				lb=new lbUsuarioXnombre(txtNombres, esta,principal);
				}else {
					lb.setVisible(true);
				}

			}
		});
		
		contentPane.add(txtNombres);
		
		clrSeparador clrSeparador_ = new clrSeparador();
		clrSeparador_.setBounds(10, 153, 369, 2);
		contentPane.add(clrSeparador_);
		
		lblAdministracin = new clrLabel("Administraci\u00F3n",2);
		lblAdministracin.setBounds(20, 153, 249, 25);
		lblAdministracin.setForeground(Colores.clrPrincipal);
		contentPane.add(lblAdministracin);
		
		clrSeparador clrSeparador__1 = new clrSeparador();
		clrSeparador__1.setBounds(10, 176, 369, 2);
		contentPane.add(clrSeparador__1);
		
		chkUsuarios = new clrCheckBox("Usuarios");
		chkUsuarios.setBounds(30, 189, 129, 25);
		contentPane.add(chkUsuarios);
		
		chkPersonas = new clrCheckBox("Personas");
		chkPersonas.setBounds(30, 225, 89, 25);
		contentPane.add(chkPersonas);
		
		chkEmpresa = new clrCheckBox("Empresas");
		chkEmpresa.setBounds(30, 261, 102, 25);
		contentPane.add(chkEmpresa);
		
		chkProcedimientos = new clrCheckBox("Procedimientos");
		chkProcedimientos.setBounds(30, 297, 143, 25);
		contentPane.add(chkProcedimientos);
		
		chkLaboratorios = new clrCheckBox("Laboratorios");
		chkLaboratorios.setBounds(192, 189, 143, 25);
		contentPane.add(chkLaboratorios);
		
		chkGeograico = new clrCheckBox("Geografico");
		chkGeograico.setBounds(192, 225, 109, 25);
		contentPane.add(chkGeograico);
		
		chkOtrosAdmin = new clrCheckBox("Otros");
		chkOtrosAdmin.setBounds(192, 261, 89, 25);
		contentPane.add(chkOtrosAdmin);
		
		clrSeparador clrSeparador__2 = new clrSeparador();
		clrSeparador__2.setBounds(10, 333, 369, 2);
		contentPane.add(clrSeparador__2);
		
		clrLabel clrLabel_ = new clrLabel("Informes y listados", 2);
		clrLabel_.setForeground(Colores.clrPrincipal);
		clrLabel_.setBounds(20, 333, 249, 25);
		contentPane.add(clrLabel_);
		
		clrSeparador clrSeparador__3 = new clrSeparador();
		clrSeparador__3.setBounds(10, 356, 369, 2);
		contentPane.add(clrSeparador__3);
		
		chkInformes = new clrCheckBox("Informes");
		chkInformes.setBounds(30, 369, 89, 25);
		contentPane.add(chkInformes);
		
		chkOtrosInformes = new clrCheckBox("otros");
		chkOtrosInformes.setBounds(192, 369, 143, 25);
		contentPane.add(chkOtrosInformes);
		
		chkListados = new clrCheckBox("Listados");
		chkListados.setBounds(30, 405, 89, 25);
		contentPane.add(chkListados);
		
		clrSeparador clrSeparador__4 = new clrSeparador();
		clrSeparador__4.setBounds(10, 441, 369, 2);
		contentPane.add(clrSeparador__4);
		
		clrSeparador clrSeparador__5 = new clrSeparador();
		clrSeparador__5.setBounds(10, 464, 369, 2);
		contentPane.add(clrSeparador__5);
		
		clrLabel clrLabel__2 = new clrLabel("Contabilidad", 2);
		clrLabel__2.setForeground(Colores.clrPrincipal);
		clrLabel__2.setBounds(20, 441, 249, 25);
		contentPane.add(clrLabel__2);
		
		chkFactura = new clrCheckBox("Facturaci\u00F3n");
		chkFactura.setBounds(192, 477, 143, 25);
		contentPane.add(chkFactura);
		
		chkCartera = new clrCheckBox("Cartera");
		chkCartera.setBounds(30, 477, 89, 25);
		contentPane.add(chkCartera);
		
		clrSeparador clrSeparador__6 = new clrSeparador();
		clrSeparador__6.setBounds(10, 513, 369, 2);
		contentPane.add(clrSeparador__6);
		
		clrSeparador clrSeparador__7 = new clrSeparador();
		clrSeparador__7.setBounds(10, 536, 369, 2);
		contentPane.add(clrSeparador__7);
		
		clrLabel clrLabel__3 = new clrLabel("Personalizar", 2);
		clrLabel__3.setForeground(Colores.clrPrincipal);
		clrLabel__3.setBounds(20, 513, 249, 25);
		contentPane.add(clrLabel__3);
		
		chkLaboratorio = new clrCheckBox("Laboratorio");
		chkLaboratorio.setBounds(192, 549, 143, 25);
		contentPane.add(chkLaboratorio);
		
		chkImpresoras = new clrCheckBox("Impresoras");
		chkImpresoras.setBounds(30, 549, 129, 25);
		contentPane.add(chkImpresoras);
		
		chkCambios = new clrCheckBox("Cambios");
		chkCambios.setBounds(30, 585, 89, 25);
		contentPane.add(chkCambios);
		
		
		
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				guardar();
				
			}

			
		});
		
		SwingUtilities.invokeLater( new Runnable() 
        { 
        public void run() 
            { 
            btnAceptar.requestFocusInWindow(); 
        } 
    }); 
			
		this.setVisible(true);
		

		
	}
	
	

	
	
	
	
	
	

	public void llenar(Usuario usu) {
		usuarioEdicion=usu;
		txtusuario.setText(usu.getUsuario());
		txtusuario.setForeground(Color.black);
		
		txtNombres.setText(usu.getNombre());
		txtNombres.setForeground(Color.black);
		for (int i = 3; i <= 17; i++) {
			System.out.println("llenando i= "+i+ "  " + usu.getAcceso().charAt(i));
			if (usu.getAcceso().charAt(i-1)=='1') {
				switch (i) {
				case 3:
					chkUsuarios.setSelected(true);
					break;
					
				case 4:
					chkPersonas.setSelected(true);
					break;
					
				case 5:
					chkEmpresa.setSelected(true);
					break;
					
				case 6:
					chkProcedimientos.setSelected(true);
					break;
					
				case 7:
					chkLaboratorios.setSelected(true);
					break;
					
				case 8:
					chkGeograico.setSelected(true);
					break;
					
				case 9:
					chkOtrosAdmin.setSelected(true);
					break;
					
				case 10:
					chkInformes.setSelected(true);
					break;
					
				case 11:
					chkListados.setSelected(true);
					break;
					
				case 12:
					chkOtrosInformes.setSelected(true);
					break;
					
				case 13:
					chkCartera.setSelected(true);
					break;
					
				case 14:
					chkFactura.setSelected(true);
					break;
					
				case 15:
					chkImpresoras.setSelected(true);
					break;
					
				case 16:
					chkCambios.setSelected(true);
					break;
					
				case 17:
					chkLaboratorio.setSelected(true);
					break;
					
		
					

				default:
					break;
				}
			}
		}
		
	}

	public void guardar(){
		if (usuarioEdicion==null) {
			lblincorrectos.setText("No has escogido ningún usuario");
		} else {
			char[] temp=usuarioEdicion.getAcceso().toCharArray();
			
			for (int i = 3; i <= 17; i++) {
				System.out.println("i = "+i+" temp = "+temp[i]);
				switch (i) {
				case 3:
					if (chkUsuarios.isSelected()) {
						temp[i-1]='1';
					}else{
						temp[i-1]='0';
					}
					break;
					
				case 4:
					if (chkPersonas.isSelected()) {
						temp[i-1]='1';
					}else{
						temp[i-1]='0';
					}
					break;
					
				case 5:
					if (chkEmpresa.isSelected()) {
						temp[i-1]='1';
					}else{
						temp[i-1]='0';
					}
					break;
					
				case 6:
					if (chkProcedimientos.isSelected()) {
						temp[i-1]='1';
					}else{
						temp[i-1]='0';
					}
					break;
					
					
				case 7:
					if (chkLaboratorios.isSelected()) {
						temp[i-1]='1';
					}else{
						temp[i-1]='0';
					}
					break;
					
				case 8:
					if (chkGeograico.isSelected()) {
						temp[i-1]='1';
					}else{
						temp[i-1]='0';
					}
					break;
					
				case 9:
					if (chkOtrosAdmin.isSelected()) {
						temp[i-1]='1';
					}else{
						temp[i-1]='0';
					}
					break;
					
				case 10:
					if (chkInformes.isSelected()) {
						temp[i-1]='1';
					}else{
						temp[i-1]='0';
					}
					break;
					
				case 11:
					if (chkListados.isSelected()) {
						temp[i-1]='1';
					}else{
						temp[i-1]='0';
					}
					break;
					
				case 12:
					if (chkOtrosInformes.isSelected()) {
						temp[i-1]='1';
					}else{
						temp[i-1]='0';
					}
					break;
					
				case 13:
					if (chkCartera.isSelected()) {
						temp[i-1]='1';
					}else{
						temp[i-1]='0';
					}
					break;
					
				case 14:
					if (chkFactura.isSelected()) {
						temp[i-1]='1';
					}else{
						temp[i-1]='0';
					}
					break;
					
				case 15:
					if (chkImpresoras.isSelected()) {
						temp[i-1]='1';
					}else{
						temp[i-1]='0';
					}
					break;
					
				case 16:
					if (chkCambios.isSelected()) {
						temp[i-1]='1';
					}else{
						temp[i-1]='0';
					}
					break;
					
				case 17:
					if (chkLaboratorio.isSelected()) {
						temp[i-1]='1';
					}else{
						temp[i-1]='0';
					}
					break;
					
			

				default:
					break;
				}
			}
			
			usuarioEdicion.setAcceso(new String(temp));
			if (conexion.getInstance().editarControlAcceso(esta, usuarioEdicion)) {
				principal.registrarAccion("Edición de control de acceso para usuario: " + usuarioEdicion.getUsuario());
				dispose();
			}else{
				lblincorrectos.setText("Error  en la conexion con la base de datos");
			}
			
		}
	}

	
	public ClrCuadroDeTexto getTxtNombres() {
		return txtNombres;
	}

	public ClrCuadroDeTexto getTxtusuario() {
		return txtusuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public void reportarError(String error){
		lblincorrectos.setText(error);
	}
}
