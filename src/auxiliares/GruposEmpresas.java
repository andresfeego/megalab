package auxiliares;

public class GruposEmpresas {
	
	private int idGrupo;
	private String nombreGrupo;
	private String empresa;
	private String tarifa;
	
	
	
	
	
	public GruposEmpresas(int idGrupo, String nombreGrupo, String codEmpresa,
			String codTarifa) {
		super();
		this.idGrupo = idGrupo;
		this.nombreGrupo = nombreGrupo;
		this.empresa = codEmpresa;
		this.tarifa = codTarifa;
	}
	
	
	public int getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}
	public String getNombreGrupo() {
		return nombreGrupo;
	}
	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getTarifa() {
		return tarifa;
	}
	public void setTarifa(String tarifa) {
		this.tarifa = tarifa;
	}
	
	
	

}
