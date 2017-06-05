package grp3022.bbs.so;

import java.util.Date;
import java.util.List;

public class PostSo {
	private List<Long> ids;
	
	private Long postUser;
	
    private Short status;
    
    private String tagIndex;
    
    private Short order;
    
    private Short timeFrame;
    
    private String keywords;
    
    private Date startTime;
    
    private Date endTime;

    public PostSo(){}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public Long getPostUser() {
		return postUser;
	}

	public void setPostUser(Long postUser) {
		this.postUser = postUser;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getTagIndex() {
		return tagIndex;
	}

	public void setTagIndex(String tagIndex) {
		this.tagIndex = tagIndex;
	}

	public Short getOrder() {
		return order;
	}

	public void setOrder(Short order) {
		this.order = order;
	}

	public Short getTimeFrame() {
		return timeFrame;
	}

	public void setTimeFrame(Short timeFrame) {
		this.timeFrame = timeFrame;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	} 

}