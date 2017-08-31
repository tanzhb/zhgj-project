package com.congmai.zhgj.web.service;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.PaymentRecord;

public interface PaymentRecordSerive extends GenericService<PaymentRecord, String>{

	Page<PaymentRecord> selectByPage(PaymentRecord collectionRecord);

}
