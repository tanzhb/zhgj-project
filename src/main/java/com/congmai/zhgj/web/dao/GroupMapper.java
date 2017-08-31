package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.Group;

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
}