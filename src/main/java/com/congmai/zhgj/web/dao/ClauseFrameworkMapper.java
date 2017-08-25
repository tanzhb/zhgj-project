package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.ClauseFramework;
import com.congmai.zhgj.web.model.ClauseFrameworkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClauseFrameworkMapper {
    int countByExample(ClauseFrameworkExample example);

    int deleteByExample(ClauseFrameworkExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(ClauseFramework record);

    int insertSelective(ClauseFramework record);

    List<ClauseFramework> selectByExample(ClauseFrameworkExample example);

    ClauseFramework selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") ClauseFramework record, @Param("example") ClauseFrameworkExample example);

    int updateByExample(@Param("record") ClauseFramework record, @Param("example") ClauseFrameworkExample example);

    int updateByPrimaryKeySelective(ClauseFramework record);

    int updateByPrimaryKey(ClauseFramework record);
}