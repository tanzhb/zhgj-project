package com.congmai.zhgj.web.dao;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.Resource;

/**
 * 
 * @ClassName ResourceMapper
 * @Description 资源Dao 接口
 * @author tanzb
 * @Date 2017年7月26日 下午2:47:18
 * @version 1.0.0
 */
public interface ResourceMapper extends GenericDao<Resource, Integer> {
    /**
     * 通过组id 查询组 拥有的资源
     * 
     * @param groupId
     * @return
     */
    List<Resource> selectResourcesByGroupId(Integer groupId);
}