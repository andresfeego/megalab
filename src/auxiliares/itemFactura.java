package auxiliares;

import java.util.ArrayList;
import java.util.Date;

public class itemFactura {
	private int idItemAI;
	private String codExamen;
	private int RU;
	private int RF;
	private int RE;
	private int valor;
	private int orden;
	private ArrayList<ItemRecepcion> itemsExamenes;
	
	private int reportado;
	private int impreso;
	private Date fechaReporte;
	private int entregado;
	private String entregadoA;
	private String observaciones;
	private Date fechaEntrega;
	
	
	public itemFactura(int idItemAI, String codExamen, int rU, int rF, int rE, int valor, int orden,ArrayList<ItemRecepcion> itemsExamene) {
		super();
		this.idItemAI = idItemAI;
		this.codExamen = codExamen;
		RU = rU;
		RF = rF;
		RE = rE;
		this.valor = valor;
		this.orden = orden;
		this.itemsExamenes=itemsExamene;
	}
	
	
	


	public itemFactura(int idItemAI, String codExamen, int rU, int rF, int rE, int valor, int orden, ArrayList<ItemRecepcion> itemsExamenes, int reportado, int impreso, Date fechaReporte, int entregado, String entregadoA, String observaciones, Date fechaEntrega) {
		super();
		this.idItemAI = idItemAI;
		this.codExamen = codExamen;
		RU = rU;
		RF = rF;
		RE = rE;
		this.valor = valor;
		this.orden = orden;
		this.itemsExamenes = itemsExamenes;
		this.reportado = reportado;
		this.impreso = impreso;
		this.fechaReporte = fechaReporte;
		this.entregado = entregado;
		this.entregadoA = entregadoA;
		this.observaciones = observaciones;
		this.fechaEntrega = fechaEntrega;
	}





	public int getIdItemAI() {
		return idItemAI;
	}


	public void setIdItemAI(int idItemAI) {
		this.idItemAI = idItemAI;
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


	public ArrayList<ItemRecepcion> getItemsExamenes() {
		return itemsExamenes;
	}


	public void setItemsExamenes(ArrayList<ItemRecepcion> itemsExamenes) {
		this.itemsExamenes = itemsExamenes;
	}





	public int getReportado() {
		return reportado;
	}





	public void setReportado(int reportado) {
		this.reportado = reportado;
	}





	public int getImpreso() {
		return impreso;
	}





	public void setImpreso(int impreso) {
		this.impreso = impreso;
	}





	public Date getFechaReporte() {
		return fechaReporte;
	}





	public void setFechaReporte(Date fechaReporte) {
		this.fechaReporte = fechaReporte;
	}





	public int getEntregado() {
		return entregado;
	}





	public void setEntregado(int entregado) {
		this.entregado = entregado;
	}





	public String getEntregadoA() {
		return entregadoA;
	}





	public void setEntregadoA(String entregadoA) {
		this.entregadoA = entregadoA;
	}





	public String getObservaciones() {
		return observaciones;
	}





	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}





	public Date getFechaEntrega() {
		return fechaEntrega;
	}





	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	
	
	
	
	
	
}
