package Interfaces;

import interfaces_Modificadas.ClrCuadroDeTexto;
import interfaces_Modificadas.btnRedondo;
import interfaces_Modificadas.clrBarraMenu;
import interfaces_Modificadas.clrFrameBordes;
import interfaces_Modificadas.clrLabel;
import interfaces_Modificadas.clrPanel;
import interfaces_Modificadas.clrPanelBordes;
import interfaces_Modificadas.clrPassword;
import interfaces_Modificadas.clrScrollBar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;

import java.awt.Component;

import javax.swing.SwingConstants;

import auxiliares.Usuario;
import conexion.conexion;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import interfaces_Modificadas.clrtextpane;

import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class ventanaPregunta extends JDialog {

	private clrPanelBordes contentPane;
	public btnRedondo btnAceptar;
	private Principal principal;
	private ventanaPregunta esta;
	private String pregunta;
	private String afirmativo;
	private String negativo;
	private int tipo;
	private int respuesta=-1;

	/**
	 * Create the frame.
	 */
	public ventanaPregunta(Principal pri, String pregunta,String afirmativo,String negativo) {

		super(pri, true);
		this.principal = pri;
		this.esta = this;
		this.pregunta=pregunta;
		this.afirmativo=afirmativo;
		this.negativo=negativo;
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 378, 250);
		/*
		 * javax.swing.plaf.InternalFrameUI ifu= this.getUI();
		 * ((javax.swing.plaf
		 * .basic.BasicInternalFrameUI)ifu).setNorthPane(null);
		 */

		// ___________________________________________________________________________________________

		this.setUndecorated(true);
		this.setLocationRelativeTo(getParent());

		contentPane = new clrPanelBordes(true);
		// setContentPane(contentPane);
		contentPane.setLayout(null);
		clrLabel lblinicio = new clrLabel("MEGALAB PREGUNTA", 2, true);
		lblinicio.setFocusCycleRoot(true);
		lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblinicio.setBounds(10, 11, 358, 28);

		contentPane.add(lblinicio);

		btnAceptar = new btnRedondo(afirmativo, new Rectangle(48, 172,121,50), 1);
		btnAceptar.setSelected(true);
		btnAceptar.setBounds(56, 172,121,50);
		contentPane.add(btnAceptar);

		btnRedondo btnCancelar = new btnRedondo(negativo, new Rectangle(237, 172,121,50), 2);
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				respuesta=0;
				dispose();
			}
		});
		btnCancelar.setBounds(192, 172, 129, 50);
		contentPane.add(btnCancelar);
		setContentPane(contentPane);
		

		btnAceptar.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					respuesta=1;
					dispose();
				}

				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					respuesta=0;
					dispose();
				}
			}

		});
		
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				respuesta=1;
				dispose();
			}

		});
		

		clrScrollBar scrollPane = new clrScrollBar();
		scrollPane.setBounds(10, 71, 358, 90);
		contentPane.add(scrollPane);

		clrtextpane clrtextpane_ = new clrtextpane(1000,false,"¿ " + pregunta + " ?",0);
		scrollPane.setViewportView(clrtextpane_);

		this.setVisible(true);

	}

	public boolean escuchar() {
		
		do {
			if (respuesta==1) {
				return true;
			} else {
				if (respuesta==0) {
					return false;
				} else {

				}
			}
		} while(0==0);
		
		
	}


}
