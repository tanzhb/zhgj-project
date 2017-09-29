package com.congmai.zhgj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PaymentRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PaymentRecordExample() {
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

        public Criteria andPaymentNumIsNull() {
            addCriterion("paymentNum is null");
            return (Criteria) this;
        }

        public Criteria andPaymentNumIsNotNull() {
            addCriterion("paymentNum is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentNumEqualTo(String value) {
            addCriterion("paymentNum =", value, "paymentNum");
            return (Criteria) this;
        }

        public Criteria andPaymentNumNotEqualTo(String value) {
            addCriterion("paymentNum <>", value, "paymentNum");
            return (Criteria) this;
        }

        public Criteria andPaymentNumGreaterThan(String value) {
            addCriterion("paymentNum >", value, "paymentNum");
            return (Criteria) this;
        }

        public Criteria andPaymentNumGreaterThanOrEqualTo(String value) {
            addCriterion("paymentNum >=", value, "paymentNum");
            return (Criteria) this;
        }

        public Criteria andPaymentNumLessThan(String value) {
            addCriterion("paymentNum <", value, "paymentNum");
            return (Criteria) this;
        }

        public Criteria andPaymentNumLessThanOrEqualTo(String value) {
            addCriterion("paymentNum <=", value, "paymentNum");
            return (Criteria) this;
        }

        public Criteria andPaymentNumLike(String value) {
            addCriterion("paymentNum like", value, "paymentNum");
            return (Criteria) this;
        }

        public Criteria andPaymentNumNotLike(String value) {
            addCriterion("paymentNum not like", value, "paymentNum");
            return (Criteria) this;
        }

        public Criteria andPaymentNumIn(List<String> values) {
            addCriterion("paymentNum in", values, "paymentNum");
            return (Criteria) this;
        }

        public Criteria andPaymentNumNotIn(List<String> values) {
            addCriterion("paymentNum not in", values, "paymentNum");
            return (Criteria) this;
        }

        public Criteria andPaymentNumBetween(String value1, String value2) {
            addCriterion("paymentNum between", value1, value2, "paymentNum");
            return (Criteria) this;
        }

        public Criteria andPaymentNumNotBetween(String value1, String value2) {
            addCriterion("paymentNum not between", value1, value2, "paymentNum");
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

        public Criteria andBuyComIdIsNull() {
            addCriterion("buyComId is null");
            return (Criteria) this;
        }

        public Criteria andBuyComIdIsNotNull() {
            addCriterion("buyComId is not null");
            return (Criteria) this;
        }

        public Criteria andBuyComIdEqualTo(String value) {
            addCriterion("buyComId =", value, "buyComId");
            return (Criteria) this;
        }

        public Criteria andBuyComIdNotEqualTo(String value) {
            addCriterion("buyComId <>", value, "buyComId");
            return (Criteria) this;
        }

        public Criteria andBuyComIdGreaterThan(String value) {
            addCriterion("buyComId >", value, "buyComId");
            return (Criteria) this;
        }

        public Criteria andBuyComIdGreaterThanOrEqualTo(String value) {
            addCriterion("buyComId >=", value, "buyComId");
            return (Criteria) this;
        }

        public Criteria andBuyComIdLessThan(String value) {
            addCriterion("buyComId <", value, "buyComId");
            return (Criteria) this;
        }

        public Criteria andBuyComIdLessThanOrEqualTo(String value) {
            addCriterion("buyComId <=", value, "buyComId");
            return (Criteria) this;
        }

        public Criteria andBuyComIdLike(String value) {
            addCriterion("buyComId like", value, "buyComId");
            return (Criteria) this;
        }

        public Criteria andBuyComIdNotLike(String value) {
            addCriterion("buyComId not like", value, "buyComId");
            return (Criteria) this;
        }

        public Criteria andBuyComIdIn(List<String> values) {
            addCriterion("buyComId in", values, "buyComId");
            return (Criteria) this;
        }

        public Criteria andBuyComIdNotIn(List<String> values) {
            addCriterion("buyComId not in", values, "buyComId");
            return (Criteria) this;
        }

        public Criteria andBuyComIdBetween(String value1, String value2) {
            addCriterion("buyComId between", value1, value2, "buyComId");
            return (Criteria) this;
        }

        public Criteria andBuyComIdNotBetween(String value1, String value2) {
            addCriterion("buyComId not between", value1, value2, "buyComId");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeIsNull() {
            addCriterion("paymentType is null");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeIsNotNull() {
            addCriterion("paymentType is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeEqualTo(String value) {
            addCriterion("paymentType =", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNotEqualTo(String value) {
            addCriterion("paymentType <>", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeGreaterThan(String value) {
            addCriterion("paymentType >", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeGreaterThanOrEqualTo(String value) {
            addCriterion("paymentType >=", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeLessThan(String value) {
            addCriterion("paymentType <", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeLessThanOrEqualTo(String value) {
            addCriterion("paymentType <=", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeLike(String value) {
            addCriterion("paymentType like", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNotLike(String value) {
            addCriterion("paymentType not like", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeIn(List<String> values) {
            addCriterion("paymentType in", values, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNotIn(List<String> values) {
            addCriterion("paymentType not in", values, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeBetween(String value1, String value2) {
            addCriterion("paymentType between", value1, value2, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNotBetween(String value1, String value2) {
            addCriterion("paymentType not between", value1, value2, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentPlanSerialIsNull() {
            addCriterion("paymentPlanSerial is null");
            return (Criteria) this;
        }

        public Criteria andPaymentPlanSerialIsNotNull() {
            addCriterion("paymentPlanSerial is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentPlanSerialEqualTo(String value) {
            addCriterion("paymentPlanSerial =", value, "paymentPlanSerial");
            return (Criteria) this;
        }

        public Criteria andPaymentPlanSerialNotEqualTo(String value) {
            addCriterion("paymentPlanSerial <>", value, "paymentPlanSerial");
            return (Criteria) this;
        }

        public Criteria andPaymentPlanSerialGreaterThan(String value) {
            addCriterion("paymentPlanSerial >", value, "paymentPlanSerial");
            return (Criteria) this;
        }

        public Criteria andPaymentPlanSerialGreaterThanOrEqualTo(String value) {
            addCriterion("paymentPlanSerial >=", value, "paymentPlanSerial");
            return (Criteria) this;
        }

        public Criteria andPaymentPlanSerialLessThan(String value) {
            addCriterion("paymentPlanSerial <", value, "paymentPlanSerial");
            return (Criteria) this;
        }

        public Criteria andPaymentPlanSerialLessThanOrEqualTo(String value) {
            addCriterion("paymentPlanSerial <=", value, "paymentPlanSerial");
            return (Criteria) this;
        }

        public Criteria andPaymentPlanSerialLike(String value) {
            addCriterion("paymentPlanSerial like", value, "paymentPlanSerial");
            return (Criteria) this;
        }

        public Criteria andPaymentPlanSerialNotLike(String value) {
            addCriterion("paymentPlanSerial not like", value, "paymentPlanSerial");
            return (Criteria) this;
        }

        public Criteria andPaymentPlanSerialIn(List<String> values) {
            addCriterion("paymentPlanSerial in", values, "paymentPlanSerial");
            return (Criteria) this;
        }

        public Criteria andPaymentPlanSerialNotIn(List<String> values) {
            addCriterion("paymentPlanSerial not in", values, "paymentPlanSerial");
            return (Criteria) this;
        }

        public Criteria andPaymentPlanSerialBetween(String value1, String value2) {
            addCriterion("paymentPlanSerial between", value1, value2, "paymentPlanSerial");
            return (Criteria) this;
        }

        public Criteria andPaymentPlanSerialNotBetween(String value1, String value2) {
            addCriterion("paymentPlanSerial not between", value1, value2, "paymentPlanSerial");
            return (Criteria) this;
        }

        public Criteria andOrderSerialIsNull() {
            addCriterion("orderSerial is null");
            return (Criteria) this;
        }

        public Criteria andOrderSerialIsNotNull() {
            addCriterion("orderSerial is not null");
            return (Criteria) this;
        }

        public Criteria andOrderSerialEqualTo(String value) {
            addCriterion("orderSerial =", value, "orderSerial");
            return (Criteria) this;
        }

        public Criteria andOrderSerialNotEqualTo(String value) {
            addCriterion("orderSerial <>", value, "orderSerial");
            return (Criteria) this;
        }

        public Criteria andOrderSerialGreaterThan(String value) {
            addCriterion("orderSerial >", value, "orderSerial");
            return (Criteria) this;
        }

        public Criteria andOrderSerialGreaterThanOrEqualTo(String value) {
            addCriterion("orderSerial >=", value, "orderSerial");
            return (Criteria) this;
        }

        public Criteria andOrderSerialLessThan(String value) {
            addCriterion("orderSerial <", value, "orderSerial");
            return (Criteria) this;
        }

        public Criteria andOrderSerialLessThanOrEqualTo(String value) {
            addCriterion("orderSerial <=", value, "orderSerial");
            return (Criteria) this;
        }

        public Criteria andOrderSerialLike(String value) {
            addCriterion("orderSerial like", value, "orderSerial");
            return (Criteria) this;
        }

        public Criteria andOrderSerialNotLike(String value) {
            addCriterion("orderSerial not like", value, "orderSerial");
            return (Criteria) this;
        }

        public Criteria andOrderSerialIn(List<String> values) {
            addCriterion("orderSerial in", values, "orderSerial");
            return (Criteria) this;
        }

        public Criteria andOrderSerialNotIn(List<String> values) {
            addCriterion("orderSerial not in", values, "orderSerial");
            return (Criteria) this;
        }

        public Criteria andOrderSerialBetween(String value1, String value2) {
            addCriterion("orderSerial between", value1, value2, "orderSerial");
            return (Criteria) this;
        }

        public Criteria andOrderSerialNotBetween(String value1, String value2) {
            addCriterion("orderSerial not between", value1, value2, "orderSerial");
            return (Criteria) this;
        }

        public Criteria andApplyPaymentAmountIsNull() {
            addCriterion("applyPaymentAmount is null");
            return (Criteria) this;
        }

        public Criteria andApplyPaymentAmountIsNotNull() {
            addCriterion("applyPaymentAmount is not null");
            return (Criteria) this;
        }

        public Criteria andApplyPaymentAmountEqualTo(String value) {
            addCriterion("applyPaymentAmount =", value, "applyPaymentAmount");
            return (Criteria) this;
        }

        public Criteria andApplyPaymentAmountNotEqualTo(String value) {
            addCriterion("applyPaymentAmount <>", value, "applyPaymentAmount");
            return (Criteria) this;
        }

        public Criteria andApplyPaymentAmountGreaterThan(String value) {
            addCriterion("applyPaymentAmount >", value, "applyPaymentAmount");
            return (Criteria) this;
        }

        public Criteria andApplyPaymentAmountGreaterThanOrEqualTo(String value) {
            addCriterion("applyPaymentAmount >=", value, "applyPaymentAmount");
            return (Criteria) this;
        }

        public Criteria andApplyPaymentAmountLessThan(String value) {
            addCriterion("applyPaymentAmount <", value, "applyPaymentAmount");
            return (Criteria) this;
        }

        public Criteria andApplyPaymentAmountLessThanOrEqualTo(String value) {
            addCriterion("applyPaymentAmount <=", value, "applyPaymentAmount");
            return (Criteria) this;
        }

        public Criteria andApplyPaymentAmountLike(String value) {
            addCriterion("applyPaymentAmount like", value, "applyPaymentAmount");
            return (Criteria) this;
        }

        public Criteria andApplyPaymentAmountNotLike(String value) {
            addCriterion("applyPaymentAmount not like", value, "applyPaymentAmount");
            return (Criteria) this;
        }

        public Criteria andApplyPaymentAmountIn(List<String> values) {
            addCriterion("applyPaymentAmount in", values, "applyPaymentAmount");
            return (Criteria) this;
        }

        public Criteria andApplyPaymentAmountNotIn(List<String> values) {
            addCriterion("applyPaymentAmount not in", values, "applyPaymentAmount");
            return (Criteria) this;
        }

        public Criteria andApplyPaymentAmountBetween(String value1, String value2) {
            addCriterion("applyPaymentAmount between", value1, value2, "applyPaymentAmount");
            return (Criteria) this;
        }

        public Criteria andApplyPaymentAmountNotBetween(String value1, String value2) {
            addCriterion("applyPaymentAmount not between", value1, value2, "applyPaymentAmount");
            return (Criteria) this;
        }

        public Criteria andApplyCurrencyIsNull() {
            addCriterion("applyCurrency is null");
            return (Criteria) this;
        }

        public Criteria andApplyCurrencyIsNotNull() {
            addCriterion("applyCurrency is not null");
            return (Criteria) this;
        }

        public Criteria andApplyCurrencyEqualTo(String value) {
            addCriterion("applyCurrency =", value, "applyCurrency");
            return (Criteria) this;
        }

        public Criteria andApplyCurrencyNotEqualTo(String value) {
            addCriterion("applyCurrency <>", value, "applyCurrency");
            return (Criteria) this;
        }

        public Criteria andApplyCurrencyGreaterThan(String value) {
            addCriterion("applyCurrency >", value, "applyCurrency");
            return (Criteria) this;
        }

        public Criteria andApplyCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("applyCurrency >=", value, "applyCurrency");
            return (Criteria) this;
        }

        public Criteria andApplyCurrencyLessThan(String value) {
            addCriterion("applyCurrency <", value, "applyCurrency");
            return (Criteria) this;
        }

        public Criteria andApplyCurrencyLessThanOrEqualTo(String value) {
            addCriterion("applyCurrency <=", value, "applyCurrency");
            return (Criteria) this;
        }

        public Criteria andApplyCurrencyLike(String value) {
            addCriterion("applyCurrency like", value, "applyCurrency");
            return (Criteria) this;
        }

        public Criteria andApplyCurrencyNotLike(String value) {
            addCriterion("applyCurrency not like", value, "applyCurrency");
            return (Criteria) this;
        }

        public Criteria andApplyCurrencyIn(List<String> values) {
            addCriterion("applyCurrency in", values, "applyCurrency");
            return (Criteria) this;
        }

        public Criteria andApplyCurrencyNotIn(List<String> values) {
            addCriterion("applyCurrency not in", values, "applyCurrency");
            return (Criteria) this;
        }

        public Criteria andApplyCurrencyBetween(String value1, String value2) {
            addCriterion("applyCurrency between", value1, value2, "applyCurrency");
            return (Criteria) this;
        }

        public Criteria andApplyCurrencyNotBetween(String value1, String value2) {
            addCriterion("applyCurrency not between", value1, value2, "applyCurrency");
            return (Criteria) this;
        }

        public Criteria andPlayPaymentDateIsNull() {
            addCriterion("playPaymentDate is null");
            return (Criteria) this;
        }

        public Criteria andPlayPaymentDateIsNotNull() {
            addCriterion("playPaymentDate is not null");
            return (Criteria) this;
        }

        public Criteria andPlayPaymentDateEqualTo(Date value) {
            addCriterionForJDBCDate("playPaymentDate =", value, "playPaymentDate");
            return (Criteria) this;
        }

        public Criteria andPlayPaymentDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("playPaymentDate <>", value, "playPaymentDate");
            return (Criteria) this;
        }

        public Criteria andPlayPaymentDateGreaterThan(Date value) {
            addCriterionForJDBCDate("playPaymentDate >", value, "playPaymentDate");
            return (Criteria) this;
        }

        public Criteria andPlayPaymentDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("playPaymentDate >=", value, "playPaymentDate");
            return (Criteria) this;
        }

        public Criteria andPlayPaymentDateLessThan(Date value) {
            addCriterionForJDBCDate("playPaymentDate <", value, "playPaymentDate");
            return (Criteria) this;
        }

        public Criteria andPlayPaymentDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("playPaymentDate <=", value, "playPaymentDate");
            return (Criteria) this;
        }

        public Criteria andPlayPaymentDateIn(List<Date> values) {
            addCriterionForJDBCDate("playPaymentDate in", values, "playPaymentDate");
            return (Criteria) this;
        }

        public Criteria andPlayPaymentDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("playPaymentDate not in", values, "playPaymentDate");
            return (Criteria) this;
        }

        public Criteria andPlayPaymentDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("playPaymentDate between", value1, value2, "playPaymentDate");
            return (Criteria) this;
        }

        public Criteria andPlayPaymentDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("playPaymentDate not between", value1, value2, "playPaymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentNodeIsNull() {
            addCriterion("paymentNode is null");
            return (Criteria) this;
        }

        public Criteria andPaymentNodeIsNotNull() {
            addCriterion("paymentNode is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentNodeEqualTo(String value) {
            addCriterion("paymentNode =", value, "paymentNode");
            return (Criteria) this;
        }

        public Criteria andPaymentNodeNotEqualTo(String value) {
            addCriterion("paymentNode <>", value, "paymentNode");
            return (Criteria) this;
        }

        public Criteria andPaymentNodeGreaterThan(String value) {
            addCriterion("paymentNode >", value, "paymentNode");
            return (Criteria) this;
        }

        public Criteria andPaymentNodeGreaterThanOrEqualTo(String value) {
            addCriterion("paymentNode >=", value, "paymentNode");
            return (Criteria) this;
        }

        public Criteria andPaymentNodeLessThan(String value) {
            addCriterion("paymentNode <", value, "paymentNode");
            return (Criteria) this;
        }

        public Criteria andPaymentNodeLessThanOrEqualTo(String value) {
            addCriterion("paymentNode <=", value, "paymentNode");
            return (Criteria) this;
        }

        public Criteria andPaymentNodeLike(String value) {
            addCriterion("paymentNode like", value, "paymentNode");
            return (Criteria) this;
        }

        public Criteria andPaymentNodeNotLike(String value) {
            addCriterion("paymentNode not like", value, "paymentNode");
            return (Criteria) this;
        }

        public Criteria andPaymentNodeIn(List<String> values) {
            addCriterion("paymentNode in", values, "paymentNode");
            return (Criteria) this;
        }

        public Criteria andPaymentNodeNotIn(List<String> values) {
            addCriterion("paymentNode not in", values, "paymentNode");
            return (Criteria) this;
        }

        public Criteria andPaymentNodeBetween(String value1, String value2) {
            addCriterion("paymentNode between", value1, value2, "paymentNode");
            return (Criteria) this;
        }

        public Criteria andPaymentNodeNotBetween(String value1, String value2) {
            addCriterion("paymentNode not between", value1, value2, "paymentNode");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNull() {
            addCriterion("payType is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNotNull() {
            addCriterion("payType is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeEqualTo(String value) {
            addCriterion("payType =", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotEqualTo(String value) {
            addCriterion("payType <>", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThan(String value) {
            addCriterion("payType >", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThanOrEqualTo(String value) {
            addCriterion("payType >=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThan(String value) {
            addCriterion("payType <", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThanOrEqualTo(String value) {
            addCriterion("payType <=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLike(String value) {
            addCriterion("payType like", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotLike(String value) {
            addCriterion("payType not like", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIn(List<String> values) {
            addCriterion("payType in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotIn(List<String> values) {
            addCriterion("payType not in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeBetween(String value1, String value2) {
            addCriterion("payType between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotBetween(String value1, String value2) {
            addCriterion("payType not between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andNodeNumIsNull() {
            addCriterion("nodeNum is null");
            return (Criteria) this;
        }

        public Criteria andNodeNumIsNotNull() {
            addCriterion("nodeNum is not null");
            return (Criteria) this;
        }

        public Criteria andNodeNumEqualTo(String value) {
            addCriterion("nodeNum =", value, "nodeNum");
            return (Criteria) this;
        }

        public Criteria andNodeNumNotEqualTo(String value) {
            addCriterion("nodeNum <>", value, "nodeNum");
            return (Criteria) this;
        }

        public Criteria andNodeNumGreaterThan(String value) {
            addCriterion("nodeNum >", value, "nodeNum");
            return (Criteria) this;
        }

        public Criteria andNodeNumGreaterThanOrEqualTo(String value) {
            addCriterion("nodeNum >=", value, "nodeNum");
            return (Criteria) this;
        }

        public Criteria andNodeNumLessThan(String value) {
            addCriterion("nodeNum <", value, "nodeNum");
            return (Criteria) this;
        }

        public Criteria andNodeNumLessThanOrEqualTo(String value) {
            addCriterion("nodeNum <=", value, "nodeNum");
            return (Criteria) this;
        }

        public Criteria andNodeNumLike(String value) {
            addCriterion("nodeNum like", value, "nodeNum");
            return (Criteria) this;
        }

        public Criteria andNodeNumNotLike(String value) {
            addCriterion("nodeNum not like", value, "nodeNum");
            return (Criteria) this;
        }

        public Criteria andNodeNumIn(List<String> values) {
            addCriterion("nodeNum in", values, "nodeNum");
            return (Criteria) this;
        }

        public Criteria andNodeNumNotIn(List<String> values) {
            addCriterion("nodeNum not in", values, "nodeNum");
            return (Criteria) this;
        }

        public Criteria andNodeNumBetween(String value1, String value2) {
            addCriterion("nodeNum between", value1, value2, "nodeNum");
            return (Criteria) this;
        }

        public Criteria andNodeNumNotBetween(String value1, String value2) {
            addCriterion("nodeNum not between", value1, value2, "nodeNum");
            return (Criteria) this;
        }

        public Criteria andBillStyleIsNull() {
            addCriterion("billStyle is null");
            return (Criteria) this;
        }

        public Criteria andBillStyleIsNotNull() {
            addCriterion("billStyle is not null");
            return (Criteria) this;
        }

        public Criteria andBillStyleEqualTo(String value) {
            addCriterion("billStyle =", value, "billStyle");
            return (Criteria) this;
        }

        public Criteria andBillStyleNotEqualTo(String value) {
            addCriterion("billStyle <>", value, "billStyle");
            return (Criteria) this;
        }

        public Criteria andBillStyleGreaterThan(String value) {
            addCriterion("billStyle >", value, "billStyle");
            return (Criteria) this;
        }

        public Criteria andBillStyleGreaterThanOrEqualTo(String value) {
            addCriterion("billStyle >=", value, "billStyle");
            return (Criteria) this;
        }

        public Criteria andBillStyleLessThan(String value) {
            addCriterion("billStyle <", value, "billStyle");
            return (Criteria) this;
        }

        public Criteria andBillStyleLessThanOrEqualTo(String value) {
            addCriterion("billStyle <=", value, "billStyle");
            return (Criteria) this;
        }

        public Criteria andBillStyleLike(String value) {
            addCriterion("billStyle like", value, "billStyle");
            return (Criteria) this;
        }

        public Criteria andBillStyleNotLike(String value) {
            addCriterion("billStyle not like", value, "billStyle");
            return (Criteria) this;
        }

        public Criteria andBillStyleIn(List<String> values) {
            addCriterion("billStyle in", values, "billStyle");
            return (Criteria) this;
        }

        public Criteria andBillStyleNotIn(List<String> values) {
            addCriterion("billStyle not in", values, "billStyle");
            return (Criteria) this;
        }

        public Criteria andBillStyleBetween(String value1, String value2) {
            addCriterion("billStyle between", value1, value2, "billStyle");
            return (Criteria) this;
        }

        public Criteria andBillStyleNotBetween(String value1, String value2) {
            addCriterion("billStyle not between", value1, value2, "billStyle");
            return (Criteria) this;
        }

        public Criteria andInvoiceSerialIsNull() {
            addCriterion("invoiceSerial is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceSerialIsNotNull() {
            addCriterion("invoiceSerial is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceSerialEqualTo(String value) {
            addCriterion("invoiceSerial =", value, "invoiceSerial");
            return (Criteria) this;
        }

        public Criteria andInvoiceSerialNotEqualTo(String value) {
            addCriterion("invoiceSerial <>", value, "invoiceSerial");
            return (Criteria) this;
        }

        public Criteria andInvoiceSerialGreaterThan(String value) {
            addCriterion("invoiceSerial >", value, "invoiceSerial");
            return (Criteria) this;
        }

        public Criteria andInvoiceSerialGreaterThanOrEqualTo(String value) {
            addCriterion("invoiceSerial >=", value, "invoiceSerial");
            return (Criteria) this;
        }

        public Criteria andInvoiceSerialLessThan(String value) {
            addCriterion("invoiceSerial <", value, "invoiceSerial");
            return (Criteria) this;
        }

        public Criteria andInvoiceSerialLessThanOrEqualTo(String value) {
            addCriterion("invoiceSerial <=", value, "invoiceSerial");
            return (Criteria) this;
        }

        public Criteria andInvoiceSerialLike(String value) {
            addCriterion("invoiceSerial like", value, "invoiceSerial");
            return (Criteria) this;
        }

        public Criteria andInvoiceSerialNotLike(String value) {
            addCriterion("invoiceSerial not like", value, "invoiceSerial");
            return (Criteria) this;
        }

        public Criteria andInvoiceSerialIn(List<String> values) {
            addCriterion("invoiceSerial in", values, "invoiceSerial");
            return (Criteria) this;
        }

        public Criteria andInvoiceSerialNotIn(List<String> values) {
            addCriterion("invoiceSerial not in", values, "invoiceSerial");
            return (Criteria) this;
        }

        public Criteria andInvoiceSerialBetween(String value1, String value2) {
            addCriterion("invoiceSerial between", value1, value2, "invoiceSerial");
            return (Criteria) this;
        }

        public Criteria andInvoiceSerialNotBetween(String value1, String value2) {
            addCriterion("invoiceSerial not between", value1, value2, "invoiceSerial");
            return (Criteria) this;
        }

        public Criteria andBillTypeIsNull() {
            addCriterion("billType is null");
            return (Criteria) this;
        }

        public Criteria andBillTypeIsNotNull() {
            addCriterion("billType is not null");
            return (Criteria) this;
        }

        public Criteria andBillTypeEqualTo(String value) {
            addCriterion("billType =", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeNotEqualTo(String value) {
            addCriterion("billType <>", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeGreaterThan(String value) {
            addCriterion("billType >", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeGreaterThanOrEqualTo(String value) {
            addCriterion("billType >=", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeLessThan(String value) {
            addCriterion("billType <", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeLessThanOrEqualTo(String value) {
            addCriterion("billType <=", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeLike(String value) {
            addCriterion("billType like", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeNotLike(String value) {
            addCriterion("billType not like", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeIn(List<String> values) {
            addCriterion("billType in", values, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeNotIn(List<String> values) {
            addCriterion("billType not in", values, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeBetween(String value1, String value2) {
            addCriterion("billType between", value1, value2, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeNotBetween(String value1, String value2) {
            addCriterion("billType not between", value1, value2, "billType");
            return (Criteria) this;
        }

        public Criteria andIsBillIsNull() {
            addCriterion("isBill is null");
            return (Criteria) this;
        }

        public Criteria andIsBillIsNotNull() {
            addCriterion("isBill is not null");
            return (Criteria) this;
        }

        public Criteria andIsBillEqualTo(String value) {
            addCriterion("isBill =", value, "isBill");
            return (Criteria) this;
        }

        public Criteria andIsBillNotEqualTo(String value) {
            addCriterion("isBill <>", value, "isBill");
            return (Criteria) this;
        }

        public Criteria andIsBillGreaterThan(String value) {
            addCriterion("isBill >", value, "isBill");
            return (Criteria) this;
        }

        public Criteria andIsBillGreaterThanOrEqualTo(String value) {
            addCriterion("isBill >=", value, "isBill");
            return (Criteria) this;
        }

        public Criteria andIsBillLessThan(String value) {
            addCriterion("isBill <", value, "isBill");
            return (Criteria) this;
        }

        public Criteria andIsBillLessThanOrEqualTo(String value) {
            addCriterion("isBill <=", value, "isBill");
            return (Criteria) this;
        }

        public Criteria andIsBillLike(String value) {
            addCriterion("isBill like", value, "isBill");
            return (Criteria) this;
        }

        public Criteria andIsBillNotLike(String value) {
            addCriterion("isBill not like", value, "isBill");
            return (Criteria) this;
        }

        public Criteria andIsBillIn(List<String> values) {
            addCriterion("isBill in", values, "isBill");
            return (Criteria) this;
        }

        public Criteria andIsBillNotIn(List<String> values) {
            addCriterion("isBill not in", values, "isBill");
            return (Criteria) this;
        }

        public Criteria andIsBillBetween(String value1, String value2) {
            addCriterion("isBill between", value1, value2, "isBill");
            return (Criteria) this;
        }

        public Criteria andIsBillNotBetween(String value1, String value2) {
            addCriterion("isBill not between", value1, value2, "isBill");
            return (Criteria) this;
        }

        public Criteria andApplyDeptIsNull() {
            addCriterion("applyDept is null");
            return (Criteria) this;
        }

        public Criteria andApplyDeptIsNotNull() {
            addCriterion("applyDept is not null");
            return (Criteria) this;
        }

        public Criteria andApplyDeptEqualTo(String value) {
            addCriterion("applyDept =", value, "applyDept");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNotEqualTo(String value) {
            addCriterion("applyDept <>", value, "applyDept");
            return (Criteria) this;
        }

        public Criteria andApplyDeptGreaterThan(String value) {
            addCriterion("applyDept >", value, "applyDept");
            return (Criteria) this;
        }

        public Criteria andApplyDeptGreaterThanOrEqualTo(String value) {
            addCriterion("applyDept >=", value, "applyDept");
            return (Criteria) this;
        }

        public Criteria andApplyDeptLessThan(String value) {
            addCriterion("applyDept <", value, "applyDept");
            return (Criteria) this;
        }

        public Criteria andApplyDeptLessThanOrEqualTo(String value) {
            addCriterion("applyDept <=", value, "applyDept");
            return (Criteria) this;
        }

        public Criteria andApplyDeptLike(String value) {
            addCriterion("applyDept like", value, "applyDept");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNotLike(String value) {
            addCriterion("applyDept not like", value, "applyDept");
            return (Criteria) this;
        }

        public Criteria andApplyDeptIn(List<String> values) {
            addCriterion("applyDept in", values, "applyDept");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNotIn(List<String> values) {
            addCriterion("applyDept not in", values, "applyDept");
            return (Criteria) this;
        }

        public Criteria andApplyDeptBetween(String value1, String value2) {
            addCriterion("applyDept between", value1, value2, "applyDept");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNotBetween(String value1, String value2) {
            addCriterion("applyDept not between", value1, value2, "applyDept");
            return (Criteria) this;
        }

        public Criteria andApplyDateIsNull() {
            addCriterion("applyDate is null");
            return (Criteria) this;
        }

        public Criteria andApplyDateIsNotNull() {
            addCriterion("applyDate is not null");
            return (Criteria) this;
        }

        public Criteria andApplyDateEqualTo(Date value) {
            addCriterionForJDBCDate("applyDate =", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("applyDate <>", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateGreaterThan(Date value) {
            addCriterionForJDBCDate("applyDate >", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("applyDate >=", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateLessThan(Date value) {
            addCriterionForJDBCDate("applyDate <", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("applyDate <=", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateIn(List<Date> values) {
            addCriterionForJDBCDate("applyDate in", values, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("applyDate not in", values, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("applyDate between", value1, value2, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("applyDate not between", value1, value2, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplicantIsNull() {
            addCriterion("applicant is null");
            return (Criteria) this;
        }

        public Criteria andApplicantIsNotNull() {
            addCriterion("applicant is not null");
            return (Criteria) this;
        }

        public Criteria andApplicantEqualTo(String value) {
            addCriterion("applicant =", value, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantNotEqualTo(String value) {
            addCriterion("applicant <>", value, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantGreaterThan(String value) {
            addCriterion("applicant >", value, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantGreaterThanOrEqualTo(String value) {
            addCriterion("applicant >=", value, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantLessThan(String value) {
            addCriterion("applicant <", value, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantLessThanOrEqualTo(String value) {
            addCriterion("applicant <=", value, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantLike(String value) {
            addCriterion("applicant like", value, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantNotLike(String value) {
            addCriterion("applicant not like", value, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantIn(List<String> values) {
            addCriterion("applicant in", values, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantNotIn(List<String> values) {
            addCriterion("applicant not in", values, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantBetween(String value1, String value2) {
            addCriterion("applicant between", value1, value2, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantNotBetween(String value1, String value2) {
            addCriterion("applicant not between", value1, value2, "applicant");
            return (Criteria) this;
        }

        public Criteria andPayeeIsNull() {
            addCriterion("payee is null");
            return (Criteria) this;
        }

        public Criteria andPayeeIsNotNull() {
            addCriterion("payee is not null");
            return (Criteria) this;
        }

        public Criteria andPayeeEqualTo(String value) {
            addCriterion("payee =", value, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeNotEqualTo(String value) {
            addCriterion("payee <>", value, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeGreaterThan(String value) {
            addCriterion("payee >", value, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeGreaterThanOrEqualTo(String value) {
            addCriterion("payee >=", value, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeLessThan(String value) {
            addCriterion("payee <", value, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeLessThanOrEqualTo(String value) {
            addCriterion("payee <=", value, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeLike(String value) {
            addCriterion("payee like", value, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeNotLike(String value) {
            addCriterion("payee not like", value, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeIn(List<String> values) {
            addCriterion("payee in", values, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeNotIn(List<String> values) {
            addCriterion("payee not in", values, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeBetween(String value1, String value2) {
            addCriterion("payee between", value1, value2, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeNotBetween(String value1, String value2) {
            addCriterion("payee not between", value1, value2, "payee");
            return (Criteria) this;
        }

        public Criteria andContactIsNull() {
            addCriterion("contact is null");
            return (Criteria) this;
        }

        public Criteria andContactIsNotNull() {
            addCriterion("contact is not null");
            return (Criteria) this;
        }

        public Criteria andContactEqualTo(String value) {
            addCriterion("contact =", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotEqualTo(String value) {
            addCriterion("contact <>", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactGreaterThan(String value) {
            addCriterion("contact >", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactGreaterThanOrEqualTo(String value) {
            addCriterion("contact >=", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLessThan(String value) {
            addCriterion("contact <", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLessThanOrEqualTo(String value) {
            addCriterion("contact <=", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLike(String value) {
            addCriterion("contact like", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotLike(String value) {
            addCriterion("contact not like", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactIn(List<String> values) {
            addCriterion("contact in", values, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotIn(List<String> values) {
            addCriterion("contact not in", values, "contact");
            return (Criteria) this;
        }

        public Criteria andContactBetween(String value1, String value2) {
            addCriterion("contact between", value1, value2, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotBetween(String value1, String value2) {
            addCriterion("contact not between", value1, value2, "contact");
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

        public Criteria andBankIsNull() {
            addCriterion("bank is null");
            return (Criteria) this;
        }

        public Criteria andBankIsNotNull() {
            addCriterion("bank is not null");
            return (Criteria) this;
        }

        public Criteria andBankEqualTo(String value) {
            addCriterion("bank =", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotEqualTo(String value) {
            addCriterion("bank <>", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankGreaterThan(String value) {
            addCriterion("bank >", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankGreaterThanOrEqualTo(String value) {
            addCriterion("bank >=", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLessThan(String value) {
            addCriterion("bank <", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLessThanOrEqualTo(String value) {
            addCriterion("bank <=", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLike(String value) {
            addCriterion("bank like", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotLike(String value) {
            addCriterion("bank not like", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankIn(List<String> values) {
            addCriterion("bank in", values, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotIn(List<String> values) {
            addCriterion("bank not in", values, "bank");
            return (Criteria) this;
        }

        public Criteria andBankBetween(String value1, String value2) {
            addCriterion("bank between", value1, value2, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotBetween(String value1, String value2) {
            addCriterion("bank not between", value1, value2, "bank");
            return (Criteria) this;
        }

        public Criteria andAccountNameIsNull() {
            addCriterion("accountName is null");
            return (Criteria) this;
        }

        public Criteria andAccountNameIsNotNull() {
            addCriterion("accountName is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNameEqualTo(String value) {
            addCriterion("accountName =", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotEqualTo(String value) {
            addCriterion("accountName <>", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThan(String value) {
            addCriterion("accountName >", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("accountName >=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThan(String value) {
            addCriterion("accountName <", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThanOrEqualTo(String value) {
            addCriterion("accountName <=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLike(String value) {
            addCriterion("accountName like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotLike(String value) {
            addCriterion("accountName not like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameIn(List<String> values) {
            addCriterion("accountName in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotIn(List<String> values) {
            addCriterion("accountName not in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameBetween(String value1, String value2) {
            addCriterion("accountName between", value1, value2, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotBetween(String value1, String value2) {
            addCriterion("accountName not between", value1, value2, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNumberIsNull() {
            addCriterion("accountNumber is null");
            return (Criteria) this;
        }

        public Criteria andAccountNumberIsNotNull() {
            addCriterion("accountNumber is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNumberEqualTo(String value) {
            addCriterion("accountNumber =", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberNotEqualTo(String value) {
            addCriterion("accountNumber <>", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberGreaterThan(String value) {
            addCriterion("accountNumber >", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberGreaterThanOrEqualTo(String value) {
            addCriterion("accountNumber >=", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberLessThan(String value) {
            addCriterion("accountNumber <", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberLessThanOrEqualTo(String value) {
            addCriterion("accountNumber <=", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberLike(String value) {
            addCriterion("accountNumber like", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberNotLike(String value) {
            addCriterion("accountNumber not like", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberIn(List<String> values) {
            addCriterion("accountNumber in", values, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberNotIn(List<String> values) {
            addCriterion("accountNumber not in", values, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberBetween(String value1, String value2) {
            addCriterion("accountNumber between", value1, value2, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberNotBetween(String value1, String value2) {
            addCriterion("accountNumber not between", value1, value2, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andPaymentDateIsNull() {
            addCriterion("paymentDate is null");
            return (Criteria) this;
        }

        public Criteria andPaymentDateIsNotNull() {
            addCriterion("paymentDate is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentDateEqualTo(Date value) {
            addCriterionForJDBCDate("paymentDate =", value, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("paymentDate <>", value, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateGreaterThan(Date value) {
            addCriterionForJDBCDate("paymentDate >", value, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("paymentDate >=", value, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateLessThan(Date value) {
            addCriterionForJDBCDate("paymentDate <", value, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("paymentDate <=", value, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateIn(List<Date> values) {
            addCriterionForJDBCDate("paymentDate in", values, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("paymentDate not in", values, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("paymentDate between", value1, value2, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("paymentDate not between", value1, value2, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentAmountIsNull() {
            addCriterion("paymentAmount is null");
            return (Criteria) this;
        }

        public Criteria andPaymentAmountIsNotNull() {
            addCriterion("paymentAmount is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentAmountEqualTo(String value) {
            addCriterion("paymentAmount =", value, "paymentAmount");
            return (Criteria) this;
        }

        public Criteria andPaymentAmountNotEqualTo(String value) {
            addCriterion("paymentAmount <>", value, "paymentAmount");
            return (Criteria) this;
        }

        public Criteria andPaymentAmountGreaterThan(String value) {
            addCriterion("paymentAmount >", value, "paymentAmount");
            return (Criteria) this;
        }

        public Criteria andPaymentAmountGreaterThanOrEqualTo(String value) {
            addCriterion("paymentAmount >=", value, "paymentAmount");
            return (Criteria) this;
        }

        public Criteria andPaymentAmountLessThan(String value) {
            addCriterion("paymentAmount <", value, "paymentAmount");
            return (Criteria) this;
        }

        public Criteria andPaymentAmountLessThanOrEqualTo(String value) {
            addCriterion("paymentAmount <=", value, "paymentAmount");
            return (Criteria) this;
        }

        public Criteria andPaymentAmountLike(String value) {
            addCriterion("paymentAmount like", value, "paymentAmount");
            return (Criteria) this;
        }

        public Criteria andPaymentAmountNotLike(String value) {
            addCriterion("paymentAmount not like", value, "paymentAmount");
            return (Criteria) this;
        }

        public Criteria andPaymentAmountIn(List<String> values) {
            addCriterion("paymentAmount in", values, "paymentAmount");
            return (Criteria) this;
        }

        public Criteria andPaymentAmountNotIn(List<String> values) {
            addCriterion("paymentAmount not in", values, "paymentAmount");
            return (Criteria) this;
        }

        public Criteria andPaymentAmountBetween(String value1, String value2) {
            addCriterion("paymentAmount between", value1, value2, "paymentAmount");
            return (Criteria) this;
        }

        public Criteria andPaymentAmountNotBetween(String value1, String value2) {
            addCriterion("paymentAmount not between", value1, value2, "paymentAmount");
            return (Criteria) this;
        }

        public Criteria andPaymentStyleIsNull() {
            addCriterion("paymentStyle is null");
            return (Criteria) this;
        }

        public Criteria andPaymentStyleIsNotNull() {
            addCriterion("paymentStyle is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentStyleEqualTo(String value) {
            addCriterion("paymentStyle =", value, "paymentStyle");
            return (Criteria) this;
        }

        public Criteria andPaymentStyleNotEqualTo(String value) {
            addCriterion("paymentStyle <>", value, "paymentStyle");
            return (Criteria) this;
        }

        public Criteria andPaymentStyleGreaterThan(String value) {
            addCriterion("paymentStyle >", value, "paymentStyle");
            return (Criteria) this;
        }

        public Criteria andPaymentStyleGreaterThanOrEqualTo(String value) {
            addCriterion("paymentStyle >=", value, "paymentStyle");
            return (Criteria) this;
        }

        public Criteria andPaymentStyleLessThan(String value) {
            addCriterion("paymentStyle <", value, "paymentStyle");
            return (Criteria) this;
        }

        public Criteria andPaymentStyleLessThanOrEqualTo(String value) {
            addCriterion("paymentStyle <=", value, "paymentStyle");
            return (Criteria) this;
        }

        public Criteria andPaymentStyleLike(String value) {
            addCriterion("paymentStyle like", value, "paymentStyle");
            return (Criteria) this;
        }

        public Criteria andPaymentStyleNotLike(String value) {
            addCriterion("paymentStyle not like", value, "paymentStyle");
            return (Criteria) this;
        }

        public Criteria andPaymentStyleIn(List<String> values) {
            addCriterion("paymentStyle in", values, "paymentStyle");
            return (Criteria) this;
        }

        public Criteria andPaymentStyleNotIn(List<String> values) {
            addCriterion("paymentStyle not in", values, "paymentStyle");
            return (Criteria) this;
        }

        public Criteria andPaymentStyleBetween(String value1, String value2) {
            addCriterion("paymentStyle between", value1, value2, "paymentStyle");
            return (Criteria) this;
        }

        public Criteria andPaymentStyleNotBetween(String value1, String value2) {
            addCriterion("paymentStyle not between", value1, value2, "paymentStyle");
            return (Criteria) this;
        }

        public Criteria andBankListIsNull() {
            addCriterion("bankList is null");
            return (Criteria) this;
        }

        public Criteria andBankListIsNotNull() {
            addCriterion("bankList is not null");
            return (Criteria) this;
        }

        public Criteria andBankListEqualTo(String value) {
            addCriterion("bankList =", value, "bankList");
            return (Criteria) this;
        }

        public Criteria andBankListNotEqualTo(String value) {
            addCriterion("bankList <>", value, "bankList");
            return (Criteria) this;
        }

        public Criteria andBankListGreaterThan(String value) {
            addCriterion("bankList >", value, "bankList");
            return (Criteria) this;
        }

        public Criteria andBankListGreaterThanOrEqualTo(String value) {
            addCriterion("bankList >=", value, "bankList");
            return (Criteria) this;
        }

        public Criteria andBankListLessThan(String value) {
            addCriterion("bankList <", value, "bankList");
            return (Criteria) this;
        }

        public Criteria andBankListLessThanOrEqualTo(String value) {
            addCriterion("bankList <=", value, "bankList");
            return (Criteria) this;
        }

        public Criteria andBankListLike(String value) {
            addCriterion("bankList like", value, "bankList");
            return (Criteria) this;
        }

        public Criteria andBankListNotLike(String value) {
            addCriterion("bankList not like", value, "bankList");
            return (Criteria) this;
        }

        public Criteria andBankListIn(List<String> values) {
            addCriterion("bankList in", values, "bankList");
            return (Criteria) this;
        }

        public Criteria andBankListNotIn(List<String> values) {
            addCriterion("bankList not in", values, "bankList");
            return (Criteria) this;
        }

        public Criteria andBankListBetween(String value1, String value2) {
            addCriterion("bankList between", value1, value2, "bankList");
            return (Criteria) this;
        }

        public Criteria andBankListNotBetween(String value1, String value2) {
            addCriterion("bankList not between", value1, value2, "bankList");
            return (Criteria) this;
        }

        public Criteria andPayerIsNull() {
            addCriterion("payer is null");
            return (Criteria) this;
        }

        public Criteria andPayerIsNotNull() {
            addCriterion("payer is not null");
            return (Criteria) this;
        }

        public Criteria andPayerEqualTo(String value) {
            addCriterion("payer =", value, "payer");
            return (Criteria) this;
        }

        public Criteria andPayerNotEqualTo(String value) {
            addCriterion("payer <>", value, "payer");
            return (Criteria) this;
        }

        public Criteria andPayerGreaterThan(String value) {
            addCriterion("payer >", value, "payer");
            return (Criteria) this;
        }

        public Criteria andPayerGreaterThanOrEqualTo(String value) {
            addCriterion("payer >=", value, "payer");
            return (Criteria) this;
        }

        public Criteria andPayerLessThan(String value) {
            addCriterion("payer <", value, "payer");
            return (Criteria) this;
        }

        public Criteria andPayerLessThanOrEqualTo(String value) {
            addCriterion("payer <=", value, "payer");
            return (Criteria) this;
        }

        public Criteria andPayerLike(String value) {
            addCriterion("payer like", value, "payer");
            return (Criteria) this;
        }

        public Criteria andPayerNotLike(String value) {
            addCriterion("payer not like", value, "payer");
            return (Criteria) this;
        }

        public Criteria andPayerIn(List<String> values) {
            addCriterion("payer in", values, "payer");
            return (Criteria) this;
        }

        public Criteria andPayerNotIn(List<String> values) {
            addCriterion("payer not in", values, "payer");
            return (Criteria) this;
        }

        public Criteria andPayerBetween(String value1, String value2) {
            addCriterion("payer between", value1, value2, "payer");
            return (Criteria) this;
        }

        public Criteria andPayerNotBetween(String value1, String value2) {
            addCriterion("payer not between", value1, value2, "payer");
            return (Criteria) this;
        }

        public Criteria andPayRemarkIsNull() {
            addCriterion("payRemark is null");
            return (Criteria) this;
        }

        public Criteria andPayRemarkIsNotNull() {
            addCriterion("payRemark is not null");
            return (Criteria) this;
        }

        public Criteria andPayRemarkEqualTo(String value) {
            addCriterion("payRemark =", value, "payRemark");
            return (Criteria) this;
        }

        public Criteria andPayRemarkNotEqualTo(String value) {
            addCriterion("payRemark <>", value, "payRemark");
            return (Criteria) this;
        }

        public Criteria andPayRemarkGreaterThan(String value) {
            addCriterion("payRemark >", value, "payRemark");
            return (Criteria) this;
        }

        public Criteria andPayRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("payRemark >=", value, "payRemark");
            return (Criteria) this;
        }

        public Criteria andPayRemarkLessThan(String value) {
            addCriterion("payRemark <", value, "payRemark");
            return (Criteria) this;
        }

        public Criteria andPayRemarkLessThanOrEqualTo(String value) {
            addCriterion("payRemark <=", value, "payRemark");
            return (Criteria) this;
        }

        public Criteria andPayRemarkLike(String value) {
            addCriterion("payRemark like", value, "payRemark");
            return (Criteria) this;
        }

        public Criteria andPayRemarkNotLike(String value) {
            addCriterion("payRemark not like", value, "payRemark");
            return (Criteria) this;
        }

        public Criteria andPayRemarkIn(List<String> values) {
            addCriterion("payRemark in", values, "payRemark");
            return (Criteria) this;
        }

        public Criteria andPayRemarkNotIn(List<String> values) {
            addCriterion("payRemark not in", values, "payRemark");
            return (Criteria) this;
        }

        public Criteria andPayRemarkBetween(String value1, String value2) {
            addCriterion("payRemark between", value1, value2, "payRemark");
            return (Criteria) this;
        }

        public Criteria andPayRemarkNotBetween(String value1, String value2) {
            addCriterion("payRemark not between", value1, value2, "payRemark");
            return (Criteria) this;
        }

        public Criteria andApproverIsNull() {
            addCriterion("approver is null");
            return (Criteria) this;
        }

        public Criteria andApproverIsNotNull() {
            addCriterion("approver is not null");
            return (Criteria) this;
        }

        public Criteria andApproverEqualTo(String value) {
            addCriterion("approver =", value, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverNotEqualTo(String value) {
            addCriterion("approver <>", value, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverGreaterThan(String value) {
            addCriterion("approver >", value, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverGreaterThanOrEqualTo(String value) {
            addCriterion("approver >=", value, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverLessThan(String value) {
            addCriterion("approver <", value, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverLessThanOrEqualTo(String value) {
            addCriterion("approver <=", value, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverLike(String value) {
            addCriterion("approver like", value, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverNotLike(String value) {
            addCriterion("approver not like", value, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverIn(List<String> values) {
            addCriterion("approver in", values, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverNotIn(List<String> values) {
            addCriterion("approver not in", values, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverBetween(String value1, String value2) {
            addCriterion("approver between", value1, value2, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverNotBetween(String value1, String value2) {
            addCriterion("approver not between", value1, value2, "approver");
            return (Criteria) this;
        }

        public Criteria andApprovalDateIsNull() {
            addCriterion("approvalDate is null");
            return (Criteria) this;
        }

        public Criteria andApprovalDateIsNotNull() {
            addCriterion("approvalDate is not null");
            return (Criteria) this;
        }

        public Criteria andApprovalDateEqualTo(Date value) {
            addCriterionForJDBCDate("approvalDate =", value, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("approvalDate <>", value, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDateGreaterThan(Date value) {
            addCriterionForJDBCDate("approvalDate >", value, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("approvalDate >=", value, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDateLessThan(Date value) {
            addCriterionForJDBCDate("approvalDate <", value, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("approvalDate <=", value, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDateIn(List<Date> values) {
            addCriterionForJDBCDate("approvalDate in", values, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("approvalDate not in", values, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("approvalDate between", value1, value2, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("approvalDate not between", value1, value2, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andPaymentVoucherIsNull() {
            addCriterion("paymentVoucher is null");
            return (Criteria) this;
        }

        public Criteria andPaymentVoucherIsNotNull() {
            addCriterion("paymentVoucher is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentVoucherEqualTo(String value) {
            addCriterion("paymentVoucher =", value, "paymentVoucher");
            return (Criteria) this;
        }

        public Criteria andPaymentVoucherNotEqualTo(String value) {
            addCriterion("paymentVoucher <>", value, "paymentVoucher");
            return (Criteria) this;
        }

        public Criteria andPaymentVoucherGreaterThan(String value) {
            addCriterion("paymentVoucher >", value, "paymentVoucher");
            return (Criteria) this;
        }

        public Criteria andPaymentVoucherGreaterThanOrEqualTo(String value) {
            addCriterion("paymentVoucher >=", value, "paymentVoucher");
            return (Criteria) this;
        }

        public Criteria andPaymentVoucherLessThan(String value) {
            addCriterion("paymentVoucher <", value, "paymentVoucher");
            return (Criteria) this;
        }

        public Criteria andPaymentVoucherLessThanOrEqualTo(String value) {
            addCriterion("paymentVoucher <=", value, "paymentVoucher");
            return (Criteria) this;
        }

        public Criteria andPaymentVoucherLike(String value) {
            addCriterion("paymentVoucher like", value, "paymentVoucher");
            return (Criteria) this;
        }

        public Criteria andPaymentVoucherNotLike(String value) {
            addCriterion("paymentVoucher not like", value, "paymentVoucher");
            return (Criteria) this;
        }

        public Criteria andPaymentVoucherIn(List<String> values) {
            addCriterion("paymentVoucher in", values, "paymentVoucher");
            return (Criteria) this;
        }

        public Criteria andPaymentVoucherNotIn(List<String> values) {
            addCriterion("paymentVoucher not in", values, "paymentVoucher");
            return (Criteria) this;
        }

        public Criteria andPaymentVoucherBetween(String value1, String value2) {
            addCriterion("paymentVoucher between", value1, value2, "paymentVoucher");
            return (Criteria) this;
        }

        public Criteria andPaymentVoucherNotBetween(String value1, String value2) {
            addCriterion("paymentVoucher not between", value1, value2, "paymentVoucher");
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

        public Criteria andProcessInstanceIdIsNull() {
            addCriterion("processInstanceId is null");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdIsNotNull() {
            addCriterion("processInstanceId is not null");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdEqualTo(String value) {
            addCriterion("processInstanceId =", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdNotEqualTo(String value) {
            addCriterion("processInstanceId <>", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdGreaterThan(String value) {
            addCriterion("processInstanceId >", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdGreaterThanOrEqualTo(String value) {
            addCriterion("processInstanceId >=", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdLessThan(String value) {
            addCriterion("processInstanceId <", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdLessThanOrEqualTo(String value) {
            addCriterion("processInstanceId <=", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdLike(String value) {
            addCriterion("processInstanceId like", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdNotLike(String value) {
            addCriterion("processInstanceId not like", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdIn(List<String> values) {
            addCriterion("processInstanceId in", values, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdNotIn(List<String> values) {
            addCriterion("processInstanceId not in", values, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdBetween(String value1, String value2) {
            addCriterion("processInstanceId between", value1, value2, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdNotBetween(String value1, String value2) {
            addCriterion("processInstanceId not between", value1, value2, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("reason not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("reason not between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("userId is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("userId is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("userId =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("userId <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("userId >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("userId >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("userId <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("userId <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("userId in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("userId not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("userId between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("userId not between", value1, value2, "userId");
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