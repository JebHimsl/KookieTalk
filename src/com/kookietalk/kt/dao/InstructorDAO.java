package com.kookietalk.kt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kookietalk.kt.entity.Instructor;

public class InstructorDAO extends BaseDAO {

	public static ArrayList<Instructor> getInstructors() {
		ArrayList<Instructor> instructors = new ArrayList<Instructor>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "select a.first_name, a.last_name, a.user_id, c.ranking from user_role b, user a left join instructor_ranking c on a.user_id=c.user_id where a.user_id=b.user_id and b.role='ROLE_TEACH' order by c.ranking desc, a.user_id asc";

		try {

			conn = getConnection();
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Instructor instructor = new Instructor();
				instructor.setFirstName(rs.getString("first_name"));
				instructor.setLastName(rs.getString("last_name"));
				instructor.setUserId(rs.getInt("user_id"));
				instructor.setRanking(rs.getDouble("ranking"));
				instructors.add(instructor);
			}

		} catch (SQLException sex) {
			sex.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException sex) {
				sex.printStackTrace();
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException sex) {
				sex.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sex) {
				sex.printStackTrace();
			}
		}

		return instructors;
	}
	
	public static Instructor getInstructor(int iId) {
		Instructor result = null;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "select a.first_name, a.last_name, a.user_id, c.ranking from user_role b, user a left join instructor_ranking c on a.user_id=c.user_id where a.user_id=b.user_id and b.role='ROLE_TEACH' and a.user_id=?";

		try {

			conn = getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, iId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Instructor instructor = new Instructor();
				instructor.setFirstName(rs.getString("first_name"));
				instructor.setLastName(rs.getString("last_name"));
				instructor.setUserId(rs.getInt("user_id"));
				instructor.setRanking(rs.getDouble("ranking"));
				result = instructor;
			}

		} catch (SQLException sex) {
			sex.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException sex) {
				sex.printStackTrace();
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException sex) {
				sex.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sex) {
				sex.printStackTrace();
			}
		}

		return result;
	}

}
