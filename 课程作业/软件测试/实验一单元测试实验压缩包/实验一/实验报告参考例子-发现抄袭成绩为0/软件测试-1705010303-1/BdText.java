package test;

import static org.junit.Assert.assertTrue;


import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import BMIBP.BMI;

@RunWith(Parameterized.class)
public class BdText {
	BMI bmi;//测试对象
	
	//测试数据
	double w;
	double h;
	String expect;
	
	//构造方法
	public BdText(double w,double h,String expect)
	{
		this.w=w;
		this.h=h;
		this.expect=expect;
	}
	
	//准备测试数据集合
	@Parameters(name = "身高：{0}  体重：{1}   期待的结果：{2}")
	public static  List datas() {
		return (List) Arrays.asList(
	              new Object[][]{
	            	 
	                      {2.0, 0.5 ,"偏瘦"},
	                      {2.0 ,3.4, "偏瘦"},
	                      {500.0, 0.5, "肥胖"},
	                      {500.0 ,3.4, "肥胖"},
	                      {1.5, 0.5, "偏瘦"},
	                      {1.5, 3.4 ,"偏瘦"},
	                      {2.5, 0.5, "偏瘦"},
	                      {2.5, 3.4, "偏瘦"},
	                      {1.0, 0.5, "偏瘦"},
	                      {1.0, 3.4, "偏瘦"},
	                      {3.0, 0.5, "偏瘦"},
	                      {3.0 ,3.4, "偏瘦"},
	                      {499.5, 0.5,"肥胖"},
	                      {499.5, 3.4, "肥胖"},
	                      {500.5, 0.5, "肥胖"},
	                      {500.5, 3.4, "肥胖"},
	                      {499.0, 0.5, "肥胖"},
	                      {499.0, 3.4, "肥胖"},
	                      {501.0, 0.5, "肥胖"},
	                      {501.0 ,3.4, "肥胖"}
	              }
	      );
	}
	
	
	//测试开始之前，创建测试对象
	@Before
	public void before() {
		bmi=new BMI(w,h);
	}
	
	//测试结束，销毁测试对象
	@After
	public void after() {
		bmi=null;
	}
	
	//进行测试
	@Test
	public void test() {
		assertTrue(expect.equals(bmi.getBMIType()));
	}
	
	
}
