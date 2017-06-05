package grp3022.bbs.service;

import java.util.List;

import grp3022.bbs.po.Reply;

public interface ReplyService {
	
	Reply getById(Long id);
	
	Long add(Reply record);
	
	void updateById(Reply record);
	
	List<Reply> getAllByPostId(long id);
	
	List<Reply> getAllByPo(Reply record);
}
