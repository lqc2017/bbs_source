package grp3022.bbs.dao;

import grp3022.bbs.po.Browse;
import grp3022.bbs.po.BrowseExample;
import grp3022.bbs.po.BrowseKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BrowseMapper {
    int countByExample(BrowseExample example);

    int deleteByExample(BrowseExample example);

    int deleteByPrimaryKey(BrowseKey key);

    int insert(Browse record);

    int insertSelective(Browse record);

    List<Browse> selectByExample(BrowseExample example);

    Browse selectByPrimaryKey(BrowseKey key);

    int updateByExampleSelective(@Param("record") Browse record, @Param("example") BrowseExample example);

    int updateByExample(@Param("record") Browse record, @Param("example") BrowseExample example);

    int updateByPrimaryKeySelective(Browse record);

    int updateByPrimaryKey(Browse record);
}