package auxiliares;

public class Empresa {
	
	private int idEmpresa;
	private String docEmpresa;
	private String razonSocial;
	private String direccion;
	private String ciudad;
	private String dependenciaCobro;
	private String tarifa;
	private int descuento;
	private String codEps;
	private int  tipoUsuario;
	private String tipoUsuarioXNombre;
	private String adicional;
	private String requisitosRecepcion;
	private int activo;
	private String email1;
	private String email2;
	private String telefono1;
	private String telefono2;
	
	
	
	
	public Empresa( String docEmpresa, String razonSocial,
			String direccion, String ciudad, String dependenciaCobro, String tarifa,
			int descuento, String codEps, int  tipoUsuario, String adicional,
			String requisitosRecepcion, int activo,String email1, String email2, String telefono1, String telefono2) {
		super();
		this.docEmpresa = docEmpresa;
		this.razonSocial = razonSocial;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.dependenciaCobro = dependenciaCobro;
		this.tarifa = tarifa;
		this.descuento = descuento;
		this.codEps = codEps;
		this.tipoUsuario = tipoUsuario;
		this.adicional = adicional;
		this.requisitosRecepcion = requisitosRecepcion;
		this.activo = activo;
		this.email1 = email1;
		this.email2 = email2;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
	}




	public Empresa( String docEmpresa, String razonSocial, String direccion, String ciudad, String dependenciaCobro, String tarifa, int descuento, String codEps, String tipoUsuarioXNombre, String adicional, String requisitosRecepcion, int activo, String email1, String email2, String telefono1, String telefono2) {
		super();
		this.idEmpresa = idEmpresa;
		this.docEmpresa = docEmpresa;
		this.razonSocial = razonSocial;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.dependenciaCobro = dependenciaCobro;
		this.tarifa = tarifa;
		this.descuento = descuento;
		this.codEps = codEps;
		this.tipoUsuarioXNombre = tipoUsuarioXNombre;
		this.adicional = adicional;
		this.requisitosRecepcion = requisitosRecepcion;
		this.activo = activo;
		this.email1 = email1;
		this.email2 = email2;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
	}




	public String getDocEmpresa() {
		return docEmpresa;
	}




	public void setDocEmpresa(String docEmpresa) {
		this.docEmpresa = docEmpresa;
	}




	public String getRazonSocial() {
		return razonSocial;
	}




	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}




	public String getDireccion() {
		return direccion;
	}




	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}




	public String getCiudad() {
		return ciudad;
	}




	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}




	public String getDependenciaCobro() {
		return dependenciaCobro;
	}




	public void setDependenciaCobro(String dependenciaCobro) {
		this.dependenciaCobro = dependenciaCobro;
	}




	public String getTarifa() {
		return tarifa;
	}




	public void setTarifa(String tarifa) {
		this.tarifa = tarifa;
	}




	public int getDescuento() {
		return descuento;
	}




	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}




	public String getCodEps() {
		return codEps;
	}




	public void setCodEps(String codEps) {
		this.codEps = codEps;
	}




	public int  getTipoUsuario() {
		return tipoUsuario;
	}




	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}




	public String getAdicional() {
		return adicional;
	}




	public void setAdicional(String adicional) {
		this.adicional = adicional;
	}




	public String getRequisitosRecepcion() {
		return requisitosRecepcion;
	}




	public void setRequisitosRecepcion(String requisitosRecepcion) {
		this.requisitosRecepcion = requisitosRecepcion;
	}




	public int getActivo() {
		return activo;
	}




	public void setActivo(int activo) {
		this.activo = activo;
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




	public int getIdEmpresa() {
		return idEmpresa;
	}




	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}




	@Override
	public String toString() {
		return idEmpresa+razonSocial+"";
	}




	public String getTipoUsuarioXNombre() {
		return tipoUsuarioXNombre;
	}




	public void setTipoUsuarioXNombre(String tipoUsuarioXNombre) {
		this.tipoUsuarioXNombre = tipoUsuarioXNombre;
	}
	
	
	
	

}
