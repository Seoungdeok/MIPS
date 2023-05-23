/*
 * TCSS 372 – Spring 2020
 * Project - MIPS Simulator
 */
package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.BitString;
import model.Type_R;

/**
 * Test Class for Type_R class
 * @author mmuppa
 * @author Seoungdeok Jeon
 * @author Tatiana Linardopoulou
 * @version Spring 2020
 *
 */
public class Type_RTest {


	/** Test fixture. */
    private BitString myTestBitString;
    
    /**
     * This method runs before EVERY test case. Use it to re-initialize test fixtures. 
     */
    @Before
    public void setUp() {
    	myTestBitString = new BitString(32);
		char test[] = {'0','0','0','0','0','0','0','1','1','0','1','0','1','1','1','1','0','0',
				'0','1','0','0','0','0','0','0','1','0','0','1','0','0'};
		myTestBitString.setBits(test);
    }
    
	@Test
	public void testType_R() {
		Type_R typeR = new Type_R(myTestBitString);
		assertNotNull(typeR);
		assertEquals(typeR.getOp().toString(), "000000" );
		assertEquals(typeR.getRs().toString(), "01101");
		assertEquals(typeR.getRt().toString(), "01111");
		assertEquals(typeR.getRd().toString(), "00010" );
		assertEquals(typeR.getShmt().toString(), "00000");
		assertEquals(typeR.getFunct().toString(),"100100" );
	}

	@Test
	public void testGetOp() {
		Type_R typeR = new Type_R(myTestBitString);
		assertEquals(typeR.getOp().toString(), "000000" );
	}

	@Test
	public void testGetRs() {
		Type_R typeR = new Type_R(myTestBitString);
		assertEquals(typeR.getRs().toString(), "01101");
	}

	@Test
	public void testGetRt() {
		Type_R typeR = new Type_R(myTestBitString);
		assertEquals(typeR.getRt().toString(), "01111");
	}

	@Test
	public void testGetRd() {
		Type_R typeR = new Type_R(myTestBitString);
		assertEquals(typeR.getRd().toString(), "00010" );
	}

	@Test
	public void testGetShmt() {
		Type_R typeR = new Type_R(myTestBitString);
		assertEquals(typeR.getShmt().toString(), "00000");
	}

	@Test
	public void testGetFunct() {
		Type_R typeR = new Type_R(myTestBitString);
		assertEquals(typeR.getFunct().toString(),"100100" );
	}

}
