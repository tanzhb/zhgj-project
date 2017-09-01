package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.Resource;

/**
 * 
 * @ClassName ResourceService
 * @Description 资源 业务接口
 * @author tanzb
 * @Date 2017年7月26日 下午2:54:46
 * @version 1.0.0
 */
public interface ResourceService extends GenericService<Resource, Integer> {

    /**
     * 通过组id 查询组 拥有的资源
     * 
     * @param groupId
     * @return
     */
    List<Resource> selectResourcesByGroupId(Integer groupId);

}
