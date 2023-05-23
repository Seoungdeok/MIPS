/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package model;

/**
 * Class to represent single instruction in instruction memory.
 * 
 * @author Seoungdeok Jeon
 * @version Spring 2020
 */
public class Instruction {

	private int myAddress;
	private BitString myBitString;
	
	/**
	 * Construct Instruction with its address and bit-string.
	 * 
	 * @param theAddress the address of this Instruction
	 * @param theBitString the bit-string of this Instruction
	 */
	public Instruction(int theAddress, BitString theBitString) {
		myAddress = theAddress;
		myBitString = theBitString;
	}
	
	/**
	 * Get the address of this Instruction.
	 * 
	 * @return the address
	 */
	public int getAddress() {
		return myAddress;
	}

	/**
	 * Get bit-string of this Instruction.
	 * 
	 * @return the bit-string
	 */
	public BitString getBitString() {
		return myBitString;
	}
	
}
