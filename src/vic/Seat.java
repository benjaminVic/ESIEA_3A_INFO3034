package vic;

public class Seat {

	private int row;
	private int col;
	private SeatType type;
	private boolean isBooked;
	
	public Seat(int row, int col, SeatType type, boolean isBooked){
		this.row = row;
		this.col = col;
		this.type = type;
		this.isBooked = isBooked;
	}
	
	public int getRow(){
		return this.row;
	}
	
	public int getCol(){
		return this.col;
	}
	
	public SeatType getType(){
		return this.type;
	}
	
	public boolean sBooked(){
		return this.isBooked;
	}
	
	public void setBooked(boolean isBooked){
		this.isBooked = isBooked;
	}
	
	public String toString(){
		char rowLetter = (char) ('A' + row);
		return ""+rowLetter+col;
	}
	
}
