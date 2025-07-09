package BMIBP;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BMITest {
	BMI testObj;//被测类
	
	@Before
    public void setUp() throws Exception {
        testObj = new BMI(0, 0);
        System.out.println("Run @Before method");
    }
	
	@After
    public void tearDown() throws Exception {
        testObj = null;
        System.out.println("Run @After method");
    }
	
	@BeforeClass
    public static void prepareEnvironment(){
        System.out.println("Run @BeforeClass method");
    }
	
	@AfterClass
    public static void RestoreEnvironment(){
        System.out.println("Run @AfterClass method");
    }

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
    public void getBMIType_Thin() {
        System.out.println("Run getBMIType_Thin");

        //创建被测对象
//        testObj = new BMI(45.0,1.6);
        testObj.setParams(45.0,1.6);

        //调用测试方法
        String actual = testObj.getBMIType();

        //校验测试结果
        String expect = "偏瘦";
        assertTrue(expect==actual);
    }
	
	@Test
    public void getBMIType_Normal(){
        System.out.println("Run getBMIType_Normal");

        testObj.setParams(55.0,1.6);
        assertTrue(testObj.getBMIType()=="正常");
    }
	
	@Test
    public void getBMIType_SlightlyFat(){
        System.out.println("Run getBMIType_SlightlyFat");
        testObj.setParams(68.0,1.6);
        assertTrue(testObj.getBMIType()=="偏胖");
    }
	
	@Test
    public void testGetBMIType_SlightlyFat(){
        System.out.println("Run testGetBMIType_SlightlyFat");
        testObj.setParams(68.0, 1.6);
        String exp = "偏胖";
        assertTrue(testObj.getBMIType()==exp);
    }
	
	@Category({BVTTest.class})
	@Test
    public void testGetBMIType_Fat(){
        System.out.println("Run testGetBMIType_Fat");
        testObj.setParams(80.0, 1.6);
        String exp = "肥胖";
        assertTrue(testObj.getBMIType()==exp);
    }

}
