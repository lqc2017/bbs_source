package grp3022.bbs.controller;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import grp3022.bbs.aop.UpdateMessage;
import grp3022.bbs.jo.Percentage;
import grp3022.bbs.po.Answer;
import grp3022.bbs.po.AnswerHelp;
import grp3022.bbs.po.BBSUser;
import grp3022.bbs.po.FollowKey;
import grp3022.bbs.po.Question;
import grp3022.bbs.service.AnswerHelpService;
import grp3022.bbs.service.AnswerService;
import grp3022.bbs.service.BBSUserService;
import grp3022.bbs.service.FollowService;
import grp3022.bbs.service.QuestionService;
import grp3022.bbs.so.AnswerHelpSo;
import grp3022.bbs.so.AnswerSo;
import grp3022.bbs.so.QuestionSo;
import grp3022.bbs.type.Tag;
import grp3022.bbs.util.TagUtil;
import grp3022.bbs.wo.Activity;

/**
 * @author 全琛
 * @create_time 上午11:07:01
 *
 */
@Controller
public class UserController {

	@Resource
	private BBSUserService userService;
	@Resource
	private QuestionService questionService;
	@Resource
	private AnswerService answerService;
	@Resource
	private AnswerHelpService answerHelpService;
	@Resource
	private FollowService followService;
	
	/**
	 * 2017年5月20日 下午2:59:37
	 * @return
	 */
	@RequestMapping(value = "/u/{userId}")
	@UpdateMessage(description = "更新消息")
	public String home(Model model,HttpSession session,@PathVariable Long userId) {
		if(session.getAttribute("userId")!=null){
			//处理当前登陆情况判断
			long currentUserId = Long.parseLong(session.getAttribute("userId").toString());
			BBSUser currentUser =  userService.getById(currentUserId);
			model.addAttribute("currentUser", currentUser);
			
			//处理关注
			if(userId!=currentUser.getId()){
				FollowKey key = new FollowKey();
				key.setUserId(userId);
				key.setFollowerId(currentUser.getId());
				model.addAttribute("followed",followService.getByKey(key)!=null?true:false);
			}
		}
		BBSUser user = userService.getById(userId);
		this.loadFolow(user,model);
		this.loadData(user,model);
		this.loadActivity(user,model);
		model.addAttribute("user",user);
		
		return "profile";
	}
	
	/**
	 * 2017年5月24日 下午9:28:25
	 * 加载关注情况
	 * @param user
	 * @param model
	 */
	private void loadFolow(BBSUser user, Model model) {
		List<Integer> followList = JSON.parseArray(user.getFollow(), Integer.class);
		model.addAttribute("followList",followList);
	}

	/**
	 * 2017年5月22日 下午4:35:59
	 * 加载问题相关数据
	 * @param user
	 * @param model
	 */
	private void loadData(BBSUser user,Model model){
		String majorStr = user.getqMajor();
		List<Percentage> majars = JSON.parseArray(majorStr,Percentage.class);
		
		String participateStr = user.getqParticipate();
		List<Percentage> participates = JSON.parseArray(participateStr,Percentage.class);
		
		Map<Integer, Tag> tagMap = TagUtil.getTagMap();
		
		model.addAttribute("majars", majars);
		model.addAttribute("participates", participates);
		model.addAttribute("tagMap", tagMap);
	}
	
	/**
	 * 2017年5月22日 下午4:35:55
	 * 加载动态
	 * @param user
	 * @param model
	 */
	private void loadActivity(BBSUser user,Model model){
		//设置显示的条目数
		int itemSize = 10; 
		//读用户配置
		
		//按照点赞，发起问题，回答问题的设置加载数据
		// 格式1：时间 xxx提出了问题：url(/question?q)
		// 格式2：时间 xxx回答了xxx提出的url(/question?q)
		// 格式3：时间 xxx对xxx在url(/question?q)上给予了认同/提出了否定
		List<Activity> activities = new ArrayList<Activity>();
		if (true) {
			List<Question> questions = questionService.getPageBySo(new QuestionSo(user.getId()), 1, itemSize).getList();
			for(Question q : questions){
				activities.add(new Activity(q.getCreateTime(),(short)10,q));
			}
		}
		if (true) {
			List<Answer> answers = answerService.getAllBySo(new AnswerSo(user.getId()));
			if(answers.size()>itemSize)
				answers = answers.subList(0, itemSize-1);
			for(Answer a : answers){
				Question q = questionService.getById(a.getQuestionId());
				BBSUser u = userService.getById(q.getCreateBy());
				activities.add(new Activity(a.getCreateTime(),(short)20,q,u));
			}
		}
		if (true) {
			List<AnswerHelp> answerHelps = answerHelpService.getAllBySo(new AnswerHelpSo(user.getId()));
			if(answerHelps.size()>itemSize)
				answerHelps = answerHelps.subList(0, itemSize);
			for(AnswerHelp ah : answerHelps){
				Long qId = answerService.getById(ah.getAnswerId()).getQuestionId();
				Question q = questionService.getById(qId);
				BBSUser u = userService.getById(ah.getUserId());
				activities.add(new Activity(ah.getCreateTime(),(short)30,q,u,ah.getIsHelpful()));
			}
		}
		//将包装类按时间排序
		Collections.sort(activities, new Comparator<Activity>() {
			@Override
			public int compare(Activity arg0, Activity arg1) {
				return arg1.getCreateTime().compareTo(arg0.getCreateTime());
			}   
		});
		if(activities.size()>itemSize)
			activities = activities.subList(0, itemSize-1);
		
		model.addAttribute("activities", activities);
	}
}
