package com.congmai.zhgj.web.security;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

/**
 * 添加多角色认证，其中一个就可以访问
 * @author tanzb
 * @version 2017年8月10日 上午9:20:54
 */
public class AnyRolesAuthorizationFilter extends RolesAuthorizationFilter {
	@Override
	public boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws IOException {

		final Subject subject = getSubject(request, response);
		final String[] rolesArray = (String[]) mappedValue;

		if (rolesArray == null || rolesArray.length == 0) {
			// no roles specified, so nothing to check - allow access.
			return true;
		}

		for (String roleName : rolesArray) {
			if (subject.hasRole(roleName)) {
				return true;
			}
		}

		return false;
	}
}
