package grp3022.bbs.controller;


import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import grp3022.bbs.po.Answer;
import grp3022.bbs.po.AnswerHelp;
import grp3022.bbs.po.AnswerHelpKey;
import grp3022.bbs.po.Question;
import grp3022.bbs.service.AnswerHelpService;
import grp3022.bbs.service.AnswerService;
import grp3022.bbs.service.QuestionService;

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
	private final String pathPrefix = "answer";

	
	/**
	 * 2017年5月13日 下午6:56:35
	 * @param answerId
	 * @return
	 */
	/*@RequestMapping(value = "")
	public String add(Answer answer) {
		try {
			System.out.println(answer.getQuestionId());
			System.out.println(answer.getContent());
			answer.setCreateBy((long)2);
			//answerService.add(answer);
		} catch (Exception e) {
			return "/question/ask_fail";
		}
		return "/question/ask_success";
	}*/
	
	/**
	 * 2017年5月14日 下午6:10:54
	 * @param answer
	 * @return
	 */
	@RequestMapping(value = "")
	public @ResponseBody String add(Answer answer) {
		try {
			long userId = 2;
			/*添加answer*/
			answer.setCreateBy(userId);
			answerService.add(answer);
			/*更新question*/
			Question question = questionService.getById(answer.getQuestionId());
			question.setAnswers(question.getAnswers()+1);
			question.setUpdateBy(userId);
			question.setUpdateTime(new Date());
			questionService.updateById(question);
			
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
			long userId = 2;
			System.out.println("answerId:"+answerId);
			System.out.println("value:"+value);
			
			/*添加帮助记录*/
			AnswerHelp answerHelp = new AnswerHelp();
			answerHelp.setAnswerId(answerId);
			answerHelp.setUserId(userId);
			answerHelp.setIsHelpful(value);
			answerHelpService.add(answerHelp);
			/*更新答案信息*/
			Answer answer = answerService.getById(answerId);
			if(value==1)
				answer.setHelpful(answer.getHelpful()+1);
			else
				answer.setHelpful(answer.getHelpful()-1);
			answerService.updateById(answer);
		} catch (Exception e) {
			return "fail";
		}
		return answerService.getById(answerId).getHelpful().toString();
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
