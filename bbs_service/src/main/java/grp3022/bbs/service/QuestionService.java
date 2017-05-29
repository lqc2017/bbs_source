package grp3022.bbs.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import grp3022.bbs.po.Question;
import grp3022.bbs.so.QuestionSo;

public interface QuestionService {
	Question getById(Long id);
	
	Long add(Question recodrd);
	
	int countBySo(QuestionSo so);
	
	//void deleteRecord(Long id);
	void updateById(Question recodrd);
	
	PageInfo<Question> getPageBySo(QuestionSo so,Integer pageNo,Integer size);

	List<Question> getAllBySo(QuestionSo questionSo);

	List<Question> getUpdateByCreateBy(Long createBy);
}
