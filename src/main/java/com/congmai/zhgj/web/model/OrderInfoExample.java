package com.congmai.zhgj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OrderInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderInfoExample() {
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

        public Criteria andContractSerialIsNull() {
            addCriterion("contractSerial is null");
            return (Criteria) this;
        }

        public Criteria andContractSerialIsNotNull() {
            addCriterion("contractSerial is not null");
            return (Criteria) this;
        }

        public Criteria andContractSerialEqualTo(String value) {
            addCriterion("contractSerial =", value, "contractSerial");
            return (Criteria) this;
        }

        public Criteria andContractSerialNotEqualTo(String value) {
            addCriterion("contractSerial <>", value, "contractSerial");
            return (Criteria) this;
        }

        public Criteria andContractSerialGreaterThan(String value) {
            addCriterion("contractSerial >", value, "contractSerial");
            return (Criteria) this;
        }

        public Criteria andContractSerialGreaterThanOrEqualTo(String value) {
            addCriterion("contractSerial >=", value, "contractSerial");
            return (Criteria) this;
        }

        public Criteria andContractSerialLessThan(String value) {
            addCriterion("contractSerial <", value, "contractSerial");
            return (Criteria) this;
        }

        public Criteria andContractSerialLessThanOrEqualTo(String value) {
            addCriterion("contractSerial <=", value, "contractSerial");
            return (Criteria) this;
        }

        public Criteria andContractSerialLike(String value) {
            addCriterion("contractSerial like", value, "contractSerial");
            return (Criteria) this;
        }

        public Criteria andContractSerialNotLike(String value) {
            addCriterion("contractSerial not like", value, "contractSerial");
            return (Criteria) this;
        }

        public Criteria andContractSerialIn(List<String> values) {
            addCriterion("contractSerial in", values, "contractSerial");
            return (Criteria) this;
        }

        public Criteria andContractSerialNotIn(List<String> values) {
            addCriterion("contractSerial not in", values, "contractSerial");
            return (Criteria) this;
        }

        public Criteria andContractSerialBetween(String value1, String value2) {
            addCriterion("contractSerial between", value1, value2, "contractSerial");
            return (Criteria) this;
        }

        public Criteria andContractSerialNotBetween(String value1, String value2) {
            addCriterion("contractSerial not between", value1, value2, "contractSerial");
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

        public Criteria andOrderNumIsNull() {
            addCriterion("orderNum is null");
            return (Criteria) this;
        }

        public Criteria andOrderNumIsNotNull() {
            addCriterion("orderNum is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNumEqualTo(String value) {
            addCriterion("orderNum =", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotEqualTo(String value) {
            addCriterion("orderNum <>", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumGreaterThan(String value) {
            addCriterion("orderNum >", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumGreaterThanOrEqualTo(String value) {
            addCriterion("orderNum >=", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumLessThan(String value) {
            addCriterion("orderNum <", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumLessThanOrEqualTo(String value) {
            addCriterion("orderNum <=", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumLike(String value) {
            addCriterion("orderNum like", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotLike(String value) {
            addCriterion("orderNum not like", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumIn(List<String> values) {
            addCriterion("orderNum in", values, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotIn(List<String> values) {
            addCriterion("orderNum not in", values, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumBetween(String value1, String value2) {
            addCriterion("orderNum between", value1, value2, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotBetween(String value1, String value2) {
            addCriterion("orderNum not between", value1, value2, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIsNull() {
            addCriterion("orderType is null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIsNotNull() {
            addCriterion("orderType is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeEqualTo(String value) {
            addCriterion("orderType =", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotEqualTo(String value) {
            addCriterion("orderType <>", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThan(String value) {
            addCriterion("orderType >", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThanOrEqualTo(String value) {
            addCriterion("orderType >=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThan(String value) {
            addCriterion("orderType <", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThanOrEqualTo(String value) {
            addCriterion("orderType <=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLike(String value) {
            addCriterion("orderType like", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotLike(String value) {
            addCriterion("orderType not like", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIn(List<String> values) {
            addCriterion("orderType in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotIn(List<String> values) {
            addCriterion("orderType not in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeBetween(String value1, String value2) {
            addCriterion("orderType between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotBetween(String value1, String value2) {
            addCriterion("orderType not between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andSellerIsNull() {
            addCriterion("seller is null");
            return (Criteria) this;
        }

        public Criteria andSellerIsNotNull() {
            addCriterion("seller is not null");
            return (Criteria) this;
        }

        public Criteria andSellerEqualTo(String value) {
            addCriterion("seller =", value, "seller");
            return (Criteria) this;
        }

        public Criteria andSellerNotEqualTo(String value) {
            addCriterion("seller <>", value, "seller");
            return (Criteria) this;
        }

        public Criteria andSellerGreaterThan(String value) {
            addCriterion("seller >", value, "seller");
            return (Criteria) this;
        }

        public Criteria andSellerGreaterThanOrEqualTo(String value) {
            addCriterion("seller >=", value, "seller");
            return (Criteria) this;
        }

        public Criteria andSellerLessThan(String value) {
            addCriterion("seller <", value, "seller");
            return (Criteria) this;
        }

        public Criteria andSellerLessThanOrEqualTo(String value) {
            addCriterion("seller <=", value, "seller");
            return (Criteria) this;
        }

        public Criteria andSellerLike(String value) {
            addCriterion("seller like", value, "seller");
            return (Criteria) this;
        }

        public Criteria andSellerNotLike(String value) {
            addCriterion("seller not like", value, "seller");
            return (Criteria) this;
        }

        public Criteria andSellerIn(List<String> values) {
            addCriterion("seller in", values, "seller");
            return (Criteria) this;
        }

        public Criteria andSellerNotIn(List<String> values) {
            addCriterion("seller not in", values, "seller");
            return (Criteria) this;
        }

        public Criteria andSellerBetween(String value1, String value2) {
            addCriterion("seller between", value1, value2, "seller");
            return (Criteria) this;
        }

        public Criteria andSellerNotBetween(String value1, String value2) {
            addCriterion("seller not between", value1, value2, "seller");
            return (Criteria) this;
        }

        public Criteria andEntrustPartyIsNull() {
            addCriterion("entrustParty is null");
            return (Criteria) this;
        }

        public Criteria andEntrustPartyIsNotNull() {
            addCriterion("entrustParty is not null");
            return (Criteria) this;
        }

        public Criteria andEntrustPartyEqualTo(String value) {
            addCriterion("entrustParty =", value, "entrustParty");
            return (Criteria) this;
        }

        public Criteria andEntrustPartyNotEqualTo(String value) {
            addCriterion("entrustParty <>", value, "entrustParty");
            return (Criteria) this;
        }

        public Criteria andEntrustPartyGreaterThan(String value) {
            addCriterion("entrustParty >", value, "entrustParty");
            return (Criteria) this;
        }

        public Criteria andEntrustPartyGreaterThanOrEqualTo(String value) {
            addCriterion("entrustParty >=", value, "entrustParty");
            return (Criteria) this;
        }

        public Criteria andEntrustPartyLessThan(String value) {
            addCriterion("entrustParty <", value, "entrustParty");
            return (Criteria) this;
        }

        public Criteria andEntrustPartyLessThanOrEqualTo(String value) {
            addCriterion("entrustParty <=", value, "entrustParty");
            return (Criteria) this;
        }

        public Criteria andEntrustPartyLike(String value) {
            addCriterion("entrustParty like", value, "entrustParty");
            return (Criteria) this;
        }

        public Criteria andEntrustPartyNotLike(String value) {
            addCriterion("entrustParty not like", value, "entrustParty");
            return (Criteria) this;
        }

        public Criteria andEntrustPartyIn(List<String> values) {
            addCriterion("entrustParty in", values, "entrustParty");
            return (Criteria) this;
        }

        public Criteria andEntrustPartyNotIn(List<String> values) {
            addCriterion("entrustParty not in", values, "entrustParty");
            return (Criteria) this;
        }

        public Criteria andEntrustPartyBetween(String value1, String value2) {
            addCriterion("entrustParty between", value1, value2, "entrustParty");
            return (Criteria) this;
        }

        public Criteria andEntrustPartyNotBetween(String value1, String value2) {
            addCriterion("entrustParty not between", value1, value2, "entrustParty");
            return (Criteria) this;
        }

        public Criteria andServiceModelIsNull() {
            addCriterion("serviceModel is null");
            return (Criteria) this;
        }

        public Criteria andServiceModelIsNotNull() {
            addCriterion("serviceModel is not null");
            return (Criteria) this;
        }

        public Criteria andServiceModelEqualTo(String value) {
            addCriterion("serviceModel =", value, "serviceModel");
            return (Criteria) this;
        }

        public Criteria andServiceModelNotEqualTo(String value) {
            addCriterion("serviceModel <>", value, "serviceModel");
            return (Criteria) this;
        }

        public Criteria andServiceModelGreaterThan(String value) {
            addCriterion("serviceModel >", value, "serviceModel");
            return (Criteria) this;
        }

        public Criteria andServiceModelGreaterThanOrEqualTo(String value) {
            addCriterion("serviceModel >=", value, "serviceModel");
            return (Criteria) this;
        }

        public Criteria andServiceModelLessThan(String value) {
            addCriterion("serviceModel <", value, "serviceModel");
            return (Criteria) this;
        }

        public Criteria andServiceModelLessThanOrEqualTo(String value) {
            addCriterion("serviceModel <=", value, "serviceModel");
            return (Criteria) this;
        }

        public Criteria andServiceModelLike(String value) {
            addCriterion("serviceModel like", value, "serviceModel");
            return (Criteria) this;
        }

        public Criteria andServiceModelNotLike(String value) {
            addCriterion("serviceModel not like", value, "serviceModel");
            return (Criteria) this;
        }

        public Criteria andServiceModelIn(List<String> values) {
            addCriterion("serviceModel in", values, "serviceModel");
            return (Criteria) this;
        }

        public Criteria andServiceModelNotIn(List<String> values) {
            addCriterion("serviceModel not in", values, "serviceModel");
            return (Criteria) this;
        }

        public Criteria andServiceModelBetween(String value1, String value2) {
            addCriterion("serviceModel between", value1, value2, "serviceModel");
            return (Criteria) this;
        }

        public Criteria andServiceModelNotBetween(String value1, String value2) {
            addCriterion("serviceModel not between", value1, value2, "serviceModel");
            return (Criteria) this;
        }

        public Criteria andDemandPlanSerialIsNull() {
            addCriterion("demandPlanSerial is null");
            return (Criteria) this;
        }

        public Criteria andDemandPlanSerialIsNotNull() {
            addCriterion("demandPlanSerial is not null");
            return (Criteria) this;
        }

        public Criteria andDemandPlanSerialEqualTo(String value) {
            addCriterion("demandPlanSerial =", value, "demandPlanSerial");
            return (Criteria) this;
        }

        public Criteria andDemandPlanSerialNotEqualTo(String value) {
            addCriterion("demandPlanSerial <>", value, "demandPlanSerial");
            return (Criteria) this;
        }

        public Criteria andDemandPlanSerialGreaterThan(String value) {
            addCriterion("demandPlanSerial >", value, "demandPlanSerial");
            return (Criteria) this;
        }

        public Criteria andDemandPlanSerialGreaterThanOrEqualTo(String value) {
            addCriterion("demandPlanSerial >=", value, "demandPlanSerial");
            return (Criteria) this;
        }

        public Criteria andDemandPlanSerialLessThan(String value) {
            addCriterion("demandPlanSerial <", value, "demandPlanSerial");
            return (Criteria) this;
        }

        public Criteria andDemandPlanSerialLessThanOrEqualTo(String value) {
            addCriterion("demandPlanSerial <=", value, "demandPlanSerial");
            return (Criteria) this;
        }

        public Criteria andDemandPlanSerialLike(String value) {
            addCriterion("demandPlanSerial like", value, "demandPlanSerial");
            return (Criteria) this;
        }

        public Criteria andDemandPlanSerialNotLike(String value) {
            addCriterion("demandPlanSerial not like", value, "demandPlanSerial");
            return (Criteria) this;
        }

        public Criteria andDemandPlanSerialIn(List<String> values) {
            addCriterion("demandPlanSerial in", values, "demandPlanSerial");
            return (Criteria) this;
        }

        public Criteria andDemandPlanSerialNotIn(List<String> values) {
            addCriterion("demandPlanSerial not in", values, "demandPlanSerial");
            return (Criteria) this;
        }

        public Criteria andDemandPlanSerialBetween(String value1, String value2) {
            addCriterion("demandPlanSerial between", value1, value2, "demandPlanSerial");
            return (Criteria) this;
        }

        public Criteria andDemandPlanSerialNotBetween(String value1, String value2) {
            addCriterion("demandPlanSerial not between", value1, value2, "demandPlanSerial");
            return (Criteria) this;
        }

        public Criteria andSaleApplySerialIsNull() {
            addCriterion("saleApplySerial is null");
            return (Criteria) this;
        }

        public Criteria andSaleApplySerialIsNotNull() {
            addCriterion("saleApplySerial is not null");
            return (Criteria) this;
        }

        public Criteria andSaleApplySerialEqualTo(String value) {
            addCriterion("saleApplySerial =", value, "saleApplySerial");
            return (Criteria) this;
        }

        public Criteria andSaleApplySerialNotEqualTo(String value) {
            addCriterion("saleApplySerial <>", value, "saleApplySerial");
            return (Criteria) this;
        }

        public Criteria andSaleApplySerialGreaterThan(String value) {
            addCriterion("saleApplySerial >", value, "saleApplySerial");
            return (Criteria) this;
        }

        public Criteria andSaleApplySerialGreaterThanOrEqualTo(String value) {
            addCriterion("saleApplySerial >=", value, "saleApplySerial");
            return (Criteria) this;
        }

        public Criteria andSaleApplySerialLessThan(String value) {
            addCriterion("saleApplySerial <", value, "saleApplySerial");
            return (Criteria) this;
        }

        public Criteria andSaleApplySerialLessThanOrEqualTo(String value) {
            addCriterion("saleApplySerial <=", value, "saleApplySerial");
            return (Criteria) this;
        }

        public Criteria andSaleApplySerialLike(String value) {
            addCriterion("saleApplySerial like", value, "saleApplySerial");
            return (Criteria) this;
        }

        public Criteria andSaleApplySerialNotLike(String value) {
            addCriterion("saleApplySerial not like", value, "saleApplySerial");
            return (Criteria) this;
        }

        public Criteria andSaleApplySerialIn(List<String> values) {
            addCriterion("saleApplySerial in", values, "saleApplySerial");
            return (Criteria) this;
        }

        public Criteria andSaleApplySerialNotIn(List<String> values) {
            addCriterion("saleApplySerial not in", values, "saleApplySerial");
            return (Criteria) this;
        }

        public Criteria andSaleApplySerialBetween(String value1, String value2) {
            addCriterion("saleApplySerial between", value1, value2, "saleApplySerial");
            return (Criteria) this;
        }

        public Criteria andSaleApplySerialNotBetween(String value1, String value2) {
            addCriterion("saleApplySerial not between", value1, value2, "saleApplySerial");
            return (Criteria) this;
        }

        public Criteria andSettlementClauseIsNull() {
            addCriterion("settlementClause is null");
            return (Criteria) this;
        }

        public Criteria andSettlementClauseIsNotNull() {
            addCriterion("settlementClause is not null");
            return (Criteria) this;
        }

        public Criteria andSettlementClauseEqualTo(String value) {
            addCriterion("settlementClause =", value, "settlementClause");
            return (Criteria) this;
        }

        public Criteria andSettlementClauseNotEqualTo(String value) {
            addCriterion("settlementClause <>", value, "settlementClause");
            return (Criteria) this;
        }

        public Criteria andSettlementClauseGreaterThan(String value) {
            addCriterion("settlementClause >", value, "settlementClause");
            return (Criteria) this;
        }

        public Criteria andSettlementClauseGreaterThanOrEqualTo(String value) {
            addCriterion("settlementClause >=", value, "settlementClause");
            return (Criteria) this;
        }

        public Criteria andSettlementClauseLessThan(String value) {
            addCriterion("settlementClause <", value, "settlementClause");
            return (Criteria) this;
        }

        public Criteria andSettlementClauseLessThanOrEqualTo(String value) {
            addCriterion("settlementClause <=", value, "settlementClause");
            return (Criteria) this;
        }

        public Criteria andSettlementClauseLike(String value) {
            addCriterion("settlementClause like", value, "settlementClause");
            return (Criteria) this;
        }

        public Criteria andSettlementClauseNotLike(String value) {
            addCriterion("settlementClause not like", value, "settlementClause");
            return (Criteria) this;
        }

        public Criteria andSettlementClauseIn(List<String> values) {
            addCriterion("settlementClause in", values, "settlementClause");
            return (Criteria) this;
        }

        public Criteria andSettlementClauseNotIn(List<String> values) {
            addCriterion("settlementClause not in", values, "settlementClause");
            return (Criteria) this;
        }

        public Criteria andSettlementClauseBetween(String value1, String value2) {
            addCriterion("settlementClause between", value1, value2, "settlementClause");
            return (Criteria) this;
        }

        public Criteria andSettlementClauseNotBetween(String value1, String value2) {
            addCriterion("settlementClause not between", value1, value2, "settlementClause");
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

        public Criteria andDeliveryModeIsNull() {
            addCriterion("deliveryMode is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryModeIsNotNull() {
            addCriterion("deliveryMode is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryModeEqualTo(String value) {
            addCriterion("deliveryMode =", value, "deliveryMode");
            return (Criteria) this;
        }

        public Criteria andDeliveryModeNotEqualTo(String value) {
            addCriterion("deliveryMode <>", value, "deliveryMode");
            return (Criteria) this;
        }

        public Criteria andDeliveryModeGreaterThan(String value) {
            addCriterion("deliveryMode >", value, "deliveryMode");
            return (Criteria) this;
        }

        public Criteria andDeliveryModeGreaterThanOrEqualTo(String value) {
            addCriterion("deliveryMode >=", value, "deliveryMode");
            return (Criteria) this;
        }

        public Criteria andDeliveryModeLessThan(String value) {
            addCriterion("deliveryMode <", value, "deliveryMode");
            return (Criteria) this;
        }

        public Criteria andDeliveryModeLessThanOrEqualTo(String value) {
            addCriterion("deliveryMode <=", value, "deliveryMode");
            return (Criteria) this;
        }

        public Criteria andDeliveryModeLike(String value) {
            addCriterion("deliveryMode like", value, "deliveryMode");
            return (Criteria) this;
        }

        public Criteria andDeliveryModeNotLike(String value) {
            addCriterion("deliveryMode not like", value, "deliveryMode");
            return (Criteria) this;
        }

        public Criteria andDeliveryModeIn(List<String> values) {
            addCriterion("deliveryMode in", values, "deliveryMode");
            return (Criteria) this;
        }

        public Criteria andDeliveryModeNotIn(List<String> values) {
            addCriterion("deliveryMode not in", values, "deliveryMode");
            return (Criteria) this;
        }

        public Criteria andDeliveryModeBetween(String value1, String value2) {
            addCriterion("deliveryMode between", value1, value2, "deliveryMode");
            return (Criteria) this;
        }

        public Criteria andDeliveryModeNotBetween(String value1, String value2) {
            addCriterion("deliveryMode not between", value1, value2, "deliveryMode");
            return (Criteria) this;
        }

        public Criteria andTransportModeIsNull() {
            addCriterion("transportMode is null");
            return (Criteria) this;
        }

        public Criteria andTransportModeIsNotNull() {
            addCriterion("transportMode is not null");
            return (Criteria) this;
        }

        public Criteria andTransportModeEqualTo(String value) {
            addCriterion("transportMode =", value, "transportMode");
            return (Criteria) this;
        }

        public Criteria andTransportModeNotEqualTo(String value) {
            addCriterion("transportMode <>", value, "transportMode");
            return (Criteria) this;
        }

        public Criteria andTransportModeGreaterThan(String value) {
            addCriterion("transportMode >", value, "transportMode");
            return (Criteria) this;
        }

        public Criteria andTransportModeGreaterThanOrEqualTo(String value) {
            addCriterion("transportMode >=", value, "transportMode");
            return (Criteria) this;
        }

        public Criteria andTransportModeLessThan(String value) {
            addCriterion("transportMode <", value, "transportMode");
            return (Criteria) this;
        }

        public Criteria andTransportModeLessThanOrEqualTo(String value) {
            addCriterion("transportMode <=", value, "transportMode");
            return (Criteria) this;
        }

        public Criteria andTransportModeLike(String value) {
            addCriterion("transportMode like", value, "transportMode");
            return (Criteria) this;
        }

        public Criteria andTransportModeNotLike(String value) {
            addCriterion("transportMode not like", value, "transportMode");
            return (Criteria) this;
        }

        public Criteria andTransportModeIn(List<String> values) {
            addCriterion("transportMode in", values, "transportMode");
            return (Criteria) this;
        }

        public Criteria andTransportModeNotIn(List<String> values) {
            addCriterion("transportMode not in", values, "transportMode");
            return (Criteria) this;
        }

        public Criteria andTransportModeBetween(String value1, String value2) {
            addCriterion("transportMode between", value1, value2, "transportMode");
            return (Criteria) this;
        }

        public Criteria andTransportModeNotBetween(String value1, String value2) {
            addCriterion("transportMode not between", value1, value2, "transportMode");
            return (Criteria) this;
        }

        public Criteria andOrderDateIsNull() {
            addCriterion("orderDate is null");
            return (Criteria) this;
        }

        public Criteria andOrderDateIsNotNull() {
            addCriterion("orderDate is not null");
            return (Criteria) this;
        }

        public Criteria andOrderDateEqualTo(Date value) {
            addCriterionForJDBCDate("orderDate =", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("orderDate <>", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateGreaterThan(Date value) {
            addCriterionForJDBCDate("orderDate >", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("orderDate >=", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateLessThan(Date value) {
            addCriterionForJDBCDate("orderDate <", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("orderDate <=", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateIn(List<Date> values) {
            addCriterionForJDBCDate("orderDate in", values, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("orderDate not in", values, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("orderDate between", value1, value2, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("orderDate not between", value1, value2, "orderDate");
            return (Criteria) this;
        }

        public Criteria andMakerIsNull() {
            addCriterion("maker is null");
            return (Criteria) this;
        }

        public Criteria andMakerIsNotNull() {
            addCriterion("maker is not null");
            return (Criteria) this;
        }

        public Criteria andMakerEqualTo(String value) {
            addCriterion("maker =", value, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerNotEqualTo(String value) {
            addCriterion("maker <>", value, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerGreaterThan(String value) {
            addCriterion("maker >", value, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerGreaterThanOrEqualTo(String value) {
            addCriterion("maker >=", value, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerLessThan(String value) {
            addCriterion("maker <", value, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerLessThanOrEqualTo(String value) {
            addCriterion("maker <=", value, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerLike(String value) {
            addCriterion("maker like", value, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerNotLike(String value) {
            addCriterion("maker not like", value, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerIn(List<String> values) {
            addCriterion("maker in", values, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerNotIn(List<String> values) {
            addCriterion("maker not in", values, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerBetween(String value1, String value2) {
            addCriterion("maker between", value1, value2, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerNotBetween(String value1, String value2) {
            addCriterion("maker not between", value1, value2, "maker");
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

        public Criteria andExchangeRateIsNull() {
            addCriterion("exchangeRate is null");
            return (Criteria) this;
        }

        public Criteria andExchangeRateIsNotNull() {
            addCriterion("exchangeRate is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeRateEqualTo(String value) {
            addCriterion("exchangeRate =", value, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateNotEqualTo(String value) {
            addCriterion("exchangeRate <>", value, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateGreaterThan(String value) {
            addCriterion("exchangeRate >", value, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateGreaterThanOrEqualTo(String value) {
            addCriterion("exchangeRate >=", value, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateLessThan(String value) {
            addCriterion("exchangeRate <", value, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateLessThanOrEqualTo(String value) {
            addCriterion("exchangeRate <=", value, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateLike(String value) {
            addCriterion("exchangeRate like", value, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateNotLike(String value) {
            addCriterion("exchangeRate not like", value, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateIn(List<String> values) {
            addCriterion("exchangeRate in", values, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateNotIn(List<String> values) {
            addCriterion("exchangeRate not in", values, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateBetween(String value1, String value2) {
            addCriterion("exchangeRate between", value1, value2, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateNotBetween(String value1, String value2) {
            addCriterion("exchangeRate not between", value1, value2, "exchangeRate");
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