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


public class clrRenderTablaItemRecepcion extends DefaultTableCellRenderer {
	 
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		   {
		
		StringDigitalCellEditorr SDCE=new StringDigitalCellEditorr();
		      if ( table.getValueAt(row, 9).toString().equals("no"))
		      {
		         this.setOpaque(true);
		         this.setBackground(Colores.clrTextoInactivo);
		         this.setForeground(Color.BLACK);
		      } else {
		    	  if ( table.getValueAt(row,9).toString().equals("si")) {
		    		  this.setOpaque(true);
				         this.setBackground(Colores.clrFondo);
				         this.setForeground(Color.BLACK);
				         
				         if (value.equals("si")&&column==6){
				    		  this.setOpaque(true);
						         this.setBackground(Colores.clrSecundario);
						         this.setForeground(Colores.clrTransparente);
				    	  }
				    	  
				    	  if (value.equals("si")&&column==7){
				    		  this.setOpaque(true);
						         this.setBackground(Colores.clrSecundario);
						         this.setForeground(Colores.clrTransparente);
				    	  }
				    	  
				    	  if (value.equals("si")&&column==8){
				    		  this.setOpaque(true);
						         this.setBackground(Colores.clrSecundario);
						         this.setForeground(Colores.clrTransparente);
				    	  }
				} else {
					 this.setOpaque(true);
			         this.setBackground(Colores.clrAlertaCamarada);
			         this.setForeground(Color.BLACK);
				}
		    	  
		    	
		         
		      }
		      super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);

		      return this;
		   }
		}