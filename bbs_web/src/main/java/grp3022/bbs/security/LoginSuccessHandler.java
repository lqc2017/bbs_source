package grp3022.bbs.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {

		//System.out.println("handle");
		if (response.isCommitted()) {
			System.out.println("Can't redirect");
			return;
		}
		//System.out.println(request.getHeader("Referer"));
		redirectStrategy.sendRedirect(request, response, request.getHeader("Referer"));
	}

	/*
	 * This method extracts the roles of currently logged-in user and returns
	 * appropriate URL according to his/her role.
	 */
	protected String determineTargetUrl(Authentication authentication) {
		String url = "";

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		List<String> roles = new ArrayList<String>();

		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}

		if (isCashier(roles)) {
			url = "/cashier/home";
		}else if (isDoctor(roles)) {
			url = "/doctor/home";
		} else if (isPatient(roles)) {
			url = "/patient/home";
		}else if(isAdmin(roles)){
			url = "/admin/home";
		}else{
			url = "/login?error";
		}

		return url;
	}
	
	private boolean isDoctor(List<String> roles) {
		if (roles.contains("ROLE_DOCTOR")) {
			return true;
		}
		return false;
	}
	
	private boolean isAdmin(List<String> roles) {
		if (roles.contains("ROLE_ADMIN")) {
			return true;
		}
		return false;
	}
	
	private boolean isPatient(List<String> roles) {
		if (roles.contains("ROLE_PATIENT")) {
			return true;
		}
		return false;
	}
	
	private boolean isCashier(List<String> roles) {
		if (roles.contains("ROLE_CASHIER")) {
			return true;
		}
		return false;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

}