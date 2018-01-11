package com.congmai.zhgj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class StockInOutRecordSelectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;
    
    private int pageIndex;
    
    private int pageSize;
    
    private String comId;//用于查询该公司的物料出入库记录
    
    private String status;//用于查询该公司的物料出入库记录
    
    private String orderSerial;//用于查询该公司的物料出入库记录
    
    
    
    public String getOrderSerial() {
		return orderSerial;
	}

	public void setOrderSerial(String orderSerial) {
		this.orderSerial = orderSerial;
	}

	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public StockInOutRecordSelectExample() {
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
            addCriterion("s.serialNum is null");
            return (Criteria) this;
        }

        public Criteria andSerialNumIsNotNull() {
            addCriterion("s.serialNum is not null");
            return (Criteria) this;
        }

        public Criteria andSerialNumEqualTo(String value) {
            addCriterion("s.serialNum =", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumNotEqualTo(String value) {
            addCriterion("s.serialNum <>", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumGreaterThan(String value) {
            addCriterion("s.serialNum >", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumGreaterThanOrEqualTo(String value) {
            addCriterion("s.serialNum >=", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumLessThan(String value) {
            addCriterion("s.serialNum <", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumLessThanOrEqualTo(String value) {
            addCriterion("s.serialNum <=", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumLike(String value) {
            addCriterion("s.serialNum like", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumNotLike(String value) {
            addCriterion("s.serialNum not like", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumIn(List<String> values) {
            addCriterion("s.serialNum in", values, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumNotIn(List<String> values) {
            addCriterion("s.serialNum not in", values, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumBetween(String value1, String value2) {
            addCriterion("s.serialNum between", value1, value2, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumNotBetween(String value1, String value2) {
            addCriterion("s.serialNum not between", value1, value2, "serialNum");
            return (Criteria) this;
        }

        public Criteria andDeliverSerialIsNull() {
            addCriterion("s.deliverSerial is null");
            return (Criteria) this;
        }

        public Criteria andDeliverSerialIsNotNull() {
            addCriterion("s.deliverSerial is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverSerialEqualTo(String value) {
            addCriterion("s.deliverSerial =", value, "deliverSerial");
            return (Criteria) this;
        }

        public Criteria andDeliverSerialNotEqualTo(String value) {
            addCriterion("s.deliverSerial <>", value, "deliverSerial");
            return (Criteria) this;
        }

        public Criteria andDeliverSerialGreaterThan(String value) {
            addCriterion("s.deliverSerial >", value, "deliverSerial");
            return (Criteria) this;
        }

        public Criteria andDeliverSerialGreaterThanOrEqualTo(String value) {
            addCriterion("s.deliverSerial >=", value, "deliverSerial");
            return (Criteria) this;
        }

        public Criteria andDeliverSerialLessThan(String value) {
            addCriterion("s.deliverSerial <", value, "deliverSerial");
            return (Criteria) this;
        }

        public Criteria andDeliverSerialLessThanOrEqualTo(String value) {
            addCriterion("s.deliverSerial <=", value, "deliverSerial");
            return (Criteria) this;
        }

        public Criteria andDeliverSerialLike(String value) {
            addCriterion("s.deliverSerial like", value, "deliverSerial");
            return (Criteria) this;
        }

        public Criteria andDeliverSerialNotLike(String value) {
            addCriterion("s.deliverSerial not like", value, "deliverSerial");
            return (Criteria) this;
        }

        public Criteria andDeliverSerialIn(List<String> values) {
            addCriterion("s.deliverSerial in", values, "deliverSerial");
            return (Criteria) this;
        }

        public Criteria andDeliverSerialNotIn(List<String> values) {
            addCriterion("s.deliverSerial not in", values, "deliverSerial");
            return (Criteria) this;
        }

        public Criteria andDeliverSerialBetween(String value1, String value2) {
            addCriterion("s.deliverSerial between", value1, value2, "deliverSerial");
            return (Criteria) this;
        }

        public Criteria andDeliverSerialNotBetween(String value1, String value2) {
            addCriterion("s.deliverSerial not between", value1, value2, "deliverSerial");
            return (Criteria) this;
        }

        public Criteria andTakeDeliverSerialIsNull() {
            addCriterion("s.takeDeliverSerial is null");
            return (Criteria) this;
        }

        public Criteria andTakeDeliverSerialIsNotNull() {
            addCriterion("s.takeDeliverSerial is not null");
            return (Criteria) this;
        }

        public Criteria andTakeDeliverSerialEqualTo(String value) {
            addCriterion("s.takeDeliverSerial =", value, "takeDeliverSerial");
            return (Criteria) this;
        }

        public Criteria andTakeDeliverSerialNotEqualTo(String value) {
            addCriterion("s.takeDeliverSerial <>", value, "takeDeliverSerial");
            return (Criteria) this;
        }

        public Criteria andTakeDeliverSerialGreaterThan(String value) {
            addCriterion("s.takeDeliverSerial >", value, "takeDeliverSerial");
            return (Criteria) this;
        }

        public Criteria andTakeDeliverSerialGreaterThanOrEqualTo(String value) {
            addCriterion("s.takeDeliverSerial >=", value, "takeDeliverSerial");
            return (Criteria) this;
        }

        public Criteria andTakeDeliverSerialLessThan(String value) {
            addCriterion("s.takeDeliverSerial <", value, "takeDeliverSerial");
            return (Criteria) this;
        }

        public Criteria andTakeDeliverSerialLessThanOrEqualTo(String value) {
            addCriterion("s.takeDeliverSerial <=", value, "takeDeliverSerial");
            return (Criteria) this;
        }

        public Criteria andTakeDeliverSerialLike(String value) {
            addCriterion("s.takeDeliverSerial like", value, "takeDeliverSerial");
            return (Criteria) this;
        }

        public Criteria andTakeDeliverSerialNotLike(String value) {
            addCriterion("s.takeDeliverSerial not like", value, "takeDeliverSerial");
            return (Criteria) this;
        }

        public Criteria andTakeDeliverSerialIn(List<String> values) {
            addCriterion("s.takeDeliverSerial in", values, "takeDeliverSerial");
            return (Criteria) this;
        }

        public Criteria andTakeDeliverSerialNotIn(List<String> values) {
            addCriterion("s.takeDeliverSerial not in", values, "takeDeliverSerial");
            return (Criteria) this;
        }

        public Criteria andTakeDeliverSerialBetween(String value1, String value2) {
            addCriterion("s.takeDeliverSerial between", value1, value2, "takeDeliverSerial");
            return (Criteria) this;
        }

        public Criteria andTakeDeliverSerialNotBetween(String value1, String value2) {
            addCriterion("s.takeDeliverSerial not between", value1, value2, "takeDeliverSerial");
            return (Criteria) this;
        }

        public Criteria andInOutNumIsNull() {
            addCriterion("s.inOutNum is null");
            return (Criteria) this;
        }

        public Criteria andInOutNumIsNotNull() {
            addCriterion("s.inOutNum is not null");
            return (Criteria) this;
        }

        public Criteria andInOutNumEqualTo(String value) {
            addCriterion("s.inOutNum =", value, "inOutNum");
            return (Criteria) this;
        }

        public Criteria andInOutNumNotEqualTo(String value) {
            addCriterion("s.inOutNum <>", value, "inOutNum");
            return (Criteria) this;
        }

        public Criteria andInOutNumGreaterThan(String value) {
            addCriterion("s.inOutNum >", value, "inOutNum");
            return (Criteria) this;
        }

        public Criteria andInOutNumGreaterThanOrEqualTo(String value) {
            addCriterion("s.inOutNum >=", value, "inOutNum");
            return (Criteria) this;
        }

        public Criteria andInOutNumLessThan(String value) {
            addCriterion("s.inOutNum <", value, "inOutNum");
            return (Criteria) this;
        }

        public Criteria andInOutNumLessThanOrEqualTo(String value) {
            addCriterion("s.inOutNum <=", value, "inOutNum");
            return (Criteria) this;
        }

        public Criteria andInOutNumLike(String value) {
            addCriterion("s.inOutNum like", value, "inOutNum");
            return (Criteria) this;
        }

        public Criteria andInOutNumNotLike(String value) {
            addCriterion("s.inOutNum not like", value, "inOutNum");
            return (Criteria) this;
        }

        public Criteria andInOutNumIn(List<String> values) {
            addCriterion("s.inOutNum in", values, "inOutNum");
            return (Criteria) this;
        }

        public Criteria andInOutNumNotIn(List<String> values) {
            addCriterion("s.inOutNum not in", values, "inOutNum");
            return (Criteria) this;
        }

        public Criteria andInOutNumBetween(String value1, String value2) {
            addCriterion("s.inOutNum between", value1, value2, "inOutNum");
            return (Criteria) this;
        }

        public Criteria andInOutNumNotBetween(String value1, String value2) {
            addCriterion("s.inOutNum not between", value1, value2, "inOutNum");
            return (Criteria) this;
        }

        public Criteria andStockDateIsNull() {
            addCriterion("s.stockDate is null");
            return (Criteria) this;
        }

        public Criteria andStockDateIsNotNull() {
            addCriterion("s.stockDate is not null");
            return (Criteria) this;
        }

        public Criteria andStockDateEqualTo(Date value) {
            addCriterionForJDBCDate("stockDate =", value, "stockDate");
            return (Criteria) this;
        }

        public Criteria andStockDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("stockDate <>", value, "stockDate");
            return (Criteria) this;
        }

        public Criteria andStockDateGreaterThan(Date value) {
            addCriterionForJDBCDate("stockDate >", value, "stockDate");
            return (Criteria) this;
        }

        public Criteria andStockDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("stockDate >=", value, "stockDate");
            return (Criteria) this;
        }

        public Criteria andStockDateLessThan(Date value) {
            addCriterionForJDBCDate("stockDate <", value, "stockDate");
            return (Criteria) this;
        }

        public Criteria andStockDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("stockDate <=", value, "stockDate");
            return (Criteria) this;
        }

        public Criteria andStockDateIn(List<Date> values) {
            addCriterionForJDBCDate("stockDate in", values, "stockDate");
            return (Criteria) this;
        }

        public Criteria andStockDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("stockDate not in", values, "stockDate");
            return (Criteria) this;
        }

        public Criteria andStockDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("stockDate between", value1, value2, "stockDate");
            return (Criteria) this;
        }

        public Criteria andStockDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("stockDate not between", value1, value2, "stockDate");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNull() {
            addCriterion("s.operator is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNotNull() {
            addCriterion("s.operator is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEqualTo(String value) {
            addCriterion("s.operator =", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotEqualTo(String value) {
            addCriterion("s.operator <>", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThan(String value) {
            addCriterion("s.operator >", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("s.operator >=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThan(String value) {
            addCriterion("s.operator <", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThanOrEqualTo(String value) {
            addCriterion("s.operator <=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLike(String value) {
            addCriterion("s.operator like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotLike(String value) {
            addCriterion("s.operator not like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorIn(List<String> values) {
            addCriterion("s.operator in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotIn(List<String> values) {
            addCriterion("s.operator not in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorBetween(String value1, String value2) {
            addCriterion("s.operator between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotBetween(String value1, String value2) {
            addCriterion("s.operator not between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andContactNumIsNull() {
            addCriterion("s.contactNum is null");
            return (Criteria) this;
        }

        public Criteria andContactNumIsNotNull() {
            addCriterion("s.contactNum is not null");
            return (Criteria) this;
        }

        public Criteria andContactNumEqualTo(String value) {
            addCriterion("s.contactNum =", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumNotEqualTo(String value) {
            addCriterion("s.contactNum <>", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumGreaterThan(String value) {
            addCriterion("s.contactNum >", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumGreaterThanOrEqualTo(String value) {
            addCriterion("s.contactNum >=", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumLessThan(String value) {
            addCriterion("s.contactNum <", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumLessThanOrEqualTo(String value) {
            addCriterion("s.contactNum <=", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumLike(String value) {
            addCriterion("s.contactNum like", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumNotLike(String value) {
            addCriterion("s.contactNum not like", value, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumIn(List<String> values) {
            addCriterion("s.contactNum in", values, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumNotIn(List<String> values) {
            addCriterion("s.contactNum not in", values, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumBetween(String value1, String value2) {
            addCriterion("s.contactNum between", value1, value2, "contactNum");
            return (Criteria) this;
        }

        public Criteria andContactNumNotBetween(String value1, String value2) {
            addCriterion("s.contactNum not between", value1, value2, "contactNum");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("s.status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("s.status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("s.status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("s.status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("s.status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("s.status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("s.status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("s.status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("s.status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("s.status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("s.status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("s.status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("s.status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("s.status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("s.remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("s.remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("s.remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("s.remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("s.remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("s.remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("s.remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("s.remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("s.remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("s.remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("s.remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("s.remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("s.remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("s.remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andDelFlgIsNull() {
            addCriterion("s.delFlg is null");
            return (Criteria) this;
        }

        public Criteria andDelFlgIsNotNull() {
            addCriterion("s.delFlg is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlgEqualTo(String value) {
            addCriterion("s.delFlg =", value, "delFlg");
            return (Criteria) this;
        }
        
        public Criteria andDeliverMaterielDelFlgEqualTo(String value) {
        	addCriterion("dm.delFlg =", value, "delFlg");
        	return (Criteria) this;
        }

        public Criteria andDelFlgNotEqualTo(String value) {
            addCriterion("s.delFlg <>", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgGreaterThan(String value) {
            addCriterion("s.delFlg >", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgGreaterThanOrEqualTo(String value) {
            addCriterion("s.delFlg >=", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgLessThan(String value) {
            addCriterion("s.delFlg <", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgLessThanOrEqualTo(String value) {
            addCriterion("s.delFlg <=", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgLike(String value) {
            addCriterion("s.delFlg like", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgNotLike(String value) {
            addCriterion("s.delFlg not like", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgIn(List<String> values) {
            addCriterion("s.delFlg in", values, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgNotIn(List<String> values) {
            addCriterion("s.delFlg not in", values, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgBetween(String value1, String value2) {
            addCriterion("s.delFlg between", value1, value2, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgNotBetween(String value1, String value2) {
            addCriterion("s.delFlg not between", value1, value2, "delFlg");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("s.creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("s.creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("s.creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("s.creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("s.creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("s.creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("s.creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("s.creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("s.creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("s.creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("s.creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("s.creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("s.creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("s.creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("s.createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("s.createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("s.createTime =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("s.createTime <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("s.createTime >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("s.createTime >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("s.createTime <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("s.createTime <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("s.createTime in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("s.createTime not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("s.createTime between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("s.createTime not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdaterIsNull() {
            addCriterion("s.updater is null");
            return (Criteria) this;
        }

        public Criteria andUpdaterIsNotNull() {
            addCriterion("s.updater is not null");
            return (Criteria) this;
        }

        public Criteria andUpdaterEqualTo(String value) {
            addCriterion("s.updater =", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotEqualTo(String value) {
            addCriterion("s.updater <>", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterGreaterThan(String value) {
            addCriterion("s.updater >", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterGreaterThanOrEqualTo(String value) {
            addCriterion("s.updater >=", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLessThan(String value) {
            addCriterion("s.updater <", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLessThanOrEqualTo(String value) {
            addCriterion("s.updater <=", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLike(String value) {
            addCriterion("s.updater like", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotLike(String value) {
            addCriterion("s.updater not like", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterIn(List<String> values) {
            addCriterion("s.updater in", values, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotIn(List<String> values) {
            addCriterion("s.updater not in", values, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterBetween(String value1, String value2) {
            addCriterion("s.updater between", value1, value2, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotBetween(String value1, String value2) {
            addCriterion("s.updater not between", value1, value2, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("s.updateTime is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("s.updateTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("s.updateTime =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("s.updateTime <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("s.updateTime >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("s.updateTime >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("s.updateTime <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("s.updateTime <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("s.updateTime in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("s.updateTime not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("s.updateTime between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("s.updateTime not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
        
        public Criteria andInOutTypeIsNull() {
            addCriterion("s.inOutType is null");
            return (Criteria) this;
        }

        public Criteria andInOutTypeIsNotNull() {
            addCriterion("s.inOutType is not null");
            return (Criteria) this;
        }

        public Criteria andInOutTypeEqualTo(String value) {
            addCriterion("s.inOutType =", value, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeNotEqualTo(String value) {
            addCriterion("s.inOutType <>", value, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeGreaterThan(String value) {
            addCriterion("s.inOutType >", value, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeGreaterThanOrEqualTo(String value) {
            addCriterion("s.inOutType >=", value, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeLessThan(String value) {
            addCriterion("s.inOutType <", value, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeLessThanOrEqualTo(String value) {
            addCriterion("s.inOutType <=", value, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeLike(String value) {
            addCriterion("s.inOutType like", value, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeNotLike(String value) {
            addCriterion("s.inOutType not like", value, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeIn(List<String> values) {
            addCriterion("s.inOutType in", values, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeNotIn(List<String> values) {
            addCriterion("s.inOutType not in", values, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeBetween(String value1, String value2) {
            addCriterion("s.inOutType between", value1, value2, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeNotBetween(String value1, String value2) {
            addCriterion("s.inOutType not between", value1, value2, "inOutType");
            return (Criteria) this;
        }

        public Criteria andShipperOrReceiverIsNull() {
            addCriterion("s.shipperOrReceiver is null");
            return (Criteria) this;
        }

        public Criteria andShipperOrReceiverIsNotNull() {
            addCriterion("s.shipperOrReceiver is not null");
            return (Criteria) this;
        }

        public Criteria andShipperOrReceiverEqualTo(String value) {
            addCriterion("s.shipperOrReceiver =", value, "shipperOrReceiver");
            return (Criteria) this;
        }

        public Criteria andShipperOrReceiverNotEqualTo(String value) {
            addCriterion("s.shipperOrReceiver <>", value, "shipperOrReceiver");
            return (Criteria) this;
        }

        public Criteria andShipperOrReceiverGreaterThan(String value) {
            addCriterion("s.shipperOrReceiver >", value, "shipperOrReceiver");
            return (Criteria) this;
        }

        public Criteria andShipperOrReceiverGreaterThanOrEqualTo(String value) {
            addCriterion("s.shipperOrReceiver >=", value, "shipperOrReceiver");
            return (Criteria) this;
        }

        public Criteria andShipperOrReceiverLessThan(String value) {
            addCriterion("s.shipperOrReceiver <", value, "shipperOrReceiver");
            return (Criteria) this;
        }

        public Criteria andShipperOrReceiverLessThanOrEqualTo(String value) {
            addCriterion("s.shipperOrReceiver <=", value, "shipperOrReceiver");
            return (Criteria) this;
        }

        public Criteria andShipperOrReceiverLike(String value) {
            addCriterion("s.shipperOrReceiver like", value, "shipperOrReceiver");
            return (Criteria) this;
        }

        public Criteria andShipperOrReceiverNotLike(String value) {
            addCriterion("s.shipperOrReceiver not like", value, "shipperOrReceiver");
            return (Criteria) this;
        }

        public Criteria andShipperOrReceiverIn(List<String> values) {
            addCriterion("s.shipperOrReceiver in", values, "shipperOrReceiver");
            return (Criteria) this;
        }

        public Criteria andShipperOrReceiverNotIn(List<String> values) {
            addCriterion("s.shipperOrReceiver not in", values, "shipperOrReceiver");
            return (Criteria) this;
        }

        public Criteria andShipperOrReceiverBetween(String value1, String value2) {
            addCriterion("s.shipperOrReceiver between", value1, value2, "shipperOrReceiver");
            return (Criteria) this;
        }

        public Criteria andShipperOrReceiverNotBetween(String value1, String value2) {
            addCriterion("s.shipperOrReceiver not between", value1, value2, "shipperOrReceiver");
            return (Criteria) this;
        }
        public Criteria andInOutFlagIsNull() {
            addCriterion("s.inOutFlag is null");
            return (Criteria) this;
        }

        public Criteria andInOutFlagIsNotNull() {
            addCriterion("s.inOutFlag is not null");
            return (Criteria) this;
        }

        public Criteria andInOutFlagEqualTo(String value) {
            addCriterion("s.inOutFlag =", value, "inOutFlag");
            return (Criteria) this;
        }

        public Criteria andInOutFlagNotEqualTo(String value) {
            addCriterion("s.inOutFlag <>", value, "inOutFlag");
            return (Criteria) this;
        }

        public Criteria andInOutFlagGreaterThan(String value) {
            addCriterion("s.inOutFlag >", value, "inOutFlag");
            return (Criteria) this;
        }

        public Criteria andInOutFlagGreaterThanOrEqualTo(String value) {
            addCriterion("s.inOutFlag >=", value, "inOutFlag");
            return (Criteria) this;
        }

        public Criteria andInOutFlagLessThan(String value) {
            addCriterion("s.inOutFlag <", value, "inOutFlag");
            return (Criteria) this;
        }

        public Criteria andInOutFlagLessThanOrEqualTo(String value) {
            addCriterion("s.inOutFlag <=", value, "inOutFlag");
            return (Criteria) this;
        }

        public Criteria andInOutFlagLike(String value) {
            addCriterion("s.inOutFlag like", value, "inOutFlag");
            return (Criteria) this;
        }

        public Criteria andInOutFlagNotLike(String value) {
            addCriterion("s.inOutFlag not like", value, "inOutFlag");
            return (Criteria) this;
        }

        public Criteria andInOutFlagIn(List<String> values) {
            addCriterion("s.inOutFlag in", values, "inOutFlag");
            return (Criteria) this;
        }

        public Criteria andInOutFlagNotIn(List<String> values) {
            addCriterion("s.inOutFlag not in", values, "inOutFlag");
            return (Criteria) this;
        }

        public Criteria andInOutFlagBetween(String value1, String value2) {
            addCriterion("s.inOutFlag between", value1, value2, "inOutFlag");
            return (Criteria) this;
        }

        public Criteria andInOutFlagNotBetween(String value1, String value2) {
            addCriterion("s.inOutFlag not between", value1, value2, "inOutFlag");
            return (Criteria) this;
        }
        
        
        public Criteria andBuyComIdEqualTo(String value) {
            addCriterion("d.buyComId =", value, "buyComId");
            return (Criteria) this;
        }
        
        public Criteria andBuyComIdIsNull() {
            addCriterion("d.buyComId is null");
            return (Criteria) this;
        }
        
        public Criteria andSupplyComIdEqualTo(String value) {
            addCriterion("d.supplyComId =", value, "supplyComId");
            return (Criteria) this;
        }
        
        public Criteria andSupplyComIdIsNull() {
            addCriterion("d.supplyComId is null");
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