/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Test;

import model.BitString;

/**
 * Test Class for BitString class
 * @author mmuppa
 * @author Seoungdeok Jeon
 * @author Tatiana Linardopoulou
 * @version Spring 2020
 *
 */
public class BitStringTest {

	@Test
	public void testBitStringConstructor() {
		BitString bitString = new BitString(4);
		char test[] = { '1', '0', '1', '0' };
		bitString.setBits(test);
		assertNotNull(bitString);
		assertEquals(bitString.getLength(), 4);
		assertArrayEquals(bitString.getBits(), test);
	}

	@Test
	public void testSetBitsOverLength() {
		BitString bitString = new BitString(32);
		try {
			bitString.setBits(new char[17]);
			fail("SetBits failed");
		} catch (IllegalArgumentException ie) {

		}
	}

	@Test
	public void testSetBits() {
		BitString bitString = new BitString(4);
		char test[] = { '1', '0', '1', '0' };
		bitString.setBits(test);
		assertEquals(bitString.getLength(), 4);
		assertArrayEquals(bitString.getBits(), test);
	}

	@Test
	public void testInvert() {
		char allOnes[] = { '1', '1', '1', '1' };
		char allZeros[] = { '0', '0', '0', '0' };
		BitString bitString = new BitString(4);
		bitString.setBits(allZeros);
		bitString.invert();
		assertArrayEquals(bitString.getBits(), allOnes);
		bitString.invert();
		assertArrayEquals(bitString.getBits(), allZeros);
	}

	@Test
	public void testAddOne() {
		char allZeros[] = { '0', '0', '0', '0' };
		char one[] = { '0', '0', '0', '1' };
		char two[] = { '0', '0', '1', '0' };
		char allOnes[] = { '1', '1', '1', '1' };
		BitString bitString = new BitString(4);
		bitString.setBits(allZeros);
		bitString.addOne();
		assertArrayEquals(bitString.getBits(), one);
		bitString.setBits(allOnes);
		bitString.addOne();
		assertArrayEquals(bitString.getBits(), allZeros);
		bitString.setBits(one);
		bitString.addOne();
		assertArrayEquals(bitString.getBits(), two);
	}

	@Test
	public void testSetValueInvalid() {

		BitString bitString = new BitString(4);
		try {
			bitString.setValue(-10);
			fail("Can set negative value for unsigned");
		} catch (IllegalArgumentException e) {

		}
		try {
			bitString.setValue(65536);
			fail("Can set more than max for unsigned");
		} catch (IllegalArgumentException e) {

		}

	}

	@Test
	public void testSetValue() {
		char ten[] = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
				'0', '1', '0', '1', '0' };

		BitString bitString = new BitString(16);
		bitString.setValue(10);
		assertArrayEquals(bitString.getBits(), ten);

	}

	@Test
	public void testSetValue2sComp() {
		char max[] = { '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1',
				'1', '1', '1', '1', '1' };
		char min[] = { '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
				'0', '0', '0', '0', '0' };
		BitString bitString = new BitString(16);
		bitString.setValue2sComp(32767);
		assertArrayEquals(bitString.getBits(), max);
		bitString.setValue2sComp(-32768);
		assertArrayEquals(bitString.getBits(), min);
	}

	@Test
	public void testSetValue2sCompInvalid() {
		BitString bitString = new BitString(4);
		try {
			bitString.setValue2sComp(-32769);
			fail("Can set negative value for 2s comp");
		} catch (IllegalArgumentException e) {

		}
		try {
			bitString.setValue2sComp(32768);
			fail("Can set more than max for 2s comp");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void testGetValue() {
		char ten[] = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
				'0', '1', '0', '1', '0' };
		BitString bitString = new BitString(16);
		bitString.setBits(ten);
		assertEquals(bitString.getValue(), 10);

	}

	@Test
	public void testGetValue2sComp() {
		char ones[] = { '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1',
				'1', '1', '1', '1', '1' };
		char min[] = { '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
				'0', '0', '0', '0', '0' };
		BitString bitString = new BitString(16);
		bitString.setBits(ones);
		assertEquals(bitString.getValue2sComp(), -1);
		bitString.setBits(min);
		assertEquals(bitString.getValue2sComp(), -32768);
	}

	@Test
	public void testAppend() {
		char fourBits[] = { '0', '0', '0', '0' };
		char eightBits[] = { '1', '0', '0', '0', '0', '0', '0', '0' };
		char twelveBits[] = { '0', '0', '0', '0', '1', '0', '0', '0', '0', '0',
				'0', '0' };
		BitString bitString = new BitString(4);
		bitString.setBits(fourBits);
		BitString anotherBitString = new BitString(8);
		anotherBitString.setBits(eightBits);
		BitString appendedString = bitString.append(anotherBitString);
		assertArrayEquals(appendedString.getBits(), twelveBits);
	}

	@Test
	public void testSubstring() {
		char twelveBits[] = { '0', '0', '0', '0', '1', '0', '0', '0', '0', '0',
				'0', '0' };
		char eightBits[] = { '1', '0', '0', '0', '0', '0', '0', '0' };
		BitString bitString = new BitString(12);
		bitString.setBits(twelveBits);
		BitString partString = bitString.substring(4, 8);
		assertArrayEquals(partString.getBits(), eightBits);
	}
	
	@Test
	public void testShowInDecimal() {
		char fourBits[] = {'0', '1', '0', '1'};
			BitString bitString = new BitString(4);
			bitString.setBits(fourBits);
			bitString.showInDecimal(true); 
			assertEquals(bitString.toString(), "5");
		
	}
	
	@Test
	public void testToString() {
		char fourBits[] = {'0', '1', '0', '1'};
			BitString bitString = new BitString(4);
			bitString.setBits(fourBits);
			bitString.showInDecimal(false); 
			assertEquals(bitString.toString(), "0101");
		
	}
}
