package grp3022.bbs.po;

import java.util.Date;

public class BBSUser {
    private Long id;

    private String nickname;

    private Short sex;

    private String telephone;

    private Date birthday;

    private String qMajor;

    private String qParticipate;

    private String address;

    private Integer aqCnt;

    private String follow;

    private String college;

    private String company;

    private String email;

    private String qq;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getqMajor() {
        return qMajor;
    }

    public void setqMajor(String qMajor) {
        this.qMajor = qMajor == null ? null : qMajor.trim();
    }

    public String getqParticipate() {
        return qParticipate;
    }

    public void setqParticipate(String qParticipate) {
        this.qParticipate = qParticipate == null ? null : qParticipate.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getAqCnt() {
        return aqCnt;
    }

    public void setAqCnt(Integer aqCnt) {
        this.aqCnt = aqCnt;
    }

    public String getFollow() {
        return follow;
    }

    public void setFollow(String follow) {
        this.follow = follow == null ? null : follow.trim();
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college == null ? null : college.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }
}