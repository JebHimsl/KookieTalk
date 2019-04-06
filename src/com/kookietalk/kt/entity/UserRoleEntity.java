package com.kookietalk.kt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table(name = "user_role")
public class UserRoleEntity {

	//@Column(name = "USER_ID")
	private int userId;
	//@Column(name = "ROLE")
	private String role;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
