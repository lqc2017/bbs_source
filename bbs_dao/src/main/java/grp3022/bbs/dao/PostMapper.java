package grp3022.bbs.dao;

import grp3022.bbs.po.Post;
import grp3022.bbs.po.PostExample;
import grp3022.bbs.so.PostSo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PostMapper {
    int countByExample(PostExample example);

    int deleteByExample(PostExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Post record);

    int insertSelective(Post record);

    List<Post> selectByExample(PostExample example);

    Post selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Post record, @Param("example") PostExample example);

    int updateByExample(@Param("record") Post record, @Param("example") PostExample example);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);
    
    List<Post> selectBySo(PostSo so);
    
    /*获得创建人未读的已更新的帖子*/
	List<Post> selectNewByCreateBy(Long postUser);
}