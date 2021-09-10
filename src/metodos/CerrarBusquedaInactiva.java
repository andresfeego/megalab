package metodos;

import Interfaces.Principal;
import Interfaces.lbBacteriologoXapellido;
import Interfaces.lbBacteriologoXid;
import Interfaces.lbBacteriologoXnombre;
import Interfaces.lbCiudadXNombre;
import Interfaces.lbCuentaXId;
import Interfaces.lbEmpresaXcod;
import Interfaces.lbEmpresaXnit;
import Interfaces.lbEmpresaXrazon;
import Interfaces.lbEspecialidadXSigla;
import Interfaces.lbEspecialidadXnombre;
import Interfaces.lbExamenXcups;
import Interfaces.lbExamenXid;
import Interfaces.lbExamenXnombre;
import Interfaces.lbGrupoEXnombre;
import Interfaces.lbLaboratorioXnit;
import Interfaces.lbLaboratorioXrazon;
import Interfaces.lbMedicoXApellido;
import Interfaces.lbMedicoXid;
import Interfaces.lbMedicoXnombre;
import Interfaces.lbPacienteXapellido;
import Interfaces.lbPacienteXid;
import Interfaces.lbPacienteXnombre;
import Interfaces.lbParentescoXNombre;
import Interfaces.lbParentescoXid;
import Interfaces.lbPerfilesXcodigo;
import Interfaces.lbPerfilesXnombre;
import Interfaces.lbPlanesPYPXid;
import Interfaces.lbPlanesPYPXnombre;
import Interfaces.lbProtocoloXcod;
import Interfaces.lbProtocoloXnombre;
import Interfaces.lbRecepcionXId;
import Interfaces.lbSalaXSigla;
import Interfaces.lbSalaXnombre;
import Interfaces.lbSeccionXsigla;
import Interfaces.lbSeccionXnombre;
import Interfaces.lbTDXSigla;
import Interfaces.lbTDXnombre;
import Interfaces.lbTMXNombre;
import Interfaces.lbTMXSigla;
import Interfaces.lbTarifaXnombre;
import Interfaces.lbUsuarioWebXid;
import Interfaces.lbUsuarioXnombre;
import Interfaces.lbUsuarioXusuario;
import Interfaces.lbfacturaXID;

public class CerrarBusquedaInactiva extends Thread{
	private Principal pri;
	private int tiempo;
	
	private lbUsuarioXusuario lb;
	private lbUsuarioXnombre lb1;
	private lbPacienteXid lb2;
	private lbPacienteXnombre lb3;
	private lbPacienteXapellido lb4;
	private lbBacteriologoXid lb5;
	private lbBacteriologoXnombre lb6;
	private lbBacteriologoXapellido lb7;
	private lbMedicoXid lb8;
	private lbMedicoXnombre lb9;
	private lbMedicoXApellido lb10;
	private lbEmpresaXnit lb11;
	private lbEmpresaXrazon lb12;
	private lbGrupoEXnombre lb13;
	private lbTDXnombre lb14;
	private lbTDXSigla lb15;
	private lbSeccionXnombre lb16;
	private lbSeccionXsigla lb17;
	private lbProtocoloXcod lb18;
	private lbProtocoloXnombre lb19;
	private lbExamenXid lb20;
	private lbExamenXnombre lb21;
	private lbExamenXcups lb22;
	private lbEmpresaXcod lb23;
	private lbPerfilesXcodigo lb24;
	private lbPerfilesXnombre lb241;
	private lbRecepcionXId lb25;
	private lbTMXSigla lb26;
	private lbTMXNombre lb27;
	private lbCiudadXNombre lb28;
	private lbSalaXSigla lb29;
	private lbSalaXnombre lb30;
	private lbParentescoXNombre lb31;
	private lbParentescoXid lb32;
	private lbPlanesPYPXid lb33;
	private lbPlanesPYPXnombre lb34;
	private lbEspecialidadXSigla lb35;
	private lbEspecialidadXnombre lb36;
	private lbLaboratorioXnit lb37;
	private lbLaboratorioXrazon lb38;
	private lbTarifaXnombre lb39;
	private lbfacturaXID lb40;
	private lbCuentaXId lb41;
	private lbUsuarioWebXid lb42;
	

	
	public CerrarBusquedaInactiva(String str, lbUsuarioXusuario listaBus,int duracion) {
		 super(str);
			this.tiempo=duracion;

		 this.pri=pri;
		 this.lb=listaBus;
		 
		 
		 }
		
				
		public CerrarBusquedaInactiva(String str, lbUsuarioXnombre listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb1=listaBus;
	}

