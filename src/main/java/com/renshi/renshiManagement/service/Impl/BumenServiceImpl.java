package com.renshi.renshiManagement.service.Impl;

import com.renshi.entity.Bumen;
import com.renshi.renshiManagement.dao.BumenDao;
import com.renshi.renshiManagement.service.BumenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 史煜
 * @Date: 2019/10/20 11:02
 */
@Service
public class BumenServiceImpl implements BumenService {

    @Autowired
    private BumenDao bumenDao;

    public List<Bumen> selectBumenByBName(String bumenName){
        List<Bumen> bumenList2  = bumenDao.selectBumenByName(bumenName);
       return bumenList2;
    }
    public Bumen selectBumen(String bumenName){
        Bumen bumen = bumenDao.selectBumen(bumenName);
        return bumen;
    }
    public Bumen selectBumenById(int id){
        Bumen bumen = bumenDao.selectBumenById(id);
        return bumen;
    }
    public List<Bumen> selectAllBumen(){
        List<Bumen> bumenlist = bumenDao.selectAllBumen();
        return bumenlist;
    }
    public void addBumenInfo(Bumen bumen){
        bumenDao.addBumenInfo(bumen);
    }
    public void updateBumenInfoById(Bumen bumen){
        bumenDao.updateBumenInfoById(bumen);
    }
    public void deleteBumenInfoById(int id){
        bumenDao.deleteBumenInfoById(id);
    }
}
