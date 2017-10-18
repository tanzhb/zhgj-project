package com.congmai.zhgj.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.web.dao.GroupMapper;
import com.congmai.zhgj.web.dao.UserMapper;
import com.congmai.zhgj.web.model.Group;
import com.congmai.zhgj.web.model.GroupExample;
import com.congmai.zhgj.web.model.GroupSelectExample;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.UserExample;
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
    
    @Resource
    private UserMapper userMapper;

    @Override
    public GenericDao<Group, Integer> getDao() {
        return groupMapper;
    }

    public Group selectGroupById(Integer groupId) {
        return groupMapper.selectGroupById(groupId);
    }

	@Override
	public List<String> selectUserIdsByGroupType(String type) {
		GroupSelectExample example = new GroupSelectExample();
		example.createCriteria().andTypeEqualTo(type);
		List<Group> groups = groupMapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(groups)){
			Group group = groups.get(0);
			if(group != null){
				
				UserExample userExample = new UserExample();
				userExample.createCriteria().andGroupIdEqualTo(group.getId());
				List<User> users= userMapper.selectByExample(userExample);
				List<String> userIds = new ArrayList<String>();
				if(CollectionUtils.isNotEmpty(users)){
					for(User user : users){
						userIds.add(user.getUserId().toString());
					}
				}
				return userIds;
			}
			
		}
		return null;
	}

}
