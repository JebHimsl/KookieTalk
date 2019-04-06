package com.kookietalk.kt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;

import com.kookietalk.kt.entity.Session;

public class SessionDAO extends BaseDAO {

	// This get all the 'booked' sessions for this instructor for the specified
	// week.
	public static ArrayList<Session> getSessions(int instructorId, int year, int week) {
		ArrayList<Session> sessions = new ArrayList<Session>();
		;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String query = "select * from session where teacher_id=? and week=? and year=?";

		try {

			conn = getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, instructorId);
			stmt.setInt(2, week);
			stmt.setInt(3, year);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Session sess = new Session();
				sess.setCreated(rs.getTimestamp("created"));
				sess.setDay(rs.getInt("day"));
				sess.setSessionId(rs.getInt("session_id"));
				sess.setStudentId(rs.getInt("student_id"));
				sess.setTeacherId(rs.getInt("teacher_id"));
				sess.setTime(rs.getInt("time"));
				sess.setWeek(rs.getInt("week"));
				sess.setYear(rs.getInt("year"));
				sessions.add(sess);
				// System.out.println("Found [" + sess.getDay() + "] day and [" + sess.getTime()
				// + "] time.");
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
		// System.out.println("Returning [" + sessions.size() + "] sessions.");
		return sessions;
	}

	// This get all the 'booked' sessions for this student
	public static ArrayList<Session> getStudentSessions(int sId) {
		ArrayList<Session> sessions = new ArrayList<Session>();
		;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String query = "select * from session where student_id=? order by year desc, week desc, day desc, time desc";
		String query2 = "select CONCAT(first_name, ' ', last_name) as name from user where user_id=?";

		try {

			conn = getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, sId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Session sess = new Session();
				sess.setCreated(rs.getTimestamp("created"));
				sess.setDay(rs.getInt("day"));
				sess.setSessionId(rs.getInt("session_id"));
				sess.setStudentId(rs.getInt("student_id"));
				sess.setTeacherId(rs.getInt("teacher_id"));
				sess.setTime(rs.getInt("time"));
				sess.setWeek(rs.getInt("week"));
				sess.setYear(rs.getInt("year"));
				sessions.add(sess);
			}

			Iterator<Session> it = sessions.iterator();
			while (it.hasNext()) {
				Session sess = it.next();
				int studentId = sess.getStudentId();
				if (!rs.isClosed()) {
					rs.close();
				}
				if (!stmt.isClosed()) {
					stmt.close();
				}
				if (conn.isClosed()) {
					System.out.println("This is a test.  Connection should not be closed.");
				}
				stmt = conn.prepareStatement(query2);
				stmt.setInt(1, studentId);
				rs = stmt.executeQuery();
				while (rs.next()) {
					// System.out.println("in DAO, student name = " + rs.getString("name"));
					sess.setStudentName(rs.getString("name"));
				}
				int teacherId = sess.getTeacherId();
				if (!rs.isClosed()) {
					rs.close();
				}
				if (!stmt.isClosed()) {
					stmt.close();
				}
				if (conn.isClosed()) {
					System.out.println("This is a test.  Connection should not be closed.");
				}
				stmt = conn.prepareStatement(query2);
				stmt.setInt(1, teacherId);
				rs = stmt.executeQuery();
				while (rs.next()) {
					// System.out.println("in DAO, teacher name = " + rs.getString("name"));
					sess.setInstructorName(rs.getString("name"));
				}
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
		// System.out.println("Returning [" + sessions.size() + "] sessions.");
		return sessions;
	}

	// This get all the 'booked' sessions for this student
	public static ArrayList<Session> getTeacherSessions(int tId) {
		ArrayList<Session> sessions = new ArrayList<Session>();
		;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String query = "select * from session where teacher_id=? order by year desc, week desc, day desc, time desc";
		String query2 = "select CONCAT(first_name, ' ', last_name) as name from user where user_id=?";

		try {

			conn = getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, tId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Session sess = new Session();
				sess.setCreated(rs.getTimestamp("created"));
				sess.setDay(rs.getInt("day"));
				sess.setSessionId(rs.getInt("session_id"));
				sess.setStudentId(rs.getInt("student_id"));
				sess.setTeacherId(rs.getInt("teacher_id"));
				sess.setTime(rs.getInt("time"));
				sess.setWeek(rs.getInt("week"));
				sess.setYear(rs.getInt("year"));
				sessions.add(sess);
			}

			Iterator<Session> it = sessions.iterator();
			while (it.hasNext()) {
				Session sess = it.next();
				int studentId = sess.getStudentId();
				if (!rs.isClosed()) {
					rs.close();
				}
				if (!stmt.isClosed()) {
					stmt.close();
				}
				if (conn.isClosed()) {
					System.out.println("This is a test.  Connection should not be closed.");
				}
				stmt = conn.prepareStatement(query2);
				stmt.setInt(1, studentId);
				rs = stmt.executeQuery();
				while (rs.next()) {
					// System.out.println("in DAO, student name = " + rs.getString("name"));
					sess.setStudentName(rs.getString("name"));
				}
				int teacherId = sess.getTeacherId();
				if (!rs.isClosed()) {
					rs.close();
				}
				if (!stmt.isClosed()) {
					stmt.close();
				}
				if (conn.isClosed()) {
					System.out.println("This is a test.  Connection should not be closed.");
				}
				stmt = conn.prepareStatement(query2);
				stmt.setInt(1, teacherId);
				rs = stmt.executeQuery();
				while (rs.next()) {
					// System.out.println("in DAO, teacher name = " + rs.getString("name"));
					sess.setInstructorName(rs.getString("name"));
				}
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
		// System.out.println("Returning [" + sessions.size() + "] sessions.");
		return sessions;
	}

	// Session data must be in normalized state.
	public static Integer saveSession(Session session) {

		Integer result = null;

		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;

		String query1 = "insert into session values(NULL, ?, ?, ?, ?, ?, ?, ?)";
		String query2 = "select max(session_id) as mxsessionid from session where teacher_id=? and year=? and week=? and day=? and time=?";

		try {

			conn = getConnection();
			// conn.setAutoCommit(false);
			stmt = conn.prepareStatement(query1);
			stmt.setInt(1, session.getStudentId());
			stmt.setInt(2, session.getTeacherId());
			stmt.setInt(3, session.getYear());
			stmt.setInt(4, session.getWeek());
			stmt.setInt(5, session.getDay());
			stmt.setInt(6, session.getTime());
			stmt.setTimestamp(7, new Timestamp(new Date().getTime()));
			stmt.executeUpdate();

			// *
			stmt2 = conn.prepareStatement(query2);
			stmt2.setInt(1, session.getTeacherId());
			stmt2.setInt(2, session.getYear());
			stmt2.setInt(3, session.getWeek());
			stmt2.setInt(4, session.getDay());
			stmt2.setInt(5, session.getTime());
			rs = stmt2.executeQuery();
			int sessionId = -1;
			while (rs.next()) {
				sessionId = rs.getInt("mxsessionid");
				session.setSessionId(sessionId);
			}
			result = sessionId;
			/**/
			// conn.commit();

		} catch (SQLException sex) {
			sex.printStackTrace();
			/*
			 * try { //conn.rollback();
			 * System.out.println("Insert failed, transaction rolled back.");
			 * }catch(SQLException sex2) { sex2.printStackTrace(); } /
			 **/
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
