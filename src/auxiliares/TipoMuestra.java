package auxiliares;

public class TipoMuestra {
	private int idTipoMuestra;
	private String nombre;
	private String sigla;
	
	
	
	public TipoMuestra(int idTipoMuestra, String nombre, String sigla) {
		super();
		this.idTipoMuestra = idTipoMuestra;
		this.nombre = nombre;
		this.sigla = sigla;
	}



	public int getIdTipoMuestra() {
		return idTipoMuestra;
	}



	public void setIdTipoMuestra(int idTipoMuestra) {
		this.idTipoMuestra = idTipoMuestra;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getSigla() {
		return sigla;
	}



	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	
}
