package grp3022.bbs.service;

import grp3022.bbs.po.ReplyHelp;
import grp3022.bbs.po.ReplyHelpKey;

public interface ReplyHelpService {
	
	ReplyHelp getByKey(ReplyHelpKey key);
	
	void add(ReplyHelp record);
}
