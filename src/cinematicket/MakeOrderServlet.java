package cinematicket;

import java.io.*;
import java.util.ArrayList;
 
import javax.servlet.annotation.WebServlet;
 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
 
@WebServlet(
    urlPatterns = {"/makeOrder"}
)
public class MakeOrderServlet extends HttpServlet {
 
 	public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
		response.setContentType("text/html");
		OrderManagerBean omb = (OrderManagerBean)request.getSession().getAttribute("orderManagerBean");
		int	rows = omb.getRowsAmount();
		int	seats = omb.getSeatsAmount();
		String strow = request.getParameter("row");
		String stnumber = request.getParameter("number");
		int row = 0;
		int number = 0;
		
		if (request.getParameter("addTicket") != null) {
			try {
				row = Integer.parseInt(strow);
				number = Integer.parseInt(stnumber);
        		if (row < 1 || row > rows || number < 1 || number > seats) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				forward("/makeOrder.jsp", request, response);
	  		}
			omb.addTicket(row, number);
			forward("/makeOrder.jsp", request, response);
		}
		if (request.getParameter("removeTicket") != null) {
			try {
				row = Integer.parseInt(strow);
				number = Integer.parseInt(stnumber);
        		if (row < 1 || row > rows || number < 1 || number > seats) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				forward("/makeOrder.jsp", request, response);
	  		}
			omb.removeTicket(row, number);
			forward("/makeOrder.jsp", request, response);
		}
		if (request.getParameter("createOrder") != null) {
			int newOrderId = omb.createOrder();
			if (newOrderId != -1) {
				request.setAttribute("newOrderId", newOrderId);
				forward("/checkOrder", request, response);
			} else {
				forward("/makeOrder.jsp", request, response);
			}
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
		response.setContentType("text/plain");
		Session currentSession = (Session)getServletContext().getAttribute("selectedSession");
		if (currentSession == null) {
			forward("/index.jsp", request, response);
		}
		getServletContext().removeAttribute("selectedSession");
		DataHandler dh = (DataHandler)getServletContext().getAttribute("cinematicket.DataHandler");
		OrderManagerBean omb = new OrderManagerBean(dh, currentSession);
		request.getSession().setAttribute("orderManagerBean", omb);
		forward("/makeOrder.jsp", request, response);
	}

	private void forward(String              url, 
                        HttpServletRequest  request, 
                        HttpServletResponse response) 
                           throws ServletException, IOException {
	    if(!url.startsWith("/"))
   			url = "/" + url;
   		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(url);
   	 	rd.forward(request, response);
    }
}
