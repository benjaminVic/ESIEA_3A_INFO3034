package vic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
	
	public Theater (String filename) throws FileNotFoundException{
		File f = new File(filename);
		Scanner sc = new Scanner(f, "UTF-8");
		sc.useDelimiter(sc.delimiter()+"|;+");
		while(sc.hasNext()){
			System.out.println(sc.next());
			//seats[row][col] = new Seat(row, col, SeatType.getSeatTypeFromSymbole(symbole),Character.isUpperCase(symbole.charAt(0)));
		}
	}
	
	public int getNbRow(){
		return seats.length;
	}
	
	public int getNbCol(){
		return seats[0].length;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for (int rowIterator = 0 ; rowIterator < getNbRow()  ; rowIterator++) {
			sb.append((char)('A'+rowIterator)+" ");
			for (int columnIterator = 0 ; columnIterator<getNbCol() ; columnIterator++){
				//En caps si le siège est réservé
				if (seats[rowIterator][columnIterator].isBooked()){
					sb.append('A');
				} else {
					sb.append('a');
				}
				sb.append(' ');
			}
			sb.append('\n');
		}
		
		//On affiche la ligne de numéro de colonne
		sb.append(" ");
		for (int columnIterator = 0 ; columnIterator < getNbCol() ; columnIterator++){
			sb.append(" " + columnIterator);
		}
		sb.append('\n');
		
		return sb.toString();
	}
	
	public void makeReservation(int row, int col) throws InvalidActionException{
		if (seats[row][col].isBooked()){
			throw new InvalidActionException("Il y a déjà une réservation à cet emplacement.");
		} else {
			seats[row][col].setBooked(true);
		}
	}
	
	public void cancelReservation(int row, int col)	throws InvalidActionException{
		if (seats[row][col].isBooked()){
			seats[row][col].setBooked(false);
		} else {
			throw new InvalidActionException("Il n'y au aucune reservation à cet emplacement.");
		}
	}
	
}
