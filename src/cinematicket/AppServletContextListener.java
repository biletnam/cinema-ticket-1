package cinematicket;

import javax.servlet.annotation.WebListener;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.*;


@WebListener
public class AppServletContextListener
               implements ServletContextListener{

        //Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext ctx = servletContextEvent.getServletContext();
		String serverLocation = System.getProperty("catalina.home");
		File webapps = new File(serverLocation, "webapps");
		File cinematicket = new File(webapps, "cinematicket");
		DataHandler dataHandler = new DataHandler(new File(cinematicket, "sessions.txt"), new File(cinematicket, "orders.ser"));
		ctx.setAttribute("cinematicket.DataHandler", dataHandler);
		System.out.println("Cinema Application started");
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		ServletContext ctx = arg0.getServletContext();
		ctx.removeAttribute("cinematicket.DataHandler");
		System.out.println("ServletContextListener destroyed");
	}


}
