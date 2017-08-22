package com.congmai.zhgj.web.service;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.ClauseDelivery;

/**
 * 
 * @ClassName ClauseDeliveryService
 * @Description  交付条款接口
 * @author qfzhao
 * @Date 2017年8月22日 下午3:59:38
 * @version 1.0.0
 */
public interface ClauseDeliveryService extends GenericService<ClauseDelivery, String> {

	ClauseDelivery selectByContractId(String id);
	
}
