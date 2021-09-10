package interfaces_Modificadas;


import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;

/**
 *
 * @The Mind 91
 */
public class StringDigitalCellEditorr {
	private DefaultCellEditor DFE;
     public DefaultCellEditor integerEditor;
     SoloNumerosTable doc = new SoloNumerosTable();

     public StringDigitalCellEditorr(){
     //JTextField al cual le pongamos el document.
     final JTextField integerField = new JTextField();
     //Poniendole el document al JTextField que creamos.
     integerField.setDocument(doc);
     integerField.setHorizontalAlignment(JTextField.LEFT);
     
    // insertando el JTextField  en el CellEditor.
     integerEditor =
           new DefaultCellEditor(integerField) {
         
            @Override
       //metodo que se encarga de devolver el valor editado.
        public Object getCellEditorValue() {
        return integerField.getText();
      }
   };
   
   DFE=integerEditor;

    }

	public DefaultCellEditor getDFE() {
		return DFE;
	}

	public void setDFE(DefaultCellEditor dFE) {
		DFE = dFE;
	}
     	
     
}

