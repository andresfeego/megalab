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

import conexion.conexionBusqueda;
import conexion.conexionCombos;

public class clrRenderTablaProtocolos extends DefaultTableCellRenderer {
	private JLabel component;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		component = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);


		if (isSelected) {
			component.setOpaque(true);
			component.setBackground(Colores.clrSecundario);
			component.setForeground(Color.BLACK);
	
			
				
		} else {
			component.setOpaque(true);
			component.setBackground(Colores.clrFondo);
			component.setForeground(Color.BLACK);
	

			if (table.getValueAt(row,1).equals("-")) {
				component.setOpaque(true);
				component.setBackground(new Color(210,211,213));
				component.setForeground(Color.BLACK);
			}
			
		

			if (table.getValueAt(row, 1).equals("•")) {
				component.setOpaque(true);
				component.setBackground(Colores.clrSecundario);
				component.setForeground(Color.BLACK);
			}
			

			
		}

		

		return component;
	}
}