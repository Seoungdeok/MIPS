/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import model.BitString;
import model.Data;
import model.DataMemory;
import model.Instruction;
import model.InstructionMemory;
import model.Computer;
import model.Register;
import model.RegisterFile;

/**
 *  A controller for MIPS Simulator.
 * 
 * @author Seoungdeok Jeon
 * @version Spring 2020
 */
public class Controller {

	private final Computer myComputer;
	private final InstructionMemory myInstructionMemory;
	private final DataMemory myDataMemory;
	private final RegisterFile myRegisterFile;

	/**
	 * Constructs a controller for MIPS Simulator.
	 */
	public Controller() {
		myComputer = new Computer();
		myInstructionMemory = myComputer.getInstructionMemory();
		myDataMemory = myComputer.getDataMemory();
		myRegisterFile = myComputer.getRegisterFile();
	}

	/**
	 * Load instructions from the input file.
	 * 
	 * @param file - file that contains MIPS machine code instructions
	 * @throws IOException when file is not correctly formatted
	 */
	public void loadInstructionsFromFile(File file) throws IOException {
		try {
			List<Instruction> instructionList = new LinkedList<>();
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			int address = 0;
			while (line != null) {
				String[] lineArr = line.split("(?<=\\G.{8})");
				for(String byteStr : lineArr) {
					char[] byteArr = byteStr.toCharArray();
					BitString bitString = new BitString(8);
					bitString.setBits(byteArr);
					Instruction instruction = new Instruction(address, bitString);
					instructionList.add(instruction);
					address++;
				}
				// read next line
				line = reader.readLine();
			}
			if(instructionList.size() > 800) { // 800 byte addressable instructions -> 200 32-bit addressable instructions
				throw new IOException();
			}

			myInstructionMemory.clear();
			myInstructionMemory.loadInstructions(instructionList.toArray(new Instruction[instructionList.size()]));
		} catch (IllegalArgumentException e) {
			throw new IOException();
		}
	}

	/**
	 * Load data from the input file.
	 * 
	 * @param file - file that contains MIPS machine code data
	 * @throws IOException when file is not correctly formatted
	 */
	public void loadDataFromFile(File file) throws IOException {
		try {
			List<Data> dataList = new LinkedList<>();
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			int address = 0;
			while (line != null) {
				char[] arr = line.toCharArray();
				BitString bitString = new BitString(32);
				bitString.setBits(arr);
				Data data = new Data(address * 4, bitString);
				dataList.add(data);
				address++;
				// read next line
				line = reader.readLine();
			}
			if(dataList.size() > 500) {
				throw new IllegalArgumentException();
			}
			myDataMemory.clear();
			myDataMemory.loadData(dataList.toArray(new Data[dataList.size()]));
		} catch (IllegalArgumentException e) {
			throw new IOException();
		}
	}
	
	/**
	 * Runs simulator one step.
	 * 
	 * @throws NullPointerException - when reached the end of the instructions
	 * @throws IOException - when address is not aligned on word boundary
	 */
	public void runOneStep() throws NullPointerException, IOException {
		myComputer.execute();
	}
	
	/**
	 * Show values in decimal if booDecimal is true.
	 * Otherwise, show values in binary.
	 * 
	 * @param booDecimal show in decimal if true; binary otherwise.
	 */
	public void showInDecimal(boolean booDecimal) {
		for(Data d : myDataMemory.getDataMemory()) {
			d.showInDecimal(booDecimal);
		}
		for(Register r : myRegisterFile.getRegisterFile()) {
			r.showInDecimal(booDecimal);
		}
	}
	
	/**
	 * Get instruction memory from Computer.
	 * 
	 * @return a instruction memory
	 */
	public InstructionMemory getInstructionMemory() {
		return myInstructionMemory;
	}

	/**
	 * Get data memory from computer.
	 * 
	 * @return a data memory
	 */
	public DataMemory getDataMemory(){
		return myDataMemory;
	}

	/**
	 * Get register file from Computer.
	 * 
	 * @return a register file
	 */
	public RegisterFile getRegisterFile() {
		return myRegisterFile;
	}

	/**
	 * Get memory address of used instruction from computer to highlight.
	 * 
	 * @return memory address of used instruction
	 */
	public int getUsedInstructionAddress() {
		return myComputer.getUsedInstructionAddress();
	}

	/**
	 * Get memory address of used data from computer to highlight.
	 * 
	 * @return memory address of used data
	 */
	public int getUsedDataAddress() {
		return myComputer.getUsedDataAddress();
	}

	/**
	 * Get array of used registers from computer to highlight.
	 * 
	 * @return array of used registers 
	 */
	public int[] getUsedRegisters() {
		return myComputer.getUsedRegisterNumbers();
	}

}
