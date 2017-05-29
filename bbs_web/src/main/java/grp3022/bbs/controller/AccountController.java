package grp3022.bbs.controller;




import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import grp3022.bbs.jo.UserSetting;
import grp3022.bbs.po.Account;
import grp3022.bbs.po.BBSUser;
import grp3022.bbs.service.AccountService;
import grp3022.bbs.service.BBSUserService;


@Controller
public class AccountController {

	@Resource
	private AccountService accountService;
	@Resource
	private BBSUserService userService;
	
	@RequestMapping(value = "/signUp")
	public String signUp(Account account,BBSUser user) {
		try {
			//初始化用户设置
			UserSetting setting = new UserSetting((short)1,(short)1,(short)1);
			user.setSetting(JSON.toJSONString(setting));
			//初始化用户关注
			int[] followList = {0,0};
			user.setFollow(JSON.toJSONString(followList));
			//初始化头像
			user.setProtraitUrl("https://avatars0.githubusercontent.com/u/26128332?v=3&s=460");
			Long userId = userService.add(user);
			
			account.setUserid(userId);
			accountService.add(account);
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	@RequestMapping(value = "/validate_username/{username}")
	public @ResponseBody String validate_username(@PathVariable String username) {
		if(accountService.countByUsername(username.trim())>0)
			return "false";
		return "success";
	}
}
