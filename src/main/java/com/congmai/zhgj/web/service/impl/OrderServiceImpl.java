package com.congmai.zhgj.web.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.druid.util.StringUtils;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.log.annotation.OperationLog;
import com.congmai.zhgj.web.dao.CompanyMapper;
import com.congmai.zhgj.web.dao.ContractMapper;
import com.congmai.zhgj.web.dao.CustomsFormMapper;
import com.congmai.zhgj.web.dao.Delivery2Mapper;
import com.congmai.zhgj.web.dao.DemandPlanMapper;
import com.congmai.zhgj.web.dao.InvoiceMapper;
import com.congmai.zhgj.web.dao.MaterielMapper;
import com.congmai.zhgj.web.dao.MemoRecordMapper;
import com.congmai.zhgj.web.dao.OrderInfoMapper;
import com.congmai.zhgj.web.dao.PayMapper;
import com.congmai.zhgj.web.dao.PaymentRecordMapper;
import com.congmai.zhgj.web.dao.PriceListMapper;
import com.congmai.zhgj.web.dao.ProcurementPlanMapper;
import com.congmai.zhgj.web.dao.StatementMapper;
import com.congmai.zhgj.web.dao.StockInOutCheckMapper;
import com.congmai.zhgj.web.dao.StockInOutRecordMapper;
import com.congmai.zhgj.web.dao.StockMapper;
import com.congmai.zhgj.web.dao.WarehouseMapper;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.CompanyExample;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.CustomsForm;
import com.congmai.zhgj.web.model.CustomsFormExample;
import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.DeliveryExample;
import com.congmai.zhgj.web.model.DemandPlan;
import com.congmai.zhgj.web.model.DemandPlanExample;
import com.congmai.zhgj.web.model.DemandPlanSelectExample;
import com.congmai.zhgj.web.model.Invoice;
import com.congmai.zhgj.web.model.InvoiceExample;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielExample;
import com.congmai.zhgj.web.model.MemoRecord;
import com.congmai.zhgj.web.model.MemoRecordExample;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.OrderInfoExample;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.PaymentRecord;
import com.congmai.zhgj.web.model.PaymentRecordExample;
import com.congmai.zhgj.web.model.PriceList;
import com.congmai.zhgj.web.model.PriceListExample;
import com.congmai.zhgj.web.model.ProcurementPlan;
import com.congmai.zhgj.web.model.ProcurementPlanExample;
import com.congmai.zhgj.web.model.Statement;
import com.congmai.zhgj.web.model.StatementExample;
import com.congmai.zhgj.web.model.Stock;
import com.congmai.zhgj.web.model.StockExample;
import com.congmai.zhgj.web.model.StockInOutCheck;
import com.congmai.zhgj.web.model.StockInOutCheckExample;
import com.congmai.zhgj.web.model.StockInOutRecord;
import com.congmai.zhgj.web.model.StockInOutRecordExample;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.OrderInfoExample.Criteria;
import com.congmai.zhgj.web.model.Warehouse;
import com.congmai.zhgj.web.model.WarehouseExample;
import com.congmai.zhgj.web.service.OrderService;

