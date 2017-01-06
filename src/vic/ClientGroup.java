 package vic;

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
		double total = getReservationCost();
		sb.append(getFirstName()).append(' ').append(getLastName()).append(" \"Group\" has reserved seat numbers.\n");
		for (Seat s : seats) {
			sb.append(s).append(" (").append(s.getType().getPrice()).append("€)\n");
		}
		String promotion = "0.0";
		if (getPromotionByQuantity(seats.size()) == 0.9) {
			promotion = "10.0";
		} else if (getPromotionByQuantity(seats.size()) == 0.8) {
			promotion = "20.0";
		}
		sb.append(" ").append(promotion).append("%\n");
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
