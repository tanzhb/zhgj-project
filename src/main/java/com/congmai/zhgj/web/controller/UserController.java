package com.congmai.zhgj.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.druid.util.StringUtils;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.security.PermissionSign;
import com.congmai.zhgj.web.security.RoleSign;
import com.congmai.zhgj.web.service.UserService;

/**
 * 
 * @ClassName UserController
 * @Description 用户控制器
 * @author tanzb
 * @Date 2017年7月26日 下午2:44:13
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户登录
     * 
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid User user, BindingResult result, Model model, HttpServletRequest request) {
        try {
            Subject subject = SecurityUtils.getSubject();
            // 已登陆则 跳到首页
            if (subject.isAuthenticated()) {
                return "redirect:/";
            }
            if (result.hasErrors()) {
                model.addAttribute("error", "参数错误！");
                return "login";
            }
            // 身份验证
            subject.login(new UsernamePasswordToken(user.getUsername(), user.getPassword()));
            // 验证成功在Session中保存用户信息
            final User authUserInfo = userService.selectByUsername(user.getUsername());
            request.getSession().setAttribute("userInfo", authUserInfo);
        } catch (AuthenticationException e) {
            // 身份验证失败
            model.addAttribute("error", "用户名或密码错误 ！");
            return "login";
        }
        return "redirect:/";
    }

    /**
     * 用户登出
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("userInfo");
        // 登出操作
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
    
    /**
     * 
     * @Description 查找所有用户信息
     * @return
     */
	@RequestMapping(value = "/findAllUsers", method = RequestMethod.GET)
	public ResponseEntity<Map> findAllUsers(HttpServletRequest request) {
		List<User> users = userService.findAllUsers();
		
		if (users.isEmpty()) {
			return new ResponseEntity<Map>(HttpStatus.NO_CONTENT);// You many
			                                                             // decide to
			                                                             // return
			                                                             // HttpStatus.NOT_FOUND
		}
		//封装datatables数据返回到前台
		Map pageMap = new HashMap();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", users.size());
		pageMap.put("recordsFiltered", users.size());
		pageMap.put("data", users);
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);
	}
	
	
	/**
     * 
     * @Description 查找所有用户合同信息
     * @return
     */
	@RequestMapping(value = "/findAllUserContract", method = RequestMethod.GET)
	public ResponseEntity<Map> findAllUserContract(HttpServletRequest request) {
		
		User user=(User) request.getSession().getAttribute("userInfo"); 
    	List<ContractVO> contractList=userService.queryContractList(user.getId()+"");
		
		if (contractList.isEmpty()) {
			return new ResponseEntity<Map>(HttpStatus.NO_CONTENT);// You many
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

	
	  @RequestMapping(value = "/saveUserContract", method = RequestMethod.POST)
	   public ResponseEntity<Void> saveUserContract(@Valid ContractVO contractVO, HttpServletRequest request,UriComponentsBuilder ucBuilder) {
		  if(StringUtils.isEmpty(contractVO.getId())){
			  String serialNum=UUID.randomUUID().toString().toUpperCase().replaceAll("-", ""); 
			   contractVO.setId(serialNum);
			   User user=(User) request.getSession().getAttribute("userInfo");   
			   contractVO.setCreator(user.getId()+"");
			   userService.insertContract(contractVO);
		  }else{
			  User user=(User) request.getSession().getAttribute("userInfo");
			  contractVO.setUpdater(user.getId()+"");
			  userService.updateContract(contractVO);
		  }
		    
		   
		   HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/userContract").buildAndExpand(contractVO.getId()).toUri());
					
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	   }
	 
	 
	 /**
	 * 删除用户合同
	 * @param contractVO
	 * @param request
	 * @return
	 */
	 @RequestMapping(value = "/deleteUserContractS", method = RequestMethod.POST)
	   public ResponseEntity<Void> deleteUserContractS(@RequestBody String ids) {
		 if ("".equals(ids) || ids == null) {
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}
		   userService.deleteUserContractS(ids); 
		   return new ResponseEntity<Void>(HttpStatus.OK);
	   }
	 
	 
	 @RequestMapping(value = "/selectConbtractById", method = RequestMethod.POST)
     public ResponseEntity<ContractVO> selectConbtractById(@RequestBody String ids) {
		 
		 ContractVO c=userService.selectConbtractById(ids);
		 return new ResponseEntity<ContractVO>(c, HttpStatus.OK);
     }
	 
	 @RequestMapping(value = "/editUserContractPage")
     public String editUserContractPage() {
		 return "editUserContractPage";
     }
	 
	 
    /**
     * 基于角色 标识的权限控制案例
     */
    @RequestMapping(value = "/admin")
    @ResponseBody
    @RequiresRoles(value = RoleSign.ADMIN)
    public String admin() {
        return "拥有admin角色,能访问";
    }

    /**
     * 基于权限标识的权限控制案例
     */
    @RequestMapping(value = "/create")
    @ResponseBody
    @RequiresPermissions(value = PermissionSign.USER_CREATE)
    public String create() {
        return "拥有user:create权限,能访问";
    }
}
