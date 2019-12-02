package com.renshi.renshiManagement.service.Impl;

import com.renshi.entity.User;
import com.renshi.renshiManagement.dao.UserDao;
import com.renshi.renshiManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 史煜
 * @Date: 2019/10/14 16:07
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public User selectUser(String username,String password,int role){
        User user = userDao.selectUser(username,password,role);
        return user;
    }

    public String selectUsername(String username){
        String username2 = userDao.selectUsername(username);
        return username2;
    }

    public  User selectUserById(int id){
        User user = userDao.selectUserById(id);
        return user;
    }
    public void insertUser(User user){
        userDao.insertUser(user);
    }

    public void deleteUserInfoById(int id){
        userDao.deleteUserInfoById(id);
    }
    public List<User>  selectAllUser(){
        List<User> userList = userDao.selectAllUser();
        return userList;
    }
    public void insertAdmin(User user){
        userDao.insertAdmin(user);
    }
    public String selectUsernameByUserId(int userid ){
        String userTruename = userDao.selectUsernameByUserId(userid);
        return userTruename;
    }
    public void updateUserInfoById(User user){
        userDao.updateUserInfoById(user);
    }
    public List<User>  selectUserInfoByUTB(String username ,String truename,String bumenid){
        List<User> userList = userDao.selectUserInfoByUTB(username ,truename,bumenid);
        return userList;
    }
}
