package grp3022.bbs.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BBSUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BBSUserExample() {
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

        public Criteria andNicknameIsNull() {
            addCriterion("NICKNAME is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("NICKNAME is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("NICKNAME =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("NICKNAME <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("NICKNAME >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("NICKNAME >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("NICKNAME <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("NICKNAME <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("NICKNAME like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("NICKNAME not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("NICKNAME in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("NICKNAME not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("NICKNAME between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("NICKNAME not between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("SEX is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("SEX is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(Short value) {
            addCriterion("SEX =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(Short value) {
            addCriterion("SEX <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(Short value) {
            addCriterion("SEX >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(Short value) {
            addCriterion("SEX >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(Short value) {
            addCriterion("SEX <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(Short value) {
            addCriterion("SEX <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<Short> values) {
            addCriterion("SEX in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<Short> values) {
            addCriterion("SEX not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(Short value1, Short value2) {
            addCriterion("SEX between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(Short value1, Short value2) {
            addCriterion("SEX not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNull() {
            addCriterion("TELEPHONE is null");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNotNull() {
            addCriterion("TELEPHONE is not null");
            return (Criteria) this;
        }

        public Criteria andTelephoneEqualTo(String value) {
            addCriterion("TELEPHONE =", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotEqualTo(String value) {
            addCriterion("TELEPHONE <>", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThan(String value) {
            addCriterion("TELEPHONE >", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("TELEPHONE >=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThan(String value) {
            addCriterion("TELEPHONE <", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThanOrEqualTo(String value) {
            addCriterion("TELEPHONE <=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLike(String value) {
            addCriterion("TELEPHONE like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotLike(String value) {
            addCriterion("TELEPHONE not like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneIn(List<String> values) {
            addCriterion("TELEPHONE in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotIn(List<String> values) {
            addCriterion("TELEPHONE not in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneBetween(String value1, String value2) {
            addCriterion("TELEPHONE between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotBetween(String value1, String value2) {
            addCriterion("TELEPHONE not between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("BIRTHDAY is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("BIRTHDAY is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(Date value) {
            addCriterion("BIRTHDAY =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(Date value) {
            addCriterion("BIRTHDAY <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(Date value) {
            addCriterion("BIRTHDAY >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterion("BIRTHDAY >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(Date value) {
            addCriterion("BIRTHDAY <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(Date value) {
            addCriterion("BIRTHDAY <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<Date> values) {
            addCriterion("BIRTHDAY in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<Date> values) {
            addCriterion("BIRTHDAY not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(Date value1, Date value2) {
            addCriterion("BIRTHDAY between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(Date value1, Date value2) {
            addCriterion("BIRTHDAY not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andQMajorIsNull() {
            addCriterion("Q_MAJOR is null");
            return (Criteria) this;
        }

        public Criteria andQMajorIsNotNull() {
            addCriterion("Q_MAJOR is not null");
            return (Criteria) this;
        }

        public Criteria andQMajorEqualTo(String value) {
            addCriterion("Q_MAJOR =", value, "qMajor");
            return (Criteria) this;
        }

        public Criteria andQMajorNotEqualTo(String value) {
            addCriterion("Q_MAJOR <>", value, "qMajor");
            return (Criteria) this;
        }

        public Criteria andQMajorGreaterThan(String value) {
            addCriterion("Q_MAJOR >", value, "qMajor");
            return (Criteria) this;
        }

        public Criteria andQMajorGreaterThanOrEqualTo(String value) {
            addCriterion("Q_MAJOR >=", value, "qMajor");
            return (Criteria) this;
        }

        public Criteria andQMajorLessThan(String value) {
            addCriterion("Q_MAJOR <", value, "qMajor");
            return (Criteria) this;
        }

        public Criteria andQMajorLessThanOrEqualTo(String value) {
            addCriterion("Q_MAJOR <=", value, "qMajor");
            return (Criteria) this;
        }

        public Criteria andQMajorLike(String value) {
            addCriterion("Q_MAJOR like", value, "qMajor");
            return (Criteria) this;
        }

        public Criteria andQMajorNotLike(String value) {
            addCriterion("Q_MAJOR not like", value, "qMajor");
            return (Criteria) this;
        }

        public Criteria andQMajorIn(List<String> values) {
            addCriterion("Q_MAJOR in", values, "qMajor");
            return (Criteria) this;
        }

        public Criteria andQMajorNotIn(List<String> values) {
            addCriterion("Q_MAJOR not in", values, "qMajor");
            return (Criteria) this;
        }

        public Criteria andQMajorBetween(String value1, String value2) {
            addCriterion("Q_MAJOR between", value1, value2, "qMajor");
            return (Criteria) this;
        }

        public Criteria andQMajorNotBetween(String value1, String value2) {
            addCriterion("Q_MAJOR not between", value1, value2, "qMajor");
            return (Criteria) this;
        }

        public Criteria andQParticipateIsNull() {
            addCriterion("Q_PARTICIPATE is null");
            return (Criteria) this;
        }

        public Criteria andQParticipateIsNotNull() {
            addCriterion("Q_PARTICIPATE is not null");
            return (Criteria) this;
        }

        public Criteria andQParticipateEqualTo(String value) {
            addCriterion("Q_PARTICIPATE =", value, "qParticipate");
            return (Criteria) this;
        }

        public Criteria andQParticipateNotEqualTo(String value) {
            addCriterion("Q_PARTICIPATE <>", value, "qParticipate");
            return (Criteria) this;
        }

        public Criteria andQParticipateGreaterThan(String value) {
            addCriterion("Q_PARTICIPATE >", value, "qParticipate");
            return (Criteria) this;
        }

        public Criteria andQParticipateGreaterThanOrEqualTo(String value) {
            addCriterion("Q_PARTICIPATE >=", value, "qParticipate");
            return (Criteria) this;
        }

        public Criteria andQParticipateLessThan(String value) {
            addCriterion("Q_PARTICIPATE <", value, "qParticipate");
            return (Criteria) this;
        }

        public Criteria andQParticipateLessThanOrEqualTo(String value) {
            addCriterion("Q_PARTICIPATE <=", value, "qParticipate");
            return (Criteria) this;
        }

        public Criteria andQParticipateLike(String value) {
            addCriterion("Q_PARTICIPATE like", value, "qParticipate");
            return (Criteria) this;
        }

        public Criteria andQParticipateNotLike(String value) {
            addCriterion("Q_PARTICIPATE not like", value, "qParticipate");
            return (Criteria) this;
        }

        public Criteria andQParticipateIn(List<String> values) {
            addCriterion("Q_PARTICIPATE in", values, "qParticipate");
            return (Criteria) this;
        }

        public Criteria andQParticipateNotIn(List<String> values) {
            addCriterion("Q_PARTICIPATE not in", values, "qParticipate");
            return (Criteria) this;
        }

        public Criteria andQParticipateBetween(String value1, String value2) {
            addCriterion("Q_PARTICIPATE between", value1, value2, "qParticipate");
            return (Criteria) this;
        }

        public Criteria andQParticipateNotBetween(String value1, String value2) {
            addCriterion("Q_PARTICIPATE not between", value1, value2, "qParticipate");
            return (Criteria) this;
        }

        public Criteria andAqCntIsNull() {
            addCriterion("AQ_CNT is null");
            return (Criteria) this;
        }

        public Criteria andAqCntIsNotNull() {
            addCriterion("AQ_CNT is not null");
            return (Criteria) this;
        }

        public Criteria andAqCntEqualTo(Integer value) {
            addCriterion("AQ_CNT =", value, "aqCnt");
            return (Criteria) this;
        }

        public Criteria andAqCntNotEqualTo(Integer value) {
            addCriterion("AQ_CNT <>", value, "aqCnt");
            return (Criteria) this;
        }

        public Criteria andAqCntGreaterThan(Integer value) {
            addCriterion("AQ_CNT >", value, "aqCnt");
            return (Criteria) this;
        }

        public Criteria andAqCntGreaterThanOrEqualTo(Integer value) {
            addCriterion("AQ_CNT >=", value, "aqCnt");
            return (Criteria) this;
        }

        public Criteria andAqCntLessThan(Integer value) {
            addCriterion("AQ_CNT <", value, "aqCnt");
            return (Criteria) this;
        }

        public Criteria andAqCntLessThanOrEqualTo(Integer value) {
            addCriterion("AQ_CNT <=", value, "aqCnt");
            return (Criteria) this;
        }

        public Criteria andAqCntIn(List<Integer> values) {
            addCriterion("AQ_CNT in", values, "aqCnt");
            return (Criteria) this;
        }

        public Criteria andAqCntNotIn(List<Integer> values) {
            addCriterion("AQ_CNT not in", values, "aqCnt");
            return (Criteria) this;
        }

        public Criteria andAqCntBetween(Integer value1, Integer value2) {
            addCriterion("AQ_CNT between", value1, value2, "aqCnt");
            return (Criteria) this;
        }

        public Criteria andAqCntNotBetween(Integer value1, Integer value2) {
            addCriterion("AQ_CNT not between", value1, value2, "aqCnt");
            return (Criteria) this;
        }

        public Criteria andFollowIsNull() {
            addCriterion("FOLLOW is null");
            return (Criteria) this;
        }

        public Criteria andFollowIsNotNull() {
            addCriterion("FOLLOW is not null");
            return (Criteria) this;
        }

        public Criteria andFollowEqualTo(String value) {
            addCriterion("FOLLOW =", value, "follow");
            return (Criteria) this;
        }

        public Criteria andFollowNotEqualTo(String value) {
            addCriterion("FOLLOW <>", value, "follow");
            return (Criteria) this;
        }

        public Criteria andFollowGreaterThan(String value) {
            addCriterion("FOLLOW >", value, "follow");
            return (Criteria) this;
        }

        public Criteria andFollowGreaterThanOrEqualTo(String value) {
            addCriterion("FOLLOW >=", value, "follow");
            return (Criteria) this;
        }

        public Criteria andFollowLessThan(String value) {
            addCriterion("FOLLOW <", value, "follow");
            return (Criteria) this;
        }

        public Criteria andFollowLessThanOrEqualTo(String value) {
            addCriterion("FOLLOW <=", value, "follow");
            return (Criteria) this;
        }

        public Criteria andFollowLike(String value) {
            addCriterion("FOLLOW like", value, "follow");
            return (Criteria) this;
        }

        public Criteria andFollowNotLike(String value) {
            addCriterion("FOLLOW not like", value, "follow");
            return (Criteria) this;
        }

        public Criteria andFollowIn(List<String> values) {
            addCriterion("FOLLOW in", values, "follow");
            return (Criteria) this;
        }

        public Criteria andFollowNotIn(List<String> values) {
            addCriterion("FOLLOW not in", values, "follow");
            return (Criteria) this;
        }

        public Criteria andFollowBetween(String value1, String value2) {
            addCriterion("FOLLOW between", value1, value2, "follow");
            return (Criteria) this;
        }

        public Criteria andFollowNotBetween(String value1, String value2) {
            addCriterion("FOLLOW not between", value1, value2, "follow");
            return (Criteria) this;
        }

        public Criteria andIContactIsNull() {
            addCriterion("I_CONTACT is null");
            return (Criteria) this;
        }

        public Criteria andIContactIsNotNull() {
            addCriterion("I_CONTACT is not null");
            return (Criteria) this;
        }

        public Criteria andIContactEqualTo(String value) {
            addCriterion("I_CONTACT =", value, "iContact");
            return (Criteria) this;
        }

        public Criteria andIContactNotEqualTo(String value) {
            addCriterion("I_CONTACT <>", value, "iContact");
            return (Criteria) this;
        }

        public Criteria andIContactGreaterThan(String value) {
            addCriterion("I_CONTACT >", value, "iContact");
            return (Criteria) this;
        }

        public Criteria andIContactGreaterThanOrEqualTo(String value) {
            addCriterion("I_CONTACT >=", value, "iContact");
            return (Criteria) this;
        }

        public Criteria andIContactLessThan(String value) {
            addCriterion("I_CONTACT <", value, "iContact");
            return (Criteria) this;
        }

        public Criteria andIContactLessThanOrEqualTo(String value) {
            addCriterion("I_CONTACT <=", value, "iContact");
            return (Criteria) this;
        }

        public Criteria andIContactLike(String value) {
            addCriterion("I_CONTACT like", value, "iContact");
            return (Criteria) this;
        }

        public Criteria andIContactNotLike(String value) {
            addCriterion("I_CONTACT not like", value, "iContact");
            return (Criteria) this;
        }

        public Criteria andIContactIn(List<String> values) {
            addCriterion("I_CONTACT in", values, "iContact");
            return (Criteria) this;
        }

        public Criteria andIContactNotIn(List<String> values) {
            addCriterion("I_CONTACT not in", values, "iContact");
            return (Criteria) this;
        }

        public Criteria andIContactBetween(String value1, String value2) {
            addCriterion("I_CONTACT between", value1, value2, "iContact");
            return (Criteria) this;
        }

        public Criteria andIContactNotBetween(String value1, String value2) {
            addCriterion("I_CONTACT not between", value1, value2, "iContact");
            return (Criteria) this;
        }

        public Criteria andIAddressIsNull() {
            addCriterion("I_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andIAddressIsNotNull() {
            addCriterion("I_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andIAddressEqualTo(String value) {
            addCriterion("I_ADDRESS =", value, "iAddress");
            return (Criteria) this;
        }

        public Criteria andIAddressNotEqualTo(String value) {
            addCriterion("I_ADDRESS <>", value, "iAddress");
            return (Criteria) this;
        }

        public Criteria andIAddressGreaterThan(String value) {
            addCriterion("I_ADDRESS >", value, "iAddress");
            return (Criteria) this;
        }

        public Criteria andIAddressGreaterThanOrEqualTo(String value) {
            addCriterion("I_ADDRESS >=", value, "iAddress");
            return (Criteria) this;
        }

        public Criteria andIAddressLessThan(String value) {
            addCriterion("I_ADDRESS <", value, "iAddress");
            return (Criteria) this;
        }

        public Criteria andIAddressLessThanOrEqualTo(String value) {
            addCriterion("I_ADDRESS <=", value, "iAddress");
            return (Criteria) this;
        }

        public Criteria andIAddressLike(String value) {
            addCriterion("I_ADDRESS like", value, "iAddress");
            return (Criteria) this;
        }

        public Criteria andIAddressNotLike(String value) {
            addCriterion("I_ADDRESS not like", value, "iAddress");
            return (Criteria) this;
        }

        public Criteria andIAddressIn(List<String> values) {
            addCriterion("I_ADDRESS in", values, "iAddress");
            return (Criteria) this;
        }

        public Criteria andIAddressNotIn(List<String> values) {
            addCriterion("I_ADDRESS not in", values, "iAddress");
            return (Criteria) this;
        }

        public Criteria andIAddressBetween(String value1, String value2) {
            addCriterion("I_ADDRESS between", value1, value2, "iAddress");
            return (Criteria) this;
        }

        public Criteria andIAddressNotBetween(String value1, String value2) {
            addCriterion("I_ADDRESS not between", value1, value2, "iAddress");
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