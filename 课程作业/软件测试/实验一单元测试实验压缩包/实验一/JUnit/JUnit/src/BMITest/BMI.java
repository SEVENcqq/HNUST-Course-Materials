package BMITest;

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
                result = "正常";
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

}