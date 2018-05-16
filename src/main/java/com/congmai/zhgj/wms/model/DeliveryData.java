package com.congmai.zhgj.wms.model;

import java.util.List;

/**
* @ClassName: DeliveryData
* @Description: TODO 封装发货信息数据
* @author likaitao
* @date 2017/11/21 13:28
* @version 1.0.0
*/
public class DeliveryData {

    private Delivery delivery;

    private List<DeliveryProduct> deliveryProducts;

    public List<DeliveryProduct> getDeliveryProducts() {
        return deliveryProducts;
    }

    public void setDeliveryProducts(List<DeliveryProduct> deliveryProducts) {
        this.deliveryProducts = deliveryProducts;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
}
