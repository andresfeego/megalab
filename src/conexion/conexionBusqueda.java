package conexion;

import interfaces_Modificadas.Colores;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import javax.imageio.ImageIO;
import javax.naming.BinaryRefAddr;
import javax.swing.JOptionPane;
import javax.swing.text.TabableView;

import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.RGBColor;

import auxiliares.Bacteriologo;
import auxiliares.Cotizacion;
import auxiliares.DatosAbono;
import auxiliares.Empresa;
import auxiliares.Factura;
import auxiliares.GruposEmpresas;
import auxiliares.ItemRecepcion;
import auxiliares.Laboratorio;
import auxiliares.Medico;
import auxiliares.MiLaboratorio;
import auxiliares.Paciente;
import auxiliares.PerfilExamenes;
import auxiliares.Recepcion;
import auxiliares.RecepcionCompleta;
import auxiliares.Secciones;
import auxiliares.Tarifa;
import auxiliares.TipoDato;
import auxiliares.TipoMuestra;
import auxiliares.Usuario;
import auxiliares.Examen;
import auxiliares.cuentaCobro;
import auxiliares.encaFirma;
import auxiliares.itemCotizacion;
import auxiliares.itemCuenta;
import auxiliares.itemFactura;
import auxiliares.itemProtocolo;
import auxiliares.itemTarifa;
import auxiliares.protocolo;

import com.sun.org.apache.xalan.internal.xsltc.dom.BitArray;

import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Logger;

import Interfaces.OpUsuario;
import Interfaces.Principal;

import java.sql.*;
import java.text.SimpleDateFormat;



public class conexionBusqueda {
	
