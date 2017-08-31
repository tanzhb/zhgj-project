package com.congmai.zhgj.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.web.dao.GroupMapper;
import com.congmai.zhgj.web.model.Group;
import com.congmai.zhgj.web.service.GroupService;

/**
 * 
 * @ClassName GroupServiceImpl
 * @Description 组Service实现类
 * @author tanzb
 * @Date 2017年7月26日 下午2:56:12
 * @version 1.0.0
 */
@Service
public class GroupServiceImpl extends GenericServiceImpl<Group, Integer> implements GroupService {

    @Resource
    private GroupMapper groupMapper;

    @Override
    public GenericDao<Group, Integer> getDao() {
        return groupMapper;
    }

    public Group selectGroupById(Integer groupId) {
        return groupMapper.selectGroupById(groupId);
    }

}
