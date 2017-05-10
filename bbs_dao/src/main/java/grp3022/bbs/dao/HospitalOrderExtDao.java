package grp3022.bbs.dao;

import java.util.List;

import grp3022.bbs.po.HospitalOrder;
import grp3022.bbs.so.HospitalOrderSo;


public interface HospitalOrderExtDao {
	List<HospitalOrder> selectBySo(HospitalOrderSo hospitalOrderSo);
	int add(HospitalOrder record);
}
