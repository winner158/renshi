package com.renshi.renshiManagement.service;

import com.renshi.entity.Bumen;

import java.util.List;

/**
 * @Author: 史煜
 * @Date: 2019/10/20 11:01
 */
public interface BumenService {
    public  List<Bumen> selectAllBumen();
    public List<Bumen> selectBumenByBName(String bumenName);
    public Bumen selectBumen(String bumenName);
    public Bumen selectBumenById(int id);
    public void addBumenInfo(Bumen bumen);
    public void updateBumenInfoById(Bumen bumen);
    public void deleteBumenInfoById(int id );
}


