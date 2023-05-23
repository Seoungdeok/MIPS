/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package model;

/**
 * Class to represent single register in register file.
 * 
 * @author Seoungdeok Jeon
 * @version Spring 2020
 */
public class Register {
	
	private String myName;
	private int myNumber;
	private BitString myBitString;
	
	/**
	 * Construct a register with its name, number, and bit-string.
	 * 
	 * @param theName the name of the Register
	 * @param theNumber the number of the Register
	 * @param theBitString the bit-string of the Register
	 */
	public Register(String theName, int theNumber, BitString theBitString) {
		myName = theName;
		myNumber = theNumber;
		myBitString = theBitString;
	}
	
	/**
	 * Get name of this Register.
	 * 
	 * @return the name
	 */
	public String getName() {
		return myName;
	}
	
	/**
	 * Get the number of this Register.
	 * 
	 * @return the number
	 */
	public int getNumber() {
		return myNumber;
	}
	
	/**
	 * Get bit-string of this Register.
	 * 
	 * @return the bit-string
	 */
	public BitString getBitString() {
		return myBitString;
	}
	
	/**
	 * Get unsigned decimal value of myBitString.
	 * 
	 * @return the unsigned decimal value of myBitString
	 */
	public int getValue() {
		return myBitString.getValue();
	}
	
	/**
	 * Get signed decimal value of myBitString.
	 * 
	 * @return the signed decimal value of myBitString
	 */
	public int getValue2sComp() {
		return myBitString.getValue2sComp();
	}
	
	/**
	 * Set myBitString.
	 * 
	 * @param theBitString the bit-string to be set for myBitString
	 */
	public void setValue(BitString theBitString) {
		myBitString.setBits(theBitString.getBits());
	}
	
	/**
	 * Set myBitString using decimal value.
	 * 
	 * @param theDecimalValue the decimal value to be set for myBitString
	 */
	public void setValueByDecimal(int theDecimalValue) {
		myBitString.setValue2sComp(theDecimalValue);
	}
	
	/**
	 * Show myBitString in decimal if booDecimal is true.
	 * Otherwise, show myBitString in binary.
	 * 
	 * @param booDecimal show in decimal if true; bitString otherwise.
	 */
	public void showInDecimal(boolean isDeciaml) {
		myBitString.showInDecimal(isDeciaml);
	}
}
