package com.congmai.zhgj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DeliveryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;
    
    private int pageIndex;
    
    private int pageSize;

    public DeliveryExample() {
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

        public Criteria andDeliverNumIsNull() {
            addCriterion("deliverNum is null");
            return (Criteria) this;
        }

        public Criteria andDeliverNumIsNotNull() {
            addCriterion("deliverNum is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverNumEqualTo(String value) {
            addCriterion("deliverNum =", value, "deliverNum");
            return (Criteria) this;
        }

        public Criteria andDeliverNumNotEqualTo(String value) {
            addCriterion("deliverNum <>", value, "deliverNum");
            return (Criteria) this;
        }

        public Criteria andDeliverNumGreaterThan(String value) {
            addCriterion("deliverNum >", value, "deliverNum");
            return (Criteria) this;
        }

        public Criteria andDeliverNumGreaterThanOrEqualTo(String value) {
            addCriterion("deliverNum >=", value, "deliverNum");
            return (Criteria) this;
        }

        public Criteria andDeliverNumLessThan(String value) {
            addCriterion("deliverNum <", value, "deliverNum");
            return (Criteria) this;
        }

        public Criteria andDeliverNumLessThanOrEqualTo(String value) {
            addCriterion("deliverNum <=", value, "deliverNum");
            return (Criteria) this;
        }

        public Criteria andDeliverNumLike(String value) {
            addCriterion("deliverNum like", value, "deliverNum");
            return (Criteria) this;
        }

        public Criteria andDeliverNumNotLike(String value) {
            addCriterion("deliverNum not like", value, "deliverNum");
            return (Criteria) this;
        }

        public Criteria andDeliverNumIn(List<String> values) {
            addCriterion("deliverNum in", values, "deliverNum");
            return (Criteria) this;
        }

        public Criteria andDeliverNumNotIn(List<String> values) {
            addCriterion("deliverNum not in", values, "deliverNum");
            return (Criteria) this;
        }

        public Criteria andDeliverNumBetween(String value1, String value2) {
            addCriterion("deliverNum between", value1, value2, "deliverNum");
            return (Criteria) this;
        }

        public Criteria andDeliverNumNotBetween(String value1, String value2) {
            addCriterion("deliverNum not between", value1, value2, "deliverNum");
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

        public Criteria andShipperIsNull() {
            addCriterion("shipper is null");
            return (Criteria) this;
        }

        public Criteria andShipperIsNotNull() {
            addCriterion("shipper is not null");
            return (Criteria) this;
        }

        public Criteria andShipperEqualTo(String value) {
            addCriterion("shipper =", value, "shipper");
            return (Criteria) this;
        }

        public Criteria andShipperNotEqualTo(String value) {
            addCriterion("shipper <>", value, "shipper");
            return (Criteria) this;
        }

        public Criteria andShipperGreaterThan(String value) {
            addCriterion("shipper >", value, "shipper");
            return (Criteria) this;
        }

        public Criteria andShipperGreaterThanOrEqualTo(String value) {
            addCriterion("shipper >=", value, "shipper");
            return (Criteria) this;
        }

        public Criteria andShipperLessThan(String value) {
            addCriterion("shipper <", value, "shipper");
            return (Criteria) this;
        }

        public Criteria andShipperLessThanOrEqualTo(String value) {
            addCriterion("shipper <=", value, "shipper");
            return (Criteria) this;
        }

        public Criteria andShipperLike(String value) {
            addCriterion("shipper like", value, "shipper");
            return (Criteria) this;
        }

        public Criteria andShipperNotLike(String value) {
            addCriterion("shipper not like", value, "shipper");
            return (Criteria) this;
        }

        public Criteria andShipperIn(List<String> values) {
            addCriterion("shipper in", values, "shipper");
            return (Criteria) this;
        }

        public Criteria andShipperNotIn(List<String> values) {
            addCriterion("shipper not in", values, "shipper");
            return (Criteria) this;
        }

        public Criteria andShipperBetween(String value1, String value2) {
            addCriterion("shipper between", value1, value2, "shipper");
            return (Criteria) this;
        }

        public Criteria andShipperNotBetween(String value1, String value2) {
            addCriterion("shipper not between", value1, value2, "shipper");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNull() {
            addCriterion("receiver is null");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNotNull() {
            addCriterion("receiver is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverEqualTo(String value) {
            addCriterion("receiver =", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotEqualTo(String value) {
            addCriterion("receiver <>", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThan(String value) {
            addCriterion("receiver >", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThanOrEqualTo(String value) {
            addCriterion("receiver >=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThan(String value) {
            addCriterion("receiver <", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThanOrEqualTo(String value) {
            addCriterion("receiver <=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLike(String value) {
            addCriterion("receiver like", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotLike(String value) {
            addCriterion("receiver not like", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverIn(List<String> values) {
            addCriterion("receiver in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotIn(List<String> values) {
            addCriterion("receiver not in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverBetween(String value1, String value2) {
            addCriterion("receiver between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotBetween(String value1, String value2) {
            addCriterion("receiver not between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andMakeDateIsNull() {
            addCriterion("makeDate is null");
            return (Criteria) this;
        }

        public Criteria andMakeDateIsNotNull() {
            addCriterion("makeDate is not null");
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

        public Criteria andApprovalIsNull() {
            addCriterion("approval is null");
            return (Criteria) this;
        }

        public Criteria andApprovalIsNotNull() {
            addCriterion("approval is not null");
            return (Criteria) this;
        }

        public Criteria andApprovalEqualTo(String value) {
            addCriterion("approval =", value, "approval");
            return (Criteria) this;
        }

        public Criteria andApprovalNotEqualTo(String value) {
            addCriterion("approval <>", value, "approval");
            return (Criteria) this;
        }

        public Criteria andApprovalGreaterThan(String value) {
            addCriterion("approval >", value, "approval");
            return (Criteria) this;
        }

        public Criteria andApprovalGreaterThanOrEqualTo(String value) {
            addCriterion("approval >=", value, "approval");
            return (Criteria) this;
        }

        public Criteria andApprovalLessThan(String value) {
            addCriterion("approval <", value, "approval");
            return (Criteria) this;
        }

        public Criteria andApprovalLessThanOrEqualTo(String value) {
            addCriterion("approval <=", value, "approval");
            return (Criteria) this;
        }

        public Criteria andApprovalLike(String value) {
            addCriterion("approval like", value, "approval");
            return (Criteria) this;
        }

        public Criteria andApprovalNotLike(String value) {
            addCriterion("approval not like", value, "approval");
            return (Criteria) this;
        }

        public Criteria andApprovalIn(List<String> values) {
            addCriterion("approval in", values, "approval");
            return (Criteria) this;
        }

        public Criteria andApprovalNotIn(List<String> values) {
            addCriterion("approval not in", values, "approval");
            return (Criteria) this;
        }

        public Criteria andApprovalBetween(String value1, String value2) {
            addCriterion("approval between", value1, value2, "approval");
            return (Criteria) this;
        }

        public Criteria andApprovalNotBetween(String value1, String value2) {
            addCriterion("approval not between", value1, value2, "approval");
            return (Criteria) this;
        }

        public Criteria andApprovalDateIsNull() {
            addCriterion("approvalDate is null");
            return (Criteria) this;
        }

        public Criteria andApprovalDateIsNotNull() {
            addCriterion("approvalDate is not null");
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
            addCriterion("warehouseSerial is null");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialIsNotNull() {
            addCriterion("warehouseSerial is not null");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialEqualTo(String value) {
            addCriterion("warehouseSerial =", value, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialNotEqualTo(String value) {
            addCriterion("warehouseSerial <>", value, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialGreaterThan(String value) {
            addCriterion("warehouseSerial >", value, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialGreaterThanOrEqualTo(String value) {
            addCriterion("warehouseSerial >=", value, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialLessThan(String value) {
            addCriterion("warehouseSerial <", value, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialLessThanOrEqualTo(String value) {
            addCriterion("warehouseSerial <=", value, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialLike(String value) {
            addCriterion("warehouseSerial like", value, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialNotLike(String value) {
            addCriterion("warehouseSerial not like", value, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialIn(List<String> values) {
            addCriterion("warehouseSerial in", values, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialNotIn(List<String> values) {
            addCriterion("warehouseSerial not in", values, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialBetween(String value1, String value2) {
            addCriterion("warehouseSerial between", value1, value2, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andWarehouseSerialNotBetween(String value1, String value2) {
            addCriterion("warehouseSerial not between", value1, value2, "warehouseSerial");
            return (Criteria) this;
        }

        public Criteria andMaterielCountIsNull() {
            addCriterion("materielCount is null");
            return (Criteria) this;
        }

        public Criteria andMaterielCountIsNotNull() {
            addCriterion("materielCount is not null");
            return (Criteria) this;
        }

        public Criteria andMaterielCountEqualTo(String value) {
            addCriterion("materielCount =", value, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountNotEqualTo(String value) {
            addCriterion("materielCount <>", value, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountGreaterThan(String value) {
            addCriterion("materielCount >", value, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountGreaterThanOrEqualTo(String value) {
            addCriterion("materielCount >=", value, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountLessThan(String value) {
            addCriterion("materielCount <", value, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountLessThanOrEqualTo(String value) {
            addCriterion("materielCount <=", value, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountLike(String value) {
            addCriterion("materielCount like", value, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountNotLike(String value) {
            addCriterion("materielCount not like", value, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountIn(List<String> values) {
            addCriterion("materielCount in", values, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountNotIn(List<String> values) {
            addCriterion("materielCount not in", values, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountBetween(String value1, String value2) {
            addCriterion("materielCount between", value1, value2, "materielCount");
            return (Criteria) this;
        }

        public Criteria andMaterielCountNotBetween(String value1, String value2) {
            addCriterion("materielCount not between", value1, value2, "materielCount");
            return (Criteria) this;
        }

        public Criteria andDeliverDateIsNull() {
            addCriterion("deliverDate is null");
            return (Criteria) this;
        }

        public Criteria andDeliverDateIsNotNull() {
            addCriterion("deliverDate is not null");
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
            addCriterion("packageCount is null");
            return (Criteria) this;
        }

        public Criteria andPackageCountIsNotNull() {
            addCriterion("packageCount is not null");
            return (Criteria) this;
        }

        public Criteria andPackageCountEqualTo(String value) {
            addCriterion("packageCount =", value, "packageCount");
            return (Criteria) this;
        }

        public Criteria andPackageCountNotEqualTo(String value) {
            addCriterion("packageCount <>", value, "packageCount");
            return (Criteria) this;
        }

        public Criteria andPackageCountGreaterThan(String value) {
            addCriterion("packageCount >", value, "packageCount");
            return (Criteria) this;
        }

        public Criteria andPackageCountGreaterThanOrEqualTo(String value) {
            addCriterion("packageCount >=", value, "packageCount");
            return (Criteria) this;
        }

        public Criteria andPackageCountLessThan(String value) {
            addCriterion("packageCount <", value, "packageCount");
            return (Criteria) this;
        }

        public Criteria andPackageCountLessThanOrEqualTo(String value) {
            addCriterion("packageCount <=", value, "packageCount");
            return (Criteria) this;
        }

        public Criteria andPackageCountLike(String value) {
            addCriterion("packageCount like", value, "packageCount");
            return (Criteria) this;
        }

        public Criteria andPackageCountNotLike(String value) {
            addCriterion("packageCount not like", value, "packageCount");
            return (Criteria) this;
        }

        public Criteria andPackageCountIn(List<String> values) {
            addCriterion("packageCount in", values, "packageCount");
            return (Criteria) this;
        }

        public Criteria andPackageCountNotIn(List<String> values) {
            addCriterion("packageCount not in", values, "packageCount");
            return (Criteria) this;
        }

        public Criteria andPackageCountBetween(String value1, String value2) {
            addCriterion("packageCount between", value1, value2, "packageCount");
            return (Criteria) this;
        }

        public Criteria andPackageCountNotBetween(String value1, String value2) {
            addCriterion("packageCount not between", value1, value2, "packageCount");
            return (Criteria) this;
        }

        public Criteria andMaterielWeightIsNull() {
            addCriterion("materielWeight is null");
            return (Criteria) this;
        }

        public Criteria andMaterielWeightIsNotNull() {
            addCriterion("materielWeight is not null");
            return (Criteria) this;
        }

        public Criteria andMaterielWeightEqualTo(String value) {
            addCriterion("materielWeight =", value, "materielWeight");
            return (Criteria) this;
        }

        public Criteria andMaterielWeightNotEqualTo(String value) {
            addCriterion("materielWeight <>", value, "materielWeight");
            return (Criteria) this;
        }

        public Criteria andMaterielWeightGreaterThan(String value) {
            addCriterion("materielWeight >", value, "materielWeight");
            return (Criteria) this;
        }

        public Criteria andMaterielWeightGreaterThanOrEqualTo(String value) {
            addCriterion("materielWeight >=", value, "materielWeight");
            return (Criteria) this;
        }

        public Criteria andMaterielWeightLessThan(String value) {
            addCriterion("materielWeight <", value, "materielWeight");
            return (Criteria) this;
        }

        public Criteria andMaterielWeightLessThanOrEqualTo(String value) {
            addCriterion("materielWeight <=", value, "materielWeight");
            return (Criteria) this;
        }

        public Criteria andMaterielWeightLike(String value) {
            addCriterion("materielWeight like", value, "materielWeight");
            return (Criteria) this;
        }

        public Criteria andMaterielWeightNotLike(String value) {
            addCriterion("materielWeight not like", value, "materielWeight");
            return (Criteria) this;
        }

        public Criteria andMaterielWeightIn(List<String> values) {
            addCriterion("materielWeight in", values, "materielWeight");
            return (Criteria) this;
        }

        public Criteria andMaterielWeightNotIn(List<String> values) {
            addCriterion("materielWeight not in", values, "materielWeight");
            return (Criteria) this;
        }

        public Criteria andMaterielWeightBetween(String value1, String value2) {
            addCriterion("materielWeight between", value1, value2, "materielWeight");
            return (Criteria) this;
        }

        public Criteria andMaterielWeightNotBetween(String value1, String value2) {
            addCriterion("materielWeight not between", value1, value2, "materielWeight");
            return (Criteria) this;
        }

        public Criteria andPackageTypeIsNull() {
            addCriterion("packageType is null");
            return (Criteria) this;
        }

        public Criteria andPackageTypeIsNotNull() {
            addCriterion("packageType is not null");
            return (Criteria) this;
        }

        public Criteria andPackageTypeEqualTo(String value) {
            addCriterion("packageType =", value, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeNotEqualTo(String value) {
            addCriterion("packageType <>", value, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeGreaterThan(String value) {
            addCriterion("packageType >", value, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeGreaterThanOrEqualTo(String value) {
            addCriterion("packageType >=", value, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeLessThan(String value) {
            addCriterion("packageType <", value, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeLessThanOrEqualTo(String value) {
            addCriterion("packageType <=", value, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeLike(String value) {
            addCriterion("packageType like", value, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeNotLike(String value) {
            addCriterion("packageType not like", value, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeIn(List<String> values) {
            addCriterion("packageType in", values, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeNotIn(List<String> values) {
            addCriterion("packageType not in", values, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeBetween(String value1, String value2) {
            addCriterion("packageType between", value1, value2, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageTypeNotBetween(String value1, String value2) {
            addCriterion("packageType not between", value1, value2, "packageType");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsIsNull() {
            addCriterion("packageSpecifications is null");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsIsNotNull() {
            addCriterion("packageSpecifications is not null");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsEqualTo(String value) {
            addCriterion("packageSpecifications =", value, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsNotEqualTo(String value) {
            addCriterion("packageSpecifications <>", value, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsGreaterThan(String value) {
            addCriterion("packageSpecifications >", value, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsGreaterThanOrEqualTo(String value) {
            addCriterion("packageSpecifications >=", value, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsLessThan(String value) {
            addCriterion("packageSpecifications <", value, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsLessThanOrEqualTo(String value) {
            addCriterion("packageSpecifications <=", value, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsLike(String value) {
            addCriterion("packageSpecifications like", value, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsNotLike(String value) {
            addCriterion("packageSpecifications not like", value, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsIn(List<String> values) {
            addCriterion("packageSpecifications in", values, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsNotIn(List<String> values) {
            addCriterion("packageSpecifications not in", values, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsBetween(String value1, String value2) {
            addCriterion("packageSpecifications between", value1, value2, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsNotBetween(String value1, String value2) {
            addCriterion("packageSpecifications not between", value1, value2, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andServiceMoneyIsNull() {
            addCriterion("serviceMoney is null");
            return (Criteria) this;
        }

        public Criteria andServiceMoneyIsNotNull() {
            addCriterion("serviceMoney is not null");
            return (Criteria) this;
        }

        public Criteria andServiceMoneyEqualTo(String value) {
            addCriterion("serviceMoney =", value, "serviceMoney");
            return (Criteria) this;
        }

        public Criteria andServiceMoneyNotEqualTo(String value) {
            addCriterion("serviceMoney <>", value, "serviceMoney");
            return (Criteria) this;
        }

        public Criteria andServiceMoneyGreaterThan(String value) {
            addCriterion("serviceMoney >", value, "serviceMoney");
            return (Criteria) this;
        }

        public Criteria andServiceMoneyGreaterThanOrEqualTo(String value) {
            addCriterion("serviceMoney >=", value, "serviceMoney");
            return (Criteria) this;
        }

        public Criteria andServiceMoneyLessThan(String value) {
            addCriterion("serviceMoney <", value, "serviceMoney");
            return (Criteria) this;
        }

        public Criteria andServiceMoneyLessThanOrEqualTo(String value) {
            addCriterion("serviceMoney <=", value, "serviceMoney");
            return (Criteria) this;
        }

        public Criteria andServiceMoneyLike(String value) {
            addCriterion("serviceMoney like", value, "serviceMoney");
            return (Criteria) this;
        }

        public Criteria andServiceMoneyNotLike(String value) {
            addCriterion("serviceMoney not like", value, "serviceMoney");
            return (Criteria) this;
        }

        public Criteria andServiceMoneyIn(List<String> values) {
            addCriterion("serviceMoney in", values, "serviceMoney");
            return (Criteria) this;
        }

        public Criteria andServiceMoneyNotIn(List<String> values) {
            addCriterion("serviceMoney not in", values, "serviceMoney");
            return (Criteria) this;
        }

        public Criteria andServiceMoneyBetween(String value1, String value2) {
            addCriterion("serviceMoney between", value1, value2, "serviceMoney");
            return (Criteria) this;
        }

        public Criteria andServiceMoneyNotBetween(String value1, String value2) {
            addCriterion("serviceMoney not between", value1, value2, "serviceMoney");
            return (Criteria) this;
        }

        public Criteria andDelivererIsNull() {
            addCriterion("deliverer is null");
            return (Criteria) this;
        }

        public Criteria andDelivererIsNotNull() {
            addCriterion("deliverer is not null");
            return (Criteria) this;
        }

        public Criteria andDelivererEqualTo(String value) {
            addCriterion("deliverer =", value, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererNotEqualTo(String value) {
            addCriterion("deliverer <>", value, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererGreaterThan(String value) {
            addCriterion("deliverer >", value, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererGreaterThanOrEqualTo(String value) {
            addCriterion("deliverer >=", value, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererLessThan(String value) {
            addCriterion("deliverer <", value, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererLessThanOrEqualTo(String value) {
            addCriterion("deliverer <=", value, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererLike(String value) {
            addCriterion("deliverer like", value, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererNotLike(String value) {
            addCriterion("deliverer not like", value, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererIn(List<String> values) {
            addCriterion("deliverer in", values, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererNotIn(List<String> values) {
            addCriterion("deliverer not in", values, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererBetween(String value1, String value2) {
            addCriterion("deliverer between", value1, value2, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererNotBetween(String value1, String value2) {
            addCriterion("deliverer not between", value1, value2, "deliverer");
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

        public Criteria andDeliverRemarkIsNull() {
            addCriterion("deliverRemark is null");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkIsNotNull() {
            addCriterion("deliverRemark is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkEqualTo(String value) {
            addCriterion("deliverRemark =", value, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkNotEqualTo(String value) {
            addCriterion("deliverRemark <>", value, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkGreaterThan(String value) {
            addCriterion("deliverRemark >", value, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("deliverRemark >=", value, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkLessThan(String value) {
            addCriterion("deliverRemark <", value, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkLessThanOrEqualTo(String value) {
            addCriterion("deliverRemark <=", value, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkLike(String value) {
            addCriterion("deliverRemark like", value, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkNotLike(String value) {
            addCriterion("deliverRemark not like", value, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkIn(List<String> values) {
            addCriterion("deliverRemark in", values, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkNotIn(List<String> values) {
            addCriterion("deliverRemark not in", values, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkBetween(String value1, String value2) {
            addCriterion("deliverRemark between", value1, value2, "deliverRemark");
            return (Criteria) this;
        }

        public Criteria andDeliverRemarkNotBetween(String value1, String value2) {
            addCriterion("deliverRemark not between", value1, value2, "deliverRemark");
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