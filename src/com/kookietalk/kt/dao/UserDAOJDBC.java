package com.kookietalk.kt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import com.kookietalk.kt.model.User;

public class UserDAOJDBC extends BaseDAO implements UserDAO {

	@Override
	public User getUser(Integer userId) {
		User user = null;

		String query = "select a.user_id, a.user_name, a.password, a.email_address, a.first_name, a.last_name, a.middle_name, a.nick_name, a.home_phone, a.mobile_phone, a.work_phone, a.timezone_id, a.dob, a.cv, b.role from user a, user_role b where a.user_id=b.user_id and a.user_id = ?;";
		String query2 = "select * from user_address where user_id=? and active='Y'";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, userId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setConfirmPwd(rs.getString("password"));
				user.setEmailAddress(rs.getString("email_address"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setMiddleName(rs.getString("middle_name"));
				user.setNickName(rs.getString("nick_name"));
				user.sethPhone(rs.getString("home_phone"));
				user.setmPhone(rs.getString("mobile_phone"));
				user.setwPhone(rs.getString("work_phone"));
				user.setTimezoneId(rs.getString("timezone_id"));
				user.setDateOfBirth(rs.getDate("dob"));
				user.setCv(rs.getString("cv"));
				user.setRole(rs.getString("role"));
			}
			rs.close();
			stmt.close();
			if (user != null) {
				stmt = conn.prepareStatement(query2);
				stmt.setInt(1, userId);
				rs = stmt.executeQuery();
				user.setmUse(true);
				while (rs.next()) {
					String type = rs.getString("type");

					if (type != null && type.equals("M")) {
						user.setmAddress1(rs.getString("line1"));
						user.setmAddress2(rs.getString("line2"));
						user.setmCity(rs.getString("city"));
						user.setmState(rs.getString("state"));
						user.setmCountry(rs.getString("country"));
						user.setmZip(rs.getString("zip"));
					} else {
						user.setmUse(false);
						user.setbAddress1(rs.getString("line1"));
						user.setbAddress2(rs.getString("line2"));
						user.setbCity(rs.getString("city"));
						user.setbState(rs.getString("state"));
						user.setbCountry(rs.getString("country"));
						user.setbZip(rs.getString("zip"));
					}
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

		return user;
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveAdmin(User user) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
		PreparedStatement stmt4 = null;
		ResultSet rs2 = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String query = "insert into user values (null,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			stmt1 = conn.prepareStatement(query);
			stmt1.setString(1, user.getEmailAddress());
			stmt1.setString(2, user.getPassword());
			stmt1.setString(3, user.getEmailAddress());
			stmt1.setString(4, user.getFirstName());
			stmt1.setString(5, user.getLastName());
			stmt1.setString(6, user.getMiddleName());
			stmt1.setString(7, user.getNickName());
			stmt1.setString(8, user.gethPhone());
			stmt1.setString(9, user.getmPhone());
			stmt1.setString(10, user.getwPhone());
			stmt1.setString(11, user.getTimezoneId());
			stmt1.setNull(12,  Types.DATE);
			stmt1.setNull(13, Types.LONGVARCHAR);
			stmt1.executeUpdate();

			// Now get the user_id for the recently entered user_name
			stmt2 = conn.prepareStatement("select user_id from user where user_name=?");
			stmt2.setString(1, user.getEmailAddress());
			rs2 = stmt2.executeQuery();
			int userId = -1;
			if (rs2.next()) {
				userId = rs2.getInt("USER_ID");
			}
			if (userId == -1) {
				System.out.println("Failed to get user id.");
			}

			// Now insert the role
			stmt3 = conn.prepareStatement("insert into user_role values (?,?)");
			stmt3.setInt(1, userId);
			stmt3.setString(2, "ROLE_ADMIN");
			stmt3.executeUpdate();

			conn.commit();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
				System.out.println("Rolled back transaction.");
			} catch (SQLException sex) {
				System.out.println("Failed to rollback.");
				sex.printStackTrace();
			}
		} finally {

			if (rs2 != null) {
				try {
					rs2.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if (stmt1 != null) {
				try {
					stmt1.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if (stmt2 != null) {
				try {
					stmt2.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if (stmt3 != null) {
				try {
					stmt3.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if (stmt4 != null) {
				try {
					stmt4.close();
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

	@Override
	public boolean saveStudent(User user) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
		PreparedStatement stmt4 = null;
		PreparedStatement stmt5 = null;
		ResultSet rs2 = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String query = "insert into user values (null,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			stmt1 = conn.prepareStatement(query);
			stmt1.setString(1, user.getEmailAddress());
			stmt1.setString(2, user.getPassword());
			stmt1.setString(3, user.getEmailAddress());
			stmt1.setString(4, user.getFirstName());
			stmt1.setString(5, user.getLastName());
			stmt1.setString(6, user.getMiddleName());
			stmt1.setString(7, user.getNickName());
			stmt1.setString(8, user.gethPhone());
			stmt1.setString(9, user.getmPhone());
			stmt1.setString(10, user.getwPhone());
			stmt1.setString(11, user.getTimezoneId());
			stmt1.setNull(12, Types.DATE);
			stmt1.setNull(13, Types.LONGVARCHAR);
			stmt1.executeUpdate();

			// Now get the user_id for the recently entered user_name
			stmt2 = conn.prepareStatement("select user_id from user where user_name=?");
			stmt2.setString(1, user.getEmailAddress());
			rs2 = stmt2.executeQuery();
			int userId = -1;
			if (rs2.next()) {
				userId = rs2.getInt("USER_ID");
			}
			if (userId == -1) {
				System.out.println("Failed to get user id.");
			}

			// Now insert the role
			stmt3 = conn.prepareStatement("insert into user_role values (?,?)");
			stmt3.setInt(1, userId);
			stmt3.setString(2, "ROLE_LEARN");
			stmt3.executeUpdate();

			// Now insert the address(es)
			stmt4 = conn.prepareStatement("insert into user_address values (?,?,?,?,?,?,?,?,?)");
			stmt4.setInt(1, userId);
			stmt4.setString(2, "Y");
			stmt4.setString(3, user.getmAddress1());
			stmt4.setString(4, user.getmAddress2());
			stmt4.setString(5, user.getmCity());
			stmt4.setString(6, user.getmState());
			stmt4.setString(7, user.getmCountry());
			stmt4.setString(8, user.getmZip());
			stmt4.setString(9, "M");
			stmt4.executeUpdate();
			if (!user.ismUse()) {
				stmt4.setInt(1, userId);
				stmt4.setString(2, "Y");
				stmt4.setString(3, user.getbAddress1());
				stmt4.setString(4, user.getbAddress2());
				stmt4.setString(5, user.getbCity());
				stmt4.setString(6, user.getbState());
				stmt4.setString(7, user.getbCountry());
				stmt4.setString(8, user.getbZip());
				stmt4.setString(9, "B");
				stmt4.executeUpdate();
			}

			// Now insert the status
			stmt5 = conn.prepareStatement("insert into learn_status values (?,?,?,?,?,?);");
			stmt5.setInt(1, userId);
			stmt5.setDouble(2, 0);
			stmt5.setInt(3, 1);
			stmt5.setInt(4, 1);
			stmt5.setInt(5, 1);
			stmt5.setInt(6, 1);
			int sinsert = stmt5.executeUpdate();
			System.out.println("Status update: " + sinsert);

			conn.commit();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
				System.out.println("Rolled back transaction.");
			} catch (SQLException sex) {
				System.out.println("Failed to rollback.");
				sex.printStackTrace();
			}
		} finally {

			if (rs2 != null) {
				try {
					rs2.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if (stmt1 != null) {
				try {
					stmt1.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if (stmt2 != null) {
				try {
					stmt2.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if (stmt3 != null) {
				try {
					stmt3.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if (stmt4 != null) {
				try {
					stmt4.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if (stmt5 != null) {
				try {
					stmt5.close();
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

	@Override
	public boolean deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUserByEmail(String email) {
		User user = null;

		String query = "select a.user_id, a.user_name, a.password, a.email_address, a.first_name, a.last_name, a.middle_name, a.nick_name, a.home_phone, a.mobile_phone, a.work_phone, a.timezone_id, a.dob, a.cv, b.role from user a, user_role b where a.user_id=b.user_id and upper(a.email_address) = ?;";
		String query2 = "select * from user_address where user_id=? and active='Y'";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, email.toUpperCase());
			rs = stmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setConfirmPwd(rs.getString("password"));
				user.setEmailAddress(rs.getString("email_address"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setMiddleName(rs.getString("middle_name"));
				user.setNickName(rs.getString("nick_name"));
				user.sethPhone(rs.getString("home_phone"));
				user.setmPhone(rs.getString("mobile_phone"));
				user.setwPhone(rs.getString("work_phone"));
				user.setTimezoneId(rs.getString("timezone_id"));
				user.setDateOfBirth(rs.getDate("dob"));
				user.setCv(rs.getString("cv"));
				user.setRole(rs.getString("role"));
			}
			rs.close();
			stmt.close();
			if (user != null) {
				stmt = conn.prepareStatement(query2);
				stmt.setInt(1, user.getUserId());
				rs = stmt.executeQuery();
				user.setmUse(true);
				while (rs.next()) {
					String type = rs.getString("type");

					if (type != null && type.equals("M")) {
						user.setmAddress1(rs.getString("line1"));
						user.setmAddress2(rs.getString("line2"));
						user.setmCity(rs.getString("city"));
						user.setmState(rs.getString("state"));
						user.setmCountry(rs.getString("country"));
						user.setmZip(rs.getString("zip"));
					} else {
						user.setmUse(false);
						user.setbAddress1(rs.getString("line1"));
						user.setbAddress2(rs.getString("line2"));
						user.setbCity(rs.getString("city"));
						user.setbState(rs.getString("state"));
						user.setbCountry(rs.getString("country"));
						user.setbZip(rs.getString("zip"));
					}
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

		return user;
	}

	@Override
	public boolean saveTeacher(User user) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
		PreparedStatement stmt4 = null;
		ResultSet rs2 = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String query = "insert into user values (null,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			stmt1 = conn.prepareStatement(query);
			stmt1.setString(1, user.getEmailAddress());
			stmt1.setString(2, user.getPassword());
			stmt1.setString(3, user.getEmailAddress());
			stmt1.setString(4, user.getFirstName());
			stmt1.setString(5, user.getLastName());
			stmt1.setString(6, user.getMiddleName());
			stmt1.setString(7, user.getNickName());
			stmt1.setString(8, user.gethPhone());
			stmt1.setString(9, user.getmPhone());
			stmt1.setString(10, user.getwPhone());
			stmt1.setString(11, user.getTimezoneId());
			stmt1.setNull(12, Types.DATE);
			stmt1.setNull(13, Types.LONGVARCHAR);
			stmt1.executeUpdate();

			// Now get the user_id for the recently entered user_name
			stmt2 = conn.prepareStatement("select user_id from user where user_name=?");
			stmt2.setString(1, user.getEmailAddress());
			rs2 = stmt2.executeQuery();
			int userId = -1;
			if (rs2.next()) {
				userId = rs2.getInt("USER_ID");
			}
			if (userId == -1) {
				System.out.println("Failed to get user id.");
			}

			// Now insert the role
			stmt3 = conn.prepareStatement("insert into user_role values (?,?)");
			stmt3.setInt(1, userId);
			stmt3.setString(2, "ROLE_TEACH");
			stmt3.executeUpdate();

			// Now insert the address(es)
			stmt4 = conn.prepareStatement("insert into user_address values (?,?,?,?,?,?,?,?,?)");
			stmt4.setInt(1, userId);
			stmt4.setString(2, "Y");
			stmt4.setString(3, user.getmAddress1());
			stmt4.setString(4, user.getmAddress2());
			stmt4.setString(5, user.getmCity());
			stmt4.setString(6, user.getmState());
			stmt4.setString(7, user.getmCountry());
			stmt4.setString(8, user.getmZip());
			stmt4.setString(9, "M");
			stmt4.executeUpdate();
			if (!user.ismUse()) {
				stmt4.setInt(1, userId);
				stmt4.setString(2, "Y");
				stmt4.setString(3, user.getbAddress1());
				stmt4.setString(4, user.getbAddress2());
				stmt4.setString(5, user.getbCity());
				stmt4.setString(6, user.getbState());
				stmt4.setString(7, user.getbCountry());
				stmt4.setString(8, user.getbZip());
				stmt4.setString(9, "B");
				stmt4.executeUpdate();
			}

			conn.commit();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
				System.out.println("Rolled back transaction.");
			} catch (SQLException sex) {
				System.out.println("Failed to rollback.");
				sex.printStackTrace();
			}
		} finally {

			if (rs2 != null) {
				try {
					rs2.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if (stmt1 != null) {
				try {
					stmt1.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if (stmt2 != null) {
				try {
					stmt2.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if (stmt3 != null) {
				try {
					stmt3.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if (stmt4 != null) {
				try {
					stmt4.close();
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

	public boolean updateStudent(User user) {

		//System.out.println("In updateStudent(" + user + ")");
		boolean result = false;
		Connection conn = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
		PreparedStatement stmt4 = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String query = "update user set user_name = ?, password = ?, email_address= ?, first_name = ?, last_name = ?, middle_name = ?, nick_name = ?, home_phone = ?, mobile_phone = ?, work_phone = ?, timezone_id = ?, dob = ?, cv = ?  where user_id = ?";
			stmt1 = conn.prepareStatement(query);
			stmt1.setString(1, user.getEmailAddress());
			stmt1.setString(2, user.getPassword());
			stmt1.setString(3, user.getEmailAddress());
			stmt1.setString(4, user.getFirstName());
			stmt1.setString(5, user.getLastName());
			stmt1.setString(6, user.getMiddleName());
			stmt1.setString(7, user.getNickName());
			stmt1.setString(8, user.gethPhone());
			stmt1.setString(9, user.getmPhone());
			stmt1.setString(10, user.getwPhone());
			stmt1.setString(11, user.getTimezoneId());
			//System.out.println("Submitted date: " + user.getDateOfBirth());
			stmt1.setDate(12, new java.sql.Date(user.getDateOfBirth().getTime()));
			stmt1.setString(13, user.getCv());
			stmt1.setInt(14, user.getUserId());
			stmt1.executeUpdate();

			// Now update the address(es)
			// first, inactivate the old addresses
			stmt2 = conn.prepareStatement("update user_address set active = 'N' where user_id=?");
			stmt2.setInt(1, user.getUserId());
			stmt2.executeUpdate();

			stmt4 = conn.prepareStatement("insert into user_address values (?,?,?,?,?,?,?,?,?)");
			stmt4.setInt(1, user.getUserId());
			stmt4.setString(2, "Y");
			stmt4.setString(3, user.getmAddress1());
			stmt4.setString(4, user.getmAddress2());
			stmt4.setString(5, user.getmCity());
			stmt4.setString(6, user.getmState());
			stmt4.setString(7, user.getmCountry());
			stmt4.setString(8, user.getmZip());
			stmt4.setString(9, "M");
			stmt4.executeUpdate();
			if (!user.ismUse()) {
				stmt4.setInt(1, user.getUserId());
				stmt4.setString(2, "Y");
				stmt4.setString(3, user.getbAddress1());
				stmt4.setString(4, user.getbAddress2());
				stmt4.setString(5, user.getbCity());
				stmt4.setString(6, user.getbState());
				stmt4.setString(7, user.getbCountry());
				stmt4.setString(8, user.getbZip());
				stmt4.setString(9, "B");
				stmt4.executeUpdate();
			}

			conn.commit();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
				System.out.println("Rolled back transaction.");
			} catch (SQLException sex) {
				System.out.println("Failed to rollback.");
				sex.printStackTrace();
			}
		} finally {
			if (stmt1 != null) {
				try {
					stmt1.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if (stmt2 != null) {
				try {
					stmt2.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if (stmt3 != null) {
				try {
					stmt3.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if (stmt4 != null) {
				try {
					stmt4.close();
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
	
	public boolean updateTeacher(User user) {

		//System.out.println("In updateStudent(" + user + ")");
		boolean result = false;
		Connection conn = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
		PreparedStatement stmt4 = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String query = "update user set user_name = ?, password = ?, email_address= ?, first_name = ?, last_name = ?, middle_name = ?, nick_name = ?, home_phone = ?, mobile_phone = ?, work_phone = ?, timezone_id = ?, dob = ?, cv = ?  where user_id = ?";
			stmt1 = conn.prepareStatement(query);
			stmt1.setString(1, user.getEmailAddress());
			stmt1.setString(2, user.getPassword());
			stmt1.setString(3, user.getEmailAddress());
			stmt1.setString(4, user.getFirstName());
			stmt1.setString(5, user.getLastName());
			stmt1.setString(6, user.getMiddleName());
			stmt1.setString(7, user.getNickName());
			stmt1.setString(8, user.gethPhone());
			stmt1.setString(9, user.getmPhone());
			stmt1.setString(10, user.getwPhone());
			stmt1.setString(11, user.getTimezoneId());
			//System.out.println("Submitted date: " + user.getDateOfBirth());
			stmt1.setDate(12, new java.sql.Date(user.getDateOfBirth().getTime()));
			stmt1.setString(13, user.getCv());
			stmt1.setInt(14, user.getUserId());
			stmt1.executeUpdate();

			// Now update the address(es)
			// first, inactivate the old addresses
			stmt2 = conn.prepareStatement("update user_address set active = 'N' where user_id=?");
			stmt2.setInt(1, user.getUserId());
			stmt2.executeUpdate();

			stmt4 = conn.prepareStatement("insert into user_address values (?,?,?,?,?,?,?,?,?)");
			stmt4.setInt(1, user.getUserId());
			stmt4.setString(2, "Y");
			stmt4.setString(3, user.getmAddress1());
			stmt4.setString(4, user.getmAddress2());
			stmt4.setString(5, user.getmCity());
			stmt4.setString(6, user.getmState());
			stmt4.setString(7, user.getmCountry());
			stmt4.setString(8, user.getmZip());
			stmt4.setString(9, "M");
			stmt4.executeUpdate();
			if (!user.ismUse()) {
				stmt4.setInt(1, user.getUserId());
				stmt4.setString(2, "Y");
				stmt4.setString(3, user.getbAddress1());
				stmt4.setString(4, user.getbAddress2());
				stmt4.setString(5, user.getbCity());
				stmt4.setString(6, user.getbState());
				stmt4.setString(7, user.getbCountry());
				stmt4.setString(8, user.getbZip());
				stmt4.setString(9, "B");
				stmt4.executeUpdate();
			}

			conn.commit();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
				System.out.println("Rolled back transaction.");
			} catch (SQLException sex) {
				System.out.println("Failed to rollback.");
				sex.printStackTrace();
			}
		} finally {
			if (stmt1 != null) {
				try {
					stmt1.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if (stmt2 != null) {
				try {
					stmt2.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if (stmt3 != null) {
				try {
					stmt3.close();
				} catch (SQLException sex) {
					sex.printStackTrace();
				}
			}
			if (stmt4 != null) {
				try {
					stmt4.close();
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
	
	public static String getUserRole(Integer uId) {
		String result = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select role from user_role where user_id=?";
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, uId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				result = rs.getString("role");
			}
		}catch(SQLException sex) {
			sex.printStackTrace();
		}finally {
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

}
