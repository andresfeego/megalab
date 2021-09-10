package interfaces_Modificadas;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class MiCheckBoxRender extends clrCheckBox implements TableCellRenderer {
		
	
	
	  private clrCheckBox component = new clrCheckBox("");

	    /** Constructor de clase */
	public MiCheckBoxRender(String label) {
			super(label);
			
		}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

	    if (isSelected) {
	    	component.setForeground(Color.BLACK);
	    	component.setBackground(Colores.clrPrincipal);
	    } else {
	    	component.setForeground(Color.BLACK);
	    	component.setBackground(Colores.clrFondo);
	    }
	    boolean b = ((Boolean) value).booleanValue();
	      ( (clrCheckBox) component).setSelected( b );
	      component.setHorizontalAlignment(JLabel.CENTER);
	      return ( (clrCheckBox) component);
	}

		

	
}
