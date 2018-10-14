package com.ztx.world.base.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConstantExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ConstantExample() {
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

    /**
     * 
     * 
     * @author wcyong
     * 
     * @date 2018-10-14
     */
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
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

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNull() {
            addCriterion("create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(Long value) {
            addCriterion("create_user_id =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(Long value) {
            addCriterion("create_user_id <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(Long value) {
            addCriterion("create_user_id >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("create_user_id >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(Long value) {
            addCriterion("create_user_id <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(Long value) {
            addCriterion("create_user_id <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<Long> values) {
            addCriterion("create_user_id in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<Long> values) {
            addCriterion("create_user_id not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(Long value1, Long value2) {
            addCriterion("create_user_id between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(Long value1, Long value2) {
            addCriterion("create_user_id not between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andConstantTypeIsNull() {
            addCriterion("constant_type is null");
            return (Criteria) this;
        }

        public Criteria andConstantTypeIsNotNull() {
            addCriterion("constant_type is not null");
            return (Criteria) this;
        }

        public Criteria andConstantTypeEqualTo(String value) {
            addCriterion("constant_type =", value, "constantType");
            return (Criteria) this;
        }

        public Criteria andConstantTypeNotEqualTo(String value) {
            addCriterion("constant_type <>", value, "constantType");
            return (Criteria) this;
        }

        public Criteria andConstantTypeGreaterThan(String value) {
            addCriterion("constant_type >", value, "constantType");
            return (Criteria) this;
        }

        public Criteria andConstantTypeGreaterThanOrEqualTo(String value) {
            addCriterion("constant_type >=", value, "constantType");
            return (Criteria) this;
        }

        public Criteria andConstantTypeLessThan(String value) {
            addCriterion("constant_type <", value, "constantType");
            return (Criteria) this;
        }

        public Criteria andConstantTypeLessThanOrEqualTo(String value) {
            addCriterion("constant_type <=", value, "constantType");
            return (Criteria) this;
        }

        public Criteria andConstantTypeLike(String value) {
            addCriterion("constant_type like", value, "constantType");
            return (Criteria) this;
        }

        public Criteria andConstantTypeNotLike(String value) {
            addCriterion("constant_type not like", value, "constantType");
            return (Criteria) this;
        }

        public Criteria andConstantTypeIn(List<String> values) {
            addCriterion("constant_type in", values, "constantType");
            return (Criteria) this;
        }

        public Criteria andConstantTypeNotIn(List<String> values) {
            addCriterion("constant_type not in", values, "constantType");
            return (Criteria) this;
        }

        public Criteria andConstantTypeBetween(String value1, String value2) {
            addCriterion("constant_type between", value1, value2, "constantType");
            return (Criteria) this;
        }

        public Criteria andConstantTypeNotBetween(String value1, String value2) {
            addCriterion("constant_type not between", value1, value2, "constantType");
            return (Criteria) this;
        }

        public Criteria andConstantKeyIsNull() {
            addCriterion("constant_key is null");
            return (Criteria) this;
        }

        public Criteria andConstantKeyIsNotNull() {
            addCriterion("constant_key is not null");
            return (Criteria) this;
        }

        public Criteria andConstantKeyEqualTo(String value) {
            addCriterion("constant_key =", value, "constantKey");
            return (Criteria) this;
        }

        public Criteria andConstantKeyNotEqualTo(String value) {
            addCriterion("constant_key <>", value, "constantKey");
            return (Criteria) this;
        }

        public Criteria andConstantKeyGreaterThan(String value) {
            addCriterion("constant_key >", value, "constantKey");
            return (Criteria) this;
        }

        public Criteria andConstantKeyGreaterThanOrEqualTo(String value) {
            addCriterion("constant_key >=", value, "constantKey");
            return (Criteria) this;
        }

        public Criteria andConstantKeyLessThan(String value) {
            addCriterion("constant_key <", value, "constantKey");
            return (Criteria) this;
        }

        public Criteria andConstantKeyLessThanOrEqualTo(String value) {
            addCriterion("constant_key <=", value, "constantKey");
            return (Criteria) this;
        }

        public Criteria andConstantKeyLike(String value) {
            addCriterion("constant_key like", value, "constantKey");
            return (Criteria) this;
        }

        public Criteria andConstantKeyNotLike(String value) {
            addCriterion("constant_key not like", value, "constantKey");
            return (Criteria) this;
        }

        public Criteria andConstantKeyIn(List<String> values) {
            addCriterion("constant_key in", values, "constantKey");
            return (Criteria) this;
        }

        public Criteria andConstantKeyNotIn(List<String> values) {
            addCriterion("constant_key not in", values, "constantKey");
            return (Criteria) this;
        }

        public Criteria andConstantKeyBetween(String value1, String value2) {
            addCriterion("constant_key between", value1, value2, "constantKey");
            return (Criteria) this;
        }

        public Criteria andConstantKeyNotBetween(String value1, String value2) {
            addCriterion("constant_key not between", value1, value2, "constantKey");
            return (Criteria) this;
        }

        public Criteria andConstantValueIsNull() {
            addCriterion("constant_value is null");
            return (Criteria) this;
        }

        public Criteria andConstantValueIsNotNull() {
            addCriterion("constant_value is not null");
            return (Criteria) this;
        }

        public Criteria andConstantValueEqualTo(String value) {
            addCriterion("constant_value =", value, "constantValue");
            return (Criteria) this;
        }

        public Criteria andConstantValueNotEqualTo(String value) {
            addCriterion("constant_value <>", value, "constantValue");
            return (Criteria) this;
        }

        public Criteria andConstantValueGreaterThan(String value) {
            addCriterion("constant_value >", value, "constantValue");
            return (Criteria) this;
        }

        public Criteria andConstantValueGreaterThanOrEqualTo(String value) {
            addCriterion("constant_value >=", value, "constantValue");
            return (Criteria) this;
        }

        public Criteria andConstantValueLessThan(String value) {
            addCriterion("constant_value <", value, "constantValue");
            return (Criteria) this;
        }

        public Criteria andConstantValueLessThanOrEqualTo(String value) {
            addCriterion("constant_value <=", value, "constantValue");
            return (Criteria) this;
        }

        public Criteria andConstantValueLike(String value) {
            addCriterion("constant_value like", value, "constantValue");
            return (Criteria) this;
        }

        public Criteria andConstantValueNotLike(String value) {
            addCriterion("constant_value not like", value, "constantValue");
            return (Criteria) this;
        }

        public Criteria andConstantValueIn(List<String> values) {
            addCriterion("constant_value in", values, "constantValue");
            return (Criteria) this;
        }

        public Criteria andConstantValueNotIn(List<String> values) {
            addCriterion("constant_value not in", values, "constantValue");
            return (Criteria) this;
        }

        public Criteria andConstantValueBetween(String value1, String value2) {
            addCriterion("constant_value between", value1, value2, "constantValue");
            return (Criteria) this;
        }

        public Criteria andConstantValueNotBetween(String value1, String value2) {
            addCriterion("constant_value not between", value1, value2, "constantValue");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * 
     * 
     * @author wcyong
     * 
     * @date 2018-10-14
     */
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