package com.controller;

import com.po.Dep;
import com.po.Emp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface IController {

    public String  save(HttpServletRequest request, HttpServletResponse response, Emp emp);
    public String  update(HttpServletRequest request, HttpServletResponse response, Emp emp);
    public String  delById(HttpServletRequest request, HttpServletResponse response, Integer eid);
    public Emp findById(HttpServletRequest request, HttpServletResponse response, Integer eid);
    public Emp  findDetail(HttpServletRequest request, HttpServletResponse response, Integer eid);
    public Map findPageAll(HttpServletRequest request, HttpServletResponse response, Integer page, Integer rows);
    public Map  doinit(HttpServletRequest request, HttpServletResponse response);
}
