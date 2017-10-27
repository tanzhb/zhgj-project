package com.congmai.zhgj.web.service;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.DeliveryTransport;

public interface DeliveryTransportService extends GenericService<DeliveryTransport, String>{
	DeliveryTransport getDeliveryTransport(String  deliverSerial);
	
}
