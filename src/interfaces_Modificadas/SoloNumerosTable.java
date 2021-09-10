package interfaces_Modificadas;

import javax.swing.text.*;
import java.awt.event.*;

public class SoloNumerosTable extends PlainDocument{



public void insertString(int arg0, String arg1, AttributeSet arg2) throws     BadLocationException{

	 if(esCifra(arg1))
	     super.insertString(arg0, arg1, arg2);

	 }

	 public boolean esCifra(String text){
	      char caracter = text.charAt(0);
	     if(((caracter < '0') || (caracter > '9')) && caracter!=KeyEvent.VK_BACK_SPACE){
                  return false;
          }

			 else
			    return true;

	 }

}

