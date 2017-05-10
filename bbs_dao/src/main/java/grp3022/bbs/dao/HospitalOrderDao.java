package grp3022.bbs.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import grp3022.bbs.po.HospitalOrder;
import grp3022.bbs.po.HospitalOrderExample;

public interface HospitalOrderDao {
    int countByExample(HospitalOrderExample example);

    int deleteByExample(HospitalOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HospitalOrder record);

    int insertSelective(HospitalOrder record);

    List<HospitalOrder> selectByExample(HospitalOrderExample example);

    HospitalOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HospitalOrder record, @Param("example") HospitalOrderExample example);

    int updateByExample(@Param("record") HospitalOrder record, @Param("example") HospitalOrderExample example);

    int updateByPrimaryKeySelective(HospitalOrder record);

    int updateByPrimaryKey(HospitalOrder record);
}