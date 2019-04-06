package com.kookietalk.kt.dao;

import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;

public interface AuthenticationDAO {
	
	public ArrayList<GrantedAuthority> authenticate(String username, String password);

}
