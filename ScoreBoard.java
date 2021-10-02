
public class ScoreBoard {
	final private static int numberOfCategories = 10;
	final private static String [] categories = {"One", "Two", "Three", "Four", "Five", "Six", "F HOUSE", "STRAIGHT",
			"POKER", "COMMANDER"};
	private static int cellSize = 15;
	private static int cellsOccuppied;
	
	//Getters
	public static int getnumberOfCategories() {
		return numberOfCategories;
	}
	public static int getcellsOccuppied() {
		return cellsOccuppied;
	}
	public static String [] getcategories() {
		return categories;
	}
	
	//Setters
	public static void setcellsOccuppied(int allocated) {
		cellsOccuppied = cellsOccuppied + allocated;
	}
	
	//Print board
	static void printBoard(Players [] arrayOfPlayers) {
		System.out.print("\n\nCurrent score board\n\n");
		//Printing line with players' names
		playerNamesRow(arrayOfPlayers);
		//Printing scores
		scoreMatrix(arrayOfPlayers);
		totalScore(arrayOfPlayers);
	}

	static void playerNamesRow(Players [] arrayOfPlayers) {
		for (int i = 0; i < cellSize; i++) {
			System.out.print("x");
		};
		System.out.print("|");
		int width1;
		int width2;
		
		for (int i = 0; i < Players.getnumberOfPlayers(); i++) {
			if (cellSize - arrayOfPlayers[i].getname().length() % 2 == 0) {
				width1 = (cellSize - arrayOfPlayers[i].getname().length())/2 + arrayOfPlayers[i].getname().length();
				width2 = cellSize - width1 + 1;
				
			}else {
				width1 = (cellSize - arrayOfPlayers[i].getname().length())/2 + arrayOfPlayers[i].getname().length() + 1;
				width2 = cellSize - width1 + 1;
				
			}
			System.out.printf("%" + width1 + "s", arrayOfPlayers[i].getname());
			System.out.printf("%" + width2 + "s", "|");
		}
		System.out.print("\n");
	}
	
	static void scoreMatrix(Players [] arrayOfPlayers) {
		for (int categoryCounter = 0; categoryCounter < numberOfCategories;	categoryCounter++) {
			lineRow();
			scoreRow(categoryCounter, arrayOfPlayers);
			System.out.print("\n");
		};
		lineRow();
	}
	
	static void lineRow() {
		for (int playerCounter = 0; playerCounter <= Players.getnumberOfPlayers(); playerCounter++) {
			for(int j = 1; j <= cellSize; j++) {
				System.out.print("-");
			};
			System.out.print(" ");
		}
		System.out.print("\n");
	}
	
	static void scoreRow(int categoryCounter, Players [] arrayOfPlayers) {
		scoreHeadline(categoryCounter);
		scores(categoryCounter, arrayOfPlayers);
	}
	
	static void scoreHeadline(int categoryCounter) {
		int width1;
		int width2;
		if (cellSize -  categories[categoryCounter].length() % 2 == 0) {
			width1 = (cellSize - categories[categoryCounter].length())/2 + categories[categoryCounter].length();
			width2 = cellSize - width1 + 1;
		}else {
			width1 = (cellSize - categories[categoryCounter].length())/2 + categories[categoryCounter].length() + 1;
			width2 = cellSize - width1 + 1;
		}
		System.out.printf("%" + width1 + "s", categories[categoryCounter]);
		System.out.printf("%" + width2 + "s", "|");
	}
	
	static void scores(int categoryCounter, Players [] arrayOfPlayers) {
		
		int scoreDigits = 2;
		int width1;
		int width2;
		if (cellSize -  scoreDigits % 2 == 0) {
			width1 = (cellSize - scoreDigits)/2 + scoreDigits;
			width2 = cellSize - width1;
		}
		else {
			width1 = (cellSize - scoreDigits)/2 + scoreDigits + 1;
			width2 = cellSize - width1 + 1;
		}
		for(int i = 0; i < arrayOfPlayers.length; i++) {
			if(arrayOfPlayers[i].getpointsPerCategory()[categoryCounter] == -1) {
				System.out.printf("%" + width1 + "s", "--");
			}else{
				System.out.printf("%" + width1 + "d", arrayOfPlayers[i].getpointsPerCategory()[categoryCounter]);
			}
			System.out.printf("%" + width2 + "s", "|");
		}
	}
	
	static void totalScore(Players [] arrayOfPlayers){
		//Printing the headline
			//Padding on each side
		int width1;
		int width2;
		if (cellSize -  "Total Score".length() % 2 == 0) {
			width1 = (cellSize - "Total Score".length())/2 + "Total Score".length();
			width2 = cellSize - width1;
		}else {
			width1 = (cellSize - "Total Score".length())/2 + "Total Score".length() + 1;
			width2 = cellSize - width1 + 1;
		}
			//The printing it self
		System.out.printf("%" + width1+ "s", "Total Score");
		System.out.printf("%" + width2  + "s", "|");
		//Printing the totals per player
			//Padding on each side
		int scoreDigits = 3;
		if (cellSize -  scoreDigits % 2 == 0) {
			width1 = (cellSize - scoreDigits)/2 + scoreDigits;
			width2 = cellSize - width1;
		}
		else {
			width1 = (cellSize - scoreDigits)/2 + scoreDigits + 1;
			width2 = cellSize - width1 + 1;
		}
			//Computing the totals per player and saving them in an array
		for (int playerCounter = 0; playerCounter < Players.getnumberOfPlayers(); playerCounter++) {
			int total = 0;
			for(int categoryCounter = 0; categoryCounter < numberOfCategories; categoryCounter++) {
				if(arrayOfPlayers[playerCounter].getpointsPerCategory()[categoryCounter] != -1) {
					total = total + arrayOfPlayers[playerCounter].getpointsPerCategory()[categoryCounter];
				}
			}
			//The printing it self
			System.out.printf("%" + width1+ "d", total);
			System.out.printf("%" + width2  + "s", "|");
			//Saving the total score per player
			arrayOfPlayers[playerCounter].settotalScore(total);
			
		};
		System.out.print("\n\n\n");
		System.out.print("F HOUSE   = 3 dices the same + 2 dices the same\nSTRAIGHT  = dices 1 to 5 or 2 to 6\nPOKER     = 4 dices de same"
				+ "\nCOMMANDER = 5 dices the same\n");
	}
}
