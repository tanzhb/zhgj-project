package com.congmai.zhgj.wms.model;

/**
 * @ClassName ReceivingData
 * @Description 到货物料 实体类
 * @author pengzg
 * @Date 2018年01月04日 17:39
 * @version 1.0.0
 **/
public class ArriveData extends GenericEntity{
    private String id;

    /**
     * 到货单id
     */
    private String billId;

    /**
     * 到货单号
     */
    private String arriveNum;

    /**
     * 物料id
     */
    private String productId;

    /**
     * 料箱个数
     */
    private float materialBox;

    /**
     * 到货物料数量
     */
    private float arriveCount;

    /**
     * 采购单价
     */
    private String unitPrice;

    /**
     * 箱号
     */
    private String barCode;

    /**
     * 生产批次号
     */
    private String productCode;


    /**物料状态,
     0:待到货
     1:已到货
     */
    private String productFlag;
    /**
     * 物料实体
     */
    private Product product;

    /**
     * 炉批号
     */
    private String batchFurnaceNum;

    /**
     * 机型机号
     */
    private String machineNum;

    /**
     * 项目编号
     */
    private String projectNum;

    /**
     * 合同编号
     */
    private String contractNum;

    /**
     * 根/枝数量
     */
    private String rootCount;


    /**
     * 物料更改状态：新增， 更改
     */
    private String productState;

    /**
     *排序 序号
     */
    private String orderIndex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public float getMaterialBox() {
        return materialBox;
    }

    public void setMaterialBox(float materialBox) {
        this.materialBox = materialBox;
    }

    public float getArriveCount() {
        return arriveCount;
    }

    public void setArriveCount(float arriveCount) {
        this.arriveCount = arriveCount;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getProductState() {
        return productState;
    }

    public void setProductState(String productState) {
        this.productState = productState;
    }

    public String getArriveNum() {
        return arriveNum;
    }

    public void setArriveNum(String arriveNum) {
        this.arriveNum = arriveNum;
    }

    public String getProductFlag() {
        return productFlag;
    }

    public void setProductFlag(String productFlag) {
        this.productFlag = productFlag;
    }

    public String getBatchFurnaceNum() {
        return batchFurnaceNum;
    }

    public void setBatchFurnaceNum(String batchFurnaceNum) {
        this.batchFurnaceNum = batchFurnaceNum;
    }

    public String getMachineNum() {
        return machineNum;
    }

    public void setMachineNum(String machineNum) {
        this.machineNum = machineNum;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public String getRootCount() {
        return rootCount;
    }

    public void setRootCount(String rootCount) {
        this.rootCount = rootCount;
    }

    public String getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(String orderIndex) {
        this.orderIndex = orderIndex;
    }
}
