package com.renshi.renshiManagement.dao.privoder;

import com.renshi.entity.Jiangjin;

import java.util.Map;

public class JiangjinDaoPrivoder {

    public String selectJiangjinByContent(Map map){
        String sql = "select * from t_jiangjin where content  like '%"+map.get("jiangjinContent")+"%' and deletestatus = 0";
        return sql;
    }
    public String selectJiangjinById(int id){
        String sql = "select * from t_jiangjin where id  = "+id+"";
        return sql;
    }

    public String selectJiangjinByUserId(int id){
        String sql = "select * from t_jiangjin where userid  = "+id+"";
        return sql;
    }
    public String selectAllJiangjin(){
        String sql = "select * from t_jiangjin where deletestatus = 0";
        return sql;
    }
    public String selectJiangjin(String jiangjinContent){
        String sql = "select * from t_jiangjin where content = '"+jiangjinContent+"'";
        return sql;
    }
    public String addJiangjinInfo(Jiangjin jiangjin){
        String sql = " INSERT  into t_jiangjin(id,content,riqi,createtime,userid) " +
                "VALUES (#{id},#{content},#{riqi},#{createtime},#{userid})";
        return sql;
    }
    //修改
    public  String updateJiangjinInfoById(Jiangjin jiangjin){
        String sql = "update t_jiangjin set content =#{content},riqi=#{riqi}" +
                "  where id = #{id} ";
        return sql;
    }
    //删除
    public  String deleteJiangjinInfoById(int id){
        String sql = "update t_jiangjin set deletestatus = 1 where id  = "+id+"";
        return  sql;
    }
}
