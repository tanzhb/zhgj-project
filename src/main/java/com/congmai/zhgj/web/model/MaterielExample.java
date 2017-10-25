package com.congmai.zhgj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MaterielExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MaterielExample() {
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

        public Criteria andMaterielIdIsNull() {
            addCriterion("materielId is null");
            return (Criteria) this;
        }

        public Criteria andMaterielIdIsNotNull() {
            addCriterion("materielId is not null");
            return (Criteria) this;
        }

        public Criteria andMaterielIdEqualTo(String value) {
            addCriterion("materielId =", value, "materielId");
            return (Criteria) this;
        }

        public Criteria andMaterielIdNotEqualTo(String value) {
            addCriterion("materielId <>", value, "materielId");
            return (Criteria) this;
        }

        public Criteria andMaterielIdGreaterThan(String value) {
            addCriterion("materielId >", value, "materielId");
            return (Criteria) this;
        }

        public Criteria andMaterielIdGreaterThanOrEqualTo(String value) {
            addCriterion("materielId >=", value, "materielId");
            return (Criteria) this;
        }

        public Criteria andMaterielIdLessThan(String value) {
            addCriterion("materielId <", value, "materielId");
            return (Criteria) this;
        }

        public Criteria andMaterielIdLessThanOrEqualTo(String value) {
            addCriterion("materielId <=", value, "materielId");
            return (Criteria) this;
        }

        public Criteria andMaterielIdLike(String value) {
            addCriterion("materielId like", value, "materielId");
            return (Criteria) this;
        }

        public Criteria andMaterielIdNotLike(String value) {
            addCriterion("materielId not like", value, "materielId");
            return (Criteria) this;
        }

        public Criteria andMaterielIdIn(List<String> values) {
            addCriterion("materielId in", values, "materielId");
            return (Criteria) this;
        }

        public Criteria andMaterielIdNotIn(List<String> values) {
            addCriterion("materielId not in", values, "materielId");
            return (Criteria) this;
        }

        public Criteria andMaterielIdBetween(String value1, String value2) {
            addCriterion("materielId between", value1, value2, "materielId");
            return (Criteria) this;
        }

        public Criteria andMaterielIdNotBetween(String value1, String value2) {
            addCriterion("materielId not between", value1, value2, "materielId");
            return (Criteria) this;
        }

        public Criteria andMaterielNumIsNull() {
            addCriterion("materielNum is null");
            return (Criteria) this;
        }

        public Criteria andMaterielNumIsNotNull() {
            addCriterion("materielNum is not null");
            return (Criteria) this;
        }

        public Criteria andMaterielNumEqualTo(String value) {
            addCriterion("materielNum =", value, "materielNum");
            return (Criteria) this;
        }

        public Criteria andMaterielNumNotEqualTo(String value) {
            addCriterion("materielNum <>", value, "materielNum");
            return (Criteria) this;
        }

        public Criteria andMaterielNumGreaterThan(String value) {
            addCriterion("materielNum >", value, "materielNum");
            return (Criteria) this;
        }

        public Criteria andMaterielNumGreaterThanOrEqualTo(String value) {
            addCriterion("materielNum >=", value, "materielNum");
            return (Criteria) this;
        }

        public Criteria andMaterielNumLessThan(String value) {
            addCriterion("materielNum <", value, "materielNum");
            return (Criteria) this;
        }

        public Criteria andMaterielNumLessThanOrEqualTo(String value) {
            addCriterion("materielNum <=", value, "materielNum");
            return (Criteria) this;
        }

        public Criteria andMaterielNumLike(String value) {
            addCriterion("materielNum like", value, "materielNum");
            return (Criteria) this;
        }

        public Criteria andMaterielNumNotLike(String value) {
            addCriterion("materielNum not like", value, "materielNum");
            return (Criteria) this;
        }

        public Criteria andMaterielNumIn(List<String> values) {
            addCriterion("materielNum in", values, "materielNum");
            return (Criteria) this;
        }

        public Criteria andMaterielNumNotIn(List<String> values) {
            addCriterion("materielNum not in", values, "materielNum");
            return (Criteria) this;
        }

        public Criteria andMaterielNumBetween(String value1, String value2) {
            addCriterion("materielNum between", value1, value2, "materielNum");
            return (Criteria) this;
        }

        public Criteria andMaterielNumNotBetween(String value1, String value2) {
            addCriterion("materielNum not between", value1, value2, "materielNum");
            return (Criteria) this;
        }

        public Criteria andMaterielNameIsNull() {
            addCriterion("materielName is null");
            return (Criteria) this;
        }

        public Criteria andMaterielNameIsNotNull() {
            addCriterion("materielName is not null");
            return (Criteria) this;
        }

        public Criteria andMaterielNameEqualTo(String value) {
            addCriterion("materielName =", value, "materielName");
            return (Criteria) this;
        }

        public Criteria andMaterielNameNotEqualTo(String value) {
            addCriterion("materielName <>", value, "materielName");
            return (Criteria) this;
        }

        public Criteria andMaterielNameGreaterThan(String value) {
            addCriterion("materielName >", value, "materielName");
            return (Criteria) this;
        }

        public Criteria andMaterielNameGreaterThanOrEqualTo(String value) {
            addCriterion("materielName >=", value, "materielName");
            return (Criteria) this;
        }

        public Criteria andMaterielNameLessThan(String value) {
            addCriterion("materielName <", value, "materielName");
            return (Criteria) this;
        }

        public Criteria andMaterielNameLessThanOrEqualTo(String value) {
            addCriterion("materielName <=", value, "materielName");
            return (Criteria) this;
        }

        public Criteria andMaterielNameLike(String value) {
            addCriterion("materielName like", value, "materielName");
            return (Criteria) this;
        }

        public Criteria andMaterielNameNotLike(String value) {
            addCriterion("materielName not like", value, "materielName");
            return (Criteria) this;
        }

        public Criteria andMaterielNameIn(List<String> values) {
            addCriterion("materielName in", values, "materielName");
            return (Criteria) this;
        }

        public Criteria andMaterielNameNotIn(List<String> values) {
            addCriterion("materielName not in", values, "materielName");
            return (Criteria) this;
        }

        public Criteria andMaterielNameBetween(String value1, String value2) {
            addCriterion("materielName between", value1, value2, "materielName");
            return (Criteria) this;
        }

        public Criteria andMaterielNameNotBetween(String value1, String value2) {
            addCriterion("materielName not between", value1, value2, "materielName");
            return (Criteria) this;
        }

        public Criteria andSpecificationsIsNull() {
            addCriterion("specifications is null");
            return (Criteria) this;
        }

        public Criteria andSpecificationsIsNotNull() {
            addCriterion("specifications is not null");
            return (Criteria) this;
        }

        public Criteria andSpecificationsEqualTo(String value) {
            addCriterion("specifications =", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsNotEqualTo(String value) {
            addCriterion("specifications <>", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsGreaterThan(String value) {
            addCriterion("specifications >", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsGreaterThanOrEqualTo(String value) {
            addCriterion("specifications >=", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsLessThan(String value) {
            addCriterion("specifications <", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsLessThanOrEqualTo(String value) {
            addCriterion("specifications <=", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsLike(String value) {
            addCriterion("specifications like", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsNotLike(String value) {
            addCriterion("specifications not like", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsIn(List<String> values) {
            addCriterion("specifications in", values, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsNotIn(List<String> values) {
            addCriterion("specifications not in", values, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsBetween(String value1, String value2) {
            addCriterion("specifications between", value1, value2, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsNotBetween(String value1, String value2) {
            addCriterion("specifications not between", value1, value2, "specifications");
            return (Criteria) this;
        }

        public Criteria andUnitIsNull() {
            addCriterion("unit is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("unit is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(String value) {
            addCriterion("unit =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(String value) {
            addCriterion("unit <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(String value) {
            addCriterion("unit >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(String value) {
            addCriterion("unit >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(String value) {
            addCriterion("unit <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(String value) {
            addCriterion("unit <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLike(String value) {
            addCriterion("unit like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotLike(String value) {
            addCriterion("unit not like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<String> values) {
            addCriterion("unit in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<String> values) {
            addCriterion("unit not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(String value1, String value2) {
            addCriterion("unit between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(String value1, String value2) {
            addCriterion("unit not between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andParentMaterielSerialIsNull() {
            addCriterion("parentMaterielSerial is null");
            return (Criteria) this;
        }

        public Criteria andParentMaterielSerialIsNotNull() {
            addCriterion("parentMaterielSerial is not null");
            return (Criteria) this;
        }

        public Criteria andParentMaterielSerialEqualTo(String value) {
            addCriterion("parentMaterielSerial =", value, "parentMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andParentMaterielSerialNotEqualTo(String value) {
            addCriterion("parentMaterielSerial <>", value, "parentMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andParentMaterielSerialGreaterThan(String value) {
            addCriterion("parentMaterielSerial >", value, "parentMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andParentMaterielSerialGreaterThanOrEqualTo(String value) {
            addCriterion("parentMaterielSerial >=", value, "parentMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andParentMaterielSerialLessThan(String value) {
            addCriterion("parentMaterielSerial <", value, "parentMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andParentMaterielSerialLessThanOrEqualTo(String value) {
            addCriterion("parentMaterielSerial <=", value, "parentMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andParentMaterielSerialLike(String value) {
            addCriterion("parentMaterielSerial like", value, "parentMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andParentMaterielSerialNotLike(String value) {
            addCriterion("parentMaterielSerial not like", value, "parentMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andParentMaterielSerialIn(List<String> values) {
            addCriterion("parentMaterielSerial in", values, "parentMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andParentMaterielSerialNotIn(List<String> values) {
            addCriterion("parentMaterielSerial not in", values, "parentMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andParentMaterielSerialBetween(String value1, String value2) {
            addCriterion("parentMaterielSerial between", value1, value2, "parentMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andParentMaterielSerialNotBetween(String value1, String value2) {
            addCriterion("parentMaterielSerial not between", value1, value2, "parentMaterielSerial");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceIsNull() {
            addCriterion("productionPlace is null");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceIsNotNull() {
            addCriterion("productionPlace is not null");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceEqualTo(String value) {
            addCriterion("productionPlace =", value, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceNotEqualTo(String value) {
            addCriterion("productionPlace <>", value, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceGreaterThan(String value) {
            addCriterion("productionPlace >", value, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceGreaterThanOrEqualTo(String value) {
            addCriterion("productionPlace >=", value, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceLessThan(String value) {
            addCriterion("productionPlace <", value, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceLessThanOrEqualTo(String value) {
            addCriterion("productionPlace <=", value, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceLike(String value) {
            addCriterion("productionPlace like", value, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceNotLike(String value) {
            addCriterion("productionPlace not like", value, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceIn(List<String> values) {
            addCriterion("productionPlace in", values, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceNotIn(List<String> values) {
            addCriterion("productionPlace not in", values, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceBetween(String value1, String value2) {
            addCriterion("productionPlace between", value1, value2, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceNotBetween(String value1, String value2) {
            addCriterion("productionPlace not between", value1, value2, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andBrandIsNull() {
            addCriterion("brand is null");
            return (Criteria) this;
        }

        public Criteria andBrandIsNotNull() {
            addCriterion("brand is not null");
            return (Criteria) this;
        }

        public Criteria andBrandEqualTo(String value) {
            addCriterion("brand =", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotEqualTo(String value) {
            addCriterion("brand <>", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThan(String value) {
            addCriterion("brand >", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThanOrEqualTo(String value) {
            addCriterion("brand >=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThan(String value) {
            addCriterion("brand <", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThanOrEqualTo(String value) {
            addCriterion("brand <=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLike(String value) {
            addCriterion("brand like", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotLike(String value) {
            addCriterion("brand not like", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandIn(List<String> values) {
            addCriterion("brand in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotIn(List<String> values) {
            addCriterion("brand not in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandBetween(String value1, String value2) {
            addCriterion("brand between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotBetween(String value1, String value2) {
            addCriterion("brand not between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andCategory1IsNull() {
            addCriterion("category1 is null");
            return (Criteria) this;
        }

        public Criteria andCategory1IsNotNull() {
            addCriterion("category1 is not null");
            return (Criteria) this;
        }

        public Criteria andCategory1EqualTo(String value) {
            addCriterion("category1 =", value, "category1");
            return (Criteria) this;
        }

        public Criteria andCategory1NotEqualTo(String value) {
            addCriterion("category1 <>", value, "category1");
            return (Criteria) this;
        }

        public Criteria andCategory1GreaterThan(String value) {
            addCriterion("category1 >", value, "category1");
            return (Criteria) this;
        }

        public Criteria andCategory1GreaterThanOrEqualTo(String value) {
            addCriterion("category1 >=", value, "category1");
            return (Criteria) this;
        }

        public Criteria andCategory1LessThan(String value) {
            addCriterion("category1 <", value, "category1");
            return (Criteria) this;
        }

        public Criteria andCategory1LessThanOrEqualTo(String value) {
            addCriterion("category1 <=", value, "category1");
            return (Criteria) this;
        }

        public Criteria andCategory1Like(String value) {
            addCriterion("category1 like", value, "category1");
            return (Criteria) this;
        }

        public Criteria andCategory1NotLike(String value) {
            addCriterion("category1 not like", value, "category1");
            return (Criteria) this;
        }

        public Criteria andCategory1In(List<String> values) {
            addCriterion("category1 in", values, "category1");
            return (Criteria) this;
        }

        public Criteria andCategory1NotIn(List<String> values) {
            addCriterion("category1 not in", values, "category1");
            return (Criteria) this;
        }

        public Criteria andCategory1Between(String value1, String value2) {
            addCriterion("category1 between", value1, value2, "category1");
            return (Criteria) this;
        }

        public Criteria andCategory1NotBetween(String value1, String value2) {
            addCriterion("category1 not between", value1, value2, "category1");
            return (Criteria) this;
        }

        public Criteria andCategory2IsNull() {
            addCriterion("category2 is null");
            return (Criteria) this;
        }

        public Criteria andCategory2IsNotNull() {
            addCriterion("category2 is not null");
            return (Criteria) this;
        }

        public Criteria andCategory2EqualTo(String value) {
            addCriterion("category2 =", value, "category2");
            return (Criteria) this;
        }

        public Criteria andCategory2NotEqualTo(String value) {
            addCriterion("category2 <>", value, "category2");
            return (Criteria) this;
        }

        public Criteria andCategory2GreaterThan(String value) {
            addCriterion("category2 >", value, "category2");
            return (Criteria) this;
        }

        public Criteria andCategory2GreaterThanOrEqualTo(String value) {
            addCriterion("category2 >=", value, "category2");
            return (Criteria) this;
        }

        public Criteria andCategory2LessThan(String value) {
            addCriterion("category2 <", value, "category2");
            return (Criteria) this;
        }

        public Criteria andCategory2LessThanOrEqualTo(String value) {
            addCriterion("category2 <=", value, "category2");
            return (Criteria) this;
        }

        public Criteria andCategory2Like(String value) {
            addCriterion("category2 like", value, "category2");
            return (Criteria) this;
        }

        public Criteria andCategory2NotLike(String value) {
            addCriterion("category2 not like", value, "category2");
            return (Criteria) this;
        }

        public Criteria andCategory2In(List<String> values) {
            addCriterion("category2 in", values, "category2");
            return (Criteria) this;
        }

        public Criteria andCategory2NotIn(List<String> values) {
            addCriterion("category2 not in", values, "category2");
            return (Criteria) this;
        }

        public Criteria andCategory2Between(String value1, String value2) {
            addCriterion("category2 between", value1, value2, "category2");
            return (Criteria) this;
        }

        public Criteria andCategory2NotBetween(String value1, String value2) {
            addCriterion("category2 not between", value1, value2, "category2");
            return (Criteria) this;
        }

        public Criteria andCategory3IsNull() {
            addCriterion("category3 is null");
            return (Criteria) this;
        }

        public Criteria andCategory3IsNotNull() {
            addCriterion("category3 is not null");
            return (Criteria) this;
        }

        public Criteria andCategory3EqualTo(String value) {
            addCriterion("category3 =", value, "category3");
            return (Criteria) this;
        }

        public Criteria andCategory3NotEqualTo(String value) {
            addCriterion("category3 <>", value, "category3");
            return (Criteria) this;
        }

        public Criteria andCategory3GreaterThan(String value) {
            addCriterion("category3 >", value, "category3");
            return (Criteria) this;
        }

        public Criteria andCategory3GreaterThanOrEqualTo(String value) {
            addCriterion("category3 >=", value, "category3");
            return (Criteria) this;
        }

        public Criteria andCategory3LessThan(String value) {
            addCriterion("category3 <", value, "category3");
            return (Criteria) this;
        }

        public Criteria andCategory3LessThanOrEqualTo(String value) {
            addCriterion("category3 <=", value, "category3");
            return (Criteria) this;
        }

        public Criteria andCategory3Like(String value) {
            addCriterion("category3 like", value, "category3");
            return (Criteria) this;
        }

        public Criteria andCategory3NotLike(String value) {
            addCriterion("category3 not like", value, "category3");
            return (Criteria) this;
        }

        public Criteria andCategory3In(List<String> values) {
            addCriterion("category3 in", values, "category3");
            return (Criteria) this;
        }

        public Criteria andCategory3NotIn(List<String> values) {
            addCriterion("category3 not in", values, "category3");
            return (Criteria) this;
        }

        public Criteria andCategory3Between(String value1, String value2) {
            addCriterion("category3 between", value1, value2, "category3");
            return (Criteria) this;
        }

        public Criteria andCategory3NotBetween(String value1, String value2) {
            addCriterion("category3 not between", value1, value2, "category3");
            return (Criteria) this;
        }

        public Criteria andMaterielAttributeIsNull() {
            addCriterion("materielAttribute is null");
            return (Criteria) this;
        }

        public Criteria andMaterielAttributeIsNotNull() {
            addCriterion("materielAttribute is not null");
            return (Criteria) this;
        }

        public Criteria andMaterielAttributeEqualTo(String value) {
            addCriterion("materielAttribute =", value, "materielAttribute");
            return (Criteria) this;
        }

        public Criteria andMaterielAttributeNotEqualTo(String value) {
            addCriterion("materielAttribute <>", value, "materielAttribute");
            return (Criteria) this;
        }

        public Criteria andMaterielAttributeGreaterThan(String value) {
            addCriterion("materielAttribute >", value, "materielAttribute");
            return (Criteria) this;
        }

        public Criteria andMaterielAttributeGreaterThanOrEqualTo(String value) {
            addCriterion("materielAttribute >=", value, "materielAttribute");
            return (Criteria) this;
        }

        public Criteria andMaterielAttributeLessThan(String value) {
            addCriterion("materielAttribute <", value, "materielAttribute");
            return (Criteria) this;
        }

        public Criteria andMaterielAttributeLessThanOrEqualTo(String value) {
            addCriterion("materielAttribute <=", value, "materielAttribute");
            return (Criteria) this;
        }

        public Criteria andMaterielAttributeLike(String value) {
            addCriterion("materielAttribute like", value, "materielAttribute");
            return (Criteria) this;
        }

        public Criteria andMaterielAttributeNotLike(String value) {
            addCriterion("materielAttribute not like", value, "materielAttribute");
            return (Criteria) this;
        }

        public Criteria andMaterielAttributeIn(List<String> values) {
            addCriterion("materielAttribute in", values, "materielAttribute");
            return (Criteria) this;
        }

        public Criteria andMaterielAttributeNotIn(List<String> values) {
            addCriterion("materielAttribute not in", values, "materielAttribute");
            return (Criteria) this;
        }

        public Criteria andMaterielAttributeBetween(String value1, String value2) {
            addCriterion("materielAttribute between", value1, value2, "materielAttribute");
            return (Criteria) this;
        }

        public Criteria andMaterielAttributeNotBetween(String value1, String value2) {
            addCriterion("materielAttribute not between", value1, value2, "materielAttribute");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeIsNull() {
            addCriterion("mnemonicCode is null");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeIsNotNull() {
            addCriterion("mnemonicCode is not null");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeEqualTo(String value) {
            addCriterion("mnemonicCode =", value, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeNotEqualTo(String value) {
            addCriterion("mnemonicCode <>", value, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeGreaterThan(String value) {
            addCriterion("mnemonicCode >", value, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeGreaterThanOrEqualTo(String value) {
            addCriterion("mnemonicCode >=", value, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeLessThan(String value) {
            addCriterion("mnemonicCode <", value, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeLessThanOrEqualTo(String value) {
            addCriterion("mnemonicCode <=", value, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeLike(String value) {
            addCriterion("mnemonicCode like", value, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeNotLike(String value) {
            addCriterion("mnemonicCode not like", value, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeIn(List<String> values) {
            addCriterion("mnemonicCode in", values, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeNotIn(List<String> values) {
            addCriterion("mnemonicCode not in", values, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeBetween(String value1, String value2) {
            addCriterion("mnemonicCode between", value1, value2, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeNotBetween(String value1, String value2) {
            addCriterion("mnemonicCode not between", value1, value2, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andIsBOMIsNull() {
            addCriterion("isBOM is null");
            return (Criteria) this;
        }

        public Criteria andIsBOMIsNotNull() {
            addCriterion("isBOM is not null");
            return (Criteria) this;
        }

        public Criteria andIsBOMEqualTo(String value) {
            addCriterion("isBOM =", value, "isBOM");
            return (Criteria) this;
        }

        public Criteria andIsBOMNotEqualTo(String value) {
            addCriterion("isBOM <>", value, "isBOM");
            return (Criteria) this;
        }

        public Criteria andIsBOMGreaterThan(String value) {
            addCriterion("isBOM >", value, "isBOM");
            return (Criteria) this;
        }

        public Criteria andIsBOMGreaterThanOrEqualTo(String value) {
            addCriterion("isBOM >=", value, "isBOM");
            return (Criteria) this;
        }

        public Criteria andIsBOMLessThan(String value) {
            addCriterion("isBOM <", value, "isBOM");
            return (Criteria) this;
        }

        public Criteria andIsBOMLessThanOrEqualTo(String value) {
            addCriterion("isBOM <=", value, "isBOM");
            return (Criteria) this;
        }

        public Criteria andIsBOMLike(String value) {
            addCriterion("isBOM like", value, "isBOM");
            return (Criteria) this;
        }

        public Criteria andIsBOMNotLike(String value) {
            addCriterion("isBOM not like", value, "isBOM");
            return (Criteria) this;
        }

        public Criteria andIsBOMIn(List<String> values) {
            addCriterion("isBOM in", values, "isBOM");
            return (Criteria) this;
        }

        public Criteria andIsBOMNotIn(List<String> values) {
            addCriterion("isBOM not in", values, "isBOM");
            return (Criteria) this;
        }

        public Criteria andIsBOMBetween(String value1, String value2) {
            addCriterion("isBOM between", value1, value2, "isBOM");
            return (Criteria) this;
        }

        public Criteria andIsBOMNotBetween(String value1, String value2) {
            addCriterion("isBOM not between", value1, value2, "isBOM");
            return (Criteria) this;
        }

        public Criteria andStockUnitIsNull() {
            addCriterion("stockUnit is null");
            return (Criteria) this;
        }

        public Criteria andStockUnitIsNotNull() {
            addCriterion("stockUnit is not null");
            return (Criteria) this;
        }

        public Criteria andStockUnitEqualTo(String value) {
            addCriterion("stockUnit =", value, "stockUnit");
            return (Criteria) this;
        }

        public Criteria andStockUnitNotEqualTo(String value) {
            addCriterion("stockUnit <>", value, "stockUnit");
            return (Criteria) this;
        }

        public Criteria andStockUnitGreaterThan(String value) {
            addCriterion("stockUnit >", value, "stockUnit");
            return (Criteria) this;
        }

        public Criteria andStockUnitGreaterThanOrEqualTo(String value) {
            addCriterion("stockUnit >=", value, "stockUnit");
            return (Criteria) this;
        }

        public Criteria andStockUnitLessThan(String value) {
            addCriterion("stockUnit <", value, "stockUnit");
            return (Criteria) this;
        }

        public Criteria andStockUnitLessThanOrEqualTo(String value) {
            addCriterion("stockUnit <=", value, "stockUnit");
            return (Criteria) this;
        }

        public Criteria andStockUnitLike(String value) {
            addCriterion("stockUnit like", value, "stockUnit");
            return (Criteria) this;
        }

        public Criteria andStockUnitNotLike(String value) {
            addCriterion("stockUnit not like", value, "stockUnit");
            return (Criteria) this;
        }

        public Criteria andStockUnitIn(List<String> values) {
            addCriterion("stockUnit in", values, "stockUnit");
            return (Criteria) this;
        }

        public Criteria andStockUnitNotIn(List<String> values) {
            addCriterion("stockUnit not in", values, "stockUnit");
            return (Criteria) this;
        }

        public Criteria andStockUnitBetween(String value1, String value2) {
            addCriterion("stockUnit between", value1, value2, "stockUnit");
            return (Criteria) this;
        }

        public Criteria andStockUnitNotBetween(String value1, String value2) {
            addCriterion("stockUnit not between", value1, value2, "stockUnit");
            return (Criteria) this;
        }

        public Criteria andOriginCountryIsNull() {
            addCriterion("originCountry is null");
            return (Criteria) this;
        }

        public Criteria andOriginCountryIsNotNull() {
            addCriterion("originCountry is not null");
            return (Criteria) this;
        }

        public Criteria andOriginCountryEqualTo(String value) {
            addCriterion("originCountry =", value, "originCountry");
            return (Criteria) this;
        }

        public Criteria andOriginCountryNotEqualTo(String value) {
            addCriterion("originCountry <>", value, "originCountry");
            return (Criteria) this;
        }

        public Criteria andOriginCountryGreaterThan(String value) {
            addCriterion("originCountry >", value, "originCountry");
            return (Criteria) this;
        }

        public Criteria andOriginCountryGreaterThanOrEqualTo(String value) {
            addCriterion("originCountry >=", value, "originCountry");
            return (Criteria) this;
        }

        public Criteria andOriginCountryLessThan(String value) {
            addCriterion("originCountry <", value, "originCountry");
            return (Criteria) this;
        }

        public Criteria andOriginCountryLessThanOrEqualTo(String value) {
            addCriterion("originCountry <=", value, "originCountry");
            return (Criteria) this;
        }

        public Criteria andOriginCountryLike(String value) {
            addCriterion("originCountry like", value, "originCountry");
            return (Criteria) this;
        }

        public Criteria andOriginCountryNotLike(String value) {
            addCriterion("originCountry not like", value, "originCountry");
            return (Criteria) this;
        }

        public Criteria andOriginCountryIn(List<String> values) {
            addCriterion("originCountry in", values, "originCountry");
            return (Criteria) this;
        }

        public Criteria andOriginCountryNotIn(List<String> values) {
            addCriterion("originCountry not in", values, "originCountry");
            return (Criteria) this;
        }

        public Criteria andOriginCountryBetween(String value1, String value2) {
            addCriterion("originCountry between", value1, value2, "originCountry");
            return (Criteria) this;
        }

        public Criteria andOriginCountryNotBetween(String value1, String value2) {
            addCriterion("originCountry not between", value1, value2, "originCountry");
            return (Criteria) this;
        }

        public Criteria andWidthIsNull() {
            addCriterion("width is null");
            return (Criteria) this;
        }

        public Criteria andWidthIsNotNull() {
            addCriterion("width is not null");
            return (Criteria) this;
        }

        public Criteria andWidthEqualTo(String value) {
            addCriterion("width =", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotEqualTo(String value) {
            addCriterion("width <>", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthGreaterThan(String value) {
            addCriterion("width >", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthGreaterThanOrEqualTo(String value) {
            addCriterion("width >=", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLessThan(String value) {
            addCriterion("width <", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLessThanOrEqualTo(String value) {
            addCriterion("width <=", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLike(String value) {
            addCriterion("width like", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotLike(String value) {
            addCriterion("width not like", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthIn(List<String> values) {
            addCriterion("width in", values, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotIn(List<String> values) {
            addCriterion("width not in", values, "width");
            return (Criteria) this;
        }

        public Criteria andWidthBetween(String value1, String value2) {
            addCriterion("width between", value1, value2, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotBetween(String value1, String value2) {
            addCriterion("width not between", value1, value2, "width");
            return (Criteria) this;
        }

        public Criteria andHeightIsNull() {
            addCriterion("height is null");
            return (Criteria) this;
        }

        public Criteria andHeightIsNotNull() {
            addCriterion("height is not null");
            return (Criteria) this;
        }

        public Criteria andHeightEqualTo(String value) {
            addCriterion("height =", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotEqualTo(String value) {
            addCriterion("height <>", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightGreaterThan(String value) {
            addCriterion("height >", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightGreaterThanOrEqualTo(String value) {
            addCriterion("height >=", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLessThan(String value) {
            addCriterion("height <", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLessThanOrEqualTo(String value) {
            addCriterion("height <=", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLike(String value) {
            addCriterion("height like", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotLike(String value) {
            addCriterion("height not like", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightIn(List<String> values) {
            addCriterion("height in", values, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotIn(List<String> values) {
            addCriterion("height not in", values, "height");
            return (Criteria) this;
        }

        public Criteria andHeightBetween(String value1, String value2) {
            addCriterion("height between", value1, value2, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotBetween(String value1, String value2) {
            addCriterion("height not between", value1, value2, "height");
            return (Criteria) this;
        }

        public Criteria andLengthIsNull() {
            addCriterion("length is null");
            return (Criteria) this;
        }

        public Criteria andLengthIsNotNull() {
            addCriterion("length is not null");
            return (Criteria) this;
        }

        public Criteria andLengthEqualTo(String value) {
            addCriterion("length =", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotEqualTo(String value) {
            addCriterion("length <>", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthGreaterThan(String value) {
            addCriterion("length >", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthGreaterThanOrEqualTo(String value) {
            addCriterion("length >=", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthLessThan(String value) {
            addCriterion("length <", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthLessThanOrEqualTo(String value) {
            addCriterion("length <=", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthLike(String value) {
            addCriterion("length like", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotLike(String value) {
            addCriterion("length not like", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthIn(List<String> values) {
            addCriterion("length in", values, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotIn(List<String> values) {
            addCriterion("length not in", values, "length");
            return (Criteria) this;
        }

        public Criteria andLengthBetween(String value1, String value2) {
            addCriterion("length between", value1, value2, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotBetween(String value1, String value2) {
            addCriterion("length not between", value1, value2, "length");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNull() {
            addCriterion("currency is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNotNull() {
            addCriterion("currency is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyEqualTo(String value) {
            addCriterion("currency =", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotEqualTo(String value) {
            addCriterion("currency <>", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThan(String value) {
            addCriterion("currency >", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("currency >=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThan(String value) {
            addCriterion("currency <", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThanOrEqualTo(String value) {
            addCriterion("currency <=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLike(String value) {
            addCriterion("currency like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotLike(String value) {
            addCriterion("currency not like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyIn(List<String> values) {
            addCriterion("currency in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotIn(List<String> values) {
            addCriterion("currency not in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyBetween(String value1, String value2) {
            addCriterion("currency between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotBetween(String value1, String value2) {
            addCriterion("currency not between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIsNull() {
            addCriterion("unitPrice is null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIsNotNull() {
            addCriterion("unitPrice is not null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceEqualTo(String value) {
            addCriterion("unitPrice =", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotEqualTo(String value) {
            addCriterion("unitPrice <>", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGreaterThan(String value) {
            addCriterion("unitPrice >", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGreaterThanOrEqualTo(String value) {
            addCriterion("unitPrice >=", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceLessThan(String value) {
            addCriterion("unitPrice <", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceLessThanOrEqualTo(String value) {
            addCriterion("unitPrice <=", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceLike(String value) {
            addCriterion("unitPrice like", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotLike(String value) {
            addCriterion("unitPrice not like", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIn(List<String> values) {
            addCriterion("unitPrice in", values, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotIn(List<String> values) {
            addCriterion("unitPrice not in", values, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceBetween(String value1, String value2) {
            addCriterion("unitPrice between", value1, value2, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotBetween(String value1, String value2) {
            addCriterion("unitPrice not between", value1, value2, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andDeliveryCycleIsNull() {
            addCriterion("deliveryCycle is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryCycleIsNotNull() {
            addCriterion("deliveryCycle is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryCycleEqualTo(String value) {
            addCriterion("deliveryCycle =", value, "deliveryCycle");
            return (Criteria) this;
        }

        public Criteria andDeliveryCycleNotEqualTo(String value) {
            addCriterion("deliveryCycle <>", value, "deliveryCycle");
            return (Criteria) this;
        }

        public Criteria andDeliveryCycleGreaterThan(String value) {
            addCriterion("deliveryCycle >", value, "deliveryCycle");
            return (Criteria) this;
        }

        public Criteria andDeliveryCycleGreaterThanOrEqualTo(String value) {
            addCriterion("deliveryCycle >=", value, "deliveryCycle");
            return (Criteria) this;
        }

        public Criteria andDeliveryCycleLessThan(String value) {
            addCriterion("deliveryCycle <", value, "deliveryCycle");
            return (Criteria) this;
        }

        public Criteria andDeliveryCycleLessThanOrEqualTo(String value) {
            addCriterion("deliveryCycle <=", value, "deliveryCycle");
            return (Criteria) this;
        }

        public Criteria andDeliveryCycleLike(String value) {
            addCriterion("deliveryCycle like", value, "deliveryCycle");
            return (Criteria) this;
        }

        public Criteria andDeliveryCycleNotLike(String value) {
            addCriterion("deliveryCycle not like", value, "deliveryCycle");
            return (Criteria) this;
        }

        public Criteria andDeliveryCycleIn(List<String> values) {
            addCriterion("deliveryCycle in", values, "deliveryCycle");
            return (Criteria) this;
        }

        public Criteria andDeliveryCycleNotIn(List<String> values) {
            addCriterion("deliveryCycle not in", values, "deliveryCycle");
            return (Criteria) this;
        }

        public Criteria andDeliveryCycleBetween(String value1, String value2) {
            addCriterion("deliveryCycle between", value1, value2, "deliveryCycle");
            return (Criteria) this;
        }

        public Criteria andDeliveryCycleNotBetween(String value1, String value2) {
            addCriterion("deliveryCycle not between", value1, value2, "deliveryCycle");
            return (Criteria) this;
        }

        public Criteria andFilingItemNoIsNull() {
            addCriterion("filingItemNo is null");
            return (Criteria) this;
        }

        public Criteria andFilingItemNoIsNotNull() {
            addCriterion("filingItemNo is not null");
            return (Criteria) this;
        }

        public Criteria andFilingItemNoEqualTo(String value) {
            addCriterion("filingItemNo =", value, "filingItemNo");
            return (Criteria) this;
        }

        public Criteria andFilingItemNoNotEqualTo(String value) {
            addCriterion("filingItemNo <>", value, "filingItemNo");
            return (Criteria) this;
        }

        public Criteria andFilingItemNoGreaterThan(String value) {
            addCriterion("filingItemNo >", value, "filingItemNo");
            return (Criteria) this;
        }

        public Criteria andFilingItemNoGreaterThanOrEqualTo(String value) {
            addCriterion("filingItemNo >=", value, "filingItemNo");
            return (Criteria) this;
        }

        public Criteria andFilingItemNoLessThan(String value) {
            addCriterion("filingItemNo <", value, "filingItemNo");
            return (Criteria) this;
        }

        public Criteria andFilingItemNoLessThanOrEqualTo(String value) {
            addCriterion("filingItemNo <=", value, "filingItemNo");
            return (Criteria) this;
        }

        public Criteria andFilingItemNoLike(String value) {
            addCriterion("filingItemNo like", value, "filingItemNo");
            return (Criteria) this;
        }

        public Criteria andFilingItemNoNotLike(String value) {
            addCriterion("filingItemNo not like", value, "filingItemNo");
            return (Criteria) this;
        }

        public Criteria andFilingItemNoIn(List<String> values) {
            addCriterion("filingItemNo in", values, "filingItemNo");
            return (Criteria) this;
        }

        public Criteria andFilingItemNoNotIn(List<String> values) {
            addCriterion("filingItemNo not in", values, "filingItemNo");
            return (Criteria) this;
        }

        public Criteria andFilingItemNoBetween(String value1, String value2) {
            addCriterion("filingItemNo between", value1, value2, "filingItemNo");
            return (Criteria) this;
        }

        public Criteria andFilingItemNoNotBetween(String value1, String value2) {
            addCriterion("filingItemNo not between", value1, value2, "filingItemNo");
            return (Criteria) this;
        }

        public Criteria andVolumeIsNull() {
            addCriterion("volume is null");
            return (Criteria) this;
        }

        public Criteria andVolumeIsNotNull() {
            addCriterion("volume is not null");
            return (Criteria) this;
        }

        public Criteria andVolumeEqualTo(String value) {
            addCriterion("volume =", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotEqualTo(String value) {
            addCriterion("volume <>", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeGreaterThan(String value) {
            addCriterion("volume >", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeGreaterThanOrEqualTo(String value) {
            addCriterion("volume >=", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLessThan(String value) {
            addCriterion("volume <", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLessThanOrEqualTo(String value) {
            addCriterion("volume <=", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLike(String value) {
            addCriterion("volume like", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotLike(String value) {
            addCriterion("volume not like", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeIn(List<String> values) {
            addCriterion("volume in", values, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotIn(List<String> values) {
            addCriterion("volume not in", values, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeBetween(String value1, String value2) {
            addCriterion("volume between", value1, value2, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotBetween(String value1, String value2) {
            addCriterion("volume not between", value1, value2, "volume");
            return (Criteria) this;
        }

        public Criteria andWeightIsNull() {
            addCriterion("weight is null");
            return (Criteria) this;
        }

        public Criteria andWeightIsNotNull() {
            addCriterion("weight is not null");
            return (Criteria) this;
        }

        public Criteria andWeightEqualTo(String value) {
            addCriterion("weight =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(String value) {
            addCriterion("weight <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(String value) {
            addCriterion("weight >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(String value) {
            addCriterion("weight >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(String value) {
            addCriterion("weight <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(String value) {
            addCriterion("weight <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLike(String value) {
            addCriterion("weight like", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotLike(String value) {
            addCriterion("weight not like", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<String> values) {
            addCriterion("weight in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<String> values) {
            addCriterion("weight not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(String value1, String value2) {
            addCriterion("weight between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(String value1, String value2) {
            addCriterion("weight not between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andPalletSpecificationIsNull() {
            addCriterion("palletSpecification is null");
            return (Criteria) this;
        }

        public Criteria andPalletSpecificationIsNotNull() {
            addCriterion("palletSpecification is not null");
            return (Criteria) this;
        }

        public Criteria andPalletSpecificationEqualTo(String value) {
            addCriterion("palletSpecification =", value, "palletSpecification");
            return (Criteria) this;
        }

        public Criteria andPalletSpecificationNotEqualTo(String value) {
            addCriterion("palletSpecification <>", value, "palletSpecification");
            return (Criteria) this;
        }

        public Criteria andPalletSpecificationGreaterThan(String value) {
            addCriterion("palletSpecification >", value, "palletSpecification");
            return (Criteria) this;
        }

        public Criteria andPalletSpecificationGreaterThanOrEqualTo(String value) {
            addCriterion("palletSpecification >=", value, "palletSpecification");
            return (Criteria) this;
        }

        public Criteria andPalletSpecificationLessThan(String value) {
            addCriterion("palletSpecification <", value, "palletSpecification");
            return (Criteria) this;
        }

        public Criteria andPalletSpecificationLessThanOrEqualTo(String value) {
            addCriterion("palletSpecification <=", value, "palletSpecification");
            return (Criteria) this;
        }

        public Criteria andPalletSpecificationLike(String value) {
            addCriterion("palletSpecification like", value, "palletSpecification");
            return (Criteria) this;
        }

        public Criteria andPalletSpecificationNotLike(String value) {
            addCriterion("palletSpecification not like", value, "palletSpecification");
            return (Criteria) this;
        }

        public Criteria andPalletSpecificationIn(List<String> values) {
            addCriterion("palletSpecification in", values, "palletSpecification");
            return (Criteria) this;
        }

        public Criteria andPalletSpecificationNotIn(List<String> values) {
            addCriterion("palletSpecification not in", values, "palletSpecification");
            return (Criteria) this;
        }

        public Criteria andPalletSpecificationBetween(String value1, String value2) {
            addCriterion("palletSpecification between", value1, value2, "palletSpecification");
            return (Criteria) this;
        }

        public Criteria andPalletSpecificationNotBetween(String value1, String value2) {
            addCriterion("palletSpecification not between", value1, value2, "palletSpecification");
            return (Criteria) this;
        }

        public Criteria andManufactureDateIsNull() {
            addCriterion("manufactureDate is null");
            return (Criteria) this;
        }

        public Criteria andManufactureDateIsNotNull() {
            addCriterion("manufactureDate is not null");
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

        public Criteria andCustomsSupervisionConditionsIsNull() {
            addCriterion("customsSupervisionConditions is null");
            return (Criteria) this;
        }

        public Criteria andCustomsSupervisionConditionsIsNotNull() {
            addCriterion("customsSupervisionConditions is not null");
            return (Criteria) this;
        }

        public Criteria andCustomsSupervisionConditionsEqualTo(String value) {
            addCriterion("customsSupervisionConditions =", value, "customsSupervisionConditions");
            return (Criteria) this;
        }

        public Criteria andCustomsSupervisionConditionsNotEqualTo(String value) {
            addCriterion("customsSupervisionConditions <>", value, "customsSupervisionConditions");
            return (Criteria) this;
        }

        public Criteria andCustomsSupervisionConditionsGreaterThan(String value) {
            addCriterion("customsSupervisionConditions >", value, "customsSupervisionConditions");
            return (Criteria) this;
        }

        public Criteria andCustomsSupervisionConditionsGreaterThanOrEqualTo(String value) {
            addCriterion("customsSupervisionConditions >=", value, "customsSupervisionConditions");
            return (Criteria) this;
        }

        public Criteria andCustomsSupervisionConditionsLessThan(String value) {
            addCriterion("customsSupervisionConditions <", value, "customsSupervisionConditions");
            return (Criteria) this;
        }

        public Criteria andCustomsSupervisionConditionsLessThanOrEqualTo(String value) {
            addCriterion("customsSupervisionConditions <=", value, "customsSupervisionConditions");
            return (Criteria) this;
        }

        public Criteria andCustomsSupervisionConditionsLike(String value) {
            addCriterion("customsSupervisionConditions like", value, "customsSupervisionConditions");
            return (Criteria) this;
        }

        public Criteria andCustomsSupervisionConditionsNotLike(String value) {
            addCriterion("customsSupervisionConditions not like", value, "customsSupervisionConditions");
            return (Criteria) this;
        }

        public Criteria andCustomsSupervisionConditionsIn(List<String> values) {
            addCriterion("customsSupervisionConditions in", values, "customsSupervisionConditions");
            return (Criteria) this;
        }

        public Criteria andCustomsSupervisionConditionsNotIn(List<String> values) {
            addCriterion("customsSupervisionConditions not in", values, "customsSupervisionConditions");
            return (Criteria) this;
        }

        public Criteria andCustomsSupervisionConditionsBetween(String value1, String value2) {
            addCriterion("customsSupervisionConditions between", value1, value2, "customsSupervisionConditions");
            return (Criteria) this;
        }

        public Criteria andCustomsSupervisionConditionsNotBetween(String value1, String value2) {
            addCriterion("customsSupervisionConditions not between", value1, value2, "customsSupervisionConditions");
            return (Criteria) this;
        }

        public Criteria andCustomsCodeIsNull() {
            addCriterion("customsCode is null");
            return (Criteria) this;
        }

        public Criteria andCustomsCodeIsNotNull() {
            addCriterion("customsCode is not null");
            return (Criteria) this;
        }

        public Criteria andCustomsCodeEqualTo(String value) {
            addCriterion("customsCode =", value, "customsCode");
            return (Criteria) this;
        }

        public Criteria andCustomsCodeNotEqualTo(String value) {
            addCriterion("customsCode <>", value, "customsCode");
            return (Criteria) this;
        }

        public Criteria andCustomsCodeGreaterThan(String value) {
            addCriterion("customsCode >", value, "customsCode");
            return (Criteria) this;
        }

        public Criteria andCustomsCodeGreaterThanOrEqualTo(String value) {
            addCriterion("customsCode >=", value, "customsCode");
            return (Criteria) this;
        }

        public Criteria andCustomsCodeLessThan(String value) {
            addCriterion("customsCode <", value, "customsCode");
            return (Criteria) this;
        }

        public Criteria andCustomsCodeLessThanOrEqualTo(String value) {
            addCriterion("customsCode <=", value, "customsCode");
            return (Criteria) this;
        }

        public Criteria andCustomsCodeLike(String value) {
            addCriterion("customsCode like", value, "customsCode");
            return (Criteria) this;
        }

        public Criteria andCustomsCodeNotLike(String value) {
            addCriterion("customsCode not like", value, "customsCode");
            return (Criteria) this;
        }

        public Criteria andCustomsCodeIn(List<String> values) {
            addCriterion("customsCode in", values, "customsCode");
            return (Criteria) this;
        }

        public Criteria andCustomsCodeNotIn(List<String> values) {
            addCriterion("customsCode not in", values, "customsCode");
            return (Criteria) this;
        }

        public Criteria andCustomsCodeBetween(String value1, String value2) {
            addCriterion("customsCode between", value1, value2, "customsCode");
            return (Criteria) this;
        }

        public Criteria andCustomsCodeNotBetween(String value1, String value2) {
            addCriterion("customsCode not between", value1, value2, "customsCode");
            return (Criteria) this;
        }

        public Criteria andQualityDateIsNull() {
            addCriterion("qualityDate is null");
            return (Criteria) this;
        }

        public Criteria andQualityDateIsNotNull() {
            addCriterion("qualityDate is not null");
            return (Criteria) this;
        }

        public Criteria andQualityDateEqualTo(String value) {
            addCriterion("qualityDate =", value, "qualityDate");
            return (Criteria) this;
        }

        public Criteria andQualityDateNotEqualTo(String value) {
            addCriterion("qualityDate <>", value, "qualityDate");
            return (Criteria) this;
        }

        public Criteria andQualityDateGreaterThan(String value) {
            addCriterion("qualityDate >", value, "qualityDate");
            return (Criteria) this;
        }

        public Criteria andQualityDateGreaterThanOrEqualTo(String value) {
            addCriterion("qualityDate >=", value, "qualityDate");
            return (Criteria) this;
        }

        public Criteria andQualityDateLessThan(String value) {
            addCriterion("qualityDate <", value, "qualityDate");
            return (Criteria) this;
        }

        public Criteria andQualityDateLessThanOrEqualTo(String value) {
            addCriterion("qualityDate <=", value, "qualityDate");
            return (Criteria) this;
        }

        public Criteria andQualityDateLike(String value) {
            addCriterion("qualityDate like", value, "qualityDate");
            return (Criteria) this;
        }

        public Criteria andQualityDateNotLike(String value) {
            addCriterion("qualityDate not like", value, "qualityDate");
            return (Criteria) this;
        }

        public Criteria andQualityDateIn(List<String> values) {
            addCriterion("qualityDate in", values, "qualityDate");
            return (Criteria) this;
        }

        public Criteria andQualityDateNotIn(List<String> values) {
            addCriterion("qualityDate not in", values, "qualityDate");
            return (Criteria) this;
        }

        public Criteria andQualityDateBetween(String value1, String value2) {
            addCriterion("qualityDate between", value1, value2, "qualityDate");
            return (Criteria) this;
        }

        public Criteria andQualityDateNotBetween(String value1, String value2) {
            addCriterion("qualityDate not between", value1, value2, "qualityDate");
            return (Criteria) this;
        }

        public Criteria andPackageNumIsNull() {
            addCriterion("packageNum is null");
            return (Criteria) this;
        }

        public Criteria andPackageNumIsNotNull() {
            addCriterion("packageNum is not null");
            return (Criteria) this;
        }

        public Criteria andPackageNumEqualTo(String value) {
            addCriterion("packageNum =", value, "packageNum");
            return (Criteria) this;
        }

        public Criteria andPackageNumNotEqualTo(String value) {
            addCriterion("packageNum <>", value, "packageNum");
            return (Criteria) this;
        }

        public Criteria andPackageNumGreaterThan(String value) {
            addCriterion("packageNum >", value, "packageNum");
            return (Criteria) this;
        }

        public Criteria andPackageNumGreaterThanOrEqualTo(String value) {
            addCriterion("packageNum >=", value, "packageNum");
            return (Criteria) this;
        }

        public Criteria andPackageNumLessThan(String value) {
            addCriterion("packageNum <", value, "packageNum");
            return (Criteria) this;
        }

        public Criteria andPackageNumLessThanOrEqualTo(String value) {
            addCriterion("packageNum <=", value, "packageNum");
            return (Criteria) this;
        }

        public Criteria andPackageNumLike(String value) {
            addCriterion("packageNum like", value, "packageNum");
            return (Criteria) this;
        }

        public Criteria andPackageNumNotLike(String value) {
            addCriterion("packageNum not like", value, "packageNum");
            return (Criteria) this;
        }

        public Criteria andPackageNumIn(List<String> values) {
            addCriterion("packageNum in", values, "packageNum");
            return (Criteria) this;
        }

        public Criteria andPackageNumNotIn(List<String> values) {
            addCriterion("packageNum not in", values, "packageNum");
            return (Criteria) this;
        }

        public Criteria andPackageNumBetween(String value1, String value2) {
            addCriterion("packageNum between", value1, value2, "packageNum");
            return (Criteria) this;
        }

        public Criteria andPackageNumNotBetween(String value1, String value2) {
            addCriterion("packageNum not between", value1, value2, "packageNum");
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

        public Criteria andPackageUnitIsNull() {
            addCriterion("packageUnit is null");
            return (Criteria) this;
        }

        public Criteria andPackageUnitIsNotNull() {
            addCriterion("packageUnit is not null");
            return (Criteria) this;
        }

        public Criteria andPackageUnitEqualTo(String value) {
            addCriterion("packageUnit =", value, "packageUnit");
            return (Criteria) this;
        }

        public Criteria andPackageUnitNotEqualTo(String value) {
            addCriterion("packageUnit <>", value, "packageUnit");
            return (Criteria) this;
        }

        public Criteria andPackageUnitGreaterThan(String value) {
            addCriterion("packageUnit >", value, "packageUnit");
            return (Criteria) this;
        }

        public Criteria andPackageUnitGreaterThanOrEqualTo(String value) {
            addCriterion("packageUnit >=", value, "packageUnit");
            return (Criteria) this;
        }

        public Criteria andPackageUnitLessThan(String value) {
            addCriterion("packageUnit <", value, "packageUnit");
            return (Criteria) this;
        }

        public Criteria andPackageUnitLessThanOrEqualTo(String value) {
            addCriterion("packageUnit <=", value, "packageUnit");
            return (Criteria) this;
        }

        public Criteria andPackageUnitLike(String value) {
            addCriterion("packageUnit like", value, "packageUnit");
            return (Criteria) this;
        }

        public Criteria andPackageUnitNotLike(String value) {
            addCriterion("packageUnit not like", value, "packageUnit");
            return (Criteria) this;
        }

        public Criteria andPackageUnitIn(List<String> values) {
            addCriterion("packageUnit in", values, "packageUnit");
            return (Criteria) this;
        }

        public Criteria andPackageUnitNotIn(List<String> values) {
            addCriterion("packageUnit not in", values, "packageUnit");
            return (Criteria) this;
        }

        public Criteria andPackageUnitBetween(String value1, String value2) {
            addCriterion("packageUnit between", value1, value2, "packageUnit");
            return (Criteria) this;
        }

        public Criteria andPackageUnitNotBetween(String value1, String value2) {
            addCriterion("packageUnit not between", value1, value2, "packageUnit");
            return (Criteria) this;
        }

        public Criteria andPackagewidthIsNull() {
            addCriterion("packagewidth is null");
            return (Criteria) this;
        }

        public Criteria andPackagewidthIsNotNull() {
            addCriterion("packagewidth is not null");
            return (Criteria) this;
        }

        public Criteria andPackagewidthEqualTo(String value) {
            addCriterion("packagewidth =", value, "packagewidth");
            return (Criteria) this;
        }

        public Criteria andPackagewidthNotEqualTo(String value) {
            addCriterion("packagewidth <>", value, "packagewidth");
            return (Criteria) this;
        }

        public Criteria andPackagewidthGreaterThan(String value) {
            addCriterion("packagewidth >", value, "packagewidth");
            return (Criteria) this;
        }

        public Criteria andPackagewidthGreaterThanOrEqualTo(String value) {
            addCriterion("packagewidth >=", value, "packagewidth");
            return (Criteria) this;
        }

        public Criteria andPackagewidthLessThan(String value) {
            addCriterion("packagewidth <", value, "packagewidth");
            return (Criteria) this;
        }

        public Criteria andPackagewidthLessThanOrEqualTo(String value) {
            addCriterion("packagewidth <=", value, "packagewidth");
            return (Criteria) this;
        }

        public Criteria andPackagewidthLike(String value) {
            addCriterion("packagewidth like", value, "packagewidth");
            return (Criteria) this;
        }

        public Criteria andPackagewidthNotLike(String value) {
            addCriterion("packagewidth not like", value, "packagewidth");
            return (Criteria) this;
        }

        public Criteria andPackagewidthIn(List<String> values) {
            addCriterion("packagewidth in", values, "packagewidth");
            return (Criteria) this;
        }

        public Criteria andPackagewidthNotIn(List<String> values) {
            addCriterion("packagewidth not in", values, "packagewidth");
            return (Criteria) this;
        }

        public Criteria andPackagewidthBetween(String value1, String value2) {
            addCriterion("packagewidth between", value1, value2, "packagewidth");
            return (Criteria) this;
        }

        public Criteria andPackagewidthNotBetween(String value1, String value2) {
            addCriterion("packagewidth not between", value1, value2, "packagewidth");
            return (Criteria) this;
        }

        public Criteria andPackageheightIsNull() {
            addCriterion("packageheight is null");
            return (Criteria) this;
        }

        public Criteria andPackageheightIsNotNull() {
            addCriterion("packageheight is not null");
            return (Criteria) this;
        }

        public Criteria andPackageheightEqualTo(String value) {
            addCriterion("packageheight =", value, "packageheight");
            return (Criteria) this;
        }

        public Criteria andPackageheightNotEqualTo(String value) {
            addCriterion("packageheight <>", value, "packageheight");
            return (Criteria) this;
        }

        public Criteria andPackageheightGreaterThan(String value) {
            addCriterion("packageheight >", value, "packageheight");
            return (Criteria) this;
        }

        public Criteria andPackageheightGreaterThanOrEqualTo(String value) {
            addCriterion("packageheight >=", value, "packageheight");
            return (Criteria) this;
        }

        public Criteria andPackageheightLessThan(String value) {
            addCriterion("packageheight <", value, "packageheight");
            return (Criteria) this;
        }

        public Criteria andPackageheightLessThanOrEqualTo(String value) {
            addCriterion("packageheight <=", value, "packageheight");
            return (Criteria) this;
        }

        public Criteria andPackageheightLike(String value) {
            addCriterion("packageheight like", value, "packageheight");
            return (Criteria) this;
        }

        public Criteria andPackageheightNotLike(String value) {
            addCriterion("packageheight not like", value, "packageheight");
            return (Criteria) this;
        }

        public Criteria andPackageheightIn(List<String> values) {
            addCriterion("packageheight in", values, "packageheight");
            return (Criteria) this;
        }

        public Criteria andPackageheightNotIn(List<String> values) {
            addCriterion("packageheight not in", values, "packageheight");
            return (Criteria) this;
        }

        public Criteria andPackageheightBetween(String value1, String value2) {
            addCriterion("packageheight between", value1, value2, "packageheight");
            return (Criteria) this;
        }

        public Criteria andPackageheightNotBetween(String value1, String value2) {
            addCriterion("packageheight not between", value1, value2, "packageheight");
            return (Criteria) this;
        }

        public Criteria andPackagelengthIsNull() {
            addCriterion("packagelength is null");
            return (Criteria) this;
        }

        public Criteria andPackagelengthIsNotNull() {
            addCriterion("packagelength is not null");
            return (Criteria) this;
        }

        public Criteria andPackagelengthEqualTo(String value) {
            addCriterion("packagelength =", value, "packagelength");
            return (Criteria) this;
        }

        public Criteria andPackagelengthNotEqualTo(String value) {
            addCriterion("packagelength <>", value, "packagelength");
            return (Criteria) this;
        }

        public Criteria andPackagelengthGreaterThan(String value) {
            addCriterion("packagelength >", value, "packagelength");
            return (Criteria) this;
        }

        public Criteria andPackagelengthGreaterThanOrEqualTo(String value) {
            addCriterion("packagelength >=", value, "packagelength");
            return (Criteria) this;
        }

        public Criteria andPackagelengthLessThan(String value) {
            addCriterion("packagelength <", value, "packagelength");
            return (Criteria) this;
        }

        public Criteria andPackagelengthLessThanOrEqualTo(String value) {
            addCriterion("packagelength <=", value, "packagelength");
            return (Criteria) this;
        }

        public Criteria andPackagelengthLike(String value) {
            addCriterion("packagelength like", value, "packagelength");
            return (Criteria) this;
        }

        public Criteria andPackagelengthNotLike(String value) {
            addCriterion("packagelength not like", value, "packagelength");
            return (Criteria) this;
        }

        public Criteria andPackagelengthIn(List<String> values) {
            addCriterion("packagelength in", values, "packagelength");
            return (Criteria) this;
        }

        public Criteria andPackagelengthNotIn(List<String> values) {
            addCriterion("packagelength not in", values, "packagelength");
            return (Criteria) this;
        }

        public Criteria andPackagelengthBetween(String value1, String value2) {
            addCriterion("packagelength between", value1, value2, "packagelength");
            return (Criteria) this;
        }

        public Criteria andPackagelengthNotBetween(String value1, String value2) {
            addCriterion("packagelength not between", value1, value2, "packagelength");
            return (Criteria) this;
        }

        public Criteria andPackagevolumeIsNull() {
            addCriterion("packagevolume is null");
            return (Criteria) this;
        }

        public Criteria andPackagevolumeIsNotNull() {
            addCriterion("packagevolume is not null");
            return (Criteria) this;
        }

        public Criteria andPackagevolumeEqualTo(String value) {
            addCriterion("packagevolume =", value, "packagevolume");
            return (Criteria) this;
        }

        public Criteria andPackagevolumeNotEqualTo(String value) {
            addCriterion("packagevolume <>", value, "packagevolume");
            return (Criteria) this;
        }

        public Criteria andPackagevolumeGreaterThan(String value) {
            addCriterion("packagevolume >", value, "packagevolume");
            return (Criteria) this;
        }

        public Criteria andPackagevolumeGreaterThanOrEqualTo(String value) {
            addCriterion("packagevolume >=", value, "packagevolume");
            return (Criteria) this;
        }

        public Criteria andPackagevolumeLessThan(String value) {
            addCriterion("packagevolume <", value, "packagevolume");
            return (Criteria) this;
        }

        public Criteria andPackagevolumeLessThanOrEqualTo(String value) {
            addCriterion("packagevolume <=", value, "packagevolume");
            return (Criteria) this;
        }

        public Criteria andPackagevolumeLike(String value) {
            addCriterion("packagevolume like", value, "packagevolume");
            return (Criteria) this;
        }

        public Criteria andPackagevolumeNotLike(String value) {
            addCriterion("packagevolume not like", value, "packagevolume");
            return (Criteria) this;
        }

        public Criteria andPackagevolumeIn(List<String> values) {
            addCriterion("packagevolume in", values, "packagevolume");
            return (Criteria) this;
        }

        public Criteria andPackagevolumeNotIn(List<String> values) {
            addCriterion("packagevolume not in", values, "packagevolume");
            return (Criteria) this;
        }

        public Criteria andPackagevolumeBetween(String value1, String value2) {
            addCriterion("packagevolume between", value1, value2, "packagevolume");
            return (Criteria) this;
        }

        public Criteria andPackagevolumeNotBetween(String value1, String value2) {
            addCriterion("packagevolume not between", value1, value2, "packagevolume");
            return (Criteria) this;
        }

        public Criteria andPackageUnitConversionIsNull() {
            addCriterion("packageUnitConversion is null");
            return (Criteria) this;
        }

        public Criteria andPackageUnitConversionIsNotNull() {
            addCriterion("packageUnitConversion is not null");
            return (Criteria) this;
        }

        public Criteria andPackageUnitConversionEqualTo(String value) {
            addCriterion("packageUnitConversion =", value, "packageUnitConversion");
            return (Criteria) this;
        }

        public Criteria andPackageUnitConversionNotEqualTo(String value) {
            addCriterion("packageUnitConversion <>", value, "packageUnitConversion");
            return (Criteria) this;
        }

        public Criteria andPackageUnitConversionGreaterThan(String value) {
            addCriterion("packageUnitConversion >", value, "packageUnitConversion");
            return (Criteria) this;
        }

        public Criteria andPackageUnitConversionGreaterThanOrEqualTo(String value) {
            addCriterion("packageUnitConversion >=", value, "packageUnitConversion");
            return (Criteria) this;
        }

        public Criteria andPackageUnitConversionLessThan(String value) {
            addCriterion("packageUnitConversion <", value, "packageUnitConversion");
            return (Criteria) this;
        }

        public Criteria andPackageUnitConversionLessThanOrEqualTo(String value) {
            addCriterion("packageUnitConversion <=", value, "packageUnitConversion");
            return (Criteria) this;
        }

        public Criteria andPackageUnitConversionLike(String value) {
            addCriterion("packageUnitConversion like", value, "packageUnitConversion");
            return (Criteria) this;
        }

        public Criteria andPackageUnitConversionNotLike(String value) {
            addCriterion("packageUnitConversion not like", value, "packageUnitConversion");
            return (Criteria) this;
        }

        public Criteria andPackageUnitConversionIn(List<String> values) {
            addCriterion("packageUnitConversion in", values, "packageUnitConversion");
            return (Criteria) this;
        }

        public Criteria andPackageUnitConversionNotIn(List<String> values) {
            addCriterion("packageUnitConversion not in", values, "packageUnitConversion");
            return (Criteria) this;
        }

        public Criteria andPackageUnitConversionBetween(String value1, String value2) {
            addCriterion("packageUnitConversion between", value1, value2, "packageUnitConversion");
            return (Criteria) this;
        }

        public Criteria andPackageUnitConversionNotBetween(String value1, String value2) {
            addCriterion("packageUnitConversion not between", value1, value2, "packageUnitConversion");
            return (Criteria) this;
        }

        public Criteria andPalletWeightIsNull() {
            addCriterion("palletWeight is null");
            return (Criteria) this;
        }

        public Criteria andPalletWeightIsNotNull() {
            addCriterion("palletWeight is not null");
            return (Criteria) this;
        }

        public Criteria andPalletWeightEqualTo(String value) {
            addCriterion("palletWeight =", value, "palletWeight");
            return (Criteria) this;
        }

        public Criteria andPalletWeightNotEqualTo(String value) {
            addCriterion("palletWeight <>", value, "palletWeight");
            return (Criteria) this;
        }

        public Criteria andPalletWeightGreaterThan(String value) {
            addCriterion("palletWeight >", value, "palletWeight");
            return (Criteria) this;
        }

        public Criteria andPalletWeightGreaterThanOrEqualTo(String value) {
            addCriterion("palletWeight >=", value, "palletWeight");
            return (Criteria) this;
        }

        public Criteria andPalletWeightLessThan(String value) {
            addCriterion("palletWeight <", value, "palletWeight");
            return (Criteria) this;
        }

        public Criteria andPalletWeightLessThanOrEqualTo(String value) {
            addCriterion("palletWeight <=", value, "palletWeight");
            return (Criteria) this;
        }

        public Criteria andPalletWeightLike(String value) {
            addCriterion("palletWeight like", value, "palletWeight");
            return (Criteria) this;
        }

        public Criteria andPalletWeightNotLike(String value) {
            addCriterion("palletWeight not like", value, "palletWeight");
            return (Criteria) this;
        }

        public Criteria andPalletWeightIn(List<String> values) {
            addCriterion("palletWeight in", values, "palletWeight");
            return (Criteria) this;
        }

        public Criteria andPalletWeightNotIn(List<String> values) {
            addCriterion("palletWeight not in", values, "palletWeight");
            return (Criteria) this;
        }

        public Criteria andPalletWeightBetween(String value1, String value2) {
            addCriterion("palletWeight between", value1, value2, "palletWeight");
            return (Criteria) this;
        }

        public Criteria andPalletWeightNotBetween(String value1, String value2) {
            addCriterion("palletWeight not between", value1, value2, "palletWeight");
            return (Criteria) this;
        }

        public Criteria andVersionNOIsNull() {
            addCriterion("versionNO is null");
            return (Criteria) this;
        }

        public Criteria andVersionNOIsNotNull() {
            addCriterion("versionNO is not null");
            return (Criteria) this;
        }

        public Criteria andVersionNOEqualTo(String value) {
            addCriterion("versionNO =", value, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNONotEqualTo(String value) {
            addCriterion("versionNO <>", value, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNOGreaterThan(String value) {
            addCriterion("versionNO >", value, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNOGreaterThanOrEqualTo(String value) {
            addCriterion("versionNO >=", value, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNOLessThan(String value) {
            addCriterion("versionNO <", value, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNOLessThanOrEqualTo(String value) {
            addCriterion("versionNO <=", value, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNOLike(String value) {
            addCriterion("versionNO like", value, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNONotLike(String value) {
            addCriterion("versionNO not like", value, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNOIn(List<String> values) {
            addCriterion("versionNO in", values, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNONotIn(List<String> values) {
            addCriterion("versionNO not in", values, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNOBetween(String value1, String value2) {
            addCriterion("versionNO between", value1, value2, "versionNO");
            return (Criteria) this;
        }

        public Criteria andVersionNONotBetween(String value1, String value2) {
            addCriterion("versionNO not between", value1, value2, "versionNO");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionIsNull() {
            addCriterion("isLatestVersion is null");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionIsNotNull() {
            addCriterion("isLatestVersion is not null");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionEqualTo(String value) {
            addCriterion("isLatestVersion =", value, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionNotEqualTo(String value) {
            addCriterion("isLatestVersion <>", value, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionGreaterThan(String value) {
            addCriterion("isLatestVersion >", value, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionGreaterThanOrEqualTo(String value) {
            addCriterion("isLatestVersion >=", value, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionLessThan(String value) {
            addCriterion("isLatestVersion <", value, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionLessThanOrEqualTo(String value) {
            addCriterion("isLatestVersion <=", value, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionLike(String value) {
            addCriterion("isLatestVersion like", value, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionNotLike(String value) {
            addCriterion("isLatestVersion not like", value, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionIn(List<String> values) {
            addCriterion("isLatestVersion in", values, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionNotIn(List<String> values) {
            addCriterion("isLatestVersion not in", values, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionBetween(String value1, String value2) {
            addCriterion("isLatestVersion between", value1, value2, "isLatestVersion");
            return (Criteria) this;
        }

        public Criteria andIsLatestVersionNotBetween(String value1, String value2) {
            addCriterion("isLatestVersion not between", value1, value2, "isLatestVersion");
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