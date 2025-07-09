package BMIBP;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import BMIBP.BMITest;

//指定运行器
//设置要执行的测试特性
//设置候选测试集
@RunWith(Categories.class)
@Categories.IncludeCategory({BVTTest.class})
@Suite.SuiteClasses({BMITest.class, BloodPressureTest.class})
public class CategoryTest {

}
