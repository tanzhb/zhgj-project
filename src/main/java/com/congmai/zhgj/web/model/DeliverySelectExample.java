package com.congmai.zhgj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DeliverySelectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;
    
    private int pageIndex;
    
    private int pageSize;

    public DeliverySelectExample() {
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
    
    

    public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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
            addCriterion("d.d.serialNum is null");
            return (Criteria) this;
        }

        public Criteria andSerialNumIsNotNull() {
            addCriterion("d.d.serialNum is not null");
            return (Criteria) this;
        }

        public Criteria andSerialNumEqualTo(String value) {
            addCriterion("d.d.serialNum =", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumNotEqualTo(String value) {
            addCriterion("d.d.serialNum <>", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumGreaterThan(String value) {
            addCriterion("d.d.serialNum >", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumGreaterThanOrEqualTo(String value) {
            addCriterion("d.d.serialNum >=", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumLessThan(String value) {
            addCriterion("d.d.serialNum <", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumLessThanOrEqualTo(String value) {
            addCriterion("d.d.serialNum <=", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumLike(String value) {
            addCriterion("d.d.serialNum like", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumNotLike(String value) {
            addCriterion("d.d.serialNum not like", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumIn(List<String> values) {
            addCriterion("d.d.serialNum in", values, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumNotIn(List<String> values) {
            addCriterion("d.d.serialNum not in", values, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumBetween(String value1, String value2) {
            addCriterion("d.d.serialNum between", value1, value2, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumNotBetween(String value1, String value2) {
            addCriterion("d.d.serialNum not between", value1, value2, "serialNum");
            return (Criteria) this;
        }

        public Criteria andDeliverNumIsNull() {
            addCriterion("d.d.deliverNum is null");
            return (Criteria) this;
        }

        public Criteria andDeliverNumIsNotNull() {
            addCriterion("d.d.deliverNum is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverNumEqualTo(String value) {
            addCriterion("d.d.deliverNum =", value, "deliverNum");
            return (Criteria) this;
        }

        public Criteria andDeliverNumNotEqualTo(String value) {
            addCriterion("d.d.deliverNum <>", value, "deliverNum");
            return (Criteria) this;
        }

        public Criteria andDeliverNumGreaterThan(String value) {
            addCriterion("d.d.deliverNum >", value, "deliverNum");
            return (Criteria) this;
        }

        public Criteria andDeliverNumGreaterThanOrEqualTo(String value) {
            addCriterion("d.d.deliverNum >=", value, "deliverNum");
            return (Criteria) this;
        }

        public Criteria andDeliverNumLessThan(String value) {
            addCriterion("d.d.deliverNum <", value, "deliverNum");
            return (Criteria) this;
        }

        public Criteria andDeliverNumLessThanOrEqualTo(String value) {
            addCriterion("d.deliverNum <=", value, "deliverNum");
            return (Criteria) this;
        }

        public Criteria andDeliverNumLike(String value) {
            addCriterion("d.deliverNum like", value, "deliverNum");
            return (Criteria) this;
        }

        public Criteria andDeliverNumNotLike(String value) {
            addCriterion("d.deliverNum not like", value, "deliverNum");
            return (Criteria) this;
        }

        public Criteria andDeliverNumIn(List<String> values) {
            addCriterion("d.deliverNum in", values, "deliverNum");
            return (Criteria) this;
        }

        public Criteria andDeliverNumNotIn(List<String> values) {
            addCriterion("d.deliverNum not in", values, "deliverNum");
            return (Criteria) this;
        }

        public Criteria andDeliverNumBetween(String value1, String value2) {
            addCriterion("d.deliverNum between", value1, value2, "deliverNum");
            return (Criteria) this;
        }

        public Criteria andDeliverNumNotBetween(String value1, String value2) {
            addCriterion("d.deliverNum not between", value1, value2, "deliverNum");
            return (Criteria) this;
        }

        public Criteria andOrderSerialIsNull() {
            addCriterion("d.orderSerial is null");
            return (Criteria) this;
        }

        public Criteria andOrderSerialIsNotNull() {
            addCriterion("d.orderSerial is not null");
            return (Criteria) this;
        }

        public Criteria andOrderSerialEqualTo(String value) {
            addCriterion("d.orderSerial =", value, "orderSerial");
            return (Criteria) this;
        }

        public Criteria andOrderSerialNotEqualTo(String value) {
            addCriterion("d.orderSerial <>", value, "orderSerial");
            return (Criteria) this;
        }

        public Criteria andOrderSerialGreaterThan(String value) {
            addCriterion("d.orderSerial >", value, "orderSerial");
            return (Criteria) this;
        }

        public Criteria andOrderSerialGreaterThanOrEqualTo(String value) {
            addCriterion("d.orderSerial >=", value, "orderSerial");
            return (Criteria) this;
        }

        public Criteria andOrderSerialLessThan(String value) {
            addCriterion("d.orderSerial <", value, "orderSerial");
            return (Criteria) this;
        }

        public Criteria andOrderSerialLessThanOrEqualTo(String value) {
            addCriterion("d.orderSerial <=", value, "orderSerial");
            return (Criteria) this;
        }

        public Criteria andOrderSerialLike(String value) {
            addCriterion("d.orderSerial like", value, "orderSerial");
            return (Criteria) this;
        }

        public Criteria andOrderSerialNotLike(String value) {
            addCriterion("d.orderSerial not like", value, "orderSerial");
            return (Criteria) this;
        }

        public Criteria andOrderSerialIn(List<String> values) {
            addCriterion("d.orderSerial in", values, "orderSerial");
            return (Criteria) this;
        }

        public Criteria andOrderSerialNotIn(List<String> values) {
            addCriterion("d.orderSerial not in", values, "orderSerial");
            return (Criteria) this;
        }

        public Criteria andOrderSerialBetween(String value1, String value2) {
            addCriterion("d.orderSerial between", value1, value2, "orderSerial");
            return (Criteria) this;
        }

        public Criteria andOrderSerialNotBetween(String value1, String value2) {
            addCriterion("d.orderSerial not between", value1, value2, "orderSerial");
            return (Criteria) this;
        }

        public Criteria andSupplyComIdIsNull() {
            addCriterion("d.supplyComId is null");
            return (Criteria) this;
        }

        public Criteria andSupplyComIdIsNotNull() {
            addCriterion("d.supplyComId is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyComIdEqualTo(String value) {
            addCriterion("d.supplyComId =", value, "supplyComId");
            return (Criteria) this;
        }

        public Criteria andSupplyComIdNotEqualTo(String value) {
            addCriterion("d.supplyComId <>", value, "supplyComId");
            return (Criteria) this;
        }

        public Criteria andSupplyComIdGreaterThan(String value) {
            addCriterion("d.supplyComId >", value, "supplyComId");
            return (Criteria) this;
        }

        public Criteria andSupplyComIdGreaterThanOrEqualTo(String value) {
            addCriterion("d.supplyComId >=", value, "supplyComId");
            return (Criteria) this;
        }

        public Criteria andSupplyComIdLessThan(String value) {
            addCriterion("d.supplyComId <", value, "supplyComId");
            return (Criteria) this;
        }

        public Criteria andSupplyComIdLessThanOrEqualTo(String value) {
            addCriterion("d.supplyComId <=", value, "supplyComId");
            return (Criteria) this;
        }

        public Criteria andSupplyComIdLike(String value) {
            addCriterion("d.supplyComId like", value, "supplyComId");
            return (Criteria) this;
        }

        public Criteria andSupplyComIdNotLike(String value) {
            addCriterion("d.supplyComId not like", value, "supplyComId");
            return (Criteria) this;
        }

        public Criteria andSupplyComIdIn(List<String> values) {
            addCriterion("d.supplyComId in", values, "supplyComId");
            return (Criteria) this;
        }

        public Criteria andSupplyComIdNotIn(List<String> values) {
            addCriterion("d.supplyComId not in", values, "supplyComId");
            return (Criteria) this;
        }

        public Criteria andSupplyComIdBetween(String value1, String value2) {
            addCriterion("d.supplyComId between", value1, value2, "supplyComId");
            return (Criteria) this;
        }

        public Criteria andSupplyComIdNotBetween(String value1, String value2) {
            addCriterion("d.supplyComId not between", value1, value2, "supplyComId");
            return (Criteria) this;
        }

        public Criteria andShipperIsNull() {
            addCriterion("d.shipper is null");
            return (Criteria) this;
        }

        public Criteria andShipperIsNotNull() {
            addCriterion("d.shipper is not null");
            return (Criteria) this;
        }

        public Criteria andShipperEqualTo(String value) {
            addCriterion("d.shipper =", value, "shipper");
            return (Criteria) this;
        }

        public Criteria andShipperNotEqualTo(String value) {
            addCriterion("d.shipper <>", value, "shipper");
            return (Criteria) this;
        }

        public Criteria andShipperGreaterThan(String value) {
            addCriterion("d.shipper >", value, "shipper");
            return (Criteria) this;
        }

        public Criteria andShipperGreaterThanOrEqualTo(String value) {
            addCriterion("d.shipper >=", value, "shipper");
            return (Criteria) this;
        }

        public Criteria andShipperLessThan(String value) {
            addCriterion("d.shipper <", value, "shipper");
            return (Criteria) this;
        }

        public Criteria andShipperLessThanOrEqualTo(String value) {
            addCriterion("d.shipper <=", value, "shipper");
            return (Criteria) this;
        }

        public Criteria andShipperLike(String value) {
            addCriterion("d.shipper like", value, "shipper");
            return (Criteria) this;
        }

        public Criteria andShipperNotLike(String value) {
            addCriterion("d.shipper not like", value, "shipper");
            return (Criteria) this;
        }

        public Criteria andShipperIn(List<String> values) {
            addCriterion("d.shipper in", values, "shipper");
            return (Criteria) this;
        }

        public Criteria andShipperNotIn(List<String> values) {
            addCriterion("d.shipper not in", values, "shipper");
            return (Criteria) this;
        }

        public Criteria andShipperBetween(String value1, String value2) {
            addCriterion("d.shipper between", value1, value2, "shipper");
            return (Criteria) this;
        }

        public Criteria andShipperNotBetween(String value1, String value2) {
            addCriterion("d.shipper not between", value1, value2, "shipper");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNull() {
            addCriterion("d.receiver is null");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNotNull() {
            addCriterion("d.receiver is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverEqualTo(String value) {
            addCriterion("d.receiver =", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotEqualTo(String value) {
            addCriterion("d.receiver <>", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThan(String value) {
            addCriterion("d.receiver >", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThanOrEqualTo(String value) {
            addCriterion("d.receiver >=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThan(String value) {
            addCriterion("d.receiver <", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThanOrEqualTo(String value) {
            addCriterion("d.receiver <=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLike(String value) {
            addCriterion("d.receiver like", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotLike(String value) {
            addCriterion("d.receiver not like", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverIn(List<String> values) {
            addCriterion("d.receiver in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotIn(List<String> values) {
            addCriterion("d.receiver not in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverBetween(String value1, String value2) {
            addCriterion("d.receiver between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotBetween(String value1, String value2) {
            addCriterion("d.receiver not between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andMakeDateIsNull() {
            addCriterion("d.makeDate is null");
            return (Criteria) this;
        }

        public Criteria andMakeDateIsNotNull() {
            addCriterion("d.makeDate is not null");
            return (Criteria) this;
        }

        public Criteria andMakeDateEqualTo(Date value) {
            addCriterionForJDBCDate("makeDate =", value, "makeDate");
            return (Criteria) this;
        }

        public Criteria andMakeDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("makeDate <>", value, "makeDate");
            return (Criteria) this;
        }

        public Criteria andMakeDateGreaterThan(Date value) {
            addCriterionForJDBCDate("makeDate >", value, "makeDate");
            return (Criteria) this;
        }

        public Criteria andMakeDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("makeDate >=", value, "makeDate");
            return (Criteria) this;
        }

        public Criteria andMakeDateLessThan(Date value) {
            addCriterionForJDBCDate("makeDate <", value, "makeDate");
            return (Criteria) this;
        }

        public Criteria andMakeDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("makeDate <=", value, "makeDate");
            return (Criteria) this;
        }

        public Criteria andMakeDateIn(List<Date> values) {
            addCriterionForJDBCDate("makeDate in", values, "makeDate");
            return (Criteria) this;
        }

        public Criteria andMakeDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("makeDate not in", values, "makeDate");
            return (Criteria) this;
        }

        public Criteria andMakeDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("makeDate between", value1, value2, "makeDate");
            return (Criteria) this;
        }

        public Criteria andMakeDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("makeDate not between", value1, value2, "makeDate");
            return (Criteria) this;
        }

        public Criteria andMakerIsNull() {
            addCriterion("d.maker is null");
            return (Criteria) this;
        }

        public Criteria andMakerIsNotNull() {
            addCriterion("d.maker is not null");
            return (Criteria) this;
        }

        public Criteria andMakerEqualTo(String value) {
            addCriterion("d.maker =", value, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerNotEqualTo(String value) {
            addCriterion("d.maker <>", value, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerGreaterThan(String value) {
            addCriterion("d.maker >", value, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerGreaterThanOrEqualTo(String value) {
            addCriterion("d.maker >=", value, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerLessThan(String value) {
            addCriterion("d.maker <", value, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerLessThanOrEqualTo(String value) {
            addCriterion("d.maker <=", value, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerLike(String value) {
            addCriterion("d.maker like", value, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerNotLike(String value) {
            addCriterion("d.maker not like", value, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerIn(List<String> values) {
            addCriterion("d.maker in", values, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerNotIn(List<String> values) {
            addCriterion("d.maker not in", values, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerBetween(String value1, String value2) {
            addCriterion("d.maker between", value1, value2, "maker");
            return (Criteria) this;
        }

        public Criteria andMakerNotBetween(String value1, String value2) {
            addCriterion("d.maker not between", value1, value2, "maker");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("d.remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("d.remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("d.remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("d.remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("d.remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("d.remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("d.remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("d.remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("d.remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("d.remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("d.remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("d.remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("d.remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("d.remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("d.status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("d.status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("d.status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("d.status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("d.status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("d.status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("d.status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("d.status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("d.status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("d.status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("d.status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("d.status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("d.status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("d.status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andApprovalIsNull() {
            addCriterion("d.approval is null");
            return (Criteria) this;
        }

        public Criteria andApprovalIsNotNull() {
            addCriterion("d.approval is not null");
            return (Criteria) this;
        }

        public Criteria andApprovalEqualTo(String value) {
            addCriterion("d.approval =", value, "approval");
            return (Criteria) this;
        }

        public Criteria andApprovalNotEqualTo(String value) {
            addCriterion("d.approval <>", value, "approval");
            return (Criteria) this;
        }

        public Criteria andApprovalGreaterThan(String value) {
            addCriterion("d.approval >", value, "approval");
            return (Criteria) this;
        }

        public Criteria andApprovalGreaterThanOrEqualTo(String value) {
            addCriterion("d.approval >=", value, "approval");
            return (Criteria) this;
        }

        public Criteria andApprovalLessThan(String value) {
            addCriterion("d.approval <", value, "approval");
            return (Criteria) this;
        }

        public Criteria andApprovalLessThanOrEqualTo(String value) {
            addCriterion("d.approval <=", value, "approval");
            return (Criteria) this;
        }

        public Criteria andApprovalLike(String value) {
            addCriterion("d.approval like", value, "approval");
            return (Criteria) this;
        }

        public Criteria andApprovalNotLike(String value) {
            addCriterion("d.approval not like", value, "approval");
            return (Criteria) this;
        }

        public Criteria andApprovalIn(List<String> values) {
            addCriterion("d.approval in", values, "approval");
            return (Criteria) this;
        }

        public Criteria andApprovalNotIn(List<String> values) {
            addCriterion("d.approval not in", values, "approval");
            return (Criteria) this;
        }

        public Criteria andApprovalBetween(String value1, String value2) {
            addCriterion("d.approval between", value1, value2, "approval");
            return (Criteria) this;
        }

        public Criteria andApprovalNotBetween(String value1, String value2) {
            addCriterion("d.approval not between", value1, value2, "approval");
            return (Criteria) this;
        }

        public Criteria andApprovalDateIsNull() {
            addCriterion("d.approvalDate is null");
            return (Criteria) this;
        }

        public Criteria andApprovalDateIsNotNull() {
            addCriterion("d.approvalDate is not null");
            return (Criteria) this;
        }

        public Criteria andApprovalDateEqualTo(Date value) {
            addCriterionForJDBCDate("approvalDate =", value, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("approvalDate <>", value, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDateGreaterThan(Date value) {
            addCriterionForJDBCDate("approvalDate >", value, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("approvalDate >=", value, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDateLessThan(Date value) {
            addCriterionForJDBCDate("approvalDate <", value, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("approvalDate <=", value, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDateIn(List<Date> values) {
            addCriterionForJDBCDate("approvalDate in", values, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("approvalDate not in", values, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("approvalDate between", value1, value2, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("approvalDate not between", value1, value2, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialIsNull() {
            addCriterion("d.warehouseSerial is null");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialIsNotNull() {
            addCriterion("d.warehouseSerial is not null");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialEqualTo(String value) {
            addCriterion("d.warehouseSerial =", value, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialNotEqualTo(String value) {
            addCriterion("d.warehouseSerial <>", value, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialGreaterThan(String value) {
            addCriterion("d.warehouseSerial >", value, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialGreaterThanOrEqualTo(String value) {
            addCriterion("d.warehouseSerial >=", value, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialLessThan(String value) {
            addCriterion("d.warehouseSerial <", value, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialLessThanOrEqualTo(String value) {
            addCriterion("d.warehouseSerial <=", value, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialLike(String value) {
            addCriterion("d.warehouseSerial like", value, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialNotLike(String value) {
            addCriterion("d.warehouseSerial not like", value, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialIn(List<String> values) {
            addCriterion("d.warehouseSerial in", values, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialNotIn(List<String> values) {
            addCriterion("d.warehouseSerial not in", values, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialBetween(String value1, String value2) {
            addCriterion("d.warehouseSerial between", value1, value2, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialNotBetween(String value1, String value2) {
            addCriterion("d.warehouseSerial not between", value1, value2, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andMaterielCountIsNull() {
            addCriterion("d.materielCount is null");
            return (Criteria) this;
        }

        public Criteria andMaterielCountIsNotNull() {
            addCriterion("d.materielCount is not null");
            return (Criteria) this;
        }

        public Criteria andMaterielCountEqualTo(String value) {
            addCriterion("d.materielCount =", value, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountNotEqualTo(String value) {
            addCriterion("d.materielCount <>", value, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountGreaterThan(String value) {
            addCriterion("d.materielCount >", value, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountGreaterThanOrEqualTo(String value) {
            addCriterion("d.materielCount >=", value, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountLessThan(String value) {
            addCriterion("d.materielCount <", value, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountLessThanOrEqualTo(String value) {
            addCriterion("d.materielCount <=", value, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountLike(String value) {
            addCriterion("d.materielCount like", value, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountNotLike(String value) {
            addCriterion("d.materielCount not like", value, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountIn(List<String> values) {
            addCriterion("d.materielCount in", values, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountNotIn(List<String> values) {
            addCriterion("d.materielCount not in", values, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountBetween(String value1, String value2) {
            addCriterion("d.materielCount between", value1, value2, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountNotBetween(String value1, String value2) {
            addCriterion("d.materielCount not between", value1, value2, "materielCount");
            return (Criteria) this;
        }

        public Criteria andDeliverDateIsNull() {
            addCriterion("d.deliverDate is null");
            return (Criteria) this;
        }

        public Criteria andDeliverDateIsNotNull() {
            addCriterion("d.deliverDate is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverDateEqualTo(Date value) {
            addCriterionForJDBCDate("deliverDate =", value, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("deliverDate <>", value, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateGreaterThan(Date value) {
            addCriterionForJDBCDate("deliverDate >", value, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("deliverDate >=", value, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateLessThan(Date value) {
            addCriterionForJDBCDate("deliverDate <", value, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("deliverDate <=", value, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateIn(List<Date> values) {
            addCriterionForJDBCDate("deliverDate in", values, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("deliverDate not in", values, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("deliverDate between", value1, value2, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andDeliverDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("deliverDate not between", value1, value2, "deliverDate");
            return (Criteria) this;
        }

        public Criteria andPackageCountIsNull() {
            addCriterion("d.packageCount is null");
            return (Criteria) this;
        }

        public Criteria andPackageCountIsNotNull() {
            addCriterion("d.packageCount is not null");
            return (Criteria) this;
        }

        public Criteria andPackageCountEqualTo(String value) {
            addCriterion("d.packageCount =", value, "packageCount");
            return (Criteria) this;
        }

        public Criteria andPackageCountNotEqualTo(String value) {
            addCriterion("d.packageCount <>", value, "packageCount");
            return (Criteria) this;
        }

        public Criteria andPackageCountGreaterThan(String value) {
            addCriterion("d.packageCount >", value, "packageCount");
            return (Criteria) this;
        }

        public Criteria andPackageCountGreaterThanOrEqualTo(String value) {
            addCriterion("d.packageCount >=", value, "packageCount");
            return (Criteria) this;
        }

        public Criteria andPackageCountLessThan(String value) {
            addCriterion("d.packageCount <", value, "packageCount");
            return (Criteria) this;
        }

        public Criteria andPackageCountLessThanOrEqualTo(String value) {
            addCriterion("d.packageCount <=", value, "packageCount");
            return (Criteria) this;
        }

        public Criteria andPackageCountLike(String value) {
            addCriterion("d.packageCount like", value, "packageCount");
            return (Criteria) this;
        }

        public Criteria andPackageCountNotLike(String value) {
            addCriterion("d.packageCount not like", value, "packageCount");
            return (Criteria) this;
        }

        public Criteria andPackageCountIn(List<String> values) {
            addCriterion("d.packageCount in", values, "packageCount");
            return (Criteria) this;
        }

        public Criteria andPackageCountNotIn(List<String> values) {
            addCriterion("d.packageCount not in", values, "packageCount");
            return (Criteria) this;
        }

        public Criteria andPackageCountBetween(String value1, String value2) {
            addCriterion("d.packageCount between", value1, value2, "packageCount");
            return (Criteria) this;
        }

        public Criteria andPackageCountNotBetween(String value1, String value2) {
            addCriterion("d.packageCount not between", value1, value2, "packageCount");
            return (Criteria) this;
        }

        public Criteria andMaterielWeightIsNull() {
            addCriterion("d.materielWeight is null");
            return (Criteria) this;
        }

        public Criteria andMaterielWeightIsNotNull() {
            addCriterion("d.materielWeight is not null");
            return (Criteria) this;
        }

        public Criteria andMaterielWeightEqualTo(String value) {
            addCriterion("d.materielWeight =", value, "materielWeight");
            return (Criteria) this;
        }

        public Criteria andMaterielWeightNotEqualTo(String value) {
            addCriterion("d.materielWeight <>", value, "materielWeight");
            return (Criteria) this;
        }

        public Criteria andMaterielWeightGreaterThan(String value) {
            addCriterion("d.materielWeight >", value, "materielWeight");
            return (Criteria) this;
        }

        public Criteria andMaterielWeightGreaterThanOrEqualTo(String value) {
            addCriterion("d.materielWeight >=", value, "materielWeight");
            return (Criteria) this;
        }

        public Criteria andMaterielWeightLessThan(String value) {
            addCriterion("d.materielWeight <", value, "materielWeight");
            return (Criteria) this;
        }

        public Criteria andMaterielWeightLessThanOrEqualTo(String value) {
            addCriterion("d.materielWeight <=", value, "materielWeight");
            return (Criteria) this;
        }

        public Criteria andMaterielWeightLike(String value) {
            addCriterion("d.materielWeight like", value, "materielWeight");
            return (Criteria) this;
        }

        public Criteria andMaterielWeightNotLike(String value) {
            addCriterion("d.materielWeight not like", value, "materielWeight");
            return (Criteria) this;
        }

        public Criteria andMaterielWeightIn(List<String> values) {
            addCriterion("d.materielWeight in", values, "materielWeight");
            return (Criteria) this;
        }

        public Criteria andMaterielWeightNotIn(List<String> values) {
            addCriterion("d.materielWeight not in", values, "materielWeight");
            return (Criteria) this;
        }

        public Criteria andMaterielWeightBetween(String value1, String value2) {
            addCriterion("d.materielWeight between", value1, value2, "materielWeight");
            return (Criteria) this;
        }

        public Criteria andMaterielWeightNotBetween(String value1, String value2) {
            addCriterion("d.materielWeight not between", value1, value2, "materielWeight");
            return (Criteria) this;
        }

        public Criteria andPackageTypeIsNull() {
            addCriterion("d.packageType is null");
            return (Criteria) this;
        }

        public Criteria andPackageTypeIsNotNull() {
            addCriterion("d.packageType is not null");
            return (Criteria) this;
        }

        public Criteria andPackageTypeEqualTo(String value) {
            addCriterion("d.packageType =", value, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeNotEqualTo(String value) {
            addCriterion("d.packageType <>", value, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeGreaterThan(String value) {
            addCriterion("d.packageType >", value, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeGreaterThanOrEqualTo(String value) {
            addCriterion("d.packageType >=", value, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeLessThan(String value) {
            addCriterion("d.packageType <", value, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeLessThanOrEqualTo(String value) {
            addCriterion("d.packageType <=", value, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeLike(String value) {
            addCriterion("d.packageType like", value, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeNotLike(String value) {
            addCriterion("d.packageType not like", value, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeIn(List<String> values) {
            addCriterion("d.packageType in", values, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeNotIn(List<String> values) {
            addCriterion("d.packageType not in", values, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeBetween(String value1, String value2) {
            addCriterion("d.packageType between", value1, value2, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeNotBetween(String value1, String value2) {
            addCriterion("d.packageType not between", value1, value2, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsIsNull() {
            addCriterion("d.packageSpecifications is null");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsIsNotNull() {
            addCriterion("d.packageSpecifications is not null");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsEqualTo(String value) {
            addCriterion("d.packageSpecifications =", value, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsNotEqualTo(String value) {
            addCriterion("d.packageSpecifications <>", value, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsGreaterThan(String value) {
            addCriterion("d.packageSpecifications >", value, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsGreaterThanOrEqualTo(String value) {
            addCriterion("d.packageSpecifications >=", value, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsLessThan(String value) {
            addCriterion("d.packageSpecifications <", value, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsLessThanOrEqualTo(String value) {
            addCriterion("d.packageSpecifications <=", value, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsLike(String value) {
            addCriterion("d.packageSpecifications like", value, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsNotLike(String value) {
            addCriterion("d.packageSpecifications not like", value, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsIn(List<String> values) {
            addCriterion("d.packageSpecifications in", values, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsNotIn(List<String> values) {
            addCriterion("d.packageSpecifications not in", values, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsBetween(String value1, String value2) {
            addCriterion("d.packageSpecifications between", value1, value2, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsNotBetween(String value1, String value2) {
            addCriterion("d.packageSpecifications not between", value1, value2, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andServiceMoneyIsNull() {
            addCriterion("d.serviceMoney is null");
            return (Criteria) this;
        }

        public Criteria andServiceMoneyIsNotNull() {
            addCriterion("d.serviceMoney is not null");
            return (Criteria) this;
        }

        public Criteria andServiceMoneyEqualTo(String value) {
            addCriterion("d.serviceMoney =", value, "serviceMoney");
            return (Criteria) this;
        }

        public Criteria andServiceMoneyNotEqualTo(String value) {
            addCriterion("d.serviceMoney <>", value, "serviceMoney");
            return (Criteria) this;
        }

        public Criteria andServiceMoneyGreaterThan(String value) {
            addCriterion("d.serviceMoney >", value, "serviceMoney");
            return (Criteria) this;
        }

        public Criteria andServiceMoneyGreaterThanOrEqualTo(String value) {
            addCriterion("d.serviceMoney >=", value, "serviceMoney");
            return (Criteria) this;
        }

        public Criteria andServiceMoneyLessThan(String value) {
            addCriterion("d.serviceMoney <", value, "serviceMoney");
            return (Criteria) this;
        }

        public Criteria andServiceMoneyLessThanOrEqualTo(String value) {
            addCriterion("d.serviceMoney <=", value, "serviceMoney");
            return (Criteria) this;
        }

        public Criteria andServiceMoneyLike(String value) {
            addCriterion("d.serviceMoney like", value, "serviceMoney");
            return (Criteria) this;
        }

        public Criteria andServiceMoneyNotLike(String value) {
            addCriterion("d.serviceMoney not like", value, "serviceMoney");
            return (Criteria) this;
        }

        public Criteria andServiceMoneyIn(List<String> values) {
            addCriterion("d.serviceMoney in", values, "serviceMoney");
            return (Criteria) this;
        }

        public Criteria andServiceMoneyNotIn(List<String> values) {
            addCriterion("d.serviceMoney not in", values, "serviceMoney");
            return (Criteria) this;
        }

        public Criteria andServiceMoneyBetween(String value1, String value2) {
            addCriterion("d.serviceMoney between", value1, value2, "serviceMoney");
            return (Criteria) this;
        }

        public Criteria andServiceMoneyNotBetween(String value1, String value2) {
            addCriterion("d.serviceMoney not between", value1, value2, "serviceMoney");
            return (Criteria) this;
        }

        public Criteria andDelivererIsNull() {
            addCriterion("d.deliverer is null");
            return (Criteria) this;
        }

        public Criteria andDelivererIsNotNull() {
            addCriterion("d.deliverer is not null");
            return (Criteria) this;
        }

        public Criteria andDelivererEqualTo(String value) {
            addCriterion("d.deliverer =", value, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererNotEqualTo(String value) {
            addCriterion("d.deliverer <>", value, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererGreaterThan(String value) {
            addCriterion("d.deliverer >", value, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererGreaterThanOrEqualTo(String value) {
            addCriterion("d.deliverer >=", value, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererLessThan(String value) {
            addCriterion("d.deliverer <", value, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererLessThanOrEqualTo(String value) {
            addCriterion("d.deliverer <=", value, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererLike(String value) {
            addCriterion("d.deliverer like", value, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererNotLike(String value) {
            addCriterion("d.deliverer not like", value, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererIn(List<String> values) {
            addCriterion("d.deliverer in", values, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererNotIn(List<String> values) {
            addCriterion("d.deliverer not in", values, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererBetween(String value1, String value2) {
            addCriterion("d.deliverer between", value1, value2, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererNotBetween(String value1, String value2) {
            addCriterion("d.deliverer not between", value1, value2, "deliverer");
            return (Criteria) this;
        }

        public Criteria andContactNumIsNull() {
            addCriterion("d.contactNum is null");
            return (Criteria) this;
        }

        public Criteria andContactNumIsNotNull() {
            addCriterion("d.contactNum is not null");
            return (Criteria) this;
        }

        public Criteria andContactNumEqualTo(String value) {
            addCriterion("d.contactNum =", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumNotEqualTo(String value) {
            addCriterion("d.contactNum <>", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumGreaterThan(String value) {
            addCriterion("d.contactNum >", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumGreaterThanOrEqualTo(String value) {
            addCriterion("d.contactNum >=", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumLessThan(String value) {
            addCriterion("d.contactNum <", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumLessThanOrEqualTo(String value) {
            addCriterion("d.contactNum <=", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumLike(String value) {
            addCriterion("d.contactNum like", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumNotLike(String value) {
            addCriterion("d.contactNum not like", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumIn(List<String> values) {
            addCriterion("d.contactNum in", values, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumNotIn(List<String> values) {
            addCriterion("d.contactNum not in", values, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumBetween(String value1, String value2) {
            addCriterion("d.contactNum between", value1, value2, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumNotBetween(String value1, String value2) {
            addCriterion("d.contactNum not between", value1, value2, "contactNum");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkIsNull() {
            addCriterion("d.deliverRemark is null");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkIsNotNull() {
            addCriterion("d.deliverRemark is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkEqualTo(String value) {
            addCriterion("d.deliverRemark =", value, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkNotEqualTo(String value) {
            addCriterion("d.deliverRemark <>", value, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkGreaterThan(String value) {
            addCriterion("d.deliverRemark >", value, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("d.deliverRemark >=", value, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkLessThan(String value) {
            addCriterion("d.deliverRemark <", value, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkLessThanOrEqualTo(String value) {
            addCriterion("d.deliverRemark <=", value, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkLike(String value) {
            addCriterion("d.deliverRemark like", value, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkNotLike(String value) {
            addCriterion("d.deliverRemark not like", value, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkIn(List<String> values) {
            addCriterion("d.deliverRemark in", values, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkNotIn(List<String> values) {
            addCriterion("d.deliverRemark not in", values, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkBetween(String value1, String value2) {
            addCriterion("d.deliverRemark between", value1, value2, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkNotBetween(String value1, String value2) {
            addCriterion("d.deliverRemark not between", value1, value2, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDelFlgIsNull() {
            addCriterion("d.delFlg is null");
            return (Criteria) this;
        }

        public Criteria andDelFlgIsNotNull() {
            addCriterion("d.delFlg is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlgEqualTo(String value) {
            addCriterion("d.delFlg =", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgNotEqualTo(String value) {
            addCriterion("d.delFlg <>", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgGreaterThan(String value) {
            addCriterion("d.delFlg >", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgGreaterThanOrEqualTo(String value) {
            addCriterion("d.delFlg >=", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgLessThan(String value) {
            addCriterion("d.delFlg <", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgLessThanOrEqualTo(String value) {
            addCriterion("d.delFlg <=", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgLike(String value) {
            addCriterion("d.delFlg like", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgNotLike(String value) {
            addCriterion("d.delFlg not like", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgIn(List<String> values) {
            addCriterion("d.delFlg in", values, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgNotIn(List<String> values) {
            addCriterion("d.delFlg not in", values, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgBetween(String value1, String value2) {
            addCriterion("d.delFlg between", value1, value2, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgNotBetween(String value1, String value2) {
            addCriterion("d.delFlg not between", value1, value2, "delFlg");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("d.creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("d.creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("d.creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("d.creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("d.creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("d.creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("d.creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("d.creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("d.creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("d.creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("d.creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("d.creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("d.creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("d.creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("d.createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("d.createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("d.createTime =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("d.createTime <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("d.createTime >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("d.createTime >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("d.createTime <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("d.createTime <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("d.createTime in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("d.createTime not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("d.createTime between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("d.createTime not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdaterIsNull() {
            addCriterion("d.updater is null");
            return (Criteria) this;
        }

        public Criteria andUpdaterIsNotNull() {
            addCriterion("d.updater is not null");
            return (Criteria) this;
        }

        public Criteria andUpdaterEqualTo(String value) {
            addCriterion("d.updater =", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotEqualTo(String value) {
            addCriterion("d.updater <>", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterGreaterThan(String value) {
            addCriterion("d.updater >", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterGreaterThanOrEqualTo(String value) {
            addCriterion("d.updater >=", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLessThan(String value) {
            addCriterion("d.updater <", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLessThanOrEqualTo(String value) {
            addCriterion("d.updater <=", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLike(String value) {
            addCriterion("d.updater like", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotLike(String value) {
            addCriterion("d.updater not like", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterIn(List<String> values) {
            addCriterion("d.updater in", values, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotIn(List<String> values) {
            addCriterion("d.updater not in", values, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterBetween(String value1, String value2) {
            addCriterion("d.updater between", value1, value2, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotBetween(String value1, String value2) {
            addCriterion("d.updater not between", value1, value2, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("d.updateTime is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("d.updateTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("d.updateTime =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("d.updateTime <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("d.updateTime >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("d.updateTime >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("d.updateTime <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("d.updateTime <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("d.updateTime in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("d.updateTime not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("d.updateTime between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("d.updateTime not between", value1, value2, "updateTime");
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