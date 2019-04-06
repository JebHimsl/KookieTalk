package com.kookietalk.kt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FinancialDAO extends BaseDAO {
	
	public static int getCredits(int userId) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String query = "select credits from learn_financial where student_id=?";
		
		try {
			
			conn = getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, userId);
			rs = stmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt("credits");
			}
			
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
		
		
		return result;
	}

}
