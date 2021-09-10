package auxiliares;

import java.util.Date;

public class DatosAbono {
	private int idFactura;
	private int codRecepcion;
	private int total;
	private int abono;
	private int saldo;
	private Date fechaYHora;
	private String rzonSocial;
	private int idPaciente;
	private String nombresYApellidos;
	
	
	public DatosAbono(int idFactura, int codRecepcion, int total, int abono, int saldo, Date fechaYHora, String rzonSocial) {
		super();
		this.idFactura = idFactura;
		this.codRecepcion = codRecepcion;
		this.total = total;
		this.abono = abono;
		this.saldo = saldo;
		this.fechaYHora = fechaYHora;
		this.rzonSocial = rzonSocial;
	}

	public DatosAbono(int idFactura, int codRecepcion, int total, int abono, int saldo, Date fechaYHora) {
		super();
		this.idFactura = idFactura;
		this.codRecepcion = codRecepcion;
		this.total = total;
		this.abono = abono;
		this.saldo = saldo;
		this.fechaYHora = fechaYHora;
	}

	

	public DatosAbono(int idFactura, int codRecepcion, int total, int abono, int saldo, Date fechaYHora, String rzonSocial, int idPaciente, String nombresYApellidos) {
		super();
		this.idFactura = idFactura;
		this.codRecepcion = codRecepcion;
		this.total = total;
		this.abono = abono;
		this.saldo = saldo;
		this.fechaYHora = fechaYHora;
		this.rzonSocial = rzonSocial;
		this.idPaciente = idPaciente;
		this.nombresYApellidos = nombresYApellidos;
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


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
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


	public Date getFechaYHora() {
		return fechaYHora;
	}


	public void setFechaYHora(Date fechaYHora) {
		this.fechaYHora = fechaYHora;
	}


	public String getRzonSocial() {
		return rzonSocial;
	}


	public void setRzonSocial(String rzonSocial) {
		this.rzonSocial = rzonSocial;
	}

	public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getNombresYApellidos() {
		return nombresYApellidos;
	}

	public void setNombresYApellidos(String nombresYApellidos) {
		this.nombresYApellidos = nombresYApellidos;
	}
	
	
	
	
}