		public CerrarBusquedaInactiva(String str, lbPacienteXid listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb2=listaBus;
			 
			 
			 }
		
		public CerrarBusquedaInactiva(String str, lbPacienteXnombre listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb3=listaBus;
			 
			 
			 }
		
		
		public CerrarBusquedaInactiva(String str, lbPacienteXapellido listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb4=listaBus;
			 
			 
			 }
		
		public CerrarBusquedaInactiva(String str, lbBacteriologoXid listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb5=listaBus;
			 
			 
			 }
		
		public CerrarBusquedaInactiva(String str, lbBacteriologoXnombre listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb6=listaBus;
			 
			 
			 }

		public CerrarBusquedaInactiva(String str, lbBacteriologoXapellido listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb7=listaBus;
			 			 
			 }
		
		public CerrarBusquedaInactiva(String str, lbMedicoXid listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb8=listaBus;
			 		 
			 }
		
		public CerrarBusquedaInactiva(String str, lbMedicoXnombre listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb9=listaBus;
			 		 
			 }
		
		public CerrarBusquedaInactiva(String str, lbMedicoXApellido listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb10=listaBus;
			 		 
			 }
		
		public CerrarBusquedaInactiva(String str, lbEmpresaXnit listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb11=listaBus;
			 		 
			 }
		
		public CerrarBusquedaInactiva(String str, lbEmpresaXrazon listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb12=listaBus;
			 		 
			 }
		
		public CerrarBusquedaInactiva(String str, lbGrupoEXnombre listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb13=listaBus;
			 		 
			 }
		
		public CerrarBusquedaInactiva(String str, lbTDXnombre listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb14=listaBus;
			 		 
			 }
		
		public CerrarBusquedaInactiva(String str, lbTDXSigla listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb15=listaBus;
			 		 
			 }
		
		public CerrarBusquedaInactiva(String str, lbSeccionXnombre listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb16=listaBus;
			 		 
			 }
		
		public CerrarBusquedaInactiva(String str, lbSeccionXsigla listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb17=listaBus;
			 		 
			 }
		
		public CerrarBusquedaInactiva(String str, lbProtocoloXcod listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb18=listaBus;
			 }
		
		public CerrarBusquedaInactiva(String str, lbProtocoloXnombre listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb19=listaBus;
			 
		}
		
		public CerrarBusquedaInactiva(String str, lbExamenXid listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb20=listaBus;
			 
		}
		
		public CerrarBusquedaInactiva(String str, lbExamenXnombre listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb21=listaBus;
			 
		}
		
		public CerrarBusquedaInactiva(String str, lbExamenXcups listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb22=listaBus;
			 
		}
		
		public CerrarBusquedaInactiva(String str, lbEmpresaXcod listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb23=listaBus;
			 
		}
		
		public CerrarBusquedaInactiva(String str, lbPerfilesXcodigo listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb24=listaBus;
			 
		}
		
		public CerrarBusquedaInactiva(String str, lbRecepcionXId listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb25=listaBus;
			 
		}
			 
		public CerrarBusquedaInactiva(String str, lbTMXSigla listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb26=listaBus;
			 
		}
		
		public CerrarBusquedaInactiva(String str, lbTMXNombre listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb27=listaBus;
			 
		}
		
		
		public CerrarBusquedaInactiva(String str, lbCiudadXNombre listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb28=listaBus;
			 
		}
		
		public CerrarBusquedaInactiva(String str, lbPerfilesXnombre listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb241=listaBus;
			 
		}
		
		public CerrarBusquedaInactiva(String str, lbSalaXSigla listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb29=listaBus;
			 
		}
		
		public CerrarBusquedaInactiva(String str, lbSalaXnombre listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb30=listaBus;
			 
		}
		
		public CerrarBusquedaInactiva(String str, lbParentescoXNombre listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb31=listaBus;
			 
		}
		
