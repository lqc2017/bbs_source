package grp3022.bbs.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ReplyExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPostIdIsNull() {
            addCriterion("POST_ID is null");
            return (Criteria) this;
        }

        public Criteria andPostIdIsNotNull() {
            addCriterion("POST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPostIdEqualTo(Long value) {
            addCriterion("POST_ID =", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdNotEqualTo(Long value) {
            addCriterion("POST_ID <>", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdGreaterThan(Long value) {
            addCriterion("POST_ID >", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdGreaterThanOrEqualTo(Long value) {
            addCriterion("POST_ID >=", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdLessThan(Long value) {
            addCriterion("POST_ID <", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdLessThanOrEqualTo(Long value) {
            addCriterion("POST_ID <=", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdIn(List<Long> values) {
            addCriterion("POST_ID in", values, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdNotIn(List<Long> values) {
            addCriterion("POST_ID not in", values, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdBetween(Long value1, Long value2) {
            addCriterion("POST_ID between", value1, value2, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdNotBetween(Long value1, Long value2) {
            addCriterion("POST_ID not between", value1, value2, "postId");
            return (Criteria) this;
        }

        public Criteria andCiteIdIsNull() {
            addCriterion("CITE_ID is null");
            return (Criteria) this;
        }

        public Criteria andCiteIdIsNotNull() {
            addCriterion("CITE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCiteIdEqualTo(Long value) {
            addCriterion("CITE_ID =", value, "citeId");
            return (Criteria) this;
        }

        public Criteria andCiteIdNotEqualTo(Long value) {
            addCriterion("CITE_ID <>", value, "citeId");
            return (Criteria) this;
        }

        public Criteria andCiteIdGreaterThan(Long value) {
            addCriterion("CITE_ID >", value, "citeId");
            return (Criteria) this;
        }

        public Criteria andCiteIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CITE_ID >=", value, "citeId");
            return (Criteria) this;
        }

        public Criteria andCiteIdLessThan(Long value) {
            addCriterion("CITE_ID <", value, "citeId");
            return (Criteria) this;
        }

        public Criteria andCiteIdLessThanOrEqualTo(Long value) {
            addCriterion("CITE_ID <=", value, "citeId");
            return (Criteria) this;
        }

        public Criteria andCiteIdIn(List<Long> values) {
            addCriterion("CITE_ID in", values, "citeId");
            return (Criteria) this;
        }

        public Criteria andCiteIdNotIn(List<Long> values) {
            addCriterion("CITE_ID not in", values, "citeId");
            return (Criteria) this;
        }

        public Criteria andCiteIdBetween(Long value1, Long value2) {
            addCriterion("CITE_ID between", value1, value2, "citeId");
            return (Criteria) this;
        }

        public Criteria andCiteIdNotBetween(Long value1, Long value2) {
            addCriterion("CITE_ID not between", value1, value2, "citeId");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("CONTENT =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("CONTENT <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("CONTENT >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("CONTENT >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("CONTENT <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("CONTENT <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("CONTENT like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("CONTENT not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("CONTENT in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("CONTENT not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("CONTENT between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("CONTENT not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andReplyTimeIsNull() {
            addCriterion("REPLY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andReplyTimeIsNotNull() {
            addCriterion("REPLY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andReplyTimeEqualTo(Date value) {
            addCriterion("REPLY_TIME =", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeNotEqualTo(Date value) {
            addCriterion("REPLY_TIME <>", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeGreaterThan(Date value) {
            addCriterion("REPLY_TIME >", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("REPLY_TIME >=", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLessThan(Date value) {
            addCriterion("REPLY_TIME <", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLessThanOrEqualTo(Date value) {
            addCriterion("REPLY_TIME <=", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeIn(List<Date> values) {
            addCriterion("REPLY_TIME in", values, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeNotIn(List<Date> values) {
            addCriterion("REPLY_TIME not in", values, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeBetween(Date value1, Date value2) {
            addCriterion("REPLY_TIME between", value1, value2, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeNotBetween(Date value1, Date value2) {
            addCriterion("REPLY_TIME not between", value1, value2, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyUserIsNull() {
            addCriterion("REPLY_USER is null");
            return (Criteria) this;
        }

        public Criteria andReplyUserIsNotNull() {
            addCriterion("REPLY_USER is not null");
            return (Criteria) this;
        }

        public Criteria andReplyUserEqualTo(Long value) {
            addCriterion("REPLY_USER =", value, "replyUser");
            return (Criteria) this;
        }

        public Criteria andReplyUserNotEqualTo(Long value) {
            addCriterion("REPLY_USER <>", value, "replyUser");
            return (Criteria) this;
        }

        public Criteria andReplyUserGreaterThan(Long value) {
            addCriterion("REPLY_USER >", value, "replyUser");
            return (Criteria) this;
        }

        public Criteria andReplyUserGreaterThanOrEqualTo(Long value) {
            addCriterion("REPLY_USER >=", value, "replyUser");
            return (Criteria) this;
        }

        public Criteria andReplyUserLessThan(Long value) {
            addCriterion("REPLY_USER <", value, "replyUser");
            return (Criteria) this;
        }

        public Criteria andReplyUserLessThanOrEqualTo(Long value) {
            addCriterion("REPLY_USER <=", value, "replyUser");
            return (Criteria) this;
        }

        public Criteria andReplyUserIn(List<Long> values) {
            addCriterion("REPLY_USER in", values, "replyUser");
            return (Criteria) this;
        }

        public Criteria andReplyUserNotIn(List<Long> values) {
            addCriterion("REPLY_USER not in", values, "replyUser");
            return (Criteria) this;
        }

        public Criteria andReplyUserBetween(Long value1, Long value2) {
            addCriterion("REPLY_USER between", value1, value2, "replyUser");
            return (Criteria) this;
        }

        public Criteria andReplyUserNotBetween(Long value1, Long value2) {
            addCriterion("REPLY_USER not between", value1, value2, "replyUser");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("SCORE is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Long value) {
            addCriterion("SCORE =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Long value) {
            addCriterion("SCORE <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Long value) {
            addCriterion("SCORE >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Long value) {
            addCriterion("SCORE >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Long value) {
            addCriterion("SCORE <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Long value) {
            addCriterion("SCORE <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Long> values) {
            addCriterion("SCORE in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Long> values) {
            addCriterion("SCORE not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Long value1, Long value2) {
            addCriterion("SCORE between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Long value1, Long value2) {
            addCriterion("SCORE not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andCiteFlorrIsNull() {
            addCriterion("CITE_FLORR is null");
            return (Criteria) this;
        }

        public Criteria andCiteFlorrIsNotNull() {
            addCriterion("CITE_FLORR is not null");
            return (Criteria) this;
        }

        public Criteria andCiteFlorrEqualTo(Long value) {
            addCriterion("CITE_FLORR =", value, "citeFlorr");
            return (Criteria) this;
        }

        public Criteria andCiteFlorrNotEqualTo(Long value) {
            addCriterion("CITE_FLORR <>", value, "citeFlorr");
            return (Criteria) this;
        }

        public Criteria andCiteFlorrGreaterThan(Long value) {
            addCriterion("CITE_FLORR >", value, "citeFlorr");
            return (Criteria) this;
        }

        public Criteria andCiteFlorrGreaterThanOrEqualTo(Long value) {
            addCriterion("CITE_FLORR >=", value, "citeFlorr");
            return (Criteria) this;
        }

        public Criteria andCiteFlorrLessThan(Long value) {
            addCriterion("CITE_FLORR <", value, "citeFlorr");
            return (Criteria) this;
        }

        public Criteria andCiteFlorrLessThanOrEqualTo(Long value) {
            addCriterion("CITE_FLORR <=", value, "citeFlorr");
            return (Criteria) this;
        }

        public Criteria andCiteFlorrIn(List<Long> values) {
            addCriterion("CITE_FLORR in", values, "citeFlorr");
            return (Criteria) this;
        }

        public Criteria andCiteFlorrNotIn(List<Long> values) {
            addCriterion("CITE_FLORR not in", values, "citeFlorr");
            return (Criteria) this;
        }

        public Criteria andCiteFlorrBetween(Long value1, Long value2) {
            addCriterion("CITE_FLORR between", value1, value2, "citeFlorr");
            return (Criteria) this;
        }

        public Criteria andCiteFlorrNotBetween(Long value1, Long value2) {
            addCriterion("CITE_FLORR not between", value1, value2, "citeFlorr");
            return (Criteria) this;
        }

        public Criteria andCiteContentIsNull() {
            addCriterion("CITE_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andCiteContentIsNotNull() {
            addCriterion("CITE_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andCiteContentEqualTo(String value) {
            addCriterion("CITE_CONTENT =", value, "citeContent");
            return (Criteria) this;
        }

        public Criteria andCiteContentNotEqualTo(String value) {
            addCriterion("CITE_CONTENT <>", value, "citeContent");
            return (Criteria) this;
        }

        public Criteria andCiteContentGreaterThan(String value) {
            addCriterion("CITE_CONTENT >", value, "citeContent");
            return (Criteria) this;
        }

        public Criteria andCiteContentGreaterThanOrEqualTo(String value) {
            addCriterion("CITE_CONTENT >=", value, "citeContent");
            return (Criteria) this;
        }

        public Criteria andCiteContentLessThan(String value) {
            addCriterion("CITE_CONTENT <", value, "citeContent");
            return (Criteria) this;
        }

        public Criteria andCiteContentLessThanOrEqualTo(String value) {
            addCriterion("CITE_CONTENT <=", value, "citeContent");
            return (Criteria) this;
        }

        public Criteria andCiteContentLike(String value) {
            addCriterion("CITE_CONTENT like", value, "citeContent");
            return (Criteria) this;
        }

        public Criteria andCiteContentNotLike(String value) {
            addCriterion("CITE_CONTENT not like", value, "citeContent");
            return (Criteria) this;
        }

        public Criteria andCiteContentIn(List<String> values) {
            addCriterion("CITE_CONTENT in", values, "citeContent");
            return (Criteria) this;
        }

        public Criteria andCiteContentNotIn(List<String> values) {
            addCriterion("CITE_CONTENT not in", values, "citeContent");
            return (Criteria) this;
        }

        public Criteria andCiteContentBetween(String value1, String value2) {
            addCriterion("CITE_CONTENT between", value1, value2, "citeContent");
            return (Criteria) this;
        }

        public Criteria andCiteContentNotBetween(String value1, String value2) {
            addCriterion("CITE_CONTENT not between", value1, value2, "citeContent");
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