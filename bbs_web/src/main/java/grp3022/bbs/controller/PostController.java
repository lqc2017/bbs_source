package grp3022.bbs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;

import grp3022.bbs.po.Answer;
import grp3022.bbs.po.AnswerHelpKey;
import grp3022.bbs.po.BBSUser;
import grp3022.bbs.po.Post;
import grp3022.bbs.po.PostExample;
import grp3022.bbs.po.Question;
import grp3022.bbs.service.BBSUserService;
import grp3022.bbs.service.PostService;
import grp3022.bbs.so.AnswerSo;
import grp3022.bbs.so.QuestionSo;
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
	
	@RequestMapping(value = "/home")
	public ModelAndView home(PostExample poster) {
		List<Post> post = postService.getAllByPo(poster);
		
		ModelAndView mav = new ModelAndView(pathPrefix + "/home");
		mav.addObject("post",post);
		return mav;
	}
	
	@RequestMapping(value = "/home/{pId}")
	public String question(@PathVariable Long pId, HttpSession session,Model model) {
		
		System.out.print(""+pId);
		/*初始化帖子*/
		Post post = postService.getById(pId);
		
		if(session.getAttribute("userId")!=null){
			long userId = Long.parseLong(session.getAttribute("userId").toString());
			BBSUser user = userService.getById(userId);
			model.addAttribute("user", user);
		}
		
		/* 更新问题信息 */
		post.setViews(post.getViews() + 1);
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
		model.addAttribute("tags", tags);
		return "/bulletin/poster";
	}
	
}
