package com.congmai.zhgj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DemandMaterielExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DemandMaterielExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andProcurementPlanSerialIsNull() {
            addCriterion("procurementPlanSerial is null");
            return (Criteria) this;
        }

        public Criteria andProcurementPlanSerialIsNotNull() {
            addCriterion("procurementPlanSerial is not null");
            return (Criteria) this;
        }

        public Criteria andProcurementPlanSerialEqualTo(String value) {
            addCriterion("procurementPlanSerial =", value, "procurementPlanSerial");
            return (Criteria) this;
        }

        public Criteria andProcurementPlanSerialNotEqualTo(String value) {
            addCriterion("procurementPlanSerial <>", value, "procurementPlanSerial");
            return (Criteria) this;
        }

        public Criteria andProcurementPlanSerialGreaterThan(String value) {
            addCriterion("procurementPlanSerial >", value, "procurementPlanSerial");
            return (Criteria) this;
        }

        public Criteria andProcurementPlanSerialGreaterThanOrEqualTo(String value) {
            addCriterion("procurementPlanSerial >=", value, "procurementPlanSerial");
            return (Criteria) this;
        }

        public Criteria andProcurementPlanSerialLessThan(String value) {
            addCriterion("procurementPlanSerial <", value, "procurementPlanSerial");
            return (Criteria) this;
        }

        public Criteria andProcurementPlanSerialLessThanOrEqualTo(String value) {
            addCriterion("procurementPlanSerial <=", value, "procurementPlanSerial");
            return (Criteria) this;
        }

        public Criteria andProcurementPlanSerialLike(String value) {
            addCriterion("procurementPlanSerial like", value, "procurementPlanSerial");
            return (Criteria) this;
        }

        public Criteria andProcurementPlanSerialNotLike(String value) {
            addCriterion("procurementPlanSerial not like", value, "procurementPlanSerial");
            return (Criteria) this;
        }

        public Criteria andProcurementPlanSerialIn(List<String> values) {
            addCriterion("procurementPlanSerial in", values, "procurementPlanSerial");
            return (Criteria) this;
        }

        public Criteria andProcurementPlanSerialNotIn(List<String> values) {
            addCriterion("procurementPlanSerial not in", values, "procurementPlanSerial");
            return (Criteria) this;
        }

        public Criteria andProcurementPlanSerialBetween(String value1, String value2) {
            addCriterion("procurementPlanSerial between", value1, value2, "procurementPlanSerial");
            return (Criteria) this;
        }

        public Criteria andProcurementPlanSerialNotBetween(String value1, String value2) {
            addCriterion("procurementPlanSerial not between", value1, value2, "procurementPlanSerial");
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

        public Criteria andSupplyMaterielSerialIsNull() {
            addCriterion("supplyMaterielSerial is null");
            return (Criteria) this;
        }

        public Criteria andSupplyMaterielSerialIsNotNull() {
            addCriterion("supplyMaterielSerial is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyMaterielSerialEqualTo(String value) {
            addCriterion("supplyMaterielSerial =", value, "supplyMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andSupplyMaterielSerialNotEqualTo(String value) {
            addCriterion("supplyMaterielSerial <>", value, "supplyMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andSupplyMaterielSerialGreaterThan(String value) {
            addCriterion("supplyMaterielSerial >", value, "supplyMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andSupplyMaterielSerialGreaterThanOrEqualTo(String value) {
            addCriterion("supplyMaterielSerial >=", value, "supplyMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andSupplyMaterielSerialLessThan(String value) {
            addCriterion("supplyMaterielSerial <", value, "supplyMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andSupplyMaterielSerialLessThanOrEqualTo(String value) {
            addCriterion("supplyMaterielSerial <=", value, "supplyMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andSupplyMaterielSerialLike(String value) {
            addCriterion("supplyMaterielSerial like", value, "supplyMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andSupplyMaterielSerialNotLike(String value) {
            addCriterion("supplyMaterielSerial not like", value, "supplyMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andSupplyMaterielSerialIn(List<String> values) {
            addCriterion("supplyMaterielSerial in", values, "supplyMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andSupplyMaterielSerialNotIn(List<String> values) {
            addCriterion("supplyMaterielSerial not in", values, "supplyMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andSupplyMaterielSerialBetween(String value1, String value2) {
            addCriterion("supplyMaterielSerial between", value1, value2, "supplyMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andSupplyMaterielSerialNotBetween(String value1, String value2) {
            addCriterion("supplyMaterielSerial not between", value1, value2, "supplyMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andPlanCountIsNull() {
            addCriterion("planCount is null");
            return (Criteria) this;
        }

        public Criteria andPlanCountIsNotNull() {
            addCriterion("planCount is not null");
            return (Criteria) this;
        }

        public Criteria andPlanCountEqualTo(String value) {
            addCriterion("planCount =", value, "planCount");
            return (Criteria) this;
        }

        public Criteria andPlanCountNotEqualTo(String value) {
            addCriterion("planCount <>", value, "planCount");
            return (Criteria) this;
        }

        public Criteria andPlanCountGreaterThan(String value) {
            addCriterion("planCount >", value, "planCount");
            return (Criteria) this;
        }

        public Criteria andPlanCountGreaterThanOrEqualTo(String value) {
            addCriterion("planCount >=", value, "planCount");
            return (Criteria) this;
        }

        public Criteria andPlanCountLessThan(String value) {
            addCriterion("planCount <", value, "planCount");
            return (Criteria) this;
        }

        public Criteria andPlanCountLessThanOrEqualTo(String value) {
            addCriterion("planCount <=", value, "planCount");
            return (Criteria) this;
        }

        public Criteria andPlanCountLike(String value) {
            addCriterion("planCount like", value, "planCount");
            return (Criteria) this;
        }

        public Criteria andPlanCountNotLike(String value) {
            addCriterion("planCount not like", value, "planCount");
            return (Criteria) this;
        }

        public Criteria andPlanCountIn(List<String> values) {
            addCriterion("planCount in", values, "planCount");
            return (Criteria) this;
        }

        public Criteria andPlanCountNotIn(List<String> values) {
            addCriterion("planCount not in", values, "planCount");
            return (Criteria) this;
        }

        public Criteria andPlanCountBetween(String value1, String value2) {
            addCriterion("planCount between", value1, value2, "planCount");
            return (Criteria) this;
        }

        public Criteria andPlanCountNotBetween(String value1, String value2) {
            addCriterion("planCount not between", value1, value2, "planCount");
            return (Criteria) this;
        }

        public Criteria andCompleteCountIsNull() {
            addCriterion("completeCount is null");
            return (Criteria) this;
        }

        public Criteria andCompleteCountIsNotNull() {
            addCriterion("completeCount is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteCountEqualTo(String value) {
            addCriterion("completeCount =", value, "completeCount");
            return (Criteria) this;
        }

        public Criteria andCompleteCountNotEqualTo(String value) {
            addCriterion("completeCount <>", value, "completeCount");
            return (Criteria) this;
        }

        public Criteria andCompleteCountGreaterThan(String value) {
            addCriterion("completeCount >", value, "completeCount");
            return (Criteria) this;
        }

        public Criteria andCompleteCountGreaterThanOrEqualTo(String value) {
            addCriterion("completeCount >=", value, "completeCount");
            return (Criteria) this;
        }

        public Criteria andCompleteCountLessThan(String value) {
            addCriterion("completeCount <", value, "completeCount");
            return (Criteria) this;
        }

        public Criteria andCompleteCountLessThanOrEqualTo(String value) {
            addCriterion("completeCount <=", value, "completeCount");
            return (Criteria) this;
        }

        public Criteria andCompleteCountLike(String value) {
            addCriterion("completeCount like", value, "completeCount");
            return (Criteria) this;
        }

        public Criteria andCompleteCountNotLike(String value) {
            addCriterion("completeCount not like", value, "completeCount");
            return (Criteria) this;
        }

        public Criteria andCompleteCountIn(List<String> values) {
            addCriterion("completeCount in", values, "completeCount");
            return (Criteria) this;
        }

        public Criteria andCompleteCountNotIn(List<String> values) {
            addCriterion("completeCount not in", values, "completeCount");
            return (Criteria) this;
        }

        public Criteria andCompleteCountBetween(String value1, String value2) {
            addCriterion("completeCount between", value1, value2, "completeCount");
            return (Criteria) this;
        }

        public Criteria andCompleteCountNotBetween(String value1, String value2) {
            addCriterion("completeCount not between", value1, value2, "completeCount");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateIsNull() {
            addCriterion("deliveryDate is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateIsNotNull() {
            addCriterion("deliveryDate is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateEqualTo(Date value) {
            addCriterionForJDBCDate("deliveryDate =", value, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("deliveryDate <>", value, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateGreaterThan(Date value) {
            addCriterionForJDBCDate("deliveryDate >", value, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("deliveryDate >=", value, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateLessThan(Date value) {
            addCriterionForJDBCDate("deliveryDate <", value, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("deliveryDate <=", value, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateIn(List<Date> values) {
            addCriterionForJDBCDate("deliveryDate in", values, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("deliveryDate not in", values, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("deliveryDate between", value1, value2, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("deliveryDate not between", value1, value2, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressIsNull() {
            addCriterion("deliveryAddress is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressIsNotNull() {
            addCriterion("deliveryAddress is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressEqualTo(String value) {
            addCriterion("deliveryAddress =", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressNotEqualTo(String value) {
            addCriterion("deliveryAddress <>", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressGreaterThan(String value) {
            addCriterion("deliveryAddress >", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressGreaterThanOrEqualTo(String value) {
            addCriterion("deliveryAddress >=", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressLessThan(String value) {
            addCriterion("deliveryAddress <", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressLessThanOrEqualTo(String value) {
            addCriterion("deliveryAddress <=", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressLike(String value) {
            addCriterion("deliveryAddress like", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressNotLike(String value) {
            addCriterion("deliveryAddress not like", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressIn(List<String> values) {
            addCriterion("deliveryAddress in", values, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressNotIn(List<String> values) {
            addCriterion("deliveryAddress not in", values, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressBetween(String value1, String value2) {
            addCriterion("deliveryAddress between", value1, value2, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressNotBetween(String value1, String value2) {
            addCriterion("deliveryAddress not between", value1, value2, "deliveryAddress");
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

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
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