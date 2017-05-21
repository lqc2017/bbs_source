package grp3022.bbs.controller;



import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import grp3022.bbs.jo.Percentage;
import grp3022.bbs.service.BBSUserService;
import grp3022.bbs.type.Tag;
import grp3022.bbs.util.TagBuilder;


/**
 * @author 全琛
 * @create_time 上午11:07:01
 *
 */
@Controller
public class DefaultController {

	@Resource
	private BBSUserService userService;
	/**
	 * 2017年5月20日 下午2:59:37
	 * @return
	 */
	@RequestMapping(value = "sss")
	public String home(Model model) {
		String majorStr = userService.getById((long) 3).getqMajor();
		System.out.println(majorStr);
		List<Percentage> majars = JSON.parseArray(majorStr,Percentage.class);
		
		String participateStr = userService.getById((long) 3).getqParticipate();
		List<Percentage> participates = JSON.parseArray(participateStr,Percentage.class);
		
		Map<Integer, Tag> tagMap = TagBuilder.getTagMap();
		
		model.addAttribute("majars", majars);
		model.addAttribute("participates", participates);
		model.addAttribute("tagMap", tagMap);
		return "profile";
	}
	
}
