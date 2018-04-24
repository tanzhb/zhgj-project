package com.congmai.zhgj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockSupplyRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StockSupplyRecordExample() {
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

        public Criteria andWarehouseSerialIsNull() {
            addCriterion("warehouseSerial is null");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialIsNotNull() {
            addCriterion("warehouseSerial is not null");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialEqualTo(String value) {
            addCriterion("warehouseSerial =", value, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialNotEqualTo(String value) {
            addCriterion("warehouseSerial <>", value, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialGreaterThan(String value) {
            addCriterion("warehouseSerial >", value, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialGreaterThanOrEqualTo(String value) {
            addCriterion("warehouseSerial >=", value, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialLessThan(String value) {
            addCriterion("warehouseSerial <", value, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialLessThanOrEqualTo(String value) {
            addCriterion("warehouseSerial <=", value, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialLike(String value) {
            addCriterion("warehouseSerial like", value, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialNotLike(String value) {
            addCriterion("warehouseSerial not like", value, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialIn(List<String> values) {
            addCriterion("warehouseSerial in", values, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialNotIn(List<String> values) {
            addCriterion("warehouseSerial not in", values, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialBetween(String value1, String value2) {
            addCriterion("warehouseSerial between", value1, value2, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialNotBetween(String value1, String value2) {
            addCriterion("warehouseSerial not between", value1, value2, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andMaterielSerialIsNull() {
            addCriterion("materielSerial is null");
            return (Criteria) this;
        }

        public Criteria andMaterielSerialIsNotNull() {
            addCriterion("materielSerial is not null");
            return (Criteria) this;
        }

        public Criteria andMaterielSerialEqualTo(String value) {
            addCriterion("materielSerial =", value, "materielSerial");
            return (Criteria) this;
        }

        public Criteria andMaterielSerialNotEqualTo(String value) {
            addCriterion("materielSerial <>", value, "materielSerial");
            return (Criteria) this;
        }

        public Criteria andMaterielSerialGreaterThan(String value) {
            addCriterion("materielSerial >", value, "materielSerial");
            return (Criteria) this;
        }

        public Criteria andMaterielSerialGreaterThanOrEqualTo(String value) {
            addCriterion("materielSerial >=", value, "materielSerial");
            return (Criteria) this;
        }

        public Criteria andMaterielSerialLessThan(String value) {
            addCriterion("materielSerial <", value, "materielSerial");
            return (Criteria) this;
        }

        public Criteria andMaterielSerialLessThanOrEqualTo(String value) {
            addCriterion("materielSerial <=", value, "materielSerial");
            return (Criteria) this;
        }

        public Criteria andMaterielSerialLike(String value) {
            addCriterion("materielSerial like", value, "materielSerial");
            return (Criteria) this;
        }

        public Criteria andMaterielSerialNotLike(String value) {
            addCriterion("materielSerial not like", value, "materielSerial");
            return (Criteria) this;
        }

        public Criteria andMaterielSerialIn(List<String> values) {
            addCriterion("materielSerial in", values, "materielSerial");
            return (Criteria) this;
        }

        public Criteria andMaterielSerialNotIn(List<String> values) {
            addCriterion("materielSerial not in", values, "materielSerial");
            return (Criteria) this;
        }

        public Criteria andMaterielSerialBetween(String value1, String value2) {
            addCriterion("materielSerial between", value1, value2, "materielSerial");
            return (Criteria) this;
        }

        public Criteria andMaterielSerialNotBetween(String value1, String value2) {
            addCriterion("materielSerial not between", value1, value2, "materielSerial");
            return (Criteria) this;
        }

        public Criteria andRelationNumIsNull() {
            addCriterion("relationNum is null");
            return (Criteria) this;
        }

        public Criteria andRelationNumIsNotNull() {
            addCriterion("relationNum is not null");
            return (Criteria) this;
        }

        public Criteria andRelationNumEqualTo(String value) {
            addCriterion("relationNum =", value, "relationNum");
            return (Criteria) this;
        }

        public Criteria andRelationNumNotEqualTo(String value) {
            addCriterion("relationNum <>", value, "relationNum");
            return (Criteria) this;
        }

        public Criteria andRelationNumGreaterThan(String value) {
            addCriterion("relationNum >", value, "relationNum");
            return (Criteria) this;
        }

        public Criteria andRelationNumGreaterThanOrEqualTo(String value) {
            addCriterion("relationNum >=", value, "relationNum");
            return (Criteria) this;
        }

        public Criteria andRelationNumLessThan(String value) {
            addCriterion("relationNum <", value, "relationNum");
            return (Criteria) this;
        }

        public Criteria andRelationNumLessThanOrEqualTo(String value) {
            addCriterion("relationNum <=", value, "relationNum");
            return (Criteria) this;
        }

        public Criteria andRelationNumLike(String value) {
            addCriterion("relationNum like", value, "relationNum");
            return (Criteria) this;
        }

        public Criteria andRelationNumNotLike(String value) {
            addCriterion("relationNum not like", value, "relationNum");
            return (Criteria) this;
        }

        public Criteria andRelationNumIn(List<String> values) {
            addCriterion("relationNum in", values, "relationNum");
            return (Criteria) this;
        }

        public Criteria andRelationNumNotIn(List<String> values) {
            addCriterion("relationNum not in", values, "relationNum");
            return (Criteria) this;
        }

        public Criteria andRelationNumBetween(String value1, String value2) {
            addCriterion("relationNum between", value1, value2, "relationNum");
            return (Criteria) this;
        }

        public Criteria andRelationNumNotBetween(String value1, String value2) {
            addCriterion("relationNum not between", value1, value2, "relationNum");
            return (Criteria) this;
        }

        public Criteria andInOutNumIsNull() {
            addCriterion("inOutNum is null");
            return (Criteria) this;
        }

        public Criteria andInOutNumIsNotNull() {
            addCriterion("inOutNum is not null");
            return (Criteria) this;
        }

        public Criteria andInOutNumEqualTo(String value) {
            addCriterion("inOutNum =", value, "inOutNum");
            return (Criteria) this;
        }

        public Criteria andInOutNumNotEqualTo(String value) {
            addCriterion("inOutNum <>", value, "inOutNum");
            return (Criteria) this;
        }

        public Criteria andInOutNumGreaterThan(String value) {
            addCriterion("inOutNum >", value, "inOutNum");
            return (Criteria) this;
        }

        public Criteria andInOutNumGreaterThanOrEqualTo(String value) {
            addCriterion("inOutNum >=", value, "inOutNum");
            return (Criteria) this;
        }

        public Criteria andInOutNumLessThan(String value) {
            addCriterion("inOutNum <", value, "inOutNum");
            return (Criteria) this;
        }

        public Criteria andInOutNumLessThanOrEqualTo(String value) {
            addCriterion("inOutNum <=", value, "inOutNum");
            return (Criteria) this;
        }

        public Criteria andInOutNumLike(String value) {
            addCriterion("inOutNum like", value, "inOutNum");
            return (Criteria) this;
        }

        public Criteria andInOutNumNotLike(String value) {
            addCriterion("inOutNum not like", value, "inOutNum");
            return (Criteria) this;
        }

        public Criteria andInOutNumIn(List<String> values) {
            addCriterion("inOutNum in", values, "inOutNum");
            return (Criteria) this;
        }

        public Criteria andInOutNumNotIn(List<String> values) {
            addCriterion("inOutNum not in", values, "inOutNum");
            return (Criteria) this;
        }

        public Criteria andInOutNumBetween(String value1, String value2) {
            addCriterion("inOutNum between", value1, value2, "inOutNum");
            return (Criteria) this;
        }

        public Criteria andInOutNumNotBetween(String value1, String value2) {
            addCriterion("inOutNum not between", value1, value2, "inOutNum");
            return (Criteria) this;
        }

        public Criteria andStockDateIsNull() {
            addCriterion("stockDate is null");
            return (Criteria) this;
        }

        public Criteria andStockDateIsNotNull() {
            addCriterion("stockDate is not null");
            return (Criteria) this;
        }

        public Criteria andStockDateEqualTo(Date value) {
            addCriterion("stockDate =", value, "stockDate");
            return (Criteria) this;
        }

        public Criteria andStockDateNotEqualTo(Date value) {
            addCriterion("stockDate <>", value, "stockDate");
            return (Criteria) this;
        }

        public Criteria andStockDateGreaterThan(Date value) {
            addCriterion("stockDate >", value, "stockDate");
            return (Criteria) this;
        }

        public Criteria andStockDateGreaterThanOrEqualTo(Date value) {
            addCriterion("stockDate >=", value, "stockDate");
            return (Criteria) this;
        }

        public Criteria andStockDateLessThan(Date value) {
            addCriterion("stockDate <", value, "stockDate");
            return (Criteria) this;
        }

        public Criteria andStockDateLessThanOrEqualTo(Date value) {
            addCriterion("stockDate <=", value, "stockDate");
            return (Criteria) this;
        }

        public Criteria andStockDateIn(List<Date> values) {
            addCriterion("stockDate in", values, "stockDate");
            return (Criteria) this;
        }

        public Criteria andStockDateNotIn(List<Date> values) {
            addCriterion("stockDate not in", values, "stockDate");
            return (Criteria) this;
        }

        public Criteria andStockDateBetween(Date value1, Date value2) {
            addCriterion("stockDate between", value1, value2, "stockDate");
            return (Criteria) this;
        }

        public Criteria andStockDateNotBetween(Date value1, Date value2) {
            addCriterion("stockDate not between", value1, value2, "stockDate");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNull() {
            addCriterion("operator is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNotNull() {
            addCriterion("operator is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEqualTo(String value) {
            addCriterion("operator =", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotEqualTo(String value) {
            addCriterion("operator <>", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThan(String value) {
            addCriterion("operator >", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("operator >=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThan(String value) {
            addCriterion("operator <", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThanOrEqualTo(String value) {
            addCriterion("operator <=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLike(String value) {
            addCriterion("operator like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotLike(String value) {
            addCriterion("operator not like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorIn(List<String> values) {
            addCriterion("operator in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotIn(List<String> values) {
            addCriterion("operator not in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorBetween(String value1, String value2) {
            addCriterion("operator between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotBetween(String value1, String value2) {
            addCriterion("operator not between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andContactNumIsNull() {
            addCriterion("contactNum is null");
            return (Criteria) this;
        }

        public Criteria andContactNumIsNotNull() {
            addCriterion("contactNum is not null");
            return (Criteria) this;
        }

        public Criteria andContactNumEqualTo(String value) {
            addCriterion("contactNum =", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumNotEqualTo(String value) {
            addCriterion("contactNum <>", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumGreaterThan(String value) {
            addCriterion("contactNum >", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumGreaterThanOrEqualTo(String value) {
            addCriterion("contactNum >=", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumLessThan(String value) {
            addCriterion("contactNum <", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumLessThanOrEqualTo(String value) {
            addCriterion("contactNum <=", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumLike(String value) {
            addCriterion("contactNum like", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumNotLike(String value) {
            addCriterion("contactNum not like", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumIn(List<String> values) {
            addCriterion("contactNum in", values, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumNotIn(List<String> values) {
            addCriterion("contactNum not in", values, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumBetween(String value1, String value2) {
            addCriterion("contactNum between", value1, value2, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumNotBetween(String value1, String value2) {
            addCriterion("contactNum not between", value1, value2, "contactNum");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
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

        public Criteria andInOutTypeIsNull() {
            addCriterion("inOutType is null");
            return (Criteria) this;
        }

        public Criteria andInOutTypeIsNotNull() {
            addCriterion("inOutType is not null");
            return (Criteria) this;
        }

        public Criteria andInOutTypeEqualTo(String value) {
            addCriterion("inOutType =", value, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeNotEqualTo(String value) {
            addCriterion("inOutType <>", value, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeGreaterThan(String value) {
            addCriterion("inOutType >", value, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeGreaterThanOrEqualTo(String value) {
            addCriterion("inOutType >=", value, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeLessThan(String value) {
            addCriterion("inOutType <", value, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeLessThanOrEqualTo(String value) {
            addCriterion("inOutType <=", value, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeLike(String value) {
            addCriterion("inOutType like", value, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeNotLike(String value) {
            addCriterion("inOutType not like", value, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeIn(List<String> values) {
            addCriterion("inOutType in", values, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeNotIn(List<String> values) {
            addCriterion("inOutType not in", values, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeBetween(String value1, String value2) {
            addCriterion("inOutType between", value1, value2, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeNotBetween(String value1, String value2) {
            addCriterion("inOutType not between", value1, value2, "inOutType");
            return (Criteria) this;
        }

        public Criteria andMaterielCountIsNull() {
            addCriterion("materielCount is null");
            return (Criteria) this;
        }

        public Criteria andMaterielCountIsNotNull() {
            addCriterion("materielCount is not null");
            return (Criteria) this;
        }

        public Criteria andMaterielCountEqualTo(String value) {
            addCriterion("materielCount =", value, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountNotEqualTo(String value) {
            addCriterion("materielCount <>", value, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountGreaterThan(String value) {
            addCriterion("materielCount >", value, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountGreaterThanOrEqualTo(String value) {
            addCriterion("materielCount >=", value, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountLessThan(String value) {
            addCriterion("materielCount <", value, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountLessThanOrEqualTo(String value) {
            addCriterion("materielCount <=", value, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountLike(String value) {
            addCriterion("materielCount like", value, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountNotLike(String value) {
            addCriterion("materielCount not like", value, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountIn(List<String> values) {
            addCriterion("materielCount in", values, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountNotIn(List<String> values) {
            addCriterion("materielCount not in", values, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountBetween(String value1, String value2) {
            addCriterion("materielCount between", value1, value2, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountNotBetween(String value1, String value2) {
            addCriterion("materielCount not between", value1, value2, "materielCount");
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