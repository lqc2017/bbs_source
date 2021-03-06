package grp3022.bbs.dao;

import grp3022.bbs.po.Question;
import grp3022.bbs.po.QuestionExample;
import grp3022.bbs.so.QuestionSo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuestionMapper {
    int countByExample(QuestionExample example);

    int deleteByExample(QuestionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Question record);

    int insertSelective(Question record);

    List<Question> selectByExample(QuestionExample example);

    Question selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Question record, @Param("example") QuestionExample example);

    int updateByExample(@Param("record") Question record, @Param("example") QuestionExample example);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);
    
    List<Question> selectBySo(QuestionSo so);

    /*获得创建人未读的已更新的问题*/
	List<Question> selectNewByCreateBy(Long createBy);
}