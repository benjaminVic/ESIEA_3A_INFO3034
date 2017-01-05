 package vic;

import java.util.Objects;

public class ClientGroup extends Client{

	public ClientGroup(String lastName, String firstName, String address) {
		super(lastName, firstName, address);
	}

	@Override
	public String toString(){
		return (super.toString())+"\"Group\"";
	}
	
	@Override
	public double getReservationCost(){
		double sum = 0;
		
		for (Seat s : seats){
			sum = sum + (s.getType().getPrice());
		}
		sum = sum * getPromotionByQuantity(seats.size());
		
		return sum;
	}
	
	@Override
	public String getExplictedCost(){
		StringBuilder sb = new StringBuilder();
		double total = 0;
		for (Seat s : seats) {
			sb.append(s).append(toString()).append("(").append(s.getType().getPrice()).append("€)\n");
			total = total + s.getType().getPrice();
		}
		sb.append(" ").append((1.0-getPromotionByQuantity(seats.size()))*100).append("%\n");
		sb.append("Total : " + total + "€\n");
		return sb.toString();
	}
		
	private double getPromotionByQuantity(int nbSeats){
		if (seats.size()>10){
			return 0.8;
		} else if (seats.size()>5){
			return 0.9;
		} else {
			return 1;
		}
	}
	
}
