package com.hnust.pojo;

/**
 * ClassName: cinfo
 * Package: com.hnust.pojo
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/8 - 13:04
 */
public class Cinfo {
    private Integer cid;
    private String cpath;
    private String cinf;

    public Cinfo() {
    }

    public Cinfo(Integer cid, String cpath, String cinf) {
        this.cid = cid;
        this.cpath = cpath;
        this.cinf = cinf;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
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

    @Override
    public String toString() {
        return "Cinfo{" +
                "cid=" + cid +
                ", cpath='" + cpath + '\'' +
                ", cinf='" + cinf + '\'' +
                '}';
    }
}
