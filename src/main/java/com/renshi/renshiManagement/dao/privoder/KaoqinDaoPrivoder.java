package com.renshi.renshiManagement.dao.privoder;

import com.renshi.entity.Kaoqin;

import java.util.Map;

public class KaoqinDaoPrivoder {

    public String selectKaoqinByLeixing(Map map){
        String sql = "select * from t_kaoqin where leixing  like '%"+map.get("kaoqinLeixing")+"%' and deletestatus = 0";
        return sql;
    }
    public String selectKaoqinById(int id){
        String sql = "select * from t_kaoqin where id  = "+id+"";
        return sql;
    }
    public String selectKaoqinByUserId(int id){
        String sql = "select * from t_kaoqin where userid  = "+id+"";
        return sql;
    }
    public String selectAllKaoqin(){
        String sql = "select * from t_kaoqin where deletestatus = 0";
        return sql;
    }
    public String selectKaoqin(String kaoqinLeixing){
        String sql = "select * from t_kaoqin where leixing = '"+kaoqinLeixing+"'";
        return sql;
    }
    public String addKaoqinInfo(Kaoqin kaoqin){
        String sql = " INSERT  into t_kaoqin(id,leixing,createtime,beizhu,userid) " +
                "VALUES (#{id},#{leixing},#{riqi},#{beizhu},#{userid})";
        return sql;
    }
    //修改
    public  String updateKaoqinInfoById(Kaoqin kaoqin){
        String sql = "update t_kaoqin set leixing =#{leixing},riqi=#{riqi},beizhu=#{beizhu}" +
                "  where id = #{id} ";
        return sql;
    }
    //删除
    public  String deleteKaoqinInfoById(int id){
        String sql = "update t_kaoqin set deletestatus = 1 where id  = "+id+"";
        return  sql;
    }
}
