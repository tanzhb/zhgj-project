package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.UserCompanyExample;
import com.congmai.zhgj.web.model.UserCompanyKey;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserCompanyMapper extends GenericDao<UserCompanyKey, String>{
    int countByExample(UserCompanyExample example);

    int deleteByExample(UserCompanyExample example);

    int deleteByPrimaryKey(UserCompanyKey key);

    int insert(UserCompanyKey record);

    int insertSelective(UserCompanyKey record);

    List<UserCompanyKey> selectByExample(UserCompanyExample example);

    int updateByExampleSelective(@Param("record") UserCompanyKey record, @Param("example") UserCompanyExample example);

    int updateByExample(@Param("record") UserCompanyKey record, @Param("example") UserCompanyExample example);
    
    String selectComIdByUserId(String user_id);
}