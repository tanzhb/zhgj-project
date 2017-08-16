package com.congmai.zhgj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PriceListExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PriceListExample() {
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

        public Criteria andPriceIdIsNull() {
            addCriterion("priceId is null");
            return (Criteria) this;
        }

        public Criteria andPriceIdIsNotNull() {
            addCriterion("priceId is not null");
            return (Criteria) this;
        }

        public Criteria andPriceIdEqualTo(String value) {
            addCriterion("priceId =", value, "priceId");
            return (Criteria) this;
        }

        public Criteria andPriceIdNotEqualTo(String value) {
            addCriterion("priceId <>", value, "priceId");
            return (Criteria) this;
        }

        public Criteria andPriceIdGreaterThan(String value) {
            addCriterion("priceId >", value, "priceId");
            return (Criteria) this;
        }

        public Criteria andPriceIdGreaterThanOrEqualTo(String value) {
            addCriterion("priceId >=", value, "priceId");
            return (Criteria) this;
        }

        public Criteria andPriceIdLessThan(String value) {
            addCriterion("priceId <", value, "priceId");
            return (Criteria) this;
        }

        public Criteria andPriceIdLessThanOrEqualTo(String value) {
            addCriterion("priceId <=", value, "priceId");
            return (Criteria) this;
        }

        public Criteria andPriceIdLike(String value) {
            addCriterion("priceId like", value, "priceId");
            return (Criteria) this;
        }

        public Criteria andPriceIdNotLike(String value) {
            addCriterion("priceId not like", value, "priceId");
            return (Criteria) this;
        }

        public Criteria andPriceIdIn(List<String> values) {
            addCriterion("priceId in", values, "priceId");
            return (Criteria) this;
        }

        public Criteria andPriceIdNotIn(List<String> values) {
            addCriterion("priceId not in", values, "priceId");
            return (Criteria) this;
        }

        public Criteria andPriceIdBetween(String value1, String value2) {
            addCriterion("priceId between", value1, value2, "priceId");
            return (Criteria) this;
        }

        public Criteria andPriceIdNotBetween(String value1, String value2) {
            addCriterion("priceId not between", value1, value2, "priceId");
            return (Criteria) this;
        }

        public Criteria andPriceNumIsNull() {
            addCriterion("priceNum is null");
            return (Criteria) this;
        }

        public Criteria andPriceNumIsNotNull() {
            addCriterion("priceNum is not null");
            return (Criteria) this;
        }

        public Criteria andPriceNumEqualTo(String value) {
            addCriterion("priceNum =", value, "priceNum");
            return (Criteria) this;
        }

        public Criteria andPriceNumNotEqualTo(String value) {
            addCriterion("priceNum <>", value, "priceNum");
            return (Criteria) this;
        }

        public Criteria andPriceNumGreaterThan(String value) {
            addCriterion("priceNum >", value, "priceNum");
            return (Criteria) this;
        }

        public Criteria andPriceNumGreaterThanOrEqualTo(String value) {
            addCriterion("priceNum >=", value, "priceNum");
            return (Criteria) this;
        }

        public Criteria andPriceNumLessThan(String value) {
            addCriterion("priceNum <", value, "priceNum");
            return (Criteria) this;
        }

        public Criteria andPriceNumLessThanOrEqualTo(String value) {
            addCriterion("priceNum <=", value, "priceNum");
            return (Criteria) this;
        }

        public Criteria andPriceNumLike(String value) {
            addCriterion("priceNum like", value, "priceNum");
            return (Criteria) this;
        }

        public Criteria andPriceNumNotLike(String value) {
            addCriterion("priceNum not like", value, "priceNum");
            return (Criteria) this;
        }

        public Criteria andPriceNumIn(List<String> values) {
            addCriterion("priceNum in", values, "priceNum");
            return (Criteria) this;
        }

        public Criteria andPriceNumNotIn(List<String> values) {
            addCriterion("priceNum not in", values, "priceNum");
            return (Criteria) this;
        }

        public Criteria andPriceNumBetween(String value1, String value2) {
            addCriterion("priceNum between", value1, value2, "priceNum");
            return (Criteria) this;
        }

        public Criteria andPriceNumNotBetween(String value1, String value2) {
            addCriterion("priceNum not between", value1, value2, "priceNum");
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

        public Criteria andPriceDescribeIsNull() {
            addCriterion("priceDescribe is null");
            return (Criteria) this;
        }

        public Criteria andPriceDescribeIsNotNull() {
            addCriterion("priceDescribe is not null");
            return (Criteria) this;
        }

        public Criteria andPriceDescribeEqualTo(String value) {
            addCriterion("priceDescribe =", value, "priceDescribe");
            return (Criteria) this;
        }

        public Criteria andPriceDescribeNotEqualTo(String value) {
            addCriterion("priceDescribe <>", value, "priceDescribe");
            return (Criteria) this;
        }

        public Criteria andPriceDescribeGreaterThan(String value) {
            addCriterion("priceDescribe >", value, "priceDescribe");
            return (Criteria) this;
        }

        public Criteria andPriceDescribeGreaterThanOrEqualTo(String value) {
            addCriterion("priceDescribe >=", value, "priceDescribe");
            return (Criteria) this;
        }

        public Criteria andPriceDescribeLessThan(String value) {
            addCriterion("priceDescribe <", value, "priceDescribe");
            return (Criteria) this;
        }

        public Criteria andPriceDescribeLessThanOrEqualTo(String value) {
            addCriterion("priceDescribe <=", value, "priceDescribe");
            return (Criteria) this;
        }

        public Criteria andPriceDescribeLike(String value) {
            addCriterion("priceDescribe like", value, "priceDescribe");
            return (Criteria) this;
        }

        public Criteria andPriceDescribeNotLike(String value) {
            addCriterion("priceDescribe not like", value, "priceDescribe");
            return (Criteria) this;
        }

        public Criteria andPriceDescribeIn(List<String> values) {
            addCriterion("priceDescribe in", values, "priceDescribe");
            return (Criteria) this;
        }

        public Criteria andPriceDescribeNotIn(List<String> values) {
            addCriterion("priceDescribe not in", values, "priceDescribe");
            return (Criteria) this;
        }

        public Criteria andPriceDescribeBetween(String value1, String value2) {
            addCriterion("priceDescribe between", value1, value2, "priceDescribe");
            return (Criteria) this;
        }

        public Criteria andPriceDescribeNotBetween(String value1, String value2) {
            addCriterion("priceDescribe not between", value1, value2, "priceDescribe");
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

        public Criteria andRateIsNull() {
            addCriterion("rate is null");
            return (Criteria) this;
        }

        public Criteria andRateIsNotNull() {
            addCriterion("rate is not null");
            return (Criteria) this;
        }

        public Criteria andRateEqualTo(String value) {
            addCriterion("rate =", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotEqualTo(String value) {
            addCriterion("rate <>", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThan(String value) {
            addCriterion("rate >", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThanOrEqualTo(String value) {
            addCriterion("rate >=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThan(String value) {
            addCriterion("rate <", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThanOrEqualTo(String value) {
            addCriterion("rate <=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLike(String value) {
            addCriterion("rate like", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotLike(String value) {
            addCriterion("rate not like", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateIn(List<String> values) {
            addCriterion("rate in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotIn(List<String> values) {
            addCriterion("rate not in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateBetween(String value1, String value2) {
            addCriterion("rate between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotBetween(String value1, String value2) {
            addCriterion("rate not between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andIsLadderPriceIsNull() {
            addCriterion("isLadderPrice is null");
            return (Criteria) this;
        }

        public Criteria andIsLadderPriceIsNotNull() {
            addCriterion("isLadderPrice is not null");
            return (Criteria) this;
        }

        public Criteria andIsLadderPriceEqualTo(String value) {
            addCriterion("isLadderPrice =", value, "isLadderPrice");
            return (Criteria) this;
        }

        public Criteria andIsLadderPriceNotEqualTo(String value) {
            addCriterion("isLadderPrice <>", value, "isLadderPrice");
            return (Criteria) this;
        }

        public Criteria andIsLadderPriceGreaterThan(String value) {
            addCriterion("isLadderPrice >", value, "isLadderPrice");
            return (Criteria) this;
        }

        public Criteria andIsLadderPriceGreaterThanOrEqualTo(String value) {
            addCriterion("isLadderPrice >=", value, "isLadderPrice");
            return (Criteria) this;
        }

        public Criteria andIsLadderPriceLessThan(String value) {
            addCriterion("isLadderPrice <", value, "isLadderPrice");
            return (Criteria) this;
        }

        public Criteria andIsLadderPriceLessThanOrEqualTo(String value) {
            addCriterion("isLadderPrice <=", value, "isLadderPrice");
            return (Criteria) this;
        }

        public Criteria andIsLadderPriceLike(String value) {
            addCriterion("isLadderPrice like", value, "isLadderPrice");
            return (Criteria) this;
        }

        public Criteria andIsLadderPriceNotLike(String value) {
            addCriterion("isLadderPrice not like", value, "isLadderPrice");
            return (Criteria) this;
        }

        public Criteria andIsLadderPriceIn(List<String> values) {
            addCriterion("isLadderPrice in", values, "isLadderPrice");
            return (Criteria) this;
        }

        public Criteria andIsLadderPriceNotIn(List<String> values) {
            addCriterion("isLadderPrice not in", values, "isLadderPrice");
            return (Criteria) this;
        }

        public Criteria andIsLadderPriceBetween(String value1, String value2) {
            addCriterion("isLadderPrice between", value1, value2, "isLadderPrice");
            return (Criteria) this;
        }

        public Criteria andIsLadderPriceNotBetween(String value1, String value2) {
            addCriterion("isLadderPrice not between", value1, value2, "isLadderPrice");
            return (Criteria) this;
        }

        public Criteria andLadderTypeIsNull() {
            addCriterion("ladderType is null");
            return (Criteria) this;
        }

        public Criteria andLadderTypeIsNotNull() {
            addCriterion("ladderType is not null");
            return (Criteria) this;
        }

        public Criteria andLadderTypeEqualTo(String value) {
            addCriterion("ladderType =", value, "ladderType");
            return (Criteria) this;
        }

        public Criteria andLadderTypeNotEqualTo(String value) {
            addCriterion("ladderType <>", value, "ladderType");
            return (Criteria) this;
        }

        public Criteria andLadderTypeGreaterThan(String value) {
            addCriterion("ladderType >", value, "ladderType");
            return (Criteria) this;
        }

        public Criteria andLadderTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ladderType >=", value, "ladderType");
            return (Criteria) this;
        }

        public Criteria andLadderTypeLessThan(String value) {
            addCriterion("ladderType <", value, "ladderType");
            return (Criteria) this;
        }

        public Criteria andLadderTypeLessThanOrEqualTo(String value) {
            addCriterion("ladderType <=", value, "ladderType");
            return (Criteria) this;
        }

        public Criteria andLadderTypeLike(String value) {
            addCriterion("ladderType like", value, "ladderType");
            return (Criteria) this;
        }

        public Criteria andLadderTypeNotLike(String value) {
            addCriterion("ladderType not like", value, "ladderType");
            return (Criteria) this;
        }

        public Criteria andLadderTypeIn(List<String> values) {
            addCriterion("ladderType in", values, "ladderType");
            return (Criteria) this;
        }

        public Criteria andLadderTypeNotIn(List<String> values) {
            addCriterion("ladderType not in", values, "ladderType");
            return (Criteria) this;
        }

        public Criteria andLadderTypeBetween(String value1, String value2) {
            addCriterion("ladderType between", value1, value2, "ladderType");
            return (Criteria) this;
        }

        public Criteria andLadderTypeNotBetween(String value1, String value2) {
            addCriterion("ladderType not between", value1, value2, "ladderType");
            return (Criteria) this;
        }

        public Criteria andInclusivePriceIsNull() {
            addCriterion("inclusivePrice is null");
            return (Criteria) this;
        }

        public Criteria andInclusivePriceIsNotNull() {
            addCriterion("inclusivePrice is not null");
            return (Criteria) this;
        }

        public Criteria andInclusivePriceEqualTo(String value) {
            addCriterion("inclusivePrice =", value, "inclusivePrice");
            return (Criteria) this;
        }

        public Criteria andInclusivePriceNotEqualTo(String value) {
            addCriterion("inclusivePrice <>", value, "inclusivePrice");
            return (Criteria) this;
        }

        public Criteria andInclusivePriceGreaterThan(String value) {
            addCriterion("inclusivePrice >", value, "inclusivePrice");
            return (Criteria) this;
        }

        public Criteria andInclusivePriceGreaterThanOrEqualTo(String value) {
            addCriterion("inclusivePrice >=", value, "inclusivePrice");
            return (Criteria) this;
        }

        public Criteria andInclusivePriceLessThan(String value) {
            addCriterion("inclusivePrice <", value, "inclusivePrice");
            return (Criteria) this;
        }

        public Criteria andInclusivePriceLessThanOrEqualTo(String value) {
            addCriterion("inclusivePrice <=", value, "inclusivePrice");
            return (Criteria) this;
        }

        public Criteria andInclusivePriceLike(String value) {
            addCriterion("inclusivePrice like", value, "inclusivePrice");
            return (Criteria) this;
        }

        public Criteria andInclusivePriceNotLike(String value) {
            addCriterion("inclusivePrice not like", value, "inclusivePrice");
            return (Criteria) this;
        }

        public Criteria andInclusivePriceIn(List<String> values) {
            addCriterion("inclusivePrice in", values, "inclusivePrice");
            return (Criteria) this;
        }

        public Criteria andInclusivePriceNotIn(List<String> values) {
            addCriterion("inclusivePrice not in", values, "inclusivePrice");
            return (Criteria) this;
        }

        public Criteria andInclusivePriceBetween(String value1, String value2) {
            addCriterion("inclusivePrice between", value1, value2, "inclusivePrice");
            return (Criteria) this;
        }

        public Criteria andInclusivePriceNotBetween(String value1, String value2) {
            addCriterion("inclusivePrice not between", value1, value2, "inclusivePrice");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(String value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(String value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(String value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(String value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(String value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(String value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLike(String value) {
            addCriterion("price like", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotLike(String value) {
            addCriterion("price not like", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<String> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<String> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(String value1, String value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(String value1, String value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceTypeIsNull() {
            addCriterion("priceType is null");
            return (Criteria) this;
        }

        public Criteria andPriceTypeIsNotNull() {
            addCriterion("priceType is not null");
            return (Criteria) this;
        }

        public Criteria andPriceTypeEqualTo(String value) {
            addCriterion("priceType =", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeNotEqualTo(String value) {
            addCriterion("priceType <>", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeGreaterThan(String value) {
            addCriterion("priceType >", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("priceType >=", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeLessThan(String value) {
            addCriterion("priceType <", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeLessThanOrEqualTo(String value) {
            addCriterion("priceType <=", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeLike(String value) {
            addCriterion("priceType like", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeNotLike(String value) {
            addCriterion("priceType not like", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeIn(List<String> values) {
            addCriterion("priceType in", values, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeNotIn(List<String> values) {
            addCriterion("priceType not in", values, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeBetween(String value1, String value2) {
            addCriterion("priceType between", value1, value2, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeNotBetween(String value1, String value2) {
            addCriterion("priceType not between", value1, value2, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceEffectiveDateIsNull() {
            addCriterion("priceEffectiveDate is null");
            return (Criteria) this;
        }

        public Criteria andPriceEffectiveDateIsNotNull() {
            addCriterion("priceEffectiveDate is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEffectiveDateEqualTo(Date value) {
            addCriterionForJDBCDate("priceEffectiveDate =", value, "priceEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andPriceEffectiveDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("priceEffectiveDate <>", value, "priceEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andPriceEffectiveDateGreaterThan(Date value) {
            addCriterionForJDBCDate("priceEffectiveDate >", value, "priceEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andPriceEffectiveDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("priceEffectiveDate >=", value, "priceEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andPriceEffectiveDateLessThan(Date value) {
            addCriterionForJDBCDate("priceEffectiveDate <", value, "priceEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andPriceEffectiveDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("priceEffectiveDate <=", value, "priceEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andPriceEffectiveDateIn(List<Date> values) {
            addCriterionForJDBCDate("priceEffectiveDate in", values, "priceEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andPriceEffectiveDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("priceEffectiveDate not in", values, "priceEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andPriceEffectiveDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("priceEffectiveDate between", value1, value2, "priceEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andPriceEffectiveDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("priceEffectiveDate not between", value1, value2, "priceEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andPriceExpirationDateIsNull() {
            addCriterion("priceExpirationDate is null");
            return (Criteria) this;
        }

        public Criteria andPriceExpirationDateIsNotNull() {
            addCriterion("priceExpirationDate is not null");
            return (Criteria) this;
        }

        public Criteria andPriceExpirationDateEqualTo(Date value) {
            addCriterionForJDBCDate("priceExpirationDate =", value, "priceExpirationDate");
            return (Criteria) this;
        }

        public Criteria andPriceExpirationDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("priceExpirationDate <>", value, "priceExpirationDate");
            return (Criteria) this;
        }

        public Criteria andPriceExpirationDateGreaterThan(Date value) {
            addCriterionForJDBCDate("priceExpirationDate >", value, "priceExpirationDate");
            return (Criteria) this;
        }

        public Criteria andPriceExpirationDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("priceExpirationDate >=", value, "priceExpirationDate");
            return (Criteria) this;
        }

        public Criteria andPriceExpirationDateLessThan(Date value) {
            addCriterionForJDBCDate("priceExpirationDate <", value, "priceExpirationDate");
            return (Criteria) this;
        }

        public Criteria andPriceExpirationDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("priceExpirationDate <=", value, "priceExpirationDate");
            return (Criteria) this;
        }

        public Criteria andPriceExpirationDateIn(List<Date> values) {
            addCriterionForJDBCDate("priceExpirationDate in", values, "priceExpirationDate");
            return (Criteria) this;
        }

        public Criteria andPriceExpirationDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("priceExpirationDate not in", values, "priceExpirationDate");
            return (Criteria) this;
        }

        public Criteria andPriceExpirationDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("priceExpirationDate between", value1, value2, "priceExpirationDate");
            return (Criteria) this;
        }

        public Criteria andPriceExpirationDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("priceExpirationDate not between", value1, value2, "priceExpirationDate");
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

        public Criteria andFileIsNull() {
            addCriterion("file is null");
            return (Criteria) this;
        }

        public Criteria andFileIsNotNull() {
            addCriterion("file is not null");
            return (Criteria) this;
        }

        public Criteria andFileEqualTo(String value) {
            addCriterion("file =", value, "file");
            return (Criteria) this;
        }

        public Criteria andFileNotEqualTo(String value) {
            addCriterion("file <>", value, "file");
            return (Criteria) this;
        }

        public Criteria andFileGreaterThan(String value) {
            addCriterion("file >", value, "file");
            return (Criteria) this;
        }

        public Criteria andFileGreaterThanOrEqualTo(String value) {
            addCriterion("file >=", value, "file");
            return (Criteria) this;
        }

        public Criteria andFileLessThan(String value) {
            addCriterion("file <", value, "file");
            return (Criteria) this;
        }

        public Criteria andFileLessThanOrEqualTo(String value) {
            addCriterion("file <=", value, "file");
            return (Criteria) this;
        }

        public Criteria andFileLike(String value) {
            addCriterion("file like", value, "file");
            return (Criteria) this;
        }

        public Criteria andFileNotLike(String value) {
            addCriterion("file not like", value, "file");
            return (Criteria) this;
        }

        public Criteria andFileIn(List<String> values) {
            addCriterion("file in", values, "file");
            return (Criteria) this;
        }

        public Criteria andFileNotIn(List<String> values) {
            addCriterion("file not in", values, "file");
            return (Criteria) this;
        }

        public Criteria andFileBetween(String value1, String value2) {
            addCriterion("file between", value1, value2, "file");
            return (Criteria) this;
        }

        public Criteria andFileNotBetween(String value1, String value2) {
            addCriterion("file not between", value1, value2, "file");
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

        public Criteria andVersionNOIsNull() {
            addCriterion("versionNO is null");
            return (Criteria) this;
        }

        public Criteria andVersionNOIsNotNull() {
            addCriterion("versionNO is not null");
            return (Criteria) this;
        }

        public Criteria andVersionNOEqualTo(String value) {
            addCriterion("versionNO =", value, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNONotEqualTo(String value) {
            addCriterion("versionNO <>", value, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNOGreaterThan(String value) {
            addCriterion("versionNO >", value, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNOGreaterThanOrEqualTo(String value) {
            addCriterion("versionNO >=", value, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNOLessThan(String value) {
            addCriterion("versionNO <", value, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNOLessThanOrEqualTo(String value) {
            addCriterion("versionNO <=", value, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNOLike(String value) {
            addCriterion("versionNO like", value, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNONotLike(String value) {
            addCriterion("versionNO not like", value, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNOIn(List<String> values) {
            addCriterion("versionNO in", values, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNONotIn(List<String> values) {
            addCriterion("versionNO not in", values, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNOBetween(String value1, String value2) {
            addCriterion("versionNO between", value1, value2, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNONotBetween(String value1, String value2) {
            addCriterion("versionNO not between", value1, value2, "versionNO");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionIsNull() {
            addCriterion("isLatestVersion is null");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionIsNotNull() {
            addCriterion("isLatestVersion is not null");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionEqualTo(String value) {
            addCriterion("isLatestVersion =", value, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionNotEqualTo(String value) {
            addCriterion("isLatestVersion <>", value, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionGreaterThan(String value) {
            addCriterion("isLatestVersion >", value, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionGreaterThanOrEqualTo(String value) {
            addCriterion("isLatestVersion >=", value, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionLessThan(String value) {
            addCriterion("isLatestVersion <", value, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionLessThanOrEqualTo(String value) {
            addCriterion("isLatestVersion <=", value, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionLike(String value) {
            addCriterion("isLatestVersion like", value, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionNotLike(String value) {
            addCriterion("isLatestVersion not like", value, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionIn(List<String> values) {
            addCriterion("isLatestVersion in", values, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionNotIn(List<String> values) {
            addCriterion("isLatestVersion not in", values, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionBetween(String value1, String value2) {
            addCriterion("isLatestVersion between", value1, value2, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionNotBetween(String value1, String value2) {
            addCriterion("isLatestVersion not between", value1, value2, "isLatestVersion");
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

        public Criteria andCurrencyIsNull() {
            addCriterion("currency is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNotNull() {
            addCriterion("currency is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyEqualTo(String value) {
            addCriterion("currency =", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotEqualTo(String value) {
            addCriterion("currency <>", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThan(String value) {
            addCriterion("currency >", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("currency >=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThan(String value) {
            addCriterion("currency <", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThanOrEqualTo(String value) {
            addCriterion("currency <=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLike(String value) {
            addCriterion("currency like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotLike(String value) {
            addCriterion("currency not like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyIn(List<String> values) {
            addCriterion("currency in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotIn(List<String> values) {
            addCriterion("currency not in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyBetween(String value1, String value2) {
            addCriterion("currency between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotBetween(String value1, String value2) {
            addCriterion("currency not between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIsNull() {
            addCriterion("unitPrice is null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIsNotNull() {
            addCriterion("unitPrice is not null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceEqualTo(String value) {
            addCriterion("unitPrice =", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotEqualTo(String value) {
            addCriterion("unitPrice <>", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGreaterThan(String value) {
            addCriterion("unitPrice >", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGreaterThanOrEqualTo(String value) {
            addCriterion("unitPrice >=", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceLessThan(String value) {
            addCriterion("unitPrice <", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceLessThanOrEqualTo(String value) {
            addCriterion("unitPrice <=", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceLike(String value) {
            addCriterion("unitPrice like", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotLike(String value) {
            addCriterion("unitPrice not like", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIn(List<String> values) {
            addCriterion("unitPrice in", values, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotIn(List<String> values) {
            addCriterion("unitPrice not in", values, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceBetween(String value1, String value2) {
            addCriterion("unitPrice between", value1, value2, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotBetween(String value1, String value2) {
            addCriterion("unitPrice not between", value1, value2, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andTopPriceIsNull() {
            addCriterion("topPrice is null");
            return (Criteria) this;
        }

        public Criteria andTopPriceIsNotNull() {
            addCriterion("topPrice is not null");
            return (Criteria) this;
        }

        public Criteria andTopPriceEqualTo(String value) {
            addCriterion("topPrice =", value, "topPrice");
            return (Criteria) this;
        }

        public Criteria andTopPriceNotEqualTo(String value) {
            addCriterion("topPrice <>", value, "topPrice");
            return (Criteria) this;
        }

        public Criteria andTopPriceGreaterThan(String value) {
            addCriterion("topPrice >", value, "topPrice");
            return (Criteria) this;
        }

        public Criteria andTopPriceGreaterThanOrEqualTo(String value) {
            addCriterion("topPrice >=", value, "topPrice");
            return (Criteria) this;
        }

        public Criteria andTopPriceLessThan(String value) {
            addCriterion("topPrice <", value, "topPrice");
            return (Criteria) this;
        }

        public Criteria andTopPriceLessThanOrEqualTo(String value) {
            addCriterion("topPrice <=", value, "topPrice");
            return (Criteria) this;
        }

        public Criteria andTopPriceLike(String value) {
            addCriterion("topPrice like", value, "topPrice");
            return (Criteria) this;
        }

        public Criteria andTopPriceNotLike(String value) {
            addCriterion("topPrice not like", value, "topPrice");
            return (Criteria) this;
        }

        public Criteria andTopPriceIn(List<String> values) {
            addCriterion("topPrice in", values, "topPrice");
            return (Criteria) this;
        }

        public Criteria andTopPriceNotIn(List<String> values) {
            addCriterion("topPrice not in", values, "topPrice");
            return (Criteria) this;
        }

        public Criteria andTopPriceBetween(String value1, String value2) {
            addCriterion("topPrice between", value1, value2, "topPrice");
            return (Criteria) this;
        }

        public Criteria andTopPriceNotBetween(String value1, String value2) {
            addCriterion("topPrice not between", value1, value2, "topPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceIsNull() {
            addCriterion("floorPrice is null");
            return (Criteria) this;
        }

        public Criteria andFloorPriceIsNotNull() {
            addCriterion("floorPrice is not null");
            return (Criteria) this;
        }

        public Criteria andFloorPriceEqualTo(String value) {
            addCriterion("floorPrice =", value, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceNotEqualTo(String value) {
            addCriterion("floorPrice <>", value, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceGreaterThan(String value) {
            addCriterion("floorPrice >", value, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceGreaterThanOrEqualTo(String value) {
            addCriterion("floorPrice >=", value, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceLessThan(String value) {
            addCriterion("floorPrice <", value, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceLessThanOrEqualTo(String value) {
            addCriterion("floorPrice <=", value, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceLike(String value) {
            addCriterion("floorPrice like", value, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceNotLike(String value) {
            addCriterion("floorPrice not like", value, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceIn(List<String> values) {
            addCriterion("floorPrice in", values, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceNotIn(List<String> values) {
            addCriterion("floorPrice not in", values, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceBetween(String value1, String value2) {
            addCriterion("floorPrice between", value1, value2, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceNotBetween(String value1, String value2) {
            addCriterion("floorPrice not between", value1, value2, "floorPrice");
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