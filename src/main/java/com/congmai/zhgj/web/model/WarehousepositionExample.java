package com.congmai.zhgj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WarehousepositionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WarehousepositionExample() {
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

        public Criteria andPositionNumIsNull() {
            addCriterion("positionNum is null");
            return (Criteria) this;
        }

        public Criteria andPositionNumIsNotNull() {
            addCriterion("positionNum is not null");
            return (Criteria) this;
        }

        public Criteria andPositionNumEqualTo(String value) {
            addCriterion("positionNum =", value, "positionNum");
            return (Criteria) this;
        }

        public Criteria andPositionNumNotEqualTo(String value) {
            addCriterion("positionNum <>", value, "positionNum");
            return (Criteria) this;
        }

        public Criteria andPositionNumGreaterThan(String value) {
            addCriterion("positionNum >", value, "positionNum");
            return (Criteria) this;
        }

        public Criteria andPositionNumGreaterThanOrEqualTo(String value) {
            addCriterion("positionNum >=", value, "positionNum");
            return (Criteria) this;
        }

        public Criteria andPositionNumLessThan(String value) {
            addCriterion("positionNum <", value, "positionNum");
            return (Criteria) this;
        }

        public Criteria andPositionNumLessThanOrEqualTo(String value) {
            addCriterion("positionNum <=", value, "positionNum");
            return (Criteria) this;
        }

        public Criteria andPositionNumLike(String value) {
            addCriterion("positionNum like", value, "positionNum");
            return (Criteria) this;
        }

        public Criteria andPositionNumNotLike(String value) {
            addCriterion("positionNum not like", value, "positionNum");
            return (Criteria) this;
        }

        public Criteria andPositionNumIn(List<String> values) {
            addCriterion("positionNum in", values, "positionNum");
            return (Criteria) this;
        }

        public Criteria andPositionNumNotIn(List<String> values) {
            addCriterion("positionNum not in", values, "positionNum");
            return (Criteria) this;
        }

        public Criteria andPositionNumBetween(String value1, String value2) {
            addCriterion("positionNum between", value1, value2, "positionNum");
            return (Criteria) this;
        }

        public Criteria andPositionNumNotBetween(String value1, String value2) {
            addCriterion("positionNum not between", value1, value2, "positionNum");
            return (Criteria) this;
        }

        public Criteria andPositionNameIsNull() {
            addCriterion("positionName is null");
            return (Criteria) this;
        }

        public Criteria andPositionNameIsNotNull() {
            addCriterion("positionName is not null");
            return (Criteria) this;
        }

        public Criteria andPositionNameEqualTo(String value) {
            addCriterion("positionName =", value, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameNotEqualTo(String value) {
            addCriterion("positionName <>", value, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameGreaterThan(String value) {
            addCriterion("positionName >", value, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameGreaterThanOrEqualTo(String value) {
            addCriterion("positionName >=", value, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameLessThan(String value) {
            addCriterion("positionName <", value, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameLessThanOrEqualTo(String value) {
            addCriterion("positionName <=", value, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameLike(String value) {
            addCriterion("positionName like", value, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameNotLike(String value) {
            addCriterion("positionName not like", value, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameIn(List<String> values) {
            addCriterion("positionName in", values, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameNotIn(List<String> values) {
            addCriterion("positionName not in", values, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameBetween(String value1, String value2) {
            addCriterion("positionName between", value1, value2, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameNotBetween(String value1, String value2) {
            addCriterion("positionName not between", value1, value2, "positionName");
            return (Criteria) this;
        }

        public Criteria andStorageAttributeIsNull() {
            addCriterion("storageAttribute is null");
            return (Criteria) this;
        }

        public Criteria andStorageAttributeIsNotNull() {
            addCriterion("storageAttribute is not null");
            return (Criteria) this;
        }

        public Criteria andStorageAttributeEqualTo(String value) {
            addCriterion("storageAttribute =", value, "storageAttribute");
            return (Criteria) this;
        }

        public Criteria andStorageAttributeNotEqualTo(String value) {
            addCriterion("storageAttribute <>", value, "storageAttribute");
            return (Criteria) this;
        }

        public Criteria andStorageAttributeGreaterThan(String value) {
            addCriterion("storageAttribute >", value, "storageAttribute");
            return (Criteria) this;
        }

        public Criteria andStorageAttributeGreaterThanOrEqualTo(String value) {
            addCriterion("storageAttribute >=", value, "storageAttribute");
            return (Criteria) this;
        }

        public Criteria andStorageAttributeLessThan(String value) {
            addCriterion("storageAttribute <", value, "storageAttribute");
            return (Criteria) this;
        }

        public Criteria andStorageAttributeLessThanOrEqualTo(String value) {
            addCriterion("storageAttribute <=", value, "storageAttribute");
            return (Criteria) this;
        }

        public Criteria andStorageAttributeLike(String value) {
            addCriterion("storageAttribute like", value, "storageAttribute");
            return (Criteria) this;
        }

        public Criteria andStorageAttributeNotLike(String value) {
            addCriterion("storageAttribute not like", value, "storageAttribute");
            return (Criteria) this;
        }

        public Criteria andStorageAttributeIn(List<String> values) {
            addCriterion("storageAttribute in", values, "storageAttribute");
            return (Criteria) this;
        }

        public Criteria andStorageAttributeNotIn(List<String> values) {
            addCriterion("storageAttribute not in", values, "storageAttribute");
            return (Criteria) this;
        }

        public Criteria andStorageAttributeBetween(String value1, String value2) {
            addCriterion("storageAttribute between", value1, value2, "storageAttribute");
            return (Criteria) this;
        }

        public Criteria andStorageAttributeNotBetween(String value1, String value2) {
            addCriterion("storageAttribute not between", value1, value2, "storageAttribute");
            return (Criteria) this;
        }

        public Criteria andMaxRowsIsNull() {
            addCriterion("maxRows is null");
            return (Criteria) this;
        }

        public Criteria andMaxRowsIsNotNull() {
            addCriterion("maxRows is not null");
            return (Criteria) this;
        }

        public Criteria andMaxRowsEqualTo(String value) {
            addCriterion("maxRows =", value, "maxRows");
            return (Criteria) this;
        }

        public Criteria andMaxRowsNotEqualTo(String value) {
            addCriterion("maxRows <>", value, "maxRows");
            return (Criteria) this;
        }

        public Criteria andMaxRowsGreaterThan(String value) {
            addCriterion("maxRows >", value, "maxRows");
            return (Criteria) this;
        }

        public Criteria andMaxRowsGreaterThanOrEqualTo(String value) {
            addCriterion("maxRows >=", value, "maxRows");
            return (Criteria) this;
        }

        public Criteria andMaxRowsLessThan(String value) {
            addCriterion("maxRows <", value, "maxRows");
            return (Criteria) this;
        }

        public Criteria andMaxRowsLessThanOrEqualTo(String value) {
            addCriterion("maxRows <=", value, "maxRows");
            return (Criteria) this;
        }

        public Criteria andMaxRowsLike(String value) {
            addCriterion("maxRows like", value, "maxRows");
            return (Criteria) this;
        }

        public Criteria andMaxRowsNotLike(String value) {
            addCriterion("maxRows not like", value, "maxRows");
            return (Criteria) this;
        }

        public Criteria andMaxRowsIn(List<String> values) {
            addCriterion("maxRows in", values, "maxRows");
            return (Criteria) this;
        }

        public Criteria andMaxRowsNotIn(List<String> values) {
            addCriterion("maxRows not in", values, "maxRows");
            return (Criteria) this;
        }

        public Criteria andMaxRowsBetween(String value1, String value2) {
            addCriterion("maxRows between", value1, value2, "maxRows");
            return (Criteria) this;
        }

        public Criteria andMaxRowsNotBetween(String value1, String value2) {
            addCriterion("maxRows not between", value1, value2, "maxRows");
            return (Criteria) this;
        }

        public Criteria andMaxColsIsNull() {
            addCriterion("maxCols is null");
            return (Criteria) this;
        }

        public Criteria andMaxColsIsNotNull() {
            addCriterion("maxCols is not null");
            return (Criteria) this;
        }

        public Criteria andMaxColsEqualTo(String value) {
            addCriterion("maxCols =", value, "maxCols");
            return (Criteria) this;
        }

        public Criteria andMaxColsNotEqualTo(String value) {
            addCriterion("maxCols <>", value, "maxCols");
            return (Criteria) this;
        }

        public Criteria andMaxColsGreaterThan(String value) {
            addCriterion("maxCols >", value, "maxCols");
            return (Criteria) this;
        }

        public Criteria andMaxColsGreaterThanOrEqualTo(String value) {
            addCriterion("maxCols >=", value, "maxCols");
            return (Criteria) this;
        }

        public Criteria andMaxColsLessThan(String value) {
            addCriterion("maxCols <", value, "maxCols");
            return (Criteria) this;
        }

        public Criteria andMaxColsLessThanOrEqualTo(String value) {
            addCriterion("maxCols <=", value, "maxCols");
            return (Criteria) this;
        }

        public Criteria andMaxColsLike(String value) {
            addCriterion("maxCols like", value, "maxCols");
            return (Criteria) this;
        }

        public Criteria andMaxColsNotLike(String value) {
            addCriterion("maxCols not like", value, "maxCols");
            return (Criteria) this;
        }

        public Criteria andMaxColsIn(List<String> values) {
            addCriterion("maxCols in", values, "maxCols");
            return (Criteria) this;
        }

        public Criteria andMaxColsNotIn(List<String> values) {
            addCriterion("maxCols not in", values, "maxCols");
            return (Criteria) this;
        }

        public Criteria andMaxColsBetween(String value1, String value2) {
            addCriterion("maxCols between", value1, value2, "maxCols");
            return (Criteria) this;
        }

        public Criteria andMaxColsNotBetween(String value1, String value2) {
            addCriterion("maxCols not between", value1, value2, "maxCols");
            return (Criteria) this;
        }

        public Criteria andMaxLayersIsNull() {
            addCriterion("maxLayers is null");
            return (Criteria) this;
        }

        public Criteria andMaxLayersIsNotNull() {
            addCriterion("maxLayers is not null");
            return (Criteria) this;
        }

        public Criteria andMaxLayersEqualTo(String value) {
            addCriterion("maxLayers =", value, "maxLayers");
            return (Criteria) this;
        }

        public Criteria andMaxLayersNotEqualTo(String value) {
            addCriterion("maxLayers <>", value, "maxLayers");
            return (Criteria) this;
        }

        public Criteria andMaxLayersGreaterThan(String value) {
            addCriterion("maxLayers >", value, "maxLayers");
            return (Criteria) this;
        }

        public Criteria andMaxLayersGreaterThanOrEqualTo(String value) {
            addCriterion("maxLayers >=", value, "maxLayers");
            return (Criteria) this;
        }

        public Criteria andMaxLayersLessThan(String value) {
            addCriterion("maxLayers <", value, "maxLayers");
            return (Criteria) this;
        }

        public Criteria andMaxLayersLessThanOrEqualTo(String value) {
            addCriterion("maxLayers <=", value, "maxLayers");
            return (Criteria) this;
        }

        public Criteria andMaxLayersLike(String value) {
            addCriterion("maxLayers like", value, "maxLayers");
            return (Criteria) this;
        }

        public Criteria andMaxLayersNotLike(String value) {
            addCriterion("maxLayers not like", value, "maxLayers");
            return (Criteria) this;
        }

        public Criteria andMaxLayersIn(List<String> values) {
            addCriterion("maxLayers in", values, "maxLayers");
            return (Criteria) this;
        }

        public Criteria andMaxLayersNotIn(List<String> values) {
            addCriterion("maxLayers not in", values, "maxLayers");
            return (Criteria) this;
        }

        public Criteria andMaxLayersBetween(String value1, String value2) {
            addCriterion("maxLayers between", value1, value2, "maxLayers");
            return (Criteria) this;
        }

        public Criteria andMaxLayersNotBetween(String value1, String value2) {
            addCriterion("maxLayers not between", value1, value2, "maxLayers");
            return (Criteria) this;
        }

        public Criteria andStorageTypeIsNull() {
            addCriterion("storageType is null");
            return (Criteria) this;
        }

        public Criteria andStorageTypeIsNotNull() {
            addCriterion("storageType is not null");
            return (Criteria) this;
        }

        public Criteria andStorageTypeEqualTo(String value) {
            addCriterion("storageType =", value, "storageType");
            return (Criteria) this;
        }

        public Criteria andStorageTypeNotEqualTo(String value) {
            addCriterion("storageType <>", value, "storageType");
            return (Criteria) this;
        }

        public Criteria andStorageTypeGreaterThan(String value) {
            addCriterion("storageType >", value, "storageType");
            return (Criteria) this;
        }

        public Criteria andStorageTypeGreaterThanOrEqualTo(String value) {
            addCriterion("storageType >=", value, "storageType");
            return (Criteria) this;
        }

        public Criteria andStorageTypeLessThan(String value) {
            addCriterion("storageType <", value, "storageType");
            return (Criteria) this;
        }

        public Criteria andStorageTypeLessThanOrEqualTo(String value) {
            addCriterion("storageType <=", value, "storageType");
            return (Criteria) this;
        }

        public Criteria andStorageTypeLike(String value) {
            addCriterion("storageType like", value, "storageType");
            return (Criteria) this;
        }

        public Criteria andStorageTypeNotLike(String value) {
            addCriterion("storageType not like", value, "storageType");
            return (Criteria) this;
        }

        public Criteria andStorageTypeIn(List<String> values) {
            addCriterion("storageType in", values, "storageType");
            return (Criteria) this;
        }

        public Criteria andStorageTypeNotIn(List<String> values) {
            addCriterion("storageType not in", values, "storageType");
            return (Criteria) this;
        }

        public Criteria andStorageTypeBetween(String value1, String value2) {
            addCriterion("storageType between", value1, value2, "storageType");
            return (Criteria) this;
        }

        public Criteria andStorageTypeNotBetween(String value1, String value2) {
            addCriterion("storageType not between", value1, value2, "storageType");
            return (Criteria) this;
        }

        public Criteria andStorageModeIsNull() {
            addCriterion("storageMode is null");
            return (Criteria) this;
        }

        public Criteria andStorageModeIsNotNull() {
            addCriterion("storageMode is not null");
            return (Criteria) this;
        }

        public Criteria andStorageModeEqualTo(String value) {
            addCriterion("storageMode =", value, "storageMode");
            return (Criteria) this;
        }

        public Criteria andStorageModeNotEqualTo(String value) {
            addCriterion("storageMode <>", value, "storageMode");
            return (Criteria) this;
        }

        public Criteria andStorageModeGreaterThan(String value) {
            addCriterion("storageMode >", value, "storageMode");
            return (Criteria) this;
        }

        public Criteria andStorageModeGreaterThanOrEqualTo(String value) {
            addCriterion("storageMode >=", value, "storageMode");
            return (Criteria) this;
        }

        public Criteria andStorageModeLessThan(String value) {
            addCriterion("storageMode <", value, "storageMode");
            return (Criteria) this;
        }

        public Criteria andStorageModeLessThanOrEqualTo(String value) {
            addCriterion("storageMode <=", value, "storageMode");
            return (Criteria) this;
        }

        public Criteria andStorageModeLike(String value) {
            addCriterion("storageMode like", value, "storageMode");
            return (Criteria) this;
        }

        public Criteria andStorageModeNotLike(String value) {
            addCriterion("storageMode not like", value, "storageMode");
            return (Criteria) this;
        }

        public Criteria andStorageModeIn(List<String> values) {
            addCriterion("storageMode in", values, "storageMode");
            return (Criteria) this;
        }

        public Criteria andStorageModeNotIn(List<String> values) {
            addCriterion("storageMode not in", values, "storageMode");
            return (Criteria) this;
        }

        public Criteria andStorageModeBetween(String value1, String value2) {
            addCriterion("storageMode between", value1, value2, "storageMode");
            return (Criteria) this;
        }

        public Criteria andStorageModeNotBetween(String value1, String value2) {
            addCriterion("storageMode not between", value1, value2, "storageMode");
            return (Criteria) this;
        }

        public Criteria andDefaultLWHIsNull() {
            addCriterion("defaultLWH is null");
            return (Criteria) this;
        }

        public Criteria andDefaultLWHIsNotNull() {
            addCriterion("defaultLWH is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultLWHEqualTo(String value) {
            addCriterion("defaultLWH =", value, "defaultLWH");
            return (Criteria) this;
        }

        public Criteria andDefaultLWHNotEqualTo(String value) {
            addCriterion("defaultLWH <>", value, "defaultLWH");
            return (Criteria) this;
        }

        public Criteria andDefaultLWHGreaterThan(String value) {
            addCriterion("defaultLWH >", value, "defaultLWH");
            return (Criteria) this;
        }

        public Criteria andDefaultLWHGreaterThanOrEqualTo(String value) {
            addCriterion("defaultLWH >=", value, "defaultLWH");
            return (Criteria) this;
        }

        public Criteria andDefaultLWHLessThan(String value) {
            addCriterion("defaultLWH <", value, "defaultLWH");
            return (Criteria) this;
        }

        public Criteria andDefaultLWHLessThanOrEqualTo(String value) {
            addCriterion("defaultLWH <=", value, "defaultLWH");
            return (Criteria) this;
        }

        public Criteria andDefaultLWHLike(String value) {
            addCriterion("defaultLWH like", value, "defaultLWH");
            return (Criteria) this;
        }

        public Criteria andDefaultLWHNotLike(String value) {
            addCriterion("defaultLWH not like", value, "defaultLWH");
            return (Criteria) this;
        }

        public Criteria andDefaultLWHIn(List<String> values) {
            addCriterion("defaultLWH in", values, "defaultLWH");
            return (Criteria) this;
        }

        public Criteria andDefaultLWHNotIn(List<String> values) {
            addCriterion("defaultLWH not in", values, "defaultLWH");
            return (Criteria) this;
        }

        public Criteria andDefaultLWHBetween(String value1, String value2) {
            addCriterion("defaultLWH between", value1, value2, "defaultLWH");
            return (Criteria) this;
        }

        public Criteria andDefaultLWHNotBetween(String value1, String value2) {
            addCriterion("defaultLWH not between", value1, value2, "defaultLWH");
            return (Criteria) this;
        }

        public Criteria andDefaultVolumeIsNull() {
            addCriterion("defaultVolume is null");
            return (Criteria) this;
        }

        public Criteria andDefaultVolumeIsNotNull() {
            addCriterion("defaultVolume is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultVolumeEqualTo(String value) {
            addCriterion("defaultVolume =", value, "defaultVolume");
            return (Criteria) this;
        }

        public Criteria andDefaultVolumeNotEqualTo(String value) {
            addCriterion("defaultVolume <>", value, "defaultVolume");
            return (Criteria) this;
        }

        public Criteria andDefaultVolumeGreaterThan(String value) {
            addCriterion("defaultVolume >", value, "defaultVolume");
            return (Criteria) this;
        }

        public Criteria andDefaultVolumeGreaterThanOrEqualTo(String value) {
            addCriterion("defaultVolume >=", value, "defaultVolume");
            return (Criteria) this;
        }

        public Criteria andDefaultVolumeLessThan(String value) {
            addCriterion("defaultVolume <", value, "defaultVolume");
            return (Criteria) this;
        }

        public Criteria andDefaultVolumeLessThanOrEqualTo(String value) {
            addCriterion("defaultVolume <=", value, "defaultVolume");
            return (Criteria) this;
        }

        public Criteria andDefaultVolumeLike(String value) {
            addCriterion("defaultVolume like", value, "defaultVolume");
            return (Criteria) this;
        }

        public Criteria andDefaultVolumeNotLike(String value) {
            addCriterion("defaultVolume not like", value, "defaultVolume");
            return (Criteria) this;
        }

        public Criteria andDefaultVolumeIn(List<String> values) {
            addCriterion("defaultVolume in", values, "defaultVolume");
            return (Criteria) this;
        }

        public Criteria andDefaultVolumeNotIn(List<String> values) {
            addCriterion("defaultVolume not in", values, "defaultVolume");
            return (Criteria) this;
        }

        public Criteria andDefaultVolumeBetween(String value1, String value2) {
            addCriterion("defaultVolume between", value1, value2, "defaultVolume");
            return (Criteria) this;
        }

        public Criteria andDefaultVolumeNotBetween(String value1, String value2) {
            addCriterion("defaultVolume not between", value1, value2, "defaultVolume");
            return (Criteria) this;
        }

        public Criteria andDefaultBearingIsNull() {
            addCriterion("defaultBearing is null");
            return (Criteria) this;
        }

        public Criteria andDefaultBearingIsNotNull() {
            addCriterion("defaultBearing is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultBearingEqualTo(String value) {
            addCriterion("defaultBearing =", value, "defaultBearing");
            return (Criteria) this;
        }

        public Criteria andDefaultBearingNotEqualTo(String value) {
            addCriterion("defaultBearing <>", value, "defaultBearing");
            return (Criteria) this;
        }

        public Criteria andDefaultBearingGreaterThan(String value) {
            addCriterion("defaultBearing >", value, "defaultBearing");
            return (Criteria) this;
        }

        public Criteria andDefaultBearingGreaterThanOrEqualTo(String value) {
            addCriterion("defaultBearing >=", value, "defaultBearing");
            return (Criteria) this;
        }

        public Criteria andDefaultBearingLessThan(String value) {
            addCriterion("defaultBearing <", value, "defaultBearing");
            return (Criteria) this;
        }

        public Criteria andDefaultBearingLessThanOrEqualTo(String value) {
            addCriterion("defaultBearing <=", value, "defaultBearing");
            return (Criteria) this;
        }

        public Criteria andDefaultBearingLike(String value) {
            addCriterion("defaultBearing like", value, "defaultBearing");
            return (Criteria) this;
        }

        public Criteria andDefaultBearingNotLike(String value) {
            addCriterion("defaultBearing not like", value, "defaultBearing");
            return (Criteria) this;
        }

        public Criteria andDefaultBearingIn(List<String> values) {
            addCriterion("defaultBearing in", values, "defaultBearing");
            return (Criteria) this;
        }

        public Criteria andDefaultBearingNotIn(List<String> values) {
            addCriterion("defaultBearing not in", values, "defaultBearing");
            return (Criteria) this;
        }

        public Criteria andDefaultBearingBetween(String value1, String value2) {
            addCriterion("defaultBearing between", value1, value2, "defaultBearing");
            return (Criteria) this;
        }

        public Criteria andDefaultBearingNotBetween(String value1, String value2) {
            addCriterion("defaultBearing not between", value1, value2, "defaultBearing");
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