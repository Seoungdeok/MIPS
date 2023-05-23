/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package model;

import java.util.Arrays;

/**
 * Class to represent instruction memory unit.
 * 
 * @author Seoungdeok Jeon
 * @version Spring 2020
 */
public class InstructionMemory {
	/** 
	 * The size of the byte-addressable instruction memory. 
	 * It can hold up to 200 instructions. 
	 */
	private final static int MAX_INSTRUCTION_MEMORY = 800;
	private Instruction[] myInstructionMemory;
	
	/**
	 * Construct instruction memory unit which can
	 * hold up to 200 instructions.
	 */
	public InstructionMemory() {
		myInstructionMemory = new Instruction[MAX_INSTRUCTION_MEMORY];
	}
	
	/**
	 * Load instructions to the instruction memory.
	 * 
	 * @param theInstructions the array of instructions to be loaded in the instruction memory
	 */
	public void loadInstructions(Instruction[] theInstructions) {
		for(int i = 0; i < theInstructions.length; i++) {
			myInstructionMemory[i] = theInstructions[i];
		}
	}
	
	/**
	 * Get instruction memory.
	 * 
	 * @return array of instructions
	 */
	public Instruction[] getInstructionMemory() {
		return myInstructionMemory;
	}
	
	/**
	 * Get instruction based on its address.
	 * 
	 * @param theMemAddress the memory address of the instruction
	 * @return instruction corresponding to theMemAddress
	 */
	public Instruction getInstruction(int theMemAddress) {
		return myInstructionMemory[theMemAddress];
	}
	
	/**
	 * Clear the array of instructions by setting elements to null.
	 */
	public void clear() {
		Arrays.fill(myInstructionMemory, null);
	}
	
	
}
