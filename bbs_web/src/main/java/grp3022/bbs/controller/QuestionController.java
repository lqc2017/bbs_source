package grp3022.bbs.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;

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
import grp3022.bbs.util.Init;

/**
 * @author 全琛
 * @create_time 上午11:07:01
 *
 */
@Controller
@RequestMapping("/question")
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
	 * 2017年5月13日 下午5:22:21
	 * 
	 * @return
	 */
	@RequestMapping(value = "/home")
	public ModelAndView home(QuestionSo questionSo, Integer pn) {
		if ((questionSo.getKeywords() == null || questionSo.getKeywords().equals(""))
				&& questionSo.getTimeFrame() == null)
			questionSo.setTimeFrame((short) 10);
		PageInfo<Question> pageInfo = questionService.getPageBySo(questionSo, pn, 10);

		/* 初始化标签 */
		Map<String, ArrayList<Tag>> tagsMap = new HashMap<String, ArrayList<Tag>>();
		for (Question question : pageInfo.getList()) {
			ArrayList<Tag> tags = new ArrayList<Tag>();
			List<Integer> indexes = JSON.parseArray(question.getTags(), Integer.class);
			for (int index : indexes) {
				for (Tag tag : Tag.values()) {
					if (tag.getIndex() == index) {
						tags.add(tag);
					}
				}
			}
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
	 * @param q问题id
	 * @param answerSo
	 * @return
	 */
	@RequestMapping(value = "")
	public ModelAndView question(Long q, AnswerSo answerSo) {
		long userId = 3;
		BBSUser user = userService.getById(userId);
		/* 初始化问题答案 */
		if (answerSo == null)
			answerSo = new AnswerSo();
		answerSo.setQuestionId(q);
		List<Answer> answers = answerService.getAllBySo(answerSo);
		/* 初始化答案评价 */
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
		/* 更新问题信息 */
		Question question = questionService.getById(q);
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

		ModelAndView mav = new ModelAndView(pathPrefix + "/question");

		mav.addObject("question", question);
		mav.addObject("user", user);
		mav.addObject("tags", tags);
		mav.addObject("answers", answers);
		mav.addObject("answerSo", answerSo);
		mav.addObject("helpEnable", helpEnable);
		return mav;
	}

	/**
	 * 2017年5月13日 下午6:30:10
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(QuestionSo questionSo, Integer pn) {
		PageInfo<Question> pageInfo = questionService.getPageBySo(questionSo, pn, null);
		ModelAndView mav = new ModelAndView(pathPrefix + "/list");
		mav.addObject("pageInfo", pageInfo);
		mav.addObject("so", questionSo);
		mav.addObject("username", "username");
		mav.addObject("format", new Format());
		return mav;
	}

	/**
	 * 2017年5月13日 下午5:22:38
	 * 
	 * @return
	 */
	@RequestMapping(value = "/edit")
	public ModelAndView edit() {
		BBSUser user = userService.getById((long) 2);
		ModelAndView mav = new ModelAndView(pathPrefix + "/edit");
		mav.addObject("user", user);
		return mav;
	}

	/**
	 * 2017年5月13日 下午5:45:40
	 * 
	 * @param question
	 * @return
	 */
	@RequestMapping(value = "/add")
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
	@RequestMapping(value = "/solved")
	public @ResponseBody String solved(Long q, Long u) {
		try {
			/* 更新问题状态 */
			Question question = questionService.getById(q);
			if (question.getCreateBy() != u)
				return "fail";
			question.setStatus((short) 20);
			question.setUpdateBy(u);
			question.setUpdateTime(new Date());
			questionService.updateById(question);
			/* 统计并更新users */
			this.statistic(q);

		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}

	/**
	 * 2017年5月19日 上午9:09:09
	 * 
	 * @param q问题id
	 */
	private void statistic(Long q) {
		/* 初始化问题答案按好评度排序 */
		Question question = questionService.getById(q);
		AnswerSo answerSo = new AnswerSo();
		answerSo.setQuestionId(q);
		answerSo.setOrder((short) 10);
		List<Answer> answers = answerService.getAllBySo(answerSo);

		/* 初始化问题标签集合 */
		List<Tag> tags = Init.InitTagList(question.getTags());

		/* 取回答好评率前五并判断其评价数是否大于5,重置好评属性 */
		if (answers.size() > 5)
			answers = answers.subList(0, 4);
		for (Answer answer : answers) {
			if (answer.getHelpful() >= 5) {
				/*更新好评字段*/
				answer.setIsAcclaimed((short) 1);
				answerService.updateById(answer);

				/* 获得每位答题者获得好评的总回答数量 */
				Long userId = answer.getCreateBy();
				BBSUser user = userService.getById(userId);
				//int total = answerService.countBySo(new AnswerSo((short) 1, user.getId()));
				
				/*更新擅长领域百分比*/
				System.out.println(user.getqMajor());
				Map<String, Float> percents = JSON.parseObject(user.getqMajor(), new TypeReference<Map<String, Float>>(){});
				Map<String, Float> buffer = new HashMap<String,Float>();
				float preSize = percents.size();
				int total = tags.size()+user.getGaCnt();
				
				for(Tag tag:tags){
					String index = tag.getIndex().toString();
					if(percents.containsKey(index)){
						float newValue = (percents.get(index)*user.getGaCnt()+1)/total;
						buffer.put(index,newValue);
						percents.remove(index);
					}else{
						float newValue = (float)1/total;
						buffer.put(tag.getIndex().toString(),newValue);
					}
				}
				for (Map.Entry<String, Float> entry : percents.entrySet()) {
					float newValue = entry.getValue()*preSize/total;
					buffer.put(entry.getKey(),newValue);
				}
				user.setqMajor(JSON.toJSONString(buffer));
				user.setGaCnt(total);
				System.out.println(user.getqMajor());
				/*提交*/
				userService.updateById(user);
			}
		}
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
