package metodos;

import Interfaces.Principal;

public class CerrarSesionInactivo extends Thread{
	private int mil;
	private Principal pri;
	private CerrarSesionInactivo esta;
	
	public CerrarSesionInactivo(String str, Principal pri,int mil) {
		 super(str);
		 this.mil=mil;
		 this.pri=pri;
		 this.esta=this;
		 
		 }
		
				
		public void run() {

			try {
				sleep(mil);
				System.out.println("ok");
				pri.cerrarSesion();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}

		 }
		
		public void restart(){
			esta=null;
			esta.start();
		}
		
		
		
}
