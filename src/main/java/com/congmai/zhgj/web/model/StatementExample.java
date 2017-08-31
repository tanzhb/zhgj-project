package com.congmai.zhgj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class StatementExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StatementExample() {
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

        public Criteria andStatementNumIsNull() {
            addCriterion("statementNum is null");
            return (Criteria) this;
        }

        public Criteria andStatementNumIsNotNull() {
            addCriterion("statementNum is not null");
            return (Criteria) this;
        }

        public Criteria andStatementNumEqualTo(String value) {
            addCriterion("statementNum =", value, "statementNum");
            return (Criteria) this;
        }

        public Criteria andStatementNumNotEqualTo(String value) {
            addCriterion("statementNum <>", value, "statementNum");
            return (Criteria) this;
        }

        public Criteria andStatementNumGreaterThan(String value) {
            addCriterion("statementNum >", value, "statementNum");
            return (Criteria) this;
        }

        public Criteria andStatementNumGreaterThanOrEqualTo(String value) {
            addCriterion("statementNum >=", value, "statementNum");
            return (Criteria) this;
        }

        public Criteria andStatementNumLessThan(String value) {
            addCriterion("statementNum <", value, "statementNum");
            return (Criteria) this;
        }

        public Criteria andStatementNumLessThanOrEqualTo(String value) {
            addCriterion("statementNum <=", value, "statementNum");
            return (Criteria) this;
        }

        public Criteria andStatementNumLike(String value) {
            addCriterion("statementNum like", value, "statementNum");
            return (Criteria) this;
        }

        public Criteria andStatementNumNotLike(String value) {
            addCriterion("statementNum not like", value, "statementNum");
            return (Criteria) this;
        }

        public Criteria andStatementNumIn(List<String> values) {
            addCriterion("statementNum in", values, "statementNum");
            return (Criteria) this;
        }

        public Criteria andStatementNumNotIn(List<String> values) {
            addCriterion("statementNum not in", values, "statementNum");
            return (Criteria) this;
        }

        public Criteria andStatementNumBetween(String value1, String value2) {
            addCriterion("statementNum between", value1, value2, "statementNum");
            return (Criteria) this;
        }

        public Criteria andStatementNumNotBetween(String value1, String value2) {
            addCriterion("statementNum not between", value1, value2, "statementNum");
            return (Criteria) this;
        }

        public Criteria andStatementDateIsNull() {
            addCriterion("statementDate is null");
            return (Criteria) this;
        }

        public Criteria andStatementDateIsNotNull() {
            addCriterion("statementDate is not null");
            return (Criteria) this;
        }

        public Criteria andStatementDateEqualTo(Date value) {
            addCriterionForJDBCDate("statementDate =", value, "statementDate");
            return (Criteria) this;
        }

        public Criteria andStatementDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("statementDate <>", value, "statementDate");
            return (Criteria) this;
        }

        public Criteria andStatementDateGreaterThan(Date value) {
            addCriterionForJDBCDate("statementDate >", value, "statementDate");
            return (Criteria) this;
        }

        public Criteria andStatementDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("statementDate >=", value, "statementDate");
            return (Criteria) this;
        }

        public Criteria andStatementDateLessThan(Date value) {
            addCriterionForJDBCDate("statementDate <", value, "statementDate");
            return (Criteria) this;
        }

        public Criteria andStatementDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("statementDate <=", value, "statementDate");
            return (Criteria) this;
        }

        public Criteria andStatementDateIn(List<Date> values) {
            addCriterionForJDBCDate("statementDate in", values, "statementDate");
            return (Criteria) this;
        }

        public Criteria andStatementDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("statementDate not in", values, "statementDate");
            return (Criteria) this;
        }

        public Criteria andStatementDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("statementDate between", value1, value2, "statementDate");
            return (Criteria) this;
        }

        public Criteria andStatementDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("statementDate not between", value1, value2, "statementDate");
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

        public Criteria andTotalAmountIsNull() {
            addCriterion("totalAmount is null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIsNotNull() {
            addCriterion("totalAmount is not null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountEqualTo(String value) {
            addCriterion("totalAmount =", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotEqualTo(String value) {
            addCriterion("totalAmount <>", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountGreaterThan(String value) {
            addCriterion("totalAmount >", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountGreaterThanOrEqualTo(String value) {
            addCriterion("totalAmount >=", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountLessThan(String value) {
            addCriterion("totalAmount <", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountLessThanOrEqualTo(String value) {
            addCriterion("totalAmount <=", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountLike(String value) {
            addCriterion("totalAmount like", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotLike(String value) {
            addCriterion("totalAmount not like", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIn(List<String> values) {
            addCriterion("totalAmount in", values, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotIn(List<String> values) {
            addCriterion("totalAmount not in", values, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountBetween(String value1, String value2) {
            addCriterion("totalAmount between", value1, value2, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotBetween(String value1, String value2) {
            addCriterion("totalAmount not between", value1, value2, "totalAmount");
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

        public Criteria andServiceAmountIsNull() {
            addCriterion("serviceAmount is null");
            return (Criteria) this;
        }

        public Criteria andServiceAmountIsNotNull() {
            addCriterion("serviceAmount is not null");
            return (Criteria) this;
        }

        public Criteria andServiceAmountEqualTo(String value) {
            addCriterion("serviceAmount =", value, "serviceAmount");
            return (Criteria) this;
        }

        public Criteria andServiceAmountNotEqualTo(String value) {
            addCriterion("serviceAmount <>", value, "serviceAmount");
            return (Criteria) this;
        }

        public Criteria andServiceAmountGreaterThan(String value) {
            addCriterion("serviceAmount >", value, "serviceAmount");
            return (Criteria) this;
        }

        public Criteria andServiceAmountGreaterThanOrEqualTo(String value) {
            addCriterion("serviceAmount >=", value, "serviceAmount");
            return (Criteria) this;
        }

        public Criteria andServiceAmountLessThan(String value) {
            addCriterion("serviceAmount <", value, "serviceAmount");
            return (Criteria) this;
        }

        public Criteria andServiceAmountLessThanOrEqualTo(String value) {
            addCriterion("serviceAmount <=", value, "serviceAmount");
            return (Criteria) this;
        }

        public Criteria andServiceAmountLike(String value) {
            addCriterion("serviceAmount like", value, "serviceAmount");
            return (Criteria) this;
        }

        public Criteria andServiceAmountNotLike(String value) {
            addCriterion("serviceAmount not like", value, "serviceAmount");
            return (Criteria) this;
        }

        public Criteria andServiceAmountIn(List<String> values) {
            addCriterion("serviceAmount in", values, "serviceAmount");
            return (Criteria) this;
        }

        public Criteria andServiceAmountNotIn(List<String> values) {
            addCriterion("serviceAmount not in", values, "serviceAmount");
            return (Criteria) this;
        }

        public Criteria andServiceAmountBetween(String value1, String value2) {
            addCriterion("serviceAmount between", value1, value2, "serviceAmount");
            return (Criteria) this;
        }

        public Criteria andServiceAmountNotBetween(String value1, String value2) {
            addCriterion("serviceAmount not between", value1, value2, "serviceAmount");
            return (Criteria) this;
        }

        public Criteria andReciveDateIsNull() {
            addCriterion("reciveDate is null");
            return (Criteria) this;
        }

        public Criteria andReciveDateIsNotNull() {
            addCriterion("reciveDate is not null");
            return (Criteria) this;
        }

        public Criteria andReciveDateEqualTo(Date value) {
            addCriterionForJDBCDate("reciveDate =", value, "reciveDate");
            return (Criteria) this;
        }

        public Criteria andReciveDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("reciveDate <>", value, "reciveDate");
            return (Criteria) this;
        }

        public Criteria andReciveDateGreaterThan(Date value) {
            addCriterionForJDBCDate("reciveDate >", value, "reciveDate");
            return (Criteria) this;
        }

        public Criteria andReciveDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("reciveDate >=", value, "reciveDate");
            return (Criteria) this;
        }

        public Criteria andReciveDateLessThan(Date value) {
            addCriterionForJDBCDate("reciveDate <", value, "reciveDate");
            return (Criteria) this;
        }

        public Criteria andReciveDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("reciveDate <=", value, "reciveDate");
            return (Criteria) this;
        }

        public Criteria andReciveDateIn(List<Date> values) {
            addCriterionForJDBCDate("reciveDate in", values, "reciveDate");
            return (Criteria) this;
        }

        public Criteria andReciveDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("reciveDate not in", values, "reciveDate");
            return (Criteria) this;
        }

        public Criteria andReciveDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("reciveDate between", value1, value2, "reciveDate");
            return (Criteria) this;
        }

        public Criteria andReciveDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("reciveDate not between", value1, value2, "reciveDate");
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