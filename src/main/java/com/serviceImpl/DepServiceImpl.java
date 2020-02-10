package com.serviceImpl;

import com.serviceutil.DaoService;
import com.po.*;
import com.service.IDepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
@Transactional
public class DepServiceImpl implements IDepService {
    @Autowired
    private DaoService daoService;

    public DaoService getDaoService() {
        return daoService;
    }

    public void setDaoService(DaoService daoService) {
        this.daoService = daoService;
    }

    @Override
    public List<Dep> findAll() {
        return daoService.getDepMapper().findAll();
    }
}
