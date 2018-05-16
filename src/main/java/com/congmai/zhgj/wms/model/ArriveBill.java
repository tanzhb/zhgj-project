package com.congmai.zhgj.wms.model;

import com.alibaba.fastjson.annotation.JSONField;


import java.util.Date;
import java.util.List;

/**
 * @ClassName ReceivingBill
 * @Description 到货单 实体类
 * @author pengzg
 * @Date 2018年01月04日 17:29
 * @version 1.0.0
 **/
public class ArriveBill extends GenericEntity {

    private String id;
    /**
     * 到货单号
     */
    private String arriveNum;

    /**
     * 到货仓库
     */
    private String arriveWarehouse;

    /**
     * 发货方
     */
    private String shipper;

    /**
     * 供应商。现在的发货方
     */
    private String supplierManage;
    private String supplierManageName;


    /**
     * 入库类型 ：采购入库、销售退货、生产入库、货品调拨、其他入库
     */
    private String storageType;
    private String storageTypeName;

    /**
     * 关联订单号
     */
    private String accOrderNum;

    /**
     * 项目编号
     */
    private String projectNum;

    /**
     * 运输单号
     */
    private String transportNum;

    /**
     * 发货仓库
     */
    private String shipWarehouse;

    /**
     * 发货地址
     */
    private String shipAddress;

    /**
     * 发货日期
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date shipDate;

    /**
     * 运输方式： 海运，空运，陆运
     */
    private String transportType;
    private String transportTypeName;


    /**
     * 状态： 待收货，待检验， 待入库，已入库
     */
    private String state;
    private String stateName;

    /**
     * 合同编号
     */
    private String contractNum;
    /**
     * 查询条件
     */
    private String searchKey;

    /**
     * 单据下物料集合
     */
    private List<ArriveData> goodsList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArriveNum() {
        return arriveNum;
    }

    public void setArriveNum(String arriveNum) {
        this.arriveNum = arriveNum;
    }

    public String getShipper() {
        return shipper;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public String getStorageTypeName() {
        return storageTypeName;
    }

    public void setStorageTypeName(String storageTypeName) {
        this.storageTypeName = storageTypeName;
    }

    public String getAccOrderNum() {
        return accOrderNum;
    }

    public void setAccOrderNum(String accOrderNum) {
        this.accOrderNum = accOrderNum;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getTransportNum() {
        return transportNum;
    }

    public void setTransportNum(String transportNum) {
        this.transportNum = transportNum;
    }

    public String getShipWarehouse() {
        return shipWarehouse;
    }

    public void setShipWarehouse(String shipWarehouse) {
        this.shipWarehouse = shipWarehouse;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getTransportTypeName() {
        return transportTypeName;
    }

    public void setTransportTypeName(String transportTypeName) {
        this.transportTypeName = transportTypeName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public List<ArriveData> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<ArriveData> goodsList) {
        this.goodsList = goodsList;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public String getSupplierManage() {
        return supplierManage;
    }

    public void setSupplierManage(String supplierManage) {
        this.supplierManage = supplierManage;
    }

    public String getSupplierManageName() {
        return supplierManageName;
    }

    public void setSupplierManageName(String supplierManageName) {
        this.supplierManageName = supplierManageName;
    }

    public String getArriveWarehouse() {
        return arriveWarehouse;
    }

    public void setArriveWarehouse(String arriveWarehouse) {
        this.arriveWarehouse = arriveWarehouse;
    }
}
