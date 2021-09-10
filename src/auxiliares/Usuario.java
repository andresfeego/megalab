package auxiliares;

import java.awt.Color;

import com.sun.org.apache.xalan.internal.xsltc.dom.BitArray;

public class Usuario {
	
	private int idUsuario;
	private String Usuario;
	private String Pass;
	private String Nombre;
	private Color color;
	private int tiempoI;
	private int tipoUsuario;
	private int activo;
	private int admin;
	private String acceso;
	
	



	public Usuario(int idUsuario, String usuario, String pass, String nombre,
			Color color, int tiempoI, int tipoUsuario, int activo,int admin,String acceso) {
		super();
		this.idUsuario = idUsuario;
		Usuario = usuario;
		Pass = pass;
		Nombre = nombre;
		this.color = color;
		this.tiempoI = tiempoI;
		this.tipoUsuario = tipoUsuario;
		this.activo = activo;
		this.admin=admin;
		this.acceso=acceso;
	}


	/*public Usuario(String usuario, String pass, String nombre, String color,
			int tiempoI, int tipoUsuario, int activo) {
		super();
		Usuario = usuario;
		Pass = pass;
		Nombre = nombre;
		this.color = color;
		this.tiempoI = tiempoI;
		this.tipoUsuario = tipoUsuario;
		this.activo = activo;
	}
*/

	public int getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getUsuario() {
		return Usuario;
	}


	public void setUsuario(String usuario) {
		Usuario = usuario;
	}


	public String getPass() {
		return Pass;
	}


	public void setPass(String pass) {
		Pass = pass;
	}


	public String getNombre() {
		return Nombre;
	}


	public void setNombre(String nombre) {
		Nombre = nombre;
	}


	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}


	public int getTiempoI() {
		return tiempoI;
	}


	public void setTiempoI(int tiempoI) {
		this.tiempoI = tiempoI;
	}


	public int getTipoUsuario() {
		return tipoUsuario;
	}


	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}


	public int getActivo() {
		return activo;
	}


	public void setActivo(int activo) {
		this.activo = activo;
	}
	
	public void setAcceso(String acceso) {
		this.acceso = acceso;
	}


	public int getAdmin() {
		return admin;
	}


	public void setAdmin(int admin) {
		this.admin = admin;
	}


	public String getAcceso() {
		return acceso;
	}	
	
	
	
	
}
