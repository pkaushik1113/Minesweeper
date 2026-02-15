import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;




/*
Name:       Prisha Kaushik
Date:       03/19/2025
Period:     7

Is this lab fully working?  Yes

Create a HW Quiz question that any student who completed this lab would reasonably be expected
to complete within 5 minutes.
How do you decide which methods to put in an interface?
*/




public class P7_Kaushik_Prisha_MinesweeperModel implements P7_Kaushik_Prisha_MSModelInterface{

	
	char[][] revealed;
	char[][] hidden;
	
	int numTotalMines;
	int numFlagsLeft;
	
	
	public P7_Kaushik_Prisha_MinesweeperModel(int rows, int cols, int mines) {
		revealed = new char[rows][cols];
		hidden = new char[rows][cols];
		
		numTotalMines = mines;	
		numFlagsLeft = mines;
		
		fillField();
	}
	
	public void fillField() {
		for(int i = 0; i < getNumRows(); i++) {
			for(int j = 0; j < getNumCols(); j++) {
				revealed[i][j] = '_';
			}
		}
	}
	
	
	
	
	public void generateField(int noRow, int noCol) {
		
		int numAdded = 0;
		Random rand = new Random();
		//System.out.println(noRow + " " + noCol + " " + numTotalMines + " "  + hidden.length + " " + hidden[0].length);
		
		while(numAdded < numTotalMines) {
			int col = rand.nextInt(getNumCols());
			int row = rand.nextInt(getNumRows());
			
			
			if(hidden[row][col] != '*' && row != noRow) {
				hidden[row][col] = '*';
				numAdded++;
			} else if(hidden[row][col] != '*' && col != noCol) {
				hidden[row][col] = '*';
				numAdded++;
			}
			
			rand = new Random();
			
		}

	}
	
	
	
	
	@Override
	public int numBorderMines(int row, int col) {
		
		ArrayList<Point> poss = new ArrayList<Point>();
		poss.add(new Point(-1, -1));
		poss.add(new Point(0, -1));
		poss.add(new Point(1, -1));
		poss.add(new Point(1, 0));
		poss.add(new Point(1, 1));
		poss.add(new Point(0, 1));
		poss.add(new Point(-1, 1));
		poss.add(new Point(-1, 0));
		
		int count = 0;
		
		for(int i = 0; i < poss.size(); i++) {
			Point now = poss.get(i);
			double xcor = now.getX();
			double ycor = now.getY();
			if(xcor + col >= 0 && xcor + col < getNumCols() && ycor + row >= 0 && ycor + row < getNumRows() && hidden[(int)(ycor + row)][(int)(xcor + col)] == '*') {
				count++;
			}
		}
		
		return count;

	}

	@Override
	public boolean isRevealed(int row, int col) {
		if(revealed[row][col] == '_') {
			return false;
		}
		return true;
	}

	@Override
	public boolean isFlagged(int row, int col) {
		if(revealed[row][col] == 'F') {
			return true;
		}
		return false;
	}

	@Override
	public int getNumRows() {
		return hidden.length;
	}

	@Override
	public int getNumCols() {
		return hidden[0].length;
	}

	@Override
	public boolean isGameOver() {
		if(allCellsExposed()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean gameWon() {
		if(allCellsExposed()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isMine(int row, int col) {
		
		if(hidden[row][col] == '*') {
			return true;
		}
		return false;
	}

	@Override
	public void revealCell(int row, int col) {
		if(!isRevealed(row, col) && numBorderMines(row, col) == 0) {
			
			if(isFlagged(row, col)) {
				numFlagsLeft++;
			}
			
			revealed[row][col] = (char)(numBorderMines(row, col) + '0');
			
			if(row + 1 < getNumRows() && col + 1 < getNumCols()) {
				revealCell(row + 1, col + 1);
			}
			
			if(row + 1 < getNumRows()) {
				revealCell(row + 1, col);
			}
			
			if(row + 1 < getNumRows() && col - 1 >= 0) {
				revealCell(row + 1, col - 1);
			}
			
			if(col - 1 >= 0) {
				revealCell(row, col - 1);
			}
			
			if(row - 1 >= 0 && col - 1 >= 0) {
				revealCell(row - 1, col - 1);
			}
			
			if(row - 1 >= 0) {
				revealCell(row - 1, col);
			}
			
			if(row - 1 >= 0 && col + 1 < getNumCols()) {
				revealCell(row - 1, col + 1);
			}
			
			if(col + 1 < getNumCols()) {
				revealCell(row, col + 1);
			}
			
		} else if(numBorderMines(row, col) != 0) {
			if(isFlagged(row, col)) {
				numFlagsLeft++;
			}
			revealed[row][col] = (char)(numBorderMines(row, col) + '0');
		}
		
	}
	

	@Override
	public void toggleFlag(int row, int col) {
		if(revealed[row][col] == '_') {
			revealed[row][col] = 'F';
			numFlagsLeft--;
		} else if(revealed[row][col] == 'F') {
			revealed[row][col] = '_';
			numFlagsLeft++;
		}
		
	}

	@Override
	public int numTotalMines() {
		return numTotalMines;
	}

	@Override
	public int getNumFlagsLeft() {
		return numFlagsLeft;
	}
	
	
	public void printHidden() {
		for(int i = -1; i < getNumRows(); i++) {
			for(int j = -1; j < getNumCols(); j++) {
				if(i < 0 && j < 0) {
					System.out.printf("%-2s", "");
				} else if(i < 0) {
					System.out.printf("%-2d", j);
				} else if(j < 0) {
					System.out.printf("%-2d", i);
				} else if(hidden[i][j] == '*') {
					System.out.printf("%-2s", "*");
				} else {
					System.out.printf("%-2s", "_");
				}
			}
			System.out.println();
		}
	}
	
	
	public void printRevealed() {
		for(int i = -1; i < getNumRows(); i++) {
			for(int j = -1; j < getNumCols(); j++) {
				if(i < 0 && j < 0) {
					System.out.printf("%-2s", "");
				} else if(i < 0) {
					System.out.printf("%-2d", j);
				} else if(j < 0) {
					System.out.printf("%-2d", i);
				} else {
					System.out.printf("%-2c", revealed[i][j]);
				}
			}
			System.out.println();
		}
	}
	
	
	
	public boolean allCellsExposed() {
		for(int i = 0; i < getNumRows(); i++) {
			for(int j = 0; j < getNumCols(); j++) {
				if(!isRevealed(i, j) && !isMine(i, j)) {
					return false;
				} //else if(isMine(i, j) && !isFlagged(i, j)) {
//					return false;
//				}
				
			}
		
		}
		return true;
		
	}
	
	
	public boolean inBounds(int row, int col) {
		if(row < getNumRows() && row >= 0 && col < getNumCols() && col >= 0) {
			return true;
		}
		return false;
	}
	
	public void revealAllMines() {
		for(int i = 0; i < getNumRows(); i++) {
			for(int j = 0; j < getNumCols(); j++) {
				if(isMine(i, j) && !isFlagged(i, j)) {
					revealed[i][j] = '*';
				} else if(isFlagged(i, j) && !isMine(i, j)) {
					toggleFlag(i, j);
					revealed[i][j] = 'X';
				}
			}
		}
	}
	
	
	public char getCharAt(int row, int col) {
		return revealed[row][col];
	}
	
	
	
	
	

}
