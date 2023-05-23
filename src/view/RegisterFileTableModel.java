/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package view;

import javax.swing.table.AbstractTableModel;
import model.Register;
import model.RegisterFile;

/**
 * Table model for the register file.
 * 
 * @author Seoungdeok Jeon
 * @version Spring 2020
 */
public class RegisterFileTableModel extends AbstractTableModel {
	
	private final String[] myColNames = new String[] {"Name", "Number", "Value"};
	private RegisterFile myRegisterFile;

	/**
	 * Set register file.
	 * 
	 * @param theRegisterFile the register file to be set
	 */
	public void setRegisterFile(RegisterFile theRegisterFile) {
		myRegisterFile = theRegisterFile;
	}
	
	@Override
	public String getColumnName(int column) {
		return myColNames[column];
	}

	@Override
	public int getColumnCount()
	{
		return myColNames.length;
	}

	@Override
	public int getRowCount()
	{
		return 32;
	}

	@Override
	public Object getValueAt(int row, int col) {
		Register register = myRegisterFile.getRegister(row);
		switch(col) {
		case 0:
			return register.getName();
		case 1:
			return register.getNumber();
		case 2:
			return register.getBitString();
		}
		return null;
	}
 
    @Override
    public void setValueAt(Object theValue, int row, int col) {
    	String s = (String) theValue;
    	Register register = myRegisterFile.getRegister(row);
        if(col == 2) {
        	register.setValueByDecimal(Integer.parseInt(s));
        }
    }
    
	@Override
    public boolean isCellEditable(int row, int col) {
		if (col == 0 || col == 1) {
			return false;
		} else {
	        return true;	
		}
    }
}
