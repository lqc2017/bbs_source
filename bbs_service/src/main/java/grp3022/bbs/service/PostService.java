package grp3022.bbs.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import grp3022.bbs.po.Post;
import grp3022.bbs.po.PostExample;

public interface PostService {
	
	Post getById(Long id);
	
	Long add(Post record);
	
	void updateById(Post record);
	
	List<Post> getAllByPo(PostExample record);
}
