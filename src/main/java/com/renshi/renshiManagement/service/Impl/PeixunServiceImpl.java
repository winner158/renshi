package com.renshi.renshiManagement.service.Impl;

import com.renshi.entity.Peixun;
import com.renshi.renshiManagement.dao.PeixunDao;
import com.renshi.renshiManagement.service.PeixunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeixunServiceImpl implements PeixunService {

    @Autowired
    private PeixunDao peixunDao;

    public List<Peixun> selectPeixunByPPeixunjihua(String peixunPeixunjihua){
        List<Peixun> peixunList2  = peixunDao.selectPeixunByPeixunjihua(peixunPeixunjihua);
        return peixunList2;
    }
    public Peixun selectPeixun(String peixunPeixunjihua){
        Peixun peixun = peixunDao.selectPeixun(peixunPeixunjihua);
        return peixun;
    }
    public Peixun selectPeixunById(int id){
        Peixun peixun = peixunDao.selectPeixunById(id);
        return peixun;
    }
    public List<Peixun> selectPeixunByUserId(int id){
        List<Peixun> peixunList = peixunDao.selectPeixunByUserId(id);
        return peixunList;
    }
    public List<Peixun> selectAllPeixun(){
        List<Peixun> peixunlist = peixunDao.selectAllPeixun();
        return peixunlist;
    }
    public void addPeixunInfo(Peixun peixun){
        peixunDao.addPeixunInfo(peixun);
    }
    public void updatePeixunInfoById(Peixun peixun){
        peixunDao.updatePeixunInfoById(peixun);
    }
    public void deletePeixunInfoById(int id){
        peixunDao.deletePeixunInfoById(id);
    }
}
