package com.congmai.zhgj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BuyMaterielExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BuyMaterielExample() {
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

        public Criteria andBuyComIdIsNull() {
            addCriterion("buyComId is null");
            return (Criteria) this;
        }

        public Criteria andBuyComIdIsNotNull() {
            addCriterion("buyComId is not null");
            return (Criteria) this;
        }

        public Criteria andBuyComIdEqualTo(String value) {
            addCriterion("buyComId =", value, "buyComId");
            return (Criteria) this;
        }

        public Criteria andBuyComIdNotEqualTo(String value) {
            addCriterion("buyComId <>", value, "buyComId");
            return (Criteria) this;
        }

        public Criteria andBuyComIdGreaterThan(String value) {
            addCriterion("buyComId >", value, "buyComId");
            return (Criteria) this;
        }

        public Criteria andBuyComIdGreaterThanOrEqualTo(String value) {
            addCriterion("buyComId >=", value, "buyComId");
            return (Criteria) this;
        }

        public Criteria andBuyComIdLessThan(String value) {
            addCriterion("buyComId <", value, "buyComId");
            return (Criteria) this;
        }

        public Criteria andBuyComIdLessThanOrEqualTo(String value) {
            addCriterion("buyComId <=", value, "buyComId");
            return (Criteria) this;
        }

        public Criteria andBuyComIdLike(String value) {
            addCriterion("buyComId like", value, "buyComId");
            return (Criteria) this;
        }

        public Criteria andBuyComIdNotLike(String value) {
            addCriterion("buyComId not like", value, "buyComId");
            return (Criteria) this;
        }

        public Criteria andBuyComIdIn(List<String> values) {
            addCriterion("buyComId in", values, "buyComId");
            return (Criteria) this;
        }

        public Criteria andBuyComIdNotIn(List<String> values) {
            addCriterion("buyComId not in", values, "buyComId");
            return (Criteria) this;
        }

        public Criteria andBuyComIdBetween(String value1, String value2) {
            addCriterion("buyComId between", value1, value2, "buyComId");
            return (Criteria) this;
        }

        public Criteria andBuyComIdNotBetween(String value1, String value2) {
            addCriterion("buyComId not between", value1, value2, "buyComId");
            return (Criteria) this;
        }

        public Criteria andBuyMaterielNumIsNull() {
            addCriterion("buyMaterielNum is null");
            return (Criteria) this;
        }

        public Criteria andBuyMaterielNumIsNotNull() {
            addCriterion("buyMaterielNum is not null");
            return (Criteria) this;
        }

        public Criteria andBuyMaterielNumEqualTo(String value) {
            addCriterion("buyMaterielNum =", value, "buyMaterielNum");
            return (Criteria) this;
        }

        public Criteria andBuyMaterielNumNotEqualTo(String value) {
            addCriterion("buyMaterielNum <>", value, "buyMaterielNum");
            return (Criteria) this;
        }

        public Criteria andBuyMaterielNumGreaterThan(String value) {
            addCriterion("buyMaterielNum >", value, "buyMaterielNum");
            return (Criteria) this;
        }

        public Criteria andBuyMaterielNumGreaterThanOrEqualTo(String value) {
            addCriterion("buyMaterielNum >=", value, "buyMaterielNum");
            return (Criteria) this;
        }

        public Criteria andBuyMaterielNumLessThan(String value) {
            addCriterion("buyMaterielNum <", value, "buyMaterielNum");
            return (Criteria) this;
        }

        public Criteria andBuyMaterielNumLessThanOrEqualTo(String value) {
            addCriterion("buyMaterielNum <=", value, "buyMaterielNum");
            return (Criteria) this;
        }

        public Criteria andBuyMaterielNumLike(String value) {
            addCriterion("buyMaterielNum like", value, "buyMaterielNum");
            return (Criteria) this;
        }

        public Criteria andBuyMaterielNumNotLike(String value) {
            addCriterion("buyMaterielNum not like", value, "buyMaterielNum");
            return (Criteria) this;
        }

        public Criteria andBuyMaterielNumIn(List<String> values) {
            addCriterion("buyMaterielNum in", values, "buyMaterielNum");
            return (Criteria) this;
        }

        public Criteria andBuyMaterielNumNotIn(List<String> values) {
            addCriterion("buyMaterielNum not in", values, "buyMaterielNum");
            return (Criteria) this;
        }

        public Criteria andBuyMaterielNumBetween(String value1, String value2) {
            addCriterion("buyMaterielNum between", value1, value2, "buyMaterielNum");
            return (Criteria) this;
        }

        public Criteria andBuyMaterielNumNotBetween(String value1, String value2) {
            addCriterion("buyMaterielNum not between", value1, value2, "buyMaterielNum");
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

        public Criteria andUnitPriceGuideIsNull() {
            addCriterion("unitPriceGuide is null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGuideIsNotNull() {
            addCriterion("unitPriceGuide is not null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGuideEqualTo(String value) {
            addCriterion("unitPriceGuide =", value, "unitPriceGuide");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGuideNotEqualTo(String value) {
            addCriterion("unitPriceGuide <>", value, "unitPriceGuide");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGuideGreaterThan(String value) {
            addCriterion("unitPriceGuide >", value, "unitPriceGuide");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGuideGreaterThanOrEqualTo(String value) {
            addCriterion("unitPriceGuide >=", value, "unitPriceGuide");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGuideLessThan(String value) {
            addCriterion("unitPriceGuide <", value, "unitPriceGuide");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGuideLessThanOrEqualTo(String value) {
            addCriterion("unitPriceGuide <=", value, "unitPriceGuide");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGuideLike(String value) {
            addCriterion("unitPriceGuide like", value, "unitPriceGuide");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGuideNotLike(String value) {
            addCriterion("unitPriceGuide not like", value, "unitPriceGuide");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGuideIn(List<String> values) {
            addCriterion("unitPriceGuide in", values, "unitPriceGuide");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGuideNotIn(List<String> values) {
            addCriterion("unitPriceGuide not in", values, "unitPriceGuide");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGuideBetween(String value1, String value2) {
            addCriterion("unitPriceGuide between", value1, value2, "unitPriceGuide");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGuideNotBetween(String value1, String value2) {
            addCriterion("unitPriceGuide not between", value1, value2, "unitPriceGuide");
            return (Criteria) this;
        }

        public Criteria andPurchaseQuotaIsNull() {
            addCriterion("purchaseQuota is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseQuotaIsNotNull() {
            addCriterion("purchaseQuota is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseQuotaEqualTo(String value) {
            addCriterion("purchaseQuota =", value, "purchaseQuota");
            return (Criteria) this;
        }

        public Criteria andPurchaseQuotaNotEqualTo(String value) {
            addCriterion("purchaseQuota <>", value, "purchaseQuota");
            return (Criteria) this;
        }

        public Criteria andPurchaseQuotaGreaterThan(String value) {
            addCriterion("purchaseQuota >", value, "purchaseQuota");
            return (Criteria) this;
        }

        public Criteria andPurchaseQuotaGreaterThanOrEqualTo(String value) {
            addCriterion("purchaseQuota >=", value, "purchaseQuota");
            return (Criteria) this;
        }

        public Criteria andPurchaseQuotaLessThan(String value) {
            addCriterion("purchaseQuota <", value, "purchaseQuota");
            return (Criteria) this;
        }

        public Criteria andPurchaseQuotaLessThanOrEqualTo(String value) {
            addCriterion("purchaseQuota <=", value, "purchaseQuota");
            return (Criteria) this;
        }

        public Criteria andPurchaseQuotaLike(String value) {
            addCriterion("purchaseQuota like", value, "purchaseQuota");
            return (Criteria) this;
        }

        public Criteria andPurchaseQuotaNotLike(String value) {
            addCriterion("purchaseQuota not like", value, "purchaseQuota");
            return (Criteria) this;
        }

        public Criteria andPurchaseQuotaIn(List<String> values) {
            addCriterion("purchaseQuota in", values, "purchaseQuota");
            return (Criteria) this;
        }

        public Criteria andPurchaseQuotaNotIn(List<String> values) {
            addCriterion("purchaseQuota not in", values, "purchaseQuota");
            return (Criteria) this;
        }

        public Criteria andPurchaseQuotaBetween(String value1, String value2) {
            addCriterion("purchaseQuota between", value1, value2, "purchaseQuota");
            return (Criteria) this;
        }

        public Criteria andPurchaseQuotaNotBetween(String value1, String value2) {
            addCriterion("purchaseQuota not between", value1, value2, "purchaseQuota");
            return (Criteria) this;
        }

        public Criteria andmoqIsNull() {
            addCriterion("moq is null");
            return (Criteria) this;
        }

        public Criteria andmoqIsNotNull() {
            addCriterion("moq is not null");
            return (Criteria) this;
        }

        public Criteria andmoqEqualTo(String value) {
            addCriterion("moq =", value, "moq");
            return (Criteria) this;
        }

        public Criteria andmoqNotEqualTo(String value) {
            addCriterion("moq <>", value, "moq");
            return (Criteria) this;
        }

        public Criteria andmoqGreaterThan(String value) {
            addCriterion("moq >", value, "moq");
            return (Criteria) this;
        }

        public Criteria andmoqGreaterThanOrEqualTo(String value) {
            addCriterion("moq >=", value, "moq");
            return (Criteria) this;
        }

        public Criteria andmoqLessThan(String value) {
            addCriterion("moq <", value, "moq");
            return (Criteria) this;
        }

        public Criteria andmoqLessThanOrEqualTo(String value) {
            addCriterion("moq <=", value, "moq");
            return (Criteria) this;
        }

        public Criteria andmoqLike(String value) {
            addCriterion("moq like", value, "moq");
            return (Criteria) this;
        }

        public Criteria andmoqNotLike(String value) {
            addCriterion("moq not like", value, "moq");
            return (Criteria) this;
        }

        public Criteria andmoqIn(List<String> values) {
            addCriterion("moq in", values, "moq");
            return (Criteria) this;
        }

        public Criteria andmoqNotIn(List<String> values) {
            addCriterion("moq not in", values, "moq");
            return (Criteria) this;
        }

        public Criteria andmoqBetween(String value1, String value2) {
            addCriterion("moq between", value1, value2, "moq");
            return (Criteria) this;
        }

        public Criteria andmoqNotBetween(String value1, String value2) {
            addCriterion("moq not between", value1, value2, "moq");
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