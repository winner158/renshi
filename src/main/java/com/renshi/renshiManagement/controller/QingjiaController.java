package com.renshi.renshiManagement.controller;

import com.alibaba.fastjson.JSONObject;
import com.renshi.entity.*;
import com.renshi.renshiManagement.service.KaoqinService;
import com.renshi.renshiManagement.service.QingjiaService;
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

/**
 * @Author: 史煜
 * @Date: 2019/11/29 10:38
 */
@Controller
public class QingjiaController {

    @Autowired
    private QingjiaService qingjiaService;

    @Autowired
    private UserService userService;

    @Autowired
    private KaoqinService kaoqinService;


    /*
     * 查询:员工个人请假信息
     */
    @RequestMapping(value = "/selectPersonalQingjiaInfo", method = RequestMethod.GET)
    public String selectPersonalQingjiaInfo(Model model, HttpSession session) {
        User u = (User) session.getAttribute("commonUser");//从session中获取user对象
        int id = u.getId();
        List<Qingjia> qingjiaList = qingjiaService.selectQingjiaByUserId(id);
        for (int i = 0; i < qingjiaList.size(); i++) {
            int userid = qingjiaList.get(i).getUserid();  //给userTruename赋值
            String truename = userService.selectUsernameByUserId(userid);
            qingjiaList.get(i).setUserTruename(truename);
        }
        model.addAttribute("qingjiaList", qingjiaList);
        model.addAttribute("qingjiapage", true);
        session.setAttribute("qingjiaList", qingjiaList);
        return "qingjia/selectPersonalQingjiaInfo";
    }

    /*
     * 查询：按审核查询
     */
    @RequestMapping(value = "/selectQingjiaInfoShenhe", method = RequestMethod.POST)
    public String selectQingjiaInfoShenhe(Model model, Map<String, Object> map,
                                          @RequestParam(value = "shenhe") String shenhe1) {
        String shenhe = shenhe1;
        List<Qingjia> qingjiaList = qingjiaService.selectQingjiaInfoByShenhe(shenhe);//查询培训计划信息(模糊查询)
        model.addAttribute("qingjiapage", true);
        if (qingjiaList.size() == 0) {      //查询失败
            map.put("msg", "未查询到相关信息，请重试！");
            qingjiaList = qingjiaService.selectAllQingjia();
            for (int i = 0; i < qingjiaList.size(); i++) {
                int userid = qingjiaList.get(i).getUserid();            //给userTruename赋值
                String truename = userService.selectUsernameByUserId(userid);
                qingjiaList.get(i).setUserTruename(truename);
            }
            model.addAttribute("qingjiaList", qingjiaList);
            return "qingjia/selectAllQingjiaInfo";
        } else {                          //查询成功
            for (int i = 0; i < qingjiaList.size(); i++) {
                int userid = qingjiaList.get(i).getUserid();            //给userTruename赋值
                String truename = userService.selectUsernameByUserId(userid);
                qingjiaList.get(i).setUserTruename(truename);
            }
            model.addAttribute("qingjiaList", qingjiaList);
            return "qingjia/selectAllQingjiaInfo";
        }
    }

    /*
     * 跳到添加请假页面
     */
    @RequestMapping("/addQingjiaInfo")
    public String addQingjiaInfo(Model model) {

        return "qingjia/addQingjiaInfo";
    }

    /*
     * 添加：请假信息
     */
    @RequestMapping(value = "/addQingjiaCheckAction", method = RequestMethod.POST)
    public String addQingjiaCheckAction(Model model,
                                        @RequestParam(value = "content") String content,
                                        @RequestParam(value = "qingjiariqi") String qingjiariqi,
                                        Map<String, Object> map, HttpSession session) {
        User u = (User) session.getAttribute("commonUser");//从session中获取commonUser对象
        Qingjia qingjia = new Qingjia();
        qingjia.setContent(content);
        qingjia.setQingjiariqi(qingjiariqi);
        qingjia.setUserid(u.getId());
        qingjia.setShenhe("审核未通过");//添加时设置审核字段时，首先设置为“未审核”
        qingjia.setCreatetime(new Date());
        qingjiaService.addQingjiaInfo(qingjia);
        map.put("msg", "请假信息添加成功！");  //该msg在peixunAddList页面显示
        return "redirect:/selectPersonalQingjiaInfo";

    }

