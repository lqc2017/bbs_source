package grp3022.bbs.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grp3022.bbs.dao.AnswerHelpMapper;
import grp3022.bbs.po.AnswerHelp;
import grp3022.bbs.po.AnswerHelpKey;

@Service
public class AnswerHelpServiceImpl implements AnswerHelpService {

	@Autowired
    private AnswerHelpMapper answerHelpDao;

	@Override
	public AnswerHelp getByKey(AnswerHelpKey key) {
		AnswerHelp record = new AnswerHelp();
        record = answerHelpDao.selectByPrimaryKey(key);
        return record;
	}

	@Override
	public void add(AnswerHelp record) {
		record.setCreateTime(new Date());
		answerHelpDao.insertSelective(record);
	}

	/*@Override
	public void deleteRecord(Long id) {
		answerDao.deleteByPrimaryKey(id);

	}

	@Override
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
