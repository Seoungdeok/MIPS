/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.BitString;
import model.Type_I;

/**
 * Test Class for Type_I class
 * @author mmuppa
 * @author Seoungdeok Jeon
 * @author Tatiana Linardopoulou
 * @version Spring 2020
 *
 */
public class Type_ITest {

	/** Test fixture. */
    private BitString myTestBitString;
    
    /**
     * This method runs before EVERY test case. Use it to re-initialize test fixtures. 
     */
    @Before
    public void setUp() {
    	myTestBitString = new BitString(32);
		char test[] = {'1','0','0','0','1','1','0','1','0','1','0','1','0','1','0','1','0','0',
				'0','0','0','0','0','0','0','0','0','1','1','1','1','0'};
		myTestBitString.setBits(test);
    }
    
    
	@Test
	public void testType_I() {
		Type_I typeI = new Type_I(myTestBitString);
		assertNotNull(typeI);
		assertEquals(typeI.getOp().toString(), "100011" );
		assertEquals(typeI.getRs().toString(), "01010");
		assertEquals(typeI.getRt().toString(), "10101");
		assertEquals(typeI.getConstant().toString(), "0000000000011110");
	}

	@Test
	public void testGetOp() {
		Type_I typeI = new Type_I(myTestBitString);
		assertEquals(typeI.getOp().toString(), "100011" );
	}

	@Test
	public void testGetRs() {
		Type_I typeI = new Type_I(myTestBitString);
		assertEquals(typeI.getRs().toString(), "01010");
	}

	@Test
	public void testGetRt() {
		Type_I typeI = new Type_I(myTestBitString);
		assertEquals(typeI.getRt().toString(), "10101");
	}

	@Test
	public void testGetConstant() {
		Type_I typeI = new Type_I(myTestBitString);
		assertEquals(typeI.getConstant().toString(), "0000000000011110");
	}

}
