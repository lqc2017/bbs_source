package grp3022.bbs.po;

import java.util.Date;

public class ReplyHelp extends ReplyHelpKey {
    private Date createTime;

    private Short isGood;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Short getIsGood() {
        return isGood;
    }

    public void setIsGood(Short isGood) {
        this.isGood = isGood;
    }
}