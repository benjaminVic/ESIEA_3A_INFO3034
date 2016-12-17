package vic;

public class ClientVIP extends Client {

	public ClientVIP(String lastName, String firstName, String address) {
		super(lastName, firstName, address);
	}
	
	@Override
	public String toString(){
		return (super.toString()+"\"VIP\"");
	}
	
	@Override
	public double getReservationCost(){
		double sum = 0;
		for (Seat s : seats){
			sum = sum + (s.getType().getPrice() * getPromotionBySeatType(s.getType()));
		}
		return sum;
	}
	
	@Override
	public String getExplictedCost(){
		StringBuilder sb = new StringBuilder();
		double  total = 0;
		for(Seat s : seats){
			if (getPromotionBySeatType(s.getType()) == 1) {
				sb.append(s.toString()+"("+s.getType().getPrice()+"€)\n");
				total = total+s.getType().getPrice();
			} else {
				sb.append(s.toString()+"("+s.getType().getPrice()+
						" -"+(1-getPromotionBySeatType(s.getType())*100)+"% => "
						+s.getType().getPrice() * getPromotionBySeatType(s.getType())+"€)\n");
				total = total+(s.getType().getPrice() * getPromotionBySeatType(s.getType()));
			}
		}
		sb.append("Total : "+total+"€\n");
		return sb.toString();
	}
	
	private double getPromotionBySeatType(SeatType type){
		if (type.getSymbole() == SeatType.FIRST_CATEGORY.getSymbole()){
			return 0.7;	
		} else if (type.getSymbole() == SeatType.SECOND_CATEGORY.getSymbole()) {
			return 0.8;
		} else if (type.getSymbole() == SeatType.THIRD_CATEGORY.getSymbole()) {
			return 0.9;
		} else {
			return 1;
		}
	}
	
	private double getReducedPrice(SeatType type){
		return (type.getPrice()*getPromotionBySeatType(type));
	}
}
