package grp3022.bbs.service;

import grp3022.bbs.po.Question;

public interface QuestionService {
	Question getRecordById(Long id);
	/*	增  */
	Long add(Question recodrd);
	/*	删  
	void deleteRecord(Long id);
		改  
	void updateRecordById(Question recodrd);
		查  
	PageInfo<Question> getPageBySo(QuestionSo hospitalOrderSo,Integer pageNo,Integer size);*/
}
