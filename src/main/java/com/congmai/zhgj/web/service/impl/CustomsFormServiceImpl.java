package com.congmai.zhgj.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.StringUtil;
import com.congmai.zhgj.log.annotation.OperationLog;
import com.congmai.zhgj.web.dao.CustomsFormMapper;
import com.congmai.zhgj.web.dao.Delivery2Mapper;
import com.congmai.zhgj.web.dao.OrderInfoMapper;
import com.congmai.zhgj.web.dao.StockInOutCheckMapper;
import com.congmai.zhgj.web.dao.StockInOutRecordMapper;
import com.congmai.zhgj.web.dao.TakeDeliveryMapper;
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
import com.congmai.zhgj.web.service.OrderService;

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
	
	@Resource
	private  TakeDeliveryMapper takeDeliveryMapper;

	@Resource
	private StockInOutCheckMapper stockInOutCheckMapper;
	@Resource
	private StockInOutRecordMapper stockInOutRecordMapper;
	@Resource
	private OrderService orderService;
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
			confirmClearance(customsForm);
		}else{
			confirmDeclaration(customsForm);
		}
		return customsFormMapper.updateByPrimaryKey(customsForm);
	}
	@OperationLog(operateType = "update" ,operationDesc = "确认报关" ,objectSerial= "{serialNum}")
	public void  confirmDeclaration(CustomsForm customsForm){
		Delivery d=new Delivery();
		d.setSerialNum(customsForm.getDeliverSerial());
		//d.setStatus(DeliveryVO.COMPLETE);//发货完成
		d.setStatus(DeliveryVO.WAIT_TAKEDELIVER_DELIVERY);//待收货
		delivery2Mapper.updateByPrimaryKeySelective(d);
		Delivery delivery=delivery2Mapper.selectByDeliveryPrimaryKey(customsForm.getDeliverSerial());
		OrderInfo o=orderInfoMapper.selectByPrimaryKey(delivery.getOrderSerial());
		OrderInfo orderInfo=new OrderInfo();
		orderInfo.setSerialNum(delivery.getOrderSerial());
		//orderInfo.setDeliverStatus(OrderInfo.COMPLETE);
		orderInfo.setDeliverStatus(orderInfo.WAIT_TAKEDELIVER);//待收货
		orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
		TakeDelivery takeDelivery = new TakeDelivery();
		TakeDelivery takeDelivery1 = takeDeliveryMapper.selectTakeDeliveryByDeliveryId(customsForm.getDeliverSerial());
		takeDelivery.setSerialNum(takeDelivery1.getSerialNum());
		//takeDelivery.setStatus(TakeDelivery.COMPLETE_Declaration);//已报关
		takeDelivery.setStatus(TakeDelivery.WAITING);//待收货
		takeDeliveryMapper.updateByPrimaryKeySelective(takeDelivery);
	}
	@OperationLog(operateType = "update" ,operationDesc = "确认清关" ,objectSerial= "{serialNum}")
   public void  confirmClearance(CustomsForm customsForm){
		/*Delivery d=new Delivery();
		d.setSerialNum(customsForm.getDeliverSerial());
		d.setStatus(TakeDelivery.WAITING);//待收货
		delivery2Mapper.updateByPrimaryKeySelective(d);*/
		
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		
		Delivery delivery=delivery2Mapper.selectByDeliveryPrimaryKey(customsForm.getDeliverSerial());
		OrderInfo o=orderInfoMapper.selectByPrimaryKey(delivery.getOrderSerial());
		OrderInfo orderInfo=new OrderInfo();
		orderInfo.setSerialNum(delivery.getOrderSerial());
		TakeDelivery takeDelivery = new TakeDelivery();
		takeDelivery = takeDeliveryMapper.selectTakeDeliveryByDeliveryId(customsForm.getDeliverSerial());
		Delivery deliverynew =new  Delivery();
		deliverynew.setSerialNum(takeDelivery.getDeliverSerial());
		if(StringUtil.isNotEmpty(o.getContractContent())&&"1".equals(o.getContractContent().substring(4, 5))){//验收条款有效
			takeDelivery.setStatus(TakeDelivery.APPLY_COMPLETE); //待检验
			this.createStockInCheckRecord(takeDelivery,currenLoginName);
			orderInfo.setDeliverStatus(orderInfo.WAIT_IN_CHECK);//已收货待检验
			deliverynew.setStatus(DeliveryVO.WAIT_CHECK);
		}else{
			takeDelivery.setStatus(TakeDelivery.CHECK_COMPLETE); //已完成
			orderInfo.setDeliverStatus(orderInfo.WAIT_INRECORD);//待入库
			deliverynew.setStatus(DeliveryVO.WAIT_IN_RECORD);
			//生成入库单
			StockInOutRecord stockInOutRecord=new StockInOutRecord();
			stockInOutRecord.setSerialNum(ApplicationUtils.random32UUID());
			stockInOutRecord.setTakeDeliverSerial(takeDelivery.getSerialNum());
			stockInOutRecord.setDeliverSerial("");
			stockInOutRecord.setInOutNum(orderService.getNumCode("IN"));
			stockInOutRecord.setDelFlg("0");
			stockInOutRecord.setStatus("0");
			stockInOutRecord.setCreator(currenLoginName);
			stockInOutRecord.setCreateTime(new Date());
			stockInOutRecord.setUpdater(currenLoginName);
			stockInOutRecord.setUpdateTime(new Date());
			stockInOutRecordMapper.insert(stockInOutRecord);
			
		}
		orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
		takeDeliveryMapper.updateByPrimaryKeySelective(takeDelivery);
		delivery2Mapper.updateByPrimaryKeySelective(deliverynew);
		
	}
	
	
	/**
	 * 生成入库检验单
	 */
	public void createStockInCheckRecord(TakeDelivery takeDelivery,
			String currenLoginName){
		StockInOutCheck check = new StockInOutCheck();
		check.setSerialNum(ApplicationUtils.random32UUID());
		check.setTakeDeliverSerial(takeDelivery.getSerialNum());
		check.setDeliverSerial("checkin");
		check.setCheckNum(orderService.getNumCode("QU"));
		check.setStatus("0");
		check.setDelFlg("0");
		check.setCreator(currenLoginName);
		check.setCreateTime(new Date());
		check.setUpdater(currenLoginName);
		check.setUpdateTime(new Date());
		stockInOutCheckMapper.insert(check);
		
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

	@Override
	public List<CustomsForm> selectCustomsFormList(String type,
			String orderSerial) {
		CustomsFormExample   example=new CustomsFormExample();
		Criteria  criteria =example.createCriteria();
		criteria.andDelFlgEqualTo("0").andCustomsFormTypeEqualTo(type).andOrderSerialEqualTo(orderSerial);
		if("declaration".equals(type)){
			//criteria.andStatusEqualTo("1");	
			criteria.andStatusEqualTo("0");	
		}else{
			criteria.andStatusEqualTo("0");
		}
		return customsFormMapper.selectByExample(example);
	}

	
   


	

}
