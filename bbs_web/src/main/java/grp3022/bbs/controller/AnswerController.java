package grp3022.bbs.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import grp3022.bbs.jo.Percentage;
import grp3022.bbs.po.Answer;
import grp3022.bbs.po.AnswerHelp;
import grp3022.bbs.po.BBSUser;
import grp3022.bbs.po.Question;
import grp3022.bbs.service.AnswerHelpService;
import grp3022.bbs.service.AnswerService;
import grp3022.bbs.service.BBSUserService;
import grp3022.bbs.service.QuestionService;
import grp3022.bbs.so.AnswerSo;
import grp3022.bbs.util.TagUtil;

/**
 * @author 全琛
 * @create_time 上午11:07:01
 *
 */
@Controller
@RequestMapping("/answer")
public class AnswerController {

	@Resource
	private AnswerService answerService;
	@Resource
	private AnswerHelpService answerHelpService;
	@Resource
	private QuestionService questionService;
	@Resource
	private BBSUserService userService;

	
	/**
	 * 2017年5月14日 下午6:10:54
	 * @param answer
	 * @return
	 */
	@RequestMapping(value = "")
	public @ResponseBody String add(Answer answer) {
		try {
			long userId = 3;
			BBSUser user = userService.getById(userId);
			
			
			/*添加answer*/
			answer.setCreateBy(userId);
			answerService.add(answer);
			
			/*更新question*/
			Question question = questionService.getById(answer.getQuestionId());
			question.setAnswers(question.getAnswers()+1);
			question.setUpdateBy(user.getId());
			question.setUpdateTime(new Date());
			questionService.updateById(question);
			
			/*如果是第一次回答问题，统计并更新user*/
			if(answerService.countBySo(new AnswerSo(user.getId(),answer.getQuestionId()))==1)
				//this.statistic(user,answer.getQuestionId());
				this.statistic(user);
			
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
	
	/**
	 * 2017年5月15日 下午5:43:47
	 * @param answer
	 * @return
	 */
	@RequestMapping(value = "answer_help")
	public @ResponseBody String answerHelp(@RequestParam(value = "a")Long answerId,@RequestParam(value = "value")Short value) {
		try {
			long userId = 3;
			
			/*添加帮助记录*/
			AnswerHelp answerHelp = new AnswerHelp();
			answerHelp.setAnswerId(answerId);
			answerHelp.setUserId(userId);
			answerHelp.setIsHelpful(value);
			answerHelpService.add(answerHelp);
			/*更新答案信息*/
			Answer answer = answerService.getById(answerId);
			if(value==1)
				answer.setHelpful(answer.getHelpful()+5);
			else
				answer.setHelpful(answer.getHelpful()-1);
			answerService.updateById(answer);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "fail";
		}
		return answerService.getById(answerId).getHelpful().toString();
	}
	
	
	/**
	 * 2017年5月19日 下午3:35:48
	 * @param user
	 * @param q 问题id
	 */
	/*private void statistic(BBSUser user,Long q){
		初始化问题
		Question question = questionService.getById(q);
		初始化问题标签集合
		List<Tag> tags = Init.InitTagList(question.getTags());
		获得回答的总条目数
		List<Long> qIds = answerService.getQIdsByCreateBy(user.getId());
		float preSize = user.getAqCnt();
		user.setAqCnt(qIds.size());
		QuestionSo questionSo = new QuestionSo();
		
		更新标签参与度百分比，先更新问题标签中的百分比，再更新Map中其他标签的百分比，时间复杂度小于O(n^2)
		System.out.println(user.getqParticipate());
		Map<String, Float> percents = JSON.parseObject(user.getqParticipate(), new TypeReference<Map<String, Float>>(){});
		Map<String, Float> buffer = new HashMap<String,Float>();
		for(Tag tag:tags){
			String index = tag.getIndex().toString();
			if(percents.containsKey(index)){
				percents.remove(index);
			}
			questionSo.setTagIndex(index);
			questionSo.setIds(qIds);
			int cnt=questionService.countBySo(questionSo);
			float p = (float)cnt/user.getAqCnt()*100;
			buffer.put(index,(float)(Math.round(p)/100.0));
		}
		for (Map.Entry<String, Float> entry : percents.entrySet()) {
			float newValue = entry.getValue()*preSize/user.getAqCnt();
			buffer.put(entry.getKey(),(float)(Math.round(newValue*100)/100.0));
		}
		user.setqParticipate(JSON.toJSONString(buffer));
		System.out.println(user.getqParticipate());
		提交
		userService.updateById(user);
	}*/
	private void statistic(BBSUser user){
		/*（查询）获得回答的过的问题id集合*/
		List<Long> qIds = answerService.getQIdsByCreateBy(user.getId());
		user.setAqCnt(qIds.size());
		/*（查询*n）获得回答过的問題集合*/
		List<Question> questions = new ArrayList<Question>();
		for(Long id : qIds){
			questions.add(questionService.getById(id));
		}
		/*统计百分比*/
		List<Percentage> percentsList = TagUtil.getTagPercentage(questions, user.getAqCnt());
		
		for(Percentage p : percentsList){
			System.out.println(p.getIndex()+":"+p.getPercent());
		}
		
		/*toJSONString储存*/
		user.setqParticipate(JSON.toJSONString(percentsList));
		/*提交*/
		userService.updateById(user);
	}
	
	/*
	 * private String getPrincipal(){ String userName = null; Object principal =
	 * SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 * 
	 * if (principal instanceof UserDetails) { userName =
	 * ((UserDetails)principal).getUsername(); } else { userName =
	 * principal.toString(); } return userName; }
	 */
}
