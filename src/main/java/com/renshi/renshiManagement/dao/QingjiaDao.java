package com.renshi.renshiManagement.dao;

import com.renshi.entity.Peixun;
import com.renshi.entity.Qingjia;
import com.renshi.entity.User;
import com.renshi.renshiManagement.dao.privoder.PeixunDaoPrivoder;
import com.renshi.renshiManagement.dao.privoder.QingjiaDaoPrivoder;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * @Author: 史煜
 * @Date: 2019/11/29 10:39
 */
public interface QingjiaDao {

    @SelectProvider(type = QingjiaDaoPrivoder.class,method = "selectQingjiaByUserId")
    public List<Qingjia> selectQingjiaByUserId(@Param(value = "id") int id);

    @SelectProvider(type = QingjiaDaoPrivoder.class,method = "selectAllQingjia")
    public List<Qingjia> selectAllQingjia();

    @SelectProvider(type = QingjiaDaoPrivoder.class,method = "selectQingjiaInfoByShenhe")
    public List<Qingjia> selectQingjiaInfoByShenhe(@Param(value = "shenhe") String shenhe);

    @InsertProvider(type = QingjiaDaoPrivoder.class,method = "addQingjiaInfo")
    public void addQingjiaInfo(Qingjia qingjia);

    @SelectProvider(type = QingjiaDaoPrivoder.class,method = "selectQingjiaInfoById")
    public Qingjia selectQingjiaInfoById(@Param(value = "id") int id);

    @UpdateProvider(type = QingjiaDaoPrivoder.class,method = "deleteQingjiaInfoById")
    public void deleteQingjiaInfoById(@Param(value = "id") int id);

    @UpdateProvider(type = QingjiaDaoPrivoder.class,method = "updateQingjiaInfoById")
    public void updateQingjiaInfoById(@Param(value = "id") int id);
    @UpdateProvider(type = QingjiaDaoPrivoder.class,method = "updateQingjiaNoInfoById")
    public void updateQingjiaNoInfoById(@Param(value = "id") int id);
}

