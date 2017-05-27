package grp3022.bbs.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grp3022.bbs.dao.ReplyMapper;
import grp3022.bbs.po.Reply;
import grp3022.bbs.po.ReplyExample;
import grp3022.bbs.po.ReplyExample.Criteria;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
    private ReplyMapper replyDao;
	
	@Override
	public Reply getById(Long id) {
		Reply record = new Reply();
        record = replyDao.selectByPrimaryKey(id);
        return record;
	}
	
	@Override
	public void updateById(Reply record) {
		System.out.println("aaaaaaaaaaaaaaaaaaa"+record.getScore());
		replyDao.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public Long add(Reply record) {
		
		/*System.out.println(record.getReplyUser());
		System.out.println(record.getName());
		System.out.println(record.getPostId());
		System.out.println(record.getContent());*/
		
		record.setReplyTime(new Date());
		replyDao.insertSelective(record);
		return record.getId();
	}
	
	@Override
	public List<Reply> getAllByPostId(long id){
		ReplyExample example = new ReplyExample();
		example.setOrderByClause("REPLY_TIME ASC");
		Criteria c = example.or();
		c.andPostIdEqualTo(id);
		List<Reply> records = replyDao.selectByExample(example);
        
        return records;
	}
}
