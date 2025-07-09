package BMIBP;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

//1.指定参数化运行器
@RunWith(Parameterized.class)
public class BMITestParam {
  BMI testObj;//被测类

  //1.定义属性，作为参数
  private double weight;//体重
  private double height;//身高
  private String expected;//预期分类

  //2.定义带参数的构造方法，传输数据
  public BMITestParam(double w, double h, String exp){
      this.weight = w;
      this.height = h;
      this.expected = exp;
  }

  //3.准备测试数据集
  @Parameterized.Parameters(name="{index}:getBMIType[{0},{1}]=[{2}]")
  public static Collection testDataset(){
      return Arrays.asList(
              new Object[][]{
                      {45.0,1.6,"偏瘦"},
                      {55.0,1.6,"正常"},
                      {68.0,1.6,"偏胖"},
                      {80.0,1.6,"肥胖"}
              }
      );
  }

  @Before
  public void setUp() throws Exception {
      testObj = new BMI(weight, height);
  }

  @After
  public void tearDown() throws Exception {
      testObj = null;
  }

  @Test
  public void getBMIType() {
      assertTrue(testObj.getBMIType()==expected);
  }
}