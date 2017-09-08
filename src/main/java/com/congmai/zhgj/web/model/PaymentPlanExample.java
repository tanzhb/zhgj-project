package com.congmai.zhgj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PaymentPlanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PaymentPlanExample() {
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

        public Criteria andPaymentPlanNumIsNull() {
            addCriterion("paymentPlanNum is null");
            return (Criteria) this;
        }

        public Criteria andPaymentPlanNumIsNotNull() {
            addCriterion("paymentPlanNum is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentPlanNumEqualTo(String value) {
            addCriterion("paymentPlanNum =", value, "paymentPlanNum");
            return (Criteria) this;
        }

        public Criteria andPaymentPlanNumNotEqualTo(String value) {
            addCriterion("paymentPlanNum <>", value, "paymentPlanNum");
            return (Criteria) this;
        }

        public Criteria andPaymentPlanNumGreaterThan(String value) {
            addCriterion("paymentPlanNum >", value, "paymentPlanNum");
            return (Criteria) this;
        }

        public Criteria andPaymentPlanNumGreaterThanOrEqualTo(String value) {
            addCriterion("paymentPlanNum >=", value, "paymentPlanNum");
            return (Criteria) this;
        }

        public Criteria andPaymentPlanNumLessThan(String value) {
            addCriterion("paymentPlanNum <", value, "paymentPlanNum");
            return (Criteria) this;
        }

        public Criteria andPaymentPlanNumLessThanOrEqualTo(String value) {
            addCriterion("paymentPlanNum <=", value, "paymentPlanNum");
            return (Criteria) this;
        }

        public Criteria andPaymentPlanNumLike(String value) {
            addCriterion("paymentPlanNum like", value, "paymentPlanNum");
            return (Criteria) this;
        }

        public Criteria andPaymentPlanNumNotLike(String value) {
            addCriterion("paymentPlanNum not like", value, "paymentPlanNum");
            return (Criteria) this;
        }

        public Criteria andPaymentPlanNumIn(List<String> values) {
            addCriterion("paymentPlanNum in", values, "paymentPlanNum");
            return (Criteria) this;
        }

        public Criteria andPaymentPlanNumNotIn(List<String> values) {
            addCriterion("paymentPlanNum not in", values, "paymentPlanNum");
            return (Criteria) this;
        }

        public Criteria andPaymentPlanNumBetween(String value1, String value2) {
            addCriterion("paymentPlanNum between", value1, value2, "paymentPlanNum");
            return (Criteria) this;
        }

        public Criteria andPaymentPlanNumNotBetween(String value1, String value2) {
            addCriterion("paymentPlanNum not between", value1, value2, "paymentPlanNum");
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

        public Criteria andClauseSettlementSerialIsNull() {
            addCriterion("clauseSettlementSerial is null");
            return (Criteria) this;
        }

        public Criteria andClauseSettlementSerialIsNotNull() {
            addCriterion("clauseSettlementSerial is not null");
            return (Criteria) this;
        }

        public Criteria andClauseSettlementSerialEqualTo(String value) {
            addCriterion("clauseSettlementSerial =", value, "clauseSettlementSerial");
            return (Criteria) this;
        }

        public Criteria andClauseSettlementSerialNotEqualTo(String value) {
            addCriterion("clauseSettlementSerial <>", value, "clauseSettlementSerial");
            return (Criteria) this;
        }

        public Criteria andClauseSettlementSerialGreaterThan(String value) {
            addCriterion("clauseSettlementSerial >", value, "clauseSettlementSerial");
            return (Criteria) this;
        }

        public Criteria andClauseSettlementSerialGreaterThanOrEqualTo(String value) {
            addCriterion("clauseSettlementSerial >=", value, "clauseSettlementSerial");
            return (Criteria) this;
        }

        public Criteria andClauseSettlementSerialLessThan(String value) {
            addCriterion("clauseSettlementSerial <", value, "clauseSettlementSerial");
            return (Criteria) this;
        }

        public Criteria andClauseSettlementSerialLessThanOrEqualTo(String value) {
            addCriterion("clauseSettlementSerial <=", value, "clauseSettlementSerial");
            return (Criteria) this;
        }

        public Criteria andClauseSettlementSerialLike(String value) {
            addCriterion("clauseSettlementSerial like", value, "clauseSettlementSerial");
            return (Criteria) this;
        }

        public Criteria andClauseSettlementSerialNotLike(String value) {
            addCriterion("clauseSettlementSerial not like", value, "clauseSettlementSerial");
            return (Criteria) this;
        }

        public Criteria andClauseSettlementSerialIn(List<String> values) {
            addCriterion("clauseSettlementSerial in", values, "clauseSettlementSerial");
            return (Criteria) this;
        }

        public Criteria andClauseSettlementSerialNotIn(List<String> values) {
            addCriterion("clauseSettlementSerial not in", values, "clauseSettlementSerial");
            return (Criteria) this;
        }

        public Criteria andClauseSettlementSerialBetween(String value1, String value2) {
            addCriterion("clauseSettlementSerial between", value1, value2, "clauseSettlementSerial");
            return (Criteria) this;
        }

        public Criteria andClauseSettlementSerialNotBetween(String value1, String value2) {
            addCriterion("clauseSettlementSerial not between", value1, value2, "clauseSettlementSerial");
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

        public Criteria andPaymentNOIsNull() {
            addCriterion("paymentNO is null");
            return (Criteria) this;
        }

        public Criteria andPaymentNOIsNotNull() {
            addCriterion("paymentNO is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentNOEqualTo(String value) {
            addCriterion("paymentNO =", value, "paymentNO");
            return (Criteria) this;
        }

        public Criteria andPaymentNONotEqualTo(String value) {
            addCriterion("paymentNO <>", value, "paymentNO");
            return (Criteria) this;
        }

        public Criteria andPaymentNOGreaterThan(String value) {
            addCriterion("paymentNO >", value, "paymentNO");
            return (Criteria) this;
        }

        public Criteria andPaymentNOGreaterThanOrEqualTo(String value) {
            addCriterion("paymentNO >=", value, "paymentNO");
            return (Criteria) this;
        }

        public Criteria andPaymentNOLessThan(String value) {
            addCriterion("paymentNO <", value, "paymentNO");
            return (Criteria) this;
        }

        public Criteria andPaymentNOLessThanOrEqualTo(String value) {
            addCriterion("paymentNO <=", value, "paymentNO");
            return (Criteria) this;
        }

        public Criteria andPaymentNOLike(String value) {
            addCriterion("paymentNO like", value, "paymentNO");
            return (Criteria) this;
        }

        public Criteria andPaymentNONotLike(String value) {
            addCriterion("paymentNO not like", value, "paymentNO");
            return (Criteria) this;
        }

        public Criteria andPaymentNOIn(List<String> values) {
            addCriterion("paymentNO in", values, "paymentNO");
            return (Criteria) this;
        }

        public Criteria andPaymentNONotIn(List<String> values) {
            addCriterion("paymentNO not in", values, "paymentNO");
            return (Criteria) this;
        }

        public Criteria andPaymentNOBetween(String value1, String value2) {
            addCriterion("paymentNO between", value1, value2, "paymentNO");
            return (Criteria) this;
        }

        public Criteria andPaymentNONotBetween(String value1, String value2) {
            addCriterion("paymentNO not between", value1, value2, "paymentNO");
            return (Criteria) this;
        }

        public Criteria andPaymentRateIsNull() {
            addCriterion("paymentRate is null");
            return (Criteria) this;
        }

        public Criteria andPaymentRateIsNotNull() {
            addCriterion("paymentRate is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentRateEqualTo(String value) {
            addCriterion("paymentRate =", value, "paymentRate");
            return (Criteria) this;
        }

        public Criteria andPaymentRateNotEqualTo(String value) {
            addCriterion("paymentRate <>", value, "paymentRate");
            return (Criteria) this;
        }

        public Criteria andPaymentRateGreaterThan(String value) {
            addCriterion("paymentRate >", value, "paymentRate");
            return (Criteria) this;
        }

        public Criteria andPaymentRateGreaterThanOrEqualTo(String value) {
            addCriterion("paymentRate >=", value, "paymentRate");
            return (Criteria) this;
        }

        public Criteria andPaymentRateLessThan(String value) {
            addCriterion("paymentRate <", value, "paymentRate");
            return (Criteria) this;
        }

        public Criteria andPaymentRateLessThanOrEqualTo(String value) {
            addCriterion("paymentRate <=", value, "paymentRate");
            return (Criteria) this;
        }

        public Criteria andPaymentRateLike(String value) {
            addCriterion("paymentRate like", value, "paymentRate");
            return (Criteria) this;
        }

        public Criteria andPaymentRateNotLike(String value) {
            addCriterion("paymentRate not like", value, "paymentRate");
            return (Criteria) this;
        }

        public Criteria andPaymentRateIn(List<String> values) {
            addCriterion("paymentRate in", values, "paymentRate");
            return (Criteria) this;
        }

        public Criteria andPaymentRateNotIn(List<String> values) {
            addCriterion("paymentRate not in", values, "paymentRate");
            return (Criteria) this;
        }

        public Criteria andPaymentRateBetween(String value1, String value2) {
            addCriterion("paymentRate between", value1, value2, "paymentRate");
            return (Criteria) this;
        }

        public Criteria andPaymentRateNotBetween(String value1, String value2) {
            addCriterion("paymentRate not between", value1, value2, "paymentRate");
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

        public Criteria andPeriodIsNull() {
            addCriterion("period is null");
            return (Criteria) this;
        }

        public Criteria andPeriodIsNotNull() {
            addCriterion("period is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodEqualTo(String value) {
            addCriterion("period =", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotEqualTo(String value) {
            addCriterion("period <>", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodGreaterThan(String value) {
            addCriterion("period >", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodGreaterThanOrEqualTo(String value) {
            addCriterion("period >=", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLessThan(String value) {
            addCriterion("period <", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLessThanOrEqualTo(String value) {
            addCriterion("period <=", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLike(String value) {
            addCriterion("period like", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotLike(String value) {
            addCriterion("period not like", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodIn(List<String> values) {
            addCriterion("period in", values, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotIn(List<String> values) {
            addCriterion("period not in", values, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodBetween(String value1, String value2) {
            addCriterion("period between", value1, value2, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotBetween(String value1, String value2) {
            addCriterion("period not between", value1, value2, "period");
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

        public Criteria andReadyAmountIsNull() {
            addCriterion("readyAmount is null");
            return (Criteria) this;
        }

        public Criteria andReadyAmountIsNotNull() {
            addCriterion("readyAmount is not null");
            return (Criteria) this;
        }

        public Criteria andReadyAmountEqualTo(String value) {
            addCriterion("readyAmount =", value, "readyAmount");
            return (Criteria) this;
        }

        public Criteria andReadyAmountNotEqualTo(String value) {
            addCriterion("readyAmount <>", value, "readyAmount");
            return (Criteria) this;
        }

        public Criteria andReadyAmountGreaterThan(String value) {
            addCriterion("readyAmount >", value, "readyAmount");
            return (Criteria) this;
        }

        public Criteria andReadyAmountGreaterThanOrEqualTo(String value) {
            addCriterion("readyAmount >=", value, "readyAmount");
            return (Criteria) this;
        }

        public Criteria andReadyAmountLessThan(String value) {
            addCriterion("readyAmount <", value, "readyAmount");
            return (Criteria) this;
        }

        public Criteria andReadyAmountLessThanOrEqualTo(String value) {
            addCriterion("readyAmount <=", value, "readyAmount");
            return (Criteria) this;
        }

        public Criteria andReadyAmountLike(String value) {
            addCriterion("readyAmount like", value, "readyAmount");
            return (Criteria) this;
        }

        public Criteria andReadyAmountNotLike(String value) {
            addCriterion("readyAmount not like", value, "readyAmount");
            return (Criteria) this;
        }

        public Criteria andReadyAmountIn(List<String> values) {
            addCriterion("readyAmount in", values, "readyAmount");
            return (Criteria) this;
        }

        public Criteria andReadyAmountNotIn(List<String> values) {
            addCriterion("readyAmount not in", values, "readyAmount");
            return (Criteria) this;
        }

        public Criteria andReadyAmountBetween(String value1, String value2) {
            addCriterion("readyAmount between", value1, value2, "readyAmount");
            return (Criteria) this;
        }

        public Criteria andReadyAmountNotBetween(String value1, String value2) {
            addCriterion("readyAmount not between", value1, value2, "readyAmount");
            return (Criteria) this;
        }

        public Criteria andUnreadyAmountIsNull() {
            addCriterion("unreadyAmount is null");
            return (Criteria) this;
        }

        public Criteria andUnreadyAmountIsNotNull() {
            addCriterion("unreadyAmount is not null");
            return (Criteria) this;
        }

        public Criteria andUnreadyAmountEqualTo(String value) {
            addCriterion("unreadyAmount =", value, "unreadyAmount");
            return (Criteria) this;
        }

        public Criteria andUnreadyAmountNotEqualTo(String value) {
            addCriterion("unreadyAmount <>", value, "unreadyAmount");
            return (Criteria) this;
        }

        public Criteria andUnreadyAmountGreaterThan(String value) {
            addCriterion("unreadyAmount >", value, "unreadyAmount");
            return (Criteria) this;
        }

        public Criteria andUnreadyAmountGreaterThanOrEqualTo(String value) {
            addCriterion("unreadyAmount >=", value, "unreadyAmount");
            return (Criteria) this;
        }

        public Criteria andUnreadyAmountLessThan(String value) {
            addCriterion("unreadyAmount <", value, "unreadyAmount");
            return (Criteria) this;
        }

        public Criteria andUnreadyAmountLessThanOrEqualTo(String value) {
            addCriterion("unreadyAmount <=", value, "unreadyAmount");
            return (Criteria) this;
        }

        public Criteria andUnreadyAmountLike(String value) {
            addCriterion("unreadyAmount like", value, "unreadyAmount");
            return (Criteria) this;
        }

        public Criteria andUnreadyAmountNotLike(String value) {
            addCriterion("unreadyAmount not like", value, "unreadyAmount");
            return (Criteria) this;
        }

        public Criteria andUnreadyAmountIn(List<String> values) {
            addCriterion("unreadyAmount in", values, "unreadyAmount");
            return (Criteria) this;
        }

        public Criteria andUnreadyAmountNotIn(List<String> values) {
            addCriterion("unreadyAmount not in", values, "unreadyAmount");
            return (Criteria) this;
        }

        public Criteria andUnreadyAmountBetween(String value1, String value2) {
            addCriterion("unreadyAmount between", value1, value2, "unreadyAmount");
            return (Criteria) this;
        }

        public Criteria andUnreadyAmountNotBetween(String value1, String value2) {
            addCriterion("unreadyAmount not between", value1, value2, "unreadyAmount");
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