package BMIBP;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import static org.junit.Assert.*;

import org.junit.Test;

//指定运行器，指定要加入的测试类
@RunWith(Suite.class)
@Suite.SuiteClasses({BMITestParam.class, BloodPressureTest.class})
public class TestSuiteWithBMIAndBP {


}
