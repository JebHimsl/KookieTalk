package com.kookietalk.kt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kookietalk.kt.entity.LearnStatus;

public class LearnStatusDAO extends BaseDAO {

	public static double getCredits(int userId) {
		double result = 0;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String query = "select credits from learn_status where student_id=?";

		try {

			conn = getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, userId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				result = rs.getDouble("credits");
			}

		} catch (SQLException sex) {
			sex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}

		}

		return result;
	}

	public static void setCredits(int studentId, double credits) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String query = "update learn_status set credits=? where student_id=?";

		try {

			conn = getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setDouble(1, credits);
			stmt.setInt(2, studentId);
			stmt.executeUpdate();
		} catch (SQLException sex) {
			sex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}

		}
	}

	public static LearnStatus getStatus(int studentId) {

		LearnStatus status = null;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String query = "select * from learn_status where student_id=?";

		try {

			conn = getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, studentId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				status = new LearnStatus();
				status.setCredits(rs.getDouble("credits"));
				status.setTitle(rs.getInt("lastTitle"));
				status.setChapter(rs.getInt("lastChapter"));
				status.setLesson(rs.getInt("lastLesson"));
				status.setSlide(rs.getInt("lastSlide"));
				status.setStudentId(rs.getInt("student_id"));
			} 

		} catch (SQLException sex) {
			sex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}

		}
		return status;
	}
	
	public static int updateStatus(LearnStatus status) {

		int result = -1;

		Connection conn = null;
		PreparedStatement stmt = null;

		String query = "update learn_status set lastTitle=?, lastChapter=?, lastLesson=?, lastSlide=? where student_id=?";
		
		System.out.println("updating status: studentId:" + status.getStudentId() + " title: " + status.getTitle() + " chapter: " + status.getChapter() + " lesson: " + status.getLesson() + " slide: " + status.getSlide());

		try {

			conn = getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, status.getTitle());
			stmt.setInt(2, status.getChapter());
			stmt.setInt(3, status.getLesson());
			stmt.setInt(4, status.getSlide());
			stmt.setInt(5, status.getStudentId());
			result = stmt.executeUpdate();
			
		} catch (SQLException sex) {
			sex.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if (conn != null) {
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
