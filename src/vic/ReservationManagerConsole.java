package vic;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
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
		this.clients = new LinkedList<Client>();
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
						+ "ac : Add Clients\n"
						+ "lc : List all Clients\n"
						+ "rc : Remove all Clients\n"
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
		String firstName,lastName,address;

		System.out.print("\nPlease enter your lastName :");
		lastName = scan.nextLine();
		System.out.print("\nPlease enter your firstName : ");
		firstName = scan.nextLine();		
		System.out.print("\nPlease enter your address : ");
		address = scan.nextLine();
		
		Client client = new Client(lastName, firstName, address);
		clients.add(client);
	}
	
	public Client selectClient(){
		//TODO
		return null;
	}
	
	public void removeLClient(){
		if (clients.isEmpty()){
			System.out.println("Please add a Client first !");
		} else {
			//Si il y a des clients dans l'appli
			for (Client c : clients){
				System.out.println("Client n°"+c.getFullString());
			}
			System.out.println("Please enter the id of the client to be removed or -1 to cancel the action.");
			try {
				int clientId = scan.nextInt();
				for (Client c : clients){
					if (c.getId() == clientId){
						clients.remove(clients.indexOf(c));
						break;
					}
				}
			} catch (InputMismatchException ime) {
				System.out.println("This is not a valid number !");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void listClient(){
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		if (!clients.isEmpty()){
			for (Client c : clients){
				sb.append(c.toString()+";");
			}
		}
		sb.append(']');
		System.out.println(sb.toString());
	}
}
