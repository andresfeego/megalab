package interfaces_Modificadas;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;


public class miRenderTabla extends DefaultTableCellRenderer {
	 
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		   {
		
		StringDigitalCellEditorr SDCE=new StringDigitalCellEditorr();
		table.setCellEditor(SDCE.getDFE());
		      super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
		      if ( !table.isCellEditable(row, column))
		      {
		         this.setOpaque(true);
		         this.setBackground(Colores.clrSecundario);
		         this.setForeground(Color.BLACK);
		      } else {
		          this.setOpaque(true);
			         this.setBackground(Colores.clrFondo);
			         this.setForeground(Color.BLACK);
		      }

		      return this;
		   }
		}