package test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import BMIBP.BMI;
import BMIBP.FatCase;
import BMIBP.FattyCase;
import BMIBP.IlegalCase;
import BMIBP.LossCase;
import BMIBP.NormCase;
import BMIBP.ThinCase;

public class EqClassTest {
	
	static BMI bmi;//测试对象，static修饰，在整个测试过程中只创建一次测试对象
	
	//测试开始前创建测试对象（整个测试过程中只执行一次）
	@BeforeClass
	public static void beforeClass() {
		bmi=new BMI();
	}
	
	
	//测试结束，销毁测试对象
	@AfterClass
	public static void afterClass() {
		bmi=null;
	}
	
	//测试一个用例之前，进行提示
	@Before
	public void beforeMethod() {
		System.out.println("测试一个用例.......");
	}
	
	//一个测试用例测试完成时进行提示
	@After
	public void afterMethod() {
		System.out.println("一个测试用例测试完成");
	}
	
	
	
	//四个偏瘦的测试用例
	@Category(ThinCase.class)
	@Test
	public void tinCase1() {
		bmi.setParams(45, 1.75);
		assertTrue("偏瘦".equals(bmi.getBMIType()));
	}
	
	
	@Category(ThinCase.class)
	@Test
	public void tinCase2() {
		bmi.setParams(45, 1.8);
		assertTrue("偏瘦".equals(bmi.getBMIType()));
	}
	
	
	@Category(ThinCase.class)
	@Test
	public void tinCase3() {
		bmi.setParams(40, 1.75);
		assertTrue("偏瘦".equals(bmi.getBMIType()));
	}
	
	
	@Category(ThinCase.class)
	@Test
	public void tinCase4() {
		bmi.setParams(40, 1.8);
		assertTrue("偏瘦".equals(bmi.getBMIType()));
	}
	
	//四个体重“正常”的测试用例
	@Category(NormCase.class)
	@Test
	public void normCase1() {
		bmi.setParams(60, 1.8);
		assertTrue("正常".equals(bmi.getBMIType()));
	}
	
	
	@Category(NormCase.class)
	@Test
	public void normCase2() {
		bmi.setParams(60, 1.7);
		assertTrue("正常".equals(bmi.getBMIType()));
	}
	
	
	@Category(NormCase.class)
	@Test
	public void normCase3() {
		bmi.setParams(65, 1.8);
		assertTrue("正常".equals(bmi.getBMIType()));
	}
	
	@Category(NormCase.class)
	@Test
	public void normCase4() {
		bmi.setParams(65, 1.7);
		assertTrue("正常".equals(bmi.getBMIType()));
	}
	
	
	//四个偏胖的测试用例
	@Category(FattyCase.class)
	@Test
	public void fattyCase1() {
		bmi.setParams(70, 1.59);
		assertTrue("偏胖".equals(bmi.getBMIType()));
	}
	
	
	@Category(FattyCase.class)
	@Test
	public void fattyCase2() {
		bmi.setParams(70, 1.63);
		assertTrue("偏胖".equals(bmi.getBMIType()));
	}
	
	
	@Category(FattyCase.class)
	@Test
	public void fattyCase3() {
		bmi.setParams(65, 1.59);
		assertTrue("偏胖".equals(bmi.getBMIType()));
	}
	
	@Category(FattyCase.class)
	@Test
	public void fattyCase4() {
		bmi.setParams(65, 1.63);
		assertTrue("偏胖".equals(bmi.getBMIType()));
	}
	
	
	//四个肥胖测试用例
	@Category(FatCase.class)
	@Test
	public void fatCase1() {
		bmi.setParams(95, 1.75);
		assertTrue("肥胖".equals(bmi.getBMIType()));
	}
	
	
	@Category(FatCase.class)
	@Test
	public void fatCase2() {
		bmi.setParams(95, 1.8);
		assertTrue("肥胖".equals(bmi.getBMIType()));
	}
	
	
	@Category(FatCase.class)
	@Test
	public void fatCase3() {
		bmi.setParams(100, 1.75);
		assertTrue("肥胖".equals(bmi.getBMIType()));
	}
	
	
	@Category(FatCase.class)
	@Test
	public void fatCase4() {
		bmi.setParams(100, 1.8);
		assertTrue("肥胖".equals(bmi.getBMIType()));
	}
	
	//三个非法的测试用例
	@Category(IlegalCase.class)
	@Test
	public void ilegalCase1() {
		bmi.setParams(-60,1.7);
		assertTrue("无效".equals(bmi.getBMIType()));
	}
	
	@Category(IlegalCase.class)
	@Test
	public void ilegalCase2() {
		bmi.setParams(60,-1.7);
		assertTrue("无效".equals(bmi.getBMIType()));
	}
	
	@Category(IlegalCase.class)
	@Test
	public void ilegalCase3() {
		bmi.setParams(-60,-1.7);
		assertTrue("无效".equals(bmi.getBMIType()));
	}
}
