/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package view;

import javax.swing.table.AbstractTableModel;
import model.BitString;
import model.Instruction;
import model.InstructionMemory;
import model.RegisterFile;
import model.Type_I;
import model.Type_J;
import model.Type_R;

/**
 * Table model for the instruction memory.
 * 
 * @author Seoungdeok Jeon
 * @version Spring 2020
 */
public class InstructionMemoryTableModel extends AbstractTableModel {

	private final String[] myColNames = {"Address", "Code", "Source"};
	private InstructionMemory myIntstructionMemory;

	/**
	 * Set instruction memory.
	 * 
	 * @param InstructionMemory the instruction memory to be set
	 */
	public void setInstructionMemory(InstructionMemory theInstructionMemory) {
		myIntstructionMemory = theInstructionMemory;
	}

	@Override
	public String getColumnName(int column) {
		return myColNames[column];
	}

	@Override
	public int getColumnCount() {
		return myColNames.length;
	}

	@Override
	public int getRowCount() {
		int row = 0;
		for(Instruction instruction : myIntstructionMemory.getInstructionMemory()) {
			if (instruction != null) {
				row++;
			}
		}
		return row/4;
	}

	@Override
	public Object getValueAt(int row, int col) {
		BitString instructionBS = new BitString(0);
		int address = 0;
		for(int i = 0; i < 4; i++) {
			Instruction instruction = myIntstructionMemory.getInstruction(row * 4 + i);
			instructionBS = instructionBS.append(instruction.getBitString());
			if(i == 0) {
				address = instruction.getAddress();
			}
		}
		
		if(instructionBS != null) {
			switch(col) {
			case 0:
				return address;
			case 1:
				return instructionBS;
			case 2:
				return getAssemblyCode(instructionBS);
			}
		}
		return null;
	}
	
	/**
	 * Get assembly code based on the instruction's bit-string.
	 * 
	 * @param instrBitString bit-string of the instruction
	 * @return the assembly code
	 */
	private String getAssemblyCode(BitString instrBitString) {
		String source = "";
		BitString opCodeBS = instrBitString.substring(0, 6);
		int opCode = opCodeBS.getValue();
		RegisterFile registerFile = new RegisterFile();
		
		// What instruction is this?
		if (opCode == 0) {
			Type_R instr_R = new Type_R(instrBitString);
			String rdName = registerFile.getRegister(instr_R.getRd().getValue()).getName();
			String rsName = registerFile.getRegister(instr_R.getRs().getValue()).getName();
			String rtName = registerFile.getRegister(instr_R.getRt().getValue()).getName();
			if (instr_R.getFunct().getValue() == 32) {		// ADD
				source = String.format("add %s, %s, %s", rdName, rsName, rtName);
			} else if(instr_R.getFunct().getValue() == 36) {	// AND
				source = String.format("and %s, %s, %s", rdName, rsName, rtName);
			} else if(instr_R.getFunct().getValue() == 8) {	// JR
				source = String.format("jr %s", rsName);
			}
		} else if (opCode == 8) {		// ADDI
			Type_I instr_I = new Type_I(instrBitString);
			String rsName = registerFile.getRegister(instr_I.getRs().getValue()).getName();
			String rtName = registerFile.getRegister(instr_I.getRt().getValue()).getName();
			int immVal = instr_I.getConstant().getValue2sComp();
			source = String.format("addi %s, %s, %d", rtName, rsName, immVal);
		} else if (opCode == 12) {		// ANDI
			Type_I instr_I = new Type_I(instrBitString);
			String rsName = registerFile.getRegister(instr_I.getRs().getValue()).getName();
			String rtName = registerFile.getRegister(instr_I.getRt().getValue()).getName();
			int immVal = instr_I.getConstant().getValue2sComp();
			source = String.format("andi %s, %s, %d", rtName, rsName, immVal);
		} else if (opCode == 35) {		// LW
			Type_I instr_I = new Type_I(instrBitString);
			String rsName = registerFile.getRegister(instr_I.getRs().getValue()).getName();
			String rtName = registerFile.getRegister(instr_I.getRt().getValue()).getName();
			int offset = instr_I.getConstant().getValue2sComp();
			source = String.format("lw %s, %d(%s)", rtName, offset, rsName);
		} else if (opCode == 43) {		// SW
			Type_I instr_I = new Type_I(instrBitString);
			String rsName = registerFile.getRegister(instr_I.getRs().getValue()).getName();
			String rtName = registerFile.getRegister(instr_I.getRt().getValue()).getName();
			int offset = instr_I.getConstant().getValue2sComp();
			source = String.format("sw %s, %d(%s)", rtName, offset, rsName);
		} else if (opCode == 4) {		// BEQ
			Type_I instr_I = new Type_I(instrBitString);
			String rsName = registerFile.getRegister(instr_I.getRs().getValue()).getName();
			String rtName = registerFile.getRegister(instr_I.getRt().getValue()).getName();
			int offset = instr_I.getConstant().getValue2sComp() * 4;
			source = String.format("beq %s, %s, %d", rsName, rtName, offset);
		} else if (opCode == 2) {		// J
			Type_J instr_J = new Type_J(instrBitString);
			int offset = instr_J.getTargetAddress().getValue2sComp() * 4;
			source = String.format("j %d", offset);
		}
		return source;
	}
}