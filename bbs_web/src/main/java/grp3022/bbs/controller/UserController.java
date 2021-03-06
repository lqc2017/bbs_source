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
import com.github.pagehelper.PageInfo;

import grp3022.bbs.aop.UpdateMessage;
import grp3022.bbs.jo.AddressInfo;
import grp3022.bbs.jo.ContactInfo;
import grp3022.bbs.jo.Percentage;
import grp3022.bbs.jo.UserSetting;
import grp3022.bbs.po.Answer;
import grp3022.bbs.po.AnswerHelp;
import grp3022.bbs.po.BBSUser;
import grp3022.bbs.po.Browse;
import grp3022.bbs.po.FollowKey;
import grp3022.bbs.po.Post;
import grp3022.bbs.po.Question;
import grp3022.bbs.po.Reply;
import grp3022.bbs.service.AnswerHelpService;
import grp3022.bbs.service.AnswerService;
import grp3022.bbs.service.BBSUserService;
import grp3022.bbs.service.BrowseService;
import grp3022.bbs.service.FollowService;
import grp3022.bbs.service.PostService;
import grp3022.bbs.service.QuestionService;
import grp3022.bbs.service.ReplyService;
import grp3022.bbs.so.AnswerHelpSo;
import grp3022.bbs.so.AnswerSo;
import grp3022.bbs.so.PostSo;
import grp3022.bbs.so.QuestionSo;
import grp3022.bbs.type.Tag;
import grp3022.bbs.util.Format;
import grp3022.bbs.util.TagUtil;
import grp3022.bbs.wo.Activity;
import grp3022.bbs.wo.UnreadMessage;
import grp3022.bbs.wo.UnreadReply;

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
	@Resource
	private PostService postService;
	@Resource
	private BrowseService browseService;
	@Resource
	private ReplyService replyService;

	/**
	 * 2017年6月3日 下午9:01:09
	 * @param model
	 * @param session
	 * @param userId
	 * @param active
	 * @return
	 */
	@RequestMapping(value = "/u/{userId}")
	@UpdateMessage(description = "用户主页")
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
		}else{
			active = 10;
		}
		BBSUser user = userService.getById(userId);
		this.loadInfomation(user, model);
		this.loadFolow(user, model);
		this.loadData(user, model);
		this.loadActivity(user, model);
		this.loadSetting(user, model);
		this.loadPostInformation(user, model);
		model.addAttribute("user", user);
		model.addAttribute("active", active);
		model.addAttribute("format", new Format());

		return "profile";
	}
	
	/**
	 * 2017年6月2日 下午7:23:30
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/u/message")
	@UpdateMessage(description = "消息页面")
	public String message(Model model, HttpSession session) {
		if (session.getAttribute("userId") != null) {
		long currentUserId = Long.parseLong(session.getAttribute("userId").toString());
		BBSUser currentUser = userService.getById(currentUserId);
		this.loadUnreadMessage(currentUser, model);
		
		model.addAttribute("format", new Format());
		return "message";
		}else{
			return "redirect:/bulletin/home";
		}
	}
	
	/**
	 * 2017年6月2日 下午7:39:39
	 * @param model
	 * @param pn
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/myq")
	@UpdateMessage(description = "我的问题")
	public String myQuestion(Model model, Integer pn, QuestionSo questionSo, HttpSession session) {
		if (session.getAttribute("userId") != null) {
			long currentUserId = Long.parseLong(session.getAttribute("userId").toString());
			if (questionSo.getCreateBy() == null)
				questionSo.setCreateBy(currentUserId);
			PageInfo<Question> pageInfo = questionService.getPageBySo(questionSo, pn, 10);

			// 加载最后回复人
			List<BBSUser> users = new ArrayList<BBSUser>();
			for (Question q : pageInfo.getList()) {
				users.add(userService.getById(q.getUpdateBy()));
			}

			model.addAttribute("pageInfo", pageInfo);
			model.addAttribute("so", questionSo);
			model.addAttribute("users", users);
			model.addAttribute("format", new Format());
			return "my_question";
		} else {
			return "redirect:/q";
		}
	}
	
	@RequestMapping(value = "/u/post")
	@UpdateMessage(description = "我的帖子")
	public String myPost(Model model, Integer pn, PostSo postSo, HttpSession session) {
		if (session.getAttribute("userId") != null) {
			long currentUserId = Long.parseLong(session.getAttribute("userId").toString());
			if (postSo.getPostUser() == null)
				postSo.setPostUser(currentUserId);
			PageInfo<Post> pageInfo = postService.getPageBySo(postSo, pn, 10);
			model.addAttribute("pageInfo", pageInfo);
			model.addAttribute("so", postSo);
			model.addAttribute("format", new Format());
			return "my_post";
		} else {
			return "redirect:/bulletin/home";
		}
	}
	/*---------------------------以上返回视图---------------------------*/
	
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
			return "redirect:/u/" + user.getId();
		}
	}
	
	/*---------------------------以上重定向---------------------------*/

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
		
		//答复总数
		int answerCnt = answerService.countBySo(new AnswerSo(user.getId()));
		model.addAttribute("answerCnt", answerCnt);
		//获得好评的答案个数
		int goodAnswerCnt = answerService.countBySo(new AnswerSo(user.getId(),(short)1));
		model.addAttribute("goodAnswerCnt", goodAnswerCnt);
		//提过的问题总数
		int questionCnt = questionService.countBySo(new QuestionSo(user.getId()));
		model.addAttribute("questionCnt", questionCnt);
		//被点过赞的次数
		int helpfulCnt = answerHelpService.countBySo(new AnswerHelpSo(user.getId(),(short)1));
		model.addAttribute("helpfulCnt", helpfulCnt);

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
				BBSUser u = userService.getById(ah.getCreateBy());
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
	 * 2017年5月28日 下午4:18:20 加载个人信息
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
	 * 2017年5月28日 下午7:44:35 加载个人设置
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
			List<Answer> unReadAnswers = answerService.getAllBySo(answerSo).subList(0, cnt-1);

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
		// 获得未读的已更新的用户帖子
		List<Post> posts = postService.getUpdateByCreateBy(user.getId());

		List<UnreadReply> unreadReplys = new ArrayList<UnreadReply>();
		for (Post p : posts) {
			long cnt = p.getReplys() - p.getReminder();
			Reply reply = new Reply();
			reply.setPostId(p.getId());
			// 获得未读的已更新的用户帖子下未读的回复
			List<Reply> unReadReplys = replyService.getAllByPo(reply).subList(0, (int)cnt);

			// 包装回复
			for (Reply r : unReadReplys) {
				BBSUser u = userService.getById(r.getReplyUser());
				UnreadReply ur = new UnreadReply(r.getReplyTime(), (short) 10, p, u);
				unreadReplys.add(ur);
			}
		}
		// 将包装类按时间排序
		Collections.sort(unreadReplys, new Comparator<UnreadReply>() {
			@Override
			public int compare(UnreadReply arg0, UnreadReply arg1) {
				return arg1.getCreateTime().compareTo(arg0.getCreateTime());
			}
		});
		model.addAttribute("unreadReplys", unreadReplys);
	}
	

	private void loadPostInformation(BBSUser user, Model model) {
		List<Post> myPost = postService.getPostByUserId(user.getId());
		List<Browse> myBrowse = browseService.getBrowseByUserId(user.getId());
		List<Post> myBrowsePost = new ArrayList<Post>();
		for(Browse b: myBrowse){
			Post post = postService.getById(b.getBrowseId());
			post.setUpdateTime(b.getBrowseTime());//用浏览时间代替post的更新时间输出信息
			myBrowsePost.add(post);
		}
		
		model.addAttribute("myPost", myPost);
		model.addAttribute("myBrowsePost", myBrowsePost);
	}
}
