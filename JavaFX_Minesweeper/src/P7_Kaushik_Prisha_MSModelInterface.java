



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
