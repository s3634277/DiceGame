import java.util.Scanner;
public class Players {
	
	private String name;
	private int totalScore; /*substitutes [] scorePerPlayer */
	private int [] pointsPerCategory; /*substitutes [][] allPointsOfPlayers */
	
	private static int numberOfPlayers;
	//private static int defaultScore = -1;   will be eliminated and substituted by a literal locally in the functions that need it; i.e., in the constructor to initialize the members of the array instance variable pointsPerCategory, and in the printBoard function
	//private static int [][] allPointsOfPlayers;    will be eliminated; substituted by [] pointsPerCategory;
	//private static int [] scorePerPlayer;
	
	//Players Constructors
	Players (int allPlayers){
		numberOfPlayers = allPlayers;
	}
	
	Players (String name){
		this.name = name;
		pointsPerCategory = new int [ScoreBoard.getnumberOfCategories()];
		for (int i = 0; i < ScoreBoard.getnumberOfCategories(); i++) {
			pointsPerCategory[i] = -1;
		}
	}
	
	// Getters
	public static int getnumberOfPlayers() {
		return numberOfPlayers;
	}
	
	public String getname() {
		return name;
	}
	public int [] getpointsPerCategory () {
		return pointsPerCategory;
	}
	
	public int gettotalScore() {
		return totalScore;
	}
	
