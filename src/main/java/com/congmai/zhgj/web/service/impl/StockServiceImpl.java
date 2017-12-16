package com.congmai.zhgj.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.dao.DeliveryMaterielMapper;
import com.congmai.zhgj.web.dao.StockInBatchMapper;
import com.congmai.zhgj.web.dao.StockInOutRecordMapper;
import com.congmai.zhgj.web.dao.StockMapper;
import com.congmai.zhgj.web.dao.StockOutBatchMapper;
import com.congmai.zhgj.web.dao.WarehouseMapper;
import com.congmai.zhgj.web.dao.WarehousepositionMapper;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.Stock;
import com.congmai.zhgj.web.model.StockExample;
import com.congmai.zhgj.web.model.StockOutBatchExample;
import com.congmai.zhgj.web.model.Warehouse;
import com.congmai.zhgj.web.model.StockExample.Criteria;
import com.congmai.zhgj.web.model.StockInBatch;
import com.congmai.zhgj.web.model.StockOutBatch;
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
	 
	 @Resource
	    private StockOutBatchMapper stockOutBatchMapper;
	 @Resource
	    private WarehouseMapper  warehouseMapper;
	 @Resource
	    private WarehousepositionMapper  warehousepositionMapper;
	 @Resource
	    private StockInBatchMapper  stockInBatchMapper;
	 

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
		List<String> Strings=null;
		if("1".equals(stock.getManageType())){//自建
			Strings= stockInOutRecordMapper.getRelationDeliverSerialForZijian(stock);
		}else if("2".equals(stock.getManageType())){//代管
			Strings= stockInOutRecordMapper.getRelationDeliverSerialForDaiguan(stock);
		}
		/*return deliveryMaterielMapper.getDetailByRelationDeliverSerialList(deliverSerialList);*/
		return Strings;
		//return stockInOutRecordMapper.getRelationDeliverSerial(stock);
	}

	@Override
	public List<String> getRelationTakeDeliverSerialList(Stock stock) {
		List<String> Strings=null;
		if("1".equals(stock.getManageType())){//自建
			Strings= stockInOutRecordMapper.getRelationTakeDeliverSerialForZijian(stock);
		}else if("2".equals(stock.getManageType())){//代管
			Strings= stockInOutRecordMapper.getRelationTakeDeliverSerialForDaiguan(stock);
		}
		/*return deliveryMaterielMapper.getDetailByRelationDeliverSerialList(deliverSerialList);*/
		return Strings;
		//return stockInOutRecordMapper.getRelationTakeDeliverSerial(stock);
	}

	@Override
	public List<DeliveryMateriel> getDeliverMaterialListForIn(
			List<String> takeDeliverSerialList,String buyComId,Stock   stock) {
		List<DeliveryMateriel>deliverMateriels=new ArrayList<DeliveryMateriel>();
		if (CollectionUtils.isNotEmpty(takeDeliverSerialList)) {
			if (StringUtils.isEmpty(buyComId)) {
				if ("1".equals(stock.getManageType())) {// 自建
					deliverMateriels = deliveryMaterielMapper
							.getDetailByRelationTakeDeliverSerialListForZijian(takeDeliverSerialList);
				} else if ("2".equals(stock.getManageType())) {
					deliverMateriels = deliveryMaterielMapper
							.getDetailByRelationTakeDeliverSerialListForDaiguan(takeDeliverSerialList);
				}

			} else {
				deliverMateriels = deliveryMaterielMapper
						.getDetailByRelationTakeDeliverSerialList(takeDeliverSerialList);
				List<DeliveryMateriel> deliverMaterielsTwo = new ArrayList<DeliveryMateriel>();
				for (DeliveryMateriel deliverMateriel : deliverMateriels) {
					if (StringUtils.isEmpty(deliverMateriel
									.getBuyComId())) {
						deliverMaterielsTwo.add(deliverMateriel);
					} else if (buyComId.equals(deliverMateriel.getBuyComId())) {
						deliverMaterielsTwo.add(deliverMateriel);
					}
				}
				deliverMateriels = deliverMaterielsTwo;
			}
		}
		for(DeliveryMateriel d:deliverMateriels){
			List<StockInBatch> stockInBatchs=d.getStockInBatchs();
			String  batchNum="";
			String  warehouseName="";
			String  positionNum="";
			String   warehouseSerial="";//仓库流水
			String   positionSerial="";//库区流水
			if(CollectionUtils.isNotEmpty(stockInBatchs)&&stockInBatchs.size()>0){
				for(int i=0;i<stockInBatchs.size();i++){
					StockInBatch s=stockInBatchs.get(i);
						batchNum=batchNum.concat(s.getBatchNum());
						if(!(warehouseSerial.indexOf(s.getWarehouseSerial())>-1)){
							warehouseName=warehouseName.concat(warehouseMapper.selectByPrimaryKey(s.getWarehouseSerial()).getWarehouseName());
							warehouseSerial=warehouseSerial.concat(s.getWarehouseSerial());
						}
						if(!(positionSerial.indexOf(s.getPositionSerial())>-1)){
							positionNum=positionNum.concat(warehousepositionMapper.selectByPrimaryKey(s.getPositionSerial()).getPositionNum());
							positionSerial=warehouseSerial.concat(s.getPositionSerial());
						}
					if(i!=stockInBatchs.size()-1){
						batchNum=batchNum.concat(";");
						warehouseName=warehouseName.concat(";");
						positionNum=positionNum.concat(";");
					}
			}
				d.setBatchNum(batchNum);
				d.setPositionNum(positionNum);
				d.setWarehouseName(warehouseName);
				}
				
			}
		return deliverMateriels;
	}

	@Override
	public List<DeliveryMateriel> getDeliverMaterialListForOut(
			List<String> deliverSerialList,Stock   stock) {
		List<DeliveryMateriel> deliveryMateriels=new  ArrayList<DeliveryMateriel>();
		if (CollectionUtils.isNotEmpty(deliverSerialList)) {
			if ("1".equals(stock.getManageType())) {// 自建
				deliveryMateriels = deliveryMaterielMapper
						.getDetailByRelationDeliverSerialListForZijian(deliverSerialList);
			} else if ("2".equals(stock.getManageType())) {// 代管
				deliveryMateriels = deliveryMaterielMapper
						.getDetailByRelationDeliverSerialListForDaiguan(deliverSerialList);
			}
		}
		/*return deliveryMaterielMapper.getDetailByRelationDeliverSerialList(deliverSerialList);*/
		for(DeliveryMateriel d:deliveryMateriels){
			List<StockOutBatch> stockOutMateriels=d.getStockOutMateriels();
			String  batchNum="";
			String  warehouseName="";
			String  positionNum="";
			String   warehouseSerial="";//仓库流水
			String   positionSerial="";//库区流水
			if(CollectionUtils.isNotEmpty(stockOutMateriels)&&stockOutMateriels.size()>0){
				for(int i=0;i<stockOutMateriels.size();i++){
					StockInBatch s=stockInBatchMapper.selectByPrimaryKey(stockOutMateriels.get(i).getStockInBatchSerial());
						batchNum=batchNum.concat(s.getBatchNum());
						if(!(warehouseSerial.indexOf(s.getWarehouseSerial())>-1)){
							warehouseName=warehouseName.concat(warehouseMapper.selectByPrimaryKey(s.getWarehouseSerial()).getWarehouseName());
							warehouseSerial=warehouseSerial.concat(s.getWarehouseSerial());
						}
						if(!(positionSerial.indexOf(s.getPositionSerial())>-1)){
							positionNum=positionNum.concat(warehousepositionMapper.selectByPrimaryKey(s.getPositionSerial()).getPositionNum());
							positionSerial=warehouseSerial.concat(s.getPositionSerial());
						}
					if(i!=stockOutMateriels.size()-1){
						batchNum=batchNum.concat(";");
						warehouseName=warehouseName.concat(";");
						positionNum=positionNum.concat(";");
					}
			}
				d.setBatchNum(batchNum);
				d.setPositionNum(positionNum);
				d.setWarehouseName(warehouseName);
				}
				
			}
		return deliveryMateriels;
	}

	@Override
	public List<Stock> selectStockListByMaterielSerial(String materielSerial) {
		StockExample example=new StockExample();
		Criteria criteria=example.createCriteria();
    	criteria.andDelFlgEqualTo("0");
    	/*criteria.andManageTypeEqualTo("1");*/
    	criteria.andMaterielSerialEqualTo(materielSerial);
		return stockMapper.selectByExample(example);
	}

	@Override
	public String getCountInAmountForZijian(String serialNum) {
		// TODO Auto-generated method stub
		return stockInOutRecordMapper.countInAmountForZijian(serialNum);
	}

	@Override
	public String getCountOutAmountForZijian(String serialNum) {
		// TODO Auto-generated method stub
		return stockInOutRecordMapper.countOutAmountForZijian(serialNum);
	}

	@Override
	public String getCountInAmountForDaiguan(String serialNum, String comId) {
		// TODO Auto-generated method stub
		StockExample se=new StockExample();
		com.congmai.zhgj.web.model.StockExample.Criteria c=se.createCriteria();
		c.andDelFlgEqualTo("0").andMaterielOwnerEqualTo(comId).andMaterielSerialEqualTo(serialNum);
		List<Stock>s=stockMapper.selectByExample(se);
		if(CollectionUtils.isEmpty(s)){
			return "0";
		}else{
			return stockInOutRecordMapper.countInAmountForDaiguan(s.get(0).getSerialNum());
		}
	}

	@Override
	public String getCountOutAmountForDaiguan(String serialNum, String comId) {
		// TODO Auto-generated method stub
		StockExample se=new StockExample();
		com.congmai.zhgj.web.model.StockExample.Criteria c=se.createCriteria();
		c.andDelFlgEqualTo("0").andMaterielOwnerEqualTo(comId).andMaterielSerialEqualTo(serialNum);
		List<Stock>s=stockMapper.selectByExample(se);
		if(CollectionUtils.isEmpty(s)){
			return "0";
		}else{
			return stockInOutRecordMapper.countOutAmountForDaiguan(s.get(0).getSerialNum());
		}
	}

	@Override
	public List<Stock> selectStockListByComId(String manageType, String comId) {
		Stock s = new Stock();
		s.setManageType(manageType);
		s.setComId(comId);
		return stockMapper.selectStockListByComId(s);
	}

	@Override
	public List<DeliveryMateriel> getStockInBatchList(String serialNum) {
		return deliveryMaterielMapper.getStockInBatchList(serialNum);
	}

	@Override
	public List<DeliveryMateriel> getStockInBatchListByMaterielOwn(
			String serialNum, String orderSerial) {
		Map m = new HashMap<String, String>();
		m.put("serialNum", serialNum);
		m.put("orderSerial", orderSerial);
		return deliveryMaterielMapper.getStockInBatchListByMaterielOwn(m);
	}

	@Override
	public List<StockOutBatch> getStockOutBatchListByDmSerialNum(
			String serialNum) {
		StockOutBatchExample se =new StockOutBatchExample();
		 com.congmai.zhgj.web.model.StockOutBatchExample.Criteria c=se.createCriteria();
		 c.andStockOutMaterielSerialEqualTo(serialNum);
		 List<StockOutBatch>list=stockOutBatchMapper.selectByExample(se);
		return list;
	}
   
	
	}
	
	
	  
	    

   

