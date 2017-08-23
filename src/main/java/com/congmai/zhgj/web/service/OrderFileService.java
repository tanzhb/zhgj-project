package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.OrderFile;
import com.congmai.zhgj.web.model.OrderFileExample;

/**
 * 
 * @ClassName OrderFileService
 * @Description 订单附件Service
 * @author qfzhao
 * @Date 2017年7月28日 下午3:04:17
 * @version 1.0.0
 */
public interface OrderFileService extends GenericService<OrderFile, String> {
	/**
     * 查询多个对象
     *
     * @return 对象集合
     */
    List<OrderFile> selectList(OrderFileExample m);

	public void deleteOrderFiles(String ids);

	void betchInsertOrderFiles(List<OrderFile> orderFiles);


}
