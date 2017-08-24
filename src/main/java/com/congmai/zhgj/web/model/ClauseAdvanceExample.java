package com.congmai.zhgj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClauseAdvanceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ClauseAdvanceExample() {
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

        public Criteria andFinancingIsNull() {
            addCriterion("financing is null");
            return (Criteria) this;
        }

        public Criteria andFinancingIsNotNull() {
            addCriterion("financing is not null");
            return (Criteria) this;
        }

        public Criteria andFinancingEqualTo(String value) {
            addCriterion("financing =", value, "financing");
            return (Criteria) this;
        }

        public Criteria andFinancingNotEqualTo(String value) {
            addCriterion("financing <>", value, "financing");
            return (Criteria) this;
        }

        public Criteria andFinancingGreaterThan(String value) {
            addCriterion("financing >", value, "financing");
            return (Criteria) this;
        }

        public Criteria andFinancingGreaterThanOrEqualTo(String value) {
            addCriterion("financing >=", value, "financing");
            return (Criteria) this;
        }

        public Criteria andFinancingLessThan(String value) {
            addCriterion("financing <", value, "financing");
            return (Criteria) this;
        }

        public Criteria andFinancingLessThanOrEqualTo(String value) {
            addCriterion("financing <=", value, "financing");
            return (Criteria) this;
        }

        public Criteria andFinancingLike(String value) {
            addCriterion("financing like", value, "financing");
            return (Criteria) this;
        }

        public Criteria andFinancingNotLike(String value) {
            addCriterion("financing not like", value, "financing");
            return (Criteria) this;
        }

        public Criteria andFinancingIn(List<String> values) {
            addCriterion("financing in", values, "financing");
            return (Criteria) this;
        }

        public Criteria andFinancingNotIn(List<String> values) {
            addCriterion("financing not in", values, "financing");
            return (Criteria) this;
        }

        public Criteria andFinancingBetween(String value1, String value2) {
            addCriterion("financing between", value1, value2, "financing");
            return (Criteria) this;
        }

        public Criteria andFinancingNotBetween(String value1, String value2) {
            addCriterion("financing not between", value1, value2, "financing");
            return (Criteria) this;
        }

        public Criteria andAdvanceIsNull() {
            addCriterion("advance is null");
            return (Criteria) this;
        }

        public Criteria andAdvanceIsNotNull() {
            addCriterion("advance is not null");
            return (Criteria) this;
        }

        public Criteria andAdvanceEqualTo(String value) {
            addCriterion("advance =", value, "advance");
            return (Criteria) this;
        }

        public Criteria andAdvanceNotEqualTo(String value) {
            addCriterion("advance <>", value, "advance");
            return (Criteria) this;
        }

        public Criteria andAdvanceGreaterThan(String value) {
            addCriterion("advance >", value, "advance");
            return (Criteria) this;
        }

        public Criteria andAdvanceGreaterThanOrEqualTo(String value) {
            addCriterion("advance >=", value, "advance");
            return (Criteria) this;
        }

        public Criteria andAdvanceLessThan(String value) {
            addCriterion("advance <", value, "advance");
            return (Criteria) this;
        }

        public Criteria andAdvanceLessThanOrEqualTo(String value) {
            addCriterion("advance <=", value, "advance");
            return (Criteria) this;
        }

        public Criteria andAdvanceLike(String value) {
            addCriterion("advance like", value, "advance");
            return (Criteria) this;
        }

        public Criteria andAdvanceNotLike(String value) {
            addCriterion("advance not like", value, "advance");
            return (Criteria) this;
        }

        public Criteria andAdvanceIn(List<String> values) {
            addCriterion("advance in", values, "advance");
            return (Criteria) this;
        }

        public Criteria andAdvanceNotIn(List<String> values) {
            addCriterion("advance not in", values, "advance");
            return (Criteria) this;
        }

        public Criteria andAdvanceBetween(String value1, String value2) {
            addCriterion("advance between", value1, value2, "advance");
            return (Criteria) this;
        }

        public Criteria andAdvanceNotBetween(String value1, String value2) {
            addCriterion("advance not between", value1, value2, "advance");
            return (Criteria) this;
        }

        public Criteria andAdvanceAmountIsNull() {
            addCriterion("advanceAmount is null");
            return (Criteria) this;
        }

        public Criteria andAdvanceAmountIsNotNull() {
            addCriterion("advanceAmount is not null");
            return (Criteria) this;
        }

        public Criteria andAdvanceAmountEqualTo(String value) {
            addCriterion("advanceAmount =", value, "advanceAmount");
            return (Criteria) this;
        }

        public Criteria andAdvanceAmountNotEqualTo(String value) {
            addCriterion("advanceAmount <>", value, "advanceAmount");
            return (Criteria) this;
        }

        public Criteria andAdvanceAmountGreaterThan(String value) {
            addCriterion("advanceAmount >", value, "advanceAmount");
            return (Criteria) this;
        }

        public Criteria andAdvanceAmountGreaterThanOrEqualTo(String value) {
            addCriterion("advanceAmount >=", value, "advanceAmount");
            return (Criteria) this;
        }

        public Criteria andAdvanceAmountLessThan(String value) {
            addCriterion("advanceAmount <", value, "advanceAmount");
            return (Criteria) this;
        }

        public Criteria andAdvanceAmountLessThanOrEqualTo(String value) {
            addCriterion("advanceAmount <=", value, "advanceAmount");
            return (Criteria) this;
        }

        public Criteria andAdvanceAmountLike(String value) {
            addCriterion("advanceAmount like", value, "advanceAmount");
            return (Criteria) this;
        }

        public Criteria andAdvanceAmountNotLike(String value) {
            addCriterion("advanceAmount not like", value, "advanceAmount");
            return (Criteria) this;
        }

        public Criteria andAdvanceAmountIn(List<String> values) {
            addCriterion("advanceAmount in", values, "advanceAmount");
            return (Criteria) this;
        }

        public Criteria andAdvanceAmountNotIn(List<String> values) {
            addCriterion("advanceAmount not in", values, "advanceAmount");
            return (Criteria) this;
        }

        public Criteria andAdvanceAmountBetween(String value1, String value2) {
            addCriterion("advanceAmount between", value1, value2, "advanceAmount");
            return (Criteria) this;
        }

        public Criteria andAdvanceAmountNotBetween(String value1, String value2) {
            addCriterion("advanceAmount not between", value1, value2, "advanceAmount");
            return (Criteria) this;
        }

        public Criteria andServiceAmountRateIsNull() {
            addCriterion("serviceAmountRate is null");
            return (Criteria) this;
        }

        public Criteria andServiceAmountRateIsNotNull() {
            addCriterion("serviceAmountRate is not null");
            return (Criteria) this;
        }

        public Criteria andServiceAmountRateEqualTo(String value) {
            addCriterion("serviceAmountRate =", value, "serviceAmountRate");
            return (Criteria) this;
        }

        public Criteria andServiceAmountRateNotEqualTo(String value) {
            addCriterion("serviceAmountRate <>", value, "serviceAmountRate");
            return (Criteria) this;
        }

        public Criteria andServiceAmountRateGreaterThan(String value) {
            addCriterion("serviceAmountRate >", value, "serviceAmountRate");
            return (Criteria) this;
        }

        public Criteria andServiceAmountRateGreaterThanOrEqualTo(String value) {
            addCriterion("serviceAmountRate >=", value, "serviceAmountRate");
            return (Criteria) this;
        }

        public Criteria andServiceAmountRateLessThan(String value) {
            addCriterion("serviceAmountRate <", value, "serviceAmountRate");
            return (Criteria) this;
        }

        public Criteria andServiceAmountRateLessThanOrEqualTo(String value) {
            addCriterion("serviceAmountRate <=", value, "serviceAmountRate");
            return (Criteria) this;
        }

        public Criteria andServiceAmountRateLike(String value) {
            addCriterion("serviceAmountRate like", value, "serviceAmountRate");
            return (Criteria) this;
        }

        public Criteria andServiceAmountRateNotLike(String value) {
            addCriterion("serviceAmountRate not like", value, "serviceAmountRate");
            return (Criteria) this;
        }

        public Criteria andServiceAmountRateIn(List<String> values) {
            addCriterion("serviceAmountRate in", values, "serviceAmountRate");
            return (Criteria) this;
        }

        public Criteria andServiceAmountRateNotIn(List<String> values) {
            addCriterion("serviceAmountRate not in", values, "serviceAmountRate");
            return (Criteria) this;
        }

        public Criteria andServiceAmountRateBetween(String value1, String value2) {
            addCriterion("serviceAmountRate between", value1, value2, "serviceAmountRate");
            return (Criteria) this;
        }

        public Criteria andServiceAmountRateNotBetween(String value1, String value2) {
            addCriterion("serviceAmountRate not between", value1, value2, "serviceAmountRate");
            return (Criteria) this;
        }

        public Criteria andAdvanceTimeIsNull() {
            addCriterion("advanceTime is null");
            return (Criteria) this;
        }

        public Criteria andAdvanceTimeIsNotNull() {
            addCriterion("advanceTime is not null");
            return (Criteria) this;
        }

        public Criteria andAdvanceTimeEqualTo(Date value) {
            addCriterion("advanceTime =", value, "advanceTime");
            return (Criteria) this;
        }

        public Criteria andAdvanceTimeNotEqualTo(Date value) {
            addCriterion("advanceTime <>", value, "advanceTime");
            return (Criteria) this;
        }

        public Criteria andAdvanceTimeGreaterThan(Date value) {
            addCriterion("advanceTime >", value, "advanceTime");
            return (Criteria) this;
        }

        public Criteria andAdvanceTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("advanceTime >=", value, "advanceTime");
            return (Criteria) this;
        }

        public Criteria andAdvanceTimeLessThan(Date value) {
            addCriterion("advanceTime <", value, "advanceTime");
            return (Criteria) this;
        }

        public Criteria andAdvanceTimeLessThanOrEqualTo(Date value) {
            addCriterion("advanceTime <=", value, "advanceTime");
            return (Criteria) this;
        }

        public Criteria andAdvanceTimeIn(List<Date> values) {
            addCriterion("advanceTime in", values, "advanceTime");
            return (Criteria) this;
        }

        public Criteria andAdvanceTimeNotIn(List<Date> values) {
            addCriterion("advanceTime not in", values, "advanceTime");
            return (Criteria) this;
        }

        public Criteria andAdvanceTimeBetween(Date value1, Date value2) {
            addCriterion("advanceTime between", value1, value2, "advanceTime");
            return (Criteria) this;
        }

        public Criteria andAdvanceTimeNotBetween(Date value1, Date value2) {
            addCriterion("advanceTime not between", value1, value2, "advanceTime");
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

        public Criteria andDepositRateIsNull() {
            addCriterion("depositRate is null");
            return (Criteria) this;
        }

        public Criteria andDepositRateIsNotNull() {
            addCriterion("depositRate is not null");
            return (Criteria) this;
        }

        public Criteria andDepositRateEqualTo(String value) {
            addCriterion("depositRate =", value, "depositRate");
            return (Criteria) this;
        }

        public Criteria andDepositRateNotEqualTo(String value) {
            addCriterion("depositRate <>", value, "depositRate");
            return (Criteria) this;
        }

        public Criteria andDepositRateGreaterThan(String value) {
            addCriterion("depositRate >", value, "depositRate");
            return (Criteria) this;
        }

        public Criteria andDepositRateGreaterThanOrEqualTo(String value) {
            addCriterion("depositRate >=", value, "depositRate");
            return (Criteria) this;
        }

        public Criteria andDepositRateLessThan(String value) {
            addCriterion("depositRate <", value, "depositRate");
            return (Criteria) this;
        }

        public Criteria andDepositRateLessThanOrEqualTo(String value) {
            addCriterion("depositRate <=", value, "depositRate");
            return (Criteria) this;
        }

        public Criteria andDepositRateLike(String value) {
            addCriterion("depositRate like", value, "depositRate");
            return (Criteria) this;
        }

        public Criteria andDepositRateNotLike(String value) {
            addCriterion("depositRate not like", value, "depositRate");
            return (Criteria) this;
        }

        public Criteria andDepositRateIn(List<String> values) {
            addCriterion("depositRate in", values, "depositRate");
            return (Criteria) this;
        }

        public Criteria andDepositRateNotIn(List<String> values) {
            addCriterion("depositRate not in", values, "depositRate");
            return (Criteria) this;
        }

        public Criteria andDepositRateBetween(String value1, String value2) {
            addCriterion("depositRate between", value1, value2, "depositRate");
            return (Criteria) this;
        }

        public Criteria andDepositRateNotBetween(String value1, String value2) {
            addCriterion("depositRate not between", value1, value2, "depositRate");
            return (Criteria) this;
        }

        public Criteria andDepositAmountIsNull() {
            addCriterion("depositAmount is null");
            return (Criteria) this;
        }

        public Criteria andDepositAmountIsNotNull() {
            addCriterion("depositAmount is not null");
            return (Criteria) this;
        }

        public Criteria andDepositAmountEqualTo(String value) {
            addCriterion("depositAmount =", value, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountNotEqualTo(String value) {
            addCriterion("depositAmount <>", value, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountGreaterThan(String value) {
            addCriterion("depositAmount >", value, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountGreaterThanOrEqualTo(String value) {
            addCriterion("depositAmount >=", value, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountLessThan(String value) {
            addCriterion("depositAmount <", value, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountLessThanOrEqualTo(String value) {
            addCriterion("depositAmount <=", value, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountLike(String value) {
            addCriterion("depositAmount like", value, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountNotLike(String value) {
            addCriterion("depositAmount not like", value, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountIn(List<String> values) {
            addCriterion("depositAmount in", values, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountNotIn(List<String> values) {
            addCriterion("depositAmount not in", values, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountBetween(String value1, String value2) {
            addCriterion("depositAmount between", value1, value2, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountNotBetween(String value1, String value2) {
            addCriterion("depositAmount not between", value1, value2, "depositAmount");
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