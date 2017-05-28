package grp3022.bbs.wo;

import java.util.Date;

import grp3022.bbs.po.BBSUser;
import grp3022.bbs.po.Question;

public class UnreadMessage {
	private Date createTime;
	
	private Short type;
	
	private Question question;
	
	private BBSUser user;
	
	public UnreadMessage(){}
	
	public UnreadMessage(Date createTime,Short type,Question question){
		this.setCreateTime(createTime);
		this.setType(type);
		this.setQuestion(question);
	}
	
	public UnreadMessage(Date createTime,Short type,Question question,BBSUser user){
		this.setCreateTime(createTime);
		this.setType(type);
		this.setQuestion(question);
		this.setUser(user);
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}


	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public BBSUser getUser() {
		return user;
	}

	public void setUser(BBSUser user) {
		this.user = user;
	}
}
