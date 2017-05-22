package grp3022.bbs.so;

public class AnswerHelpSo {
   /* private Date createTime;

    private Short isHelpful;*/

	private Long userId;
	
	public AnswerHelpSo(){}
	
	public AnswerHelpSo(Long userId){
		this.setUserId(userId);
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}