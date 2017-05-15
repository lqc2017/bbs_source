package grp3022.bbs.dao;

import grp3022.bbs.po.AnswerHelp;
import grp3022.bbs.po.AnswerHelpExample;
import grp3022.bbs.po.AnswerHelpKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AnswerHelpMapper {
    int countByExample(AnswerHelpExample example);

    int deleteByExample(AnswerHelpExample example);

    int deleteByPrimaryKey(AnswerHelpKey key);

    int insert(AnswerHelp record);

    int insertSelective(AnswerHelp record);

    List<AnswerHelp> selectByExample(AnswerHelpExample example);

    AnswerHelp selectByPrimaryKey(AnswerHelpKey key);

    int updateByExampleSelective(@Param("record") AnswerHelp record, @Param("example") AnswerHelpExample example);

    int updateByExample(@Param("record") AnswerHelp record, @Param("example") AnswerHelpExample example);

    int updateByPrimaryKeySelective(AnswerHelp record);

    int updateByPrimaryKey(AnswerHelp record);
}