package com.controller;

import com.serviceutil.BizService;
import com.po.Dep;
import com.po.Emp;
import com.po.PageBean;
import com.po.Welfare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EmpController implements IController {
    @Autowired
    private BizService bizService;

    public BizService getBizService() {
        return bizService;
    }

    public void setBizService(BizService bizService) {
        this.bizService = bizService;
    }

    @RequestMapping(value="save_Emp.do")
    public String save(HttpServletRequest request, HttpServletResponse response, Emp emp) {
        System.out.println("11111111");
        System.out.println("emp--------"+emp.toString());
//        String realpath = request.getRealPath("/");

        boolean flag=bizService.getEmpBiz().save(emp);
        System.out.println(flag);
        if(flag){
            return 1+"";
        }else{
            return 0+"";
        }
    }

    @RequestMapping(value="update_Emp.do")
    public String update(HttpServletRequest request, HttpServletResponse response, Emp emp) {

//		String realpath = request.getRealPath("/");

        boolean flag=bizService.getEmpBiz().update(emp);
        if(flag){
            return 1+"";
        }else{
            return 0+"";
        }
    }

    @RequestMapping(value="delById_Emp.do")
    public String delById(HttpServletRequest request, HttpServletResponse response, Integer eid) {
        boolean flag=bizService.getEmpBiz().delById(eid);
        if(flag){
            return 1+"";
        }else{
            return 0+"";
        }
    }

    @RequestMapping(value="findById_Emp.do")
    public Emp findById(HttpServletRequest request, HttpServletResponse response, Integer eid) {
        System.out.println(eid);
        Emp oldemp=bizService.getEmpBiz().findById(eid);
        System.out.println(oldemp.toString());

        return oldemp;
    }

    @RequestMapping(value="findDetail_Emp.do")
    public Emp findDetail(HttpServletRequest request, HttpServletResponse response, Integer eid) {
        Emp oldemp=bizService.getEmpBiz().findById(eid);
        return oldemp;
    }

    @RequestMapping(value="findPageAll_Emp.do")
    public Map findPageAll(HttpServletRequest request, HttpServletResponse response, Integer page, Integer rows) {
        //页面使用的是easyUI,需要返回数据格式必须是map的key-value形式
        Map<String,Object> map=new HashMap<>();
        PageBean pb=new PageBean();
        page=page==null||page<1?pb.getPage():page;
        rows=rows==null||rows<1?pb.getRows():rows;
        if(rows>10) rows=10;
        pb.setPage(page);
        pb.setRows(rows);
        //获取当前页记录集合
        List<Emp> lsemp=bizService.getEmpBiz().findPageAll(pb);
        //总记录数
        int maxrows=bizService.getEmpBiz().findMaxRows();
        //封装数据到easyui
        map.put("page", page);
        map.put("rows", lsemp);
        map.put("total",maxrows);

        return map;
    }

    @RequestMapping(value="doinit_Emp.do")
    public Map doinit(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map=new HashMap<>();
        List<Dep> lsdep=bizService.getDepBiz().findAll();
        List<Welfare> lswf=bizService.getWelfareBiz().findAll();
        map.put("lsdep", lsdep);
        map.put("lswf", lswf);

        return map;
    }



}
