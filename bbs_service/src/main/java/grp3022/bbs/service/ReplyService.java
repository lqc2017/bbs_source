package grp3022.bbs.service;

import grp3022.bbs.po.Reply;

public interface ReplyService {
	
	Reply getById(Long id);
	
	Long add(Reply record);
}
