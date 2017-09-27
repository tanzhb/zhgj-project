/**
 * Project Name:SpringOA
 * File Name:UserServiceImpl.java
 * Package Name:com.zml.oa.service.impl
 * Date:2014-11-9上午12:53:20
 *
 */
package com.congmai.zhgj.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.web.dao.UserMapper;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.UserExample;
import com.congmai.zhgj.web.service.UserService;

/**
 * 
 * @ClassName UserServiceImpl
 * @Description 用户服务实现类
 * @author tanzb
 * @Date 2017年8月23日 上午10:40:59
 * @version 1.0.0
 */

@Service
public class UserServiceImpl extends GenericServiceImpl<User, Integer> implements UserService {
	
	@Resource
	private UserMapper userMapper;



	public User authentication(User user) {
		return userMapper.authentication(user);
	}


	@Override
	public GenericDao<User, Integer> getDao() {
		return userMapper;
	}

	public User selectByUsername(String loginName) {
		UserExample example = new UserExample();
		User user = null;
		example.createCriteria().andUserNameEqualTo(loginName);
		List<User> list = null;
		try{
			list = userMapper.selectByExample(example);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if (list != null && list.size() > 0) {
			user = list.get(0);
		}
		return user;
	}
  
	@Override
	public int update(User model) {
		return userMapper.updateByPrimaryKeySelective(model);
	}


	@Override
	public User getUserInfo(Integer userId) {
		// TODO Auto-generated method stub
		return userMapper.getUserInfo(userId);
	}


	@Override
	public Company getUserCompanyInfo(Integer userId) {
		// TODO Auto-generated method stub
		return userMapper.getUserCompanyInfo(userId);
	}
}
