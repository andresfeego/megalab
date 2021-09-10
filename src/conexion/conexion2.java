package conexion;

import interfaces_Modificadas.Colores;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;

import com.sun.org.apache.xalan.internal.xsltc.dom.LoadDocument;

import otrosImpresos.CuentaCobroXEmpresa;
import otrosImpresos.imprimirReporte;
import metodos.ObtenerFechaColombia;
import Interfaces.OpAbonoXEmpresa;
import Interfaces.OpAbonoXFactura;
import Interfaces.OpAbonoXpaciente;
import Interfaces.OpAnularRecepcion;
import Interfaces.OpBacteriologo;
import Interfaces.OpCambiosRecepcion;
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
import Interfaces.OpSede;
import Interfaces.OpTamanosPapel;
import Interfaces.OpTarifas;
import Interfaces.OpTipoDato;
import Interfaces.OpTipoMuestras;
import Interfaces.OpUsuario;
import Interfaces.OpUsuarioWEB;
import Interfaces.Principal;
import InterfacesListados.OpCuentaXEmpresa;
import ModuloDeReporte.OpReporte7600;
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
import auxiliares.Sede;
import auxiliares.TipoDato;
import auxiliares.TipoMuestra;
import auxiliares.Usuario;
import auxiliares.cuentaCobro;
import auxiliares.encaFirma;
import auxiliares.itemCotizacion;
import auxiliares.itemCuenta;
import auxiliares.itemFactura;
import auxiliares.itemProtocolo;
import auxiliares.itemTarifa;
import auxiliares.protocolo;
//Importamos las librerias de Apache Commons
public class conexion2 {

	private static Connection con;
	private static conexion2 INSTANCE = null;
	private static Usuario usuar;
	private static String host;
	private static String user;
	private static String pass;
	private static String dtbs;

	public conexion2() {

	}
	
	

