import java.util.Scanner;

public class Dice {
	static private int numberOfDices = 5;
	static private int [] dices = new int [numberOfDices];
	static private int [] dicesToRoll = new int [numberOfDices];
	
	

	//Getters
	static public int [] getdices() {
		return dices;
	}
	
	static public int getnumberOfDices() {
		return numberOfDices;
	}
	
	
	//Dice roll
	static int diceRoll(int diceRollNumber, int currentPlayer, Players [] arrayOfPlayers, Scanner input) {
		if(diceRollNumber == 0) {
			System.out.print("\n\nHi "  + arrayOfPlayers[currentPlayer].getname() + ", it's your turn now, press 'y' and then 'enter' to roll the dices for the first time in this turn"
					+ " (y/n): ");
			while(true) {
				if(input.next().toUpperCase().equals("Y")) {
					System.out.printf("\n\n%s\n\n", "Dices");
					for (int i = 0; i < 5; i++) {
							dices [i] = 1 + (int)(Math.random() * 6);
							System.out.printf("dice %c = %d\n", ('A' + i), dices [i]);
					}
					++diceRollNumber;
					System.out.printf("\n This was go number %d rolling the dices in this turn\n", diceRollNumber);
					return diceRollNumber;
				}
				System.out.print("\n\nCome on now  " + arrayOfPlayers[currentPlayer].getname() + ", you have to press 'y': ");
			}
		}else {
			++diceRollNumber;
			System.out.printf("\n This was go number %d rolling the dices in this turn\n", diceRollNumber);
			System.out.printf("\n\n%-16s%s\n\n", "Dices rolled", "Dices kept");
			for (int i = 0; i < 5; i++) {
				if(dicesToRoll[i] == 1) {
					dices [i] = 1 + (int)(Math.random() * 6);
					System.out.printf("%s %c = %d\n", "Dice",  ('A' + i), dices[i]);
				}else {
					System.out.printf("%21s %c = %d\n", "Dice", ('A' + i), dices [i]);
				}
			}
			return diceRollNumber;
		}
	}
	//Selection of dices to roll
	static String pickedToRoll(Scanner input, int diceRollNumber) {
		if(diceRollNumber != 1) {
			System.out.print("\n\nWould you like to pick the same dices as the previous go? (y/n):  ");
			if (input.next().toUpperCase().equals("Y")) {
				System.out.print("\n\nAre you sure that's how you want to roll the dices? (y/n): ");
				String rollDecision = input.next().toUpperCase();
				if(!rollDecision.equals("Y")) {
					for (int i = 0; i < numberOfDices; i++){
						dicesToRoll[i] = 0;
					}
				}
				return rollDecision;
			}
			else {
				return pickingProcess(input);
			}
		}else {
			return pickingProcess(input);
		}
	}
	
	static String pickingProcess (Scanner input) {
		for (int i = 0; i < numberOfDices; i++){
			dicesToRoll[i] = 0;
		}
		System.out.print("\nPlease enter the letters representing each dice to roll in alphabetical order-no space in between, then press the "
				+ "\"enter\" key. \n(e.g. if you want to pick the dices 'A' and 'D' press ' ad\"enter\" ' not ' da\"enter\" '). " 
				+ "If you want to roll all the dices just press \"enter\"\n");
		input.nextLine();
		String dicesPicked = "";
		boolean pickingDice = true;
		while(pickingDice) {
			dicesPicked = input.nextLine();
			if(dicesPicked.equals("")) {
				break;
			}
			//Input validation
			if(dicesPicked.length() > 5) {
				System.out.print("\nYou may enter up to 5 dices. Please try again: ");
				continue;
			}
			int i;
			for (i = 0; i < dicesPicked.length(); i++) {
				if(dicesPicked.toUpperCase().charAt(i) < 'A' || dicesPicked.toUpperCase().charAt(i) > 'A' + numberOfDices - 1){
					System.out.print("\nYou must enter letters between 'a' and 'e' (upper or lower case). Please try again: ");
					break;
				}
			}
			if(i < dicesPicked.length()) {
				continue;
			}
			pickingDice = false;
		}
		//Signaling which dices are to be rolled
			//All the dices are to be rolled
		if(dicesPicked.equals("")) {
			for (int i = 0; i < numberOfDices; i++) {
				dicesToRoll [i] = 1;
			}
			//Specific dices are to be rolled
		}else {
			for (int i = 0; i < dicesPicked.length(); i++) {
				dicesToRoll[dicesPicked.toUpperCase().charAt(i) - 'A'] = 1;
			}	
		}
		System.out.printf("\n\n%-16s%s\n\n", "Dices to roll", "Dices to keep");
		for(int i = 0; i < numberOfDices; i++) {
			if(dicesToRoll[i] == 1) {
				System.out.printf("Dice %c = %d\n", 65 + i, dices[i]);
			}else {
				System.out.printf("%21s %c = %d\n", "Dice",  65 + i, dices[i]);
			}
		}
		System.out.print("\n\nAre you sure that's how you want to roll the dices? (y/n): ");
		String rollDecision = input.next().toUpperCase();
		if(!rollDecision.equals("Y")) {
			for (int i = 0; i < numberOfDices; i++){
				dicesToRoll[i] = 0;
			}
		}
		return rollDecision;
	}
	
}
