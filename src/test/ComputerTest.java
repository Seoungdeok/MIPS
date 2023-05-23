/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package test;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import model.BitString;
import model.Computer;
import model.Data;
import model.Instruction;
import model.InstructionMemory;
import model.RegisterFile;
import model.Type_I;
import model.Type_J;
import model.Type_R;

/**
 * Test Class for Computer class
 * @author Seoungdeok Jeon
 * @author Tatiana Linardopoulou
 * @version Spring 2020
 *
 */
public class ComputerTest {

	/** Test fixture. */
	private Computer myTestComputer;

	/**
	 * This method runs before EVERY test case. Use it to re-initialize test fixtures. 
	 */
	@Before
	public void setUp() {
		myTestComputer = new Computer();
	}

	@Test
	public void testComputer() {
		BitString bitString = new BitString(8);
		char test[] = {'0','1','0','0','1','0','0','1'};
		bitString.setBits(test);

		// create test instruction memory
		InstructionMemory testInstrMem = new InstructionMemory();
		Instruction[] theInstructions = new Instruction[800];
		for (int i = 0; i < 800; i++) {
			Instruction theInstruction = new Instruction(i, bitString);
			theInstructions[i] = theInstruction;
		}
		testInstrMem.loadInstructions(theInstructions);

		// set up instruction memory
		myTestComputer.getInstructionMemory().loadInstructions(theInstructions);

		for(int i = 0; i < 800; i++) {
			assertEquals(testInstrMem.getInstruction(i).getAddress(), 
					myTestComputer.getInstructionMemory().getInstruction(i).getAddress());
			assertEquals(testInstrMem.getInstruction(i).getBitString().toString(), 
					myTestComputer.getInstructionMemory().getInstruction(i).getBitString().toString());
		}
		BitString dataBitString = new BitString(32);
		char test2[] = { '0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0',
				'0','0','0','0','0','0','0','0','0','0','0','0','1','0','1','0' };
		dataBitString.setBits(test2);
		// create test data memory
		Data[] testDataMem = new Data[500];
		for (int i = 0; i < 500; i++) {
			testDataMem[i] = new Data(i * 4, dataBitString);
			myTestComputer.getDataMemory().setData(i * 4, dataBitString);
		}

		for(int i = 0; i < 500; i++) {
			assertEquals(testDataMem[i].getAddress(), 
					myTestComputer.getDataMemory().getData(i * 4).getAddress());
			assertEquals(testDataMem[i].getBitString().toString(), 
					myTestComputer.getDataMemory().getData(i * 4).getBitString().toString());	
		}
		RegisterFile testRegFile = new RegisterFile();
		for(int i = 0; i < 32; i++) {
			assertEquals(testRegFile.getRegisterFile()[i].getName(), 
					myTestComputer.getRegisterFile().getRegisterFile()[i].getName());
			assertEquals(testRegFile.getRegisterFile()[i].getBitString().toString(), 
					myTestComputer.getRegisterFile().getRegisterFile()[i].getBitString().toString());
			assertEquals(testRegFile.getRegisterFile()[i].getNumber(), 
					myTestComputer.getRegisterFile().getRegisterFile()[i].getNumber());
		}
	}

	@Test
	public void testGetInstructionMemory() {
		BitString bitString = new BitString(8);
		char test[] = {'0','1','0','0','1','0','0','1'};
		bitString.setBits(test);

		// create test instruction memory
		InstructionMemory testInstrMem = new InstructionMemory();
		Instruction[] theInstructions = new Instruction[800];
		for (int i = 0; i < 800; i++) {
			Instruction theInstruction = new Instruction(i, bitString);
			theInstructions[i] = theInstruction;
		}
		testInstrMem.loadInstructions(theInstructions);

		// set up instruction memory
		myTestComputer.getInstructionMemory().loadInstructions(theInstructions);

		for(int i = 0; i < 800; i++) {
			assertEquals(testInstrMem.getInstruction(i).getAddress(), 
					myTestComputer.getInstructionMemory().getInstruction(i).getAddress());
			assertEquals(testInstrMem.getInstruction(i).getBitString().toString(), 
					myTestComputer.getInstructionMemory().getInstruction(i).getBitString().toString());
		}
	}

