package grp3022.bbs.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grp3022.bbs.dao.AccountMapper;
import grp3022.bbs.po.Account;
import grp3022.bbs.po.AccountExample;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
    private AccountMapper accountDao;

	@Override
	public Account getById(String id) {
		Account record = accountDao.selectByPrimaryKey(id);
        return record;
	}

	@Override
	public void add(Account record) {
		record.setCreateTime(new Date());
		accountDao.insertSelective(record);
	}

	/*@Override
	public void deleteRecord(Long id) {
		answerDao.deleteByPrimaryKey(id);

	}*/

	@Override
	public void updateById(Account answer) {
		accountDao.updateByPrimaryKeySelective(answer);
	}

	@Override
	public int countByUsername(String username) {
		AccountExample example = new AccountExample();
		example.or().andUsernameEqualTo(username);
		return accountDao.countByExample(example);
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
}
