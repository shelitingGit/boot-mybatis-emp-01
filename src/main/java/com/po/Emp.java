package com.po;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Emp implements Serializable {
    //员工信息
    private Integer eid;
    private String ename;
    private String sex;
    private String address;
    private Date birthday;
    private Integer depid;
    //加入页面相关属性
    //部门名称
    private String depname;
    //薪资
    private Float emoney;
    //与福利相关
    private String[] wids;//福利ID数组
    private List<Welfare> lswf;//该员工福利结合

    //日期格式转换
    private String sdate;
    public Emp() {
        super();
    }
    public Emp(Integer eid, String ename, String sex, String address, Date birthday, Integer depid) {
        super();
        this.eid = eid;
        this.ename = ename;
        this.sex = sex;
        this.address = address;

        this.birthday = birthday;

        this.depid = depid;
    }

    public Emp(Integer eid, String ename, String sex, String address, Date birthday, Integer depid,
               String depname) {
        super();
        this.eid = eid;
        this.ename = ename;
        this.sex = sex;
        this.address = address;
        this.birthday = birthday;

        this.depid = depid;
        this.depname = depname;
        System.out.println("构造");
    }
    public Integer getEid() {
        return eid;
    }
    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }
    public void setEname(String ename) {
        this.ename = ename;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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
    public Float getEmoney() {
        return emoney;
    }
    public void setEmoney(Float emoney) {
        this.emoney = emoney;
    }
    public String[] getWids() {
        return wids;
    }
    public void setWids(String[] wids) {
        this.wids = wids;
    }
    public List<Welfare> getLswf() {
        return lswf;
    }
    public void setLswf(List<Welfare> lswf) {
        this.lswf = lswf;
    }

    public String getSdate() {
        if(birthday!=null){
            sdate=new SimpleDateFormat("yyyy-MM-dd").format(birthday);
        }
        return sdate;
    }
    public void setSdate(String sdate) {
        if(sdate!=null&&!sdate.trim().equals("")){
            try {
                birthday=new SimpleDateFormat("yyyy-MM-dd").parse(sdate);
            } catch (ParseException e) {

                e.printStackTrace();
            }
        }
        this.sdate = sdate;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "eid=" + eid +
                ", ename='" + ename + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                ", depid=" + depid +
                ", depname='" + depname + '\'' +
                ", emoney=" + emoney +
                ", wids=" + Arrays.toString(wids) +
                ", lswf=" + lswf +
                ", sdate='" + sdate + '\'' +
                '}';
    }
}
