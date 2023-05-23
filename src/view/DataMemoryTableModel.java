/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package view;

import javax.swing.table.AbstractTableModel;
import model.Data;
import model.DataMemory;

/**
 * Table model for the data memory.
 * 
 * @author Seoungdeok Jeon
 * @version Spring 2020
 */
public class DataMemoryTableModel extends AbstractTableModel {

	private final String[] myColNames = new String[] {"Address", "Value"};
	private DataMemory myDataMemory;

	/**
	 * Set data memory.
	 * 
	 * @param theDataMemory the data memory to be set
	 */
	public void setDataMemory(DataMemory theDataMemory) {
		myDataMemory = theDataMemory;
	}

	@Override
	public String getColumnName(int column) {
		return myColNames[column];
	}

	@Override
	public int getColumnCount() {
		return myColNames.length;
	}

	@Override
	public int getRowCount() {
		return 500;
	}

	@Override
	public Object getValueAt(int row, int col) {
		Data data = myDataMemory.getData(row * 4);
		switch(col) {
		case 0:
			return data.getAddress();
		case 1:
			return data.getBitString();
		}
		return null;
	}

	@Override
	public void setValueAt(Object theValue, int row, int col) {
		String s = (String) theValue;
		Data data = myDataMemory.getData(row * 4);
		if(col == 1) {
			data.setBitStringByDecimal(Integer.parseInt(s));
		}
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		if (col == 0) {
			return false;
		} else {
			return true;	
		}
	}
}