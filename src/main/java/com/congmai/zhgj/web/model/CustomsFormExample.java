package com.congmai.zhgj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomsFormExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CustomsFormExample() {
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

        public Criteria andCustomsFormNumIsNull() {
            addCriterion("customsFormNum is null");
            return (Criteria) this;
        }

        public Criteria andCustomsFormNumIsNotNull() {
            addCriterion("customsFormNum is not null");
            return (Criteria) this;
        }

        public Criteria andCustomsFormNumEqualTo(String value) {
            addCriterion("customsFormNum =", value, "customsFormNum");
            return (Criteria) this;
        }

        public Criteria andCustomsFormNumNotEqualTo(String value) {
            addCriterion("customsFormNum <>", value, "customsFormNum");
            return (Criteria) this;
        }

        public Criteria andCustomsFormNumGreaterThan(String value) {
            addCriterion("customsFormNum >", value, "customsFormNum");
            return (Criteria) this;
        }

        public Criteria andCustomsFormNumGreaterThanOrEqualTo(String value) {
            addCriterion("customsFormNum >=", value, "customsFormNum");
            return (Criteria) this;
        }

        public Criteria andCustomsFormNumLessThan(String value) {
            addCriterion("customsFormNum <", value, "customsFormNum");
            return (Criteria) this;
        }

        public Criteria andCustomsFormNumLessThanOrEqualTo(String value) {
            addCriterion("customsFormNum <=", value, "customsFormNum");
            return (Criteria) this;
        }

        public Criteria andCustomsFormNumLike(String value) {
            addCriterion("customsFormNum like", value, "customsFormNum");
            return (Criteria) this;
        }

        public Criteria andCustomsFormNumNotLike(String value) {
            addCriterion("customsFormNum not like", value, "customsFormNum");
            return (Criteria) this;
        }

        public Criteria andCustomsFormNumIn(List<String> values) {
            addCriterion("customsFormNum in", values, "customsFormNum");
            return (Criteria) this;
        }

        public Criteria andCustomsFormNumNotIn(List<String> values) {
            addCriterion("customsFormNum not in", values, "customsFormNum");
            return (Criteria) this;
        }

        public Criteria andCustomsFormNumBetween(String value1, String value2) {
            addCriterion("customsFormNum between", value1, value2, "customsFormNum");
            return (Criteria) this;
        }

        public Criteria andCustomsFormNumNotBetween(String value1, String value2) {
            addCriterion("customsFormNum not between", value1, value2, "customsFormNum");
            return (Criteria) this;
        }

        public Criteria andCustomsFormTypeIsNull() {
            addCriterion("customsFormType is null");
            return (Criteria) this;
        }

        public Criteria andCustomsFormTypeIsNotNull() {
            addCriterion("customsFormType is not null");
            return (Criteria) this;
        }

        public Criteria andCustomsFormTypeEqualTo(String value) {
            addCriterion("customsFormType =", value, "customsFormType");
            return (Criteria) this;
        }

        public Criteria andCustomsFormTypeNotEqualTo(String value) {
            addCriterion("customsFormType <>", value, "customsFormType");
            return (Criteria) this;
        }

        public Criteria andCustomsFormTypeGreaterThan(String value) {
            addCriterion("customsFormType >", value, "customsFormType");
            return (Criteria) this;
        }

        public Criteria andCustomsFormTypeGreaterThanOrEqualTo(String value) {
            addCriterion("customsFormType >=", value, "customsFormType");
            return (Criteria) this;
        }

        public Criteria andCustomsFormTypeLessThan(String value) {
            addCriterion("customsFormType <", value, "customsFormType");
            return (Criteria) this;
        }

        public Criteria andCustomsFormTypeLessThanOrEqualTo(String value) {
            addCriterion("customsFormType <=", value, "customsFormType");
            return (Criteria) this;
        }

        public Criteria andCustomsFormTypeLike(String value) {
            addCriterion("customsFormType like", value, "customsFormType");
            return (Criteria) this;
        }

        public Criteria andCustomsFormTypeNotLike(String value) {
            addCriterion("customsFormType not like", value, "customsFormType");
            return (Criteria) this;
        }

        public Criteria andCustomsFormTypeIn(List<String> values) {
            addCriterion("customsFormType in", values, "customsFormType");
            return (Criteria) this;
        }

        public Criteria andCustomsFormTypeNotIn(List<String> values) {
            addCriterion("customsFormType not in", values, "customsFormType");
            return (Criteria) this;
        }

        public Criteria andCustomsFormTypeBetween(String value1, String value2) {
            addCriterion("customsFormType between", value1, value2, "customsFormType");
            return (Criteria) this;
        }

        public Criteria andCustomsFormTypeNotBetween(String value1, String value2) {
            addCriterion("customsFormType not between", value1, value2, "customsFormType");
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

        public Criteria andDeliverAmountIsNull() {
            addCriterion("deliverAmount is null");
            return (Criteria) this;
        }

        public Criteria andDeliverAmountIsNotNull() {
            addCriterion("deliverAmount is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverAmountEqualTo(String value) {
            addCriterion("deliverAmount =", value, "deliverAmount");
            return (Criteria) this;
        }

        public Criteria andDeliverAmountNotEqualTo(String value) {
            addCriterion("deliverAmount <>", value, "deliverAmount");
            return (Criteria) this;
        }

        public Criteria andDeliverAmountGreaterThan(String value) {
            addCriterion("deliverAmount >", value, "deliverAmount");
            return (Criteria) this;
        }

        public Criteria andDeliverAmountGreaterThanOrEqualTo(String value) {
            addCriterion("deliverAmount >=", value, "deliverAmount");
            return (Criteria) this;
        }

        public Criteria andDeliverAmountLessThan(String value) {
            addCriterion("deliverAmount <", value, "deliverAmount");
            return (Criteria) this;
        }

        public Criteria andDeliverAmountLessThanOrEqualTo(String value) {
            addCriterion("deliverAmount <=", value, "deliverAmount");
            return (Criteria) this;
        }

        public Criteria andDeliverAmountLike(String value) {
            addCriterion("deliverAmount like", value, "deliverAmount");
            return (Criteria) this;
        }

        public Criteria andDeliverAmountNotLike(String value) {
            addCriterion("deliverAmount not like", value, "deliverAmount");
            return (Criteria) this;
        }

        public Criteria andDeliverAmountIn(List<String> values) {
            addCriterion("deliverAmount in", values, "deliverAmount");
            return (Criteria) this;
        }

        public Criteria andDeliverAmountNotIn(List<String> values) {
            addCriterion("deliverAmount not in", values, "deliverAmount");
            return (Criteria) this;
        }

        public Criteria andDeliverAmountBetween(String value1, String value2) {
            addCriterion("deliverAmount between", value1, value2, "deliverAmount");
            return (Criteria) this;
        }

        public Criteria andDeliverAmountNotBetween(String value1, String value2) {
            addCriterion("deliverAmount not between", value1, value2, "deliverAmount");
            return (Criteria) this;
        }

        public Criteria andAddedTaxIsNull() {
            addCriterion("addedTax is null");
            return (Criteria) this;
        }

        public Criteria andAddedTaxIsNotNull() {
            addCriterion("addedTax is not null");
            return (Criteria) this;
        }

        public Criteria andAddedTaxEqualTo(String value) {
            addCriterion("addedTax =", value, "addedTax");
            return (Criteria) this;
        }

        public Criteria andAddedTaxNotEqualTo(String value) {
            addCriterion("addedTax <>", value, "addedTax");
            return (Criteria) this;
        }

        public Criteria andAddedTaxGreaterThan(String value) {
            addCriterion("addedTax >", value, "addedTax");
            return (Criteria) this;
        }

        public Criteria andAddedTaxGreaterThanOrEqualTo(String value) {
            addCriterion("addedTax >=", value, "addedTax");
            return (Criteria) this;
        }

        public Criteria andAddedTaxLessThan(String value) {
            addCriterion("addedTax <", value, "addedTax");
            return (Criteria) this;
        }

        public Criteria andAddedTaxLessThanOrEqualTo(String value) {
            addCriterion("addedTax <=", value, "addedTax");
            return (Criteria) this;
        }

        public Criteria andAddedTaxLike(String value) {
            addCriterion("addedTax like", value, "addedTax");
            return (Criteria) this;
        }

        public Criteria andAddedTaxNotLike(String value) {
            addCriterion("addedTax not like", value, "addedTax");
            return (Criteria) this;
        }

        public Criteria andAddedTaxIn(List<String> values) {
            addCriterion("addedTax in", values, "addedTax");
            return (Criteria) this;
        }

        public Criteria andAddedTaxNotIn(List<String> values) {
            addCriterion("addedTax not in", values, "addedTax");
            return (Criteria) this;
        }

        public Criteria andAddedTaxBetween(String value1, String value2) {
            addCriterion("addedTax between", value1, value2, "addedTax");
            return (Criteria) this;
        }

        public Criteria andAddedTaxNotBetween(String value1, String value2) {
            addCriterion("addedTax not between", value1, value2, "addedTax");
            return (Criteria) this;
        }

        public Criteria andCustomsAmountIsNull() {
            addCriterion("customsAmount is null");
            return (Criteria) this;
        }

        public Criteria andCustomsAmountIsNotNull() {
            addCriterion("customsAmount is not null");
            return (Criteria) this;
        }

        public Criteria andCustomsAmountEqualTo(String value) {
            addCriterion("customsAmount =", value, "customsAmount");
            return (Criteria) this;
        }

        public Criteria andCustomsAmountNotEqualTo(String value) {
            addCriterion("customsAmount <>", value, "customsAmount");
            return (Criteria) this;
        }

        public Criteria andCustomsAmountGreaterThan(String value) {
            addCriterion("customsAmount >", value, "customsAmount");
            return (Criteria) this;
        }

        public Criteria andCustomsAmountGreaterThanOrEqualTo(String value) {
            addCriterion("customsAmount >=", value, "customsAmount");
            return (Criteria) this;
        }

        public Criteria andCustomsAmountLessThan(String value) {
            addCriterion("customsAmount <", value, "customsAmount");
            return (Criteria) this;
        }

        public Criteria andCustomsAmountLessThanOrEqualTo(String value) {
            addCriterion("customsAmount <=", value, "customsAmount");
            return (Criteria) this;
        }

        public Criteria andCustomsAmountLike(String value) {
            addCriterion("customsAmount like", value, "customsAmount");
            return (Criteria) this;
        }

        public Criteria andCustomsAmountNotLike(String value) {
            addCriterion("customsAmount not like", value, "customsAmount");
            return (Criteria) this;
        }

        public Criteria andCustomsAmountIn(List<String> values) {
            addCriterion("customsAmount in", values, "customsAmount");
            return (Criteria) this;
        }

        public Criteria andCustomsAmountNotIn(List<String> values) {
            addCriterion("customsAmount not in", values, "customsAmount");
            return (Criteria) this;
        }

        public Criteria andCustomsAmountBetween(String value1, String value2) {
            addCriterion("customsAmount between", value1, value2, "customsAmount");
            return (Criteria) this;
        }

        public Criteria andCustomsAmountNotBetween(String value1, String value2) {
            addCriterion("customsAmount not between", value1, value2, "customsAmount");
            return (Criteria) this;
        }

        public Criteria andShipNumberIsNull() {
            addCriterion("shipNumber is null");
            return (Criteria) this;
        }

        public Criteria andShipNumberIsNotNull() {
            addCriterion("shipNumber is not null");
            return (Criteria) this;
        }

        public Criteria andShipNumberEqualTo(String value) {
            addCriterion("shipNumber =", value, "shipNumber");
            return (Criteria) this;
        }

        public Criteria andShipNumberNotEqualTo(String value) {
            addCriterion("shipNumber <>", value, "shipNumber");
            return (Criteria) this;
        }

        public Criteria andShipNumberGreaterThan(String value) {
            addCriterion("shipNumber >", value, "shipNumber");
            return (Criteria) this;
        }

        public Criteria andShipNumberGreaterThanOrEqualTo(String value) {
            addCriterion("shipNumber >=", value, "shipNumber");
            return (Criteria) this;
        }

        public Criteria andShipNumberLessThan(String value) {
            addCriterion("shipNumber <", value, "shipNumber");
            return (Criteria) this;
        }

        public Criteria andShipNumberLessThanOrEqualTo(String value) {
            addCriterion("shipNumber <=", value, "shipNumber");
            return (Criteria) this;
        }

        public Criteria andShipNumberLike(String value) {
            addCriterion("shipNumber like", value, "shipNumber");
            return (Criteria) this;
        }

        public Criteria andShipNumberNotLike(String value) {
            addCriterion("shipNumber not like", value, "shipNumber");
            return (Criteria) this;
        }

        public Criteria andShipNumberIn(List<String> values) {
            addCriterion("shipNumber in", values, "shipNumber");
            return (Criteria) this;
        }

        public Criteria andShipNumberNotIn(List<String> values) {
            addCriterion("shipNumber not in", values, "shipNumber");
            return (Criteria) this;
        }

        public Criteria andShipNumberBetween(String value1, String value2) {
            addCriterion("shipNumber between", value1, value2, "shipNumber");
            return (Criteria) this;
        }

        public Criteria andShipNumberNotBetween(String value1, String value2) {
            addCriterion("shipNumber not between", value1, value2, "shipNumber");
            return (Criteria) this;
        }

        public Criteria andPlayArrivalDateIsNull() {
            addCriterion("playArrivalDate is null");
            return (Criteria) this;
        }

        public Criteria andPlayArrivalDateIsNotNull() {
            addCriterion("playArrivalDate is not null");
            return (Criteria) this;
        }

        public Criteria andPlayArrivalDateEqualTo(Date value) {
            addCriterion("playArrivalDate =", value, "playArrivalDate");
            return (Criteria) this;
        }

        public Criteria andPlayArrivalDateNotEqualTo(Date value) {
            addCriterion("playArrivalDate <>", value, "playArrivalDate");
            return (Criteria) this;
        }

        public Criteria andPlayArrivalDateGreaterThan(Date value) {
            addCriterion("playArrivalDate >", value, "playArrivalDate");
            return (Criteria) this;
        }

        public Criteria andPlayArrivalDateGreaterThanOrEqualTo(Date value) {
            addCriterion("playArrivalDate >=", value, "playArrivalDate");
            return (Criteria) this;
        }

        public Criteria andPlayArrivalDateLessThan(Date value) {
            addCriterion("playArrivalDate <", value, "playArrivalDate");
            return (Criteria) this;
        }

        public Criteria andPlayArrivalDateLessThanOrEqualTo(Date value) {
            addCriterion("playArrivalDate <=", value, "playArrivalDate");
            return (Criteria) this;
        }

        public Criteria andPlayArrivalDateIn(List<Date> values) {
            addCriterion("playArrivalDate in", values, "playArrivalDate");
            return (Criteria) this;
        }

        public Criteria andPlayArrivalDateNotIn(List<Date> values) {
            addCriterion("playArrivalDate not in", values, "playArrivalDate");
            return (Criteria) this;
        }

        public Criteria andPlayArrivalDateBetween(Date value1, Date value2) {
            addCriterion("playArrivalDate between", value1, value2, "playArrivalDate");
            return (Criteria) this;
        }

        public Criteria andPlayArrivalDateNotBetween(Date value1, Date value2) {
            addCriterion("playArrivalDate not between", value1, value2, "playArrivalDate");
            return (Criteria) this;
        }

        public Criteria andPortIsNull() {
            addCriterion("port is null");
            return (Criteria) this;
        }

        public Criteria andPortIsNotNull() {
            addCriterion("port is not null");
            return (Criteria) this;
        }

        public Criteria andPortEqualTo(String value) {
            addCriterion("port =", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotEqualTo(String value) {
            addCriterion("port <>", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThan(String value) {
            addCriterion("port >", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThanOrEqualTo(String value) {
            addCriterion("port >=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThan(String value) {
            addCriterion("port <", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThanOrEqualTo(String value) {
            addCriterion("port <=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLike(String value) {
            addCriterion("port like", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotLike(String value) {
            addCriterion("port not like", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortIn(List<String> values) {
            addCriterion("port in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotIn(List<String> values) {
            addCriterion("port not in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortBetween(String value1, String value2) {
            addCriterion("port between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotBetween(String value1, String value2) {
            addCriterion("port not between", value1, value2, "port");
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