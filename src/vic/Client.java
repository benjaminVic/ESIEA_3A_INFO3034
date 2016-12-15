package vic;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Client implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static int currentId = 0;
	private final int id;
	private final String lastName; 
	private final String firstName;
	private final String address;
	public LinkedList<Seat> seats;
	
	public Client (String lastName, String firstName, String address) {
		this.id = currentId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		currentId ++;
		seats = new LinkedList<Seat>();
	}

	public int getId(){
		return this.id;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public String getAddress(){
		return this.address;
	}
	
	public String toString(){
		return getFirstName()+" "+getLastName();
	}
	
	public String getFullString(){
		return String.valueOf(getId())+" "+toString()+" ("+getAddress()+")";
	}
	
	public void setCurrentId(int id){
		currentId = id;
	}
	
	public void addSeat(Seat s){
		seats.add(s);
	}
	
	public void removeSeat(Seat s){
		seats.remove(s);	
	}
	
	public List<Seat> getSeats(){
		return seats;
	}
	
	public double getReservationCost(){
		double sum = 0;
		for (Seat s : seats){
			sum = sum + s.getType().getPrice();
		}
		return sum;
	}
	
	public String getExplicitedCost(){
		//TODO
		return null;
	}
	
}
