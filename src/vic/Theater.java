package vic;

public class Theater {

	private Seat[][] seats;
	private String filename;
	
	public Theater (int nbRow, int nbCol){
		this.seats = new Seat[nbRow][nbCol];
		for (int rowIterator = 0 ; rowIterator<nbRow ; rowIterator++){
			for (int colIterator = 0 ; colIterator<nbCol ; colIterator++){
				this.seats[rowIterator][colIterator] = new Seat(rowIterator, colIterator, SeatType.FIRST_CATEGORY, false);
			}
		}
	}
	
	public int getNbRow(){
		return seats.length;
	}
	
	public int getNbCol(){
		return seats[0].length;
	}
	
	public String toString(){
		//TODO
		return null;
	}
	
	public void makeReservation(int row, int col) throws InvalidActionException{
		//TODO
	}
	
	public void cancelReservation(int row, int col)	throws InvalidActionException{
		//TODO
	}
	
}
