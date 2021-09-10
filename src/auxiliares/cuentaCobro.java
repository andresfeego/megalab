package auxiliares;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

public class cuentaCobro {
	private int idCuenta;
	private Date fechaCuenta;
	private Date inicioRango;
	private Date finRango;
	private String cdoEPS;
	private String idEmpresa;
	private String nombreEmpresa;
	private int codSubgrupo;
	private String numContrato;
	private String Copago;
	private int descuento;
	private int netoPago;
	private int cantServicios;
	private ArrayList<itemCuenta> items;
	private InputStream pdf;
	
	
	public cuentaCobro(int idCuenta, Date fechaCuenta, Date inicioRango, Date finRango, String cdoEPS, String idEmpresa, String nombreEmpresa, int codSubgrupo, String numContrato, String copago, int descuento, int netoPago, int cantServicios, ArrayList<itemCuenta> items, InputStream pdf) {
		super();
		this.idCuenta = idCuenta;
		this.fechaCuenta = fechaCuenta;
		this.inicioRango = inicioRango;
		this.finRango = finRango;
		this.cdoEPS = cdoEPS;
		this.idEmpresa = idEmpresa;
		this.nombreEmpresa = nombreEmpresa;
		this.codSubgrupo = codSubgrupo;
		this.numContrato = numContrato;
		Copago = copago;
		this.descuento = descuento;
		this.netoPago = netoPago;
		this.cantServicios = cantServicios;
		this.items = items;
		this.pdf = pdf;
	}



	public int getIdCuenta() {
		return idCuenta;
	}



	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}



	public Date getFechaCuenta() {
		return fechaCuenta;
	}



	public void setFechaCuenta(Date fechaCuenta) {
		this.fechaCuenta = fechaCuenta;
	}



	public Date getInicioRango() {
		return inicioRango;
	}



	public void setInicioRango(Date inicioRango) {
		this.inicioRango = inicioRango;
	}



	public Date getFinRango() {
		return finRango;
	}



	public void setFinRango(Date finRango) {
		this.finRango = finRango;
	}



	public String getCdoEPS() {
		return cdoEPS;
	}



	public void setCdoEPS(String cdoEPS) {
		this.cdoEPS = cdoEPS;
	}



	public String getNombreEmpresa() {
		return nombreEmpresa;
	}



	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}



	public int getCodSubgrupo() {
		return codSubgrupo;
	}



	public void setCodSubgrupo(int codSubgrupo) {
		this.codSubgrupo = codSubgrupo;
	}



	public String getNumContrato() {
		return numContrato;
	}



	public void setNumContrato(String numContrato) {
		this.numContrato = numContrato;
	}



	public String getCopago() {
		return Copago;
	}



	public void setCopago(String copago) {
		Copago = copago;
	}



	public int getDescuento() {
		return descuento;
	}



	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}



	public int getNetoPago() {
		return netoPago;
	}



	public void setNetoPago(int netoPago) {
		this.netoPago = netoPago;
	}



	public int getCantServicios() {
		return cantServicios;
	}



	public void setCantServicios(int cantServicios) {
		this.cantServicios = cantServicios;
	}



	public InputStream getPdf() {
		return pdf;
	}



	public void setPdf(InputStream pdf) {
		this.pdf = pdf;
	}



	public ArrayList<itemCuenta> getItems() {
		return items;
	}



	public void setItems(ArrayList<itemCuenta> items) {
		this.items = items;
	}



	public String getIdEmpresa() {
		return idEmpresa;
	}



	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	
	
	
	
	
	
	
	
}
