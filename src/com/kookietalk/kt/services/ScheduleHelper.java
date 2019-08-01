package com.kookietalk.kt.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;

import com.kookietalk.kt.dao.ScheduleDAO;
import com.kookietalk.kt.dao.SessionDAO;
import com.kookietalk.kt.entity.Session;
import com.kookietalk.kt.entity.TeachSession;
import com.kookietalk.kt.model.User;

public class ScheduleHelper {

	// Get session as a string.
	public static String getSchString(TeachSession ts) {
		StringBuffer result = new StringBuffer();
		int start = ts.getStarttime();
		int end = ts.getEndtime();
		int length = 48;
		for (int i = 0; i < length; i++) {
			if (i < start) {
				result.append('0');
			} else if (i >= start && i <= end) {
				result.append('1');
			} else {
				result.append('0');
			}
		}
		return result.toString();
	}

	// Get schedule as an arrayList of Sessions
	/*
	 * public static ArrayList<TeachSession> getSchSessions(ArrayList<String>
	 * dbSchedule) { ArrayList<TeachSession> schedule = new
	 * ArrayList<TeachSession>(); int day = 0; Iterator<String> it =
	 * dbSchedule.iterator(); while (it.hasNext()) { String times = it.next(); int
	 * length = times.length(); boolean inSession = false; int starttime = -1; int
	 * endtime = -1; TeachSession ts = new TeachSession(); ts.setDay(day); boolean
	 * save = false; for (int i = 0; i < length; i++) { char c = times.charAt(i);
	 * 
	 * if (c == '1' && !inSession) { ts.setStarttime(i); inSession = true; save =
	 * true; } else if (c == '0' && inSession) { ts.setEndtime(i - 1); inSession =
	 * false; schedule.add(ts); ts = new TeachSession(); ts.setDay(day); save =
	 * false; } } if (save) { endtime = length - 1; ts.setEndtime(endtime);
	 * schedule.add(ts); } day++; } return schedule; } /
	 **/
	
	public static int getSlot(Calendar cal) {
		int result = 0;
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		result = hour * 2;
		if(min >= 30) {
			result++;
		}
		return result;
	}

