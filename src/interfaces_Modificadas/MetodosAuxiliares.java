package interfaces_Modificadas;

import java.util.Enumeration;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

public class MetodosAuxiliares {
	
	
	public static clrRadioButton SeleccionButtonGroup (ButtonGroup group){
		
		
		        for (Enumeration e=group.getElements(); e.hasMoreElements(); ) 
		        {
		            clrRadioButton b = (clrRadioButton)e.nextElement();
		            if (b.getModel() == group.getSelection()) 
		            {
		                return b;
		            }
		        }

		        return null;
	
		
	}

}
