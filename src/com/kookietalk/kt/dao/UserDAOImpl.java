package com.kookietalk.kt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kookietalk.kt.entity.UserEntity;
import com.kookietalk.kt.model.User;

public class UserDAOImpl implements UserDAO {
	
	
	public User getUser(Integer i) {
		return new User();
	}
	
	public List getUsers() {
		return new ArrayList<User>();
	}
	
	
	
	/*
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User getUser(Integer userId) {
		User result = null;
		return result;
	}

	@Override
	public List<User> getUsers() {
		List<User> result = null;
		return result;
	}
	/**/

	/*
	@Override
	public boolean saveUser(User user) {
		boolean result = false;
		Connection conn = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/ktdb?useSSL=false&user=root&password=Ms@2441");
			System.out.println("Manual connection: " + conn);

			String query = "insert into member (username, password) values (?,?)";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, user.getEmailAddress());
			stmt.setString(2, user.getPassword());
			stmt.executeUpdate();
			result = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					System.out.println("Closing connection");
					conn.close();
					System.out.println("Connection closed.");
				}

			} catch (Exception connEx) {
				connEx.printStackTrace();
			}
		}

		return result;
	}
	*/
	
	@Override
	public boolean saveAdmin(User user) {
		boolean result = false;
		UserEntity ue = new UserEntity();
		ue.setEmailAddress(user.getEmailAddress());
		ue.setfName(user.getFirstName());
		ue.sethPhone(user.gethPhone());
		ue.setlName(user.getLastName());
		ue.setmName(user.getMiddleName());
		ue.setmPhone(user.getmPhone());
		ue.setnName(user.getNickName());
		ue.setPassword(user.getPassword());
		//ue.setUserId(user.getUserId());
		ue.setUserName(user.getUserName());
		ue.setwPhone(user.getwPhone());
		//ue.setBillingAddressId(user.getBillingAddressId());
		//ue.setMailingAddressId(user.getMailingAddressId());
		/*
		try {
			System.out.println("SessionFactory is: " + sessionFactory);
			Session currentSession = sessionFactory.getCurrentSession();
			currentSession.saveOrUpdate(ue);
			System.out.println("Post user insert, user_id is: " + ue.getUserId());
			result = true;
		} catch(Exception ex) {
			ex.printStackTrace();
			result = false;
		}
		/**/

		return result;
	}
	
	@Override
	public boolean saveStudent(User user) {
		boolean result = false;
		UserEntity ue = new UserEntity();
		ue.setEmailAddress(user.getEmailAddress());
		ue.setfName(user.getFirstName());
		ue.sethPhone(user.gethPhone());
		ue.setlName(user.getLastName());
		ue.setmName(user.getMiddleName());
		ue.setmPhone(user.getmPhone());
		ue.setnName(user.getNickName());
		ue.setPassword(user.getPassword());
		//ue.setUserId(user.getUserId());
		ue.setUserName(user.getUserName());
		ue.setwPhone(user.getwPhone());
		//ue.setBillingAddressId(user.getBillingAddressId());
		//ue.setMailingAddressId(user.getMailingAddressId());
		/*
		try {
			System.out.println("SessionFactory is: " + sessionFactory);
			Session currentSession = sessionFactory.getCurrentSession();
			currentSession.saveOrUpdate(ue);
			System.out.println("Post user insert, user_id is: " + ue.getUserId());
			result = true;
		} catch(Exception ex) {
			ex.printStackTrace();
			result = false;
		}
		/**/

		return result;
	}
	
	@Override
	public boolean saveTeacher(User user) {
		boolean result = false;
		UserEntity ue = new UserEntity();
		ue.setEmailAddress(user.getEmailAddress());
		ue.setfName(user.getFirstName());
		ue.sethPhone(user.gethPhone());
		ue.setlName(user.getLastName());
		ue.setmName(user.getMiddleName());
		ue.setmPhone(user.getmPhone());
		ue.setnName(user.getNickName());
		ue.setPassword(user.getPassword());
		//ue.setUserId(user.getUserId());
		ue.setUserName(user.getUserName());
		ue.setwPhone(user.getwPhone());
		//ue.setBillingAddressId(user.getBillingAddressId());
		//ue.setMailingAddressId(user.getMailingAddressId());
		
		
		/*
		try {
			System.out.println("SessionFactory is: " + sessionFactory);
			Session currentSession = sessionFactory.getCurrentSession();
			currentSession.saveOrUpdate(ue);
			System.out.println("Post user insert, user_id is: " + ue.getUserId());
			result = true;
		} catch(Exception ex) {
			ex.printStackTrace();
			result = false;
		}
		/**/

		return result;
	}

	@Override
	public boolean deleteUser(Integer userId) {
		boolean result = false;
		return result;
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
}
