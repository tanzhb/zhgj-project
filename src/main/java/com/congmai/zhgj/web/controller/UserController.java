package com.congmai.zhgj.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.congmai.zhgj.core.util.SimpleMailSender;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.Warehouse;
import com.congmai.zhgj.web.security.PasswordHelper;
import com.congmai.zhgj.web.security.PermissionSign;
import com.congmai.zhgj.web.security.RoleSign;
import com.congmai.zhgj.web.security.SecurityRealm;
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

	@Autowired
	protected PasswordHelper passwordHelper;
	
	@Autowired
    private SecurityRealm securityRealm;

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
			if (result.hasErrors()) {
				model.addAttribute("error", "参数错误！");
				return "login";
			}
			// 身份验证
			try {
				UsernamePasswordToken upt = new UsernamePasswordToken(
						user.getUserName(), user.getUserPwd());
				if ("on".equals(rememberMe)) {// 记住我
					upt.setRememberMe(true);
				}
				subject.login(upt);
				// 身份验证失败

			} catch (Exception e) {
				// e.printStackTrace();
				model.addAttribute("error", "用户名或密码错误 ！");
				return "login";
			}

			// 验证成功在Session中保存用户信息
			final User authUserInfo = userService.selectByUsername(user
					.getUserName());
			
			UserUtil.saveUserToSession(subject.getSession(), authUserInfo);
//			request.getSession().setAttribute("userInfo", authUserInfo);
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
		if (u == null) {
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
		Map m = (Map) password;
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();// 获取当前登录用户名

		User user = userService.selectByUsername(currenLoginName);
		if (user == null) {// 当前用户过期或不存在
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			
			if (user.getUserPwd()
					.equals(new SimpleHash("md5", m.get("oldPassword")
							.toString(), ByteSource.Util.bytes(user
							.getCredentialsSalt()), 2).toHex())) {
				user.setUserPwd(m.get("newPassword").toString());
				passwordHelper.encryptPassword(user);
				userService.update(user);
			} else {// 原密码输入有错
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

	
	@RequestMapping(value = "/getUserInfo")
	@ResponseBody
	public ResponseEntity<User> getUserInfo(){
		User user=null;
	    user = UserUtil.getUserFromSession();
	    user=userService.getUserInfo(user.getUserId());
	    
	    Company company=userService.getUserCompanyInfo(user.getUserId());
	    user.setCompany(company);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
	public ResponseEntity<Void> updateUserInfo(User user,MultipartFile file) {
		if (user == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		String avatar=null;
		if(file!=null){
			avatar=uploadFile(file);
			user.setAvatar(avatar);
		}
		userService.updateUserInfo(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/updateCompanyInfo", method = RequestMethod.POST)
	public ResponseEntity<Void> updateCompanyInfo(Company company) {
		User user=null;
	    user = UserUtil.getUserFromSession();
	    user=userService.getUserInfo(user.getUserId());
	    
		if (company == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		company.setUpdater(String.valueOf(user.getUserId()));
		userService.updateCompanyInfo(company);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/getCompanyInfo")
	@ResponseBody
	public ResponseEntity<Company> getCompanyInfo(){
		User user=null;
	    user = UserUtil.getUserFromSession();
	    user=userService.getUserInfo(user.getUserId());
	    
	    Company company=userService.getUserCompanyInfo(user.getUserId());
	    
		return new ResponseEntity<Company>(company, HttpStatus.OK);	
	}
	
	/**
	 * 
	 * @Description 修改邮箱
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/changeEmail", method = RequestMethod.POST)
	public ResponseEntity<Void> changeEmail(@RequestBody Object password) {
		Map m = (Map) password;
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();// 获取当前登录用户名
		User user = userService.selectByUsername(currenLoginName);
		
		if (user == null) {// 当前用户过期或不存在
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			if (user.getUserPwd().equals(new SimpleHash("md5", m.get("password").toString(), ByteSource.Util.bytes(user.getCredentialsSalt()), 2).toHex())) {
				String email=m.get("email").toString();
				user.setEmail(email);
				userService.updateEmail(user);
			} else {// 原密码输入有错
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}

		}

		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	
	/**
	 * 邮箱验证码	
	 */
	@RequestMapping(value="/emailmessage")
	@ResponseBody
	public String emailmessage(String cuEmail) throws Exception{
		try {
			String randommsg = String.valueOf((int)(Math.random()*9000+1000));
			SimpleMailSender sms = new SimpleMailSender();
			sms.sendqqmail(cuEmail, "邮箱验证", "您的验证码是:"+randommsg);
			return randommsg;
			// TODO 处理返回值,参见HTTP协议文档
		} catch (Exception e) {
			// TODO 处理异常
			e.printStackTrace();
		}
		return "0";
	}
	
	/**
	 * 上传执行
	 * @param file（上传的文件）
	 * @return
	 */
	public String uploadFile(MultipartFile file){
		String filePath = getClasspath()+"uploadAttachFiles/";
		String randomName=UUID.randomUUID().toString().toUpperCase().replaceAll("-", ""); 
		String fileName = fileUp(file, filePath,randomName);
		System.out.println(fileName);
		return fileName;
	}


	/**
	 * 复制文件
	 * @param file (文件对象）
	 * @param filePath （文件路径）
	 * @param fileName   （文件名）
	 * @return
	 */
	public  String fileUp(MultipartFile file, String filePath, String fileName){
		String extName = ""; // 扩展名格式：
		try {

			if (file.getOriginalFilename().lastIndexOf(".") >= 0){
				extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			}

			copyFile(file.getInputStream(), filePath, fileName+extName).replaceAll("-", "");

		} catch (IOException e) {
			System.out.println(e);
		}
		return fileName+extName;
	}


	/**
	 * 写文件到当前目录的upload目录中
	 * @param in
	 * @param fileName
	 * @throws IOException
	 */
	private  String copyFile(InputStream in, String dir, String realName)
			throws IOException {
		File file = mkdirsmy(dir,realName);
		FileUtils.copyInputStreamToFile(in, file);
		return realName;
	}


	/**判断路径是否存在，否：创建此路径
	 * @param dir  文件路径
	 * @param realName  文件名
	 * @throws IOException 
	 */
	public  File mkdirsmy(String dir, String realName) throws IOException{
		File file = new File(dir, realName);
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		return file;
	}




	/**获取classpath1
	 * @return
	 */
	public  String getClasspath(){
		String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../").replaceAll("file:/", "").replaceAll("%20", " ").trim();	
		if(path.indexOf(":") != 1){
			path = File.separator + path;
		}
		return path;
	}
}