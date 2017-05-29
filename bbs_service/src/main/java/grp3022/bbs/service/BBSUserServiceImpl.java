package grp3022.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grp3022.bbs.dao.BBSUserMapper;
import grp3022.bbs.po.BBSUser;
import grp3022.bbs.po.BBSUserExample;

@Service
public class BBSUserServiceImpl implements BBSUserService {

	@Autowired
    private BBSUserMapper bbsUserDao;

	@Override
	public BBSUser getById(Long id) {
		BBSUser record = bbsUserDao.selectByPrimaryKey(id);
        return record;
	}

	/*@Override
	public void add(BBSUser record) {
		record.setCreateTime(new Date());
		bbsUserDao.insertSelective(record);
	}*/

	/*@Override
	public void deleteRecord(Long id) {
		answerDao.deleteByPrimaryKey(id);

	}*/

	@Override
	public void updateById(BBSUser answer) {
		bbsUserDao.updateByPrimaryKeySelective(answer);
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
	
	@Override
	public List<BBSUser> getAllByPo(BBSUserExample example){
		example.setOrderByClause("SCORE DESC");
		List<BBSUser> records = bbsUserDao.selectByExample(example);
		return records;
	}
}
