package grp3022.bbs.wo;

import java.util.Date;

import grp3022.bbs.po.BBSUser;
import grp3022.bbs.po.Post;

public class UnreadReply {
	private Date createTime;
	
	private Short type;
	
	private Post post;
	
	private BBSUser user;
	
	public UnreadReply(){}
	
	public UnreadReply(Date createTime,Short type,Post post){
		this.setCreateTime(createTime);
		this.setType(type);
		this.setPost(post);
	}
	
	public UnreadReply(Date createTime,Short type,Post post,BBSUser user){
		this.setCreateTime(createTime);
		this.setType(type);
		this.setPost(post);
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


	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public BBSUser getUser() {
		return user;
	}

	public void setUser(BBSUser user) {
		this.user = user;
	}
}
