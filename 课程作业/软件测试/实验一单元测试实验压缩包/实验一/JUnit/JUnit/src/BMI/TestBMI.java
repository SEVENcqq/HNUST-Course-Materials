package BMI;

import static org.junit.Assert.*;

import org.junit.Test;

/***
 * 测试类
 * @author Hetingqin
 *
 */

public class TestBMI {
    BMI bmiObj;// 被测类
    // 创建被测对象
    public void createTestObj(double w, double h){
        bmiObj = new BMI(w, h);
    }
    // 释放被测对象
    public void freeTestObj(){
        bmiObj = null;
    }
    // 执行结果校验
    public boolean verify(String expected, String actual){
        if ( expected==actual ){
            return true;
        }else{
            return false;
        }
    }
    // 记录执行过程
    public String  record(double w, double h, String expected, String actual, boolean testResult){
        String output = "";
        if(testResult){
            output += "Pass. 体重:" + w + ", 身高:" + h;
        }else{
            output += "Fail. 体重:" + w + ", 身高:" + h +
                    ", Expected:" + expected + ", Actual:" +  actual;
        }
        return output;
    }
    // 测试用例1
    public void testGetBMIType1(){
        createTestObj(45.0, 1.6);
        String actual = bmiObj.getBMIType();
        boolean testResult = verify("偏瘦", actual );
        System.out.println(record(45.0,.6, "偏瘦",
                actual, testResult));
        freeTestObj();
    }
    // 测试用例2
    public void testGetBMIType2(){
        createTestObj(55.0, 1.6);
        String actual = bmiObj.getBMIType();
        boolean testResult = verify("正常", actual );
        System.out.println(record(55.0,1.6, "正常",
                actual, testResult));
        freeTestObj();
    }
    // 测试用例3
    public void testGetBMIType3(){
        createTestObj(68.0, 1.6);
        String actual = bmiObj.getBMIType();
        boolean testResult = verify("偏胖", actual );
        System.out.println(record(68.0,1.6, "偏胖", actual, testResult));
        freeTestObj();
    }
    // 测试用例4
    public void testGetBMIType4(){
        createTestObj(80.0, 1.6);
        String actual = bmiObj.getBMIType();
        boolean testResult = verify("肥胖", actual );
        System.out.println(record(80.0,1.6, "肥胖", actual, testResult));
        freeTestObj();
    }
    public static void main(String[] args){
        TestBMI test = new TestBMI();
        test.testGetBMIType1();
        test.testGetBMIType2();
        test.testGetBMIType3();
        test.testGetBMIType4();
    }
}
