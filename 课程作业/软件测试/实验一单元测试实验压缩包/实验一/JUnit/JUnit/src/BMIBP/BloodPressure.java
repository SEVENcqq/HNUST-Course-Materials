package BMIBP;

public class BloodPressure {
    int systolicPressure;// 收缩压
    int diatolicPressure;// 舒张压

    // Getter and Setter
    public int getSystolicPressure() {
        return systolicPressure;
    }
    public void setSystolicPressure(int systolicPressure) {
        this.systolicPressure = systolicPressure;
    }
    public int getDiatolicPressure() {
        return diatolicPressure;
    }
    public void setDiatolicPressure(int diatolicPressure) {
        this.diatolicPressure = diatolicPressure;
    }
    // 设置收缩压和舒张压
    public void setParams(int sysPressure, int diaPressure){
        systolicPressure = sysPressure;
        diatolicPressure = diaPressure;
    }

    // Construction
    public BloodPressure(){
        systolicPressure = 0;
        diatolicPressure = 0;
    }
    public BloodPressure(int sysPressure, int diaPressure){
        systolicPressure = sysPressure;
        diatolicPressure = diaPressure;
    }

    // 根据血压值判断所属血压分级
    public String getPressureLevel(){
        String level = "";
        if(systolicPressure<120 && diatolicPressure<80){
            level = "正常";
        }else if( (systolicPressure>=120 && systolicPressure<=139) && (diatolicPressure>=80 && diatolicPressure<=89)){
            level = "正常高值";
        }else if( (systolicPressure>=140 && systolicPressure<=159) || (diatolicPressure>=90 && diatolicPressure<=99)){
            level = "1级高血压";
        }else if( (systolicPressure>=160 && systolicPressure<=179) || (diatolicPressure>=100 && diatolicPressure<=109)){
            level = "2级高血压";
        }else if( (systolicPressure>=180) || (diatolicPressure>=110)){
            level = "3级高血压";
        }else{
            level = "异常值";
        }
        return level;
    }
}
