package auxiliares;

import java.util.ArrayList;

public class AbonoXPaciente {
	private String idPaciente;
	private int codFormaPago;
	private int abono;
	private String obs;
	private ArrayList<DatosAbono> DA;
	private int numeroTarjeta;

	
	

	public AbonoXPaciente(String idPaciente, int codFormaPago, int abono, String obs, ArrayList<DatosAbono> dA, int numeroTarjeta) {
		super();
		this.idPaciente = idPaciente;
		this.codFormaPago = codFormaPago;
		this.abono = abono;
		this.obs = obs;
		DA = dA;
		this.numeroTarjeta = numeroTarjeta;
	}
	
	
	public String getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
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
