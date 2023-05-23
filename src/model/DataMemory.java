/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package model;

/**
 * Class to represent data memory unit.
 * 
 * @author Seoungdeok Jeon
 * @version Spring 2020
 */
public class DataMemory {

	private final static int MAX_DATA_MEMORY = 500;
	private Data[] myDataMemory;
	
	/**
	 * Construct data memory unit which can 
	 * hold up to 500 pieces of data.
	 */
	public DataMemory() {
		myDataMemory = new Data[MAX_DATA_MEMORY];
		for(int i = 0; i < MAX_DATA_MEMORY; i++) {
			BitString binaryValue = new BitString(32);
			binaryValue.setValue(0);
			Data data = new Data(i * 4, binaryValue);
			myDataMemory[i] = data;
		}
	}
	
	/**
	 * Load data to the data memory.
	 * 
	 * @param theData the array of data to be loaded in the data memory
	 */
	public void loadData(Data[] theData) {
		for(int i = 0; i < theData.length; i++) {
			myDataMemory[i] = theData[i];
		}
	}
	
	/**
	 * Get data memory.
	 * 
	 * @return array of data
	 */
	public Data[] getDataMemory() {
		return myDataMemory;
	}
	
	/**
	 * Get data based on its address.
	 * 
	 * @param theMemAddress the memory address of the data
	 * @return data corresponding to theMemAddress
	 */
	public Data getData(int theMemAddress) {
		return myDataMemory[theMemAddress/4];
	}
	
	/**
	 * Set data with memory address and bit-string.
	 * 
	 * @param theMemAddress the memory address of the data
	 * @param theBitString the bit-string to be set for data
	 */
	public void setData(int theMemAddress, BitString theBitString) {
		myDataMemory[theMemAddress/4].setBitString(theBitString);
	}
	
	/**
	 * Clear the array of data by setting elements to 0.
	 */
	public void clear() {
		for(int i = 0; i < MAX_DATA_MEMORY; i++) {
			BitString binaryValue = new BitString(32);
			binaryValue.setValue(0);
			Data data = new Data(i * 4, binaryValue);
			myDataMemory[i] = data;
		}
	}
}
