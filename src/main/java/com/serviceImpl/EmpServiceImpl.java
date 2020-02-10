package com.serviceImpl;

import com.serviceutil.DaoService;
import com.po.*;
import com.service.IEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmpServiceImpl implements IEmpService {
    @Autowired
    private DaoService daoService;

    public DaoService getDaoService() {
        return daoService;
    }

    public void setDaoService(DaoService daoService) {
        this.daoService = daoService;
    }

    @Override
    public boolean save(Emp emp) {
        System.out.println("emp-------empbizimpl"+emp.toString());
        int code=daoService.getEmpMapper().save(emp);
        if(code>0){
            //获取刚才保存的员工对象id
            Integer eid=daoService.getEmpMapper().findMaxEid();
            /***保存薪资**/
            Salary sa=new Salary(eid,emp.getEmoney());
            daoService.getSalaryMapper().save(sa);
            /***保存薪资end**/
            /***保存员工福利数据**/
            String[] wids=emp.getWids();
            if(wids!=null&&wids.length>0){
                for(int i=0;i<wids.length;i++){
                    EmpWelfare ewf=new EmpWelfare(eid,Integer.parseInt(wids[i]));
                    daoService.getEmpWelfareMapper().save(ewf);
                }
            }
            /***保存员工福利数据end**/
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Emp emp) {
        int code=daoService.getEmpMapper().update(emp);
        if(code>0){
            /***更新薪资**/
            //获取原来薪资
            Salary oldsa=daoService.getSalaryMapper().findSalaryByEid(emp.getEid());
            if(oldsa!=null&&oldsa.getEmoney()>0){
                oldsa.setEmoney(emp.getEmoney());
                daoService.getSalaryMapper().updateByEid(oldsa);
            }else{
                Salary sa=new Salary(emp.getEid(),emp.getEmoney());
                daoService.getSalaryMapper().save(sa);
            }
            /***更新薪资end**/
            /***更新员工福利数据**/
            //获取原来的员工福利
            List<Welfare> lswf=daoService.getEmpWelfareMapper().findByEid(emp.getEid());
            if(lswf!=null&&lswf.size()>0){
                //删除员工原有福利
                daoService.getEmpWelfareMapper().delByEid(emp.getEid());
            }
            String[] wids=emp.getWids();
            if(wids!=null&&wids.length>0){
                for(int i=0;i<wids.length;i++){
                    EmpWelfare ewf=new EmpWelfare(emp.getEid(),Integer.parseInt(wids[i]));
                    daoService.getEmpWelfareMapper().save(ewf);
                }
            }

            /***更新员工福利数据end**/
            return true;
        }
        return false;
    }

    @Override
    public boolean delById(Integer eid) {
        //先删除从表数据
        daoService.getSalaryMapper().delByEid(eid);
        daoService.getEmpWelfareMapper().delByEid(eid);
        //删除员工数据
        int code=daoService.getEmpMapper().delById(eid);
        if(code>0){
            return true;
        }
        return false;
    }

    @Override
    public Emp findById(Integer eid) {
        //获取员工对象
        Emp oldemp=daoService.getEmpMapper().findById(eid);
        System.out.println("s:"+oldemp.toString());
        //获取该员工薪资
        Salary oldsa=daoService.getSalaryMapper().findSalaryByEid(eid);
        if(oldsa!=null&&oldsa.getEmoney()>0){
            System.out.println("454545454");
            oldemp.setEmoney(oldsa.getEmoney());
        }
        //获取该员工福利集合
        List<Welfare> lswf=daoService.getEmpWelfareMapper().findByEid(eid);
        System.out.println("lswf:"+lswf.size());
        if(lswf!=null&&lswf.size()>0){
            //获取该员工原有福利id方便复选框选中
            String[] wids=new String[lswf.size()];
            for(int i=0;i<wids.length;i++){
                Welfare wf=lswf.get(i);
                wids[i]=wf.getWid().toString();
            }
            oldemp.setWids(wids);
            oldemp.setLswf(lswf);
        }
        System.out.println("s:"+oldemp.toString());
        return oldemp;
    }

    @Override
    public List<Emp> findPageAll(PageBean pb) {
        if(pb==null){
            pb=new PageBean();
        }
        return daoService.getEmpMapper().findPageAll(pb);
    }

    @Override
    public int findMaxRows() {

        return daoService.getEmpMapper().findMaxRows();
    }

}
