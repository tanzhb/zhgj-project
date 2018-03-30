package com.congmai.zhgj.web.service;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.PaymentRecord;

public interface PaymentRecordSerive extends GenericService<PaymentRecord, String>{

	Page<PaymentRecord> selectByPage(PaymentRecord collectionRecord);
	 /**
     * 查询订单已付金额
     * @param record
     */
    public String selectPaiedMoney(String serialNum);
    
    
    
    /**
     * 查询订单已开票金额
     * @param record
     */
    public String selectBilledMoney(String serialNum);
    
    

}
