package com.congmai.zhgj.web.dao;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.Menu;
import com.congmai.zhgj.web.model.Role;

/**
 * 
 * @ClassName RoleMapper
 * @Description 角色Dao 接口
 * @author tanzb
 * @Date 2017年7月26日 下午2:47:18
 * @version 1.0.0
 */
public interface MenuMapper extends GenericDao<Menu, Integer> {
    /**
     * 通过角色id 查询角色 拥有的菜单
     * 
     * @param roleId
     * @return
     */
    List<Menu> selectMenusByRoleId(Integer roleId);
}