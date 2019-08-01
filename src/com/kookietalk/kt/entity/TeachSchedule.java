package com.kookietalk.kt.entity;

import java.util.ArrayList;
import java.util.List;

public class TeachSchedule {

	private List<TeachSession> sessions = new ArrayList<TeachSession>();
	
	//don't use - sessions can only be added in bulk after checking database via ScheduleHelper
	public void addSession(TeachSession session) {
		sessions.add(session);
	}

	public List<TeachSession> getSessions() {
		return sessions;
	}

	public void setSessions(ArrayList<TeachSession> sessions2) {
		sessions = sessions2;
	}
}
