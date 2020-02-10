package com.serviceutil;

import com.service.IDepService;
import com.service.IEmpService;
import com.service.IWelfareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BizService {
    @Autowired
    private IDepService depBiz;
    @Autowired
    private IEmpService empBiz;
    @Autowired
    private IWelfareService welfareBiz;
    public IDepService getDepBiz() {
        return depBiz;
    }
    public void setDepBiz(IDepService depBiz) {
        this.depBiz = depBiz;
    }
    public IEmpService getEmpBiz() {
        return empBiz;
    }
    public void setEmpBiz(IEmpService empBiz) {
        this.empBiz = empBiz;
    }
    public IWelfareService getWelfareBiz() {
        return welfareBiz;
    }
    public void setWelfareBiz(IWelfareService welfareBiz) {
        this.welfareBiz = welfareBiz;
    }
}
