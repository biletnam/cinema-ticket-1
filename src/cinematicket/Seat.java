package cinematicket;

import java.io.*;

public class Seat implements Serializable{

	private int row;
	private int number;
	private boolean isOccupied;
	
	public Seat(int row, int number) {
		this.number = number;
		this.row = row;
		isOccupied = false;
	}
	
	public String getNumber() {
		if (isOccupied) {
			return "----";
		}else {
			if (number < 10)
				return "0"+number;
			else
				return "" + number;
		}
	}

	public int getRow() {
	    return row;
    }

	public int getRealNumber() {
		return number;
	}

	public boolean isAvailable() {
	    if (isOccupied)
			return false;
		else
			return true;
    }
	
	public void occupy() {
		this.isOccupied = true;
	}

	public void free() {
	    this.isOccupied = false;
    }
}
