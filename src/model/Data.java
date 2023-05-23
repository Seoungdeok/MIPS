/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package model;

/**
 * Class to represent single data in data memory.
 * 
 * @author Seoungdeok Jeon
 * @version Spring 2020
 */
public class Data {
	
	private int myAddress;
	private BitString myBitString;
	
	/**
	 * Construct Data with its address and bit-string.
	 * 
	 * @param theAddress the address of this Data
	 * @param theBitString the bit-string of this Data
	 */
	public Data(int theAddress, BitString theBitString) {
		myBitString = theBitString;
		myAddress = theAddress;
	}

	/**
	 * Get the address of this Data.
	 * 
	 * @return the address
	 */
	public int getAddress() {
		return myAddress;
	}
	
	/**
	 * Get bit-string of this Data.
	 * 
	 * @return the bit-string
	 */
	public BitString getBitString() {
		return myBitString;
	}
	
	/**
	 * Set myBitString.
	 * 
	 * @param theBitString the bit-string to be set for myBitString
	 */
	public void setBitString(BitString theBitString) {
		myBitString.setBits(theBitString.getBits());
	}
	
	/**
	 * Set myBitString using decimal value.
	 * 
	 * @param theDecimalValue the decimal value to be set for myBitString
	 */
	public void setBitStringByDecimal(int theDecimalValue) {
		myBitString.setValue2sComp(theDecimalValue);
	}

	/**
	 * Show myBitString in decimal if booDecimal is true.
	 * Otherwise, show myBitString in binary.
	 * 
	 * @param booDecimal show in decimal if true; bitString otherwise.
	 */
	public void showInDecimal(boolean isDecimal) {
		myBitString.showInDecimal(isDecimal);
	}
}
