package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.NoticeShare;
import com.congmai.zhgj.web.model.NoticeShareExample;
import com.congmai.zhgj.web.model.NoticeShareKey;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface NoticeShareMapper extends GenericDao<NoticeShare, String>{
    int countByExample(NoticeShareExample example);

    int deleteByExample(NoticeShareExample example);

    int deleteByPrimaryKey(NoticeShareKey key);

    int insert(NoticeShare record);

    int insertSelective(NoticeShare record);

    List<NoticeShare> selectByExample(NoticeShareExample example);

    NoticeShare selectByPrimaryKey(NoticeShareKey key);

    int updateByExampleSelective(@Param("record") NoticeShare record, @Param("example") NoticeShareExample example);

    int updateByExample(@Param("record") NoticeShare record, @Param("example") NoticeShareExample example);

    int updateByPrimaryKeySelective(NoticeShare record);

    int updateByPrimaryKey(NoticeShare record);
}