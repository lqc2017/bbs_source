package grp3022.bbs.dao;

import grp3022.bbs.po.ReplyHelp;
import grp3022.bbs.po.ReplyHelpExample;
import grp3022.bbs.po.ReplyHelpKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReplyHelpMapper {
    int countByExample(ReplyHelpExample example);

    int deleteByExample(ReplyHelpExample example);

    int deleteByPrimaryKey(ReplyHelpKey key);

    int insert(ReplyHelp record);

    int insertSelective(ReplyHelp record);

    List<ReplyHelp> selectByExample(ReplyHelpExample example);

    ReplyHelp selectByPrimaryKey(ReplyHelpKey key);

    int updateByExampleSelective(@Param("record") ReplyHelp record, @Param("example") ReplyHelpExample example);

    int updateByExample(@Param("record") ReplyHelp record, @Param("example") ReplyHelpExample example);

    int updateByPrimaryKeySelective(ReplyHelp record);

    int updateByPrimaryKey(ReplyHelp record);
}