package grp3022.bbs.po;

import java.util.Date;

public class Browse extends BrowseKey {
    private Date browseTime;

    private Long browseCount;

    public Date getBrowseTime() {
        return browseTime;
    }

    public void setBrowseTime(Date browseTime) {
        this.browseTime = browseTime;
    }

    public Long getBrowseCount() {
        return browseCount;
    }

    public void setBrowseCount(Long browseCount) {
        this.browseCount = browseCount;
    }
}