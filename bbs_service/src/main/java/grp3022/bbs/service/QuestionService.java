package grp3022.bbs.service;

import com.github.pagehelper.PageInfo;

import grp3022.bbs.po.Question;
import grp3022.bbs.so.QuestionSo;

public interface QuestionService {
	Question getRecordById(Long id);
	
	Long add(Question recodrd);
	
	/*void deleteRecord(Long id);
	void updateRecordById(Question recodrd);*/
	
	PageInfo<Question> getPageBySo(QuestionSo hospitalOrderSo,Integer pageNo,Integer size);
}
