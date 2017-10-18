package com.congmai.zhgj.web.dao;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.Group;
import com.congmai.zhgj.web.model.GroupExample;
import com.congmai.zhgj.web.model.GroupSelectExample;

/**
 * 
 * @ClassName GroupMapper
 * @Description 组Dao 接口
 * @author tanzb
 * @Date 2017年7月26日 下午2:47:18
 * @version 1.0.0
 */
public interface GroupMapper extends GenericDao<Group, Integer> {
    /**
     * 通过组id 查询组信息
     * 
     * @param id
     * @return
     */
	Group selectGroupById(Integer id);
	
	/**
	 * 
	 * @Description (根据条件查询组信息)
	 * @param example
	 * @return
	 */
	List<Group> selectByExample(GroupSelectExample example);
}