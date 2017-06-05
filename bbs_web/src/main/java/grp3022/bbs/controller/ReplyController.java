package grp3022.bbs.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import grp3022.bbs.aop.UpdateMessage;
import grp3022.bbs.po.BBSUser;
import grp3022.bbs.po.Post;
import grp3022.bbs.po.Reply;
import grp3022.bbs.po.ReplyHelp;
import grp3022.bbs.service.BBSUserService;
import grp3022.bbs.service.PostService;
import grp3022.bbs.service.ReplyHelpService;
import grp3022.bbs.service.ReplyService;

@Controller
@RequestMapping("/bulletin")
public class ReplyController {
	
	@Resource
	private BBSUserService userService;
	@Resource
	private ReplyService replyService;
	@Resource
	private ReplyHelpService replyHelpService;
	@Resource
	private PostService postService;

	@RequestMapping(value = "/reply")
	@UpdateMessage(description = "回复")
	public @ResponseBody String add(Reply reply) {
		try {
			
			//BBSUser user = userService.getById(reply.getReplyUser());
			/*找到对应楼层的回复id*/
			if(reply.getCiteId()!=null){
				reply.setCiteFlorr(reply.getCiteId());
				List<Reply> finding = replyService.getAllByPostId(reply.getPostId());
				Reply findout = finding.get((int) (reply.getCiteId()-1));
				reply.setCiteId(findout.getId());
				reply.setCiteContent(findout.getContent());
			}
			replyService.add(reply);
			
			/*更新用户分数*/
			long replyUser = reply.getReplyUser();
			BBSUser user = userService.getById(replyUser);
			user.setScore(user.getScore()+2);
			userService.updateById(user);
			
			/*更新post*/
			Post post = postService.getById(reply.getPostId());
			post.setReplys(post.getReplys()+1);
			post.setUpdateTime(new Date());
			post.setFloor(post.getFloor()+1);
			postService.updateById(post);
			
			
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
	
	@RequestMapping(value = "/reply_help")
	@UpdateMessage(description = "评价")
	public @ResponseBody String reply_helpful(@RequestParam(value = "r")Long replyId
			,@RequestParam(value = "value")Short value,HttpSession session) {
		try {
			//BBSUser user = userService.getById(reply.getReplyUser());
			if(session.getAttribute("userId")==null){
				return "fail";
			}
			long userId = Long.parseLong(session.getAttribute("userId").toString());
			/*添加评价记录*/
			ReplyHelp replyhelp = new ReplyHelp();
			replyhelp.setReplyId(replyId);
			replyhelp.setUserId(userId);
			replyhelp.setIsGood(value);
			replyHelpService.add(replyhelp);
			
			/*更新reply*/
			Reply reply = replyService.getById(replyhelp.getReplyId());
			if(value==1)
				reply.setScore(reply.getScore()+1);
			else
				reply.setScore(reply.getScore()-1);
			replyService.updateById(reply);
			
			/*更新用户分数*/
			long replyUser = reply.getReplyUser();
			BBSUser user = userService.getById(replyUser);
			if(value==1)
				user.setScore(user.getScore()+1);
			else if(value==2&&user.getScore()>0)
				user.setScore(user.getScore()-1);
			userService.updateById(user);
			

		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
	
	@RequestMapping(value = "/accept")
	@UpdateMessage(description = "采纳")
	public @ResponseBody String accept(@RequestParam(value = "r")Long replyId,@RequestParam(value = "p")Long postId,HttpSession session) {
		try {
			//BBSUser user = userService.getById(reply.getReplyUser());
			if(session.getAttribute("userId")==null){
				return "fail";
			}
			//long userId = Long.parseLong(session.getAttribute("userId").toString());
			
			/*更新帖子表*/
			Post post = postService.getById(postId);
			post.setAcceptId(replyId);
			postService.updateById(post);
			
			
			
			/*更新reply*/
			Reply reply = replyService.getById(replyId);
			reply.setScore(reply.getScore()+post.getRewards());
			replyService.updateById(reply);
			
			/*更新用户分数*/
			long replyUser = reply.getReplyUser();
			BBSUser user = userService.getById(replyUser);
			user.setScore(user.getScore()+post.getRewards());
			userService.updateById(user);
			

		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
}
