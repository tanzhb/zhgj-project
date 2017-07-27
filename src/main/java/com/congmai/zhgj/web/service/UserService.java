package com.congmai.zhgj.web.service;

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
public interface UserService extends GenericService<User, Long> {

    /**
     * 用户验证
     * 
     * @param user
     * @return
     */
    User authentication(User user);

    /**
     * 根据用户名查询用户
     * 
     * @param username
     * @return
     */
    User selectByUsername(String username);
}
