package com.congmai.zhgj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockOutBatchExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StockOutBatchExample() {
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

        public Criteria andStockOutSerialIsNull() {
            addCriterion("stockOutSerial is null");
            return (Criteria) this;
        }

        public Criteria andStockOutSerialIsNotNull() {
            addCriterion("stockOutSerial is not null");
            return (Criteria) this;
        }

        public Criteria andStockOutSerialEqualTo(String value) {
            addCriterion("stockOutSerial =", value, "stockOutSerial");
            return (Criteria) this;
        }

        public Criteria andStockOutSerialNotEqualTo(String value) {
            addCriterion("stockOutSerial <>", value, "stockOutSerial");
            return (Criteria) this;
        }

        public Criteria andStockOutSerialGreaterThan(String value) {
            addCriterion("stockOutSerial >", value, "stockOutSerial");
            return (Criteria) this;
        }

        public Criteria andStockOutSerialGreaterThanOrEqualTo(String value) {
            addCriterion("stockOutSerial >=", value, "stockOutSerial");
            return (Criteria) this;
        }

        public Criteria andStockOutSerialLessThan(String value) {
            addCriterion("stockOutSerial <", value, "stockOutSerial");
            return (Criteria) this;
        }

        public Criteria andStockOutSerialLessThanOrEqualTo(String value) {
            addCriterion("stockOutSerial <=", value, "stockOutSerial");
            return (Criteria) this;
        }

        public Criteria andStockOutSerialLike(String value) {
            addCriterion("stockOutSerial like", value, "stockOutSerial");
            return (Criteria) this;
        }

        public Criteria andStockOutSerialNotLike(String value) {
            addCriterion("stockOutSerial not like", value, "stockOutSerial");
            return (Criteria) this;
        }

        public Criteria andStockOutSerialIn(List<String> values) {
            addCriterion("stockOutSerial in", values, "stockOutSerial");
            return (Criteria) this;
        }

        public Criteria andStockOutSerialNotIn(List<String> values) {
            addCriterion("stockOutSerial not in", values, "stockOutSerial");
            return (Criteria) this;
        }

        public Criteria andStockOutSerialBetween(String value1, String value2) {
            addCriterion("stockOutSerial between", value1, value2, "stockOutSerial");
            return (Criteria) this;
        }

        public Criteria andStockOutSerialNotBetween(String value1, String value2) {
            addCriterion("stockOutSerial not between", value1, value2, "stockOutSerial");
            return (Criteria) this;
        }

        public Criteria andStockInSerialIsNull() {
            addCriterion("stockInSerial is null");
            return (Criteria) this;
        }

        public Criteria andStockInSerialIsNotNull() {
            addCriterion("stockInSerial is not null");
            return (Criteria) this;
        }

        public Criteria andStockInSerialEqualTo(String value) {
            addCriterion("stockInSerial =", value, "stockInSerial");
            return (Criteria) this;
        }

        public Criteria andStockInSerialNotEqualTo(String value) {
            addCriterion("stockInSerial <>", value, "stockInSerial");
            return (Criteria) this;
        }

        public Criteria andStockInSerialGreaterThan(String value) {
            addCriterion("stockInSerial >", value, "stockInSerial");
            return (Criteria) this;
        }

        public Criteria andStockInSerialGreaterThanOrEqualTo(String value) {
            addCriterion("stockInSerial >=", value, "stockInSerial");
            return (Criteria) this;
        }

        public Criteria andStockInSerialLessThan(String value) {
            addCriterion("stockInSerial <", value, "stockInSerial");
            return (Criteria) this;
        }

        public Criteria andStockInSerialLessThanOrEqualTo(String value) {
            addCriterion("stockInSerial <=", value, "stockInSerial");
            return (Criteria) this;
        }

        public Criteria andStockInSerialLike(String value) {
            addCriterion("stockInSerial like", value, "stockInSerial");
            return (Criteria) this;
        }

        public Criteria andStockInSerialNotLike(String value) {
            addCriterion("stockInSerial not like", value, "stockInSerial");
            return (Criteria) this;
        }

        public Criteria andStockInSerialIn(List<String> values) {
            addCriterion("stockInSerial in", values, "stockInSerial");
            return (Criteria) this;
        }

        public Criteria andStockInSerialNotIn(List<String> values) {
            addCriterion("stockInSerial not in", values, "stockInSerial");
            return (Criteria) this;
        }

        public Criteria andStockInSerialBetween(String value1, String value2) {
            addCriterion("stockInSerial between", value1, value2, "stockInSerial");
            return (Criteria) this;
        }

        public Criteria andStockInSerialNotBetween(String value1, String value2) {
            addCriterion("stockInSerial not between", value1, value2, "stockInSerial");
            return (Criteria) this;
        }

        public Criteria andStockOutMaterielSerialIsNull() {
            addCriterion("stockOutMaterielSerial is null");
            return (Criteria) this;
        }

        public Criteria andStockOutMaterielSerialIsNotNull() {
            addCriterion("stockOutMaterielSerial is not null");
            return (Criteria) this;
        }

        public Criteria andStockOutMaterielSerialEqualTo(String value) {
            addCriterion("stockOutMaterielSerial =", value, "stockOutMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andStockOutMaterielSerialNotEqualTo(String value) {
            addCriterion("stockOutMaterielSerial <>", value, "stockOutMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andStockOutMaterielSerialGreaterThan(String value) {
            addCriterion("stockOutMaterielSerial >", value, "stockOutMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andStockOutMaterielSerialGreaterThanOrEqualTo(String value) {
            addCriterion("stockOutMaterielSerial >=", value, "stockOutMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andStockOutMaterielSerialLessThan(String value) {
            addCriterion("stockOutMaterielSerial <", value, "stockOutMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andStockOutMaterielSerialLessThanOrEqualTo(String value) {
            addCriterion("stockOutMaterielSerial <=", value, "stockOutMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andStockOutMaterielSerialLike(String value) {
            addCriterion("stockOutMaterielSerial like", value, "stockOutMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andStockOutMaterielSerialNotLike(String value) {
            addCriterion("stockOutMaterielSerial not like", value, "stockOutMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andStockOutMaterielSerialIn(List<String> values) {
            addCriterion("stockOutMaterielSerial in", values, "stockOutMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andStockOutMaterielSerialNotIn(List<String> values) {
            addCriterion("stockOutMaterielSerial not in", values, "stockOutMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andStockOutMaterielSerialBetween(String value1, String value2) {
            addCriterion("stockOutMaterielSerial between", value1, value2, "stockOutMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andStockOutMaterielSerialNotBetween(String value1, String value2) {
            addCriterion("stockOutMaterielSerial not between", value1, value2, "stockOutMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andStockInMaterielSerialIsNull() {
            addCriterion("stockInMaterielSerial is null");
            return (Criteria) this;
        }

        public Criteria andStockInMaterielSerialIsNotNull() {
            addCriterion("stockInMaterielSerial is not null");
            return (Criteria) this;
        }

        public Criteria andStockInMaterielSerialEqualTo(String value) {
            addCriterion("stockInMaterielSerial =", value, "stockInMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andStockInMaterielSerialNotEqualTo(String value) {
            addCriterion("stockInMaterielSerial <>", value, "stockInMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andStockInMaterielSerialGreaterThan(String value) {
            addCriterion("stockInMaterielSerial >", value, "stockInMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andStockInMaterielSerialGreaterThanOrEqualTo(String value) {
            addCriterion("stockInMaterielSerial >=", value, "stockInMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andStockInMaterielSerialLessThan(String value) {
            addCriterion("stockInMaterielSerial <", value, "stockInMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andStockInMaterielSerialLessThanOrEqualTo(String value) {
            addCriterion("stockInMaterielSerial <=", value, "stockInMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andStockInMaterielSerialLike(String value) {
            addCriterion("stockInMaterielSerial like", value, "stockInMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andStockInMaterielSerialNotLike(String value) {
            addCriterion("stockInMaterielSerial not like", value, "stockInMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andStockInMaterielSerialIn(List<String> values) {
            addCriterion("stockInMaterielSerial in", values, "stockInMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andStockInMaterielSerialNotIn(List<String> values) {
            addCriterion("stockInMaterielSerial not in", values, "stockInMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andStockInMaterielSerialBetween(String value1, String value2) {
            addCriterion("stockInMaterielSerial between", value1, value2, "stockInMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andStockInMaterielSerialNotBetween(String value1, String value2) {
            addCriterion("stockInMaterielSerial not between", value1, value2, "stockInMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andOutCountIsNull() {
            addCriterion("outCount is null");
            return (Criteria) this;
        }

        public Criteria andOutCountIsNotNull() {
            addCriterion("outCount is not null");
            return (Criteria) this;
        }

        public Criteria andOutCountEqualTo(String value) {
            addCriterion("outCount =", value, "outCount");
            return (Criteria) this;
        }

        public Criteria andOutCountNotEqualTo(String value) {
            addCriterion("outCount <>", value, "outCount");
            return (Criteria) this;
        }

        public Criteria andOutCountGreaterThan(String value) {
            addCriterion("outCount >", value, "outCount");
            return (Criteria) this;
        }

        public Criteria andOutCountGreaterThanOrEqualTo(String value) {
            addCriterion("outCount >=", value, "outCount");
            return (Criteria) this;
        }

        public Criteria andOutCountLessThan(String value) {
            addCriterion("outCount <", value, "outCount");
            return (Criteria) this;
        }

        public Criteria andOutCountLessThanOrEqualTo(String value) {
            addCriterion("outCount <=", value, "outCount");
            return (Criteria) this;
        }

        public Criteria andOutCountLike(String value) {
            addCriterion("outCount like", value, "outCount");
            return (Criteria) this;
        }

        public Criteria andOutCountNotLike(String value) {
            addCriterion("outCount not like", value, "outCount");
            return (Criteria) this;
        }

        public Criteria andOutCountIn(List<String> values) {
            addCriterion("outCount in", values, "outCount");
            return (Criteria) this;
        }

        public Criteria andOutCountNotIn(List<String> values) {
            addCriterion("outCount not in", values, "outCount");
            return (Criteria) this;
        }

        public Criteria andOutCountBetween(String value1, String value2) {
            addCriterion("outCount between", value1, value2, "outCount");
            return (Criteria) this;
        }

        public Criteria andOutCountNotBetween(String value1, String value2) {
            addCriterion("outCount not between", value1, value2, "outCount");
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