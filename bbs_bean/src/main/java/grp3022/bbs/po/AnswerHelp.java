package grp3022.bbs.po;

import java.util.Date;

public class AnswerHelp extends AnswerHelpKey {
    private Date createTime;

    private Short isHelpful;

    private Long createBy;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Short getIsHelpful() {
        return isHelpful;
    }

    public void setIsHelpful(Short isHelpful) {
        this.isHelpful = isHelpful;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }
}