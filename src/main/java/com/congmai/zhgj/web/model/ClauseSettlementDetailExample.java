package com.congmai.zhgj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClauseSettlementDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ClauseSettlementDetailExample() {
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

        public Criteria andDeliveryNodeIsNull() {
            addCriterion("deliveryNode is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryNodeIsNotNull() {
            addCriterion("deliveryNode is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryNodeEqualTo(String value) {
            addCriterion("deliveryNode =", value, "deliveryNode");
            return (Criteria) this;
        }

        public Criteria andDeliveryNodeNotEqualTo(String value) {
            addCriterion("deliveryNode <>", value, "deliveryNode");
            return (Criteria) this;
        }

        public Criteria andDeliveryNodeGreaterThan(String value) {
            addCriterion("deliveryNode >", value, "deliveryNode");
            return (Criteria) this;
        }

        public Criteria andDeliveryNodeGreaterThanOrEqualTo(String value) {
            addCriterion("deliveryNode >=", value, "deliveryNode");
            return (Criteria) this;
        }

        public Criteria andDeliveryNodeLessThan(String value) {
            addCriterion("deliveryNode <", value, "deliveryNode");
            return (Criteria) this;
        }

        public Criteria andDeliveryNodeLessThanOrEqualTo(String value) {
            addCriterion("deliveryNode <=", value, "deliveryNode");
            return (Criteria) this;
        }

        public Criteria andDeliveryNodeLike(String value) {
            addCriterion("deliveryNode like", value, "deliveryNode");
            return (Criteria) this;
        }

        public Criteria andDeliveryNodeNotLike(String value) {
            addCriterion("deliveryNode not like", value, "deliveryNode");
            return (Criteria) this;
        }

        public Criteria andDeliveryNodeIn(List<String> values) {
            addCriterion("deliveryNode in", values, "deliveryNode");
            return (Criteria) this;
        }

        public Criteria andDeliveryNodeNotIn(List<String> values) {
            addCriterion("deliveryNode not in", values, "deliveryNode");
            return (Criteria) this;
        }

        public Criteria andDeliveryNodeBetween(String value1, String value2) {
            addCriterion("deliveryNode between", value1, value2, "deliveryNode");
            return (Criteria) this;
        }

        public Criteria andDeliveryNodeNotBetween(String value1, String value2) {
            addCriterion("deliveryNode not between", value1, value2, "deliveryNode");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodIsNull() {
            addCriterion("accountPeriod is null");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodIsNotNull() {
            addCriterion("accountPeriod is not null");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodEqualTo(String value) {
            addCriterion("accountPeriod =", value, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodNotEqualTo(String value) {
            addCriterion("accountPeriod <>", value, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodGreaterThan(String value) {
            addCriterion("accountPeriod >", value, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodGreaterThanOrEqualTo(String value) {
            addCriterion("accountPeriod >=", value, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodLessThan(String value) {
            addCriterion("accountPeriod <", value, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodLessThanOrEqualTo(String value) {
            addCriterion("accountPeriod <=", value, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodLike(String value) {
            addCriterion("accountPeriod like", value, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodNotLike(String value) {
            addCriterion("accountPeriod not like", value, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodIn(List<String> values) {
            addCriterion("accountPeriod in", values, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodNotIn(List<String> values) {
            addCriterion("accountPeriod not in", values, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodBetween(String value1, String value2) {
            addCriterion("accountPeriod between", value1, value2, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodNotBetween(String value1, String value2) {
            addCriterion("accountPeriod not between", value1, value2, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andDeliveryRateIsNull() {
            addCriterion("deliveryRate is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryRateIsNotNull() {
            addCriterion("deliveryRate is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryRateEqualTo(String value) {
            addCriterion("deliveryRate =", value, "deliveryRate");
            return (Criteria) this;
        }

        public Criteria andDeliveryRateNotEqualTo(String value) {
            addCriterion("deliveryRate <>", value, "deliveryRate");
            return (Criteria) this;
        }

        public Criteria andDeliveryRateGreaterThan(String value) {
            addCriterion("deliveryRate >", value, "deliveryRate");
            return (Criteria) this;
        }

        public Criteria andDeliveryRateGreaterThanOrEqualTo(String value) {
            addCriterion("deliveryRate >=", value, "deliveryRate");
            return (Criteria) this;
        }

        public Criteria andDeliveryRateLessThan(String value) {
            addCriterion("deliveryRate <", value, "deliveryRate");
            return (Criteria) this;
        }

        public Criteria andDeliveryRateLessThanOrEqualTo(String value) {
            addCriterion("deliveryRate <=", value, "deliveryRate");
            return (Criteria) this;
        }

        public Criteria andDeliveryRateLike(String value) {
            addCriterion("deliveryRate like", value, "deliveryRate");
            return (Criteria) this;
        }

        public Criteria andDeliveryRateNotLike(String value) {
            addCriterion("deliveryRate not like", value, "deliveryRate");
            return (Criteria) this;
        }

        public Criteria andDeliveryRateIn(List<String> values) {
            addCriterion("deliveryRate in", values, "deliveryRate");
            return (Criteria) this;
        }

        public Criteria andDeliveryRateNotIn(List<String> values) {
            addCriterion("deliveryRate not in", values, "deliveryRate");
            return (Criteria) this;
        }

        public Criteria andDeliveryRateBetween(String value1, String value2) {
            addCriterion("deliveryRate between", value1, value2, "deliveryRate");
            return (Criteria) this;
        }

        public Criteria andDeliveryRateNotBetween(String value1, String value2) {
            addCriterion("deliveryRate not between", value1, value2, "deliveryRate");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmountIsNull() {
            addCriterion("deliveryAmount is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmountIsNotNull() {
            addCriterion("deliveryAmount is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmountEqualTo(String value) {
            addCriterion("deliveryAmount =", value, "deliveryAmount");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmountNotEqualTo(String value) {
            addCriterion("deliveryAmount <>", value, "deliveryAmount");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmountGreaterThan(String value) {
            addCriterion("deliveryAmount >", value, "deliveryAmount");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmountGreaterThanOrEqualTo(String value) {
            addCriterion("deliveryAmount >=", value, "deliveryAmount");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmountLessThan(String value) {
            addCriterion("deliveryAmount <", value, "deliveryAmount");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmountLessThanOrEqualTo(String value) {
            addCriterion("deliveryAmount <=", value, "deliveryAmount");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmountLike(String value) {
            addCriterion("deliveryAmount like", value, "deliveryAmount");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmountNotLike(String value) {
            addCriterion("deliveryAmount not like", value, "deliveryAmount");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmountIn(List<String> values) {
            addCriterion("deliveryAmount in", values, "deliveryAmount");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmountNotIn(List<String> values) {
            addCriterion("deliveryAmount not in", values, "deliveryAmount");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmountBetween(String value1, String value2) {
            addCriterion("deliveryAmount between", value1, value2, "deliveryAmount");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmountNotBetween(String value1, String value2) {
            addCriterion("deliveryAmount not between", value1, value2, "deliveryAmount");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodIsNull() {
            addCriterion("paymentMethod is null");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodIsNotNull() {
            addCriterion("paymentMethod is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodEqualTo(String value) {
            addCriterion("paymentMethod =", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodNotEqualTo(String value) {
            addCriterion("paymentMethod <>", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodGreaterThan(String value) {
            addCriterion("paymentMethod >", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodGreaterThanOrEqualTo(String value) {
            addCriterion("paymentMethod >=", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodLessThan(String value) {
            addCriterion("paymentMethod <", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodLessThanOrEqualTo(String value) {
            addCriterion("paymentMethod <=", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodLike(String value) {
            addCriterion("paymentMethod like", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodNotLike(String value) {
            addCriterion("paymentMethod not like", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodIn(List<String> values) {
            addCriterion("paymentMethod in", values, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodNotIn(List<String> values) {
            addCriterion("paymentMethod not in", values, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodBetween(String value1, String value2) {
            addCriterion("paymentMethod between", value1, value2, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodNotBetween(String value1, String value2) {
            addCriterion("paymentMethod not between", value1, value2, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andBillingMethodIsNull() {
            addCriterion("billingMethod is null");
            return (Criteria) this;
        }

        public Criteria andBillingMethodIsNotNull() {
            addCriterion("billingMethod is not null");
            return (Criteria) this;
        }

        public Criteria andBillingMethodEqualTo(String value) {
            addCriterion("billingMethod =", value, "billingMethod");
            return (Criteria) this;
        }

        public Criteria andBillingMethodNotEqualTo(String value) {
            addCriterion("billingMethod <>", value, "billingMethod");
            return (Criteria) this;
        }

        public Criteria andBillingMethodGreaterThan(String value) {
            addCriterion("billingMethod >", value, "billingMethod");
            return (Criteria) this;
        }

        public Criteria andBillingMethodGreaterThanOrEqualTo(String value) {
            addCriterion("billingMethod >=", value, "billingMethod");
            return (Criteria) this;
        }

        public Criteria andBillingMethodLessThan(String value) {
            addCriterion("billingMethod <", value, "billingMethod");
            return (Criteria) this;
        }

        public Criteria andBillingMethodLessThanOrEqualTo(String value) {
            addCriterion("billingMethod <=", value, "billingMethod");
            return (Criteria) this;
        }

        public Criteria andBillingMethodLike(String value) {
            addCriterion("billingMethod like", value, "billingMethod");
            return (Criteria) this;
        }

        public Criteria andBillingMethodNotLike(String value) {
            addCriterion("billingMethod not like", value, "billingMethod");
            return (Criteria) this;
        }

        public Criteria andBillingMethodIn(List<String> values) {
            addCriterion("billingMethod in", values, "billingMethod");
            return (Criteria) this;
        }

        public Criteria andBillingMethodNotIn(List<String> values) {
            addCriterion("billingMethod not in", values, "billingMethod");
            return (Criteria) this;
        }

        public Criteria andBillingMethodBetween(String value1, String value2) {
            addCriterion("billingMethod between", value1, value2, "billingMethod");
            return (Criteria) this;
        }

        public Criteria andBillingMethodNotBetween(String value1, String value2) {
            addCriterion("billingMethod not between", value1, value2, "billingMethod");
            return (Criteria) this;
        }

        public Criteria andBillingAmountIsNull() {
            addCriterion("billingAmount is null");
            return (Criteria) this;
        }

        public Criteria andBillingAmountIsNotNull() {
            addCriterion("billingAmount is not null");
            return (Criteria) this;
        }

        public Criteria andBillingAmountEqualTo(String value) {
            addCriterion("billingAmount =", value, "billingAmount");
            return (Criteria) this;
        }

        public Criteria andBillingAmountNotEqualTo(String value) {
            addCriterion("billingAmount <>", value, "billingAmount");
            return (Criteria) this;
        }

        public Criteria andBillingAmountGreaterThan(String value) {
            addCriterion("billingAmount >", value, "billingAmount");
            return (Criteria) this;
        }

        public Criteria andBillingAmountGreaterThanOrEqualTo(String value) {
            addCriterion("billingAmount >=", value, "billingAmount");
            return (Criteria) this;
        }

        public Criteria andBillingAmountLessThan(String value) {
            addCriterion("billingAmount <", value, "billingAmount");
            return (Criteria) this;
        }

        public Criteria andBillingAmountLessThanOrEqualTo(String value) {
            addCriterion("billingAmount <=", value, "billingAmount");
            return (Criteria) this;
        }

        public Criteria andBillingAmountLike(String value) {
            addCriterion("billingAmount like", value, "billingAmount");
            return (Criteria) this;
        }

        public Criteria andBillingAmountNotLike(String value) {
            addCriterion("billingAmount not like", value, "billingAmount");
            return (Criteria) this;
        }

        public Criteria andBillingAmountIn(List<String> values) {
            addCriterion("billingAmount in", values, "billingAmount");
            return (Criteria) this;
        }

        public Criteria andBillingAmountNotIn(List<String> values) {
            addCriterion("billingAmount not in", values, "billingAmount");
            return (Criteria) this;
        }

        public Criteria andBillingAmountBetween(String value1, String value2) {
            addCriterion("billingAmount between", value1, value2, "billingAmount");
            return (Criteria) this;
        }

        public Criteria andBillingAmountNotBetween(String value1, String value2) {
            addCriterion("billingAmount not between", value1, value2, "billingAmount");
            return (Criteria) this;
        }

        public Criteria andUnbilledAmountIsNull() {
            addCriterion("unbilledAmount is null");
            return (Criteria) this;
        }

        public Criteria andUnbilledAmountIsNotNull() {
            addCriterion("unbilledAmount is not null");
            return (Criteria) this;
        }

        public Criteria andUnbilledAmountEqualTo(String value) {
            addCriterion("unbilledAmount =", value, "unbilledAmount");
            return (Criteria) this;
        }

        public Criteria andUnbilledAmountNotEqualTo(String value) {
            addCriterion("unbilledAmount <>", value, "unbilledAmount");
            return (Criteria) this;
        }

        public Criteria andUnbilledAmountGreaterThan(String value) {
            addCriterion("unbilledAmount >", value, "unbilledAmount");
            return (Criteria) this;
        }

        public Criteria andUnbilledAmountGreaterThanOrEqualTo(String value) {
            addCriterion("unbilledAmount >=", value, "unbilledAmount");
            return (Criteria) this;
        }

        public Criteria andUnbilledAmountLessThan(String value) {
            addCriterion("unbilledAmount <", value, "unbilledAmount");
            return (Criteria) this;
        }

        public Criteria andUnbilledAmountLessThanOrEqualTo(String value) {
            addCriterion("unbilledAmount <=", value, "unbilledAmount");
            return (Criteria) this;
        }

        public Criteria andUnbilledAmountLike(String value) {
            addCriterion("unbilledAmount like", value, "unbilledAmount");
            return (Criteria) this;
        }

        public Criteria andUnbilledAmountNotLike(String value) {
            addCriterion("unbilledAmount not like", value, "unbilledAmount");
            return (Criteria) this;
        }

        public Criteria andUnbilledAmountIn(List<String> values) {
            addCriterion("unbilledAmount in", values, "unbilledAmount");
            return (Criteria) this;
        }

        public Criteria andUnbilledAmountNotIn(List<String> values) {
            addCriterion("unbilledAmount not in", values, "unbilledAmount");
            return (Criteria) this;
        }

        public Criteria andUnbilledAmountBetween(String value1, String value2) {
            addCriterion("unbilledAmount between", value1, value2, "unbilledAmount");
            return (Criteria) this;
        }

        public Criteria andUnbilledAmountNotBetween(String value1, String value2) {
            addCriterion("unbilledAmount not between", value1, value2, "unbilledAmount");
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