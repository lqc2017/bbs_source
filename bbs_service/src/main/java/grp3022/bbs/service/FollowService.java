package grp3022.bbs.service;


import grp3022.bbs.po.Follow;
import grp3022.bbs.po.FollowKey;


public interface FollowService {
	Follow getByKey(FollowKey key);
	
	void add(Follow record);

	/*List<Follow> getAllBySo(FollowSo so);*/
	
	void deleteByKey(FollowKey key);
	/*void updateRecordById(Answer recodrd);*/
	
	//PageInfo<Answer> getPageBySo(AnswerSo hospitalOrderSo,Integer pageNo,Integer size);
	
	//List<Follow> getAllBySo(FollowSo hospitalOrderSo);
}
