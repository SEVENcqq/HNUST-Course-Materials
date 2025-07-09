package BMITest;

import static org.junit.Assert.*;

import org.junit.Test;

import BMI.BMI;

public class HelloTest {

	@Test
	public void test() {
		BMI testObj = new BMI(45.0, 1.6);
		// testObj.setParams(45.0, 1.6);
		String expected = "偏瘦";
		assertTrue(testObj.getBMIType() == expected);
	}

}
