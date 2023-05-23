/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package model;

/**
 * Class to represent J type instruction.
 * 
 * @author Seoungdeok Jeon
 * @version Spring 2020
 */
public class Type_J {

	private BitString myOp;
	private BitString myTargetAddress;
	
	/**
	 * Construct J type instruction with bit-string
	 * @param theBitString the bit-string of J type instruction
	 */
	public Type_J(BitString theInstruction) {
		myOp = theInstruction.substring(0, 6);
		myTargetAddress = theInstruction.substring(6, 26);
	}

	/**
	 * Get op code part of this instruction.
	 * @return the op code
	 */
	public BitString getOp() {
		return myOp;
	}

	/**
	 * Get the target address of this instruction.
	 * @return the target address
	 */
	public BitString getTargetAddress() {
		return myTargetAddress;
	}


}
