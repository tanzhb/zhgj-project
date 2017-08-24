package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.ClauseCheckAccept;
import com.congmai.zhgj.web.model.ClauseCheckAcceptExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClauseCheckAcceptMapper {
    int countByExample(ClauseCheckAcceptExample example);

    int deleteByExample(ClauseCheckAcceptExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(ClauseCheckAccept record);

    int insertSelective(ClauseCheckAccept record);

    List<ClauseCheckAccept> selectByExample(ClauseCheckAcceptExample example);

    ClauseCheckAccept selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") ClauseCheckAccept record, @Param("example") ClauseCheckAcceptExample example);

    int updateByExample(@Param("record") ClauseCheckAccept record, @Param("example") ClauseCheckAcceptExample example);

    int updateByPrimaryKeySelective(ClauseCheckAccept record);

    int updateByPrimaryKey(ClauseCheckAccept record);
}