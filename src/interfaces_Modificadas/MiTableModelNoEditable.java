package interfaces_Modificadas;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class MiTableModelNoEditable extends DefaultTableModel {

	public MiTableModelNoEditable() {
		super();
		
	}

	public MiTableModelNoEditable(int rowCount, int columnCount) {
		super(rowCount, columnCount);
		
	}

	public MiTableModelNoEditable(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
		
	}

	public MiTableModelNoEditable(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
		
	}

	public MiTableModelNoEditable(Vector columnNames, int rowCount) {
		super(columnNames, rowCount);
		
	}

	public MiTableModelNoEditable(Vector data, Vector columnNames) {
		super(data, columnNames);
		
	}

	@Override
	public boolean isCellEditable(int row, int column) {
	return false;
	}
	
		
}
