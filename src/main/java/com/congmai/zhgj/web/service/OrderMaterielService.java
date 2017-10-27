package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielExample;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.OrderInfoExample;
import com.congmai.zhgj.web.model.OrderMateriel;
import com.congmai.zhgj.web.model.OrderMaterielExample;
import com.congmai.zhgj.web.model.User;

/**
 * 
 * @ClassName MaterielService
 * @Description 订单物料Service
 * @author qfzhao
 * @Date 2017年7月28日 下午3:04:17
 * @version 1.0.0
 */
public interface OrderMaterielService extends GenericService<OrderMateriel, String> {
	/**
     * 查询多个对象
     *
     * @return 对象集合
     */
    List<OrderMateriel> selectList(OrderMaterielExample m);

	public void deleteOrderMateriels(String ids);

	void betchInsertOrderMateriel(List<OrderMateriel> orderMateriel);


}
