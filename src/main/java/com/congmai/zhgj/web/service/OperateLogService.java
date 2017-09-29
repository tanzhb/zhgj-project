package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.ClauseDelivery;
import com.congmai.zhgj.web.model.OperateLog;
import com.congmai.zhgj.web.model.OperateLogExample;
import com.congmai.zhgj.web.model.OrderFile;
import com.congmai.zhgj.web.model.OrderFileExample;

/**
 * 
 * @ClassName OperateLogService
 * @Description 操作日志
 * @author qfzhao
 * @Date 2017年9月23日 下午2:27:11
 * @version 1.0.0
 */
public interface OperateLogService extends GenericService<OperateLog, String> {

    List<OperateLog> selectList(OperateLogExample m);

	List<OperateLog> findDeliverLogByOrderSerialNum(String serialNum);

	List<OperateLog> findPayLogByOrderSerialNum(String serialNum);
	
}
