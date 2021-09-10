package auxiliares;

import java.util.ArrayList;
import java.util.Date;

public class itemCotizacion {
	private int idItemAI;
	private int idCotizacion;
	private String codExamen;
	private int RU;
	private int RF;
	private int RE;
	private int valor;
	private int orden;
	
	
	public itemCotizacion(int idItemAI, int idCotizacion, String codExamen, int rU, int rF, int rE, int valor, int orden) {
		super();
		this.idItemAI = idItemAI;
		this.idCotizacion = idCotizacion;
		this.codExamen = codExamen;
		RU = rU;
		RF = rF;
		RE = rE;
		this.valor = valor;
		this.orden = orden;
	}


	public int getIdItemAI() {
		return idItemAI;
	}


	public void setIdItemAI(int idItemAI) {
		this.idItemAI = idItemAI;
	}


	public int getIdCotizacion() {
		return idCotizacion;
	}


	public void setIdCotizacion(int idCotizacion) {
		this.idCotizacion = idCotizacion;
	}


	public String getCodExamen() {
		return codExamen;
	}


	public void setCodExamen(String codExamen) {
		this.codExamen = codExamen;
	}


	public int getRU() {
		return RU;
	}


	public void setRU(int rU) {
		RU = rU;
	}


	public int getRF() {
		return RF;
	}


	public void setRF(int rF) {
		RF = rF;
	}


	public int getRE() {
		return RE;
	}


	public void setRE(int rE) {
		RE = rE;
	}


	public int getValor() {
		return valor;
	}


	public void setValor(int valor) {
		this.valor = valor;
	}


	public int getOrden() {
		return orden;
	}


	public void setOrden(int orden) {
		this.orden = orden;
	}

	
	
	
	
}
