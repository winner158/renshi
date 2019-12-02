package com.renshi.renshiManagement.service;

import com.renshi.entity.Qingjia;
import com.renshi.entity.User;

import java.util.List;

/**
 * @Author: 史煜
 * @Date: 2019/11/29 10:39
 */
public interface QingjiaService {

    public List<Qingjia> selectQingjiaByUserId(int id);

    public List<Qingjia> selectAllQingjia();

    public List<Qingjia> selectQingjiaInfoByShenhe(String shenhe);

    public void addQingjiaInfo(Qingjia qingjia);

    public Qingjia selectQingjiaInfoById(int id);

    public void deleteQingjiaInfoById(int id);

    public void updateQingjiaInfoById(int id);

    public void updateQingjiaNoInfoById(int id);

}
