package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.Menu;

/**
 * 
 * @ClassName MenuService
 * @Description 权限 业务接口
 * @author tanzb
 * @Date 2017年7月26日 下午2:54:46
 * @version 1.0.0
 */
public interface MenuService extends GenericService<Menu, Integer> {

    /**
     * 通过角色id 查询角色 拥有的菜单
     * 
     * @param roleId
     * @return
     */
    List<Menu> selectMenusByRoleId(Integer roleId);

}
