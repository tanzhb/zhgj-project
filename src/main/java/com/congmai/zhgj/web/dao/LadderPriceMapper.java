package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.LadderPrice;
import com.congmai.zhgj.web.model.LadderPriceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LadderPriceMapper {
    int countByExample(LadderPriceExample example);

    int deleteByExample(LadderPriceExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(LadderPrice record);

    int insertSelective(LadderPrice record);

    List<LadderPrice> selectByExample(LadderPriceExample example);

    LadderPrice selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") LadderPrice record, @Param("example") LadderPriceExample example);

    int updateByExample(@Param("record") LadderPrice record, @Param("example") LadderPriceExample example);

    int updateByPrimaryKeySelective(LadderPrice record);

    int updateByPrimaryKey(LadderPrice record);
}