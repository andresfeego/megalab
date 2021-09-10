package metodos;

import interfaces_Modificadas.circuloConexion;

import java.awt.Color;

import conexion.conexion;
import conexion.conexionComprobar;
import Interfaces.Principal;
import Interfaces.lbBacteriologoXapellido;
import Interfaces.lbBacteriologoXid;
import Interfaces.lbBacteriologoXnombre;
import Interfaces.lbEmpresaXnit;
import Interfaces.lbEmpresaXrazon;
import Interfaces.lbGrupoEXnombre;
import Interfaces.lbMedicoXApellido;
import Interfaces.lbMedicoXid;
import Interfaces.lbMedicoXnombre;
import Interfaces.lbPacienteXapellido;
import Interfaces.lbPacienteXid;
import Interfaces.lbPacienteXnombre;
import Interfaces.lbProtocoloXcod;
import Interfaces.lbProtocoloXnombre;
import Interfaces.lbSeccionXsigla;
import Interfaces.lbSeccionXnombre;
import Interfaces.lbTDXSigla;
import Interfaces.lbTDXnombre;
import Interfaces.lbUsuarioXnombre;
import Interfaces.lbUsuarioXusuario;

public class ComprobarConexion extends Thread{
	
	private circuloConexion cirConexion;
	
	
	
	
			 	

		public ComprobarConexion( circuloConexion cirConexion) {
		super();
		this.cirConexion = cirConexion;
	}






		public void run() {

			try {
				sleep(500);
				if (!conexionComprobar.comprobarConexion()) {
					cirConexion.setColorBoton(Color.RED);
					cirConexion.setLabel("Sin conexion");
					cirConexion.repaint();
				} else {
					cirConexion.setColorBoton(Color.GREEN);
					cirConexion.setLabel("Conectado con servidor");
					cirConexion.repaint();
				}
				
				conexionComprobar.closeConnection1();
				sleep(4000);
				ComprobarConexion auxi=new ComprobarConexion(cirConexion);
				auxi.start();
				
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}

		 }
		
}
