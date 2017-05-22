package grp3022.bbs.wo;

import java.util.Date;

import grp3022.bbs.po.BBSUser;
import grp3022.bbs.po.Question;

public class Activity {
	private Date createTime;
	
	private Short type;
	
	private Question question;
	
	private BBSUser user;
	
	private Short isHelpful;
	
	public Activity(){}
	
	public Activity(Date createTime,Short type,Question question){
		this.setCreateTime(createTime);
		this.setType(type);
		this.setQuestion(question);
	}
	
	public Activity(Date createTime,Short type,Question question,BBSUser user){
		this.setCreateTime(createTime);
		this.setType(type);
		this.setQuestion(question);
		this.setUser(user);
	}
	
	public Activity(Date createTime,Short type,Question question,BBSUser user,Short isHelpful) {
		this.setCreateTime(createTime);
		this.setType(type);
		this.setQuestion(question);
		this.setUser(user);
		this.setIsHelpful(isHelpful);
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

	public Short getIsHelpful() {
		return isHelpful;
	}

	public void setIsHelpful(Short isHelpful) {
		this.isHelpful = isHelpful;
	}
}
