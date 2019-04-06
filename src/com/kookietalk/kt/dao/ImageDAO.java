package com.kookietalk.kt.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.Part;

import com.kookietalk.kt.entity.Image;
import com.kookietalk.kt.model.User;

public class ImageDAO extends BaseDAO {
	
	public static Image getUserPhoto(Integer iId) {
		Image image = null;
		
		String query = "select * from image where id=? and type='userPhoto' order by created desc";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, iId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				image = new Image();
				image.setImageId(rs.getInt("image_id"));
				image.setId(rs.getInt("id"));
				image.setLabel(rs.getString("label"));
				image.setType(rs.getString("type"));
				image.setCreated(rs.getTimestamp("created"));
				Blob blob = rs.getBlob("image");
				image.setImage(blob.getBytes(1, (int)blob.length()));
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
		
		return image;
	}
	
	public static Image getImage(Integer iId) {
		Image image = null;
		
		String query = "select * from image where image_id=?";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, iId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				image = new Image();
				image.setImageId(rs.getInt("image_id"));
				image.setId(rs.getInt("id"));
				image.setLabel(rs.getString("label"));
				image.setType(rs.getString("type"));
				image.setCreated(rs.getTimestamp("created"));
				Blob blob = rs.getBlob("image");
				image.setImage(blob.getBytes(1, (int)blob.length()));
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
		
		return image;
	}
	
	public static boolean setImage(InputStream is, int userId, String type, String label) {
		
		System.out.println("In setImage: " + is + ":" + userId + ":" + type + ":" + label);
		boolean result = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String query = "insert into image (id, image, type, created, label) values (?,?,?,?,?)";
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, userId);
			stmt.setBinaryStream(2, is);
			stmt.setString(3, type);
			stmt.setTimestamp(4, new Timestamp(new Date().getTime()));
			stmt.setString(5, label);
			stmt.executeUpdate();
			result = true;
			
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
