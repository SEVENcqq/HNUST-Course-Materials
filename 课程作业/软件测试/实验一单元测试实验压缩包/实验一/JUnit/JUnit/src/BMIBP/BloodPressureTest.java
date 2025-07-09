package BMIBP;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

import org.junit.Test;

public class BloodPressureTest {

	//定义被测类
    BloodPressure testObj;

    @Before
    public void setUp() throws Exception {
        testObj = new BloodPressure();
    }

    @After
    public void tearDown() throws Exception {
        testObj = null;
    }
    
    @Test
    public void getPressureLevel_Normal() {
        testObj.setParams(100,70);
        assertTrue(testObj.getPressureLevel()=="正常");
    }
    
    @Test
    public void getPressureLevel_FirstLevel(){
        testObj.setParams(150, 95);
        assertTrue("1级高血压" == testObj.getPressureLevel());
    }
    
    @Test
    public void getPressureLevel_SecondLevel(){
        testObj.setParams(170, 105);
        assertTrue("2级高血压" == testObj.getPressureLevel());
    }

    @Category({BVTTest.class})
    @Test
    public void getPressureLevel_ThirdLevel(){
        testObj.setParams(190, 120);
        assertTrue("3级高血压" == testObj.getPressureLevel());
    }

    @Category({BVTTest.class})
    @Test
    public void getPressureLevel_Boundary1(){
        testObj.setParams(119, 70);
        assertTrue("正常" == testObj.getPressureLevel());
    }

    @Test
    public void getPressureLevel_Boundary2(){
        testObj.setParams(120, 85);
        assertTrue("正常高值" == testObj.getPressureLevel());
    }

    @Test
    public void getPressureLevel_Boundary3(){
        testObj.setParams(121, 85);
        assertTrue("正常高值" == testObj.getPressureLevel());
    }

    @Test
    public void getPressureLevel_Boundary4(){
        testObj.setParams(139, 85);
        assertTrue("正常高值" == testObj.getPressureLevel());
    }

    @Test
    public void getPressureLevel_Boundary5(){
        testObj.setParams(140, 85);
        assertTrue("1级高血压" == testObj.getPressureLevel());
    }

    @Test
    public void getPressureLevel_Boundary6(){
        testObj.setParams(141, 85);
        assertTrue("1级高血压" == testObj.getPressureLevel());
    }

    @Test
    public void getPressureLevel_Boundary7(){
        testObj.setParams(159, 85);
        assertTrue("1级高血压" == testObj.getPressureLevel());
    }

    @Test
    public void getPressureLevel_Boundary8(){
        testObj.setParams(160, 85);
        assertTrue("2级高血压" == testObj.getPressureLevel());
    }

    @Test
    public void getPressureLevel_Boundary9(){
        testObj.setParams(179, 85);
        assertTrue("2级高血压" == testObj.getPressureLevel());
    }

    @Test
    public void getPressureLevel_Boundary10(){
        testObj.setParams(180, 85);
        assertTrue("3级高血压" == testObj.getPressureLevel());
    }

    @Test
    public void getPressureLevel_Boundary11(){
        testObj.setParams(181, 85);
        assertTrue("3级高血压" == testObj.getPressureLevel());
    }
}