		public CerrarBusquedaInactiva(String str, lbParentescoXid listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb32=listaBus;
			 
		}
		
		public CerrarBusquedaInactiva(String str, lbPlanesPYPXid listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb33=listaBus;
			 
		}
		
		public CerrarBusquedaInactiva(String str, lbPlanesPYPXnombre listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb34=listaBus;
			 
		}
		
		public CerrarBusquedaInactiva(String str, lbEspecialidadXSigla listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb35=listaBus;
			 
		}
		
		public CerrarBusquedaInactiva(String str, lbEspecialidadXnombre listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb36=listaBus;
			 
		}
		
		public CerrarBusquedaInactiva(String str, lbLaboratorioXnit  listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb37=listaBus;
			 
		}
		
		public CerrarBusquedaInactiva(String str, lbLaboratorioXrazon listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb38=listaBus;
			 
		}
		

		public CerrarBusquedaInactiva(String str, lbTarifaXnombre listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb39=listaBus;
			 
		}
		
		public CerrarBusquedaInactiva(String str, lbfacturaXID listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb40=listaBus;
			 
		}
		
		
		public CerrarBusquedaInactiva(String str, lbCuentaXId listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb41=listaBus;
			 
		}
		
		public CerrarBusquedaInactiva(String str, lbUsuarioWebXid listaBus,int duracion) {
			 super(str);
				this.tiempo=duracion;
			 this.pri=pri;
			 this.lb42=listaBus;
			 
		}
		

		
		public void run() {

			try {
				sleep(tiempo);
				if(lb!=null)lb.cerrarBusqueda();
				if(lb1!=null)lb1.cerrarBusqueda();
				if(lb2!=null)lb2.cerrarBusqueda();
				if(lb3!=null)lb3.cerrarBusqueda();
				if(lb4!=null)lb4.cerrarBusqueda();
				if(lb5!=null)lb5.cerrarBusqueda();
				if(lb6!=null)lb6.cerrarBusqueda();
				if(lb7!=null)lb7.cerrarBusqueda();
				if(lb8!=null)lb8.cerrarBusqueda();
				if(lb9!=null)lb9.cerrarBusqueda();
				if(lb10!=null)lb10.cerrarBusqueda();
				if(lb11!=null)lb11.cerrarBusqueda();
				if(lb12!=null)lb12.cerrarBusqueda();
				if(lb14!=null)lb14.cerrarBusqueda();
				if(lb15!=null)lb15.cerrarBusqueda();
				if(lb16!=null)lb16.cerrarBusqueda();
				if(lb17!=null)lb17.cerrarBusqueda();
				if(lb18!=null)lb18.cerrarBusqueda();
				if(lb19!=null)lb19.cerrarBusqueda();
				if(lb20!=null)lb20.cerrarBusqueda();
				if(lb21!=null)lb21.cerrarBusqueda();
				if(lb22!=null)lb22.cerrarBusqueda();
				if(lb23!=null)lb23.cerrarBusqueda();
				if(lb24!=null)lb24.cerrarBusqueda();
				if(lb241!=null)lb241.cerrarBusqueda();
				if(lb25!=null)lb25.cerrarBusqueda();
				if(lb26!=null)lb26.cerrarBusqueda();
				if(lb27!=null)lb27.cerrarBusqueda();
				if(lb28!=null)lb28.cerrarBusqueda();
				if(lb29!=null)lb29.cerrarBusqueda();
				if(lb30!=null)lb30.cerrarBusqueda();
				if(lb31!=null)lb31.cerrarBusqueda();
				if(lb32!=null)lb32.cerrarBusqueda();
				if(lb33!=null)lb33.cerrarBusqueda();
				if(lb34!=null)lb34.cerrarBusqueda();
				if(lb35!=null)lb35.cerrarBusqueda();
				if(lb36!=null)lb36.cerrarBusqueda();
				if(lb37!=null)lb37.cerrarBusqueda();
				if(lb38!=null)lb38.cerrarBusqueda();
				if(lb39!=null)lb39.cerrarBusqueda();
				if(lb40!=null)lb40.cerrarBusqueda();
				if(lb41!=null)lb41.cerrarBusqueda();
				if(lb42!=null)lb42.cerrarBusqueda();

			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}

		 }
		
}