    /*
     **查看个人的请假信息
     */
    @RequestMapping("/selectQingjiaInfoById")
    public String selectQingjiaInfoById(Model model, @RequestParam("qingjiaId") String qingjiaId) {
        int id = Integer.parseInt(qingjiaId);
        Qingjia qingjia = qingjiaService.selectQingjiaInfoById(id);
        //给userTruename赋值
        int userid = qingjia.getUserid();
        String truename = userService.selectUsernameByUserId(userid);
        qingjia.setUserTruename(truename);
        model.addAttribute("qingjiapage", true);
        model.addAttribute("qingjia", qingjia);
        return "qingjia/selectQingjiaInfo";
    }
    /**
     * 删除请假(未审核时删除，审核了就不能删除了!!!!)
     */
    @ResponseBody //返回json必须，否则就直接返回Thymeleaf页面
    @RequestMapping(value = "/deleteQingjiaInfoById",  method = RequestMethod.GET)
    public String deleteQingjiaInfoById(@RequestParam("qingjiaId") String qingjiaId){
        int id = Integer.parseInt(qingjiaId);
        String msg = "";
        qingjiaService.deleteQingjiaInfoById(id);
        msg = "删除成功";
        JSONObject result = new JSONObject();
        result.put("msg", msg);
        return result.toJSONString();
    }


    //管理员请假审核管理
    /*
     * 查询:所有请假信息
     */
    @RequestMapping(value = "/selectQingjiaInfo", method = RequestMethod.GET)
    public String selectQingjiaInfo(Model model,HttpSession session){
        List<Qingjia> qingjiaList = qingjiaService.selectAllQingjia();
        for(int i=0;i<qingjiaList.size();i++){
            int userid = qingjiaList.get(i).getUserid();            //给userTruename赋值
            String truename = userService.selectUsernameByUserId(userid);
            qingjiaList.get(i).setUserTruename(truename);
        }
        model.addAttribute("qingjiaList", qingjiaList);//将peixun显示到页面上
        model.addAttribute("qingjiapage",true);
        session.setAttribute("qingjiaList", qingjiaList);
        return "qingjia/selectAllQingjiaInfo";  //peixunList显示培训计划信息
    }

    //审核通过
    @ResponseBody
    @RequestMapping(value = "/passShenheInfoById", method = RequestMethod.GET)
    public String passShenheInfoById(Model model,
                                       @RequestParam(value = "qingjiaId") String qingjiaId,
                                       Map<String, Object> map, HttpSession session) {
        int id = Integer.parseInt(qingjiaId);
        Qingjia qingjia = qingjiaService.selectQingjiaInfoById(id);
        qingjia.setShenhe("审核通过");
        qingjiaService.updateQingjiaInfoById(id);//更新请假的shenhe字段为“审核通过”
        Kaoqin kaoqin = new Kaoqin();
        kaoqin.setLeixing("请假");
        kaoqin.setCreatetime(new Date());
        kaoqin.setBeizhu(qingjia.getContent());
        kaoqin.setUserid(qingjia.getUserid());
        kaoqin.setUserTruename(qingjia.getUserTruename());
        kaoqinService.addKaoqinInfo(kaoqin);//添加考勤信息
        String msg = "";
        msg = "审核通过";
        JSONObject result = new JSONObject();
        result.put("msg", msg);
        return result.toJSONString();
    }

    //审核不通过
    @ResponseBody
    @RequestMapping(value = "/nopassShenheInfoById", method = RequestMethod.GET)
    public String nopassShenheInfoById(Model model,
                                     @RequestParam(value = "qingjiaId") String qingjiaId,
                                     Map<String, Object> map, HttpSession session) {
        int id = Integer.parseInt(qingjiaId);
        Qingjia qingjia = qingjiaService.selectQingjiaInfoById(id);
        qingjia.setShenhe("审核未通过");
        qingjiaService.updateQingjiaNoInfoById(id);//更新请假的shenhe字段为“审核通过”
        String msg = "";
        msg = "审核未通过";
        JSONObject result = new JSONObject();
        result.put("msg", msg);
        return result.toJSONString();
    }
}