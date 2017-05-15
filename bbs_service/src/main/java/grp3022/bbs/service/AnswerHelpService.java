package grp3022.bbs.service;



import grp3022.bbs.po.AnswerHelp;
import grp3022.bbs.po.AnswerHelpKey;


public interface AnswerHelpService {
	AnswerHelp getByKey(AnswerHelpKey key);
	
	void add(AnswerHelp recodrd);
	
	/*void deleteRecord(Long id);
	void updateRecordById(Answer recodrd);*/
	
	//PageInfo<Answer> getPageBySo(AnswerSo hospitalOrderSo,Integer pageNo,Integer size);
	
	//List<AnswerHelp> getAllBySo(AnswerHelpSo hospitalOrderSo);
}
