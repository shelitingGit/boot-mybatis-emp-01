package com.mapper;

import com.po.Welfare;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IWelfareMapper {
    public List<Welfare> findAll();
}
