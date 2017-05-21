package grp3022.bbs.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grp3022.bbs.dao.AnswerMapper;
import grp3022.bbs.po.Answer;
import grp3022.bbs.po.AnswerExample;
import grp3022.bbs.po.AnswerExample.Criteria;
import grp3022.bbs.so.AnswerSo;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
    private AnswerMapper answerDao;

	@Override
	public Answer getById(Long id) {
		Answer record = new Answer();
        record = answerDao.selectByPrimaryKey(id);
        return record;
	}
	
	@Override
	public int countBySo(AnswerSo so) {
		AnswerExample example = new AnswerExample();
        Criteria c = example.or();
        if(so.getCreateBy()!=null)
        	c.andCreateByEqualTo(so.getCreateBy());
        if(so.getQuestionId()!=null)
        	c.andQuestionIdEqualTo(so.getQuestionId());
        if(so.getIsAcclaimed()!=null)
        	c.andIsAcclaimedEqualTo((short)1);
        if(so.getQuestionIds()!=null)
        	c.andQuestionIdIn(so.getQuestionIds());
        return answerDao.countByExample(example);
	}

	@Override
	public Long add(Answer record) {
		record.setCreateTime(new Date());
		answerDao.insertSelective(record);
		return record.getId();
	}

	@Override
	public void updateById(Answer answer) {
		answerDao.updateByPrimaryKeySelective(answer);
	}

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
	
	public List<Answer> getAllBySo(AnswerSo so) {
        AnswerExample example = new AnswerExample();
        Criteria c = example.or();
        if(so.getIsAcclaimed()!=null){
        	c.andIsAcclaimedEqualTo(so.getIsAcclaimed());
        }
        if(so.getQuestionId()!=null){
        	c.andQuestionIdEqualTo(so.getQuestionId());
        }
        if(so.getCreateBy()!=null){
        	c.andCreateByEqualTo(so.getCreateBy());
        }
        if(so.getOrder()==null||so.getOrder()==0)
        	example.setOrderByClause("CREATE_TIME DESC");
        else if(so.getOrder()==10)
        	example.setOrderByClause("HELPFUL DESC,CREATE_TIME");
        List<Answer> records = answerDao.selectByExample(example);
        
        return records;
	}

	@Override
	public List<Long> getQIdsByCreateBy(Long createBy) {
		if(createBy==null)
			return null;
		List<Long> qIds = answerDao.selectQIds(createBy);
		return qIds;
	}
}
