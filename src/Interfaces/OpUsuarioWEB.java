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

public class OpUsuarioWEB extends JDialog {

	private clrPanelBordes contentPane;
	private ClrCuadroDeTexto txtNombres;
	private clrLabel lblincorrectos;
	private Principal principal;
	private Usuario usuario;
	private clrLabel lblinicio;
	private btnRedondo btnAceptar;
	private btnRedondo btnCancelar;
	private ClrCuadroDeTexto passPrimera;
	private circuloUsuario btnRedondo_;
	private clrLabel clrLabel__1;
	private clrComboBox cbTiempo;
	private CirclePanel circlePanel;
	private clrCheckBox chbActivo;
	private OpUsuarioWEB ou;
	private lbUsuarioWebXid lb;
	private int estado=-1;
	private String seleccionado="";
	private int  idSeleccionado=-1;

	/**
	 * Create the frame.
	 */
	public OpUsuarioWEB(Principal pri ,Usuario usuario) {
		super(pri,true);
		System.out.println();
		this.usuario=usuario;
		ou=this;
		this.principal=pri;
		setRootPaneCheckingEnabled(false);
		


		setBounds(100, 100, 389, 271);
		this.setUndecorated(true);
		this.setLocationRelativeTo(getParent()); 
		contentPane = new clrPanelBordes(true);
		contentPane.setLayout(null);
		lblinicio = new clrLabel("Restaurar contrase\u00F1a Web",2,true);
		lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblinicio.setBounds(10, 11, 369, 28);
		
		contentPane.add(lblinicio);
		 				
		 
		 
		 btnAceptar = new btnRedondo("Restaurar",new Rectangle(48, 172,121,50),3);
		 btnAceptar.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					if (seleccionado.equals("")) {
						
						lblincorrectos.setText("No has seleccionado ningùn usuario");
						
					} else {
						restaurar();
						
					}
				} 
				
if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
	dispose();
} 
			}
		
		});
		 btnAceptar.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if (seleccionado.equals("")) {
					
					lblincorrectos.setText("No has seleccionado ningùn usuario");
					
				} else {
					restaurar();
					
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
		 btnAceptar.setBounds(41, 178, 135, 50);
		 contentPane.add(btnAceptar);
		
		
		
		btnCancelar = new btnRedondo("Cancelar",new Rectangle(237, 172,121,50),2);
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(lb!=null)lb.setVisible(false);
				
				dispose();
			}
		});
		btnCancelar.setBounds(218, 178, 129, 50);
		contentPane.add(btnCancelar);
		setContentPane(contentPane);
		
		getRootPane().setDefaultButton(btnAceptar);
		
		lblincorrectos = new clrLabel("", 1);
		lblincorrectos.setForeground(Colores.clrAlertaCamarada);
		lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
		lblincorrectos.setAlignmentX(0.5f);
		lblincorrectos.setBounds(10, 39, 358, 28);
		contentPane.add(lblincorrectos);
		
	
		
		txtNombres = new ClrCuadroDeTexto(75,false,"id de usuario web",true);
		txtNombres.setBounds(41, 101, 306, 28);
		txtNombres.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				lb.setVisible(false);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				
				if (lb==null) {
					lb=new lbUsuarioWebXid(txtNombres, ou, principal);
				}else {
					lb.setVisible(true);
				}

			}
		});
		
		contentPane.add(txtNombres);
		
		
		
		btnAceptar.addMouseListener(new MouseAdapter() {
			
			
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
	
	
	
	
	public void listaBusquedas(ClrCuadroDeTexto cuadroTexto){
		int activo=0;
		
		clrScrollBar scrollPane = new clrScrollBar();
		scrollPane.setBounds((int)(cuadroTexto.getBounds().getX()+cuadroTexto.getBounds().getWidth()+20),(int)(cuadroTexto.getBounds().getY()),(int)Math.floor(cuadroTexto.getBounds().getWidth()), 150);
		scrollPane.transferFocus();
		this.contentPane.add(scrollPane);
		this.setBounds((int)this.getBounds().getX(),(int)this.getBounds().getY(),(int)this.getBounds().getWidth()+500,(int)this.getBounds().getHeight());
		
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"item 1", "item 1", "item 1", "item 1", "item 1", "item 1", "item 1", "item 1", "item 1", "item 1", "item 1"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		if(activo==0){
		list.setBounds((int)Math.floor(ou.getBounds().getX()+cuadroTexto.getBounds().getX()),(int)Math.floor(ou.getBounds().getY()+cuadroTexto.getBounds().getHeight()+cuadroTexto.getBounds().getY()),(int)Math.floor(cuadroTexto.getBounds().getWidth()), 150);
		activo=1;
		}
		scrollPane.setViewportView(list);
		list.setFocusTraversalKeysEnabled(false);
		list.setFocusCycleRoot(true);
		
	}
	
	
	public ClrCuadroDeTexto getTxtNombres() {
		return txtNombres;
	}

	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	/*public static void main(String[] args) {
		OpUsuario op=new OpUsuario(null, new Usuario(1, "nn", "nn", "nn", new Color(125,134,21), 1800000, 1, 1, "111111111111111"), "Modificar");
		op.setVisible(true);
	}*/
	
	public void reportarError(String error){
		lblincorrectos.setText(error);
	}




	public void llenar(String usuario,int idusuario) {
		seleccionado=usuario;
		idSeleccionado=idusuario;
		txtNombres.setText(usuario);
		txtNombres.setForeground(Color.BLACK);
		
	}
	
	public void restaurar(){
		conexion cone=conexion.getInstance();
		
		if (cone.restaurarPassUsuWeb(ou,seleccionado,idSeleccionado)) {
			ou.principal.registrarAccion("Restauraciòn de contraseña de usuario Web  '"+seleccionado+"'");
			dispose();
		}else{
			lblincorrectos.setText("Error  en la conexion con la base de datos");
		}
	}
}
