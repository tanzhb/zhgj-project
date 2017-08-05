package com.congmai.zhgj.web.service;

import java.util.List;

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
public interface RoleService extends GenericService<Role, Long> {
    /**
     * 通过用户id 查询用户 拥有的角色
     * 
     * @param userId
     * @return
     */
    List<Role> selectRolesByUserId(Long userId);
}