	@Test
	public void testGetDataMemory() {
		BitString bitString = new BitString(32);
		char test[] = { '0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0',
				'0','0','0','0','0','0','0','0','0','0','0','0','1','0','1','0' };
		bitString.setBits(test);
		// create test data memory
		Data[] testDataMem = new Data[500];
		for (int i = 0; i < 500; i++) {
			testDataMem[i] = new Data(i * 4, bitString);
			myTestComputer.getDataMemory().setData(i * 4, bitString);
		}

		for(int i = 0; i < 500; i++) {
			assertEquals(testDataMem[i].getAddress(), 
					myTestComputer.getDataMemory().getData(i * 4).getAddress());
			assertEquals(testDataMem[i].getBitString().toString(), 
					myTestComputer.getDataMemory().getData(i * 4).getBitString().toString());	
		}
	}

	@Test
	public void testGetRegisterFile() {
		RegisterFile testRegFile = new RegisterFile();
		for(int i = 0; i < 32; i++) {
			assertEquals(testRegFile.getRegisterFile()[i].getName(), 
					myTestComputer.getRegisterFile().getRegisterFile()[i].getName());
			assertEquals(testRegFile.getRegisterFile()[i].getBitString().toString(), 
					myTestComputer.getRegisterFile().getRegisterFile()[i].getBitString().toString());
			assertEquals(testRegFile.getRegisterFile()[i].getNumber(), 
					myTestComputer.getRegisterFile().getRegisterFile()[i].getNumber());
		}
	}

	@Test
	public void testGetUsedInstructionAddress() {
		assertEquals(-1, myTestComputer.getUsedInstructionAddress());
	}

	@Test
	public void testGetUsedDataAddress() {
		assertEquals(-1, myTestComputer.getUsedDataAddress());
	}

	@Test
	public void testGetUsedRegisterNumbers() {
		for(int i = 0; i < 3; i++) {
			assertEquals(-1, myTestComputer.getUsedRegisterNumbers()[i]);	
		}
	}

	@Test
	public void testExecuteAdd() {
		// add $t2(10), $t0 (8), $t1 (9)
		String addStr = "00000001000010010101000000100000";
		char[] addArray = addStr.toCharArray();
		BitString addBS = new BitString(32);
		addBS.setBits(addArray);
		Type_R instr_R = new Type_R(addBS);
		myTestComputer.getRegisterFile().getRegister(8).setValueByDecimal(10);
		myTestComputer.getRegisterFile().getRegister(9).setValueByDecimal(15);
		myTestComputer.executeAdd(instr_R);
		assertEquals(25, myTestComputer.getRegisterFile().getRegister(10).getValue());
	}

	@Test
	public void testExecuteAnd() {
		// and $t5 (13), $t3 (11), $t4 (12)
		String andStr = "00000001011011000110100000100100";
		char[] andArray = andStr.toCharArray();
		BitString andBS = new BitString(32);
		andBS.setBits(andArray);
		Type_R instr_R = new Type_R(andBS);
		myTestComputer.getRegisterFile().getRegister(11).setValueByDecimal(7);
		myTestComputer.getRegisterFile().getRegister(12).setValueByDecimal(7);
		myTestComputer.executeAnd(instr_R);
		assertEquals(7, myTestComputer.getRegisterFile().getRegister(13).getValue());
	}

	@Test
	public void testExecuteJr() throws IOException {
		// jr $t0 (8)
		String jrStr = "00000001000000000000000000001000";
		char[] jrArray = jrStr.toCharArray();
		BitString jrBS = new BitString(32);
		jrBS.setBits(jrArray);
		Type_R instr_R = new Type_R(jrBS);
		myTestComputer.getRegisterFile().getRegister(8).setValueByDecimal(8);
		myTestComputer.executeJr(instr_R);

		assertEquals(8, myTestComputer.getPC().getValue());
	}

	@Test(expected = IOException.class)
	public void testExecuteJrException() throws IOException {
		// jr $t0 (8)
		String jrStr = "00000001000000000000000000001000";
		char[] jrArray = jrStr.toCharArray();
		BitString jrBS = new BitString(32);
		jrBS.setBits(jrArray);
		Type_R instr_R = new Type_R(jrBS);
		myTestComputer.getRegisterFile().getRegister(8).setValueByDecimal(2);	// jump address is not word aligned
		myTestComputer.executeJr(instr_R);
	}

	@Test
	public void testExecuteAddi() {
		// addi	$t7 (15), $t6 (14), 1
		String addiStr = "00100001110011110000000000000001";
		char[] addiArray = addiStr.toCharArray();
		BitString addiBS = new BitString(32);
		addiBS.setBits(addiArray);
		Type_I instr_I = new Type_I(addiBS);
		myTestComputer.getRegisterFile().getRegister(14).setValueByDecimal(9);
		myTestComputer.executeAddi(instr_I);
		assertEquals(10, myTestComputer.getRegisterFile().getRegister(15).getValue());
	}

