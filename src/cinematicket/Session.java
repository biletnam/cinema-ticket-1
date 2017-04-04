package cinematicket;

import java.io.Serializable;

public class Session implements Serializable {

    private static final int ROWS = 15;
    private static final int SEATS = 21;
    private final String day;
    private final String time;
    private final String film;
    private final int price;
    private Ticket[][] tickets;

    public Session(String day, String time, int price, String film) {
        this.day = day;
        this.time = time;
        this.price = price;
        this.film = film;
        this.tickets = new Ticket[ROWS][SEATS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < SEATS; j++) {
                tickets[i][j] = new Ticket(i + 1, j + 1);
            }
        }
    }

    class Ticket implements Serializable {
        private String id;
        private Seat ticketSeat;

        public Ticket(int row, int seat) {
            ticketSeat = new Seat(row, seat);
            this.id = calculateId();
        }

        public String getTime() { return time; }

        public int getPrice() { return price; }

        public String getFilm() { return film; }

        public String getDay() { return day; }

        public Seat getTicketSeat() { return ticketSeat; }

        public int getRow() { return ticketSeat.getRow(); }

        public String getNumber() {
            return ticketSeat.getNumber();
        }

		public int getRealNumber() { return ticketSeat.getRealNumber(); }

        public String getId() { return id; }

        public String printTicket() {
            StringBuilder sb = new StringBuilder()
                    .append("=============================<br>")
                    .append("TICKET #").append(getId()).append("<br>")
                    .append("=============================<br>")
                    .append("DAY:_").append(getTime()).append("<br>")
                    .append("TIME:_").append(getDay()).append("<br>")
                    .append("FILM:_").append(getFilm()).append("<br>")
                    .append("ROW:_").append(getRow()).append("<br>")
                    .append("SEAT:_").append(getRealNumber()).append("<br>")
                    .append("=============================<br>")
                    .append("PRICE:*****************").append(getPrice()).append("rub").append("<br>")
                    .append("=============================<br>");
            return sb.toString();
        }

        private String calculateId() {
           StringBuilder s = new StringBuilder("");
           int d = 0;
           String[] arr = time.split(":");
           String t = arr[0];
           switch(day){
               case "Monday": { d = 1; break; }
               case "Tuesday": { d = 2; break; }
               case "Wednesday": { d = 3; break; }
               case "Thursday": { d = 4; break; }
               case "Friday": { d = 5; break; }
               case "Saturday": { d = 6; break; }
               case "Sunday": { d = 7; break; }
           }
           s.append(d).append(t).append(ticketSeat.getNumber()).append(ticketSeat.getRow());
           return s.toString();
        }

        public boolean equals(Object object) {
            if (this == object) return true;
            if (!(object instanceof Ticket)) return false;
            if (!super.equals(object)) return false;

            Ticket ticket = (Ticket) object;

            if (!getId().equals(ticket.getId())) return false;

            return true;
        }

        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + getId().hashCode();
            return result;
        }
    }

    public int getRowsAmount() { return ROWS; }

    public int getSeatsAmount() { return SEATS; }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public int getPrice() {
        return price;
    }

    public String getFilm() { return film; }

    public boolean isTicketAvailable(Ticket t) {
        return t.getTicketSeat().isAvailable();
    }

    public void occupyTicket(Ticket t) {
        t.getTicketSeat().occupy();
    }

    public void freeTicket(Ticket t) {
        t.getTicketSeat().free();
    }

    public Ticket getTicket(int row, int seat) {
        if (row > 0 || row < ROWS + 1 || seat > 0 || seat < SEATS + 1) {
            return tickets[row - 1][seat - 1];
        } else
            return null;
    }

    public String printSeats() {
        String s = new StringBuilder()
                .append("||||||||||||------------------------------------------------------------------------------------------||||||||||||<br>")
                .append("||||||||||||-------------------------------------SCREEN--------------------------------------||||||||||||<br>")
                .append("<br>")
                .append("<br>")
                .append(printCinema())
                .toString();
        return s;
    }

    private String printCinema() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < ROWS; i++) {
			if (i < 9)
            	s.append("0").append(i + 1).append("|");
			else
            	s.append(i + 1).append("|");
            for (int j = 0; j < SEATS; j++) {
                s.append(tickets[i][j].getNumber()).append(" ");
            }
            s.append("<br>");
        }
        return s.toString();
    }

	public String printSession() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getDay()).append(" | ")
			.append(this.getTime()).append(" | ")
			.append(this.getFilm()).append(" | ")
			.append(this.getPrice()).append("rub<br>");
		return sb.toString();
	}

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Session)) return false;
        if (!super.equals(object)) return false;

        Session session = (Session) object;

        if (!getDay().equals(session.getDay())) return false;
        if (!getTime().equals(session.getTime())) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getDay().hashCode();
        result = 31 * result + getTime().hashCode();
        return result;
    }
}

