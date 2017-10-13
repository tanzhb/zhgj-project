package com.congmai.zhgj.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.dao.DeliveryMaterielMapper;
import com.congmai.zhgj.web.dao.StockInOutRecordMapper;
import com.congmai.zhgj.web.dao.StockMapper;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.Stock;
import com.congmai.zhgj.web.model.StockExample;
import com.congmai.zhgj.web.model.StockExample.Criteria;
import com.congmai.zhgj.web.model.StockInOutRecord;
import com.congmai.zhgj.web.service.StockService;

/**
 * 
 * @ClassName StockServiceImpl
 * @Descripzhaichao
 * @Date 2017年8月21日 下午4:56:29
 * @version 1.0.0
 */
@Service
public class StockServiceImpl extends GenericServiceImpl<Stock, String> implements StockService {

	 @Resource
	    private StockMapper  stockMapper;
	 @Resource
	    private StockInOutRecordMapper stockInOutRecordMapper;
	 
	 @Resource
	    private DeliveryMaterielMapper deliveryMaterielMapper;

	@Override
	public GenericDao<Stock, String> getDao() {
		return stockMapper;
	}

	@Override
	public void deleteStock(String serialNums) {
		List<String> idList = ApplicationUtils.getIdList(serialNums);
		stockMapper.deleteStock(idList);
		
	}

	@Override
	public List<Stock> selectStockList(String  manageType) {
		StockExample se=new  StockExample();
    	Criteria criteria=se.createCriteria();
    	criteria.andDelFlgEqualTo("0");
    	criteria.andManageTypeEqualTo(manageType);
		return stockMapper.selectByExample(se);
	}

	@Override
	public List<String> getRelationDeliverSerialList(Stock stock) {
		return stockInOutRecordMapper.getRelationDeliverSerial(stock);
	}

	@Override
	public List<String> getRelationTakeDeliverSerialList(Stock stock) {
		return stockInOutRecordMapper.getRelationTakeDeliverSerial(stock);
	}

	@Override
	public List<DeliveryMateriel> getDeliverMaterialListForIn(
			List<String> takeDeliverSerialList,String buyComId) {
		List<DeliveryMateriel>deliverMateriels=null;
		if(StringUtils.isEmpty(buyComId)){
			deliverMateriels= deliveryMaterielMapper.getDetailByRelationTakeDeliverSerialList(takeDeliverSerialList);
		}else{
			deliverMateriels= deliveryMaterielMapper.getDetailByRelationTakeDeliverSerialList(takeDeliverSerialList);
			List<DeliveryMateriel>deliverMaterielsTwo=new ArrayList<DeliveryMateriel>();
			for(DeliveryMateriel deliverMateriel: deliverMateriels){
				if("zhgj".equals(buyComId)&&StringUtils.isEmpty(deliverMateriel.getBuyComId())){
					deliverMaterielsTwo.add(deliverMateriel);
				}else if(buyComId.equals(deliverMateriel.getBuyComId())){
					deliverMaterielsTwo.add(deliverMateriel);
				}
			}
			deliverMateriels=deliverMaterielsTwo;
		}
		return deliverMateriels;
	}

	@Override
	public List<DeliveryMateriel> getDeliverMaterialListForOut(
			List<String> deliverSerialList) {
		return deliveryMaterielMapper.getDetailByRelationDeliverSerialList(deliverSerialList);
	}

	@Override
	public List<Stock> selectStockListByMaterielSerial(String materielSerial) {
		StockExample example=new StockExample();
		Criteria criteria=example.createCriteria();
    	criteria.andDelFlgEqualTo("0");
    	criteria.andManageTypeEqualTo("1");
		return stockMapper.selectByExample(example);
	}
   
	
	}
	
	
	  
	    

   

