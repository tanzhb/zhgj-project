package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.BaseVO;
import com.congmai.zhgj.web.model.ProcessBase;

public interface ProcessBaseMapper {

    int deleteByPrimaryKey(String serialNum);

    int insert(BaseVO record);

    int insertSelective(BaseVO record);

    ProcessBase selectByPrimaryKey(String serialNum);

    int updateByPrimaryKeySelective(BaseVO record);

    int updateByPrimaryKey(BaseVO record);
}