package com.congmai.zhgj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SupplyMaterielExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SupplyMaterielExample() {
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

        public Criteria andSupplyMaterielNumIsNull() {
            addCriterion("supplyMaterielNum is null");
            return (Criteria) this;
        }

        public Criteria andSupplyMaterielNumIsNotNull() {
            addCriterion("supplyMaterielNum is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyMaterielNumEqualTo(String value) {
            addCriterion("supplyMaterielNum =", value, "supplyMaterielNum");
            return (Criteria) this;
        }

        public Criteria andSupplyMaterielNumNotEqualTo(String value) {
            addCriterion("supplyMaterielNum <>", value, "supplyMaterielNum");
            return (Criteria) this;
        }

        public Criteria andSupplyMaterielNumGreaterThan(String value) {
            addCriterion("supplyMaterielNum >", value, "supplyMaterielNum");
            return (Criteria) this;
        }

        public Criteria andSupplyMaterielNumGreaterThanOrEqualTo(String value) {
            addCriterion("supplyMaterielNum >=", value, "supplyMaterielNum");
            return (Criteria) this;
        }

        public Criteria andSupplyMaterielNumLessThan(String value) {
            addCriterion("supplyMaterielNum <", value, "supplyMaterielNum");
            return (Criteria) this;
        }

        public Criteria andSupplyMaterielNumLessThanOrEqualTo(String value) {
            addCriterion("supplyMaterielNum <=", value, "supplyMaterielNum");
            return (Criteria) this;
        }

        public Criteria andSupplyMaterielNumLike(String value) {
            addCriterion("supplyMaterielNum like", value, "supplyMaterielNum");
            return (Criteria) this;
        }

        public Criteria andSupplyMaterielNumNotLike(String value) {
            addCriterion("supplyMaterielNum not like", value, "supplyMaterielNum");
            return (Criteria) this;
        }

        public Criteria andSupplyMaterielNumIn(List<String> values) {
            addCriterion("supplyMaterielNum in", values, "supplyMaterielNum");
            return (Criteria) this;
        }

        public Criteria andSupplyMaterielNumNotIn(List<String> values) {
            addCriterion("supplyMaterielNum not in", values, "supplyMaterielNum");
            return (Criteria) this;
        }

        public Criteria andSupplyMaterielNumBetween(String value1, String value2) {
            addCriterion("supplyMaterielNum between", value1, value2, "supplyMaterielNum");
            return (Criteria) this;
        }

        public Criteria andSupplyMaterielNumNotBetween(String value1, String value2) {
            addCriterion("supplyMaterielNum not between", value1, value2, "supplyMaterielNum");
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