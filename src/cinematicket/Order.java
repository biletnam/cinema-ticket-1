package cinematicket;

import java.io.Serializable;
import java.util.HashSet;

public class Order implements Serializable {
    private int id;
	private Session orderSession;
    private HashSet<Session.Ticket> orderTickets;

    public Order(HashSet<Session.Ticket> orderTickets, Session orderSession, int id) {
        this.orderTickets = orderTickets;
		this.orderSession = orderSession;
		this.id = id;
    }

    public HashSet<Session.Ticket> getOrderTickets() { return orderTickets; }

    public int getId() { return id; }

	public Session getOrderSession() { return orderSession; }
}
