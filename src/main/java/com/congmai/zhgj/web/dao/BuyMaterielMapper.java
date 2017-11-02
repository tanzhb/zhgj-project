package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.BuyMateriel;
import com.congmai.zhgj.web.model.BuyMaterielExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuyMaterielMapper {
    int countByExample(BuyMaterielExample example);

    int deleteByExample(BuyMaterielExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(BuyMateriel record);

    int insertSelective(BuyMateriel record);

    List<BuyMateriel> selectByExample(BuyMaterielExample example);

    BuyMateriel selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") BuyMateriel record, @Param("example") BuyMaterielExample example);

    int updateByExample(@Param("record") BuyMateriel record, @Param("example") BuyMaterielExample example);

    int updateByPrimaryKeySelective(BuyMateriel record);

    int updateByPrimaryKey(BuyMateriel record);
}