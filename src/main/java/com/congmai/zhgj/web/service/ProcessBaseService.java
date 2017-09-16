package com.congmai.zhgj.web.service;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.BaseVO;
import com.congmai.zhgj.web.model.ClauseAdvance;
import com.congmai.zhgj.web.model.ProcessBase;

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
}