	public static String getPeriod(Integer week) {
		String result = null;
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.WEEK_OF_YEAR, week);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		result = CalendarHelper.formatDate(cal.getTime());
		return result;
	}

	public static String getTime(Integer time) {
		StringBuffer result = new StringBuffer();
		String minutes = null;
		if (time % 2 == 0) {
			minutes = ":00";
		} else {
			minutes = ":30";
		}
		int iHour = time / 2;
		if (time >= 26) {
			iHour -= 12;
		}
		String hour = Integer.toString(iHour);
		String ampm = "am";
		if (time >= 24) {
			ampm = "pm";
		}
		result.append(hour).append(minutes).append(ampm);
		return result.toString();
	}

	public static int[] getTimeVals(Integer time) {
		int[] result = new int[2];
		int minutes = 0;
		if (time % 2 == 0) {
			minutes = 0;
		} else {
			minutes = 30;
		}
		int hour = time / 2;
		result[0] = hour;
		result[1] = minutes;
		return result;
	}

	public static Date getDate(int year, int week, int day, int hour, int min) {
		Date result = null;
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, week);
		cal.set(Calendar.DAY_OF_WEEK, day);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		result = cal.getTime();

		return result;
	}

	// not used
	public static ArrayList<String> removeClasses(ArrayList<String> scheduleStrings, ArrayList<Session> toRemove) {
		ArrayList<String> result = new ArrayList<String>();
		Iterator<String> it = scheduleStrings.iterator();
		int currentDay = 0;
		while (it.hasNext()) {
			String current = it.next();
			Iterator<Session> sit = toRemove.iterator();
			while (sit.hasNext()) {
				Session session = sit.next();
				if (session.getDay() == currentDay) {
					int startTime = session.getTime();
					char[] chars = current.toCharArray();
					chars[startTime] = '0';
					current = new String(chars);
				}
			}
			result.add(current);
			currentDay++;
		}
		return result;
	}

	public static void setTaken(ArrayList<Session> taken, ArrayList<TeachSession> learnSessions) {
		Iterator<TeachSession> it = learnSessions.iterator();
		while (it.hasNext()) {
			TeachSession open = it.next();
			int start = open.getStarttime();
			int end = open.getEndtime();
			int day = open.getDay();
			int week = open.getWeekNo();
			int year = open.getYear();
			Iterator<Session> tookIt = taken.iterator();
			while (tookIt.hasNext()) {
				Session took = tookIt.next();
				int startTook = took.getTime();
				int dayTook = took.getDay();
				int weekTook = took.getWeek();
				int yearTook = took.getYear();
				if (dayTook == day && weekTook == week && yearTook == year && start <= startTook && end >= startTook) {
					open.setAction(startTook);
				}
			}

		}

	}

	public static boolean validateDeletion(ArrayList<Session> taken, TeachSession session) {
		boolean result = true;
		Iterator<Session> it = taken.iterator();
		while (it.hasNext()) {
			Session sess = it.next();
			int sessTime = sess.getTime();
			int sessDay = sess.getDay();
			if (sessDay == session.getDay() && session.getStarttime() <= sessTime && session.getEndtime() >= sessTime) {
				result = false;
				break;
			}
		}
		return result;
	}

	public static double getOffsetUnits(TimeZone zone, Date date) {
		double result = 100000.0;
		double offset = (double) (zone.getOffset(date.getTime()));
		result = (offset / 3600000) * 2;
		return result;
	}

	// This method gets the learn sessions based on user's time zone
	public static ArrayList<Session> getRemoteSessions(Integer instructorId, int year, int week, TimeZone zone,
			TimeZone local) {
		ArrayList<Session> result = new ArrayList<Session>();

		//System.out.println("In getRemoteSessions: iId[" + instructorId + "] year[" + year + "] week[" + week + "] zone[" + zone.getID() + "] local[" + local.getID() + "]");

		// Is the user's zone ahead or behind the local zone.
		long offsetDate = new Date().getTime();
		int direction = 1;
		if (zone.getOffset(offsetDate) > local.getOffset(offsetDate)) {
			direction = -1;
		}

		ArrayList<Session> cTaken = SessionDAO.getSessions(instructorId, year, week);
		ArrayList<Session> oTaken = SessionDAO.getSessions(instructorId, year, week + direction);
		ArrayList<Session> o2Taken = SessionDAO.getSessions(instructorId, year, week - direction);

		ArrayList<Session> current = ScheduleHelper.parseRemoteSessions(cTaken, zone, local);
		ArrayList<Session> out = ScheduleHelper.parseRemoteSessions(oTaken, zone, local);
		ArrayList<Session> out2 = ScheduleHelper.parseRemoteSessions(o2Taken, zone, local);

		//System.out.println("Current sessions after parsing: " + current.size());

		// Remove any not in requested week
		Iterator<Session> it2 = current.iterator();
		while (it2.hasNext()) {
			Session session = it2.next();
			//System.out.println("Evaluating session: " + session.toString());
			int sessWeek = session.getWeek();
			if (sessWeek == week) {
				result.add(session);
			}
		}
		Iterator<Session> it = out.iterator();
		while (it.hasNext()) {
			Session session = it.next();
			//System.out.println("Evaluating session_o: " + session.toString());
			int sessWeek = session.getWeek();
			if (sessWeek == week) {
				result.add(session);
			}
		}
		Iterator<Session> it3 = out2.iterator();
		while (it3.hasNext()) {
			Session session = it3.next();
			//System.out.println("Evaluating session_o2: " + session.toString());
			int sessWeek = session.getWeek();
			if (sessWeek == week) {
				result.add(session);
			}
		}

		// result = ScheduleHelper.adjustSession(result, zone, local, true);

		return result;
	}

	public static ArrayList<Session> parseRemoteSessions(ArrayList<Session> taken, TimeZone zone, TimeZone local) {

		// Here we are taking the db taken sessions and converting each date/time for
		// remote zone.

		ArrayList<Session> result = new ArrayList<Session>();

		/*
		 * Iterator<Session> it = taken.iterator(); while (it.hasNext()) {
		 * 
		 * Session sess = it.next(); int year = sess.getYear(); int week =
		 * sess.getWeek(); int day = sess.getDay(); int time = sess.getTime();
		 * 
		 * // the day upon which to calculate the offset (daylight savings factor) Date
		 * offsetDate = ScheduleHelper.getDate(year, week, day + 1, 23, 59);
		 * 
		 * // this is the offSet units to add to 'time' double zoneOffsetUnits =
		 * ScheduleHelper.getOffsetUnits(zone, offsetDate); double localOffsetUnits =
		 * ScheduleHelper.getOffsetUnits(local, offsetDate);
		 * 
		 * int offsetUnits = 0; if (zoneOffsetUnits >= localOffsetUnits) { offsetUnits =
		 * (int) (zoneOffsetUnits - localOffsetUnits); } else { offsetUnits = (int)
		 * (localOffsetUnits - zoneOffsetUnits); }
		 * 
		 * int arrow = 1; if (zoneOffsetUnits >= localOffsetUnits) { arrow = -1; }
		 * offsetUnits *= arrow;
		 * 
		 * int adjTime = time + offsetUnits; int adjDay = day; int adjWeek = week; int
		 * adjYear = year; if (adjTime < 0) { adjTime += 48; adjDay--; if (adjDay < 0) {
		 * adjDay += 7; adjWeek--; if (adjWeek < 0) { adjWeek += 52; adjYear--; } } }
		 * else if (adjTime >= 48) { adjTime -= 48; adjDay++; if (adjDay >= 7) { adjDay
		 * -= 7; adjWeek++; if (adjWeek >= 52) { adjWeek -= 52; adjYear++; } } }
		 * 
		 * time = adjTime; day = adjDay; week = adjWeek; year = adjYear;
		 * 
		 * Session newSess = new Session(); newSess.setCreated(sess.getCreated());
		 * newSess.setDay(day); newSess.setSessionId(sess.getSessionId());
		 * newSess.setStudentId(sess.getStudentId());
		 * newSess.setTeacherId(sess.getTeacherId()); newSess.setTime(time);
		 * newSess.setWeek(week); newSess.setYear(year);
		 * 
		 * result.add(newSess);
		 * 
		 * } /
		 **/

		result = ScheduleHelper.adjustSession(taken, zone, local, true);

		return result;
	}

	public static ArrayList<Session> adjustSession(ArrayList<Session> sessions, TimeZone zone, TimeZone local,
			boolean fromLocal) {
		ArrayList<Session> adjSessions = new ArrayList<Session>();

		Iterator<Session> it = sessions.iterator();
		while (it.hasNext()) {

			Session sess = it.next();
			int year = sess.getYear();
			int week = sess.getWeek();
			int day = sess.getDay();
			int time = sess.getTime();

			//System.out.println("Adjusting session from: " + sess.toString());

			// the day upon which to calculate the offset (daylight savings factor)
			Date offsetDate = new Date();
			//Date offsetDate = ScheduleHelper.getDate(year, week, day + 1, 2, 00);
			//System.out.println("Using OD: " + CalendarHelper.formatDisplay(offsetDate));

			// this is the offSet units to add to 'time'
			double zoneOffsetUnits = ScheduleHelper.getOffsetUnits(zone, offsetDate);
			double localOffsetUnits = ScheduleHelper.getOffsetUnits(local, offsetDate);
			//System.out.println("ZO: " + zoneOffsetUnits);
			//System.out.println("LO: " + localOffsetUnits);
			

			int offsetUnits = 0;
			if (zoneOffsetUnits >= localOffsetUnits) {
				offsetUnits = (int) (zoneOffsetUnits - localOffsetUnits);
			} else {
				offsetUnits = (int) (localOffsetUnits - zoneOffsetUnits);
			}

			// which direction is relative to local or remote
			int arrow = 1;
			if (fromLocal) {
				if (localOffsetUnits >= zoneOffsetUnits) {
					arrow = -1;
				}
			} else {
				if (zoneOffsetUnits >= localOffsetUnits) {
					arrow = -1;
				}
			}
			offsetUnits *= arrow;
			//System.out.println("Offset: " + offsetUnits);

			int adjTime = time + offsetUnits;
			int adjDay = day;
			int adjWeek = week;
			int adjYear = year;
			if (adjTime < 0) {
				adjTime += 48;
				adjDay--;
				if (adjDay < 0) {
					adjDay += 7;
					adjWeek--;
					if (adjWeek < 0) {
						adjWeek += 52;
						adjYear--;
					}
				}
			} else if (adjTime >= 48) {
				adjTime -= 48;
				adjDay++;
				if (adjDay >= 7) {
					adjDay -= 7;
					adjWeek++;
					if (adjWeek >= 52) {
						adjWeek -= 52;
						adjYear++;
					}
				}
			}

			Session newSess = new Session();
			newSess.setCreated(sess.getCreated());
			newSess.setDay(adjDay);
			newSess.setSessionId(sess.getSessionId());
			newSess.setStudentId(sess.getStudentId());
			newSess.setTeacherId(sess.getTeacherId());
			newSess.setTime(adjTime);
			newSess.setWeek(adjWeek);
			newSess.setYear(adjYear);
			newSess.setStudentName(sess.getStudentName());
			newSess.setInstructorName(sess.getInstructorName());

			//System.out.println("Adjusted session to: " + newSess.toString());

			adjSessions.add(newSess);
		}
		/*
		 * Iterator<Session> it = sessions.iterator(); while (it.hasNext()) { Session
		 * sess = it.next();
		 * 
		 * int year = sess.getYear(); int week = sess.getWeek(); int day =
		 * sess.getDay(); int starttime = sess.getTime();
		 * 
		 * System.out.println("Adjusting: " + year + " : " + week + " : " + day + " : "
		 * + starttime);
		 * 
		 * // the day upon which to calculate the offset (daylight savings factor) Date
		 * offsetDate = ScheduleHelper.getDate(year, week, day + 1, 23, 59); //
		 * System.out.println("Offset Date: " + offsetDate.toString());
		 * 
		 * // this is the offSet units to add to 'time' double zoneOffsetUnits =
		 * ScheduleHelper.getOffsetUnits(zone, offsetDate); double localOffsetUnits =
		 * ScheduleHelper.getOffsetUnits(local, offsetDate);
		 * 
		 * int offsetUnits = 0; if (zoneOffsetUnits >= localOffsetUnits) { offsetUnits =
		 * (int) (zoneOffsetUnits - localOffsetUnits); } else { offsetUnits = (int)
		 * (localOffsetUnits - zoneOffsetUnits); }
		 * 
		 * // which direction is relative to local or remote int arrow = 1; if
		 * (fromLocal) { if (localOffsetUnits >= zoneOffsetUnits) { arrow = -1; } } else
		 * { if (zoneOffsetUnits >= localOffsetUnits) { arrow = -1; } } offsetUnits *=
		 * arrow;
		 * 
		 * System.out.println("Local offset units: " + localOffsetUnits);
		 * System.out.println("Zone offset units: " + zoneOffsetUnits);
		 * System.out.println("Calculated offset units: " + offsetUnits);
		 * 
		 * int adjSTime = starttime + offsetUnits; int adjSDay = day; int adjSWeek =
		 * week; int adjSYear = year; if (adjSTime < 0) { adjSTime += 48; adjSDay--; if
		 * (adjSDay < 0) { adjSDay += 7; adjSWeek--; if (adjSWeek < 0) { adjSWeek += 52;
		 * adjSYear--; } } } else if (adjSTime >= 48) { adjSTime -= 48; adjSDay++; if
		 * (adjSDay >= 7) { adjSDay -= 7; adjSWeek++; if (adjSWeek >= 52) { adjSWeek -=
		 * 52; adjSYear++; } } }
		 * 
		 * 
		 * starttime = adjSTime; day = adjSDay; week = adjSWeek; year = adjSYear;
		 * 
		 * System.out.println("To: " + year + " : " + week + " : " + day + " : " +
		 * starttime);
		 * 
		 * Session newSess = new Session(); newSess.setCreated(sess.getCreated());
		 * newSess.setDay(day); newSess.setSessionId(sess.getSessionId());
		 * newSess.setStudentId(sess.getStudentId());
		 * newSess.setTeacherId(sess.getTeacherId()); newSess.setTime(starttime);
		 * newSess.setWeek(week); newSess.setYear(year);
		 * 
		 * adjSessions.add(newSess); } /
		 **/
		return adjSessions;
	}

	// This method gets the teacher sessions based on user's time zone
	public static ArrayList<TeachSession> getRemoteSchSessions(Integer userId, int year, int week, TimeZone zone,
			TimeZone local) {
		ArrayList<TeachSession> result = new ArrayList<TeachSession>();

		// System.out.println("Getting remote view of schedule(" + userId + ", " + year
		// + ", " + week + ", " + zone.getID() + ", " + local.getID());

		// Is the user's zone ahead or behind the local zone.
		long offsetDate = new Date().getTime();
		int direction = 1;
		if (zone.getOffset(offsetDate) > local.getOffset(offsetDate)) {
			direction = -1;
		}

		// if direction is -1, then get the previous week's values, otherwise get them
		// from next week
		ArrayList<String> current = ScheduleDAO.getSchedule(userId, week, year);
		ArrayList<String> out = ScheduleDAO.getSchedule(userId, week + direction, year);
		ArrayList<String> out2 = ScheduleDAO.getSchedule(userId, week - direction, year);

		ArrayList<TeachSession> cSchedule = ScheduleHelper.parseSchSessions(current, zone, local, userId, year, week);
		ArrayList<TeachSession> oSchedule = ScheduleHelper.parseSchSessions(out, zone, local, userId, year,
				week + direction);
		ArrayList<TeachSession> oSchedule2 = ScheduleHelper.parseSchSessions(out2, zone, local, userId, year,
				week - direction);

		// System.out.println("Found current[" + cSchedule.size() + "] out[" +
		// oSchedule.size() + "] out2[" + oSchedule2.size() + "]");

		// iterate and fix hangovers
		Iterator<TeachSession> cIt = cSchedule.iterator();
		while (cIt.hasNext()) {
			TeachSession session = cIt.next();
			// System.out.println("Evaluating ts: " + session);
			int start = session.getStarttime();
			int end = session.getEndtime();
			if (end < start) {
				int sday = session.getDay();
				int sweek = session.getWeekNo();
				int syear = session.getYear();
				TeachSession remainder = new TeachSession(session);
				remainder.setStarttime(0);
				if (sday == 6) {
					if (sweek == 51) {
						remainder.setDay(0);
						remainder.setWeekNo(0);
						;
						remainder.setYear(syear + 1);
					} else {
						remainder.setDay(0);
						remainder.setWeekNo(sweek + 1);
					}
				} else {
					remainder.setDay(sday + 1);
				}
				if (remainder.getWeekNo() == week) {
					result.add(remainder);
				}
				// Close the session end time
				session.setEndtime(47);
			}
			int sessWeek = session.getWeekNo();
			if (sessWeek == week) {
				result.add(session);
			}
		}

		Iterator<TeachSession> it = oSchedule.iterator();
		while (it.hasNext()) {
			TeachSession session = it.next();
			// System.out.println("Evaluating ts: " + session);
			int start = session.getStarttime();
			int end = session.getEndtime();
			if (end < start) {
				TeachSession remainder = new TeachSession(session);
				int sday = session.getDay();
				int sweek = session.getWeekNo();
				int syear = session.getYear();

				remainder.setStarttime(0);
				if (sday == 6) {
					if (sweek == 51) {
						remainder.setDay(0);
						remainder.setWeekNo(0);
						;
						remainder.setYear(syear + 1);
					} else {
						remainder.setDay(0);
						remainder.setWeekNo(sweek + 1);
					}
				} else {
					remainder.setDay(sday + 1);
				}
				if (remainder.getWeekNo() == week) {
					result.add(remainder);
				}
				// Close the session end time
				session.setEndtime(47);
			}
			int sessWeek = session.getWeekNo();
			if (sessWeek == week) {
				result.add(session);
			}
		}
		Iterator<TeachSession> it3 = oSchedule2.iterator();
		while (it3.hasNext()) {
			TeachSession session = it3.next();
			// System.out.println("Evaluating ts: " + session);
			int start = session.getStarttime();
			int end = session.getEndtime();
			if (end < start) {
				session.setEndtime(47);
				int sday = session.getDay();
				int sweek = session.getWeekNo();
				int syear = session.getYear();
				TeachSession remainder = new TeachSession(session);
				remainder.setStarttime(0);
				if (sday == 6) {
					if (sweek == 51) {
						remainder.setDay(0);
						remainder.setWeekNo(0);
						;
						remainder.setYear(syear + 1);
					} else {
						remainder.setDay(0);
						remainder.setWeekNo(sweek + 1);
					}
				} else {
					remainder.setDay(sday + 1);
				}
				if (remainder.getWeekNo() == week) {
					result.add(remainder);
				}
				// Close the session end time
				session.setEndtime(47);
			}
			int sessWeek = session.getWeekNo();
			if (sessWeek == week) {
				result.add(session);
			}
		}

		return result;

	}

	// Get schedule as an arrayList of TeachSessions
	public static ArrayList<TeachSession> parseSchSessions(ArrayList<String> dbSchedule, TimeZone zone, TimeZone local,
			int userId, int year, int week) {

		ArrayList<TeachSession> schedule = new ArrayList<TeachSession>();
		int day = 0;
		Iterator<String> it = dbSchedule.iterator();
		while (it.hasNext()) {
			String times = it.next();
			// System.out.println("Parsing [" + day + "] value [" + times + "]");
			int length = times.length();
			boolean inSession = false;
			int endtime = -1;
			TeachSession ts = new TeachSession();
			ts.setInstructorId(userId);
			ts.setYear(year);
			ts.setWeekNo(week);
			ts.setDay(day);
			boolean save = false;
			for (int i = 0; i < length; i++) {
				char c = times.charAt(i);

				if (c == '1' && !inSession) {
					ts.setStarttime(i);
					inSession = true;
					save = true;
				} else if (c == '0' && inSession) {
					ts.setEndtime(i - 1);
					inSession = false;
					schedule.add(ts);
					ts = new TeachSession();
					ts.setInstructorId(userId);
					ts.setYear(year);
					ts.setWeekNo(week);
					ts.setDay(day);
					save = false;
				}
			}
			if (save) {
				endtime = length - 1;
				ts.setEndtime(endtime);
				schedule.add(ts);
			}
			day++;
		}

		schedule = ScheduleHelper.adjustSchedule(schedule, zone, local, true);

		return schedule;
	}

	public static ArrayList<TeachSession> adjustSchedule(ArrayList<TeachSession> schedule, TimeZone zone,
			TimeZone local, boolean fromLocal) {
		ArrayList<TeachSession> adjSchedule = new ArrayList<TeachSession>();

		Iterator<TeachSession> it = schedule.iterator();
		while (it.hasNext()) {
			TeachSession sess = it.next();

			int year = sess.getYear();
			int week = sess.getWeekNo();
			int day = sess.getDay();
			int starttime = sess.getStarttime();
			int endtime = sess.getEndtime();

			// System.out.println("Adjusting: " + year + " : " + week + " : " + day + " : "
			// + starttime + " : " + endtime);

			// the day upon which to calculate the offset (daylight savings factor)
			Date offsetDate = new Date();
			//Date offsetDate = ScheduleHelper.getDate(year, week, day + 1, 23, 59);
			// System.out.println("Offset Date: " + offsetDate.toString());

			// this is the offSet units to add to 'time'
			double zoneOffsetUnits = ScheduleHelper.getOffsetUnits(zone, offsetDate);
			double localOffsetUnits = ScheduleHelper.getOffsetUnits(local, offsetDate);

			int offsetUnits = 0;
			if (zoneOffsetUnits >= localOffsetUnits) {
				offsetUnits = (int) (zoneOffsetUnits - localOffsetUnits);
			} else {
				offsetUnits = (int) (localOffsetUnits - zoneOffsetUnits);
			}

			// which direction is relative to local or remote
			int arrow = 1;
			if (fromLocal) {
				if (localOffsetUnits >= zoneOffsetUnits) {
					arrow = -1;
				}
			} else {
				if (zoneOffsetUnits >= localOffsetUnits) {
					arrow = -1;
				}
			}
			offsetUnits *= arrow;

			// System.out.println("Local offset units: " + localOffsetUnits);
			// System.out.println("Zone offset units: " + zoneOffsetUnits);
			// System.out.println("Calculated offset units: " + offsetUnits);

			int adjSTime = starttime + offsetUnits;
			int adjSDay = day;
			int adjSWeek = week;
			int adjSYear = year;
			if (adjSTime < 0) {
				adjSTime += 48;
				adjSDay--;
				if (adjSDay < 0) {
					adjSDay += 7;
					adjSWeek--;
					if (adjSWeek < 0) {
						adjSWeek += 52;
						adjSYear--;
					}
				}
			} else if (adjSTime >= 48) {
				adjSTime -= 48;
				adjSDay++;
				if (adjSDay >= 7) {
					adjSDay -= 7;
					adjSWeek++;
					if (adjSWeek >= 52) {
						adjSWeek -= 52;
						adjSYear++;
					}
				}
			}
			int adjETime = endtime + offsetUnits;
			int adjEDay = day;
			int adjEWeek = week;
			@SuppressWarnings("unused")
			int adjEYear = year;
			if (adjETime < 0) {
				adjETime += 48;
				adjEDay--;
				if (adjEDay < 0) {
					adjEDay += 7;
					adjEWeek--;
					if (adjEWeek < 0) {
						adjEWeek += 52;
						adjEYear--;
					}
				}
			} else if (adjETime >= 48) {
				adjETime -= 48;
				adjEDay++;
				if (adjEDay >= 7) {
					adjEDay -= 7;
					adjEWeek++;
					if (adjEWeek >= 52) {
						adjEWeek -= 52;
						adjEYear++;
					}
				}
			}

			endtime = adjETime;
			starttime = adjSTime;
			day = adjSDay;
			week = adjSWeek;
			year = adjSYear;

			// System.out.println("To: " + year + " : " + week + " : " + day + " : " +
			// starttime + " : " + endtime);

			TeachSession newSess = new TeachSession();
			newSess.setAction(sess.getAction());
			newSess.setDay(day);
			newSess.setEndtime(endtime);
			newSess.setInstructorId(sess.getInstructorId());
			newSess.setStarttime(starttime);
			newSess.setWeekNo(week);
			newSess.setYear(year);

			adjSchedule.add(newSess);

		}

		return adjSchedule;
	}

	public static ArrayList<Session> getStudentSessions(User user) {
		ArrayList<Session> result = new ArrayList<Session>();
		
		TimeZone local = TimeZone.getTimeZone("US/Mountain");
		TimeZone zone = TimeZone.getTimeZone(user.getTimezoneId());
		
		int studentId = user.getUserId();

		result = SessionDAO.getStudentSessions(studentId);
		/*
		Iterator<Session> it = result.iterator();
		while (it.hasNext()) {
			Session sess = it.next();
			System.out.println("SS: " + sess.toString());
		}
		/**/

		result = ScheduleHelper.adjustSession(result, zone, local, true);

		return result;
	}
	
	public static ArrayList<Session> getTeacherSessions(User user) {
		ArrayList<Session> result = new ArrayList<Session>();
		
		TimeZone local = TimeZone.getTimeZone("US/Mountain");
		TimeZone zone = TimeZone.getTimeZone(user.getTimezoneId());
		
		int teacherId = user.getUserId();

		result = SessionDAO.getTeacherSessions(teacherId);
		/*
		Iterator<Session> it = result.iterator();
		while (it.hasNext()) {
			Session sess = it.next();
			//System.out.println("SS: " + sess.toString());
		}
		/**/

		result = ScheduleHelper.adjustSession(result, zone, local, true);

		return result;
	}

	public static ArrayList<Session> setRemoteTime(User user, ArrayList<Session> sessions) {
		ArrayList<Session> result = new ArrayList<Session>();

		Iterator<Session> it = sessions.iterator();
		while (it.hasNext()) {

			Session sess = it.next();
			int day = sess.getDay();
			int week = sess.getWeek();
			int year = sess.getYear();
			int time = sess.getTime();
			
			int hour = time / 2;
			int min = 0;
			if(time%2 == 1) {
				min = 30;
			}
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_WEEK, day + 1);// 0 based internal representation... add 1 for Calendar constants.
			cal.set(Calendar.WEEK_OF_YEAR, week);
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.HOUR_OF_DAY, hour);
			cal.set(Calendar.MINUTE, min);
			cal.set(Calendar.SECOND, 0);
			
			Date remoteTime = cal.getTime();
			
			String display = CalendarHelper.formatDisplay(remoteTime);
			//System.out.println("Setting TimeLabel to: " + display);
			sess.setTimeLabel(display);
			result.add(sess);
		}

		return result;
	}

	public static void main(String[] args) {
		System.out.println(getPeriod(7));
		for (int i = 0; i <= 47; i++) {
			System.out.println(getTime(i));
		}

		TimeZone zone = TimeZone.getTimeZone("America/Denver");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_YEAR, 180);
		Date date = cal.getTime();
		double offset = ScheduleHelper.getOffsetUnits(zone, date);
		System.out.println("Offset: " + offset);

		System.out.println("SUNDAY: " + Calendar.SUNDAY);
		System.out.println("MONDAY: " + Calendar.MONDAY);
		System.out.println("TUESDAY: " + Calendar.TUESDAY);
		System.out.println("WEDNESDAY: " + Calendar.WEDNESDAY);
		System.out.println("THURSDAY: " + Calendar.THURSDAY);
		System.out.println("FRIDAY: " + Calendar.FRIDAY);
		System.out.println("SATURDAY: " + Calendar.SATURDAY);

		TeachSession ts = new TeachSession();
		ts.setYear(2019);
		ts.setWeekNo(8);
		ts.setDay(6);
		ts.setStarttime(0);
		ts.setEndtime(41);
		ArrayList<TeachSession> list = new ArrayList<TeachSession>();
		list.add(ts);
		TimeZone local = TimeZone.getTimeZone("America/Detroit");
		ScheduleHelper.adjustSchedule(list, zone, local, false);

	}
}
