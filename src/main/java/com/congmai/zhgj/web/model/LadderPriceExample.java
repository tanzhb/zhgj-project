package com.congmai.zhgj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LadderPriceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LadderPriceExample() {
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

        public Criteria andPriceSerialIsNull() {
            addCriterion("priceSerial is null");
            return (Criteria) this;
        }

        public Criteria andPriceSerialIsNotNull() {
            addCriterion("priceSerial is not null");
            return (Criteria) this;
        }

        public Criteria andPriceSerialEqualTo(String value) {
            addCriterion("priceSerial =", value, "priceSerial");
            return (Criteria) this;
        }

        public Criteria andPriceSerialNotEqualTo(String value) {
            addCriterion("priceSerial <>", value, "priceSerial");
            return (Criteria) this;
        }

        public Criteria andPriceSerialGreaterThan(String value) {
            addCriterion("priceSerial >", value, "priceSerial");
            return (Criteria) this;
        }

        public Criteria andPriceSerialGreaterThanOrEqualTo(String value) {
            addCriterion("priceSerial >=", value, "priceSerial");
            return (Criteria) this;
        }

        public Criteria andPriceSerialLessThan(String value) {
            addCriterion("priceSerial <", value, "priceSerial");
            return (Criteria) this;
        }

        public Criteria andPriceSerialLessThanOrEqualTo(String value) {
            addCriterion("priceSerial <=", value, "priceSerial");
            return (Criteria) this;
        }

        public Criteria andPriceSerialLike(String value) {
            addCriterion("priceSerial like", value, "priceSerial");
            return (Criteria) this;
        }

        public Criteria andPriceSerialNotLike(String value) {
            addCriterion("priceSerial not like", value, "priceSerial");
            return (Criteria) this;
        }

        public Criteria andPriceSerialIn(List<String> values) {
            addCriterion("priceSerial in", values, "priceSerial");
            return (Criteria) this;
        }

        public Criteria andPriceSerialNotIn(List<String> values) {
            addCriterion("priceSerial not in", values, "priceSerial");
            return (Criteria) this;
        }

        public Criteria andPriceSerialBetween(String value1, String value2) {
            addCriterion("priceSerial between", value1, value2, "priceSerial");
            return (Criteria) this;
        }

        public Criteria andPriceSerialNotBetween(String value1, String value2) {
            addCriterion("priceSerial not between", value1, value2, "priceSerial");
            return (Criteria) this;
        }

        public Criteria andCountStartIsNull() {
            addCriterion("countStart is null");
            return (Criteria) this;
        }

        public Criteria andCountStartIsNotNull() {
            addCriterion("countStart is not null");
            return (Criteria) this;
        }

        public Criteria andCountStartEqualTo(String value) {
            addCriterion("countStart =", value, "countStart");
            return (Criteria) this;
        }

        public Criteria andCountStartNotEqualTo(String value) {
            addCriterion("countStart <>", value, "countStart");
            return (Criteria) this;
        }

        public Criteria andCountStartGreaterThan(String value) {
            addCriterion("countStart >", value, "countStart");
            return (Criteria) this;
        }

        public Criteria andCountStartGreaterThanOrEqualTo(String value) {
            addCriterion("countStart >=", value, "countStart");
            return (Criteria) this;
        }

        public Criteria andCountStartLessThan(String value) {
            addCriterion("countStart <", value, "countStart");
            return (Criteria) this;
        }

        public Criteria andCountStartLessThanOrEqualTo(String value) {
            addCriterion("countStart <=", value, "countStart");
            return (Criteria) this;
        }

        public Criteria andCountStartLike(String value) {
            addCriterion("countStart like", value, "countStart");
            return (Criteria) this;
        }

        public Criteria andCountStartNotLike(String value) {
            addCriterion("countStart not like", value, "countStart");
            return (Criteria) this;
        }

        public Criteria andCountStartIn(List<String> values) {
            addCriterion("countStart in", values, "countStart");
            return (Criteria) this;
        }

        public Criteria andCountStartNotIn(List<String> values) {
            addCriterion("countStart not in", values, "countStart");
            return (Criteria) this;
        }

        public Criteria andCountStartBetween(String value1, String value2) {
            addCriterion("countStart between", value1, value2, "countStart");
            return (Criteria) this;
        }

        public Criteria andCountStartNotBetween(String value1, String value2) {
            addCriterion("countStart not between", value1, value2, "countStart");
            return (Criteria) this;
        }

        public Criteria andCountEndIsNull() {
            addCriterion("countEnd is null");
            return (Criteria) this;
        }

        public Criteria andCountEndIsNotNull() {
            addCriterion("countEnd is not null");
            return (Criteria) this;
        }

        public Criteria andCountEndEqualTo(String value) {
            addCriterion("countEnd =", value, "countEnd");
            return (Criteria) this;
        }

        public Criteria andCountEndNotEqualTo(String value) {
            addCriterion("countEnd <>", value, "countEnd");
            return (Criteria) this;
        }

        public Criteria andCountEndGreaterThan(String value) {
            addCriterion("countEnd >", value, "countEnd");
            return (Criteria) this;
        }

        public Criteria andCountEndGreaterThanOrEqualTo(String value) {
            addCriterion("countEnd >=", value, "countEnd");
            return (Criteria) this;
        }

        public Criteria andCountEndLessThan(String value) {
            addCriterion("countEnd <", value, "countEnd");
            return (Criteria) this;
        }

        public Criteria andCountEndLessThanOrEqualTo(String value) {
            addCriterion("countEnd <=", value, "countEnd");
            return (Criteria) this;
        }

        public Criteria andCountEndLike(String value) {
            addCriterion("countEnd like", value, "countEnd");
            return (Criteria) this;
        }

        public Criteria andCountEndNotLike(String value) {
            addCriterion("countEnd not like", value, "countEnd");
            return (Criteria) this;
        }

        public Criteria andCountEndIn(List<String> values) {
            addCriterion("countEnd in", values, "countEnd");
            return (Criteria) this;
        }

        public Criteria andCountEndNotIn(List<String> values) {
            addCriterion("countEnd not in", values, "countEnd");
            return (Criteria) this;
        }

        public Criteria andCountEndBetween(String value1, String value2) {
            addCriterion("countEnd between", value1, value2, "countEnd");
            return (Criteria) this;
        }

        public Criteria andCountEndNotBetween(String value1, String value2) {
            addCriterion("countEnd not between", value1, value2, "countEnd");
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