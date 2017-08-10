package com.congmai.zhgj.web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.dao.UserMapper;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.UserExample;
import com.congmai.zhgj.web.service.UserService;

/**
 * 
 * @ClassName UserServiceImpl
 * @Description 用户Service实现类
 * @author tanzb
 * @Date 2017年7月26日 下午2:56:29
 * @version 1.0.0
 */
@Service
public class UserServiceImpl extends GenericServiceImpl<User, Integer> implements
		UserService {

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
		example.createCriteria().andUsernameEqualTo(loginName);
		final List<User> list = userMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			user = list.get(0);
		}
		return user;
	}
  
	@Override
	public int update(User model) {
		return userMapper.updateByPrimaryKeySelective(model);
	}
	
}