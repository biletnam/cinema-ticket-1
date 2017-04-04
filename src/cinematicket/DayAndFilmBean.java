package cinematicket;

import java.util.ArrayList;
import java.io.*;

public class DayAndFilmBean implements Serializable {
	private static final String[] DAYS = new String[] {
		"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

	private static final String[] TIME_ODD = {"9:00", "11:00", "13:00", "15:00", "17:00", "19:00", "21:00"};
	private static final String[] TIME_EVEN = {"10:00", "12:00", "14:00", "16:00", "18:00", "20:00", "22:00"};

	private DataHandler dh = null;
	private ArrayList<Session> currentSessions = null;
	private ArrayList<Session> weekSessions = null;
	private boolean showInfo = false;

	public DayAndFilmBean() {  }

	public DayAndFilmBean(DataHandler dh) {
		this.dh = dh;
		this.weekSessions = dh.getWeekSessions();
	}

	public ArrayList<Session> getDayFilmTimeSessionList(String day, String film, String time) {
		ArrayList<Session> sessions = new ArrayList<>();
		for (Session s : weekSessions) {
			if (s.getFilm().equals(film) && s.getTime().equals(time) && s.getDay().equals(day))
				sessions.add(s);
		}
		return sessions;
	}

	public ArrayList<Session> getFilmTimeSessionList(String film, String time) {
		ArrayList<Session> sessions = new ArrayList<>();
		for (Session s : weekSessions) {
			if (s.getFilm().equals(film) && s.getTime().equals(time))
				sessions.add(s);
		}
		return sessions;
	}

	public ArrayList<Session> getDayFilmSessionList(String day, String film) {
		ArrayList<Session> sessions = new ArrayList<>();
		for (Session s : weekSessions) {
			if (s.getFilm().equals(film) && s.getDay().equals(day))
				sessions.add(s);
		}
		return sessions;
	}

	public ArrayList<Session> getDayTimeSessionList(String day, String time) {
		ArrayList<Session> sessions = new ArrayList<>();
		for (Session s : weekSessions) {
			if (s.getTime().equals(time) && s.getDay().equals(day))
				sessions.add(s);
		}
		return sessions;
	}

	public ArrayList<Session> getTimeSessionList(String time) {
		ArrayList<Session> sessions = new ArrayList<>();
		for (Session s : weekSessions) {
			if (s.getTime().equals(time))
				sessions.add(s);
		}
		return sessions;
	}

	public ArrayList<Session> getFilmSessionList(String film) {
		ArrayList<Session> sessions = new ArrayList<>();
		for (Session s : weekSessions) {
			if (s.getFilm().equals(film))
				sessions.add(s);
		}
		return sessions;
	}

	public ArrayList<Session> getDaySessionList(String day) {
		ArrayList<Session> sessions = new ArrayList<>();
		for (Session s : weekSessions) {
			if (s.getDay().equals(day))
				sessions.add(s);
		}
		return sessions;
    }

	public void setCurrentSessions(ArrayList<Session> sessions) {
		this.currentSessions = sessions;
	}

	public boolean getShowInfo() {
		return this.showInfo;
	}

	public void setShowInfo(boolean b) {
		this.showInfo = b;
	}

	public String getInfo() {
		if (this.dh == null || currentSessions == null || showInfo == false)
			return "";

		if (currentSessions.size() != 0) {
			StringBuffer sb = new StringBuffer();
			for (Session s : currentSessions) {
				sb.append(s.printSession()).append("<br>");
			}
			return sb.toString();
		} else
			return "Sorry, no such session. Select another combination.<br>";
	}

	public String getCinema() {
		if (this.dh == null || currentSessions == null || showInfo == false)
			return "";
		
		return currentSessions.get(0).printSeats();

	}
}
