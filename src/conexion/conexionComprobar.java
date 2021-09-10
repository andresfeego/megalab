package conexion;

import interfaces_Modificadas.Colores;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import javax.imageio.ImageIO;

import metodos.ObtenerFechaColombia;
import Interfaces.OpAbonoXEmpresa;
import Interfaces.OpAbonoXFactura;
import Interfaces.OpAbonoXpaciente;
import Interfaces.OpBacteriologo;
import Interfaces.OpCiudades;
import Interfaces.OpControlAcceso;
import Interfaces.OpEmpresa;
import Interfaces.OpEspecialidades;
import Interfaces.OpExamen;
import Interfaces.OpGrupoEmpresa;
import Interfaces.OpItemCotizacion;
import Interfaces.OpItemRecepcion;
import Interfaces.OpLaboratorios;
import Interfaces.OpMedico;
import Interfaces.OpMiLaboratorio;
import Interfaces.OpNotasCD;
import Interfaces.OpOPTD;
import Interfaces.OpPaciente;
import Interfaces.OpParentesco;
import Interfaces.OpPerfiles;
import Interfaces.OpProtocolo;
import Interfaces.OpPyP;
import Interfaces.OpRegistroEntrega;
import Interfaces.OpReportarResultados;
import Interfaces.OpSalas;
import Interfaces.OpSecciones;
import Interfaces.OpTarifas;
import Interfaces.OpTipoDato;
import Interfaces.OpTipoMuestras;
import Interfaces.OpUsuario;
import Interfaces.Principal;
import auxiliares.AbonoXEmpresa;
import auxiliares.AbonoXFactura;
import auxiliares.AbonoXPaciente;
import auxiliares.Bacteriologo;
import auxiliares.Cotizacion;
import auxiliares.DatosAbono;
import auxiliares.Empresa;
import auxiliares.Examen;
import auxiliares.Factura;
import auxiliares.GruposEmpresas;
import auxiliares.ItemRecepcion;
import auxiliares.Laboratorio;
import auxiliares.Medico;
import auxiliares.MiLaboratorio;
import auxiliares.Nota;
import auxiliares.OTD;
import auxiliares.Paciente;
import auxiliares.PerfilExamenes;
import auxiliares.Recepcion;
import auxiliares.RecepcionCompleta;
import auxiliares.Reporte;
import auxiliares.Secciones;
import auxiliares.TipoDato;
import auxiliares.TipoMuestra;
import auxiliares.Usuario;
import auxiliares.itemCotizacion;
import auxiliares.itemFactura;
import auxiliares.itemProtocolo;
import auxiliares.itemTarifa;
import auxiliares.protocolo;
//Importamos las librerias de Apache Commons
public class conexionComprobar {

	private static Connection con;
	private static conexionComprobar INSTANCE = null;
	private static Usuario usuar;
	


	

	//////////////////////////////////////////////////////////////////////////////
	
	public static void performConnection1() {

		String host = Colores.getHost();
		String user = Colores.getUser();
		String pass = Colores.getPass();
		String dtbs = Colores.getDtbs();
		

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String newConnectionURL = "jdbc:mysql://" + host + ":3306/" + dtbs + "?" + "user=" + user + "&password=" + pass;
			con = DriverManager.getConnection(newConnectionURL);
		} catch (Exception e) {
			System.err.println("error"+e);
			INSTANCE = null;
		}

	}

	private static void createInstance1() {
		if (INSTANCE == null) {
			INSTANCE = new conexionComprobar();
		}
	}
	
	public static conexionComprobar getInstance1() {
		if (INSTANCE == null){
			createInstance1();
		}
		return INSTANCE;
	}
	
	public static void closeConnection1() {
		try {
			con.close();
			INSTANCE = null;

		} catch (Exception e) {
			INSTANCE = null;
			
			
		}
	}
	//////////////////////////////////////////////////////////////////////////////  comprobar
	
	public static boolean comprobarConexion() {
		String host = Colores.getHost();
		String user = Colores.getUser();
		String pass = Colores.getPass();
		String dtbs = Colores.getDtbs();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String newConnectionURL = "jdbc:mysql://" + host + ":3306/" + dtbs + "?" + "user=" + user + "&password=" + pass;
			con = DriverManager.getConnection(newConnectionURL);
			con.close();
			return true;
		} catch (Exception e) {
			System.err.println("error"+e);

			return  false;
		}

	}

	
}