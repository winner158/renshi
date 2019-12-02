package com.renshi.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: 史煜
 * @Date: 2019/10/14 16:12
 */
@Setter
@Getter
@Data
public class User {
    private int id;
    private int deletestatus;//表示是否删除的状态，0表示未删除，1表示删除
    private String username;
    private String password;
    private Date createtime;
    private int role;//1表示系统管理员,0表示员工
    private String truename;
    private Bumen bumen;//name = "bumenid"
    private String bumenid;//name = "bumenid"
//    private String bumenname;//部门名
    private String zhiwu;//普通员工 。部门经理  （部门基本工资+2000）
    private String dizhi;//地址
    private String lianxifangshi;//联系方式
    private String wenhuachengdu;//文化程度
    private String zhengzhimianmao;//政治面貌
    private String jiguan;//籍贯
    private String ruzhishijian;//入职时间
    private String xingbie;//性别
}
