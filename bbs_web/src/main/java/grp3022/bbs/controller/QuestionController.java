package grp3022.bbs.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;

import grp3022.bbs.aop.UpdateMessage;
import grp3022.bbs.jo.Percentage;
import grp3022.bbs.po.Answer;
import grp3022.bbs.po.AnswerHelpKey;
import grp3022.bbs.po.BBSUser;
import grp3022.bbs.po.Question;
import grp3022.bbs.service.AnswerHelpService;
import grp3022.bbs.service.AnswerService;
import grp3022.bbs.service.BBSUserService;
import grp3022.bbs.service.QuestionService;
import grp3022.bbs.so.AnswerSo;
import grp3022.bbs.so.QuestionSo;
import grp3022.bbs.type.Tag;
import grp3022.bbs.util.Format;
import grp3022.bbs.util.TagUtil;

@Controller
public class QuestionController {

	@Resource
	private QuestionService questionService;
	@Resource
	private AnswerService answerService;
	@Resource
	private AnswerHelpService answerHelpService;
	@Resource
	private BBSUserService userService;
	private final String pathPrefix = "question";

	
	/**
	 * 2017年5月25日 下午3:13:16
	 * @param questionSo
	 * @param pn
	 * @return
	 */
	@RequestMapping(value = "/q")
	@UpdateMessage(description = "问题首页")
	public ModelAndView home(QuestionSo questionSo, Integer pn) {
		if ((questionSo.getKeywords() == null || questionSo.getKeywords().equals(""))
				&& questionSo.getTimeFrame() == null)
			questionSo.setTimeFrame((short) 10);
		PageInfo<Question> pageInfo = questionService.getPageBySo(questionSo, pn, 10);

		/* 初始化标签 */
		Map<String, List<Tag>> tagsMap = new HashMap<String, List<Tag>>();
		for (Question question : pageInfo.getList()) {
			List<Integer> indexes = JSON.parseArray(question.getTags(), Integer.class);
			List<Tag> tags = TagUtil.getTagListByIndex(indexes);
			tagsMap.put(question.getId().toString(), tags);
		}
		/* 初始化选中标签 */
		Tag s_tag = null;
		if (questionSo.getTagIndex() != null && !questionSo.getTagIndex().equals("")) {
			int index = Integer.parseInt(questionSo.getTagIndex());
			for (Tag tag : Tag.values()) {
				if (tag.getIndex() == index) {
					s_tag = tag;
				}
			}
		}

		ModelAndView mav = new ModelAndView(pathPrefix + "/home");
		mav.addObject("pageInfo", pageInfo);
		mav.addObject("tagsMap", tagsMap);
		mav.addObject("s_tag", s_tag);
		mav.addObject("questionSo", questionSo);
		mav.addObject("format", new Format());
		return mav;
	}

	/**
	 * 2017年5月19日 上午9:09:39
	 * 
	 * @param qId问题id
	 * @param answerSo
	 * @return
	 */
	@RequestMapping(value = "/q/{qId}")
	@UpdateMessage(description = "问题界面")
	public String question(@PathVariable Long qId, AnswerSo answerSo,HttpSession session,Model model) {
		/*初始化问题*/
		Question question = questionService.getById(qId);
		/* 初始化问题答案 */
		if (answerSo == null)
			answerSo = new AnswerSo();
		answerSo.setQuestionId(qId);
		List<Answer> answers = answerService.getAllBySo(answerSo);
		
		if(session.getAttribute("userId")!=null){
			long userId = Long.parseLong(session.getAttribute("userId").toString());
			BBSUser user = userService.getById(userId);
			
			/*如果浏览用户为创建者，将reminder字段与答案数量字段同步*/
			if(userId==question.getCreateBy()){
				question.setReminder(question.getAnswers());
			}
			
			/* 初始化是否评价 */
			List<Boolean> helpEnable = new ArrayList<Boolean>();
			for (Answer answer : answers) {
				AnswerHelpKey key = new AnswerHelpKey();
				key.setUserId(userId);
				key.setAnswerId(answer.getId());
				if (answerHelpService.getByKey(key) != null)
					helpEnable.add(false);
				else
					helpEnable.add(true);
			}
			
			model.addAttribute("user", user);
			model.addAttribute("helpEnable", helpEnable);
		}
		
		/* 更新问题信息 */
		question.setViews(question.getViews() + 1);
		questionService.updateById(question);
		
		/* 初始化问题标签 */
		List<Tag> tags = new ArrayList<Tag>();
		List<Integer> indexes = JSON.parseArray(question.getTags(), Integer.class);
		for (int index : indexes) {
			for (Tag tag : Tag.values()) {
				if (tag.getIndex() == index) {
					tags.add(tag);
				}
			}
		}

		model.addAttribute("question", question);
		model.addAttribute("tags", tags);
		model.addAttribute("answers", answers);
		model.addAttribute("order", answerSo.getOrder());
		return "/question/question";
	}

