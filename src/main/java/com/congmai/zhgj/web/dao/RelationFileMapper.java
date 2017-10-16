package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.RelationFile;
import com.congmai.zhgj.web.model.RelationFileExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface RelationFileMapper extends GenericDao<RelationFile, String>{
    int countByExample(RelationFileExample example);

    int deleteByExample(RelationFileExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(RelationFile record);

    int insertSelective(RelationFile record);

    List<RelationFile> selectByExample(RelationFileExample example);

    RelationFile selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") RelationFile record, @Param("example") RelationFileExample example);

    int updateByExample(@Param("record") RelationFile record, @Param("example") RelationFileExample example);

    int updateByPrimaryKeySelective(RelationFile record);

    int updateByPrimaryKey(RelationFile record);
}