package cinematicket;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
 
import javax.servlet.annotation.WebServlet;
 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
 
@WebServlet(
    urlPatterns = {"", "/findSessions"}
)
public class SessionSelectorServlet extends HttpServlet {
 
 	public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
		response.setContentType("text/plain");
		String selectedDay = request.getParameter("day");
		String selectedFilm = request.getParameter("film");
		String selectedTime = request.getParameter("time");
		if (selectedDay.equals("") && selectedFilm.equals("") && selectedTime.equals("")) {
			forward("/index.jsp", request, response);		
		}

		DataHandler dh = (DataHandler)getServletContext().getAttribute("cinematicket.DataHandler");
		DayAndFilmBean daf = new DayAndFilmBean(dh);

		if (selectedDay.equals("") && !selectedFilm.equals("") && selectedTime.equals("")) {
			ArrayList<Session> curSessions = daf.getFilmSessionList(selectedFilm);
			daf.setCurrentSessions(curSessions);
			daf.setShowInfo(true);
			request.setAttribute("dayAndFilmBean", daf);
			forward("/index.jsp", request, response);
		}
		if (!selectedDay.equals("") && selectedFilm.equals("") && selectedTime.equals("")) {
			ArrayList<Session> curSessions = daf.getDaySessionList(selectedDay);
			daf.setCurrentSessions(curSessions);
			daf.setShowInfo(true);
			request.setAttribute("dayAndFilmBean", daf);
			forward("/index.jsp", request, response);
		}
		if (selectedDay.equals("") && selectedFilm.equals("") && !selectedTime.equals("")) {
			ArrayList<Session> curSessions = daf.getTimeSessionList(selectedTime);
			daf.setCurrentSessions(curSessions);
			daf.setShowInfo(true);
			request.setAttribute("dayAndFilmBean", daf);
			forward("/index.jsp", request, response);
		}
		if (!selectedDay.equals("") && !selectedFilm.equals("") && selectedTime.equals("")) {
			ArrayList<Session> curSessions = daf.getDayFilmSessionList(selectedDay, selectedFilm);
			daf.setCurrentSessions(curSessions);
			daf.setShowInfo(true);
			request.setAttribute("dayAndFilmBean", daf);
			if (curSessions.size() != 1) {
				forward("/index.jsp", request, response);
			} else {
				Session selectedSession = curSessions.get(0);
				getServletContext().setAttribute("selectedSession", selectedSession);
				forward("/index.jsp", request, response);
			}
		}
		if (selectedDay.equals("") && !selectedFilm.equals("") && !selectedTime.equals("")) {
			ArrayList<Session> curSessions = daf.getFilmTimeSessionList(selectedFilm, selectedTime);
			daf.setCurrentSessions(curSessions);
			daf.setShowInfo(true);
			request.setAttribute("dayAndFilmBean", daf);
			if (curSessions.size() != 1) {
				forward("/index.jsp", request, response);
			} else {
				Session selectedSession = curSessions.get(0);
				getServletContext().setAttribute("selectedSession", selectedSession);
				forward("/index.jsp", request, response);
			}
		}
		if (!selectedDay.equals("") && selectedFilm.equals("") && !selectedTime.equals("")) {
			ArrayList<Session> curSessions = daf.getDayTimeSessionList(selectedDay, selectedTime);
			daf.setCurrentSessions(curSessions);
			daf.setShowInfo(true);
			request.setAttribute("dayAndFilmBean", daf);
			if (curSessions.size() != 1) {
				forward("/index.jsp", request, response);
			} else {
				Session selectedSession = curSessions.get(0);
				getServletContext().setAttribute("selectedSession", selectedSession);
				forward("/index.jsp", request, response);
			}
		}
		if (!selectedDay.equals("") && !selectedFilm.equals("") && !selectedTime.equals("")) {
			ArrayList<Session> curSessions = daf.getDayFilmTimeSessionList(selectedDay, selectedFilm, selectedTime);
			daf.setCurrentSessions(curSessions);
			daf.setShowInfo(true);
			request.setAttribute("dayAndFilmBean", daf);
			if (curSessions.size() == 0) {
				forward("/index.jsp", request, response);
			} else {
				Session selectedSession = curSessions.get(0);
				getServletContext().setAttribute("selectedSession", selectedSession);
				forward("/index.jsp", request, response);
			}
		}
	}

		
		public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
			forward("/index.jsp", request, response);
		}

		private void forward(String              url, 
                         HttpServletRequest  request, 
                         HttpServletResponse response) 
                            throws ServletException, IOException{

        if(!url.startsWith("/"))
            url = "/" + url;
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
