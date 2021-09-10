package auxiliares;

public class protocolo {

	private int idProtocolo;
	private String codigo;
	private String nombre;
	private int codSeccion;
	private int idPlanilla;
	private int orden;

	public protocolo(int idProtocolo, String codigo, String nombre,
			int codSeccion, int idPlanilla, int orden) {
		super();
		this.idProtocolo = idProtocolo;
		this.nombre = nombre;
		this.codSeccion = codSeccion;
		this.idPlanilla = idPlanilla;
		this.orden = orden;
		this.codigo = codigo;
	}

	public int getIdProtocolo() {
		return idProtocolo;
	}

	public void setIdProtocolo(int idProtocolo) {
		this.idProtocolo = idProtocolo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCodSeccion() {
		return codSeccion;
	}

	public void setCodSeccion(int codSeccion) {
		this.codSeccion = codSeccion;
	}

	public int getIdPlanilla() {
		return idPlanilla;
	}

	public void setIdPlanilla(int idPlanilla) {
		this.idPlanilla = idPlanilla;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
