package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.OrderFile;
import com.congmai.zhgj.web.model.OrderFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderFileMapper {
    int countByExample(OrderFileExample example);

    int deleteByExample(OrderFileExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(OrderFile record);

    int insertSelective(OrderFile record);

    List<OrderFile> selectByExample(OrderFileExample example);

    OrderFile selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") OrderFile record, @Param("example") OrderFileExample example);

    int updateByExample(@Param("record") OrderFile record, @Param("example") OrderFileExample example);

    int updateByPrimaryKeySelective(OrderFile record);

    int updateByPrimaryKey(OrderFile record);
}