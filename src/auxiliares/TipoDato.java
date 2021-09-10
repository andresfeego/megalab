package auxiliares;

public class TipoDato {
	private int idTD;
	private String nombreTD;
	private String siglaTD;
	
	
	
	public TipoDato(int idTD, String nombreTD, String siglaTD) {
		super();
		this.idTD = idTD;
		this.nombreTD = nombreTD;
		this.siglaTD = siglaTD;
	}



	public int getIdTD() {
		return idTD;
	}



	public void setIdTD(int idTD) {
		this.idTD = idTD;
	}



	public String getNombreTD() {
		return nombreTD;
	}



	public void setNombreTD(String nombreTD) {
		this.nombreTD = nombreTD;
	}



	public String getSiglaTD() {
		return siglaTD;
	}



	public void setSiglaTD(String siglaTD) {
		this.siglaTD = siglaTD;
	}
	
	
	
	
	
}
