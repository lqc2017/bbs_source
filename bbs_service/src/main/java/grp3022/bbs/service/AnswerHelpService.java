package grp3022.bbs.service;



import java.util.List;

import grp3022.bbs.po.AnswerHelp;
import grp3022.bbs.po.AnswerHelpKey;
import grp3022.bbs.so.AnswerHelpSo;


public interface AnswerHelpService {
	AnswerHelp getByKey(AnswerHelpKey key);
	
	void add(AnswerHelp recodrd);

	List<AnswerHelp> getAllBySo(AnswerHelpSo so);
	
	/*void deleteRecord(Long id);
	void updateRecordById(Answer recodrd);*/
	
	//PageInfo<Answer> getPageBySo(AnswerSo hospitalOrderSo,Integer pageNo,Integer size);
	
	//List<AnswerHelp> getAllBySo(AnswerHelpSo hospitalOrderSo);
}
