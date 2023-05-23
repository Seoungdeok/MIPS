/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package model;

import java.io.IOException;

/**
 * Computer class comprises of instruction memory, 
 * data memory, register file, and
 * can execute the instructions based on PC
 * 
 * @author mmuppa
 * @author Seoungdeok Jeon
 * @version Spring 2020
 */
public class Computer {

	private final InstructionMemory myInstructionMemory;
	private final DataMemory myDataMemory;
	private final RegisterFile myRegisterFile;
	private BitString myPC;
	private BitString myCurrentInstr;

	// Used values to be highlighted
	private int myUsedInstructionAddress;
	private int myUsedDataAddress;
	private int[] myUsedRegisterNumbers;

	/**
	 * Initializes all the Instruction memory, Data memory, and registers.
	 * PC, instruction to 32 bit 0s .
	 * Represents the initial state.
	 */
	public Computer() {
		myInstructionMemory = new InstructionMemory();
		myRegisterFile = new RegisterFile();
		myDataMemory = new DataMemory();
		myPC = new BitString(32);
		myPC.setValue(0);

		// Instruction, Data, or Register are not used initially.
		myUsedInstructionAddress = -1;
		myUsedDataAddress = -1;
		myUsedRegisterNumbers = new int[]{-1, -1, -1};
	}

	/**
	 * Executes a instruction based on the PC.
	 * 
	 * @throws NullPointerException when reached the end of the instructions
	 * @throws IOException - when address is not aligned on word boundary
	 * @throws IllegalArgumentException
	 */
	public void execute() throws NullPointerException, IOException {
		// Fetch the instruction
		int instrMemAddrees = myPC.getValue();
		BitString fetchInstr = new BitString(0);
		for(int i = 0; i < 4; i++) {	// Instruction Memory is byte addressable
			Instruction instruction = myInstructionMemory.getInstruction(instrMemAddrees + i);
			fetchInstr = fetchInstr.append(instruction.getBitString());
		}
		myCurrentInstr = fetchInstr;
		myUsedInstructionAddress = instrMemAddrees; 
		myPC.addFour();
		// Decode the instruction's first 6 bits 
		// to figure out the opcode
		BitString opCodeStr = myCurrentInstr.substring(0, 6);
		int opCode = opCodeStr.getValue();
		// What instruction is this?
		if (opCode == 0) {
			Type_R instr_R = new Type_R(myCurrentInstr);
			if (instr_R.getFunct().getValue() == 32) {		// ADD
				executeAdd(instr_R);
			} else if(instr_R.getFunct().getValue() == 36) {	// AND
				executeAnd(instr_R);
			} else if(instr_R.getFunct().getValue() == 8) {	// JR
				executeJr(instr_R);
			}
		} else if (opCode == 8) {		// ADDI
			Type_I instr_I = new Type_I(myCurrentInstr);
			executeAddi(instr_I);
		} else if (opCode == 12) {		// ANDI
			Type_I instr_I = new Type_I(myCurrentInstr);
			executeAndi(instr_I);
		} else if (opCode == 35) {		// LW
			Type_I instr_I = new Type_I(myCurrentInstr);
			executeLw(instr_I);
		} else if (opCode == 43) {		// SW
			Type_I instr_I = new Type_I(myCurrentInstr);
			executeSw(instr_I);
		} else if (opCode == 4) {		// BEQ
			Type_I instr_I = new Type_I(myCurrentInstr);
			executeBeq(instr_I);
		} else if (opCode == 2) {		// J
			Type_J instr_J = new Type_J(myCurrentInstr);
			executeJ(instr_J);
		}
	}

	/**
	 * Performs add operation.
	 * 
	 * @param theInstr - the R type instruction
	 */
	public void executeAdd(Type_R theInstr) {
		BitString resultBS = new BitString(32);
		int rsNum = theInstr.getRs().getValue();
		int rtNum = theInstr.getRt().getValue();
		int rdNum = theInstr.getRd().getValue();
		int num1 = myRegisterFile.getRegister(rsNum).getValue2sComp();
		int num2 = myRegisterFile.getRegister(rtNum).getValue2sComp();
		int result = num1 + num2;
		resultBS.setValue2sComp(result);
		myRegisterFile.setRegister(rdNum, resultBS);

		myUsedDataAddress = -1;
		myUsedRegisterNumbers = new int[] {rsNum, rtNum, rdNum};
	}

	/**
	 * Performs and operation.
	 * 
	 * @param theInstr - the R type instruction
	 */
	public void executeAnd(Type_R theInstr) {
		BitString resultBS = new BitString(32);
		int rsNum = theInstr.getRs().getValue();
		int rtNum = theInstr.getRt().getValue();
		int rdNum = theInstr.getRd().getValue();
		int num1 = myRegisterFile.getRegister(rsNum).getValue2sComp();
		int num2 = myRegisterFile.getRegister(rtNum).getValue2sComp();
		int result = (num1 & num2);
		resultBS.setValue2sComp(result);
		myRegisterFile.setRegister(rdNum, resultBS);

		myUsedDataAddress = -1;
		myUsedRegisterNumbers = new int[] {rsNum, rtNum, rdNum};
	}

	/**
	 * Performs jr operation.
	 * 
	 * @param theInstr - the R type instruction
	 * @throws IOException - when jump address not aligned on word boundary
	 */
	public void executeJr(Type_R theInstr) throws IOException {
		int rsNum = theInstr.getRs().getValue();
		int newPC = myRegisterFile.getRegister(rsNum).getValue();
		if(((newPC) % 4) != 0) {
			throw new IOException();
		}
		myPC.setValue(newPC);

		myUsedDataAddress = -1;
		myUsedRegisterNumbers = new int[] {rsNum, -1, -1};
	}

