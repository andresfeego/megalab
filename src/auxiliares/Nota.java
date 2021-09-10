package auxiliares;

public class Nota {
	private int idNota;
	private Factura factura;
	private int valor;
	private String motivo;
	private int tipoCD;
	private int totalFactura;
	
	


	public Nota(int idNota, Factura factura, int valor, String motivo, int tipoCD, int totalFactura) {
		super();
		this.idNota = idNota;
		this.factura = factura;
		this.valor = valor;
		this.motivo = motivo;
		this.tipoCD = tipoCD;
		this.totalFactura = totalFactura;
	}


	public int getIdNota() {
		return idNota;
	}


	public void setIdNota(int idNota) {
		this.idNota = idNota;
	}



	public Factura getFactura() {
		return factura;
	}


	public void setFactura(Factura factura) {
		this.factura = factura;
	}


	public int getValor() {
		return valor;
	}


	public void setValor(int valor) {
		this.valor = valor;
	}


	public String getMotivo() {
		return motivo;
	}


	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}


	public int getTipoCD() {
		return tipoCD;
	}


	public void setTipoCD(int tipoCD) {
		this.tipoCD = tipoCD;
	}


	public int getTotalFactura() {
		return totalFactura;
	}


	public void setTotalFactura(int totalFactura) {
		this.totalFactura = totalFactura;
	}
	
	
	
	
}
