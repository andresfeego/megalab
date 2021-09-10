package auxiliares;

import java.util.Date;

public class Medico {
	
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
	private String idmedico;
	private int especialidad;
	private int activo;
	
	
	
	
	
	public Medico(String id, int tipoId, String nombres,
			String apellidos, Date fechaNacimiento, String edad, int genero,
			int zonaResidencial, String ciudad, String direccion,
			String email1, String email2, String telefono1, String telefono2,
			String idmedico, int especialidad, int activo) {
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
		this.idmedico = idmedico;
		this.especialidad = especialidad;
		this.activo = activo;
		
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


	public String getIdmedico() {
		return idmedico;
	}


	public void setIdmedico(String idmedico) {
		this.idmedico = idmedico;
	}


	public int getEspecialidad() {
		return especialidad;
	}


	public void setEspecialidad(int especialidad) {
		this.especialidad = especialidad;
	}


	public int getActivo() {
		return activo;
	}


	public void setActivo(int activo) {
		this.activo = activo;
	}
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
}
