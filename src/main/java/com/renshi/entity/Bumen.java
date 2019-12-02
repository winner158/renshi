package com.renshi.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: 史煜
 * @Date: 2019/10/14 16:14
 */
@Setter
@Getter
@Data
public class Bumen {
    private int id;
    private int deletestatus;//表示是否删除的状态，0表示未删除，1表示删除
    private String name;//部门名称
    private String jibengongzi;//基本工资
}





