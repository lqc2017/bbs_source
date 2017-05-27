package grp3022.bbs.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import grp3022.bbs.po.Answer;
import grp3022.bbs.po.BBSUser;
import grp3022.bbs.po.Post;
import grp3022.bbs.po.Question;
import grp3022.bbs.po.Reply;
import grp3022.bbs.po.ReplyHelp;
import grp3022.bbs.service.BBSUserService;
import grp3022.bbs.service.PostService;
import grp3022.bbs.service.ReplyHelpService;
import grp3022.bbs.service.ReplyService;
import grp3022.bbs.so.AnswerSo;

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
	public @ResponseBody String add(Reply reply) {
		try {
			
			//BBSUser user = userService.getById(reply.getReplyUser());
			replyService.add(reply);
			
			/*更新post*/
			Post post = postService.getById(reply.getPostId());
			post.setReplys(post.getReplys()+1);
			post.setUpdateTime(new Date());
			post.setReplys(post.getFloor()+1);
			postService.updateById(post);
			
			
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
	
	@RequestMapping(value = "/reply_help")
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
			
			
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
}
