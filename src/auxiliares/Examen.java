package auxiliares;

import java.util.ArrayList;

public class Examen {
	

	private int idExamen;
	private String nombre;
	private int cups;
	private int duracion;
	private int nivel;
	private int idMuestra;
	private int idDias;
	private int stickers;
	private int codProtocolo;
	private String codigo;
	private String codBayer;
	private int seRemite;
	private String auxiWeb;
	private ArrayList<itemTarifa> IT;
	
	
	
	
	
	public Examen(int idExamen, String nombre, int cups, int duracion, int nivel, int idMuestra, int idDias,int stickers, int codProtocolo, String codigo, String codBayer, int seRemite,String auxiWeb) {
		super();
		this.idExamen = idExamen;
		this.nombre = nombre;
		this.cups = cups;
		this.duracion = duracion;
		this.nivel = nivel;
		this.idMuestra = idMuestra;
		this.idDias = idDias;
		this.stickers=stickers;
		this.codProtocolo = codProtocolo;
		this.codigo = codigo;
		this.codBayer = codBayer;
		this.seRemite = seRemite;
		this.auxiWeb=auxiWeb;
	}
	
	
	



	public Examen(int idExamen, String nombre, int cups, int duracion, int nivel, int idMuestra, int idDias,int stickers, int codProtocolo, String codigo, String codBayer, int seRemite,String auxiWeb, ArrayList<itemTarifa> iT) {
		super();
		this.idExamen = idExamen;
		this.nombre = nombre;
		this.cups = cups;
		this.duracion = duracion;
		this.nivel = nivel;
		this.idMuestra = idMuestra;
		this.idDias = idDias;
		this.stickers=stickers;
		this.codProtocolo = codProtocolo;
		this.codigo = codigo;
		this.codBayer = codBayer;
		this.seRemite = seRemite;
		this.auxiWeb=auxiWeb;

		IT = iT;
	}






	public int getIdExamen() {
		return idExamen;
	}
	public void setIdExamen(int idExamen) {
		this.idExamen = idExamen;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCups() {
		return cups;
	}
	public void setCups(int cups) {
		this.cups = cups;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public int getIdMuestra() {
		return idMuestra;
	}
	public void setIdMuestra(int idMuestra) {
		this.idMuestra = idMuestra;
	}
	public int getIdDias() {
		return idDias;
	}
	public void setIdDias(int idDias) {
		this.idDias = idDias;
	}
	public int getCodProtocolo() {
		return codProtocolo;
	}
	public void setCodProtocolo(int codProtocolo) {
		this.codProtocolo = codProtocolo;
	}
	public String getSigla() {
		return codigo;
	}
	public void setSigla(String sigla) {
		this.codigo = sigla;
	}
	public String getCodBayer() {
		return codBayer;
	}
	public void setCodBayer(String codBayer) {
		this.codBayer = codBayer;
	}
	public int getSeRemite() {
		return seRemite;
	}
	public void setSeRemite(int seRemite) {
		this.seRemite = seRemite;
	}
	public int getStickers() {
		return stickers;
	}
	public void setStickers(int stickers) {
		this.stickers = stickers;
	}
	public ArrayList<itemTarifa> getIT() {
		return IT;
	}
	public void setIT(ArrayList<itemTarifa> iT) {
		IT = iT;
	}






	public String getAuxiWeb() {
		return auxiWeb;
	}






	public void setAuxiWeb(String auxiWeb) {
		this.auxiWeb = auxiWeb;
	}
	
	
	
	
}
