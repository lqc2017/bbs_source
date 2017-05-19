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

    private Integer gaCnt;

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

    public Integer getGaCnt() {
        return gaCnt;
    }

    public void setGaCnt(Integer gaCnt) {
        this.gaCnt = gaCnt;
    }
}