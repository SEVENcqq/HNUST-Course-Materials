package BMI;
import java.util.Scanner;

import static java.lang.Math.abs;
/***
 * 
 * @author Htq
 * @description:根据身高和体重计算BMI，并根据国内BMI指标判断健康状况
 */
public class BMI {
    double weight;// 体重
    double height;// 身高

    // Getter and Setter
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    // 设置体重和身高
    public void setParams(double w, double h){
        weight = w;
        height = h;
    }

    // Construction
    public BMI(double w, double h){
        weight = w;
        height = h;
    }

    // 根据BMI值判断健康状况
    public String getBMIType(){
        double bmi = 0.0;
        String result = "";
        if( weight>0 && height>0){
            //1.计算bmi
            bmi = weight/(height*height);
            //2.根据bmi判断所属健康分类
            if(bmi<18.5){
                result = "偏瘦";
            }else if(bmi<24){
                result = "正常l";
            }else if(bmi<28){
                result = "偏胖";
            }else{
                result = "肥胖";
            }
        }else{
            return "Weight or height error!";
        }
        return result;
    }

    public static void main(String[] args) {
        // 方案2：
        // 脚本自行根据测试用例设计来设置体重和身高，并自动校验执行结果
        // 1.创建被测对象
        BMI testObj = new BMI(45.0, 1.6);

        // 2.调用被测方法（使用测试用例）
        String actual = testObj.getBMIType();

        // 3.校验执行结果
        String expected = "偏瘦";
        String output = "";
        if( actual==expected ){
            output += "Pass.";
        }else{
            output += "Fail. 体重:45.0, 身高:1.6, Expected:" + expected + ", Actual:" +  actual;
        }
        output += "\n";

        // 测试用例2
        testObj.setParams(55.0, 1.6);
        actual = testObj.getBMIType();
        expected = "正常";
        if( actual==expected ){
            output += "Pass.";
        }else{
            output += "Fail. 体重:55.0, 身高:1.6, Expected:" + expected + ", Actual:" +  actual;
        }
        output += "\n";

        // 测试用例3
        testObj.setParams(68.0, 1.6);
        actual = testObj.getBMIType();
        expected = "偏胖";
        if( actual==expected ){
            output += "Pass.";
        }else{
            output += "Fail. 体重:68.0, 身高:1.6, Expected:" + expected + ", Actual:" +  actual;
        }
        output += "\n";

        // 测试用例4
        testObj.setParams(80.0, 1.6);
        actual = testObj.getBMIType();
        expected = "肥胖";
        if( actual==expected ){
            output += "Pass.";
        }else{
            output += "Fail. 体重:80.0, 身高:1.6, Expected:" + expected + ", Actual:" +  actual;
        }
        output += "\n";

        //4. 输出结果
        System.out.println(output);
    }

//        // 方案1：
//        // 用户输入体重和身高，调用被测方法，结果输出到屏幕
//        Scanner reader = new Scanner(System.in);
//        double w=0.0, h=0.0;
//        System.out.println("请输入体重和身高，以等号=结束");
//        while (reader.hasNextDouble()){
//            w = reader.nextDouble();
//            h = reader.nextDouble();
//        }
//        BMI testObj = new BMI(w, h);
//        String result = testObj.getBMIType();
//        String output = "体重：" + w + "，身高：" + h + "，BMI状况是：" + result;
//        System.out.println(output);
//    }





//        BMI tmpObj = new BMI(45.0, 1.6);
//        String type = tmpObj.getBMIType();
//        System.out.println(type);
//
//        tmpObj.setParams(55.0, 1.6);
//        System.out.println(tmpObj.getBMIType());
//
//        tmpObj.setParams(68.0, 1.6);
//        System.out.println(tmpObj.getBMIType());
//
//        tmpObj.setParams(80.0, 1.6);
//        System.out.println(tmpObj.getBMIType());

}