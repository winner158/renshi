package com.renshi.renshiManagement.service.Impl;

import com.renshi.entity.Qingjia;
import com.renshi.entity.User;
import com.renshi.renshiManagement.dao.QingjiaDao;
import com.renshi.renshiManagement.service.QingjiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 史煜
 * @Date: 2019/11/29 10:39
 */
@Service
public class QingjiaServiceImpl implements QingjiaService {

    @Autowired
    private QingjiaDao qingjiaDao;

    public List<Qingjia> selectQingjiaByUserId(int id){
        List<Qingjia> qingjiaList = qingjiaDao.selectQingjiaByUserId(id);
        return qingjiaList;
    }
    public List<Qingjia> selectAllQingjia(){
        List<Qingjia> qingjiaList = qingjiaDao.selectAllQingjia();
        return qingjiaList;
    }
    public List<Qingjia> selectQingjiaInfoByShenhe(String shenhe){
        List<Qingjia> qingjiaList = qingjiaDao.selectQingjiaInfoByShenhe(shenhe);
        return qingjiaList;
    }
    public void addQingjiaInfo(Qingjia qingjia){
        qingjiaDao.addQingjiaInfo(qingjia);
    }
    public Qingjia selectQingjiaInfoById(int id){
        Qingjia qingjia = qingjiaDao.selectQingjiaInfoById(id);
        return qingjia;
    }

    public void deleteQingjiaInfoById(int id){
        qingjiaDao.deleteQingjiaInfoById(id);
    }

    public void updateQingjiaInfoById(int id){
        qingjiaDao.updateQingjiaInfoById(id);
    }

    public void updateQingjiaNoInfoById(int id){
        qingjiaDao.updateQingjiaNoInfoById(id);
    }
}
