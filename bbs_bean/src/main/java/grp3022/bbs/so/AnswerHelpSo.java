package grp3022.bbs.so;

public class AnswerHelpSo {
    //private Date createTime;

    private Short isHelpful;

	private Long userId;
	
	private Long answerId;
	
	private Long createBy;
	
	public AnswerHelpSo(){}
	
	public AnswerHelpSo(Long userId){
		this.setUserId(userId);
	}
	
	public AnswerHelpSo(Long createBy,Short isHelpful){
		this.setCreateBy(createBy);;
		this.setIsHelpful(isHelpful);
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Short getIsHelpful() {
		return isHelpful;
	}

	public void setIsHelpful(Short isHelpful) {
		this.isHelpful = isHelpful;
	}

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
}