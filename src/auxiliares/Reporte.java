package auxiliares;

public class Reporte {
	private String ValorResultado;
	private int idItemRecepcion;
	
	
	public Reporte(String valorResultado, int idItemRecepcion) {
		super();
		ValorResultado = valorResultado;
		this.idItemRecepcion = idItemRecepcion;
	}


	public String getValorResultado() {
		return ValorResultado;
	}


	public void setValorResultado(String valorResultado) {
		ValorResultado = valorResultado;
	}


	public int getIdItemRecepcion() {
		return idItemRecepcion;
	}


	public void setIdItemRecepcion(int idItemRecepcion) {
		this.idItemRecepcion = idItemRecepcion;
	}
	
	
}
