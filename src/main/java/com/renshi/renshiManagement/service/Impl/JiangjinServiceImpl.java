package com.renshi.renshiManagement.service.Impl;

import com.renshi.entity.Jiangjin;
import com.renshi.renshiManagement.dao.JiangjinDao;
import com.renshi.renshiManagement.service.JiangjinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JiangjinServiceImpl implements JiangjinService {

    @Autowired
    private JiangjinDao jiangjinDao;

    public List<Jiangjin> selectJiangjinByJContent(String jiangjinContent){
        List<Jiangjin> jiangjinList2  = jiangjinDao.selectJiangjinByContent(jiangjinContent);
        return jiangjinList2;
    }
    public Jiangjin selectJiangjin(String jiangjinContent){
        Jiangjin jiangjin = jiangjinDao.selectJiangjin(jiangjinContent);
        return jiangjin;
    }
    public Jiangjin selectJiangjinById(int id){
        Jiangjin jiangjin = jiangjinDao.selectJiangjinById(id);
        return jiangjin;
    }

    public    List<Jiangjin> selectJiangjinByUserId(int id){
        List<Jiangjin> jiangjinList = jiangjinDao.selectJiangjinByUserId(id);
        return jiangjinList;
    }
    public List<Jiangjin> selectAllJiangjin(){
        List<Jiangjin> jiangjinlist = jiangjinDao.selectAllJiangjin();
        return jiangjinlist;
    }
    public void addJiangjinInfo(Jiangjin jiangjin){
        jiangjinDao.addJiangjinInfo(jiangjin);
    }
    public void updateJiangjinInfoById(Jiangjin jiangjin){
        jiangjinDao.updateJiangjinInfoById(jiangjin);
    }
    public void deleteJiangjinInfoById(int id){
        jiangjinDao.deleteJiangjinInfoById(id);
    }
}
