package conexion;

import interfaces_Modificadas.Colores;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.Attribute;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.Destination;
import javax.print.attribute.standard.PrinterInfo;
import javax.print.attribute.standard.PrinterIsAcceptingJobs;
import javax.print.attribute.standard.PrinterLocation;
import javax.print.attribute.standard.PrinterMakeAndModel;
import javax.print.attribute.standard.PrinterState;
import javax.swing.JOptionPane;
import javax.swing.text.TabableView;

import Interfaces.Principal;
import auxiliares.Tarifa;
import auxiliares.Usuario;
import auxiliares.itemProtocolo;
import auxiliares.itemTarifa;

public class conexionCombos {

	private static Connection con;
	private static conexionCombos INSTANCE = null;
	private static String host;
	private static String user;
	private static String pass;
	private static String dtbs;
	
	public conexionCombos() {

	}

	public static void conectar() {
		System.out.println("intentando conectar COMBB CONETAR()");

		 host = Colores.getHost();
		user = Colores.getUser();
		pass = Colores.getPass();
		dtbs = Colores.getDtbs();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String newConnectionURL = "jdbc:mysql://" + host + ":3306/" + dtbs + "?" + "user=" + user + "&password=" + pass;
			con = DriverManager.getConnection(newConnectionURL);
			System.out.println("abriendo  desde combos");

		} catch (Exception e) {
			System.out.println("error abriendo combos ");
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 3> en la base de datos : "+e.toString());
			closeConnection();	
		}

	}

	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new conexionCombos();
		}
	}

	public static conexionCombos getInstance() {
		System.out.println("intentando conectar COMBB");

		if (INSTANCE == null)
			createInstance();
		return INSTANCE;
	}

	public static void closeConnection() {
		if (INSTANCE!=null) {

			try {
				con.close();
				System.out.println("cerrrando desde comos");
				INSTANCE = null;
			} catch (Exception e) {
				INSTANCE = null;
			}
		
		}
	}

	
	
	
	
	
	public String[] listaTipoId() {
		conectar();
		Statement consulta = null;
		ResultSet tabla = null;

		try {
			consulta = con.createStatement();
			tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".tipo_doc");

			if (tabla.next()) {
				tabla.last();
				String[] salida = new String[tabla.getRow() + 1];
				tabla.beforeFirst();
				salida[0] = "Tipo de documento";
				int indice = 1;
				while (tabla.next()) {
					salida[indice] = tabla.getString(2) + " " + tabla.getString(3);
					indice++;
				}

				closeConnection();	return salida;
			} else {
				closeConnection();	return null;

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 4> en la base de datos : "+e.toString());
			closeConnection();	
			return null;
		}

	}

	public String[] listaZonaResidencial() {
		conectar();

		Statement consulta = null;
		ResultSet tabla = null;

		try {
			consulta = con.createStatement();
			tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".zona_residencial");

			if (tabla.next()) {
				tabla.last();
				String[] salida = new String[tabla.getRow() + 1];
				tabla.beforeFirst();
				salida[0] = "Zona residencial";
				int indice = 1;
				while (tabla.next()) {
					salida[indice] = tabla.getString(2);
					indice++;
				}

				closeConnection();	return salida;
			} else {
				closeConnection();	return null;

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 5> en la base de datos : "+e.toString());
			closeConnection();	
			return null;
		}

	}

	public String[] listaParentesco() {
		conectar();

		Statement consulta = null;
		ResultSet tabla = null;

		try {
			consulta = con.createStatement();
			tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".tipo_parentesco");

			if (tabla.next()) {
				tabla.last();
				String[] salida = new String[tabla.getRow() + 1];
				tabla.beforeFirst();
				salida[0] = "Afiliado o parentesco";
				int indice = 1;
				while (tabla.next()) {
					salida[indice] = tabla.getString(2);
					indice++;
				}

				closeConnection();	return salida;
			} else {
				closeConnection();	return null;

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 6> en la base de datos : "+e.toString());
			closeConnection();	
			return null;
		}

	}

	public String[] listaGeneros() {
		conectar();

		Statement consulta = null;
		ResultSet tabla = null;

		try {
			consulta = con.createStatement();
			tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".genero");

			if (tabla.next()) {
				tabla.last();
				String[] salida = new String[tabla.getRow() + 1];
				tabla.beforeFirst();
				salida[0] = "Genero";
				int indice = 1;
				while (tabla.next()) {
					salida[indice] = tabla.getString(3);
					indice++;
				}

				closeConnection();	return salida;
			} else {
				closeConnection();	return null;

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 7> en la base de datos : "+e.toString());
			closeConnection();	
			return null;
		}

	}

	public String[] listaCiudades() {
		conectar();
		
		Statement consulta = null;
		ResultSet tabla = null;

		try {
			consulta = con.createStatement();
			tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".ciudad where ciudad.activo=1");

			if (tabla.next()) {
				tabla.last();
				String[] salida = new String[tabla.getRow() + 1];
				tabla.beforeFirst();
				salida[0] = "Ciudad de residencia";
				int indice = 1;
				while (tabla.next()) {
					salida[indice] = tabla.getString(2);
					indice++;
				}

				closeConnection();	return salida;
			} else {
				closeConnection();	return null;

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 8> en la base de datos : "+e.toString());
			closeConnection();	
			return null;
		}

	}

	public String[] listaEspecialidades() {
		conectar();
		
		Statement consulta = null;
		ResultSet tabla = null;

		try {
			consulta = con.createStatement();
			tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".tipo_especialidad");

			if (tabla.next()) {
				tabla.last();
				String[] salida = new String[tabla.getRow() + 1];
				tabla.beforeFirst();
				salida[0] = "Especialidad";
				int indice = 1;
				while (tabla.next()) {
					salida[indice] = tabla.getString(2) + " • " + tabla.getString(3);
					indice++;
				}

				closeConnection();	return salida;
			} else {
				closeConnection();	return null;

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 9> en la base de datos : "+e.toString());
			closeConnection();	
			return null;
		}

	}

	public String[] listaTipoUsuario() {
		conectar();
		
		Statement consulta = null;
		ResultSet tabla = null;

		try {
			consulta = con.createStatement();
			tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".tipo_usuarios");

			if (tabla.next()) {
				tabla.last();
				String[] salida = new String[tabla.getRow() + 1];
				tabla.beforeFirst();
				salida[0] = "Tipo de usuario";
				int indice = 1;
				while (tabla.next()) {
					salida[indice] =tabla.getString(1)+" • "+ tabla.getString(2);
					indice++;
				}

				closeConnection();	return salida;
			} else {
				closeConnection();	return new String[]{"Sin tipos de usuario"};

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 10> en la base de datos : "+e.toString());
			closeConnection();	
			return new String[]{};
		}

	}

	public String[] listaTarifas() {
		conectar();
		
		Statement consulta = null;
		ResultSet tabla = null;

		try {
			consulta = con.createStatement();
			tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".tarifas where tarifas.activo=1");

			if (tabla.next()) {
				tabla.last();
				String[] salida = new String[tabla.getRow() + 1];
				tabla.beforeFirst();
				salida[0] = "Tarifa";
				int indice = 1;
				while (tabla.next()) {
					salida[indice] = tabla.getString(2);
					indice++;
				}

				closeConnection();	return salida;
			} else {
				closeConnection();	return null;

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 11> en la base de datos : "+e.toString());
			closeConnection();	
			return null;
		}

	}

	public String[] listaEmpresas() {
		conectar();
		
		Statement consulta = null;
		ResultSet tabla = null;

		try {
			consulta = con.createStatement();
			tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".empresa where empresa.activo=1");

			if (tabla.next()) {
				tabla.last();
				String[] salida = new String[tabla.getRow() + 1];
				tabla.beforeFirst();
				salida[0] = "Empresa";
				int indice = 1;
				while (tabla.next()) {
					salida[indice] = tabla.getString(3);
					indice++;
				}

				closeConnection();	return salida;
			} else {
				closeConnection();	return null;

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 12> en la base de datos : "+e.toString());
			closeConnection();	
			return null;
		}

	}

	public String[] listaTD() {
		conectar();
		
		Statement consulta = null;
		ResultSet tabla = null;

		try {
			consulta = con.createStatement();
			tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".tipo_de_datos where tipo_de_datos.activo=1 and tipo_de_datos.protegido=0 order by Nombre asc");

			if (tabla.next()) {
				tabla.last();
				String[] salida = new String[tabla.getRow() + 1];
				tabla.beforeFirst();
				salida[0] = "Tipo de datos";
				int indice = 1;
				while (tabla.next()) {
					salida[indice] = tabla.getString(2) + " • " + tabla.getString(3);
					indice++;
				}

				closeConnection();	return salida;
			} else {
				closeConnection();	return null;

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 13> en la base de datos : "+e.toString());
			closeConnection();	
			return null;
		}

	}

	public String[] listaSecciones() {
		conectar();
		
		Statement consulta = null;
		ResultSet tabla = null;

		try {
			consulta = con.createStatement();
			tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".secciones where secciones.activo=1");

			if (tabla.next()) {
				tabla.last();
				String[] salida = new String[tabla.getRow() + 1];
				tabla.beforeFirst();
				salida[0] = "Sección";
				int indice = 1;
				while (tabla.next()) {
					salida[indice] = tabla.getString(3) + " • " + tabla.getString(2);
					indice++;
				}

				closeConnection();	return salida;
			} else {
				closeConnection();	return null;

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 14> en la base de datos : "+e.toString());
			closeConnection();	
			return null;
		}

	}

	public String[] listaPlanillas() {
		conectar();
		
		Statement consulta = null;
		ResultSet tabla = null;

		try {
			consulta = con.createStatement();
			tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".tipo_planilla");

			if (tabla.next()) {
				tabla.last();
				String[] salida = new String[tabla.getRow() + 1];
				tabla.beforeFirst();
				salida[0] = "Tipo de planilla";
				int indice = 1;
				while (tabla.next()) {
					salida[indice] = tabla.getString(2) + " • " + tabla.getString(1);
					indice++;
				}

				closeConnection();	return salida;
			} else {
				closeConnection();	return null;

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 15> en la base de datos : "+e.toString());
			closeConnection();	
			return null;
		}

	}

	public String[] listaGeneros2() {
		conectar();
		
		Statement consulta = null;
		ResultSet tabla = null;

		try {
			consulta = con.createStatement();
			tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".genero");

			if (tabla.next()) {
				tabla.last();
				String[] salida = new String[tabla.getRow() + 2];
				tabla.beforeFirst();
				salida[0] = "Genero";
				int indice = 1;
				while (tabla.next()) {
					salida[indice] = tabla.getString(2) + " • " + tabla.getString(3);
					indice++;
				}
				salida[indice] = "A • Ambos";
				closeConnection();	return salida;
			} else {
				closeConnection();	return null;

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 16> en la base de datos : "+e.toString());
			closeConnection();	
			return null;
		}

	}

	public String[] listaOPTDvector(String nombreTD) {
		conectar();
		
		Statement consulta = null;
		ResultSet tabla = null;

		Statement consulta1 = null;
		ResultSet tabla1 = null;

		try {

			consulta1 = con.createStatement();
			tabla1 = consulta1.executeQuery("SELECT * FROM "+dtbs+".tipo_de_datos where Nombre like '" + nombreTD + "' and  tipo_de_datos.activo=1");
			tabla1.next();

			consulta = con.createStatement();
			tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".opciones_tipo_de_datos where idTD =" + tabla1.getInt(1));
			tabla.last();
			String[] salida = new String[tabla.getRow() + 1];
			tabla.beforeFirst();
			salida[0] = "Valor normal";
			int indice = 1;
			if (tabla.next()) {

				while (tabla.next()) {
					salida[indice] = tabla.getString(2);
					indice++;
				}

				closeConnection();	return salida;
			} else {
				closeConnection();	return null;

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 17> en la base de datos : "+e.toString());
			closeConnection();	
			return null;
		}

	}

	public String[] listaOPTDvectorXid(String idTD) {
		conectar();
		
		Statement consulta = null;
		ResultSet tabla = null;

		Statement consulta1 = null;
		ResultSet tabla1 = null;

		try {

			consulta1 = con.createStatement();
			tabla1 = consulta1.executeQuery("SELECT * FROM "+dtbs+".tipo_de_datos where idTipo_de_datos like '" + idTD + "' and tipo_de_datos.activo=1");
			tabla1.next();

			consulta = con.createStatement();
			tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".opciones_tipo_de_datos where idTD =" + tabla1.getInt(1));
			tabla.last();
			String[] salida = new String[tabla.getRow() + 1];
			tabla.beforeFirst();
			salida[0] = "Valor normal";
			int indice = 1;
			if (tabla.next()) {

				while (tabla.next()) {
					salida[indice] = tabla.getString(2);
					indice++;
				}

				closeConnection();	return salida;
			} else {
				closeConnection();	return null;

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 18> en la base de datos : "+e.toString());
			closeConnection();	
			return null;
		}

	}

	
	public String[] listaMuestras() {
		conectar();
		
		Statement consulta = null;
		ResultSet tabla = null;

		try {
			consulta = con.createStatement();
			tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".tipo_muestras where tipo_muestras.activo=1");

			if (tabla.next()) {
				tabla.last();
				String[] salida = new String[tabla.getRow() + 1];
				tabla.beforeFirst();
				salida[0] = "Tipo de muestra";
				int indice = 1;
				while (tabla.next()) {
					salida[indice] = tabla.getString(2)+" • "+tabla.getString(3);
					indice++;
				}

				closeConnection();	return salida;
			} else {
				closeConnection();	return null;

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 19> en la base de datos : "+e.toString());
			closeConnection();	
			return null;
		}

	}

	public String[] listaSubGrupoEmpresa(int codEmpresa) {
		conectar();
		
		Statement consulta = null;
		ResultSet tabla = null;

		try {
			consulta = con.createStatement();
			tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".sub_grupo_empresa where codEmpresa ='"+codEmpresa+"' and sub_grupo_empresa.activo=1");

			if (tabla.next()) {
				tabla.last();
				String[] salida = new String[tabla.getRow() + 1];
				tabla.beforeFirst();
				salida[0] = "Sub grupo de empresa";
				int indice = 1;
				while (tabla.next()) {
					salida[indice] = tabla.getString(1)+" • "+tabla.getString(2);
					indice++;
				}

				closeConnection();	return salida;
			} else {
				closeConnection();	return new String[]{};

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 20> en la base de datos : "+e.toString());
			closeConnection();	
			return new String[]{};
		}

	}

	public String[] listaPlanesPP() {
		conectar();
		
		Statement consulta = null;
		ResultSet tabla = null;

		try {
			consulta = con.createStatement();
			tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".planes_pyp where planes_pyp.activo=1");

			if (tabla.next()) {
				tabla.last();
				String[] salida = new String[tabla.getRow() + 1];
				tabla.beforeFirst();
				salida[0] = "Planes P y P";
				int indice = 1;
				while (tabla.next()) {
					salida[indice] =tabla.getInt(1)+" • "+ tabla.getString(2);
					indice++;
				}

				closeConnection();	return salida;
			} else {
				closeConnection();	return new String[]{"Sin planes P y P"};

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 21> en la base de datos : "+e.toString());
			closeConnection();	
			return  new String[]{};
		}
}
	
	public String[] listaSalas() {
		conectar();
		
		Statement consulta = null;
		ResultSet tabla = null;

		try {
			consulta = con.createStatement();
			tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".sala where sala.activo=1");

			if (tabla.next()) {
				tabla.last();
				String[] salida = new String[tabla.getRow() + 1];
				tabla.beforeFirst();
				salida[0] = "Sala";
				int indice = 1;
				while (tabla.next()) {
					salida[indice] = tabla.getString(2)+" • "+ tabla.getString(3);
					indice++;
				}

				closeConnection();	return salida;
			} else {
				closeConnection();	return new String[]{"Sin Salas"};

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 22> en la base de datos : "+e.toString());
			closeConnection();	
			return  new String[]{};
		}

	}

	public String[] listaFinalidad() {
		conectar();
		
		Statement consulta = null;
		ResultSet tabla = null;

		try {
			consulta = con.createStatement();
			tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".finalidad_procedimiento");

			if (tabla.next()) {
				tabla.last();
				String[] salida = new String[tabla.getRow() + 1];
				tabla.beforeFirst();
				salida[0] = "Finalidad del procedimiento";
				int indice = 1;
				while (tabla.next()) {
					salida[indice] = tabla.getString(1)+" • "+ tabla.getString(2);
					indice++;
				}

				closeConnection();	return salida;
			} else {
				closeConnection();	return new String[]{"Sin Finalidad de procedimientos"};

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 23> en la base de datos : "+e.toString());
			closeConnection();	
			return  new String[]{};
		}

	}

	public String[] listaAmbito() {
		conectar();
		
		Statement consulta = null;
		ResultSet tabla = null;

		try {
			consulta = con.createStatement();
			tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".ambito_procedimiento");

			if (tabla.next()) {
				tabla.last();
				String[] salida = new String[tabla.getRow() + 1];
				tabla.beforeFirst();
				salida[0] = "Ambito del procedimiento";
				int indice = 1;
				while (tabla.next()) {
					salida[indice] = tabla.getString(1)+" • "+ tabla.getString(2);
					indice++;
				}

				closeConnection();	return salida;
			} else {
				closeConnection();	return new String[]{"Sin Finalidad de procedimientos"};

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 24> en la base de datos : "+e.toString());
			closeConnection();	
			return  new String[]{};
		}

	}

	public String[] listaTipoPago() {
		conectar();
		
		Statement consulta = null;
		ResultSet tabla = null;

		try {
			consulta = con.createStatement();
			tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".tipo_pago");

			if (tabla.next()) {
				tabla.last();
				String[] salida = new String[tabla.getRow() + 1];
				tabla.beforeFirst();
				salida[0] = "Tipo de pago";
				int indice = 1;
				while (tabla.next()) {
					salida[indice] = tabla.getString(1)+" • "+ tabla.getString(2);
					indice++;
				}

				closeConnection();	return salida;
			} else {
				closeConnection();	return new String[]{"Sin tipos de pago"};

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 25> en la base de datos : "+e.toString());
			closeConnection();	
			return  new String[]{};
		}

	}

	
	public String[] listaFormaPago() {
		conectar();
		
		Statement consulta = null;
		ResultSet tabla = null;

		try {
			consulta = con.createStatement();
			tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".formas_pago");

			if (tabla.next()) {
				tabla.last();
				String[] salida = new String[tabla.getRow() + 1];
				tabla.beforeFirst();
				salida[0] = "Forma de pago";
				int indice = 1;
				while (tabla.next()) {
					salida[indice] = tabla.getString(1)+" • "+ tabla.getString(2); 
					
					indice++;
				}

				closeConnection();	return salida;
			} else {
				closeConnection();	return new String[]{"Sin formas de pago"};

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 26> en la base de datos : "+e.toString());
			closeConnection();	
			return  new String[]{};
		}

	}
	
	public String[] listaBacteriologos() {
		conectar();
		
		Statement consulta = null;
		ResultSet tabla = null;

		try {
			consulta = con.createStatement();
			tabla = consulta.executeQuery("SELECT bacteriologo.cod_Bacteriologo,persona.nombres,persona.apellidos FROM "+dtbs+".bacteriologo,"+dtbs+".persona where persona.cod_persona=bacteriologo.cod_Bacteriologo and bacteriologo.activo=1");

			if (tabla.next()) {
				tabla.last();
				String[] salida = new String[tabla.getRow() + 1];
				tabla.beforeFirst();
				salida[0] = "Firma";
				int indice = 1;
				while (tabla.next()) {
					salida[indice] = tabla.getString(1)+" • "+ tabla.getString(2)+" "+ tabla.getString(3); 
					
					indice++;
				}

				closeConnection();	return salida;
			} else {
				closeConnection();	return new String[]{"Sin bacteriologos"};

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 26> en la base de datos : "+e.toString());
			closeConnection();	
			return  new String[]{};
		}

	}
	
	public String[] listaTamPapel() {
	
				String[] salida = new String[4];
			
				salida[0] = "Tamaños de papel";
				salida[1] = "Carta";
				salida[2] = "Media Carta";
				salida[3] = "Oficio";
				
				return salida;
		
		}
	
	// ########################################################################################################################
	// matrices para tablas

	public String[][] listaOPTD(String nombreTD) {
		conectar();
		
		Statement consulta = null;
		ResultSet tabla = null;

		Statement consulta1 = null;
		ResultSet tabla1 = null;
		System.out.println(nombreTD);
		try {
			consulta1 = con.createStatement();
			tabla1 = consulta1.executeQuery("SELECT * FROM "+dtbs+".tipo_de_datos where Nombre like '" + nombreTD + "' and tipo_de_datos.activo=1 ");
			tabla1.next();

			consulta = con.createStatement();
			tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".opciones_tipo_de_datos where idTD =" + tabla1.getInt(1));

			if (tabla.next()) {
				tabla.last();
				String[][] salida = new String[tabla.getRow()][2];
				tabla.beforeFirst();
				int indice = 0;
				while (tabla.next()) {
					salida[indice][0] = tabla.getString(1);
					salida[indice][1] = tabla.getString(2);
					indice++;
				}

				closeConnection();	return salida;
			} else {
				closeConnection();	return null;

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 27> en la base de datos : "+e.toString());
			closeConnection();	
			return null;
		}

	}

	public String[][] listaItemsProtocolo(int idProto) {
		conectar();
		

		Statement consulta = null;
		ResultSet tabla = null;
		Statement consulta1 = null;
		ResultSet tabla1 = null;
		System.out.println(idProto);
		try {
			consulta1 = con.createStatement();
			tabla1 = consulta1.executeQuery("SELECT * FROM "+dtbs+".item_protocolo where cod_protocolo like '" + idProto + "' order by `orden` asc");

			if (tabla1.next()) {

				tabla1.last();

				String[][] salida = new String[tabla1.getRow()][9];
				tabla1.beforeFirst();
				int indice = 0;
				while (tabla1.next()) {
					consulta = con.createStatement();
					tabla = consulta.executeQuery("SELECT * FROM "+dtbs+".tipo_de_datos where idTipo_de_datos =" + tabla1.getInt(4));
					tabla.next();
					if (tabla1.getInt(4) == 45) {
						String auuxi = "<html><b>" + tabla1.getString(3) + "</b></html>";
						salida[indice][0] = auuxi;
						salida[indice][1] = "-";
						salida[indice][2] = "";
						salida[indice][3] = "";
						salida[indice][4] = "";
						salida[indice][5] = "";
						salida[indice][6] = "";
						salida[indice][7] = "";
						salida[indice][8] =""+tabla1.getInt(11);
						indice++;
					} else {
						if (tabla1.getInt(4) == 2) {
							String auuxi = "<html><b>" + tabla1.getString(3) + "</b></html>";

							salida[indice][0] =auuxi;
							salida[indice][1] = "•";
							salida[indice][2] = "";
							salida[indice][3] = "";
							salida[indice][4] = "";
							salida[indice][5] = "";
							salida[indice][6] = "";
							salida[indice][7] = "";
							salida[indice][8] =""+tabla1.getInt(11);
							indice++;

						} else {

							salida[indice][0] = tabla1.getString(3);
							salida[indice][1] = tabla.getString(3);
							salida[indice][2] = tabla1.getString(5);
							salida[indice][3] = tabla1.getString(6);
							if (tabla1.getString(7).equals("-1")) {
								salida[indice][4] = "";

							} else {
								salida[indice][4] = tabla1.getString(7);

							}

							if (tabla1.getString(8).equals("-1")) {
								salida[indice][5] = "";

							} else {
								salida[indice][5] = tabla1.getString(8);

							}

							if (tabla1.getString(9).equals("-1")) {
								salida[indice][6] = "";

							} else {
								salida[indice][6] = tabla1.getString(9);

							}
							 String genero="";
							 if (tabla1.getString(10).equals("1")) {
								  genero="F";
							} else {
								if (tabla1.getString(10).equals("2")) {
									  genero="M";
								} else {
									  genero="A";
								}
							}
							salida[indice][7] = genero;
							salida[indice][8] = tabla1.getString(11);
							indice++;

						}
						
					}
					
				}

				closeConnection();	return salida;
			} else {
				closeConnection();	return null;

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 28> en la base de datos : "+e.toString());
			closeConnection();	
			return null;
		}

	}

	public ArrayList<itemProtocolo> arrayItemsProtocolo(int idProto) {
		conectar();
		

		Statement consulta1 = null;
		ResultSet tabla1 = null;
		System.out.println(idProto);
		try {
			consulta1 = con.createStatement();
			tabla1 = consulta1.executeQuery("SELECT * FROM "+dtbs+".item_protocolo where cod_protocolo like '" + idProto + "' order by `orden` asc");
			tabla1.next();

			if (tabla1.next()) {
				tabla1.last();
				ArrayList<itemProtocolo> salida = new ArrayList<itemProtocolo>();
				tabla1.beforeFirst();

				while (tabla1.next()) {

					if (tabla1.getInt(4) == 1 || tabla1.getInt(4) == 2) {
						itemProtocolo auxiitem = new itemProtocolo(tabla1.getInt(1), tabla1.getInt(2), tabla1.getString(3), tabla1.getInt(4), "", "", "", -1, -1, -1, tabla1.getInt(11),tabla1.getString(12));
						salida.add(auxiitem);
					} else {
						if (tabla1.getInt(4) == 3) {
							itemProtocolo auxiitem = new itemProtocolo(tabla1.getInt(1), tabla1.getInt(2), tabla1.getString(3), tabla1.getInt(4), tabla1.getString(5), tabla1.getString(6), "", Double.parseDouble(tabla1.getString(8)), Double.parseDouble(tabla1.getString(9)), tabla1.getInt(10), tabla1.getInt(11),tabla1.getString(12));
							salida.add(auxiitem);
						} else {
							itemProtocolo auxiitem = new itemProtocolo(tabla1.getInt(1), tabla1.getInt(2), tabla1.getString(3), tabla1.getInt(4), tabla1.getString(5), tabla1.getString(6), tabla1.getString(7), -1, -1, tabla1.getInt(10), tabla1.getInt(11),tabla1.getString(12));
							salida.add(auxiitem);
						}

					}

				}

				closeConnection();	return salida;
			} else {
				closeConnection();	return null;

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 29> en la base de datos : "+e.toString());
			closeConnection();	
			return null;
		}

	}
	
	public String[][] listaTarifas(int idProto) {
		conectar();
		

		Statement consulta = null;
		ResultSet tabla = null;
		Statement consulta1 = null;
		ResultSet tabla1 = null;
		System.out.println(idProto);
		try {
			consulta1 = con.createStatement();
			tabla1 = consulta1.executeQuery("SELECT * FROM "+dtbs+".tarifas where tarifas.activo=1 order by idtarifas asc");
			
 			if (tabla1.next()) {
 				tabla1.last();
 				String [][] salida=new String [tabla1.getRow()][6];
 				tabla1.first();
				int indice =0;
				while (tabla1.next()) {
					
					consulta = con.createStatement();
					tabla = consulta1.executeQuery("SELECT * FROM "+dtbs+".tabla_tarifas where  cod_examen =' "+tabla1.getInt(1)+"'");
					if (tabla.next()) {
						salida [indice][0]=tabla1.getInt(0)+"";
						salida [indice][1]=tabla1.getString(1)+"";
						salida [indice][2]=tabla.getInt(3)+"";
						salida [indice][3]=tabla.getInt(4)+"";
						salida [indice][4]=tabla.getInt(5)+"";
						salida [indice][5]=tabla.getInt(6)+"";
						indice++;
					}else{
						salida [indice][0]=tabla1.getInt(0)+"";
						salida [indice][1]=tabla1.getString(1)+"";
						salida [indice][2]="";
						salida [indice][3]="";
						salida [indice][4]="";
						salida [indice][5]="";
						indice++;
					}
				
					
				}
				closeConnection();	return salida;
				
			} else {
				closeConnection();	return null;

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 30> en la base de datos : "+e.toString());
			closeConnection();	
			return null;
		}

	}

	public String[][] listaTarifasVacio(){
		conectar();
		

		Statement consulta = null;
		ResultSet tabla = null;
		Statement consulta1 = null;
		ResultSet tabla1 = null;
		try {
			consulta1 = con.createStatement();
			tabla1 = consulta1.executeQuery("SELECT * FROM "+dtbs+".tarifas where tarifas.activo=1 order by idtarifas asc ");
			
 			if (tabla1.next()) {
 				tabla1.last();
 				String [][] salida=new String [tabla1.getRow()][6];
 				tabla1.beforeFirst();
				int indice =0;
				while (tabla1.next()) {
		
						salida [indice][0]=tabla1.getInt(1)+"";
						salida [indice][1]=tabla1.getString(2)+"";
						salida [indice][2]="";
						salida [indice][3]="";
						salida [indice][4]="";
						salida [indice][5]="";
						indice++;
	
				}
				closeConnection();	return salida;
				
			} else {
				closeConnection();	return null;

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 31> en la base de datos : "+e.toString());
			closeConnection();	
			return null;
		}

	}

	public ArrayList<Tarifa> arrayTarifas() {
		conectar();
		

		Statement consulta1 = null;
		ResultSet tabla1 = null;
		try {
			consulta1 = con.createStatement();
			tabla1 = consulta1.executeQuery("SELECT * FROM "+dtbs+".tarifas where tarifas.activo=1 order by `idtarifas` asc");
			tabla1.next();

			if (tabla1.next()) {
				tabla1.last();
				ArrayList<Tarifa> salida = new ArrayList<Tarifa>();
				tabla1.beforeFirst();

				while (tabla1.next()) {
					
					Tarifa auxitarifa=new Tarifa(tabla1.getInt(1), tabla1.getString(2), tabla1.getInt(3), tabla1.getInt(4)); 
					salida.add(auxitarifa);
					
				}

				closeConnection();	return salida;
			} else {
				closeConnection();	return null;

			}
		} catch (Exception e) {
			Principal.getInstancePrincipal().registrarErrorDB("< ERROR CONCOM 32> en la base de datos : "+e.toString());
			closeConnection();	
			return null;
		}

	}
	
	//#####################################################################     METODOS LOCALES SIN CONEXION
	
	
	   public String[]  listaImpresoras() {
		   
	        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
	 
	        String[] salida=new String[services.length+1];
	        salida[0]="Lista de impresoras";
	        int indice=1;
	        for (PrintService printService : services) {
	 
	          salida[indice]=printService.getName();
	          indice++;
	        }
	 return salida;
	    }
}
