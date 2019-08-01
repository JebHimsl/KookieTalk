package com.kookietalk.kt.authenticate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.kookietalk.kt.dao.UserDAO;
import com.kookietalk.kt.dao.UserDAOJDBC;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}
	
	protected String determineTargetUrl(Authentication authentication, HttpServletRequest request, String userName) {
		String url = "";
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<String> roles = new ArrayList<>();
		for(GrantedAuthority a: authorities) {
			roles.add(a.getAuthority());
		}
		
		
		// Get the user object and set it in the request for forwarding
		HttpSession session = request.getSession();
		if(!roles.isEmpty()) {
			UserDAO udao = new UserDAOJDBC();
			com.kookietalk.kt.model.User user = udao.getUserByEmail(userName);
			session.setAttribute("user", user);
		}
		request.setAttribute("role", "User");
		
		if(isAdmin(roles)) {
			url="/ktFlow?role=admin&userName=" + userName;
		} else if(isLearn(roles)) {
			url="/ktFlow?role=learn&userName=" + userName;
		} else if(isTeach(roles)) {
			url="/ktFlow?role=teach&userName=" + userName;
		}
		return url;
	}
	
	private boolean isAdmin(List<String> roles) {
		boolean result = false;
		if(roles.contains("ROLE_ADMIN")) {
			result = true;
		}
		return result;
	}
	
	private boolean isLearn(List<String> roles) {
		boolean result = false;
		if(roles.contains("ROLE_LEARN")) {
			result = true;
		}
		return result;
	}
	
	private boolean isTeach(List<String> roles) {
		boolean result = false;
		if(roles.contains("ROLE_TEACH")) {
			result = true;
		}
		return result;
	}
	
	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		
		String userName = authentication.getName();
		/*User user = (User) authentication.getPrincipal();
		if(user != null) {
			userName = user.getUsername();
		}
		/**/
		String targetUrl = determineTargetUrl(authentication, request, userName);
		if(response.isCommitted()) {
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

}
