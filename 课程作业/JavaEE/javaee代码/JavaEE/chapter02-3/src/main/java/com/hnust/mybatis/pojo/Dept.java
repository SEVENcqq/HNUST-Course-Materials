package com.hnust.mybatis.pojo;

import java.util.List;

/**
 * ClassName: Dept
 * Package: com.hnust.mybatis.pojo
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/14 - 15:48
 */
public class Dept {
    private int deptId;
    private String deptName;
    private List<Emp> emps;

    public Dept() {
    }

    public Dept(int deptId, String deptName) {
        this.deptId = deptId;
        this.deptName = deptName;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<Emp> getEmps() {
        return emps;
    }

    public void setEmps(List<Emp> emps) {
        this.emps = emps;
    }

    /*@Override
    public String toString() {
        return "Dept{" +
                "deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                '}';
    }*/

    @Override
    public String toString() {
        return "Dept{" +
                "deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", emps=" + emps +
                '}';
    }
}
