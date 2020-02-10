package com.mapper;

import com.po.Salary;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ISalaryMapper {
    //保存薪资
    public int save(Salary sa);
    //根据员工编号查询薪资
    public Salary findSalaryByEid(Integer eid);
    //根据员工编号更新薪资
    public int updateByEid(Salary sa);
    //根据员工编号删除薪资
    public int delByEid(Integer eid);
}
