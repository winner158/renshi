package com.renshi.renshiManagement.controller;

import com.alibaba.fastjson.JSONObject;
import com.renshi.entity.Bumen;
import com.renshi.entity.Peixun;
import com.renshi.entity.User;
import com.renshi.renshiManagement.service.BumenService;
import com.renshi.renshiManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class UserController implements CommandLineRunner{


    @Autowired
    private UserService userService;
    @Autowired
    private BumenService bumenService;

    /**
     * 登陆:
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    /*登陆参数验证：
     * 任意用户---登录操作：
     * 判断用户名是否存在，若存在，则判断该用户信息是否正确，若正确，则登录成功；(此处可以加验证码)
     * 											            若不正确，则提示“用户名密码/角色有误！”
     *                    若不存在，则提示“用户名不存在，请先注册！”
     */
    @RequestMapping(value = "/logincheckAction", method = RequestMethod.POST)
    public String logincheckAction(Model model,
                                   @RequestParam(value = "username") String username,
                                   @RequestParam(value = "password") String password,
                                   @RequestParam(value = "role") int role,
                                   Map<String, Object> map, HttpSession session) {
        String username2 = userService.selectUsername(username);
        User user = userService.selectUser(username, password, role);
        if (username2 != null) {
            if (user !=null) {
                if (user.getUsername().equals("admin")) {//管理员
                    session.setAttribute("user", user);
                    model.addAttribute("user",user);
                    return "index";            //登陆成功
                }else{                             //普通员工
                    session.setAttribute("commonUser",user);
                    model.addAttribute("commonUser",user);
                    return "index2";            //登陆成功
                }
            } else {
                map.put("msg", "用户名密码/角色有误，请重试！");
                return "login";
            }
        } else {
            map.put("msg", "用户名不存在，请先注册！");
            return "login";
        }
    }
    /*
     * 注 册
     */
    @RequestMapping("/register")
    public String register(Model model) {
       List<Bumen> bumenList =  bumenService.selectAllBumen();
        model.addAttribute("bumenList", bumenList);
        return "register";
    }

    /*注册功能：
     *判断注册的用户名是否存在，若不存在，则注册成功；
     * 					        若存在，则注册失败
     */
    @RequestMapping(value = "registercheckAction", method = RequestMethod.POST)
    public String registercheckAction(Model model,
                                      @RequestParam(value = "username") String username,
                                      @RequestParam(value = "password") String password,
                                      @RequestParam(value = "truename") String truename,
                                      @RequestParam(value = "bumen") String bumenid,
                                      @RequestParam(value = "zhiwu") String zhiwu,
                                      @RequestParam(value = "dizhi") String dizhi,
                                      @RequestParam(value = "lianxifangshi") String lianxifangshi,
                                      @RequestParam(value = "wenhuachengdu") String wenhuachengdu,
                                      @RequestParam(value = "zhengzhimianmao") String zhengzhimianmao,
                                      @RequestParam(value = "jiguan") String jiguan,
                                      @RequestParam(value = "xingbie") String xingbie,
                                      Map<String, Object> map, HttpSession session) {
        String username2 = userService.selectUsername(username);
        if(username2 == null){           //注册成功
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setTruename(truename);
            user.setXingbie(xingbie);
            user.setZhiwu(zhiwu);
            user.setCreatetime(new Date());
            user.setZhengzhimianmao(zhengzhimianmao);
            user.setWenhuachengdu(wenhuachengdu);
            user.setDizhi(dizhi);
            user.setJiguan(jiguan);
            user.setLianxifangshi(lianxifangshi);
            user.setBumenid(bumenid);
            user.setRole(0);      //默认role值为0
            userService.insertUser(user);  //添加user
            return "registerSuccess";      //返回成功页面
        }else{                              //注册失败
            map.put("msg", "用户名已存在，请重新注册！");
            return "login";                  //返回登陆页面
        }
    }
    /*
     * 系统初始化admin
     */
    @Override
    public void run(String... args) throws Exception {
        init();
    }
    public void init(){
        System.out.println("项目启动了。。。。，执行了方法");
        User admin = userService.selectUser("admin", "111111", 1);
        if (admin == null) {
            System.out.println("admin是null...");
            admin = new User();
            admin.setPassword("111111");
            admin.setRole(1);
            admin.setTruename("admin");
            admin.setUsername("admin");
            userService.insertAdmin(admin);
        }
    }
    /*
     * 退出系统
     */
    @RequestMapping("/loginout")
    public String loginout(Map<String, Object> map, HttpSession session) {
        map.put("msg","确定要退出吗？");
        session.removeAttribute("user");//将user对象从session中移除
        return "login";
    }
    /*
     * 返回站点首页
     */
    @RequestMapping("/returnIndex")
    public String returnIndex(){
        return "index";
    }

    @RequestMapping("/returnIndex2")
    public String returnIndex2(){
        return "index2";
    }


    /*
     * 查询:所有员工信息
     */
    @RequestMapping(value = "/selectAllUserInfo", method = RequestMethod.GET)
    public String selectAllUserInfo(Model model,HttpSession session){
        List<User> userList = userService.selectAllUser();
        List<Bumen> bumenList = bumenService.selectAllBumen();
        model.addAttribute("userList", userList);
        model.addAttribute("bumenList", bumenList);
        model.addAttribute("userpage",true);
        session.setAttribute("userList", userList);
        return "user/selectAllUserInfo";
    }

    /*
     * 查询：按用户名、真实姓名、部门名查询
     */
    @RequestMapping(value = "/selectUserInfoByUTB",method = RequestMethod.POST)
    public String selectUserInfoByUTB(Model model,Map<String ,Object> map ,
                                                @RequestParam(value = "username")String username,
                                                @RequestParam(value = "truename")String truename,
                                                @RequestParam(value = "bumen")String  bumenid){
        List<User> userList= userService.selectUserInfoByUTB(username,truename,bumenid);//查询培训计划信息(模糊查询)
        model.addAttribute("userpage",true);
        if(userList.size() ==0 ){      //查询失败
            map.put("msg","未查询到相关信息，请重试！");
            userList = userService.selectAllUser();
            model.addAttribute("userList", userList);
            List<Bumen> bumenList = bumenService.selectAllBumen();
            model.addAttribute("bumenList", bumenList);
            return "user/selectAllUserInfo";
        }else{                               //查询成功
            List<Bumen> bumenList = bumenService.selectAllBumen();
            model.addAttribute("userList", userList);
            model.addAttribute("bumenList", bumenList);
            return "user/selectAllUserInfo";
        }
    }

    /*
     **修改员工信息
     */
    @RequestMapping("/updateUserInfoById")
    public String updateUserInfoById(Model model,@RequestParam("userId") String userId){
        int id = Integer.parseInt(userId);
        User user = userService.selectUserById(id);
        List<Bumen> bumenList = bumenService.selectAllBumen();
        model.addAttribute("bumenList", bumenList);
        model.addAttribute("user",user);
        model.addAttribute("userpage",true);
        return "user/updateUserInfo";
    }


    /**
     *修改员工的信息
     * （哪些参数可修改，则可以@RequestParam传入哪些参数；即页面不可修改的参数不传到后台）
     */
    @RequestMapping(value = "/updateUserAction",method = RequestMethod.POST)
    public String updateEupdateUserActionditorAction( @RequestParam(value = "userId") String userId,
                                      @RequestParam(value = "truename", required = false) String truename,
                                      @RequestParam(value = "ruzhishijian", required = false) String ruzhishijian,
                                      @RequestParam(value = "lianxifangshi", required = false) String lianxifangshi,
                                      @RequestParam(value = "dizhi", required = false) String dizhi,
                                      @RequestParam(value = "jiguan", required = false) String jiguan,
                                      @RequestParam(value = "wenhuachengdu", required = false) String wenhuachengdu)
    {

        int id = Integer.parseInt(userId);
        User user = userService.selectUserById(id);
        user.setTruename(truename);
        user.setRuzhishijian(ruzhishijian);
        user.setLianxifangshi(lianxifangshi);
        user.setDizhi(dizhi);
        user.setJiguan(jiguan);
        user.setWenhuachengdu(wenhuachengdu);
        userService.updateUserInfoById(user);//更新：修改为新数据  未更新：将查询出的bookCategory重Update(未变)
        return "redirect:/selectAllUserInfo";
    }

    /**
     * 删除员工
     */
    @ResponseBody //返回json必须，否则就直接返回Thymeleaf页面
    @RequestMapping(value = "/deleteUserInfoById",  method = RequestMethod.GET)
    public String deleteUserInfoById(@RequestParam("userId") String userId){
        int id = Integer.parseInt(userId);
        String msg = "";
        userService.deleteUserInfoById(id);
        msg = "删除成功";
        JSONObject result = new JSONObject();
        result.put("msg", msg);
        return result.toJSONString();
    }
    /*
     **查看某个培训计划信息
     */
    @RequestMapping("/selectUserInfoById")
    public String selectUserInfoById(Model model,@RequestParam("userId") String userId){
        int id = Integer.parseInt(userId);
        User user = userService.selectUserById(id);
        model.addAttribute("userpage",true);
        model.addAttribute("user",user);
        return "user/selectUserInfo";
    }


    /*
     * 查询:员工个人信息
     */
    @RequestMapping(value = "/selectPersonalUserInfo", method = RequestMethod.GET)
    public String selectPersonalUserInfo(Model model,HttpSession session){
        User u = (User) session.getAttribute("commonUser");//从session中获取user对象
        int id = u.getId();
        User user = userService.selectUserById(id);    //查询user
        model.addAttribute("user", user);
        model.addAttribute("userpage2",true);
        return "user/selectPersonalUserInfo";
    }
}
