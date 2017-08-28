package com.congmai.zhgj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OrderMaterielExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderMaterielExample() {
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

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(String value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(String value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(String value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(String value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(String value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(String value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLike(String value) {
            addCriterion("amount like", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotLike(String value) {
            addCriterion("amount not like", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<String> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<String> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(String value1, String value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(String value1, String value2) {
            addCriterion("amount not between", value1, value2, "amount");
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

        public Criteria andDiscountRateIsNull() {
            addCriterion("discountRate is null");
            return (Criteria) this;
        }

        public Criteria andDiscountRateIsNotNull() {
            addCriterion("discountRate is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountRateEqualTo(String value) {
            addCriterion("discountRate =", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateNotEqualTo(String value) {
            addCriterion("discountRate <>", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateGreaterThan(String value) {
            addCriterion("discountRate >", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateGreaterThanOrEqualTo(String value) {
            addCriterion("discountRate >=", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateLessThan(String value) {
            addCriterion("discountRate <", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateLessThanOrEqualTo(String value) {
            addCriterion("discountRate <=", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateLike(String value) {
            addCriterion("discountRate like", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateNotLike(String value) {
            addCriterion("discountRate not like", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateIn(List<String> values) {
            addCriterion("discountRate in", values, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateNotIn(List<String> values) {
            addCriterion("discountRate not in", values, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateBetween(String value1, String value2) {
            addCriterion("discountRate between", value1, value2, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateNotBetween(String value1, String value2) {
            addCriterion("discountRate not between", value1, value2, "discountRate");
            return (Criteria) this;
        }

        public Criteria andServiceRateIsNull() {
            addCriterion("serviceRate is null");
            return (Criteria) this;
        }

        public Criteria andServiceRateIsNotNull() {
            addCriterion("serviceRate is not null");
            return (Criteria) this;
        }

        public Criteria andServiceRateEqualTo(String value) {
            addCriterion("serviceRate =", value, "serviceRate");
            return (Criteria) this;
        }

        public Criteria andServiceRateNotEqualTo(String value) {
            addCriterion("serviceRate <>", value, "serviceRate");
            return (Criteria) this;
        }

        public Criteria andServiceRateGreaterThan(String value) {
            addCriterion("serviceRate >", value, "serviceRate");
            return (Criteria) this;
        }

        public Criteria andServiceRateGreaterThanOrEqualTo(String value) {
            addCriterion("serviceRate >=", value, "serviceRate");
            return (Criteria) this;
        }

        public Criteria andServiceRateLessThan(String value) {
            addCriterion("serviceRate <", value, "serviceRate");
            return (Criteria) this;
        }

        public Criteria andServiceRateLessThanOrEqualTo(String value) {
            addCriterion("serviceRate <=", value, "serviceRate");
            return (Criteria) this;
        }

        public Criteria andServiceRateLike(String value) {
            addCriterion("serviceRate like", value, "serviceRate");
            return (Criteria) this;
        }

        public Criteria andServiceRateNotLike(String value) {
            addCriterion("serviceRate not like", value, "serviceRate");
            return (Criteria) this;
        }

        public Criteria andServiceRateIn(List<String> values) {
            addCriterion("serviceRate in", values, "serviceRate");
            return (Criteria) this;
        }

        public Criteria andServiceRateNotIn(List<String> values) {
            addCriterion("serviceRate not in", values, "serviceRate");
            return (Criteria) this;
        }

        public Criteria andServiceRateBetween(String value1, String value2) {
            addCriterion("serviceRate between", value1, value2, "serviceRate");
            return (Criteria) this;
        }

        public Criteria andServiceRateNotBetween(String value1, String value2) {
            addCriterion("serviceRate not between", value1, value2, "serviceRate");
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