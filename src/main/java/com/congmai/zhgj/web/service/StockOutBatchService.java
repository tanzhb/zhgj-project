package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.StockOutBatch;


/**
 * @ClassName StockOutBatchService
 * @Description TODO(出库来源批次)
 * @author zhaichao
 * @Date 2017年10月12日 下午5:33:33
 * @version 1.0.0
 */
public interface StockOutBatchService extends GenericService<StockOutBatch, String> {
   
List<StockOutBatch> selectList(String deliverMaterielSerial);//根据发货物料流水查看出库批次信息

	
}