	/**
	 * 2017年5月25日 下午3:13:40
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/q/edit")
	@UpdateMessage(description = "编辑问题")
	public String edit(Model model,HttpSession session) {
		if(session.getAttribute("userId")!=null){
			long userId = Long.parseLong(session.getAttribute("userId").toString());
			BBSUser user = userService.getById(userId);
			model.addAttribute("user", user);
		}
		return "/question/edit";
	}

	/**
	 * 2017年5月13日 下午5:45:40
	 * 
	 * @param question
	 * @return
	 */
	@RequestMapping(value = "/q/add")
	public String add(Question question) {
		try {
			questionService.add(question);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "/question/ask_fail";
		}
		return "/question/ask_success";
	}

	/**
	 * 2017年5月17日 下午7:55:23
	 * 
	 * @param q问题id
	 * @param u解决发起者id
	 * @return
	 */
	@RequestMapping(value = "/q/solved")
	public @ResponseBody String solved(Long q, Long u) {
		try {
			/* 更新问题状态 */
			Question question = questionService.getById(q);
			if (question.getCreateBy() != u)
				return "fail";
			
			/* 统计并更新users */
			this.majorStatistic(q,5,5);
			
			question.setStatus((short) 20);
			question.setUpdateBy(u);
			question.setUpdateTime(new Date());
			questionService.updateById(question);

		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}

	/**
	 * 2017年5月24日 下午4:38:35
	 * @param q 问题id
	 * @param minCnt 答案评优的最低好评数
	 * @param maxSize 最大优质答案个数
	 */
	private void majorStatistic(Long q,int minCnt,int maxSize) {
		/* 初始化问题答案按好评度排序 */
		List<Answer> answers = answerService.getAllBySo(new AnswerSo(q,(short) 10));

		/* 取回答好评率前五并判断其评价数是否大于5,重置好评属性 */
		if (answers.size() > maxSize)
			answers = answers.subList(0, maxSize-1);
		for (Answer answer : answers) {
			if (answer.getHelpful() >= minCnt) {
				/*更新好评字段*/
				answer.setIsAcclaimed((short) 1);
				answerService.updateById(answer);
			}
		}
		/* 统计每位回答者的擅长百分比 */
		for (Answer answer : answers) {
			Long userId = answer.getCreateBy();
			BBSUser user = userService.getById(userId);
			
			/* 获得每位答题者获得好评的問題集合 */
			List<Answer> as = answerService.getAllBySo(new AnswerSo(user.getId(), (short) 1));
			List<Question> questions = new ArrayList<Question>();
			for(Answer a : as){
				questions.add(questionService.getById(a.getQuestionId()));
			}
			/*统计分母*/
			int total = 0;
			for (Question question : questions) {
				List<Integer> indexes = JSON.parseArray(question.getTags(), Integer.class);
				total += indexes.size();
			}
			/*统计百分比*/
			List<Percentage> percentsList = TagUtil.getTagPercentage(questions, total);

			for(Percentage p : percentsList){
				System.out.println(p.getIndex()+":"+p.getPercent());
			}
			System.out.println(JSON.toJSONString(percentsList));
			/*toJSONString储存*/
			user.setqMajor(JSON.toJSONString(percentsList));;
			/*提交*/
			userService.updateById(user);
		}
	}
}
