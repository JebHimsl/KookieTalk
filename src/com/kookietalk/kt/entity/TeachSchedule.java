package com.kookietalk.kt.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TeachSchedule {

	private List sessions = new ArrayList();
	
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
