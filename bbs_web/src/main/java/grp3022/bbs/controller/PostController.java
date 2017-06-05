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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;

import grp3022.bbs.aop.UpdateMessage;
import grp3022.bbs.po.BBSUser;
import grp3022.bbs.po.BBSUserExample;
import grp3022.bbs.po.Browse;
import grp3022.bbs.po.BrowseKey;
import grp3022.bbs.po.Post;
import grp3022.bbs.po.PostExample;
import grp3022.bbs.po.Reply;
import grp3022.bbs.po.ReplyHelpKey;
import grp3022.bbs.service.BBSUserService;
import grp3022.bbs.service.BrowseService;
import grp3022.bbs.service.PostService;
import grp3022.bbs.service.ReplyHelpService;
import grp3022.bbs.service.ReplyService;
import grp3022.bbs.so.PostSo;
import grp3022.bbs.type.Tag;
import grp3022.bbs.util.Format;
import grp3022.bbs.util.TagUtil;


@Controller
@RequestMapping("/bulletin")
public class PostController {
	
	@Resource
	private BBSUserService userService;
	@Resource
	private PostService postService;
	@Resource
	private BrowseService browseService;
	@Resource
	private ReplyService replyService;
	@Resource
	private ReplyHelpService replyHelpService;
	private final String pathPrefix = "bulletin";
	
	
	@RequestMapping(value = "/post")
	@UpdateMessage(description = "发布帖子")
	public String post(Model model,HttpSession session) {
		if(session.getAttribute("userId")!=null){
			long userId = Long.parseLong(session.getAttribute("userId").toString());
			BBSUser user = userService.getById(userId);
			model.addAttribute("user", user);
		}
		return "/bulletin/post";
	}
	
	@RequestMapping(value = "/add")
	@UpdateMessage(description = "添加帖子")
	public @ResponseBody String add(Post poster) {
		try {
			//System.out.println(""+poster.getTitle());
			poster.setRewards((short) 50);
			postService.add(poster);
			
			/*更新用户分数*/
			long userId = poster.getPostUser();
			BBSUser user = userService.getById(userId);
			user.setScore(user.getScore()+10);
			userService.updateById(user);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "fail";
		}
		return "success";
	}
	
	@RequestMapping(value = "/home")
	@UpdateMessage(description = "论坛主页")
	public ModelAndView home(PostSo postSo,Integer pn,PostExample poster, BBSUserExample usere, HttpSession session) {
		
		/*if ((postSo.getKeywords() == null || postSo.getKeywords().equals(""))
				&& postSo.getTimeFrame() == null)
			postSo.setTimeFrame((short) 10);*/
		PageInfo<Post> pageInfo = postService.getPageBySo(postSo, pn, 10);

		/* 初始化标签 */
		Map<String, List<Tag>> tagsMap = new HashMap<String, List<Tag>>();
		for (Post post : pageInfo.getList()) {
			List<Integer> indexes = JSON.parseArray(post.getTags(), Integer.class);
			List<Tag> tags = TagUtil.getTagListByIndex(indexes);
			tagsMap.put(post.getId().toString(), tags);
		}
		/* 初始化选中标签 */
		Tag s_tag = null;
		if (postSo.getTagIndex() != null && !postSo.getTagIndex().equals("")) {
			int index = Integer.parseInt(postSo.getTagIndex());
			for (Tag tag : Tag.values()) {
				if (tag.getIndex() == index) {
					s_tag = tag;
				}
			}
		}

		
		/*List<Post> post = postService.getAllByPo(poster);*/
		
		ModelAndView mav = new ModelAndView(pathPrefix + "/home");
		
		/*获取排行榜信息*/
		List<BBSUser> rankuser = userService.getAllByPo(usere);
		
		if(session.getAttribute("userId")!=null){
			long userId = Long.parseLong(session.getAttribute("userId").toString());
			BBSUser user = userService.getById(userId);
			mav.addObject("user", user);
		}
		mav.addObject("rankuser",rankuser);
		/*mav.addObject("post",post);*/
		mav.addObject("pageInfo", pageInfo);
		mav.addObject("tagsMap", tagsMap);
		mav.addObject("s_tag", s_tag);
		mav.addObject("postSo", postSo);
		mav.addObject("format", new Format());
		return mav;
	}
	
