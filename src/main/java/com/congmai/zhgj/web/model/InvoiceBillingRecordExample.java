package com.congmai.zhgj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvoiceBillingRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InvoiceBillingRecordExample() {
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

        public Criteria andInvoiceSerialIsNull() {
            addCriterion("invoiceSerial is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceSerialIsNotNull() {
            addCriterion("invoiceSerial is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceSerialEqualTo(String value) {
            addCriterion("invoiceSerial =", value, "invoiceSerial");
            return (Criteria) this;
        }

        public Criteria andInvoiceSerialNotEqualTo(String value) {
            addCriterion("invoiceSerial <>", value, "invoiceSerial");
            return (Criteria) this;
        }

        public Criteria andInvoiceSerialGreaterThan(String value) {
            addCriterion("invoiceSerial >", value, "invoiceSerial");
            return (Criteria) this;
        }

        public Criteria andInvoiceSerialGreaterThanOrEqualTo(String value) {
            addCriterion("invoiceSerial >=", value, "invoiceSerial");
            return (Criteria) this;
        }

        public Criteria andInvoiceSerialLessThan(String value) {
            addCriterion("invoiceSerial <", value, "invoiceSerial");
            return (Criteria) this;
        }

        public Criteria andInvoiceSerialLessThanOrEqualTo(String value) {
            addCriterion("invoiceSerial <=", value, "invoiceSerial");
            return (Criteria) this;
        }

        public Criteria andInvoiceSerialLike(String value) {
            addCriterion("invoiceSerial like", value, "invoiceSerial");
            return (Criteria) this;
        }

        public Criteria andInvoiceSerialNotLike(String value) {
            addCriterion("invoiceSerial not like", value, "invoiceSerial");
            return (Criteria) this;
        }

        public Criteria andInvoiceSerialIn(List<String> values) {
            addCriterion("invoiceSerial in", values, "invoiceSerial");
            return (Criteria) this;
        }

        public Criteria andInvoiceSerialNotIn(List<String> values) {
            addCriterion("invoiceSerial not in", values, "invoiceSerial");
            return (Criteria) this;
        }

        public Criteria andInvoiceSerialBetween(String value1, String value2) {
            addCriterion("invoiceSerial between", value1, value2, "invoiceSerial");
            return (Criteria) this;
        }

        public Criteria andInvoiceSerialNotBetween(String value1, String value2) {
            addCriterion("invoiceSerial not between", value1, value2, "invoiceSerial");
            return (Criteria) this;
        }

        public Criteria andOrderMaterielSerialIsNull() {
            addCriterion("orderMaterielSerial is null");
            return (Criteria) this;
        }

        public Criteria andOrderMaterielSerialIsNotNull() {
            addCriterion("orderMaterielSerial is not null");
            return (Criteria) this;
        }

        public Criteria andOrderMaterielSerialEqualTo(String value) {
            addCriterion("orderMaterielSerial =", value, "orderMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andOrderMaterielSerialNotEqualTo(String value) {
            addCriterion("orderMaterielSerial <>", value, "orderMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andOrderMaterielSerialGreaterThan(String value) {
            addCriterion("orderMaterielSerial >", value, "orderMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andOrderMaterielSerialGreaterThanOrEqualTo(String value) {
            addCriterion("orderMaterielSerial >=", value, "orderMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andOrderMaterielSerialLessThan(String value) {
            addCriterion("orderMaterielSerial <", value, "orderMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andOrderMaterielSerialLessThanOrEqualTo(String value) {
            addCriterion("orderMaterielSerial <=", value, "orderMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andOrderMaterielSerialLike(String value) {
            addCriterion("orderMaterielSerial like", value, "orderMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andOrderMaterielSerialNotLike(String value) {
            addCriterion("orderMaterielSerial not like", value, "orderMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andOrderMaterielSerialIn(List<String> values) {
            addCriterion("orderMaterielSerial in", values, "orderMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andOrderMaterielSerialNotIn(List<String> values) {
            addCriterion("orderMaterielSerial not in", values, "orderMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andOrderMaterielSerialBetween(String value1, String value2) {
            addCriterion("orderMaterielSerial between", value1, value2, "orderMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andOrderMaterielSerialNotBetween(String value1, String value2) {
            addCriterion("orderMaterielSerial not between", value1, value2, "orderMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andBillCountIsNull() {
            addCriterion("billCount is null");
            return (Criteria) this;
        }

        public Criteria andBillCountIsNotNull() {
            addCriterion("billCount is not null");
            return (Criteria) this;
        }

        public Criteria andBillCountEqualTo(String value) {
            addCriterion("billCount =", value, "billCount");
            return (Criteria) this;
        }

        public Criteria andBillCountNotEqualTo(String value) {
            addCriterion("billCount <>", value, "billCount");
            return (Criteria) this;
        }

        public Criteria andBillCountGreaterThan(String value) {
            addCriterion("billCount >", value, "billCount");
            return (Criteria) this;
        }

        public Criteria andBillCountGreaterThanOrEqualTo(String value) {
            addCriterion("billCount >=", value, "billCount");
            return (Criteria) this;
        }

        public Criteria andBillCountLessThan(String value) {
            addCriterion("billCount <", value, "billCount");
            return (Criteria) this;
        }

        public Criteria andBillCountLessThanOrEqualTo(String value) {
            addCriterion("billCount <=", value, "billCount");
            return (Criteria) this;
        }

        public Criteria andBillCountLike(String value) {
            addCriterion("billCount like", value, "billCount");
            return (Criteria) this;
        }

        public Criteria andBillCountNotLike(String value) {
            addCriterion("billCount not like", value, "billCount");
            return (Criteria) this;
        }

        public Criteria andBillCountIn(List<String> values) {
            addCriterion("billCount in", values, "billCount");
            return (Criteria) this;
        }

        public Criteria andBillCountNotIn(List<String> values) {
            addCriterion("billCount not in", values, "billCount");
            return (Criteria) this;
        }

        public Criteria andBillCountBetween(String value1, String value2) {
            addCriterion("billCount between", value1, value2, "billCount");
            return (Criteria) this;
        }

        public Criteria andBillCountNotBetween(String value1, String value2) {
            addCriterion("billCount not between", value1, value2, "billCount");
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