	@Test
	public void testExecuteAndi() {
		// andi	$t9 (25), $t8 (24), 0x77
		String andiStr = "00110011000110010000000001110111";
		char[] andiArray = andiStr.toCharArray();
		BitString andiBS = new BitString(32);
		andiBS.setBits(andiArray);
		Type_I instr_I = new Type_I(andiBS);
		myTestComputer.getRegisterFile().getRegister(24).setValueByDecimal(119);
		myTestComputer.executeAndi(instr_I);
		assertEquals(119, myTestComputer.getRegisterFile().getRegister(25).getValue());
	}

	@Test
	public void testExecuteLw() throws IOException {
		// lw $t0 (8), 0($at) (1)
		String lwStr = "10001100001010000000000000000000";
		char[] lwArray = lwStr.toCharArray();
		BitString lwBS = new BitString(32);
		lwBS.setBits(lwArray);
		Type_I instr_I = new Type_I(lwBS);
		BitString test = new BitString(32);
		test.setValue(5);
		myTestComputer.getDataMemory().getData(0).setBitString(test);
		myTestComputer.executeLw(instr_I);
		assertEquals(5, myTestComputer.getRegisterFile().getRegister(8).getValue());
	}

	@Test(expected = IOException.class)
	public void testExecuteLwException() throws IOException {
		// lw $t0 (8), 1($at) (1)
		String lwStr = "10001100001010000000000000000001";
		char[] lwArray = lwStr.toCharArray();
		BitString lwBS = new BitString(32);
		lwBS.setBits(lwArray);
		Type_I instr_I = new Type_I(lwBS);
		myTestComputer.executeLw(instr_I);
	}
	
	@Test
	public void testExecuteSw() throws IOException {
		// sw $t2 (10), 20($at) (1)
		String swStr = "10101100001010100000000000010100";
		char[] swArray = swStr.toCharArray();
		BitString swBS = new BitString(32);
		swBS.setBits(swArray);
		Type_I instr_I = new Type_I(swBS);
		myTestComputer.getRegisterFile().getRegister(10).setValueByDecimal(150);
		myTestComputer.executeSw(instr_I);
		assertEquals(150, myTestComputer.getDataMemory().getData(20).getBitString().getValue());
	}

	@Test(expected = IOException.class)
	public void testExecuteSwException() throws IOException {
		// sw $t2 (10), 21($at) (1)
		String swStr = "10101100001010100000000000010101";
		char[] swArray = swStr.toCharArray();
		BitString swBS = new BitString(32);
		swBS.setBits(swArray);
		Type_I instr_I = new Type_I(swBS);
		myTestComputer.executeSw(instr_I);
	}
	
	@Test
	public void testExecuteBeqNotTaken() {
		// beq $t0(8), $t1(9), 2
		String beqStr = "00010001000010010000000000000010";
		char[] beqArray = beqStr.toCharArray();
		BitString beqBS = new BitString(32);
		beqBS.setBits(beqArray);
		Type_I instr_I = new Type_I(beqBS);
		myTestComputer.getRegisterFile().getRegister(8).setValueByDecimal(1);
		myTestComputer.getRegisterFile().getRegister(9).setValueByDecimal(2);
		myTestComputer.executeBeq(instr_I);
		assertEquals(0, myTestComputer.getPC().getValue());
	}

	@Test
	public void testExecuteBeqTaken() {
		// beq $t0(8), $t0(9), 2
		String beqStr = "00010001000010000000000000000010";
		char[] beqArray = beqStr.toCharArray();
		BitString beqBS = new BitString(32);
		beqBS.setBits(beqArray);
		Type_I instr_I = new Type_I(beqBS);
		myTestComputer.getRegisterFile().getRegister(8).setValueByDecimal(1);
		myTestComputer.executeBeq(instr_I);
		assertEquals(8, myTestComputer.getPC().getValue());
	}

	@Test
	public void testExecuteJ() {
		// j 2
		String jStr = "00001000000000000000000000000010";
		char[] jArray = jStr.toCharArray();
		BitString jBS = new BitString(32);
		jBS.setBits(jArray);
		Type_J instr_J = new Type_J(jBS);
		myTestComputer.executeJ(instr_J);
		assertEquals(8, myTestComputer.getPC().getValue());
	}

}
