package com.renshi.renshiManagement.dao.privoder;

import com.renshi.entity.Bumen;
import com.renshi.entity.User;

import java.util.Map;

/**
 * @Author: 史煜
 * @Date: 2019/10/14 16:07
 */
public class UserDaoPrivoder {
    public String selectUsername(String username){
        String sql = "select * from t_user" +
                " where username ='"+username+"' ";
        return sql;
    }

    public String selectUser(String username,String password,int role){
        String sql = "select * from t_user" +
                " where username ='"+username+"'and password = '"+password+"' and role = '"+role+"'";
        return sql;
    }

    public String selectUserById(int id){
        String sql = "select * from t_user where id = "+id+" and deletestatus = 0";
        return sql;
    }

    public String selectUserInfoByUTB(String username,String truename,String bumenid){
        String sql = "select * from t_user  " +
                " where username like '%"+username+"%'  and truename like '%"+truename+"%'  and bumenid like '%"+bumenid+"%'";

        return sql;
    }

    public String selectUsernameByUserId(int userid){
        String sql = "select truename from t_user where id = '"+userid+"'";
        return sql;
    }

    public String selectAllUser(){
        String sql = "select * from t_user where role =0";
        return sql;
    }
    public String insertUser(User user){
        String sql = "insert into t_user(username,password,truename,xingbie,zhiwu,createtime,zhengzhimianmao,wenhuachengdu," +
                " ruzhishijian,dizhi,jiguan,lianxifangshi,bumenid,role) VALUES(#{username},#{password},#{truename},#{xingbie},#{zhiwu},#{createtime},#{zhengzhimianmao},#{wenhuachengdu}," +
                " #{createtime},#{dizhi},#{jiguan},#{lianxifangshi},#{bumenid},#{role})";
        return sql;
    }

    public String insertAdmin(User admin){
        String sql = "insert into t_user(username,password,role) VALUES (#{username},#{password},#{role})";
        return sql;
    }

    public String updateUserInfoById(User user){
        String sql = "update t_user set truename = #{truename},ruzhishijian = #{ruzhishijian},lianxifangshi = #{lianxifangshi}," +
                " dizhi = #{dizhi},jiguan =#{jiguan},wenhuachengdu = #{wenhuachengdu} where id = #{id}";
        return sql;
    }

    public  String deleteUserInfoById(int id){
        String sql = "update t_user set deletestatus = 1 where id  = "+id+"";
        return  sql;
    }
}
