package auxiliares;

public class OTD {
	
	private int idOTD;
	private String Opcion;
	private int idTD;
	
	
	
	
	public OTD(int idOTD, String opcion, int idTD) {
		super();
		this.idOTD = idOTD;
		Opcion = opcion;
		this.idTD = idTD;
	}
	
	
	public int getIdOTD() {
		return idOTD;
	}
	public void setIdOTD(int idOTD) {
		this.idOTD = idOTD;
	}
	public String getOpcion() {
		return Opcion;
	}
	public void setOpcion(String opcion) {
		Opcion = opcion;
	}
	public int getIdTD() {
		return idTD;
	}
	public void setIdTD(int idTD) {
		this.idTD = idTD;
	}
	
	

}
