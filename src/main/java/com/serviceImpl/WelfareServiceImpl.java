package com.serviceImpl;

import com.serviceutil.DaoService;
import com.po.Welfare;
import com.service.IWelfareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WelfareServiceImpl implements IWelfareService {
    @Autowired
    private DaoService daoService;

    public DaoService getDaoService() {
        return daoService;
    }

    public void setDaoService(DaoService daoService) {
        this.daoService = daoService;
    }
    @Override
    public List<Welfare> findAll() {

        return daoService.getWelfareMapper().findAll();
    }
}
