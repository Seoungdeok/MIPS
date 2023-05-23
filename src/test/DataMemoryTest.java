/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.BitString;
import model.Data;
import model.DataMemory;

/**
 * Test Class for DataMemory class
 * @author Seoungdeok Jeon
 * @author Tatiana Linardopoulou
 * @version Spring 2020
 *
 */
public class DataMemoryTest {

	
	/** Test fixture. */
    private DataMemory myTestDataMemory;
    private final char[] test = { '0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0',
			'0','0','0','0','0','0','0','0','0','0','0','0','1','0','1','0' };
    /**
     * This method runs before EVERY test case. Use it to re-initialize test fixtures. 
     */
    @Before
    public void setUp() {
    	myTestDataMemory = new DataMemory();
    }
    
	@Test
	public void testDataMemory() {
		BitString bitString = new BitString(32);
		bitString.setBits(test);
		myTestDataMemory.setData(4, bitString);
		assertEquals("00000000000000000000000000001010", myTestDataMemory.getData(4).getBitString().toString());
	}

	@Test
	public void testGetDataMemory() {
		BitString bitString = new BitString(32);
		bitString.setBits(test);
		Data[] testArray = new Data[500];
		for (int i = 0; i < 500; i++) {
			testArray[i] = new Data(i * 4, bitString);
			myTestDataMemory.setData(i * 4, bitString);
		}
		
		for(int i = 0; i < 500; i++) {
			assertEquals(testArray[i].getAddress(), myTestDataMemory.getDataMemory()[i].getAddress());
			assertEquals(testArray[i].getBitString().toString(), myTestDataMemory.getDataMemory()[i].getBitString().toString());	
		}
	}

	@Test
	public void testGetData() {
		BitString bitString = new BitString(32);
		bitString.setBits(test);
		myTestDataMemory.setData(4, bitString);
		assertEquals("00000000000000000000000000001010", myTestDataMemory.getData(4).getBitString().toString());
	}

	@Test
	public void testSetData() {
		BitString bitString = new BitString(32);
		bitString.setBits(test);
		myTestDataMemory.setData(4, bitString);
		assertEquals("00000000000000000000000000001010", myTestDataMemory.getData(4).getBitString().toString());
	}

}
