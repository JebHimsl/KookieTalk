package com.kookietalk.kt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;


public class ScheduleDAO extends BaseDAO {

	public static ArrayList<String> getSchedule(int userid, int weekOfYear, int year) {
		ArrayList<String> result = new ArrayList<String>();

		String query = "select * from schedule where USER_ID = ? and WEEK = ? and YEAR=?";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ScheduleDAO.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, userid);
			stmt.setInt(2, weekOfYear);
			stmt.setInt(3, year);
			rs = stmt.executeQuery();
			if(rs.next()) {
				//System.out.println("Found existing schedule in getSchedule");
				result.add(rs.getString("SUNDAY"));
				result.add(rs.getString("MONDAY"));
				result.add(rs.getString("TUESDAY"));
				result.add(rs.getString("WEDNESDAY"));
				result.add(rs.getString("THURSDAY"));
				result.add(rs.getString("FRIDAY"));
				result.add(rs.getString("SATURDAY"));
			} else {
				//System.out.println("Did not find existing schedule in getSchedule");
				for(int i = 0; i < 7; i++) {
					result.add("000000000000000000000000000000000000000000000000");
				}
			}
		} catch(SQLException sex) {
			sex.printStackTrace();
		} finally {
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
			}if(conn != null) {
				try {
					conn.close();
				} catch(SQLException sex) {
					sex.printStackTrace();
				}
			}
		}
		return result;
	}

	public static void setSchedule(int userId, int weekNo, int day, String newSchedule, int year) {

		// First, make sure this user has a schedule for this week. If not create an
		// empty one.
		String query1 = "select * from schedule where USER_ID = ? and WEEK = ? and YEAR=?";
		String blank = "000000000000000000000000000000000000000000000000";
		String query2 = "insert into schedule (USER_ID, WEEK, SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, YEAR) values (?,?,?,?,?,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
		ResultSet rs = null;
		ArrayList<String> currentSchedule = new ArrayList<String>();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(query1);
			stmt.setInt(1, userId);
			stmt.setInt(2, weekNo);
			stmt.setInt(3, year);
			rs = stmt.executeQuery();
			boolean found = false;
			//System.out.println("Checking for existing schedule...");
			if (rs.next()) {
				//System.out.println("schedule found.");
				found = true;
				currentSchedule.add(rs.getString("SUNDAY"));
				currentSchedule.add(rs.getString("MONDAY"));
				currentSchedule.add(rs.getString("TUESDAY"));
				currentSchedule.add(rs.getString("WEDNESDAY"));
				currentSchedule.add(rs.getString("THURSDAY"));
				currentSchedule.add(rs.getString("FRIDAY"));
				currentSchedule.add(rs.getString("SATURDAY"));
			}
			if (!found) {
				//System.out.println("schedule not found.");
				stmt2 = conn.prepareStatement(query2);
				stmt2.setInt(1, userId);
				stmt2.setInt(2, weekNo);
				stmt2.setString(3, blank);
				stmt2.setString(4, blank);
				stmt2.setString(5, blank);
				stmt2.setString(6, blank);
				stmt2.setString(7, blank);
				stmt2.setString(8, blank);
				stmt2.setString(9, blank);
				stmt2.setInt(10, year);
				stmt2.executeUpdate();
				for (int i = 0; i < 7; i++) {
					currentSchedule.add(blank);
				}
			}
			// Now update the schedule
			@SuppressWarnings("unused")
			String toUpdate = currentSchedule.get(day);
			String updateDay = null;
			switch (day) {
			case 0:
				updateDay = "SUNDAY";
				break;
			case 1:
				updateDay = "MONDAY";
				break;
			case 2:
				updateDay = "TUESDAY";
				break;
			case 3:
				updateDay = "WEDNESDAY";
				break;
			case 4:
				updateDay = "THURSDAY";
				break;
			case 5:
				updateDay = "FRIDAY";
				break;
			case 6:
				updateDay = "SATURDAY";
				break;
			}
			
			String query3 = "update schedule set " + updateDay + "= ? where USER_ID = ? and WEEK = ? and YEAR=?";
			stmt3 = conn.prepareStatement(query3);
			stmt3.setString(1, newSchedule);
			stmt3.setInt(2, userId);
			stmt3.setInt(3, weekNo);
			stmt3.setInt(4, year);
			stmt3.executeUpdate();	
		} catch (SQLException sex) {
			sex.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				}catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				}catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if(stmt2 != null) {
				try {
					stmt2.close();
				}catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if(stmt3 != null) {
				try {
					stmt3.close();
				}catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				}catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
		}

	}
	
	public static ArrayList<String> saveUserInput(int userid, int weekOfYear, int starttime, int endtime, int day, int action, int year) {
		ArrayList<String> result = new ArrayList<String>();

		Timestamp ts = new Timestamp(new Date().getTime());
		String query = "insert into teach_input values (?,?,?,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ScheduleDAO.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, userid);
			stmt.setInt(2, weekOfYear);
			stmt.setInt(3, starttime);
			stmt.setInt(4, endtime);
			stmt.setInt(5, day);
			stmt.setInt(6, action);
			stmt.setTimestamp(7, ts);
			stmt.setInt(8, year);
			stmt.executeUpdate();
		} catch(SQLException sex) {
			sex.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch(SQLException sex) {
					sex.printStackTrace();
				}
			}if(conn != null) {
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
