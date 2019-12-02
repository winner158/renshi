package com.renshi.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: 史煜
 * @Date: 2019/10/15 15:23
 */
@Setter
@Getter
@Data
public class Qingjia {
    private int id;
    private int deletestatus;//表示是否删除的状态，0表示未删除，1表示删除
    private String content;//请假事由
    private String qingjiariqi;//请假日期
    private String shenhe;//审核状态参数  未审核  ，审核通过  审核不通过
    private Date createtime;
    private int userid;  //userId是外键
    private String userTruename;//user的真实姓名
}
