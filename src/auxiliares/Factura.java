package auxiliares;

public class Factura {
	private int idFactura;
	private int codRecepcion;
	private int codFormadePago;
	private int descuentoPorciento;
	private int codTipoPago;
	private int abono;
	private int saldo;
	private int totalSinD;
	private int totalConD;
	private String observaciones;
	private int anulada;
	
	
	public Factura(int idFactura, int codRecepcion, int codFormadePago, int descuentoPorciento, int codTipoPago, int abono, int saldo, int totalSinD, int totalConD, String observaciones) {
		super();
		this.idFactura = idFactura;
		this.codRecepcion = codRecepcion;
		
		this.codFormadePago = codFormadePago;
		this.descuentoPorciento = descuentoPorciento;
		this.codTipoPago = codTipoPago;
		this.abono = abono;
		this.saldo = saldo;
		this.totalSinD = totalSinD;
		this.totalConD = totalConD;
		this.observaciones = observaciones;

	}
	
	
	public Factura(int idFactura, int codRecepcion, int codFormadePago, int descuentoPorciento, int codTipoPago, int abono, int saldo, int totalSinD, int totalConD, String observaciones,int anulada) {
		super();
		this.idFactura = idFactura;
		this.codRecepcion = codRecepcion;
		
		this.codFormadePago = codFormadePago;
		this.descuentoPorciento = descuentoPorciento;
		this.codTipoPago = codTipoPago;
		this.abono = abono;
		this.saldo = saldo;
		this.totalSinD = totalSinD;
		this.totalConD = totalConD;
		this.observaciones = observaciones;
		this.anulada=anulada;

	}


	public int getIdFactura() {
		return idFactura;
	}


	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}


	public int getCodRecepcion() {
		return codRecepcion;
	}


	public void setCodRecepcion(int codRecepcion) {
		this.codRecepcion = codRecepcion;
	}

	public int getCodFormadePago() {
		return codFormadePago;
	}


	public void setCodFormadePago(int codFormadePago) {
		this.codFormadePago = codFormadePago;
	}


	public int getDescuentoPorciento() {
		return descuentoPorciento;
	}


	public void setDescuentoPorciento(int descuentoPorciento) {
		this.descuentoPorciento = descuentoPorciento;
	}


	public int getCodTipoPago() {
		return codTipoPago;
	}


	public void setCodTipoPago(int codTipoPago) {
		this.codTipoPago = codTipoPago;
	}


	public int getAbono() {
		return abono;
	}


	public void setAbono(int abono) {
		this.abono = abono;
	}


	public int getSaldo() {
		return saldo;
	}


	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}


	public int getTotalSinD() {
		return totalSinD;
	}


	public void setTotalSinD(int totalSinD) {
		this.totalSinD = totalSinD;
	}


	public int getTotalConD() {
		return totalConD;
	}


	public void setTotalConD(int totalConD) {
		this.totalConD = totalConD;
	}


	public String getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}


	public int getAnulada() {
		return anulada;
	}


	public void setAnulada(int anulada) {
		this.anulada = anulada;
	}
	
	
	
	
	
}
