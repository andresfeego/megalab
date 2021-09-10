package interfaces_Modificadas;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

import conexion.conexionCombos;

public class opExamenesTableCellEditor extends AbstractCellEditor implements TableCellEditor {

	private TableCellEditor editor;

	@Override
	public Object getCellEditorValue() {
		  if (editor != null) {
              return editor.getCellEditorValue();
          }

          return null;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

		
		if (column==2) {
			ClrCuadroDeTexto CT=new ClrCuadroDeTexto(15,true,"");
			editor = new MiTextfieldEditor(CT);
			return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
		} else {
			if (column==3||column==4||column==5) {
				ClrCuadroDeTexto CT=new ClrCuadroDeTexto(2,true,"");
				editor = new MiTextfieldEditor(CT);
				return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
			}else{
				editor = new DefaultCellEditor(new JTextField());
				return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
			}
			
		}
		 
	
	}
	
	
	
	
}
