package com.mapper;

import com.po.Dep;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IDepMapper {

    public List<Dep> findAll();
}