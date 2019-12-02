package com.renshi.renshiManagement.dao;

import com.renshi.entity.Kaoqin;
import com.renshi.renshiManagement.dao.privoder.KaoqinDaoPrivoder;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface KaoqinDao {

    @SelectProvider(type = KaoqinDaoPrivoder.class,method = "selectKaoqinByLeixing")
    public List<Kaoqin> selectKaoqinByLeixing(@Param(value = "kaoqinLeixing") String kaoqinLeixing);

    @SelectProvider(type = KaoqinDaoPrivoder.class,method = "selectKaoqinById")
    public Kaoqin selectKaoqinById(@Param(value = "id") int id);

    @SelectProvider(type = KaoqinDaoPrivoder.class,method = "selectKaoqinByUserId")
    public  List<Kaoqin> selectKaoqinByUserId(@Param(value = "id") int id);

    @SelectProvider(type = KaoqinDaoPrivoder.class,method = "selectAllKaoqin")
    public List<Kaoqin> selectAllKaoqin();

    @SelectProvider(type = KaoqinDaoPrivoder.class,method = "selectKaoqin")
    public Kaoqin selectKaoqin(@Param(value = "kaoqinLeixing") String kaoqinLeixing);

    @InsertProvider(type = KaoqinDaoPrivoder.class,method = "addKaoqinInfo")
    public void addKaoqinInfo(Kaoqin kaoqin);

    @UpdateProvider(type = KaoqinDaoPrivoder.class,method = "updateKaoqinInfoById")
    public void updateKaoqinInfoById(Kaoqin kaoqin);

    @UpdateProvider(type = KaoqinDaoPrivoder.class,method = "deleteKaoqinInfoById")
    public void deleteKaoqinInfoById(@Param(value = "id") int id);

}
