package com.kookietalk.kt.entity;

public class TeachSession {

	private int instructorId;
	private int weekNo;
	private int starttime;
	private int endtime;
	private int day;
	private int action;
	private int year;
	private String period;
	private int[] days;
	
	

	public TeachSession() {
		
	}
	
	public TeachSession(TeachSession copy) {
		this.instructorId = copy.getInstructorId();
		this.weekNo = copy.getWeekNo();
		this.starttime = copy.getStarttime();
		this.endtime = copy.getEndtime();
		this.day = copy.getDay();
		this.action = copy.getAction();
		this.year = copy.getYear();
	}

	@Override
	public String toString() {
		return "TeachSession [instructorId=" + instructorId + ", weekNo=" + weekNo + ", starttime=" + starttime
				+ ", endtime=" + endtime + ", day=" + day + ", action=" + action + ", year=" + year + "]";
	}

	public TeachSession(int instructorId, int weekNo, int starttime, int endtime, int day, int year) {
		this.instructorId = instructorId;
		this.weekNo = weekNo;
		this.starttime = starttime;
		this.endtime = endtime;
		this.day = day;
		this.year = year;
	}


	public int[] getDays() {
		return days;
	}

	public void setDays(int[] days) {
		this.days = days;
	}

	public int getInstructorId() {
		return instructorId;
	}


	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
	}


	public int getWeekNo() {
		return weekNo;
	}


	public void setWeekNo(int weekNo) {
		this.weekNo = weekNo;
	}


	public int getStarttime() {
		return starttime;
	}

	public void setStarttime(int starttime) {
		this.starttime = starttime;
	}

	public int getEndtime() {
		return endtime;
	}

	public void setEndtime(int endtime) {
		this.endtime = endtime;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}
}