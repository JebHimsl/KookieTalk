package com.kookietalk.kt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kookietalk.kt.entity.Session;

public class BaseDAO {

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager
					.getConnection("jdbc:mysql://kookie.cygio3os2zym.us-west-2.rds.amazonaws.com:3306/kookiedev?useSSL=false&user=kookiedev&password=kookiedev");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static ArrayList<Object> templateOnly(){
		ArrayList<Object> objects = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection();
			stmt = conn.prepareStatement("");
			rs = stmt.executeQuery();
			
		}catch(SQLException sex) {
			sex.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException sex) {
					sex.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch(SQLException sex) {
					sex.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException sex) {
					sex.printStackTrace();
				}
			}
			
		}
		
		return objects;
	}
	
}
