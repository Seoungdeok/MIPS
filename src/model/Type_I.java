/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package model;

/**
 * Class to represent I type instruction.
 * 
 * @author Seoungdeok Jeon
 * @version Spring 2020
 */
public class Type_I {

	private BitString myOp;
	private BitString myRs;
	private BitString myRt;
	private BitString myConstant;
	
	/**
	 * Construct I type instruction with bit-string
	 * @param theBitString the bit-string of I type instruction
	 */
	public Type_I(BitString theBitString) {
		myOp = theBitString.substring(0, 6);
		myRs = theBitString.substring(6, 5);
		myRt = theBitString.substring(11, 5);
		myConstant = theBitString.substring(16, 16);
	}

	/**
	 * Get op code part of this instruction.
	 * @return the op code
	 */
	public BitString getOp() {
		return myOp;
	}

	/**
	 * Get rs part of this instruction.
	 * @return the rs
	 */
	public BitString getRs() {
		return myRs;
	}

	/**
	 * Get rt part of this instruction.
	 * @return the rt
	 */
	public BitString getRt() {
		return myRt;
	}

	/**
	 * Get constant value of this instruction.
	 * @return the constant
	 */
	public BitString getConstant() {
		return myConstant;
	}
}
