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
public class Peixun {
    private int id;
    private int deletestatus;//表示是否删除的状态，0表示未删除，1表示删除
    private String peixunjihua;//培训计划
    private String peixunneirong;//培训内容
    private String peixunzhouqi;//培训周期
    private String peixundidian;//培训地点
    private Date createtime;
    private int userid;  //userId是外键
    private String userTruename;//user的真实姓名
}
