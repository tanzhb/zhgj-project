package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.User;

/**
 * 
 * @ClassName UserService
 * @Description  用户 业务 接口
 * @author tanzb
 * @Date 2017年7月26日 下午2:55:25
 * @version 1.0.0
 */
public interface UserService extends GenericService<User, Integer> {

    /**
     * 用户验证
     * 
     * @param user
     * @return
     */
    User authentication(User user);
 
    /**
     * 
     * @Description 通过用户名找用户
     * @param id
     * @return
     */
	User selectByUsername(String name);
	
    /**
     * 
     * @Description 更新用户
     * @param id
     * @return
     */
	int update(User user);
 
}
