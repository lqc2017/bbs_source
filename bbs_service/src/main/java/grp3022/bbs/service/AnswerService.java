package grp3022.bbs.service;


import java.util.List;

import grp3022.bbs.po.Answer;
import grp3022.bbs.so.AnswerSo;

public interface AnswerService {
	Answer getById(Long id);
	
	Long add(Answer recodrd);
	
	//void deleteRecord(Long id);
	void updateById(Answer recodrd);
	
	//PageInfo<Answer> getPageBySo(AnswerSo hospitalOrderSo,Integer pageNo,Integer size);
	
	List<Answer> getAllBySo(AnswerSo hospitalOrderSo);
}
