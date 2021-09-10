package auxiliares;

public class Tarifa {
	
	private int idTarifa;
	private String descripcion;
	private int recalculo;
	private int protegido;
	
	
	public Tarifa(int idTarifa, String descripcion, int recalculo, int protegido) {
		super();
		this.idTarifa = idTarifa;
		this.descripcion = descripcion;
		this.recalculo = recalculo;
		this.protegido = protegido;
	}
	
	
	public int getIdTarifa() {
		return idTarifa;
	}
	public void setIdTarifa(int idTarifa) {
		this.idTarifa = idTarifa;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getRecalculo() {
		return recalculo;
	}
	public void setRecalculo(int recalculo) {
		this.recalculo = recalculo;
	}
	public int getProtegido() {
		return protegido;
	}
	public void setProtegido(int protegido) {
		this.protegido = protegido;
	}
	
	
}
