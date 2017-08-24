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
	
	

}
