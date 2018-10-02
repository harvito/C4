/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author mharve17
 */
public class C4 {
	static BufferedReader br;
	public static final int GAME_IN_PROGRESS = 0;
	public static final int GAME_WON_P1 = 1;
	public static final int GAME_WON_P2 = 2;
	public static final int GAME_DRAWN = 3;
	
	Player p1, p2;
	Board board;
	int gameStatus;
	
	
	public C4(Board board, Player p1, Player p2) {
		this.board = board;
		this.p1 = p1;
		this.p2 = p2;
		gameStatus = GAME_IN_PROGRESS;
	}
	
	public void setGameDrawn() {
		gameStatus = GAME_DRAWN;
	}
	
	public void setGameWon(char colour) {
		if (colour == p1.colour)
			gameStatus = GAME_WON_P1;
		else if (colour == p2.colour)
			gameStatus = GAME_WON_P2;
		else
			; //TODO throw an exception? this should never happen
	}
	
	void start() {
		//MAIN GAME LOOP
		Boolean isP1Turn = true;
		while (gameStatus == GAME_IN_PROGRESS) {
			board.print();
			if (isP1Turn)
				p1.play(board);
			else
				p2.play(board);
			
			board.updateGameStatus(this);
			
			isP1Turn = !isP1Turn;
		}
		
		board.print();
		
		switch (gameStatus) {
		case GAME_WON_P1:
			System.out.println("Player 1 wins! Thanks for playing!");
			break;
		case GAME_WON_P2:
			System.out.println("Player 2 wins! Thanks for playing!");
			break;
		case GAME_DRAWN:
			System.out.println("It's a draw! Thanks for playing!");
			break;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		C4 c4 = new C4(new Board(), new HumanPlayer('x'), new RandomPlayer('o'));
		c4.start();
		System.exit(0);
	}
    
}