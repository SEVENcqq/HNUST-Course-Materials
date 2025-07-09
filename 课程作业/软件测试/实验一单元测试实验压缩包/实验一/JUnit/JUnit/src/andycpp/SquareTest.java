package andycpp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import org.junit.runner.RunWith;

import org.junit.runners.Parameterized;

import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;

import java.util.Collection;

@RunWith(Parameterized.class)
public class SquareTest {

	private static Caculator calculator = new Caculator();

	private int param;

	private int result;

	@Parameters
    public static Collection data()  {

        return  Arrays.asList( new  Object[][] {

                { 2 ,  4 } ,

                { 0 ,  0 } ,

                {-3 ,  9 } ,

       } );

   }

	// 构造函数，对变量进行初始化
	public SquareTest(int param, int result) {

		this.param = param;

		this.result = result;

	}

	@Test
	public void square() {

		calculator.square(param);

		assertEquals(result, calculator.getResult());

	}

}