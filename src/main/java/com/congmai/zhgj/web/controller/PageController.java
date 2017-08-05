package com.congmai.zhgj.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @ClassName PageController
 * @Description 视图控制器,返回jsp视图给前端
 * @author tanzb
 * @Date 2017年7月26日 下午2:43:47
 * @version 1.0.0
 */
@Controller
@RequestMapping("/page")
public class PageController {

    /**
     * 登录页
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * dashboard页
     */
    @RequestMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
    
    /**
     * header页
     */
    @RequestMapping("/header")
    public String header() {
        return "header";
    }
    
    /**
     * sidebar页
     */
    @RequestMapping("/sidebar")
    public String sidebar() {
        return "sidebar";
    }
    
    /**
     * themePanel页
     */
    @RequestMapping("/themePanel")
    public String themePanel() {
        return "themePanel";
    }
    
    /**
     * quickSidebar页
     */
    @RequestMapping("/quickSidebar")
    public String quickSidebar() {
        return "quickSidebar";
    }
    
    /**
     * footer页
     */
    @RequestMapping("/footer")
    public String footer() {
        return "footer";
    }
    
    /**
     * blank页
     */
    @RequestMapping("/blank")
    public String blank() {
        return "blank";
    }
    
    /**
     * 价格目录
     */
    @RequestMapping("/jgml")
    public String jgml() {
        return "jgml";
    }
    
    /**
     * 用户管理
     */
    @RequestMapping("/user")
    public String user() {
        return "user";
    }
    /**
     * 新增物料
     */
    @RequestMapping("/addMateriel")
    public String addMateriel(String serialNum,String view) {
        return "materiel/addMateriel";
    }
    
    /**
     * 新增物料
     */
    @RequestMapping("/materiel")
    public String materiel() {
        return "materiel/materiel";
    }
    
    /**
     * 404页
     */
    @RequestMapping("/404")
    public String error404() {
        return "404";
    }

    /**
     * 401页
     */
    @RequestMapping("/401")
    public String error401() {
        return "401";
    }

    /**
     * 500页
     */
    @RequestMapping("/500")
    public String error500() {
        return "500";
    }

}