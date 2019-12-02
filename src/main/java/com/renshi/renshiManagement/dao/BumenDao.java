package com.renshi.renshiManagement.dao;
import com.renshi.entity.Bumen;
import com.renshi.entity.User;
import com.renshi.renshiManagement.dao.privoder.BumenDaoPrivoder;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: 史煜
 * @Date: 2019/10/20 11:02
 */
public interface BumenDao {

    @SelectProvider(type = BumenDaoPrivoder.class,method = "selectBumenByName")
    public List<Bumen> selectBumenByName(@Param(value = "bumenName") String bumenName);

    @SelectProvider(type = BumenDaoPrivoder.class,method = "selectBumenById")
    public Bumen selectBumenById(@Param(value = "id") int id);

    @SelectProvider(type = BumenDaoPrivoder.class,method = "selectAllBumen")
    public List<Bumen> selectAllBumen();

    @SelectProvider(type = BumenDaoPrivoder.class,method = "selectBumen")
    public Bumen selectBumen(@Param(value = "bumenName") String bumenName);

    @InsertProvider(type = BumenDaoPrivoder.class,method = "addBumenInfo")
    public void addBumenInfo(Bumen bumen);

    @UpdateProvider(type = BumenDaoPrivoder.class,method = "updateBumenInfoById")
    public void updateBumenInfoById(Bumen bumen);

    @UpdateProvider(type = BumenDaoPrivoder.class,method = "deleteBumenInfoById")
    public void deleteBumenInfoById(@Param(value = "id") int id);

}
