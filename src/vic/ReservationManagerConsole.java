package vic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

public class ReservationManagerConsole {
	
	private Scanner scan;
	private Theater theater;
	private LinkedList<Client> clients;
	private static final String pathToClients = "files/clientList.bak";
	
	public ReservationManagerConsole(){
		
		//Au besoin on crée le dossier files
		File filesDir = new File("files");
		if (!filesDir.exists()) {
		    try{
		        filesDir.mkdir();
		    } 
		    catch(SecurityException se){
		        se.printStackTrace();
		    }
		}
		
		this.scan = new Scanner(System.in);
		try {
			this.theater = new Theater("files/test1.csv");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.clients = new LinkedList<Client>();
		File f = new File(pathToClients);
		if (f.exists()){
			try {
				clients = Serializer.<LinkedList<Client>>loadFromFile(pathToClients);
				//On regarde le numéro le plus haut
				int id=0;
				for (Client c : clients){
					if (c.getId()>id){
						id = c.getId()+1;
						c.setCurrentId(id);
					}
				}				
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
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
						+ "sr : Show Reservation\n"
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
				
			case "sr" :
				showReservation();
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
		try {
			Serializer.saveToFile(pathToClients, clients);
		} catch (IOException e) {
			e.printStackTrace();
		}
		theater.save();
		scan.close();
		System.out.println("Bye Bye");
	}

	public void makeReservation(){
		if (clients.isEmpty()){
			System.out.println("Please add a Client first !");
		} else {
			for (Client c : clients){
				System.out.println("Client n°"+c.getFullString());
			}
			System.out.println("Please enter the id of the wanted client or -1 to cancel the action.");
			try {
				int clientId = Integer.valueOf(scan.nextLine());
				for (Client c : clients){
					if (c.getId() == clientId){
						String valRow,valColumns;
						System.out.println("Please enter row letter");
						valRow = scan.nextLine().toUpperCase();
						System.out.println("Please enter colum number");
						valColumns = scan.nextLine();
						try {
							c.addSeat(theater.makeReservation((int)valRow.toCharArray()[0]-'A',
									Integer.valueOf(valColumns)));
						} catch (Exception e) {
							System.out.println("/!\\ This space is not valid for reservation /!\\");
						}
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
	
	public void showReservation(){
		if (clients.isEmpty()){
			System.out.println("Please add a Client first !");
		} else {
			for (Client c : clients){
				System.out.println("Client n°"+c.getFullString());
			}
			System.out.println("Please enter the id of the wanted client or -1 to cancel the action.");
			try {
				int clientId = Integer.valueOf(scan.nextLine());
				for (Client c : clients){
					if (c.getId() == clientId){
						System.out.println(c.getExplictedCost());
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

	public void cancelReservation(){
		if (clients.isEmpty()){
			System.out.println("Please add a Client first !");
		} else {
			for (Client c : clients){
				System.out.println("Client n°"+c.getFullString());
			}
			System.out.println("Please enter the id of the wanted client or -1 to cancel the action.");
			try {
				int clientId = Integer.valueOf(scan.nextLine());
				for (Client c : clients){
					if (c.getId() == clientId){
						String valRow,valColumns;
						System.out.println("\nPlease enter row letter");
						valRow = scan.nextLine().toUpperCase();
						System.out.println("\nPlease enter colum number");
						valColumns = scan.nextLine();
						try {
							c.removeSeat(theater.cancelReservation((int)valRow.toCharArray()[0]-'A',
									Integer.valueOf(valColumns)));
						} catch (InvalidActionException e) {
							System.out.println("/!\\ This space is not valid for reservation /!\\");
						}

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
	
	public void addClient(){
		String firstName,lastName,address;

		System.out.println("Please enter your lastName :");
		lastName = scan.nextLine();
		System.out.println("Please enter your firstName : ");
		firstName = scan.nextLine();		
		System.out.println("Please enter your address : ");
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
				int clientId = Integer.valueOf(scan.nextLine());
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
