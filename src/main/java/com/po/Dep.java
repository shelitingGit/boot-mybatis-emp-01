package com.po;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Dep implements Serializable {
    private Integer depid;
    private String depname;

    public Dep(Integer depid, String depname) {
        this.depid = depid;
        this.depname = depname;
    }

    public Dep() {
    }

    public Integer getDepid() {
        return depid;
    }

    public void setDepid(Integer depid) {
        this.depid = depid;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    @Override
    public String toString() {
        return "Dep{" +
                "depid=" + depid +
                ", depname='" + depname + '\'' +
                '}';
    }
}