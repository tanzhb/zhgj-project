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
	
	private StockInOutRecord record;

	public Delivery getDelivery() {
		return delivery;
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
	
	

}
