package grp3022.bbs.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import grp3022.bbs.po.Answer;
import grp3022.bbs.service.AnswerService;

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
			System.out.println(answer.getQuestionId());
			System.out.println(answer.getContent());
			answer.setCreateBy((long)2);
			answerService.add(answer);
		} catch (Exception e) {
			return "fail";
		}
		return "success";
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
