package com.congmai.zhgj.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.Role;
import com.congmai.zhgj.web.model.RoleExample;

/**
 * 
 * @ClassName RoleMapper
 * @Description 角色Dao 接口
 * @author tanzb
 * @Date 2017年7月26日 下午2:47:18
 * @version 1.0.0
 */
public interface RoleMapper extends GenericDao<Role, Integer> {
    /**
     * 通过角色roleId 查询角色信息
     * 
     * @param roleId
     * @return
     */
    Role selectRoleById(Integer roleId);
}