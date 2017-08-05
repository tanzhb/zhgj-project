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
    
    @RequestMapping("/userContract")
    public String userContract(HttpServletRequest request,Model model) {
        return "userContract";
    }
    
    
   /* @RequestMapping("/userContractList")
    public String userContractList(HttpServletRequest request,Model model) {
    	User user=(User) request.getSession().getAttribute("userInfo"); 
    	List<ContractVO> contractList=userService.queryContractList(user.getId()+"");
    	model.addAttribute("contractList", contractList);
        return "userContractList";
    }*/
    
    @RequestMapping(value = "/userContractList", method = RequestMethod.GET)
	public ResponseEntity<Map> userContractList(HttpServletRequest request) {
    	User user=(User) request.getSession().getAttribute("userInfo"); 
    	List<ContractVO> contractList=userService.queryContractList(user.getId()+"");
		
		if (contractList.isEmpty()) {
			return new ResponseEntity<Map>(HttpStatus.NO_CONTENT);// You many
			                                                             // decide to
			                                                             // return
			                                                             // HttpStatus.NOT_FOUND
		}
		//封装datatables数据返回到前台
		Map pageMap = new HashMap();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", contractList.size());
		pageMap.put("recordsFiltered", contractList.size());
		pageMap.put("data", contractList);
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);
	}
    
    
    @RequestMapping("/addUserContract")
    public String addUserContract() {
        return "addUserContract";
    }
    
    /**
     * 用户管理
     */
    @RequestMapping("/user")
    public String user() {
        return "user";
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