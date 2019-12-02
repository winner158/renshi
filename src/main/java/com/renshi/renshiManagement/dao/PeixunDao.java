package com.renshi.renshiManagement.dao;
import com.renshi.entity.Peixun;
import com.renshi.renshiManagement.dao.privoder.PeixunDaoPrivoder;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

public interface PeixunDao {

    @SelectProvider(type = PeixunDaoPrivoder.class,method = "selectPeixunByPeixunjihua")
    public List<Peixun> selectPeixunByPeixunjihua(@Param(value = "peixunPeixunjihua") String peixunPeixunjihua);

    @SelectProvider(type = PeixunDaoPrivoder.class,method = "selectPeixunById")
    public Peixun selectPeixunById(@Param(value = "id") int id);

    @SelectProvider(type = PeixunDaoPrivoder.class,method = "selectPeixunByUserId")
    public List<Peixun> selectPeixunByUserId(@Param(value = "id") int id);

    @SelectProvider(type = PeixunDaoPrivoder.class,method = "selectAllPeixun")
    public List<Peixun> selectAllPeixun();

    @SelectProvider(type = PeixunDaoPrivoder.class,method = "selectPeixun")
    public Peixun selectPeixun(@Param(value = "peixunPeixunjihua") String peixunPeixunjihua);

    @InsertProvider(type = PeixunDaoPrivoder.class,method = "addPeixunInfo")
    public void addPeixunInfo(Peixun peixun);

    @UpdateProvider(type = PeixunDaoPrivoder.class,method = "updatePeixunInfoById")
    public void updatePeixunInfoById(Peixun peixun);

    @UpdateProvider(type = PeixunDaoPrivoder.class,method = "deletePeixunInfoById")
    public void deletePeixunInfoById(@Param(value = "id") int id);

}
