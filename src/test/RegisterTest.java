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

/**
 * Test Class for Register class
 * 
 * @author Seoungdeok Jeon
 * @author Tatiana Linardopoulou
 * @version Spring 2020
 *
 */
public class RegisterTest {

	/** Test fixture. */
    private Register myTestRegister;
    
    /**
     * This method runs before EVERY test case. Use it to re-initialize test fixtures. 
     */
    @Before
    public void setUp() {
    	BitString bitString = new BitString(32);
		char test[] = {'0','0','0','0','0','0','0','1','1','0','1','0','1','1','1','1','0','0',
				'0','1','0','0','0','0','0','0','1','0','0','1','0','0'};
		bitString.setBits(test);
		String regName = "$t0";
		int regNum = 8;
		myTestRegister = new Register(regName, regNum, bitString);
    }
    
	@Test
	public void testRegister() {
		assertNotNull(myTestRegister);
		assertEquals("00000001101011110001000000100100", myTestRegister.getBitString().toString());
		assertEquals("$t0", myTestRegister.getName());
		assertEquals(8, myTestRegister.getNumber());
	}

	@Test
	public void testGetName() {
		assertEquals("$t0", myTestRegister.getName() );
	}

	@Test
	public void testGetNumber() {
		assertEquals(8, myTestRegister.getNumber());
	}

	@Test
	public void testGetBitString() {
		assertEquals("00000001101011110001000000100100", myTestRegister.getBitString().toString());
	}

	@Test
	public void testGetValue() {
		assertEquals(28250148, myTestRegister.getValue());
	}

	@Test
	public void testGetValue2sComp() {
		assertEquals(28250148, myTestRegister.getValue2sComp());
	}

	@Test
	public void testSetBitString() {
		BitString bitString = new BitString(32);
		BitString bitString2 = new BitString(32);
		char zeroes[] = {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0',
				'0','0','0','0','0','0','0','0','0','0','0','0','0','0'};
		char test[] = {'0','0','0','0','0','0','0','1','1','0','1','0','1','1','1','1','0','0',
				'0','1','0','0','0','0','0','0','1','0','0','1','0','0'};
		bitString.setBits(zeroes);
		bitString2.setBits(test);
		String regName = "$t0";
		int regNum = 8;
		Register register = new Register(regName, regNum, bitString);
		register.setValue(bitString2);
		assertEquals("00000001101011110001000000100100", register.getBitString().toString());
	}

	@Test
	public void testSetBitStringInDecimal() {
		BitString bitString = new BitString(32);
		char zeroes[] = {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0',
				'0','0','0','0','0','0','0','0','0','0','0','0','0','0'};
		int decimal = 10;
		bitString.setBits(zeroes);
		String regName = "$t0";
		int regNum = 8;
		Register register = new Register(regName, regNum, bitString);
		register.setValueByDecimal(decimal);
		assertEquals(10, register.getValue());
	}

	@Test
	public void testShowInDecimal() {
		BitString bitString = new BitString(32);
		char zeroes[] = {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0',
				'0','0','0','0','0','0','0','0','0','0','0','0','0','0'};
		int decimal = 10;
		bitString.setBits(zeroes);
		String regName = "$t0";
		int regNum = 8;
		Register register = new Register(regName, regNum, bitString);
		register.setValueByDecimal(decimal);
		register.showInDecimal(true);
		assertEquals(10, register.getValue());
	}

}
