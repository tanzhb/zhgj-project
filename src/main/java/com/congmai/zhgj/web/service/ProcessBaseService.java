package com.congmai.zhgj.web.service;

import java.util.List;
import java.util.Map;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.BaseVO;
import com.congmai.zhgj.web.model.ClauseAdvance;
import com.congmai.zhgj.web.model.HistoricTaskVO;
import com.congmai.zhgj.web.model.ProcessBase;
import com.congmai.zhgj.web.model.User;

/**
 * 
 * @ClassName ProcessBaseService
 * @Description TODO(流程相关字段)
 * @author qfzhao
 * @Date 2017年9月16日 下午2:33:40
 * @version 1.0.0
 */
public interface ProcessBaseService extends GenericService<BaseVO, String> {

	ProcessBase selectBaseById(String id);

	List<HistoricTaskVO> findFinishedTaskInstancesDiy(Map map);

	void insertHistoricTask(HistoricTaskVO historicTaskVO);
}
