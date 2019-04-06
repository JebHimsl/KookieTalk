package com.kookietalk.kt.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kookietalk.kt.dao.FinancialDAO;
import com.kookietalk.kt.dao.InstructorDAO;
import com.kookietalk.kt.dao.ScheduleDAO;
import com.kookietalk.kt.dao.SessionDAO;
import com.kookietalk.kt.dao.UserDAOJDBC;
import com.kookietalk.kt.entity.Instructor;
import com.kookietalk.kt.entity.Session;
import com.kookietalk.kt.entity.TeachSession;
import com.kookietalk.kt.model.User;
import com.kookietalk.kt.services.CalendarHelper;
import com.kookietalk.kt.services.ScheduleHelper;

@Controller
@RequestMapping("/session")
public class SessionController {
	
	@RequestMapping("/wb")
	public String whiteboard() {

		
		return "wboard";
	}

	@RequestMapping("/learnmedia")
	public String learnMedia(HttpSession session, Model model, @RequestParam("sessionId") Integer sessionId,
			@RequestParam("submit") String submit) {

		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/login";
		}
		
		model.addAttribute("sessionId", sessionId);
		model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());

		String returnVal = "chat";
		if (submit.equals("Review")) {
			returnVal = "learnReview";
		}
		if (submit.equals("Replay")) {
			returnVal = "learnReplay";
		}
		return returnVal;
	}
	
	@RequestMapping("/teachmedia")
	public String teachMedia(HttpSession session, Model model, @RequestParam("sessionId") Integer sessionId, @RequestParam("submit") String submit) {

		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/login";
		}

		model.addAttribute("sessionId", sessionId);
		model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());

		String returnVal = "chat";
		if (submit.equals("Review")) {
			returnVal = "teachReview";
		}
		if (submit.equals("Replay")) {
			returnVal = "teachReplay";
		}
		return returnVal;
	}

	@RequestMapping("/classes")
	public String classes(HttpSession session, Model model) {
		// ArrayList<Session> sessions = ScheduleHelper.getRemoteSessions(instructorId,
		// year, week, zone, local);
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/login";
		}

		ArrayList<Session> sessions = ScheduleHelper.getStudentSessions(user);
		ArrayList<Session> remoteSessions = ScheduleHelper.setRemoteTime(user, sessions);
		model.addAttribute("studentSessions", remoteSessions);

		return "learnClasses";
	}

	// Called by student
	// This method gets the instructors available to teach a session
	@RequestMapping("/instructors")
	public String instructors(Model model) {
		ArrayList<Instructor> list = InstructorDAO.getInstructors();
		model.addAttribute("instructors", list);
		return "learnInstructors";
	}

	// Called by student
	@RequestMapping("/instructorSchedule")
	public String instructorschedule(HttpSession session, Model model, @RequestParam("instructor_id") Integer iId,
			@RequestParam("week") Integer week, @RequestParam("year") Integer year, HttpServletRequest request) {

		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/login";
		}

		// These inputs are for the user's time zone, adjust before saving
		TimeZone zone = TimeZone.getTimeZone(user.getTimezoneId());
		TimeZone local = TimeZone.getTimeZone("US/Mountain");

		// get the schedule for this instructor_id
		//System.out.println("Got instructor id: " + iId + " for week:  " + week);

		ArrayList<TeachSession> learnSessions = ScheduleHelper.getRemoteSchSessions(iId, year, week, zone, local);

		ArrayList<Session> adjTaken = ScheduleHelper.getRemoteSessions(iId, year, week, zone, local);

		model.addAttribute("taken", adjTaken);
		model.addAttribute("learnSessions", learnSessions);
		model.addAttribute("period", ScheduleHelper.getPeriod(week));
		model.addAttribute("week", week);
		model.addAttribute("year", year);
		model.addAttribute("instructor_id", iId);
		return "learnSchedule";
	}

	@RequestMapping("/instructorBio")
	public String instructorBio(Model model, @RequestParam("instructor_id") Integer iId) {
		
		System.out.println("Got instructor id: " + iId);
		UserDAOJDBC udao = new UserDAOJDBC();
		User instructor = udao.getUser(iId);
		model.addAttribute("instructor", instructor);

		return "learnBio";
	}

	@RequestMapping("/confirmSession")
	public String confirmSession(HttpSession session, Model model, @RequestParam("instructor_id") Integer iId,
			@RequestParam("day") Integer day, @RequestParam("time") Integer time, @RequestParam("week") Integer week,
			@RequestParam("year") Integer year) {

		// HttpSession dependency, validate session
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/login";
		}

		//System.out.println("Year: " + year + " Week: " + week + " Day: " + day + " Time: " + time);
		//System.out.println("Instructor ID: " + iId);

		// Get Credits for this user

		int credits = FinancialDAO.getCredits(user.getUserId());

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, week);
		cal.set(Calendar.DAY_OF_WEEK, day + 1);
		String date = CalendarHelper.formatDate(cal.getTime());
		String from = ScheduleHelper.getTime(time) + " to " + ScheduleHelper.getTime(time + 1);
		Instructor instructor = InstructorDAO.getInstructor(iId);
		model.addAttribute("instructorName", instructor.getFirstName() + " " + instructor.getLastName());
		model.addAttribute("date", date);
		model.addAttribute("from", from);
		model.addAttribute("credits", credits);
		model.addAttribute("year", year);
		model.addAttribute("week", week);
		model.addAttribute("day", day);
		model.addAttribute("time", time);
		model.addAttribute("student_id", user.getUserId());
		model.addAttribute("instructor_id", iId);
		return "learnConfirm";
	}

	@RequestMapping("/saveSession")
	public String saveSession(HttpSession session, Model model, @RequestParam("instructor_id") Integer iId,
			@RequestParam("student_id") Integer sId, @RequestParam("day") Integer day,
			@RequestParam("time") Integer time, @RequestParam("week") Integer week,
			@RequestParam("year") Integer year) {

		// HttpSession dependency, validate session
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/login";
		}

		// These inputs are for the user's time zone, adjust before saving
		TimeZone zone = TimeZone.getTimeZone(user.getTimezoneId());
		TimeZone local = TimeZone.getTimeZone("US/Mountain");

		//System.out.println("Student data in: " + year + " : " + week + " : " + day + " : " + time);

		// day is based on 0..6, add 1 to equal calendar day for date calculations
		// the day upon which to calculate the offset (daylight savings factor)
		Date offsetDate = new Date();
		// Date offsetDate = ScheduleHelper.getDate(year, week + 1, day + 1, 0, 0);

		// this is the offSet units to add to 'time'
		double zoneOffsetUnits = ScheduleHelper.getOffsetUnits(zone, offsetDate);
		double localOffsetUnits = ScheduleHelper.getOffsetUnits(local, offsetDate);

		// which direction is relative to local or remote
		boolean fromLocal = false;

		int offsetUnits = 0;
		if (zoneOffsetUnits >= localOffsetUnits) {
			offsetUnits = (int) (zoneOffsetUnits - localOffsetUnits);
		} else {
			offsetUnits = (int) (localOffsetUnits - zoneOffsetUnits);
		}

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
		//System.out.println("Offset in S_C_saveSession: " + offsetUnits);

		//
		/*
		 * int offsetUnits = 0; if (zoneOffsetUnits >= localOffsetUnits) { offsetUnits =
		 * (int) (zoneOffsetUnits - localOffsetUnits); } else { offsetUnits = (int)
		 * (localOffsetUnits - zoneOffsetUnits); }
		 * 
		 * int arrow = 1; if (zoneOffsetUnits >= localOffsetUnits) { arrow = -1; }
		 * offsetUnits *= arrow; /
		 **/

		// System.out.println("OffsetUnits: " + offsetUnits);

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

		/*
		 * time = adjTime; day = adjDay; week = adjWeek; year = adjYear; /
		 **/

		//System.out.println("User data revised: " + adjYear + " : " + adjWeek + " : " + adjDay + " : " + adjTime);

		//System.out.println("saving the session... update instructor schedule and student classes.");
		Session tsession = new Session();
		tsession.setCreated(new Timestamp(new Date().getTime()));
		tsession.setDay(adjDay);
		tsession.setStudentId(sId);
		tsession.setTeacherId(iId);
		tsession.setTime(adjTime);
		tsession.setWeek(adjWeek);
		tsession.setYear(adjYear);
		Integer sessionId = SessionDAO.saveSession(tsession);
		System.out.println("After saving, session id is: " + sessionId);
		if (sessionId == null) {
			String message = "An error occurred while attempting to save the desired session.  Please contact technical support.";
			model.addAttribute("error", message);

		} else {
			tsession.setSessionId(sessionId);
		}
		//System.out.println("Final session object: " + tsession.toString());

		ArrayList<TeachSession> learnSessions = ScheduleHelper.getRemoteSchSessions(iId, year, week, zone, local);
		ArrayList<Session> taken = ScheduleHelper.getRemoteSessions(iId, year, week, zone, local);

		model.addAttribute("taken", taken);
		model.addAttribute("learnSessions", learnSessions);
		model.addAttribute("period", ScheduleHelper.getPeriod(week));
		model.addAttribute("week", week);
		model.addAttribute("year", year);
		model.addAttribute("instructor_id", iId);

		return "learnSchedule";
	}

	@RequestMapping("/teachClasses")
	public String startSession(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/login";
		}

		ArrayList<Session> sessions = ScheduleHelper.getTeacherSessions(user);
		ArrayList<Session> remoteSessions = ScheduleHelper.setRemoteTime(user, sessions);
		model.addAttribute("teacherSessions", remoteSessions);

		return "teachClasses";
	}


	@RequestMapping("/week1")
	public String week1Scheduler(HttpSession session, Model model) {

		// HttpSession dependency, validate session
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/login";
		}
		int userId = user.getUserId();

		// These inputs are for the user's time zone, adjust before saving
		TimeZone zone = TimeZone.getTimeZone(user.getTimezoneId());
		TimeZone local = TimeZone.getTimeZone("US/Mountain");

		// Maybe pass in reference to week. For now, just build it.
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		String lastPeriod = CalendarHelper.formatDate(cal.getTime());

		int year = cal.get(Calendar.YEAR);
		int weekOfYear = cal.get(Calendar.WEEK_OF_YEAR);

		model.addAttribute("period", lastPeriod);
		model.addAttribute("scheduleWeek", new Integer(weekOfYear));
		model.addAttribute("scheduleYear", new Integer(year));
		model.addAttribute("userid", new Integer(user.getUserId()));

		//System.out.println("Getting schedule for week 1, user id [" + userId + "] week number [" + weekOfYear + "]");

		// Next, build the schedule from the database
		ArrayList<TeachSession> sessions = ScheduleHelper.getRemoteSchSessions(userId, year, weekOfYear, zone, local);
		ArrayList<Session> adjTaken = ScheduleHelper.getRemoteSessions(userId, year, weekOfYear, zone, local);

		model.addAttribute("taken", adjTaken);
		model.addAttribute("learnSessions", sessions);

		return "teachSchedule";
	}

	@RequestMapping("/week2")
	public String week2Scheduler(HttpSession session, Model model) {
		// HttpSession dependency, validate session
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/login";
		}
		int userId = user.getUserId();

		// These inputs are for the user's time zone, adjust before saving
		TimeZone zone = TimeZone.getTimeZone(user.getTimezoneId());
		TimeZone local = TimeZone.getTimeZone("US/Mountain");

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		String nextPeriod = CalendarHelper.formatDate(cal.getTime());

		int year = cal.get(Calendar.YEAR);
		int weekOfYear = cal.get(Calendar.WEEK_OF_YEAR);

		model.addAttribute("period", nextPeriod);
		model.addAttribute("scheduleWeek", new Integer(weekOfYear));
		model.addAttribute("scheduleYear", new Integer(year));
		model.addAttribute("userid", new Integer(user.getUserId()));

		//System.out.println("Getting schedule for week 2, user id [" + userId + "] week number [" + weekOfYear + "]");

		// Next, build the schedule from the database
		ArrayList<TeachSession> sessions = ScheduleHelper.getRemoteSchSessions(userId, year, weekOfYear, zone, local);
		ArrayList<Session> adjTaken = ScheduleHelper.getRemoteSessions(userId, year, weekOfYear, zone, local);

		model.addAttribute("taken", adjTaken);
		model.addAttribute("learnSessions", sessions);

		return "teachSchedule";
	}

	@RequestMapping("/week3")
	public String week3Scheduler(HttpSession session, Model model) {

		// HttpSession dependency, validate session
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/login";
		}
		int userId = user.getUserId();

		// These inputs are for the user's time zone, adjust before saving
		TimeZone zone = TimeZone.getTimeZone(user.getTimezoneId());
		TimeZone local = TimeZone.getTimeZone("US/Mountain");

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		cal.add(Calendar.WEEK_OF_YEAR, 2);
		String secondPeriod = CalendarHelper.formatDate(cal.getTime());

		int year = cal.get(Calendar.YEAR);
		int weekOfYear = cal.get(Calendar.WEEK_OF_YEAR);

		model.addAttribute("period", secondPeriod);
		model.addAttribute("scheduleWeek", new Integer(weekOfYear));
		model.addAttribute("scheduleYear", new Integer(year));
		model.addAttribute("userid", new Integer(user.getUserId()));

		//System.out.println("Getting schedule for week 3, user id [" + userId + "] week number [" + weekOfYear + "]");

		// Next, build the schedule from the database
		ArrayList<TeachSession> sessions = ScheduleHelper.getRemoteSchSessions(userId, year, weekOfYear, zone, local);
		ArrayList<Session> adjTaken = ScheduleHelper.getRemoteSessions(userId, year, weekOfYear, zone, local);

		model.addAttribute("taken", adjTaken);
		model.addAttribute("learnSessions", sessions);

		return "teachSchedule";
	}

	@RequestMapping("/saveSchedule")
	public String saveSchedule(HttpSession httpSession, @Valid @ModelAttribute("teachSession") TeachSession session,
			BindingResult result, Model model) {

		// HttpSession dependency, validate session
		User user = (User) httpSession.getAttribute("user");
		if (user == null) {
			return "redirect:/user/login";
		}
		int userId = user.getUserId();
		int week = session.getWeekNo();
		int year = session.getYear();

		model.addAttribute("period", session.getPeriod());
		model.addAttribute("userid", session.getInstructorId());
		model.addAttribute("scheduleWeek", session.getWeekNo());
		model.addAttribute("scheduleYear", session.getYear());

		// These inputs are for the user's time zone, adjust before saving
		TimeZone zone = TimeZone.getTimeZone(user.getTimezoneId());
		TimeZone local = TimeZone.getTimeZone("US/Mountain");

		StringBuffer errorString = null;
		if (result.hasErrors()) {
			errorString = new StringBuffer();
			List<ObjectError> bads = result.getAllErrors();
			Iterator<ObjectError> it = bads.iterator();
			while (it.hasNext()) {
				ObjectError oe = it.next();
				oe.getDefaultMessage();
				errorString.append("\n" + oe.getDefaultMessage());
			}
		}
		// Check that input end > start
		int inStart = session.getStarttime();
		int inEnd = session.getEndtime();
		if (inEnd < inStart) {
			if (errorString == null) {
				errorString = new StringBuffer();
			}
			String message = "\nEnd time must be equal to or greater than start time.";
			errorString.append(message);
		}

		if (errorString != null) {
			model.addAttribute("error", errorString.toString());
			return "teachSchedule";
		}

		//System.out.println("saving....");

		// OK, so the validation has succeeded and we are now going to save the
		// schedule.

		// inputs must be normalized
		ArrayList<TeachSession> inputs = new ArrayList<TeachSession>();
		inputs.add(session);
		inputs = ScheduleHelper.adjustSchedule(inputs, zone, local, false);
		session = inputs.get(0);

		// First, save the request data for historical purposes
		ScheduleDAO.saveUserInput(session.getInstructorId(), session.getWeekNo(), session.getStarttime(),
				session.getEndtime(), session.getDay(), session.getAction(), session.getYear());

		// Deal with out of week time slots -- all that happens here is that the session
		// spans days, meaning end < start as a result of the time zone adjustment.
		TeachSession out = null;
		int start = session.getStarttime();
		int end = session.getEndtime();

		//System.out.println("Out start: " + start + " end: " + end);

		if (end < start) {
			out = new TeachSession(session);
			session.setEndtime(47);
			out.setStarttime(0);
			int selectedDay = session.getDay();
			int selectedWeek = session.getWeekNo();
			int selectedYear = session.getYear();
			if (selectedDay == 6) {
				// roll week and year
				out.setDay(0);
				if (selectedWeek == 51) {
					out.setWeekNo(0);
					out.setYear(selectedYear + 1);
				} else {
					out.setWeekNo(selectedWeek + 1);
				}
			} else {
				out.setDay(selectedDay + 1);
			}
		}

		// Now save session and, if necessary, out.

		// Create or retrieve schedule for week of 'session'.
		ArrayList<String> current = ScheduleDAO.getSchedule(user.getUserId(), session.getWeekNo(), session.getYear());

		int upDateDay = session.getDay();
		int action = session.getAction();
		String toUpdate = current.get(upDateDay);
		System.out.println("updating day: " + upDateDay + " value of " + toUpdate + "]");
		String newSession = ScheduleHelper.getSchString(session);
		
		/*String act = "ADD";
		if (action == 2) {
			act = "DELETE";
		}
		//System.out.println("updating with new session to [" + act + "] as " + newSession + "]");
		/**/

		ArrayList<Session> taken = SessionDAO.getSessions(user.getUserId(), session.getYear(), session.getWeekNo());

		StringBuffer saveSession = new StringBuffer();
		if (action == 1) {
			// Add
			for (int i = 0; i < toUpdate.length(); i++) {
				char u = toUpdate.charAt(i);
				char n = newSession.charAt(i);
				if (u == '0' && n == '0') {
					saveSession.append('0');
				} else {
					saveSession.append('1');
				}
			}
		} else {
			// Validate to prevent deletion. If false, don't delete and return error
			// message.
			if (ScheduleHelper.validateDeletion(taken, session)) {

				// Subtract
				for (int i = 0; i < toUpdate.length(); i++) {
					char u = toUpdate.charAt(i);
					char n = newSession.charAt(i);
					if (u == '1' && n == '0') {
						saveSession.append('1');
					} else {
						saveSession.append('0');
					}
				}
			} else {
				String error = "Unable to delete confirmed session.  Contact support.";
				model.addAttribute("error", error);
				saveSession.append(toUpdate);
			}
		}
		//System.out.println("Set schedule on [" + session.getYear() + ", " + session.getWeekNo() + ", " + session.getDay() + "] to [" + saveSession.toString() + "]");
		ScheduleDAO.setSchedule(user.getUserId().intValue(), session.getWeekNo(), session.getDay(),
				saveSession.toString(), session.getYear());

		if (out != null) {
			// Create or retrieve schedule for week of 'session'.
			ArrayList<String> next = ScheduleDAO.getSchedule(user.getUserId().intValue(), out.getWeekNo(),
					out.getYear());

			int outupDateDay = out.getDay();
			int outaction = out.getAction();
			String outtoUpdate = next.get(outupDateDay);
			//System.out.println("updating out day: " + outupDateDay + " value of " + outtoUpdate + "]");
			String outnewSession = ScheduleHelper.getSchString(out);
			
			/*
			String outact = "ADD";
			if (outaction == 2) {
				outact = "DELETE";
			}
			System.out.println("updating with new out session to [" + outact + "] as " + outnewSession + "]");
			/**/


			ArrayList<Session> outtaken = SessionDAO.getSessions(user.getUserId(), out.getYear(), out.getWeekNo());

			StringBuffer outsaveSession = new StringBuffer();
			if (outaction == 1) {
				// Add
				for (int i = 0; i < outtoUpdate.length(); i++) {
					char u = outtoUpdate.charAt(i);
					char n = outnewSession.charAt(i);
					if (u == '0' && n == '0') {
						outsaveSession.append('0');
					} else {
						outsaveSession.append('1');
					}
				}
			} else {
				// Validate to prevent deletion. If false, don't delete and return error
				// message.
				if (ScheduleHelper.validateDeletion(outtaken, out)) {

					// Subtract
					for (int i = 0; i < outtoUpdate.length(); i++) {
						char u = outtoUpdate.charAt(i);
						char n = outnewSession.charAt(i);
						if (u == '1' && n == '0') {
							outsaveSession.append('1');
						} else {
							outsaveSession.append('0');
						}
					}
				} else {
					String error = "Unable to delete confirmed session.  Contact support.";
					model.addAttribute("error", error);
					outsaveSession.append(outtoUpdate);
				}
			}
			//System.out.println("Set out of range schedule on [" + out.getYear() + ", " + out.getWeekNo() + ", " + out.getDay() + "] to [" + outsaveSession.toString() + "]");
			ScheduleDAO.setSchedule(user.getUserId().intValue(), out.getWeekNo(), out.getDay(),
					outsaveSession.toString(), out.getYear());
		}

		// Next, build the schedule from the database
		ArrayList<TeachSession> sessions = ScheduleHelper.getRemoteSchSessions(userId, year, week, zone, local);
		ArrayList<Session> takens = ScheduleHelper.getRemoteSessions(userId, year, week, zone, local);

		model.addAttribute("taken", takens);
		model.addAttribute("learnSessions", sessions);
		model.addAttribute("scheduleWeek", week);
		model.addAttribute("scheduleYear", year);

		return "teachSchedule";
	}

	public static void main(String[] args) {

		String[] tzs = TimeZone.getAvailableIDs();
		for (String index : tzs) {
			TimeZone temp = TimeZone.getTimeZone(index);
			System.out.println(index + " : " + temp.getDisplayName());
		}
		TimeZone myZone = TimeZone.getTimeZone("US/Eastern");
		String disName = myZone.getDisplayName();
		System.out.println(disName);
		TimeZone zone = TimeZone.getTimeZone("Asia/Seoul");
		String disName2 = zone.getDisplayName();
		System.out.println(disName2);

		String myZoneId = myZone.getID();
		System.out.println(myZoneId);
		String zoneId = zone.getID();
		System.out.println(zoneId);

		GregorianCalendar calLocal = new GregorianCalendar();
		GregorianCalendar calRemote = new GregorianCalendar();
		calLocal.setTimeZone(myZone);
		calRemote.setTimeZone(zone);

		Calendar jvm = Calendar.getInstance();
		System.out.println("Current local time is: " + new Date(jvm.getTimeInMillis()));
		TimeZone jzone = jvm.getTimeZone();

		int lOffset = myZone.getOffset(calLocal.getTimeInMillis());
		int rOffset = zone.getOffset(calRemote.getTimeInMillis());
		int jOffset = jzone.getOffset(jvm.getTimeInMillis());
		System.out.println("JVM offset: " + jOffset);
		System.out.println("Local offset: " + lOffset);
		System.out.println("Remote offset: " + rOffset);

		long hrs12 = 12 * 3600 * 1000;
		long lAbs = hrs12 + lOffset;
		long rAbs = hrs12 + rOffset;
		long jAbs = hrs12 + jOffset;

		System.out.println("hrs12 = " + hrs12);
		System.out.println("lAbs = " + lAbs);
		System.out.println("rAbs = " + rAbs);
		System.out.println("jAbs = " + jAbs);

		int milliDiffLocal = lOffset - jOffset;
		int milliDiffRemote = rOffset - jOffset;

		/*
		 * if(jOffset < 0 && lOffset < 0) { if(jOffset <= lOffset) { milliDiffLocal =
		 * lOffset - jOffset; } else { milliDiffLocal = lOffset - jOffset; } } else if
		 * (jOffset >= 0 && lOffset < 0){ milliDiffLocal = lOffset - jOffset; } else if
		 * (jOffset >= 0 && lOffset >= 0) { if(jOffset <= lOffset) { milliDiffLocal =
		 * lOffset - jOffset; } else { milliDiffLocal = lOffset - jOffset; } } else if
		 * (jOffset < 0 && lOffset >= 0) { milliDiffLocal = lOffset - jOffset; }
		 * 
		 * if(jOffset < 0 && rOffset < 0) { if(jOffset <= rOffset) { milliDiffRemote =
		 * rOffset - jOffset; } else { milliDiffRemote = rOffset - jOffset; } } else if
		 * (jOffset >= 0 && rOffset < 0){ milliDiffRemote = rOffset - jOffset; } else if
		 * (jOffset >= 0 && rOffset >= 0) { if(jOffset <= rOffset) { milliDiffRemote =
		 * rOffset - jOffset; } else { milliDiffRemote = rOffset - jOffset; } } else if
		 * (jOffset < 0 && rOffset >= 0) { milliDiffRemote = rOffset - jOffset; } /
		 **/

		System.out.println("Offset Student = " + milliDiffRemote);
		System.out.println("Offset Teacher = " + milliDiffLocal);
		System.out.println("Current Teacher time is: " + new Date(jvm.getTimeInMillis() + milliDiffLocal));
		System.out.println("Current Student time is: " + new Date(jvm.getTimeInMillis() + milliDiffRemote));

		int lyear = calLocal.get(Calendar.YEAR);
		int ldoy = calLocal.get(Calendar.DAY_OF_YEAR);
		int lhour = calLocal.get(Calendar.HOUR_OF_DAY);
		int lmin = calLocal.get(Calendar.MINUTE);
		int lsec = calLocal.get(Calendar.SECOND);

		int ryear = calRemote.get(Calendar.YEAR);
		int rdoy = calRemote.get(Calendar.DAY_OF_YEAR);
		int rhour = calRemote.get(Calendar.HOUR_OF_DAY);
		int rmin = calRemote.get(Calendar.MINUTE);
		int rsec = calRemote.get(Calendar.SECOND);

		System.out.println("Local millis: " + calLocal.getTimeInMillis());
		System.out.println("Remote millis: " + calRemote.getTimeInMillis());

		System.out.println("Year compare: local [" + lyear + "] remote [" + ryear + "]");
		System.out.println("Day compare: local [" + ldoy + "] remote [" + rdoy + "]");
		System.out.println("Hour compare: local [" + lhour + "] remote [" + rhour + "]");
		System.out.println("Minute compare: local [" + lmin + "] remote [" + rmin + "]");
		System.out.println("Second compare: local [" + lsec + "] remote [" + rsec + "]");
		// Souel is UTC+9, Mountain is UTC-7

		// Still don't understand this.
		if (calLocal.after(calRemote)) {
			System.out.println("Local is after remote.");
		}
		if (calLocal.before(calRemote)) {
			System.out.println("Local is before remote.");
		}
		if (calLocal.equals(calRemote)) {
			System.out.println("Local is equal to remote.");
		}

		int timeVal = 47;
		// Relative to JVM instance.
		Calendar local = Calendar.getInstance();
		int hday = 0;
		int min = 0;
		hday = timeVal / 2;
		if (timeVal % 2 != 0) {
			min = 30;
		}
		local.set(Calendar.HOUR_OF_DAY, hday);
		local.set(Calendar.MINUTE, min);
		local.set(Calendar.SECOND, 0);
		local.set(Calendar.MILLISECOND, 0);

		Date jTime = new Date(local.getTimeInMillis());
		Date lTime = new Date(local.getTimeInMillis() + milliDiffLocal);
		Date rTime = new Date(local.getTimeInMillis() + milliDiffRemote);
		System.out.println("For time period [" + timeVal + "] jvm time is [" + jTime + "] teacher time is [" + lTime
				+ "] student time is [" + rTime + "]");

	}
}
