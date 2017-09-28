package com.congmai.zhgj.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.UserExample;

/**
 * @author tanzb
 * @version 2017年8月9日 下午10:22:09
 */
public interface UserMapper extends GenericDao<User, Integer>{
    /**
     * 用户登录验证查询
     * 
     * @param record
     * @return
     */
    User authentication(@Param("record") User record);
    
    List<User> selectByExample(UserExample example);
    
    int updateByPrimaryKeySelective(User record);
    
    
    public User getUserInfo(Integer userId);
    
    
    
    public Company getUserCompanyInfo(Integer userId);
    
    
    
    public void updateUserInfo(User user);
    
    
    public void updateCompanyInfo(Company company);
    
    
    public void updateEmail(User user);
}
