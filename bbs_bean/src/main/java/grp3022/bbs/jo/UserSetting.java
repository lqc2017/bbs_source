package grp3022.bbs.jo;

public class UserSetting {
	private Short showInfo;
	private Short showActivity;
	private Short messageRemind;
	public UserSetting(){}
	public UserSetting(Short showInfo, Short showActivity, Short messageRemind){
		this.setShowInfo(showInfo);
		this.setShowActivity(showActivity);
		this.setMessageRemind(messageRemind);
	}
	public Short getShowInfo() {
		return showInfo;
	}
	public void setShowInfo(Short showInfo) {
		this.showInfo = showInfo;
	}
	public Short getShowActivity() {
		return showActivity;
	}
	public void setShowActivity(Short showActivity) {
		this.showActivity = showActivity;
	}
	public Short getMessageRemind() {
		return messageRemind;
	}
	public void setMessageRemind(Short messageRemind) {
		this.messageRemind = messageRemind;
	}
	
	
}