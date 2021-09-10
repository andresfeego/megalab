package interfaces_Modificadas;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

import conexion.conexionCombos;

public class ReportesTableCellEditor extends AbstractCellEditor implements TableCellEditor {

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

		if(!table.getValueAt(row, 9).equals("45")&&!table.getValueAt(row, 9).equals("1")&&!table.getValueAt(row, 9).equals("2")&&!table.getValueAt(row, 9).equals("3")&&column==1){
			editor = new MiComboboxEditor( new clrComboBox(conexionCombos.getInstance().listaOPTDvectorXid(table.getValueAt(row, 9)+""),0));
			return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
		}else{		
			if (table.getValueAt(row, 9).equals("3")) {
				ClrCuadroDeTexto CT=new ClrCuadroDeTexto(100,false,"");
				CT.soloNumeros();
				editor = new MiTextfieldEditor(CT);
				return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
			} else {
				if (table.getValueAt(row, 9).equals("1")) {
					ClrCuadroDeTexto CT=new ClrCuadroDeTexto(100,false,"");
					editor = new MiTextfieldEditor(CT);
					return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
				} else {
				editor = new DefaultCellEditor(new JTextField());
				return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
				}
			}
			 
		}
	}
	
	
	
	
}
