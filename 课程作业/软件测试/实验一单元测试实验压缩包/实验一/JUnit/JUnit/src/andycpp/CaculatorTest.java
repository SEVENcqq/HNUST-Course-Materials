package andycpp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class CaculatorTest {
	
	private static Caculator ca = new Caculator();

	@Before
	public void setUp() throws Exception {
		ca.clear();
	}

	@Test
	public void testAdd() {
//		fail("Not yet implemented");
		ca.add(2);
		ca.add(4);
		assertEquals(6, ca.getResult());
	}

	@Test
	public void testSubstract() {
//		fail("Not yet implemented");
		ca.add(10);
		ca.substract(2);
		assertEquals(8, ca.getResult());
	}

//	@Ignore("not yet implemented")
	@Test
	public void testMultiply() {
//		fail("Not yet implemented");
	}

	@Test
	public void testDivide() {
		ca.add(8);
		ca.divide(2);
		assertEquals(4, ca.getResult());
//		fail("Not yet implemented");
	}
	
//	@Disabled
	@Test(timeout = 1000)
	public void squareRoot(){
		ca.squareRoot(4);
		assertEquals(2, ca.getResult());
	}
	
	@Test(expected  =  ArithmeticException.class)
	public void divideByzero(){
		ca.divide(0);
	}

}
