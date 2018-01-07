package com.congmai.zhgj.web.dao;

import java.util.List;
import java.util.Map;

import com.congmai.zhgj.web.model.BaseVO;
import com.congmai.zhgj.web.model.HistoricTaskVO;
import com.congmai.zhgj.web.model.ProcessBase;

public interface ProcessBaseMapper {

    int deleteByPrimaryKey(String serialNum);

    int insert(BaseVO record);

    int insertSelective(BaseVO record);

    ProcessBase selectByPrimaryKey(String serialNum);

    int updateByPrimaryKeySelective(BaseVO record);

    int updateByPrimaryKey(BaseVO record);

	List<HistoricTaskVO> findFinishedTaskInstancesDiy(Map map);

	void insertHistoricTask(HistoricTaskVO historicTaskVO);
	void updateHistoricTask(HistoricTaskVO historicTaskVO);
}