package com.congmai.zhgj.web.security;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import com.congmai.zhgj.web.controller.ProcessAction;
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
private static final Logger logger = Logger.getLogger(SecurityRealm.class);

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
    
    public void getAthoritycache(String loginname){
    	RealmSecurityManager securityManager =  
			     (RealmSecurityManager) SecurityUtils.getSecurityManager();
		SecurityRealm securityRealm = (SecurityRealm) securityManager.getRealms().iterator().next();
		Cache<Object, AuthorizationInfo> cac =  securityRealm.getCacheManager().getCache(loginname);
		SimplePrincipalCollection co = new SimplePrincipalCollection();
		co.add(loginname, super.getName());
		Cache<Object, AuthorizationInfo> cache = securityRealm.getAuthorizationCache();
		
		
		
		if(cache != null){
			Object key = securityRealm.getAuthorizationCacheKey(co);
			AuthorizationInfo info = cache.get(key);
			AuthorizationInfo info22 = cac.get(key);
			AuthorizationInfo o = cac.remove(key);
			cache.remove(key);
			AuthorizationInfo nfo = cache.get(key);
			cache.clear();
			System.out.println("---");
		}
    }
    
    
    
    @Override
    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        Cache c = getAuthenticationCache();
        logger.info("清除【认证】缓存之前");
        for(Object o : c.keys()){
            logger.info( o + " , " + c.get(o));
        }
        super.clearCachedAuthenticationInfo(principals);
        logger.info("调用父类清除【认证】缓存之后");
        for(Object o : c.keys()){
            logger.info( o + " , " + c.get(o));
        }

        // 添加下面的代码清空【认证】的缓存
        SimplePrincipalCollection spc = new SimplePrincipalCollection(principals.getPrimaryPrincipal().toString(),getName());
        super.clearCachedAuthenticationInfo(spc);
        logger.info("添加了代码清除【认证】缓存之后");
        int cacheSize = c.keys().size();
        logger.info("【认证】缓存的大小:" + c.keys().size());
        if (cacheSize == 0){
            logger.info("说明【认证】缓存被清空了。");
        }
    }

    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        logger.info("清除【授权】缓存之前");
        Cache c = getAuthorizationCache();
        for(Object o : c.keys()){
        	Object oo = c.get(o);
            logger.info( o + " , " + c.get(o));
        }
        super.clearCachedAuthorizationInfo(principals);
        logger.info("清除【授权】缓存之后");
        int cacheSize = c.keys().size();
        logger.info("【授权】缓存的大小:" + cacheSize);

        for(Object o : c.keys()){
            logger.info( o + " , " + c.get(o));
        }
        if(cacheSize == 0){
            logger.info("说明【授权】缓存被清空了。");
        }

    }
    
    
  //系统登出后 会自动调用以下方法清理授权和认证缓存
//    @Override
//    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
//        super.clearCachedAuthorizationInfo(principals);
//    }
//
//    @Override
//    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
//        super.clearCachedAuthenticationInfo(principals);
//    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}
