package visor;

import java.io.IOException;

import javax.swing.JDialog;

import Interfaces.Principal;


public class VisorPlanos extends JDialog {


	

	public VisorPlanos(String path) {
		
		Runtime obj = Runtime.getRuntime();
		try {
			obj.exec("notepad "+path);
		} catch (IOException e) {
			Principal.getInstancePrincipal().registrarErrorDB("Error abriendo archivo plano: "+e);
		}
	}
}
