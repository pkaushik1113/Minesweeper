

/*
Name:       Prisha Kaushik
Date:       03/13/2025
Period:     7

Is this lab fully working?  Yes

Create a HW Quiz question that any student who completed this lab would reasonably be expected
to complete within 5 minutes.
How do you decide which methods to put in an interface?
*/



public interface P7_Kaushik_Prisha_MSModelInterface {

	
	//public void updateData();
	
	
	public int numBorderMines(int row, int col);
	
	
	public boolean isRevealed(int row, int col);
	
	
	public boolean isFlagged(int row, int col);
	
	
	public int getNumRows();
	
	
	public int getNumCols();
	
	
	public boolean isGameOver();
	
	
	public boolean gameWon();
	
	
	public boolean isMine(int row, int col);
	
	
	public void revealCell(int row, int col);
	
	
	public void toggleFlag(int row, int col);
	
	
	public int numTotalMines();
	
	
	public int getNumFlagsLeft();
	
}
