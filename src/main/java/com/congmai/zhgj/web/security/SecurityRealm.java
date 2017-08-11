package com.congmai.zhgj.web.security;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.model.Menu;
import com.congmai.zhgj.web.model.Role;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.service.MenuService;
import com.congmai.zhgj.web.service.RoleService;
import com.congmai.zhgj.web.service.UserService;

/**
 * 
 * @ClassName SecurityRealm
 * @Description 用户身份验证,授权 Realm 组件
 * @author tanzb
 * @Date 2017年7月26日 下午2:54:20
 * @version 1.0.0
 */
@Component(value = "securityRealm")
public class SecurityRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private MenuService menuService;

    /**
     * 权限检查
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String loginName = String.valueOf(principals.getPrimaryPrincipal());
        List<String> roleIdList = null;
        

        final User user = userService.selectByUsername(loginName);
        if(user.getRoleid() != null){
        	roleIdList = ApplicationUtils.getIdList(user.getRoleid());
        	if(roleIdList != null && roleIdList.size() > 0 ){
        		for(String roleId : roleIdList){
        			Role role = roleService.selectRoleById(Integer.valueOf(roleId));
        			authorizationInfo.addRole(role.getTips());// 添加角色
        			
        			final List<Menu> menus = menuService.selectMenusByRoleId(Integer.valueOf(roleId));
        			
        			for(Menu menu : menus){
        				authorizationInfo.addStringPermission(menu.getCode());// 添加权限
        			}
        		}
        	}
        	
        }
        
        return authorizationInfo;
    }

    /**
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String loginName = String.valueOf(token.getPrincipal());
        String credentials = new String((char[]) token.getCredentials());
        
        // 通过数据库进行验证
        final User authentication = userService.selectByUsername(loginName);
        if(authentication == null){ 
        	throw new AuthenticationException("用户名或密码错误.") ;
        }
        
        String source = authentication.getSalt();        
        if(!authentication.getPassword().equals(ShiroKit.md5(credentials, source))){
        	throw new AuthenticationException("用户名或密码错误.") ;
        }
        
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(loginName, credentials, new Md5Hash(source), getName());
        
        return authenticationInfo;
    }

}
