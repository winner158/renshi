package com.renshi.renshiManagement.service;

import com.renshi.entity.Bumen;
import com.renshi.entity.User;

import java.util.List;

/**
 * @Author: 史煜
 * @Date: 2019/10/14 16:07
 */
public interface UserService {
    public String selectUsername(String username);
    public User selectUser(String username,String password,int role);

    public  User selectUserById(int id);
    public void insertUser(User user);
    public void deleteUserInfoById(int id);
    public List<User>  selectAllUser();
    public void insertAdmin(User user);
    public String selectUsernameByUserId(int userid);
    public void updateUserInfoById(User user);
    public List<User> selectUserInfoByUTB(String username ,String truename,String bumenid);
}
