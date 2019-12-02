package com.renshi.renshiManagement.service.Impl;

import com.renshi.entity.Kaoqin;
import com.renshi.renshiManagement.dao.KaoqinDao;
import com.renshi.renshiManagement.service.KaoqinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KaoqinServiceImpl implements KaoqinService {

    @Autowired
    private KaoqinDao kaoqinDao;

    public List<Kaoqin> selectKaoqinByKLeixing(String kaoqinLeixing){
        List<Kaoqin> kaoqinList2  = kaoqinDao.selectKaoqinByLeixing(kaoqinLeixing);
        return kaoqinList2;
    }
    public Kaoqin selectKaoqin(String kaoqinLeixing){
        Kaoqin kaoqin = kaoqinDao.selectKaoqin(kaoqinLeixing);
        return kaoqin;
    }
    public Kaoqin selectKaoqinById(int id){
        Kaoqin kaoqin = kaoqinDao.selectKaoqinById(id);
        return kaoqin;
    }
    public List<Kaoqin> selectKaoqinByUserId(int id){
        List<Kaoqin> kaoqinList = kaoqinDao.selectKaoqinByUserId(id);
        return kaoqinList;
    }
    public List<Kaoqin> selectAllKaoqin(){
        List<Kaoqin> kaoqinlist = kaoqinDao.selectAllKaoqin();
        return kaoqinlist;
    }
    public void addKaoqinInfo(Kaoqin kaoqin){
        kaoqinDao.addKaoqinInfo(kaoqin);
    }
    public void updateKaoqinInfoById(Kaoqin kaoqin){
        kaoqinDao.updateKaoqinInfoById(kaoqin);
    }
    public void deleteKaoqinInfoById(int id){
        kaoqinDao.deleteKaoqinInfoById(id);
    }
}
