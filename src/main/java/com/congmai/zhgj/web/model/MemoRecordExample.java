package com.congmai.zhgj.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MemoRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MemoRecordExample() {
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

        public Criteria andMemoNumIsNull() {
            addCriterion("memoNum is null");
            return (Criteria) this;
        }

        public Criteria andMemoNumIsNotNull() {
            addCriterion("memoNum is not null");
            return (Criteria) this;
        }

        public Criteria andMemoNumEqualTo(String value) {
            addCriterion("memoNum =", value, "memoNum");
            return (Criteria) this;
        }

        public Criteria andMemoNumNotEqualTo(String value) {
            addCriterion("memoNum <>", value, "memoNum");
            return (Criteria) this;
        }

        public Criteria andMemoNumGreaterThan(String value) {
            addCriterion("memoNum >", value, "memoNum");
            return (Criteria) this;
        }

        public Criteria andMemoNumGreaterThanOrEqualTo(String value) {
            addCriterion("memoNum >=", value, "memoNum");
            return (Criteria) this;
        }

        public Criteria andMemoNumLessThan(String value) {
            addCriterion("memoNum <", value, "memoNum");
            return (Criteria) this;
        }

        public Criteria andMemoNumLessThanOrEqualTo(String value) {
            addCriterion("memoNum <=", value, "memoNum");
            return (Criteria) this;
        }

        public Criteria andMemoNumLike(String value) {
            addCriterion("memoNum like", value, "memoNum");
            return (Criteria) this;
        }

        public Criteria andMemoNumNotLike(String value) {
            addCriterion("memoNum not like", value, "memoNum");
            return (Criteria) this;
        }

        public Criteria andMemoNumIn(List<String> values) {
            addCriterion("memoNum in", values, "memoNum");
            return (Criteria) this;
        }

        public Criteria andMemoNumNotIn(List<String> values) {
            addCriterion("memoNum not in", values, "memoNum");
            return (Criteria) this;
        }

        public Criteria andMemoNumBetween(String value1, String value2) {
            addCriterion("memoNum between", value1, value2, "memoNum");
            return (Criteria) this;
        }

        public Criteria andMemoNumNotBetween(String value1, String value2) {
            addCriterion("memoNum not between", value1, value2, "memoNum");
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

        public Criteria andVerificationMoneyAmountIsNull() {
            addCriterion("verificationMoneyAmount is null");
            return (Criteria) this;
        }

        public Criteria andVerificationMoneyAmountIsNotNull() {
            addCriterion("verificationMoneyAmount is not null");
            return (Criteria) this;
        }

        public Criteria andVerificationMoneyAmountEqualTo(String value) {
            addCriterion("verificationMoneyAmount =", value, "verificationMoneyAmount");
            return (Criteria) this;
        }

        public Criteria andVerificationMoneyAmountNotEqualTo(String value) {
            addCriterion("verificationMoneyAmount <>", value, "verificationMoneyAmount");
            return (Criteria) this;
        }

        public Criteria andVerificationMoneyAmountGreaterThan(String value) {
            addCriterion("verificationMoneyAmount >", value, "verificationMoneyAmount");
            return (Criteria) this;
        }

        public Criteria andVerificationMoneyAmountGreaterThanOrEqualTo(String value) {
            addCriterion("verificationMoneyAmount >=", value, "verificationMoneyAmount");
            return (Criteria) this;
        }

        public Criteria andVerificationMoneyAmountLessThan(String value) {
            addCriterion("verificationMoneyAmount <", value, "verificationMoneyAmount");
            return (Criteria) this;
        }

        public Criteria andVerificationMoneyAmountLessThanOrEqualTo(String value) {
            addCriterion("verificationMoneyAmount <=", value, "verificationMoneyAmount");
            return (Criteria) this;
        }

        public Criteria andVerificationMoneyAmountLike(String value) {
            addCriterion("verificationMoneyAmount like", value, "verificationMoneyAmount");
            return (Criteria) this;
        }

        public Criteria andVerificationMoneyAmountNotLike(String value) {
            addCriterion("verificationMoneyAmount not like", value, "verificationMoneyAmount");
            return (Criteria) this;
        }

        public Criteria andVerificationMoneyAmountIn(List<String> values) {
            addCriterion("verificationMoneyAmount in", values, "verificationMoneyAmount");
            return (Criteria) this;
        }

        public Criteria andVerificationMoneyAmountNotIn(List<String> values) {
            addCriterion("verificationMoneyAmount not in", values, "verificationMoneyAmount");
            return (Criteria) this;
        }

        public Criteria andVerificationMoneyAmountBetween(String value1, String value2) {
            addCriterion("verificationMoneyAmount between", value1, value2, "verificationMoneyAmount");
            return (Criteria) this;
        }

        public Criteria andVerificationMoneyAmountNotBetween(String value1, String value2) {
            addCriterion("verificationMoneyAmount not between", value1, value2, "verificationMoneyAmount");
            return (Criteria) this;
        }

        public Criteria andMoneyAmountIsNull() {
            addCriterion("moneyAmount is null");
            return (Criteria) this;
        }

        public Criteria andMoneyAmountIsNotNull() {
            addCriterion("moneyAmount is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyAmountEqualTo(String value) {
            addCriterion("moneyAmount =", value, "moneyAmount");
            return (Criteria) this;
        }

        public Criteria andMoneyAmountNotEqualTo(String value) {
            addCriterion("moneyAmount <>", value, "moneyAmount");
            return (Criteria) this;
        }

        public Criteria andMoneyAmountGreaterThan(String value) {
            addCriterion("moneyAmount >", value, "moneyAmount");
            return (Criteria) this;
        }

        public Criteria andMoneyAmountGreaterThanOrEqualTo(String value) {
            addCriterion("moneyAmount >=", value, "moneyAmount");
            return (Criteria) this;
        }

        public Criteria andMoneyAmountLessThan(String value) {
            addCriterion("moneyAmount <", value, "moneyAmount");
            return (Criteria) this;
        }

        public Criteria andMoneyAmountLessThanOrEqualTo(String value) {
            addCriterion("moneyAmount <=", value, "moneyAmount");
            return (Criteria) this;
        }

        public Criteria andMoneyAmountLike(String value) {
            addCriterion("moneyAmount like", value, "moneyAmount");
            return (Criteria) this;
        }

        public Criteria andMoneyAmountNotLike(String value) {
            addCriterion("moneyAmount not like", value, "moneyAmount");
            return (Criteria) this;
        }

        public Criteria andMoneyAmountIn(List<String> values) {
            addCriterion("moneyAmount in", values, "moneyAmount");
            return (Criteria) this;
        }

        public Criteria andMoneyAmountNotIn(List<String> values) {
            addCriterion("moneyAmount not in", values, "moneyAmount");
            return (Criteria) this;
        }

        public Criteria andMoneyAmountBetween(String value1, String value2) {
            addCriterion("moneyAmount between", value1, value2, "moneyAmount");
            return (Criteria) this;
        }

        public Criteria andMoneyAmountNotBetween(String value1, String value2) {
            addCriterion("moneyAmount not between", value1, value2, "moneyAmount");
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

        public Criteria andPayeeIsNull() {
            addCriterion("payee is null");
            return (Criteria) this;
        }

        public Criteria andPayeeIsNotNull() {
            addCriterion("payee is not null");
            return (Criteria) this;
        }

        public Criteria andPayeeEqualTo(String value) {
            addCriterion("payee =", value, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeNotEqualTo(String value) {
            addCriterion("payee <>", value, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeGreaterThan(String value) {
            addCriterion("payee >", value, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeGreaterThanOrEqualTo(String value) {
            addCriterion("payee >=", value, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeLessThan(String value) {
            addCriterion("payee <", value, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeLessThanOrEqualTo(String value) {
            addCriterion("payee <=", value, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeLike(String value) {
            addCriterion("payee like", value, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeNotLike(String value) {
            addCriterion("payee not like", value, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeIn(List<String> values) {
            addCriterion("payee in", values, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeNotIn(List<String> values) {
            addCriterion("payee not in", values, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeBetween(String value1, String value2) {
            addCriterion("payee between", value1, value2, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeNotBetween(String value1, String value2) {
            addCriterion("payee not between", value1, value2, "payee");
            return (Criteria) this;
        }

        public Criteria andBankIsNull() {
            addCriterion("bank is null");
            return (Criteria) this;
        }

        public Criteria andBankIsNotNull() {
            addCriterion("bank is not null");
            return (Criteria) this;
        }

        public Criteria andBankEqualTo(String value) {
            addCriterion("bank =", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotEqualTo(String value) {
            addCriterion("bank <>", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankGreaterThan(String value) {
            addCriterion("bank >", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankGreaterThanOrEqualTo(String value) {
            addCriterion("bank >=", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLessThan(String value) {
            addCriterion("bank <", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLessThanOrEqualTo(String value) {
            addCriterion("bank <=", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLike(String value) {
            addCriterion("bank like", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotLike(String value) {
            addCriterion("bank not like", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankIn(List<String> values) {
            addCriterion("bank in", values, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotIn(List<String> values) {
            addCriterion("bank not in", values, "bank");
            return (Criteria) this;
        }

        public Criteria andBankBetween(String value1, String value2) {
            addCriterion("bank between", value1, value2, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotBetween(String value1, String value2) {
            addCriterion("bank not between", value1, value2, "bank");
            return (Criteria) this;
        }

        public Criteria andAccountNameIsNull() {
            addCriterion("accountName is null");
            return (Criteria) this;
        }

        public Criteria andAccountNameIsNotNull() {
            addCriterion("accountName is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNameEqualTo(String value) {
            addCriterion("accountName =", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotEqualTo(String value) {
            addCriterion("accountName <>", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThan(String value) {
            addCriterion("accountName >", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("accountName >=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThan(String value) {
            addCriterion("accountName <", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThanOrEqualTo(String value) {
            addCriterion("accountName <=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLike(String value) {
            addCriterion("accountName like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotLike(String value) {
            addCriterion("accountName not like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameIn(List<String> values) {
            addCriterion("accountName in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotIn(List<String> values) {
            addCriterion("accountName not in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameBetween(String value1, String value2) {
            addCriterion("accountName between", value1, value2, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotBetween(String value1, String value2) {
            addCriterion("accountName not between", value1, value2, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNumberIsNull() {
            addCriterion("accountNumber is null");
            return (Criteria) this;
        }

        public Criteria andAccountNumberIsNotNull() {
            addCriterion("accountNumber is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNumberEqualTo(String value) {
            addCriterion("accountNumber =", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberNotEqualTo(String value) {
            addCriterion("accountNumber <>", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberGreaterThan(String value) {
            addCriterion("accountNumber >", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberGreaterThanOrEqualTo(String value) {
            addCriterion("accountNumber >=", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberLessThan(String value) {
            addCriterion("accountNumber <", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberLessThanOrEqualTo(String value) {
            addCriterion("accountNumber <=", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberLike(String value) {
            addCriterion("accountNumber like", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberNotLike(String value) {
            addCriterion("accountNumber not like", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberIn(List<String> values) {
            addCriterion("accountNumber in", values, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberNotIn(List<String> values) {
            addCriterion("accountNumber not in", values, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberBetween(String value1, String value2) {
            addCriterion("accountNumber between", value1, value2, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberNotBetween(String value1, String value2) {
            addCriterion("accountNumber not between", value1, value2, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andPaymentDateIsNull() {
            addCriterion("paymentDate is null");
            return (Criteria) this;
        }

        public Criteria andPaymentDateIsNotNull() {
            addCriterion("paymentDate is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentDateEqualTo(Date value) {
            addCriterionForJDBCDate("paymentDate =", value, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("paymentDate <>", value, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateGreaterThan(Date value) {
            addCriterionForJDBCDate("paymentDate >", value, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("paymentDate >=", value, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateLessThan(Date value) {
            addCriterionForJDBCDate("paymentDate <", value, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("paymentDate <=", value, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateIn(List<Date> values) {
            addCriterionForJDBCDate("paymentDate in", values, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("paymentDate not in", values, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("paymentDate between", value1, value2, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("paymentDate not between", value1, value2, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentStyleIsNull() {
            addCriterion("paymentStyle is null");
            return (Criteria) this;
        }

        public Criteria andPaymentStyleIsNotNull() {
            addCriterion("paymentStyle is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentStyleEqualTo(String value) {
            addCriterion("paymentStyle =", value, "paymentStyle");
            return (Criteria) this;
        }

        public Criteria andPaymentStyleNotEqualTo(String value) {
            addCriterion("paymentStyle <>", value, "paymentStyle");
            return (Criteria) this;
        }

        public Criteria andPaymentStyleGreaterThan(String value) {
            addCriterion("paymentStyle >", value, "paymentStyle");
            return (Criteria) this;
        }

        public Criteria andPaymentStyleGreaterThanOrEqualTo(String value) {
            addCriterion("paymentStyle >=", value, "paymentStyle");
            return (Criteria) this;
        }

        public Criteria andPaymentStyleLessThan(String value) {
            addCriterion("paymentStyle <", value, "paymentStyle");
            return (Criteria) this;
        }

        public Criteria andPaymentStyleLessThanOrEqualTo(String value) {
            addCriterion("paymentStyle <=", value, "paymentStyle");
            return (Criteria) this;
        }

        public Criteria andPaymentStyleLike(String value) {
            addCriterion("paymentStyle like", value, "paymentStyle");
            return (Criteria) this;
        }

        public Criteria andPaymentStyleNotLike(String value) {
            addCriterion("paymentStyle not like", value, "paymentStyle");
            return (Criteria) this;
        }

        public Criteria andPaymentStyleIn(List<String> values) {
            addCriterion("paymentStyle in", values, "paymentStyle");
            return (Criteria) this;
        }

        public Criteria andPaymentStyleNotIn(List<String> values) {
            addCriterion("paymentStyle not in", values, "paymentStyle");
            return (Criteria) this;
        }

        public Criteria andPaymentStyleBetween(String value1, String value2) {
            addCriterion("paymentStyle between", value1, value2, "paymentStyle");
            return (Criteria) this;
        }

        public Criteria andPaymentStyleNotBetween(String value1, String value2) {
            addCriterion("paymentStyle not between", value1, value2, "paymentStyle");
            return (Criteria) this;
        }

        public Criteria andPaymentVoucherIsNull() {
            addCriterion("paymentVoucher is null");
            return (Criteria) this;
        }

        public Criteria andPaymentVoucherIsNotNull() {
            addCriterion("paymentVoucher is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentVoucherEqualTo(String value) {
            addCriterion("paymentVoucher =", value, "paymentVoucher");
            return (Criteria) this;
        }

        public Criteria andPaymentVoucherNotEqualTo(String value) {
            addCriterion("paymentVoucher <>", value, "paymentVoucher");
            return (Criteria) this;
        }

        public Criteria andPaymentVoucherGreaterThan(String value) {
            addCriterion("paymentVoucher >", value, "paymentVoucher");
            return (Criteria) this;
        }

        public Criteria andPaymentVoucherGreaterThanOrEqualTo(String value) {
            addCriterion("paymentVoucher >=", value, "paymentVoucher");
            return (Criteria) this;
        }

        public Criteria andPaymentVoucherLessThan(String value) {
            addCriterion("paymentVoucher <", value, "paymentVoucher");
            return (Criteria) this;
        }

        public Criteria andPaymentVoucherLessThanOrEqualTo(String value) {
            addCriterion("paymentVoucher <=", value, "paymentVoucher");
            return (Criteria) this;
        }

        public Criteria andPaymentVoucherLike(String value) {
            addCriterion("paymentVoucher like", value, "paymentVoucher");
            return (Criteria) this;
        }

        public Criteria andPaymentVoucherNotLike(String value) {
            addCriterion("paymentVoucher not like", value, "paymentVoucher");
            return (Criteria) this;
        }

        public Criteria andPaymentVoucherIn(List<String> values) {
            addCriterion("paymentVoucher in", values, "paymentVoucher");
            return (Criteria) this;
        }

        public Criteria andPaymentVoucherNotIn(List<String> values) {
            addCriterion("paymentVoucher not in", values, "paymentVoucher");
            return (Criteria) this;
        }

        public Criteria andPaymentVoucherBetween(String value1, String value2) {
            addCriterion("paymentVoucher between", value1, value2, "paymentVoucher");
            return (Criteria) this;
        }

        public Criteria andPaymentVoucherNotBetween(String value1, String value2) {
            addCriterion("paymentVoucher not between", value1, value2, "paymentVoucher");
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