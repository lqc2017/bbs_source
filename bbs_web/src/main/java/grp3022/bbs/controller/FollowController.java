package grp3022.bbs.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import grp3022.bbs.po.BBSUser;
import grp3022.bbs.po.Follow;
import grp3022.bbs.service.BBSUserService;
import grp3022.bbs.service.FollowService;


@Controller
public class FollowController {

	@Resource
	private FollowService followService;
	@Resource
	private BBSUserService userService;
	
	/**
	 * 2017年5月24日 下午9:19:34
	 * @param follow
	 * @return
	 */
	@RequestMapping(value = "/follow")
	public @ResponseBody String add(Follow follow) {
		try {
			System.out.println(follow.getFollowerId()+"->"+follow.getUserId());
			followService.add(follow);
			this.updateUserFollow(follow.getFollowerId(), 1, 0);
			this.updateUserFollow(follow.getUserId(), 0, 1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "fail";
		}
		return "success";
	}
	
	/**
	 * 2017年5月24日 下午9:19:39
	 * @param follow
	 * @return
	 */
	@RequestMapping(value = "/unfo")
	public @ResponseBody String delete(Follow follow) {
		try {
			if(follow.getUserId()==null)
				return "nolog";
			System.out.println(follow.getFollowerId()+"><"+follow.getUserId());
			followService.deleteByKey(follow);
			this.updateUserFollow(follow.getFollowerId(), -1, 0);
			this.updateUserFollow(follow.getUserId(), 0, -1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "fail";
		}
		return "success";
	}
	
	/**
	 * 2017年5月24日 下午9:19:43
	 * @param userId
	 * @param followChange 关注人变化量
	 * @param followerChange 粉丝变化量
	 */
	private void updateUserFollow(Long userId,int followChange,int followerChange){
		BBSUser user = userService.getById(userId);
		List<Integer> followList = JSON.parseArray(user.getFollow(), Integer.class);
		followList.set(0, followList.get(0)+followChange);
		followList.set(1, followList.get(1)+followerChange);
		user.setFollow(JSON.toJSONString(followList));
		userService.updateById(user);
	}
}
