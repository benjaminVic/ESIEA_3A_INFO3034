package vic;

import java.util.Objects;
import java.util.Scanner;

public class ReservationManagerConsole {
	
	public ReservationManagerConsole(){
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome to the Reservation Manager");
		String scannerResult = new String("");
		Scanner sc = new Scanner(System.in);
		while (!Objects.equals(scannerResult,"q")){
			System.out.println("What do you want to do (h for help)");
			scannerResult = sc.nextLine();
			switch(scannerResult){
			
			case "h" :
				System.out.println("h: Print this help\nq: Quit");
				break;
			}
		}
		System.out.println("Bye Bye");
	}

}
