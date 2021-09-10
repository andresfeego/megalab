package auxiliares;

import java.awt.Color;

public class Sede {
	private String ciudad;
	private String nombre;
	private String sede;
	private Color principal;
	private Color secundario;
	private Color alertas;
	private Color iconosNegros;
	
	private String Sprincipal;
	private String Ssecundario;
	private String Salertas;
	private String SiconosNegros;
	
	public Sede(String ciudad, String nombre, String sede, Color principal, Color secundario, Color alertas, Color iconosNegros) {
		super();
		this.ciudad = ciudad;
		this.nombre = nombre;
		this.sede = sede;
		this.principal = principal;
		this.secundario = secundario;
		this.alertas = alertas;
		this.iconosNegros = iconosNegros;
	}

	
	
	public Sede(String ciudad, String nombre, String sede, String sprincipal, String ssecundario, String salertas, String siconosNegros) {
		super();
		this.ciudad = ciudad;
		this.nombre = nombre;
		this.sede = sede;
		Sprincipal = sprincipal;
		Ssecundario = ssecundario;
		Salertas = salertas;
		SiconosNegros = siconosNegros;
	}



	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public Color getPrincipal() {
		return principal;
	}

	public void setPrincipal(Color principal) {
		this.principal = principal;
	}

	public Color getSecundario() {
		return secundario;
	}

	public void setSecundario(Color secundario) {
		this.secundario = secundario;
	}

	public Color getAlertas() {
		return alertas;
	}

	public void setAlertas(Color alertas) {
		this.alertas = alertas;
	}

	public Color getIconosNegros() {
		return iconosNegros;
	}

	public void setIconosNegros(Color iconosNegros) {
		this.iconosNegros = iconosNegros;
	}



	public String getSprincipal() {
		return Sprincipal;
	}



	public void setSprincipal(String sprincipal) {
		Sprincipal = sprincipal;
	}



	public String getSsecundario() {
		return Ssecundario;
	}



	public void setSsecundario(String ssecundario) {
		Ssecundario = ssecundario;
	}



	public String getSalertas() {
		return Salertas;
	}



	public void setSalertas(String salertas) {
		Salertas = salertas;
	}



	public String getSiconosNegros() {
		return SiconosNegros;
	}



	public void setSiconosNegros(String siconosNegros) {
		SiconosNegros = siconosNegros;
	}
	
	
	
	
}
