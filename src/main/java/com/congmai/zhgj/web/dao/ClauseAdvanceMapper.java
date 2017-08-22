package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.ClauseAdvance;
import com.congmai.zhgj.web.model.ClauseAdvanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClauseAdvanceMapper {
    int countByExample(ClauseAdvanceExample example);

    int deleteByExample(ClauseAdvanceExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(ClauseAdvance record);

    int insertSelective(ClauseAdvance record);

    List<ClauseAdvance> selectByExample(ClauseAdvanceExample example);

    ClauseAdvance selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") ClauseAdvance record, @Param("example") ClauseAdvanceExample example);

    int updateByExample(@Param("record") ClauseAdvance record, @Param("example") ClauseAdvanceExample example);

    int updateByPrimaryKeySelective(ClauseAdvance record);

    int updateByPrimaryKey(ClauseAdvance record);
}