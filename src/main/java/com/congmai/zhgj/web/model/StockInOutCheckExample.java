package com.congmai.zhgj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class StockInOutCheckExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StockInOutCheckExample() {
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

        public Criteria andTakeDeliverSerialIsNull() {
            addCriterion("takeDeliverSerial is null");
            return (Criteria) this;
        }

        public Criteria andTakeDeliverSerialIsNotNull() {
            addCriterion("takeDeliverSerial is not null");
            return (Criteria) this;
        }

        public Criteria andTakeDeliverSerialEqualTo(String value) {
            addCriterion("takeDeliverSerial =", value, "takeDeliverSerial");
            return (Criteria) this;
        }

        public Criteria andTakeDeliverSerialNotEqualTo(String value) {
            addCriterion("takeDeliverSerial <>", value, "takeDeliverSerial");
            return (Criteria) this;
        }

        public Criteria andTakeDeliverSerialGreaterThan(String value) {
            addCriterion("takeDeliverSerial >", value, "takeDeliverSerial");
            return (Criteria) this;
        }

        public Criteria andTakeDeliverSerialGreaterThanOrEqualTo(String value) {
            addCriterion("takeDeliverSerial >=", value, "takeDeliverSerial");
            return (Criteria) this;
        }

        public Criteria andTakeDeliverSerialLessThan(String value) {
            addCriterion("takeDeliverSerial <", value, "takeDeliverSerial");
            return (Criteria) this;
        }

        public Criteria andTakeDeliverSerialLessThanOrEqualTo(String value) {
            addCriterion("takeDeliverSerial <=", value, "takeDeliverSerial");
            return (Criteria) this;
        }

        public Criteria andTakeDeliverSerialLike(String value) {
            addCriterion("takeDeliverSerial like", value, "takeDeliverSerial");
            return (Criteria) this;
        }

        public Criteria andTakeDeliverSerialNotLike(String value) {
            addCriterion("takeDeliverSerial not like", value, "takeDeliverSerial");
            return (Criteria) this;
        }

        public Criteria andTakeDeliverSerialIn(List<String> values) {
            addCriterion("takeDeliverSerial in", values, "takeDeliverSerial");
            return (Criteria) this;
        }

        public Criteria andTakeDeliverSerialNotIn(List<String> values) {
            addCriterion("takeDeliverSerial not in", values, "takeDeliverSerial");
            return (Criteria) this;
        }

        public Criteria andTakeDeliverSerialBetween(String value1, String value2) {
            addCriterion("takeDeliverSerial between", value1, value2, "takeDeliverSerial");
            return (Criteria) this;
        }

        public Criteria andTakeDeliverSerialNotBetween(String value1, String value2) {
            addCriterion("takeDeliverSerial not between", value1, value2, "takeDeliverSerial");
            return (Criteria) this;
        }

        public Criteria andCheckNumIsNull() {
            addCriterion("checkNum is null");
            return (Criteria) this;
        }

        public Criteria andCheckNumIsNotNull() {
            addCriterion("checkNum is not null");
            return (Criteria) this;
        }

        public Criteria andCheckNumEqualTo(String value) {
            addCriterion("checkNum =", value, "checkNum");
            return (Criteria) this;
        }

        public Criteria andCheckNumNotEqualTo(String value) {
            addCriterion("checkNum <>", value, "checkNum");
            return (Criteria) this;
        }

        public Criteria andCheckNumGreaterThan(String value) {
            addCriterion("checkNum >", value, "checkNum");
            return (Criteria) this;
        }

        public Criteria andCheckNumGreaterThanOrEqualTo(String value) {
            addCriterion("checkNum >=", value, "checkNum");
            return (Criteria) this;
        }

        public Criteria andCheckNumLessThan(String value) {
            addCriterion("checkNum <", value, "checkNum");
            return (Criteria) this;
        }

        public Criteria andCheckNumLessThanOrEqualTo(String value) {
            addCriterion("checkNum <=", value, "checkNum");
            return (Criteria) this;
        }

        public Criteria andCheckNumLike(String value) {
            addCriterion("checkNum like", value, "checkNum");
            return (Criteria) this;
        }

        public Criteria andCheckNumNotLike(String value) {
            addCriterion("checkNum not like", value, "checkNum");
            return (Criteria) this;
        }

        public Criteria andCheckNumIn(List<String> values) {
            addCriterion("checkNum in", values, "checkNum");
            return (Criteria) this;
        }

        public Criteria andCheckNumNotIn(List<String> values) {
            addCriterion("checkNum not in", values, "checkNum");
            return (Criteria) this;
        }

        public Criteria andCheckNumBetween(String value1, String value2) {
            addCriterion("checkNum between", value1, value2, "checkNum");
            return (Criteria) this;
        }

        public Criteria andCheckNumNotBetween(String value1, String value2) {
            addCriterion("checkNum not between", value1, value2, "checkNum");
            return (Criteria) this;
        }

        public Criteria andCheckPartyIsNull() {
            addCriterion("checkParty is null");
            return (Criteria) this;
        }

        public Criteria andCheckPartyIsNotNull() {
            addCriterion("checkParty is not null");
            return (Criteria) this;
        }

        public Criteria andCheckPartyEqualTo(String value) {
            addCriterion("checkParty =", value, "checkParty");
            return (Criteria) this;
        }

        public Criteria andCheckPartyNotEqualTo(String value) {
            addCriterion("checkParty <>", value, "checkParty");
            return (Criteria) this;
        }

        public Criteria andCheckPartyGreaterThan(String value) {
            addCriterion("checkParty >", value, "checkParty");
            return (Criteria) this;
        }

        public Criteria andCheckPartyGreaterThanOrEqualTo(String value) {
            addCriterion("checkParty >=", value, "checkParty");
            return (Criteria) this;
        }

        public Criteria andCheckPartyLessThan(String value) {
            addCriterion("checkParty <", value, "checkParty");
            return (Criteria) this;
        }

        public Criteria andCheckPartyLessThanOrEqualTo(String value) {
            addCriterion("checkParty <=", value, "checkParty");
            return (Criteria) this;
        }

        public Criteria andCheckPartyLike(String value) {
            addCriterion("checkParty like", value, "checkParty");
            return (Criteria) this;
        }

        public Criteria andCheckPartyNotLike(String value) {
            addCriterion("checkParty not like", value, "checkParty");
            return (Criteria) this;
        }

        public Criteria andCheckPartyIn(List<String> values) {
            addCriterion("checkParty in", values, "checkParty");
            return (Criteria) this;
        }

        public Criteria andCheckPartyNotIn(List<String> values) {
            addCriterion("checkParty not in", values, "checkParty");
            return (Criteria) this;
        }

        public Criteria andCheckPartyBetween(String value1, String value2) {
            addCriterion("checkParty between", value1, value2, "checkParty");
            return (Criteria) this;
        }

        public Criteria andCheckPartyNotBetween(String value1, String value2) {
            addCriterion("checkParty not between", value1, value2, "checkParty");
            return (Criteria) this;
        }

        public Criteria andCheckDateIsNull() {
            addCriterion("checkDate is null");
            return (Criteria) this;
        }

        public Criteria andCheckDateIsNotNull() {
            addCriterion("checkDate is not null");
            return (Criteria) this;
        }

        public Criteria andCheckDateEqualTo(Date value) {
            addCriterionForJDBCDate("checkDate =", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("checkDate <>", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateGreaterThan(Date value) {
            addCriterionForJDBCDate("checkDate >", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("checkDate >=", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateLessThan(Date value) {
            addCriterionForJDBCDate("checkDate <", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("checkDate <=", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateIn(List<Date> values) {
            addCriterionForJDBCDate("checkDate in", values, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("checkDate not in", values, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("checkDate between", value1, value2, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("checkDate not between", value1, value2, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckerIsNull() {
            addCriterion("checker is null");
            return (Criteria) this;
        }

        public Criteria andCheckerIsNotNull() {
            addCriterion("checker is not null");
            return (Criteria) this;
        }

        public Criteria andCheckerEqualTo(String value) {
            addCriterion("checker =", value, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerNotEqualTo(String value) {
            addCriterion("checker <>", value, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerGreaterThan(String value) {
            addCriterion("checker >", value, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerGreaterThanOrEqualTo(String value) {
            addCriterion("checker >=", value, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerLessThan(String value) {
            addCriterion("checker <", value, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerLessThanOrEqualTo(String value) {
            addCriterion("checker <=", value, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerLike(String value) {
            addCriterion("checker like", value, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerNotLike(String value) {
            addCriterion("checker not like", value, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerIn(List<String> values) {
            addCriterion("checker in", values, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerNotIn(List<String> values) {
            addCriterion("checker not in", values, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerBetween(String value1, String value2) {
            addCriterion("checker between", value1, value2, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerNotBetween(String value1, String value2) {
            addCriterion("checker not between", value1, value2, "checker");
            return (Criteria) this;
        }

        public Criteria andContactNumIsNull() {
            addCriterion("contactNum is null");
            return (Criteria) this;
        }

        public Criteria andContactNumIsNotNull() {
            addCriterion("contactNum is not null");
            return (Criteria) this;
        }

        public Criteria andContactNumEqualTo(String value) {
            addCriterion("contactNum =", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumNotEqualTo(String value) {
            addCriterion("contactNum <>", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumGreaterThan(String value) {
            addCriterion("contactNum >", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumGreaterThanOrEqualTo(String value) {
            addCriterion("contactNum >=", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumLessThan(String value) {
            addCriterion("contactNum <", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumLessThanOrEqualTo(String value) {
            addCriterion("contactNum <=", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumLike(String value) {
            addCriterion("contactNum like", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumNotLike(String value) {
            addCriterion("contactNum not like", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumIn(List<String> values) {
            addCriterion("contactNum in", values, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumNotIn(List<String> values) {
            addCriterion("contactNum not in", values, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumBetween(String value1, String value2) {
            addCriterion("contactNum between", value1, value2, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumNotBetween(String value1, String value2) {
            addCriterion("contactNum not between", value1, value2, "contactNum");
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