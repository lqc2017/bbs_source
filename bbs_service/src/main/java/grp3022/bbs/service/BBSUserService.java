package grp3022.bbs.service;



import grp3022.bbs.po.BBSUser;
public interface BBSUserService{

	BBSUser getById(Long id);
	
	Long add(BBSUser recodrd);
	
	//void deleteRecord(Long id);
	void updateById(BBSUser recodrd);
	
	//PageInfo<BBSUser> getPageBySo(BBSUserSo so,Integer pageNo,Integer size);
	
	//List<BBSUser> getAllBySo(BBSUserSo so);
}
