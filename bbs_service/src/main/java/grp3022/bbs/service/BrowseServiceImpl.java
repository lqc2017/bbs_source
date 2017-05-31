package grp3022.bbs.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grp3022.bbs.dao.BrowseMapper;
import grp3022.bbs.po.Browse;
import grp3022.bbs.po.BrowseExample;
import grp3022.bbs.po.BrowseKey;
import grp3022.bbs.po.BrowseExample.Criteria;

@Service
public class BrowseServiceImpl implements BrowseService{
	
	@Autowired
    private BrowseMapper browseDao;
	@Override
	public Browse getByKey(BrowseKey key){
		Browse record = new Browse();
        record = browseDao.selectByPrimaryKey(key);
        return record;
	}
	@Override
	public void updateByKey(Browse record){
		browseDao.updateByPrimaryKeySelective(record);
	}
	@Override
	public void add(Browse record){
		record.setBrowseCount((long) 1);
		record.setBrowseTime(new Date());
		browseDao.insertSelective(record);
	}
	@Override
	public List<Browse> getBrowseByUserId(long userId){
		BrowseExample example = new BrowseExample();
		example.setOrderByClause("BROWSE_TIME DESC");
		Criteria c = example.or();
		c.andUserIdEqualTo(userId);
		List<Browse> browse = browseDao.selectByExample(example);
		return browse;
	}

}