	public static void conectar() {
		System.out.println("intentando conectar CON 2 CONECTAR");


		 host = Colores.getHost2();
		 user = Colores.getUser2();
		 pass = Colores.getPass2();
		 dtbs = Colores.getDtbs2();
		 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String newConnectionURL = "jdbc:mysql://" + host + ":3306/" + dtbs + "?" + "user=" + user + "&password=" + pass;
			con = DriverManager.getConnection(newConnectionURL);
			System.out.println("abriendo  desde conexion 2");
			
		} catch (Exception e) {
			System.out.println("error al conectar");
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 3> :no es conectar con la base de datos");
			closeConnection();
			INSTANCE = null;
		}

	}

	private static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new conexion2();
		}
	}

	public static conexion2 getInstance(Usuario usuario) {
		if (INSTANCE == null){
			createInstance();
		}
		usuar=usuario;
		return INSTANCE;
	}

	public static void closeConnection() {
		try {
			//con.close();
			System.out.println("cerrrando desde conexion 2");
			INSTANCE = null;

		} catch (Exception e) {
			INSTANCE = null;
			
		}
	}

	//////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////////
	public static Usuario acceso(String usu, String pass) {
		conectar();
		Statement consulta = null;
		Statement consulta2 = null;
		ResultSet tabla = null;
		ResultSet tabla2 = null;

		try {
			consulta = con.createStatement();
			consulta2 = con.createStatement();
			tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".usuarios WHERE  Usuario COLLATE utf8_bin ='" + usu + "' and pass COLLATE utf8_bin ='" + pass + "'");

			if (tabla.next()) {

				tabla2 = consulta2.executeQuery("SELECT  `R` ,  `G` ,  `B`  from colores where idUsuario='" + tabla.getInt(1) + "'");
				tabla2.next();
				Color col = new Color(tabla2.getInt(1), tabla2.getInt(2), tabla2.getInt(3));
				try {
					usuar = new Usuario(tabla.getInt(1), tabla.getString(2), tabla.getString(3), tabla.getString(4), col, tabla.getInt(5), tabla.getInt(6), tabla.getInt(7), tabla.getInt(8), tabla.getString(9));
					closeConnection();
					return usuar;

				} catch (Exception e) {
					
					Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2-2 4> en la base de datos : "+e.toString());
					closeConnection();
					return null;

				}

			} else {
				closeConnection();
				return null;

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2-2 5> en la base de datos : "+e.toString());
			closeConnection();
			return null;
		}

	}

	public static Usuario acceso(String usu) {
		conectar();

		Statement consulta = null;
		Statement consulta2 = null;
		ResultSet tabla = null;
		ResultSet tabla2 = null;

		try {
			consulta = con.createStatement();
			consulta2 = con.createStatement();
			tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".usuarios WHERE  Usuario COLLATE utf8_bin = '" + usu + "'");

			if (tabla.next()) {

				tabla2 = consulta2.executeQuery("SELECT  `R` ,  `G` ,  `B`,  `A`  from colores where idUsuario='" + tabla.getInt(1) + "'");
				tabla2.next();
				Color col = new Color(tabla2.getInt(1), tabla2.getInt(2), tabla2.getInt(3), tabla2.getInt(4));
				System.out.println(col.toString());
				try {
					usuar = new Usuario(tabla.getInt(1), tabla.getString(2), tabla.getString(3), tabla.getString(4), col, tabla.getInt(5), tabla.getInt(6), tabla.getInt(7),tabla.getInt(8), tabla.getString(9));
					closeConnection();
					return usuar;

				} catch (Exception e) {
					
					Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2-2 6> en la base de datos : "+e.toString());
					closeConnection();
					return null;

				}

			} else {
				closeConnection();
				return null;

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 7> en la base de datos : "+e.toString());
			closeConnection();
			return null;
		}

	}

	public static boolean tieneAcceso(String usu) {
		conectar();

		Statement consulta = null;
		Statement consulta2 = null;
		ResultSet tabla = null;
		ResultSet tabla2 = null;

		try {
			consulta = con.createStatement();
			consulta2 = con.createStatement();
			tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".usuarios WHERE  Usuario COLLATE utf8_bin = '" + usu + "' and activo=1");

			if (tabla.next()) {
				System.err.println("ok conexion 2");return true;
			} else {
				closeConnection();
				return false;

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 7.1> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	
	// ############################################################################ METODOS DE ADICION######################################################################################
	
	public boolean nuevoUsuario(OpUsuario opVentana, String usu, String pass, String nom, int tiempo, int primVez, int activo, int r, int g, int b,int a) throws SQLException {
		conectar();

		tiempo = tiempo * 60000;
		
		Statement consulta = con.createStatement();
		ResultSet tabla = consulta.executeQuery("select usuario from usuarios where usuario='" + usu + "'");

		if (tabla.next()) {
			opVentana.reportarError("El usuario ya esta en uso");
			closeConnection();
			return false;
		} else {
			
			try {

				String seleccio = "INSERT INTO `"+dtbs+"`.`usuarios` (`usuario`,`pass`,`nombreCompleto`,`tiempoInactivo`,`primeraVez`,`activo`)VALUES (?,?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(seleccio);
				ps.setString(1, usu);
				ps.setString(2, pass);
				ps.setString(3, nom);
				ps.setInt(4, tiempo);
				ps.setInt(5, primVez);
				ps.setInt(6, activo);
				ps.executeUpdate();

				Statement consulta2 = con.createStatement();
				ResultSet tabla2 = consulta.executeQuery("select idUsuarios from usuarios where usuario='" + usu + "'");
				tabla2.next();

				String seleccion = "INSERT INTO  `"+dtbs+"`.`colores` (`idUsuario` ,`R` ,`G` ,`B`,`A`)VALUES (?,?,?,?,?)";
				PreparedStatement ps2 = con.prepareStatement(seleccion);

				ps2.setInt(1, tabla2.getInt(1));
				ps2.setInt(2, r);
				ps2.setInt(3, g);
				ps2.setInt(4, b);
				ps2.setInt(5, a);
				ps2.executeUpdate();
				closeConnection();
				System.err.println("ok conexion 2");return true;
			
				
			} catch (Exception e) {
				System.err.println(e);
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 8> en la base de datos : "+e.toString());
				closeConnection();
				return false;
			}
		}
	}

	public boolean nuevoPaciente(OpPaciente opVentana, Paciente paciente) {
		conectar();

		try {
			Statement consulta = con.createStatement();
			ResultSet tabla = consulta.executeQuery("select * from "+dtbs+".paciente where idpaciente='" + paciente.getId() + "'");

			if (tabla.next()) {
				opVentana.reportarError("Numero de documento ya registrado.");
				closeConnection();
				return false;
			} else {

				Statement auxic = con.createStatement();
				System.out.println(paciente.getCiudad());
				ResultSet resultado = auxic.executeQuery("select idciudad from "+dtbs+".ciudad where nombre_ciudad like'%" + paciente.getCiudad() + "%'");
				resultado.next();
				int auxiciudad = resultado.getInt(1);

				
				String seleccio = "INSERT INTO `"+dtbs+"`.`persona` (`cod_persona`, `nombres`, `apellidos`, `fecha_nacimiento`, `genero`, `zona_residencial`, `ciudad`, `tipo_doc`, `direccion`) VALUES (?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(seleccio);
				ps.setString(1, paciente.getId());
				ps.setString(2, paciente.getNombres());
				ps.setString(3, paciente.getApellidos());
				ps.setDate(4, new java.sql.Date(paciente.getFechaNacimiento().getTime()));
				ps.setInt(5, paciente.getGenero());
				ps.setInt(6, paciente.getZonaResidencial());
				ps.setInt(7, auxiciudad);
				ps.setInt(8, paciente.getTipoId());
				ps.setString(9, paciente.getDireccion());
				ps.executeUpdate();

				
				String seleccion3 = "INSERT INTO `"+dtbs+"`.`usuarioweb` (`usuario`, `pass`) VALUES (?,?);";
				PreparedStatement ps3 = con.prepareStatement(seleccion3);

				ps3.setString(1, paciente.getId());
				ps3.setString(2, paciente.getId());
				ps3.executeUpdate();
				
				
				String seleccion = "INSERT INTO `"+dtbs+"`.`paciente` (`idpaciente`, `parentesco`, `numero_carn`) VALUES (?,?,?);";
				PreparedStatement ps2 = con.prepareStatement(seleccion);

				ps2.setString(1, paciente.getId());
				ps2.setInt(2, paciente.getParentesco());
				ps2.setString(3, paciente.getNum_Carnet());
				ps2.executeUpdate();

				if (!paciente.getEmail1().equals("")) {
					String queryE1 = "INSERT INTO  `"+dtbs+"`.`email` (`id_email` ,`email`)VALUES (NULL,?);";
					PreparedStatement E1 = con.prepareStatement(queryE1);

					E1.setString(1, paciente.getEmail1());
					E1.executeUpdate();

					Statement consultaE1 = con.createStatement();
					ResultSet consultaEmail1 = consultaE1.executeQuery("select id_email from "+dtbs+".email where email='" + paciente.getEmail1() + "'");

					consultaEmail1.next();

					String queryE1_tabla = "INSERT INTO  `"+dtbs+"`.`email_x_persona` (`persona_cod_persona` ,`email_id_email`)VALUES (?,?);";
					PreparedStatement E1_tabla = con.prepareStatement(queryE1_tabla);

					E1_tabla.setString(1, paciente.getId());
					E1_tabla.setInt(2, consultaEmail1.getInt(1));
					E1_tabla.executeUpdate();

				}
				if (!paciente.getEmail2().equals("")) {
					String queryE2 = "INSERT INTO  `"+dtbs+"`.`email` (`id_email` ,`email`)VALUES (NULL,?);";
					PreparedStatement E2 = con.prepareStatement(queryE2);

					E2.setString(1, paciente.getEmail2());
					E2.executeUpdate();

					Statement consultaE2 = con.createStatement();
					ResultSet consultaEmail2 = consultaE2.executeQuery("select id_email from "+dtbs+".email where email='" + paciente.getEmail2() + "'");

					consultaEmail2.next();

					String queryE2_tabla = "INSERT INTO  `"+dtbs+"`.`email_x_persona` (`persona_cod_persona` ,`email_id_email`)VALUES (?,?);";
					PreparedStatement E2_tabla = con.prepareStatement(queryE2_tabla);

					E2_tabla.setString(1, paciente.getId());
					E2_tabla.setInt(2, consultaEmail2.getInt(1));
					E2_tabla.executeUpdate();

				}

				if (!paciente.getTelefono1().equals("")) {
					String queryT1 = "INSERT INTO  `"+dtbs+"`.`telefono` (`id_telefono` ,`telefono`)VALUES (NULL,?);";
					PreparedStatement T1 = con.prepareStatement(queryT1);

					T1.setString(1, paciente.getTelefono1());
					T1.executeUpdate();

					Statement consultaT1 = con.createStatement();
					ResultSet consultaTel1 = consultaT1.executeQuery("select id_telefono from "+dtbs+".telefono where telefono='" + paciente.getTelefono1() + "'");

					consultaTel1.next();

					String queryT1_tabla = "INSERT INTO  `"+dtbs+"`.`telefono_x_persona` (`persona_cod_persona` ,`telefono_telefono`)VALUES (?,?);";
					PreparedStatement T1_tabla = con.prepareStatement(queryT1_tabla);

					T1_tabla.setString(1, paciente.getId());
					T1_tabla.setInt(2, consultaTel1.getInt(1));
					T1_tabla.executeUpdate();

				}
				if (!paciente.getTelefono2().equals("")) {
					String queryT2 = "INSERT INTO  `"+dtbs+"`.`telefono` (`id_telefono` ,`telefono`)VALUES (NULL,?);";
					PreparedStatement T2 = con.prepareStatement(queryT2);

					T2.setString(1, paciente.getTelefono2());
					T2.executeUpdate();

					Statement consultaT2 = con.createStatement();
					ResultSet consultaTel2 = consultaT2.executeQuery("select id_telefono from "+dtbs+".telefono where telefono='" + paciente.getTelefono2() + "'");

					consultaTel2.next();

					String queryT2_tabla = "INSERT INTO  `"+dtbs+"`.`telefono_x_persona` (`persona_cod_persona` ,`telefono_telefono`)VALUES (?,?);";
					PreparedStatement T2_tabla = con.prepareStatement(queryT2_tabla);

					T2_tabla.setString(1, paciente.getId());
					T2_tabla.setInt(2, consultaTel2.getInt(1));
					T2_tabla.executeUpdate();

				}
				closeConnection();
				System.err.println("ok conexion 2");return true;
			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 9> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoBacteriologo(OpBacteriologo opVentana, Bacteriologo bacteriologo) {
		conectar();

		try {
			Statement consulta = con.createStatement();
			ResultSet tabla = consulta.executeQuery("select * from "+dtbs+".bacteriologo where cod_Bacteriologo='" + bacteriologo.getId() + "'");

			if (tabla.next()) {
				opVentana.reportarError("bacteriologo ya registrado");
				closeConnection();
				return false;
			} else {

				Statement auxic = con.createStatement();
				ResultSet resultado = auxic.executeQuery("select idciudad from "+dtbs+".ciudad where nombre_ciudad like'%" + bacteriologo.getCiudad() + "%'");
				resultado.next();
				int auxiciudad = resultado.getInt(1);

				String pattern = "yyyy/MM/dd";
				SimpleDateFormat formateador = new SimpleDateFormat(pattern);

				String seleccio = "INSERT INTO `"+dtbs+"`.`persona` (`cod_persona`, `nombres`, `apellidos`, `fecha_nacimiento`, `genero`, `zona_residencial`, `ciudad`, `tipo_doc`, `direccion`) VALUES (?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(seleccio);
				ps.setString(1, bacteriologo.getId());
				ps.setString(2, bacteriologo.getNombres());
				ps.setString(3, bacteriologo.getApellidos());
				ps.setDate(4, new java.sql.Date(bacteriologo.getFechaNacimiento().getTime()));
				ps.setInt(5, bacteriologo.getGenero());
				ps.setInt(6, bacteriologo.getZonaResidencial());
				ps.setInt(7, auxiciudad);
				ps.setInt(8, bacteriologo.getTipoId());
				ps.setString(9, bacteriologo.getDireccion());
				ps.executeUpdate();

				File file = new File("C:/6342522/img/"+bacteriologo.getId()+".png");
				Image icon=bacteriologo.getFirma();
				BufferedImage image = new BufferedImage(icon.getWidth(null), icon.getHeight(null), BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2 = image.createGraphics();
				g2.drawImage(icon, 0, 0, null);
				g2.dispose();
				
				ImageIO.write(image, "png",file);
				
				String seleccion = "INSERT INTO `"+dtbs+"`.`bacteriologo` (`cod_Bacteriologo`, `registro`, `titulo`, `otros_estudios`, `inf_adicional`, `imagen`, `claveFirma`) VALUES (?,?,?,?,?,?,?);";
				PreparedStatement ps2 = con.prepareStatement(seleccion);

				ps2.setString(1, bacteriologo.getId());
				ps2.setString(2, bacteriologo.getRegistro());
				ps2.setString(3, bacteriologo.getTitulo());
				ps2.setString(4, bacteriologo.getOtrosEstudios());
				ps2.setString(5, bacteriologo.getInf_adicional());
				ps2.setBlob(6, bacteriologo.getFIS());
				ps2.setInt(7, bacteriologo.getClaveFirma());
				ps2.executeUpdate();

				if (!bacteriologo.getEmail1().equals("")) {
					String queryE1 = "INSERT INTO  `"+dtbs+"`.`email` (`id_email` ,`email`)VALUES (NULL,?);";
					PreparedStatement E1 = con.prepareStatement(queryE1);

					E1.setString(1, bacteriologo.getEmail1());
					E1.executeUpdate();

					Statement consultaE1 = con.createStatement();
					ResultSet consultaEmail1 = consultaE1.executeQuery("select id_email from "+dtbs+".email where email='" + bacteriologo.getEmail1() + "'");

					consultaEmail1.next();

					String queryE1_tabla = "INSERT INTO  `"+dtbs+"`.`email_x_persona` (`persona_cod_persona` ,`email_id_email`)VALUES (?,?);";
					PreparedStatement E1_tabla = con.prepareStatement(queryE1_tabla);

					E1_tabla.setString(1, bacteriologo.getId());
					E1_tabla.setInt(2, consultaEmail1.getInt(1));
					E1_tabla.executeUpdate();

				}
				if (!bacteriologo.getEmail2().equals("")) {
					String queryE2 = "INSERT INTO  `"+dtbs+"`.`email` (`id_email` ,`email`)VALUES (NULL,?);";
					PreparedStatement E2 = con.prepareStatement(queryE2);

					E2.setString(1, bacteriologo.getEmail2());
					E2.executeUpdate();

					Statement consultaE2 = con.createStatement();
					ResultSet consultaEmail2 = consultaE2.executeQuery("select id_email from "+dtbs+".email where email='" + bacteriologo.getEmail2() + "'");

					consultaEmail2.next();

					String queryE2_tabla = "INSERT INTO  `"+dtbs+"`.`email_x_persona` (`persona_cod_persona` ,`email_id_email`)VALUES (?,?);";
					PreparedStatement E2_tabla = con.prepareStatement(queryE2_tabla);

					E2_tabla.setString(1, bacteriologo.getId());
					E2_tabla.setInt(2, consultaEmail2.getInt(1));
					E2_tabla.executeUpdate();

				}

				if (!bacteriologo.getTelefono1().equals("")) {
					String queryT1 = "INSERT INTO  `"+dtbs+"`.`telefono` (`id_telefono` ,`telefono`)VALUES (NULL,?);";
					PreparedStatement T1 = con.prepareStatement(queryT1);

					T1.setString(1, bacteriologo.getTelefono1());
					T1.executeUpdate();

					Statement consultaT1 = con.createStatement();
					ResultSet consultaTel1 = consultaT1.executeQuery("select id_telefono from "+dtbs+".telefono where telefono='" + bacteriologo.getTelefono1() + "'");

					consultaTel1.next();

					String queryT1_tabla = "INSERT INTO  `"+dtbs+"`.`telefono_x_persona` (`persona_cod_persona` ,`telefono_telefono`)VALUES (?,?);";
					PreparedStatement T1_tabla = con.prepareStatement(queryT1_tabla);

					T1_tabla.setString(1, bacteriologo.getId());
					T1_tabla.setInt(2, consultaTel1.getInt(1));
					T1_tabla.executeUpdate();

				}
				if (!bacteriologo.getTelefono2().equals("")) {
					String queryT2 = "INSERT INTO  `"+dtbs+"`.`telefono` (`id_telefono` ,`telefono`)VALUES (NULL,?);";
					PreparedStatement T2 = con.prepareStatement(queryT2);

					T2.setString(1, bacteriologo.getTelefono2());
					T2.executeUpdate();

					Statement consultaT2 = con.createStatement();
					ResultSet consultaTel2 = consultaT2.executeQuery("select id_telefono from "+dtbs+".telefono where telefono='" + bacteriologo.getTelefono2() + "'");

					consultaTel2.next();

					String queryT2_tabla = "INSERT INTO  `"+dtbs+"`.`telefono_x_persona` (`persona_cod_persona` ,`telefono_telefono`)VALUES (?,?);";
					PreparedStatement T2_tabla = con.prepareStatement(queryT2_tabla);

					T2_tabla.setString(1, bacteriologo.getId());
					T2_tabla.setInt(2, consultaTel2.getInt(1));
					T2_tabla.executeUpdate();

				}
				closeConnection();
				System.err.println("ok conexion 2");return true;
			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 10> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoMedico(OpMedico opVentana, Medico medico) {
		conectar();

		try {
			Statement consulta = con.createStatement();
			Statement consulta2 = con.createStatement();
			ResultSet tabla = consulta.executeQuery("select * from "+dtbs+".medico where idmedico='" + medico.getIdmedico() + "'");
			ResultSet tabla1 = consulta2.executeQuery("select * from "+dtbs+".persona where cod_persona='" + medico.getId() + "'");

			if (tabla.next() || tabla1.next()) {
				opVentana.reportarError("Médico ya registrado");
				closeConnection();
				return false;
			} else {

				Statement auxic = con.createStatement();
				ResultSet resultado = auxic.executeQuery("select idciudad from "+dtbs+".ciudad where nombre_ciudad like'%" + medico.getCiudad() + "%'");
				resultado.next();
				int auxiciudad = resultado.getInt(1);

				String pattern = "yyyy/MM/dd";
				SimpleDateFormat formateador = new SimpleDateFormat(pattern);

				String seleccio = "INSERT INTO `"+dtbs+"`.`persona` (`cod_persona`, `nombres`, `apellidos`, `fecha_nacimiento`, `genero`, `zona_residencial`, `ciudad`, `tipo_doc`, `direccion`) VALUES (?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(seleccio);
				ps.setString(1, medico.getId());
				ps.setString(2, medico.getNombres());
				ps.setString(3, medico.getApellidos());
				ps.setDate(4, new java.sql.Date(medico.getFechaNacimiento().getTime()));
				ps.setInt(5, medico.getGenero());
				ps.setInt(6, medico.getZonaResidencial());
				ps.setInt(7, auxiciudad);
				ps.setInt(8, medico.getTipoId());
				ps.setString(9, medico.getDireccion());
				ps.executeUpdate();

				String seleccion = "INSERT INTO `"+dtbs+"`.`medico` (`idmedico`, `id_persona`, `especialidad`, `activo`) VALUES (?,?,?,?);";
				PreparedStatement ps2 = con.prepareStatement(seleccion);

				ps2.setString(1, medico.getIdmedico());
				ps2.setString(2, medico.getId());
				ps2.setInt(3, medico.getEspecialidad());
				ps2.setInt(4, medico.getActivo());
				ps2.executeUpdate();

				if (!medico.getEmail1().equals("")) {
					String queryE1 = "INSERT INTO  `"+dtbs+"`.`email` (`id_email` ,`email`)VALUES (NULL,?);";
					PreparedStatement E1 = con.prepareStatement(queryE1);

					E1.setString(1, medico.getEmail1());
					E1.executeUpdate();

					Statement consultaE1 = con.createStatement();
					ResultSet consultaEmail1 = consultaE1.executeQuery("select id_email from "+dtbs+".email where email='" + medico.getEmail1() + "'");

					consultaEmail1.next();

					String queryE1_tabla = "INSERT INTO  `"+dtbs+"`.`email_x_persona` (`persona_cod_persona` ,`email_id_email`)VALUES (?,?);";
					PreparedStatement E1_tabla = con.prepareStatement(queryE1_tabla);

					E1_tabla.setString(1, medico.getId());
					E1_tabla.setInt(2, consultaEmail1.getInt(1));
					E1_tabla.executeUpdate();

				}
				if (!medico.getEmail2().equals("")) {
					String queryE2 = "INSERT INTO  `"+dtbs+"`.`email` (`id_email` ,`email`)VALUES (NULL,?);";
					PreparedStatement E2 = con.prepareStatement(queryE2);

					E2.setString(1, medico.getEmail2());
					E2.executeUpdate();

					Statement consultaE2 = con.createStatement();
					ResultSet consultaEmail2 = consultaE2.executeQuery("select id_email from "+dtbs+".email where email='" + medico.getEmail2() + "'");

					consultaEmail2.next();

					String queryE2_tabla = "INSERT INTO  `"+dtbs+"`.`email_x_persona` (`persona_cod_persona` ,`email_id_email`)VALUES (?,?);";
					PreparedStatement E2_tabla = con.prepareStatement(queryE2_tabla);

					E2_tabla.setString(1, medico.getId());
					E2_tabla.setInt(2, consultaEmail2.getInt(1));
					E2_tabla.executeUpdate();

				}

				if (!medico.getTelefono1().equals("")) {
					String queryT1 = "INSERT INTO  `"+dtbs+"`.`telefono` (`id_telefono` ,`telefono`)VALUES (NULL,?);";
					PreparedStatement T1 = con.prepareStatement(queryT1);

					T1.setString(1, medico.getTelefono1());
					T1.executeUpdate();

					Statement consultaT1 = con.createStatement();
					ResultSet consultaTel1 = consultaT1.executeQuery("select id_telefono from "+dtbs+".telefono where telefono='" + medico.getTelefono1() + "'");

					consultaTel1.next();

					String queryT1_tabla = "INSERT INTO  `"+dtbs+"`.`telefono_x_persona` (`persona_cod_persona` ,`telefono_telefono`)VALUES (?,?);";
					PreparedStatement T1_tabla = con.prepareStatement(queryT1_tabla);

					T1_tabla.setString(1, medico.getId());
					T1_tabla.setInt(2, consultaTel1.getInt(1));
					T1_tabla.executeUpdate();

				}
				if (!medico.getTelefono2().equals("")) {
					String queryT2 = "INSERT INTO  `"+dtbs+"`.`telefono` (`id_telefono` ,`telefono`)VALUES (NULL,?);";
					PreparedStatement T2 = con.prepareStatement(queryT2);

					T2.setString(1, medico.getTelefono2());
					T2.executeUpdate();

					Statement consultaT2 = con.createStatement();
					ResultSet consultaTel2 = consultaT2.executeQuery("select id_telefono from "+dtbs+".telefono where telefono='" + medico.getTelefono2() + "'");

					consultaTel2.next();

					String queryT2_tabla = "INSERT INTO  `"+dtbs+"`.`telefono_x_persona` (`persona_cod_persona` ,`telefono_telefono`)VALUES (?,?);";
					PreparedStatement T2_tabla = con.prepareStatement(queryT2_tabla);

					T2_tabla.setString(1, medico.getId());
					T2_tabla.setInt(2, consultaTel2.getInt(1));
					T2_tabla.executeUpdate();

				}
		closeConnection();
				System.err.println("ok conexion 2");return true;
		
			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 11> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevaEmpresa(OpEmpresa opVentana, Empresa empresa) {
		conectar();

		try {
			Statement consulta = con.createStatement();
			ResultSet tabla = consulta.executeQuery("select * from "+dtbs+".empresa where doc_empresa='" + empresa.getDocEmpresa() + "'");

			if (tabla.next()) {
				opVentana.reportarError("Ya existe una empresa registrda con este NIT");
				closeConnection();
				return false;
			} else {

				Statement auxic = con.createStatement();
				ResultSet resultado = auxic.executeQuery("select idciudad from "+dtbs+".ciudad where nombre_ciudad like'%" + empresa.getCiudad() + "%'");
				resultado.next();
				int auxiciudad = resultado.getInt(1);

				Statement auxic1 = con.createStatement();
				ResultSet resultado1 = auxic1.executeQuery("select idtarifas from "+dtbs+".tarifas where descripcion like'%" + empresa.getTarifa() + "%'");
				resultado1.next();
				int auxitarifa = resultado1.getInt(1);

			

				String seleccio = "INSERT INTO `"+dtbs+"`.`empresa` (`doc_empresa`, `razon_social`, `direccion`, `ciudad`, `dependencia_cobro`, `tarifa`, `descuento`, `cod_eps`, `tipo_usuario`, `adicional`, `requisitos_recepcion`, `activo`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(seleccio);
				ps.setString(1, empresa.getDocEmpresa());
				ps.setString(2, empresa.getRazonSocial());
				ps.setString(3, empresa.getDireccion());
				ps.setInt(4, auxiciudad);
				ps.setString(5, empresa.getDependenciaCobro());
				ps.setInt(6, auxitarifa);
				ps.setInt(7, empresa.getDescuento());
				ps.setString(8, empresa.getCodEps());
				ps.setInt(9, empresa.getTipoUsuario());
				ps.setString(10, empresa.getAdicional());
				ps.setString(11, empresa.getRequisitosRecepcion());
				ps.setInt(12, empresa.getActivo());

				ps.executeUpdate();
				
				String seleccion3 = "INSERT INTO `"+dtbs+"`.`usuarioweb` (`usuario`, `pass`, `tipoUsuario`) VALUES (?,?,?);";
				PreparedStatement ps3 = con.prepareStatement(seleccion3);

				ps3.setString(1, empresa.getDocEmpresa());
				ps3.setString(2, empresa.getDocEmpresa());
				ps3.setInt(3, 2);
				ps3.executeUpdate();
				
				

				if (!empresa.getEmail1().equals("")) {
					String queryE1 = "INSERT INTO  `"+dtbs+"`.`email` (`id_email` ,`email`)VALUES (NULL,?);";
					PreparedStatement E1 = con.prepareStatement(queryE1);

					E1.setString(1, empresa.getEmail1());
					E1.executeUpdate();

					Statement consultaE1 = con.createStatement();
					ResultSet consultaEmail1 = consultaE1.executeQuery("select id_email from "+dtbs+".email where email='" + empresa.getEmail1() + "'");

					consultaEmail1.next();

					String queryE1_tabla = "INSERT INTO  `"+dtbs+"`.`email_x_empresa` (`empresa_id_empresa` ,`email_id_email`)VALUES (?,?);";
					PreparedStatement E1_tabla = con.prepareStatement(queryE1_tabla);

					Statement consultaEm1 = con.createStatement();
					ResultSet consultaEmpre1 = consultaEm1.executeQuery("select id_empresa from "+dtbs+".empresa where doc_empresa='" + empresa.getDocEmpresa() + "'");
					consultaEmpre1.next();

					E1_tabla.setInt(1, consultaEmpre1.getInt(1));
					E1_tabla.setInt(2, consultaEmail1.getInt(1));
					E1_tabla.executeUpdate();

				}
				if (!empresa.getEmail2().equals("")) {
					String queryE2 = "INSERT INTO  `"+dtbs+"`.`email` (`id_email` ,`email`)VALUES (NULL,?);";
					PreparedStatement E2 = con.prepareStatement(queryE2);

					E2.setString(1, empresa.getEmail2());
					E2.executeUpdate();

					Statement consultaE2 = con.createStatement();
					ResultSet consultaEmail2 = consultaE2.executeQuery("select id_email from "+dtbs+".email where email='" + empresa.getEmail2() + "'");

					consultaEmail2.next();

					String queryE2_tabla = "INSERT INTO  `"+dtbs+"`.`email_x_empresa` (`empresa_id_empresa` ,`email_id_email`)VALUES (?,?);";
					PreparedStatement E2_tabla = con.prepareStatement(queryE2_tabla);

					Statement consultaEm1 = con.createStatement();
					ResultSet consultaEmpre1 = consultaEm1.executeQuery("select id_empresa from "+dtbs+".empresa where doc_empresa='" + empresa.getDocEmpresa() + "'");
					consultaEmpre1.next();

					E2_tabla.setInt(1, consultaEmpre1.getInt(1));
					E2_tabla.setInt(2, consultaEmail2.getInt(1));
					E2_tabla.executeUpdate();

				}

				if (!empresa.getTelefono1().equals("")) {
					String queryT1 = "INSERT INTO  `"+dtbs+"`.`telefono` (`id_telefono` ,`telefono`)VALUES (NULL,?);";
					PreparedStatement T1 = con.prepareStatement(queryT1);

					T1.setString(1, empresa.getTelefono1());
					T1.executeUpdate();

					Statement consultaT1 = con.createStatement();
					ResultSet consultaTel1 = consultaT1.executeQuery("select id_telefono from "+dtbs+".telefono where telefono='" + empresa.getTelefono1() + "'");

					consultaTel1.next();

					String queryT1_tabla = "INSERT INTO  `"+dtbs+"`.`telefono_x_empresa` (`empresa_id_empresa` ,`telefono_telefono`)VALUES (?,?);";
					PreparedStatement T1_tabla = con.prepareStatement(queryT1_tabla);

					Statement consultaEm1 = con.createStatement();
					ResultSet consultaEmpre1 = consultaEm1.executeQuery("select id_empresa from "+dtbs+".empresa where doc_empresa='" + empresa.getDocEmpresa() + "'");
					consultaEmpre1.next();

					T1_tabla.setInt(1, consultaEmpre1.getInt(1));
					T1_tabla.setInt(2, consultaTel1.getInt(1));
					T1_tabla.executeUpdate();

				}
				if (!empresa.getTelefono2().equals("")) {
					String queryT2 = "INSERT INTO  `"+dtbs+"`.`telefono` (`id_telefono` ,`telefono`)VALUES (NULL,?);";
					PreparedStatement T2 = con.prepareStatement(queryT2);

					T2.setString(1, empresa.getTelefono2());
					T2.executeUpdate();

					Statement consultaT2 = con.createStatement();
					ResultSet consultaTel2 = consultaT2.executeQuery("select id_telefono from "+dtbs+".telefono where telefono='" + empresa.getTelefono2() + "'");

					consultaTel2.next();

					String queryT2_tabla = "INSERT INTO  `"+dtbs+"`.`telefono_x_empresa` (`empresa_id_empresa` ,`telefono_telefono`)VALUES (?,?);";
					PreparedStatement T2_tabla = con.prepareStatement(queryT2_tabla);

					Statement consultaEm1 = con.createStatement();
					ResultSet consultaEmpre1 = consultaEm1.executeQuery("select id_empresa from "+dtbs+".empresa where doc_empresa='" + empresa.getDocEmpresa() + "'");
					consultaEmpre1.next();

					T2_tabla.setInt(1, consultaEmpre1.getInt(1));
					T2_tabla.setInt(2, consultaTel2.getInt(1));
					T2_tabla.executeUpdate();

				}
				closeConnection();
				System.err.println("ok conexion 2");return true;
			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 12> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoGrupoEmpresa(OpGrupoEmpresa opVentana, GruposEmpresas grupoEmpresa) {
		conectar();

		try {
			Statement consulta = con.createStatement();
			ResultSet tabla = consulta.executeQuery("select * from "+dtbs+".sub_grupo_empresa where nombre_empresa='" + grupoEmpresa.getNombreGrupo() + "'");

			if (tabla.next()) {
				opVentana.reportarError("Ya existe un grupo de empresa con este nombre.");
				closeConnection();
				return false;
			} else {

				Statement auxic1 = con.createStatement();
				ResultSet resultado1 = auxic1.executeQuery("select idtarifas from "+dtbs+".tarifas where descripcion like'%" + grupoEmpresa.getTarifa() + "%'");
				resultado1.next();
				int auxitarifa = resultado1.getInt(1);

				Statement auxic2 = con.createStatement();
				ResultSet resultado2 = auxic2.executeQuery("select id_empresa from "+dtbs+".empresa where razon_social like'%" + grupoEmpresa.getEmpresa() + "%'");
				resultado2.next();
				int auxiempresa = resultado2.getInt(1);

				String seleccio = "INSERT INTO `"+dtbs+"`.`sub_grupo_empresa` (`nombre_empresa`, `codEmpresa`, `codTarifa`) VALUES (?,?,?)";
				PreparedStatement ps = con.prepareStatement(seleccio);
				ps.setString(1, grupoEmpresa.getNombreGrupo());
				ps.setInt(2, auxiempresa);
				ps.setInt(3, auxitarifa);

				ps.executeUpdate();

				closeConnection();
				System.err.println("ok conexion 2");return true;
			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 13> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoTD(OpTipoDato opVentana, TipoDato TD) {
		conectar();

		try {
			Statement consulta = con.createStatement();
			ResultSet tabla = consulta.executeQuery("select * from "+dtbs+".tipo_de_datos where Nombre='" + TD.getNombreTD() + "'");

			if (tabla.next()) {
				opVentana.reportarError("Ya existe un tipo de dato con este nombre.");
				closeConnection();
				return false;
			} else {

				String seleccio = "INSERT INTO `"+dtbs+"`.`tipo_de_datos` (`Nombre`, `Sigla`) VALUES (?,?)";
				PreparedStatement ps = con.prepareStatement(seleccio);
				ps.setString(1, TD.getNombreTD());
				ps.setString(2, TD.getSiglaTD().toUpperCase());

				ps.executeUpdate();

				closeConnection();
				System.err.println("ok conexion 2");return true;
			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 14> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevSeccion(OpSecciones opVentana, Secciones seccion) {
		conectar();

		try {
			Statement consulta = con.createStatement();
			ResultSet tabla = consulta.executeQuery("select * from "+dtbs+".secciones where nombre='" + seccion.getNombre() + "'");

			if (tabla.next()) {
				opVentana.reportarError("Ya existe una sección con este nombre.");
				closeConnection();
				return false;
			} else {

				String seleccio = "INSERT INTO `"+dtbs+"`.`secciones` (`nombre`, `sigla`) VALUES (?,?)";
				PreparedStatement ps = con.prepareStatement(seleccio);
				ps.setString(1, seccion.getNombre());
				ps.setString(2, seccion.getSigla().toUpperCase());

				ps.executeUpdate();

				closeConnection();
				System.err.println("ok conexion 2");return true;
			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 15> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoProtocolo(OpProtocolo opVentana, protocolo protocolo, ArrayList<itemProtocolo> paraAgregar) {
		conectar();

		try {
			Statement consulta = con.createStatement();
			ResultSet tabla = consulta.executeQuery("select * from "+dtbs+".protocolo where codigo='" + protocolo.getCodigo() + "'");

			if (tabla.next()) {
				opVentana.reportarError("Ya existe una sección con este código.");
				closeConnection();
				return false;
			} else {

				String seleccio = "INSERT INTO `"+dtbs+"`.`protocolo` (`codigo`, `nombre`, `cod_seccion`,`id_planilla`,`orden`) VALUES (?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(seleccio);
				ps.setString(1, protocolo.getCodigo());
				ps.setString(2, protocolo.getNombre());
				ps.setInt(3, protocolo.getCodSeccion());
				ps.setInt(4, protocolo.getIdPlanilla());
				ps.setInt(5, protocolo.getOrden());
				ps.executeUpdate();

				Statement consulta1 = con.createStatement();
				ResultSet tabla1 = consulta1.executeQuery("select * from "+dtbs+".protocolo where codigo='" + protocolo.getCodigo() + "'");
				tabla1.next();
				int auxiCodProtocolo = tabla1.getInt(1);

				for (int i = 0; i < paraAgregar.size(); i++) {
					itemProtocolo IP = paraAgregar.get(i);

					String seleccion = "INSERT INTO `"+dtbs+"`.`item_protocolo` (`cod_protocolo`, `Nombre`,`cod_tipo_de_dato`,`Unidad_de_medida`,`Abreviatura_unidad`,`Valor_normal`,`Desde`,`Hasta`,`generos`,`orden`,`interpretacion`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement ps1 = con.prepareStatement(seleccion);
					ps1.setInt(1, auxiCodProtocolo);
					ps1.setString(2, IP.getNombre());
					ps1.setInt(3, IP.getCodTD());
					ps1.setString(4, IP.getUnidad());
					ps1.setString(5, IP.getAbreviatura());
					ps1.setString(6, IP.getValor());
					ps1.setString(7, IP.getDesde()+"");
					ps1.setString(8, IP.getHasta()+"");
					ps1.setInt(9, IP.getGenero());
					ps1.setInt(10, IP.getOrden());
					ps1.setString(11, IP.getInterpreta());
					ps1.executeUpdate();
				}

				closeConnection();
				System.err.println("ok conexion 2");return true;
			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 16> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoExamen(OpExamen opVentana, Examen examen, ArrayList<itemTarifa> paraAgregar,int[] dias) {
		conectar();

		try {
			Statement consulta = con.createStatement();
			ResultSet tabla = consulta.executeQuery("select * from "+dtbs+".examen where codigo='" + examen.getSigla() + "'");

			if (tabla.next()) {
				opVentana.reportarError("Ya existe un examen con este código.");
				closeConnection();
				return false;
			} else {
				
				String seleccio = "INSERT INTO `"+dtbs+"`.`tabla_dias` (`lunes`, `martes`, `miercoles`,`jueves`,`viernes`,`sabado`,`domingo`) VALUES (?,?,?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(seleccio);
				ps.setInt(1, dias[0]);
				ps.setInt(2, dias[1]);
				ps.setInt(3, dias[2]);
				ps.setInt(4, dias[3]);
				ps.setInt(5, dias[4]);
				ps.setInt(6, dias[5]);
				ps.setInt(7, dias[6]);
				ps.executeUpdate();
				
				Statement consulta1 = con.createStatement();
				ResultSet tabla1 = consulta1.executeQuery("SELECT MAX( idTabla_dias ) FROM tabla_dias");
				tabla1.next();
				String seleccion = "INSERT INTO `"+dtbs+"`.`examen` (`Nombre`, `Cups`, `Duracion_dias`,`Nivel`,`id_muestra`,`id_dias`,`cod_protocolo`,`codigo`,`cod_Bayer`,`se_remite`) VALUES (?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps1 = con.prepareStatement(seleccion);
				ps1.setString(1, examen.getNombre());
				ps1.setInt(2, examen.getCups());
				ps1.setInt(3, examen.getDuracion());
				ps1.setInt(4, examen.getNivel());
				ps1.setInt(5, examen.getIdMuestra());
				ps1.setInt(6, tabla1.getInt(1));
				ps1.setInt(7, examen.getCodProtocolo());
				ps1.setString(8, examen.getSigla());
				ps1.setString(9, examen.getCodBayer());
				ps1.setInt(10, examen.getSeRemite());
				ps1.executeUpdate();

				Statement consulta2 = con.createStatement();
				ResultSet tabla2 = consulta2.executeQuery("SELECT MAX( idExamen ) FROM examen");
				tabla2.next();
				for (int i = 0; i < paraAgregar.size(); i++) {
					itemTarifa IT = paraAgregar.get(i);

					String seleccion2= "INSERT INTO `"+dtbs+"`.`tabla_tarifas` (`cod_examen`, `Cod_tarifa`,`Valor`,`Recarg_urgencias`,`Recarg_festivo`,`Recarg_especial`) VALUES (?,?,?,?,?,?)";
					PreparedStatement ps2 = con.prepareStatement(seleccion2);
					ps2.setInt(1,tabla2.getInt(1) );
					ps2.setInt(2,IT.getCodTarifa() );
					ps2.setInt(3,IT.getValor() );
					ps2.setInt(4,IT.getRecargaUrgencias() );
					ps2.setInt(5,IT.getRecargaFestivos());
					ps2.setInt(6,IT.getRecargaEspecial());
					ps2.executeUpdate();
				}

				closeConnection();
				System.err.println("ok conexion 2");return true;
			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 17> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoRecepcion(OpItemRecepcion opVentana, RecepcionCompleta RC) {
		conectar();

		try {
			ResultSet tablaIF = null;
				Recepcion recepcion=RC.getRecepcion();
				Factura factura=RC.getFactura();
				ArrayList<itemFactura> itemsFactura = RC.getItemsFactura();
				
				
				
				String seleccio = "INSERT INTO `"+dtbs+"`.`recepcion` (`codPaciente`, `sede`, `numeroMuestra`, `codempresa`, `codTairfa`, `Autorizacion`, `numOrden`, `fechaOrden`, `codSubEmpresa`, `codPyP`, `codSala`, `cama`, `codTipoUsuario`, `codAmbitoProcedimiento`, `codFinalidadProcedimiento`, `codMedico`, `fechaTomaMuestra`, `fechaRecepcion`, `Observaciones`, `recepcionadoPor`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(seleccio);
				ps.setString(1, recepcion.getPaciente().getId());
				ps.setString(2, recepcion.getSede());
				ps.setInt(3, recepcion.getNumeroMuestra());
				ps.setInt(4, recepcion.getEmpresa().getIdEmpresa());
				ps.setInt(5, recepcion.getTarifa().getIdTarifa());
				ps.setString(6, recepcion.getAutorizacion());
				ps.setString(7, recepcion.getNumeroOrden());
				ps.setDate(8, new java.sql.Date(recepcion.getFechaOrden().getTime()));				
				ps.setInt(9, recepcion.getCodSubEmpresa());
				ps.setInt(10, recepcion.getCodEPyP());
				ps.setInt(11, recepcion.getCodSala());
				ps.setInt(12, recepcion.getCama());
				ps.setInt(13, recepcion.getCodTipoUsuario());
				ps.setInt(14, recepcion.getCodAmbito());
				ps.setInt(15, recepcion.getCodFinalidad());
				ps.setString(16, recepcion.getCodMedico());
				ps.setDate(17, new java.sql.Date(recepcion.getFechaTomaMuestra().getTime()));
				ps.setDate(18, new java.sql.Date(recepcion.getFechaRecepcion().getTime()));
				ps.setString(19, recepcion.getObservaciones());
				ps.setString(20,Principal.getInstancePrincipal().getUsuario().getNombre());
				
				ps.executeUpdate();
				
				Statement consulta1 = con.createStatement();
				ResultSet tabla1 = consulta1.executeQuery("SELECT MAX( idRecepcion ) FROM recepcion");
				tabla1.next();
				
				String seleccion = "INSERT INTO `"+dtbs+"`.`facturas` (`codRecepcion`, `codFormadePago`, `descuento`, `codTipoPago`, `abono`, `saldo`, `totalSinD`, `totalConD`, `observaciones`) VALUES (?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps1 = con.prepareStatement(seleccion);
				
				ps1.setInt(1, tabla1.getInt(1));
				ps1.setInt(2, factura.getCodFormadePago());
				ps1.setInt(3, factura.getDescuentoPorciento());
				ps1.setInt(4, factura.getCodTipoPago());
				ps1.setInt(5, factura.getAbono());
				ps1.setInt(6, factura.getSaldo());
				ps1.setInt(7, factura.getTotalSinD());
				ps1.setInt(8, factura.getTotalConD());
				ps1.setString(9, factura.getObservaciones());
				ps1.executeUpdate();

				Statement consulta2 = con.createStatement();
				ResultSet tabla2 = consulta2.executeQuery("SELECT MAX( 	idfactura ) FROM facturas");
				tabla2.next();
				//ObtenerFechaColombia OFC=new ObtenerFechaColombia();
				//	ps.setDate(8, new java.sql.Date(OFC.fechaColombia().getTime()));
				
			for (int i = 0; i < itemsFactura.size(); i++) {
				itemFactura IF=itemsFactura.get(i);
				
				String seleccionIF= "INSERT INTO `"+dtbs+"`.`itemfacturayrecepcion` (`codExamen`, `RU`, `RF`, `RE`, `valor`, `orden` ) VALUES (?,?,?,?,?,?)";
				PreparedStatement psIF = con.prepareStatement(seleccionIF);
				
				psIF.setString(1, IF.getCodExamen());
				psIF.setInt(2, IF.getRU());
				psIF.setInt(3, IF.getRF());
				psIF.setInt(4, IF.getRE());
				psIF.setInt(5, IF.getValor());
				psIF.setInt(6, IF.getOrden());
			
	
				psIF.executeUpdate();
				
				Statement consultaIF = con.createStatement();
				 tablaIF = consultaIF.executeQuery("SELECT MAX( 	idItemFactura  ) FROM itemfacturayrecepcion");
				tablaIF.next();
				
				String seleccionIxF= "INSERT INTO `"+dtbs+"`.`items_x_factura` (`idFactura`, `idItemFactura` ) VALUES (?,?)";
				PreparedStatement psIxF = con.prepareStatement(seleccionIxF);
				
				psIxF.setInt(1, tabla2.getInt(1));
				psIxF.setInt(2, tablaIF.getInt(1));
			
	
				psIxF.executeUpdate();
				ArrayList<ItemRecepcion> itemsRecepcion=IF.getItemsExamenes();
				for (int j = 0; j < itemsRecepcion.size(); j++) {
					ItemRecepcion IR = itemsRecepcion.get(j);

					String seleccion2= "INSERT INTO `"+dtbs+"`.`item_examenrecepcion` (`idExamen`,`concepto`, `idItemProtocolo`, `Valor_normal`, `Desde`, `Hasta`, `valorResultado`, `tipoDato`, `genero`, `orden`, `Unidad_de_medida`,`interpretacion` ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement ps2 = con.prepareStatement(seleccion2);
					
				
					ps2.setString(1, IR.getIdExamen());
					ps2.setString(2, IR.getConcepto());
					ps2.setInt(3, IR.getIdProtocolo());
					ps2.setString(4, IR.getValorNormal());
					ps2.setString(5, IR.getDesde()+"");
					ps2.setString(6, IR.getHasta()+"");
					ps2.setString(7, IR.getValorResultado());
					ps2.setInt(8, IR.getTipoDato());
					ps2.setInt(9, IR.getGenero());
					ps2.setInt(10, IR.getOrden());
					ps2.setString(11, IR.getUnidadMedida());
					ps2.setString(12, IR.getInterpretacion());
		
					ps2.executeUpdate();
					
					Statement consultaAuxi = con.createStatement();
					ResultSet tablaAuxi = consultaAuxi.executeQuery("SELECT MAX(	idItemExamenRecepcion) FROM item_examenrecepcion");
					tablaAuxi.next();
					
					String seleccionAuxi= "INSERT INTO `"+dtbs+"`.`ier_x_recepcion` (`idItemFacturayRecepcion`, `idItemExamenRecepcion`) VALUES (?,?)";
					PreparedStatement psAuxi = con.prepareStatement(seleccionAuxi);
					
					psAuxi.setInt(1,tablaIF.getInt(1) );
					psAuxi.setInt(2, tablaAuxi.getInt(1));
					
					psAuxi.executeUpdate();
					
				}
			}
				

				

				closeConnection();
				System.err.println("ok conexion 2");return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 18> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean reportar(OpReportarResultados opVentana, ArrayList<Reporte> paraReportar,int idItemFactura ) {
		conectar();

		try {	
			
			for (int i = 0; i < paraReportar.size(); i++) {
				Reporte reporte=paraReportar.get(i);
				Statement ps = con.createStatement();
				ps.executeUpdate("update   `"+dtbs+"`.`item_examenrecepcion` SET `reportado` =  '1', `valorResultado` =  '"+reporte.getValorResultado()+"' WHERE  `item_examenrecepcion`.`idItemExamenRecepcion` ="+reporte.getIdItemRecepcion());	
			}
			ObtenerFechaColombia of=new ObtenerFechaColombia();
			Statement ps1 = con.createStatement();
			
			ps1.executeUpdate("UPDATE  `"+dtbs+"`.`itemfacturayrecepcion` SET  `reportado` =  '1', `fechaReporte` =  '"+new java.sql.Timestamp(of.fechaColombia().getTime())+"' WHERE  `itemfacturayrecepcion`.`idItemFactura` ="+idItemFactura);
			
			
		closeConnection();
		System.err.println("ok conexion 2");return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 19> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}
	
	public boolean escribirObserva(final OpReportarResultados opVentana,int idItemFactura,final String observa ) {
		conectar();

		try {	
			
			Statement ps1 = con.createStatement();
			
			ps1.executeUpdate("UPDATE  `"+dtbs+"`.`itemfacturayrecepcion` SET  `Observaciones` =  '"+observa+"'  WHERE  `itemfacturayrecepcion`.`idItemFactura` ="+idItemFactura);
			
			
		closeConnection();
		
			
		return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 19.1> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}
	
	public boolean reportar(OpReporte7600 opVentana, ArrayList<Reporte> paraReportar,int idItemFactura ) {
		conectar();

		try {	
			
			for (int i = 0; i < paraReportar.size(); i++) {
				Reporte reporte=paraReportar.get(i);
				Statement ps = con.createStatement();
				ps.executeUpdate("update   `"+dtbs+"`.`item_examenrecepcion` SET `reportado` =  '1', `valorResultado` =  '"+reporte.getValorResultado()+"' WHERE  `item_examenrecepcion`.`idItemExamenRecepcion` ="+reporte.getIdItemRecepcion());	
			}
			ObtenerFechaColombia of=new ObtenerFechaColombia();
			Statement ps1 = con.createStatement();
			
			ps1.executeUpdate("UPDATE  `"+dtbs+"`.`itemfacturayrecepcion` SET  `reportado` =  '1', `fechaReporte` =  '"+new java.sql.Timestamp(of.fechaColombia().getTime())+"' WHERE  `itemfacturayrecepcion`.`idItemFactura` ="+idItemFactura);
			
			
		closeConnection();
		System.err.println("ok conexion 2");return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 19> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean reportarEntrega(OpRegistroEntrega opVentana, RecepcionCompleta RC,String obs , ArrayList<itemFactura> items) {
		conectar();

		try {	
			
			for (int i = 0; i < items.size(); i++) {
				itemFactura IF=items.get(i);
				
				ObtenerFechaColombia OFC=new ObtenerFechaColombia();
				
				
				Statement ps = con.createStatement();
				ps.executeUpdate("update   `"+dtbs+"`.`itemfacturayrecepcion` SET `entregado` =  '1', `entregadoA` =  '"+IF.getEntregadoA()+"', `fechaEntrega` =  '"+new java.sql.Timestamp(OFC.fechaColombia().getTime())+"' WHERE  `itemfacturayrecepcion`.`idItemFactura` ="+IF.getIdItemAI());	
				
				String seleccio = "INSERT INTO `"+dtbs+"`.`reporte_entrega` (`codRecepcion`, `idItemFactura`, `nombreReceptor`, `observaciones`) VALUES (?,?,?,?)";
			PreparedStatement ps2 = con.prepareStatement(seleccio);
			ps2.setInt(1, RC.getRecepcion().getIdREcepcion());
			ps2.setInt(2, IF.getIdItemAI());
			ps2.setString(3, IF.getEntregadoA());
			ps2.setString(4, obs);

			ps2.executeUpdate();
			
			}
		
			
		closeConnection();
		System.err.println("ok conexion 2");return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 20> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean reportarImpreso(final imprimirReporte opVentana, itemFactura IF) {
		conectar();

		try {	
			

			
			
			
			
			Statement ps = con.createStatement();
			ps.executeUpdate("update   `"+dtbs+"`.`itemfacturayrecepcion` SET `impreso` =  '1'  WHERE  `itemfacturayrecepcion`.`idItemFactura` ="+IF.getIdItemAI());	
			
					
			
		closeConnection();
		System.err.println("conexion 2 ok");return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 2 20> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}
	
	public boolean nuevoCotizacion(OpItemCotizacion opVentana, Cotizacion coti) {
		conectar();

		try {
			ResultSet tablaIF = null;
				
				ArrayList<itemCotizacion> items = coti.getItems();
				
			
				
				String seleccion = "INSERT INTO `"+dtbs+"`.`cotizacion` (`aNombre`, `codTarifa`, `descuento`, `totalSinD`, `totalConD`, `observaciones`, `direccion`, `telefono`) VALUES (?,?,?,?,?,?,?,?)";
				PreparedStatement ps1 = con.prepareStatement(seleccion);
				
				ps1.setString(1, coti.getCotizadoA());
				ps1.setInt(2, coti.getCodTarifa());
				ps1.setInt(3, coti.getDescuento());
				ps1.setInt(4, coti.getTotalSinD());
				ps1.setInt(5, coti.getTotalConD());
				ps1.setString(6, coti.getObs());
				ps1.setString(7, coti.getDireccion());
				ps1.setString(8, coti.getTelefono());
				ps1.executeUpdate();

				Statement consulta2 = con.createStatement();
				ResultSet tabla2 = consulta2.executeQuery("SELECT MAX( 	idcotizacion ) FROM cotizacion");
				tabla2.next();
				
				
			for (int i = 0; i < items.size(); i++) {
				itemCotizacion IC=items.get(i);
				
				String seleccionIC= "INSERT INTO `"+dtbs+"`.`examen_x_cotizacion` (`idCotizacion`,`codExamen`, `RU`, `RF`, `RE`, `valor`, `orden` ) VALUES (?,?,?,?,?,?,?)";
				PreparedStatement psIC = con.prepareStatement(seleccionIC);
				int codExamen=conexionBusqueda.getInstance().examenXcodigo(IC.getCodExamen()).getIdExamen();
				psIC.setInt(1, tabla2.getInt(1));
				psIC.setInt(2, codExamen);
				psIC.setInt(3, IC.getRU());
				psIC.setInt(4, IC.getRF());
				psIC.setInt(5, IC.getRE());
				psIC.setInt(6, IC.getValor());
				psIC.setInt(7, IC.getOrden());
				psIC.executeUpdate();
				
		
			}
				

				

				closeConnection();
				System.err.println("ok conexion 2");return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 21> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	// #############################################################################

	public boolean nuevoTipoMuestra(OpTipoMuestras opVentana, TipoMuestra muestra) {
		conectar();

		try {
			Statement consulta = con.createStatement();
			ResultSet tabla = consulta.executeQuery("select * from "+dtbs+".tipo_muestras  where sigla='" + muestra.getSigla() + "'");

			if (tabla.next()) {
				opVentana.reportarError("Ya existe un tipo de muestra con esta sigla.");
				closeConnection();
				return false;
			} else {

				String seleccio = "INSERT INTO `"+dtbs+"`.`tipo_muestras` (`Nombre`, `sigla`) VALUES (?,?)";
				PreparedStatement ps = con.prepareStatement(seleccio);
				ps.setString(1,muestra.getNombre());
				ps.setString(2, muestra.getSigla());

				ps.executeUpdate();

				closeConnection();
				System.err.println("ok conexion 2");return true;
			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 22> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoCiudad(OpCiudades opVentana, String  ciudad) {
		conectar();

		try {	
			
			String seleccio = "INSERT INTO `"+dtbs+"`.`ciudad` (`nombre_ciudad`) VALUES (?)";
		PreparedStatement ps = con.prepareStatement(seleccio);
		ps.setString(1,ciudad);

		ps.executeUpdate();

		closeConnection();
		System.err.println("ok conexion 2");return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 23> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoPerfilExamenes(OpPerfiles opVentana, PerfilExamenes perfil) {
		conectar();

		try {	
			
			String seleccio = "INSERT INTO `"+dtbs+"`.`perfiles` (`Nombre`) VALUES (?)";
		PreparedStatement ps = con.prepareStatement(seleccio);
		ps.setString(1,perfil.getNombrePerfil());

		ps.executeUpdate();
		
		Statement consulta1 = con.createStatement();
		ResultSet tabla1 = consulta1.executeQuery("SELECT MAX( idPerfiles ) FROM perfiles");
		tabla1.next();
		
		
		ArrayList<Examen> Items=perfil.getExamenesDelPerfil();
		for (int i = 0; i < Items.size(); i++) {
			Examen examen=Items.get(i);
			
			String seleccio1 = "INSERT INTO `"+dtbs+"`.`item_x_perfiles` (`Perfiles_idPerfiles`, `Examen_idExamen`) VALUES (?,?)";
			PreparedStatement ps1 = con.prepareStatement(seleccio1);
			ps1.setInt(1,tabla1.getInt(1));
			ps1.setInt(2,examen.getIdExamen());

			ps1.executeUpdate();

		}

		closeConnection();
		System.err.println("ok conexion 2");return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 24> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoSala(OpSalas opVentana, String sigla, String Nombre) {
		conectar();

		try {	
			
		String seleccio = "INSERT INTO `"+dtbs+"`.`sala` (`sigla`,`descripcion`) VALUES (?,?)";
		PreparedStatement ps = con.prepareStatement(seleccio);
		
		ps.setString(1,sigla);
		ps.setString(2,Nombre);

		ps.executeUpdate();

		closeConnection();
		System.err.println("ok conexion 2");return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 25> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoParentesco(OpParentesco opVentana, String  parentesco) {
		conectar();

		try {	
			
			String seleccio = "INSERT INTO `"+dtbs+"`.`tipo_parentesco` (`descaripcion`) VALUES (?)";
		PreparedStatement ps = con.prepareStatement(seleccio);
		ps.setString(1,parentesco);

		ps.executeUpdate();

		closeConnection();
		System.err.println("ok conexion 2");return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 26> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoPlanPYP(OpPyP opVentana, String  nombre) {
		conectar();

		try {	
			
			String seleccio = "INSERT INTO `"+dtbs+"`.`planes_pyp` (`descripcion`) VALUES (?)";
		PreparedStatement ps = con.prepareStatement(seleccio);
		ps.setString(1,nombre);

		ps.executeUpdate();

		closeConnection();
		System.err.println("ok conexion 2");return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 27> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoEspecialidad(OpEspecialidades opVentana, String sigla, String Nombre) {
		conectar();

		try {	
			
		String seleccio = "INSERT INTO `"+dtbs+"`.`tipo_especialidad` (`sigla`,`descripcion`) VALUES (?,?)";
		PreparedStatement ps = con.prepareStatement(seleccio);
		
		ps.setString(1,sigla);
		ps.setString(2,Nombre);

		ps.executeUpdate();

		closeConnection();
		System.err.println("ok conexion 2");return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 28> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoLaboratorio(OpLaboratorios opVentana, Laboratorio lab) {
		conectar();

		try {
			Statement consulta = con.createStatement();
			ResultSet tabla = consulta.executeQuery("select * from "+dtbs+".laboratorios where doc_laboratorio='" + lab.getNit() + "'");

			if (tabla.next()) {
				opVentana.reportarError("Ya existe un laboratorio con este Nit.");
				closeConnection();
				return false;
			} else {

				String seleccio = "INSERT INTO `"+dtbs+"`.`laboratorios` (`doc_laboratorio`,`nombre`,`direccion`,`ciudad`, `inf_adicional`) VALUES (?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(seleccio);
				ps.setString(1, lab.getNit());
				ps.setString(2, lab.getRazonSocial());
				ps.setString(3, lab.getDireccion());
				ps.setInt(4, lab.getIdCiudad());
				ps.setString(5, lab.getInfAdicional());

				ps.executeUpdate();

				closeConnection();
				System.err.println("ok conexion 2");return true;
			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 29> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoTarifa(OpTarifas opVentana, String recalculo, String Nombre) {
		conectar();

		try {
			Statement consulta = con.createStatement();
			ResultSet tabla = consulta.executeQuery("select * from "+dtbs+".tarifas where descripcion='" + Nombre + "'");
			
			if (tabla.next()) {
				opVentana.reportarError("Ya existe una tarifa con este nombre");
				closeConnection();
				return false;
			} else {
				
				
				String seleccio = "INSERT INTO `"+dtbs+"`.`tarifas` (`recalculo`,`descripcion`) VALUES (?,?)";
				PreparedStatement ps = con.prepareStatement(seleccio);
				
				ps.setString(1,recalculo);
				ps.setString(2,Nombre);
				ps.executeUpdate();
				
				Statement consulta2 = con.createStatement();
				ResultSet tabla2 = consulta2.executeQuery("SELECT MAX( 	idtarifas ) FROM tarifas");
				tabla2.next();
				
				Statement consultaE = con.createStatement();
				ResultSet tablaE = consultaE.executeQuery("select  `examen`.`idExamen` from "+dtbs+".examen order by idExamen asc");
				
				while(tablaE.next()){
					Statement consultaTP = con.createStatement();
					ResultSet tablaTP = consultaTP.executeQuery("select tabla_tarifas.valor from "+dtbs+".tabla_tarifas where cod_examen = "+tablaE.getInt(1)+" and  Cod_tarifa =0");
					tablaTP.next(); 
					
					int valorPrincipal=tablaTP.getInt(1);
					int recalculoInt=Integer.parseInt(recalculo);
					int valorCalculado=((valorPrincipal+((valorPrincipal*recalculoInt)/100))/100)*100;
					
					String seleccioV = "INSERT INTO `"+dtbs+"`.`tabla_tarifas` (`cod_examen`,`Cod_tarifa`,`Valor`) VALUES (?,?,?)";
					PreparedStatement pV = con.prepareStatement(seleccioV);
					
					pV.setInt(1,tablaE.getInt(1));
					pV.setInt(2,tabla2.getInt(1));
					pV.setInt(3,valorCalculado);
					pV.executeUpdate();
					
				}
				
				

				closeConnection();
				System.err.println("ok conexion 2");return true;
				
				
			}
			
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 30> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoAbonoXpaciente(OpAbonoXpaciente opVentana,AbonoXPaciente abono) {
		conectar();

		try {
				
			int restante=abono.getAbono();
			
				for (int i = 0; i < abono.getDA().size(); i++) {
					DatosAbono DA=abono.getDA().get(i);
					if(restante>0){
					if(restante>=DA.getSaldo()){
						Statement ps = con.createStatement();
						ps.executeUpdate("UPDATE  `"+dtbs+"`.`facturas` SET  `abono` =  '"+(DA.getAbono()+DA.getSaldo())+"',`saldo` =  '0' WHERE  `facturas`.`idfactura` ="+DA.getIdFactura());
						restante=restante-DA.getSaldo();
					}else{
						Statement ps = con.createStatement();
						ps.executeUpdate("UPDATE  `"+dtbs+"`.`facturas` SET  `abono` =  '"+(DA.getAbono()+restante)+"',`saldo` =  '"+(DA.getSaldo()-restante)+"' WHERE  `facturas`.`idfactura` ="+DA.getIdFactura());
						restante=restante-DA.getSaldo();
					}
					}
				}
						
				String seleccioV = "INSERT INTO  `"+dtbs+"`.`abonosxpaciente` (`idPaciente` ,`codFormaPago` ,`abono` ,`observaciones` ,`numeroTarjeta`)VALUES (?,?,?,?,?)";
				PreparedStatement pV = con.prepareStatement(seleccioV);
				
				pV.setString(1,abono.getIdPaciente());
				pV.setInt(2,abono.getCodFormaPago());
				pV.setInt(3,abono.getAbono());
				pV.setString(4,abono.getObs());
				pV.setInt(5,abono.getNumeroTarjeta());
				pV.executeUpdate();
				

				closeConnection();
				System.err.println("ok conexion 2");return true;
				
				
		
			
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 31> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoAbonoXEmpresa(OpAbonoXEmpresa opVentana,AbonoXEmpresa abono) {
		conectar();

		try {
				
			int restante=abono.getAbono();
			
				for (int i = 0; i < abono.getDA().size(); i++) {
					DatosAbono DA=abono.getDA().get(i);
					if(restante>0){
					if(restante>=DA.getSaldo()){
						Statement ps = con.createStatement();
						ps.executeUpdate("UPDATE  `"+dtbs+"`.`facturas` SET  `abono` =  '"+(DA.getAbono()+DA.getSaldo())+"',`saldo` =  '0' WHERE  `facturas`.`idfactura` ="+DA.getIdFactura());
						restante=restante-DA.getSaldo();
					}else{
						Statement ps = con.createStatement();
						ps.executeUpdate("UPDATE  `"+dtbs+"`.`facturas` SET  `abono` =  '"+(DA.getAbono()+restante)+"',`saldo` =  '"+(DA.getSaldo()-restante)+"' WHERE  `facturas`.`idfactura` ="+DA.getIdFactura());
						restante=restante-DA.getSaldo();
					}
					}
				}
						
				String seleccioV = "INSERT INTO  `"+dtbs+"`.`abonosxempresa` (`idEmpresa` ,`codFormaPago` ,`abono` ,`observaciones` ,`numeroTarjeta`)VALUES (?,?,?,?,?)";
				PreparedStatement pV = con.prepareStatement(seleccioV);
				
				pV.setInt(1,abono.getIdPaciente());
				pV.setInt(2,abono.getCodFormaPago());
				pV.setInt(3,abono.getAbono());
				pV.setString(4,abono.getObs());
				pV.setInt(5,abono.getNumeroTarjeta());
				pV.executeUpdate();
				

				closeConnection();
				System.err.println("ok conexion 2");return true;
				
				
		
			
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 32> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoAbonoXFactura(OpAbonoXFactura opVentana,Factura fac,AbonoXFactura abono) {
		conectar();

		try {
				
						Statement ps = con.createStatement();
						ps.executeUpdate("UPDATE  `"+dtbs+"`.`facturas` SET  `abono` =  '"+(fac.getAbono()+abono.getAbono())+"',`saldo` =  '"+(fac.getSaldo()-abono.getAbono())+"' WHERE  `facturas`.`idfactura` ="+fac.getIdFactura());
					
						
				String seleccioV = "INSERT INTO  `"+dtbs+"`.`abonosxfactura` (`idFactura` ,`codFormaPago` ,`abono` ,`observaciones` ,`numeroTarjeta`)VALUES (?,?,?,?,?)";
				PreparedStatement pV = con.prepareStatement(seleccioV);
				
				pV.setInt(1,abono.getIdFactura());
				pV.setInt(2,abono.getCodFormaPago());
				pV.setInt(3,abono.getAbono());
				pV.setString(4,abono.getObs());
				pV.setInt(5,abono.getNumeroTarjeta());
				pV.executeUpdate();
				

				closeConnection();
				System.err.println("ok conexion 2");return true;
				
				
		
			
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 33> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoNota(OpNotasCD opVentana, Nota nota) {
		conectar();

		try {
				
				if (nota.getTipoCD()==1) {
					if (nota.getValor()>nota.getFactura().getSaldo()) {
						
						int restante=nota.getValor()-nota.getFactura().getSaldo();
						
						Statement ps = con.createStatement();
						ps.executeUpdate("UPDATE  `"+dtbs+"`.`facturas` SET  `abono` =  '"+(nota.getFactura().getAbono()-restante)+"',`saldo` = '0'  WHERE  `facturas`.`idfactura` ="+nota.getFactura().getIdFactura());
					
						
					} else {
						Statement ps = con.createStatement();
						ps.executeUpdate("UPDATE  `"+dtbs+"`.`facturas` SET  `saldo` =  '"+(nota.getFactura().getSaldo()-nota.getValor())+"' WHERE  `facturas`.`idfactura` ="+nota.getFactura().getIdFactura());
					}


				} else {
					Statement ps = con.createStatement();
					ps.executeUpdate("UPDATE  `"+dtbs+"`.`facturas` SET  `saldo` = '"+nota.getFactura().getSaldo()+nota.getValor()+"'  WHERE  `facturas`.`idfactura` ="+nota.getFactura().getIdFactura());
				}
			
					
					
						
				String seleccioV = "INSERT INTO  `"+dtbs+"`.`notascreditodebito` (`idFactura` ,`valor` ,`motivo` ,`tipoCD`)VALUES (?,?,?,?)";
				PreparedStatement pV = con.prepareStatement(seleccioV);
				
				pV.setInt(1, nota.getFactura().getIdFactura());
				pV.setInt(2,nota.getValor());
				pV.setString(3,nota.getMotivo());
				pV.setInt(4,nota.getTipoCD());
				pV.executeUpdate();
				

				closeConnection();
				System.err.println("ok conexion 2");return true;
				
				
		
			
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 34> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	
	public boolean nuevoCuenta(cuentaCobro cuenta) {
		conectar();

		try {
			ResultSet tablaIF = null;
			
				ArrayList<itemCuenta> itemsCuenta = cuenta.getItems();
				
				
				
				String seleccio = "INSERT INTO `"+dtbs+"`.`cuentacobro` (`fechaCuenta`, `inicioRango`, `finRango`, `codEPS`,`nombreEmpresa`, `codSuGrupo`, `numContrato`, `copago`, `descuento`, `netoApagar`, `cantServicios`, `pdfRegistro`, `idEmpresa`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(seleccio);
				ps.setDate(1, new java.sql.Date(cuenta.getFechaCuenta().getTime()));
				ps.setDate(2, new java.sql.Date(cuenta.getInicioRango().getTime()));
				ps.setDate(3, new java.sql.Date(cuenta.getFinRango().getTime()));
				ps.setString(4, cuenta.getCdoEPS());
				ps.setString(5, cuenta.getNombreEmpresa());
				ps.setInt(6, cuenta.getCodSubgrupo());
				ps.setString(7, cuenta.getNumContrato());
				ps.setString(8, cuenta.getCopago());
				ps.setInt(9, cuenta.getDescuento());
				ps.setInt(10, cuenta.getNetoPago());
				ps.setInt(11, cuenta.getCantServicios());
				ps.setBlob(12,cuenta.getPdf());
				ps.setString(13, cuenta.getIdEmpresa());

				
				ps.executeUpdate();
				
				Statement consulta1 = con.createStatement();
				ResultSet tabla1 = consulta1.executeQuery("SELECT MAX( idCuenta ) FROM cuentacobro");
				tabla1.next();
				
				
				
				for (int i = 0; i < itemsCuenta.size(); i++) {
					itemCuenta IC=itemsCuenta.get(i);
					String seleccion = "INSERT INTO `"+dtbs+"`.`item_x_cuenta` (`idCuenta`, `numFacturaREcepcion`, `idPaciente`, `fechaRecepcion`, `valorProcedimiento`, `cups`) VALUES (?,?,?,?,?,?)";
				PreparedStatement ps1 = con.prepareStatement(seleccion);
				
				ps1.setInt(1, tabla1.getInt(1));
				ps1.setInt(2, IC.getNumFacturaRecepcion());
				ps1.setString(3, IC.getIdPaciente());
				ps1.setDate(4, new java.sql.Date(IC.getFechaRecepcion().getTime()));
				ps1.setInt(5, IC.getValorProcedimiento());
				ps1.setInt(6, IC.getCups());
			
				ps1.executeUpdate();
				}
				closeConnection();
				System.err.println("ok conexion 2");return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 18.10> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	// #############################################################################
	// METODOS DE MODIFICACION	// ############################################################################ METODOS DE EDICION######################################################################################

	public boolean editarUsuario(Usuario usu, String nom, Color color, int tiempo) throws SQLException {
		conectar();

		try {
			Statement ps = con.createStatement();
			ps.executeUpdate("UPDATE  `"+dtbs+"`.`usuarios` SET  `nombreCompleto` =  '" + nom + "',`tiempoInactivo` =  '" + tiempo + "' WHERE  `usuarios`.`idUsuarios` ='" + usu.getIdUsuario() + "'");

			Statement ps2 = con.createStatement();
			ps2.executeUpdate("UPDATE  `"+dtbs+"`.`colores` SET  `R` =  '" + color.getRed() + "',`G` =  '" + color.getGreen() + "',`B` =  '" + color.getBlue()+ "',`A` =  '" + color.getAlpha() + "' WHERE  `colores`.`idUsuario` =" + usu.getIdUsuario());
			closeConnection();
			System.err.println("ok conexion 2");return true;

		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 35> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarPaciente(Paciente pac) {
		conectar();

		try {
			Statement ciu = con.createStatement();
			ResultSet codciu = ciu.executeQuery("SELECT  `idciudad` FROM  `ciudad` WHERE  `nombre_ciudad` LIKE  '" + pac.getCiudad() + "'");
			codciu.next();

			Statement ps = con.createStatement();
			ps.executeUpdate("UPDATE  `"+dtbs+"`.`persona` SET  `nombres` =  '" + pac.getNombres() + "',`apellidos` =  '" + pac.getApellidos() + "',`fecha_nacimiento` =  '" + new java.sql.Date(pac.getFechaNacimiento().getTime()) + "',`genero` =  '" + pac.getGenero() + "',`zona_residencial` =  '" + pac.getZonaResidencial() + "',`ciudad` =  '" + codciu.getInt(1) + "',`tipo_doc` =  '"
					+ pac.getTipoId() + "',`direccion` =  '" + pac.getDireccion() + "' WHERE  `persona`.`cod_persona` =" + pac.getId() + "");

			Statement p3 = con.createStatement();
			p3.executeUpdate("UPDATE  `"+dtbs+"`.`paciente` SET  `parentesco` =  '" + pac.getParentesco() + "',`numero_carn` =  '" + pac.getNum_Carnet() + "' WHERE  `paciente`.`idpaciente` =" + pac.getId() + "");

			Statement idmail1 = con.createStatement();
			ResultSet REidmail1 = idmail1.executeQuery("SELECT  `email_id_email` FROM  `email_x_persona` WHERE  `persona_cod_persona` LIKE  '" + pac.getId() + "'");
			if (REidmail1.next()) {

				Statement p4 = con.createStatement();
				p4.executeUpdate("UPDATE  `"+dtbs+"`.`email` SET  `email` =  '" + pac.getEmail1() + "' WHERE  `email`.`id_email` =" + REidmail1.getInt(1) + "");

				if (REidmail1.next()) {
					Statement p5 = con.createStatement();
					p5.executeUpdate("UPDATE  `"+dtbs+"`.`email` SET  `email` =  '" + pac.getEmail2() + "' WHERE  `email`.`id_email` =" + REidmail1.getInt(1) + "");

				} else {

					if (!pac.getEmail1().equals("") || !pac.getEmail2().equals("")) {
						if (!pac.getEmail2().equals("")) {
							String queryE2 = "INSERT INTO  `"+dtbs+"`.`email` (`id_email` ,`email`)VALUES (NULL,?);";
							PreparedStatement E2 = con.prepareStatement(queryE2);

							E2.setString(1, pac.getEmail2());
							E2.executeUpdate();

							Statement consultaE2 = con.createStatement();
							ResultSet consultaEmail2 = consultaE2.executeQuery("select id_email from "+dtbs+".email where email='" + pac.getEmail2() + "'");

							consultaEmail2.next();

							String queryE2_tabla = "INSERT INTO  `"+dtbs+"`.`email_x_persona` (`persona_cod_persona` ,`email_id_email`)VALUES (?,?);";
							PreparedStatement E2_tabla = con.prepareStatement(queryE2_tabla);

							E2_tabla.setString(1, pac.getId());
							E2_tabla.setInt(2, consultaEmail2.getInt(1));
							E2_tabla.executeUpdate();

						}
					}

				}
			} else {
				if (!pac.getEmail1().equals("") || !pac.getEmail2().equals("")) {
					if (!pac.getEmail1().equals("")) {
						String queryE1 = "INSERT INTO  `"+dtbs+"`.`email` (`id_email` ,`email`)VALUES (NULL,?);";
						PreparedStatement E1 = con.prepareStatement(queryE1);

						E1.setString(1, pac.getEmail1());
						E1.executeUpdate();

						Statement consultaE1 = con.createStatement();
						ResultSet consultaEmail1 = consultaE1.executeQuery("select id_email from "+dtbs+".email where email='" + pac.getEmail1() + "'");

						consultaEmail1.next();

						String queryE1_tabla = "INSERT INTO  `"+dtbs+"`.`email_x_persona` (`persona_cod_persona` ,`email_id_email`)VALUES (?,?);";
						PreparedStatement E1_tabla = con.prepareStatement(queryE1_tabla);

						E1_tabla.setString(1, pac.getId());
						E1_tabla.setInt(2, consultaEmail1.getInt(1));
						E1_tabla.executeUpdate();

					}
					if (!pac.getEmail2().equals("")) {
						String queryE2 = "INSERT INTO  `"+dtbs+"`.`email` (`id_email` ,`email`)VALUES (NULL,?);";
						PreparedStatement E2 = con.prepareStatement(queryE2);

						E2.setString(1, pac.getEmail2());
						E2.executeUpdate();

						Statement consultaE2 = con.createStatement();
						ResultSet consultaEmail2 = consultaE2.executeQuery("select id_email from "+dtbs+".email where email='" + pac.getEmail2() + "'");

						consultaEmail2.next();

						String queryE2_tabla = "INSERT INTO  `"+dtbs+"`.`email_x_persona` (`persona_cod_persona` ,`email_id_email`)VALUES (?,?);";
						PreparedStatement E2_tabla = con.prepareStatement(queryE2_tabla);

						E2_tabla.setString(1, pac.getId());
						E2_tabla.setInt(2, consultaEmail2.getInt(1));
						E2_tabla.executeUpdate();

					}
				}
			}

			Statement idtel = con.createStatement();
			ResultSet REidtel1 = idtel.executeQuery("SELECT  telefono_telefono FROM  "+dtbs+".telefono_x_persona WHERE  persona_cod_persona =" + pac.getId() + "");
			if (REidtel1.next()) {
				Statement p5 = con.createStatement();
				p5.executeUpdate("UPDATE  `"+dtbs+"`.`telefono` SET  `telefono` =  '" + pac.getTelefono1() + "' WHERE  `telefono`.`id_telefono` =" + REidtel1.getInt(1) + "");

				if (REidtel1.next()) {
					Statement p6 = con.createStatement();
					p6.executeUpdate("UPDATE  `"+dtbs+"`.`telefono` SET  `telefono` =  '" + pac.getTelefono2() + "' WHERE  `telefono`.`id_telefono` =" + REidtel1.getInt(1) + "");

				} else {
					if (!pac.getTelefono2().equals("")) {

						if (!pac.getTelefono2().equals("")) {
							String queryT2 = "INSERT INTO  `"+dtbs+"`.`telefono` (`id_telefono` ,`telefono`)VALUES (NULL,?);";
							PreparedStatement T2 = con.prepareStatement(queryT2);

							T2.setString(1, pac.getTelefono2());
							T2.executeUpdate();

							Statement consultaT2 = con.createStatement();
							ResultSet consultaTel2 = consultaT2.executeQuery("select id_telefono from "+dtbs+".telefono where telefono='" + pac.getTelefono2() + "'");

							consultaTel2.next();

							String queryT2_tabla = "INSERT INTO  `"+dtbs+"`.`telefono_x_persona` (`persona_cod_persona` ,`telefono_telefono`)VALUES (?,?);";
							PreparedStatement T2_tabla = con.prepareStatement(queryT2_tabla);

							T2_tabla.setString(1, pac.getId());
							T2_tabla.setInt(2, consultaTel2.getInt(1));
							T2_tabla.executeUpdate();

						}
					}
				}
			} else {
				if (!pac.getTelefono1().equals("") || !pac.getTelefono2().equals("")) {
					if (!pac.getTelefono1().equals("")) {
						String queryT1 = "INSERT INTO  `"+dtbs+"`.`telefono` (`id_telefono` ,`telefono`)VALUES (NULL,?);";
						PreparedStatement T1 = con.prepareStatement(queryT1);

						T1.setString(1, pac.getTelefono1());
						T1.executeUpdate();

						Statement consultaT1 = con.createStatement();
						ResultSet consultaTel1 = consultaT1.executeQuery("select id_telefono from "+dtbs+".telefono where telefono='" + pac.getTelefono1() + "'");

						consultaTel1.next();

						String queryT1_tabla = "INSERT INTO  `"+dtbs+"`.`telefono_x_persona` (`persona_cod_persona` ,`telefono_telefono`)VALUES (?,?);";
						PreparedStatement T1_tabla = con.prepareStatement(queryT1_tabla);

						T1_tabla.setString(1, pac.getId());
						T1_tabla.setInt(2, consultaTel1.getInt(1));
						T1_tabla.executeUpdate();

					}
					if (!pac.getTelefono2().equals("")) {
						String queryT2 = "INSERT INTO  `"+dtbs+"`.`telefono` (`id_telefono` ,`telefono`)VALUES (NULL,?);";
						PreparedStatement T2 = con.prepareStatement(queryT2);

						T2.setString(1, pac.getTelefono2());
						T2.executeUpdate();

						Statement consultaT2 = con.createStatement();
						ResultSet consultaTel2 = consultaT2.executeQuery("select id_telefono from "+dtbs+".telefono where telefono='" + pac.getTelefono2() + "'");

						consultaTel2.next();

						String queryT2_tabla = "INSERT INTO  `"+dtbs+"`.`telefono_x_persona` (`persona_cod_persona` ,`telefono_telefono`)VALUES (?,?);";
						PreparedStatement T2_tabla = con.prepareStatement(queryT2_tabla);

						T2_tabla.setString(1, pac.getId());
						T2_tabla.setInt(2, consultaTel2.getInt(1));
						T2_tabla.executeUpdate();

					}
				}
			}
			closeConnection();
			System.err.println("ok conexion 2");System.err.println("ok conexion 2");return true;

		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 36> en la base de datos : "+e.toString());
	closeConnection();
			return false;
		}

	}

	public boolean editarBacteriologo(Bacteriologo bact) {
		conectar();

		try {
			Statement ciu = con.createStatement();
			ResultSet codciu = ciu.executeQuery("SELECT  `idciudad` FROM  `ciudad` WHERE  `nombre_ciudad` LIKE  '" + bact.getCiudad() + "'");
			codciu.next();

			Statement ps = con.createStatement();
			ps.executeUpdate("UPDATE  `"+dtbs+"`.`persona` SET  `nombres` =  '" + bact.getNombres() + "',`apellidos` =  '" + bact.getApellidos() + "',`fecha_nacimiento` =  '" + new java.sql.Date(bact.getFechaNacimiento().getTime()) + "',`genero` =  '" + bact.getGenero() + "',`zona_residencial` =  '" + bact.getZonaResidencial() + "',`ciudad` =  '" + codciu.getInt(1) + "',`tipo_doc` =  '"
					+ bact.getTipoId() + "',`direccion` =  '" + bact.getDireccion() + "' WHERE  `persona`.`cod_persona` =" + bact.getId() + "");

			Statement p3 = con.createStatement();
			p3.executeUpdate("UPDATE  `"+dtbs+"`.`bacteriologo` SET  `registro` =  '" + bact.getRegistro() + "',`titulo` =  '" + bact.getTitulo() + "' ,`otros_estudios` =  '" + bact.getOtrosEstudios() + "' ,`inf_adicional` =  '" + bact.getInf_adicional() + "' ,`claveFirma` =  '" + bact.getClaveFirma() + "' WHERE  `bacteriologo`.`cod_Bacteriologo` =" + bact.getId() + "");
			
			if(bact.getFIS()!=null){
				
				PreparedStatement stmt = con.prepareStatement("UPDATE  `"+dtbs+"`.`bacteriologo` SET  `imagen` = ? WHERE  `bacteriologo`.`cod_Bacteriologo` =?");
				stmt.setBlob(1,bact.getFIS());
				stmt.setString(2, bact.getId());
				stmt.executeUpdate();
				
				
				
			}
			
			Statement idmail1 = con.createStatement();
			ResultSet REidmail1 = idmail1.executeQuery("SELECT  `email_id_email` FROM  `email_x_persona` WHERE  `persona_cod_persona` LIKE  '" + bact.getId() + "'");
			if (REidmail1.next()) {

				Statement p4 = con.createStatement();
				p4.executeUpdate("UPDATE  `"+dtbs+"`.`email` SET  `email` =  '" + bact.getEmail1() + "' WHERE  `email`.`id_email` =" + REidmail1.getInt(1) + "");

				if (REidmail1.next()) {
					Statement p5 = con.createStatement();
					p5.executeUpdate("UPDATE  `"+dtbs+"`.`email` SET  `email` =  '" + bact.getEmail2() + "' WHERE  `email`.`id_email` =" + REidmail1.getInt(1) + "");

				} else {

					if (!bact.getEmail1().equals("") || !bact.getEmail2().equals("")) {
						if (!bact.getEmail2().equals("")) {
							String queryE2 = "INSERT INTO  `"+dtbs+"`.`email` (`id_email` ,`email`)VALUES (NULL,?);";
							PreparedStatement E2 = con.prepareStatement(queryE2);

							E2.setString(1, bact.getEmail2());
							E2.executeUpdate();

							Statement consultaE2 = con.createStatement();
							ResultSet consultaEmail2 = consultaE2.executeQuery("select id_email from "+dtbs+".email where email='" + bact.getEmail2() + "'");

							consultaEmail2.next();

							String queryE2_tabla = "INSERT INTO  `"+dtbs+"`.`email_x_persona` (`persona_cod_persona` ,`email_id_email`)VALUES (?,?);";
							PreparedStatement E2_tabla = con.prepareStatement(queryE2_tabla);

							E2_tabla.setString(1, bact.getId());
							E2_tabla.setInt(2, consultaEmail2.getInt(1));
							E2_tabla.executeUpdate();

						}
					}

				}
			} else {
				if (!bact.getEmail1().equals("") || !bact.getEmail2().equals("")) {
					if (!bact.getEmail1().equals("")) {
						String queryE1 = "INSERT INTO  `"+dtbs+"`.`email` (`id_email` ,`email`)VALUES (NULL,?);";
						PreparedStatement E1 = con.prepareStatement(queryE1);

						E1.setString(1, bact.getEmail1());
						E1.executeUpdate();

						Statement consultaE1 = con.createStatement();
						ResultSet consultaEmail1 = consultaE1.executeQuery("select id_email from "+dtbs+".email where email='" + bact.getEmail1() + "'");

						consultaEmail1.next();

						String queryE1_tabla = "INSERT INTO  `"+dtbs+"`.`email_x_persona` (`persona_cod_persona` ,`email_id_email`)VALUES (?,?);";
						PreparedStatement E1_tabla = con.prepareStatement(queryE1_tabla);

						E1_tabla.setString(1, bact.getId());
						E1_tabla.setInt(2, consultaEmail1.getInt(1));
						E1_tabla.executeUpdate();

					}
					if (!bact.getEmail2().equals("")) {
						String queryE2 = "INSERT INTO  `"+dtbs+"`.`email` (`id_email` ,`email`)VALUES (NULL,?);";
						PreparedStatement E2 = con.prepareStatement(queryE2);

						E2.setString(1, bact.getEmail2());
						E2.executeUpdate();

						Statement consultaE2 = con.createStatement();
						ResultSet consultaEmail2 = consultaE2.executeQuery("select id_email from "+dtbs+".email where email='" + bact.getEmail2() + "'");

						consultaEmail2.next();

						String queryE2_tabla = "INSERT INTO  `"+dtbs+"`.`email_x_persona` (`persona_cod_persona` ,`email_id_email`)VALUES (?,?);";
						PreparedStatement E2_tabla = con.prepareStatement(queryE2_tabla);

						E2_tabla.setString(1, bact.getId());
						E2_tabla.setInt(2, consultaEmail2.getInt(1));
						E2_tabla.executeUpdate();

					}
				}
			}

			Statement idtel = con.createStatement();
			ResultSet REidtel1 = idtel.executeQuery("SELECT  telefono_telefono FROM  "+dtbs+".telefono_x_persona WHERE  persona_cod_persona =" + bact.getId() + "");
			if (REidtel1.next()) {
				System.out.println("cuando se va a modificar teledfono" + REidtel1);
				Statement p5 = con.createStatement();
				p5.executeUpdate("UPDATE  `"+dtbs+"`.`telefono` SET  `telefono` =  '" + bact.getTelefono1() + "' WHERE  `telefono`.`id_telefono` =" + REidtel1.getInt(1) + "");

				if (REidtel1.next()) {
					Statement p6 = con.createStatement();
					p6.executeUpdate("UPDATE  `"+dtbs+"`.`telefono` SET  `telefono` =  '" + bact.getTelefono2() + "' WHERE  `telefono`.`id_telefono` =" + REidtel1.getInt(1) + "");

				} else {
					if (!bact.getTelefono2().equals("")) {

						if (!bact.getTelefono2().equals("")) {
							String queryT2 = "INSERT INTO  `"+dtbs+"`.`telefono` (`id_telefono` ,`telefono`)VALUES (NULL,?);";
							PreparedStatement T2 = con.prepareStatement(queryT2);

							T2.setString(1, bact.getTelefono2());
							T2.executeUpdate();

							Statement consultaT2 = con.createStatement();
							ResultSet consultaTel2 = consultaT2.executeQuery("select id_telefono from "+dtbs+".telefono where telefono='" + bact.getTelefono2() + "'");

							consultaTel2.next();

							String queryT2_tabla = "INSERT INTO  `"+dtbs+"`.`telefono_x_persona` (`persona_cod_persona` ,`telefono_telefono`)VALUES (?,?);";
							PreparedStatement T2_tabla = con.prepareStatement(queryT2_tabla);

							T2_tabla.setString(1, bact.getId());
							T2_tabla.setInt(2, consultaTel2.getInt(1));
							T2_tabla.executeUpdate();

						}
					}
				}
			} else {
				if (!bact.getTelefono1().equals("") || !bact.getTelefono2().equals("")) {
					if (!bact.getTelefono1().equals("")) {
						String queryT1 = "INSERT INTO  `"+dtbs+"`.`telefono` (`id_telefono` ,`telefono`)VALUES (NULL,?);";
						PreparedStatement T1 = con.prepareStatement(queryT1);

						T1.setString(1, bact.getTelefono1());
						T1.executeUpdate();

						Statement consultaT1 = con.createStatement();
						ResultSet consultaTel1 = consultaT1.executeQuery("select id_telefono from "+dtbs+".telefono where telefono='" + bact.getTelefono1() + "'");

						consultaTel1.next();

						String queryT1_tabla = "INSERT INTO  `"+dtbs+"`.`telefono_x_persona` (`persona_cod_persona` ,`telefono_telefono`)VALUES (?,?);";
						PreparedStatement T1_tabla = con.prepareStatement(queryT1_tabla);

						T1_tabla.setString(1, bact.getId());
						T1_tabla.setInt(2, consultaTel1.getInt(1));
						T1_tabla.executeUpdate();

					}
					if (!bact.getTelefono2().equals("")) {
						String queryT2 = "INSERT INTO  `"+dtbs+"`.`telefono` (`id_telefono` ,`telefono`)VALUES (NULL,?);";
						PreparedStatement T2 = con.prepareStatement(queryT2);

						T2.setString(1, bact.getTelefono2());
						T2.executeUpdate();

						Statement consultaT2 = con.createStatement();
						ResultSet consultaTel2 = consultaT2.executeQuery("select id_telefono from "+dtbs+".telefono where telefono='" + bact.getTelefono2() + "'");

						consultaTel2.next();

						String queryT2_tabla = "INSERT INTO  `"+dtbs+"`.`telefono_x_persona` (`persona_cod_persona` ,`telefono_telefono`)VALUES (?,?);";
						PreparedStatement T2_tabla = con.prepareStatement(queryT2_tabla);

						T2_tabla.setString(1, bact.getId());
						T2_tabla.setInt(2, consultaTel2.getInt(1));
						T2_tabla.executeUpdate();

					}
				}
			}
			closeConnection();
			System.err.println("ok conexion 2");return true;

		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 37> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarMedico(Medico medico) {
		conectar();

		try {
			Statement ciu = con.createStatement();
			ResultSet codciu = ciu.executeQuery("SELECT  `idciudad` FROM  `ciudad` WHERE  `nombre_ciudad` LIKE  '" + medico.getCiudad() + "'");
			codciu.next();

			Statement ps = con.createStatement();
			ps.executeUpdate("UPDATE  `"+dtbs+"`.`persona` SET  `nombres` =  '" + medico.getNombres() + "',`apellidos` =  '" + medico.getApellidos() + "',`fecha_nacimiento` =  '" + new java.sql.Date(medico.getFechaNacimiento().getTime()) + "',`genero` =  '" + medico.getGenero() + "',`zona_residencial` =  '" + medico.getZonaResidencial() + "',`ciudad` =  '" + codciu.getInt(1)
					+ "',`tipo_doc` =  '" + medico.getTipoId() + "',`direccion` =  '" + medico.getDireccion() + "' WHERE  `persona`.`cod_persona` =" + medico.getId() + "");

			Statement p3 = con.createStatement();
			p3.executeUpdate("UPDATE  `"+dtbs+"`.`medico` SET  `especialidad` =  '" + medico.getEspecialidad() + "',`idmedico` =  '" + medico.getIdmedico() + "' WHERE  `medico`.`id_persona` =" + medico.getId() + "");

			Statement idmail1 = con.createStatement();
			ResultSet REidmail1 = idmail1.executeQuery("SELECT  `email_id_email` FROM  `email_x_persona` WHERE  `persona_cod_persona` LIKE  '" + medico.getId() + "'");
			if (REidmail1.next()) {

				Statement p4 = con.createStatement();
				p4.executeUpdate("UPDATE  `"+dtbs+"`.`email` SET  `email` =  '" + medico.getEmail1() + "' WHERE  `email`.`id_email` =" + REidmail1.getInt(1) + "");

				if (REidmail1.next()) {
					Statement p5 = con.createStatement();
					p5.executeUpdate("UPDATE  `"+dtbs+"`.`email` SET  `email` =  '" + medico.getEmail2() + "' WHERE  `email`.`id_email` =" + REidmail1.getInt(1) + "");

				} else {

					if (!medico.getEmail1().equals("") || !medico.getEmail2().equals("")) {
						if (!medico.getEmail2().equals("")) {
							String queryE2 = "INSERT INTO  `"+dtbs+"`.`email` (`id_email` ,`email`)VALUES (NULL,?);";
							PreparedStatement E2 = con.prepareStatement(queryE2);

							E2.setString(1, medico.getEmail2());
							E2.executeUpdate();

							Statement consultaE2 = con.createStatement();
							ResultSet consultaEmail2 = consultaE2.executeQuery("select id_email from "+dtbs+".email where email='" + medico.getEmail2() + "'");

							consultaEmail2.next();

							String queryE2_tabla = "INSERT INTO  `"+dtbs+"`.`email_x_persona` (`persona_cod_persona` ,`email_id_email`)VALUES (?,?);";
							PreparedStatement E2_tabla = con.prepareStatement(queryE2_tabla);

							E2_tabla.setString(1, medico.getId());
							E2_tabla.setInt(2, consultaEmail2.getInt(1));
							E2_tabla.executeUpdate();

						}
					}

				}
			} else {
				if (!medico.getEmail1().equals("") || !medico.getEmail2().equals("")) {
					if (!medico.getEmail1().equals("")) {
						String queryE1 = "INSERT INTO  `"+dtbs+"`.`email` (`id_email` ,`email`)VALUES (NULL,?);";
						PreparedStatement E1 = con.prepareStatement(queryE1);

						E1.setString(1, medico.getEmail1());
						E1.executeUpdate();

						Statement consultaE1 = con.createStatement();
						ResultSet consultaEmail1 = consultaE1.executeQuery("select id_email from "+dtbs+".email where email='" + medico.getEmail1() + "'");

						consultaEmail1.next();

						String queryE1_tabla = "INSERT INTO  `"+dtbs+"`.`email_x_persona` (`persona_cod_persona` ,`email_id_email`)VALUES (?,?);";
						PreparedStatement E1_tabla = con.prepareStatement(queryE1_tabla);

						E1_tabla.setString(1, medico.getId());
						E1_tabla.setInt(2, consultaEmail1.getInt(1));
						E1_tabla.executeUpdate();

					}
					if (!medico.getEmail2().equals("")) {
						String queryE2 = "INSERT INTO  `"+dtbs+"`.`email` (`id_email` ,`email`)VALUES (NULL,?);";
						PreparedStatement E2 = con.prepareStatement(queryE2);

						E2.setString(1, medico.getEmail2());
						E2.executeUpdate();

						Statement consultaE2 = con.createStatement();
						ResultSet consultaEmail2 = consultaE2.executeQuery("select id_email from "+dtbs+".email where email='" + medico.getEmail2() + "'");

						consultaEmail2.next();

						String queryE2_tabla = "INSERT INTO  `"+dtbs+"`.`email_x_persona` (`persona_cod_persona` ,`email_id_email`)VALUES (?,?);";
						PreparedStatement E2_tabla = con.prepareStatement(queryE2_tabla);

						E2_tabla.setString(1, medico.getId());
						E2_tabla.setInt(2, consultaEmail2.getInt(1));
						E2_tabla.executeUpdate();

					}
				}
			}

			Statement idtel = con.createStatement();
			ResultSet REidtel1 = idtel.executeQuery("SELECT  telefono_telefono FROM  "+dtbs+".telefono_x_persona WHERE  persona_cod_persona =" + medico.getId() + "");
			if (REidtel1.next()) {
				System.out.println("cuando se va a modificar teledfono" + REidtel1);
				Statement p5 = con.createStatement();
				p5.executeUpdate("UPDATE  `"+dtbs+"`.`telefono` SET  `telefono` =  '" + medico.getTelefono1() + "' WHERE  `telefono`.`id_telefono` =" + REidtel1.getInt(1) + "");

				if (REidtel1.next()) {
					Statement p6 = con.createStatement();
					p6.executeUpdate("UPDATE  `"+dtbs+"`.`telefono` SET  `telefono` =  '" + medico.getTelefono2() + "' WHERE  `telefono`.`id_telefono` =" + REidtel1.getInt(1) + "");

				} else {
					if (!medico.getTelefono2().equals("")) {

						if (!medico.getTelefono2().equals("")) {
							String queryT2 = "INSERT INTO  `"+dtbs+"`.`telefono` (`id_telefono` ,`telefono`)VALUES (NULL,?);";
							PreparedStatement T2 = con.prepareStatement(queryT2);

							T2.setString(1, medico.getTelefono2());
							T2.executeUpdate();

							Statement consultaT2 = con.createStatement();
							ResultSet consultaTel2 = consultaT2.executeQuery("select id_telefono from "+dtbs+".telefono where telefono='" + medico.getTelefono2() + "'");

							consultaTel2.next();

							String queryT2_tabla = "INSERT INTO  `"+dtbs+"`.`telefono_x_persona` (`persona_cod_persona` ,`telefono_telefono`)VALUES (?,?);";
							PreparedStatement T2_tabla = con.prepareStatement(queryT2_tabla);

							T2_tabla.setString(1, medico.getId());
							T2_tabla.setInt(2, consultaTel2.getInt(1));
							T2_tabla.executeUpdate();

						}
					}
				}
			} else {
				if (!medico.getTelefono1().equals("") || !medico.getTelefono2().equals("")) {
					if (!medico.getTelefono1().equals("")) {
						String queryT1 = "INSERT INTO  `"+dtbs+"`.`telefono` (`id_telefono` ,`telefono`)VALUES (NULL,?);";
						PreparedStatement T1 = con.prepareStatement(queryT1);

						T1.setString(1, medico.getTelefono1());
						T1.executeUpdate();

						Statement consultaT1 = con.createStatement();
						ResultSet consultaTel1 = consultaT1.executeQuery("select id_telefono from "+dtbs+".telefono where telefono='" + medico.getTelefono1() + "'");

						consultaTel1.next();

						String queryT1_tabla = "INSERT INTO  `"+dtbs+"`.`telefono_x_persona` (`persona_cod_persona` ,`telefono_telefono`)VALUES (?,?);";
						PreparedStatement T1_tabla = con.prepareStatement(queryT1_tabla);

						T1_tabla.setString(1, medico.getId());
						T1_tabla.setInt(2, consultaTel1.getInt(1));
						T1_tabla.executeUpdate();

					}
					if (!medico.getTelefono2().equals("")) {
						String queryT2 = "INSERT INTO  `"+dtbs+"`.`telefono` (`id_telefono` ,`telefono`)VALUES (NULL,?);";
						PreparedStatement T2 = con.prepareStatement(queryT2);

						T2.setString(1, medico.getTelefono2());
						T2.executeUpdate();

						Statement consultaT2 = con.createStatement();
						ResultSet consultaTel2 = consultaT2.executeQuery("select id_telefono from "+dtbs+".telefono where telefono='" + medico.getTelefono2() + "'");

						consultaTel2.next();

						String queryT2_tabla = "INSERT INTO  `"+dtbs+"`.`telefono_x_persona` (`persona_cod_persona` ,`telefono_telefono`)VALUES (?,?);";
						PreparedStatement T2_tabla = con.prepareStatement(queryT2_tabla);

						T2_tabla.setString(1, medico.getId());
						T2_tabla.setInt(2, consultaTel2.getInt(1));
						T2_tabla.executeUpdate();

					}
				}
			}
			closeConnection();
			System.err.println("ok conexion 2");return true;

		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 38> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarEmpresa(Empresa empresa,String antiguoNIT) {
		conectar();
		
	
		try {
			Statement ciu = con.createStatement();
			ResultSet codciu = ciu.executeQuery("SELECT  `idciudad` FROM  `ciudad` WHERE  `nombre_ciudad` LIKE  '" + empresa.getCiudad() + "'");
			codciu.next();

			Statement tar = con.createStatement();
			ResultSet codtar = tar.executeQuery("SELECT  `idtarifas` FROM  `tarifas` WHERE  `descripcion` LIKE  '" + empresa.getTarifa() + "'");
			codtar.next();

			Statement Tusu = con.createStatement();
			ResultSet codTusu = Tusu.executeQuery("SELECT  `idtipo_usuarios` FROM  `tipo_usuarios` WHERE  `descripcion` LIKE  '" + empresa.getTipoUsuarioXNombre() + "'");
			codTusu.next();

			Statement ps = con.createStatement();
			ps.executeUpdate("UPDATE  `"+dtbs+"`.`empresa` SET  `doc_empresa` =  '" + empresa.getDocEmpresa() + "',`razon_social` =  '" + empresa.getRazonSocial() + "',`direccion` =  '" + empresa.getDireccion() + "',`ciudad` =  '" + codciu.getInt(1) + "',`dependencia_cobro` =  '" + empresa.getDependenciaCobro() + "',`tarifa` =  '" + codtar.getInt(1) + "',`descuento` =  '"
					+ empresa.getDescuento() + "',`cod_eps` =  '" + empresa.getCodEps() + "',`tipo_usuario` =  '" + codTusu.getInt(1) + "',`adicional` =  '" + empresa.getAdicional() + "',`requisitos_recepcion` =  '" + empresa.getRequisitosRecepcion() + "' WHERE  `empresa`.`id_empresa` =" + empresa.getIdEmpresa() + "");
			
			Statement psUW = con.createStatement();
			psUW.executeUpdate("UPDATE  `"+dtbs+"`.`usuarioweb` SET  `usuario` =  '" + empresa.getDocEmpresa() + "',`pass` =  '" + empresa.getDocEmpresa() + "' WHERE  `usuarioweb`.`usuario` =" + antiguoNIT + "");

			Statement idmail1 = con.createStatement();
			ResultSet REidmail1 = idmail1.executeQuery("SELECT  `email_id_email` FROM  `email_x_empresa` WHERE  `empresa_id_empresa` LIKE  '" + empresa.getIdEmpresa() + "'");
			if (REidmail1.next()) {

				Statement p4 = con.createStatement();
				p4.executeUpdate("UPDATE  `"+dtbs+"`.`email` SET  `email` =  '" + empresa.getEmail1() + "' WHERE  `email`.`id_email` =" + REidmail1.getInt(1) + "");

				if (REidmail1.next()) {
					Statement p5 = con.createStatement();
					p5.executeUpdate("UPDATE  `"+dtbs+"`.`email` SET  `email` =  '" + empresa.getEmail2() + "' WHERE  `email`.`id_email` =" + REidmail1.getInt(1) + "");

				} else {

					if (!empresa.getEmail1().equals("") || !empresa.getEmail2().equals("")) {
						if (!empresa.getEmail2().equals("")) {
							String queryE2 = "INSERT INTO  `"+dtbs+"`.`email` (`id_email` ,`email`)VALUES (NULL,?);";
							PreparedStatement E2 = con.prepareStatement(queryE2);

							E2.setString(1, empresa.getEmail2());
							E2.executeUpdate();

							Statement consultaE2 = con.createStatement();
							ResultSet consultaEmail2 = consultaE2.executeQuery("select id_email from "+dtbs+".email where email='" + empresa.getEmail2() + "'");

							consultaEmail2.next();

							String queryE2_tabla = "INSERT INTO  `"+dtbs+"`.`email_x_empresa` (`empresa_id_empresa` ,`email_id_email`)VALUES (?,?);";
							PreparedStatement E2_tabla = con.prepareStatement(queryE2_tabla);

							E2_tabla.setInt(1, empresa.getIdEmpresa());
							E2_tabla.setInt(2, consultaEmail2.getInt(1));
							E2_tabla.executeUpdate();

						}
					}

				}
			} else {
				if (!empresa.getEmail1().equals("") || !empresa.getEmail2().equals("")) {
					if (!empresa.getEmail1().equals("")) {
						String queryE1 = "INSERT INTO  `"+dtbs+"`.`email` (`id_email` ,`email`)VALUES (NULL,?);";
						PreparedStatement E1 = con.prepareStatement(queryE1);

						E1.setString(1, empresa.getEmail1());
						E1.executeUpdate();

						Statement consultaE1 = con.createStatement();
						ResultSet consultaEmail1 = consultaE1.executeQuery("select id_email from "+dtbs+".email where email='" + empresa.getEmail1() + "'");

						consultaEmail1.next();

						String queryE1_tabla = "INSERT INTO  `"+dtbs+"`.`email_x_empresa` (`empresa_id_empresa` ,`email_id_email`)VALUES (?,?);";
						PreparedStatement E1_tabla = con.prepareStatement(queryE1_tabla);

						E1_tabla.setInt(1, empresa.getIdEmpresa());
						E1_tabla.setInt(2, consultaEmail1.getInt(1));
						E1_tabla.executeUpdate();

					}
					if (!empresa.getEmail2().equals("")) {
						String queryE2 = "INSERT INTO  `"+dtbs+"`.`email` (`id_email` ,`email`)VALUES (NULL,?);";
						PreparedStatement E2 = con.prepareStatement(queryE2);

						E2.setString(1, empresa.getEmail2());
						E2.executeUpdate();

						Statement consultaE2 = con.createStatement();
						ResultSet consultaEmail2 = consultaE2.executeQuery("select id_email from "+dtbs+".email where email='" + empresa.getEmail2() + "'");

						consultaEmail2.next();

						String queryE2_tabla = "INSERT INTO  `"+dtbs+"`.`email_x_empresa` (`empresa_id_empresa` ,`email_id_email`)VALUES (?,?);";
						PreparedStatement E2_tabla = con.prepareStatement(queryE2_tabla);

						E2_tabla.setInt(1, empresa.getIdEmpresa());
						E2_tabla.setInt(2, consultaEmail2.getInt(1));
						E2_tabla.executeUpdate();

					}
				}
			}

			Statement idtel = con.createStatement();
			ResultSet REidtel1 = idtel.executeQuery("SELECT  telefono_telefono FROM  "+dtbs+".telefono_x_empresa WHERE  empresa_id_empresa =" + empresa.getIdEmpresa() + "");
			if (REidtel1.next()) {
				System.out.println("cuando se va a modificar teledfono" + REidtel1);
				Statement p5 = con.createStatement();
				p5.executeUpdate("UPDATE  `"+dtbs+"`.`telefono` SET  `telefono` =  '" + empresa.getTelefono1() + "' WHERE  `telefono`.`id_telefono` =" + REidtel1.getInt(1) + "");

				if (REidtel1.next()) {
					Statement p6 = con.createStatement();
					p6.executeUpdate("UPDATE  `"+dtbs+"`.`telefono` SET  `telefono` =  '" + empresa.getTelefono2() + "' WHERE  `telefono`.`id_telefono` =" + REidtel1.getInt(1) + "");

				} else {
					if (!empresa.getTelefono2().equals("")) {

						if (!empresa.getTelefono2().equals("")) {
							String queryT2 = "INSERT INTO  `"+dtbs+"`.`telefono` (`id_telefono` ,`telefono`)VALUES (NULL,?);";
							PreparedStatement T2 = con.prepareStatement(queryT2);

							T2.setString(1, empresa.getTelefono2());
							T2.executeUpdate();

							Statement consultaT2 = con.createStatement();
							ResultSet consultaTel2 = consultaT2.executeQuery("select id_telefono from "+dtbs+".telefono where telefono='" + empresa.getTelefono2() + "'");

							consultaTel2.next();

							String queryT2_tabla = "INSERT INTO  `"+dtbs+"`.`telefono_x_empresa` (`empresa_id_empresa` ,`telefono_telefono`)VALUES (?,?);";
							PreparedStatement T2_tabla = con.prepareStatement(queryT2_tabla);

							T2_tabla.setInt(1, empresa.getIdEmpresa());
							T2_tabla.setInt(2, consultaTel2.getInt(1));
							T2_tabla.executeUpdate();

						}
					}
				}
			} else {
				if (!empresa.getTelefono1().equals("") || !empresa.getTelefono2().equals("")) {
					if (!empresa.getTelefono1().equals("")) {
						String queryT1 = "INSERT INTO  `"+dtbs+"`.`telefono` (`id_telefono` ,`telefono`)VALUES (NULL,?);";
						PreparedStatement T1 = con.prepareStatement(queryT1);

						T1.setString(1, empresa.getTelefono1());
						T1.executeUpdate();

						Statement consultaT1 = con.createStatement();
						ResultSet consultaTel1 = consultaT1.executeQuery("select id_telefono from "+dtbs+".telefono where telefono='" + empresa.getTelefono1() + "'");

						consultaTel1.next();

						String queryT1_tabla = "INSERT INTO  `"+dtbs+"`.`telefono_x_empresa` (`empresa_id_empresa` ,`telefono_telefono`)VALUES (?,?);";
						PreparedStatement T1_tabla = con.prepareStatement(queryT1_tabla);

						T1_tabla.setInt(1, empresa.getIdEmpresa());
						T1_tabla.setInt(2, consultaTel1.getInt(1));
						T1_tabla.executeUpdate();

					}
					if (!empresa.getTelefono2().equals("")) {
						String queryT2 = "INSERT INTO  `"+dtbs+"`.`telefono` (`id_telefono` ,`telefono`)VALUES (NULL,?);";
						PreparedStatement T2 = con.prepareStatement(queryT2);

						T2.setString(1, empresa.getTelefono2());
						T2.executeUpdate();

						Statement consultaT2 = con.createStatement();
						ResultSet consultaTel2 = consultaT2.executeQuery("select id_telefono from "+dtbs+".telefono where telefono='" + empresa.getTelefono2() + "'");

						consultaTel2.next();

						String queryT2_tabla = "INSERT INTO  `"+dtbs+"`.`telefono_x_empresa` (`empresa_id_empresa` ,`telefono_telefono`)VALUES (?,?);";
						PreparedStatement T2_tabla = con.prepareStatement(queryT2_tabla);

						T2_tabla.setInt(1, empresa.getIdEmpresa());
						T2_tabla.setInt(2, consultaTel2.getInt(1));
						T2_tabla.executeUpdate();

					}
				}
			}
			closeConnection();
			System.err.println("ok conexion 2");return true;

		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 39> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarGrupoEmpresa(OpGrupoEmpresa opVentana, GruposEmpresas grupoEmpresa, String NombreAntiguo) {
		conectar();

		try {
			if (grupoEmpresa.getNombreGrupo().equals(NombreAntiguo)) {
				Statement tar = con.createStatement();
				ResultSet codtar = tar.executeQuery("SELECT  `idtarifas` FROM  `tarifas` WHERE  `descripcion` LIKE  '" + grupoEmpresa.getTarifa() + "'");
				codtar.next();

				Statement Tusu = con.createStatement();
				ResultSet codEmpresa = Tusu.executeQuery("SELECT  `id_empresa` FROM  `empresa` WHERE  `razon_social` LIKE  '" + grupoEmpresa.getEmpresa() + "'");
				codEmpresa.next();

				Statement ps = con.createStatement();
				ps.executeUpdate("UPDATE  `"+dtbs+"`.`sub_grupo_empresa` SET  `nombre_empresa` =  '" + grupoEmpresa.getNombreGrupo() + "',`codEmpresa` =  '" + codEmpresa.getInt(1) + "',`codTarifa` =  '" + codtar.getInt(1) + "' WHERE  `sub_grupo_empresa`.`idsub_grupo_empresa` =" + grupoEmpresa.getIdGrupo() + "");
				closeConnection();
				System.err.println("ok conexion 2");return true;
			} else {
				Statement consulta = con.createStatement();
				ResultSet tabla = consulta.executeQuery("select * from "+dtbs+".sub_grupo_empresa where nombre_empresa='" + grupoEmpresa.getNombreGrupo() + "'");

				if (tabla.next()) {
					opVentana.reportarError("Ya existe un grupo de empresa con este nombre.");
					closeConnection();
					return false;
				} else {
					Statement tar = con.createStatement();
					ResultSet codtar = tar.executeQuery("SELECT  `idtarifas` FROM  `tarifas` WHERE  `descripcion` LIKE  '" + grupoEmpresa.getTarifa() + "'");
					codtar.next();

					Statement Tusu = con.createStatement();
					ResultSet codEmpresa = Tusu.executeQuery("SELECT  `id_empresa` FROM  `empresa` WHERE  `razon_social` LIKE  '" + grupoEmpresa.getEmpresa() + "'");
					codEmpresa.next();

					Statement ps = con.createStatement();
					ps.executeUpdate("UPDATE  `"+dtbs+"`.`sub_grupo_empresa` SET  `nombre_empresa` =  '" + grupoEmpresa.getNombreGrupo() + "',`codEmpresa` =  '" + codEmpresa.getInt(1) + "',`codTarifa` =  '" + codtar.getInt(1) + "' WHERE  `sub_grupo_empresa`.`idsub_grupo_empresa` =" + grupoEmpresa.getIdGrupo() + "");
					closeConnection();
					System.err.println("ok conexion 2");return true;

				}
			}

		} catch (Exception e) {
			
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 40> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarTD(OpTipoDato opVentana, TipoDato TD, String NombreAnt) {
		conectar();

		try {
			if (TD.getNombreTD().equals(NombreAnt)) {
				Statement ps = con.createStatement();
				ps.executeUpdate("UPDATE  `"+dtbs+"`.`tipo_de_datos` SET  `Nombre` =  '" + TD.getNombreTD() + "',`Sigla` =  '" + TD.getSiglaTD().toUpperCase() + "' WHERE  `tipo_de_datos`.`idTipo_de_datos` =" + TD.getIdTD() + "");
				closeConnection();
				System.err.println("ok conexion 2");return true;

			} else {

				Statement consulta = con.createStatement();
				ResultSet tabla = consulta.executeQuery("select * from "+dtbs+".tipo_de_datos where Nombre='" + TD.getNombreTD() + "'");

				if (tabla.next()) {
					opVentana.reportarError("Ya existe un tipo de dato con este nombre.");
					closeConnection();
					return false;
				} else {

					Statement ps = con.createStatement();
					ps.executeUpdate("UPDATE  `"+dtbs+"`.`tipo_de_datos` SET  `Nombre` =  '" + TD.getNombreTD() + "',`Sigla` =  '" + TD.getSiglaTD().toUpperCase() + "' WHERE  `tipo_de_datos`.`idTipo_de_datos` =" + TD.getIdTD() + "");
					closeConnection();
					System.err.println("ok conexion 2");return true;
				}
			}

		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 41> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarOPTD(OpOPTD opVentana, ArrayList<OTD> paraAgregar, ArrayList<OTD> paraEliminar) {
		conectar();

		try {


			Iterator<OTD> i = paraAgregar.iterator();
			while (i.hasNext()) {

				OTD elemento = i.next();
				System.out.println(elemento.getOpcion() + "aki Opcion");

				try {
					String consul = "INSERT INTO  `"+dtbs+"`.`opciones_tipo_de_datos` (`Opcion` ,`idTD`)VALUES (?,?);";
					PreparedStatement ps = con.prepareStatement(consul);
					ps.setString(1, elemento.getOpcion());
					ps.setInt(2, elemento.getIdTD());
					ps.executeUpdate();

				} catch (SQLException e) {
					
					
					Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 42> en la base de datos : "+e.toString());
					closeConnection();
					return false;
				}

			}

			Iterator<OTD> i2 = paraEliminar.iterator();
			while (i2.hasNext()) {
				OTD elemento2 = i2.next();

				try {
					Statement ps2 = con.createStatement();
					ps2.executeUpdate("DELETE FROM `"+dtbs+"`.`opciones_tipo_de_datos` WHERE `opciones_tipo_de_datos`.`idOpTD` = " + elemento2.getIdOTD() + ";");

				} catch (SQLException e) {
					
					
					Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 43> en la base de datos : "+e.toString());
					closeConnection();
					return false;
				}

			}

			closeConnection();
			System.err.println("ok conexion 2");return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 44> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}
	}

	public boolean editarSeccion(OpSecciones opVentana, Secciones seccion, String NombreAnt) {
		conectar();

		try {
			if (seccion.getNombre().equals(NombreAnt)) {
				Statement ps = con.createStatement();
				ps.executeUpdate("UPDATE  `"+dtbs+"`.`secciones` SET  `nombre` =  '" + seccion.getNombre() + "',`sigla` =  '" + seccion.getSigla().toUpperCase() + "' WHERE  `secciones`.`idsecciones` =" + seccion.getIdSeccion() + "");
				closeConnection();
				System.err.println("ok conexion 2");return true;

			} else {

				Statement consulta = con.createStatement();
				ResultSet tabla = consulta.executeQuery("select * from "+dtbs+".secciones where nombre='" + seccion.getNombre() + "'");

				if (tabla.next()) {
					opVentana.reportarError("Ya existe una sección con este nombre.");
					closeConnection();
					return false;
				} else {

					Statement ps = con.createStatement();
					ps.executeUpdate("UPDATE  `"+dtbs+"`.`secciones` SET  `nombre` =  '" + seccion.getNombre() + "',`sigla` =  '" + seccion.getSigla().toUpperCase() + "' WHERE  `secciones`.`idsecciones` =" + seccion.getIdSeccion() + "");
					closeConnection();
					System.err.println("ok conexion 2");return true;
				}
			}

		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 45> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarProtocolo(OpProtocolo opVentana, protocolo  protocolo, ArrayList<itemProtocolo> ParaModificar,ArrayList<itemProtocolo> ParaEliminar,String codAntiguo) {
		conectar();

		try {
			Statement ciu = con.createStatement();
			ResultSet codciu = ciu.executeQuery("SELECT  * FROM  `protocolo` WHERE  `codigo` LIKE  '" + protocolo.getCodigo() + "'");
			
			if (codciu.next()&&!codAntiguo.equals(protocolo.getCodigo())) {
				opVentana.reportarError("Ya existe un protocolo con este codigo");
				return false;
			} else {
				Statement ps = con.createStatement();
				ps.executeUpdate("UPDATE  `"+dtbs+"`.`protocolo` SET  `nombre` =  '" + protocolo.getNombre() + "',`codigo` =  '" + protocolo.getCodigo() .toUpperCase()+ "',`cod_seccion` =  '" + protocolo.getCodSeccion() +"' ,`id_planilla` =  '" + protocolo.getIdPlanilla() +"',`orden` =  '" + protocolo.getOrden() +"' WHERE  `protocolo`.`idProtocolo` =" + protocolo.getIdProtocolo() + "");
				
				for (int i = 0; i < ParaModificar.size(); i++) {
					if (ParaModificar.get(i).getIdItemProtocolo()!=-1) {
						Statement ps1 = con.createStatement();
						ps1.executeUpdate("UPDATE  `"+dtbs+"`.`item_protocolo` SET  `nombre` =  '" + ParaModificar.get(i).getNombre() + "',`cod_tipo_de_dato` =  '" + ParaModificar.get(i).getCodTD() +"' ,`Unidad_de_medida` =  '" + ParaModificar.get(i).getUnidad() +"',`Abreviatura_unidad` =  '" + ParaModificar.get(i).getAbreviatura() +"',`Valor_normal` =  '" + ParaModificar.get(i).getValor() +"',`Desde` =  '" + ParaModificar.get(i).getDesde() +"' ,`Hasta` =  '" + ParaModificar.get(i).getHasta() +"' ,`generos` =  '" + ParaModificar.get(i).getGenero() +"' ,`orden` =  '" + ParaModificar.get(i).getOrden()+"' ,`interpretacion` =  '" + ParaModificar.get(i).getInterpreta() +"' WHERE  `item_protocolo`.`idItem_protocolo` =" + ParaModificar.get(i).getIdItemProtocolo() + "");

					}else {
						itemProtocolo IP = ParaModificar.get(i);
						
						
						
						String seleccion = "INSERT INTO `"+dtbs+"`.`item_protocolo` (`cod_protocolo`, `Nombre`,`cod_tipo_de_dato`,`Unidad_de_medida`,`Abreviatura_unidad`,`Valor_normal`,`Desde`,`Hasta`,`generos`,`orden`,`interpretacion`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
						PreparedStatement ps1 = con.prepareStatement(seleccion);
						ps1.setInt(1, protocolo.getIdProtocolo());
						ps1.setString(2, IP.getNombre());
						ps1.setInt(3, IP.getCodTD());
						ps1.setString(4, IP.getUnidad());
						ps1.setString(5, IP.getAbreviatura());
						ps1.setString(6, IP.getValor());
						ps1.setString(7, IP.getDesde()+"");
						ps1.setString(8, IP.getHasta()+"");
						ps1.setInt(9, IP.getGenero());
						ps1.setInt(10, IP.getOrden());
						ps1.setString(11, IP.getInterpreta());
						ps1.executeUpdate();
					}
				}
				
				for (int i = 0; i < ParaEliminar.size(); i++) {
						if (ParaEliminar.get(i).getIdItemProtocolo()!=-1) {
							
						
							Statement ps1 = con.createStatement();
					ps1.executeUpdate("DELETE FROM `"+dtbs+"`.`item_protocolo` WHERE `item_protocolo`.`idItem_protocolo` = "+ParaEliminar.get(i).getIdItemProtocolo());
					
						} 
					
				}
				
				closeConnection();
				System.err.println("ok conexion 2");return true;

		
			}
			
				

		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 46> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarExamen(OpExamen opVentana, Examen examen, ArrayList<itemTarifa> ParaModificar,int[] dias,String codAntiguo) {
		conectar();

		try {
			Statement ciu = con.createStatement();
			ResultSet codciu = ciu.executeQuery("SELECT  * FROM  `examen` WHERE  `codigo` LIKE  '" + examen.getSigla() + "'");
			
			if (codciu.next()&&!codAntiguo.equals(examen.getSigla())) {
				opVentana.reportarError("Ya existe un examen con este código");
				closeConnection();
				return false;
			} else {
				Statement ps = con.createStatement();
				ps.executeUpdate("UPDATE  `"+dtbs+"`.`examen` SET  `Nombre` =  '" + examen.getNombre() + "',`Cups` =  '" + examen.getCups()+ "',`Duracion_dias` =  '" +examen.getDuracion() +"' ,`Nivel` =  '" + examen.getNivel() +"',`id_muestra` =  '" + examen.getIdMuestra() +"',`id_dias` =  '" + examen.getIdDias() +"',`stickers` =  '" + examen.getStickers() +"',`cod_protocolo` =  '" + examen.getCodProtocolo() +"',`codigo` =  '" + examen.getSigla() +"',`cod_Bayer` =  '" +examen.getCodBayer()+"',`se_remite` =  '" + examen.getSeRemite()+"' WHERE  `examen`.`idExamen` =" +examen.getIdExamen() + "");
				
				for (int i = 0; i < ParaModificar.size(); i++) {
					
						Statement ps1 = con.createStatement();
						ps1.executeUpdate("UPDATE  `"+dtbs+"`.`tabla_tarifas` SET  `cod_examen` =  '" + ParaModificar.get(i).getCodExamen() + "',`Cod_tarifa` =  '" + ParaModificar.get(i).getCodTarifa() +"' ,`Valor` =  '" + ParaModificar.get(i).getValor() +"',`Recarg_urgencias` =  '" + ParaModificar.get(i).getRecargaUrgencias() +"',`Recarg_festivo` =  '" + ParaModificar.get(i).getRecargaFestivos() +"',`Recarg_especial` =  '" + ParaModificar.get(i).getRecargaEspecial() +"' WHERE  `tabla_tarifas`.`idTabla_tarifas` =" + ParaModificar.get(i).getIdItemTarifa() + "");
						
		
				}
				
				Statement ps2 = con.createStatement();
				ps2.executeUpdate("UPDATE  `"+dtbs+"`.`tabla_dias` SET  `lunes` =  '" + dias[0] + "',`martes` =  '" + dias[1] +"' ,`miercoles` =  '" +dias[2] +"',`jueves` =  '" + dias[3]+"',`viernes` =  '" + dias[4]+"',`sabado` =  '" +dias[5] +"',`domingo` =  '" + dias[6]+"' WHERE  `tabla_dias`.`idTabla_dias` =" + examen.getIdDias() + "");
				
				
				closeConnection();
				System.err.println("ok conexion 2");return true;

		
			}
			
				

		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 47> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarTipoMuestra(OpTipoMuestras  opVentana, TipoMuestra TM, String NombreAnt) {
		conectar();

		try {
			if (TM.getSigla().equals(NombreAnt)) {
				Statement ps = con.createStatement();
				ps.executeUpdate("UPDATE  `"+dtbs+"`.`tipo_muestras` SET  `Nombre` =  '" + TM.getNombre() + "',`sigla` =  '" + TM.getSigla().toUpperCase() + "' WHERE  `tipo_muestras`.`idTipo_muestras` =" + TM.getIdTipoMuestra() + "");
				closeConnection();
				System.err.println("ok conexion 2");return true;

			} else {

				Statement consulta = con.createStatement();
				ResultSet tabla = consulta.executeQuery("select * from "+dtbs+".tipo_muestras where sigla='" + TM.getSigla() + "'");

				if (tabla.next()) {
					opVentana.reportarError("Ya existe un tipo de muestra con esta Sigla.");
					closeConnection();
					return false;
				} else {

					Statement ps = con.createStatement();
					ps.executeUpdate("UPDATE  `"+dtbs+"`.`tipo_muestras` SET  `Nombre` =  '" + TM.getNombre() + "',`sigla` =  '" + TM.getSigla().toUpperCase() + "' WHERE  `tipo_muestras`.`idTipo_muestras` =" + TM.getIdTipoMuestra() + "");
					closeConnection();
					System.err.println("ok conexion 2");return true;
				}
			}

		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 48> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarCiudad(OpCiudades  opVentana, int id, String NombreAnt) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`ciudad` SET  `nombre_ciudad` =  '" + NombreAnt + "'  WHERE  `ciudad`.`idciudad` = " + id + "");
		closeConnection();
		System.err.println("ok conexion 2");return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 49> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarPerfil(OpPerfiles  opVentana,PerfilExamenes perfil,ArrayList<Examen> paraEliminar,ArrayList<Examen> paraAgregar) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`perfiles` SET  `Nombre` =  '" + perfil.getNombrePerfil() + "'  WHERE  `perfiles`.`idPerfiles` = " + perfil.getIdPerfil() + "");
		
		
		for (int j = 0; j < paraEliminar.size(); j++) {
			Examen exa=paraEliminar.get(j);
			
			Statement ps2 = con.createStatement();
			ps2.executeUpdate("DELETE FROM  `"+dtbs+"`.`item_x_perfiles`  WHERE `item_x_perfiles`.`Perfiles_idPerfiles` = " + perfil.getIdPerfil() + " and  `item_x_perfiles`.`Examen_idExamen` = "+ exa.getIdExamen());
			
		}
		
		
		for (int i = 0; i < paraAgregar.size(); i++) {
			Examen examen=paraAgregar.get(i);
			
			String seleccio1 = "INSERT INTO `"+dtbs+"`.`item_x_perfiles` (`Perfiles_idPerfiles`, `Examen_idExamen`) VALUES (?,?)";
			PreparedStatement ps1 = con.prepareStatement(seleccio1);
			ps1.setInt(1,perfil.getIdPerfil());
			ps1.setInt(2,examen.getIdExamen());

			ps1.executeUpdate();

		}
		closeConnection();
		System.err.println("ok conexion 2");return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 50> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarSala(OpSalas  opVentana, int id,String nombreN,String siglaN) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`sala` SET  `sigla` =  '" + siglaN + "',`descripcion` =  '" + nombreN + "'  WHERE  `sala`.`idsala` = " + id + "");
		closeConnection();
		System.err.println("ok conexion 2");return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 51> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarParentesco(OpParentesco  opVentana, int id, String parentesco) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`tipo_parentesco` SET  `descaripcion` =  '" + parentesco + "'  WHERE  `tipo_parentesco`.`idtipo_parentesco` = " + id + "");
		closeConnection();
		System.err.println("ok conexion 2");return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 52> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}
	
	public boolean editarPlanPYP(OpPyP  opVentana, int id, String nombre) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`planes_pyp` SET  `descripcion` =  '" + nombre + "'  WHERE  `planes_pyp`.`idplanes_pyp` = " + id + "");
		closeConnection();
		System.err.println("ok conexion 2");return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 53> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarEspecialidad(OpEspecialidades  opVentana, int id,String nombreN,String siglaN) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`tipo_especialidad` SET  `sigla` =  '" + siglaN + "',`descripcion` =  '" + nombreN + "'  WHERE  `tipo_especialidad`.`idtipo_especialidad` = " + id + "");
		closeConnection();
		System.err.println("ok conexion 2");return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 54> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarLaboratorio(OpLaboratorios opVentana, Laboratorio lab, String NitAnt) {
		conectar();

		try {
			if (lab.getNit().equals(NitAnt)) {
				Statement ps = con.createStatement();
				ps.executeUpdate("UPDATE  `"+dtbs+"`.`laboratorios` SET  `doc_laboratorio` =  '" + lab.getNit() + "',`nombre` =  '" + lab.getRazonSocial() + "',`direccion` =  '" + lab.getDireccion() + "',`ciudad` =  '" + lab.getIdCiudad() + "',`inf_adicional` =  '" + lab.getInfAdicional() + "' WHERE  id_laboratorios  =" + lab.getIdLab()+ "");
				closeConnection();
				System.err.println("ok conexion 2");return true;

			} else {

				Statement consulta = con.createStatement();
				ResultSet tabla = consulta.executeQuery("select * from "+dtbs+".laboratorios where doc_laboratorio='" + lab.getNit() + "'");

				if (tabla.next()) {
					opVentana.reportarError("Ya existe un laboratorio con este Nit.");
					closeConnection();
					return false;
				} else {

					Statement ps = con.createStatement();
					ps.executeUpdate("UPDATE  `"+dtbs+"`.`laboratorios` SET  `doc_laboratorio` =  '" + lab.getNit() + "',`nombre` =  '" + lab.getRazonSocial() + "',`direccion` =  '" + lab.getDireccion() + "',`ciudad` =  '" + lab.getIdCiudad() + "',`inf_adicional` =  '" + lab.getInfAdicional() + "' WHERE  id_laboratorios =" + lab.getIdLab()+ "");
					closeConnection();
					System.err.println("ok conexion 2");return true;
				}
			}

		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 55> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarTarifa(OpTarifas  opVentana, int id,String nombreN,String recalcucloN,String nombreAn,String recalculoAn) {
		conectar();

		try {
			if (!nombreAn.equals(nombreN)) {
				Statement consulta = con.createStatement();
				ResultSet tabla = consulta.executeQuery("select * from "+dtbs+".tarifas where descripcion='" + nombreN + "'");
				if (tabla.next()) {
					opVentana.reportarError("Ya existe una tarifa con este nombre");
					closeConnection();
					return false;
				} else {
					Statement ps = con.createStatement();
					ps.executeUpdate("UPDATE  `"+dtbs+"`.`tarifas` SET  `recalculo` =  '" + recalcucloN + "',`descripcion` =  '" + nombreN + "'  WHERE  `tarifas`.`idtarifas` = " + id + "");
					
					if (!recalculoAn.equals(recalcucloN)) {
						
						Statement consultaE = con.createStatement();
						ResultSet tablaE = consultaE.executeQuery("select  `examen`.`idExamen` from "+dtbs+".examen order by idExamen asc");
						
						while(tablaE.next()){
							Statement consultaTP = con.createStatement();
							ResultSet tablaTP = consultaTP.executeQuery("select tabla_tarifas.valor from "+dtbs+".tabla_tarifas where cod_examen = "+tablaE.getInt(1)+" and  Cod_tarifa =0");
							tablaTP.next(); 
							
							int valorPrincipal=tablaTP.getInt(1);
							int recalculoInt=Integer.parseInt(recalcucloN);
							int valorCalculado=((valorPrincipal+((valorPrincipal*recalculoInt)/100))/100)*100;
							
							Statement psF = con.createStatement();
							psF.executeUpdate("UPDATE  `"+dtbs+"`.`tabla_tarifas` SET  `Valor` =  '" + valorCalculado + "'  WHERE  `tabla_tarifas`.`cod_examen` = " +tablaE.getInt(1) + "  and `tabla_tarifas`.`Cod_tarifa` = " + id + "");
							
						}
					}
					
					closeConnection();
					System.err.println("ok conexion 2");return true;
				}
			} else {
				Statement ps = con.createStatement();
				ps.executeUpdate("UPDATE  `"+dtbs+"`.`tarifas` SET  `recalculo` =  '" + recalcucloN + "'  WHERE  `tarifas`.`idtarifas` = " + id + "");
				
				if (!recalculoAn.equals(recalcucloN)) {
					
					Statement consultaE = con.createStatement();
					ResultSet tablaE = consultaE.executeQuery("select  `examen`.`idExamen` from "+dtbs+".examen order by idExamen asc");
					
					while(tablaE.next()){
						Statement consultaTP = con.createStatement();
						ResultSet tablaTP = consultaTP.executeQuery("select tabla_tarifas.valor from "+dtbs+".tabla_tarifas where cod_examen = "+tablaE.getInt(1)+" and  Cod_tarifa =0");
						tablaTP.next(); 
						
						int valorPrincipal=tablaTP.getInt(1);
						int recalculoInt=Integer.parseInt(recalcucloN);
						int valorCalculado=((valorPrincipal+((valorPrincipal*recalculoInt)/100))/100)*100;
						
						Statement psF = con.createStatement();
						psF.executeUpdate("UPDATE  `"+dtbs+"`.`tabla_tarifas` SET  `Valor` =  '" + valorCalculado + "'  WHERE  `tabla_tarifas`.`cod_examen` = " +tablaE.getInt(1) + "  and `tabla_tarifas`.`Cod_tarifa` = " + id + "");
						
					}
				}
				
				closeConnection();
				System.err.println("ok conexion 2");return true;
			}
			
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 56> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	
	public boolean editarControlAcceso(OpControlAcceso  opVentana, Usuario usuario) {
		conectar();

		try {
			
			Statement psF = con.createStatement();
			psF.executeUpdate("UPDATE  `"+dtbs+"`.`usuarios` SET  `acceso` =  '"+usuario.getAcceso()+"' WHERE  `usuarios`.`idUsuarios` ="+usuario.getIdUsuario());
			
			
			closeConnection();
			System.err.println("ok conexion 2");return true;
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 57> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean configLaboratorio(OpMiLaboratorio opVentana, MiLaboratorio lab) {
		conectar();


		try {
			

			Statement ps = con.createStatement();
			ps.executeUpdate("UPDATE  `"+dtbs+"`.`0laboratorio` SET  `razonSocial` =  '"+lab.getRazonSocial()+"',`tipoDoc` =  '"+lab.getTipoDoc()+"',`numeroDoc` =  '"+lab.getNumeroDoc()+"',`codIPS` =  '"+lab.getCodigoIPS()+"',`dirección` =  '"+lab.getDireccion()+"',`tel1` =  '"+lab.getTelefono1()+"',`tel2` =  '"+lab.getTelefono2()+"',`email1` =  '"+lab.getEmail1()+"',`email2` =  '"+lab.getEmai2()+"' WHERE  `0laboratorio`.`id` =1");
			

		
			if(lab.getLogoIS()!=null){
				
				PreparedStatement stmt = con.prepareStatement("UPDATE  `"+dtbs+"`.`0laboratorio` SET  `logo` = ? WHERE  `0laboratorio`.`id` =?");
				stmt.setBlob(1,lab.getLogoIS());
				stmt.setString(2, "1");
				stmt.executeUpdate();
				
				
				
			}
			
			
		
		
			closeConnection();
			System.err.println("ok conexion 2");return true;

		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 58> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean configEncaWeb(OpTamanosPapel opVentana, encaFirma encaFirma,InputStream firma,InputStream encabe) {
		conectar();

		try {
			


			Statement ps = con.createStatement();
			ps.executeUpdate("UPDATE  `"+dtbs+"`.`encabezadoweb` SET  `queReportar` =  '"+encaFirma.getQueReporta()+"' , `quienFirma` = '"+encaFirma.getQuienFirma1()+"', `quienFirma2` = '"+encaFirma.getQuienFirma2()+"'  WHERE  `encabezadoweb`.`id` =1");
			
		
				
			if(encabe!=null){
				
				PreparedStatement stmt = con.prepareStatement("UPDATE  `"+dtbs+"`.`encabezadoweb` SET  `encabezado` = ? WHERE  `encabezadoweb`.`id` =1");
				stmt.setBinaryStream(1,encabe);
				stmt.executeUpdate();
				
				
				
			}
			
			
		       
		        
		if(firma!=null){
			
		
				System.err.println("Firma = "+firma.available());
				PreparedStatement stmt1 = con.prepareStatement("UPDATE  `"+dtbs+"`.`encabezadoweb` SET  `firma` = ? WHERE  `encabezadoweb`.`id` =1");
				stmt1.setBinaryStream(1,firma);
			
				stmt1.executeUpdate();
				
				
				
			}
			
			



		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 58.1> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}finally{
		System.err.println("ok conexion 2");return true;
	}

	}
	

	
	public boolean configSede(OpSede opVentana, Sede sede) {
		conectar();


		try {
			

			Statement ps = con.createStatement();
			ps.executeUpdate("UPDATE  `"+dtbs+"`.`sedes` SET  `ciudad` =  '"+sede.getCiudad()+"',`nombre` =  '"+sede.getNombre()+"',`sede` =  '"+sede.getSede()+"',`principal` =  '"+sede.getSprincipal()+"',`alertas` =  '"+sede.getSalertas()+"',`secundario` =  '"+sede.getSsecundario()+"',`	iconosNegros` =  '"+sede.getSiconosNegros()+"'  WHERE  `sedes`.`idSede` =1");
			
		
			closeConnection();
			System.err.println("ok conexion 2");return true;

		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 58.1> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarRecepcion(OpCambiosRecepcion  opVentana, Recepcion recepcion) {
		conectar();

		try {
			
			Statement psF = con.createStatement();
			psF.executeUpdate("UPDATE  `"+dtbs+"`.`recepcion` SET  `Autorizacion` =  '"+recepcion.getAutorizacion()+"' , `numOrden` =  '"+recepcion.getNumeroOrden()+"' , `codPyP` =  '"+recepcion.getCodEPyP()+"' , `fechaOrden` =  '"+  new java.sql.Date(recepcion.getFechaOrden().getTime()) +"' , `codSala` =  '"+recepcion.getCodSala()+"' , `codTipoUsuario` =  '"+recepcion.getCodTipoUsuario()+"' , `codAmbitoProcedimiento` =  '"+recepcion.getCodAmbito()+"' , `codFinalidadProcedimiento` =  '"+recepcion.getCodFinalidad()+"' , `codMedico` =  '"+recepcion.getCodMedico()+"' , `fechaTomaMuestra` =  '"+ new java.sql.Date(recepcion.getFechaTomaMuestra().getTime()) +"' , `cama` =  '"+recepcion.getCama()+"' , `numeroMuestra` =  '"+recepcion.getNumeroMuestra()+"' , `Observaciones` =  '"+recepcion.getObservaciones()+"' WHERE  `recepcion`.`idRecepcion` ="+recepcion.getIdREcepcion());
			
			
			closeConnection();
			System.err.println("ok conexion 2");return true;
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 57.1> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	
	// # ############################################################################ METODOS DE ELIMINACION######################################################################################

	
	

	//ok
	public boolean eliminarPaciente(String idPaciente) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`paciente` SET  `activo` =0  WHERE  `paciente`.`idpaciente` =" + idPaciente + "");
		closeConnection();
		System.err.println("ok conexion 2");return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 59> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	//ok
	public boolean eliminarBacteriologo(String idBact) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`bacteriologo` SET  `activo` =0  WHERE  `bacteriologo`.`cod_Bacteriologo` =" + idBact + "");
		closeConnection();
		System.err.println("ok conexion 2");return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 60> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	//ok
	public boolean eliminarMedico(String  idMedico) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`medico` SET  `activo` =0  WHERE  `medico`.`idmedico` =" + idMedico + "");
		closeConnection();
		System.err.println("ok conexion 2");return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 61> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	//ok
		public boolean eliminarExamen(int  idExamen) {
			conectar();

			try {	
				
				Statement ps = con.createStatement();
			ps.executeUpdate("UPDATE  `"+dtbs+"`.`examen` SET  `activo` =0  WHERE  `examen`.`idExamen` =" + idExamen + "");
			closeConnection();
			System.err.println("ok conexion 2");return true;

			
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 62> en la base de datos : "+e.toString());
				closeConnection();
				return false;
			}

		}

	
	//ok
		public boolean eliminarEmpresa(int idEmpresa) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`empresa` SET  `activo` =0  WHERE  `empresa`.`id_empresa` =" + idEmpresa + "");
		closeConnection();
		System.err.println("ok conexion 2");return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 63> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	//ok
	public boolean eliminarGrupoEmpresa(int grupoEmpresa) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`sub_grupo_empresa` SET  `activo` =0  WHERE  `sub_grupo_empresa`.`idsub_grupo_empresa` =" + grupoEmpresa + "");
		closeConnection();
		System.err.println("ok conexion 2");return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 64> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}
	
	//ok
	public boolean eliminarTD(TipoDato TD) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`tipo_de_datos` SET  `activo` =0  WHERE  `tipo_de_datos`.`idTipo_de_datos` =" + TD.getIdTD() + "");
		closeConnection();
		System.err.println("ok conexion 2");return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 65> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	//ok
	public boolean eliminarSeccion(Secciones seccion) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`secciones` SET  `activo` =0  WHERE  `secciones`.`	idsecciones` =" + seccion.getIdSeccion() + "");
		closeConnection();
		System.err.println("ok conexion 2");return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 66> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	//	ok
	public boolean eliminarTipoMuestra(OpTipoMuestras  opVentana, int idmuestra) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`tipo_muestras` SET  `activo` =0  WHERE  `tipo_muestras`.`idTipo_muestras` =" + idmuestra + "");
		closeConnection();
		System.err.println("ok conexion 2");return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 67> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}
	
	
	// ########################################


	//ok
	public boolean eliminarCiudad(OpCiudades  opVentana, int id) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`ciudad` SET  `activo` =0  WHERE  `ciudad`.`idciudad` =" + id + "");
		closeConnection();
		System.err.println("ok conexion 2");return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 68> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	//ok
	public boolean eliminarSala(OpSalas  opVentana, int id) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`sala` SET  `activo` =0  WHERE  `sala`.`idsala` =" + id + "");
		closeConnection();
		System.err.println("ok conexion 2");return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 69> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}
	
	//ok
	public boolean eliminarParentesco(OpParentesco  opVentana, int id) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`tipo_parentesco` SET  `activo` =0  WHERE  `tipo_parentesco`.`idtipo_parentesco` =" + id + "");
		closeConnection();
		System.err.println("ok conexion 2");return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 70> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	//ok
	public boolean eliminarPlanPYP(OpPyP  opVentana, int id) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`planes_pyp` SET  `activo` =0  WHERE  `planes_pyp`.`idplanes_pyp` =" + id + "");
		closeConnection();
		System.err.println("ok conexion 2");return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 71> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	//ok
	public boolean eliminarEspecialidad(OpEspecialidades  opVentana, int id) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`tipo_especialidad` SET  `activo` =0  WHERE  `tipo_especialidad`.`idtipo_especialidad` =" + id + "");
		closeConnection();
		System.err.println("ok conexion 2");return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 72> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}
	
	//ok
	public boolean eliminarLaboratorio(OpLaboratorios  opVentana, int idLab) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`laboratorios` SET  `activo` =0  WHERE  `laboratorios`.`id_laboratorios` =" + idLab + "");
		closeConnection();
		System.err.println("ok conexion 2");return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 73> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}
	
	//ok
	public boolean eliminarTarifa(OpTarifas  opVentana, int idTarifa) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`tarifas` SET  `activo` =0  WHERE  `tarifas`.`idtarifas` =" + idTarifa + "");
		closeConnection();
		System.err.println("ok conexion 2");return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 74> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}
	
	//ok
	public boolean eliminarProtocolo(OpProtocolo opVentana, int idProtocolo) {
			conectar();

			try {	
				
				Statement ps = con.createStatement();
			ps.executeUpdate("UPDATE  `"+dtbs+"`.`protocolo` SET  `activo` =0  WHERE  `protocolo`.`idProtocolo` =" + idProtocolo + "");
			closeConnection();
			System.err.println("ok conexion 2");return true;

			
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 75> en la base de datos : "+e.toString());
				closeConnection();
				return false;
			}

		}
		
	//ok
	public boolean AnularRecepcion(OpAnularRecepcion opVentana, int idRecepcion) {
					conectar();

					try {	
						
						Statement ps = con.createStatement();
					ps.executeUpdate("UPDATE  `"+dtbs+"`.`recepcion` SET  `anulada` =1  WHERE  `recepcion`.`idRecepcion` =" + idRecepcion + "");
					
					Statement pF = con.createStatement();
					pF.executeUpdate("UPDATE  `"+dtbs+"`.`facturas` SET  `anulada` =1  WHERE  `facturas`.`codRecepcion` =" + idRecepcion + "");
					closeConnection();
					System.err.println("ok conexion 2");return true;

					
					} catch (Exception e) {
						Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 75.1> en la base de datos : "+e.toString());
						closeConnection();
						return false;
					}

				}
	
	
	public boolean restaurarPassUsuWeb(final OpUsuarioWEB opVentana, final String Usuario, final int idUsuario) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`usuarioweb` SET  `pass` ='"+Usuario+"'  WHERE  `usuarioweb`.`idUsuarioWeb` =" + idUsuario );
		
	
		closeConnection();
		
		return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2  75.1> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}
				
	// #############################################################################
	// METODOS ADICIONALES DE USUARIO

	public boolean activaciones(Usuario usu, String estado) {
		conectar();

		if (estado.equals("Activar")) {
			try {
				Statement ps = con.createStatement();
				ps.executeUpdate("UPDATE  `"+dtbs+"`.`usuarios` SET  `activo` =  '" + 1 + "' WHERE  `usuarios`.`usuario` ='" + usu.getUsuario() + "'");

				closeConnection();
				System.err.println("ok conexion 2");return true;

			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 69> en la base de datos : "+e.toString());
				closeConnection();
				return false;
			}
		} else {
			try {
				Statement ps = con.createStatement();
				ps.executeUpdate("UPDATE  `"+dtbs+"`.`usuarios` SET  `activo` =  '" + 0 + "' WHERE  `usuarios`.`usuario` ='" + usu.getUsuario() + "'");

				System.out.println("desactivaaaaaa");
				closeConnection();
				System.err.println("ok conexion 2");return true;

			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 76> en la base de datos : "+e.toString());
				closeConnection();
				return false;
			}

		}

	}

	public boolean cambiarPass(String nuevaPass) {
		conectar();

		Statement ps;
		try {
			ps = con.createStatement();
			ps.executeUpdate("UPDATE `usuarios` SET  `pass` =  '" + nuevaPass + "' WHERE  `usuarios`.`idUsuarios` ='" + usuar.getIdUsuario() + "'");
			closeConnection();
			System.err.println("ok conexion 2");return true;
		} catch (SQLException e) {
			
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 77> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public String registrarAccion(Usuario usuario, String accion, String sede) {
		conectar();

		
		try {
			String seleccio = "INSERT INTO `"+dtbs+"`.`eventos` (`idUsuario`, `accion`, `sede`) VALUES (?,?,?)";
			PreparedStatement ps = con.prepareStatement(seleccio);
			if (usuario == null) {
				ps.setInt(1, 0);

			} else {
				ps.setInt(1, usuario.getIdUsuario());
			}
			ps.setString(2, accion);
			ps.setString(3, sede+"/"+Colores.estacion);
			ps.executeUpdate();
			closeConnection();
			return "true";

		} catch (SQLException e) {
			
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON-2 78> en la base de datos : "+e.toString());
			closeConnection();
			return "< ERROR CON-2 73> en la base de datos : ";
		}

	}

	public File covertir(InputStream inputStream){
	OutputStream outputStream = null;

	try {

		outputStream =  new FileOutputStream(new File("C:/6342522/temp/temp.png"));

		int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = inputStream.read(bytes)) != -1) {
			outputStream.write(bytes, 0, read);
		}

		System.out.println("Done!");

		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (outputStream != null) {
			try {
				// outputStream.flush();
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		File file=new File("C:/6342522/temp/temp.png");
			
			return file;
	} catch (IOException e) {
		e.printStackTrace();
		return null;
	} 
    
	}
	
}
