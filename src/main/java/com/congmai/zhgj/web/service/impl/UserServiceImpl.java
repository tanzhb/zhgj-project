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
	public void insertContract(ContractVO contractVO) {
		// TODO Auto-generated method stub
		userMapper.insertContract(contractVO);
	}

	public List<User> findAllUsers() {
		List<User> list = userMapper.findAllUsers();
		if(list != null && list.size() > 0){
			for(User user : list){
				if(user.getDelFlg() != null){
					if("0".equals(user.getDelFlg())) user.setDelFlg("否");
					else user.setDelFlg("是");
				}
			}
		}
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
}	public List<ContractVO> queryContractList(String userId) {
		// TODO Auto-generated method stub
		
		return userMapper.queryContractList(userId);
	}

	
	@Override
	public void deleteUserContractS(String ids) {
		List<String> idList = ApplicationUtils.getIdList(ids);
		userMapper.deleteUserContractS(idList);
	}

	@Override
	public ContractVO selectConbtractById(String id) {
		// TODO Auto-generated method stub
		
		return userMapper.selectContractById(id);
	}

	@Override
	public void updateContract(ContractVO contractVO) {
		// TODO Auto-generated method stub
		userMapper.updateContract(contractVO);
	}
}