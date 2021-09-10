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


public class clrRenderTablaExamenesXReportar extends DefaultTableCellRenderer {
	 private JLabel component;
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		   {
			component= (JLabel)   super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
		
		    
		
		
			
		      if (isSelected)
		      {
		    	  component.setOpaque(true);
		    	  component.setBackground(Colores.clrSecundario);
		    	  component.setForeground(Color.BLACK);
		    	 
		      } else {
		    	  if ((value.toString().equals("no")&&column==3)||(value.toString().equals("no")&&column==4)) {
		    		  component.setOpaque(true);
			    	  component.setBackground(Colores.clrSecundario);
			    	  component.setForeground(Color.BLACK);
				}else{
		    	
					 component.setOpaque(true);
			    	  component.setBackground(Colores.clrFondo);
			    	  component.setForeground(Color.BLACK);
				
		    	  }
		    	 
		    	  }
		     

		      return component;
		   }
		}