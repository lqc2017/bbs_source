package grp3022.bbs.service;



import java.util.List;

import grp3022.bbs.po.BBSUser;
import grp3022.bbs.po.BBSUserExample;

public interface BBSUserService{

	BBSUser getById(Long id);
	
	Long add(BBSUser recodrd);
	
	//void deleteRecord(Long id);
	void updateById(BBSUser recodrd);
	
	//PageInfo<BBSUser> getPageBySo(BBSUserSo so,Integer pageNo,Integer size);
	
	//List<BBSUser> getAllBySo(BBSUserSo so);
	List<BBSUser> getAllByPo(BBSUserExample example);
}
