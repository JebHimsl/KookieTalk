package com.kookietalk.kt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class AuthenticationDAOJDBC extends BaseDAO implements AuthenticationDAO {
	
	@Override
	public ArrayList<GrantedAuthority> authenticate(String username, String password) {
		ArrayList<GrantedAuthority> result = null;
		
		Connection conn = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2= null;
		
		//TODO: Call method to hash password.
		String obPass = password;
		
		try {
			
			conn = getConnection();
			String query1 = "select * from user where user_name=? and password=?";
			stmt1 = conn.prepareStatement(query1);
			stmt1.setString(1, username);
			stmt1.setString(2, obPass);
			rs1 = stmt1.executeQuery();
			if(rs1.next()) {
				int userId = rs1.getInt("USER_ID");
				String query2 = "select role from user_role where user_id=?";
				stmt2 = conn.prepareStatement(query2);
				stmt2.setInt(1, userId);
				rs2 = stmt2.executeQuery();
				result = new ArrayList<>();
				while(rs2.next()) {
					String role = rs2.getString("ROLE");
					GrantedAuthority ga = new SimpleGrantedAuthority(role);
					result.add(ga);
				}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(rs2 != null) {
				try {
					rs2.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if(rs1 != null) {
				try {
					rs1.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if(stmt2 != null) {
				try {
					stmt2.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if(stmt1 != null) {
				try {
					stmt1.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
		}
		
		return result;
	}

}
