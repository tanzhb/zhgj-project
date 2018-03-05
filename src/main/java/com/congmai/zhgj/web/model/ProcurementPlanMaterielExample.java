package com.congmai.zhgj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ProcurementPlanMaterielExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProcurementPlanMaterielExample() {
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

        public Criteria andSupplyComIdIsNull() {
            addCriterion("supplyComId is null");
            return (Criteria) this;
        }

        public Criteria andSupplyComIdIsNotNull() {
            addCriterion("supplyComId is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyComIdEqualTo(String value) {
            addCriterion("supplyComId =", value, "supplyComId");
            return (Criteria) this;
        }

        public Criteria andSupplyComIdNotEqualTo(String value) {
            addCriterion("supplyComId <>", value, "supplyComId");
            return (Criteria) this;
        }

        public Criteria andSupplyComIdGreaterThan(String value) {
            addCriterion("supplyComId >", value, "supplyComId");
            return (Criteria) this;
        }

        public Criteria andSupplyComIdGreaterThanOrEqualTo(String value) {
            addCriterion("supplyComId >=", value, "supplyComId");
            return (Criteria) this;
        }

        public Criteria andSupplyComIdLessThan(String value) {
            addCriterion("supplyComId <", value, "supplyComId");
            return (Criteria) this;
        }

        public Criteria andSupplyComIdLessThanOrEqualTo(String value) {
            addCriterion("supplyComId <=", value, "supplyComId");
            return (Criteria) this;
        }

        public Criteria andSupplyComIdLike(String value) {
            addCriterion("supplyComId like", value, "supplyComId");
            return (Criteria) this;
        }

        public Criteria andSupplyComIdNotLike(String value) {
            addCriterion("supplyComId not like", value, "supplyComId");
            return (Criteria) this;
        }

        public Criteria andSupplyComIdIn(List<String> values) {
            addCriterion("supplyComId in", values, "supplyComId");
            return (Criteria) this;
        }

        public Criteria andSupplyComIdNotIn(List<String> values) {
            addCriterion("supplyComId not in", values, "supplyComId");
            return (Criteria) this;
        }

        public Criteria andSupplyComIdBetween(String value1, String value2) {
            addCriterion("supplyComId between", value1, value2, "supplyComId");
            return (Criteria) this;
        }

        public Criteria andSupplyComIdNotBetween(String value1, String value2) {
            addCriterion("supplyComId not between", value1, value2, "supplyComId");
            return (Criteria) this;
        }

        public Criteria andDemandMaterielSerialIsNull() {
            addCriterion("demandMaterielSerial is null");
            return (Criteria) this;
        }

        public Criteria andDemandMaterielSerialIsNotNull() {
            addCriterion("demandMaterielSerial is not null");
            return (Criteria) this;
        }

        public Criteria andDemandMaterielSerialEqualTo(String value) {
            addCriterion("demandMaterielSerial =", value, "demandMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andDemandMaterielSerialNotEqualTo(String value) {
            addCriterion("demandMaterielSerial <>", value, "demandMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andDemandMaterielSerialGreaterThan(String value) {
            addCriterion("demandMaterielSerial >", value, "demandMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andDemandMaterielSerialGreaterThanOrEqualTo(String value) {
            addCriterion("demandMaterielSerial >=", value, "demandMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andDemandMaterielSerialLessThan(String value) {
            addCriterion("demandMaterielSerial <", value, "demandMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andDemandMaterielSerialLessThanOrEqualTo(String value) {
            addCriterion("demandMaterielSerial <=", value, "demandMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andDemandMaterielSerialLike(String value) {
            addCriterion("demandMaterielSerial like", value, "demandMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andDemandMaterielSerialNotLike(String value) {
            addCriterion("demandMaterielSerial not like", value, "demandMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andDemandMaterielSerialIn(List<String> values) {
            addCriterion("demandMaterielSerial in", values, "demandMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andDemandMaterielSerialNotIn(List<String> values) {
            addCriterion("demandMaterielSerial not in", values, "demandMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andDemandMaterielSerialBetween(String value1, String value2) {
            addCriterion("demandMaterielSerial between", value1, value2, "demandMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andDemandMaterielSerialNotBetween(String value1, String value2) {
            addCriterion("demandMaterielSerial not between", value1, value2, "demandMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andSingleDoseIsNull() {
            addCriterion("singleDose is null");
            return (Criteria) this;
        }

        public Criteria andSingleDoseIsNotNull() {
            addCriterion("singleDose is not null");
            return (Criteria) this;
        }

        public Criteria andSingleDoseEqualTo(String value) {
            addCriterion("singleDose =", value, "singleDose");
            return (Criteria) this;
        }

        public Criteria andSingleDoseNotEqualTo(String value) {
            addCriterion("singleDose <>", value, "singleDose");
            return (Criteria) this;
        }

        public Criteria andSingleDoseGreaterThan(String value) {
            addCriterion("singleDose >", value, "singleDose");
            return (Criteria) this;
        }

        public Criteria andSingleDoseGreaterThanOrEqualTo(String value) {
            addCriterion("singleDose >=", value, "singleDose");
            return (Criteria) this;
        }

        public Criteria andSingleDoseLessThan(String value) {
            addCriterion("singleDose <", value, "singleDose");
            return (Criteria) this;
        }

        public Criteria andSingleDoseLessThanOrEqualTo(String value) {
            addCriterion("singleDose <=", value, "singleDose");
            return (Criteria) this;
        }

        public Criteria andSingleDoseLike(String value) {
            addCriterion("singleDose like", value, "singleDose");
            return (Criteria) this;
        }

        public Criteria andSingleDoseNotLike(String value) {
            addCriterion("singleDose not like", value, "singleDose");
            return (Criteria) this;
        }

        public Criteria andSingleDoseIn(List<String> values) {
            addCriterion("singleDose in", values, "singleDose");
            return (Criteria) this;
        }

        public Criteria andSingleDoseNotIn(List<String> values) {
            addCriterion("singleDose not in", values, "singleDose");
            return (Criteria) this;
        }

        public Criteria andSingleDoseBetween(String value1, String value2) {
            addCriterion("singleDose between", value1, value2, "singleDose");
            return (Criteria) this;
        }

        public Criteria andSingleDoseNotBetween(String value1, String value2) {
            addCriterion("singleDose not between", value1, value2, "singleDose");
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

        public Criteria andBuyCountIsNull() {
            addCriterion("buyCount is null");
            return (Criteria) this;
        }

        public Criteria andBuyCountIsNotNull() {
            addCriterion("buyCount is not null");
            return (Criteria) this;
        }

        public Criteria andBuyCountEqualTo(String value) {
            addCriterion("buyCount =", value, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountNotEqualTo(String value) {
            addCriterion("buyCount <>", value, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountGreaterThan(String value) {
            addCriterion("buyCount >", value, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountGreaterThanOrEqualTo(String value) {
            addCriterion("buyCount >=", value, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountLessThan(String value) {
            addCriterion("buyCount <", value, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountLessThanOrEqualTo(String value) {
            addCriterion("buyCount <=", value, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountLike(String value) {
            addCriterion("buyCount like", value, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountNotLike(String value) {
            addCriterion("buyCount not like", value, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountIn(List<String> values) {
            addCriterion("buyCount in", values, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountNotIn(List<String> values) {
            addCriterion("buyCount not in", values, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountBetween(String value1, String value2) {
            addCriterion("buyCount between", value1, value2, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountNotBetween(String value1, String value2) {
            addCriterion("buyCount not between", value1, value2, "buyCount");
            return (Criteria) this;
        }

        public Criteria andOrderUnitPriceIsNull() {
            addCriterion("orderUnitPrice is null");
            return (Criteria) this;
        }

        public Criteria andOrderUnitPriceIsNotNull() {
            addCriterion("orderUnitPrice is not null");
            return (Criteria) this;
        }

        public Criteria andOrderUnitPriceEqualTo(String value) {
            addCriterion("orderUnitPrice =", value, "orderUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOrderUnitPriceNotEqualTo(String value) {
            addCriterion("orderUnitPrice <>", value, "orderUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOrderUnitPriceGreaterThan(String value) {
            addCriterion("orderUnitPrice >", value, "orderUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOrderUnitPriceGreaterThanOrEqualTo(String value) {
            addCriterion("orderUnitPrice >=", value, "orderUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOrderUnitPriceLessThan(String value) {
            addCriterion("orderUnitPrice <", value, "orderUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOrderUnitPriceLessThanOrEqualTo(String value) {
            addCriterion("orderUnitPrice <=", value, "orderUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOrderUnitPriceLike(String value) {
            addCriterion("orderUnitPrice like", value, "orderUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOrderUnitPriceNotLike(String value) {
            addCriterion("orderUnitPrice not like", value, "orderUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOrderUnitPriceIn(List<String> values) {
            addCriterion("orderUnitPrice in", values, "orderUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOrderUnitPriceNotIn(List<String> values) {
            addCriterion("orderUnitPrice not in", values, "orderUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOrderUnitPriceBetween(String value1, String value2) {
            addCriterion("orderUnitPrice between", value1, value2, "orderUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOrderUnitPriceNotBetween(String value1, String value2) {
            addCriterion("orderUnitPrice not between", value1, value2, "orderUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOrderRateUnitIsNull() {
            addCriterion("orderRateUnit is null");
            return (Criteria) this;
        }

        public Criteria andOrderRateUnitIsNotNull() {
            addCriterion("orderRateUnit is not null");
            return (Criteria) this;
        }

        public Criteria andOrderRateUnitEqualTo(String value) {
            addCriterion("orderRateUnit =", value, "orderRateUnit");
            return (Criteria) this;
        }

        public Criteria andOrderRateUnitNotEqualTo(String value) {
            addCriterion("orderRateUnit <>", value, "orderRateUnit");
            return (Criteria) this;
        }

        public Criteria andOrderRateUnitGreaterThan(String value) {
            addCriterion("orderRateUnit >", value, "orderRateUnit");
            return (Criteria) this;
        }

        public Criteria andOrderRateUnitGreaterThanOrEqualTo(String value) {
            addCriterion("orderRateUnit >=", value, "orderRateUnit");
            return (Criteria) this;
        }

        public Criteria andOrderRateUnitLessThan(String value) {
            addCriterion("orderRateUnit <", value, "orderRateUnit");
            return (Criteria) this;
        }

        public Criteria andOrderRateUnitLessThanOrEqualTo(String value) {
            addCriterion("orderRateUnit <=", value, "orderRateUnit");
            return (Criteria) this;
        }

        public Criteria andOrderRateUnitLike(String value) {
            addCriterion("orderRateUnit like", value, "orderRateUnit");
            return (Criteria) this;
        }

        public Criteria andOrderRateUnitNotLike(String value) {
            addCriterion("orderRateUnit not like", value, "orderRateUnit");
            return (Criteria) this;
        }

        public Criteria andOrderRateUnitIn(List<String> values) {
            addCriterion("orderRateUnit in", values, "orderRateUnit");
            return (Criteria) this;
        }

        public Criteria andOrderRateUnitNotIn(List<String> values) {
            addCriterion("orderRateUnit not in", values, "orderRateUnit");
            return (Criteria) this;
        }

        public Criteria andOrderRateUnitBetween(String value1, String value2) {
            addCriterion("orderRateUnit between", value1, value2, "orderRateUnit");
            return (Criteria) this;
        }

        public Criteria andOrderRateUnitNotBetween(String value1, String value2) {
            addCriterion("orderRateUnit not between", value1, value2, "orderRateUnit");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNull() {
            addCriterion("money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(String value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(String value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(String value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(String value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(String value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(String value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLike(String value) {
            addCriterion("money like", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotLike(String value) {
            addCriterion("money not like", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<String> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<String> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(String value1, String value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(String value1, String value2) {
            addCriterion("money not between", value1, value2, "money");
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

        public Criteria andLastDeliveryDateIsNull() {
            addCriterion("lastDeliveryDate is null");
            return (Criteria) this;
        }

        public Criteria andLastDeliveryDateIsNotNull() {
            addCriterion("lastDeliveryDate is not null");
            return (Criteria) this;
        }

        public Criteria andLastDeliveryDateEqualTo(Date value) {
            addCriterionForJDBCDate("lastDeliveryDate =", value, "lastDeliveryDate");
            return (Criteria) this;
        }

        public Criteria andLastDeliveryDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("lastDeliveryDate <>", value, "lastDeliveryDate");
            return (Criteria) this;
        }

        public Criteria andLastDeliveryDateGreaterThan(Date value) {
            addCriterionForJDBCDate("lastDeliveryDate >", value, "lastDeliveryDate");
            return (Criteria) this;
        }

        public Criteria andLastDeliveryDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("lastDeliveryDate >=", value, "lastDeliveryDate");
            return (Criteria) this;
        }

        public Criteria andLastDeliveryDateLessThan(Date value) {
            addCriterionForJDBCDate("lastDeliveryDate <", value, "lastDeliveryDate");
            return (Criteria) this;
        }

        public Criteria andLastDeliveryDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("lastDeliveryDate <=", value, "lastDeliveryDate");
            return (Criteria) this;
        }

        public Criteria andLastDeliveryDateIn(List<Date> values) {
            addCriterionForJDBCDate("lastDeliveryDate in", values, "lastDeliveryDate");
            return (Criteria) this;
        }

        public Criteria andLastDeliveryDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("lastDeliveryDate not in", values, "lastDeliveryDate");
            return (Criteria) this;
        }

        public Criteria andLastDeliveryDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("lastDeliveryDate between", value1, value2, "lastDeliveryDate");
            return (Criteria) this;
        }

        public Criteria andLastDeliveryDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("lastDeliveryDate not between", value1, value2, "lastDeliveryDate");
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