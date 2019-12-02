package com.renshi.renshiManagement.controller;
import com.alibaba.fastjson.JSONObject;
import com.renshi.entity.Kaoqin;
import com.renshi.entity.Peixun;
import com.renshi.entity.User;
import com.renshi.renshiManagement.service.KaoqinService;
import com.renshi.renshiManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class KaoqinController {

    @Autowired
    private KaoqinService kaoqinService;
    @Autowired
    private UserService userService;

    /*
     * 查询:所有考勤信息
     */
    @RequestMapping(value = "/selectKaoqinInfo", method = RequestMethod.GET)
    public String selectKaoqinInfo(Model model,HttpSession session){
        List<Kaoqin> kaoqinList = kaoqinService.selectAllKaoqin();
        for(int i=0;i<kaoqinList.size();i++){
            int userid = kaoqinList.get(i).getUserid();            //给userTruename赋值
            String truename = userService.selectUsernameByUserId(userid);
            kaoqinList.get(i).setUserTruename(truename);
        }
        model.addAttribute("kaoqinList", kaoqinList);//将kaoqin显示到页面上
        model.addAttribute("kaoqinpage",true);
        session.setAttribute("kaoqinList", kaoqinList);
        return "kaoqin/selectAllKaoqinInfo";  //kaoqinList显示考勤信息
    }

    /*
     * 查询：按考勤类型查询
     */
    @RequestMapping(value = "/selectKaoqinInfoByLeixing",method = RequestMethod.POST)
    public String selectKaoqinInfoByLeixing(Model model,Map<String ,Object> map ,
                                            @RequestParam(value = "leixing")String leixing){
        String kaoqinLeixing = leixing ;
        System.out.println("考勤类型是："+kaoqinLeixing);
        List<Kaoqin> kaoqinList= kaoqinService.selectKaoqinByKLeixing(kaoqinLeixing) ;//查询考勤信息(模糊查询)
        model.addAttribute("kaoqinpage",true);
        if(kaoqinList.size() ==0 ){      //查询失败
            map.put("msg","未查询到相关信息，请重试！");
            kaoqinList = kaoqinService.selectAllKaoqin();
            for(int i=0;i<kaoqinList.size();i++){
                int userid = kaoqinList.get(i).getUserid();            //给userTruename赋值
                String truename = userService.selectUsernameByUserId(userid);
                kaoqinList.get(i).setUserTruename(truename);
            }
            model.addAttribute("kaoqinList",kaoqinList);
            return "kaoqin/selectAllKaoqinInfo";
        }else{                          //查询成功
            for(int i=0;i<kaoqinList.size();i++){
                int userid = kaoqinList.get(i).getUserid();            //给userTruename赋值
                String truename = userService.selectUsernameByUserId(userid);
                kaoqinList.get(i).setUserTruename(truename);
            }
            model.addAttribute("kaoqinList", kaoqinList);
            return "kaoqin/selectAllKaoqinInfo";
        }
    }

    /*
     * 跳到添加考勤页面
     */
    @RequestMapping("/addKaoqinInfo")
    public String addKaoqinInfo(Model model){
        List<User> userList  = userService.selectAllUser();
        model.addAttribute("userList",userList);
        return "kaoqin/addKaoqinInfo";
    }

    /*
     * 添加：考勤信息
     */
    @RequestMapping(value = "/addKaoqinCheckAction", method = RequestMethod.POST)
    public String addKaoqinCheckAction(Model model,
                                       @RequestParam(value = "leixing") String leixing,
                                       @RequestParam(value = "riqi") String riqi,
                                       @RequestParam(value = "beizhu") String beizhu,
                                       @RequestParam(value = "userTruename") int  userid,
                                       Map<String, Object> map, HttpSession session){
        //若该考勤存在，则不添加；若不存在，则添加
        String kaoqinLeixing = leixing ;
        System.out.println("输入的考勤名是-..."+kaoqinLeixing);
        Kaoqin kaoqin = kaoqinService.selectKaoqin(kaoqinLeixing);
        if(kaoqin == null){
            Kaoqin kaoqin2 = new Kaoqin();
            kaoqin2.setLeixing(leixing ) ;
            kaoqin2.setRiqi(riqi ) ;
            kaoqin2.setBeizhu(beizhu ) ;
            kaoqin2.setUserid(userid);
            kaoqinService.addKaoqinInfo(kaoqin2);
            map.put("msg", "考勤添加成功！");  //该msg在kaoqinAddList页面显示
            return "redirect:/selectKaoqinInfo";
        }else{
            map.put("msg", "操作失败，该考勤名已经存在！");
            return "kaoqin/addKaoqinInfo";
        }
    }

    /*
     **修改考勤信息
     */
    @RequestMapping("/updateKaoqinInfoById")
    public String updateKaoqinInfoById(Model model,@RequestParam("kaoqinId") String kaoqinId){
        int id = Integer.parseInt(kaoqinId);
        System.out.println("得到的修改考勤信息的id是：。。。"+kaoqinId);
        Kaoqin kaoqin = kaoqinService.selectKaoqinById(id);
        model.addAttribute("kaoqin",kaoqin);
        model.addAttribute("kaoqinpage",true);
        return "kaoqin/updateKaoqinInfo";
    }

    /**
     *修改考勤信息
     * （哪些参数可修改，则可以@RequestParam传入哪些参数；即页面不可修改的参数不传到后台）
     */
    @RequestMapping(value = "/updateKaoqinAction",method = RequestMethod.POST)
    public String updateEditorAction( @RequestParam(value = "kaoqinId") String kaoqinId,
                                      @RequestParam(value = "leixing", required = false) String leixing,
                                      @RequestParam(value = "riqi", required = false) String riqi,
                                      @RequestParam(value = "beizhu", required = false) String beizhu
    ){
        int id = Integer.parseInt(kaoqinId);
        Kaoqin kaoqin = kaoqinService.selectKaoqinById(id);//editor（即state = 0 1 2）
        kaoqin.setLeixing(leixing ) ;
        kaoqin.setRiqi(riqi ) ;
        kaoqin.setBeizhu(beizhu ) ;
        kaoqinService.updateKaoqinInfoById(kaoqin);//更新：修改为新数据  未更新：将查询出的bookCategory重Update(未变)
        return "redirect:/selectKaoqinInfo";
    }

    /**
     * 删除考勤
     */
    @ResponseBody //返回json必须，否则就直接返回Thymeleaf页面
    @RequestMapping(value = "/deleteKaoqinInfoById",  method = RequestMethod.GET)
    public String deleteKaoqinInfoById(@RequestParam("kaoqinId") String kaoqinId){
        int id = Integer.parseInt(kaoqinId);
        String msg = "";
        kaoqinService.deleteKaoqinInfoById(id);
        msg = "删除成功";
        JSONObject result = new JSONObject();
        result.put("msg", msg);
        return result.toJSONString();
    }
    /*
     **查看某个考勤信息
     */
    @RequestMapping("/selectKaoqinInfoById")
    public String selectKaoqinInfoById(Model model,@RequestParam("kaoqinId") String kaoqinId){
        int id = Integer.parseInt(kaoqinId);
        Kaoqin kaoqin = kaoqinService.selectKaoqinById(id);
        //给userTruename赋值
        int userid = kaoqin.getUserid();
        String truename = userService.selectUsernameByUserId(userid);
        kaoqin.setUserTruename(truename);
        model.addAttribute("kaoqinpage",true);
        model.addAttribute("kaoqin",kaoqin);
        return "kaoqin/selectKaoqinInfo";
    }

    /*
     * 查询:员工个人考勤信息
     */
    @RequestMapping(value = "/selectPersonalKaoqinInfo", method = RequestMethod.GET)
    public String selectPersonalKaoqinInfo(Model model,HttpSession session){
        User u = (User) session.getAttribute("commonUser");//从session中获取user对象
        int id = u.getId();
        List<Kaoqin> kaoqinList = kaoqinService.selectKaoqinByUserId(id);//查询考勤信息
        for (int i = 0; i < kaoqinList.size(); i++) {
            int userid = kaoqinList.get(i).getUserid();  //给userTruename赋值
            String truename = userService.selectUsernameByUserId(userid);
            kaoqinList.get(i).setUserTruename(truename);
        }
        model.addAttribute("kaoqinList", kaoqinList);
        model.addAttribute("kaoqinpage2",true);
        return "kaoqin/selectPersonalKaoqinInfo";
    }
}
