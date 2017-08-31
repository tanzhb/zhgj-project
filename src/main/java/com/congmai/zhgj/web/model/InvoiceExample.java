package com.congmai.zhgj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class InvoiceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InvoiceExample() {
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

        public Criteria andInvoiceNumIsNull() {
            addCriterion("invoiceNum is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumIsNotNull() {
            addCriterion("invoiceNum is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumEqualTo(String value) {
            addCriterion("invoiceNum =", value, "invoiceNum");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumNotEqualTo(String value) {
            addCriterion("invoiceNum <>", value, "invoiceNum");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumGreaterThan(String value) {
            addCriterion("invoiceNum >", value, "invoiceNum");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumGreaterThanOrEqualTo(String value) {
            addCriterion("invoiceNum >=", value, "invoiceNum");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumLessThan(String value) {
            addCriterion("invoiceNum <", value, "invoiceNum");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumLessThanOrEqualTo(String value) {
            addCriterion("invoiceNum <=", value, "invoiceNum");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumLike(String value) {
            addCriterion("invoiceNum like", value, "invoiceNum");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumNotLike(String value) {
            addCriterion("invoiceNum not like", value, "invoiceNum");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumIn(List<String> values) {
            addCriterion("invoiceNum in", values, "invoiceNum");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumNotIn(List<String> values) {
            addCriterion("invoiceNum not in", values, "invoiceNum");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumBetween(String value1, String value2) {
            addCriterion("invoiceNum between", value1, value2, "invoiceNum");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumNotBetween(String value1, String value2) {
            addCriterion("invoiceNum not between", value1, value2, "invoiceNum");
            return (Criteria) this;
        }

        public Criteria andBillingPartyIsNull() {
            addCriterion("billingParty is null");
            return (Criteria) this;
        }

        public Criteria andBillingPartyIsNotNull() {
            addCriterion("billingParty is not null");
            return (Criteria) this;
        }

        public Criteria andBillingPartyEqualTo(String value) {
            addCriterion("billingParty =", value, "billingParty");
            return (Criteria) this;
        }

        public Criteria andBillingPartyNotEqualTo(String value) {
            addCriterion("billingParty <>", value, "billingParty");
            return (Criteria) this;
        }

        public Criteria andBillingPartyGreaterThan(String value) {
            addCriterion("billingParty >", value, "billingParty");
            return (Criteria) this;
        }

        public Criteria andBillingPartyGreaterThanOrEqualTo(String value) {
            addCriterion("billingParty >=", value, "billingParty");
            return (Criteria) this;
        }

        public Criteria andBillingPartyLessThan(String value) {
            addCriterion("billingParty <", value, "billingParty");
            return (Criteria) this;
        }

        public Criteria andBillingPartyLessThanOrEqualTo(String value) {
            addCriterion("billingParty <=", value, "billingParty");
            return (Criteria) this;
        }

        public Criteria andBillingPartyLike(String value) {
            addCriterion("billingParty like", value, "billingParty");
            return (Criteria) this;
        }

        public Criteria andBillingPartyNotLike(String value) {
            addCriterion("billingParty not like", value, "billingParty");
            return (Criteria) this;
        }

        public Criteria andBillingPartyIn(List<String> values) {
            addCriterion("billingParty in", values, "billingParty");
            return (Criteria) this;
        }

        public Criteria andBillingPartyNotIn(List<String> values) {
            addCriterion("billingParty not in", values, "billingParty");
            return (Criteria) this;
        }

        public Criteria andBillingPartyBetween(String value1, String value2) {
            addCriterion("billingParty between", value1, value2, "billingParty");
            return (Criteria) this;
        }

        public Criteria andBillingPartyNotBetween(String value1, String value2) {
            addCriterion("billingParty not between", value1, value2, "billingParty");
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

        public Criteria andPaymentSerialIsNull() {
            addCriterion("paymentSerial is null");
            return (Criteria) this;
        }

        public Criteria andPaymentSerialIsNotNull() {
            addCriterion("paymentSerial is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentSerialEqualTo(String value) {
            addCriterion("paymentSerial =", value, "paymentSerial");
            return (Criteria) this;
        }

        public Criteria andPaymentSerialNotEqualTo(String value) {
            addCriterion("paymentSerial <>", value, "paymentSerial");
            return (Criteria) this;
        }

        public Criteria andPaymentSerialGreaterThan(String value) {
            addCriterion("paymentSerial >", value, "paymentSerial");
            return (Criteria) this;
        }

        public Criteria andPaymentSerialGreaterThanOrEqualTo(String value) {
            addCriterion("paymentSerial >=", value, "paymentSerial");
            return (Criteria) this;
        }

        public Criteria andPaymentSerialLessThan(String value) {
            addCriterion("paymentSerial <", value, "paymentSerial");
            return (Criteria) this;
        }

        public Criteria andPaymentSerialLessThanOrEqualTo(String value) {
            addCriterion("paymentSerial <=", value, "paymentSerial");
            return (Criteria) this;
        }

        public Criteria andPaymentSerialLike(String value) {
            addCriterion("paymentSerial like", value, "paymentSerial");
            return (Criteria) this;
        }

        public Criteria andPaymentSerialNotLike(String value) {
            addCriterion("paymentSerial not like", value, "paymentSerial");
            return (Criteria) this;
        }

        public Criteria andPaymentSerialIn(List<String> values) {
            addCriterion("paymentSerial in", values, "paymentSerial");
            return (Criteria) this;
        }

        public Criteria andPaymentSerialNotIn(List<String> values) {
            addCriterion("paymentSerial not in", values, "paymentSerial");
            return (Criteria) this;
        }

        public Criteria andPaymentSerialBetween(String value1, String value2) {
            addCriterion("paymentSerial between", value1, value2, "paymentSerial");
            return (Criteria) this;
        }

        public Criteria andPaymentSerialNotBetween(String value1, String value2) {
            addCriterion("paymentSerial not between", value1, value2, "paymentSerial");
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

        public Criteria andPaymentStatusIsNull() {
            addCriterion("paymentStatus is null");
            return (Criteria) this;
        }

        public Criteria andPaymentStatusIsNotNull() {
            addCriterion("paymentStatus is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentStatusEqualTo(String value) {
            addCriterion("paymentStatus =", value, "paymentStatus");
            return (Criteria) this;
        }

        public Criteria andPaymentStatusNotEqualTo(String value) {
            addCriterion("paymentStatus <>", value, "paymentStatus");
            return (Criteria) this;
        }

        public Criteria andPaymentStatusGreaterThan(String value) {
            addCriterion("paymentStatus >", value, "paymentStatus");
            return (Criteria) this;
        }

        public Criteria andPaymentStatusGreaterThanOrEqualTo(String value) {
            addCriterion("paymentStatus >=", value, "paymentStatus");
            return (Criteria) this;
        }

        public Criteria andPaymentStatusLessThan(String value) {
            addCriterion("paymentStatus <", value, "paymentStatus");
            return (Criteria) this;
        }

        public Criteria andPaymentStatusLessThanOrEqualTo(String value) {
            addCriterion("paymentStatus <=", value, "paymentStatus");
            return (Criteria) this;
        }

        public Criteria andPaymentStatusLike(String value) {
            addCriterion("paymentStatus like", value, "paymentStatus");
            return (Criteria) this;
        }

        public Criteria andPaymentStatusNotLike(String value) {
            addCriterion("paymentStatus not like", value, "paymentStatus");
            return (Criteria) this;
        }

        public Criteria andPaymentStatusIn(List<String> values) {
            addCriterion("paymentStatus in", values, "paymentStatus");
            return (Criteria) this;
        }

        public Criteria andPaymentStatusNotIn(List<String> values) {
            addCriterion("paymentStatus not in", values, "paymentStatus");
            return (Criteria) this;
        }

        public Criteria andPaymentStatusBetween(String value1, String value2) {
            addCriterion("paymentStatus between", value1, value2, "paymentStatus");
            return (Criteria) this;
        }

        public Criteria andPaymentStatusNotBetween(String value1, String value2) {
            addCriterion("paymentStatus not between", value1, value2, "paymentStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeIsNull() {
            addCriterion("invoiceType is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeIsNotNull() {
            addCriterion("invoiceType is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeEqualTo(String value) {
            addCriterion("invoiceType =", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotEqualTo(String value) {
            addCriterion("invoiceType <>", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeGreaterThan(String value) {
            addCriterion("invoiceType >", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("invoiceType >=", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeLessThan(String value) {
            addCriterion("invoiceType <", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeLessThanOrEqualTo(String value) {
            addCriterion("invoiceType <=", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeLike(String value) {
            addCriterion("invoiceType like", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotLike(String value) {
            addCriterion("invoiceType not like", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeIn(List<String> values) {
            addCriterion("invoiceType in", values, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotIn(List<String> values) {
            addCriterion("invoiceType not in", values, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeBetween(String value1, String value2) {
            addCriterion("invoiceType between", value1, value2, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotBetween(String value1, String value2) {
            addCriterion("invoiceType not between", value1, value2, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountIsNull() {
            addCriterion("invoiceAmount is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountIsNotNull() {
            addCriterion("invoiceAmount is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountEqualTo(String value) {
            addCriterion("invoiceAmount =", value, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountNotEqualTo(String value) {
            addCriterion("invoiceAmount <>", value, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountGreaterThan(String value) {
            addCriterion("invoiceAmount >", value, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountGreaterThanOrEqualTo(String value) {
            addCriterion("invoiceAmount >=", value, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountLessThan(String value) {
            addCriterion("invoiceAmount <", value, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountLessThanOrEqualTo(String value) {
            addCriterion("invoiceAmount <=", value, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountLike(String value) {
            addCriterion("invoiceAmount like", value, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountNotLike(String value) {
            addCriterion("invoiceAmount not like", value, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountIn(List<String> values) {
            addCriterion("invoiceAmount in", values, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountNotIn(List<String> values) {
            addCriterion("invoiceAmount not in", values, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountBetween(String value1, String value2) {
            addCriterion("invoiceAmount between", value1, value2, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountNotBetween(String value1, String value2) {
            addCriterion("invoiceAmount not between", value1, value2, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andBillingDateIsNull() {
            addCriterion("billingDate is null");
            return (Criteria) this;
        }

        public Criteria andBillingDateIsNotNull() {
            addCriterion("billingDate is not null");
            return (Criteria) this;
        }

        public Criteria andBillingDateEqualTo(Date value) {
            addCriterionForJDBCDate("billingDate =", value, "billingDate");
            return (Criteria) this;
        }

        public Criteria andBillingDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("billingDate <>", value, "billingDate");
            return (Criteria) this;
        }

        public Criteria andBillingDateGreaterThan(Date value) {
            addCriterionForJDBCDate("billingDate >", value, "billingDate");
            return (Criteria) this;
        }

        public Criteria andBillingDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("billingDate >=", value, "billingDate");
            return (Criteria) this;
        }

        public Criteria andBillingDateLessThan(Date value) {
            addCriterionForJDBCDate("billingDate <", value, "billingDate");
            return (Criteria) this;
        }

        public Criteria andBillingDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("billingDate <=", value, "billingDate");
            return (Criteria) this;
        }

        public Criteria andBillingDateIn(List<Date> values) {
            addCriterionForJDBCDate("billingDate in", values, "billingDate");
            return (Criteria) this;
        }

        public Criteria andBillingDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("billingDate not in", values, "billingDate");
            return (Criteria) this;
        }

        public Criteria andBillingDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("billingDate between", value1, value2, "billingDate");
            return (Criteria) this;
        }

        public Criteria andBillingDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("billingDate not between", value1, value2, "billingDate");
            return (Criteria) this;
        }

        public Criteria andInvoiceNOIsNull() {
            addCriterion("invoiceNO is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNOIsNotNull() {
            addCriterion("invoiceNO is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNOEqualTo(String value) {
            addCriterion("invoiceNO =", value, "invoiceNO");
            return (Criteria) this;
        }

        public Criteria andInvoiceNONotEqualTo(String value) {
            addCriterion("invoiceNO <>", value, "invoiceNO");
            return (Criteria) this;
        }

        public Criteria andInvoiceNOGreaterThan(String value) {
            addCriterion("invoiceNO >", value, "invoiceNO");
            return (Criteria) this;
        }

        public Criteria andInvoiceNOGreaterThanOrEqualTo(String value) {
            addCriterion("invoiceNO >=", value, "invoiceNO");
            return (Criteria) this;
        }

        public Criteria andInvoiceNOLessThan(String value) {
            addCriterion("invoiceNO <", value, "invoiceNO");
            return (Criteria) this;
        }

        public Criteria andInvoiceNOLessThanOrEqualTo(String value) {
            addCriterion("invoiceNO <=", value, "invoiceNO");
            return (Criteria) this;
        }

        public Criteria andInvoiceNOLike(String value) {
            addCriterion("invoiceNO like", value, "invoiceNO");
            return (Criteria) this;
        }

        public Criteria andInvoiceNONotLike(String value) {
            addCriterion("invoiceNO not like", value, "invoiceNO");
            return (Criteria) this;
        }

        public Criteria andInvoiceNOIn(List<String> values) {
            addCriterion("invoiceNO in", values, "invoiceNO");
            return (Criteria) this;
        }

        public Criteria andInvoiceNONotIn(List<String> values) {
            addCriterion("invoiceNO not in", values, "invoiceNO");
            return (Criteria) this;
        }

        public Criteria andInvoiceNOBetween(String value1, String value2) {
            addCriterion("invoiceNO between", value1, value2, "invoiceNO");
            return (Criteria) this;
        }

        public Criteria andInvoiceNONotBetween(String value1, String value2) {
            addCriterion("invoiceNO not between", value1, value2, "invoiceNO");
            return (Criteria) this;
        }

        public Criteria andSubmitterIsNull() {
            addCriterion("submitter is null");
            return (Criteria) this;
        }

        public Criteria andSubmitterIsNotNull() {
            addCriterion("submitter is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitterEqualTo(String value) {
            addCriterion("submitter =", value, "submitter");
            return (Criteria) this;
        }

        public Criteria andSubmitterNotEqualTo(String value) {
            addCriterion("submitter <>", value, "submitter");
            return (Criteria) this;
        }

        public Criteria andSubmitterGreaterThan(String value) {
            addCriterion("submitter >", value, "submitter");
            return (Criteria) this;
        }

        public Criteria andSubmitterGreaterThanOrEqualTo(String value) {
            addCriterion("submitter >=", value, "submitter");
            return (Criteria) this;
        }

        public Criteria andSubmitterLessThan(String value) {
            addCriterion("submitter <", value, "submitter");
            return (Criteria) this;
        }

        public Criteria andSubmitterLessThanOrEqualTo(String value) {
            addCriterion("submitter <=", value, "submitter");
            return (Criteria) this;
        }

        public Criteria andSubmitterLike(String value) {
            addCriterion("submitter like", value, "submitter");
            return (Criteria) this;
        }

        public Criteria andSubmitterNotLike(String value) {
            addCriterion("submitter not like", value, "submitter");
            return (Criteria) this;
        }

        public Criteria andSubmitterIn(List<String> values) {
            addCriterion("submitter in", values, "submitter");
            return (Criteria) this;
        }

        public Criteria andSubmitterNotIn(List<String> values) {
            addCriterion("submitter not in", values, "submitter");
            return (Criteria) this;
        }

        public Criteria andSubmitterBetween(String value1, String value2) {
            addCriterion("submitter between", value1, value2, "submitter");
            return (Criteria) this;
        }

        public Criteria andSubmitterNotBetween(String value1, String value2) {
            addCriterion("submitter not between", value1, value2, "submitter");
            return (Criteria) this;
        }

        public Criteria andSubmitDateIsNull() {
            addCriterion("submitDate is null");
            return (Criteria) this;
        }

        public Criteria andSubmitDateIsNotNull() {
            addCriterion("submitDate is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitDateEqualTo(Date value) {
            addCriterionForJDBCDate("submitDate =", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("submitDate <>", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateGreaterThan(Date value) {
            addCriterionForJDBCDate("submitDate >", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("submitDate >=", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateLessThan(Date value) {
            addCriterionForJDBCDate("submitDate <", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("submitDate <=", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateIn(List<Date> values) {
            addCriterionForJDBCDate("submitDate in", values, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("submitDate not in", values, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("submitDate between", value1, value2, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("submitDate not between", value1, value2, "submitDate");
            return (Criteria) this;
        }

        public Criteria andBillingRemarkIsNull() {
            addCriterion("billingRemark is null");
            return (Criteria) this;
        }

        public Criteria andBillingRemarkIsNotNull() {
            addCriterion("billingRemark is not null");
            return (Criteria) this;
        }

        public Criteria andBillingRemarkEqualTo(String value) {
            addCriterion("billingRemark =", value, "billingRemark");
            return (Criteria) this;
        }

        public Criteria andBillingRemarkNotEqualTo(String value) {
            addCriterion("billingRemark <>", value, "billingRemark");
            return (Criteria) this;
        }

        public Criteria andBillingRemarkGreaterThan(String value) {
            addCriterion("billingRemark >", value, "billingRemark");
            return (Criteria) this;
        }

        public Criteria andBillingRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("billingRemark >=", value, "billingRemark");
            return (Criteria) this;
        }

        public Criteria andBillingRemarkLessThan(String value) {
            addCriterion("billingRemark <", value, "billingRemark");
            return (Criteria) this;
        }

        public Criteria andBillingRemarkLessThanOrEqualTo(String value) {
            addCriterion("billingRemark <=", value, "billingRemark");
            return (Criteria) this;
        }

        public Criteria andBillingRemarkLike(String value) {
            addCriterion("billingRemark like", value, "billingRemark");
            return (Criteria) this;
        }

        public Criteria andBillingRemarkNotLike(String value) {
            addCriterion("billingRemark not like", value, "billingRemark");
            return (Criteria) this;
        }

        public Criteria andBillingRemarkIn(List<String> values) {
            addCriterion("billingRemark in", values, "billingRemark");
            return (Criteria) this;
        }

        public Criteria andBillingRemarkNotIn(List<String> values) {
            addCriterion("billingRemark not in", values, "billingRemark");
            return (Criteria) this;
        }

        public Criteria andBillingRemarkBetween(String value1, String value2) {
            addCriterion("billingRemark between", value1, value2, "billingRemark");
            return (Criteria) this;
        }

        public Criteria andBillingRemarkNotBetween(String value1, String value2) {
            addCriterion("billingRemark not between", value1, value2, "billingRemark");
            return (Criteria) this;
        }

        public Criteria andReceiptDateIsNull() {
            addCriterion("receiptDate is null");
            return (Criteria) this;
        }

        public Criteria andReceiptDateIsNotNull() {
            addCriterion("receiptDate is not null");
            return (Criteria) this;
        }

        public Criteria andReceiptDateEqualTo(Date value) {
            addCriterionForJDBCDate("receiptDate =", value, "receiptDate");
            return (Criteria) this;
        }

        public Criteria andReceiptDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("receiptDate <>", value, "receiptDate");
            return (Criteria) this;
        }

        public Criteria andReceiptDateGreaterThan(Date value) {
            addCriterionForJDBCDate("receiptDate >", value, "receiptDate");
            return (Criteria) this;
        }

        public Criteria andReceiptDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("receiptDate >=", value, "receiptDate");
            return (Criteria) this;
        }

        public Criteria andReceiptDateLessThan(Date value) {
            addCriterionForJDBCDate("receiptDate <", value, "receiptDate");
            return (Criteria) this;
        }

        public Criteria andReceiptDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("receiptDate <=", value, "receiptDate");
            return (Criteria) this;
        }

        public Criteria andReceiptDateIn(List<Date> values) {
            addCriterionForJDBCDate("receiptDate in", values, "receiptDate");
            return (Criteria) this;
        }

        public Criteria andReceiptDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("receiptDate not in", values, "receiptDate");
            return (Criteria) this;
        }

        public Criteria andReceiptDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("receiptDate between", value1, value2, "receiptDate");
            return (Criteria) this;
        }

        public Criteria andReceiptDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("receiptDate not between", value1, value2, "receiptDate");
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

        public Criteria andReceiptRemarkIsNull() {
            addCriterion("receiptRemark is null");
            return (Criteria) this;
        }

        public Criteria andReceiptRemarkIsNotNull() {
            addCriterion("receiptRemark is not null");
            return (Criteria) this;
        }

        public Criteria andReceiptRemarkEqualTo(String value) {
            addCriterion("receiptRemark =", value, "receiptRemark");
            return (Criteria) this;
        }

        public Criteria andReceiptRemarkNotEqualTo(String value) {
            addCriterion("receiptRemark <>", value, "receiptRemark");
            return (Criteria) this;
        }

        public Criteria andReceiptRemarkGreaterThan(String value) {
            addCriterion("receiptRemark >", value, "receiptRemark");
            return (Criteria) this;
        }

        public Criteria andReceiptRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("receiptRemark >=", value, "receiptRemark");
            return (Criteria) this;
        }

        public Criteria andReceiptRemarkLessThan(String value) {
            addCriterion("receiptRemark <", value, "receiptRemark");
            return (Criteria) this;
        }

        public Criteria andReceiptRemarkLessThanOrEqualTo(String value) {
            addCriterion("receiptRemark <=", value, "receiptRemark");
            return (Criteria) this;
        }

        public Criteria andReceiptRemarkLike(String value) {
            addCriterion("receiptRemark like", value, "receiptRemark");
            return (Criteria) this;
        }

        public Criteria andReceiptRemarkNotLike(String value) {
            addCriterion("receiptRemark not like", value, "receiptRemark");
            return (Criteria) this;
        }

        public Criteria andReceiptRemarkIn(List<String> values) {
            addCriterion("receiptRemark in", values, "receiptRemark");
            return (Criteria) this;
        }

        public Criteria andReceiptRemarkNotIn(List<String> values) {
            addCriterion("receiptRemark not in", values, "receiptRemark");
            return (Criteria) this;
        }

        public Criteria andReceiptRemarkBetween(String value1, String value2) {
            addCriterion("receiptRemark between", value1, value2, "receiptRemark");
            return (Criteria) this;
        }

        public Criteria andReceiptRemarkNotBetween(String value1, String value2) {
            addCriterion("receiptRemark not between", value1, value2, "receiptRemark");
            return (Criteria) this;
        }

        public Criteria andInvoiceVoucherIsNull() {
            addCriterion("invoiceVoucher is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceVoucherIsNotNull() {
            addCriterion("invoiceVoucher is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceVoucherEqualTo(String value) {
            addCriterion("invoiceVoucher =", value, "invoiceVoucher");
            return (Criteria) this;
        }

        public Criteria andInvoiceVoucherNotEqualTo(String value) {
            addCriterion("invoiceVoucher <>", value, "invoiceVoucher");
            return (Criteria) this;
        }

        public Criteria andInvoiceVoucherGreaterThan(String value) {
            addCriterion("invoiceVoucher >", value, "invoiceVoucher");
            return (Criteria) this;
        }

        public Criteria andInvoiceVoucherGreaterThanOrEqualTo(String value) {
            addCriterion("invoiceVoucher >=", value, "invoiceVoucher");
            return (Criteria) this;
        }

        public Criteria andInvoiceVoucherLessThan(String value) {
            addCriterion("invoiceVoucher <", value, "invoiceVoucher");
            return (Criteria) this;
        }

        public Criteria andInvoiceVoucherLessThanOrEqualTo(String value) {
            addCriterion("invoiceVoucher <=", value, "invoiceVoucher");
            return (Criteria) this;
        }

        public Criteria andInvoiceVoucherLike(String value) {
            addCriterion("invoiceVoucher like", value, "invoiceVoucher");
            return (Criteria) this;
        }

        public Criteria andInvoiceVoucherNotLike(String value) {
            addCriterion("invoiceVoucher not like", value, "invoiceVoucher");
            return (Criteria) this;
        }

        public Criteria andInvoiceVoucherIn(List<String> values) {
            addCriterion("invoiceVoucher in", values, "invoiceVoucher");
            return (Criteria) this;
        }

        public Criteria andInvoiceVoucherNotIn(List<String> values) {
            addCriterion("invoiceVoucher not in", values, "invoiceVoucher");
            return (Criteria) this;
        }

        public Criteria andInvoiceVoucherBetween(String value1, String value2) {
            addCriterion("invoiceVoucher between", value1, value2, "invoiceVoucher");
            return (Criteria) this;
        }

        public Criteria andInvoiceVoucherNotBetween(String value1, String value2) {
            addCriterion("invoiceVoucher not between", value1, value2, "invoiceVoucher");
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