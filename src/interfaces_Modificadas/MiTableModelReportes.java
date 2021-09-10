package interfaces_Modificadas;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class MiTableModelReportes extends DefaultTableModel {

	public MiTableModelReportes() {
		super();
		
	}

	public MiTableModelReportes(int rowCount, int columnCount) {
		super(rowCount, columnCount);
		
	}

	public MiTableModelReportes(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
		
	}

	public MiTableModelReportes(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
		
	}

	public MiTableModelReportes(Vector columnNames, int rowCount) {
		super(columnNames, rowCount);
		
	}

	public MiTableModelReportes(Vector data, Vector columnNames) {
		super(data, columnNames);
		
	}

	@Override
	public boolean isCellEditable(int row, int column) {
	if (column==1&&!this.getValueAt(row, 9).equals("2")&&!this.getValueAt(row, 9).equals("1")) {
		return true;
	} else {
return false;
	}
	}
	
		
}
