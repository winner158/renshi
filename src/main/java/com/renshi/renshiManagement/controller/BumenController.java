package com.renshi.renshiManagement.controller;
import com.alibaba.fastjson.JSONObject;
import com.renshi.entity.Bumen;
import com.renshi.renshiManagement.service.BumenService;
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

/**
 * @Author: 史煜
 * @Date: 2019/10/20 11:03
 */
@Controller
public class BumenController {

    @Autowired
    private BumenService bumenService;

    /*
     * 查询:所有部门信息
     */
    @RequestMapping(value = "/selectAllBumenInfo", method = RequestMethod.GET)
    public String selectAllBumenInfo(Model model,HttpSession session){
        List<Bumen> bumenList = bumenService.selectAllBumen();
        model.addAttribute("bumenList", bumenList);//将bumen显示到页面上
        model.addAttribute("bumenpage",true);
        session.setAttribute("bumenList", bumenList);
        return "bumen/selectAllBumenInfo";  //bumenList显示部门信息
    }

    /*
     * 查询：按部门名查询
     */
    @RequestMapping(value = "/selectBumenInfoByName",method = RequestMethod.POST)
    public String selectBumenInfoByName(Model model,Map<String ,Object> map ,
                                        @RequestParam(value = "name")String name){
        String bumenName = name;
        System.out.println("从前台得来的部门名是："+bumenName);
        List<Bumen> bumenList= bumenService.selectBumenByBName(bumenName);//查询部门信息(模糊查询)
        model.addAttribute("bumenpage",true);
        if(bumenList.size() ==0 ){      //查询失败
            map.put("msg","未查询到相关信息，请重试！");
            bumenList = bumenService.selectAllBumen();
            model.addAttribute("bumenList",bumenList);
            return "bumen/selectAllBumenInfo";
        }else{                          //查询成功
            model.addAttribute("bumenList", bumenList);
            return "bumen/selectAllBumenInfo";
        }
    }

    /*
     * 跳到添加部门页面
     */
    @RequestMapping("/addBumenInfo")
    public String addBumenInfo(){
        return "bumen/addBumenInfo";
    }

    /*
     * 添加：部门信息
     */
    @RequestMapping(value = "/addBumenCheckAction", method = RequestMethod.POST)
    public String addBumenCheckAction(Model model,
                                          @RequestParam(value = "name") String name,
                                          @RequestParam(value = "jibengongzi") String jibengongzi,
                                          Map<String, Object> map, HttpSession session){
        //若该部门存在，则添加；若不存在，则不添加
        String bumenName = name;
        System.out.println("输入的部门名是-..."+bumenName);
        Bumen bumen = bumenService.selectBumen(bumenName);
        if(bumen == null){
            Bumen bumen2 = new Bumen();
            bumen2.setJibengongzi(jibengongzi);
            bumen2.setName(bumenName);
            bumenService.addBumenInfo(bumen2);
            map.put("msg", "部门添加成功！");  //该msg在bumenAddList页面显示
            return "redirect:/selectAllBumenInfo";
        }else{
            map.put("msg", "操作失败，该部门名已经存在！");
            return "bumen/addBumenInfo";
        }
    }

    /*
     **修改部门信息
     */
    @RequestMapping("/updateBumenInfoById")
    public String updateBumenInfoById(Model model,@RequestParam("bumenId") String bumenId){
        int id = Integer.parseInt(bumenId);
        System.out.println("得到的修改部门信息的id是：。。。"+bumenId);
        Bumen bumen = bumenService.selectBumenById(id);
        model.addAttribute("bumen",bumen);
        model.addAttribute("bumenpage",true);
        return "bumen/updateBumenInfo";
    }

    /**
     *修改部门信息
     * （哪些参数可修改，则可以@RequestParam传入哪些参数；即页面不可修改的参数不传到后台）
     */
    @RequestMapping(value = "/updateBumenAction",method = RequestMethod.POST)
    public String updateEditorAction( @RequestParam(value = "bumenId") String bumenId,
                                      @RequestParam(value = "name", required = false) String name,
                                      @RequestParam(value = "jibengongzi", required = false) String jibengongzi){
        int id = Integer.parseInt(bumenId);
        Bumen bumen = bumenService.selectBumenById(id);//editor（即state = 0 1 2）
        bumen.setName(name);
        bumen.setJibengongzi(jibengongzi);
        bumenService.updateBumenInfoById(bumen);//更新：修改为新数据  未更新：将查询出的bookCategory重Update(未变)
        return "redirect:/selectAllBumenInfo";
    }

    /**
     * 删除部门
     */
    @ResponseBody //返回json必须，否则就直接返回Thymeleaf页面
    @RequestMapping(value = "/deleteBumenInfoById",  method = RequestMethod.GET)
    public String deleteBumenInfoById(@RequestParam("bumenId") String bumenId){
        int id = Integer.parseInt(bumenId);
        String msg = "";
        bumenService.deleteBumenInfoById(id);
        msg = "删除成功";
        JSONObject result = new JSONObject();
        result.put("msg", msg);
        return result.toJSONString();
    }
    /*
     **查看某个部门信息
     */
    @RequestMapping("/selectBumenInfoById")
    public String selectBumenInfoById(Model model,@RequestParam("bumenId") String bumenId){
        int id = Integer.parseInt(bumenId);
        Bumen bumen = bumenService.selectBumenById(id);
        model.addAttribute("bumenpage",true);
        model.addAttribute("bumen",bumen);
        return "bumen/selectBumenInfo";
    }
}
