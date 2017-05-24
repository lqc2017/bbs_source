package grp3022.bbs.dao;

import grp3022.bbs.po.Follow;
import grp3022.bbs.po.FollowExample;
import grp3022.bbs.po.FollowKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FollowMapper {
    int countByExample(FollowExample example);

    int deleteByExample(FollowExample example);

    int deleteByPrimaryKey(FollowKey key);

    int insert(Follow record);

    int insertSelective(Follow record);

    List<Follow> selectByExample(FollowExample example);

    Follow selectByPrimaryKey(FollowKey key);

    int updateByExampleSelective(@Param("record") Follow record, @Param("example") FollowExample example);

    int updateByExample(@Param("record") Follow record, @Param("example") FollowExample example);

    int updateByPrimaryKeySelective(Follow record);

    int updateByPrimaryKey(Follow record);
}