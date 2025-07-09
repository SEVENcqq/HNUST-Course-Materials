package com.hnust.pojo;

import java.util.List;

/**
 * ClassName: School
 * Package: com.hnust.pojo
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/22 - 21:45
 */
public class School {
    private Integer sid;
    private String sname;
    private List<Course> courses;

    public School() {
    }

    public School(Integer sid, String sname) {
        this.sid = sid;
        this.sname = sname;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "School{" +
                "sid=" + sid +
                ", name='" + sname + '\'' +
                ", courses=" + courses +
                '}';
    }
}
