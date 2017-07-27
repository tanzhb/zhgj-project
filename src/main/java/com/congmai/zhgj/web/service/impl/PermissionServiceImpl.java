package com.congmai.zhgj.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.web.dao.PermissionMapper;
import com.congmai.zhgj.web.model.Permission;
import com.congmai.zhgj.web.service.PermissionService;

/**
 * 
 * @ClassName PermissionServiceImpl
 * @Description 权限Service实现类
 * @author tanzb
 * @Date 2017年7月26日 下午2:55:52
 * @version 1.0.0
 */
@Service
public class PermissionServiceImpl extends GenericServiceImpl<Permission, Long> implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;


    @Override
    public GenericDao<Permission, Long> getDao() {
        return permissionMapper;
    }

    public List<Permission> selectPermissionsByRoleId(Long roleId) {
        return permissionMapper.selectPermissionsByRoleId(roleId);
    }
}
