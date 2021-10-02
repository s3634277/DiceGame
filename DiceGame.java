import java.util.Scanner;
public class DiceGame {
	public static void main(String[] args) {
		
		Scanner input = new Scanner (System.in);
		
	//Creation of player objects and the array to store them
		System.out.print("Welcome to the dice game!!\n\nPlease enter the number of "
														+ "players: ");
			// Array of players
		boolean notInteger = true;
		int allPlayers = 0;
		while(notInteger) {
			String allPlayersString = input.next();
			if(!isNumeric(allPlayersString)) {
				System.out.print("You must enter an integer number: ");
			}else {
				allPlayers = Integer.parseInt(allPlayersString);
				notInteger = false;
			}
		}
		new Players(allPlayers);
		Players [] arrayOfPlayers = Players.playersArray();
		input.nextLine();
			// Player objects
		int playerNumber = 0;
		while (allPlayers > playerNumber) {
		System.out.print("\nPlease enter player's name: ");
		String name = input.nextLine();
		arrayOfPlayers[playerNumber] = new Players(name);
		playerNumber++;
		}
	//Play the game
		final int goesPerTurn = 3;
		int currentPlayer = 0;
		boolean gamePlay = true;
		while(gamePlay){
			int diceRollNumber = 0;
				//Print score board and roll dices for the first time for the first player
			ScoreBoard.printBoard(arrayOfPlayers);
			diceRollNumber = Dice.diceRoll(diceRollNumber, currentPlayer, arrayOfPlayers, input);
			
				// Menu options after rolling dices
			boolean turnPlay = true;
			while(turnPlay) {
				switch(menu(input)) {
					case 1: // Selection of subset of dices to roll in each go
						String rollDecision = Dice.pickedToRoll(input, diceRollNumber);
						while(!rollDecision.equals("Y")) {
							System.out.print("\nLet's do it again\n");
							rollDecision = Dice.pickedToRoll(input, diceRollNumber);
						}
						//Roll dices and print current score board
						ScoreBoard.printBoard(arrayOfPlayers);
						diceRollNumber = Dice.diceRoll(diceRollNumber, currentPlayer, arrayOfPlayers, input);
						if (diceRollNumber >= goesPerTurn) {
							System.out.print("\nYour turn is over; you have to assign points to your score now\n\n");
							// Determining whether all the cells in the score board have been allocated points (if so determines winner and exits the program)
							if(Players.pointsAllocation(input, currentPlayer, arrayOfPlayers)) {
								//Sorts players' scores from highest to lowest to determine winner
								int highestScore = Players.highestScore(arrayOfPlayers);
								System.out.print("\nCONGRATS ");
								for (int i = 0; i < Players.getnumberOfPlayers(); i++ ) {
									if(arrayOfPlayers[i].gettotalScore() == highestScore) {
										System.out.printf(" %s,  ", arrayOfPlayers[i].getname());
									}
								}
								System.out.print(" YOU WON. THE BEERS ARE ON YOU YAY !!!!!!");
								//Printing final score board before exiting the program
								ScoreBoard.printBoard(arrayOfPlayers);
								System.exit(0);
							}else if(currentPlayer >= allPlayers - 1){
								currentPlayer = 0;
							}else {
								currentPlayer++;
							}
							turnPlay = false;
							break;
						}
						break;
					case 2:
						if(Players.pointsAllocation(input, currentPlayer, arrayOfPlayers)) {
							int highestScore = Players.highestScore(arrayOfPlayers);
							System.out.print("\nCONGRATS ");
							for (int i = 0; i < Players.getnumberOfPlayers(); i++ ) {
								if(arrayOfPlayers[i].gettotalScore() == highestScore) {
									System.out.printf(" %s,  ", arrayOfPlayers[i].getname());
								}
							}
							System.out.print("YOU WON. THE BEERS ARE ON YOU YAY !!!!!!");
							//Printing final score board before exiting the program
							ScoreBoard.printBoard(arrayOfPlayers);
							System.exit(0);
						}else if(currentPlayer >= allPlayers - 1){
							currentPlayer = 0;                                                
						}else {
							currentPlayer++;
						}
						turnPlay = false;
						break;
				/*System.exit(0); */
					case 3:
						System.out.print("\nBYE!");
						System.exit(0);
				}
			}
		}
	}
	
	// Menu
	static int menu (Scanner input){
		System.out.println("\nSelect an option: \n" +
							"1. Pick a set of dices to roll again\n" +
							"2. Assign points to your score and end turn\n" +
							"3. End the game\n");
		
		
		boolean notInteger = true;
		int menuSelection = 0;
		while(notInteger) {
			String menuSelectionString = input.next();
			if(!DiceGame.isNumeric(menuSelectionString) || menuSelectionString == "") {
				System.out.print("You must enter an integer number between 1 and 3 inclusive: ");
			}else {
				menuSelection = Integer.parseInt(menuSelectionString);
				if(menuSelection >= 1 && menuSelection <= 3) {
					notInteger = false;
				}else {
					System.out.print("You must enter an integer number between 1 and 3 inclusive: ");
				}
			}
		}
		return menuSelection;
	}
	
	//Utility function to check whether an entry is a number
	public static boolean isNumeric(String str) { 
		  try {  
		    Integer.parseInt(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
	
}

