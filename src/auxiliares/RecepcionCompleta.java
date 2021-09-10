package auxiliares;

import java.util.ArrayList;

public class RecepcionCompleta {
	private Recepcion recepcion;
	private Factura factura;
	private ArrayList<itemFactura> itemsFactura;
	
	
	public RecepcionCompleta(Recepcion recepcion, Factura factura, ArrayList<itemFactura> itemsFactura) {
		super();
		this.recepcion = recepcion;
		this.factura = factura;
		this.itemsFactura=itemsFactura;
	}


	public Recepcion getRecepcion() {
		return recepcion;
	}


	public void setRecepcion(Recepcion recepcion) {
		this.recepcion = recepcion;
	}


	public Factura getFactura() {
		return factura;
	}


	public void setFactura(Factura factura) {
		this.factura = factura;
	}



	public ArrayList<itemFactura> getItemsFactura() {
		return itemsFactura;
	}


	public void setItemsFactura(ArrayList<itemFactura> itemsFactura) {
		this.itemsFactura = itemsFactura;
	}
	
	
	
	
}