/**
 * 
 * @ClassName MarerielServiceImpl
 * @Description 订单ServiceImpl
 * @author qfzhao
 * @Date 2017年7月28日 下午3:06:52
 * @version 1.0.0
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderInfoMapper OrderInfoMapper;
  //合同的dao
  	@Resource
  	private ContractMapper contractMapper;
  	@Resource
  	private InvoiceMapper invoiceMapper;
  	@Resource
  	private WarehouseMapper warehouseMapper;
  	@Resource
  	private PaymentRecordMapper  paymentRecordMapper;
  	@Resource
  	private CompanyMapper  companyMapper;
  	@Resource
  	private StockMapper  stockMapper;
  	@Resource
  	private StockInOutCheckMapper stockInOutCheckMapper;
  	@Resource
  	private Delivery2Mapper  delivery2Mapper;
  	
  	@Resource
  	private PriceListMapper  priceListMapper;
  	
  	@Resource
  	private StatementMapper    statementMapper;
  	
  	@Resource
  	private DemandPlanMapper     demandPlanMapper;
  	
  	@Resource
  	private MaterielMapper     materielMapper;
  	
  	@Resource
  	private CustomsFormMapper     customsFormMapper;
  	
  	@Resource
  	private 	StockInOutRecordMapper     stockInOutRecordMapper;
	@Resource
  	private 	MemoRecordMapper     memoRecordMapper;
	@Resource
  	private ProcurementPlanMapper procurementPlanMapper;
	
	
  	
  	
  	
  	
  	
  
    
	@Override
	@OperationLog(operateType = "add" ,operationDesc = "订单新增" ,objectSerial= "{serialNum}")
	public int insert(OrderInfo model) {
		return OrderInfoMapper.insert(model);
	}

	@Override
	@OperationLog(operateType = "update" ,operationDesc = "订单修改" ,objectSerial= "{serialNum}")
	public int update(OrderInfo model) {
		return OrderInfoMapper.updateByPrimaryKeySelective(model);
	}
	
	@Override
	public int updateStatus(OrderInfo model) {
		return OrderInfoMapper.updateByPrimaryKeySelective(model);
	}
	
	@Override
	@OperationLog(operateType = "update" ,operationDesc = "分解采购" ,objectSerial= "{serialNum}")
	public int updateOrderRelation(OrderInfo model) {
		return OrderInfoMapper.updateByPrimaryKeySelective(model);
	}

	@Override
	@OperationLog(operateType = "update" ,operationDesc = "平台确认订单" ,objectSerial= "{serialNum}")
	public void reciveOrder(OrderInfo orderInfo) {
		OrderInfoMapper.updateByPrimaryKeySelective(orderInfo);
	}
	
	@Override
	public int delete(String serialNum) {
		return OrderInfoMapper.deleteByPrimaryKey(serialNum);
	}

	@Override
	public OrderInfo selectById(String serialNum) {
		return OrderInfoMapper.selectByPrimaryKey(serialNum);
	}

	@Override
	public OrderInfo selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderInfo> selectList() {
		return OrderInfoMapper.selectByExample(null);
	}

	@Override
	public List<OrderInfo> selectList(OrderInfoExample m) {
		return OrderInfoMapper.selectByExample(m);
	}
	
	@Override
	public List<OrderInfo> selectFramList(OrderInfo m) {
		return OrderInfoMapper.selectFramByExample(m);
	}
	
	@Override
	public List<OrderInfo> selectCommenList(OrderInfo m) {
		return OrderInfoMapper.selectCommenByExample(m);
	}

	@Override
	public void deleteOrderInfos(String ids) {
		List<String> idList = ApplicationUtils.getIdList(ids);
		for(String id : idList){
			OrderInfo m = new OrderInfo();
			m.setSerialNum(id);
			m.setDelFlg("1");
			m.setUpdateTime(new Date());
			OrderInfoMapper.updateByPrimaryKeySelective(m);
		}

	}

	@Override
	public void insertContract(ContractVO contract) {
		OrderInfo m = new OrderInfo();
		m.setSerialNum(contract.getOrderSerial());
		m.setContractSerial(contract.getId());
		m.setUpdateTime(new Date());
		OrderInfoMapper.updateByPrimaryKeySelective(m);
		
		contractMapper.insertContract(contract);
	}

	@Override
	public void updateContract(ContractVO contract) {
		contractMapper.updateContract4order(contract);
	}

	@Override
	public String getNumCode(String codeType) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("codeType", codeType);
		map.put("newNo", "");
		/*if(codeType.length()==1){//1位前缀
			OrderInfoMapper.getNumCode1(map);
		}else if(codeType.length()==2){//2位前缀
			OrderInfoMapper.getNumCode2(map);
		}*/
		OrderInfoMapper.getNumCode(map);
		return (String) map.get("newNo");
	}

	@Override
	public String checkNum(OrderInfo orderInfo) {
		return OrderInfoMapper.checkNum(orderInfo);
	}

