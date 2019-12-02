package com.renshi.renshiManagement.dao.privoder;

import com.renshi.entity.Peixun;

import java.util.Map;

public class PeixunDaoPrivoder {

    public String selectPeixunByPeixunjihua(Map map){
        String sql = "select * from t_peixun where peixunjihua  like '%"+map.get("peixunPeixunjihua")+"%' and deletestatus = 0";
        return sql;
    }
    public String selectPeixunById(int id){
        String sql = "select * from t_peixun where id  = "+id+"";
        return sql;
    }

    public String selectPeixunByUserId(int id){
        String sql = "select * from t_peixun where userid  = "+id+"";
        return sql;
    }
    public String selectAllPeixun(){
        String sql = "select * from t_peixun where deletestatus = 0";
        return sql;
    }
    public String selectPeixun(String peixunPeixunjihua){
        String sql = "select * from t_peixun where peixunjihua = '"+peixunPeixunjihua+"'";
        return sql;
    }
    public String addPeixunInfo(Peixun peixun){
        String sql = " INSERT  into t_peixun(id,peixunjihua,peixunneirong,peixunzhouqi,peixundidian,userid) " +
                "VALUES (#{id},#{peixunjihua},#{peixunneirong},#{peixunzhouqi},#{peixundidian},#{userid})";
        return sql;
    }
    //修改
    public  String updatePeixunInfoById(Peixun peixun){
        String sql = "update t_peixun set peixunjihua =#{peixunjihua},peixunneirong=#{peixunneirong},peixunzhouqi =#{peixunzhouqi},peixundidian =#{peixundidian}" +
                "  where id = #{id} ";
        return sql;
    }
    //删除
    public  String deletePeixunInfoById(int id){
        String sql = "update t_peixun set deletestatus = 1 where id  = "+id+"";
        return  sql;
    }
}
