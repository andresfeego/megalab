package auxiliares;

import java.util.Calendar;
import java.util.Date;

public class Recepcion {
	private int idREcepcion;
	private Paciente paciente;
	private Empresa empresa;
	private Tarifa tarifa;
	private String sede;
	private int numeroMuestra;
	private String autorizacion;
	private String numeroOrden;
	private Date fechaOrden;
	private int codSubEmpresa;
	private int codEPyP;
	private int codSala;
	private int cama;
	private int TipoUsuario;
	private int codAmbito;
	private int codFinalidad;
	private String codMedico;
	private Date fechaTomaMuestra;
	private Date fechaRecepcion;
	private String observaciones;
	private String recepcionadoPor;
	private int anulada;
	
	
	
	public Recepcion(Paciente paciente, Empresa empresa, Tarifa tarifa, String sede, int numeroMuestra, String autorizacion, String numeroOrden, Date fechaOrden, int codSubEmpresa, int codEPyP, int codSala, int cama, int  codTipoUsuario, int codAmbito, int codFinalidad, String codMedico, Date fechaTomaMuestra, Date fechaRecepcion, String observaciones) {
		super();
		this.paciente = paciente;
		this.empresa = empresa;
		this.tarifa = tarifa;
		this.sede = sede;
		this.numeroMuestra = numeroMuestra;
		this.autorizacion = autorizacion;
		this.numeroOrden = numeroOrden;
		this.fechaOrden = fechaOrden;
		this.codSubEmpresa = codSubEmpresa;
		this.codEPyP = codEPyP;
		this.codSala = codSala;
		this.cama = cama;
		this.TipoUsuario = codTipoUsuario;
		this.codAmbito = codAmbito;
		this.codFinalidad = codFinalidad;
		this.codMedico = codMedico;
		this.fechaTomaMuestra = fechaTomaMuestra;
		this.fechaRecepcion = fechaRecepcion;
		this.observaciones = observaciones;
	}
	
	public Recepcion(int idRecepcion,Paciente paciente, Empresa empresa, Tarifa tarifa, String sede, int numeroMuestra, String autorizacion, String numeroOrden, Date fechaOrden, int codSubEmpresa, int codEPyP, int codSala, int cama, int  codTipoUsuario, int codAmbito, int codFinalidad, String codMedico, Date fechaTomaMuestra, Date fechaRecepcion, String observaciones,int anulada,String recepcionadoPor) {
		super();
		this.idREcepcion=idRecepcion;
		this.paciente = paciente;
		this.empresa = empresa;
		this.tarifa = tarifa;
		this.sede = sede;
		this.numeroMuestra = numeroMuestra;
		this.autorizacion = autorizacion;
		this.numeroOrden = numeroOrden;
		this.fechaOrden = fechaOrden;
		this.codSubEmpresa = codSubEmpresa;
		this.codEPyP = codEPyP;
		this.codSala = codSala;
		this.cama = cama;
		this.TipoUsuario = codTipoUsuario;
		this.codAmbito = codAmbito;
		this.codFinalidad = codFinalidad;
		this.codMedico = codMedico;
		this.fechaTomaMuestra = fechaTomaMuestra;
		this.fechaRecepcion = fechaRecepcion;
		this.observaciones = observaciones;
		this.anulada=anulada;
		this.recepcionadoPor=recepcionadoPor;
		
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public Tarifa getTarifa() {
		return tarifa;
	}
	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}
	public String getSede() {
		return sede;
	}
	public void setSede(String sede) {
		this.sede = sede;
	}
	public int getNumeroMuestra() {
		return numeroMuestra;
	}
	public void setNumeroMuestra(int numeroMuestra) {
		this.numeroMuestra = numeroMuestra;
	}
	public String getAutorizacion() {
		return autorizacion;
	}
	public void setAutorizacion(String autorizacion) {
		this.autorizacion = autorizacion;
	}
	public String getNumeroOrden() {
		return numeroOrden;
	}
	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}
	public Date getFechaOrden() {
		return fechaOrden;
	}
	public void setFechaOrden(Date fechaOrden) {
		this.fechaOrden = fechaOrden;
	}
	public int getCodSubEmpresa() {
		return codSubEmpresa;
	}
	public void setCodSubEmpresa(int codSubEmpresa) {
		this.codSubEmpresa = codSubEmpresa;
	}
	public int getCodEPyP() {
		return codEPyP;
	}
	public void setCodEPyP(int codEPyP) {
		this.codEPyP = codEPyP;
	}
	public int getCodSala() {
		return codSala;
	}
	public void setCodSala(int codSala) {
		this.codSala = codSala;
	}
	public int getCama() {
		return cama;
	}
	public void setCama(int cama) {
		this.cama = cama;
	}
	public int  getCodTipoUsuario() {
		return TipoUsuario;
	}
	public void setCodTipoUsuario(int  codTipoUsuario) {
		this.TipoUsuario = codTipoUsuario;
	}
	public int getCodAmbito() {
		return codAmbito;
	}
	public void setCodAmbito(int codAmbito) {
		this.codAmbito = codAmbito;
	}
	public int getCodFinalidad() {
		return codFinalidad;
	}
	public void setCodFinalidad(int codFinalidad) {
		this.codFinalidad = codFinalidad;
	}
	public String getCodMedico() {
		return codMedico;
	}
	public void setCodMedico(String codMedico) {
		this.codMedico = codMedico;
	}
	public Date getFechaTomaMuestra() {
		return fechaTomaMuestra;
	}
	public void setFechaTomaMuestra(Date fechaTomaMuestra) {
		this.fechaTomaMuestra = fechaTomaMuestra;
	}
	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}
	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getIdREcepcion() {
		return idREcepcion;
	}

	public void setIdREcepcion(int idREcepcion) {
		this.idREcepcion = idREcepcion;
	}

	public int getTipoUsuario() {
		return TipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		TipoUsuario = tipoUsuario;
	}

	public int getAnulada() {
		return anulada;
	}

	public void setAnulada(int anulada) {
		this.anulada = anulada;
	}

	public String getRecepcionadoPor() {
		return recepcionadoPor;
	}

	public void setRecepcionadoPor(String recepcionadoPor) {
		this.recepcionadoPor = recepcionadoPor;
	}
	
	public String getCodMedicoString() {
		if (codMedico.endsWith("-1")) {
			return "";
		} else {
			return codMedico;
		}

	}
	
	public String getAutorizacionString() {
		if (autorizacion.endsWith("-1")) {
			return "";
		} else {
			return autorizacion;
		}

	}
	
	public String getNumeroOrdenString() {
		if (numeroOrden.endsWith("-1")) {
			return "";
		} else {
			return numeroOrden;
		}

	}
	
	
}
