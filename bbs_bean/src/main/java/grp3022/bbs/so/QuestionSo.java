package grp3022.bbs.so;

public class QuestionSo {
	private Long createBy;
	
    private String title;

    private String describe;

    private Short status;
    
    private String tagIndex;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public String getTagIndex() {
		return tagIndex;
	}

	public void setTagIndex(String tagIndex) {
		this.tagIndex = tagIndex;
	}

}