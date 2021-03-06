package grp3022.bbs.controller;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DefaultController {

	@RequestMapping(value = "/loginPage")
	public String login() {
		return "login";
	}
	
	/**
	 * 2017年5月23日 下午11:06:28
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/logout")
		public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null){    
				new SecurityContextLogoutHandler().logout(request, response, auth);
			}
			return "redirect:"+request.getHeader("Referer");
	}
}
