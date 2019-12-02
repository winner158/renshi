package com.renshi.renshiManagement.service;

import com.renshi.entity.Kaoqin;

import java.util.List;

public interface KaoqinService {
    public  List<Kaoqin> selectAllKaoqin();
    public List<Kaoqin> selectKaoqinByKLeixing(String kaoqinLeixing);
    public Kaoqin selectKaoqin(String kaoqinleixing);
    public Kaoqin selectKaoqinById(int id);
    public List<Kaoqin> selectKaoqinByUserId(int id);
    public void addKaoqinInfo(Kaoqin kaoqin);
    public void updateKaoqinInfoById(Kaoqin kaoqin);
    public void deleteKaoqinInfoById(int id);
}


