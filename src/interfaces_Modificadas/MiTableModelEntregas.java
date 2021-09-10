package interfaces_Modificadas;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class MiTableModelEntregas extends DefaultTableModel {

	public MiTableModelEntregas() {
		super();
		
	}

	public MiTableModelEntregas(int rowCount, int columnCount) {
		super(rowCount, columnCount);
		
	}

	public MiTableModelEntregas(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
		
	}

	public MiTableModelEntregas(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
		
	}

	public MiTableModelEntregas(Vector columnNames, int rowCount) {
		super(columnNames, rowCount);
		
	}

	public MiTableModelEntregas(Vector data, Vector columnNames) {
		super(data, columnNames);
		
	}

	@Override
	public boolean isCellEditable(int row, int column) {
	if (column==5) {
		return true;
	} else {
return false;
	}
	}
	
		
}
