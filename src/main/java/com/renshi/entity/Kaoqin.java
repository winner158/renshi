package com.renshi.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: 史煜
 * @Date: 2019/10/15 15:22
 */
@Setter
@Getter
@Data
public class Kaoqin {
    private int id;
    private int deletestatus;//表示是否删除的状态，0表示未删除，1表示删除
    private String leixing;//旷工 迟到  早退  出差  请假
    private String riqi;//日期
    private String beizhu;//备注
    private String kouqian;//扣钱
    private Date createtime;
    private int userid;  //userId是外键
    private String userTruename;//user的真实姓名
}
