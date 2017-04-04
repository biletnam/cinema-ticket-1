package cinematicket;

import java.io.*;
import java.util.*;

public class OrderManagerBean implements Serializable {
	
	private Session currentSession = null;
	private DataHandler dataHandler = null;
	private List<Order> orderList = null;
	private Order currentOrder;
	private HashSet<Session.Ticket> ticketSet = null;

	public OrderManagerBean() {  }

	public OrderManagerBean(DataHandler dh, Session currentSession) {
		this.dataHandler = dh;
		this.currentSession = currentSession;
		this.orderList = dh.getOrderList();
		this.ticketSet = new HashSet<>();
	}

	public OrderManagerBean(DataHandler dh) {
		this.dataHandler = dh;
		this.orderList = dh.getOrderList();
	}

	public void addTicket(int row, int number){
		Session.Ticket t = currentSession.getTicket(row, number);
		if (t != null && currentSession.isTicketAvailable(t)) {
			ticketSet.add(t);
		}
	}

	public void removeTicket(int row, int number) {
		Session.Ticket t = currentSession.getTicket(row, number);
		if (t != null) {
			ticketSet.remove(t);
		}
	}

	public String getSessionInfo() {
		return currentSession.printSession();
	}
	
	public String getSessionSeats() {
		return currentSession.printSeats();
	}

	public Order getOrder(int id) {
		for (Order o : orderList) {
			if (o.getId() == id)
				return o;
		}
		return null;
	}

	public boolean setCurrentOrder(int id) {
		currentOrder = getOrder(id);
		if(currentOrder != null) {
			ticketSet = currentOrder.getOrderTickets();
			currentSession = currentOrder.getOrderSession();
			return true;
		}
		return false;
	}

	public int createOrder() {
		int oc = dataHandler.getOrderId();
		int ts = ticketSet.size();
		if (ts > 0) {
			for (Session.Ticket t : ticketSet) {
				currentSession.occupyTicket(t);
			}
			Order newOrder = new Order(ticketSet, currentSession, oc);
			orderList.add(newOrder);
			dataHandler.setOrderList(orderList);
			dataHandler.increaseOrderId();
			ticketSet = new HashSet<>();
			return oc;
		} else
			return -1;
	}

	public void cancelOrder() {
		for(Session.Ticket st : ticketSet) {
			Session.Ticket t = currentSession.getTicket(st.getRow(), st.getRealNumber());
			currentSession.freeTicket(t);
		}
		for (Order o : orderList) {
			if (o.getId() == currentOrder.getId())
				orderList.remove(o);
			break;
		}
		dataHandler.setOrderList(orderList);
	}

	public String getPrintTicketList() {
		if (ticketSet == null || ticketSet.size() == 0) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for (Session.Ticket st : ticketSet) {
			sb.append(st.printTicket()).append("<br><br>");
		}
		return sb.toString();
	}

	public String getOrderInfo() {
		if (currentOrder == null)
			return "";
		StringBuffer sb = new StringBuffer();
		Session s = currentOrder.getOrderSession();
		sb.append("Order ID: #");
		sb.append(currentOrder.getId()).append("<br>");
		sb.append("Tickets amount:  ").append(ticketSet.size()).append("<br>");
		sb.append("Day:  ").append(s.getDay()).append("<br>");
		sb.append("Time:  ").append(s.getTime()).append("<br>");
		sb.append("Film:  ").append(s.getFilm()).append("<br>");
		sb.append("Total price:  ").append(getTotalPrice()).append("<br>");
		sb.append("<br>").append("<br>");
		sb.append(currentSession.printSeats()).append("<br>");
		sb.append("NICE WATCHING!");

		return sb.toString();
	}

	public String getTotalPrice() {
		StringBuffer sb = new StringBuffer();
		int result = 0;
		for (Session.Ticket t : ticketSet) {
			result += t.getPrice();
		}
		return sb.append(result).append("rub").toString();
	}
	
	public int getRowsAmount() {
		return currentSession.getRowsAmount();
	}

	public int getSeatsAmount() {
		return currentSession.getSeatsAmount();
	}



}
