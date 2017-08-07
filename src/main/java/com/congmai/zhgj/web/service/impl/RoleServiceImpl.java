package com.congmai.zhgj.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.web.dao.RoleMapper;
import com.congmai.zhgj.web.model.Role;
import com.congmai.zhgj.web.service.RoleService;

/**
 * 
 * @ClassName RoleServiceImpl
 * @Description 角色Service实现类
 * @author tanzb
 * @Date 2017年7月26日 下午2:56:12
 * @version 1.0.0
 */
@Service
public class RoleServiceImpl extends GenericServiceImpl<Role, String> implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public GenericDao<Role, String> getDao() {
        return roleMapper;
    }

    public List<Role> selectRolesByUserId(String userId) {
        return roleMapper.selectRolesByUserId(userId);
    }

}
