package auxiliares;

import java.awt.Image;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

public class Bacteriologo {
	
	private String id;
	private int tipoId;
	private String nombres;
	private String apellidos;
	private Date fechaNacimiento;
	private String edad;
	private int genero;
	private int zonaResidencial;
	private String ciudad;
	private String direccion;
	private String email1;
	private String email2;
	private String telefono1;
	private String telefono2;
	private String registro;
	private String titulo;
	private String otrosEstudios;
	private String inf_adicional;
	private Image firma;
	private InputStream FIS;
	private int claveFirma;
	
	
	
/*	public Bacteriologo(String id, int tipoId, String nombres,
			String apellidos, Date fechaNacimiento, String edad, int genero,
			int zonaResidencial, String ciudad, String direccion,
			String email1, String email2, String telefono1, String telefono2,
			String registro, String titulo, String otrosEstudios,
			String inf_adicional) {
		super();
		this.id = id;
		this.tipoId = tipoId;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.edad = edad;
		this.genero = genero;
		this.zonaResidencial = zonaResidencial;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.email1 = email1;
		this.email2 = email2;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
		this.registro = registro;
		this.titulo = titulo;
		this.otrosEstudios = otrosEstudios;
		this.inf_adicional = inf_adicional;
	}
	
	*/
	
	




	public Bacteriologo(String id, int tipoId, String nombres, String apellidos, Date fechaNacimiento, String edad, int genero, int zonaResidencial, String ciudad, String direccion, String email1, String email2, String telefono1, String telefono2, String registro, String titulo, String otrosEstudios, String inf_adicional, Image firma, InputStream fIS,int claveFirma) {
		super();
		this.id = id;
		this.tipoId = tipoId;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.edad = edad;
		this.genero = genero;
		this.zonaResidencial = zonaResidencial;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.email1 = email1;
		this.email2 = email2;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
		this.registro = registro;
		this.titulo = titulo;
		this.otrosEstudios = otrosEstudios;
		this.inf_adicional = inf_adicional;
		this.firma = firma;
		FIS = fIS;
		this.claveFirma=claveFirma;
	}








	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getTipoId() {
		return tipoId;
	}
	public void setTipoId(int tipoId) {
		this.tipoId = tipoId;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getEdad() {
		return edad;
	}
	public void setEdad(String edad) {
		this.edad = edad;
	}
	public int getGenero() {
		return genero;
	}
	public void setGenero(int genero) {
		this.genero = genero;
	}
	public int getZonaResidencial() {
		return zonaResidencial;
	}
	public void setZonaResidencial(int zonaResidencial) {
		this.zonaResidencial = zonaResidencial;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getTelefono1() {
		return telefono1;
	}
	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}
	public String getTelefono2() {
		return telefono2;
	}
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
	public String getRegistro() {
		return registro;
	}
	public void setRegistro(String registro) {
		this.registro = registro;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getOtrosEstudios() {
		return otrosEstudios;
	}
	public void setOtrosEstudios(String otrosEstudios) {
		this.otrosEstudios = otrosEstudios;
	}
	public String getInf_adicional() {
		return inf_adicional;
	}
	public void setInf_adicional(String inf_adicional) {
		this.inf_adicional = inf_adicional;
	}


	public Image getFirma() {
		return firma;
	}


	public void setFirma(Image firma) {
		this.firma = firma;
	}


	public InputStream getFIS() {
		return FIS;
	}


	public void setFIS(InputStream fIS) {
		FIS = fIS;
	}








	public int getClaveFirma() {
		return claveFirma;
	}








	public void setClaveFirma(int claveFirma) {
		this.claveFirma = claveFirma;
	}
	
	
	
	
	
	
	
	
	
	

	
	
	
	
}
