package interfaces_Modificadas;

import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class MiTableModelTarifas extends DefaultTableModel {

	public MiTableModelTarifas() {
		super();
		
	}

	public MiTableModelTarifas(int rowCount, int columnCount) {
		super(rowCount, columnCount);
		
	}

	public MiTableModelTarifas(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
		
	}

	public MiTableModelTarifas(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
		
	}

	public MiTableModelTarifas(Vector columnNames, int rowCount) {
		super(columnNames, rowCount);
		
	}

	public MiTableModelTarifas(Vector data, Vector columnNames) {
		super(data, columnNames);
		
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		if (column==0||column==1) {
			return false;
		}
			return true;
	}
	
	public void escuchar(){
		this.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent arg0) {
				System.out.println("cambi cambio");
				
			}
		});
	}
		
}
