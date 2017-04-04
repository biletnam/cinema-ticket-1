package cinematicket;


import java.io.*;
import java.util.*;

public class DataHandler {

    private File sessionsFile;
    private File ordersFile;
    private ArrayList<Session> weekSessions;
    private List<Order> orderList;
	private int orderId = 0;

    public DataHandler(File sessionsFile, File ordersFile) {
        this.sessionsFile = sessionsFile;
        this.ordersFile = ordersFile;
		loadSessionsFromFile();
		loadOrdersFromFIle();
		synchronizeSessionsAndOrders();
    }

	public ArrayList<Session> getWeekSessions() {
		return this.weekSessions;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
//		saveOrderListToFile();
	}

/*	public void saveOrderListToFile() {
		try {
			RandomAccessFile raf = new RandomAccessFile(ordersFile, "rw");
			raf.setLength(0);
			ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(ordersFile));
			ous.writeObject(orderList);
			ous.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}*/

	public int getOrderId() { return orderId; }

	public void increaseOrderId() { ++orderId; }

    private void loadOrdersFromFIle() {
        try {
			if (!ordersFile.exists()) {
				ordersFile.createNewFile();
			}
			if (ordersFile.length() == 0) {
				this.orderList = new ArrayList<Order>();
				return;
			}
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(ordersFile));
			List<Order> ol;
			ol = (List<Order>)is.readObject();
			if (ol == null) {
				this.orderList = new ArrayList<Order>();
				return;
			}
			this.orderList = ol;
			is.close();
		} catch (EOFException e) { return; }
		catch (ClassNotFoundException e) { 
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		}     
	}

	private void synchronizeSessionsAndOrders() {
		for (Order o : orderList) {
			Session orderSession = o.getOrderSession();
			for (Session session : weekSessions) {
				if (session.equals(orderSession)) {
					HashSet<Session.Ticket> orderTickets = o.getOrderTickets();
					for (Session.Ticket ot : orderTickets) {
						Session.Ticket st = session.getTicket(ot.getRow(), ot.getRealNumber());
						session.occupyTicket(st);
					}
					break;
				} 
			}
		}
	}

	private void loadSessionsFromFile() {
        try {
            weekSessions = new ArrayList<>();
            FileReader fr = new FileReader(sessionsFile);
            BufferedReader br = new BufferedReader(fr);
            String str;
            while ((str = br.readLine()) != null) {
               String[] arr = str.split("/");
               Session s = new Session(arr[0], arr[1], Integer.parseInt(arr[2]), arr[3]);
               weekSessions.add(s);
            }
            fr.close();
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("No such file: " + sessionsFile.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
