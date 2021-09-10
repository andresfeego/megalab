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

import conexion.conexionBusqueda;
import conexion.conexionCombos;

public class clrRenderTablaNoReportar extends DefaultTableCellRenderer {
	private JLabel component;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		component = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);


component.setBackground(Colores.clrFondo);
		
		if (isSelected) {
			component.setOpaque(true);
			component.setBackground(Colores.clrSecundario);
			component.setForeground(Color.BLACK);
			
			if (!table.getValueAt(row, 1).equals("")&&table.getValueAt(row, 9).equals("4")&&column==2) {
				if (Integer.parseInt(table.getValueAt(row, 1)+"")<Integer.parseInt(table.getValueAt(row, 5)+"")) {
					component.setOpaque(true);
					component.setForeground(Color.red);
					component.setText("B");
				}else{
					if (Integer.parseInt(table.getValueAt(row, 1)+"")>Integer.parseInt(table.getValueAt(row, 6)+"")) {
						component.setOpaque(true);
						component.setForeground(Color.red);
						component.setText("A");
					} else {
						component.setOpaque(true);
						component.setForeground(Color.red);
						component.setText("N");
					}
				}
			}
			
				
		} else {
			component.setOpaque(true);
			component.setBackground(Colores.clrFondo);
			component.setForeground(Colores.clrTextoInactivo);
		

			if ((value.toString().equals("no") && column == 12)) {
				component.setOpaque(true);
				component.setBackground(Colores.clrSecundario);
				component.setForeground(Colores.clrTextoInactivo);
			}

			if (table.getValueAt(row, 9).equals("2")) {
				component.setOpaque(true);
				component.setBackground(Colores.clrAlertaCamarada);
				component.setForeground(Color.BLACK);
			}

			if (table.getValueAt(row, 9).equals("1")) {
				component.setOpaque(true);
				component.setBackground(Colores.clrTextoInactivo);
				component.setForeground(Color.BLACK);
			}
			
			if (!table.getValueAt(row, 1).equals("")&&table.getValueAt(row, 9).equals("4")&&column==2) {
				if (Integer.parseInt(table.getValueAt(row, 1)+"")<Integer.parseInt(table.getValueAt(row, 5)+"")) {
					component.setOpaque(true);
					component.setForeground(Color.red);
					component.setText("B");
				}else{
					if (Integer.parseInt(table.getValueAt(row, 1)+"")>Integer.parseInt(table.getValueAt(row, 6)+"")) {
						component.setOpaque(true);
						component.setForeground(Color.red);
						component.setText("A");
					} else {
						component.setOpaque(true);
						component.setForeground(Color.red);
						component.setText("N");
					}
				}
			}
			
		}

		

		return component;
	}
}