package com.renshi.renshiManagement.controller;
import com.alibaba.fastjson.JSONObject;
import com.renshi.entity.Bumen;
import com.renshi.entity.Peixun;
import com.renshi.entity.Qingjia;
import com.renshi.entity.User;
import com.renshi.renshiManagement.service.PeixunService;
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
public class PeixunController {

    @Autowired
    private PeixunService peixunService;

    @Autowired
    private UserService userService;
    /*
     * 查询:所有培训信息
     */
    @RequestMapping(value = "/selectPeixunInfo", method = RequestMethod.GET)
    public String selectPeixunInfo(Model model,HttpSession session){
        System.out.println("进入controller.....：");
        List<Peixun> peixunList = peixunService.selectAllPeixun();
        for(int i=0;i<peixunList.size();i++){
            int userid = peixunList.get(i).getUserid();            //给userTruename赋值
            String truename = userService.selectUsernameByUserId(userid);
            peixunList.get(i).setUserTruename(truename);
        }
        model.addAttribute("peixunList", peixunList);//将peixun显示到页面上
        model.addAttribute("peixunpage",true);
        session.setAttribute("peixunList", peixunList);
        return "peixun/selectAllPeixunInfo";  //peixunList显示培训计划信息
    }

    /*
     * 查询：按名查询
     */
    @RequestMapping(value = "/selectPeixunInfoByPeixunjihua",method = RequestMethod.POST)
    public String selectPeixunInfoByPeixunjihua(Model model,Map<String ,Object> map ,
                                                @RequestParam(value = "peixunjihua")String peixunjihua){
        String peixunPeixunjihua = peixunjihua;
        System.out.println("培训名称是："+peixunPeixunjihua);
        List<Peixun> peixunList= peixunService.selectPeixunByPPeixunjihua(peixunPeixunjihua);//查询培训计划信息(模糊查询)
        System.out.println("得到的peixunlist信息是："+peixunList.toString() );
        model.addAttribute("peixunpage",true);
        if(peixunList.size() ==0 ){      //查询失败
            map.put("msg","未查询到相关信息，请重试！");
            peixunList = peixunService.selectAllPeixun();
            for(int i=0;i<peixunList.size();i++){
                int userid = peixunList.get(i).getUserid();            //给userTruename赋值
                String truename = userService.selectUsernameByUserId(userid);
                peixunList.get(i).setUserTruename(truename);
            }
            model.addAttribute("peixunList",peixunList);
            return "peixun/selectAllPeixunInfo";
        }else{                          //查询成功
            for(int i=0;i<peixunList.size();i++){
                int userid = peixunList.get(i).getUserid();            //给userTruename赋值
                String truename = userService.selectUsernameByUserId(userid);
                peixunList.get(i).setUserTruename(truename);
            }
            model.addAttribute("peixunList", peixunList);
            return "peixun/selectAllPeixunInfo";
        }
    }

    /*
     * 跳到添加培训信息页面
     */
    @RequestMapping("/addPeixunInfo")
    public String addPeixunInfo(Model model){
        List<User> userList  = userService.selectAllUser();
        model.addAttribute("userList",userList);
        return "peixun/addPeixunInfo";
    }

    /*
     * 添加：培训信息
     */
    @RequestMapping(value = "/addPeixunCheckAction", method = RequestMethod.POST)
    public String addPeixunCheckAction(Model model,
                                       @RequestParam(value = "peixunjihua") String peixunjihua,
                                       @RequestParam(value = "peixunneirong") String peixunneirong,
                                       @RequestParam(value = "peixunzhouqi") String peixunzhouqi,
                                       @RequestParam(value = "peixundidian") String peixundidian,
                                       @RequestParam(value = "userTruename") int  userid,
                                       Map<String, Object> map, HttpSession session){
        //若培训名称存在，则不添加；若不存在，则添加
        String peixunPeixunjihua = peixunjihua;
        System.out.println("输入的培训计划名是-..."+peixunPeixunjihua);
        Peixun peixun = peixunService.selectPeixun(peixunPeixunjihua);
        if(peixun == null){
            Peixun peixun2 = new Peixun();
            peixun2.setPeixunneirong(peixunneirong);
            peixun2.setPeixunjihua(peixunPeixunjihua);
            peixun2.setPeixunzhouqi(peixunzhouqi);
            peixun2.setPeixundidian(peixundidian);
            peixun2.setUserid(userid);
            peixunService.addPeixunInfo(peixun2);
            map.put("msg", "培训计划添加成功！");  //该msg在peixunAddList页面显示
            return "redirect:/selectPeixunInfo";
        }else{
            map.put("msg", "操作失败，该培训计划已经存在！");
            return "peixun/addPeixunInfo";
        }
    }

