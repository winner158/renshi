package com.renshi.renshiManagement.controller;
import com.alibaba.fastjson.JSONObject;
import com.renshi.entity.Jiangjin;
import com.renshi.entity.Kaoqin;
import com.renshi.entity.User;
import com.renshi.renshiManagement.service.JiangjinService;
import com.renshi.renshiManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class JiangjinController {

    @Autowired
    private JiangjinService jiangjinService;
    @Autowired
    private UserService userService;

    /*
     * 查询:所有奖金信息
     */
    @RequestMapping(value = "/selectJiangjinInfo", method = RequestMethod.GET)
    public String selectJiangjinInfo(Model model,HttpSession session){
        List<Jiangjin> jiangjinList = jiangjinService.selectAllJiangjin();

        for(int i=0;i<jiangjinList.size();i++){
            System.out.println("啦啦啦啦。。。");
            int userid = jiangjinList.get(i).getUserid();            //给userTruename赋值
            String truename = userService.selectUsernameByUserId(userid);
            jiangjinList.get(i).setUserTruename(truename);
        }
        model.addAttribute("jiangjinList", jiangjinList);//将jiangjin显示到页面上
        model.addAttribute("jiangjinpage",true);
        session.setAttribute("jiangjinList", jiangjinList);
        return "jiangjin/selectAllJiangjinInfo";  //jiangjinList显示奖金信息
    }

    /*
     * 查询：按奖金名查询
     */
    @RequestMapping(value = "/selectJiangjinInfoByContent",method = RequestMethod.POST)
    public String selectJiangjinInfoByContent(Model model,Map<String ,Object> map ,
                                           @RequestParam(value = "content")String content){
        String jiangjinContent = content ;
        List<Jiangjin> jiangjinList= jiangjinService.selectJiangjinByJContent(jiangjinContent) ;//查询奖金信息(模糊查询)
        model.addAttribute("jiangjinpage",true);
        if(jiangjinList.size() ==0 ){      //查询失败
            map.put("msg","未查询到相关信息，请重试！");
            jiangjinList = jiangjinService.selectAllJiangjin();
            for(int i=0;i<jiangjinList.size();i++){
                int userid = jiangjinList.get(i).getUserid();            //给userTruename赋值
                String truename = userService.selectUsernameByUserId(userid);
                jiangjinList.get(i).setUserTruename(truename);
            }
            model.addAttribute("jiangjinList",jiangjinList);
            return "jiangjin/selectAllJiangjinInfo";
        }else{                          //查询成功
            for(int i=0;i<jiangjinList.size();i++){
                int userid = jiangjinList.get(i).getUserid();            //给userTruename赋值
                String truename = userService.selectUsernameByUserId(userid);
                jiangjinList.get(i).setUserTruename(truename);
            }
            model.addAttribute("jiangjinList", jiangjinList);
            return "jiangjin/selectAllJiangjinInfo";
        }
    }

    /*
     * 跳到添加奖金页面
     */
    @RequestMapping("/addJiangjinInfo")
    public String addJiangjinInfo(Model model){
        List<User> userList  = userService.selectAllUser();
        model.addAttribute("userList",userList);
        return "jiangjin/addJiangjinInfo";
    }

    /*
     * 添加：奖金信息
     */
    @RequestMapping(value = "/addJiangjinCheckAction", method = RequestMethod.POST)
    public String addJiangjinCheckAction(Model model,
                                         @RequestParam(value = "content") String content,
                                         @RequestParam(value = "riqi") String riqi,
                                         @RequestParam(value = "userTruename") int  userid,
                                         Map<String, Object> map, HttpSession session){
        //若该奖金存在，则不添加；若不存在，则添加
        String jiangjinContent = content ;
        Jiangjin jiangjin = jiangjinService.selectJiangjin(jiangjinContent);
        if(jiangjin == null){
            Jiangjin jiangjin2 = new Jiangjin();
            jiangjin2.setContent(content ) ;
            jiangjin2.setRiqi(riqi ) ;
            jiangjin2.setUserid(userid);
            jiangjin2.setCreatetime(new Date());
            jiangjinService.addJiangjinInfo(jiangjin2);
            map.put("msg", "奖金添加成功！");  //该msg在jiangjinAddList页面显示
            return "redirect:/selectJiangjinInfo";
        }else{
            map.put("msg", "操作失败，该奖金名已经存在！");
            return "jiangjin/addJiangjinInfo";
        }
    }

    /*
     **修改奖金信息
     */
    @RequestMapping("/updateJiangjinInfoById")
    public String updateJiangjinInfoById(Model model,@RequestParam("jiangjinId") String jiangjinId){
        int id = Integer.parseInt(jiangjinId);
        System.out.println("得到的修改奖金信息的id是：。。。"+jiangjinId);
        Jiangjin jiangjin = jiangjinService.selectJiangjinById(id);
        model.addAttribute("jiangjin",jiangjin);
        model.addAttribute("jiangjinpage",true);
        return "jiangjin/updateJiangjinInfo";
    }

    /**
     *修改奖金信息
     * （哪些参数可修改，则可以@RequestParam传入哪些参数；即页面不可修改的参数不传到后台）
     */
    @RequestMapping(value = "/updateJiangjinAction",method = RequestMethod.POST)
    public String updateEditorAction( @RequestParam(value = "jiangjinId") String jiangjinId,
                                      @RequestParam(value = "content", required = false) String content,
                                      @RequestParam(value = "riqi", required = false) String riqi
    ){
        int id = Integer.parseInt(jiangjinId);
        Jiangjin jiangjin = jiangjinService.selectJiangjinById(id);//editor（即state = 0 1 2）
        jiangjin.setContent(content ) ;
        jiangjin.setRiqi(riqi ) ;
        jiangjinService.updateJiangjinInfoById(jiangjin);//更新：修改为新数据  未更新：将查询出的bookCategory重Update(未变)
        return "redirect:/selectJiangjinInfo";
    }

    /**
     * 删除奖金
     */
    @ResponseBody //返回json必须，否则就直接返回Thymeleaf页面
    @RequestMapping(value = "/deleteJiangjinInfoById",  method = RequestMethod.GET)
    public String deleteJiangjinInfoById(@RequestParam("jiangjinId") String jiangjinId){
        int id = Integer.parseInt(jiangjinId);
        String msg = "";
        jiangjinService.deleteJiangjinInfoById(id);
        msg = "删除成功";
        JSONObject result = new JSONObject();
        result.put("msg", msg);
        return result.toJSONString();
    }
    /*
     **查看某个奖金信息
     */
    @RequestMapping("/selectJiangjinInfoById")
    public String selectJiangjinInfoById(Model model,@RequestParam("jiangjinId") String jiangjinId){
        int id = Integer.parseInt(jiangjinId);
        Jiangjin jiangjin = jiangjinService.selectJiangjinById(id);
        //给userTruename赋值
        int userid = jiangjin.getUserid();
        String truename = userService.selectUsernameByUserId(userid);
        jiangjin.setUserTruename(truename);
        model.addAttribute("jiangjinpage",true);
        model.addAttribute("jiangjin",jiangjin);
        return "jiangjin/selectJiangjinInfo";
    }
    /*
     * 查询:员工个人奖金信息
     */
    @RequestMapping(value = "/selectPersonaliangjinInfo", method = RequestMethod.GET)
    public String selectPersonaliangjinInfo(Model model,HttpSession session){
        User u = (User) session.getAttribute("commonUser");//从session中获取user对象
        int id = u.getId();
        List<Jiangjin> jiangjinList   = jiangjinService.selectJiangjinByUserId(id);//查询培训计划信息
        for (int i = 0; i < jiangjinList.size(); i++) {
            int userid = jiangjinList.get(i).getUserid();  //给userTruename赋值
            String truename = userService.selectUsernameByUserId(userid);
            jiangjinList.get(i).setUserTruename(truename);
        }
        model.addAttribute("jiangjinList", jiangjinList);
        model.addAttribute("jiangjinpage2",true);
        return "jiangjin/selectPersonalJiangjinInfo";
    }
}
