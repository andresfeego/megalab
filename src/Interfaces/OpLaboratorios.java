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
import auxiliares.Laboratorio;
import auxiliares.Paciente;
import auxiliares.Usuario;
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
import javax.swing.SwingUtilities;

public class OpLaboratorios extends JDialog {
	private clrPanelBordes contentPane;
	private clrLabel lblincorrectos;
	private Principal principal;
	private Usuario usuario;
	private clrLabel lblinicio;
	private circuloUsuario btnRedondo_;
	private String accion;
	private OpLaboratorios esta;
	private ClrCuadroDeTexto txtNIT;
	private ClrCuadroDeTexto txtRazonS;
	private clrComboBox cbCiudad;
	private ClrCuadroDeTexto txtDireccion;
	private clrScrollBar clrScrollBar_;
	private btnRedondo btnCancelar;
	private btnRedondo btnAceptar;
	private lbLaboratorioXnit lb1;
	private lbLaboratorioXrazon lb2;
	private clrtextpane txtInfAdicional;
	private String nitAnt;
	private int idLab = -1;

	public OpLaboratorios(Principal principal, Usuario usuario, String Accion) {
		super(principal, true);
		this.principal = principal;
		this.usuario = usuario;
		this.accion = Accion;
		this.esta = this;

		// this.setDefaultLookAndFeelDecorated(false);

		setBounds(100, 100, 626, 332);
		this.setUndecorated(true);
		this.setLocationRelativeTo(getParent());
		contentPane = new clrPanelBordes(true);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		if (accion.equals("Agregar")) {

			lblinicio = new clrLabel("Agregar laboratorio", 2, true);
			lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
			lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblinicio.setBounds(10, 11, 606, 28);
			contentPane.add(lblinicio);

			lblincorrectos = new clrLabel("", 1);
			lblincorrectos.setForeground(Colores.clrAlertaCamarada);
			lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
			lblincorrectos.setAlignmentX(0.5f);
			lblincorrectos.setBounds(10, 50, 606, 28);
			contentPane.add(lblincorrectos);

			txtNIT = new ClrCuadroDeTexto(15,false,"Nit", 1);
			txtNIT.setBounds(20, 78, 308, 20);

			contentPane.add(txtNIT);

			txtRazonS = new ClrCuadroDeTexto(100,false,"Razón social", 1);
			txtRazonS.setBounds(20, 109, 308, 20);
			contentPane.add(txtRazonS);

			txtDireccion = new ClrCuadroDeTexto(100,false,"Direccion", 1);
			txtDireccion.setColumns(10);
			txtDireccion.setBounds(20, 145, 308, 20);
			contentPane.add(txtDireccion);

			btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50), 2);
			btnCancelar.setBounds(323, 234, 129, 50);
			btnCancelar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();

				}
			});
			contentPane.add(btnCancelar);

			btnAceptar = new btnRedondo("Agregar", new Rectangle(48, 172,121,50), 1);
			btnAceptar.setSelected(true);
			btnAceptar.setBounds(175, 234,121,50);
			btnAceptar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					agregarLaboratorio();

				}
			});
			contentPane.add(btnAceptar);

			cbCiudad = new clrComboBox(conexionCombos.getInstance().listaCiudades(),1);
			cbCiudad.setBounds(21, 181, 308, 20);
			contentPane.add(cbCiudad);

			clrScrollBar_ = new clrScrollBar();
			clrScrollBar_.setBounds(338, 77, 265, 124);
			contentPane.add(clrScrollBar_);

			txtInfAdicional = new clrtextpane(500,false,"Informacion adicional",0);
			clrScrollBar_.setViewportView(txtInfAdicional);

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					btnAceptar.requestFocusInWindow();
				}
			});

			this.setVisible(true);

		} else {
			if (accion.equals("Modificar")) {

				lblinicio = new clrLabel("Modificar laboratorio", 2, true);
				lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
				lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
				lblinicio.setBounds(10, 11, 606, 28);
				contentPane.add(lblinicio);

				lblincorrectos = new clrLabel("", 1);
				lblincorrectos.setForeground(Colores.clrAlertaCamarada);
				lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
				lblincorrectos.setAlignmentX(0.5f);
				lblincorrectos.setBounds(10, 50, 606, 28);
				contentPane.add(lblincorrectos);

				txtNIT = new ClrCuadroDeTexto(15,false,"Nit", true, 1);
				txtNIT.setBounds(20, 78, 308, 20);
				txtNIT.addFocusListener(new FocusListener() {

					@Override
					public void focusLost(FocusEvent e) {
						lb1.setVisible(false);

					}

					@Override
					public void focusGained(FocusEvent e) {
						if (lb1 == null) {
							lb1 = new lbLaboratorioXnit(txtNIT, esta, esta.principal);
						}

					}
				});


				contentPane.add(txtNIT);

				txtRazonS = new ClrCuadroDeTexto(100,false,"Razón social", true, 1);
				txtRazonS.setBounds(20, 109, 308, 20);
				txtRazonS.addFocusListener(new FocusListener() {

					@Override
					public void focusLost(FocusEvent e) {
						lb2.setVisible(false);
					}

					@Override
					public void focusGained(FocusEvent e) {
						if (lb2 == null) {
							lb2 = new lbLaboratorioXrazon(txtRazonS, esta, esta.principal);
						}

					}
				});
				contentPane.add(txtRazonS);

				txtDireccion = new ClrCuadroDeTexto(100,false,"Direccion", true);
				txtDireccion.setColumns(10);
				txtDireccion.setBounds(20, 145, 308, 20);
				contentPane.add(txtDireccion);

				btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50), 2);
				btnCancelar.setBounds(323, 234, 129, 50);
				btnCancelar.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();

					}
				});
				contentPane.add(btnCancelar);

				btnAceptar = new btnRedondo("Modificar", new Rectangle(48, 172,121,50), 3);
				btnAceptar.setSelected(true);
				btnAceptar.setBounds(175, 234,121,50);
				btnAceptar.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						modificarLaboratorio();

					}
				});
				contentPane.add(btnAceptar);

				cbCiudad = new clrComboBox(conexionCombos.getInstance().listaCiudades(),1);
				cbCiudad.setBounds(21, 181, 308, 20);
				contentPane.add(cbCiudad);

				clrScrollBar_ = new clrScrollBar();
				clrScrollBar_.setBounds(338, 77, 265, 124);
				contentPane.add(clrScrollBar_);

				txtInfAdicional = new clrtextpane(500,false,"Informacion adicional",0);
				clrScrollBar_.setViewportView(txtInfAdicional);

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						btnAceptar.requestFocusInWindow();
					}
				});

				this.setVisible(true);

			} else {

				lblinicio = new clrLabel("Eliminar  laboratorio", 2, true);
				lblinicio.setHorizontalAlignment(SwingConstants.CENTER);
				lblinicio.setAlignmentX(Component.CENTER_ALIGNMENT);
				lblinicio.setBounds(10, 11, 606, 28);
				contentPane.add(lblinicio);

				lblincorrectos = new clrLabel("", 1);
				lblincorrectos.setForeground(Colores.clrAlertaCamarada);
				lblincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
				lblincorrectos.setAlignmentX(0.5f);
				lblincorrectos.setBounds(10, 50, 606, 28);
				contentPane.add(lblincorrectos);

				txtNIT = new ClrCuadroDeTexto(15,false,"Nit", true);
				txtNIT.setBounds(20, 78, 308, 20);
				txtNIT.addFocusListener(new FocusListener() {

					@Override
					public void focusLost(FocusEvent e) {
						lb1.setVisible(false);

					}

					@Override
					public void focusGained(FocusEvent e) {
						if (lb1 == null) {
							lb1 = new lbLaboratorioXnit(txtNIT, esta, esta.principal);
						}

					}
				});


				contentPane.add(txtNIT);

				txtRazonS = new ClrCuadroDeTexto(100,false,"Razón social", true);
				txtRazonS.setBounds(20, 109, 308, 20);
				txtRazonS.addFocusListener(new FocusListener() {

					@Override
					public void focusLost(FocusEvent e) {
						lb2.setVisible(false);
					}

					@Override
					public void focusGained(FocusEvent e) {
						if (lb2 == null) {
							lb2 = new lbLaboratorioXrazon(txtRazonS, esta, esta.principal);
						}

					}
				});
				contentPane.add(txtRazonS);

				txtDireccion = new ClrCuadroDeTexto(100,false,"Direccion", true);
				txtDireccion.setColumns(10);
				txtDireccion.setBounds(20, 145, 308, 20);
				contentPane.add(txtDireccion);

				btnCancelar = new btnRedondo("Cancelar", new Rectangle(237, 172,121,50), 2);
				btnCancelar.setBounds(323, 234, 129, 50);
				btnCancelar.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();

					}
				});
				contentPane.add(btnCancelar);

				btnAceptar = new btnRedondo("Eliminar", new Rectangle(48, 172,121,50), 3);
				btnAceptar.setSelected(true);
				btnAceptar.setBounds(175, 234,121,50);
				btnAceptar.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						eliminarLaboratorio();

					}
				});
				contentPane.add(btnAceptar);

				cbCiudad = new clrComboBox(conexionCombos.getInstance().listaCiudades(),1);
				cbCiudad.setBounds(21, 181, 308, 20);
				contentPane.add(cbCiudad);

				clrScrollBar_ = new clrScrollBar();
				clrScrollBar_.setBounds(338, 77, 265, 124);
				contentPane.add(clrScrollBar_);

				txtInfAdicional = new clrtextpane(500,false,"Informacion adicional",0);
				clrScrollBar_.setViewportView(txtInfAdicional);

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						btnAceptar.requestFocusInWindow();
					}
				});

				this.setVisible(true);

			}
		}

	}

	public void agregarLaboratorio() {

		if (txtNIT.getText().equals(txtNIT.getLabel()) || txtRazonS.getText().equals(txtRazonS.getLabel()) || txtDireccion.getText().equals(txtDireccion.getLabel()) || cbCiudad.getSelectedIndex() == 0) {

			lblincorrectos.setText("Faltan campos por llenar");
		} else {
			lblincorrectos.setText("");

			int auxiCiudad = conexionBusqueda.getInstance().idCiudadXnombre(cbCiudad.getSelectedItem() + "");
			String auxiInfoAdicional = "";
			if (!txtInfAdicional.getText().equals(txtInfAdicional.getLabel())) {
				auxiInfoAdicional = txtInfAdicional.getText();
			}
			;

			Laboratorio lab = new Laboratorio(txtNIT.getText(), txtRazonS.getText(), txtDireccion.getText(), auxiCiudad, auxiInfoAdicional);

			conexion cone = conexion.getInstance();
			if (cone.nuevoLaboratorio(esta, lab)) {
				esta.principal.registrarAccion("Creacion de laboratorio '" + txtNIT.getText() + " " + txtRazonS.getText() + "'");
				dispose();

			} else{
				lblincorrectos.setText("Error  en la conexion con la base de datos");
			}
		}

	}

	public void modificarLaboratorio() {

		if (txtNIT.getText().equals(txtNIT.getLabel()) || txtRazonS.getText().equals(txtRazonS.getLabel()) || txtDireccion.getText().equals(txtDireccion.getLabel()) || cbCiudad.getSelectedIndex() == 0) {

			lblincorrectos.setText("Faltan campos por llenar");
		} else {
			lblincorrectos.setText("");

			int auxiCiudad = conexionBusqueda.getInstance().idCiudadXnombre(cbCiudad.getSelectedItem() + "");
			String auxiInfoAdicional = "";
			if (!txtInfAdicional.getText().equals(txtInfAdicional.getLabel())) {
				auxiInfoAdicional = txtInfAdicional.getText();
			}
			;

			Laboratorio lab = new Laboratorio(idLab, txtNIT.getText(), txtRazonS.getText(), txtDireccion.getText(), auxiCiudad, auxiInfoAdicional);

			conexion cone = conexion.getInstance();
			if (cone.editarLaboratorio(esta, lab, nitAnt)) {
				esta.principal.registrarAccion("Modificación de laboratorio '" + txtNIT.getText() + " " + txtRazonS.getText() + "'");
				dispose();

			} else{
				lblincorrectos.setText("Error  en la conexion con la base de datos");
			}
		}

	}

	public void eliminarLaboratorio() {

		if (idLab == -1) {

			lblincorrectos.setText("No has seleccionado ningun laboratorio");
		} else {

			conexion cone = conexion.getInstance();
			if (cone.eliminarLaboratorio(esta, idLab)) {
				esta.principal.registrarAccion("Eliminación de laboratorio '" + txtNIT.getText() + " " + txtRazonS.getText() + "'");
				dispose();

			} else{
				lblincorrectos.setText("Error  en la conexion con la base de datos");
			}
		}

	}

	public void llenar(Laboratorio lab) {
		idLab = lab.getIdLab();
		nitAnt = lab.getNit();

		txtNIT.setText(lab.getNit());
		txtNIT.setForeground(Color.BLACK);

		txtRazonS.setText(lab.getRazonSocial());
		txtRazonS.setForeground(Color.black);

		txtDireccion.setText(lab.getDireccion());
		txtDireccion.setForeground(Color.black);

		String[] auxiciu = conexionBusqueda.getInstance().ciudadXid(lab.getIdCiudad() + "");
		cbCiudad.setSelectedItem(auxiciu[1]);

		if (!lab.getInfAdicional().equals("")) {
			txtInfAdicional.setText(lab.getInfAdicional());
			txtInfAdicional.setForeground(Color.BLACK);
		}else{
			txtInfAdicional.reiniciar();
		}

	}

	public void reportarError(String error) {
		lblincorrectos.setText(error);
	}
}
