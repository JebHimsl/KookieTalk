package com.kookietalk.kt.entity;

import java.sql.Timestamp;

public class Session {

	private int sessionId;
	private int studentId;
	private int teacherId;
	private int year;
	private int week;
	private int day;
	private int time;
	private Timestamp created;
	
	private String studentName;
	private String instructorName;
	private String timeLabel;
	
	

	public Session() {
		
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public String getTimeLabel() {
		return timeLabel;
	}

	public void setTimeLabel(String timeLabel) {
		this.timeLabel = timeLabel;
	}

	@Override
	public String toString() {
		return "Session [sessionId=" + sessionId + ", studentId=" + studentId + ", teacherId=" + teacherId + ", year="
				+ year + ", week=" + week + ", day=" + day + ", time=" + time + ", studentName=" + studentName
				+ ", instructorName=" + instructorName + ", timeLabel=" + timeLabel + "]";
	}
	
	
}
