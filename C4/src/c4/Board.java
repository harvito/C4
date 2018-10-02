package c4;

public class Board {
	private char[][] grid = new char[5][7]; //5 rows, 7 columns
    
	public Board() {
    	for (int i = 0; i < 5; i++) {
        	for (int j = 0; j < 7; j++) {
            	grid[i][j] = ' ';
        	}
    	}
	}
    
	//player 1 is x
	public void insert(char colour, int playerCol) throws Exception {
    	//find space
    	int i;
    	int boardCol = playerCol - 1;
    	for (i = 4; i >= 0; i--)
        	if (grid[i][boardCol] != ' ')
            	break;
   	 
	   	if (i == 4) //no space left in column
	   		throw new Exception("column is full");
	   	else
	       	grid[i+1][boardCol] = colour;
	}
    
	public void print() {
    	for (int i = 4; i >= 0; i--) {
        	System.out.print("| ");
        	for (int j = 0; j < 7; j++) {
            	System.out.print(grid[i][j] + " ");
        	}
        	System.out.print("|\n");
    	}
    	System.out.println("+ 1 2 3 4 5 6 7 +");
	}
	
	//checks if there's a winner or if the game has drawn
	public void updateGameStatus(C4 game) {
		char colour;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++) {
				colour = grid[i][j];
				if (checkWinAt(i, j))
					game.setGameWon(colour);
			}
		}
		if (checkDraw())
			game.setGameDrawn();
	}
	
	private Boolean checkWinAt(int row, int col) {
		char colour = grid[row][col];
		if (nextNInDirectionMatchColour(row-1, col-1, 3, -1, -1, colour)) {
			highlightWin(row, col, 3, -1, -1);
			return true; //down left
		}
		if (nextNInDirectionMatchColour(row-1, col, 3, -1, 0, colour)) {
			highlightWin(row, col, 3, -1, 0);
			return true; //down
		}
		if (nextNInDirectionMatchColour(row-1, col+1, 3, -1, 1, colour)) {
			highlightWin(row, col, 3, -1, 1);
			return true; // down right
		}
		if (nextNInDirectionMatchColour(row, col+1, 3, 0, 1, colour)) {
			highlightWin(row, col, 3, 0, 1);
			return true; // right
		}
		return false;
	}
	
	//recursively check that the next N pieces in direction (dRow, dCol) match parameter colour
	private Boolean nextNInDirectionMatchColour(int row, int col, int N, int dRow, int dCol, char colour) {
		char thisColour;
		try {
			thisColour = grid[row][col];
		} catch (Exception e) {
			// out of bounds
			return false;
		}
		
		if (N == 1) // base case
			return (thisColour == colour);
		else {
			if (thisColour == colour) // if the local colour matches, go ahead and check the next one
				return nextNInDirectionMatchColour(row+dRow, col+dCol, N-1, dRow, dCol, colour);
			else //if not, just return false
				return false;
		}
	}
	
	private void highlightWin(int row, int col, int N, int dRow, int dCol) {
		grid[row][col] = Character.toUpperCase(grid[row][col]);
		if (N > 0) {
			highlightWin(row+dRow, col+dCol, N-1, dRow, dCol);
		}
	}
	
	private Boolean checkDraw() {
		for (int i = 0; i < 7; i++)
			if (grid[4][i] == ' ')
				return false;
		return true;
	}
}