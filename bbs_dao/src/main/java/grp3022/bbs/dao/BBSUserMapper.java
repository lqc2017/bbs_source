package grp3022.bbs.dao;

import grp3022.bbs.po.BBSUser;
import grp3022.bbs.po.BBSUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BBSUserMapper {
    int countByExample(BBSUserExample example);

    int deleteByExample(BBSUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BBSUser record);

    Long insertSelective(BBSUser record);

    List<BBSUser> selectByExample(BBSUserExample example);

    BBSUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BBSUser record, @Param("example") BBSUserExample example);

    int updateByExample(@Param("record") BBSUser record, @Param("example") BBSUserExample example);

    int updateByPrimaryKeySelective(BBSUser record);

    int updateByPrimaryKey(BBSUser record);
}