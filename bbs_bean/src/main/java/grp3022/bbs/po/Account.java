package grp3022.bbs.po;

import java.util.Date;

public class Account {
    private String username;

    private String password;

    private Long userid;

    private Short role;

    private Date latestLogin;

    private Date createTime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Short getRole() {
        return role;
    }

    public void setRole(Short role) {
        this.role = role;
    }

    public Date getLatestLogin() {
        return latestLogin;
    }

    public void setLatestLogin(Date latestLogin) {
        this.latestLogin = latestLogin;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}