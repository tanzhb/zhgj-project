package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.ContractVO;
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
     * 
     * @Description 查找所有用户信息
     * @return
     */
    List<User> findAllUsers();
    
    /**
     * 
     * @Description 某用户是否存在
     * @param user
     * @return
     */
    boolean isUserExist(User user);
    /**
     * 
     * @Description 通过id找用户
     * @param id
     * @return
     */
    User selectById(Long id);
    /**
     * 
     * @Description 通过用户名找用户
     * @param id
     * @return
     */
	User selectByUsername(String name);
    /**
     * 
     * @Description 添加用户
     * @param id
     * @return
     */
	int insert(User user);
    /**
     * 
     * @Description 更新用户
     * @param id
     * @return
     */
	int update(User user);
    /**
     * 
     * @Description 根据id删除某用户
     * @param id
     * @return
     */
	int delete(Long id);
    /**
     * 
     * @Description 批量删除
     * @param ids
     * @return
     */
	void deleteUsers(String ids);
    
}
