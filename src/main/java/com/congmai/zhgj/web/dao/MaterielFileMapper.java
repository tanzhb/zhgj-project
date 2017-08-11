package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.MaterielFile;
import com.congmai.zhgj.web.model.MaterielFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MaterielFileMapper {
    int countByExample(MaterielFileExample example);

    int deleteByExample(MaterielFileExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(MaterielFile record);

    int insertSelective(MaterielFile record);

    List<MaterielFile> selectByExample(MaterielFileExample example);

    MaterielFile selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") MaterielFile record, @Param("example") MaterielFileExample example);

    int updateByExample(@Param("record") MaterielFile record, @Param("example") MaterielFileExample example);

    int updateByPrimaryKeySelective(MaterielFile record);

    int updateByPrimaryKey(MaterielFile record);
}