package com.renshi.renshiManagement.dao;

import com.renshi.entity.Bumen;
import com.renshi.entity.Peixun;
import com.renshi.entity.User;
import com.renshi.renshiManagement.dao.privoder.BumenDaoPrivoder;
import com.renshi.renshiManagement.dao.privoder.PeixunDaoPrivoder;
import com.renshi.renshiManagement.dao.privoder.UserDaoPrivoder;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: 史煜
 * @Date: 2019/10/14 16:07
 */
public interface UserDao {

    //查询：通过username进行查询的user:
    @SelectProvider(type = UserDaoPrivoder.class,method = "selectUsername")
    public String  selectUsername(@RequestParam(value = "username") String username);

    //查询：通过username、password、role查询user:
    @SelectProvider(type = UserDaoPrivoder.class,method = "selectUser")
    public User selectUser(@RequestParam(value = "username") String username,
                           @RequestParam(value = "password") String password,
                            @RequestParam(value = "role") int role);



    @SelectProvider(type = UserDaoPrivoder.class,method = "selectUserById")
    public User  selectUserById(@Param(value = "id") int id);
    //查询
    @SelectProvider(type = UserDaoPrivoder.class,method = "selectUsernameByUserId")
    public String selectUsernameByUserId(@Param("userid") int userid);


    //查询所有user:
    @SelectProvider(type = UserDaoPrivoder.class,method = "selectAllUser")
    public List<User> selectAllUser();

    //根据用户名、真实姓名、部门名查询user:
    @SelectProvider(type = UserDaoPrivoder.class,method = "selectUserInfoByUTB")
    public List<User> selectUserInfoByUTB(@RequestParam(value = "username", required = false) String username,
                                          @RequestParam(value = "truename", required = false) String truename,
                                          @RequestParam(value = "bumenid", required = false) String bumenid);

    //添加用户：
    @InsertProvider(type = UserDaoPrivoder.class,method = "insertUser")
    public void insertUser(User user);

    //添加用户：
    @InsertProvider(type = UserDaoPrivoder.class,method = "insertAdmin")
    public void insertAdmin(User admin);

    //更新
    @UpdateProvider(type = UserDaoPrivoder.class,method = "updateUserInfoById")
    public void updateUserInfoById(User user);

    //删除
    @UpdateProvider(type = UserDaoPrivoder.class,method = "deleteUserInfoById")
    public void deleteUserInfoById(@Param(value = "id") int id);


}
