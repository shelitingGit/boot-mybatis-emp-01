package com.serviceutil;

import com.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DaoService {
    @Autowired
    private IDepMapper depMapper;
    @Autowired
    private IEmpMapper empMapper;
    @Autowired
    private IEmpWelfareMapper empWelfareMapper;
    @Autowired
    private ISalaryMapper salaryMapper;
    @Autowired
    private IWelfareMapper welfareMapper;
    public IDepMapper getDepMapper() {
        return depMapper;
    }
    public void setDepMapper(IDepMapper depMapper) {
        this.depMapper = depMapper;
    }
    public IEmpMapper getEmpMapper() {
        return empMapper;
    }
    public void setEmpMapper(IEmpMapper empMapper) {
        this.empMapper = empMapper;
    }
    public IEmpWelfareMapper getEmpWelfareMapper() {
        return empWelfareMapper;
    }
    public void setEmpWelfareMapper(IEmpWelfareMapper empWelfareMapper) {
        this.empWelfareMapper = empWelfareMapper;
    }
    public ISalaryMapper getSalaryMapper() {
        return salaryMapper;
    }
    public void setSalaryMapper(ISalaryMapper salaryMapper) {
        this.salaryMapper = salaryMapper;
    }
    public IWelfareMapper getWelfareMapper() {
        return welfareMapper;
    }
    public void setWelfareMapper(IWelfareMapper welfareMapper) {
        this.welfareMapper = welfareMapper;
    }

}
