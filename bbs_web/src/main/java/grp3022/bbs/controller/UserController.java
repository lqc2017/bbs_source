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
import grp3022.bbs.jo.AddressInfo;
import grp3022.bbs.jo.ContactInfo;
import grp3022.bbs.jo.Percentage;
import grp3022.bbs.jo.UserSetting;
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
import grp3022.bbs.util.Format;
import grp3022.bbs.util.TagUtil;
import grp3022.bbs.wo.Activity;
import grp3022.bbs.wo.UnreadMessage;

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
	 * 
	 * @return
	 */
	@RequestMapping(value = "/u/{userId}")
	@UpdateMessage(description = "更新消息")
	public String home(Model model, HttpSession session, @PathVariable Long userId,Short active) {
		if (session.getAttribute("userId") != null) {
			// 处理当前登陆情况判断
			long currentUserId = Long.parseLong(session.getAttribute("userId").toString());
			BBSUser currentUser = userService.getById(currentUserId);

			// 加载关注情况
			if (userId != currentUser.getId()) {
				FollowKey key = new FollowKey();
				key.setUserId(userId);
				key.setFollowerId(currentUser.getId());
				model.addAttribute("followed", followService.getByKey(key) != null ? true : false);
			}

			// 加载未读消息
			this.loadUnreadMessage(currentUser, model);
		}
		BBSUser user = userService.getById(userId);
		this.loadInfomation(user, model);
		this.loadFolow(user, model);
		this.loadData(user, model);
		this.loadActivity(user, model);
		this.loadSetting(user, model);
		model.addAttribute("user", user);
		model.addAttribute("active", active);
		model.addAttribute("format", new Format());

		return "profile";
	}

	/**
	 * 2017年5月28日 下午4:14:45
	 * 
	 * @param user
	 * @param contactInfo
	 * @param addressInfo
	 * @param userSetting
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/u/update")
	public String update(BBSUser user, ContactInfo contactInfo, AddressInfo addressInfo, UserSetting userSetting,
			HttpSession session) {
		try {
			long currentUserId = Long.parseLong(session.getAttribute("userId").toString());
			user.setId(currentUserId);
			if (contactInfo.getEmail() != null && contactInfo.getMobilephone() != null && contactInfo.getQq() != null) {
				System.out.println(JSON.toJSONString(contactInfo));
				user.setiContact(JSON.toJSONString(contactInfo));
			}
			if (addressInfo.getSchool() != null && addressInfo.getCompany() != null && addressInfo.getHome() != null) {
				System.out.println(JSON.toJSONString(addressInfo));
				user.setiAddress(JSON.toJSONString(addressInfo));
			}
			if (userSetting.getShowInfo() != null && userSetting.getShowActivity() != null
					&& userSetting.getMessageRemind() != null) {
				System.out.println(JSON.toJSONString(userSetting));
				user.setSetting(JSON.toJSONString(userSetting));
			}
			userService.updateById(user);
			return "redirect:/u/" + user.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "/question/ask_fail";
		}
	}

	/**
	 * 2017年5月24日 下午9:28:25 加载关注情况
	 * 
	 * @param user
	 * @param model
	 */
	private void loadFolow(BBSUser user, Model model) {
		List<Integer> followList = JSON.parseArray(user.getFollow(), Integer.class);
		model.addAttribute("followList", followList);
	}

	/**
	 * 2017年5月22日 下午4:35:59 加载问题相关数据
	 * 
	 * @param user
	 * @param model
	 */
	private void loadData(BBSUser user, Model model) {
		String majorStr = user.getqMajor();
		List<Percentage> majars = JSON.parseArray(majorStr, Percentage.class);

		String participateStr = user.getqParticipate();
		List<Percentage> participates = JSON.parseArray(participateStr, Percentage.class);

		Map<Integer, Tag> tagMap = TagUtil.getTagMap();

		model.addAttribute("majars", majars);
		model.addAttribute("participates", participates);
		model.addAttribute("tagMap", tagMap);
	}

	/**
	 * 2017年5月22日 下午4:35:55 加载动态
	 * 
	 * @param user
	 * @param model
	 */
	private void loadActivity(BBSUser user, Model model) {
		// 设置显示的条目数
		int itemSize = 10;
		// 读用户配置

		// 按照点赞，发起问题，回答问题的设置加载数据
		// 格式1：时间 xxx提出了问题：url(/question?q)
		// 格式2：时间 xxx回答了xxx提出的url(/question?q)
		// 格式3：时间 xxx对xxx在url(/question?q)上给予了认同/提出了否定
		List<Activity> activities = new ArrayList<Activity>();
		if (true) {
			List<Question> questions = questionService.getPageBySo(new QuestionSo(user.getId()), 1, itemSize).getList();
			for (Question q : questions) {
				activities.add(new Activity(q.getCreateTime(), (short) 10, q));
			}
		}
		if (true) {
			List<Answer> answers = answerService.getAllBySo(new AnswerSo(user.getId()));
			if (answers.size() > itemSize)
				answers = answers.subList(0, itemSize - 1);
			for (Answer a : answers) {
				Question q = questionService.getById(a.getQuestionId());
				BBSUser u = userService.getById(q.getCreateBy());
				activities.add(new Activity(a.getCreateTime(), (short) 20, q, u));
			}
		}
		if (true) {
			List<AnswerHelp> answerHelps = answerHelpService.getAllBySo(new AnswerHelpSo(user.getId()));
			if (answerHelps.size() > itemSize)
				answerHelps = answerHelps.subList(0, itemSize);
			for (AnswerHelp ah : answerHelps) {
				Long qId = answerService.getById(ah.getAnswerId()).getQuestionId();
				Question q = questionService.getById(qId);
				BBSUser u = userService.getById(ah.getUserId());
				activities.add(new Activity(ah.getCreateTime(), (short) 30, q, u, ah.getIsHelpful()));
			}
		}
		// 将包装类按时间排序
		Collections.sort(activities, new Comparator<Activity>() {
			@Override
			public int compare(Activity arg0, Activity arg1) {
				return arg1.getCreateTime().compareTo(arg0.getCreateTime());
			}
		});
		if (activities.size() > itemSize)
			activities = activities.subList(0, itemSize - 1);

		model.addAttribute("activities", activities);
	}

	/**
	 * 2017年5月28日 下午4:18:20
	 * 
	 * @param user
	 * @param model
	 */
	private void loadInfomation(BBSUser user, Model model) {
		String contactStr = user.getiContact();
		ContactInfo contactInfo = JSON.parseObject(contactStr, ContactInfo.class);
		model.addAttribute("contactInfo", contactInfo);

		String addressStr = user.getiAddress();
		AddressInfo addressInfo = JSON.parseObject(addressStr, AddressInfo.class);
		model.addAttribute("addressInfo", addressInfo);
	}

	/**
	 * 2017年5月28日 下午7:44:35
	 * 
	 * @param user
	 * @param model
	 */
	private void loadSetting(BBSUser user, Model model) {
		String settingStr = user.getSetting();
		UserSetting userSetting = JSON.parseObject(settingStr, UserSetting.class);
		model.addAttribute("userSetting", userSetting);
	}

	/**
	 * 2017年5月28日 下午7:44:45
	 * 
	 * @param currentUser
	 * @param model
	 */
	private void loadUnreadMessage(BBSUser user, Model model) {
		// 获得未读的已更新的用户问题
		List<Question> questions = questionService.getUpdateByCreateBy(user.getId());
		
		List<UnreadMessage> unreadMessages = new ArrayList<UnreadMessage>();
		for (Question q : questions) {
			int cnt = q.getAnswers() - q.getReminder();
			AnswerSo answerSo = new AnswerSo();
			answerSo.setQuestionId(q.getId());
			// 获得未读的已更新的用户问题下未读的答案
			List<Answer> unReadAnswers = answerService.getAllBySo(answerSo).subList(0, cnt);

			// 包装答案
			for (Answer a : unReadAnswers) {
				BBSUser u = userService.getById(a.getCreateBy());
				UnreadMessage um = new UnreadMessage(a.getCreateTime(), (short) 10, q, u);
				unreadMessages.add(um);
			}
		}
		// 将包装类按时间排序
		Collections.sort(unreadMessages, new Comparator<UnreadMessage>() {
			@Override
			public int compare(UnreadMessage arg0, UnreadMessage arg1) {
				return arg1.getCreateTime().compareTo(arg0.getCreateTime());
			}
		});
		model.addAttribute("unreadMessages", unreadMessages);
	}
}
