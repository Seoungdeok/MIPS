/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.BitString;
import model.Type_J;

/**
 * Test Class for Type_J class
 * @author mmuppa
 * @author Seoungdeok Jeon
 * @author Tatiana Linardopoulou
 * @version Spring 2020
 *
 */
public class Type_JTest {

	/** Test fixture. */
    private BitString myTestBitString;
    
    /**
     * This method runs before EVERY test case. Use it to re-initialize test fixtures. 
     */
    @Before
    public void setUp() {
    	myTestBitString = new BitString(32);
		char test[] = {'0','0','0','0','1','0','0','0','0','0','0','0','0','0','0','0','0','0',
				'0','0','0','0','0','0','0','0','0','0','0','0','0','0'};
		myTestBitString.setBits(test);
    }
    
    
	@Test
	public void testType_J() {
		Type_J typeJ = new Type_J(myTestBitString);
		assertNotNull(typeJ);
		assertEquals(typeJ.getOp().toString(), "000010" );
		assertEquals(typeJ.getTargetAddress().toString(), "00000000000000000000000000");
	}

	@Test
	public void testGetOp() {
		Type_J typeJ = new Type_J(myTestBitString);
		assertEquals(typeJ.getOp().toString(), "000010" );
	}

	@Test
	public void testGetTargetAddress() {
		Type_J typeJ = new Type_J(myTestBitString);
		assertEquals(typeJ.getTargetAddress().toString(), "00000000000000000000000000");
	}

}
