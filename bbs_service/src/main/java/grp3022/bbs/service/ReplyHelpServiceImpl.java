package grp3022.bbs.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grp3022.bbs.dao.ReplyHelpMapper;
import grp3022.bbs.po.ReplyHelp;
import grp3022.bbs.po.ReplyHelpKey;

@Service
public class ReplyHelpServiceImpl implements ReplyHelpService{
	
	@Autowired
    private ReplyHelpMapper replyHelpDao;

	@Override
	public ReplyHelp getByKey(ReplyHelpKey key) {
		ReplyHelp record = new ReplyHelp();
        record = replyHelpDao.selectByPrimaryKey(key);
        return record;
	}

	@Override
	public void add(ReplyHelp record) {
		record.setCreateTime(new Date());
		replyHelpDao.insertSelective(record);
	}
}
