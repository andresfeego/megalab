package auxiliares;

import java.util.Date;

public class itemCuenta {
	private int idItem;
	private int idCuenta;
	private int numFacturaRecepcion;
	private String idPaciente;
	private Date fechaRecepcion;
	private int valorProcedimiento;
	private int cups;
	

 

	public itemCuenta(int idItem, int idCuenta, int numFacturaRecepcion, String idPaciente, Date fechaRecepcion, int valorProcedimiento,int cups) {
		super();
		this.idItem = idItem;
		this.idCuenta = idCuenta;
		this.numFacturaRecepcion = numFacturaRecepcion;
		this.idPaciente = idPaciente;
		this.fechaRecepcion = fechaRecepcion;
		this.valorProcedimiento = valorProcedimiento;
		this.cups=cups;
	}


	public int getIdItem() {
		return idItem;
	}


	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}


	public int getNumFacturaRecepcion() {
		return numFacturaRecepcion;
	}


	public void setNumFacturaRecepcion(int numFacturaRecepcion) {
		this.numFacturaRecepcion = numFacturaRecepcion;
	}


	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}


	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}


	public int getValorProcedimiento() {
		return valorProcedimiento;
	}


	public void setValorProcedimiento(int valorProcedimiento) {
		this.valorProcedimiento = valorProcedimiento;
	}


	public int getIdCuenta() {
		return idCuenta;
	}


	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}


	public String getIdPaciente() {
		return idPaciente;
	}


	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}


	public int getCups() {
		return cups;
	}


	public void setCups(int cups) {
		this.cups = cups;
	}
	
	
	
}
