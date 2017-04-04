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
    urlPatterns = {"/checkOrder"}
)
public class CheckOrderServlet extends HttpServlet {
 
 	public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
		response.setContentType("text/plain");
		DataHandler dh = (DataHandler)getServletContext().getAttribute("cinematicket.DataHandler");
		if (request.getParameter("createOrder") != null) {
			OrderManagerBean omb = new OrderManagerBean(dh);
			int newOrderId = (int)request.getAttribute("newOrderId");
			omb.setCurrentOrder(newOrderId);
			request.getSession().setAttribute("orderManagerBean", omb);
			forward("/checkOrder.jsp", request, response);
		}
		if( request.getParameter("checkOrderButton") != null ){
			OrderManagerBean omb = new OrderManagerBean(dh);
			int orderId;
			try {
				String oi = request.getParameter("orderId");
				orderId = Integer.parseInt(oi);
				if (omb.setCurrentOrder(orderId) == true) {
					request.getSession().setAttribute("orderManagerBean", omb);
					forward("/checkOrder.jsp", request, response);
				}
				forward("/index.jsp", request, response);
			} catch (NumberFormatException e) {
				forward("/index.jsp", request, response);
			} catch (NullPointerException e) {
				forward("/index.jsp", request, response);
			}
		}
		if (request.getParameter("cancelOrder") != null) {
			OrderManagerBean omb = (OrderManagerBean) request.getSession().getAttribute("orderManagerBean");
			omb = (OrderManagerBean) request.getSession().getAttribute("orderManagerBean");
			omb.cancelOrder();
			forward("index.jsp", request, response);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
		forward("/checkOrder.jsp", request, response);
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
