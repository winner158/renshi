package com.renshi.renshiManagement.dao;
import com.renshi.entity.Jiangjin;
import com.renshi.renshiManagement.dao.privoder.JiangjinDaoPrivoder;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

public interface JiangjinDao {

    @SelectProvider(type = JiangjinDaoPrivoder.class,method = "selectJiangjinByContent")
    public List<Jiangjin> selectJiangjinByContent(@Param(value = "jiangjinContent") String jiangjinContent);

    @SelectProvider(type = JiangjinDaoPrivoder.class,method = "selectJiangjinById")
    public Jiangjin selectJiangjinById(@Param(value = "id") int id);

    @SelectProvider(type = JiangjinDaoPrivoder.class,method = "selectJiangjinByUserId")
    public  List<Jiangjin> selectJiangjinByUserId(@Param(value = "id") int id);

    @SelectProvider(type = JiangjinDaoPrivoder.class,method = "selectAllJiangjin")
    public List<Jiangjin> selectAllJiangjin();

    @SelectProvider(type = JiangjinDaoPrivoder.class,method = "selectJiangjin")
    public Jiangjin selectJiangjin(@Param(value = "jiangjinContent") String jiangjinContent);

    @InsertProvider(type = JiangjinDaoPrivoder.class,method = "addJiangjinInfo")
    public void addJiangjinInfo(Jiangjin jiangjin);

    @UpdateProvider(type = JiangjinDaoPrivoder.class,method = "updateJiangjinInfoById")
    public void updateJiangjinInfoById(Jiangjin jiangjin);

    @UpdateProvider(type = JiangjinDaoPrivoder.class,method = "deleteJiangjinInfoById")
    public void deleteJiangjinInfoById(@Param(value = "id") int id);

}
