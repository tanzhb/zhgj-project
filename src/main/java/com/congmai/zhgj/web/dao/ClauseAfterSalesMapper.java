package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.ClauseAfterSales;
import com.congmai.zhgj.web.model.ClauseAfterSalesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClauseAfterSalesMapper {
    int countByExample(ClauseAfterSalesExample example);

    int deleteByExample(ClauseAfterSalesExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(ClauseAfterSales record);

    int insertSelective(ClauseAfterSales record);

    List<ClauseAfterSales> selectByExample(ClauseAfterSalesExample example);

    ClauseAfterSales selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") ClauseAfterSales record, @Param("example") ClauseAfterSalesExample example);

    int updateByExample(@Param("record") ClauseAfterSales record, @Param("example") ClauseAfterSalesExample example);

    int updateByPrimaryKeySelective(ClauseAfterSales record);

    int updateByPrimaryKey(ClauseAfterSales record);
}