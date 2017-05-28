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

    private Integer aqCnt;

    private String follow;

    private String iContact;

    private String iAddress;

    private String protraitUrl;

    private String setting;

    private Integer score;

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

    public String getiContact() {
        return iContact;
    }

    public void setiContact(String iContact) {
        this.iContact = iContact == null ? null : iContact.trim();
    }

    public String getiAddress() {
        return iAddress;
    }

    public void setiAddress(String iAddress) {
        this.iAddress = iAddress == null ? null : iAddress.trim();
    }

    public String getProtraitUrl() {
        return protraitUrl;
    }

    public void setProtraitUrl(String protraitUrl) {
        this.protraitUrl = protraitUrl == null ? null : protraitUrl.trim();
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting == null ? null : setting.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}