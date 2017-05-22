package grp3022.bbs.so;

public class AnswerSo {

    //private String content;

    private Long createBy;
    
    //private Date createTime;

    private Long questionId;
    
    private Short isAcclaimed;
    
    private Short order;
    
    public AnswerSo(){}
    
    public AnswerSo(Long createBy) {
		this.setCreateBy(createBy);
	}
    
	public AnswerSo(Long createBy, Long questionId) {
		this.setQuestionId(questionId);
		this.setCreateBy(createBy);
	}
    
	public AnswerSo(Long createBy,Short isAcclaimed){
    	this.setIsAcclaimed(isAcclaimed);
    	this.setCreateBy(createBy);
    }
    
    /*public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }*/

	public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    /*public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }*/

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

	public Short getOrder() {
		return order;
	}

	public void setOrder(Short order) {
		this.order = order;
	}

	public Short getIsAcclaimed() {
		return isAcclaimed;
	}

	public void setIsAcclaimed(Short isAcclaimed) {
		this.isAcclaimed = isAcclaimed;
	}
}