package grp3022.bbs.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import grp3022.bbs.dao.QuestionMapper;
import grp3022.bbs.po.Question;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
    private QuestionMapper questionDao;

	@Override
	public Question getRecordById(Long id) {
		Question record = new Question();
        record = questionDao.selectByPrimaryKey(id);
        return record;
	}

	@Override
	public Long add(Question record) {
		record.setCreateTime(new Date());
		questionDao.insertSelective(record);
		return record.getId();
	}

	/*@Override
	public void deleteRecord(Long id) {
		questionDao.deleteByPrimaryKey(id);

	}

	@Override
	public void updateRecordById(Question question) {
		questionDao.updateByPrimaryKeySelective(question);
	}

	@Override
	public PageInfo<Question> getPageBySo(QuestionSo so, Integer pageNo,Integer size) {
		pageNo = pageNo == null?1:pageNo;
		size = size == null?10:size;
        PageHelper.startPage(pageNo, size);
        List<Question> records = questionExtDao.selectBySo(so);
        PageInfo<Question> page = new PageInfo<Question>(records);
        if(page.getPageNum()>page.getPages())
        	page.setPageNum(page.getPages());
        return page;
	}*/
}
