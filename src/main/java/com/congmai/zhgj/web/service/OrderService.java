package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielExample;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.OrderInfoExample;
import com.congmai.zhgj.web.model.User;

/**
 * 
 * @ClassName OrderService
 * @Description 订单Service
 * @author qfzhao
 * @Date 2017年7月28日 下午3:04:17
 * @version 1.0.0
 */
public interface OrderService extends GenericService<OrderInfo, String> {
	/**
     * 查询多个对象
     *
     * @return 对象集合
     */
    List<OrderInfo> selectList(OrderInfoExample m);
    
    
    List<OrderInfo> selectFramList(OrderInfo parm);
    
    List<OrderInfo> selectCommenList(OrderInfo m);
    
	public void deleteOrderInfos(String ids);

	void insertContract(ContractVO contract);

	void updateContract(ContractVO contract);


	void reciveOrder(OrderInfo orderInfo);
	
	int updateStatus(OrderInfo orderInfo);
	
	String getNumCode(String codeType);

	String checkNum(OrderInfo orderInfo);
	
	Boolean isExist(String  codeType,String num,String serialNum);//判断编号是否重复


	int submitOrder(OrderInfo orderInfo);


	int acceptSubmit(OrderInfo orderInfo);


	int updateOrderRelation(OrderInfo updateOrderInfo);


	int supplyConfirmed(OrderInfo orderInfo);


	int pingTaiSubmit(OrderInfo orderInfo);
	
	OrderInfo selectByOrderNum(String orderNum);

}
