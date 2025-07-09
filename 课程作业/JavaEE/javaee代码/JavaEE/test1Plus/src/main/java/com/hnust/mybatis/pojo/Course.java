package com.hnust.mybatis.pojo;

/**
 * ClassName: Course
 * Package: com.hnust.mybatis.pojo
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/22 - 21:44
 */
public class Course {
    private Integer cid;
    private String cname;
    private Integer hours;
    private Integer schools;
    private String cpath;
    private String cinf;

    public Course() {
    }

    /*public Course(Integer cid, String cname, Integer hours, Integer schools) {
        this.cid = cid;
        this.cname = cname;
        this.hours = hours;
        this.schools = schools;
    }*/

    public Course(Integer cid, String cname, Integer hours, Integer schools, String cpath, String cinf) {
        this.cid = cid;
        this.cname = cname;
        this.hours = hours;
        this.schools = schools;
        this.cpath = cpath;
        this.cinf = cinf;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getSchools() {
        return schools;
    }

    public void setSchools(Integer schools) {
        this.schools = schools;
    }

    public String getCpath() {
        return cpath;
    }

    public void setCpath(String cpath) {
        this.cpath = cpath;
    }

    public String getCinf() {
        return cinf;
    }

    public void setCinf(String cinf) {
        this.cinf = cinf;
    }

    /*@Override
    public String toString() {
        return "Course{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", hours=" + hours +
                ", schools=" + schools +
                '}';
    }*/

    @Override
    public String toString() {
        return "Course{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", hours=" + hours +
                ", schools=" + schools +
                ", cpath='" + cpath + '\'' +
                ", cinf='" + cinf + '\'' +
                '}';
    }
}
