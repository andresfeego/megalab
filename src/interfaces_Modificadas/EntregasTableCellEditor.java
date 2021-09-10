package interfaces_Modificadas;

import java.awt.Color;
import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import conexion.conexionCombos;

public class EntregasTableCellEditor extends DefaultCellEditor  implements TableCellRenderer   {

	

	private clrCheckBox editor=new clrCheckBox("");
    private boolean value = false; // valor de la celda

    public EntregasTableCellEditor() {
		super(new clrCheckBox(""));
		
	}
//
    
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

		  boolean b = ((Boolean) value).booleanValue();
	        ( (clrCheckBox) editor).setSelected( b );
	         editor.setHorizontalAlignment(JLabel.CENTER);
	         editor.setForeground(Color.BLACK);
	         editor.setBackground(Colores.clrPrincipal);
	        return ( (clrCheckBox) editor);     
			 
		}

	  @Override
	    public Object getCellEditorValue() {
	        return ((clrCheckBox)editor).isSelected();        
	    }

	    /** cuando termina la manipulacion de la celda */
	    @Override
	    public boolean stopCellEditing() {        
	        value = ((Boolean)getCellEditorValue()).booleanValue() ;
	        System.out.println(getCellEditorValue()+"");
	        ((clrCheckBox)editor).setSelected( value );
	         editor.setHorizontalAlignment(JLabel.CENTER);

	        return super.stopCellEditing();
	    }

	    /** retorna componente */
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	         if (value == null)
	            return null;         
	         editor.setForeground(Color.BLACK);
	         editor.setBackground(Colores.clrPrincipal);
	         editor.setHorizontalAlignment(JLabel.CENTER);
	         return ( (clrCheckBox) editor );
	    }
	
	
	
	
	
}
