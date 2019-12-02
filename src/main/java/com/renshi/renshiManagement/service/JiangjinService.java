package com.renshi.renshiManagement.service;

import com.renshi.entity.Jiangjin;

import java.util.List;

public interface JiangjinService {
    public  List<Jiangjin> selectAllJiangjin();
    public List<Jiangjin> selectJiangjinByJContent(String jiangjinContent);
    public Jiangjin selectJiangjin(String jiangjinContent);
    public Jiangjin selectJiangjinById(int id);
    public    List<Jiangjin> selectJiangjinByUserId(int id);
    public void addJiangjinInfo(Jiangjin jiangjin);
    public void updateJiangjinInfoById(Jiangjin jiangjin);
    public void deleteJiangjinInfoById(int id);
}