@Override
	@OperationLog(operateType = "update" ,operationDesc = "客户提交" ,objectSerial= "{serialNum}")
	public int submitOrder(OrderInfo model) {
		return OrderInfoMapper.updateByPrimaryKeySelective(model);
		
	}

	@Override
	@OperationLog(operateType = "update" ,operationDesc = "接受订单" ,objectSerial= "{serialNum}")
	public int acceptSubmit(OrderInfo orderInfo) {
		return OrderInfoMapper.updateByPrimaryKeySelective(orderInfo);
	}
@Override
public Boolean  isExist(String codeType, String num,String serialNum) {
		// TODO Auto-generated method stub
		Boolean  flag=true;//默认存在
		if("order".equals(codeType)){//订单
			OrderInfoExample m=new OrderInfoExample();
			Criteria c=m.createCriteria();
			c.andDelFlgEqualTo("0");
			if(!StringUtils.isEmpty(num)){
				c.andOrderNumEqualTo(num);
			}else {
				flag = false;
				return flag;
			}
			if(!StringUtils.isEmpty(serialNum)){
				c.andSerialNumNotEqualTo(serialNum);
			}
			List<OrderInfo>list=OrderInfoMapper.selectByExample(m);
			if(CollectionUtils.isEmpty(list)){
				flag=false;
			}
		}else if("invoice".equals(codeType)){//发票
			InvoiceExample i=new InvoiceExample();
			com.congmai.zhgj.web.model.InvoiceExample.Criteria c=i.createCriteria();
			c.andDelFlgEqualTo("0");
			c.andInvoiceNumEqualTo(num);
			if(!StringUtils.isEmpty(serialNum)){
				c.andSerialNumNotEqualTo(serialNum);
			}
			List<Invoice>list=invoiceMapper.selectByExample(i);
			if(CollectionUtils.isEmpty(list)){
				flag=false;
			}  
			
		}else if("check".equals(codeType)){//检验
			StockInOutCheckExample se =new StockInOutCheckExample();
			com.congmai.zhgj.web.model.StockInOutCheckExample.Criteria c=se.createCriteria();
			c.andDelFlgEqualTo("0");
			c.andCheckNumEqualTo(num);
			if(!StringUtils.isEmpty(serialNum)){
				c.andSerialNumNotEqualTo(serialNum);
			}
			List<StockInOutCheck>list =stockInOutCheckMapper.selectByExample(se);
			if(CollectionUtils.isEmpty(list)){
				flag=false;
			}
		}else if("customsForm".equals(codeType)){//清报关单
			CustomsFormExample ce=new CustomsFormExample();
			com.congmai.zhgj.web.model.CustomsFormExample.Criteria c=ce.createCriteria();
			c.andDelFlgEqualTo("0");
			c.andCustomsFormNumEqualTo(num);
			if(!StringUtils.isEmpty(serialNum)){
				c.andSerialNumNotEqualTo(serialNum);
			}
			List<CustomsForm>list=customsFormMapper.selectByExample(ce);
			if(CollectionUtils.isEmpty(list)){
				flag=false;
			}
		}else if("warehouse".equals(codeType)){//仓库
			WarehouseExample we=new WarehouseExample();
			com.congmai.zhgj.web.model.WarehouseExample.Criteria c=we.createCriteria();
			c.andDelFlgEqualTo("0");
			c.andWarehouseNumEqualTo(num);
			if(!StringUtils.isEmpty(serialNum)){
				c.andSerialNumNotEqualTo(serialNum);
			}
			List<Warehouse>list=warehouseMapper.selectByExample(we);
			if(CollectionUtils.isEmpty(list)){
				flag=false;
			}
		}else if("materiel".equals(codeType)||"materielPackage".equals(codeType)){//物料
			MaterielExample me=new MaterielExample();
			com.congmai.zhgj.web.model.MaterielExample.Criteria c=me.createCriteria();
			c.andDelFlgEqualTo("0");
			c.andIsLatestVersionEqualTo("1");
			if("materiel".equals(codeType)){
				c.andMaterielNumEqualTo(num);
			}
			if("materielPackage".equals(codeType)){
				c.andPackageNumEqualTo(num);
			}
			if(!StringUtils.isEmpty(serialNum)){
				c.andSerialNumNotEqualTo(serialNum);
			}
			List<Materiel>list=materielMapper.selectByExample(me);
			if(CollectionUtils.isEmpty(list)){
				flag=false;
			}
		}else if("price".equals(codeType)){//价格
			PriceListExample pe=new PriceListExample();
			com.congmai.zhgj.web.model.PriceListExample.Criteria c=pe.createCriteria();
			c.andDelFlgEqualTo("0");
			c.andPriceNumEqualTo(num);
			if(!StringUtils.isEmpty(serialNum)){
				c.andSerialNumNotEqualTo(serialNum);
			}
			List<PriceList> list=priceListMapper.selectByExample(pe);
			if(CollectionUtils.isEmpty(list)){
				flag=false;
			}
		}else if("contract".equals(codeType)){//合同
			ContractVO c=new ContractVO();
			c.setContractNum(num);
			if(!StringUtils.isEmpty(serialNum)){
				c.setId(serialNum);
			}
			String count=contractMapper.checkNum(c);
			if("0".equals(count)){
				flag=false;
			}
		}else if("demandPlan".equals(codeType)){//需求计划
			DemandPlanSelectExample dpe=new DemandPlanSelectExample();
			com.congmai.zhgj.web.model.DemandPlanSelectExample.Criteria c=dpe.createCriteria();
			c.andDelFlgEqualTo("0");
			c.andDemandPlanNumEqualTo(num);
			if(!StringUtils.isEmpty(serialNum)){
				c.andSerialNumNotEqualTo(serialNum);
			}
			List<DemandPlan>list=demandPlanMapper.selectByExample(dpe);
			if(CollectionUtils.isEmpty(list)){
				flag=false;
			}
		}else if("stock".equals(codeType)){//库存
			StockExample se=new StockExample();
			com.congmai.zhgj.web.model.StockExample.Criteria c=se.createCriteria();
			c.andDelFlgEqualTo("0");
			c.andStockNumEqualTo(num);
			if(!StringUtils.isEmpty(serialNum)){
				c.andSerialNumNotEqualTo(serialNum);
			}
			List<Stock>list=stockMapper.selectByExample(se);
			if(CollectionUtils.isEmpty(list)){
				flag=false;
			}
			
		}else if("deliver".equals(codeType)){//发货
			DeliveryExample de=new DeliveryExample();
			com.congmai.zhgj.web.model.DeliveryExample.Criteria c=de.createCriteria();
			c.andDelFlgEqualTo("0");
			c.andDeliverNumEqualTo(num);
			if(!StringUtils.isEmpty(serialNum)){
				c.andSerialNumNotEqualTo(serialNum);
			}
			List<Delivery>list=delivery2Mapper.selectByExample(de);
			if(CollectionUtils.isEmpty(list)){
				flag=false;
			}
		}else if("statement".equals(codeType)){//对账单
			StatementExample se=new StatementExample ();
			com.congmai.zhgj.web.model.StatementExample .Criteria c=se.createCriteria();
			c.andDelFlgEqualTo("0");
			c.andStatementNumEqualTo(num);
			if(!StringUtils.isEmpty(serialNum)){
				c.andSerialNumNotEqualTo(serialNum);
			}
			List<Statement>list=statementMapper.selectByExample(se);
			if(CollectionUtils.isEmpty(list)){
				flag=false;
			}
		}else if("payOrReceive".equals(codeType)){//收付款
			PaymentRecordExample  pe=new PaymentRecordExample();
			com.congmai.zhgj.web.model.PaymentRecordExample.Criteria c=pe.createCriteria();
			c.andDelFlgEqualTo("0");
			c.andPaymentNumEqualTo(num);
			if(!StringUtils.isEmpty(serialNum)){
				c.andSerialNumNotEqualTo(serialNum);
			}
			List<PaymentRecord>list=paymentRecordMapper.selectByExample(pe);
			if(CollectionUtils.isEmpty(list)){
				flag=false;
			}
		}else if("company".equals(codeType)){//企业
			CompanyExample ce=new CompanyExample();
			com.congmai.zhgj.web.model.CompanyExample.Criteria c=ce.createCriteria();
			c.andDelFlgEqualTo("0");
			c.andComNumEqualTo(num);
			if(!StringUtils.isEmpty(serialNum)){
				c.andComIdNotEqualTo(serialNum);
			}
			List<Company>list=companyMapper.selectByExample(ce);
			if(CollectionUtils.isEmpty(list)){
				flag=false;
			}
		}else if("record".equals(codeType)){//出入库计划
			StockInOutRecordExample soe=new StockInOutRecordExample();
			com.congmai.zhgj.web.model.StockInOutRecordExample.Criteria c=soe.createCriteria();
			c.andDelFlgEqualTo("0");
			c.andInOutNumEqualTo(num);
			if(!StringUtils.isEmpty(serialNum)){
				c.andSerialNumNotEqualTo(serialNum);
			}
			List<StockInOutRecord>list=stockInOutRecordMapper.selectByExample(soe);
			if(CollectionUtils.isEmpty(list)){
				flag=false;
			}
		}else if("payOrReceiveMemo".equals(codeType)){//收付款水单
			MemoRecordExample  mre=new MemoRecordExample();
			com.congmai.zhgj.web.model.MemoRecordExample.Criteria c=mre.createCriteria();
			c.andDelFlgEqualTo("0");
			c.andMemoNumEqualTo(num);
			if(!StringUtils.isEmpty(serialNum)){
				c.andSerialNumNotEqualTo(serialNum);
			}
			List<MemoRecord>list=memoRecordMapper.selectByExample(mre);
			if(CollectionUtils.isEmpty(list)){
				flag=false;
			}
		}else if("procurementPlan".equals(codeType)){//采购计划
			ProcurementPlanExample  mre=new ProcurementPlanExample();
			com.congmai.zhgj.web.model.ProcurementPlanExample.Criteria c=mre.createCriteria();
			c.andDelFlgEqualTo("0");
			c.andProcurementPlanNumEqualTo(num);
			if(!StringUtils.isEmpty(serialNum)){
				c.andSerialNumNotEqualTo(serialNum);
			}
			List<ProcurementPlan>list=procurementPlanMapper.selectByExample(mre);
			if(CollectionUtils.isEmpty(list)){
				flag=false;
			}
		}
		return flag;
	}

@Override
@OperationLog(operateType = "update" ,operationDesc = "供应商确认订单" ,objectSerial= "{serialNum}")
public int supplyConfirmed(OrderInfo orderInfo) {
	return OrderInfoMapper.updateByPrimaryKeySelective(orderInfo);
}

@Override
@OperationLog(operateType = "update" ,operationDesc = "平台提交订单" ,objectSerial= "{serialNum}")
public int pingTaiSubmit(OrderInfo orderInfo) {
	return OrderInfoMapper.updateByPrimaryKeySelective(orderInfo);
}

@Override
public OrderInfo selectByOrderNum(String orderNum) {
	return OrderInfoMapper.selectByOrderNum(orderNum);
}

@Override
public List<OrderInfo> selectUnfinishedBuyOrderList(String comId) {
	// TODO Auto-generated method stub
	return OrderInfoMapper.selectUnfinishedBuyOrderList(comId);
}


	

}
