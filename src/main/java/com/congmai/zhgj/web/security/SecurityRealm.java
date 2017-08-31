package com.congmai.zhgj.web.security;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import com.congmai.zhgj.web.model.Group;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.service.GroupService;
import com.congmai.zhgj.web.service.ResourceService;
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
    private GroupService groupService;

    @Resource
    private ResourceService resourceService;

    /**
     * 权限检查
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String loginName = String.valueOf(principals.getPrimaryPrincipal());

        final User user = userService.selectByUsername(loginName);
        
        //本系统设计为一个用户属于一个用户组，即用户组就是用户的角色（employee、finance、hr、boss..）；每个用户组有不同的权限（资源）        
        if(user.getGroupId() != null){
        	Group group = groupService.selectGroupById(Integer.valueOf(user.getGroupId()));
        	
        	if(group != null){
        		authorizationInfo.addRole(group.getType());
        		final List<com.congmai.zhgj.web.model.Resource> resources = resourceService.selectResourcesByGroupId(Integer.valueOf(user.getGroupId()));
        		
        		for(com.congmai.zhgj.web.model.Resource resource : resources){
    				authorizationInfo.addStringPermission(resource.getPermission());// 添加权限
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
        
        String source = authentication.getUserSalt();        
        if(!authentication.getUserPwd().equals(new SimpleHash("md5", credentials
				.toString(), ByteSource.Util.bytes(authentication
				.getCredentialsSalt()), 2).toHex())){
        	throw new AuthenticationException("用户名或密码错误.") ;
        }
        
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(loginName, credentials, new Md5Hash(source), getName());
        
        return authenticationInfo;
    }

}
