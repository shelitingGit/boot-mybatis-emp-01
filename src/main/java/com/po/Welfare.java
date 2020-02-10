package com.po;

import java.io.Serializable;

public class Welfare implements Serializable {
    private Integer wid;
    private String wname;
    public Welfare() {
        super();
    }
    public Welfare(Integer wid, String wname) {
        super();
        this.wid = wid;
        this.wname = wname;
    }
    public Integer getWid() {
        return wid;
    }
    public void setWid(Integer wid) {
        this.wid = wid;
    }
    public String getWname() {
        return wname;
    }
    public void setWname(String wname) {
        this.wname = wname;
    }
    @Override
    public String toString() {
        return "Welfare [wid=" + wid + ", wname=" + wname + "]";
    }



}
