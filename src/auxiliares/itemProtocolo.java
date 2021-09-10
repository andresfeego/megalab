package auxiliares;

public class itemProtocolo {
	
	private int idItemProtocolo;
	private int codProtocolo;
	private String nombre;
	private int codTD;
	private String unidad;
	private String abreviatura;
	private String valor;
	private double desde;
	private double hasta;
	private int genero;
	private int orden;
	private String interpreta;
	
	
	
	public itemProtocolo(int idItemProtocolo, int codProtocolo, String nombre,
			int codTD, String unidad, String abreviatura, String valor, double desde,
			double hasta, int genero,int orden,String interpreta) {
		super();
		this.idItemProtocolo = idItemProtocolo;
		this.codProtocolo = codProtocolo;
		this.nombre = nombre;
		this.codTD = codTD;
		this.unidad = unidad;
		this.abreviatura = abreviatura;
		this.valor = valor;
		this.desde = desde;
		this.hasta = hasta;
		this.genero = genero;
		this.orden=orden;
		this.interpreta=interpreta;
	}


	public int getIdItemProtocolo() {
		return idItemProtocolo;
	}


	public void setIdItemProtocolo(int idItemProtocolo) {
		this.idItemProtocolo = idItemProtocolo;
	}


	public int getCodProtocolo() {
		return codProtocolo;
	}


	public void setCodProtocolo(int codProtocolo) {
		this.codProtocolo = codProtocolo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getCodTD() {
		return codTD;
	}


	public void setCodTD(int codTD) {
		this.codTD = codTD;
	}


	public String getUnidad() {
		return unidad;
	}


	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}


	public String getAbreviatura() {
		return abreviatura;
	}


	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}


	public String getValor() {
		return valor;
	}


	public void setValor(String valor) {
		this.valor = valor;
	}


	public double getDesde() {
		return desde;
	}


	public void setDesde(double desde) {
		this.desde = desde;
	}


	public double getHasta() {
		return hasta;
	}


	public void setHasta(double hasta) {
		this.hasta = hasta;
	}


	public int getGenero() {
		return genero;
	}


	public void setGenero(int genero) {
		this.genero = genero;
	}
	
	


	public int getOrden() {
		return orden;
	}


	public void setOrden(int orden) {
		this.orden = orden;
	}
	
	


	public String getInterpreta() {
		return interpreta;
	}


	public void setInterpreta(String interpreta) {
		this.interpreta = interpreta;
	}


	@Override
	public String toString() {
		
		StringBuffer SB=new StringBuffer();
		SB.append("item---> ");
		SB.append("id: "+idItemProtocolo);
		SB.append("codProtocolo: "+codProtocolo);
		SB.append("nombre: "+nombre);
		SB.append("codTD: "+codTD);
		SB.append("unidad: "+unidad);
		SB.append("abrevi: "+abreviatura);
		SB.append("valor: "+valor);
		SB.append("desde: "+desde);
		SB.append("hasta: "+hasta);
		SB.append("genero: "+genero);
		SB.append("orden: "+orden);
		SB.append("\n");
		
		
		return SB.toString();
	}
	
	
	

}
