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
		return -1;
		//TODO
	}
	
	@Override
	public String getExplictedCost(){
		return null;
		//TODO
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
