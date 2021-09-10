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

public class clrRenderTablaReportar extends DefaultTableCellRenderer {
	private JLabel component;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		component = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);


		if (isSelected) {
			component.setOpaque(true);
			component.setBackground(Colores.clrSecundario);
			component.setForeground(Color.BLACK);
			
			if (!table.getValueAt(row, 1).equals("")&&table.getValueAt(row, 9).equals("4")&&column==2) {
				if (Double.parseDouble(table.getValueAt(row, 1)+"")<Double.parseDouble(table.getValueAt(row, 5)+"")) {
					component.setOpaque(true);
					component.setBackground(Colores.clrFondo);
					component.setForeground(Color.red);
					component.setText("B");
				}else{
					if (Double.parseDouble(table.getValueAt(row, 1)+"")>Double.parseDouble(table.getValueAt(row, 6)+"")) {
						component.setOpaque(true);
						component.setBackground(Colores.clrFondo);
						component.setForeground(Color.red);
						component.setText("A");
					} else {
						component.setOpaque(true);
						component.setBackground(Colores.clrFondo);
						component.setForeground(Color.red);
						component.setText("N");
					}
				}
			}
			
				
		} else {
			component.setOpaque(true);
			component.setBackground(Colores.clrFondo);
			component.setForeground(Color.BLACK);
		

			if ((value.toString().equals("no") && column == 12)) {
				component.setOpaque(true);
				component.setBackground(Colores.clrSecundario);
				component.setForeground(Color.BLACK);
			}

			if (table.getValueAt(row, 9).equals("2")) {
				component.setOpaque(true);
				component.setBackground(Colores.clrAlertaCamarada);
				component.setForeground(Color.BLACK);
			}
			
		

			if (table.getValueAt(row, 9).equals("45")) {
				component.setOpaque(true);
				component.setBackground(Colores.clrFondoInactivo);
				component.setForeground(Color.BLACK);
			}
			

			
			if (!table.getValueAt(row, 1).equals("")&&table.getValueAt(row, 9).equals("4")&&column==2) {
				if (Double.parseDouble(table.getValueAt(row, 1)+"")<Double.parseDouble(table.getValueAt(row, 5)+"")) {
					component.setOpaque(true);
					component.setBackground(Colores.clrFondo);
					component.setForeground(Color.red);
					component.setText("B");
				}else{
					if (Double.parseDouble(table.getValueAt(row, 1)+"")>Double.parseDouble(table.getValueAt(row, 6)+"")) {
						component.setOpaque(true);
						component.setBackground(Colores.clrFondo);
						component.setForeground(Color.red);
						component.setText("A");
					} else {
						component.setOpaque(true);
						component.setBackground(Colores.clrFondo);
						component.setForeground(Color.red);
						component.setText("N");
					}
				}
			}
			
		}

		

		return component;
	}
}