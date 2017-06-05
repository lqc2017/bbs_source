package grp3022.bbs.po;

import java.util.Date;

public class Post {
    private Long id;

    private String content;

    private String title;

    private Date postTime;

    private String tags;

    private Long views;

    private Long replys;

    private Short status;

    private Long floor;

    private Long postUser;

    private Date updateTime;

    private Short rewards;

    private String name;

    private Long acceptId;

    private Long reminder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getReplys() {
        return replys;
    }

    public void setReplys(Long replys) {
        this.replys = replys;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Long getFloor() {
        return floor;
    }

    public void setFloor(Long floor) {
        this.floor = floor;
    }

    public Long getPostUser() {
        return postUser;
    }

    public void setPostUser(Long postUser) {
        this.postUser = postUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Short getRewards() {
        return rewards;
    }

    public void setRewards(Short rewards) {
        this.rewards = rewards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getAcceptId() {
        return acceptId;
    }

    public void setAcceptId(Long acceptId) {
        this.acceptId = acceptId;
    }

    public Long getReminder() {
        return reminder;
    }

    public void setReminder(Long reminder) {
        this.reminder = reminder;
    }
}