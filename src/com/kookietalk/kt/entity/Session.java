package com.kookietalk.kt.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import com.kookietalk.kt.dao.UserDAOJDBC;
import com.kookietalk.kt.model.User;

public class Session {

	private int sessionId;
	private int studentId;
	private int teacherId;
	private int year;
	private int week;
	private int day;
	private int time;
	private Timestamp created;
	private int closed;

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
	
	public int getClosed() {
		return closed;
	}

	public void setClosed(int closed) {
		this.closed = closed;
	}

	public boolean isTimeToJoin() {
		boolean result = false;
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, week);
		cal.set(Calendar.DAY_OF_WEEK, day + 1);
		int hour = time / 2;
		int minute = time % 2 == 1 ? 30 : 0;
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, 0);
		long calTime = cal.getTimeInMillis();
		long curTime = new Date().getTime();
		long beforebuffer = 10L * 60 * 1000;
		long afterbuffer = 30L * 60 * 1000;
		if (calTime <= curTime + beforebuffer && curTime <= calTime + afterbuffer) {
			result = true;
		}
		return result;
	}

	public boolean isTimeToReview() {
		boolean result = false;
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, week);
		cal.set(Calendar.DAY_OF_WEEK, day + 1);
		int hour = time / 2;
		int minute = time % 2 == 1 ? 30 : 0;
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, 0);
		long calTime = cal.getTimeInMillis();
		long curTime = new Date().getTime();
		if (curTime >= calTime) {
			result = true;
		}
		return result;
	}

	public String getSessionStartTime(String type) {
		String result = null;

		String timezoneId = null;
		User user = null;
		UserDAOJDBC udao = new UserDAOJDBC();
		if (type.equals("student")) {
			user = udao.getUser(studentId);
		} else if (type.equals("teacher")){
			user = udao.getUser(teacherId);
		}
		if (user != null) {
			timezoneId = user.getTimezoneId();
		}
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, week);
		cal.set(Calendar.DAY_OF_WEEK, day + 1);
		int hour = time / 2;
		int minute = time % 2 == 1 ? 30 : 0;
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, 0);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-M-yyyy hh:mm:ss a z");
		ZoneId toTimeZone = ZoneId.of(timezoneId);
		LocalDateTime ldt = LocalDateTime.of(year, cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), hour, minute);
		ZonedDateTime zldt = ldt.atZone(TimeZone.getDefault().toZoneId());
		ZonedDateTime zdt = zldt.withZoneSameInstant(toTimeZone);

		result = formatter.format(zdt);
		return result;
	}
	
	

	public String getSessionStart() {
		String result = null;

		String timezoneId = TimeZone.getDefault().getID();
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, week);
		cal.set(Calendar.DAY_OF_WEEK, day + 1);
		int hour = time / 2;
		int minute = time % 2 == 1 ? 30 : 0;
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, 0);

		DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
		ZoneId toTimeZone = ZoneId.of(timezoneId);
		LocalDateTime ldt = LocalDateTime.of(year, cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), hour, minute);
		ZonedDateTime zldt = ldt.atZone(TimeZone.getDefault().toZoneId());
		ZonedDateTime zdt = zldt.withZoneSameInstant(toTimeZone);

		result = formatter.format(zdt);
		return result;
	}
	
	public String getSessionExpiration() {
		String result = null;

		String timezoneId = TimeZone.getDefault().getID();
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, week);
		cal.set(Calendar.DAY_OF_WEEK, day + 1);
		int hour = time / 2;
		int minute = time % 2 == 1 ? 30 : 0;
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, 0);
		
		// let session expiration be in 3 days from start time 
		cal.add(Calendar.DAY_OF_WEEK, 3);

		DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
		ZoneId toTimeZone = ZoneId.of(timezoneId);
		LocalDateTime ldt = LocalDateTime.of(year, cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), hour, minute);
		ZonedDateTime zldt = ldt.atZone(TimeZone.getDefault().toZoneId());
		ZonedDateTime zdt = zldt.withZoneSameInstant(toTimeZone);

		result = formatter.format(zdt);
		return result;
	}

	@Override
	public String toString() {
		return "Session [sessionId=" + sessionId + ", studentId=" + studentId + ", teacherId=" + teacherId + ", year=" + year + ", week=" + week + ", day=" + day + ", time=" + time + ", studentName="
				+ studentName + ", instructorName=" + instructorName + ", timeLabel=" + timeLabel + "]";
	}

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		long time = cal.getTimeInMillis();
		Date date = new Date(time);
		/*
		 * System.out.println(date); System.out.println(cal.get(Calendar.YEAR));
		 * System.out.println(cal.get(Calendar.WEEK_OF_YEAR));
		 * System.out.println(cal.get(Calendar.DAY_OF_WEEK));
		 * System.out.println(cal.get(Calendar.HOUR_OF_DAY));
		 * System.out.println(cal.get(Calendar.MINUTE));
		 * System.out.println(cal.get(Calendar.SECOND)); /
		 **/
		Session sess = new Session();
		sess.setYear(2019);
		sess.setWeek(21);
		sess.setDay(3);
		sess.setTime(46);
		sess.setStudentId(1);
		sess.setTeacherId(2);

		System.out.println(sess.isTimeToJoin());
		System.out.println(sess.isTimeToReview());

		System.out.println("Student time: " + sess.getSessionStartTime("student"));
		System.out.println("Teacher time: " + sess.getSessionStartTime("teacher"));

	}

}
