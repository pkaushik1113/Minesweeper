import java.util.Scanner;



/*
Name:       Prisha Kaushik
Date:       03/19/2025
Period:     7

Is this lab fully working?  Yes

Create a HW Quiz question that any student who completed this lab would reasonably be expected
to complete within 5 minutes.
How do you decide which methods to put in an interface?
*/



public class P7_Kaushik_Prisha_MinesweeperController {

	 P7_Kaushik_Prisha_MinesweeperModel mod;
	 boolean revealed = false;
	

	
	public P7_Kaushik_Prisha_MinesweeperController(int rows, int cols, int mines) {
		 mod = new P7_Kaushik_Prisha_MinesweeperModel(rows, cols, mines);
	}
	
	
	
	
	public void play() {
		System.out.println("Welcome to... ");
		System.out.println("  __  __ _             _____                                  \n"
				+ " |  \\/  (_)           / ____|                                 \n"
				+ " | \\  / |_ _ __   ___| (_____      _____  ___ _ __   ___ _ __ \n"
				+ " | |\\/| | | '_ \\ / _ \\\\___ \\ \\ /\\ / / _ \\/ _ \\ '_ \\ / _ \\ '__|\n"
				+ " | |  | | | | | |  __/____) \\ V  V /  __/  __/ |_) |  __/ |   \n"
				+ " |_|  |_|_|_| |_|\\___|_____/ \\_/\\_/ \\___|\\___| .__/ \\___|_|   \n"
				+ "                                             | |              \n"
				+ "                                             |_|              ");
		
		Scanner in = new Scanner(System.in);
		while(true) {
			
			
			
			//mod.printRevealed();
			System.out.println("************* What the player sees *************");
			printBoard();
			System.out.println();
			System.out.println("************* Lower board *************");
			mod.printHidden();
			
			if(mod.allCellsExposed() && revealed) {
				System.out.println("Game Won!");
				break;
			}
			
			System.out.println("There are " + mod.getNumFlagsLeft() + " mines remaining.\n");
			
	//		System.out.println("************* Lower board *************");
	//		mod.printHidden();
			
			System.out.println("Would you like to flag a cell, reveal a cell, or quit?");
			System.out.print("Enter 'f' or 'r' or 'q' -> ");
			String input = in.next();
			
			if(input.charAt(0) == 'q' || input.charAt(0) == 'Q') {
				break;
			}
			
			System.out.print("Enter row -> ");
			int row = in.nextInt();
			System.out.print("Enter col -> ");
			int col = in.nextInt();
			
			if(input.charAt(0) == 'r' || input.charAt(0) == 'R') {
				if(!revealed) {
					mod.generateField(row, col);
					revealed = true;
				}
				if(mod.inBounds(row,  col) && !mod.isMine(row, col)) {
					mod.revealCell(row, col);
				} else if(mod.isMine(row, col)) {
					System.out.println();
					mod.revealAllMines();
					printBoard();
					System.out.println("Game Over!");
					break;
				} else {
					System.out.println("Enter valid input");
					continue;
				}
			} else if(input.charAt(0) == 'f' || input.charAt(0) == 'F') {
				if(mod.inBounds(row,  col)) {
					mod.toggleFlag(row, col);
				} else {
					System.out.println("Enter valid input");
					continue;
				}
			}
			
			System.out.println();
			
		}
		
		System.out.println("\nWant to play again?");
		System.out.print("Enter 'y' or 'n' -> ");
		String input = in.next();
		
		System.out.println();
		
		if(input.charAt(0) == 'y' || input.charAt(0) == 'Y') {
			play();
		}
		
		
		
	}
	
	
	
	
	public void printBoard() {
		for(int i = -1; i < mod.getNumRows(); i++) {
			for(int j = -1; j < mod.getNumCols(); j++) {
				if(i < 0 && j < 0) {
					System.out.printf("%-2s", "");
				} else if(i < 0) {
					System.out.printf("%-2d", j);
				} else if(j < 0) {
					System.out.printf("%-2d", i);
				} else{
					if(mod.getCharAt(i, j) == '0') {
						System.out.printf("%-2s", "");
					} else {
						System.out.printf("%-2c", mod.getCharAt(i, j));
					}
				}
			}
			System.out.println();
		}
	}
	
	
	

	
	
	
}
