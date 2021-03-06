package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.Group;
import com.congmai.zhgj.web.model.Role;
import com.congmai.zhgj.web.model.User;

/**
 * 
 * @ClassName GroupService
 * @Description 组 业务接口
 * @author tanzb
 * @Date 2017年7月26日 下午2:55:06
 * @version 1.0.0
 */
public interface GroupService extends GenericService<Group, Integer> {
    /**
     * 通过组groupId 查询组信息
     * 
     * @param groupId
     * @return
     */
	Group selectGroupById(Integer groupId);
	
	/**
	 * 
	 * @Description (根据用户类型获得用户组组员id)
	 * @param type
	 * @return
	 */
	List<User> selectUserIdsByGroupType(String type);
}
