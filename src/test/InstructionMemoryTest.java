/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package test;

import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Test;
import model.BitString;
import model.Instruction;
import model.InstructionMemory;

/**
 * Test Class for InstructionMemory class
 * @author Seoungdeok Jeon
 * @author Tatiana Linardopoulou
 * @version Spring 2020
 *
 */
public class InstructionMemoryTest {

	@Test
	public void testInstructionMemory() {
		BitString bitString = new BitString(8);
		char test[] = {'0','0','0','0','0','0','0','1'};
		bitString.setBits(test);
		Instruction instruction1 = new Instruction(4, bitString);
		Instruction[] theInstructions = {instruction1}; 
		InstructionMemory InstMem = new InstructionMemory();
		InstMem.loadInstructions(theInstructions);
		assertNotNull(InstMem);
		assertEquals("00000001",InstMem.getInstruction(0).getBitString().toString());
	}

	@Test
	public void testLoadInstructions() {
		BitString bitString = new BitString(8);
		char test[] = {'0','0','0','0','0','0','0','1'};
		bitString.setBits(test);
		Instruction instruction1 = new Instruction(4, bitString);
		Instruction[] theInstructions = {instruction1}; 
		InstructionMemory InstMem = new InstructionMemory();
		InstMem.loadInstructions(theInstructions);
		assertEquals("00000001",InstMem.getInstruction(0).getBitString().toString());
	}

	@Test
	public void testGetInstructionMemory() {
		BitString bitString = new BitString(8);
		char test[] = {'0','0','0','0','0','0','0','1'};
		bitString.setBits(test);
		Instruction[] testArray = new Instruction[800];
		for (int i = 0; i < 800; i++) {
			Arrays.fill(testArray, new Instruction(i, bitString));
		}
		
		Instruction[] theInstructions = new Instruction[800];
		for (int i = 0; i < 800; i++) {
			Instruction theInstruction = new Instruction(i, bitString);
			for (int j = 0; j < 800; j++) {
				theInstructions[j] = theInstruction;
			}
		}
		InstructionMemory instMem = new InstructionMemory();
		for (int j = 0; j < 800 ; j++) {
			instMem.loadInstructions(theInstructions);
		}
		for(int i = 0; i < 800; i++) {
			assertEquals(testArray[i].getAddress(), instMem.getInstructionMemory()[i].getAddress());
			assertEquals(testArray[i].getBitString(), instMem.getInstructionMemory()[i].getBitString());
		}
	}

	@Test
	public void testGetInstruction() {
		BitString bitString = new BitString(8);
		char test[] = {'0','0','0','0','0','0','0','1'};
		bitString.setBits(test);
		Instruction instruction1 = new Instruction(4, bitString);
		Instruction[] theInstructions = {instruction1}; 
		InstructionMemory InstMem = new InstructionMemory();
		InstMem.loadInstructions(theInstructions);
		assertEquals("00000001",InstMem.getInstruction(0).getBitString().toString());
	}

	@Test
	public void testClear() {
		BitString bitString = new BitString(8);
		char test[] = {'0','0','0','0','0','0','0','1'};
		bitString.setBits(test);
		Instruction instruction1 = new Instruction(4, bitString);
		Instruction[] theInstructions = {instruction1}; 
		InstructionMemory InstMem = new InstructionMemory();
		InstMem.loadInstructions(theInstructions);
		assertEquals("00000001",InstMem.getInstruction(0).getBitString().toString());
		InstMem.clear();
		assertNull(InstMem.getInstruction(0));
	}

}
