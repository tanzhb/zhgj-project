package com.congmai.zhgj.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.web.dao.CompanyAddressMapper;
import com.congmai.zhgj.web.dao.CompanyManageMapper;
import com.congmai.zhgj.web.dao.UserMapper;
import com.congmai.zhgj.web.model.CompanyAddress;
import com.congmai.zhgj.web.model.CompanyAddressExample;
import com.congmai.zhgj.web.model.CompanyManage;
import com.congmai.zhgj.web.model.CompanyManageExample;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.service.CompanyAddressService;
import com.congmai.zhgj.web.service.CompanyManageService;
@Service
public class CompanyManageServiceImpl extends GenericServiceImpl<CompanyManage, String>
		implements CompanyManageService{

	@Autowired
	private CompanyManageMapper companyManageMapper;
	@Autowired
	private UserMapper userMapper;
	@Override
	public GenericDao<CompanyManage, String> getDao() {
		
		return this.companyManageMapper;
	}

	@Override
	public List<CompanyManage> selectListByComId(String comId) {
		CompanyManageExample example = new CompanyManageExample();
		example.createCriteria().andComIdEqualTo(comId);
		List<CompanyManage>cm=companyManageMapper.selectByExample(example);
		String[]s=cm.get(0).getUserId().split(",");
		List<User>users=new ArrayList<User>();
		for(int i=0;i<s.length;i++){
			User u=userMapper.selectByPrimaryKey(Integer.parseInt(s[i]));
			if(u!=null){
				users.add(u);
			}
		}
		cm.get(0).setUsers(users);
		return cm ;
	}

	@Override
	public void deleteByComId(String comId) {
		// TODO Auto-generated method stub
		CompanyManageExample example = new CompanyManageExample();
		example.createCriteria().andComIdEqualTo(comId);
		 companyManageMapper.deleteByExample(example);
	}
}
