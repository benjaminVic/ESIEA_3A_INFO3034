package vic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Theater {

	private Seat[][] seats;
	private String filename;
	private final String sourceFilePath;

	public Theater(int nbRow, int nbCol) {
		this.seats = new Seat[nbRow][nbCol];
		for (int rowIterator = 0; rowIterator < nbRow; rowIterator++) {
			for (int colIterator = 0; colIterator < nbCol; colIterator++) {
				this.seats[rowIterator][colIterator] = new Seat(rowIterator, colIterator, SeatType.FIRST_CATEGORY,
						false);
			}
		}
		this.sourceFilePath = null;
	}

	public Theater(String filename) throws FileNotFoundException {
		//Choix du fichier
		this.sourceFilePath = filename;
		File f = new File(filename);
		
		//Lecture du fichier
		Scanner sc = new Scanner(f, "UTF-8");
		sc.useDelimiter(sc.delimiter() + "|;+");

		// On récupère les premier params
		int rowLenght = Integer.valueOf(sc.next());
		int columnLenght = Integer.valueOf(sc.next());

		this.seats = new Seat[rowLenght][columnLenght];
		
		for (int row = 0; row < rowLenght; row++) {
			for (int col = 0 ; col < columnLenght; col++) {
				String valeurLue = sc.next();
				if (!Objects.equals(valeurLue,"")) {
					seats[row][col] = new Seat(row, col, 
							SeatType.getSeatTypeFromSymbole(valeurLue),Character.isUpperCase(valeurLue.charAt(0)));
				} else {
					col--;
				}
				// seats[row][col] = new Seat(row, col, SeatType.getSeatTypeFromSymbole(symbole),Character.isUpperCase(symbole.charAt(0)));
			}
		}
	}

	public int getNbRow() {
		return seats.length;
	}

	public int getNbCol() {
		return seats[0].length;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int rowIterator = 0; rowIterator < getNbRow(); rowIterator++) {
			sb.append((char) ('A' + rowIterator) + " ");
			for (int columnIterator = 0; columnIterator < getNbCol(); columnIterator++) {
				// En caps si le siège est réservé
				if (seats[rowIterator][columnIterator].isBooked()) {
					sb.append(seats[rowIterator][columnIterator].getType().getSymbole().toUpperCase());
				} else {
					sb.append(seats[rowIterator][columnIterator].getType().getSymbole().toLowerCase());
				}
				sb.append(' ');
			}
			sb.append('\n');
		}

		// On affiche la ligne de numéro de colonne
		sb.append(" ");
		for (int columnIterator = 0; columnIterator < getNbCol(); columnIterator++) {
			sb.append(" " + columnIterator);
		}
		sb.append('\n');

		return sb.toString();
	}

	public void makeReservation(int row, int col) throws InvalidActionException {
		if (seats[row][col].isBooked()) {
			throw new InvalidActionException("Il y a déjà une réservation à cet emplacement.");
		} else {
			seats[row][col].setBooked(true);
		}
	}

	public void cancelReservation(int row, int col) throws InvalidActionException {
		if (seats[row][col].isBooked()) {
			seats[row][col].setBooked(false);
		} else {
			throw new InvalidActionException("Il n'y au aucune reservation à cet emplacement.");
		}
	}

	public void save(){
		//Setting filename
		String newFileNamePath;
		if (Objects.isNull(sourceFilePath)){
			newFileNamePath = "./theatre.bak;";
		} else {
			newFileNamePath = sourceFilePath+".bak";
		}
		
		//Prepping string
		try {
			FileWriter fw = new FileWriter(newFileNamePath);
			StringBuilder sb = new StringBuilder();
			sb.append(getNbRow() + ";" + getNbCol()+"\n");
			for (int rowIterator = 0; rowIterator < getNbRow(); rowIterator++) {
				for (int colIterator = 0; colIterator < getNbCol(); colIterator++) {
					if (seats[rowIterator][colIterator].isBooked()){
						sb.append(seats[rowIterator][colIterator].getType().getSymbole().toUpperCase());
					} else {
						sb.append(seats[rowIterator][colIterator].getType().getSymbole().toLowerCase());
					}
				}
				sb.append('\n');
			}
			//Actually saving shit
			//System.out.println(sb.toString());
			fw.write(sb.toString());
			fw.flush();
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
}
