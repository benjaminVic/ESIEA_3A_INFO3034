package vic;

public enum SeatType {
	SCENE("S", -1.0),
	OBSTACLE("X", -1.0),
	FIRST_CATEGORY("a", 125.0),
	SECOND_CATEGORY("b", 80.0),
	THIRD_CATEGORY("c",50.0),
	FOURTH_CATEGORY("d",20.0),
	FIFTH_CATEGORY("e",10.0);
	
	private String symbole;
	private double price;

	SeatType(String symbole, double price){
		this.symbole = symbole;
		this.price = price;
	}
	
	public String toString(){
		return getSymbole();
	}
	
	public String getSymbole(){
		return this.symbole;
	}
	
	public double getPrice(){
		return this.price;
	}
}
