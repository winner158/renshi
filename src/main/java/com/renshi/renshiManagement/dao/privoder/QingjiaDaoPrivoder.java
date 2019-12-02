package com.renshi.renshiManagement.dao.privoder;

import com.renshi.entity.Peixun;
import com.renshi.entity.Qingjia;

/**
 * @Author: 史煜
 * @Date: 2019/11/29 10:39
 */
public class QingjiaDaoPrivoder {

    public String selectQingjiaByUserId(int id){
        String sql = "select * from t_qingjia where userid = "+id+" and deletestatus = 0";
        return sql;
    }

    public String selectAllQingjia(){
        String sql = "select * from t_qingjia where deletestatus = 0";
        return sql;
    }

    public String selectQingjiaInfoByShenhe(String shenhe){
        String sql = "select * from t_qingjia where shenhe = '"+shenhe+"' and deletestatus = 0";
        return sql;
    }
    public String addQingjiaInfo(Qingjia qingjia){
        String sql = " INSERT  into t_qingjia(id,content,qingjiariqi,createtime,shenhe,userid) " +
                "VALUES (#{id},#{content},#{qingjiariqi},#{createtime},#{shenhe},#{userid})";
        return sql;
    }

    public String selectQingjiaInfoById(int id){
        String sql = "select * from t_qingjia where id = "+id+" and deletestatus = 0";
        return sql;
    }
    //删除
    public  String deleteQingjiaInfoById(int id){
        String sql = "update t_qingjia set deletestatus = 1 where id  = "+id+" and shenhe = '审核未通过' ";
        return  sql;
    }

    //更新shenhe
    public String updateQingjiaInfoById(int id){
        String sql = "update t_qingjia set shenhe = '审核通过' where id  = "+id+" ";
        return sql;
    }
    //更新shenhe不通过
    public String updateQingjiaNoInfoById(int id){
        String sql = "update t_qingjia set shenhe = '审核未通过' where id  = "+id+" ";
        return sql;
    }
}

