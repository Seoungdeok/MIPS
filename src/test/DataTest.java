/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package test;

import static org.junit.Assert.*;
import org.junit.Test;
import model.BitString;
import model.Data;

/**
 * Test Class for Data class
 * 
 * @author Seoungdeok Jeon
 * @author Tatiana Linardopoulou
 * @version Spring 2020
 *
 */
public class DataTest {
	
	@Test
	public void testData() {
		BitString bitString = new BitString(4);
		char test[] = { '1', '0', '1', '0' };
		bitString.setBits(test);
		Data data = new Data(4, bitString);
		assertNotNull(data);
		assertEquals(data.getAddress(), 4);
		assertEquals(data.getBitString().toString(), "1010");
	}

	@Test
	public void testGetAddress() {
		BitString bitString = new BitString(4);
		char test[] = { '1', '0', '1', '0' };
		bitString.setBits(test);
		Data data = new Data(4, bitString);
		assertEquals(data.getAddress(), 4);
	}

	@Test
	public void testGetBitString() {
		BitString bitString = new BitString(4);
		char test[] = { '1', '0', '1', '0' };
		bitString.setBits(test);
		Data data = new Data(4, bitString);
		assertEquals(data.getBitString().toString(), "1010");
	}

	@Test
	public void testSetBitString() {
		BitString bitString = new BitString(4);
		BitString bitString2 = new BitString(4);
		char test[] = { '1', '0', '1', '0' };
		bitString.setBits(test);
		Data data = new Data(4, bitString2);
		data.setBitString(bitString);
		assertEquals(data.getBitString().toString(), bitString.toString());
	}

	@Test
	public void testSetBitStringInDecimal() {
		char fourBits[] = {'0', '0', '0', '0'};
		BitString bitString = new BitString(4);
		bitString.setBits(fourBits);
		Data data = new Data(4, bitString);
		data.setBitStringByDecimal(5);
		assertEquals(data.getBitString().toString(), "0101");
	}

	@Test
	public void testShowInDecimal() {
		char fourBits[] = {'0', '1', '0', '1'};
		BitString bitString = new BitString(4);
		bitString.setBits(fourBits);
		Data data = new Data(4, bitString);
		data.showInDecimal(true);
		assertEquals(data.getBitString().toString(), "5");

	}

}
