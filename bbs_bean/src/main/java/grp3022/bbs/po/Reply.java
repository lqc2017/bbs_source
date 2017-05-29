package grp3022.bbs.po;

import java.util.Date;

public class Reply {
    private Long id;

    private Long postId;

    private Long citeId;

    private String content;

    private Date replyTime;

    private Long replyUser;

    private String name;

    private Long score;

    private Long citeFlorr;

    private String citeContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getCiteId() {
        return citeId;
    }

    public void setCiteId(Long citeId) {
        this.citeId = citeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public Long getReplyUser() {
        return replyUser;
    }

    public void setReplyUser(Long replyUser) {
        this.replyUser = replyUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getCiteFlorr() {
        return citeFlorr;
    }

    public void setCiteFlorr(Long citeFlorr) {
        this.citeFlorr = citeFlorr;
    }

    public String getCiteContent() {
        return citeContent;
    }

    public void setCiteContent(String citeContent) {
        this.citeContent = citeContent == null ? null : citeContent.trim();
    }
}