package grp3022.bbs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;

import grp3022.bbs.po.Answer;
import grp3022.bbs.po.AnswerHelpKey;
import grp3022.bbs.po.BBSUser;
import grp3022.bbs.po.Post;
import grp3022.bbs.service.BBSUserService;
import grp3022.bbs.service.PostService;
import grp3022.bbs.so.AnswerSo;
import grp3022.bbs.so.PostSo;
import grp3022.bbs.so.QuestionSo;
import grp3022.bbs.type.Tag;
import grp3022.bbs.util.Format;


@Controller
@RequestMapping("/bulletin")
public class PostController {
	
	@Resource
	private BBSUserService userService;
	@Resource
	private PostService postService;
	private final String pathPrefix = "bulletin";
	
	@RequestMapping(value = "/post")
	public String post(Model model,HttpSession session) {
		if(session.getAttribute("userId")!=null){
			long userId = Long.parseLong(session.getAttribute("userId").toString());
			BBSUser user = userService.getById(userId);
			model.addAttribute("user", user);
		}
		return "/bulletin/post";
	}
	
	@RequestMapping(value = "/add")
	public String add(Post poster) {
		try {
			//System.out.println(""+poster.getTitle());
			postService.add(poster);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "/question/ask_fail";
		}
		return "/question/ask_success";
	}
	
}
