package conexion;

import interfaces_Modificadas.Colores;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

import org.apache.commons.io.IOUtils;

import com.sun.org.apache.bcel.internal.generic.ISUB;

import otrosImpresos.CuentaCobroXEmpresa;
import otrosImpresos.imprimirReporte;
import metodos.CopyInputstream;
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
public class conexion {

	private static Connection con;
	private static conexion INSTANCE = null;
	private static Usuario usuar;
	private static String host;
	private static String user;
	private static String pass;
	private static String dtbs;
	
	private  InputStream firma;
	private  InputStream firma2;
	private  InputStream encabezado;
	private  InputStream encabezado2;

	public conexion() {

	}
	
	

	public static void conectar() {
		System.out.println("intentando conectar CON CONECTAR");


		 host = Colores.getHost();
		 user = Colores.getUser();
		 pass = Colores.getPass();
		 dtbs = Colores.getDtbs();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String newConnectionURL = "jdbc:mysql://" + host + ":3306/" + dtbs + "?" + "user=" + user + "&password=" + pass;
			con = DriverManager.getConnection(newConnectionURL);
			System.out.println("abriendo  desde conexion");

		} catch (Exception e) {
			System.out.println("error al conectar");
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 3> :no es conectar con la base de datos");
			closeConnection();
			INSTANCE = null;
		}

	}

	private static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new conexion();
		}
	}

	public static conexion getInstance() {
		if (INSTANCE == null){
			createInstance();
		}
		return INSTANCE;
	}

	public static void closeConnection() {
		try {
			con.close();
			System.out.println("cerrrando desde conexion");
			INSTANCE = null;

		} catch (Exception e) {
			INSTANCE = null;
			
		}
	}

	//////////////////////////////////////////////////////////////////////////////
	
	public static void performConnection1() {
System.out.println("intentando conectar CON perform con 1");

String host = Colores.getHost();
String user = Colores.getUser();
String pass = Colores.getPass();
String dtbs = Colores.getDtbs();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String newConnectionURL = "jdbc:mysql://" + host + "/" + dtbs + "?" + "user=" + user + "&password=" + pass;
			con = DriverManager.getConnection(newConnectionURL);
		} catch (Exception e) {
			
			INSTANCE = null;
		}

	}

	private static void createInstance1() {
		if (INSTANCE == null) {
			INSTANCE = new conexion();
			performConnection1();
		}
	}
	
	public static conexion getInstannce1() {
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
					
					Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 4> en la base de datos : "+e.toString());
					closeConnection();
					return null;

				}

			} else {
				closeConnection();
				return null;

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 5> en la base de datos : "+e.toString());
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
					
					Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 6> en la base de datos : "+e.toString());
					closeConnection();
					return null;

				}

			} else {
				closeConnection();
				return null;

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 7> en la base de datos : "+e.toString());
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
				return true;
			} else {
				closeConnection();
				return false;

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 7.1> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	
	// ############################################################################ METODOS DE ADICION######################################################################################
	
	public boolean nuevoUsuario(final OpUsuario opVentana,final String usu,final String pass,final String nom, final int tiempo,final int primVez,final int activo,final int r,final int g,final int b,final int a) throws SQLException {
		conectar();

		final int tiemp = tiempo * 60000;
		
		Statement consulta = con.createStatement();
		ResultSet tabla = consulta.executeQuery("select usuario from Usuarios where usuario='" + usu + "'");

		if (tabla.next()) {
			opVentana.reportarError("El usuario ya esta en uso");
			closeConnection();
			return false;
		} else {
			
			try {

				String seleccio = "INSERT INTO `Usuarios` (`usuario`,`pass`,`nombreCompleto`,`tiempoInactivo`,`primeraVez`,`activo`)VALUES (?,?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(seleccio);
				ps.setString(1, usu);
				ps.setString(2, pass);
				ps.setString(3, nom);
				ps.setInt(4, tiemp);
				ps.setInt(5, primVez);
				ps.setInt(6, activo);
				ps.executeUpdate();

				Statement consulta2 = con.createStatement();
				ResultSet tabla2 = consulta2.executeQuery("select idUsuarios from Usuarios where usuario='" + usu + "'");
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
				
				Thread t = new Thread(new Runnable() {
					public void run()
					{
				
							try {
								conexion2.getInstance(usuar).nuevoUsuario(opVentana, usu, pass, nom, tiempo, primVez, activo, r, g, b, a);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					

					}
				});
				t.start();

				
				
				return true;
			
				
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 8> en la base de datos : "+e.toString());
				closeConnection();
				return false;
			}
		}
	}

	public boolean nuevoPaciente(final OpPaciente opVentana, final Paciente paciente) {
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
				
				Thread t = new Thread(new Runnable() {
					public void run()
					{
						conexion2.getInstance(usuar).nuevoPaciente(opVentana, paciente);

					}
				});
				t.start();
				return true;
			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 9> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoBacteriologo(final OpBacteriologo opVentana, final Bacteriologo bacteriologo) {
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
				Thread t = new Thread(new Runnable() {
					public void run()
					{
						
							conexion2.getInstance(usuar).nuevoBacteriologo(opVentana, bacteriologo);

					}
				});
				t.start();
				return true;
			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 10> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoMedico(final OpMedico opVentana, final Medico medico) {
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
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).nuevoMedico(opVentana, medico);

			}
		});
		t.start();
				return true;
		
			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 11> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevaEmpresa(final OpEmpresa opVentana,final Empresa empresa) {
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
				Thread t = new Thread(new Runnable() {
					public void run()
					{
						
							conexion2.getInstance(usuar).nuevaEmpresa(opVentana, empresa);

					}
				});
				t.start();
				return true;
			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 12> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoGrupoEmpresa(final OpGrupoEmpresa opVentana, final GruposEmpresas grupoEmpresa) {
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
				
				Thread t = new Thread(new Runnable() {
					public void run()
					{
						
							conexion2.getInstance(usuar).nuevoGrupoEmpresa(opVentana, grupoEmpresa);

					}
				});
				t.start();
				
				return true;
			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 13> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoTD(final OpTipoDato opVentana, final TipoDato TD) {
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
				
				Thread t = new Thread(new Runnable() {
					public void run()
					{
						
							conexion2.getInstance(usuar).nuevoTD(opVentana, TD);

					}
				});
				t.start();
				
				return true;
			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 14> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevSeccion(final OpSecciones opVentana, final Secciones seccion) {
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
				
				Thread t = new Thread(new Runnable() {
					public void run()
					{
						
							conexion2.getInstance(usuar).nuevSeccion(opVentana, seccion);

					}
				});
				t.start();
				
				return true;
			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 15> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoProtocolo(final OpProtocolo opVentana, final protocolo protocolo, final ArrayList<itemProtocolo> paraAgregar) {
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
				
				Thread t = new Thread(new Runnable() {
					public void run()
					{
						
							conexion2.getInstance(usuar).nuevoProtocolo(opVentana, protocolo, paraAgregar);

					}
				});
				t.start();
				
				return true;
			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 16> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoExamen(final OpExamen opVentana, final Examen examen, final ArrayList<itemTarifa> paraAgregar,final int[] dias) {
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
				String seleccion = "INSERT INTO `"+dtbs+"`.`examen` (`Nombre`, `Cups`, `Duracion_dias`,`Nivel`,`id_muestra`,`id_dias`,`cod_protocolo`,`codigo`,`cod_Bayer`,`se_remite`,`auxiliarPagina`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
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
				ps1.setString(11, examen.getAuxiWeb());
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
				
				Thread t = new Thread(new Runnable() {
					public void run()
					{
						
							conexion2.getInstance(usuar).nuevoExamen(opVentana, examen, paraAgregar, dias);

					}
				});
				t.start();
				
				return true;
			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 17> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoRecepcion(final OpItemRecepcion opVentana, final RecepcionCompleta RC) {
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
				
				Thread t = new Thread(new Runnable() {
					public void run()
					{
						
							conexion2.getInstance(usuar).nuevoRecepcion(opVentana, RC);

					}
				});
				t.start();
				
				return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 18> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean reportar(final OpReportarResultados opVentana, final ArrayList<Reporte> paraReportar,final int idItemFactura ) {
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
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).reportar(opVentana, paraReportar, idItemFactura);

			}
		});
		t.start();
		
		return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 19> en la base de datos : "+e.toString());
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
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).escribirObserva(opVentana, idItemFactura, observa);

			}
		});
		t.start();
		
		return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 19.1> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}
	
	public boolean reportar(final OpReporte7600 opVentana, final ArrayList<Reporte> paraReportar,final int idItemFactura ) {
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
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).reportar(opVentana, paraReportar, idItemFactura);

			}
		});
		t.start();
		
		return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 19> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean reportarEntrega(final OpRegistroEntrega opVentana, final RecepcionCompleta RC,final String obs , final ArrayList<itemFactura> items) {
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
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).reportarEntrega(opVentana, RC, obs, items);

			}
		});
		t.start();
		
		return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 20> en la base de datos : "+e.toString());
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
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).reportarImpreso(opVentana, IF);

			}
		});
		t.start();
		
		return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 20> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	
	public boolean nuevoCotizacion(final OpItemCotizacion opVentana, final Cotizacion coti) {
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
				
				Thread t = new Thread(new Runnable() {
					public void run()
					{
						
							conexion2.getInstance(usuar).nuevoCotizacion(opVentana, coti);

					}
				});
				t.start();
				
				return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 21> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	
	
	// #############################################################################

	public boolean nuevoTipoMuestra(final OpTipoMuestras opVentana, final TipoMuestra muestra) {
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
				
				Thread t = new Thread(new Runnable() {
					public void run()
					{
						
							conexion2.getInstance(usuar).nuevoTipoMuestra(opVentana, muestra);

					}
				});
				t.start();
				
				return true;
			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 22> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoCiudad(final OpCiudades opVentana, final String  ciudad) {
		conectar();

		try {	
			
			String seleccio = "INSERT INTO `"+dtbs+"`.`ciudad` (`nombre_ciudad`) VALUES (?)";
		PreparedStatement ps = con.prepareStatement(seleccio);
		ps.setString(1,ciudad);

		ps.executeUpdate();

		closeConnection();
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).nuevoCiudad(opVentana, ciudad);

			}
		});
		t.start();
		
		return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 23> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoPerfilExamenes(final OpPerfiles opVentana, final PerfilExamenes perfil) {
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
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).nuevoPerfilExamenes(opVentana, perfil);

			}
		});
		t.start();
		
		return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 24> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoSala(final OpSalas opVentana, final String sigla, final String Nombre) {
		conectar();

		try {	
			
		String seleccio = "INSERT INTO `"+dtbs+"`.`sala` (`sigla`,`descripcion`) VALUES (?,?)";
		PreparedStatement ps = con.prepareStatement(seleccio);
		
		ps.setString(1,sigla);
		ps.setString(2,Nombre);

		ps.executeUpdate();

		closeConnection();
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).nuevoSala(opVentana, sigla, Nombre);

			}
		});
		t.start();
		
		return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 25> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoParentesco(final OpParentesco opVentana, final String  parentesco) {
		conectar();

		try {	
			
			String seleccio = "INSERT INTO `"+dtbs+"`.`tipo_parentesco` (`descaripcion`) VALUES (?)";
		PreparedStatement ps = con.prepareStatement(seleccio);
		ps.setString(1,parentesco);

		ps.executeUpdate();

		closeConnection();
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).nuevoParentesco(opVentana, parentesco);

			}
		});
		t.start();
		
		return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 26> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoPlanPYP(final OpPyP opVentana, final String  nombre) {
		conectar();

		try {	
			
			String seleccio = "INSERT INTO `"+dtbs+"`.`planes_pyp` (`descripcion`) VALUES (?)";
		PreparedStatement ps = con.prepareStatement(seleccio);
		ps.setString(1,nombre);

		ps.executeUpdate();

		closeConnection();
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).nuevoPlanPYP(opVentana, nombre);

			}
		});
		t.start();
		
		return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 27> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoEspecialidad(final OpEspecialidades opVentana, final String sigla, final String Nombre) {
		conectar();

		try {	
			
		String seleccio = "INSERT INTO `"+dtbs+"`.`tipo_especialidad` (`sigla`,`descripcion`) VALUES (?,?)";
		PreparedStatement ps = con.prepareStatement(seleccio);
		
		ps.setString(1,sigla);
		ps.setString(2,Nombre);

		ps.executeUpdate();

		closeConnection();
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).nuevoEspecialidad(opVentana, sigla, Nombre);

			}
		});
		t.start();
		
		return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 28> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoLaboratorio(final OpLaboratorios opVentana, final Laboratorio lab) {
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
				
				Thread t = new Thread(new Runnable() {
					public void run()
					{
						
							conexion2.getInstance(usuar).nuevoLaboratorio(opVentana, lab);

					}
				});
				t.start();
				
				return true;
			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 29> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoTarifa(final OpTarifas opVentana, final String recalculo, final String Nombre) {
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
				
				Thread t = new Thread(new Runnable() {
					public void run()
					{
						
							conexion2.getInstance(usuar).nuevoTarifa(opVentana, recalculo, Nombre);

					}
				});
				t.start();
				
				return true;
				
				
			}
			
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 30> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoAbonoXpaciente(final OpAbonoXpaciente opVentana,final AbonoXPaciente abono) {
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
				
				Thread t = new Thread(new Runnable() {
					public void run()
					{
						
							conexion2.getInstance(usuar).nuevoAbonoXpaciente(opVentana, abono);

					}
				});
				t.start();
				
				return true;
				
				
		
			
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 31> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoAbonoXEmpresa(final OpAbonoXEmpresa opVentana,final AbonoXEmpresa abono) {
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
				
				Thread t = new Thread(new Runnable() {
					public void run()
					{
						
							conexion2.getInstance(usuar).nuevoAbonoXEmpresa(opVentana, abono);

					}
				});
				t.start();
				
				return true;
				
				
		
			
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 32> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoAbonoXFactura(final OpAbonoXFactura opVentana,final Factura fac,final AbonoXFactura abono) {
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
				
				Thread t = new Thread(new Runnable() {
					public void run()
					{
						
							conexion2.getInstance(usuar).nuevoAbonoXFactura(opVentana, fac, abono);

					}
				});
				t.start();
				
				return true;
				
				
		
			
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 33> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean nuevoNota(final OpNotasCD opVentana, final Nota nota) {
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
				
				Thread t = new Thread(new Runnable() {
					public void run()
					{
						
							conexion2.getInstance(usuar).nuevoNota(opVentana, nota);

					}
				});
				t.start();
				
				return true;
				
				
		
			
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 34> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	
	public boolean nuevoCuenta(final cuentaCobro cuenta) {
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
				
				Thread t = new Thread(new Runnable() {
					public void run()
					{
						
							conexion2.getInstance(usuar).nuevoCuenta(cuenta);

					}
				});
				t.start();
				
				return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 18.10> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	// #############################################################################
	// METODOS DE MODIFICACION	// ############################################################################ METODOS DE EDICION######################################################################################

	public boolean editarUsuario(final Usuario usu, final String nom, final Color color, final int tiempo) throws SQLException {
		conectar();

		try {
			Statement ps = con.createStatement();
			ps.executeUpdate("UPDATE  `"+dtbs+"`.`usuarios` SET  `nombreCompleto` =  '" + nom + "',`tiempoInactivo` =  '" + tiempo + "' WHERE  `usuarios`.`idUsuarios` ='" + usu.getIdUsuario() + "'");

			Statement ps2 = con.createStatement();
			ps2.executeUpdate("UPDATE  `"+dtbs+"`.`colores` SET  `R` =  '" + color.getRed() + "',`G` =  '" + color.getGreen() + "',`B` =  '" + color.getBlue()+ "',`A` =  '" + color.getAlpha() + "' WHERE  `colores`.`idUsuario` =" + usu.getIdUsuario());
			closeConnection();
			
			Thread t = new Thread(new Runnable() {
				public void run()
				{
					
						try {
							conexion2.getInstance(usuar).editarUsuario(usu, nom, color, tiempo);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

				}
			});
			t.start();
			
			return true;

		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 35> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarPaciente(final Paciente pac) {
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
			
			Thread t = new Thread(new Runnable() {
				public void run()
				{
					
						conexion2.getInstance(usuar).editarPaciente(pac);

				}
			});
			t.start();
			
			return true;

		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 36> en la base de datos : "+e.toString());
	closeConnection();
			return false;
		}

	}

	public boolean editarBacteriologo(final Bacteriologo bact) {
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
			
			Thread t = new Thread(new Runnable() {
				public void run()
				{
					
						conexion2.getInstance(usuar).editarBacteriologo(bact);

				}
			});
			t.start();
			
			return true;

		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 37> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarMedico(final Medico medico) {
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
			
			Thread t = new Thread(new Runnable() {
				public void run()
				{
					
						conexion2.getInstance(usuar).editarMedico(medico);

				}
			});
			t.start();
			
			return true;

		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 38> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarEmpresa(final Empresa empresa,final String antiguoNIT) {
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
			
			Thread t = new Thread(new Runnable() {
				public void run()
				{
					
						conexion2.getInstance(usuar).editarEmpresa(empresa, antiguoNIT);

				}
			});
			t.start();
			
			return true;

		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 39> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarGrupoEmpresa(final OpGrupoEmpresa opVentana, final GruposEmpresas grupoEmpresa, final String NombreAntiguo) {
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
				return true;
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
					
					Thread t = new Thread(new Runnable() {
						public void run()
						{
							
								conexion2.getInstance(usuar).editarGrupoEmpresa(opVentana, grupoEmpresa, NombreAntiguo);

						}
					});
					t.start();
					
					return true;

				}
			}

		} catch (Exception e) {
			
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 40> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarTD(final OpTipoDato opVentana, final TipoDato TD, final String NombreAnt) {
		conectar();

		try {
			if (TD.getNombreTD().equals(NombreAnt)) {
				Statement ps = con.createStatement();
				ps.executeUpdate("UPDATE  `"+dtbs+"`.`tipo_de_datos` SET  `Nombre` =  '" + TD.getNombreTD() + "',`Sigla` =  '" + TD.getSiglaTD().toUpperCase() + "' WHERE  `tipo_de_datos`.`idTipo_de_datos` =" + TD.getIdTD() + "");
				closeConnection();
				return true;

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
					
					Thread t = new Thread(new Runnable() {
						public void run()
						{
							
								conexion2.getInstance(usuar).editarTD(opVentana, TD, NombreAnt);

						}
					});
					t.start();
					
					return true;
				}
			}

		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 41> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarOPTD(final OpOPTD opVentana, final ArrayList<OTD> paraAgregar, final ArrayList<OTD> paraEliminar) {
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
					
					
					Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 42> en la base de datos : "+e.toString());
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
					
					
					Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 43> en la base de datos : "+e.toString());
					closeConnection();
					return false;
				}

			}

			closeConnection();
			
			Thread t = new Thread(new Runnable() {
				public void run()
				{
					
						conexion2.getInstance(usuar).editarOPTD(opVentana, paraAgregar, paraEliminar);

				}
			});
			t.start();
			
			return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 44> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}
	}

	public boolean editarSeccion(final OpSecciones opVentana, final Secciones seccion, final String NombreAnt) {
		conectar();

		try {
			if (seccion.getNombre().equals(NombreAnt)) {
				Statement ps = con.createStatement();
				ps.executeUpdate("UPDATE  `"+dtbs+"`.`secciones` SET  `nombre` =  '" + seccion.getNombre() + "',`sigla` =  '" + seccion.getSigla().toUpperCase() + "' WHERE  `secciones`.`idsecciones` =" + seccion.getIdSeccion() + "");
				closeConnection();
				return true;

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
					
					Thread t = new Thread(new Runnable() {
						public void run()
						{
							
								conexion2.getInstance(usuar).editarSeccion(opVentana, seccion, NombreAnt);

						}
					});
					t.start();
					
					return true;
				}
			}

		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 45> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarProtocolo(final OpProtocolo opVentana, final protocolo  protocolo, final ArrayList<itemProtocolo> ParaModificar,final ArrayList<itemProtocolo> ParaEliminar,final String codAntiguo) {
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
				
				Thread t = new Thread(new Runnable() {
					public void run()
					{
						
							conexion2.getInstance(usuar).editarProtocolo(opVentana, protocolo, ParaModificar, ParaEliminar, codAntiguo);

					}
				});
				t.start();
				
				return true;

		
			}
			
				

		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 46> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarExamen(final OpExamen opVentana, final Examen examen, final ArrayList<itemTarifa> ParaModificar,final int[] dias,final String codAntiguo) {
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
				ps.executeUpdate("UPDATE  `"+dtbs+"`.`examen` SET  `Nombre` =  '" + examen.getNombre() + "',`Cups` =  '" + examen.getCups()+ "',`Duracion_dias` =  '" +examen.getDuracion() +"' ,`Nivel` =  '" + examen.getNivel() +"',`id_muestra` =  '" + examen.getIdMuestra() +"',`id_dias` =  '" + examen.getIdDias() +"',`stickers` =  '" + examen.getStickers() +"',`cod_protocolo` =  '" + examen.getCodProtocolo() +"',`codigo` =  '" + examen.getSigla() +"',`cod_Bayer` =  '" +examen.getCodBayer()+"',`se_remite` =  '" + examen.getSeRemite()+"',`auxiliarPagina` =  '" + examen.getAuxiWeb()+"' WHERE  `examen`.`idExamen` =" +examen.getIdExamen() + "");
				
				for (int i = 0; i < ParaModificar.size(); i++) {
					
						Statement ps1 = con.createStatement();
						ps1.executeUpdate("UPDATE  `"+dtbs+"`.`tabla_tarifas` SET  `cod_examen` =  '" + ParaModificar.get(i).getCodExamen() + "',`Cod_tarifa` =  '" + ParaModificar.get(i).getCodTarifa() +"' ,`Valor` =  '" + ParaModificar.get(i).getValor() +"',`Recarg_urgencias` =  '" + ParaModificar.get(i).getRecargaUrgencias() +"',`Recarg_festivo` =  '" + ParaModificar.get(i).getRecargaFestivos() +"',`Recarg_especial` =  '" + ParaModificar.get(i).getRecargaEspecial() +"' WHERE  `tabla_tarifas`.`idTabla_tarifas` =" + ParaModificar.get(i).getIdItemTarifa() + "");
						
		
				}
				
				Statement ps2 = con.createStatement();
				ps2.executeUpdate("UPDATE  `"+dtbs+"`.`tabla_dias` SET  `lunes` =  '" + dias[0] + "',`martes` =  '" + dias[1] +"' ,`miercoles` =  '" +dias[2] +"',`jueves` =  '" + dias[3]+"',`viernes` =  '" + dias[4]+"',`sabado` =  '" +dias[5] +"',`domingo` =  '" + dias[6]+"' WHERE  `tabla_dias`.`idTabla_dias` =" + examen.getIdDias() + "");
				
				
				closeConnection();
				
				Thread t = new Thread(new Runnable() {
					public void run()
					{
						
							conexion2.getInstance(usuar).editarExamen(opVentana, examen, ParaModificar, dias, codAntiguo);

					}
				});
				t.start();
				
				return true;

		
			}
			
				

		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 47> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarTipoMuestra(final OpTipoMuestras  opVentana, final TipoMuestra TM, final String NombreAnt) {
		conectar();

		try {
			if (TM.getSigla().equals(NombreAnt)) {
				Statement ps = con.createStatement();
				ps.executeUpdate("UPDATE  `"+dtbs+"`.`tipo_muestras` SET  `Nombre` =  '" + TM.getNombre() + "',`sigla` =  '" + TM.getSigla().toUpperCase() + "' WHERE  `tipo_muestras`.`idTipo_muestras` =" + TM.getIdTipoMuestra() + "");
				closeConnection();
				return true;

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
					
					Thread t = new Thread(new Runnable() {
						public void run()
						{
							
								conexion2.getInstance(usuar).editarTipoMuestra(opVentana, TM, NombreAnt);

						}
					});
					t.start();
					
					return true;
				}
			}

		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 48> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarCiudad(final OpCiudades  opVentana, final int id, final String NombreAnt) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`ciudad` SET  `nombre_ciudad` =  '" + NombreAnt + "'  WHERE  `ciudad`.`idciudad` = " + id + "");
		closeConnection();
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).editarCiudad(opVentana, id, NombreAnt);

			}
		});
		t.start();
		
		return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 49> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarPerfil(final OpPerfiles  opVentana,final PerfilExamenes perfil,final ArrayList<Examen> paraEliminar,final ArrayList<Examen> paraAgregar) {
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
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).editarPerfil(opVentana, perfil, paraEliminar, paraAgregar);

			}
		});
		t.start();
		
		return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 50> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarSala(final OpSalas  opVentana, final int id,final String nombreN,final String siglaN) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`sala` SET  `sigla` =  '" + siglaN + "',`descripcion` =  '" + nombreN + "'  WHERE  `sala`.`idsala` = " + id + "");
		closeConnection();
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).editarSala(opVentana, id, nombreN, siglaN);

			}
		});
		t.start();
		
		return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 51> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarParentesco(final OpParentesco  opVentana, final int id, final String parentesco) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`tipo_parentesco` SET  `descaripcion` =  '" + parentesco + "'  WHERE  `tipo_parentesco`.`idtipo_parentesco` = " + id + "");
		closeConnection();
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).editarParentesco(opVentana, id, parentesco);

			}
		});
		t.start();
		
		return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 52> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}
	
	public boolean editarPlanPYP(final OpPyP  opVentana, final int id, final String nombre) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`planes_pyp` SET  `descripcion` =  '" + nombre + "'  WHERE  `planes_pyp`.`idplanes_pyp` = " + id + "");
		closeConnection();
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).editarPlanPYP(opVentana, id, nombre);

			}
		});
		t.start();
		
		return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 53> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarEspecialidad(final OpEspecialidades  opVentana, final int id,final String nombreN,final String siglaN) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`tipo_especialidad` SET  `sigla` =  '" + siglaN + "',`descripcion` =  '" + nombreN + "'  WHERE  `tipo_especialidad`.`idtipo_especialidad` = " + id + "");
		closeConnection();
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).editarEspecialidad(opVentana, id, nombreN, siglaN);

			}
		});
		t.start();
		
		return true;
		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 54> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarLaboratorio(final OpLaboratorios opVentana, final Laboratorio lab, final String NitAnt) {
		conectar();

		try {
			if (lab.getNit().equals(NitAnt)) {
				Statement ps = con.createStatement();
				ps.executeUpdate("UPDATE  `"+dtbs+"`.`laboratorios` SET  `doc_laboratorio` =  '" + lab.getNit() + "',`nombre` =  '" + lab.getRazonSocial() + "',`direccion` =  '" + lab.getDireccion() + "',`ciudad` =  '" + lab.getIdCiudad() + "',`inf_adicional` =  '" + lab.getInfAdicional() + "' WHERE  id_laboratorios  =" + lab.getIdLab()+ "");
				closeConnection();
				
				Thread t = new Thread(new Runnable() {
					public void run()
					{
						
							conexion2.getInstance(usuar).editarLaboratorio(opVentana, lab, NitAnt);

					}
				});
				t.start();
				
				return true;

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
					
					Thread t = new Thread(new Runnable() {
						public void run()
						{
							
								conexion2.getInstance(usuar).editarLaboratorio(opVentana, lab, NitAnt);

						}
					});
					t.start();
					
					return true;
				}
			}

		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 55> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarTarifa(final OpTarifas  opVentana, final int id,final String nombreN,final String recalcucloN,final String nombreAn,final String recalculoAn) {
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
					
					Thread t = new Thread(new Runnable() {
						public void run()
						{
							
								conexion2.getInstance(usuar).editarTarifa(opVentana, id, nombreN, recalcucloN, nombreAn, recalculoAn);

						}
					});
					t.start();
					
					return true;
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
						System.err.println("cod examen"+tablaE.getInt(1));
						psF.executeUpdate("UPDATE  `"+dtbs+"`.`tabla_tarifas` SET  `Valor` =  '" + valorCalculado + "'  WHERE  `tabla_tarifas`.`cod_examen` = " +tablaE.getInt(1) + "  and `tabla_tarifas`.`Cod_tarifa` = " + id + "");
						
					}
				}
				
				closeConnection();
				
				Thread t = new Thread(new Runnable() {
					public void run()
					{
						
							conexion2.getInstance(usuar).editarTarifa(opVentana, id, nombreN, recalcucloN, nombreAn, recalculoAn);

					}
				});
				t.start();
				
				return true;
			}
			
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 56> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	
	public boolean editarControlAcceso(final OpControlAcceso  opVentana, final Usuario usuario) {
		conectar();

		try {
			
			Statement psF = con.createStatement();
			psF.executeUpdate("UPDATE  `"+dtbs+"`.`usuarios` SET  `acceso` =  '"+usuario.getAcceso()+"' WHERE  `usuarios`.`idUsuarios` ="+usuario.getIdUsuario());
			
			
			closeConnection();
			
			Thread t = new Thread(new Runnable() {
				public void run()
				{
					
						conexion2.getInstance(usuar).editarControlAcceso(opVentana, usuario);

				}
			});
			t.start();
			
			return true;
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 57> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean configLaboratorio(final OpMiLaboratorio opVentana, final MiLaboratorio lab) {
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
			
			Thread t = new Thread(new Runnable() {
				public void run()
				{
					
						conexion2.getInstance(usuar).configLaboratorio(opVentana, lab);

				}
			});
			t.start();
			
			return true;

		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 58> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean configEncaWeb(final OpTamanosPapel opVentana, final encaFirma encaFirma) {
		conectar();
	
	
		
		

		try {
			

			Statement ps = con.createStatement();
			ps.executeUpdate("UPDATE  `"+dtbs+"`.`encabezadoweb` SET  `queReportar` =  '"+encaFirma.getQueReporta()+"' , `quienFirma` = '"+encaFirma.getQuienFirma1()+"', `quienFirma2` = '"+encaFirma.getQuienFirma2()+"'  WHERE  `encabezadoweb`.`id` =1");
			
		
		
			if(encaFirma.getEncaIS()!=null){
				
				int read = 0;
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				byte[] buff = new byte[1024];
				try {
					while ((read = encaFirma.getEncaIS().read(buff)) != -1) {
					    bos.write(buff, 0, read);
					}	
					byte[] streamData = bos.toByteArray();
				    encabezado=new ByteArrayInputStream(streamData);
				    encabezado2=new ByteArrayInputStream(streamData);
				    
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				PreparedStatement stmt = con.prepareStatement("UPDATE  `"+dtbs+"`.`encabezadoweb` SET  `encabezado` = ? WHERE  `encabezadoweb`.`id` =1");
				stmt.setBlob(1,encabezado);
				stmt.executeUpdate();
				
				
				
			}
			
		if(encaFirma.getFirmaIS()!=null){
			
			int read = 0;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buff = new byte[1024];
			try {
				while ((read = encaFirma.getFirmaIS().read(buff)) != -1) {
				    bos.write(buff, 0, read);
				}	
				byte[] streamData = bos.toByteArray();
			    firma=new ByteArrayInputStream(streamData);
			    firma2=new ByteArrayInputStream(streamData);
			    
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
				PreparedStatement stmt1 = con.prepareStatement("UPDATE  `"+dtbs+"`.`encabezadoweb` SET  `firma` = ? WHERE  `encabezadoweb`.`id` =1");
				stmt1.setBinaryStream(1,firma);
			
				stmt1.executeUpdate();
				
				
				
			}
			
			
		
		
			closeConnection();
			
			Thread t = new Thread(new Runnable() {
				public void run()
				{
									
						conexion2.getInstance(usuar).configEncaWeb(opVentana, encaFirma,firma2,encabezado2);

				}
			});
			t.start();
			
			return true;

		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 58.1> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	
	public boolean configSede(final OpSede opVentana, final Sede sede) {
		conectar();


		try {
			

			Statement ps = con.createStatement();
			ps.executeUpdate("UPDATE  `"+dtbs+"`.`sedes` SET  `ciudad` =  '"+sede.getCiudad()+"',`nombre` =  '"+sede.getNombre()+"',`sede` =  '"+sede.getSede()+"',`principal` =  '"+sede.getSprincipal()+"',`alertas` =  '"+sede.getSalertas()+"',`secundario` =  '"+sede.getSsecundario()+"',`	iconosNegros` =  '"+sede.getSiconosNegros()+"'  WHERE  `sedes`.`idSede` =1");
			
		
			closeConnection();
			
			Thread t = new Thread(new Runnable() {
				public void run()
				{
					
						conexion2.getInstance(usuar).configSede(opVentana, sede);

				}
			});
			t.start();
			
			return true;

		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 58.1> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public boolean editarRecepcion(final OpCambiosRecepcion  opVentana, final Recepcion recepcion) {
		conectar();

		try {
			
			Statement psF = con.createStatement();
			psF.executeUpdate("UPDATE  `"+dtbs+"`.`recepcion` SET  `Autorizacion` =  '"+recepcion.getAutorizacion()+"' , `numOrden` =  '"+recepcion.getNumeroOrden()+"' , `codPyP` =  '"+recepcion.getCodEPyP()+"' , `fechaOrden` =  '"+  new java.sql.Date(recepcion.getFechaOrden().getTime()) +"' , `codSala` =  '"+recepcion.getCodSala()+"' , `codTipoUsuario` =  '"+recepcion.getCodTipoUsuario()+"' , `codAmbitoProcedimiento` =  '"+recepcion.getCodAmbito()+"' , `codFinalidadProcedimiento` =  '"+recepcion.getCodFinalidad()+"' , `codMedico` =  '"+recepcion.getCodMedico()+"' , `fechaTomaMuestra` =  '"+ new java.sql.Date(recepcion.getFechaTomaMuestra().getTime()) +"' , `cama` =  '"+recepcion.getCama()+"' , `numeroMuestra` =  '"+recepcion.getNumeroMuestra()+"' , `Observaciones` =  '"+recepcion.getObservaciones()+"' WHERE  `recepcion`.`idRecepcion` ="+recepcion.getIdREcepcion());
			
			
			closeConnection();
			
			Thread t = new Thread(new Runnable() {
				public void run()
				{
					
						conexion2.getInstance(usuar).editarRecepcion(opVentana, recepcion);

				}
			});
			t.start();
			
			return true;
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 57.1> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	
	// # ############################################################################ METODOS DE ELIMINACION######################################################################################

	
	

	//ok
	public boolean eliminarPaciente(final String idPaciente) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`paciente` SET  `activo` =0  WHERE  `paciente`.`idpaciente` =" + idPaciente + "");
		closeConnection();
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).eliminarPaciente(idPaciente);

			}
		});
		t.start();
		
		return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 59> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	//ok
	public boolean eliminarBacteriologo(final String idBact) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`bacteriologo` SET  `activo` =0  WHERE  `bacteriologo`.`cod_Bacteriologo` =" + idBact + "");
		closeConnection();
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).eliminarBacteriologo(idBact);

			}
		});
		t.start();
		
		return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 60> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	//ok
	public boolean eliminarMedico(final String  idMedico) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`medico` SET  `activo` =0  WHERE  `medico`.`idmedico` =" + idMedico + "");
		closeConnection();
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).eliminarMedico(idMedico);

			}
		});
		t.start();
		
		return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 61> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	//ok
		public boolean eliminarExamen(final int  idExamen) {
			conectar();

			try {	
				
				Statement ps = con.createStatement();
			ps.executeUpdate("UPDATE  `"+dtbs+"`.`examen` SET  `activo` =0  WHERE  `examen`.`idExamen` =" + idExamen + "");
			closeConnection();
			
			Thread t = new Thread(new Runnable() {
				public void run()
				{
					
						conexion2.getInstance(usuar).eliminarExamen(idExamen);

				}
			});
			t.start();
			
			return true;

			
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 62> en la base de datos : "+e.toString());
				closeConnection();
				return false;
			}

		}

	
	//ok
		public boolean eliminarEmpresa(final int idEmpresa) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`empresa` SET  `activo` =0  WHERE  `empresa`.`id_empresa` =" + idEmpresa + "");
		closeConnection();
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).eliminarEmpresa(idEmpresa);

			}
		});
		t.start();
		
		return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 63> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	//ok
	public boolean eliminarGrupoEmpresa(final int grupoEmpresa) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`sub_grupo_empresa` SET  `activo` =0  WHERE  `sub_grupo_empresa`.`idsub_grupo_empresa` =" + grupoEmpresa + "");
		closeConnection();
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).eliminarGrupoEmpresa(grupoEmpresa);

			}
		});
		t.start();
		
		return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 64> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}
	
	//ok
	public boolean eliminarTD(final TipoDato TD) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`tipo_de_datos` SET  `activo` =0  WHERE  `tipo_de_datos`.`idTipo_de_datos` =" + TD.getIdTD() + "");
		closeConnection();
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).eliminarTD(TD);

			}
		});
		t.start();
		
		return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 65> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	//ok
	public boolean eliminarSeccion(final Secciones seccion) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`secciones` SET  `activo` =0  WHERE  `secciones`.`	idsecciones` =" + seccion.getIdSeccion() + "");
		closeConnection();
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).eliminarSeccion(seccion);

			}
		});
		t.start();
		
		return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 66> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	//	ok
	public boolean eliminarTipoMuestra(final OpTipoMuestras  opVentana, final int idmuestra) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`tipo_muestras` SET  `activo` =0  WHERE  `tipo_muestras`.`idTipo_muestras` =" + idmuestra + "");
		closeConnection();
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).eliminarTipoMuestra(opVentana, idmuestra);

			}
		});
		t.start();
		
		return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 67> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}
	
	
	// ########################################


	//ok
	public boolean eliminarCiudad(final OpCiudades  opVentana, final int id) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`ciudad` SET  `activo` =0  WHERE  `ciudad`.`idciudad` =" + id + "");
		closeConnection();
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).eliminarCiudad(opVentana, id);

			}
		});
		t.start();
		
		return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 68> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	//ok
	public boolean eliminarSala(final OpSalas  opVentana, final int id) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`sala` SET  `activo` =0  WHERE  `sala`.`idsala` =" + id + "");
		closeConnection();
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).eliminarSala(opVentana, id);

			}
		});
		t.start();		
		
		return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 69> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}
	
	//ok
	public boolean eliminarParentesco(final OpParentesco  opVentana, final int id) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`tipo_parentesco` SET  `activo` =0  WHERE  `tipo_parentesco`.`idtipo_parentesco` =" + id + "");
		closeConnection();
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).eliminarParentesco(opVentana, id);

			}
		});
		t.start();
		
		return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 70> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	//ok
	public boolean eliminarPlanPYP(final OpPyP  opVentana, final int id) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`planes_pyp` SET  `activo` =0  WHERE  `planes_pyp`.`idplanes_pyp` =" + id + "");
		closeConnection();
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).eliminarPlanPYP(opVentana, id);

			}
		});
		t.start();
		
		return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 71> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	//ok
	public boolean eliminarEspecialidad(final OpEspecialidades  opVentana, final int id) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`tipo_especialidad` SET  `activo` =0  WHERE  `tipo_especialidad`.`idtipo_especialidad` =" + id + "");
		closeConnection();
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).eliminarEspecialidad(opVentana, id);

			}
		});
		t.start();
		
		return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 72> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}
	
	//ok
	public boolean eliminarLaboratorio(final OpLaboratorios  opVentana, final int idLab) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`laboratorios` SET  `activo` =0  WHERE  `laboratorios`.`id_laboratorios` =" + idLab + "");
		closeConnection();
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).eliminarLaboratorio(opVentana, idLab);

			}
		});
		t.start();
		
		return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 73> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}
	
	//ok
	public boolean eliminarTarifa(final OpTarifas  opVentana, final int idTarifa) {
		conectar();

		try {	
			
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`tarifas` SET  `activo` =0  WHERE  `tarifas`.`idtarifas` =" + idTarifa + "");
		closeConnection();
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).eliminarTarifa(opVentana, idTarifa);

			}
		});
		t.start();
		
		return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 74> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}
	
	//ok
	public boolean eliminarProtocolo(final OpProtocolo opVentana, final int idProtocolo) {
			conectar();

			try {	
				
				Statement ps = con.createStatement();
			ps.executeUpdate("UPDATE  `"+dtbs+"`.`protocolo` SET  `activo` =0  WHERE  `protocolo`.`idProtocolo` =" + idProtocolo + "");
			closeConnection();
			
			Thread t = new Thread(new Runnable() {
				public void run()
				{
					
						conexion2.getInstance(usuar).eliminarProtocolo(opVentana, idProtocolo);
						
				}
			});
			t.start();
			
			return true;

			
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 75> en la base de datos : "+e.toString());
				closeConnection();
				return false;
			}

		}
		
	//ok
	public boolean AnularRecepcion(final OpAnularRecepcion opVentana, final int idRecepcion) {
					conectar();

					try {	
						
						Statement ps = con.createStatement();
					ps.executeUpdate("UPDATE  `"+dtbs+"`.`recepcion` SET  `anulada` =1  WHERE  `recepcion`.`idRecepcion` =" + idRecepcion + "");
					
					Statement pF = con.createStatement();
					pF.executeUpdate("UPDATE  `"+dtbs+"`.`facturas` SET  `anulada` =1  WHERE  `facturas`.`codRecepcion` =" + idRecepcion + "");
					closeConnection();
					
					Thread t = new Thread(new Runnable() {
						public void run()
						{
							
								conexion2.getInstance(usuar).AnularRecepcion(opVentana, idRecepcion);

						}
					});
					t.start();
					
					return true;

					
					} catch (Exception e) {
						Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 75.1> en la base de datos : "+e.toString());
						closeConnection();
						return false;
					}

				}
	
	
	public boolean restaurarPassUsuWeb(final OpUsuarioWEB opVentana, final String Usuario, final int idUsuario) {
		conectar();

		try {	
			System.err.println("Cambiando "+ Usuario);
			Statement ps = con.createStatement();
		ps.executeUpdate("UPDATE  `"+dtbs+"`.`usuarioweb` SET  `pass` ='"+Usuario+"'  WHERE  `usuarioweb`.`idUsuarioWeb` =" + idUsuario );
		
		System.err.println("Cambiando "+ Usuario);

		closeConnection();
		
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				
					conexion2.getInstance(usuar).restaurarPassUsuWeb(opVentana, Usuario,idUsuario);

			}
		});
		t.start();
		
		return true;

		
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 75.1> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}
				
	// #############################################################################
	// METODOS ADICIONALES DE USUARIO

	public boolean activaciones(final Usuario usu, final String estado) {
		conectar();

		if (estado.equals("Activar")) {
			try {
				Statement ps = con.createStatement();
				ps.executeUpdate("UPDATE  `"+dtbs+"`.`usuarios` SET  `activo` =  '" + 1 + "' WHERE  `usuarios`.`usuario` ='" + usu.getUsuario() + "'");

				closeConnection();
				
				Thread t = new Thread(new Runnable() {
					public void run()
					{
						
							conexion2.getInstance(usuar).activaciones(usu, estado);

					}
				});
				t.start();
				
				return true;

			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 69> en la base de datos : "+e.toString());
				closeConnection();
				return false;
			}
		} else {
			try {
				Statement ps = con.createStatement();
				ps.executeUpdate("UPDATE  `"+dtbs+"`.`usuarios` SET  `activo` =  '" + 0 + "' WHERE  `usuarios`.`usuario` ='" + usu.getUsuario() + "'");

				System.out.println("desactivaaaaaa");
				closeConnection();
				Thread t = new Thread(new Runnable() {
					public void run()
					{
						
							conexion2.getInstance(usuar).activaciones(usu, estado);

					}
				});
				t.start();
				return true;

			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 76> en la base de datos : "+e.toString());
				closeConnection();
				return false;
			}

		}

	}

	public boolean cambiarPass(final String nuevaPass) {
		conectar();

		Statement ps;
		try {
			ps = con.createStatement();
			ps.executeUpdate("UPDATE `usuarios` SET  `pass` =  '" + nuevaPass + "' WHERE  `usuarios`.`idUsuarios` ='" + usuar.getIdUsuario() + "'");
			closeConnection();
			
			Thread t = new Thread(new Runnable() {
				public void run()
				{
					
						conexion2.getInstance(usuar).cambiarPass(nuevaPass);

				}
			});
			t.start();
			
			return true;
		} catch (SQLException e) {
			
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 77> en la base de datos : "+e.toString());
			closeConnection();
			return false;
		}

	}

	public String registrarAccion(final Usuario usuario, final String accion, final String sede) {
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
			
			Thread t = new Thread(new Runnable() {
				public void run()
				{
					
						conexion2.getInstance(usuar).registrarAccion(usuario, accion, sede);
				}
			});
			t.start();
			
			return "true";

		} catch (SQLException e) {
			
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CON 78> en la base de datos : "+e.toString());
			closeConnection();
			return "< ERROR CON 73> en la base de datos : ";
		}

	}

}