	//Setters
		public  void settotalScore(int total) {
			totalScore = totalScore + total;
		}
	
	
	// Array of players
	static Players [] playersArray (){
		Players [] arrayOfPlayers = new Players [numberOfPlayers];
		return arrayOfPlayers;
	}
	
	
	//Determining the highest score
	static int highestScore(Players [] arrayOfPlayers){
		int highestIndex = 0;
		int highestScore = 0;
		for (int i = 0; i < numberOfPlayers; i++) {
			if(arrayOfPlayers[highestIndex].totalScore <= arrayOfPlayers[i].totalScore){
				highestIndex = i;
				highestScore = arrayOfPlayers[highestIndex].totalScore;
			}
		}							
		return highestScore;
	}
	
	
	//Assign points
	static boolean pointsAllocation(Scanner input, int currentPlayer, Players [] arrayOfPlayers) {
		//Sorting dices from lowest to highest points
		int [] diceToAssign = Dice.getdices();
		for (int i = 0; i < Dice.getnumberOfDices() - 1; i++) {
			int lowestIndex = i;
			int lowestDice = diceToAssign[i];
			for (int j = i + 1; j < Dice.getnumberOfDices(); j++) {
				if(diceToAssign[j] < lowestDice) {
					lowestIndex = j;
					lowestDice = diceToAssign[j];
				}
			}
			diceToAssign[lowestIndex] = diceToAssign[i];
			diceToAssign[i] = lowestDice;										
		}
		// Generating "point assignment menu"
		String [] categories = ScoreBoard.getcategories();
		System.out.print("\n Select from the menu which category you wish to assign points to (enter the corresponding digit and then press 'enter'):\n");
		for(int i = 0; i < ScoreBoard.getnumberOfCategories(); i++) {
			System.out.printf("%d. %s\n", i + 1, categories[i]);
		}
		// Processing selection
		boolean flag = true;
		while(flag) {
			//Validation of option entry
			boolean notInteger = true;
			int categoryAssigned = 0;
			while(notInteger) {
				String categoryAssignedString = input.next();
				if(!DiceGame.isNumeric(categoryAssignedString)) {
					System.out.print("You mus enter an integer number between 1 and 10 inclusive: ");
				}else {
					categoryAssigned = Integer.parseInt(categoryAssignedString);
					if(categoryAssigned >= 1 && categoryAssigned <= 10) {
						notInteger = false;
					}else {
						System.out.print("You mus enter an integer number between 1 and 10 inclusive: ");
					}
				}
			}
			// Entry processing
			int allocated = 1;
			switch(categoryAssigned) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
				flag = selectionValidation(categoryAssigned, currentPlayer, arrayOfPlayers);
				if(flag == true) {
					break;
				}
				pointsAllocationToCell (diceToAssign, categoryAssigned, currentPlayer, arrayOfPlayers);
				ScoreBoard.setcellsOccuppied(allocated);
				break;
			case 7:
				flag = selectionValidation(categoryAssigned, currentPlayer, arrayOfPlayers);
				if(flag == true) {
					break;
				}
				int largerSubset = Dice.getnumberOfDices()/2;
				for (int i = 0; i < largerSubset - 1; i++) {
					if(diceToAssign[i] != diceToAssign[i + 1]){
						arrayOfPlayers[currentPlayer].pointsPerCategory[categoryAssigned - 1] = 0;
						break;
					}
				}
				if(arrayOfPlayers[currentPlayer].pointsPerCategory[categoryAssigned - 1] == 0) {
					ScoreBoard.setcellsOccuppied(allocated);
					break;
				}
				for (int i = largerSubset + 1; i < Dice.getnumberOfDices() - 1; i++) {
					if(diceToAssign[i] != diceToAssign[i + 1]){
						arrayOfPlayers[currentPlayer].pointsPerCategory[categoryAssigned - 1] = 0;
						break;
					}
				}
				if(arrayOfPlayers[currentPlayer].pointsPerCategory[categoryAssigned - 1] == 0) {
					ScoreBoard.setcellsOccuppied(allocated);
					break;
				}
				if((diceToAssign[largerSubset] != diceToAssign[largerSubset - 1]) && (diceToAssign[largerSubset] != diceToAssign[largerSubset + 1])) {
					arrayOfPlayers[currentPlayer].pointsPerCategory[categoryAssigned - 1] = 0;
					ScoreBoard.setcellsOccuppied(allocated);
					break;
				}
				arrayOfPlayers[currentPlayer].pointsPerCategory[categoryAssigned - 1] = 20;
				ScoreBoard.setcellsOccuppied(allocated);
				break;
			case 8:
				flag = selectionValidation(categoryAssigned, currentPlayer, arrayOfPlayers);
				if(flag == true) {
					break;
				}
				for (int i = Dice.getnumberOfDices() - 1; i > 0; i--) {
					if (diceToAssign[i] - diceToAssign[i - 1] != 1) {
						arrayOfPlayers[currentPlayer].pointsPerCategory[categoryAssigned - 1] = 0;
						break;
					}
				}
				if (arrayOfPlayers[currentPlayer].pointsPerCategory[categoryAssigned - 1] == 0) {
					ScoreBoard.setcellsOccuppied(allocated);
					break;
				}else {
					arrayOfPlayers[currentPlayer].pointsPerCategory[categoryAssigned - 1] = 30;
					ScoreBoard.setcellsOccuppied(allocated);
					break;
				}
			case 9:
				flag = selectionValidation(categoryAssigned, currentPlayer, arrayOfPlayers);
				if(flag == true) {
					break;
				}
				int matches = 0;
				int midDiceIndex = Dice.getnumberOfDices()/2;
				for (int i = 0; i < Dice.getnumberOfDices(); i++) {
					if(diceToAssign[midDiceIndex] == diceToAssign[i]) {
						matches++;
					} 
				}
				if(matches >= 4) {
					arrayOfPlayers[currentPlayer].pointsPerCategory[categoryAssigned - 1] = 40;
					ScoreBoard.setcellsOccuppied(allocated);
					break;
				}
				arrayOfPlayers[currentPlayer].pointsPerCategory[categoryAssigned - 1] = 0;
				ScoreBoard.setcellsOccuppied(allocated);
				break;
			case 10:
				flag = selectionValidation(categoryAssigned, currentPlayer, arrayOfPlayers);
				if(flag == true) {
					break;
				}
				matches = 0;
				midDiceIndex = Dice.getnumberOfDices()/2;
				for (int i = 0; i < Dice.getnumberOfDices(); i++) {
					if(diceToAssign[midDiceIndex] == diceToAssign[i]) {
						matches++;
					} 
				}
				if(matches == 5) {
					arrayOfPlayers[currentPlayer].pointsPerCategory[categoryAssigned -1] = 50;
					ScoreBoard.setcellsOccuppied(allocated);
					break;
				}
				arrayOfPlayers[currentPlayer].pointsPerCategory[categoryAssigned - 1] = 0;
				ScoreBoard.setcellsOccuppied(allocated);
				break;
			default:
				System.out.print("You mus enter an integer number between 1 and 10 inclusive: ");
			}
		}
		//Checking whether all the cells in the score board are occupied
		if(ScoreBoard.getcellsOccuppied() == (ScoreBoard.getnumberOfCategories() * numberOfPlayers)) {
			return true;
		}else {
			return false;
		}
	}

	
	static boolean selectionValidation (int categoryAssigned, int currentPlayer, Players [] arrayOfPlayers) {
		if (arrayOfPlayers[currentPlayer].pointsPerCategory[categoryAssigned - 1] != -1) {
			System.out.print("\n That category is aready occupied. Please select another category: ");
			return true;
		}
		return false;
	}
	
	static void pointsAllocationToCell (int [] diceToAssign, int categoryAssigned, int currentPlayer, Players [] arrayOfPlayers) {
		int points = 0;
		for(int i = 0; i < Dice.getnumberOfDices(); i++) {
			if(diceToAssign[i] != categoryAssigned) {
				diceToAssign[i] = 0;
			}
			points = points + diceToAssign[i];		
		}
		arrayOfPlayers[currentPlayer].pointsPerCategory[categoryAssigned - 1] = points;
	}
}


