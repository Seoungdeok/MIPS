/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package test;

import static org.junit.Assert.*;
import org.junit.Test;
import model.BitString;
import model.Instruction;

/**
 * Test Class for Instruction class
 * 
 * @author Seoungdeok Jeon
 * @author Tatiana Linardopoulou
 * @version Spring 2020
 *
 */
public class InstructionTest {

	@Test
	public void testInstruction() {
		BitString bitString = new BitString(32);
		char test[] = {'0','0','0','0','0','0','0','1','1','0','1','0','1','1','1','1','0','0',
				'0','1','0','0','0','0','0','0','1','0','0','1','0','0'};
		bitString.setBits(test);
		Instruction instruction = new Instruction(4, bitString);
		assertNotNull(instruction);
		assertEquals(instruction.getAddress(), 4);
		assertEquals(instruction.getBitString().toString(), "00000001101011110001000000100100");
	}

	@Test
	public void testGetAddress() {
		BitString bitString = new BitString(32);
		char test[] = {'0','0','0','0','0','0','0','1','1','0','1','0','1','1','1','1','0','0',
				'0','1','0','0','0','0','0','0','1','0','0','1','0','0'};
		bitString.setBits(test);
		Instruction instruction = new Instruction(4, bitString);
		assertEquals(instruction.getAddress(), 4);	
	}

	@Test
	public void testGetBitString() {
		BitString bitString = new BitString(32);
		char test[] = {'0','0','0','0','0','0','0','1','1','0','1','0','1','1','1','1','0','0',
				'0','1','0','0','0','0','0','0','1','0','0','1','0','0'};
		bitString.setBits(test);
		Instruction instruction = new Instruction(4, bitString);
		assertEquals(instruction.getBitString().toString(), "00000001101011110001000000100100");
	}

}
