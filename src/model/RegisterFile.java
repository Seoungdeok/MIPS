/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package model;

/**
 * Class to represent register file unit.
 * 
 * @author Seoungdeok Jeon
 * @version Spring 2020
 */
public class RegisterFile {
	
	private final static int MAX_REGISTER = 32;
	private final static String[] REGISTERS_NAME = {"$zero", "$at", "$v0", "$v1", "$a0", "$a1", "$a2", "$a3",
											 		"$t0", "$t1", "$t2", "$t3", "$t4", "$t5", "$t6", "$t7",
											 		"$s0", "$s1", "$s2", "$s3", "$s4", "$s5", "$s6", "$s7",
											 		"$t8", "$t9", "$k0", "$k1", "$gp", "$sp", "$fp", "$ra"};
	private Register[] myRegisterFile;
	
	/**
	 * Construct register file with 32 registers.
	 */
	public RegisterFile() {
		myRegisterFile = new Register[MAX_REGISTER];
		for(int number = 0; number < MAX_REGISTER; number++) {
			String name = REGISTERS_NAME[number];
			BitString value = new BitString(32);
			value.setValue(0);
			Register register = new Register(name, number, value);
			myRegisterFile[number] = register;	
		}
	}
	
	/**
	 * Get register file.
	 * 
	 * @return array of registers
	 */
	public Register[] getRegisterFile() {
		return myRegisterFile;
	}
	
	/**
	 * Get register based on its number.
	 * 
	 * @param theRegisterNumber the number of this register
	 * @return register corresponding to theRegisterNumber
	 */
	public Register getRegister(int theRegisterNumber) {
		return myRegisterFile[theRegisterNumber];
	}
	
	/**
	 * Set register with its number and bit-string.
	 * @param theRegisterNumber the number of this register
	 * @param theBitString the bit-string to be set for register
	 */
	public void setRegister(int theRegisterNumber, BitString theBitString) {
		myRegisterFile[theRegisterNumber].setValue(theBitString);
	}
}
