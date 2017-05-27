package grp3022.bbs.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import grp3022.bbs.po.Answer;
import grp3022.bbs.po.BBSUser;
import grp3022.bbs.po.Post;
import grp3022.bbs.po.Question;
import grp3022.bbs.po.Reply;
import grp3022.bbs.service.BBSUserService;
import grp3022.bbs.service.PostService;
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
			postService.updateById(post);
			
			
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
}
