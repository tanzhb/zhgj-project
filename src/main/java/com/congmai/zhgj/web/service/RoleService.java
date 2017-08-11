package com.congmai.zhgj.web.service;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.Role;

/**
 * 
 * @ClassName RoleService
 * @Description 角色 业务接口
 * @author tanzb
 * @Date 2017年7月26日 下午2:55:06
 * @version 1.0.0
 */
public interface RoleService extends GenericService<Role, Integer> {
    /**
     * 通过角色roleId 查询角色信息
     * 
     * @param roleId
     * @return
     */
    Role selectRoleById(Integer roleId);
}
