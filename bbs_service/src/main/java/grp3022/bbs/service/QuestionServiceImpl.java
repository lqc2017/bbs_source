package grp3022.bbs.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import grp3022.bbs.dao.QuestionMapper;
import grp3022.bbs.po.Question;
import grp3022.bbs.po.QuestionExample;
import grp3022.bbs.po.QuestionExample.Criteria;
import grp3022.bbs.so.QuestionSo;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionMapper questionDao;

	@Override
	public Question getById(Long id) {
		Question record = new Question();
		record = questionDao.selectByPrimaryKey(id);
		return record;
	}

	@Override
	public Long add(Question record) {
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		questionDao.insertSelective(record);
		return record.getId();
	}
	
	@Override
	public int countBySo(QuestionSo so) {
		QuestionExample example = new QuestionExample();
		example.or().andIdIn(so.getIds()).andTagsLike("%"+so.getTagIndex()+"%");
		return questionDao.countByExample(example);
	}

	/*
	 * @Override public void deleteRecord(Long id) {
	 * questionDao.deleteByPrimaryKey(id);
	 * 
	 * }
	 */

	@Override
	public void updateById(Question record) {
		questionDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public PageInfo<Question> getPageBySo(QuestionSo so, Integer pageNo, Integer size) {
		pageNo = pageNo == null ? 1 : pageNo;
		size = size == null ? 10 : size;
		PageHelper.startPage(pageNo, size);
		/*初始化日期范围*/
		if (so.getTimeFrame() != null){
			Calendar cal = Calendar.getInstance();
			if(so.getTimeFrame()==10){
				cal.setTime(new Date()); 
			}else if(so.getTimeFrame()==20){
				cal.set(Calendar.DAY_OF_WEEK, 1);
			}else{
				cal.set(Calendar.DAY_OF_MONTH, 1);
			}
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			so.setStartTime(cal.getTime());
			so.setEndTime(new Date());
		}
		List<Question> records = questionDao.selectBySo(so);
		
		System.out.println(records.size());
		PageInfo<Question> page = new PageInfo<Question>(records);
		if (page.getPageNum() > page.getPages())
			page.setPageNum(1);
		return page;
	}

	@Override
	public List<Long> getIdsBySo(QuestionSo questionSo) {
		QuestionExample example = new QuestionExample();
		Criteria c = example.or();
		c.andTagsLike("%"+questionSo.getTagIndex()+"%");
		List<Question> records = questionDao.selectByExample(example);
		List<Long> ids = new ArrayList<Long>();
		for(Question record: records){
			ids.add(record.getId());
		}
		return ids;
	}
}
