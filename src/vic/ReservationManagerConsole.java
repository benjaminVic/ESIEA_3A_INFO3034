package vic;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

public class ReservationManagerConsole {
	
	private Scanner scan;
	private Theater theater;
	private LinkedList<Client> clients;
	
	public ReservationManagerConsole(){
		this.scan = new Scanner(System.in);
		try {
			this.theater = new Theater("files/test1.csv");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ReservationManagerConsole rmc = new ReservationManagerConsole();
		rmc.menuHandler();		
	}
	
	public void menuHandler(){
		System.out.println("Welcome to the Reservation Manager");
		String scannerResult = new String("");
		
		while (!Objects.equals(scannerResult,"q")){
			System.out.println("What do you want to do (h for help)");
			scannerResult = scan.nextLine();
			switch(scannerResult){
			
			case "h" :
				System.out.println("h: Print this help\n"
						+ "st : Show Theater\n"
						+ "mr : Make a Reservation\n"
						+ "cr : Cancel a reservation\n"
						+ "q: Quit");
				break;
				
			case "st" :
				System.out.println(theater.toString());
				break;
				
			case "mr" :
				makeReservation();
				break;
				
			case "cr" :
				cancelReservation();
				break;
				
			case "lc" :
				listClient();
				break;
				
			case "ac" :
				addClient();
				break;
				
			case "rc" :
				removeLClient();
				break;
				
			}
		}
		
		theater.save();
		scan.close();
		System.out.println("Bye Bye");
	}

	public void makeReservation(){
		String valRow,valColumns;
		System.out.println("\nPlease enter row letter");
		valRow = scan.nextLine().toUpperCase();
		System.out.println("\nPlease enter colum number");
		valColumns = scan.nextLine();
		try {
			theater.makeReservation((int)valRow.toCharArray()[0]-'A', Integer.valueOf(valColumns));
		} catch (InvalidActionException e) {
			System.out.println("/!\\ This space is not valid for reservation /!\\");
		}
	}
	
	public void cancelReservation(){
		String valRow,valColumns;
		System.out.println("\nPlease enter row letter");
		valRow = scan.nextLine();
		System.out.println("\nPlease enter colum number");
		valColumns = scan.nextLine();
		try {
			theater.cancelReservation((int)valRow.toCharArray()[0]-'A', Integer.valueOf(valColumns));
		} catch (InvalidActionException e) {
			System.out.println("/!\\ This space is not valid for reservation /!\\");
		}
	}
	
	public void addClient(){
		//TODO
	}
	
	public Client selectClient(){
		//TODO
		return null;
	}
	
	public void removeLClient(){
		//TODO
	}
	
	public void listClient(){
		StringBuilder sb = new StringBuilder();
		for (Client c : clients){
			sb.append(c.toString()+" ");
			//TODO
		}
	}
}
