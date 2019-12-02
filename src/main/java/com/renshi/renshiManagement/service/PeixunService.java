package com.renshi.renshiManagement.service;

import com.renshi.entity.Peixun;

import java.util.List;

public interface PeixunService {
    public  List<Peixun> selectAllPeixun();
    public List<Peixun> selectPeixunByPPeixunjihua(String peixunPeixunjihua);
    public Peixun selectPeixun(String peixunPeixunjihua);
    public Peixun selectPeixunById(int id);
    public List<Peixun> selectPeixunByUserId(int id);
    public void addPeixunInfo(Peixun peixun);
    public void updatePeixunInfoById(Peixun peixun);
    public void deletePeixunInfoById(int id);
}


