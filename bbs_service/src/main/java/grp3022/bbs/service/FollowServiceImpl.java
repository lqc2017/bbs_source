package grp3022.bbs.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grp3022.bbs.dao.FollowMapper;
import grp3022.bbs.po.Follow;
import grp3022.bbs.po.FollowKey;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
    private FollowMapper followDao;

	@Override
	public Follow getByKey(FollowKey key) {
		Follow record = new Follow();
        record = followDao.selectByPrimaryKey(key);
        return record;
	}

	@Override
	public void add(Follow record) {
		record.setCreateTime(new Date());
		followDao.insertSelective(record);
	}

	/*@Override
	public List<Follow> getAllBySo(FollowSo so) {
		FollowExample example = new FollowExample();
		Criteria c = example.or();
		c.andUserIdEqualTo(so.getUserId());
		example.setOrderByClause("CREATE_TIME DESC");
        return followDao.selectByExample(example);
	}*/

	@Override
	public void deleteByKey(FollowKey key) {
		followDao.deleteByPrimaryKey(key);

	}

	/*@Override
	public void updateRecordById(Answer answer) {
		answerDao.updateByPrimaryKeySelective(answer);
	}*/

	/*@Override
	public PageInfo<Answer> getPageBySo(AnswerSo so, Integer pageNo,Integer size) {
		pageNo = pageNo == null?1:pageNo;
		size = size == null?10:size;
        PageHelper.startPage(pageNo, size);
        //List<Answer> records = answerDao.selectBySo(so);
        AnswerExample example = new AnswerExample();
        example.or().andQuestionIdEqualTo(so.getQuestionId());
        example.setOrderByClause("CREATE_TIME DESC");
        List<Answer> records = answerDao.selectByExample(example);
        PageInfo<Answer> page = new PageInfo<Answer>(records);
        if(page.getPageNum()>page.getPages())
        	page.setPageNum(page.getPages());
        return page;
	}*/
}
