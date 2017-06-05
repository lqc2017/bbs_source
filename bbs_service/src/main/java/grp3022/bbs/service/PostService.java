package grp3022.bbs.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import grp3022.bbs.po.Post;
import grp3022.bbs.po.PostExample;
import grp3022.bbs.so.PostSo;

public interface PostService {
	
	Post getById(Long id);
	
	Long add(Post record);
	
	void updateById(Post record);
	
	List<Post> getAllByPo(PostExample record);
	
	List<Post> getPostByUserId(long userId);
	
	PageInfo<Post> getPageBySo(PostSo so,Integer pageNo,Integer size);
	
	List<Post> getUpdateByCreateBy(Long createBy);
}
