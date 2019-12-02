package com.renshi.renshiManagement.dao.privoder;

import com.renshi.entity.Bumen;

import java.util.Map;

/**
 * @Author: 史煜
 * @Date: 2019/10/20 11:02
 */
public class BumenDaoPrivoder {

    public String selectBumenByName(Map map){
        String sql = "select * from t_bumen where name  like '%"+map.get("bumenName")+"%' and deletestatus = 0";
        return sql;
    }
    public String selectBumenById(int id){
        String sql = "select * from t_bumen where id  = "+id+"";
        return sql;
    }
    public String selectAllBumen(){
        String sql = "select * from t_bumen where deletestatus = 0";
        return sql;
    }
    public String selectBumen(String bumenName){
        String sql = "select * from t_bumen where name = '"+bumenName+"'";
        return sql;
    }
    public String addBumenInfo(Bumen bumen){
        String sql = " INSERT  into t_bumen(id,name,jibengongzi) " +
                "VALUES (#{id},#{name},#{jibengongzi})";
        return sql;
    }
    //修改
    public  String updateBumenInfoById(Bumen bumen){
        String sql = "update t_bumen set name =#{name},jibengongzi=#{jibengongzi}" +
                "  where id = #{id} ";
        return sql;
    }
    //删除
    public  String deleteBumenInfoById(int id){
        String sql = "update t_bumen set deletestatus = 1 where id  = "+id+"";
        return  sql;
    }
}
