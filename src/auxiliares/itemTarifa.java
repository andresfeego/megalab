package auxiliares;

public class itemTarifa {
	private int idItemTarifa;
		private int codExamen;
		private int codTarifa;
		private int valor;
		private int recargaUrgencias;
		private int recargaFestivos;
		private int recargaEspecial;
		
		
		
	
		
		
		
		public itemTarifa(int idItemTarifa, int codExamen, int codTarifa, int valor, int recargaUrgencias, int recargaFestivos, int recargaEspecial) {
			super();
			this.idItemTarifa = idItemTarifa;
			this.codExamen = codExamen;
			this.codTarifa = codTarifa;
			this.valor = valor;
			this.recargaUrgencias = recargaUrgencias;
			this.recargaFestivos = recargaFestivos;
			this.recargaEspecial = recargaEspecial;
		}
		public int getIdItemTarifa() {
			return idItemTarifa;
		}
		public void setIdItemTarifa(int idItemTarifa) {
			this.idItemTarifa = idItemTarifa;
		}
		public int getCodExamen() {
			return codExamen;
		}
		public void setCodExamen(int codExamen) {
			this.codExamen = codExamen;
		}
		public int getCodTarifa() {
			return codTarifa;
		}
		public void setCodTarifa(int codTarifa) {
			this.codTarifa = codTarifa;
		}
		public int getValor() {
			return valor;
		}
		public void setValor(int valor) {
			this.valor = valor;
		}
		public int getRecargaUrgencias() {
			return recargaUrgencias;
		}
		public void setRecargaUrgencias(int recargaUrgencias) {
			this.recargaUrgencias = recargaUrgencias;
		}
		public int getRecargaFestivos() {
			return recargaFestivos;
		}
		public void setRecargaFestivos(int recargaFestivos) {
			this.recargaFestivos = recargaFestivos;
		}
		public int getRecargaEspecial() {
			return recargaEspecial;
		}
		public void setRecargaEspecial(int recargaEspecial) {
			this.recargaEspecial = recargaEspecial;
		}
		@Override
		public String toString() {
			StringBuffer SF=new StringBuffer();
			
			SF.append("codtarifa = "+codExamen+"codtarifa = "+codTarifa);
			
			return SF.toString();
		}
		
		
		
		
}
