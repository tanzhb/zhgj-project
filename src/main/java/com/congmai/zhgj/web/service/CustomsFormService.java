package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.CustomsForm;
import com.congmai.zhgj.web.model.CustomsFormExample;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielExample;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.OrderInfoExample;
import com.congmai.zhgj.web.model.PriceList;
import com.congmai.zhgj.web.model.RelationFile;
import com.congmai.zhgj.web.model.User;

/**
 * 
 * @ClassName 报关/清关
 * @Description 报关/清关Service
 * @author 翟超
 * @Date 2017年10月20日 下午3:04:17
 * @version 1.0.0
 */
public interface CustomsFormService extends GenericService<CustomsForm, String> {
	

	int updateStatus(CustomsForm customsForm);
	 List<CustomsForm> selectCustomsFormList(String  type );
	 void deleteCustomsForm(String serialNumList);
	 
	
	    
	   


}