	private static Connection con;
    private static conexionBusqueda INSTANCE = null;
    private static Usuario usuar;
	private static String host;
	private static String user;
	private static String pass;
	private static String dtbs;
    

	
	public conexionBusqueda() {
	
	}
	

	
	public static void conectar() {
		System.out.println("intentando conectar BUS CONECTAR");
		 host = Colores.getHost();
		 user = Colores.getUser();
		 pass = Colores.getPass();
		 dtbs = Colores.getDtbs();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String newConnectionURL = "jdbc:mysql://" + host + ":3306/" + dtbs
					+ "?" + "user=" + user + "&password=" + pass;
					con = DriverManager.getConnection(newConnectionURL);
					System.out.println("abriendo  desde busqueda");

		}catch (Exception e) {
			closeConnection();	
		}
		
	}
	
	
	private synchronized static void createInstance() {
	        if (INSTANCE == null) { 
	            INSTANCE = new conexionBusqueda();
	        }
	    }
	 
	   
	    public static conexionBusqueda getInstance() {
	    	System.out.println("intentando conectar BUS");

	        if (INSTANCE == null) createInstance();
	        return INSTANCE;
	    }
	
	public static void closeConnection() {
		try {
			con.close();
			System.out.println("cerrrando desde busqueda");
			INSTANCE = null;
		}catch (Exception e) {
			INSTANCE = null;
		}
	}





	public static Usuario usuarioXusuario(String usu){
		conectar();
		
		Statement consulta=null;
		Statement consulta2=null;
		ResultSet tabla=null;
		ResultSet tabla2=null;
		
	
			try {
				consulta = con.createStatement();
				consulta2=con.createStatement();
				tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".usuarios WHERE  Usuario= '"+usu+"'");

				if (tabla.next()) {

					tabla2 = consulta2.executeQuery("SELECT  `R` ,  `G` ,  `B`,  `A`  from colores where idUsuario='"+ tabla.getInt(1)+"'");
					tabla2.next();
					Color col=new Color(tabla2.getInt(1), tabla2.getInt(2),tabla2.getInt(3),tabla2.getInt(4));
					System.out.println(col.toString());
					try {
						usuar=new Usuario(tabla.getInt(1), tabla.getString(2), tabla.getString(3), tabla.getString(4),col, tabla.getInt(5), tabla.getInt(6), tabla.getInt(7),tabla.getInt(8), tabla.getString(9));
						return usuar;

					} catch (Exception e) {
						
						Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 4> En la conexion con la base de datos"+e);
						closeConnection();	
						return null;

					}

				} else {
					return null;

				}
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 5> En la conexion con la base de datos"+e);
				closeConnection();				
				return null;
			}
			
		
				
	}
	
	public static Usuario usuarioXnombre(String nombre){
		conectar();
		
		Statement consulta=null;
		Statement consulta2=null;
		ResultSet tabla=null;
		ResultSet tabla2=null;
		
	
			try {
				consulta = con.createStatement();
				consulta2=con.createStatement();
				tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".usuarios WHERE  nombreCompleto= '"+nombre+"'");

				if (tabla.next()) {

					tabla2 = consulta2.executeQuery("SELECT  `R` ,  `G` ,  `B`,  `A`  from colores where idUsuario='"+ tabla.getInt(1)+"'");
					tabla2.next();
					Color col=new Color(tabla2.getInt(1), tabla2.getInt(2),tabla2.getInt(3),tabla2.getInt(4));
					System.out.println(col.toString());
					try {
						usuar=new Usuario(tabla.getInt(1), tabla.getString(2), tabla.getString(3), tabla.getString(4),col, tabla.getInt(5), tabla.getInt(6), tabla.getInt(7),tabla.getInt(8), tabla.getString(9));
						return usuar;

					} catch (Exception e) {
						
						Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 6> En la conexion con la base de datos"+e);
						closeConnection();	
						return null;

					}

				} else {
					return null;

				}
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 7> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	public static Paciente pacienteXid(String id){
		conectar();
		
		Statement consulta=null;
		Statement consulta2=null;
		Statement consulta3=null;
		ResultSet tabla=null;
		ResultSet tabla2=null;
		ResultSet tabla3=null;
		String telefono1="";
		String telefono2="";
	
			try {
				consulta = con.createStatement();
				consulta2=con.createStatement();
				consulta3=con.createStatement();
				tabla = consulta.executeQuery("select persona.cod_persona, persona.tipo_doc, persona.nombres, persona.apellidos, persona.fecha_nacimiento,	persona.genero, persona.zona_residencial, ciudad.nombre_ciudad, persona.direccion, paciente.parentesco, paciente.numero_carn from "+dtbs+".persona, "+dtbs+".paciente,"+dtbs+".ciudad where cod_persona like "+id+" and idpaciente like "+id+" and persona.ciudad=ciudad.idciudad");
				
				if (tabla.next()) {
					String email1="";
					String email2="";
					tabla2 = consulta2.executeQuery("select email.email from "+dtbs+".email_x_persona join "+dtbs+".persona on persona.cod_persona = email_x_persona.persona_cod_persona join "+dtbs+".email on email.id_email = email_x_persona.email_id_email where persona.cod_persona = "+id+"");
					
					try {
						
					
					if(tabla2.next()){
					email1=tabla2.getString(1);
					}
					if(tabla2.next()){
					email2=tabla2.getString(1);
					}
					
					
					tabla3 = consulta3.executeQuery("select telefono.telefono from "+dtbs+".telefono_x_persona join "+dtbs+".persona on persona.cod_persona = telefono_x_persona.persona_cod_persona join "+dtbs+".telefono on telefono.id_telefono = telefono_x_persona.telefono_telefono where persona.cod_persona = "+id+"");
					if(tabla3.next()){
						telefono1=tabla3.getString(1);
						}
						if(tabla3.next()){
						telefono2=tabla3.getString(1);
						}
						
						
					} catch (Exception e) {
						Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 8> En la conexion con la base de datos"+e);
						closeConnection();
					}
					try {
						Paciente paci= new Paciente(tabla.getString(1), tabla.getInt(2), tabla.getString(3), tabla.getString(4), tabla.getDate(5), "", tabla.getInt(6), tabla.getInt(7), tabla.getString(8), tabla.getString(9), tabla.getInt(10), tabla.getString(11), email1, email2, telefono1, telefono2); 
						return paci;

					} catch (Exception e) {
						
						Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 9> En la conexion con la base de datos"+e);
						closeConnection();
						return null;

					}

				} else {
					return null;

				}
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 10> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	public static Paciente pacienteXnombre(String nombre){
		conectar();
		
		Statement consulta=null;
		Statement consulta2=null;
		Statement consulta3=null;
		ResultSet tabla=null;
		ResultSet tabla2=null;
		ResultSet tabla3=null;
		String telefono1="";
		String telefono2="";
		String email1="";
		String email2="";
		
			try {

				consulta = con.createStatement();
				consulta2=con.createStatement();
				consulta3=con.createStatement();
				tabla = consulta.executeQuery("select persona.cod_persona, persona.tipo_doc, persona.nombres, persona.apellidos, persona.fecha_nacimiento,	persona.genero, persona.zona_residencial, ciudad.nombre_ciudad, persona.direccion, paciente.parentesco, paciente.numero_carn from "+dtbs+".persona, "+dtbs+".paciente,"+dtbs+".ciudad where nombres like '"+nombre+"' and idpaciente = persona.cod_persona  and persona.ciudad=ciudad.idciudad");

				if (tabla.next()) {
					tabla2 = consulta2.executeQuery("select email.email from "+dtbs+".email_x_persona join "+dtbs+".persona on persona.cod_persona = email_x_persona.persona_cod_persona join "+dtbs+".email on email.id_email = email_x_persona.email_id_email where persona.cod_persona = "+tabla.getInt(1)+"");

					try {
						
					
					if(tabla2.next()){
					email1=tabla2.getString(1);
					}
					if(tabla2.next()){
					email2=tabla2.getString(1);
					}
					
					tabla3 = consulta3.executeQuery("select telefono.telefono from "+dtbs+".telefono_x_persona join "+dtbs+".persona on persona.cod_persona = telefono_x_persona.persona_cod_persona join "+dtbs+".telefono on telefono.id_telefono = telefono_x_persona.telefono_telefono where persona.cod_persona = "+tabla.getInt(1)+"");
					if(tabla3.next()){
						telefono1=tabla3.getString(1);
						}
						if(tabla3.next()){
						telefono2=tabla3.getString(1);
						}
						
						
					} catch (Exception e) {
						Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 11> En la conexion con la base de datos"+e);
						closeConnection();
					}
					try {
						System.out.println("tel1"+telefono1);
						System.out.println("tel2"+telefono2);
						Paciente paci= new Paciente(tabla.getString(1), tabla.getInt(2), tabla.getString(3), tabla.getString(4), tabla.getDate(5), "", tabla.getInt(6), tabla.getInt(7), tabla.getString(8), tabla.getString(9), tabla.getInt(10), tabla.getString(11), email1, email2, telefono1, telefono2); 
						return paci;

					} catch (Exception e) {
						
						Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 12> En la conexion con la base de datos"+e);
						closeConnection();
						return null;

					}

				} else {
					return null;

				}
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 13> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}

	
	public static Paciente pacienteXapellido(String apellido){
		conectar();
		
		Statement consulta=null;
		Statement consulta2=null;
		Statement consulta3=null;
		ResultSet tabla=null;
		ResultSet tabla2=null;
		ResultSet tabla3=null;
		String telefono1="";
		String telefono2="";
		String email1="";
		String email2="";
		
			try {

				consulta = con.createStatement();
				consulta2=con.createStatement();
				consulta3=con.createStatement();
				tabla = consulta.executeQuery("select persona.cod_persona, persona.tipo_doc, persona.nombres, persona.apellidos, persona.fecha_nacimiento,	persona.genero, persona.zona_residencial, ciudad.nombre_ciudad, persona.direccion, paciente.parentesco, paciente.numero_carn from "+dtbs+".persona, "+dtbs+".paciente,"+dtbs+".ciudad where apellidos like '"+apellido+"' and idpaciente = persona.cod_persona  and persona.ciudad=ciudad.idciudad");

				if (tabla.next()) {
					tabla2 = consulta2.executeQuery("select email.email from "+dtbs+".email_x_persona join "+dtbs+".persona on persona.cod_persona = email_x_persona.persona_cod_persona join "+dtbs+".email on email.id_email = email_x_persona.email_id_email where persona.cod_persona = "+tabla.getInt(1)+"");

					try {
						
					
					if(tabla2.next()){
					email1=tabla2.getString(1);
					}
					if(tabla2.next()){
					email2=tabla2.getString(1);
					}
					
					tabla3 = consulta3.executeQuery("select telefono.telefono from "+dtbs+".telefono_x_persona join "+dtbs+".persona on persona.cod_persona = telefono_x_persona.persona_cod_persona join "+dtbs+".telefono on telefono.id_telefono = telefono_x_persona.telefono_telefono where persona.cod_persona = "+tabla.getInt(1)+"");
					if(tabla3.next()){
						telefono1=tabla3.getString(1);
						}
						if(tabla3.next()){
						telefono2=tabla3.getString(1);
						}
						
						
					} catch (Exception e) {
						Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 14> En la conexion con la base de datos"+e);
						closeConnection();
					}
					try {
						System.out.println("tel1"+telefono1);
						System.out.println("tel2"+telefono2);
						Paciente paci= new Paciente(tabla.getString(1), tabla.getInt(2), tabla.getString(3), tabla.getString(4), tabla.getDate(5), "", tabla.getInt(6), tabla.getInt(7), tabla.getString(8), tabla.getString(9), tabla.getInt(10), tabla.getString(11), email1, email2, telefono1, telefono2); 
						return paci;

					} catch (Exception e) {
						
						Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 15> En la conexion con la base de datos"+e);
						closeConnection();
						return null;

					}

				} else {
					return null;

				}
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 16> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	
	public static Bacteriologo bacteriologoXid(String id){
		conectar();
		
		Statement consulta=null;
		Statement consulta2=null;
		Statement consulta3=null;
		ResultSet tabla=null;
		ResultSet tabla2=null;
		ResultSet tabla3=null;
		String telefono1="";
		String telefono2="";
	
			try {
				consulta = con.createStatement();
				consulta2=con.createStatement();
				consulta3=con.createStatement();
				tabla = consulta.executeQuery("select persona.cod_persona, persona.tipo_doc, persona.nombres, persona.apellidos, persona.fecha_nacimiento,	persona.genero, persona.zona_residencial, ciudad.nombre_ciudad, persona.direccion, bacteriologo.registro, bacteriologo.titulo, bacteriologo.otros_estudios, bacteriologo.inf_adicional, bacteriologo.imagen, bacteriologo.claveFirma from "+dtbs+".persona, "+dtbs+".bacteriologo,"+dtbs+".ciudad where cod_persona like "+id+" and cod_Bacteriologo like "+id+" and persona.ciudad=ciudad.idciudad");
				
				if (tabla.next()) {
					String email1="";
					String email2="";
					tabla2 = consulta2.executeQuery("select email.email from "+dtbs+".email_x_persona join "+dtbs+".persona on persona.cod_persona = email_x_persona.persona_cod_persona join "+dtbs+".email on email.id_email = email_x_persona.email_id_email where persona.cod_persona = "+id+"");
					
					try {
						
					
					if(tabla2.next()){
					email1=tabla2.getString(1);
					}
					if(tabla2.next()){
					email2=tabla2.getString(1);
					}
					
					
					tabla3 = consulta3.executeQuery("select telefono.telefono from "+dtbs+".telefono_x_persona join "+dtbs+".persona on persona.cod_persona = telefono_x_persona.persona_cod_persona join "+dtbs+".telefono on telefono.id_telefono = telefono_x_persona.telefono_telefono where persona.cod_persona = "+id+"");
					if(tabla3.next()){
						telefono1=tabla3.getString(1);
						}
						if(tabla3.next()){
						telefono2=tabla3.getString(1);
						}
						
						
					} catch (Exception e) {
						Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 17> En la conexion con la base de datos"+e);
						closeConnection();
					}
					try {
						
						Blob blob = tabla.getBlob("imagen");
						byte[] data = blob.getBytes(1,(int)blob.length());
						BufferedImage img = null;
						try {
						img = ImageIO.read(new 	ByteArrayInputStream(data));
						} catch (IOException ex) {
							Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 18> en la base de datos : "+ex.toString());
							closeConnection();
						}
						Bacteriologo bacte= new Bacteriologo(tabla.getString(1), tabla.getInt(2), tabla.getString(3), tabla.getString(4), tabla.getDate(5), "", tabla.getInt(6), tabla.getInt(7), tabla.getString(8), tabla.getString(9), email1, email2, telefono1, telefono2, tabla.getString(10), tabla.getString(11), tabla.getString(12),tabla.getString(13),img,null,tabla.getInt(15)); 
						return bacte;

					} catch (Exception e) {
						
						Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 19> En la conexion con la base de datos"+e);
						closeConnection();
						return null;

					}

				} else {
					return null;

				}
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 20> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	public static Medico medicoXid(String id){
		conectar();
		
		Statement consulta=null;
		Statement consulta2=null;
		Statement consulta3=null;
		ResultSet tabla=null;
		ResultSet tabla2=null;
		ResultSet tabla3=null;
		String telefono1="";
		String telefono2="";
	
			try {
				consulta = con.createStatement();
				consulta2=con.createStatement();
				consulta3=con.createStatement();
				tabla = consulta.executeQuery("select persona.cod_persona, persona.tipo_doc, persona.nombres, persona.apellidos, persona.fecha_nacimiento,	persona.genero, persona.zona_residencial, ciudad.nombre_ciudad, persona.direccion, medico.idmedico, medico.especialidad, medico.activo from "+dtbs+".persona, "+dtbs+".medico, "+dtbs+".ciudad where cod_persona like "+id+" and id_persona like "+id+" and persona.ciudad=ciudad.idciudad");
				
				if (tabla.next()) {
					String email1="";
					String email2="";
					tabla2 = consulta2.executeQuery("select email.email from "+dtbs+".email_x_persona join "+dtbs+".persona on persona.cod_persona = email_x_persona.persona_cod_persona join "+dtbs+".email on email.id_email = email_x_persona.email_id_email where persona.cod_persona = "+id+"");
					
					try {
						
					
					if(tabla2.next()){
					email1=tabla2.getString(1);
					}
					if(tabla2.next()){
					email2=tabla2.getString(1);
					}
					
					
					tabla3 = consulta3.executeQuery("select telefono.telefono from "+dtbs+".telefono_x_persona join "+dtbs+".persona on persona.cod_persona = telefono_x_persona.persona_cod_persona join "+dtbs+".telefono on telefono.id_telefono = telefono_x_persona.telefono_telefono where persona.cod_persona = "+id+"");
					if(tabla3.next()){
						telefono1=tabla3.getString(1);
						}
						if(tabla3.next()){
						telefono2=tabla3.getString(1);
						}
						
						
					} catch (Exception e) {
						Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 21> En la conexion con la base de datos"+e);
						closeConnection();
					}
					try {
						Medico medic= new Medico(tabla.getString(1), tabla.getInt(2), tabla.getString(3), tabla.getString(4), tabla.getDate(5), "", tabla.getInt(6), tabla.getInt(7), tabla.getString(8), tabla.getString(9), email1, email2, telefono1, telefono2, tabla.getString(10), tabla.getInt(11),tabla.getInt(12)); 
						return medic;

					} catch (Exception e) {
						
						Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 22> En la conexion con la base de datos"+e);
						closeConnection();
						return null;

					}

				} else {
					return null;

				}
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 23> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	public static Empresa empresaXid(String id){
		conectar();
		
		Statement consulta=null;
		Statement consulta2=null;
		Statement consulta3=null;
		ResultSet tabla=null;
		ResultSet tabla2=null;
		ResultSet tabla3=null;
		String telefono1="";
		String telefono2="";
	
			try {
				consulta = con.createStatement();
				consulta2=con.createStatement();
				consulta3=con.createStatement();
				tabla = consulta.executeQuery("select empresa.id_empresa, empresa.doc_empresa, empresa.razon_social, empresa.direccion, ciudad.nombre_ciudad, empresa.dependencia_cobro, tarifas.descripcion, empresa.descuento, empresa.cod_eps, tipo_usuarios.idtipo_usuarios, empresa.adicional, empresa.requisitos_recepcion, empresa.activo from "+dtbs+".empresa, "+dtbs+".tarifas, "+dtbs+".ciudad, "+dtbs+".tipo_usuarios where id_empresa like "+id+" and empresa.ciudad=ciudad.idciudad and empresa.tarifa=tarifas.idtarifas and empresa.tipo_usuario=tipo_usuarios.idtipo_usuarios");
				
				if (tabla.next()) {
					String email1="";
					String email2="";
					tabla2 = consulta2.executeQuery("select email.email from "+dtbs+".email_x_empresa join "+dtbs+".empresa on empresa.id_empresa = email_x_empresa.empresa_id_empresa join "+dtbs+".email on email.id_email = email_x_empresa.email_id_email where empresa.id_empresa = "+id+"");
					
					try {
						
					
					if(tabla2.next()){
					email1=tabla2.getString(1);
					}
					if(tabla2.next()){
					email2=tabla2.getString(1);
					}
					
					
					tabla3 = consulta3.executeQuery("select telefono.telefono from "+dtbs+".telefono_x_empresa join "+dtbs+".empresa on empresa.id_empresa = telefono_x_empresa.empresa_id_empresa join "+dtbs+".telefono on telefono.id_telefono = telefono_x_empresa.telefono_telefono where empresa.id_empresa = "+id+"");
					if(tabla3.next()){
						telefono1=tabla3.getString(1);
						}
						if(tabla3.next()){
						telefono2=tabla3.getString(1);
						}
						
						
					} catch (Exception e) {
						Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 24> En la conexion con la base de datos"+e);
						closeConnection();
					}
					try {
						Empresa mempresa= new Empresa(tabla.getString(2), tabla.getString(3), tabla.getString(4), tabla.getString(5), tabla.getString(6), tabla.getString(7), tabla.getInt(8), tabla.getString(9),tabla.getInt(10),tabla.getString(11),tabla.getString(12),tabla.getInt(13), email1, email2, telefono1, telefono2);
						mempresa.setIdEmpresa(tabla.getInt(1));
						return mempresa;

					} catch (Exception e) {
						
						Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 25> En la conexion con la base de datos"+e);
						closeConnection();
						return null;

					}

				} else {
					return null;

				}
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 26> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	
	public static GruposEmpresas grupoEmpresaXnombre(String nombre){
		conectar();
		Statement consulta=null;
		Statement consulta2=null;
		Statement consulta3=null;
		ResultSet tabla=null;
		ResultSet tabla2=null;
		ResultSet tabla3=null;
		String telefono1="";
		String telefono2="";
			try {
				
				consulta = con.createStatement();
				tabla = consulta.executeQuery("select sub_grupo_empresa.idsub_grupo_empresa, tarifas.descripcion,empresa.razon_social from "+dtbs+".empresa, "+dtbs+".tarifas, "+dtbs+".sub_grupo_empresa where nombre_empresa like '"+nombre+"' and empresa.id_empresa=sub_grupo_empresa.codEmpresa and sub_grupo_empresa.codTarifa=tarifas.idtarifas ");
				tabla.next();
				GruposEmpresas Grupo=new GruposEmpresas(tabla.getInt(1), nombre, tabla.getString(3), tabla.getString(2));
				
			return Grupo;
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 27> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	
	public static GruposEmpresas grupoEmpresaXID(String id){
		conectar();
		
		Statement consulta=null;
		Statement consulta2=null;
		Statement consulta3=null;
		ResultSet tabla=null;
		ResultSet tabla2=null;
		ResultSet tabla3=null;
		String telefono1="";
		String telefono2="";
			try {
				
				consulta = con.createStatement();
				tabla = consulta.executeQuery("select sub_grupo_empresa.idsub_grupo_empresa, tarifas.descripcion,empresa.razon_social,sub_grupo_empresa.nombre_empresa from "+dtbs+".empresa, "+dtbs+".tarifas, "+dtbs+".sub_grupo_empresa where idsub_grupo_empresa like '"+id+"' and empresa.id_empresa=sub_grupo_empresa.codEmpresa and sub_grupo_empresa.codTarifa=tarifas.idtarifas ");
				tabla.next();
				GruposEmpresas Grupo=new GruposEmpresas(tabla.getInt(1), tabla.getString(4), tabla.getString(3), tabla.getString(2));
				
			return Grupo;
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 27.1> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	public static TipoDato TDXnombre(String nombre){
		conectar();
		
		Statement consulta=null;
		Statement consulta2=null;
		Statement consulta3=null;
		ResultSet tabla=null;
		ResultSet tabla2=null;
		ResultSet tabla3=null;
		String telefono1="";
		String telefono2="";
			try {
				
				consulta = con.createStatement();
				tabla = consulta.executeQuery("select * from "+dtbs+".tipo_de_datos where Nombre like '"+nombre+"'");
				tabla.next();
				TipoDato TD=new TipoDato(tabla.getInt(1), nombre, tabla.getString(3));
				
			return TD;
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 28> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	public static Secciones seccionXnombre(String nombre){
		conectar();
		
		Statement consulta=null;
		Statement consulta2=null;
		Statement consulta3=null;
		ResultSet tabla=null;
		ResultSet tabla2=null;
		ResultSet tabla3=null;
		String telefono1="";
		String telefono2="";
			try {
				
				consulta = con.createStatement();
				tabla = consulta.executeQuery("select * from "+dtbs+".secciones where nombre like '"+nombre+"'");
				tabla.next();
				Secciones seccion=new Secciones(tabla.getInt(1),tabla.getString(2), nombre);
				
			return seccion;
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 29>  En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	public static Secciones seccionXid(int  id){
		conectar();
		
		Statement consulta=null;
		Statement consulta2=null;
		Statement consulta3=null;
		ResultSet tabla=null;
		ResultSet tabla2=null;
		ResultSet tabla3=null;
		String telefono1="";
		String telefono2="";
			try {
				
				consulta = con.createStatement();
				tabla = consulta.executeQuery("select * from "+dtbs+".secciones where idsecciones like '"+id+"'");
				tabla.next();
				Secciones seccion=new Secciones(id,tabla.getString(2),tabla.getString(3));
				
			return seccion;
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 30> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	
	public static protocolo protocoloXnombre(String nombre){
		conectar();
		
		Statement consulta=null;
		ResultSet tabla=null;
	
			try {
				
				consulta = con.createStatement();
				tabla = consulta.executeQuery("select * from "+dtbs+".protocolo where nombre like '"+nombre+"'");
				tabla.next();
				protocolo  protocolo=new protocolo(tabla.getInt(1),tabla.getString(2),tabla.getString(3),tabla.getInt(4),tabla.getInt(5),tabla.getInt(6));
				
			return protocolo;
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 31> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	public static protocolo protocoloXid(String id){
		conectar();
		
		Statement consulta=null;
		ResultSet tabla=null;
	
			try {
				
				consulta = con.createStatement();
				tabla = consulta.executeQuery("select * from "+dtbs+".protocolo where idProtocolo like '"+id+"'");
				tabla.next();
				protocolo  protocolo=new protocolo(tabla.getInt(1),tabla.getString(2),tabla.getString(3),tabla.getInt(4),tabla.getInt(5),tabla.getInt(6));
				
			return protocolo;
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 32> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	
	public static TipoMuestra  muestraXsigla(String sigla){
		conectar();
		
		Statement consulta=null;
		ResultSet tabla=null;
	
			try {
				
				consulta = con.createStatement();
				tabla = consulta.executeQuery("select * from "+dtbs+".tipo_muestras where sigla  like '"+sigla+"'");
				tabla.next();
				TipoMuestra muestra=new TipoMuestra(tabla.getInt(1), tabla.getString(2), tabla.getString(3));
				
			return muestra;
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 33> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	public static TipoMuestra  muestraXid(String id){
		conectar();
		
		Statement consulta=null;
		ResultSet tabla=null;
	
			try {
				
				consulta = con.createStatement();
				tabla = consulta.executeQuery("select * from "+dtbs+".tipo_muestras where idTipo_muestras  like '"+id+"'");
				tabla.next();
				TipoMuestra muestra=new TipoMuestra(tabla.getInt(1), tabla.getString(2), tabla.getString(3));
				
			return muestra;
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 34> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	
	public static int[]  diasXid(String id){
		conectar();
		
		Statement consulta=null;
		ResultSet tabla=null;
		Statement consulta1=null;
		ResultSet tabla1=null;
	
			try {
				
				consulta = con.createStatement();
				tabla = consulta.executeQuery("select * from "+dtbs+".tabla_dias where idTabla_dias  like '"+id+"'");
				tabla.next();
				int[] salida=new int[]{tabla.getInt(2),tabla.getInt(3),tabla.getInt(4),tabla.getInt(5),tabla.getInt(6),tabla.getInt(7),tabla.getInt(8)};
				
				
			return salida;
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 35> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	public static Examen  examenXcodigo(String id){
		conectar();
		
		Statement consulta=null;
		ResultSet tabla=null;
		Statement consulta1=null;
		ResultSet tabla1=null;
	
			try {
				
				consulta = con.createStatement();
				tabla = consulta.executeQuery("select * from "+dtbs+".examen where codigo  like '"+id+"'");
				tabla.next();
				
				consulta1 = con.createStatement();
				tabla1 = consulta1.executeQuery("select * from "+dtbs+".tabla_tarifas where cod_examen  like '"+tabla.getInt(1)+"' order by  Cod_tarifa asc");
				
				ArrayList<itemTarifa> array=new ArrayList<itemTarifa>();
				
				while(tabla1.next()){
					int auxiurgen=-1;
					int auxifes=-1;
					int auxiesp=-1;
					
					if (tabla1.getInt(5)!=0)auxiurgen=tabla1.getInt(5);
					if (tabla1.getInt(6)!=0)auxifes=tabla1.getInt(6);
					if (tabla1.getInt(7)!=0)auxiesp=tabla1.getInt(7);
							itemTarifa IT=new itemTarifa(tabla1.getInt(1),tabla1.getInt(2), tabla1.getInt(3), tabla1.getInt(4), auxiurgen,auxifes, auxiesp);
							array.add(IT);
				}
				
				
				
				Examen examen=new Examen(tabla.getInt(1), tabla.getString(2), tabla.getInt(3), tabla.getInt(4), tabla.getInt(5), tabla.getInt(6), tabla.getInt(7),tabla.getInt(8), tabla.getInt(9),  tabla.getString(10),  tabla.getString(11), tabla.getInt(12), tabla.getString(14), array);
				
			return examen;
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 36> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	public static Examen  examenXid(String id){
		conectar();
		
		Statement consulta=null;
		ResultSet tabla=null;
		Statement consulta1=null;
		ResultSet tabla1=null;
	
			try {
				
				consulta = con.createStatement();
				tabla = consulta.executeQuery("select * from "+dtbs+".examen where idExamen  like '"+id+"'");
				tabla.next();
				
				consulta1 = con.createStatement();
				tabla1 = consulta1.executeQuery("select * from "+dtbs+".tabla_tarifas where cod_examen  like '"+tabla.getInt(1)+"' order by  Cod_tarifa asc");
				
				ArrayList<itemTarifa> array=new ArrayList<itemTarifa>();
				
				while(tabla1.next()){
					int auxiurgen=-1;
					int auxifes=-1;
					int auxiesp=-1;
					
					if (tabla1.getInt(5)!=0)auxiurgen=tabla1.getInt(5);
					if (tabla1.getInt(6)!=0)auxifes=tabla1.getInt(6);
					if (tabla1.getInt(7)!=0)auxiesp=tabla1.getInt(7);
							itemTarifa IT=new itemTarifa(tabla1.getInt(1),tabla1.getInt(2), tabla1.getInt(3), tabla1.getInt(4), auxiurgen,auxifes, auxiesp);
							array.add(IT);
				}
				
				
				
				Examen examen=new Examen(tabla.getInt(1), tabla.getString(2), tabla.getInt(3), tabla.getInt(4), tabla.getInt(5), tabla.getInt(6), tabla.getInt(7),tabla.getInt(8), tabla.getInt(9),  tabla.getString(10),  tabla.getString(11), tabla.getInt(12), tabla.getString(14), array);
				
			return examen;
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 36> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	
	public static Tarifa TarifaXnombre(String nombre){
		conectar();
		
		Statement consulta=null;
		Statement consulta2=null;
		Statement consulta3=null;
		ResultSet tabla=null;
		ResultSet tabla2=null;
		ResultSet tabla3=null;
		String telefono1="";
		String telefono2="";
			try {
				
				consulta = con.createStatement();
				tabla = consulta.executeQuery("select * from "+dtbs+".tarifas where descripcion like '"+nombre+"'");
				tabla.next();
				Tarifa Tar=new Tarifa(tabla.getInt(1), tabla.getString(2), tabla.getInt(3), tabla.getInt(4));
				
			return Tar;
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 37> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	public static Tarifa TarifaXid(String nombre){
		conectar();
		
		Statement consulta=null;
		Statement consulta2=null;
		Statement consulta3=null;
		ResultSet tabla=null;
		ResultSet tabla2=null;
		ResultSet tabla3=null;
		String telefono1="";
		String telefono2="";
			try {
				
				consulta = con.createStatement();
				tabla = consulta.executeQuery("select * from "+dtbs+".tarifas where idtarifas  like '"+nombre+"'");
				tabla.next();
				Tarifa Tar=new Tarifa(tabla.getInt(1), tabla.getString(2), tabla.getInt(3), tabla.getInt(4));
				
			return Tar;
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 38> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	public int[] busquedaValoresXExamen(int idExamen, int idTarifa){
		conectar();
		
		int[] salida=new int[]{0,0,0,0};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select  tabla_tarifas.Valor , tabla_tarifas.Recarg_urgencias , tabla_tarifas.Recarg_festivo ,tabla_tarifas. Recarg_especial  from "+dtbs+".tabla_tarifas where cod_examen ="+idExamen+" and Cod_tarifa ="+idTarifa);
			
			
			
			while (tabla.next()) {
				
				salida[0]=tabla.getInt(1);
				salida[1]=tabla.getInt(2);
				salida[2]=tabla.getInt(3);
				salida[3]=tabla.getInt(4);
				
			}
						return salida;
		} catch (SQLException e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 39> En la conexion con la base de datos"+e);
			closeConnection();
			return salida;
		}
		
		
	}
	
	public static PerfilExamenes PerfilXcodigo(String codigo){
		conectar();
		
		Statement consulta=null;
		Statement consulta2=null;
		Statement consulta3=null;
		ResultSet tabla=null;
		ResultSet tabla2=null;
		ResultSet tabla3=null;
		String telefono1="";
		String telefono2="";
			try {
				
				consulta = con.createStatement();
				tabla = consulta.executeQuery("select * from "+dtbs+".perfiles where idPerfiles  like '"+codigo+"'");
				tabla.next();
				
				
				consulta2 = con.createStatement();
				tabla2 = consulta2.executeQuery("select * from "+dtbs+".examen as e, "+dtbs+".item_x_perfiles as p where p.Perfiles_idPerfiles  like '"+codigo+"' and p.Examen_idExamen=e.idExamen");
				
				ArrayList<Examen> listaExamenes=new ArrayList<Examen>();
				while (tabla2.next()) {
					Examen e= new Examen(tabla2.getInt(1), tabla2.getString(2), tabla2.getInt(3), tabla2.getInt(4), tabla2.getInt(5), tabla2.getInt(6), 0, tabla2.getInt(8), tabla2.getInt(9),  tabla2.getString(10), tabla2.getString(11), tabla2.getInt(12), tabla2.getString(14));
					listaExamenes.add(e);
						
				}
				PerfilExamenes salida=new PerfilExamenes(tabla.getInt(1), tabla.getString(2), listaExamenes);
				
			return salida;
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 40>  En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	public static int ultimaRecepcion( ){
		conectar();
		
	int salida=-1;
	
			try {
				
				Statement consulta1 = con.createStatement();
				ResultSet tabla1 = consulta1.executeQuery("SELECT MAX( idRecepcion ) FROM recepcion");
				tabla1.next();
				salida=tabla1.getInt(1);
				return salida;
				
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 41> En la conexion con la base de datos"+e);
				closeConnection();
				return salida;
			}
			
		
				
	}
	
	public static int ultimaCuentaCobro( ){
		conectar();
		
	int salida=-1;
	
			try {
				
				Statement consulta1 = con.createStatement();
				ResultSet tabla1 = consulta1.executeQuery("SELECT MAX( idCuenta ) FROM cuentacobro");
				tabla1.next();
				salida=tabla1.getInt(1);
				return salida;
				
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 41.1> En la conexion con la base de datos"+e);
				closeConnection();
				return salida;
			}
			
		
				
	}
	
	public static int ultimaCotizacion(){
		conectar();
		
		int salida=-1;
		
				try {
					
					Statement consulta1 = con.createStatement();
					ResultSet tabla1 = consulta1.executeQuery("SELECT MAX( 	idcotizacion ) FROM cotizacion");
					tabla1.next();
					
					return tabla1.getInt(1);
					
				} catch (Exception e) {
					Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 42> En la conexion con la base de datos"+e);
					closeConnection();
					return salida;
				}
				
			
					
		}
	
	public static Cotizacion CotizacionCompleta( String id ){
		conectar();
		
		
				try {
					
					Statement consulta1 = con.createStatement();
					ResultSet tabla1 = consulta1.executeQuery("SELECT *  FROM "+dtbs+".cotizacion where idcotizacion='"+id+"'");
					tabla1.next();
					
					Statement consulta2 = con.createStatement();
					ResultSet tabla2 = consulta2.executeQuery("SELECT *  FROM "+dtbs+".examen_x_cotizacion where idCotizacion='"+tabla1.getInt(1)+"'");
					
					ArrayList<itemCotizacion> items = new ArrayList<itemCotizacion>();
					while(tabla2.next()){
						System.out.println("valorrrrrrrrrrrrrrrrrrrrrrrrr"+tabla2.getInt(6));
					itemCotizacion IC=new itemCotizacion(tabla2.getInt(1), tabla2.getInt(2), tabla2.getString(3), tabla2.getInt(4), tabla2.getInt(5), tabla2.getInt(6), tabla2.getInt(7), tabla2.getInt(8));
					items.add(IC);
					}
					
					Cotizacion cotizacion=new Cotizacion(tabla1.getInt(1), tabla1.getString(2), tabla1.getString(3), tabla1.getString(4), tabla1.getDate(5), tabla1.getInt(6), tabla1.getInt(7), tabla1.getInt(8), tabla1.getInt(9), items, tabla1.getString(10));
					return cotizacion;
					
				} catch (Exception e) {
					Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 42> En la conexion con la base de datos"+e);
					closeConnection();
					return null;
				}
				
			
					
		}
		
	public static RecepcionCompleta RCXid(int  id){
		conectar();
		
		Statement consulta=null;
		ResultSet tabla=null;
		RecepcionCompleta RC=null   ;
	
			try {
				
				consulta = con.createStatement();
				tabla = consulta.executeQuery("select * from "+dtbs+".recepcion where idRecepcion like '"+id+"'");
				tabla.next();
				Recepcion  recepcion=new Recepcion(tabla.getInt(1),conexionBusqueda.getInstance().pacienteXid(tabla.getString(2)),conexionBusqueda.getInstance().empresaXid(tabla.getString(5)),conexionBusqueda.getInstance().TarifaXid(tabla.getString(6)),tabla.getString(3),tabla.getInt(4),tabla.getString(7),tabla.getString(8),tabla.getDate(9),tabla.getInt(10),tabla.getInt(11),tabla.getInt(12),tabla.getInt(13),tabla.getInt(14),tabla.getInt(15),tabla.getInt(16),tabla.getString(17),tabla.getDate(18), tabla.getDate(19),tabla.getString(20),tabla.getInt(22),tabla.getString(21));
				ArrayList<itemFactura> itemsFactura=new ArrayList<itemFactura>();
				
							
				Statement consultaIF = con.createStatement();
				ResultSet tablaIF = consultaIF.executeQuery("select itemfacturayrecepcion.* from "+dtbs+".items_x_factura join "+dtbs+".facturas on facturas.idfactura = items_x_factura.idFactura join "+dtbs+".itemfacturayrecepcion on itemfacturayrecepcion.idItemFactura = items_x_factura.idItemFactura where facturas.idfactura ="+id);
							int i =0;	
				while(tablaIF.next()){
					System.out.println("por aki pasa :"+i);
					i++;
					ArrayList<ItemRecepcion> itemsExamenes=new ArrayList<ItemRecepcion>();
					
					Statement consultaIE = con.createStatement();
					ResultSet tablaIE = consultaIE.executeQuery("select item_examenrecepcion.* from "+dtbs+".ier_x_recepcion join "+dtbs+".itemfacturayrecepcion on itemfacturayrecepcion.idItemFactura = ier_x_recepcion.idItemFacturayRecepcion join "+dtbs+".item_examenrecepcion on item_examenrecepcion.idItemExamenRecepcion = ier_x_recepcion.idItemExamenRecepcion where itemfacturayrecepcion.idItemFactura ="+tablaIF.getInt(1));
					
					while(tablaIE.next()){
						ItemRecepcion IR= new ItemRecepcion(tablaIE.getInt(1),tablaIE.getString(5), tablaIE.getString(2), tablaIE.getInt(6), tablaIE.getString(7), Double.parseDouble(tablaIE.getString(8)),Double.parseDouble(tablaIE.getString(9)), tablaIE.getString(10),tablaIE.getInt(11), tablaIE.getInt(12), tablaIE.getInt(14), tablaIE.getInt(13),tablaIE.getString(3),tablaIE.getString(4),tablaIE.getString(15));
						itemsExamenes.add(IR);
					}
					itemFactura IF=new  itemFactura(tablaIF.getInt(1), tablaIF.getString(2), tablaIF.getInt(3), tablaIF.getInt(4), tablaIF.getInt(5), tablaIF.getInt(6), tablaIF.getInt(7), itemsExamenes, tablaIF.getInt(8), tablaIF.getInt(9), tablaIF.getDate(10), tablaIF.getInt(11), tablaIF.getString(12),tablaIF.getString(13), tablaIF.getDate(14));
					itemsFactura.add(IF);
				}
				
				Statement consultaFac = con.createStatement();
				ResultSet tablaFac = consultaFac.executeQuery("select * from "+dtbs+".facturas where codRecepcion like '"+id+"'");
				tablaFac.next();
				Factura fac=new Factura(tablaFac.getInt(1), tablaFac.getInt(2), tablaFac.getInt(3), tablaFac.getInt(4), tablaFac.getInt(5), tablaFac.getInt(6), tablaFac.getInt(7), tablaFac.getInt(8), tablaFac.getInt(9), tablaFac.getString(10), tablaFac.getInt(12));

				
				
				
				
				RC=new  RecepcionCompleta(recepcion, fac, itemsFactura);
				
			return RC;
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 43> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	public static cuentaCobro CCCXid(int  id){
		conectar();
		
		Statement consulta=null;
		ResultSet tabla=null;
		RecepcionCompleta RC=null   ;
	
			try {
				
				consulta = con.createStatement();
				tabla = consulta.executeQuery("select * from "+dtbs+".cuentacobro where idCuenta like '"+id+"'");
				tabla.next();
				ArrayList<itemCuenta> itemsCuenta=new ArrayList<itemCuenta>();
				
							
				Statement consultaIC = con.createStatement();
				ResultSet tablaIC = consultaIC.executeQuery("select * from "+dtbs+".item_x_cuenta  where item_x_cuenta.idCuenta ="+id);
							int i =0;	
				while(tablaIC.next()){
				
					itemsCuenta.add(new itemCuenta(tablaIC.getInt(1), tablaIC.getInt(2), tablaIC.getInt(3), tablaIC.getString(4), tablaIC.getDate(5), tablaIC.getInt(7),tablaIC.getInt(6)));
				}
				
				
				cuentaCobro  cuenta=new cuentaCobro(tabla.getInt(1), tabla.getTimestamp(2), tabla.getDate(3), tabla.getDate(4), tabla.getString(5), tabla.getString(6), tabla.getString(7), tabla.getInt(8), tabla.getString(9), tabla.getString(10), tabla.getInt(11),  tabla.getInt(12),  tabla.getInt(13), itemsCuenta, null);

				
				
			
				
			return cuenta;
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 43.1> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	public static String[]  SalaXsigla(String id){
		conectar();
		
		Statement consulta=null;
		ResultSet tabla=null;
	String[] salida=new String[3];
			try {
				
				consulta = con.createStatement();
				tabla = consulta.executeQuery("select * from "+dtbs+".sala where sigla  like '"+id+"'");
				tabla.next();
				
				salida[0]=tabla.getInt(1)+"";
				salida[1]=tabla.getString(2);
				salida[2]=tabla.getString(3);
				
			return salida;
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 44> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
			
	}
	
	public static String[]  SalaXid(String id){
		conectar();
		
		Statement consulta=null;
		ResultSet tabla=null;
	String[] salida=new String[3];
			try {
				
				consulta = con.createStatement();
				tabla = consulta.executeQuery("select * from "+dtbs+".sala where idsala  like '"+id+"'");
				tabla.next();
				
				salida[0]=tabla.getInt(1)+"";
				salida[1]=tabla.getString(2);
				salida[2]=tabla.getString(3);
				
			return salida;
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 44.1> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
			
	}
	
	
	
	public static String[]  ciudadXid(String id){
		conectar();
		
		Statement consulta=null;
		ResultSet tabla=null;
	String[] salida=new String[2];
			try {
				
				consulta = con.createStatement();
				tabla = consulta.executeQuery("select * from "+dtbs+".ciudad where idciudad  like '"+id+"'");
				tabla.next();
				
				salida[0]=tabla.getInt(1)+"";
				salida[1]=tabla.getString(2);
				
			return salida;
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 45> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	public static int  idCiudadXnombre(String nombre){
		conectar();
		
		Statement consulta=null;
		ResultSet tabla=null;
	int salida=-1;
			try {
				
				consulta = con.createStatement();
				tabla = consulta.executeQuery("select * from "+dtbs+".ciudad where nombre_ciudad  like '"+nombre+"'");
				if(tabla.next()){
				
				salida=tabla.getInt(1);
				}
			return salida;
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 46> En la conexion con la base de datos"+e);
				closeConnection();
				return -1;
			}
			
		
				
	}
	
	public static String[]  parentesoXid(String id){
		conectar();
		
		Statement consulta=null;
		ResultSet tabla=null;
	String[] salida=new String[2];
			try {
				
				consulta = con.createStatement();
				tabla = consulta.executeQuery("select * from "+dtbs+".tipo_parentesco where idtipo_parentesco  like '"+id+"'");
				tabla.next();
				
				salida[0]=tabla.getInt(1)+"";
				salida[1]=tabla.getString(2);
				
			return salida;
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 47> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	public static String[]  planPYPXid(String id){
		conectar();

		Statement consulta=null;
		ResultSet tabla=null;
	String[] salida=new String[2];
			try {
				
				consulta = con.createStatement();
				tabla = consulta.executeQuery("select * from "+dtbs+".planes_pyp where idplanes_pyp  like '"+id+"'");
				tabla.next();
				
				salida[0]=tabla.getInt(1)+"";
				salida[1]=tabla.getString(2);

			return salida;
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 48> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
							
	}
	
	public static String[]  EspecialidadXsigla(String id){
		conectar();
		
		Statement consulta=null;
		ResultSet tabla=null;
	String[] salida=new String[3];
			try {
				
				consulta = con.createStatement();
				tabla = consulta.executeQuery("select * from "+dtbs+".tipo_especialidad where sigla  like '"+id+"'");
				tabla.next();
				
				salida[0]=tabla.getInt(1)+"";
				salida[1]=tabla.getString(2);
				salida[2]=tabla.getString(3);
				
			return salida;
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 49> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	public static Laboratorio LaboratorioXnit(String id){
		conectar();
		
		Statement consulta=null;
		ResultSet tabla=null;
	Laboratorio salida;
			try {
				
				consulta = con.createStatement();
				tabla = consulta.executeQuery("select * from "+dtbs+".laboratorios where doc_laboratorio  like '"+id+"'");
				tabla.next();
				
				salida=new Laboratorio(tabla.getInt(1), tabla.getString(2), tabla.getString(3), tabla.getString(4), tabla.getInt(5), tabla.getString(6));
				
			return salida;
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 50> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	public static String[]  tarifaXnombre(String nombre){
		conectar();
		
		Statement consulta=null;
		ResultSet tabla=null;
	String[] salida=new String[3];
			try {
				
				consulta = con.createStatement();
				tabla = consulta.executeQuery("select * from "+dtbs+".tarifas where descripcion like '"+nombre+"'");
				tabla.next();
				
				salida[0]=tabla.getInt(1)+"";
				salida[1]=tabla.getString(2);
				salida[2]=tabla.getString(3);
				
			return salida;
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 51> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	public static Factura  FacturaXID(String id){
		conectar();
		
		Statement consulta=null;
		ResultSet tabla=null;

			try {
				
				consulta = con.createStatement();
				tabla = consulta.executeQuery("select * from "+dtbs+".facturas where idfactura like '"+id+"'");
				tabla.next();
				
			Factura fac=new  Factura(tabla.getInt(1), tabla.getInt(2), tabla.getInt(3), tabla.getInt(4), tabla.getInt(5), tabla.getInt(6), tabla.getInt(7), tabla.getInt(8), tabla.getInt(9), tabla.getString(10));
				
			return fac;
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 52> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	
	public static MiLaboratorio  miLab(){
		conectar();
		
		Statement consulta=null;
		ResultSet tabla=null;

			try {
				
				consulta = con.createStatement();
				tabla = consulta.executeQuery("select * from "+dtbs+".0laboratorio where id = 1");
				tabla.next();
				
				
				
					
					Blob blob = tabla.getBlob("logo");
					byte[] data = blob.getBytes(1,(int)blob.length());
					BufferedImage img = null;
					try {
					img = ImageIO.read(new 	ByteArrayInputStream(data));
					} catch (IOException ex) {
						//Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 102> en la base de datos : "+ex.toString());
						closeConnection();
					}
					
				
			MiLaboratorio lab=new  MiLaboratorio(tabla.getString(2), tabla.getInt(3), tabla.getString(4), tabla.getString(5), tabla.getString(6), tabla.getString(7), tabla.getString(8), tabla.getString(9), tabla.getString(10), img,null);
			
				
			return lab;
			} catch (Exception e) {
				//Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 101> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	
	public static encaFirma  encaFirma(){
		conectar();
		
		Statement consulta=null;
		ResultSet tabla=null;

			try {
				
				consulta = con.createStatement();
				tabla = consulta.executeQuery("select * from "+dtbs+".encabezadoweb where id = 1");
				tabla.next();
				
				
				
					
					Blob blob = tabla.getBlob("encabezado");
					byte[] data = blob.getBytes(1,(int)blob.length());
					BufferedImage img = null;
					try {
					img = ImageIO.read(new 	ByteArrayInputStream(data));
					} catch (IOException ex) {
						//Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 102> en la base de datos : "+ex.toString());
						closeConnection();
					}
					
					Blob blob2 = tabla.getBlob("firma");
					byte[] data2 = blob2.getBytes(1,(int)blob2.length());
					BufferedImage img2 = null;
					try {
					img2 = ImageIO.read(new 	ByteArrayInputStream(data2));
					} catch (IOException ex) {
						//Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 102> en la base de datos : "+ex.toString());
						closeConnection();
					}
					
				
			encaFirma encaFirma=new  encaFirma(tabla.getString(2), tabla.getString(3), tabla.getInt(4), null, img2, null, img);
			
				
			return encaFirma;
			} catch (Exception e) {
				//Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 101> En la conexion con la base de datos"+e);
				closeConnection();
				return null;
			}
			
		
				
	}
	
	
	
	// METODOS DEbusquedas para busquedas automaticas  #################################################################################################################################

	
	//####################################################################################################################################################     METODOS DE BUSQUEDAS
	
	public String[] busquedaUsuarioXusuario(String usu){
		conectar();
		
		String[] salida=new String[]{usu};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select usuario from "+dtbs+".usuarios where Usuario like '%"+usu+"%'  and usuarios.admin=0");
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(1);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 53> En la conexion con la base de datos"+e);
			closeConnection();
			return salida;
		}
		
		
	}
	
	public String[] busquedaUsuarioXnombre(String nombre){
		conectar();
		
	String[] salida=new String[]{nombre};
	try {

		Statement consulta;
		consulta = con.createStatement();

		ResultSet tabla = consulta.executeQuery("select nombreCompleto from "+dtbs+".usuarios where nombreCompleto like '%"+nombre+"%' and usuarios.admin=0");
		
		int size=0;
		while (tabla.next()) {
			
			size++;
			
		}
		
		tabla.beforeFirst();
		salida=new String[size];
		int index=1;
		while (tabla.next()) {
			String usuario= tabla.getString(1);

			salida[index-1]=usuario;
			index++;
		}
		return salida;
	} catch (SQLException e) {
		Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 54>  En la conexion con la base de datos"+e);
		closeConnection();
		return salida;
	}
	
	
}
	
	

	public String[] busquedaPacienteXid(String id){
		conectar();
		
		String[] salida=new String[]{id+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select persona.nombres, persona.cod_persona, persona.apellidos  from "+dtbs+".persona, "+dtbs+".paciente where cod_persona like '%"+id+"%' and (cod_persona = idpaciente) and paciente.activo=1");
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(2)+" • "+tabla.getString(1)+" "+tabla.getString(3);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 55> En la conexion con la base de datos"+e);
			closeConnection();
			return salida;
		}
		
		
	}
	
	public String[] busquedaPacienteXnombre(String nombre){
		conectar();
		
		String[] salida=new String[]{nombre};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select persona.nombres, persona.cod_persona, persona.apellidos  from "+dtbs+".persona, "+dtbs+".paciente where nombres like '%"+nombre+"%' and (cod_persona = idpaciente) and paciente.activo=1");
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(2)+" • "+tabla.getString(1)+" "+tabla.getString(3);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 56> En la conexion con la base de datos"+e);
			closeConnection();
			return salida;
		}
		
		
	}
	
	public String[] busquedaPacienteXapellido(String apellido){
		conectar();
		
		String[] salida=new String[]{apellido};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select persona.nombres, persona.cod_persona, persona.apellidos  from "+dtbs+".persona, "+dtbs+".paciente where apellidos like '%"+apellido+"%' and (cod_persona = idpaciente) and paciente.activo=1");
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(2)+" • "+tabla.getString(1)+" "+tabla.getString(3);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 57> En la conexion con la base de datos"+e);
			closeConnection();
			return salida;
		}
		
		
	}
	
	
	
	public String[] busquedaBacteriologoXid(String id){
		conectar();
		
		String[] salida=new String[]{id+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select persona.nombres, persona.cod_persona, persona.apellidos  from "+dtbs+".persona, "+dtbs+".bacteriologo where cod_persona like '%"+id+"%' and (cod_persona=cod_Bacteriologo) and bacteriologo.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(2)+" • "+tabla.getString(1)+" "+tabla.getString(3);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 58> En la conexion con la base de datos"+e);
			closeConnection();
			return salida;
		}
		
		
	}
	
	public String[] busquedaBacteriologoXnombre(String nombre){
		conectar();
		
		String[] salida=new String[]{nombre+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select persona.nombres, persona.cod_persona, persona.apellidos  from "+dtbs+".persona, "+dtbs+".bacteriologo where nombres like '%"+nombre+"%' and (cod_persona=cod_Bacteriologo) and bacteriologo.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(2)+" • "+tabla.getString(1)+" "+tabla.getString(3);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 59> En la conexion con la base de datos"+e);
			closeConnection();
			return salida;
		}
		
		
	}
	
	public String[] busquedaBacteriologoXapellido(String apellido){
		conectar();
		
		String[] salida=new String[]{apellido+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select persona.nombres, persona.cod_persona, persona.apellidos  from "+dtbs+".persona, "+dtbs+".bacteriologo where apellidos like '%"+apellido+"%' and (cod_persona=cod_Bacteriologo) and bacteriologo.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(2)+" • "+tabla.getString(1)+" "+tabla.getString(3);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 60> En la conexion con la base de datos"+e);
			closeConnection();
			return salida;
		}
		
		
	}
	
	
	
	public String[] busquedaMedicoXid(String id){
		conectar();
		
		String[] salida=new String[]{id+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select persona.cod_persona, medico.idmedico  from "+dtbs+".persona, "+dtbs+".medico where idmedico like '%"+id+"%' and (cod_persona=id_persona) and medico.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(1)+" • "+tabla.getString(2);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 61> En la conexion con la base de datos"+e);
			closeConnection();
			return salida;
		}
		
		
	}
	
	public String[] busquedaMedicoXnombre(String id){
		conectar();
		
		String[] salida=new String[]{id+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select persona.nombres, persona.cod_persona, persona.apellidos  from "+dtbs+".persona, "+dtbs+".medico where nombres like '%"+id+"%' and (cod_persona=id_persona) and medico.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(2)+" • "+tabla.getString(1)+" "+tabla.getString(3);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 62> En la conexion con la base de datos"+e);
			closeConnection();
			return salida;
		}
		
		
	}
	
	public String[] busquedaMedicoXapellido(String id){
		conectar();
		
		String[] salida=new String[]{id+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select persona.nombres, persona.cod_persona, persona.apellidos  from "+dtbs+".persona, "+dtbs+".medico where apellidos like '%"+id+"%' and (cod_persona=id_persona) and medico.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(2)+" • "+tabla.getString(1)+" "+tabla.getString(3);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 63> En la conexion con la base de datos"+e);
			closeConnection();
			return salida;
		}
		
		
	}
	
	
	
	public String[] busquedaEmpresaXnit(String id){
		conectar();
		
		String[] salida=new String[]{id+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select empresa.id_empresa, empresa.doc_empresa, empresa.razon_social  from "+dtbs+".empresa where doc_empresa like '%"+id+"%' and empresa.activo=1  ");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(1)+" • "+tabla.getString(2)+" • "+tabla.getString(3);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 64> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	public String[] busquedaEmpresaXcod(String id){
		conectar();
		
		String[] salida=new String[]{id+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select empresa.id_empresa, empresa.doc_empresa, empresa.razon_social  from "+dtbs+".empresa where id_empresa like '%"+id+"%' and empresa.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(1)+" • "+tabla.getString(2)+" • "+tabla.getString(3);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 65> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	public String[] busquedaEmpresaXrazon(String id){
		conectar();
		
		String[] salida=new String[]{id+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select empresa.id_empresa, empresa.doc_empresa, empresa.razon_social  from "+dtbs+".empresa where razon_social like '%"+id+"%' and empresa.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(1)+" • "+tabla.getString(2)+" • "+tabla.getString(3);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 66> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	
	
	public String[] busquedaGrupoEmpresaXnombre(String id){
		conectar();
		
		String[] salida=new String[]{id+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select sub_grupo_empresa.nombre_empresa  from "+dtbs+".sub_grupo_empresa where nombre_empresa like '%"+id+"%' and sub_grupo_empresa.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(1);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 67> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	
	
	public String[] busquedaTDXnombre(String id){
		conectar();
		
		String[] salida=new String[]{id+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select tipo_de_datos.Nombre, tipo_de_datos.Sigla  from "+dtbs+".tipo_de_datos where Nombre like '%"+id+"%' and tipo_de_datos.activo=1 and tipo_de_datos.protegido=0 and idTipo_de_datos != 3 and idTipo_de_datos != 1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(1)+" • "+tabla.getString(2);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 68> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	public String[] busquedaTDXSigla(String id){
		conectar();
		
		
		String[] salida=new String[]{id+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select tipo_de_datos.Nombre, tipo_de_datos.Sigla  from "+dtbs+".tipo_de_datos where Sigla like '%"+id+"%' and tipo_de_datos.activo=1 and tipo_de_datos.protegido=0  and idTipo_de_datos != 3 and idTipo_de_datos != 1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(1)+" • "+tabla.getString(2);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 69> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}

	
	
	public String[] busquedaSeccionXnombre(String id){
		conectar();
		
		String[] salida=new String[]{id+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select secciones.nombre, secciones.sigla  from "+dtbs+".secciones where nombre like '%"+id+"%' and secciones.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(1)+" • "+tabla.getString(2);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 70> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	public String[] busquedaSeccionXid(String id){
		conectar();
		
		String[] salida=new String[]{id+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select secciones.nombre, secciones.sigla  from "+dtbs+".secciones where sigla like '%"+id+"%' and secciones.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(1)+" • "+tabla.getString(2);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 71> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	
	
	public String[] busquedaProtocoloXcod(String cod){
		conectar();
		
		String[] salida=new String[]{cod+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select protocolo.codigo, protocolo.nombre  from "+dtbs+".protocolo where codigo like '%"+cod+"%' and protocolo.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(1)+" • "+tabla.getString(2);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 72> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	public String[] busquedaProtocoloXNombre(String nombre){
		conectar();
		
		String[] salida=new String[]{nombre+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select protocolo.codigo, protocolo.nombre  from "+dtbs+".protocolo where nombre like '%"+nombre+"%' and protocolo.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(2)+" • "+tabla.getString(1);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 73> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	
	
	public String[] busquedaExamenXnombre(String nombre){
		conectar();
		
		String[] salida=new String[]{nombre+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select examen.codigo, examen.Nombre  from "+dtbs+".examen where Nombre like '%"+nombre+"%' and examen.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(2)+" • "+tabla.getString(1);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 74> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	public String[] busquedaExamenXid(String id){
		conectar();

		String[] salida=new String[]{id+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select examen.codigo, examen.Nombre  from "+dtbs+".examen where codigo like '%"+id+"%' and examen.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(1)+" • "+tabla.getString(2);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 75> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	public String[] busquedaExamenXcups(String cups){
		conectar();
		
		String[] salida=new String[]{cups+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select examen.codigo, examen.Cups,  examen.Nombre  from "+dtbs+".examen where Cups like '%"+cups+"%' and examen.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(2)+" • "+tabla.getString(3)+" • "+tabla.getString(1);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 76> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	
	
	public String[] busquedaPerfilesXcodigo(String id){
		conectar();
		
		String[] salida=new String[]{id+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select *  from "+dtbs+".perfiles where idPerfiles like '%"+id+"%' and perfiles.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(1)+" • "+tabla.getString(2);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 77> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
		
	public String[] busquedaPerfilesXnombre(String nombre){
		conectar();
		
		String[] salida=new String[]{nombre+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select *  from "+dtbs+".perfiles where Nombre like '%"+nombre+"%' and perfiles.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(1)+" • "+tabla.getString(2);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 78> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}

	
	
	public ArrayList<itemProtocolo> codProtocoloXidExamen(String codExamen){
		conectar();
		
	ArrayList<itemProtocolo> salida=new  ArrayList<itemProtocolo>();
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select examen.cod_protocolo  from "+dtbs+".examen where codigo like '"+codExamen+"' ");
			tabla.next();
			int codProtocolo= tabla.getInt(1);
			
			Statement consulta2;
			consulta2 = con.createStatement();

			ResultSet tabla2 = consulta2.executeQuery("select *  from "+dtbs+".item_protocolo where cod_protocolo =  "+codProtocolo+" order by orden asc");
			while (tabla2.next()) {
				itemProtocolo IP=new itemProtocolo(tabla2.getInt(1), tabla2.getInt(2), tabla2.getString(3), tabla2.getInt(4), tabla2.getString(5), tabla2.getString(6), tabla2.getString(7), Double.parseDouble(tabla2.getString(8)), Double.parseDouble(tabla2.getString(9)), tabla2.getInt(10), tabla2.getInt(11), tabla2.getString(12));

				salida.add(IP);
			}
			
			return salida;
			
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 79> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	
	public String[] busquedaRecepcionXid(String id){
		conectar();
		
		String[] salida=new String[]{id+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select recepcion.idRecepcion, persona.nombres, persona.apellidos  from "+dtbs+".recepcion , "+dtbs+".persona  where recepcion.idRecepcion like '%"+id+"%' and recepcion.codPaciente=persona.cod_persona and recepcion.anulada=0");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(1)+" • "+tabla.getString(2)+" • "+tabla.getString(3);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 80> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	public String[] busquedaCuentaXid(String id){
		conectar();
		
		String[] salida=new String[]{id+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select cuentacobro.idCuenta, cuentacobro.nombreEmpresa from "+dtbs+".cuentacobro where cuentacobro.idCuenta like '%"+id+"%' ");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(1)+" • "+tabla.getString(2);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 80.1> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	public ArrayList<RecepcionCompleta> recepcionesAnterioresA(Paciente paciente,Recepcion recepcionAreportar){
		conectar();
		
		ArrayList<RecepcionCompleta> salida=new  ArrayList<RecepcionCompleta>();
			try {

				Statement consulta;
				consulta = con.createStatement();
				System.out.println("idrececion  >>>>"+ recepcionAreportar.getIdREcepcion());
				ResultSet tabla = consulta.executeQuery("select * from  `recepcion` where  `codPaciente` like  '"+paciente.getId()+"' and idRecepcion < '"+recepcionAreportar.getIdREcepcion()+"' and recepcion.anulada =0 order by  `recepcion`.`idRecepcion` desc limit 0 , 4 ");
			
				while (tabla.next()) {
					
					RecepcionCompleta RC=this.getInstance().RCXid(tabla.getInt(1));
					System.out.println("recorre>>>> "+RC.getRecepcion().getIdREcepcion());
					salida.add(RC);
				}
				
				return salida;
				
			} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 81> En la conexion con la base de datos"+e);			closeConnection();
				return salida;
			}
			
			
		}
	
	public ArrayList<RecepcionCompleta> ultimasRecepcioness(Paciente paciente){
		conectar();
		
		ArrayList<RecepcionCompleta> salida=new  ArrayList<RecepcionCompleta>();
			try {

				Statement consulta;
				consulta = con.createStatement();

				ResultSet tabla = consulta.executeQuery("select * from  `recepcion` where  `codPaciente` like  '"+paciente.getId()+"' and recepcion.anulada=0 order by  `recepcion`.`idRecepcion` desc limit 1 , 4");
			
				while (tabla.next()) {
					
					RecepcionCompleta RC=this.getInstance().RCXid(tabla.getInt(1));
					salida.add(RC);
				}
				
				return salida;
				
			} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 82> En la conexion con la base de datos"+e);			closeConnection();
				return salida;
			}
			
			
		}
		
	
	
	public String[] busquedaTipomuestraXsigla(String sigla){
		conectar();
		
		String[] salida=new String[]{sigla+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select *  from "+dtbs+".tipo_muestras where sigla  like '%"+sigla+"%' and tipo_muestras.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(3)+" • "+tabla.getString(2);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 83> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	public String[] busquedaTipomuestraXnombre(String nombre){
		conectar();
		
		String[] salida=new String[]{nombre+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select *  from "+dtbs+".tipo_muestras where Nombre  like '%"+nombre+"%' and tipo_muestras.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(3)+" • "+tabla.getString(2);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 84> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	
	
	public String[] busquedaCiudadXnombre(String nombre){
		conectar();
		
		String[] salida=new String[]{nombre+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select *  from "+dtbs+".ciudad where nombre_ciudad  like '%"+nombre+"%' and ciudad.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(1)+" • "+tabla.getString(2);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 85> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	
	public String[] busquedaSalaXsigla(String nombre){
		conectar();
		
		String[] salida=new String[]{nombre+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select *  from "+dtbs+".sala where sigla  like '%"+nombre+"%' and sala.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(2)+" • "+tabla.getString(3);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 86> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	public String[] busquedaSalaXNombre(String nombre){
		conectar();
		
		String[] salida=new String[]{nombre+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select *  from "+dtbs+".sala where descripcion  like '%"+nombre+"%' and sala.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(2)+" • "+tabla.getString(3);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 87> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	
	
	public String[] busquedaParentescoXnombre(String nombre){
		conectar();
		
		String[] salida=new String[]{nombre+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select *  from "+dtbs+".tipo_parentesco where descaripcion  like '%"+nombre+"%' and tipo_parentesco.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(1)+" • "+tabla.getString(2);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 88> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	public String[] busquedaParentescoXid(String id){
		conectar();
		
		String[] salida=new String[]{id+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select *  from "+dtbs+".tipo_parentesco where idtipo_parentesco  like '%"+id+"%' and tipo_parentesco.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(1)+" • "+tabla.getString(2);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 89> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	
	
	public String[] busquedaPlanesPYPXnombre(String nombre){
		conectar();
		
		String[] salida=new String[]{nombre+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select *  from "+dtbs+".planes_pyp where descripcion  like '%"+nombre+"%' and planes_pyp.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(1)+" • "+tabla.getString(2);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 90> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	public String[] busquedaPlanesPYPXid(String id){
		conectar();
		
		String[] salida=new String[]{id+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select *  from "+dtbs+".planes_pyp where idplanes_pyp  like '%"+id+"%' and planes_pyp.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(1)+" • "+tabla.getString(2);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 91> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	
	
	public String[] busquedaEspecialidadXsigla(String sigla){
		conectar();
		
		String[] salida=new String[]{sigla+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select *  from "+dtbs+".tipo_especialidad where sigla  like '%"+sigla+"%' and tipo_especialidad.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(2)+" • "+tabla.getString(3);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 92> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	public String[] busquedaEspecialidadXnombre(String nombre){
		conectar();
		
		String[] salida=new String[]{nombre+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select *  from "+dtbs+".tipo_especialidad where descripcion  like '%"+nombre+"%' and tipo_especialidad.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(2)+" • "+tabla.getString(3);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 93> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	
	
	public String[] busquedaLaboratorioXnit(String Nit){
		conectar();
		
		String[] salida=new String[]{Nit+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select *  from "+dtbs+".laboratorios where doc_laboratorio  like '%"+Nit+"%' and laboratorios.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(2)+" • "+tabla.getString(3);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 94> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	public String[] busquedaLaboratorioXnombre(String nombre){
		conectar();
		
		String[] salida=new String[]{nombre+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select *  from "+dtbs+".laboratorios where nombre  like '%"+nombre+"%"+dtbs+"atorios.activo=1");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(2)+" • "+tabla.getString(3);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 95> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	
	
	public String[] busquedaTarifaXnombre(String nombre){
		conectar();
		
		String[] salida=new String[]{nombre+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select *  from "+dtbs+".tarifas where descripcion  like '%"+nombre+"%' and tarifas.activo=1 and tarifas.protegida=0");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(2)+" • "+tabla.getString(3);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 96> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}

	
	
	public String[] busquedaFacturasXID(String id){
		conectar();
		
		String[] salida=new String[]{id+""};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select facturas.idfactura,facturas.codRecepcion,facturas.totalConD, facturas.abono, facturas.saldo, facturas.FechayHora,empresa.razon_social,recepcion.codPaciente from "+dtbs+".facturas, "+dtbs+".recepcion, "+dtbs+".empresa where facturas.idfactura like '%"+id+"%' and recepcion.idRecepcion=facturas.codRecepcion and facturas.anulada=0 and recepcion.codempresa=empresa.id_empresa order by facturas.idfactura desc");
			
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(1)+" • "+tabla.getString(2)+" • "+tabla.getString(3);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 97> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	public ArrayList<DatosAbono> facturasDabonoXpaciente(String idPaciente){
		conectar();
		
		ArrayList<DatosAbono> salida=new ArrayList<DatosAbono>();
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select facturas.idfactura,facturas.codRecepcion,facturas.totalConD, facturas.abono, facturas.saldo, facturas.FechayHora,empresa.razon_social from "+dtbs+".facturas, "+dtbs+".recepcion, "+dtbs+".empresa where recepcion.codPaciente = "+idPaciente+" and recepcion.idRecepcion=facturas.codRecepcion and facturas.anulada=0 and facturas.saldo !=0 and recepcion.codempresa=empresa.id_empresa order by facturas.FechayHora desc");
			
			
			while (tabla.next()) {
				DatosAbono DA=new DatosAbono(tabla.getInt(1), tabla.getInt(2), tabla.getInt(3), tabla.getInt(4), tabla.getInt(5), tabla.getTimestamp(6), tabla.getString(7));
				salida.add(DA);
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 98> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}

	public ArrayList<DatosAbono> facturasDabonoXEmpresa(String idEmpresa){
		conectar();
		
		ArrayList<DatosAbono> salida=new ArrayList<DatosAbono>();
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select facturas.idfactura,facturas.codRecepcion,facturas.totalConD, facturas.abono, facturas.saldo, facturas.FechayHora from "+dtbs+".facturas, "+dtbs+".recepcion where recepcion.codempresa = "+idEmpresa+" and recepcion.idRecepcion=facturas.codRecepcion and facturas.anulada=0 and facturas.saldo !=0   order by facturas.FechayHora desc;");
			
			
			while (tabla.next()) {
				DatosAbono DA=new DatosAbono(tabla.getInt(1), tabla.getInt(2), tabla.getInt(3), tabla.getInt(4), tabla.getInt(5), tabla.getTimestamp(6));
				salida.add(DA);
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 99> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	public ArrayList<DatosAbono> facturasDabonoXEmpresa(String idEmpresa,java.util.Date fechaH,java.util.Date fechaD){
		conectar();
SimpleDateFormat formatter;
		
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		String fechaFH = formatter.format(fechaH);
		String fechaFD = formatter.format(fechaD);
		ArrayList<DatosAbono> salida=new ArrayList<DatosAbono>();
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select facturas.idfactura,facturas.codRecepcion,facturas.totalConD, facturas.abono, facturas.saldo, facturas.FechayHora from "+dtbs+".facturas, "+dtbs+".recepcion where recepcion.codempresa = "+idEmpresa+" and recepcion.idRecepcion=facturas.codRecepcion and facturas.anulada=0 and facturas.saldo !=0 and facturas.FechayHora <= '"+fechaFH+" 23:59:59' and facturas.FechayHora >= '"+fechaFD+" 00:00:00' order by facturas.FechayHora asc;");
			
			
			while (tabla.next()) {
				DatosAbono DA=new DatosAbono(tabla.getInt(1), tabla.getInt(2), tabla.getInt(3), tabla.getInt(4), tabla.getInt(5), tabla.getTimestamp(6));
				salida.add(DA);
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 99.1> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}

	public ArrayList<DatosAbono> facturasDabonoXPaciente(String idPaciente,java.util.Date fechaH,java.util.Date fechaD){
		conectar();
SimpleDateFormat formatter;
		
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		String fechaFH = formatter.format(fechaH);
		String fechaFD = formatter.format(fechaD);
		ArrayList<DatosAbono> salida=new ArrayList<DatosAbono>();
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select facturas.idfactura,facturas.codRecepcion,facturas.totalConD, facturas.abono, facturas.saldo, facturas.FechayHora,empresa.razon_social from "+dtbs+".facturas, "+dtbs+".recepcion, "+dtbs+".empresa where recepcion.codPaciente = "+idPaciente+" and recepcion.idRecepcion=facturas.codRecepcion and facturas.anulada=0 and facturas.saldo !=0  and recepcion.codempresa=empresa.id_empresa  and facturas.FechayHora <= '"+fechaFH+" 23:59:59' and facturas.FechayHora >= '"+fechaFD+" 00:00:00' order by facturas.FechayHora asc;");
			System.err.println("pasa por aqui ------------------------------------");

			
			while (tabla.next()) {
				System.err.println("pasa por aqui ------------------------------------");

				DatosAbono DA=new DatosAbono(tabla.getInt(1), tabla.getInt(2), tabla.getInt(3), tabla.getInt(4), tabla.getInt(5), tabla.getTimestamp(6), tabla.getString(7));
				salida.add(DA);
			}
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 99.2> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}

	
	public DatosAbono facturasDabonoXfactura(String idfactura){
		conectar();
		
		
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select facturas.idfactura,facturas.codRecepcion,facturas.totalConD, facturas.abono, facturas.saldo, facturas.FechayHora,empresa.razon_social,recepcion.codPaciente from "+dtbs+".facturas, "+dtbs+".recepcion, "+dtbs+".empresa where facturas.idfactura = "+idfactura+" and recepcion.idRecepcion=facturas.codRecepcion and facturas.anulada=0 and recepcion.codempresa=empresa.id_empresa order by facturas.FechayHora desc");
			tabla.next();
			Paciente PA=pacienteXid(tabla.getString(8));			
			
			DatosAbono DA=new DatosAbono(tabla.getInt(1), tabla.getInt(2), tabla.getInt(3), tabla.getInt(4), tabla.getInt(5), tabla.getTimestamp(6), tabla.getString(7), tabla.getInt(8),(PA.getNombres()+" • "+PA.getApellidos()));
		return DA;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 100> En la conexion con la base de datos"+e);			closeConnection();
			return null;
		}
		
		
	}

//#####################
	
	
	public ArrayList<RecepcionCompleta> arrayExamenesXSeccion(java.util.Date elDia, String NombreSeccion){
		conectar();

		SimpleDateFormat formatter;
		
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		String fecha = formatter.format(elDia);
		
		Secciones seccion=seccionXnombre(NombreSeccion);
		
		ArrayList<RecepcionCompleta> salida=new ArrayList<RecepcionCompleta>();
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("SELECT `recepcion`.`idRecepcion` FROM  `"+dtbs+"`.`recepcion` WHERE  `fechaRecepcion` =  '"+fecha+"'");
			
			
		
			while (tabla.next()) {
				
			RecepcionCompleta RC=RCXid(tabla.getInt(1));
			
		for (int i = 0; i < RC.getItemsFactura().size(); i++) {
			itemFactura IF=RC.getItemsFactura().get(i);
			Examen exa=examenXcodigo(IF.getCodExamen());

			if ((conexionBusqueda.getInstance().seccionXid((conexionBusqueda.getInstance().protocoloXid(exa.getCodProtocolo()+"")).getCodSeccion()).getNombre()+"").equals(seccion.getNombre())) {
				salida.add(RC);

			}
		}
			}
			
			
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 75.1> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	public ArrayList<RecepcionCompleta> arrayExamenesXExamen(java.util.Date elDia, String CodExamen){
		conectar();

		SimpleDateFormat formatter;
		
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		String fecha = formatter.format(elDia);
		
		
		ArrayList<RecepcionCompleta> salida=new ArrayList<RecepcionCompleta>();
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("SELECT `recepcion`.`idRecepcion` FROM  `"+dtbs+"`.`recepcion` WHERE  `fechaRecepcion` =  '"+fecha+"'");
			
			
		
			while (tabla.next()) {
				
			RecepcionCompleta RC=RCXid(tabla.getInt(1));
			
		for (int i = 0; i < RC.getItemsFactura().size(); i++) {
			itemFactura IF=RC.getItemsFactura().get(i);
			Examen exa=examenXcodigo(IF.getCodExamen());

			if (exa.getSigla().equals(CodExamen)) {
				salida.add(RC);

			}
		}
			}
			
			
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 75.2> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	
	public ArrayList<RecepcionCompleta> arrayExamenesXFecha(java.util.Date desde,java.util.Date hasta){
		conectar();

		SimpleDateFormat formatter;
		
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		String fechaDesde = formatter.format(desde);
		String fechaHasta = formatter.format(hasta);
		
		
		ArrayList<RecepcionCompleta> salida=new ArrayList<RecepcionCompleta>();
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("SELECT `recepcion`.`idRecepcion` FROM  `"+dtbs+"`.`recepcion` WHERE  `fechaRecepcion` >=  '"+fechaDesde+"' and`fechaRecepcion` <=  '"+fechaHasta+"'");
			
			
		
			while (tabla.next()) {
				
			RecepcionCompleta RC=RCXid(tabla.getInt(1));
			
		
			salida.add(RC);

		}
			
			
			return salida;
		} catch (SQLException e) {			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 75.3> En la conexion con la base de datos"+e);			closeConnection();
			return salida;
		}
		
		
	}
	
	
	public static String[]  usuariowebXid(String usu){
		conectar();
		
		Statement consulta=null;
		Statement consulta2=null;
		ResultSet tabla=null;
		ResultSet tabla2=null;
		
	
			try {
				consulta = con.createStatement();
				consulta2=con.createStatement();
				tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".usuarioweb WHERE  usuario= '"+usu+"'");

				if (tabla.next()) {
					String[] salida=new String[2];
				
					try {
						salida[0]=tabla.getString(2);
						salida[1]=tabla.getString(1);
						
						return salida ;

					} catch (Exception e) {
						
						Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 76> En la conexion con la base de datos"+e);
						closeConnection();	
						return null;

					}

				} else {
					return null;

				}
			} catch (Exception e) {
				Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 76.2> En la conexion con la base de datos"+e);
				closeConnection();				
				return null;
			}
			
		
				
	}
	
	public String[] busquedaUsuarioWebXid(String usu){
		conectar();
		
		String[] salida=new String[]{usu};
		try {

			Statement consulta;
			consulta = con.createStatement();

			ResultSet tabla = consulta.executeQuery("select usuario from "+dtbs+".usuarioweb where usuario like '%"+usu+"%'  ");
			
			int size=0;
			while (tabla.next()) {
				
				size++;
				
			}
			
			tabla.beforeFirst();
			salida=new String[size];
			int index=1;
			while (tabla.next()) {
				String usuario= tabla.getString(1);

				salida[index-1]=usuario;
				index++;
			}
			return salida;
		} catch (SQLException e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONBUS 76.1> En la conexion con la base de datos"+e);
			closeConnection();
			return salida;
		}
		
		
	}
	
}


