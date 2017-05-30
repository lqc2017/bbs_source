package grp3022.bbs.service;

import java.util.ArrayList;
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
}