    /*
     **修改培训信息
     */
    @RequestMapping("/updatePeixunInfoById")
    public String updatePeixunInfoById(Model model,@RequestParam("peixunId") String peixunId){
        int id = Integer.parseInt(peixunId);
        System.out.println("得到的修改培训计划信息的id是：。。。"+peixunId);
        Peixun peixun = peixunService.selectPeixunById(id);
        model.addAttribute("peixun",peixun);
        model.addAttribute("peixunpage",true);
        return "peixun/updatePeixunInfo";
    }

    /**
     *修改培训计划信息
     * （哪些参数可修改，则可以@RequestParam传入哪些参数；即页面不可修改的参数不传到后台）
     */
    @RequestMapping(value = "/updatePeixunAction",method = RequestMethod.POST)
    public String updateEditorAction( @RequestParam(value = "peixunId") String peixunId,
                                      @RequestParam(value = "peixunjihua", required = false) String peixunjihua,
                                      @RequestParam(value = "peixunneirong", required = false) String peixunneirong,
                                      @RequestParam(value = "peixunzhouqi", required = false) String peixunzhouqi,
                                      @RequestParam(value = "peixundidian", required = false) String peixundidian)
    {
        int id = Integer.parseInt(peixunId);
        Peixun peixun = peixunService.selectPeixunById(id);//editor（即state = 0 1 2）
        peixun.setPeixunjihua(peixunjihua);
        peixun.setPeixunneirong(peixunneirong);
        peixun.setPeixunzhouqi(peixunzhouqi);
        peixun.setPeixundidian(peixundidian);
        peixunService.updatePeixunInfoById(peixun);//更新：修改为新数据  未更新：将查询出的bookCategory重Update(未变)
        return "redirect:/selectPeixunInfo";


    }

    /**
     * 删除培训计划
     */
    @ResponseBody //返回json必须，否则就直接返回Thymeleaf页面
    @RequestMapping(value = "/deletePeixunInfoById",  method = RequestMethod.GET)
    public String deletePeixunInfoById(@RequestParam("peixunId") String peixunId){
        int id = Integer.parseInt(peixunId);
        String msg = "";
        peixunService.deletePeixunInfoById(id);
        msg = "删除成功";
        JSONObject result = new JSONObject();
        result.put("msg", msg);
        return result.toJSONString();
    }
    /*
     **查看某个培训计划信息
     */
    @RequestMapping("/selectPeixunInfoById")
    public String selectPeixunInfoById(Model model,@RequestParam("peixunId") String peixunId){
        int id = Integer.parseInt(peixunId);
        Peixun peixun = peixunService.selectPeixunById(id);
        //给userTruename赋值
        int userid = peixun.getUserid();
        String truename = userService.selectUsernameByUserId(userid);
        peixun.setUserTruename(truename);
        model.addAttribute("peixunpage",true);
        model.addAttribute("peixun",peixun);
        return "peixun/selectPeixunInfo";
    }

    /*
     * 查询:员工个人培训计划信息
     */
    @RequestMapping(value = "/selectPersonalPeixunInfo", method = RequestMethod.GET)
    public String selectPersonalPeixunInfo(Model model,HttpSession session){
        User u = (User) session.getAttribute("commonUser");//从session中获取user对象
        int id = u.getId();
        List<Peixun> peixunList = peixunService.selectPeixunByUserId(id);//查询培训计划信息
        for (int i = 0; i < peixunList.size(); i++) {
            int userid = peixunList.get(i).getUserid();  //给userTruename赋值
            String truename = userService.selectUsernameByUserId(userid);
            peixunList.get(i).setUserTruename(truename);
        }
        model.addAttribute("peixunList", peixunList);
        model.addAttribute("peixunpage2",true);
        return "peixun/selectPersonalPeixunInfo";
    }
}
