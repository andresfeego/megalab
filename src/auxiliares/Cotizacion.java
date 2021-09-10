package auxiliares;

import java.util.ArrayList;
import java.util.Date;

public class Cotizacion {
	private int idCotizacion;
	private String cotizadoA;
	private String direccion;
	private String telefono;
	private Date fecha;
	private int codTarifa;
	private int descuento;
	private int totalSinD;
	private int totalConD;
	private String obs;
	ArrayList<itemCotizacion> items;
	
	
	
	public Cotizacion(int idCotizacion, String cotizadoA,String direccion,String telefono, Date fecha, int codTarifa, int descuento, int totalSinD, int totalConD, ArrayList<itemCotizacion> items,String obs) {
		super();
		this.idCotizacion = idCotizacion;
		this.cotizadoA = cotizadoA;
		this.direccion=direccion;
		this.telefono=telefono;
		this.fecha = fecha;
		this.codTarifa = codTarifa;
		this.descuento = descuento;
		this.totalSinD = totalSinD;
		this.totalConD = totalConD;
		this.items = items;
		this.obs=obs;
	}


	public int getIdCotizacion() {
		return idCotizacion;
	}


	public void setIdCotizacion(int idCotizacion) {
		this.idCotizacion = idCotizacion;
	}


	public String getCotizadoA() {
		return cotizadoA;
	}


	public void setCotizadoA(String cotizadoA) {
		this.cotizadoA = cotizadoA;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public int getCodTarifa() {
		return codTarifa;
	}


	public void setCodTarifa(int codTarifa) {
		this.codTarifa = codTarifa;
	}


	public int getDescuento() {
		return descuento;
	}


	public void setDescuento(int descuento) {
		this.descuento = descuento;
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


	public ArrayList<itemCotizacion> getItems() {
		return items;
	}


	public void setItems(ArrayList<itemCotizacion> items) {
		this.items = items;
	}


	public String getObs() {
		return obs;
	}


	public void setObs(String obs) {
		this.obs = obs;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
	
	
	
}
