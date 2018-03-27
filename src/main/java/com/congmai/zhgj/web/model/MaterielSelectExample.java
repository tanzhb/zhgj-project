package com.congmai.zhgj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.congmai.zhgj.web.model.MaterielExample.Criteria;

public class MaterielSelectExample {
    protected String orderByClause;

    protected boolean distinct;

	private int start;
	private int length;
    
    protected List<Criteria> oredCriteria;
    
    private String searchStr;

    public MaterielSelectExample() {
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
            addCriterion("m.serialNum is null");
            return (Criteria) this;
        }

        public Criteria andSerialNumIsNotNull() {
            addCriterion("m.serialNum is not null");
            return (Criteria) this;
        }

        public Criteria andSerialNumEqualTo(String value) {
            addCriterion("m.serialNum =", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumNotEqualTo(String value) {
            addCriterion("m.serialNum <>", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumGreaterThan(String value) {
            addCriterion("m.serialNum >", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumGreaterThanOrEqualTo(String value) {
            addCriterion("m.serialNum >=", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumLessThan(String value) {
            addCriterion("m.serialNum <", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumLessThanOrEqualTo(String value) {
            addCriterion("m.serialNum <=", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumLike(String value) {
            addCriterion("m.serialNum like", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumNotLike(String value) {
            addCriterion("m.serialNum not like", value, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumIn(List<String> values) {
            addCriterion("m.serialNum in", values, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumNotIn(List<String> values) {
            addCriterion("m.serialNum not in", values, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumBetween(String value1, String value2) {
            addCriterion("m.serialNum between", value1, value2, "serialNum");
            return (Criteria) this;
        }

        public Criteria andSerialNumNotBetween(String value1, String value2) {
            addCriterion("m.serialNum not between", value1, value2, "serialNum");
            return (Criteria) this;
        }

        public Criteria andMaterielIdIsNull() {
            addCriterion("m.materielId is null");
            return (Criteria) this;
        }

        public Criteria andMaterielIdIsNotNull() {
            addCriterion("m.materielId is not null");
            return (Criteria) this;
        }

        public Criteria andMaterielIdEqualTo(String value) {
            addCriterion("m.materielId =", value, "materielId");
            return (Criteria) this;
        }

        public Criteria andMaterielIdNotEqualTo(String value) {
            addCriterion("m.materielId <>", value, "materielId");
            return (Criteria) this;
        }

        public Criteria andMaterielIdGreaterThan(String value) {
            addCriterion("m.materielId >", value, "materielId");
            return (Criteria) this;
        }

        public Criteria andMaterielIdGreaterThanOrEqualTo(String value) {
            addCriterion("m.materielId >=", value, "materielId");
            return (Criteria) this;
        }

        public Criteria andMaterielIdLessThan(String value) {
            addCriterion("m.materielId <", value, "materielId");
            return (Criteria) this;
        }

        public Criteria andMaterielIdLessThanOrEqualTo(String value) {
            addCriterion("m.materielId <=", value, "materielId");
            return (Criteria) this;
        }

        public Criteria andMaterielIdLike(String value) {
            addCriterion("m.materielId like", value, "materielId");
            return (Criteria) this;
        }

        public Criteria andMaterielIdNotLike(String value) {
            addCriterion("m.materielId not like", value, "materielId");
            return (Criteria) this;
        }

        public Criteria andMaterielIdIn(List<String> values) {
            addCriterion("m.materielId in", values, "materielId");
            return (Criteria) this;
        }

        public Criteria andMaterielIdNotIn(List<String> values) {
            addCriterion("m.materielId not in", values, "materielId");
            return (Criteria) this;
        }

        public Criteria andMaterielIdBetween(String value1, String value2) {
            addCriterion("m.materielId between", value1, value2, "materielId");
            return (Criteria) this;
        }

        public Criteria andMaterielIdNotBetween(String value1, String value2) {
            addCriterion("m.materielId not between", value1, value2, "materielId");
            return (Criteria) this;
        }

        public Criteria andMaterielNumIsNull() {
            addCriterion("m.materielNum is null");
            return (Criteria) this;
        }

        public Criteria andMaterielNumIsNotNull() {
            addCriterion("m.materielNum is not null");
            return (Criteria) this;
        }

        public Criteria andMaterielNumEqualTo(String value) {
            addCriterion("m.materielNum =", value, "materielNum");
            return (Criteria) this;
        }

        public Criteria andMaterielNumNotEqualTo(String value) {
            addCriterion("m.materielNum <>", value, "materielNum");
            return (Criteria) this;
        }

        public Criteria andMaterielNumGreaterThan(String value) {
            addCriterion("m.materielNum >", value, "materielNum");
            return (Criteria) this;
        }

        public Criteria andMaterielNumGreaterThanOrEqualTo(String value) {
            addCriterion("m.materielNum >=", value, "materielNum");
            return (Criteria) this;
        }

        public Criteria andMaterielNumLessThan(String value) {
            addCriterion("m.materielNum <", value, "materielNum");
            return (Criteria) this;
        }

        public Criteria andMaterielNumLessThanOrEqualTo(String value) {
            addCriterion("m.materielNum <=", value, "materielNum");
            return (Criteria) this;
        }

        public Criteria andMaterielNumLike(String value) {
            addCriterion("m.materielNum like", value, "materielNum");
            return (Criteria) this;
        }

        public Criteria andMaterielNumNotLike(String value) {
            addCriterion("m.materielNum not like", value, "materielNum");
            return (Criteria) this;
        }

        public Criteria andMaterielNumIn(List<String> values) {
            addCriterion("m.materielNum in", values, "materielNum");
            return (Criteria) this;
        }

        public Criteria andMaterielNumNotIn(List<String> values) {
            addCriterion("m.materielNum not in", values, "materielNum");
            return (Criteria) this;
        }

        public Criteria andMaterielNumBetween(String value1, String value2) {
            addCriterion("m.materielNum between", value1, value2, "materielNum");
            return (Criteria) this;
        }

        public Criteria andMaterielNumNotBetween(String value1, String value2) {
            addCriterion("m.materielNum not between", value1, value2, "materielNum");
            return (Criteria) this;
        }

        public Criteria andMaterielNameIsNull() {
            addCriterion("m.materielName is null");
            return (Criteria) this;
        }

        public Criteria andMaterielNameIsNotNull() {
            addCriterion("m.materielName is not null");
            return (Criteria) this;
        }

        public Criteria andMaterielNameEqualTo(String value) {
            addCriterion("m.materielName =", value, "materielName");
            return (Criteria) this;
        }

        public Criteria andMaterielNameNotEqualTo(String value) {
            addCriterion("m.materielName <>", value, "materielName");
            return (Criteria) this;
        }

        public Criteria andMaterielNameGreaterThan(String value) {
            addCriterion("m.materielName >", value, "materielName");
            return (Criteria) this;
        }

        public Criteria andMaterielNameGreaterThanOrEqualTo(String value) {
            addCriterion("m.materielName >=", value, "materielName");
            return (Criteria) this;
        }

        public Criteria andMaterielNameLessThan(String value) {
            addCriterion("m.materielName <", value, "materielName");
            return (Criteria) this;
        }

        public Criteria andMaterielNameLessThanOrEqualTo(String value) {
            addCriterion("m.materielName <=", value, "materielName");
            return (Criteria) this;
        }

        public Criteria andMaterielNameLike(String value) {
            addCriterion("m.materielName like", value, "materielName");
            return (Criteria) this;
        }

        public Criteria andMaterielNameNotLike(String value) {
            addCriterion("m.materielName not like", value, "materielName");
            return (Criteria) this;
        }

        public Criteria andMaterielNameIn(List<String> values) {
            addCriterion("m.materielName in", values, "materielName");
            return (Criteria) this;
        }

        public Criteria andMaterielNameNotIn(List<String> values) {
            addCriterion("m.materielName not in", values, "materielName");
            return (Criteria) this;
        }

        public Criteria andMaterielNameBetween(String value1, String value2) {
            addCriterion("m.materielName between", value1, value2, "materielName");
            return (Criteria) this;
        }

        public Criteria andMaterielNameNotBetween(String value1, String value2) {
            addCriterion("m.materielName not between", value1, value2, "materielName");
            return (Criteria) this;
        }

        public Criteria andSpecificationsIsNull() {
            addCriterion("m.specifications is null");
            return (Criteria) this;
        }

        public Criteria andSpecificationsIsNotNull() {
            addCriterion("m.specifications is not null");
            return (Criteria) this;
        }

        public Criteria andSpecificationsEqualTo(String value) {
            addCriterion("m.specifications =", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsNotEqualTo(String value) {
            addCriterion("m.specifications <>", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsGreaterThan(String value) {
            addCriterion("m.specifications >", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsGreaterThanOrEqualTo(String value) {
            addCriterion("m.specifications >=", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsLessThan(String value) {
            addCriterion("m.specifications <", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsLessThanOrEqualTo(String value) {
            addCriterion("m.specifications <=", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsLike(String value) {
            addCriterion("m.specifications like", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsNotLike(String value) {
            addCriterion("m.specifications not like", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsIn(List<String> values) {
            addCriterion("m.specifications in", values, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsNotIn(List<String> values) {
            addCriterion("m.specifications not in", values, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsBetween(String value1, String value2) {
            addCriterion("m.specifications between", value1, value2, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsNotBetween(String value1, String value2) {
            addCriterion("m.specifications not between", value1, value2, "specifications");
            return (Criteria) this;
        }

        public Criteria andUnitIsNull() {
            addCriterion("m.unit is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("m.unit is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(String value) {
            addCriterion("m.unit =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(String value) {
            addCriterion("m.unit <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(String value) {
            addCriterion("m.unit >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(String value) {
            addCriterion("m.unit >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(String value) {
            addCriterion("m.unit <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(String value) {
            addCriterion("m.unit <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLike(String value) {
            addCriterion("m.unit like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotLike(String value) {
            addCriterion("m.unit not like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<String> values) {
            addCriterion("m.unit in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<String> values) {
            addCriterion("m.unit not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(String value1, String value2) {
            addCriterion("m.unit between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(String value1, String value2) {
            addCriterion("m.unit not between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andParentMaterielSerialIsNull() {
            addCriterion("m.parentMaterielSerial is null");
            return (Criteria) this;
        }

        public Criteria andParentMaterielSerialIsNotNull() {
            addCriterion("m.parentMaterielSerial is not null");
            return (Criteria) this;
        }

        public Criteria andParentMaterielSerialEqualTo(String value) {
            addCriterion("m.parentMaterielSerial =", value, "parentMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andParentMaterielSerialNotEqualTo(String value) {
            addCriterion("m.parentMaterielSerial <>", value, "parentMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andParentMaterielSerialGreaterThan(String value) {
            addCriterion("m.parentMaterielSerial >", value, "parentMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andParentMaterielSerialGreaterThanOrEqualTo(String value) {
            addCriterion("m.parentMaterielSerial >=", value, "parentMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andParentMaterielSerialLessThan(String value) {
            addCriterion("m.parentMaterielSerial <", value, "parentMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andParentMaterielSerialLessThanOrEqualTo(String value) {
            addCriterion("m.parentMaterielSerial <=", value, "parentMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andParentMaterielSerialLike(String value) {
            addCriterion("m.parentMaterielSerial like", value, "parentMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andParentMaterielSerialNotLike(String value) {
            addCriterion("m.parentMaterielSerial not like", value, "parentMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andParentMaterielSerialIn(List<String> values) {
            addCriterion("m.parentMaterielSerial in", values, "parentMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andParentMaterielSerialNotIn(List<String> values) {
            addCriterion("m.parentMaterielSerial not in", values, "parentMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andParentMaterielSerialBetween(String value1, String value2) {
            addCriterion("m.parentMaterielSerial between", value1, value2, "parentMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andParentMaterielSerialNotBetween(String value1, String value2) {
            addCriterion("m.parentMaterielSerial not between", value1, value2, "parentMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("m.type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("m.type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("m.type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("m.type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("m.type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("m.type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("m.type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("m.type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("m.type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("m.type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("m.type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("m.type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("m.type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("m.type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceIsNull() {
            addCriterion("m.productionPlace is null");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceIsNotNull() {
            addCriterion("m.productionPlace is not null");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceEqualTo(String value) {
            addCriterion("m.productionPlace =", value, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceNotEqualTo(String value) {
            addCriterion("m.productionPlace <>", value, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceGreaterThan(String value) {
            addCriterion("m.productionPlace >", value, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceGreaterThanOrEqualTo(String value) {
            addCriterion("m.productionPlace >=", value, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceLessThan(String value) {
            addCriterion("m.productionPlace <", value, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceLessThanOrEqualTo(String value) {
            addCriterion("m.productionPlace <=", value, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceLike(String value) {
            addCriterion("m.productionPlace like", value, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceNotLike(String value) {
            addCriterion("m.productionPlace not like", value, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceIn(List<String> values) {
            addCriterion("m.productionPlace in", values, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceNotIn(List<String> values) {
            addCriterion("m.productionPlace not in", values, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceBetween(String value1, String value2) {
            addCriterion("m.productionPlace between", value1, value2, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceNotBetween(String value1, String value2) {
            addCriterion("m.productionPlace not between", value1, value2, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andBrandIsNull() {
            addCriterion("m.brand is null");
            return (Criteria) this;
        }

        public Criteria andBrandIsNotNull() {
            addCriterion("m.brand is not null");
            return (Criteria) this;
        }

        public Criteria andBrandEqualTo(String value) {
            addCriterion("m.brand =", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotEqualTo(String value) {
            addCriterion("m.brand <>", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThan(String value) {
            addCriterion("m.brand >", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThanOrEqualTo(String value) {
            addCriterion("m.brand >=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThan(String value) {
            addCriterion("m.brand <", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThanOrEqualTo(String value) {
            addCriterion("m.brand <=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLike(String value) {
            addCriterion("m.brand like", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotLike(String value) {
            addCriterion("m.brand not like", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandIn(List<String> values) {
            addCriterion("m.brand in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotIn(List<String> values) {
            addCriterion("m.brand not in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandBetween(String value1, String value2) {
            addCriterion("m.brand between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotBetween(String value1, String value2) {
            addCriterion("m.brand not between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andCategory1IsNull() {
            addCriterion("m.category1 is null");
            return (Criteria) this;
        }

        public Criteria andCategory1IsNotNull() {
            addCriterion("m.category1 is not null");
            return (Criteria) this;
        }

        public Criteria andCategory1EqualTo(String value) {
            addCriterion("m.category1 =", value, "category1");
            return (Criteria) this;
        }

        public Criteria andCategory1NotEqualTo(String value) {
            addCriterion("m.category1 <>", value, "category1");
            return (Criteria) this;
        }

        public Criteria andCategory1GreaterThan(String value) {
            addCriterion("m.category1 >", value, "category1");
            return (Criteria) this;
        }

        public Criteria andCategory1GreaterThanOrEqualTo(String value) {
            addCriterion("m.category1 >=", value, "category1");
            return (Criteria) this;
        }

        public Criteria andCategory1LessThan(String value) {
            addCriterion("m.category1 <", value, "category1");
            return (Criteria) this;
        }

        public Criteria andCategory1LessThanOrEqualTo(String value) {
            addCriterion("m.category1 <=", value, "category1");
            return (Criteria) this;
        }

        public Criteria andCategory1Like(String value) {
            addCriterion("m.category1 like", value, "category1");
            return (Criteria) this;
        }

        public Criteria andCategory1NotLike(String value) {
            addCriterion("m.category1 not like", value, "category1");
            return (Criteria) this;
        }

        public Criteria andCategory1In(List<String> values) {
            addCriterion("m.category1 in", values, "category1");
            return (Criteria) this;
        }

        public Criteria andCategory1NotIn(List<String> values) {
            addCriterion("m.category1 not in", values, "category1");
            return (Criteria) this;
        }

        public Criteria andCategory1Between(String value1, String value2) {
            addCriterion("m.category1 between", value1, value2, "category1");
            return (Criteria) this;
        }

        public Criteria andCategory1NotBetween(String value1, String value2) {
            addCriterion("m.category1 not between", value1, value2, "category1");
            return (Criteria) this;
        }

        public Criteria andCategory2IsNull() {
            addCriterion("m.category2 is null");
            return (Criteria) this;
        }

        public Criteria andCategory2IsNotNull() {
            addCriterion("m.category2 is not null");
            return (Criteria) this;
        }

        public Criteria andCategory2EqualTo(String value) {
            addCriterion("m.category2 =", value, "category2");
            return (Criteria) this;
        }

        public Criteria andCategory2NotEqualTo(String value) {
            addCriterion("m.category2 <>", value, "category2");
            return (Criteria) this;
        }

        public Criteria andCategory2GreaterThan(String value) {
            addCriterion("m.category2 >", value, "category2");
            return (Criteria) this;
        }

        public Criteria andCategory2GreaterThanOrEqualTo(String value) {
            addCriterion("m.category2 >=", value, "category2");
            return (Criteria) this;
        }

        public Criteria andCategory2LessThan(String value) {
            addCriterion("m.category2 <", value, "category2");
            return (Criteria) this;
        }

        public Criteria andCategory2LessThanOrEqualTo(String value) {
            addCriterion("m.category2 <=", value, "category2");
            return (Criteria) this;
        }

        public Criteria andCategory2Like(String value) {
            addCriterion("m.category2 like", value, "category2");
            return (Criteria) this;
        }

        public Criteria andCategory2NotLike(String value) {
            addCriterion("m.category2 not like", value, "category2");
            return (Criteria) this;
        }

        public Criteria andCategory2In(List<String> values) {
            addCriterion("m.category2 in", values, "category2");
            return (Criteria) this;
        }

        public Criteria andCategory2NotIn(List<String> values) {
            addCriterion("m.category2 not in", values, "category2");
            return (Criteria) this;
        }

        public Criteria andCategory2Between(String value1, String value2) {
            addCriterion("m.category2 between", value1, value2, "category2");
            return (Criteria) this;
        }

        public Criteria andCategory2NotBetween(String value1, String value2) {
            addCriterion("m.category2 not between", value1, value2, "category2");
            return (Criteria) this;
        }

        public Criteria andCategory3IsNull() {
            addCriterion("m.category3 is null");
            return (Criteria) this;
        }

        public Criteria andCategory3IsNotNull() {
            addCriterion("m.category3 is not null");
            return (Criteria) this;
        }

        public Criteria andCategory3EqualTo(String value) {
            addCriterion("m.category3 =", value, "category3");
            return (Criteria) this;
        }

        public Criteria andCategory3NotEqualTo(String value) {
            addCriterion("m.category3 <>", value, "category3");
            return (Criteria) this;
        }

        public Criteria andCategory3GreaterThan(String value) {
            addCriterion("m.category3 >", value, "category3");
            return (Criteria) this;
        }

        public Criteria andCategory3GreaterThanOrEqualTo(String value) {
            addCriterion("m.category3 >=", value, "category3");
            return (Criteria) this;
        }

        public Criteria andCategory3LessThan(String value) {
            addCriterion("m.category3 <", value, "category3");
            return (Criteria) this;
        }

        public Criteria andCategory3LessThanOrEqualTo(String value) {
            addCriterion("m.category3 <=", value, "category3");
            return (Criteria) this;
        }

        public Criteria andCategory3Like(String value) {
            addCriterion("m.category3 like", value, "category3");
            return (Criteria) this;
        }

        public Criteria andCategory3NotLike(String value) {
            addCriterion("m.category3 not like", value, "category3");
            return (Criteria) this;
        }

        public Criteria andCategory3In(List<String> values) {
            addCriterion("m.category3 in", values, "category3");
            return (Criteria) this;
        }

        public Criteria andCategory3NotIn(List<String> values) {
            addCriterion("m.category3 not in", values, "category3");
            return (Criteria) this;
        }

        public Criteria andCategory3Between(String value1, String value2) {
            addCriterion("m.category3 between", value1, value2, "category3");
            return (Criteria) this;
        }

        public Criteria andCategory3NotBetween(String value1, String value2) {
            addCriterion("m.category3 not between", value1, value2, "category3");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeIsNull() {
            addCriterion("m.MnemonicCode is null");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeIsNotNull() {
            addCriterion("m.MnemonicCode is not null");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeEqualTo(String value) {
            addCriterion("m.MnemonicCode =", value, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeNotEqualTo(String value) {
            addCriterion("m.MnemonicCode <>", value, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeGreaterThan(String value) {
            addCriterion("m.MnemonicCode >", value, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeGreaterThanOrEqualTo(String value) {
            addCriterion("m.MnemonicCode >=", value, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeLessThan(String value) {
            addCriterion("m.MnemonicCode <", value, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeLessThanOrEqualTo(String value) {
            addCriterion("m.MnemonicCode <=", value, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeLike(String value) {
            addCriterion("m.MnemonicCode like", value, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeNotLike(String value) {
            addCriterion("m.MnemonicCode not like", value, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeIn(List<String> values) {
            addCriterion("m.MnemonicCode in", values, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeNotIn(List<String> values) {
            addCriterion("m.MnemonicCode not in", values, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeBetween(String value1, String value2) {
            addCriterion("m.MnemonicCode between", value1, value2, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeNotBetween(String value1, String value2) {
            addCriterion("m.MnemonicCode not between", value1, value2, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andIsBOMIsNull() {
            addCriterion("m.isBOM is null");
            return (Criteria) this;
        }

        public Criteria andIsBOMIsNotNull() {
            addCriterion("m.isBOM is not null");
            return (Criteria) this;
        }

        public Criteria andIsBOMEqualTo(String value) {
            addCriterion("m.isBOM =", value, "isBOM");
            return (Criteria) this;
        }

        public Criteria andIsBOMNotEqualTo(String value) {
            addCriterion("m.isBOM <>", value, "isBOM");
            return (Criteria) this;
        }

        public Criteria andIsBOMGreaterThan(String value) {
            addCriterion("m.isBOM >", value, "isBOM");
            return (Criteria) this;
        }

        public Criteria andIsBOMGreaterThanOrEqualTo(String value) {
            addCriterion("m.isBOM >=", value, "isBOM");
            return (Criteria) this;
        }

        public Criteria andIsBOMLessThan(String value) {
            addCriterion("m.isBOM <", value, "isBOM");
            return (Criteria) this;
        }

        public Criteria andIsBOMLessThanOrEqualTo(String value) {
            addCriterion("m.isBOM <=", value, "isBOM");
            return (Criteria) this;
        }

        public Criteria andIsBOMLike(String value) {
            addCriterion("m.isBOM like", value, "isBOM");
            return (Criteria) this;
        }

        public Criteria andIsBOMNotLike(String value) {
            addCriterion("m.isBOM not like", value, "isBOM");
            return (Criteria) this;
        }

        public Criteria andIsBOMIn(List<String> values) {
            addCriterion("m.isBOM in", values, "isBOM");
            return (Criteria) this;
        }

        public Criteria andIsBOMNotIn(List<String> values) {
            addCriterion("m.isBOM not in", values, "isBOM");
            return (Criteria) this;
        }

        public Criteria andIsBOMBetween(String value1, String value2) {
            addCriterion("m.isBOM between", value1, value2, "isBOM");
            return (Criteria) this;
        }

        public Criteria andIsBOMNotBetween(String value1, String value2) {
            addCriterion("m.isBOM not between", value1, value2, "isBOM");
            return (Criteria) this;
        }

        public Criteria andStockUnitIsNull() {
            addCriterion("m.stockUnit is null");
            return (Criteria) this;
        }

        public Criteria andStockUnitIsNotNull() {
            addCriterion("m.stockUnit is not null");
            return (Criteria) this;
        }

        public Criteria andStockUnitEqualTo(String value) {
            addCriterion("m.stockUnit =", value, "stockUnit");
            return (Criteria) this;
        }

        public Criteria andStockUnitNotEqualTo(String value) {
            addCriterion("m.stockUnit <>", value, "stockUnit");
            return (Criteria) this;
        }

        public Criteria andStockUnitGreaterThan(String value) {
            addCriterion("m.stockUnit >", value, "stockUnit");
            return (Criteria) this;
        }

        public Criteria andStockUnitGreaterThanOrEqualTo(String value) {
            addCriterion("m.stockUnit >=", value, "stockUnit");
            return (Criteria) this;
        }

        public Criteria andStockUnitLessThan(String value) {
            addCriterion("m.stockUnit <", value, "stockUnit");
            return (Criteria) this;
        }

        public Criteria andStockUnitLessThanOrEqualTo(String value) {
            addCriterion("m.stockUnit <=", value, "stockUnit");
            return (Criteria) this;
        }

        public Criteria andStockUnitLike(String value) {
            addCriterion("m.stockUnit like", value, "stockUnit");
            return (Criteria) this;
        }

        public Criteria andStockUnitNotLike(String value) {
            addCriterion("m.stockUnit not like", value, "stockUnit");
            return (Criteria) this;
        }

        public Criteria andStockUnitIn(List<String> values) {
            addCriterion("m.stockUnit in", values, "stockUnit");
            return (Criteria) this;
        }

        public Criteria andStockUnitNotIn(List<String> values) {
            addCriterion("m.stockUnit not in", values, "stockUnit");
            return (Criteria) this;
        }

        public Criteria andStockUnitBetween(String value1, String value2) {
            addCriterion("m.stockUnit between", value1, value2, "stockUnit");
            return (Criteria) this;
        }

        public Criteria andStockUnitNotBetween(String value1, String value2) {
            addCriterion("m.stockUnit not between", value1, value2, "stockUnit");
            return (Criteria) this;
        }

        public Criteria andOriginCountryIsNull() {
            addCriterion("m.originCountry is null");
            return (Criteria) this;
        }

        public Criteria andOriginCountryIsNotNull() {
            addCriterion("m.originCountry is not null");
            return (Criteria) this;
        }

        public Criteria andOriginCountryEqualTo(String value) {
            addCriterion("m.originCountry =", value, "originCountry");
            return (Criteria) this;
        }

        public Criteria andOriginCountryNotEqualTo(String value) {
            addCriterion("m.originCountry <>", value, "originCountry");
            return (Criteria) this;
        }

        public Criteria andOriginCountryGreaterThan(String value) {
            addCriterion("m.originCountry >", value, "originCountry");
            return (Criteria) this;
        }

        public Criteria andOriginCountryGreaterThanOrEqualTo(String value) {
            addCriterion("m.originCountry >=", value, "originCountry");
            return (Criteria) this;
        }

        public Criteria andOriginCountryLessThan(String value) {
            addCriterion("m.originCountry <", value, "originCountry");
            return (Criteria) this;
        }

        public Criteria andOriginCountryLessThanOrEqualTo(String value) {
            addCriterion("m.originCountry <=", value, "originCountry");
            return (Criteria) this;
        }

        public Criteria andOriginCountryLike(String value) {
            addCriterion("m.originCountry like", value, "originCountry");
            return (Criteria) this;
        }

        public Criteria andOriginCountryNotLike(String value) {
            addCriterion("m.originCountry not like", value, "originCountry");
            return (Criteria) this;
        }

        public Criteria andOriginCountryIn(List<String> values) {
            addCriterion("m.originCountry in", values, "originCountry");
            return (Criteria) this;
        }

        public Criteria andOriginCountryNotIn(List<String> values) {
            addCriterion("m.originCountry not in", values, "originCountry");
            return (Criteria) this;
        }

        public Criteria andOriginCountryBetween(String value1, String value2) {
            addCriterion("m.originCountry between", value1, value2, "originCountry");
            return (Criteria) this;
        }

        public Criteria andOriginCountryNotBetween(String value1, String value2) {
            addCriterion("m.originCountry not between", value1, value2, "originCountry");
            return (Criteria) this;
        }

        public Criteria andWidthIsNull() {
            addCriterion("m.width is null");
            return (Criteria) this;
        }

        public Criteria andWidthIsNotNull() {
            addCriterion("m.width is not null");
            return (Criteria) this;
        }

        public Criteria andWidthEqualTo(String value) {
            addCriterion("m.width =", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotEqualTo(String value) {
            addCriterion("m.width <>", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthGreaterThan(String value) {
            addCriterion("m.width >", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthGreaterThanOrEqualTo(String value) {
            addCriterion("m.width >=", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLessThan(String value) {
            addCriterion("m.width <", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLessThanOrEqualTo(String value) {
            addCriterion("m.width <=", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLike(String value) {
            addCriterion("m.width like", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotLike(String value) {
            addCriterion("m.width not like", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthIn(List<String> values) {
            addCriterion("m.width in", values, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotIn(List<String> values) {
            addCriterion("m.width not in", values, "width");
            return (Criteria) this;
        }

        public Criteria andWidthBetween(String value1, String value2) {
            addCriterion("m.width between", value1, value2, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotBetween(String value1, String value2) {
            addCriterion("m.width not between", value1, value2, "width");
            return (Criteria) this;
        }

        public Criteria andHeightIsNull() {
            addCriterion("m.height is null");
            return (Criteria) this;
        }

        public Criteria andHeightIsNotNull() {
            addCriterion("m.height is not null");
            return (Criteria) this;
        }

        public Criteria andHeightEqualTo(String value) {
            addCriterion("m.height =", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotEqualTo(String value) {
            addCriterion("m.height <>", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightGreaterThan(String value) {
            addCriterion("m.height >", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightGreaterThanOrEqualTo(String value) {
            addCriterion("m.height >=", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLessThan(String value) {
            addCriterion("m.height <", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLessThanOrEqualTo(String value) {
            addCriterion("m.height <=", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLike(String value) {
            addCriterion("m.height like", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotLike(String value) {
            addCriterion("m.height not like", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightIn(List<String> values) {
            addCriterion("m.height in", values, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotIn(List<String> values) {
            addCriterion("m.height not in", values, "height");
            return (Criteria) this;
        }

        public Criteria andHeightBetween(String value1, String value2) {
            addCriterion("m.height between", value1, value2, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotBetween(String value1, String value2) {
            addCriterion("m.height not between", value1, value2, "height");
            return (Criteria) this;
        }

        public Criteria andLengthIsNull() {
            addCriterion("m.length is null");
            return (Criteria) this;
        }

        public Criteria andLengthIsNotNull() {
            addCriterion("m.length is not null");
            return (Criteria) this;
        }

        public Criteria andLengthEqualTo(String value) {
            addCriterion("m.length =", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotEqualTo(String value) {
            addCriterion("m.length <>", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthGreaterThan(String value) {
            addCriterion("m.length >", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthGreaterThanOrEqualTo(String value) {
            addCriterion("m.length >=", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthLessThan(String value) {
            addCriterion("m.length <", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthLessThanOrEqualTo(String value) {
            addCriterion("m.length <=", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthLike(String value) {
            addCriterion("m.length like", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotLike(String value) {
            addCriterion("m.length not like", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthIn(List<String> values) {
            addCriterion("m.length in", values, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotIn(List<String> values) {
            addCriterion("m.length not in", values, "length");
            return (Criteria) this;
        }

        public Criteria andLengthBetween(String value1, String value2) {
            addCriterion("m.length between", value1, value2, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotBetween(String value1, String value2) {
            addCriterion("m.length not between", value1, value2, "length");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNull() {
            addCriterion("m.currency is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNotNull() {
            addCriterion("m.currency is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyEqualTo(String value) {
            addCriterion("m.currency =", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotEqualTo(String value) {
            addCriterion("m.currency <>", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThan(String value) {
            addCriterion("m.currency >", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("m.currency >=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThan(String value) {
            addCriterion("m.currency <", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThanOrEqualTo(String value) {
            addCriterion("m.currency <=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLike(String value) {
            addCriterion("m.currency like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotLike(String value) {
            addCriterion("m.currency not like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyIn(List<String> values) {
            addCriterion("m.currency in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotIn(List<String> values) {
            addCriterion("m.currency not in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyBetween(String value1, String value2) {
            addCriterion("m.currency between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotBetween(String value1, String value2) {
            addCriterion("m.currency not between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIsNull() {
            addCriterion("m.unitPrice is null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIsNotNull() {
            addCriterion("m.unitPrice is not null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceEqualTo(String value) {
            addCriterion("m.unitPrice =", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotEqualTo(String value) {
            addCriterion("m.unitPrice <>", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGreaterThan(String value) {
            addCriterion("m.unitPrice >", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGreaterThanOrEqualTo(String value) {
            addCriterion("m.unitPrice >=", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceLessThan(String value) {
            addCriterion("m.unitPrice <", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceLessThanOrEqualTo(String value) {
            addCriterion("m.unitPrice <=", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceLike(String value) {
            addCriterion("m.unitPrice like", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotLike(String value) {
            addCriterion("m.unitPrice not like", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIn(List<String> values) {
            addCriterion("m.unitPrice in", values, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotIn(List<String> values) {
            addCriterion("m.unitPrice not in", values, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceBetween(String value1, String value2) {
            addCriterion("m.unitPrice between", value1, value2, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotBetween(String value1, String value2) {
            addCriterion("m.unitPrice not between", value1, value2, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andFilingItemNoIsNull() {
            addCriterion("m.filingItemNo is null");
            return (Criteria) this;
        }

        public Criteria andFilingItemNoIsNotNull() {
            addCriterion("m.filingItemNo is not null");
            return (Criteria) this;
        }

        public Criteria andFilingItemNoEqualTo(String value) {
            addCriterion("m.filingItemNo =", value, "filingItemNo");
            return (Criteria) this;
        }

        public Criteria andFilingItemNoNotEqualTo(String value) {
            addCriterion("m.filingItemNo <>", value, "filingItemNo");
            return (Criteria) this;
        }

        public Criteria andFilingItemNoGreaterThan(String value) {
            addCriterion("m.filingItemNo >", value, "filingItemNo");
            return (Criteria) this;
        }

        public Criteria andFilingItemNoGreaterThanOrEqualTo(String value) {
            addCriterion("m.filingItemNo >=", value, "filingItemNo");
            return (Criteria) this;
        }

        public Criteria andFilingItemNoLessThan(String value) {
            addCriterion("m.filingItemNo <", value, "filingItemNo");
            return (Criteria) this;
        }

        public Criteria andFilingItemNoLessThanOrEqualTo(String value) {
            addCriterion("m.filingItemNo <=", value, "filingItemNo");
            return (Criteria) this;
        }

        public Criteria andFilingItemNoLike(String value) {
            addCriterion("m.filingItemNo like", value, "filingItemNo");
            return (Criteria) this;
        }

        public Criteria andFilingItemNoNotLike(String value) {
            addCriterion("m.filingItemNo not like", value, "filingItemNo");
            return (Criteria) this;
        }

        public Criteria andFilingItemNoIn(List<String> values) {
            addCriterion("m.filingItemNo in", values, "filingItemNo");
            return (Criteria) this;
        }

        public Criteria andFilingItemNoNotIn(List<String> values) {
            addCriterion("m.filingItemNo not in", values, "filingItemNo");
            return (Criteria) this;
        }

        public Criteria andFilingItemNoBetween(String value1, String value2) {
            addCriterion("m.filingItemNo between", value1, value2, "filingItemNo");
            return (Criteria) this;
        }

        public Criteria andFilingItemNoNotBetween(String value1, String value2) {
            addCriterion("m.filingItemNo not between", value1, value2, "filingItemNo");
            return (Criteria) this;
        }

        public Criteria andVolumeIsNull() {
            addCriterion("m.volume is null");
            return (Criteria) this;
        }

        public Criteria andVolumeIsNotNull() {
            addCriterion("m.volume is not null");
            return (Criteria) this;
        }

        public Criteria andVolumeEqualTo(String value) {
            addCriterion("m.volume =", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotEqualTo(String value) {
            addCriterion("m.volume <>", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeGreaterThan(String value) {
            addCriterion("m.volume >", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeGreaterThanOrEqualTo(String value) {
            addCriterion("m.volume >=", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLessThan(String value) {
            addCriterion("m.volume <", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLessThanOrEqualTo(String value) {
            addCriterion("m.volume <=", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLike(String value) {
            addCriterion("m.volume like", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotLike(String value) {
            addCriterion("m.volume not like", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeIn(List<String> values) {
            addCriterion("m.volume in", values, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotIn(List<String> values) {
            addCriterion("m.volume not in", values, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeBetween(String value1, String value2) {
            addCriterion("m.volume between", value1, value2, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotBetween(String value1, String value2) {
            addCriterion("m.volume not between", value1, value2, "volume");
            return (Criteria) this;
        }

        public Criteria andWeightIsNull() {
            addCriterion("m.weight is null");
            return (Criteria) this;
        }

        public Criteria andWeightIsNotNull() {
            addCriterion("m.weight is not null");
            return (Criteria) this;
        }

        public Criteria andWeightEqualTo(String value) {
            addCriterion("m.weight =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(String value) {
            addCriterion("m.weight <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(String value) {
            addCriterion("m.weight >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(String value) {
            addCriterion("m.weight >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(String value) {
            addCriterion("m.weight <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(String value) {
            addCriterion("m.weight <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLike(String value) {
            addCriterion("m.weight like", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotLike(String value) {
            addCriterion("m.weight not like", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<String> values) {
            addCriterion("m.weight in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<String> values) {
            addCriterion("m.weight not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(String value1, String value2) {
            addCriterion("m.weight between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(String value1, String value2) {
            addCriterion("m.weight not between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andPalletSpecificationIsNull() {
            addCriterion("m.palletSpecification is null");
            return (Criteria) this;
        }

        public Criteria andPalletSpecificationIsNotNull() {
            addCriterion("m.palletSpecification is not null");
            return (Criteria) this;
        }

        public Criteria andPalletSpecificationEqualTo(String value) {
            addCriterion("m.palletSpecification =", value, "palletSpecification");
            return (Criteria) this;
        }

        public Criteria andPalletSpecificationNotEqualTo(String value) {
            addCriterion("m.palletSpecification <>", value, "palletSpecification");
            return (Criteria) this;
        }

        public Criteria andPalletSpecificationGreaterThan(String value) {
            addCriterion("m.palletSpecification >", value, "palletSpecification");
            return (Criteria) this;
        }

        public Criteria andPalletSpecificationGreaterThanOrEqualTo(String value) {
            addCriterion("m.palletSpecification >=", value, "palletSpecification");
            return (Criteria) this;
        }

        public Criteria andPalletSpecificationLessThan(String value) {
            addCriterion("m.palletSpecification <", value, "palletSpecification");
            return (Criteria) this;
        }

        public Criteria andPalletSpecificationLessThanOrEqualTo(String value) {
            addCriterion("m.palletSpecification <=", value, "palletSpecification");
            return (Criteria) this;
        }

        public Criteria andPalletSpecificationLike(String value) {
            addCriterion("m.palletSpecification like", value, "palletSpecification");
            return (Criteria) this;
        }

        public Criteria andPalletSpecificationNotLike(String value) {
            addCriterion("m.palletSpecification not like", value, "palletSpecification");
            return (Criteria) this;
        }

        public Criteria andPalletSpecificationIn(List<String> values) {
            addCriterion("m.palletSpecification in", values, "palletSpecification");
            return (Criteria) this;
        }

        public Criteria andPalletSpecificationNotIn(List<String> values) {
            addCriterion("m.palletSpecification not in", values, "palletSpecification");
            return (Criteria) this;
        }

        public Criteria andPalletSpecificationBetween(String value1, String value2) {
            addCriterion("m.palletSpecification between", value1, value2, "palletSpecification");
            return (Criteria) this;
        }

        public Criteria andPalletSpecificationNotBetween(String value1, String value2) {
            addCriterion("m.palletSpecification not between", value1, value2, "palletSpecification");
            return (Criteria) this;
        }

        public Criteria andManufactureDateIsNull() {
            addCriterion("m.manufactureDate is null");
            return (Criteria) this;
        }

        public Criteria andManufactureDateIsNotNull() {
            addCriterion("m.manufactureDate is not null");
            return (Criteria) this;
        }

        public Criteria andManufactureDateEqualTo(Date value) {
            addCriterionForJDBCDate("manufactureDate =", value, "manufactureDate");
            return (Criteria) this;
        }

        public Criteria andManufactureDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("manufactureDate <>", value, "manufactureDate");
            return (Criteria) this;
        }

        public Criteria andManufactureDateGreaterThan(Date value) {
            addCriterionForJDBCDate("manufactureDate >", value, "manufactureDate");
            return (Criteria) this;
        }

        public Criteria andManufactureDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("manufactureDate >=", value, "manufactureDate");
            return (Criteria) this;
        }

        public Criteria andManufactureDateLessThan(Date value) {
            addCriterionForJDBCDate("manufactureDate <", value, "manufactureDate");
            return (Criteria) this;
        }

        public Criteria andManufactureDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("manufactureDate <=", value, "manufactureDate");
            return (Criteria) this;
        }

        public Criteria andManufactureDateIn(List<Date> values) {
            addCriterionForJDBCDate("manufactureDate in", values, "manufactureDate");
            return (Criteria) this;
        }

        public Criteria andManufactureDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("manufactureDate not in", values, "manufactureDate");
            return (Criteria) this;
        }

        public Criteria andManufactureDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("manufactureDate between", value1, value2, "manufactureDate");
            return (Criteria) this;
        }

        public Criteria andManufactureDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("manufactureDate not between", value1, value2, "manufactureDate");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("m.remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("m.remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("m.remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("m.remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("m.remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("m.remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("m.remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("m.remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("m.remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("m.remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("m.remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("m.remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("m.remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("m.remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andPackageNumIsNull() {
            addCriterion("m.packageNum is null");
            return (Criteria) this;
        }

        public Criteria andPackageNumIsNotNull() {
            addCriterion("m.packageNum is not null");
            return (Criteria) this;
        }

        public Criteria andPackageNumEqualTo(String value) {
            addCriterion("m.packageNum =", value, "packageNum");
            return (Criteria) this;
        }

        public Criteria andPackageNumNotEqualTo(String value) {
            addCriterion("m.packageNum <>", value, "packageNum");
            return (Criteria) this;
        }

        public Criteria andPackageNumGreaterThan(String value) {
            addCriterion("m.packageNum >", value, "packageNum");
            return (Criteria) this;
        }

        public Criteria andPackageNumGreaterThanOrEqualTo(String value) {
            addCriterion("m.packageNum >=", value, "packageNum");
            return (Criteria) this;
        }

        public Criteria andPackageNumLessThan(String value) {
            addCriterion("m.packageNum <", value, "packageNum");
            return (Criteria) this;
        }

        public Criteria andPackageNumLessThanOrEqualTo(String value) {
            addCriterion("m.packageNum <=", value, "packageNum");
            return (Criteria) this;
        }

        public Criteria andPackageNumLike(String value) {
            addCriterion("m.packageNum like", value, "packageNum");
            return (Criteria) this;
        }

        public Criteria andPackageNumNotLike(String value) {
            addCriterion("m.packageNum not like", value, "packageNum");
            return (Criteria) this;
        }

        public Criteria andPackageNumIn(List<String> values) {
            addCriterion("m.packageNum in", values, "packageNum");
            return (Criteria) this;
        }

        public Criteria andPackageNumNotIn(List<String> values) {
            addCriterion("m.packageNum not in", values, "packageNum");
            return (Criteria) this;
        }

        public Criteria andPackageNumBetween(String value1, String value2) {
            addCriterion("m.packageNum between", value1, value2, "packageNum");
            return (Criteria) this;
        }

        public Criteria andPackageNumNotBetween(String value1, String value2) {
            addCriterion("m.packageNum not between", value1, value2, "packageNum");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsIsNull() {
            addCriterion("m.packageSpecifications is null");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsIsNotNull() {
            addCriterion("m.packageSpecifications is not null");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsEqualTo(String value) {
            addCriterion("m.packageSpecifications =", value, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsNotEqualTo(String value) {
            addCriterion("m.packageSpecifications <>", value, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsGreaterThan(String value) {
            addCriterion("m.packageSpecifications >", value, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsGreaterThanOrEqualTo(String value) {
            addCriterion("m.packageSpecifications >=", value, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsLessThan(String value) {
            addCriterion("m.packageSpecifications <", value, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsLessThanOrEqualTo(String value) {
            addCriterion("m.packageSpecifications <=", value, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsLike(String value) {
            addCriterion("m.packageSpecifications like", value, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsNotLike(String value) {
            addCriterion("m.packageSpecifications not like", value, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsIn(List<String> values) {
            addCriterion("m.packageSpecifications in", values, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsNotIn(List<String> values) {
            addCriterion("m.packageSpecifications not in", values, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsBetween(String value1, String value2) {
            addCriterion("m.packageSpecifications between", value1, value2, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageSpecificationsNotBetween(String value1, String value2) {
            addCriterion("m.packageSpecifications not between", value1, value2, "packageSpecifications");
            return (Criteria) this;
        }

        public Criteria andPackageUnitIsNull() {
            addCriterion("m.packageUnit is null");
            return (Criteria) this;
        }

        public Criteria andPackageUnitIsNotNull() {
            addCriterion("m.packageUnit is not null");
            return (Criteria) this;
        }

        public Criteria andPackageUnitEqualTo(String value) {
            addCriterion("m.packageUnit =", value, "packageUnit");
            return (Criteria) this;
        }

        public Criteria andPackageUnitNotEqualTo(String value) {
            addCriterion("m.packageUnit <>", value, "packageUnit");
            return (Criteria) this;
        }

        public Criteria andPackageUnitGreaterThan(String value) {
            addCriterion("m.packageUnit >", value, "packageUnit");
            return (Criteria) this;
        }

        public Criteria andPackageUnitGreaterThanOrEqualTo(String value) {
            addCriterion("m.packageUnit >=", value, "packageUnit");
            return (Criteria) this;
        }

        public Criteria andPackageUnitLessThan(String value) {
            addCriterion("m.packageUnit <", value, "packageUnit");
            return (Criteria) this;
        }

        public Criteria andPackageUnitLessThanOrEqualTo(String value) {
            addCriterion("m.packageUnit <=", value, "packageUnit");
            return (Criteria) this;
        }

        public Criteria andPackageUnitLike(String value) {
            addCriterion("m.packageUnit like", value, "packageUnit");
            return (Criteria) this;
        }

        public Criteria andPackageUnitNotLike(String value) {
            addCriterion("m.packageUnit not like", value, "packageUnit");
            return (Criteria) this;
        }

        public Criteria andPackageUnitIn(List<String> values) {
            addCriterion("m.packageUnit in", values, "packageUnit");
            return (Criteria) this;
        }

        public Criteria andPackageUnitNotIn(List<String> values) {
            addCriterion("m.packageUnit not in", values, "packageUnit");
            return (Criteria) this;
        }

        public Criteria andPackageUnitBetween(String value1, String value2) {
            addCriterion("m.packageUnit between", value1, value2, "packageUnit");
            return (Criteria) this;
        }

        public Criteria andPackageUnitNotBetween(String value1, String value2) {
            addCriterion("m.packageUnit not between", value1, value2, "packageUnit");
            return (Criteria) this;
        }

        public Criteria andPackagewidthIsNull() {
            addCriterion("m.packagewidth is null");
            return (Criteria) this;
        }

        public Criteria andPackagewidthIsNotNull() {
            addCriterion("m.packagewidth is not null");
            return (Criteria) this;
        }

        public Criteria andPackagewidthEqualTo(String value) {
            addCriterion("m.packagewidth =", value, "packagewidth");
            return (Criteria) this;
        }

        public Criteria andPackagewidthNotEqualTo(String value) {
            addCriterion("m.packagewidth <>", value, "packagewidth");
            return (Criteria) this;
        }

        public Criteria andPackagewidthGreaterThan(String value) {
            addCriterion("m.packagewidth >", value, "packagewidth");
            return (Criteria) this;
        }

        public Criteria andPackagewidthGreaterThanOrEqualTo(String value) {
            addCriterion("m.packagewidth >=", value, "packagewidth");
            return (Criteria) this;
        }

        public Criteria andPackagewidthLessThan(String value) {
            addCriterion("m.packagewidth <", value, "packagewidth");
            return (Criteria) this;
        }

        public Criteria andPackagewidthLessThanOrEqualTo(String value) {
            addCriterion("m.packagewidth <=", value, "packagewidth");
            return (Criteria) this;
        }

        public Criteria andPackagewidthLike(String value) {
            addCriterion("m.packagewidth like", value, "packagewidth");
            return (Criteria) this;
        }

        public Criteria andPackagewidthNotLike(String value) {
            addCriterion("m.packagewidth not like", value, "packagewidth");
            return (Criteria) this;
        }

        public Criteria andPackagewidthIn(List<String> values) {
            addCriterion("m.packagewidth in", values, "packagewidth");
            return (Criteria) this;
        }

        public Criteria andPackagewidthNotIn(List<String> values) {
            addCriterion("m.packagewidth not in", values, "packagewidth");
            return (Criteria) this;
        }

        public Criteria andPackagewidthBetween(String value1, String value2) {
            addCriterion("m.packagewidth between", value1, value2, "packagewidth");
            return (Criteria) this;
        }

        public Criteria andPackagewidthNotBetween(String value1, String value2) {
            addCriterion("m.packagewidth not between", value1, value2, "packagewidth");
            return (Criteria) this;
        }

        public Criteria andPackageheightIsNull() {
            addCriterion("m.packageheight is null");
            return (Criteria) this;
        }

        public Criteria andPackageheightIsNotNull() {
            addCriterion("m.packageheight is not null");
            return (Criteria) this;
        }

        public Criteria andPackageheightEqualTo(String value) {
            addCriterion("m.packageheight =", value, "packageheight");
            return (Criteria) this;
        }

        public Criteria andPackageheightNotEqualTo(String value) {
            addCriterion("m.packageheight <>", value, "packageheight");
            return (Criteria) this;
        }

        public Criteria andPackageheightGreaterThan(String value) {
            addCriterion("m.packageheight >", value, "packageheight");
            return (Criteria) this;
        }

        public Criteria andPackageheightGreaterThanOrEqualTo(String value) {
            addCriterion("m.packageheight >=", value, "packageheight");
            return (Criteria) this;
        }

        public Criteria andPackageheightLessThan(String value) {
            addCriterion("m.packageheight <", value, "packageheight");
            return (Criteria) this;
        }

        public Criteria andPackageheightLessThanOrEqualTo(String value) {
            addCriterion("m.packageheight <=", value, "packageheight");
            return (Criteria) this;
        }

        public Criteria andPackageheightLike(String value) {
            addCriterion("m.packageheight like", value, "packageheight");
            return (Criteria) this;
        }

        public Criteria andPackageheightNotLike(String value) {
            addCriterion("m.packageheight not like", value, "packageheight");
            return (Criteria) this;
        }

        public Criteria andPackageheightIn(List<String> values) {
            addCriterion("m.packageheight in", values, "packageheight");
            return (Criteria) this;
        }

        public Criteria andPackageheightNotIn(List<String> values) {
            addCriterion("m.packageheight not in", values, "packageheight");
            return (Criteria) this;
        }

        public Criteria andPackageheightBetween(String value1, String value2) {
            addCriterion("m.packageheight between", value1, value2, "packageheight");
            return (Criteria) this;
        }

        public Criteria andPackageheightNotBetween(String value1, String value2) {
            addCriterion("m.packageheight not between", value1, value2, "packageheight");
            return (Criteria) this;
        }

        public Criteria andPackagelengthIsNull() {
            addCriterion("m.packagelength is null");
            return (Criteria) this;
        }

        public Criteria andPackagelengthIsNotNull() {
            addCriterion("m.packagelength is not null");
            return (Criteria) this;
        }

        public Criteria andPackagelengthEqualTo(String value) {
            addCriterion("m.packagelength =", value, "packagelength");
            return (Criteria) this;
        }

        public Criteria andPackagelengthNotEqualTo(String value) {
            addCriterion("m.packagelength <>", value, "packagelength");
            return (Criteria) this;
        }

        public Criteria andPackagelengthGreaterThan(String value) {
            addCriterion("m.packagelength >", value, "packagelength");
            return (Criteria) this;
        }

        public Criteria andPackagelengthGreaterThanOrEqualTo(String value) {
            addCriterion("m.packagelength >=", value, "packagelength");
            return (Criteria) this;
        }

        public Criteria andPackagelengthLessThan(String value) {
            addCriterion("m.packagelength <", value, "packagelength");
            return (Criteria) this;
        }

        public Criteria andPackagelengthLessThanOrEqualTo(String value) {
            addCriterion("m.packagelength <=", value, "packagelength");
            return (Criteria) this;
        }

        public Criteria andPackagelengthLike(String value) {
            addCriterion("m.packagelength like", value, "packagelength");
            return (Criteria) this;
        }

        public Criteria andPackagelengthNotLike(String value) {
            addCriterion("m.packagelength not like", value, "packagelength");
            return (Criteria) this;
        }

        public Criteria andPackagelengthIn(List<String> values) {
            addCriterion("m.packagelength in", values, "packagelength");
            return (Criteria) this;
        }

        public Criteria andPackagelengthNotIn(List<String> values) {
            addCriterion("m.packagelength not in", values, "packagelength");
            return (Criteria) this;
        }

        public Criteria andPackagelengthBetween(String value1, String value2) {
            addCriterion("m.packagelength between", value1, value2, "packagelength");
            return (Criteria) this;
        }

        public Criteria andPackagelengthNotBetween(String value1, String value2) {
            addCriterion("m.packagelength not between", value1, value2, "packagelength");
            return (Criteria) this;
        }

        public Criteria andPackagevolumeIsNull() {
            addCriterion("m.packagevolume is null");
            return (Criteria) this;
        }

        public Criteria andPackagevolumeIsNotNull() {
            addCriterion("m.packagevolume is not null");
            return (Criteria) this;
        }

        public Criteria andPackagevolumeEqualTo(String value) {
            addCriterion("m.packagevolume =", value, "packagevolume");
            return (Criteria) this;
        }

        public Criteria andPackagevolumeNotEqualTo(String value) {
            addCriterion("m.packagevolume <>", value, "packagevolume");
            return (Criteria) this;
        }

        public Criteria andPackagevolumeGreaterThan(String value) {
            addCriterion("m.packagevolume >", value, "packagevolume");
            return (Criteria) this;
        }

        public Criteria andPackagevolumeGreaterThanOrEqualTo(String value) {
            addCriterion("m.packagevolume >=", value, "packagevolume");
            return (Criteria) this;
        }

        public Criteria andPackagevolumeLessThan(String value) {
            addCriterion("m.packagevolume <", value, "packagevolume");
            return (Criteria) this;
        }

        public Criteria andPackagevolumeLessThanOrEqualTo(String value) {
            addCriterion("m.packagevolume <=", value, "packagevolume");
            return (Criteria) this;
        }

        public Criteria andPackagevolumeLike(String value) {
            addCriterion("m.packagevolume like", value, "packagevolume");
            return (Criteria) this;
        }

        public Criteria andPackagevolumeNotLike(String value) {
            addCriterion("m.packagevolume not like", value, "packagevolume");
            return (Criteria) this;
        }

        public Criteria andPackagevolumeIn(List<String> values) {
            addCriterion("m.packagevolume in", values, "packagevolume");
            return (Criteria) this;
        }

        public Criteria andPackagevolumeNotIn(List<String> values) {
            addCriterion("m.packagevolume not in", values, "packagevolume");
            return (Criteria) this;
        }

        public Criteria andPackagevolumeBetween(String value1, String value2) {
            addCriterion("m.packagevolume between", value1, value2, "packagevolume");
            return (Criteria) this;
        }

        public Criteria andPackagevolumeNotBetween(String value1, String value2) {
            addCriterion("m.packagevolume not between", value1, value2, "packagevolume");
            return (Criteria) this;
        }

        public Criteria andPackageUnitConversionIsNull() {
            addCriterion("m.packageUnitConversion is null");
            return (Criteria) this;
        }

        public Criteria andPackageUnitConversionIsNotNull() {
            addCriterion("m.packageUnitConversion is not null");
            return (Criteria) this;
        }

        public Criteria andPackageUnitConversionEqualTo(String value) {
            addCriterion("m.packageUnitConversion =", value, "packageUnitConversion");
            return (Criteria) this;
        }

        public Criteria andPackageUnitConversionNotEqualTo(String value) {
            addCriterion("m.packageUnitConversion <>", value, "packageUnitConversion");
            return (Criteria) this;
        }

        public Criteria andPackageUnitConversionGreaterThan(String value) {
            addCriterion("m.packageUnitConversion >", value, "packageUnitConversion");
            return (Criteria) this;
        }

        public Criteria andPackageUnitConversionGreaterThanOrEqualTo(String value) {
            addCriterion("m.packageUnitConversion >=", value, "packageUnitConversion");
            return (Criteria) this;
        }

        public Criteria andPackageUnitConversionLessThan(String value) {
            addCriterion("m.packageUnitConversion <", value, "packageUnitConversion");
            return (Criteria) this;
        }

        public Criteria andPackageUnitConversionLessThanOrEqualTo(String value) {
            addCriterion("m.packageUnitConversion <=", value, "packageUnitConversion");
            return (Criteria) this;
        }

        public Criteria andPackageUnitConversionLike(String value) {
            addCriterion("m.packageUnitConversion like", value, "packageUnitConversion");
            return (Criteria) this;
        }

        public Criteria andPackageUnitConversionNotLike(String value) {
            addCriterion("m.packageUnitConversion not like", value, "packageUnitConversion");
            return (Criteria) this;
        }

        public Criteria andPackageUnitConversionIn(List<String> values) {
            addCriterion("m.packageUnitConversion in", values, "packageUnitConversion");
            return (Criteria) this;
        }

        public Criteria andPackageUnitConversionNotIn(List<String> values) {
            addCriterion("m.packageUnitConversion not in", values, "packageUnitConversion");
            return (Criteria) this;
        }

        public Criteria andPackageUnitConversionBetween(String value1, String value2) {
            addCriterion("m.packageUnitConversion between", value1, value2, "packageUnitConversion");
            return (Criteria) this;
        }

        public Criteria andPackageUnitConversionNotBetween(String value1, String value2) {
            addCriterion("m.packageUnitConversion not between", value1, value2, "packageUnitConversion");
            return (Criteria) this;
        }

        public Criteria andPalletWeightIsNull() {
            addCriterion("m.palletWeight is null");
            return (Criteria) this;
        }

        public Criteria andPalletWeightIsNotNull() {
            addCriterion("m.palletWeight is not null");
            return (Criteria) this;
        }

        public Criteria andPalletWeightEqualTo(String value) {
            addCriterion("m.palletWeight =", value, "palletWeight");
            return (Criteria) this;
        }

        public Criteria andPalletWeightNotEqualTo(String value) {
            addCriterion("m.palletWeight <>", value, "palletWeight");
            return (Criteria) this;
        }

        public Criteria andPalletWeightGreaterThan(String value) {
            addCriterion("m.palletWeight >", value, "palletWeight");
            return (Criteria) this;
        }

        public Criteria andPalletWeightGreaterThanOrEqualTo(String value) {
            addCriterion("m.palletWeight >=", value, "palletWeight");
            return (Criteria) this;
        }

        public Criteria andPalletWeightLessThan(String value) {
            addCriterion("m.palletWeight <", value, "palletWeight");
            return (Criteria) this;
        }

        public Criteria andPalletWeightLessThanOrEqualTo(String value) {
            addCriterion("m.palletWeight <=", value, "palletWeight");
            return (Criteria) this;
        }

        public Criteria andPalletWeightLike(String value) {
            addCriterion("m.palletWeight like", value, "palletWeight");
            return (Criteria) this;
        }

        public Criteria andPalletWeightNotLike(String value) {
            addCriterion("m.palletWeight not like", value, "palletWeight");
            return (Criteria) this;
        }

        public Criteria andPalletWeightIn(List<String> values) {
            addCriterion("m.palletWeight in", values, "palletWeight");
            return (Criteria) this;
        }

        public Criteria andPalletWeightNotIn(List<String> values) {
            addCriterion("m.palletWeight not in", values, "palletWeight");
            return (Criteria) this;
        }

        public Criteria andPalletWeightBetween(String value1, String value2) {
            addCriterion("m.palletWeight between", value1, value2, "palletWeight");
            return (Criteria) this;
        }

        public Criteria andPalletWeightNotBetween(String value1, String value2) {
            addCriterion("m.palletWeight not between", value1, value2, "palletWeight");
            return (Criteria) this;
        }

        public Criteria andCustomsSupervisionConditionsIsNull() {
            addCriterion("m.customsSupervisionConditions is null");
            return (Criteria) this;
        }

        public Criteria andCustomsSupervisionConditionsIsNotNull() {
            addCriterion("m.customsSupervisionConditions is not null");
            return (Criteria) this;
        }

        public Criteria andCustomsSupervisionConditionsEqualTo(String value) {
            addCriterion("m.customsSupervisionConditions =", value, "customsSupervisionConditions");
            return (Criteria) this;
        }

        public Criteria andCustomsSupervisionConditionsNotEqualTo(String value) {
            addCriterion("m.customsSupervisionConditions <>", value, "customsSupervisionConditions");
            return (Criteria) this;
        }

        public Criteria andCustomsSupervisionConditionsGreaterThan(String value) {
            addCriterion("m.customsSupervisionConditions >", value, "customsSupervisionConditions");
            return (Criteria) this;
        }

        public Criteria andCustomsSupervisionConditionsGreaterThanOrEqualTo(String value) {
            addCriterion("m.customsSupervisionConditions >=", value, "customsSupervisionConditions");
            return (Criteria) this;
        }

        public Criteria andCustomsSupervisionConditionsLessThan(String value) {
            addCriterion("m.customsSupervisionConditions <", value, "customsSupervisionConditions");
            return (Criteria) this;
        }

        public Criteria andCustomsSupervisionConditionsLessThanOrEqualTo(String value) {
            addCriterion("m.customsSupervisionConditions <=", value, "customsSupervisionConditions");
            return (Criteria) this;
        }

        public Criteria andCustomsSupervisionConditionsLike(String value) {
            addCriterion("m.customsSupervisionConditions like", value, "customsSupervisionConditions");
            return (Criteria) this;
        }

        public Criteria andCustomsSupervisionConditionsNotLike(String value) {
            addCriterion("m.customsSupervisionConditions not like", value, "customsSupervisionConditions");
            return (Criteria) this;
        }

        public Criteria andCustomsSupervisionConditionsIn(List<String> values) {
            addCriterion("m.customsSupervisionConditions in", values, "customsSupervisionConditions");
            return (Criteria) this;
        }

        public Criteria andCustomsSupervisionConditionsNotIn(List<String> values) {
            addCriterion("m.customsSupervisionConditions not in", values, "customsSupervisionConditions");
            return (Criteria) this;
        }

        public Criteria andCustomsSupervisionConditionsBetween(String value1, String value2) {
            addCriterion("m.customsSupervisionConditions between", value1, value2, "customsSupervisionConditions");
            return (Criteria) this;
        }

        public Criteria andCustomsSupervisionConditionsNotBetween(String value1, String value2) {
            addCriterion("m.customsSupervisionConditions not between", value1, value2, "customsSupervisionConditions");
            return (Criteria) this;
        }

        public Criteria andQualityDateIsNull() {
            addCriterion("m.qualityDate is null");
            return (Criteria) this;
        }

        public Criteria andQualityDateIsNotNull() {
            addCriterion("m.qualityDate is not null");
            return (Criteria) this;
        }

        public Criteria andQualityDateEqualTo(String value) {
            addCriterion("m.qualityDate =", value, "qualityDate");
            return (Criteria) this;
        }

        public Criteria andQualityDateNotEqualTo(String value) {
            addCriterion("m.qualityDate <>", value, "qualityDate");
            return (Criteria) this;
        }

        public Criteria andQualityDateGreaterThan(String value) {
            addCriterion("m.qualityDate >", value, "qualityDate");
            return (Criteria) this;
        }

        public Criteria andQualityDateGreaterThanOrEqualTo(String value) {
            addCriterion("m.qualityDate >=", value, "qualityDate");
            return (Criteria) this;
        }

        public Criteria andQualityDateLessThan(String value) {
            addCriterion("m.qualityDate <", value, "qualityDate");
            return (Criteria) this;
        }

        public Criteria andQualityDateLessThanOrEqualTo(String value) {
            addCriterion("m.qualityDate <=", value, "qualityDate");
            return (Criteria) this;
        }

        public Criteria andQualityDateLike(String value) {
            addCriterion("m.qualityDate like", value, "qualityDate");
            return (Criteria) this;
        }

        public Criteria andQualityDateNotLike(String value) {
            addCriterion("m.qualityDate not like", value, "qualityDate");
            return (Criteria) this;
        }

        public Criteria andQualityDateIn(List<String> values) {
            addCriterion("m.qualityDate in", values, "qualityDate");
            return (Criteria) this;
        }

        public Criteria andQualityDateNotIn(List<String> values) {
            addCriterion("m.qualityDate not in", values, "qualityDate");
            return (Criteria) this;
        }

        public Criteria andQualityDateBetween(String value1, String value2) {
            addCriterion("m.qualityDate between", value1, value2, "qualityDate");
            return (Criteria) this;
        }

        public Criteria andQualityDateNotBetween(String value1, String value2) {
            addCriterion("m.qualityDate not between", value1, value2, "qualityDate");
            return (Criteria) this;
        }

        public Criteria andVersionNOIsNull() {
            addCriterion("m.versionNO is null");
            return (Criteria) this;
        }

        public Criteria andVersionNOIsNotNull() {
            addCriterion("m.versionNO is not null");
            return (Criteria) this;
        }

        public Criteria andVersionNOEqualTo(String value) {
            addCriterion("m.versionNO =", value, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNONotEqualTo(String value) {
            addCriterion("m.versionNO <>", value, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNOGreaterThan(String value) {
            addCriterion("m.versionNO >", value, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNOGreaterThanOrEqualTo(String value) {
            addCriterion("m.versionNO >=", value, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNOLessThan(String value) {
            addCriterion("m.versionNO <", value, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNOLessThanOrEqualTo(String value) {
            addCriterion("m.versionNO <=", value, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNOLike(String value) {
            addCriterion("m.versionNO like", value, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNONotLike(String value) {
            addCriterion("m.versionNO not like", value, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNOIn(List<String> values) {
            addCriterion("m.versionNO in", values, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNONotIn(List<String> values) {
            addCriterion("m.versionNO not in", values, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNOBetween(String value1, String value2) {
            addCriterion("m.versionNO between", value1, value2, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNONotBetween(String value1, String value2) {
            addCriterion("m.versionNO not between", value1, value2, "versionNO");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionIsNull() {
            addCriterion("m.isLatestVersion is null");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionIsNotNull() {
            addCriterion("m.isLatestVersion is not null");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionEqualTo(String value) {
            addCriterion("m.isLatestVersion =", value, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionNotEqualTo(String value) {
            addCriterion("m.isLatestVersion <>", value, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionGreaterThan(String value) {
            addCriterion("m.isLatestVersion >", value, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionGreaterThanOrEqualTo(String value) {
            addCriterion("m.isLatestVersion >=", value, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionLessThan(String value) {
            addCriterion("m.isLatestVersion <", value, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionLessThanOrEqualTo(String value) {
            addCriterion("m.isLatestVersion <=", value, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionLike(String value) {
            addCriterion("m.isLatestVersion like", value, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionNotLike(String value) {
            addCriterion("m.isLatestVersion not like", value, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionIn(List<String> values) {
            addCriterion("m.isLatestVersion in", values, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionNotIn(List<String> values) {
            addCriterion("m.isLatestVersion not in", values, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionBetween(String value1, String value2) {
            addCriterion("m.isLatestVersion between", value1, value2, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionNotBetween(String value1, String value2) {
            addCriterion("m.isLatestVersion not between", value1, value2, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("m.status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("m.status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("m.status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("m.status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("m.status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("m.status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("m.status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("m.status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("m.status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("m.status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("m.status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("m.status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("m.status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("m.status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andDelFlgIsNull() {
            addCriterion("m.delFlg is null");
            return (Criteria) this;
        }

        public Criteria andDelFlgIsNotNull() {
            addCriterion("m.delFlg is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlgEqualTo(String value) {
            addCriterion("m.delFlg =", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgNotEqualTo(String value) {
            addCriterion("m.delFlg <>", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgGreaterThan(String value) {
            addCriterion("m.delFlg >", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgGreaterThanOrEqualTo(String value) {
            addCriterion("m.delFlg >=", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgLessThan(String value) {
            addCriterion("m.delFlg <", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgLessThanOrEqualTo(String value) {
            addCriterion("m.delFlg <=", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgLike(String value) {
            addCriterion("m.delFlg like", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgNotLike(String value) {
            addCriterion("m.delFlg not like", value, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgIn(List<String> values) {
            addCriterion("m.delFlg in", values, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgNotIn(List<String> values) {
            addCriterion("m.delFlg not in", values, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgBetween(String value1, String value2) {
            addCriterion("m.delFlg between", value1, value2, "delFlg");
            return (Criteria) this;
        }

        public Criteria andDelFlgNotBetween(String value1, String value2) {
            addCriterion("m.delFlg not between", value1, value2, "delFlg");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("m.creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("m.creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("m.creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("m.creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("m.creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("m.creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("m.creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("m.creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("m.creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("m.creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("m.creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("m.creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("m.creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("m.creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("m.createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("m.createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("m.createTime =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("m.createTime <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("m.createTime >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("m.createTime >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("m.createTime <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("m.createTime <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("m.createTime in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("m.createTime not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("m.createTime between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("m.createTime not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdaterIsNull() {
            addCriterion("m.updater is null");
            return (Criteria) this;
        }

        public Criteria andUpdaterIsNotNull() {
            addCriterion("m.updater is not null");
            return (Criteria) this;
        }

        public Criteria andUpdaterEqualTo(String value) {
            addCriterion("m.updater =", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotEqualTo(String value) {
            addCriterion("m.updater <>", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterGreaterThan(String value) {
            addCriterion("m.updater >", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterGreaterThanOrEqualTo(String value) {
            addCriterion("m.updater >=", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLessThan(String value) {
            addCriterion("m.updater <", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLessThanOrEqualTo(String value) {
            addCriterion("m.updater <=", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLike(String value) {
            addCriterion("m.updater like", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotLike(String value) {
            addCriterion("m.updater not like", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterIn(List<String> values) {
            addCriterion("m.updater in", values, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotIn(List<String> values) {
            addCriterion("m.updater not in", values, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterBetween(String value1, String value2) {
            addCriterion("m.updater between", value1, value2, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotBetween(String value1, String value2) {
            addCriterion("m.updater not between", value1, value2, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("m.updateTime is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("m.updateTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("m.updateTime =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("m.updateTime <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("m.updateTime >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("m.updateTime >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("m.updateTime <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("m.updateTime <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("m.updateTime in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("m.updateTime not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("m.updateTime between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("m.updateTime not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
        
        public Criteria andSupplyComIdIn(List<String> values) {
            addCriterion("sm.supplyComId in", values, "supplyComId");
            return (Criteria) this;
        }
        
        public Criteria andBuyComIdIn(List<String> values) {
            addCriterion("bm.buyComId in", values, "buyComId");
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

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getSearchStr() {
		return searchStr;
	}

	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}
    
    
    
}