	@RequestMapping(value = "/home/{pId}")
	@UpdateMessage(description = "读帖页面")
	public String question(@PathVariable Long pId, HttpSession session,Model model) {
		
		System.out.print(""+pId);
		/*初始化帖子*/
		Post post = postService.getById(pId);
		
		/*初始化发表用户信息*/
		BBSUser postuser = userService.getById(post.getPostUser());
		
		/*初始化回复*/
		List<Reply> replys = replyService.getAllByPostId(pId);
		
		long userId = 0;
		if(session.getAttribute("userId")!=null){
			userId = Long.parseLong(session.getAttribute("userId").toString());
			BBSUser user = userService.getById(userId);
			model.addAttribute("user", user);
			
			/*更新浏览记录*/
			BrowseKey key = new BrowseKey();
			key.setUserId(userId);
			key.setBrowseId(pId);
			if(browseService.getByKey(key)!=null){
				Browse record = browseService.getByKey(key);
				record.setBrowseCount(record.getBrowseCount()+1);
				record.setBrowseTime(new Date());
				browseService.updateByKey(record);
			}
			else{
				Browse record = new Browse();
				record.setBrowseId(pId);
				record.setUserId(userId);
				browseService.add(record);
			}
			
			/*更新未读回复情况*/
			if(userId==post.getPostUser()){
				post.setReminder(post.getReplys());
			}
		}
		
		/*初始化回复用户信息*/
		List<BBSUser> replyusers = new ArrayList<BBSUser>();
		/*初始化是否点赞*/ 
		List<Integer> helpEnable = new ArrayList<Integer>();
		for (Reply reply : replys) {
			BBSUser replyuser = userService.getById(reply.getReplyUser());
			replyusers.add(replyuser);
			ReplyHelpKey key = new ReplyHelpKey();
			key.setUserId(userId);
			key.setReplyId(reply.getId());
			long replyUser = reply.getReplyUser();
			if(replyUser == userId){//无法给自己点赞和引用自己说过的话
				helpEnable.add(3);
			}
			else{
				if (replyHelpService.getByKey(key) != null){
					if(replyHelpService.getByKey(key).getIsGood()==1){
						helpEnable.add(1);
					}
					else
						helpEnable.add(2);
				}
				else
					helpEnable.add(0);
			}
		}
		model.addAttribute("helpEnable", helpEnable);
		
		
		/* 更新问题信息并更新用户分数 */
		post.setViews(post.getViews() + 1);
		if(Long.parseLong(post.getViews().toString())<10000){//浏览分数最多100分
			long postUser = post.getPostUser();
			BBSUser user = userService.getById(postUser);
			user.setScore(user.getScore()-((Integer.parseInt(post.getViews().toString()))-1)/100+(Integer.parseInt(post.getViews().toString()))/100);
			userService.updateById(user);
		}
		postService.updateById(post);
		
		/* 初始化问题标签 */
		List<Tag> tags = new ArrayList<Tag>();
		List<Integer> indexes = JSON.parseArray(post.getTags(), Integer.class);
		for (int index : indexes) {
			for (Tag tag : Tag.values()) {
				if (tag.getIndex() == index) {
					tags.add(tag);
				}
			}
		}
		
		model.addAttribute("post", post);
		model.addAttribute("postuser", postuser);
		model.addAttribute("tags", tags);
		model.addAttribute("replys", replys);
		model.addAttribute("replyusers", replyusers);
		return "/bulletin/poster";
	}
	
	@RequestMapping(value = "/finish")
	@UpdateMessage(description = "结帖")
	public @ResponseBody String finish(@RequestParam(value = "p")Long postId,HttpSession session) {
		try {
			//BBSUser user = userService.getById(reply.getReplyUser());
			if(session.getAttribute("userId")==null){
				return "fail";
			}
			
			/*更新帖子表*/
			Post post = postService.getById(postId);
			post.setStatus((short) 1);
			postService.updateById(post);

		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
}
