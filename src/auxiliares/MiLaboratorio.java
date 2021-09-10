package auxiliares;

import java.awt.Image;
import java.io.InputStream;

public class MiLaboratorio {
	private String razonSocial;
	private int tipoDoc;
	private String numeroDoc;
	private String codigoIPS;
	private String direccion;
	private String telefono1;
	private String telefono2;
	private String email1;
	private String emai2;
	private Image logoImagen;
	private InputStream logoIS;

	public MiLaboratorio(String razonSocial, int tipoDoc, String numeroDoc, String codigoIPS, String direccion, String telefono1, String telefono2, String email1, String emai2, Image logoImagen, InputStream logoIS) {
		super();
		this.razonSocial = razonSocial;
		this.tipoDoc = tipoDoc;
		this.numeroDoc = numeroDoc;
		this.codigoIPS = codigoIPS;
		this.direccion = direccion;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
		this.email1 = email1;
		this.emai2 = emai2;
		this.logoImagen = logoImagen;
		this.logoIS = logoIS;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public int getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(int tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getNumeroDoc() {
		return numeroDoc;
	}

	public void setNumeroDoc(String numeroDoc) {
		this.numeroDoc = numeroDoc;
	}

	public String getCodigoIPS() {
		return codigoIPS;
	}

	public void setCodigoIPS(String codigoIPS) {
		this.codigoIPS = codigoIPS;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmai2() {
		return emai2;
	}

	public void setEmai2(String emai2) {
		this.emai2 = emai2;
	}

	public Image getLogoImagen() {
		return logoImagen;
	}

	public void setLogoImagen(Image logoImagen) {
		this.logoImagen = logoImagen;
	}

	public InputStream getLogoIS() {
		return logoIS;
	}

	public void setLogoIS(InputStream logoIS) {
		this.logoIS = logoIS;
	}

	public String getTipoDocString(){
		String salida="";		
		switch (tipoDoc) {
		case 1:
			salida="CC";
			break;
			
		case 2:
			salida="CE";
			break;
			
		case 3:
			salida="NI";
			break;
			
		case 4:
			salida="PA";
			break;

		default:
			break;
		}
		
		return salida;
	}
	
}
