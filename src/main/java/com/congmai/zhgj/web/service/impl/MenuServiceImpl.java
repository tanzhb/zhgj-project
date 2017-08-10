package com.congmai.zhgj.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.web.dao.MenuMapper;
import com.congmai.zhgj.web.model.Menu;
import com.congmai.zhgj.web.model.Role;
import com.congmai.zhgj.web.service.MenuService;

/**
 * 
 * @ClassName MenuServiceImpl
 * @Description 权限Service实现类
 * @author tanzb
 * @Date 2017年7月26日 下午2:55:52
 * @version 1.0.0
 */
@Service
public class MenuServiceImpl extends GenericServiceImpl<Menu, Integer> implements MenuService {

    @Resource
    private MenuMapper menuMapper;


    @Override
    public GenericDao<Menu, Integer> getDao() {
        return menuMapper;
    }

    public List<Menu> selectMenusByRoleId(Integer roleId) {
        return menuMapper.selectMenusByRoleId(roleId);
    }
}
