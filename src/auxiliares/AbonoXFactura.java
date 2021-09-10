package auxiliares;

import java.util.ArrayList;

public class AbonoXFactura {
	private int  idFactura;
	private int codFormaPago;
	private int abono;
	private String obs;
	private DatosAbono DA;
	private int numeroTarjeta;

	
	

	public AbonoXFactura(int idFac, int codFormaPago, int abono, String obs, DatosAbono dA, int numeroTarjeta) {
		super();
		this.idFactura = idFac;
		this.codFormaPago = codFormaPago;
		this.abono = abono;
		this.obs = obs;
		DA = dA;
		this.numeroTarjeta = numeroTarjeta;
	}
	
	
	public int getIdFactura() {
		return idFactura;
	}


	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}


	public void setDA(DatosAbono dA) {
		DA = dA;
	}


	public int getCodFormaPago() {
		return codFormaPago;
	}
	public void setCodFormaPago(int codFormaPago) {
		this.codFormaPago = codFormaPago;
	}
	public int getAbono() {
		return abono;
	}
	public void setAbono(int abono) {
		this.abono = abono;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public int getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(int numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}


	
	
	
	
}
