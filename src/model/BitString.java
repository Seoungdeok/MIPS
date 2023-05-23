/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package model;

import java.util.Arrays;

/**
 * A BitString class represents a series of 1s and 0s and can hold up to
 * a maximum of 32 bits and also keeps track of the number of bits stored.
 * It has operations to do various operations associated with 1s and 0s - 
 * substring, append, copy, setting and getting 2s complement value, etc.
 * 
 * @author mmuppa
 * @author Seoungdeok Jeon
 * @version Spring 2020
 */
public class BitString {
	
	private static final int MAX_BITS = 32;
	
	private char[] myBits;
	private int myLength;
	// Values for range checking
	private int myMaxValue;
	private int myMinValue;
	private int myMaxUnsignedValue;
	private boolean showInDecimal;
	
	/**
	 * Constructor for BitString object.
	 * 
	 * @param theLength - the length of the bit string
	 */
	public BitString(int theLength) {
		if (theLength > MAX_BITS) {
			throw new IllegalArgumentException("Exceeds bit string length");
		}
		myLength = theLength;
		myBits = new char[theLength];
		myMaxValue = (int) (Math.pow(2, myLength - 1) - 1);
		myMinValue = (int) (-Math.pow(2, myLength - 1));
		myMaxUnsignedValue = (int) (Math.pow(2, myLength) - 1);
		showInDecimal = false;
	}
	
	/**
	 * Sets the corresponding bits by copying.
	 * If length of the bits are not the same then throw exception.
	 * 
	 * @param theBits - Array of bits 
	 */
	public void setBits(char[] theBits) {
		if (theBits == null || theBits.length != myLength) {
			throw new IllegalArgumentException("Invalid input: null or unsuitable bit string length");
		}
		myBits = Arrays.copyOf(theBits, theBits.length);
	}

	/**
	 * Flips all the bits of the BitString. 
	 */
	public void invert() {
		if (myBits == null) {
			throw new IllegalArgumentException("Bit String must be set first.");
		}
		for (int i = 0; i < myLength; i++) {
			if (myBits[i] == '0') {
				myBits[i] = '1';
			} else {
				myBits[i] = '0';
			}
		}
	}

	/**
	 * Adds 1 to the BitString. 
	 */
	public void addOne() {
		if (myBits == null) {
			throw new IllegalArgumentException("Bit String must be set first.");
		}
		for (int i = myLength - 1; i >= 0; i--) {
			if (myBits[i] == '0') {
				myBits[i] = '1';
				return;
			} else {
				myBits[i] = '0';
			}
		}
	}

	/**
	 * Adds 4 to the BitString. 
	 */
	public void addFour() {
		for(int i = 1; i <= 4; i++) {
			addOne();
		}
	}
	
	/**
	 * Sets the unsigned binary representation of the input 
	 * decimal number 
	 * @param n - the unsigned value
	 */
	public void setValue(int n) {
		// Check if it can be represented in the bits available
		if (n < 0 || n > myMaxUnsignedValue) {
			throw new IllegalArgumentException("Cannot represent in "
					+ myLength + " bits.");
		}
		
		for (int i = myLength - 1; i >= 0; i--) {
			myBits[i] = (n % 2 == 0) ? '0' : '1';
			n /= 2;
		}
	}

	/**
	 * Sets the 2s complement binary value of the input value
	 * @param n negative or positive decimal value
	 */
	public void setValue2sComp(int n) {
		if (n < myMinValue || n > myMaxValue) {
			throw new IllegalArgumentException("Cannot represent in "
					+ myLength + " bits.");
		}
		boolean isNegative = n < 0;
		if (n < 0) {
			n *= -1;
		}
		setValue(n);
		if (isNegative) {
			invert();
			addOne();
		}
	}

	/**
	 * Creates a copy of the BitString and returns
	 * @return copy of BitString object
	 */
	public BitString copy() {
		if (myBits == null) {
			throw new IllegalArgumentException("Nothing to copy.");
		}
		BitString copy = new BitString(myLength);
		copy.myBits = Arrays.copyOf(myBits, myLength);
		return copy;
	}

	/**
	 * Returns the unsigned value of the BitString representation. 
	 * @return decimal unsigned value
	 */
	public int getValue() {
		if (myBits == null) {
			throw new IllegalArgumentException("Bit String must be set first.");
		}
		int value = 0;
		for (int i = 0; i < myLength; i++) {
			value *= 2;
			value += myBits[i] == '1' ? 1 : 0;
		}
		return value;
	}

	/**
	 * Returns the 2s complement value of the BitString representation. 
	 * @return decimal value
	 */
	public int getValue2sComp() {
		if (myBits == null) {
			throw new IllegalArgumentException("Bit String must be set first.");
		}
		boolean negative = myBits[0] == '1';
		if (negative) {
			BitString copyString = copy();
			copyString.invert();
			copyString.addOne();
			return -1 * copyString.getValue();
		} else {
			return getValue();
		}
	}

	/**
	 * Returns a new BitString that is combination of this and the 
	 * other BitString appended to it. 
	 * @param other
	 * @return new BitString that combines both
	 */
	public BitString append(BitString other) {
		if (myBits == null || other == null) {
			throw new IllegalArgumentException("Bit String must be set first.");
		}
		BitString bitString = new BitString(myLength + other.myLength);
		if (myLength + other.myLength > MAX_BITS) {
			throw new IllegalArgumentException("Exceeds bit string length");
		}
		int index = 0;
		for (int i = 0; i < this.myLength; i++) {
			bitString.myBits[index++] = this.myBits[i];
		}
		for (int i = 0; i < other.myLength; i++) {
			bitString.myBits[index++] = other.myBits[i];
		}
		return bitString;
	}

	/**
	 * Returns a substring of the given string. 
	 * @param start
	 * @param length
	 * @return A new BitString is created from the source starting at the index
	 *         and with the length.
	 */
	public BitString substring(int start, int length) {
		BitString subStr = new BitString(length);
		for (int i = 0; i < length; i++) {
			subStr.myBits[i] = myBits[start + i];
		}
		return subStr;
	}

	/**
	 * Returns an array of the bits stored in the BitString
	 * @return character array of bits
	 */
	public char[] getBits() {
		return myBits;
	}

	/**
	 * Returns the length of the BitString
	 * @return length
	 */
	public int getLength() {
		return myLength;
	}
	
	/**
	 * Show values in decimal if booDecimal is true.
	 * Otherwise, show values in binary.
	 * 
	 * @param booDecimal show in decimal if true; binary otherwise.
	 */
	public void showInDecimal(boolean booDecimal) {
		showInDecimal = booDecimal;
	}
	
	@Override
	public String toString() {
		if(showInDecimal) {
			int val = this.getValue();
			return Integer.toString(val); 
		} else {
			StringBuilder sb = new StringBuilder();
			for(char bit: myBits) {
				sb.append(bit);
			}
			return sb.toString();
		}
	}

}
