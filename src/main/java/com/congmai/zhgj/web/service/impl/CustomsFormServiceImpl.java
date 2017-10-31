package com.congmai.zhgj.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.log.annotation.OperationLog;
import com.congmai.zhgj.web.dao.CustomsFormMapper;
import com.congmai.zhgj.web.dao.Delivery2Mapper;
import com.congmai.zhgj.web.dao.OrderInfoMapper;
import com.congmai.zhgj.web.model.CustomsForm;
import com.congmai.zhgj.web.model.CustomsFormExample;
import com.congmai.zhgj.web.model.CustomsFormExample.Criteria;
import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.DeliveryVO;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.StockInOutCheck;
import com.congmai.zhgj.web.model.StockInOutRecord;
import com.congmai.zhgj.web.model.TakeDelivery;
import com.congmai.zhgj.web.service.CustomsFormService;

/**
 * 
 * @ClassName CustomsFormServiceImpl
 * @Description  报关单/清关单 ServiceImpl
 * @author zhaichao
 * @Date 2017年10月23日 下午3:06:52
 * @version 1.0.0
 */
@Service
public class CustomsFormServiceImpl extends GenericServiceImpl<CustomsForm, String> implements CustomsFormService {

	
	@Resource
	private  CustomsFormMapper customsFormMapper;
	@Resource
	private  Delivery2Mapper delivery2Mapper;
	
	@Resource
	private  OrderInfoMapper  orderInfoMapper;

	
	@Override
	public GenericDao<CustomsForm, String> getDao() {
		// TODO Auto-generated method stub
		return customsFormMapper;
	}

	@Override
	/*@OperationLog(operateType = "update" ,operationDesc = "确认清/报关" ,objectSerial= "{serialNum}")*/
	public int updateStatus(CustomsForm customsForm) {
		// TODO Auto-generated method stub
		if("clearance".equals(customsForm.getCustomsFormType())){
			confirmDeclaration(customsForm);
		}else{
			confirmClearance(customsForm);
		}
		return customsFormMapper.updateByPrimaryKey(customsForm);
	}
	@OperationLog(operateType = "update" ,operationDesc = "确认报关" ,objectSerial= "{serialNum}")
	public void  confirmDeclaration(CustomsForm customsForm){
		Delivery d=new Delivery();
		d.setSerialNum(customsForm.getDeliverSerial());
		d.setStatus(DeliveryVO.COMPLETE);//发货完成
		delivery2Mapper.updateByPrimaryKeySelective(d);
	}
	@OperationLog(operateType = "update" ,operationDesc = "确认清关" ,objectSerial= "{serialNum}")
   public void  confirmClearance(CustomsForm customsForm){
		Delivery d=new Delivery();
		d.setSerialNum(customsForm.getDeliverSerial());
		d.setStatus(TakeDelivery.WAITING);//待收货
		delivery2Mapper.updateByPrimaryKeySelective(d);
		
	}
	@Override
	public List<CustomsForm> selectCustomsFormList(String type) {
		// TODO Auto-generated method stub
		CustomsFormExample   example=new CustomsFormExample();
		Criteria  criteria =example.createCriteria();
		criteria.andDelFlgEqualTo("0").andCustomsFormTypeEqualTo(type);
		return customsFormMapper.selectByExample(example);
	}

	@Override
	public void deleteCustomsForm(String serialNumList) {
		// TODO Auto-generated method stub
		List<String> idList = ApplicationUtils.getIdList(serialNumList);
		// TODO Auto-generated method stub
		customsFormMapper.deleteCustomsForm(idList);
		
	}

	
   


	

}
