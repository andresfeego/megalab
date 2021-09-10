package auxiliares;

public class Laboratorio {
	private int idLab;
	private String nit;
	private String RazonSocial;
	private String direccion;
	private int idCiudad;
	private String infAdicional;
	
	
	public Laboratorio(String nit, String razonSocial, String direccion, int idCiudad, String infAdicional) {
		super();
		this.nit = nit;
		RazonSocial = razonSocial;
		this.direccion = direccion;
		this.idCiudad = idCiudad;
		this.infAdicional = infAdicional;
	}


	public Laboratorio(int idLab, String nit, String razonSocial, String direccion, int idCiudad, String infAdicional) {
		super();
		this.idLab = idLab;
		this.nit = nit;
		RazonSocial = razonSocial;
		this.direccion = direccion;
		this.idCiudad = idCiudad;
		this.infAdicional = infAdicional;
	}


	public int getIdLab() {
		return idLab;
	}


	public void setIdLab(int idLab) {
		this.idLab = idLab;
	}


	public String getNit() {
		return nit;
	}


	public void setNit(String nit) {
		this.nit = nit;
	}


	public String getRazonSocial() {
		return RazonSocial;
	}


	public void setRazonSocial(String razonSocial) {
		RazonSocial = razonSocial;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public int getIdCiudad() {
		return idCiudad;
	}


	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}


	public String getInfAdicional() {
		return infAdicional;
	}


	public void setInfAdicional(String infAdicional) {
		this.infAdicional = infAdicional;
	}

	
	
	
	
}
