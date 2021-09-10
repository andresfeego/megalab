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

public class VentanaColores extends JDialog {

	private clrPanelBordes contentPane;
	private clrLabel lblincorrectos;
	private Principal principal;
	private clrLabel lblinicio;
	private btnRedondo btnAceptar;
	private btnRedondo btnCancelar;
	private ClrCuadroDeTexto txtusuario;
	private ClrCuadroDeTexto passPrimera;
	private circuloUsuario btnRedondo_;
	private CirclePanel circlePanel;
	private clrCheckBox chbActivo;
	private VentanaColores ou;
	private lbUsuarioXusuario lb1;
	private lbUsuarioXnombre lb;
	private int estado=-1;
	private Color respuesta;
	private Color colorinicial;
	

	/**
	 * Create the frame.
	 */
	public VentanaColores(Principal pri ,Color color) {
		super(pri,true);
		System.out.println();
		ou=this;
		colorinicial=color;
		this.principal=pri;
		setRootPaneCheckingEnabled(false);
		

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 389, 322);
	/*  javax.swing.plaf.InternalFrameUI ifu= this.getUI();
	  ((javax.swing.plaf.basic.BasicInternalFrameUI)ifu).setNorthPane(null);  */
	  
	  //___________________________________________________________________________________________
	  
	 this.setUndecorated(true);
	 this.setLocationRelativeTo(getParent()); 

		
		contentPane = new clrPanelBordes(true);
		//setContentPane(contentPane);
		contentPane.setLayout(null);
		lblinicio = new clrLabel("Modificar Usuario",2,true);
		lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblinicio.setBounds(10, 11, 369, 28);
		
		contentPane.add(lblinicio);
		
		
		btnAceptar = new btnRedondo("Aceptar",new Rectangle(48, 172,121,50),1);
		
		btnAceptar.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {} 
				
if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
	dispose();
} 
			}
		
		});
		
		
		btnAceptar.setSelected(true);
		btnAceptar.setBounds(41, 249, 135, 50);
		contentPane.add(btnAceptar);
		
		
		
		btnCancelar = new btnRedondo("Cancelar",new Rectangle(237, 172,121,50),2);
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				respuesta=colorinicial;

				dispose();
			}
		});
		btnCancelar.setBounds(218, 249, 129, 50);
		contentPane.add(btnCancelar);
		setContentPane(contentPane);
		
		getRootPane().setDefaultButton(btnAceptar);
		
		lblincorrectos = new clrLabel("", 1);
		lblincorrectos.setForeground(Colores.clrAlertaCamarada);
		lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
		lblincorrectos.setAlignmentX(0.5f);
		lblincorrectos.setBounds(10, 39, 358, 28);
		contentPane.add(lblincorrectos);
		
		circlePanel = new CirclePanel();
		circlePanel.setBounds(41, 78, 184, 160);
		
		circlePanel.repaint();

		contentPane.add(circlePanel);
		
		final circuloUsuario btnRedondo_ = new circuloUsuario(new Usuario(1, "", "", "", Color.WHITE, 1, 1, 1,0, ""), new Rectangle(48, 172,121,50));
		btnRedondo_.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRedondo_.setSelected(true);
		btnRedondo_.setBounds(276, 127, 58, 50);
		btnRedondo_.getUsuario().setColor(circlePanel.getColor());
		contentPane.add(btnRedondo_);
		circlePanel.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				btnRedondo_.getUsuario().setColor(circlePanel.getColor());
				btnRedondo_.repaint();
			}
		});
		
		
		
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println(circlePanel.getColor());
				if (circlePanel.getColor()!=null) {
					respuesta=circlePanel.getColor();
					dispose();
				} else {
					lblincorrectos.setText("No has escogido ningún color");
				}
				
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
	

public Color escuchar() {
		
		do {
			if (respuesta!=null) {
				return respuesta;
			} 
		} while(0==0);
		
		
	}


	



	
	/*public static void main(String[] args) {
		OpUsuario op=new OpUsuario(null, new Usuario(1, "nn", "nn", "nn", new Color(125,134,21), 1800000, 1, 1, "111111111111111"), "Modificar");
		op.setVisible(true);
	}*/
	
	public void reportarError(String error){
		lblincorrectos.setText(error);
	}
}
