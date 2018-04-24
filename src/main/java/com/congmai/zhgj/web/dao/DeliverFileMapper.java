package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.DeliverFile;
import com.congmai.zhgj.web.model.DeliverFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeliverFileMapper {
    int countByExample(DeliverFileExample example);

    int deleteByExample(DeliverFileExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(DeliverFile record);

    int insertSelective(DeliverFile record);

    List<DeliverFile> selectByExample(DeliverFileExample example);

    DeliverFile selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") DeliverFile record, @Param("example") DeliverFileExample example);

    int updateByExample(@Param("record") DeliverFile record, @Param("example") DeliverFileExample example);

    int updateByPrimaryKeySelective(DeliverFile record);

    int updateByPrimaryKey(DeliverFile record);
}