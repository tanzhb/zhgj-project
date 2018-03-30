package com.congmai.zhgj.web.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.web.dao.PayMapper;
import com.congmai.zhgj.web.dao.PaymentRecordMapper;
import com.congmai.zhgj.web.model.PaymentRecord;
import com.congmai.zhgj.web.model.PaymentRecordExample;
import com.congmai.zhgj.web.model.PaymentRecordExample.Criteria;
import com.congmai.zhgj.web.service.PaymentRecordSerive;
@Service
public class PaymentRecordServiceImpl extends GenericServiceImpl<PaymentRecord, String>
		implements PaymentRecordSerive {

	@Autowired
	private PaymentRecordMapper paymentRecordMapper;//PayMapper
	@Autowired
	private PayMapper payMapper;
	@Override
	public GenericDao<PaymentRecord, String> getDao() {
		
		return this.paymentRecordMapper;
	}
	
	@Override
	public Page<PaymentRecord> selectByPage(PaymentRecord record) {
		PaymentRecordExample example = new PaymentRecordExample();
		Criteria c = example.createCriteria();
		c.andDelFlgEqualTo("0");
		if(record.getBuyComId()==null){
			c.andBuyComIdIsNull();
		}else if(StringUtils.isNotBlank(record.getBuyComId())){
			c.andBuyComIdEqualTo(record.getBuyComId());
		}
		
		if(record.getSupplyComId()==null){
			c.andSupplyComIdIsNull();
			
		}else if(StringUtils.isNotBlank(record.getSupplyComId())){
			c.andSupplyComIdEqualTo(record.getSupplyComId());
		}
		//example.setPageIndex(0);
		//example.setPageSize(-1);
		Page<PaymentRecord> page = new Page<PaymentRecord>(0, -1);
		page.setResult(paymentRecordMapper.selectByExample(example));
		page.setTotalCount(paymentRecordMapper.countByExample(example));
		return page;
	}

	@Override
	public String selectPaiedMoney(String serialNum) {
		// TODO Auto-generated method stub
		return payMapper.selectPaiedMoney(serialNum);
	}

	@Override
	public String selectBilledMoney(String serialNum) {
		// TODO Auto-generated method stub
		return payMapper.selectBilledMoney(serialNum);
	}

	


}
