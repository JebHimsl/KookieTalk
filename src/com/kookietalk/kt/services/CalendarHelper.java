package com.kookietalk.kt.services;

import java.text.SimpleDateFormat;
import java.util.*;

public class CalendarHelper {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CalendarHelper.showPeriods();
		System.out.println(CalendarHelper.formatDisplay(new Date()));
		
	}

	public static String formatDate(Date date) {
		String result = null;
		SimpleDateFormat sdf = new SimpleDateFormat("E yyyy-MMM-dd");
		result = sdf.format(date);
		return result;
	}
	
	public static String formatDisplay(Date date) {
		String result = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a ");
		result = sdf.format(date);
		return result;
	}

	public static void showPeriods() {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		String lastPeriod = CalendarHelper.formatDate(cal.getTime());
		System.out.println("lastPeriod: " + lastPeriod);
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		String nextPeriod = CalendarHelper.formatDate(cal.getTime());
		System.out.println("nextPeriod: " + nextPeriod);
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		String secondPeriod = CalendarHelper.formatDate(cal.getTime());
		System.out.println("secondPeriod: " + secondPeriod);
	}
}
