package com.congmai.zhgj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DeliveryMaterielExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeliveryMaterielExample() {
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

        public Criteria andDeliverSerialIsNull() {
            addCriterion("deliverSerial is null");
            return (Criteria) this;
        }

        public Criteria andDeliverSerialIsNotNull() {
            addCriterion("deliverSerial is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverSerialEqualTo(String value) {
            addCriterion("deliverSerial =", value, "deliverSerial");
            return (Criteria) this;
        }

        public Criteria andDeliverSerialNotEqualTo(String value) {
            addCriterion("deliverSerial <>", value, "deliverSerial");
            return (Criteria) this;
        }

        public Criteria andDeliverSerialGreaterThan(String value) {
            addCriterion("deliverSerial >", value, "deliverSerial");
            return (Criteria) this;
        }

        public Criteria andDeliverSerialGreaterThanOrEqualTo(String value) {
            addCriterion("deliverSerial >=", value, "deliverSerial");
            return (Criteria) this;
        }

        public Criteria andDeliverSerialLessThan(String value) {
            addCriterion("deliverSerial <", value, "deliverSerial");
            return (Criteria) this;
        }

        public Criteria andDeliverSerialLessThanOrEqualTo(String value) {
            addCriterion("deliverSerial <=", value, "deliverSerial");
            return (Criteria) this;
        }

        public Criteria andDeliverSerialLike(String value) {
            addCriterion("deliverSerial like", value, "deliverSerial");
            return (Criteria) this;
        }

        public Criteria andDeliverSerialNotLike(String value) {
            addCriterion("deliverSerial not like", value, "deliverSerial");
            return (Criteria) this;
        }

        public Criteria andDeliverSerialIn(List<String> values) {
            addCriterion("deliverSerial in", values, "deliverSerial");
            return (Criteria) this;
        }

        public Criteria andDeliverSerialNotIn(List<String> values) {
            addCriterion("deliverSerial not in", values, "deliverSerial");
            return (Criteria) this;
        }

        public Criteria andDeliverSerialBetween(String value1, String value2) {
            addCriterion("deliverSerial between", value1, value2, "deliverSerial");
            return (Criteria) this;
        }

        public Criteria andDeliverSerialNotBetween(String value1, String value2) {
            addCriterion("deliverSerial not between", value1, value2, "deliverSerial");
            return (Criteria) this;
        }

        public Criteria andOrderMaterielSerialIsNull() {
            addCriterion("orderMaterielSerial is null");
            return (Criteria) this;
        }

        public Criteria andOrderMaterielSerialIsNotNull() {
            addCriterion("orderMaterielSerial is not null");
            return (Criteria) this;
        }

        public Criteria andOrderMaterielSerialEqualTo(String value) {
            addCriterion("orderMaterielSerial =", value, "orderMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andOrderMaterielSerialNotEqualTo(String value) {
            addCriterion("orderMaterielSerial <>", value, "orderMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andOrderMaterielSerialGreaterThan(String value) {
            addCriterion("orderMaterielSerial >", value, "orderMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andOrderMaterielSerialGreaterThanOrEqualTo(String value) {
            addCriterion("orderMaterielSerial >=", value, "orderMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andOrderMaterielSerialLessThan(String value) {
            addCriterion("orderMaterielSerial <", value, "orderMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andOrderMaterielSerialLessThanOrEqualTo(String value) {
            addCriterion("orderMaterielSerial <=", value, "orderMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andOrderMaterielSerialLike(String value) {
            addCriterion("orderMaterielSerial like", value, "orderMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andOrderMaterielSerialNotLike(String value) {
            addCriterion("orderMaterielSerial not like", value, "orderMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andOrderMaterielSerialIn(List<String> values) {
            addCriterion("orderMaterielSerial in", values, "orderMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andOrderMaterielSerialNotIn(List<String> values) {
            addCriterion("orderMaterielSerial not in", values, "orderMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andOrderMaterielSerialBetween(String value1, String value2) {
            addCriterion("orderMaterielSerial between", value1, value2, "orderMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andOrderMaterielSerialNotBetween(String value1, String value2) {
            addCriterion("orderMaterielSerial not between", value1, value2, "orderMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andBatchNumIsNull() {
            addCriterion("batchNum is null");
            return (Criteria) this;
        }

        public Criteria andBatchNumIsNotNull() {
            addCriterion("batchNum is not null");
            return (Criteria) this;
        }

        public Criteria andBatchNumEqualTo(String value) {
            addCriterion("batchNum =", value, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumNotEqualTo(String value) {
            addCriterion("batchNum <>", value, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumGreaterThan(String value) {
            addCriterion("batchNum >", value, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumGreaterThanOrEqualTo(String value) {
            addCriterion("batchNum >=", value, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumLessThan(String value) {
            addCriterion("batchNum <", value, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumLessThanOrEqualTo(String value) {
            addCriterion("batchNum <=", value, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumLike(String value) {
            addCriterion("batchNum like", value, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumNotLike(String value) {
            addCriterion("batchNum not like", value, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumIn(List<String> values) {
            addCriterion("batchNum in", values, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumNotIn(List<String> values) {
            addCriterion("batchNum not in", values, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumBetween(String value1, String value2) {
            addCriterion("batchNum between", value1, value2, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumNotBetween(String value1, String value2) {
            addCriterion("batchNum not between", value1, value2, "batchNum");
            return (Criteria) this;
        }

        public Criteria andManufactureDateIsNull() {
            addCriterion("manufactureDate is null");
            return (Criteria) this;
        }

        public Criteria andManufactureDateIsNotNull() {
            addCriterion("manufactureDate is not null");
            return (Criteria) this;
        }

        public Criteria andManufactureDateEqualTo(Date value) {
            addCriterionForJDBCDate("manufactureDate =", value, "manufactureDate");
            return (Criteria) this;
        }

        public Criteria andManufactureDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("manufactureDate <>", value, "manufactureDate");
            return (Criteria) this;
        }

        public Criteria andManufactureDateGreaterThan(Date value) {
            addCriterionForJDBCDate("manufactureDate >", value, "manufactureDate");
            return (Criteria) this;
        }

        public Criteria andManufactureDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("manufactureDate >=", value, "manufactureDate");
            return (Criteria) this;
        }

        public Criteria andManufactureDateLessThan(Date value) {
            addCriterionForJDBCDate("manufactureDate <", value, "manufactureDate");
            return (Criteria) this;
        }

        public Criteria andManufactureDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("manufactureDate <=", value, "manufactureDate");
            return (Criteria) this;
        }

        public Criteria andManufactureDateIn(List<Date> values) {
            addCriterionForJDBCDate("manufactureDate in", values, "manufactureDate");
            return (Criteria) this;
        }

        public Criteria andManufactureDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("manufactureDate not in", values, "manufactureDate");
            return (Criteria) this;
        }

        public Criteria andManufactureDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("manufactureDate between", value1, value2, "manufactureDate");
            return (Criteria) this;
        }

        public Criteria andManufactureDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("manufactureDate not between", value1, value2, "manufactureDate");
            return (Criteria) this;
        }

        public Criteria andDeliverCountIsNull() {
            addCriterion("deliverCount is null");
            return (Criteria) this;
        }

        public Criteria andDeliverCountIsNotNull() {
            addCriterion("deliverCount is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverCountEqualTo(String value) {
            addCriterion("deliverCount =", value, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountNotEqualTo(String value) {
            addCriterion("deliverCount <>", value, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountGreaterThan(String value) {
            addCriterion("deliverCount >", value, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountGreaterThanOrEqualTo(String value) {
            addCriterion("deliverCount >=", value, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountLessThan(String value) {
            addCriterion("deliverCount <", value, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountLessThanOrEqualTo(String value) {
            addCriterion("deliverCount <=", value, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountLike(String value) {
            addCriterion("deliverCount like", value, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountNotLike(String value) {
            addCriterion("deliverCount not like", value, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountIn(List<String> values) {
            addCriterion("deliverCount in", values, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountNotIn(List<String> values) {
            addCriterion("deliverCount not in", values, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountBetween(String value1, String value2) {
            addCriterion("deliverCount between", value1, value2, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountNotBetween(String value1, String value2) {
            addCriterion("deliverCount not between", value1, value2, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkIsNull() {
            addCriterion("deliverRemark is null");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkIsNotNull() {
            addCriterion("deliverRemark is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkEqualTo(String value) {
            addCriterion("deliverRemark =", value, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkNotEqualTo(String value) {
            addCriterion("deliverRemark <>", value, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkGreaterThan(String value) {
            addCriterion("deliverRemark >", value, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("deliverRemark >=", value, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkLessThan(String value) {
            addCriterion("deliverRemark <", value, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkLessThanOrEqualTo(String value) {
            addCriterion("deliverRemark <=", value, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkLike(String value) {
            addCriterion("deliverRemark like", value, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkNotLike(String value) {
            addCriterion("deliverRemark not like", value, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkIn(List<String> values) {
            addCriterion("deliverRemark in", values, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkNotIn(List<String> values) {
            addCriterion("deliverRemark not in", values, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkBetween(String value1, String value2) {
            addCriterion("deliverRemark between", value1, value2, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkNotBetween(String value1, String value2) {
            addCriterion("deliverRemark not between", value1, value2, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andAcceptCountIsNull() {
            addCriterion("acceptCount is null");
            return (Criteria) this;
        }

        public Criteria andAcceptCountIsNotNull() {
            addCriterion("acceptCount is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptCountEqualTo(String value) {
            addCriterion("acceptCount =", value, "acceptCount");
            return (Criteria) this;
        }

        public Criteria andAcceptCountNotEqualTo(String value) {
            addCriterion("acceptCount <>", value, "acceptCount");
            return (Criteria) this;
        }

        public Criteria andAcceptCountGreaterThan(String value) {
            addCriterion("acceptCount >", value, "acceptCount");
            return (Criteria) this;
        }

        public Criteria andAcceptCountGreaterThanOrEqualTo(String value) {
            addCriterion("acceptCount >=", value, "acceptCount");
            return (Criteria) this;
        }

        public Criteria andAcceptCountLessThan(String value) {
            addCriterion("acceptCount <", value, "acceptCount");
            return (Criteria) this;
        }

        public Criteria andAcceptCountLessThanOrEqualTo(String value) {
            addCriterion("acceptCount <=", value, "acceptCount");
            return (Criteria) this;
        }

        public Criteria andAcceptCountLike(String value) {
            addCriterion("acceptCount like", value, "acceptCount");
            return (Criteria) this;
        }

        public Criteria andAcceptCountNotLike(String value) {
            addCriterion("acceptCount not like", value, "acceptCount");
            return (Criteria) this;
        }

        public Criteria andAcceptCountIn(List<String> values) {
            addCriterion("acceptCount in", values, "acceptCount");
            return (Criteria) this;
        }

        public Criteria andAcceptCountNotIn(List<String> values) {
            addCriterion("acceptCount not in", values, "acceptCount");
            return (Criteria) this;
        }

        public Criteria andAcceptCountBetween(String value1, String value2) {
            addCriterion("acceptCount between", value1, value2, "acceptCount");
            return (Criteria) this;
        }

        public Criteria andAcceptCountNotBetween(String value1, String value2) {
            addCriterion("acceptCount not between", value1, value2, "acceptCount");
            return (Criteria) this;
        }

        public Criteria andRefuseCountIsNull() {
            addCriterion("refuseCount is null");
            return (Criteria) this;
        }

        public Criteria andRefuseCountIsNotNull() {
            addCriterion("refuseCount is not null");
            return (Criteria) this;
        }

        public Criteria andRefuseCountEqualTo(String value) {
            addCriterion("refuseCount =", value, "refuseCount");
            return (Criteria) this;
        }

        public Criteria andRefuseCountNotEqualTo(String value) {
            addCriterion("refuseCount <>", value, "refuseCount");
            return (Criteria) this;
        }

        public Criteria andRefuseCountGreaterThan(String value) {
            addCriterion("refuseCount >", value, "refuseCount");
            return (Criteria) this;
        }

        public Criteria andRefuseCountGreaterThanOrEqualTo(String value) {
            addCriterion("refuseCount >=", value, "refuseCount");
            return (Criteria) this;
        }

        public Criteria andRefuseCountLessThan(String value) {
            addCriterion("refuseCount <", value, "refuseCount");
            return (Criteria) this;
        }

        public Criteria andRefuseCountLessThanOrEqualTo(String value) {
            addCriterion("refuseCount <=", value, "refuseCount");
            return (Criteria) this;
        }

        public Criteria andRefuseCountLike(String value) {
            addCriterion("refuseCount like", value, "refuseCount");
            return (Criteria) this;
        }

        public Criteria andRefuseCountNotLike(String value) {
            addCriterion("refuseCount not like", value, "refuseCount");
            return (Criteria) this;
        }

        public Criteria andRefuseCountIn(List<String> values) {
            addCriterion("refuseCount in", values, "refuseCount");
            return (Criteria) this;
        }

        public Criteria andRefuseCountNotIn(List<String> values) {
            addCriterion("refuseCount not in", values, "refuseCount");
            return (Criteria) this;
        }

        public Criteria andRefuseCountBetween(String value1, String value2) {
            addCriterion("refuseCount between", value1, value2, "refuseCount");
            return (Criteria) this;
        }

        public Criteria andRefuseCountNotBetween(String value1, String value2) {
            addCriterion("refuseCount not between", value1, value2, "refuseCount");
            return (Criteria) this;
        }

        public Criteria andTakeRemarkIsNull() {
            addCriterion("takeRemark is null");
            return (Criteria) this;
        }

        public Criteria andTakeRemarkIsNotNull() {
            addCriterion("takeRemark is not null");
            return (Criteria) this;
        }

        public Criteria andTakeRemarkEqualTo(String value) {
            addCriterion("takeRemark =", value, "takeRemark");
            return (Criteria) this;
        }

        public Criteria andTakeRemarkNotEqualTo(String value) {
            addCriterion("takeRemark <>", value, "takeRemark");
            return (Criteria) this;
        }

        public Criteria andTakeRemarkGreaterThan(String value) {
            addCriterion("takeRemark >", value, "takeRemark");
            return (Criteria) this;
        }

        public Criteria andTakeRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("takeRemark >=", value, "takeRemark");
            return (Criteria) this;
        }

        public Criteria andTakeRemarkLessThan(String value) {
            addCriterion("takeRemark <", value, "takeRemark");
            return (Criteria) this;
        }

        public Criteria andTakeRemarkLessThanOrEqualTo(String value) {
            addCriterion("takeRemark <=", value, "takeRemark");
            return (Criteria) this;
        }

        public Criteria andTakeRemarkLike(String value) {
            addCriterion("takeRemark like", value, "takeRemark");
            return (Criteria) this;
        }

        public Criteria andTakeRemarkNotLike(String value) {
            addCriterion("takeRemark not like", value, "takeRemark");
            return (Criteria) this;
        }

        public Criteria andTakeRemarkIn(List<String> values) {
            addCriterion("takeRemark in", values, "takeRemark");
            return (Criteria) this;
        }

        public Criteria andTakeRemarkNotIn(List<String> values) {
            addCriterion("takeRemark not in", values, "takeRemark");
            return (Criteria) this;
        }

        public Criteria andTakeRemarkBetween(String value1, String value2) {
            addCriterion("takeRemark between", value1, value2, "takeRemark");
            return (Criteria) this;
        }

        public Criteria andTakeRemarkNotBetween(String value1, String value2) {
            addCriterion("takeRemark not between", value1, value2, "takeRemark");
            return (Criteria) this;
        }

        public Criteria andQualifiedCountIsNull() {
            addCriterion("qualifiedCount is null");
            return (Criteria) this;
        }

        public Criteria andQualifiedCountIsNotNull() {
            addCriterion("qualifiedCount is not null");
            return (Criteria) this;
        }

        public Criteria andQualifiedCountEqualTo(String value) {
            addCriterion("qualifiedCount =", value, "qualifiedCount");
            return (Criteria) this;
        }

        public Criteria andQualifiedCountNotEqualTo(String value) {
            addCriterion("qualifiedCount <>", value, "qualifiedCount");
            return (Criteria) this;
        }

        public Criteria andQualifiedCountGreaterThan(String value) {
            addCriterion("qualifiedCount >", value, "qualifiedCount");
            return (Criteria) this;
        }

        public Criteria andQualifiedCountGreaterThanOrEqualTo(String value) {
            addCriterion("qualifiedCount >=", value, "qualifiedCount");
            return (Criteria) this;
        }

        public Criteria andQualifiedCountLessThan(String value) {
            addCriterion("qualifiedCount <", value, "qualifiedCount");
            return (Criteria) this;
        }

        public Criteria andQualifiedCountLessThanOrEqualTo(String value) {
            addCriterion("qualifiedCount <=", value, "qualifiedCount");
            return (Criteria) this;
        }

        public Criteria andQualifiedCountLike(String value) {
            addCriterion("qualifiedCount like", value, "qualifiedCount");
            return (Criteria) this;
        }

        public Criteria andQualifiedCountNotLike(String value) {
            addCriterion("qualifiedCount not like", value, "qualifiedCount");
            return (Criteria) this;
        }

        public Criteria andQualifiedCountIn(List<String> values) {
            addCriterion("qualifiedCount in", values, "qualifiedCount");
            return (Criteria) this;
        }

        public Criteria andQualifiedCountNotIn(List<String> values) {
            addCriterion("qualifiedCount not in", values, "qualifiedCount");
            return (Criteria) this;
        }

        public Criteria andQualifiedCountBetween(String value1, String value2) {
            addCriterion("qualifiedCount between", value1, value2, "qualifiedCount");
            return (Criteria) this;
        }

        public Criteria andQualifiedCountNotBetween(String value1, String value2) {
            addCriterion("qualifiedCount not between", value1, value2, "qualifiedCount");
            return (Criteria) this;
        }

        public Criteria andUnqualifiedCountIsNull() {
            addCriterion("unqualifiedCount is null");
            return (Criteria) this;
        }

        public Criteria andUnqualifiedCountIsNotNull() {
            addCriterion("unqualifiedCount is not null");
            return (Criteria) this;
        }

        public Criteria andUnqualifiedCountEqualTo(String value) {
            addCriterion("unqualifiedCount =", value, "unqualifiedCount");
            return (Criteria) this;
        }

        public Criteria andUnqualifiedCountNotEqualTo(String value) {
            addCriterion("unqualifiedCount <>", value, "unqualifiedCount");
            return (Criteria) this;
        }

        public Criteria andUnqualifiedCountGreaterThan(String value) {
            addCriterion("unqualifiedCount >", value, "unqualifiedCount");
            return (Criteria) this;
        }

        public Criteria andUnqualifiedCountGreaterThanOrEqualTo(String value) {
            addCriterion("unqualifiedCount >=", value, "unqualifiedCount");
            return (Criteria) this;
        }

        public Criteria andUnqualifiedCountLessThan(String value) {
            addCriterion("unqualifiedCount <", value, "unqualifiedCount");
            return (Criteria) this;
        }

        public Criteria andUnqualifiedCountLessThanOrEqualTo(String value) {
            addCriterion("unqualifiedCount <=", value, "unqualifiedCount");
            return (Criteria) this;
        }

        public Criteria andUnqualifiedCountLike(String value) {
            addCriterion("unqualifiedCount like", value, "unqualifiedCount");
            return (Criteria) this;
        }

        public Criteria andUnqualifiedCountNotLike(String value) {
            addCriterion("unqualifiedCount not like", value, "unqualifiedCount");
            return (Criteria) this;
        }

        public Criteria andUnqualifiedCountIn(List<String> values) {
            addCriterion("unqualifiedCount in", values, "unqualifiedCount");
            return (Criteria) this;
        }

        public Criteria andUnqualifiedCountNotIn(List<String> values) {
            addCriterion("unqualifiedCount not in", values, "unqualifiedCount");
            return (Criteria) this;
        }

        public Criteria andUnqualifiedCountBetween(String value1, String value2) {
            addCriterion("unqualifiedCount between", value1, value2, "unqualifiedCount");
            return (Criteria) this;
        }

        public Criteria andUnqualifiedCountNotBetween(String value1, String value2) {
            addCriterion("unqualifiedCount not between", value1, value2, "unqualifiedCount");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkIsNull() {
            addCriterion("checkRemark is null");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkIsNotNull() {
            addCriterion("checkRemark is not null");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkEqualTo(String value) {
            addCriterion("checkRemark =", value, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkNotEqualTo(String value) {
            addCriterion("checkRemark <>", value, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkGreaterThan(String value) {
            addCriterion("checkRemark >", value, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("checkRemark >=", value, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkLessThan(String value) {
            addCriterion("checkRemark <", value, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkLessThanOrEqualTo(String value) {
            addCriterion("checkRemark <=", value, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkLike(String value) {
            addCriterion("checkRemark like", value, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkNotLike(String value) {
            addCriterion("checkRemark not like", value, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkIn(List<String> values) {
            addCriterion("checkRemark in", values, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkNotIn(List<String> values) {
            addCriterion("checkRemark not in", values, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkBetween(String value1, String value2) {
            addCriterion("checkRemark between", value1, value2, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkNotBetween(String value1, String value2) {
            addCriterion("checkRemark not between", value1, value2, "checkRemark");
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

        public Criteria andPositionSerialIsNull() {
            addCriterion("positionSerial is null");
            return (Criteria) this;
        }

        public Criteria andPositionSerialIsNotNull() {
            addCriterion("positionSerial is not null");
            return (Criteria) this;
        }

        public Criteria andPositionSerialEqualTo(String value) {
            addCriterion("positionSerial =", value, "positionSerial");
            return (Criteria) this;
        }

        public Criteria andPositionSerialNotEqualTo(String value) {
            addCriterion("positionSerial <>", value, "positionSerial");
            return (Criteria) this;
        }

        public Criteria andPositionSerialGreaterThan(String value) {
            addCriterion("positionSerial >", value, "positionSerial");
            return (Criteria) this;
        }

        public Criteria andPositionSerialGreaterThanOrEqualTo(String value) {
            addCriterion("positionSerial >=", value, "positionSerial");
            return (Criteria) this;
        }

        public Criteria andPositionSerialLessThan(String value) {
            addCriterion("positionSerial <", value, "positionSerial");
            return (Criteria) this;
        }

        public Criteria andPositionSerialLessThanOrEqualTo(String value) {
            addCriterion("positionSerial <=", value, "positionSerial");
            return (Criteria) this;
        }

        public Criteria andPositionSerialLike(String value) {
            addCriterion("positionSerial like", value, "positionSerial");
            return (Criteria) this;
        }

        public Criteria andPositionSerialNotLike(String value) {
            addCriterion("positionSerial not like", value, "positionSerial");
            return (Criteria) this;
        }

        public Criteria andPositionSerialIn(List<String> values) {
            addCriterion("positionSerial in", values, "positionSerial");
            return (Criteria) this;
        }

        public Criteria andPositionSerialNotIn(List<String> values) {
            addCriterion("positionSerial not in", values, "positionSerial");
            return (Criteria) this;
        }

        public Criteria andPositionSerialBetween(String value1, String value2) {
            addCriterion("positionSerial between", value1, value2, "positionSerial");
            return (Criteria) this;
        }

        public Criteria andPositionSerialNotBetween(String value1, String value2) {
            addCriterion("positionSerial not between", value1, value2, "positionSerial");
            return (Criteria) this;
        }

        public Criteria andStockCountIsNull() {
            addCriterion("stockCount is null");
            return (Criteria) this;
        }

        public Criteria andStockCountIsNotNull() {
            addCriterion("stockCount is not null");
            return (Criteria) this;
        }

        public Criteria andStockCountEqualTo(String value) {
            addCriterion("stockCount =", value, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountNotEqualTo(String value) {
            addCriterion("stockCount <>", value, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountGreaterThan(String value) {
            addCriterion("stockCount >", value, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountGreaterThanOrEqualTo(String value) {
            addCriterion("stockCount >=", value, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountLessThan(String value) {
            addCriterion("stockCount <", value, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountLessThanOrEqualTo(String value) {
            addCriterion("stockCount <=", value, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountLike(String value) {
            addCriterion("stockCount like", value, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountNotLike(String value) {
            addCriterion("stockCount not like", value, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountIn(List<String> values) {
            addCriterion("stockCount in", values, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountNotIn(List<String> values) {
            addCriterion("stockCount not in", values, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountBetween(String value1, String value2) {
            addCriterion("stockCount between", value1, value2, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountNotBetween(String value1, String value2) {
            addCriterion("stockCount not between", value1, value2, "stockCount");
            return (Criteria) this;
        }

        public Criteria andUnstockCountIsNull() {
            addCriterion("unstockCount is null");
            return (Criteria) this;
        }

        public Criteria andUnstockCountIsNotNull() {
            addCriterion("unstockCount is not null");
            return (Criteria) this;
        }

        public Criteria andUnstockCountEqualTo(String value) {
            addCriterion("unstockCount =", value, "unstockCount");
            return (Criteria) this;
        }

        public Criteria andUnstockCountNotEqualTo(String value) {
            addCriterion("unstockCount <>", value, "unstockCount");
            return (Criteria) this;
        }

        public Criteria andUnstockCountGreaterThan(String value) {
            addCriterion("unstockCount >", value, "unstockCount");
            return (Criteria) this;
        }

        public Criteria andUnstockCountGreaterThanOrEqualTo(String value) {
            addCriterion("unstockCount >=", value, "unstockCount");
            return (Criteria) this;
        }

        public Criteria andUnstockCountLessThan(String value) {
            addCriterion("unstockCount <", value, "unstockCount");
            return (Criteria) this;
        }

        public Criteria andUnstockCountLessThanOrEqualTo(String value) {
            addCriterion("unstockCount <=", value, "unstockCount");
            return (Criteria) this;
        }

        public Criteria andUnstockCountLike(String value) {
            addCriterion("unstockCount like", value, "unstockCount");
            return (Criteria) this;
        }

        public Criteria andUnstockCountNotLike(String value) {
            addCriterion("unstockCount not like", value, "unstockCount");
            return (Criteria) this;
        }

        public Criteria andUnstockCountIn(List<String> values) {
            addCriterion("unstockCount in", values, "unstockCount");
            return (Criteria) this;
        }

        public Criteria andUnstockCountNotIn(List<String> values) {
            addCriterion("unstockCount not in", values, "unstockCount");
            return (Criteria) this;
        }

        public Criteria andUnstockCountBetween(String value1, String value2) {
            addCriterion("unstockCount between", value1, value2, "unstockCount");
            return (Criteria) this;
        }

        public Criteria andUnstockCountNotBetween(String value1, String value2) {
            addCriterion("unstockCount not between", value1, value2, "unstockCount");
            return (Criteria) this;
        }

        public Criteria andStockRemarkIsNull() {
            addCriterion("stockRemark is null");
            return (Criteria) this;
        }

        public Criteria andStockRemarkIsNotNull() {
            addCriterion("stockRemark is not null");
            return (Criteria) this;
        }

        public Criteria andStockRemarkEqualTo(String value) {
            addCriterion("stockRemark =", value, "stockRemark");
            return (Criteria) this;
        }

        public Criteria andStockRemarkNotEqualTo(String value) {
            addCriterion("stockRemark <>", value, "stockRemark");
            return (Criteria) this;
        }

        public Criteria andStockRemarkGreaterThan(String value) {
            addCriterion("stockRemark >", value, "stockRemark");
            return (Criteria) this;
        }

        public Criteria andStockRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("stockRemark >=", value, "stockRemark");
            return (Criteria) this;
        }

        public Criteria andStockRemarkLessThan(String value) {
            addCriterion("stockRemark <", value, "stockRemark");
            return (Criteria) this;
        }

        public Criteria andStockRemarkLessThanOrEqualTo(String value) {
            addCriterion("stockRemark <=", value, "stockRemark");
            return (Criteria) this;
        }

        public Criteria andStockRemarkLike(String value) {
            addCriterion("stockRemark like", value, "stockRemark");
            return (Criteria) this;
        }

        public Criteria andStockRemarkNotLike(String value) {
            addCriterion("stockRemark not like", value, "stockRemark");
            return (Criteria) this;
        }

        public Criteria andStockRemarkIn(List<String> values) {
            addCriterion("stockRemark in", values, "stockRemark");
            return (Criteria) this;
        }

        public Criteria andStockRemarkNotIn(List<String> values) {
            addCriterion("stockRemark not in", values, "stockRemark");
            return (Criteria) this;
        }

        public Criteria andStockRemarkBetween(String value1, String value2) {
            addCriterion("stockRemark between", value1, value2, "stockRemark");
            return (Criteria) this;
        }

        public Criteria andStockRemarkNotBetween(String value1, String value2) {
            addCriterion("stockRemark not between", value1, value2, "stockRemark");
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