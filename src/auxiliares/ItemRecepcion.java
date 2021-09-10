package auxiliares;

import java.util.Calendar;

public class ItemRecepcion {
	private int idItemRecepcion;
	private String concepto;
	private String unidadMedida;
	private String abreviaturaMedida;
	private String idExamen;
	private int  idProtocolo;
	private String valor;
	private double Desde;
	private double Hasta;
	private String valorResultado;
	private int tipoDato;
	private int genero;
	private int reportado;
	private String interpretacion;
	
	private int orden;
	
	
	public ItemRecepcion(int idItemRecepcion, String idExamen,String concepto, int  idProtocolo, String valor, double desde, double hasta, String valorResultado, int tipoDato, int genero, int reportado,  int orden,String unidadMedida,String abrevMedida,String interpretacion) {
		super();
		this.idItemRecepcion = idItemRecepcion;
		this.concepto=concepto;
		this.idExamen = idExamen;
		this.unidadMedida=unidadMedida;
		this.abreviaturaMedida=abrevMedida;
		this.idProtocolo = idProtocolo;
		this.valor = valor;
		this.Desde= desde;
		this.Hasta = hasta;
		this.valorResultado = valorResultado;
		this.reportado = reportado;
		this.tipoDato=tipoDato;
		this.genero=genero;
		this.orden = orden;
		this.interpretacion=interpretacion;
	}
	
	
	
	


	public int getIdRecepcion() {
		return idItemRecepcion;
	}


	public void setIdRecepcion(int idRecepcion) {
		this.idItemRecepcion = idRecepcion;
	}


	public String getIdExamen() {
		return idExamen;
	}


	public void setIdExamen(String idExamen) {
		this.idExamen = idExamen;
	}


	public String getValorNormal() {
		return valor;
	}


	public void setValor(String valor) {
		this.valor = valor;
	}


	public double getDesde() {
		return Desde;
	}


	public void setDesde(double desde) {
		Desde = desde;
	}


	public double getHasta() {
		return Hasta;
	}


	public void setHasta(double hasta) {
		Hasta = hasta;
	}


	public String getValorResultado() {
		return valorResultado;
	}


	public void setValorResultado(String valorResultado) {
		this.valorResultado = valorResultado;
	}


	public int getReportado() {
		return reportado;
	}


	public void setReportado(int reportado) {
		this.reportado = reportado;
	}





	public int getTipoDato() {
		return tipoDato;
	}


	public void setTipoDato(int tipoDato) {
		this.tipoDato = tipoDato;
	}


	public int getGenero() {
		return genero;
	}


	public void setGenero(int genero) {
		this.genero = genero;
	}


	public int getOrden() {
		return orden;
	}


	public void setOrden(int orden) {
		this.orden = orden;
	}


	public int getIdItemRecepcion() {
		return idItemRecepcion;
	}


	public void setIdItemRecepcion(int idItemRecepcion) {
		this.idItemRecepcion = idItemRecepcion;
	}


	public int getIdProtocolo() {
		return idProtocolo;
	}


	public void setIdProtocolo(int idProtocolo) {
		this.idProtocolo = idProtocolo;
	}




	public String getConcepto() {
		return concepto;
	}




	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}




	public String getUnidadMedida() {
		return unidadMedida;
	}




	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}




	public String getAbreviaturaMedida() {
		return abreviaturaMedida;
	}




	public void setAbreviaturaMedida(String abreviaturaMedida) {
		this.abreviaturaMedida = abreviaturaMedida;
	}






	public String getInterpretacion() {
		return interpretacion;
	}






	public void setInterpretacion(String interpretacion) {
		this.interpretacion = interpretacion;
	}






	public String getValor() {
		return valor;
	}
	
	
	
	
	
}
