package com.kookietalk.kt.dao;

import java.util.List;

import com.kookietalk.kt.model.User;

public interface UserDAO {
	public User getUser(Integer userId);
	public List<User> getUsers();
	public boolean saveAdmin(User user);
	public boolean saveStudent(User user);
	public boolean saveTeacher(User user);
	public boolean deleteUser(Integer userId);
	public User getUserByEmail(String email);
}
