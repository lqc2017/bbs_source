package grp3022.bbs.so;

import java.util.List;

public class AnswerSo {

    //private String content;

    private Long createBy;
    
    private List<Long> questionIds;

    //private Date createTime;

    private Long questionId;
    
    private Short isAcclaimed;
    
    private Short order;
    
    public AnswerSo(Long questionId,Long createBy){
    	this.setQuestionId(questionId);
    	this.setCreateBy(createBy);
    }
    
    public AnswerSo(Short isAcclaimed,Long createBy){
    	this.setIsAcclaimed(isAcclaimed);
    	this.setCreateBy(createBy);
    }
    
    public AnswerSo(Short isAcclaimed,Long createBy,List<Long> questionIds){
    	this.setIsAcclaimed(isAcclaimed);
    	this.setCreateBy(createBy);
    	this.setQuestionIds(questionIds);
    }
    
    public AnswerSo(){}


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

	public List<Long> getQuestionIds() {
		return questionIds;
	}

	public void setQuestionIds(List<Long> questionIds) {
		this.questionIds = questionIds;
	}
}