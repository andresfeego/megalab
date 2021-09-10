package auxiliares;

public class Secciones {
	
	private int idSeccion;
	private String sigla;
	private String nombre;
	
	
	
	
	public Secciones(int idSeccion, String sigla, String descripcion) {
		super();
		this.idSeccion = idSeccion;
		this.sigla = sigla;
		nombre = descripcion;
	}
	
	
	public int getIdSeccion() {
		return idSeccion;
	}
	public void setIdSeccion(int idSeccion) {
		this.idSeccion = idSeccion;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		nombre = nombre;
	}
	
	

}
