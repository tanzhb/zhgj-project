package com.congmai.zhgj.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.service.ContractService;
import com.congmai.zhgj.web.service.UserService;

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

	 @Resource
	 private UserService userService;
	 
	 @Resource
	 private ContractService contractService;
	 
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
     * 合同列表
     * @return 列表页面的url
     */
    @RequestMapping("/userContract")
    public String userContract() {
        return "contract/userContract";
    }
    
    /**
     * 采购预测
     */
    @RequestMapping("/purchaseForecast")
    public String purchaseForecast(){
    	return "purchase/purchaseForecast";
    }
    
    
    
    /**
     * 添加合同页面
     * @return 添加合同页面url
     */
    @RequestMapping("/addUserContract")
    public String addUserContract() {
        return "contract/addUserContract";
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