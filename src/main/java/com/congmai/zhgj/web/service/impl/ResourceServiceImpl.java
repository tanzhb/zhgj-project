package com.congmai.zhgj.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.web.dao.ResourceMapper;
import com.congmai.zhgj.web.service.ResourceService;

/**
 * 
 * @ClassName ResourceServiceImpl
 * @Description 资源Service实现类
 * @author tanzb
 * @Date 2017年7月26日 下午2:55:52
 * @version 1.0.0
 */
@Service
public class ResourceServiceImpl extends GenericServiceImpl<com.congmai.zhgj.web.model.Resource, Integer> implements ResourceService {

    @Resource
    private ResourceMapper resourceMapper;


    @Override
    public GenericDao<com.congmai.zhgj.web.model.Resource, Integer> getDao() {
        return resourceMapper;
    }

    public List<com.congmai.zhgj.web.model.Resource> selectResourcesByGroupId(Integer groupId) {
        return resourceMapper.selectResourcesByGroupId(groupId);
    }
}
