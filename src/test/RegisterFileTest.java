/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.BitString;
import model.Register;
import model.RegisterFile;

/**
 * Test for the RegisterFile class.
 * 
 * @author Seoungdeok Jeon
 * @author Tatiana Linardopoulou
 * @version Spring 2020
 */
public class RegisterFileTest {

	/** Test fixture. */
    private RegisterFile myTestRegisterFile;
    
	private final static String[] REGISTERS_NAME = {"$zero", "$at", "$v0", "$v1", "$a0", "$a1", "$a2", "$a3",
	 		"$t0", "$t1", "$t2", "$t3", "$t4", "$t5", "$t6", "$t7",
	 		"$s0", "$s1", "$s2", "$s3", "$s4", "$s5", "$s6", "$s7",
	 		"$t8", "$t9", "$k0", "$k1", "$gp", "$sp", "$fp", "$ra"};
    
	@Before
	public void setUp() {
		myTestRegisterFile = new RegisterFile();
	}
	@Test
	public void testRegisterFile() {
		assertNotNull(myTestRegisterFile);
		Register[] testArray = new Register[32];
		for(int number = 0; number < 32; number++) {
			String name = REGISTERS_NAME[number];
			BitString value = new BitString(32);
			value.setValue(0);
			Register register = new Register(name, number, value);
			testArray[number] = register;	
		}
		for(int i = 0; i < 32; i++) {
			assertEquals(testArray[i].getName(), myTestRegisterFile.getRegisterFile()[i].getName());
			assertEquals(testArray[i].getNumber(), myTestRegisterFile.getRegisterFile()[i].getNumber());
			assertEquals(testArray[i].getValue(), myTestRegisterFile.getRegisterFile()[i].getValue());
		}
	}
	
	@Test
	public void testGetRegisterFile() {
		Register[] testArray = new Register[32];
		for(int number = 0; number < 32; number++) {
			String name = REGISTERS_NAME[number];
			BitString value = new BitString(32);
			value.setValue(0);
			Register register = new Register(name, number, value);
			testArray[number] = register;	
		}
		for(int i = 0; i < 32; i++) {
			assertEquals(testArray[i].getName(), myTestRegisterFile.getRegisterFile()[i].getName());
			assertEquals(testArray[i].getNumber(), myTestRegisterFile.getRegisterFile()[i].getNumber());
			assertEquals(testArray[i].getValue(), myTestRegisterFile.getRegisterFile()[i].getValue());
		}
	}
	
	@Test
	public void testGetRegister() {
		String testName = "$zero";
		assertEquals(testName, myTestRegisterFile.getRegister(0).getName());
	}

	@Test
	public void testSetRegister() {
		Register[] testFile = myTestRegisterFile.getRegisterFile();
		char test[] = {'0','0','0','0','0','0','0','1','1','0','1','0','1','1','1','1','0','0',
				'0','1','0','0','0','0','0','0','1','0','0','1','0','0'};
		BitString  bitString = new BitString(32);
		bitString.setBits(test);
		testFile[3].setValue(bitString);
		assertEquals(28250148, myTestRegisterFile.getRegisterFile()[3].getValue());
	}

}
