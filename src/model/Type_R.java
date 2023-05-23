/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package model;

/**
 * Class to represent R type instruction.
 * 
 * @author Seoungdeok Jeon
 * @version Spring 2020
 */
public class Type_R {

	private BitString myOp;
	private BitString myRs;
	private BitString myRt;
	private BitString myRd;
	private BitString myShmt;
	private BitString myFunct;
	
	/**
	 * Construct R type instruction with bit-string
	 * @param theBitString the bit-string of R type instruction
	 */
	public Type_R(BitString theInstruction) {
		myOp = theInstruction.substring(0, 6);
		myRs = theInstruction.substring(6, 5);
		myRt = theInstruction.substring(11, 5);
		myRd = theInstruction.substring(16, 5);
		myShmt = theInstruction.substring(21, 5);
		myFunct = theInstruction.substring(26, 6);
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
	 * Get rd part of this instruction.
	 * @return the rd
	 */
	public BitString getRd() {
		return myRd;
	}


	/**
	 * Get shmt part of this instruction.
	 * @return the shmt
	 */
	public BitString getShmt() {
		return myShmt;
	}

	/**
	 * Get funct part of this instruction.
	 * @return the funct
	 */
	public BitString getFunct() {
		return myFunct;
	}

}
