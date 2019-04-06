package com.kookietalk.kt.authenticate;

import java.util.Collection;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import com.kookietalk.kt.dao.AuthenticationDAO;
import com.kookietalk.kt.dao.AuthenticationDAOJDBC;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		AuthenticationDAO authDAO = new AuthenticationDAOJDBC();
		Collection<? extends GrantedAuthority> roles = authDAO.authenticate(name, password);
		
		
		UsernamePasswordAuthenticationToken token = null;
		if(roles != null) {
			token = new UsernamePasswordAuthenticationToken(name, password, roles);
		}
		
		return token;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
