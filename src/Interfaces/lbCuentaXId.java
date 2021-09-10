package Interfaces;

import interfaces_Modificadas.ClrCuadroDeTexto;
import interfaces_Modificadas.Colores;
import interfaces_Modificadas.btnRedondo;
import interfaces_Modificadas.clrBarraMenu;
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
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.ScrollPane;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import java.awt.Component;

import javax.swing.SwingConstants;

import InterfacesListados.OpResXRecepcion;
import InterfacesListados.OpRipsXCuentaCobro;
import ModuloDeReporte.OpReporte7600;
import auxiliares.Paciente;
import auxiliares.RecepcionCompleta;
import auxiliares.Usuario;
import auxiliares.cuentaCobro;
import conexion.conexion;
import conexion.conexionBusqueda;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.StringTokenizer;

import javax.swing.JList;
import javax.swing.AbstractListModel;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import sun.font.CreatedFontTracker;
import metodos.CerrarBusquedaInactiva;

public class lbCuentaXId extends JDialog {

	private clrPanelBordes contentPane;
	private Principal principal;
	private cuentaCobro CC;
	private lbCuentaXId esta = null;
	private CerrarBusquedaInactiva crono = null;
	private ClrCuadroDeTexto cuadrodetexto;
	private JList list;
	private OpRipsXCuentaCobro op1;
	
	private int s = -1;

	/**
	 * Create the frame.
	 * 
	 */

	public lbCuentaXId(ClrCuadroDeTexto cuadroTexto, OpRipsXCuentaCobro op, clrPanelBordes panel, Principal pri) {

		super(pri, false);
		this.principal = pri;
		this.op1 = op;
		this.esta = this;
		this.cuadrodetexto = cuadroTexto;

		setRootPaneCheckingEnabled(false);
		setLocationRelativeTo(op1);
		setBounds((int) Math.floor(op1.getBounds().getX() + cuadroTexto.getX() + panel.getBounds().getX()), (int) Math.floor(op1.getBounds().getY() + cuadroTexto.getHeight() + 4 + cuadroTexto.getBounds().getY() + panel.getBounds().getY()), 273, 190);
		setAlwaysOnTop(true);
		this.setUndecorated(true);
		contentPane = new clrPanelBordes(false);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		clrScrollBar scrollPane = new clrScrollBar();
		contentPane.setBorder(new LineBorder(Colores.clrPrincipal, 2));
		scrollPane.setBounds(3, 40, 267, 146);
		scrollPane.transferFocus();
		contentPane.add(scrollPane);
		list = new JList();
		list.setBounds(0, 0, 274, 150);
		scrollPane.setViewportView(list);
		list.setFocusTraversalKeysEnabled(false);
		list.setFocusCycleRoot(true);
		JLabel lblNewLabel = new JLabel("Busqueda...");
		lblNewLabel.setForeground(Colores.clrPrincipal);
		lblNewLabel.setFont(Colores.fuenteMediana);
		lblNewLabel.setBounds(10, 11, 404, 20);
		contentPane.add(lblNewLabel);
		prepararEscuchas();
		this.setVisible(false);

	}

	

	public void listaok() {
		crearBusqueda(cuadrodetexto.getText());
		if (this.isVisible() == false) {
			this.setVisible(true);
		}

	}

	private void prepararEscuchas() {
		cuadrodetexto.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					if (s == -1 || s == 0) {
						s = list.getModel().getSize() - 1;
						list.setSelectedIndex(s);
					} else {
						s--;
						list.setSelectedIndex(s);
					}
				} else {
					if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						if (s == list.getModel().getSize() - 1) {
							s = 0;
							list.setSelectedIndex(s);
						} else {
							s++;
							list.setSelectedIndex(s);
						}
					} else {
						if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
							cerrarBusqueda();
						} else {
							listaok();
							s = -1;
						}
					}

				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (crono == null) {
					crono = new CerrarBusquedaInactiva("cerrar", esta, Colores.tiempoBusInactivasNormal);
					crono.start();
				} else {
					crono.stop();
					crono = new CerrarBusquedaInactiva("cerrar", esta, Colores.tiempoBusInactivasNormal);
					crono.start();
				}

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					list.setSelectedIndex(s);
					// /////

					if (s != -1) {

						if (list.getSelectedValue().toString().equals("")) {
						} else {
							String[] tokens = list.getSelectedValue().toString().split(" � ");
							esta.asignar(tokens[0]);
						}
					} else {
						if (!cuadrodetexto.getText().equals(cuadrodetexto.getLabel())) {
							esta.asignar(cuadrodetexto.getText());

						}
					}

					// /////////////

				}
			}

		});

		cuadrodetexto.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				esta.dispose();
			}

			@Override
			public void focusGained(FocusEvent e) {

			}
		});

	}

	public void asignar(String idAsignar) {
		cuentaCobro CC = null;
		try {
			CC = conexionBusqueda.getInstance().CCCXid(Integer.parseInt(idAsignar));
			esta.setCC(CC);
			if (op1 != null)
				op1.llenarCuenta(CC);


		} catch (Exception e) {
			if (op1 != null)
				op1.reportarError("Error al buscar");
		
		}
		crono = new CerrarBusquedaInactiva("cerrar", esta, Colores.tiempoBusInactivasRapido);
		crono.start();

	}

	public void cerrarBusqueda() {
		esta.setVisible(false);

	}

	public void crearBusqueda(String busqueda) {

		if (busqueda.equals("")) {
			list.setModel(new AbstractListModel() {
				String[] values = new String[] { "" };

				public int getSize() {
					return values.length;
				}

				public Object getElementAt(int index) {
					return values[index];
				}
			});
		} else {

			list.setModel(new AbstractListModel() {
				conexionBusqueda conb = conexionBusqueda.getInstance();
				String[] values = conb.busquedaCuentaXid(cuadrodetexto.getText());

				public int getSize() {
					return values.length;
				}

				public Object getElementAt(int index) {
					return values[index];
				}
			});
			list.setSelectedIndex(s);

		}

	}

	public cuentaCobro getCC() {
		return CC;
	}

	public void setCC(cuentaCobro CC) {
		CC = CC;
	}

}
