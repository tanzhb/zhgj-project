package com.congmai.zhgj.web.model;

import java.util.List;

import com.congmai.zhgj.web.model.Delivery;
import com.congmai.zhgj.web.model.DeliveryMateriel;
import com.congmai.zhgj.web.model.DeliveryTransport;
import com.congmai.zhgj.web.model.TakeDelivery;

public class TakeDeliveryParams {
	
	private Delivery delivery;
	
	private DeliveryTransport deliveryTransport;
	
	private TakeDelivery takeDelivery;
	
	private List<DeliveryMateriel> deliveryMateriels;
	
	private List<StockOutBatch> stockOutMateriels;//出库批次信息
	
	private List<VerificationRecord> verificationRecords;//核销记录
	
	private List<InvoiceBillingRecord> invoiceBillingRecords;//发票物料收开票记录
	
	private List<DemandPlanMateriel> demandPlanMateriels;//需求计划物料
	
	
	
	private StockInOutRecord record;

	public Delivery getDelivery() {
		return delivery;
	}

	public List<VerificationRecord> getVerificationRecords() {
		return verificationRecords;
	}

	public void setVerificationRecords(List<VerificationRecord> verificationRecords) {
		this.verificationRecords = verificationRecords;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public DeliveryTransport getDeliveryTransport() {
		return deliveryTransport;
	}

	public void setDeliveryTransport(DeliveryTransport deliveryTransport) {
		this.deliveryTransport = deliveryTransport;
	}

	public TakeDelivery getTakeDelivery() {
		return takeDelivery;
	}

	public void setTakeDelivery(TakeDelivery takeDelivery) {
		this.takeDelivery = takeDelivery;
	}

	public List<DeliveryMateriel> getDeliveryMateriels() {
		return deliveryMateriels;
	}

	public void setDeliveryMateriels(List<DeliveryMateriel> deliveryMateriels) {
		this.deliveryMateriels = deliveryMateriels;
	}

	public StockInOutRecord getRecord() {
		return record;
	}

	public void setRecord(StockInOutRecord record) {
		this.record = record;
	}

	public List<StockOutBatch> getStockOutMateriels() {
		return stockOutMateriels;
	}

	public void setStockOutMateriels(List<StockOutBatch> stockOutMateriels) {
		this.stockOutMateriels = stockOutMateriels;
	}

	public List<InvoiceBillingRecord> getInvoiceBillingRecords() {
		return invoiceBillingRecords;
	}

	public void setInvoiceBillingRecords(
			List<InvoiceBillingRecord> invoiceBillingRecords) {
		this.invoiceBillingRecords = invoiceBillingRecords;
	}

	public List<DemandPlanMateriel> getDemandPlanMateriels() {
		return demandPlanMateriels;
	}

	public void setDemandPlanMateriels(List<DemandPlanMateriel> demandPlanMateriels) {
		this.demandPlanMateriels = demandPlanMateriels;
	}
	
	

}
