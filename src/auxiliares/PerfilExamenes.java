package auxiliares;

import java.util.ArrayList;

public class PerfilExamenes {
	private int idPerfil;
	private String nombrePerfil;
	private ArrayList<Examen> examenesDelPerfil=new ArrayList<Examen>();
	
	
	public PerfilExamenes(int idPerfil, String nombrePerfil, ArrayList<Examen> examenesDelPerfil) {
		super();
		this.idPerfil = idPerfil;
		this.nombrePerfil = nombrePerfil;
		this.examenesDelPerfil = examenesDelPerfil;
	}


	public int getIdPerfil() {
		return idPerfil;
	}


	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}


	public String getNombrePerfil() {
		return nombrePerfil;
	}


	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}


	public ArrayList<Examen> getExamenesDelPerfil() {
		return examenesDelPerfil;
	}


	public void setExamenesDelPerfil(ArrayList<Examen> examenesDelPerfil) {
		this.examenesDelPerfil = examenesDelPerfil;
	}
	
	
	
	
}
