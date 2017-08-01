package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MaterielMapper {
    int countByExample(MaterielExample example);

    int deleteByExample(MaterielExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(Materiel record);

    int insertSelective(Materiel record);

    List<Materiel> selectByExample(MaterielExample example);

    Materiel selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") Materiel record, @Param("example") MaterielExample example);

    int updateByExample(@Param("record") Materiel record, @Param("example") MaterielExample example);

    int updateByPrimaryKeySelective(Materiel record);

}