package grp3022.bbs.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import grp3022.bbs.dao.QuestionMapper;
import grp3022.bbs.po.Question;
import grp3022.bbs.po.QuestionExample;
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
		// List<Question> records = questionDao.selectBySo(so);
		QuestionExample example = new QuestionExample();
		if (so.getCreateBy() != null)
			example.or().andCreateByEqualTo(so.getCreateBy());
		if (so.getTagIndex() != null)
			example.or().andTagsLike("%"+so.getTagIndex()+"%");
		example.setOrderByClause("CREATE_TIME DESC");
		List<Question> records = questionDao.selectByExample(example);
		//System.out.println(records.size());
		PageInfo<Question> page = new PageInfo<Question>(records);
		if (page.getPageNum() > page.getPages())
			page.setPageNum(page.getPages());
		return page;
	}
}
