package com.congmai.zhgj.web.controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
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

import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.security.PermissionSign;
import com.congmai.zhgj.web.security.RoleSign;
import com.congmai.zhgj.web.security.ShiroKit;
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

	private PasswordService svc = new DefaultPasswordService();
	/**
	 * 用户登录
	 * 
	 * @param user
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Valid User user, BindingResult result, Model model,
			HttpServletRequest request) {
		String rememberMe = request.getParameter("rememberMe");
		try {
			Subject subject = SecurityUtils.getSubject();
			// 已登陆则 跳到首页
			if (subject.isAuthenticated() || subject.isRemembered()) {
				return "redirect:/";
			}
			boolean b = subject.isRemembered();
			if (result.hasErrors()) {
				model.addAttribute("error", "参数错误！");
				return "login";
			}
			// 身份验证
			try{
				UsernamePasswordToken upt = new UsernamePasswordToken(user.getAccount(), user
						.getPassword());
				if("on".equals(rememberMe)){//记住我
					upt.setRememberMe(true);
				}
				subject.login(upt);
				// 身份验证失败
				
			}catch(Exception e){
//				e.printStackTrace();
				model.addAttribute("error", "用户名或密码错误 ！");
				return "login";
			}
			
			// 验证成功在Session中保存用户信息
			final User authUserInfo = userService.selectByUsername(user.getAccount());
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
	 * @Description 检查某用户名是否存在
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/selectByUsername", method = RequestMethod.POST)
	public String selectByUsername(HttpServletRequest request) {
		String user = request.getParameter("");
		if (user == null) {
			return "false";
		}
		User u = userService.selectByUsername(user);
		if(u == null){
			return "false";
		}
		return "true";
	}
	
	/**
	 * 
	 * @Description 修改密码
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/updatePass", method = RequestMethod.POST)
	public ResponseEntity<Void> updatePass(@RequestBody Object password) {
		Map m = (Map)password;
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		
		User user = userService.selectByUsername(currenLoginName);
		if(user == null){//当前用户过期或不存在
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}else{
			String source = user.getSalt();
			if(user.getPassword().equals(ShiroKit.md5(m.get("oldPassword").toString(), source))){	  
				String newMd5 = ShiroKit.md5(m.get("newPassword").toString(), user.getSalt());
				user.setPassword(newMd5);
				userService.update(user);
			}else{//原密码输入有错
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}
		}

		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
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