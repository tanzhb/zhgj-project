package com.congmai.zhgj.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.dao.UserMapper;
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
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements
		UserService {

	@Resource
	private UserMapper userMapper;

	@Override
	public int insert(User model) {
		return userMapper.insertSelective(model);
	}

	@Override
	public int update(User model) {
		return userMapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public int delete(Long id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	public User authentication(User user) {
		return userMapper.authentication(user);
	}

	@Override
	public User selectById(Long id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public GenericDao<User, Long> getDao() {
		return userMapper;
	}

	public User selectByUsername(String username) {
		UserExample example = new UserExample();
		User user = null;
		example.createCriteria().andUsernameEqualTo(username);
		final List<User> list = userMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			user = list.get(0);
		}
		return user;
	}

	public List<User> findAllUsers() {
		List<User> list = userMapper.findAllUsers();
		return list;
	}

	public boolean isUserExist(User user) {
		boolean exist = false;
		if (userMapper.isUserExist(user) > 0) {
			exist = true;
		}
		return exist;
	}

	public void deleteUsers(String ids) {
		List<String> idList = ApplicationUtils.getIdList(ids);
		userMapper.deleteUsers(idList);
	}
}
