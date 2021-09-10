package auxiliares;

import java.awt.Image;
import java.io.InputStream;

public class encaFirma {
	private String quienFirma1;
	private String quienFirma2;
	private int queReporta;
	private InputStream firmaIS;
	private Image firmaImg;
	private InputStream encaIS;
	private Image encaImg;
	
	
	public encaFirma(String quienFirma1, String quienFirma2, int queReporta, InputStream firmaIS, Image firmaImg, InputStream encaIS, Image encaImg) {
		super();
		this.quienFirma1 = quienFirma1;
		this.quienFirma2 = quienFirma2;
		this.queReporta = queReporta;
		this.firmaIS = firmaIS;
		this.firmaImg = firmaImg;
		this.encaIS = encaIS;
		this.encaImg = encaImg;
	}


	
	
	public encaFirma() {
		super();
	}




	public String getQuienFirma1() {
		return quienFirma1;
	}


	public void setQuienFirma1(String quienFirma1) {
		this.quienFirma1 = quienFirma1;
	}


	public String getQuienFirma2() {
		return quienFirma2;
	}


	public void setQuienFirma2(String quienFirma2) {
		this.quienFirma2 = quienFirma2;
	}


	public int getQueReporta() {
		return queReporta;
	}


	public void setQueReporta(int queReporta) {
		this.queReporta = queReporta;
	}


	public InputStream getFirmaIS() {
		return firmaIS;
	}


	public void setFirmaIS(InputStream firmaIS) {
		this.firmaIS = firmaIS;
	}


	public Image getFirmaImg() {
		return firmaImg;
	}


	public void setFirmaImg(Image firmaImg) {
		this.firmaImg = firmaImg;
	}


	public InputStream getEncaIS() {
		return encaIS;
	}


	public void setEncaIS(InputStream encaIS) {
		this.encaIS = encaIS;
	}


	public Image getEncaImg() {
		return encaImg;
	}


	public void setEncaImg(Image encaImg) {
		this.encaImg = encaImg;
	}
	
	
	
	
	
	
	
	
}
