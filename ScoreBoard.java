
public class ScoreBoard {
	final private static int numberOfCategories = 10;
	final private static String [] categories = {"One", "Two", "Three", "Four", "Five", "Six", "F HOUSE", "STRAIGHT", "POKER",
			"COMMANDER"};
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
	static void printBoard(Players [] arrayOfPlayers, int [] [] playerScores){
		System.out.print("\n\nCurrent score board\n\n");
		//Printing line with players' names
		playerNamesRow(arrayOfPlayers);
		//Printing scores
		scoreMatrix(playerScores);
		totalScore(playerScores);
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
				width1 = (cellSize - arrayOfPlayers[i].getname().length())/2;
				width2 = (cellSize - arrayOfPlayers[i].getname().length())/2 + arrayOfPlayers[i].getname().length() + 1;
			}else {
				width1 = (cellSize - arrayOfPlayers[i].getname().length())/2;
				width2 = (cellSize - arrayOfPlayers[i].getname().length())/2 + arrayOfPlayers[i].getname().length() + 2;
			}
			System.out.printf("%" + width2 + "s", arrayOfPlayers[i].getname());
			System.out.printf("%" + width1 + "s", "|");
		}
		System.out.print("\n");
	}
	
	static void scoreMatrix(int [] [] playerScores) {
		for (int categoryCounter = 0; categoryCounter < numberOfCategories;	categoryCounter++) {
			lineRow();
			scoreRow(categoryCounter, playerScores);
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
	
	static void scoreRow(int categoryCounter, int [] [] playerScores) {
		scoreHeadline(categoryCounter);
		scores(categoryCounter, playerScores);
	}
	
	static void scoreHeadline(int categoryCounter) {
		int width1;
		int width2;
		if (cellSize -  categories[categoryCounter].length() % 2 == 0) {
			width1 = (cellSize - categories[categoryCounter].length())/2;
			width2 = (cellSize - categories[categoryCounter].length())/2 + categories[categoryCounter].length() + 1;
		}else {
			width1 = (cellSize - categories[categoryCounter].length())/2;
			width2 = (cellSize - categories[categoryCounter].length())/2 + categories[categoryCounter].length() + 2;
		}
		System.out.printf("%" + width2 + "s", categories[categoryCounter]);
		System.out.printf("%" + width1 + "s", "|");
	}
	
	static void scores(int categoryCounter, int [] [] playerScores) {
		int scoreDigits = 2;
		int width1;
		int width2;
		if (cellSize -  scoreDigits % 2 == 0) {
			width1 = (cellSize - scoreDigits)/2;
			width2 = (cellSize - scoreDigits)/2 + scoreDigits + 1;
		}
		else {
			width1 = (cellSize - scoreDigits)/2;
			width2 = (cellSize - scoreDigits)/2 + scoreDigits + 2;
		}
		for (int playerCounter = 0; playerCounter < Players.getnumberOfPlayers(); playerCounter++) { 
			if(playerScores[categoryCounter][playerCounter] == Players.getdefaultScore()) {
				System.out.printf("%" + width2 + "s", "--");
			}else{
				System.out.printf("%" + width2 + "d", playerScores[categoryCounter][playerCounter]);
			}
			System.out.printf("%" + width1 + "s", "|");
			
		};
	}
	
	static void totalScore(int [] [] playerScores){
		//Printing the headline
			//Padding on each side
		int width1;
		int width2;
		if (cellSize -  "Total Score".length() % 2 == 0) {
			width1 = (cellSize - "Total Score".length())/2;
			width2 = (cellSize - "Total Score".length())/2 + "Total Score".length() + 1;
		}else {
			width1 = (cellSize - "Total Score".length())/2;
			width2 = (cellSize - "Total Score".length())/2 + "Total Score".length() + 2;
		}
			//The printing it self
		System.out.printf("%" + width2 + "s", "Total Score");
		System.out.printf("%" + width1 + "s", "|");
		//Printing the totals per player
			//Padding on each side
		int scoreDigits = 2;
		if (cellSize -  scoreDigits % 2 == 0) {
			width1 = (cellSize - scoreDigits)/2;
			width2 = (cellSize - scoreDigits)/2 + scoreDigits + 1;
		}
		else {
			width1 = (cellSize - scoreDigits)/2;
			width2 = (cellSize - scoreDigits)/2 + scoreDigits + 2;
		}
			//Computing the totals per player and saving them in an array
		for (int playerCounter = 0; playerCounter < Players.getnumberOfPlayers(); playerCounter++) {
			int total = 0;
			for(int categoryCounter = 0; categoryCounter < numberOfCategories; categoryCounter++) {
				if(playerScores[categoryCounter][playerCounter] != Players.getdefaultScore()) {
					total = total + playerScores[categoryCounter][playerCounter];
				}
			}
			//The printing it self
			System.out.printf("%" + width2 + "d", total);
			System.out.printf("%" + width1 + "s", "|");
			//Saving the total score per player
			Players.getscorePerPlayer()[playerCounter] = total;
			
		};
		System.out.print("\n");
	}
}