	/**
	 * Performs addi operation.
	 * 
	 * @param theInstr - the I type instruction
	 */
	public void executeAddi(Type_I theInstr) {
		BitString resultBS = new BitString(32);
		int rsNum = theInstr.getRs().getValue();
		int rtNum = theInstr.getRt().getValue();
		int immVal = theInstr.getConstant().getValue2sComp();
		int num = myRegisterFile.getRegister(rsNum).getValue2sComp();
		int result = num + immVal;
		resultBS.setValue2sComp(result);
		myRegisterFile.setRegister(rtNum, resultBS);

		myUsedDataAddress = -1;
		myUsedRegisterNumbers = new int[] {rsNum, rtNum, -1};
	}

	/**
	 * Performs andi operation.
	 * 
	 * @param theInstr - the I type instruction
	 */
	public void executeAndi(Type_I theInstr) {
		BitString resultBS = new BitString(32);
		int rsNum = theInstr.getRs().getValue();
		int rtNum = theInstr.getRt().getValue();
		int immVal = theInstr.getConstant().getValue2sComp();
		int rsContent = myRegisterFile.getRegister(rsNum).getValue2sComp();
		int result = rsContent & immVal;
		resultBS.setValue2sComp(result);
		myRegisterFile.setRegister(rtNum, resultBS);

		myUsedDataAddress = -1;
		myUsedRegisterNumbers = new int[] {rsNum, rtNum, -1};
	}

	/**
	 * Performs lw operation.
	 * 
	 * @param theInstr - the I type instruction
	 * @throws IOException - when fetch address not aligned on word boundary
	 */
	public void executeLw(Type_I theInstr) throws IOException {
		int rsNum = theInstr.getRs().getValue();
		int rtNum = theInstr.getRt().getValue();
		int offset = theInstr.getConstant().getValue2sComp();
		int rsContent = myRegisterFile.getRegister(rsNum).getValue2sComp();
		if(((rsContent + offset) % 4) != 0) {
			throw new IOException();
		}
		int memAddress = (rsContent + offset);
		BitString memContent = myDataMemory.getData(memAddress).getBitString();
		myRegisterFile.setRegister(rtNum, memContent);

		myUsedDataAddress = memAddress;
		myUsedRegisterNumbers = new int[] {rsNum, rtNum, -1};
	}

	/**
	 * Performs sw operation.
	 * 
	 * @param theInstr - the I type instruction
	 * @throws IOException - when store address not aligned on word boundary
	 */
	public void executeSw(Type_I theInstr) throws IOException {
		int rsNum = theInstr.getRs().getValue();
		int rtNum = theInstr.getRt().getValue();
		int offset = theInstr.getConstant().getValue2sComp();
		int rsContent = myRegisterFile.getRegister(rsNum).getValue2sComp();
		if(((rsContent + offset) % 4) != 0) {
			throw new IOException();
		}
		int memAddress = (rsContent + offset);
		BitString data =  myRegisterFile.getRegister(rtNum).getBitString();
		myDataMemory.setData(memAddress, data);

		myUsedDataAddress = memAddress;
		myUsedRegisterNumbers = new int[] {rsNum, rtNum, -1};
	}

	/**
	 * Performs beq operation.
	 * 
	 * @param theInstr - the I type instruction
	 */
	public void executeBeq(Type_I theInstr) {
		int rsNum = theInstr.getRs().getValue();
		int rtNum = theInstr.getRt().getValue();
		int offset = theInstr.getConstant().getValue2sComp();
		int rsContent = myRegisterFile.getRegister(rsNum).getValue2sComp();
		int rtContent = myRegisterFile.getRegister(rtNum).getValue2sComp();
		if (rsContent == rtContent) {
			int newPC = myPC.getValue() + (offset * 4);
			myPC.setValue(newPC);
		}
		myUsedDataAddress = -1;
		myUsedRegisterNumbers = new int[] {rsNum, rtNum, -1};
	}

	/**
	 * Performs j operation.
	 * 
	 * @param theInstr - the J type instruction
	 */
	public void executeJ(Type_J theInstr) {
		BitString targetAddress = theInstr.getTargetAddress();
		BitString topFourBitsPC = myPC.substring(0, 4);
		BitString twoZeros = new BitString(2);
		twoZeros.setValue(0);
		BitString updatedPC = topFourBitsPC.append(targetAddress).append(twoZeros);
		myPC = updatedPC;

		myUsedDataAddress = -1;
		myUsedRegisterNumbers = new int[] {-1, -1, -1};
	}
	
	/**
	 * Get instruction memory.
	 * 
	 * @return a instruction memory
	 */
	public InstructionMemory getInstructionMemory() {
		return myInstructionMemory;
	}

	/**
	 * Get data memory.
	 * 
	 * @return a data memory
	 */
	public DataMemory getDataMemory() {
		return myDataMemory;
	}

	/**
	 * Get register file.
	 * 
	 * @return a register file
	 */
	public RegisterFile getRegisterFile() {
		return myRegisterFile;
	}

	/**
	 * Get memory address of used instruction to highlight.
	 * 
	 * @return memory address of used instruction
	 */
	public int getUsedInstructionAddress() {
		return myUsedInstructionAddress;
	}

	/**
	 * Get memory address of used data to highlight.
	 * 
	 * @return memory address of used data
	 */
	public int getUsedDataAddress() {
		return myUsedDataAddress;
	}

	/**
	 * Get array of used registers to highlight.
	 * 
	 * @return array of used registers 
	 */
	public int[] getUsedRegisterNumbers() {
		return myUsedRegisterNumbers;
	}

	/**
	 * Get PC.
	 * 
	 * @return myPC
	 */
	public BitString getPC() {
		return myPC;
	}
}
