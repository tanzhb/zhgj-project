package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.BOMMateriel;
import com.congmai.zhgj.web.model.BOMMaterielExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BOMMaterielMapper {
    int countByExample(BOMMaterielExample example);

    int deleteByExample(BOMMaterielExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(BOMMateriel record);

    int insertSelective(BOMMateriel record);

    List<BOMMateriel> selectByExample(BOMMaterielExample example);

    BOMMateriel selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") BOMMateriel record, @Param("example") BOMMaterielExample example);

    int updateByExample(@Param("record") BOMMateriel record, @Param("example") BOMMaterielExample example);

    int updateByPrimaryKeySelective(BOMMateriel record);

    int updateByPrimaryKey(BOMMateriel record);
}