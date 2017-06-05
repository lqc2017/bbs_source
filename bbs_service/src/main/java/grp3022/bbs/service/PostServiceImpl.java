package grp3022.bbs.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import grp3022.bbs.dao.PostMapper;
import grp3022.bbs.po.Post;
import grp3022.bbs.po.PostExample;
import grp3022.bbs.po.PostExample.Criteria;
import grp3022.bbs.so.PostSo;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostMapper postDao;
	
	@Override
	public Long add(Post record) {
		record.setPostTime(new Date());
		record.setUpdateTime(new Date());
		postDao.insertSelective(record);
		return record.getId();
	}
	
	@Override
	public Post getById(Long id) {
		Post record = new Post();
		record = postDao.selectByPrimaryKey(id);
		return record;
	}
	
	@Override
	public void updateById(Post record) {
		postDao.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public List<Post> getAllByPo(PostExample record){
		record.setOrderByClause("POST_TIME DESC");
		List<Post> post = postDao.selectByExample(record);
		return post;
	}
	@Override
	public List<Post> getPostByUserId(long userId){
		PostExample example = new PostExample();
		example.setOrderByClause("UPDATE_TIME DESC");
		Criteria c = example.or();
		c.andPostUserEqualTo(userId);
		List<Post> post = postDao.selectByExample(example);
		return post;
	}
	
	@Override
	public PageInfo<Post> getPageBySo(PostSo so, Integer pageNo, Integer size) {
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
		List<Post> records = postDao.selectBySo(so);
		
		//System.out.println(records.size());
		PageInfo<Post> page = new PageInfo<Post>(records);
		if (page.getPageNum() > page.getPages())
			page.setPageNum(1);
		return page;
	}
	@Override
	public List<Post> getUpdateByCreateBy(Long createBy) {
		if(createBy==null)
			return null;
		return postDao.selectNewByCreateBy(createBy);
	}
}
