package auxiliares;

import java.util.ArrayList;

public class AbonoXEmpresa {
	private int  idEmpresa;
	private int codFormaPago;
	private int abono;
	private String obs;
	private ArrayList<DatosAbono> DA;
	private int numeroTarjeta;

	


	public AbonoXEmpresa(int idEmpresa, int codFormaPago, int abono, String obs, ArrayList<DatosAbono> dA, int numeroTarjeta) {
		super();
		this.idEmpresa = idEmpresa;
		this.codFormaPago = codFormaPago;
		this.abono = abono;
		this.obs = obs;
		DA = dA;
		this.numeroTarjeta = numeroTarjeta;
	}
	// ACTUALIZACIONN    preguntar si es necesario que se registren los plane pyp y subgrupos de empresas en las facturas para hacer abonos por separado 
	
	public int getIdPaciente() {
		return idEmpresa;
	}
	public void setIdPaciente(int idEmpresa) {
		this.idEmpresa = idEmpresa;
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


	public ArrayList<DatosAbono> getDA() {
		return DA;
	}


	public void setDA(ArrayList<DatosAbono> dA) {
		DA = dA;
	}
	
	
	
	
	
}
