package com.congmai.zhgj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StorageRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StorageRecordExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andSerialNumIsNull() {
            addCriterion("serialNum is null");
            return (Criteria) this;
        }

        public Criteria andSerialNumIsNotNull() {
            addCriterion("serialNum is not null");
            return (Criteria) this;
        }

        public Criteria andSerialNumEqualTo(String value) {
            addCriterion("serialNum =", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumNotEqualTo(String value) {
            addCriterion("serialNum <>", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumGreaterThan(String value) {
            addCriterion("serialNum >", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumGreaterThanOrEqualTo(String value) {
            addCriterion("serialNum >=", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumLessThan(String value) {
            addCriterion("serialNum <", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumLessThanOrEqualTo(String value) {
            addCriterion("serialNum <=", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumLike(String value) {
            addCriterion("serialNum like", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumNotLike(String value) {
            addCriterion("serialNum not like", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumIn(List<String> values) {
            addCriterion("serialNum in", values, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumNotIn(List<String> values) {
            addCriterion("serialNum not in", values, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumBetween(String value1, String value2) {
            addCriterion("serialNum between", value1, value2, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumNotBetween(String value1, String value2) {
            addCriterion("serialNum not between", value1, value2, "serialNum");
            return (Criteria) this;
        }

        public Criteria andStockSerialIsNull() {
            addCriterion("stockSerial is null");
            return (Criteria) this;
        }

        public Criteria andStockSerialIsNotNull() {
            addCriterion("stockSerial is not null");
            return (Criteria) this;
        }

        public Criteria andStockSerialEqualTo(String value) {
            addCriterion("stockSerial =", value, "stockSerial");
            return (Criteria) this;
        }

        public Criteria andStockSerialNotEqualTo(String value) {
            addCriterion("stockSerial <>", value, "stockSerial");
            return (Criteria) this;
        }

        public Criteria andStockSerialGreaterThan(String value) {
            addCriterion("stockSerial >", value, "stockSerial");
            return (Criteria) this;
        }

        public Criteria andStockSerialGreaterThanOrEqualTo(String value) {
            addCriterion("stockSerial >=", value, "stockSerial");
            return (Criteria) this;
        }

        public Criteria andStockSerialLessThan(String value) {
            addCriterion("stockSerial <", value, "stockSerial");
            return (Criteria) this;
        }

        public Criteria andStockSerialLessThanOrEqualTo(String value) {
            addCriterion("stockSerial <=", value, "stockSerial");
            return (Criteria) this;
        }

        public Criteria andStockSerialLike(String value) {
            addCriterion("stockSerial like", value, "stockSerial");
            return (Criteria) this;
        }

        public Criteria andStockSerialNotLike(String value) {
            addCriterion("stockSerial not like", value, "stockSerial");
            return (Criteria) this;
        }

        public Criteria andStockSerialIn(List<String> values) {
            addCriterion("stockSerial in", values, "stockSerial");
            return (Criteria) this;
        }

        public Criteria andStockSerialNotIn(List<String> values) {
            addCriterion("stockSerial not in", values, "stockSerial");
            return (Criteria) this;
        }

        public Criteria andStockSerialBetween(String value1, String value2) {
            addCriterion("stockSerial between", value1, value2, "stockSerial");
            return (Criteria) this;
        }

        public Criteria andStockSerialNotBetween(String value1, String value2) {
            addCriterion("stockSerial not between", value1, value2, "stockSerial");
            return (Criteria) this;
        }

        public Criteria andWarehousePositionSerialIsNull() {
            addCriterion("warehousePositionSerial is null");
            return (Criteria) this;
        }

        public Criteria andWarehousePositionSerialIsNotNull() {
            addCriterion("warehousePositionSerial is not null");
            return (Criteria) this;
        }

        public Criteria andWarehousePositionSerialEqualTo(String value) {
            addCriterion("warehousePositionSerial =", value, "warehousePositionSerial");
            return (Criteria) this;
        }

        public Criteria andWarehousePositionSerialNotEqualTo(String value) {
            addCriterion("warehousePositionSerial <>", value, "warehousePositionSerial");
            return (Criteria) this;
        }

        public Criteria andWarehousePositionSerialGreaterThan(String value) {
            addCriterion("warehousePositionSerial >", value, "warehousePositionSerial");
            return (Criteria) this;
        }

        public Criteria andWarehousePositionSerialGreaterThanOrEqualTo(String value) {
            addCriterion("warehousePositionSerial >=", value, "warehousePositionSerial");
            return (Criteria) this;
        }

        public Criteria andWarehousePositionSerialLessThan(String value) {
            addCriterion("warehousePositionSerial <", value, "warehousePositionSerial");
            return (Criteria) this;
        }

        public Criteria andWarehousePositionSerialLessThanOrEqualTo(String value) {
            addCriterion("warehousePositionSerial <=", value, "warehousePositionSerial");
            return (Criteria) this;
        }

        public Criteria andWarehousePositionSerialLike(String value) {
            addCriterion("warehousePositionSerial like", value, "warehousePositionSerial");
            return (Criteria) this;
        }

        public Criteria andWarehousePositionSerialNotLike(String value) {
            addCriterion("warehousePositionSerial not like", value, "warehousePositionSerial");
            return (Criteria) this;
        }

        public Criteria andWarehousePositionSerialIn(List<String> values) {
            addCriterion("warehousePositionSerial in", values, "warehousePositionSerial");
            return (Criteria) this;
        }

        public Criteria andWarehousePositionSerialNotIn(List<String> values) {
            addCriterion("warehousePositionSerial not in", values, "warehousePositionSerial");
            return (Criteria) this;
        }

        public Criteria andWarehousePositionSerialBetween(String value1, String value2) {
            addCriterion("warehousePositionSerial between", value1, value2, "warehousePositionSerial");
            return (Criteria) this;
        }

        public Criteria andWarehousePositionSerialNotBetween(String value1, String value2) {
            addCriterion("warehousePositionSerial not between", value1, value2, "warehousePositionSerial");
            return (Criteria) this;
        }

        public Criteria andDeliveryMaterielSerialIsNull() {
            addCriterion("deliveryMaterielSerial is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryMaterielSerialIsNotNull() {
            addCriterion("deliveryMaterielSerial is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryMaterielSerialEqualTo(String value) {
            addCriterion("deliveryMaterielSerial =", value, "deliveryMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andDeliveryMaterielSerialNotEqualTo(String value) {
            addCriterion("deliveryMaterielSerial <>", value, "deliveryMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andDeliveryMaterielSerialGreaterThan(String value) {
            addCriterion("deliveryMaterielSerial >", value, "deliveryMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andDeliveryMaterielSerialGreaterThanOrEqualTo(String value) {
            addCriterion("deliveryMaterielSerial >=", value, "deliveryMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andDeliveryMaterielSerialLessThan(String value) {
            addCriterion("deliveryMaterielSerial <", value, "deliveryMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andDeliveryMaterielSerialLessThanOrEqualTo(String value) {
            addCriterion("deliveryMaterielSerial <=", value, "deliveryMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andDeliveryMaterielSerialLike(String value) {
            addCriterion("deliveryMaterielSerial like", value, "deliveryMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andDeliveryMaterielSerialNotLike(String value) {
            addCriterion("deliveryMaterielSerial not like", value, "deliveryMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andDeliveryMaterielSerialIn(List<String> values) {
            addCriterion("deliveryMaterielSerial in", values, "deliveryMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andDeliveryMaterielSerialNotIn(List<String> values) {
            addCriterion("deliveryMaterielSerial not in", values, "deliveryMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andDeliveryMaterielSerialBetween(String value1, String value2) {
            addCriterion("deliveryMaterielSerial between", value1, value2, "deliveryMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andDeliveryMaterielSerialNotBetween(String value1, String value2) {
            addCriterion("deliveryMaterielSerial not between", value1, value2, "deliveryMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andInOrOutIsNull() {
            addCriterion("inOrOut is null");
            return (Criteria) this;
        }

        public Criteria andInOrOutIsNotNull() {
            addCriterion("inOrOut is not null");
            return (Criteria) this;
        }

        public Criteria andInOrOutEqualTo(String value) {
            addCriterion("inOrOut =", value, "inOrOut");
            return (Criteria) this;
        }

        public Criteria andInOrOutNotEqualTo(String value) {
            addCriterion("inOrOut <>", value, "inOrOut");
            return (Criteria) this;
        }

        public Criteria andInOrOutGreaterThan(String value) {
            addCriterion("inOrOut >", value, "inOrOut");
            return (Criteria) this;
        }

        public Criteria andInOrOutGreaterThanOrEqualTo(String value) {
            addCriterion("inOrOut >=", value, "inOrOut");
            return (Criteria) this;
        }

        public Criteria andInOrOutLessThan(String value) {
            addCriterion("inOrOut <", value, "inOrOut");
            return (Criteria) this;
        }

        public Criteria andInOrOutLessThanOrEqualTo(String value) {
            addCriterion("inOrOut <=", value, "inOrOut");
            return (Criteria) this;
        }

        public Criteria andInOrOutLike(String value) {
            addCriterion("inOrOut like", value, "inOrOut");
            return (Criteria) this;
        }

        public Criteria andInOrOutNotLike(String value) {
            addCriterion("inOrOut not like", value, "inOrOut");
            return (Criteria) this;
        }

        public Criteria andInOrOutIn(List<String> values) {
            addCriterion("inOrOut in", values, "inOrOut");
            return (Criteria) this;
        }

        public Criteria andInOrOutNotIn(List<String> values) {
            addCriterion("inOrOut not in", values, "inOrOut");
            return (Criteria) this;
        }

        public Criteria andInOrOutBetween(String value1, String value2) {
            addCriterion("inOrOut between", value1, value2, "inOrOut");
            return (Criteria) this;
        }

        public Criteria andInOrOutNotBetween(String value1, String value2) {
            addCriterion("inOrOut not between", value1, value2, "inOrOut");
            return (Criteria) this;
        }

        public Criteria andDelFlgIsNull() {
            addCriterion("delFlg is null");
            return (Criteria) this;
        }

        public Criteria andDelFlgIsNotNull() {
            addCriterion("delFlg is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlgEqualTo(String value) {
            addCriterion("delFlg =", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgNotEqualTo(String value) {
            addCriterion("delFlg <>", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgGreaterThan(String value) {
            addCriterion("delFlg >", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgGreaterThanOrEqualTo(String value) {
            addCriterion("delFlg >=", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgLessThan(String value) {
            addCriterion("delFlg <", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgLessThanOrEqualTo(String value) {
            addCriterion("delFlg <=", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgLike(String value) {
            addCriterion("delFlg like", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgNotLike(String value) {
            addCriterion("delFlg not like", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgIn(List<String> values) {
            addCriterion("delFlg in", values, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgNotIn(List<String> values) {
            addCriterion("delFlg not in", values, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgBetween(String value1, String value2) {
            addCriterion("delFlg between", value1, value2, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgNotBetween(String value1, String value2) {
            addCriterion("delFlg not between", value1, value2, "delFlg");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("createTime <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdaterIsNull() {
            addCriterion("updater is null");
            return (Criteria) this;
        }

        public Criteria andUpdaterIsNotNull() {
            addCriterion("updater is not null");
            return (Criteria) this;
        }

        public Criteria andUpdaterEqualTo(String value) {
            addCriterion("updater =", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotEqualTo(String value) {
            addCriterion("updater <>", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterGreaterThan(String value) {
            addCriterion("updater >", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterGreaterThanOrEqualTo(String value) {
            addCriterion("updater >=", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLessThan(String value) {
            addCriterion("updater <", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLessThanOrEqualTo(String value) {
            addCriterion("updater <=", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLike(String value) {
            addCriterion("updater like", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotLike(String value) {
            addCriterion("updater not like", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterIn(List<String> values) {
            addCriterion("updater in", values, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotIn(List<String> values) {
            addCriterion("updater not in", values, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterBetween(String value1, String value2) {
            addCriterion("updater between", value1, value2, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotBetween(String value1, String value2) {
            addCriterion("updater not between", value1, value2, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("updateTime is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("updateTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("updateTime =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("updateTime <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("updateTime >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updateTime >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("updateTime <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("updateTime <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("updateTime in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("updateTime not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("updateTime between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("updateTime not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}