package interfaces_Modificadas;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import com.sun.org.apache.bcel.internal.generic.CALOAD;

import conexion.conexionBusqueda;
import conexion.conexionCombos;

public class clrRenderTablaAbonosXPaciente extends DefaultTableCellRenderer {
	private JLabel component;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		component = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		

		if (isSelected) {
			component.setOpaque(true);
			component.setBackground(Colores.clrSecundario);
			component.setForeground(Color.BLACK);
		
			if (column==6&&!table.getValueAt(row, 6).equals("Particular")) {
				component.setOpaque(true);
				component.setBackground(Colores.clrAlertaCamarada);
				component.setForeground(Color.BLACK);
			}
			
					
		} else {
			component.setOpaque(true);
			component.setBackground(Colores.clrFondo);
			component.setForeground(Color.BLACK);
		
			if (column==6&&!table.getValueAt(row, 6).equals("Particular")) {
				component.setOpaque(true);
				component.setBackground(Colores.clrAlertaCamarada);
				component.setForeground(Color.BLACK);
			}
			
					
		}

		

		return component;
	}
}