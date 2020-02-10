package com.po;

import java.io.Serializable;

public class EmpWelfare implements Serializable {
    private Integer ewid;
    private Integer eid;
    private Integer wid;
    public EmpWelfare() {
        super();

    }
    public EmpWelfare(Integer ewid, Integer eid, Integer wid) {
        super();
        this.ewid = ewid;
        this.eid = eid;
        this.wid = wid;
    }

    public EmpWelfare(Integer eid, Integer wid) {
        super();
        this.eid = eid;
        this.wid = wid;
    }
    public Integer getEwid() {
        return ewid;
    }
    public void setEwid(Integer ewid) {
        this.ewid = ewid;
    }
    public Integer getEid() {
        return eid;
    }
    public void setEid(Integer eid) {
        this.eid = eid;
    }
    public Integer getWid() {
        return wid;
    }
    public void setWid(Integer wid) {
        this.wid = wid;
    }
    @Override
    public String toString() {
        return "EmpWelfare [ewid=" + ewid + ", eid=" + eid + ", wid=" + wid + "]";
    }

}
